import importlib.util
import io
import subprocess
import unittest
import zipfile
from datetime import datetime, timezone
from pathlib import Path
from unittest.mock import patch


spec = importlib.util.spec_from_file_location("daily_build_report", Path(__file__).with_name("daily-build-report.py"))
reporter = importlib.util.module_from_spec(spec)
spec.loader.exec_module(reporter)
NOW = datetime(2026, 10, 7, 1, tzinfo=timezone.utc)
START = datetime(2026, 10, 6, 1, tzinfo=timezone.utc)


def run(run_id=1, branch="master", conclusion="failure", created="2026-10-06T17:30:00Z", **overrides):
    return {"id": run_id, "head_branch": branch, "event": "workflow_dispatch",
            "status": "completed", "conclusion": conclusion, "created_at": created,
            "html_url": f"https://github.com/axonivy-market/portal/actions/runs/{run_id}", **overrides}


def archive(xml):
    data = io.BytesIO()
    with zipfile.ZipFile(data, "w") as reports:
        reports.writestr("surefire-reports/TEST-example.xml", xml)
        reports.writestr("selenide/example.xml", "not a JUnit report")
    return data.getvalue()


class DailyBuildReportTest(unittest.TestCase):
    def test_latest_pass_replaces_failure_and_ignores_other_branches_and_prs(self):
        runs = [run(), run(2, conclusion="success", created="2026-10-07T00:00:00Z"),
                run(3, branch="feature/example"), run(4, event="pull_request")]
        self.assertEqual(2, reporter.latest_run(runs, "master", START, NOW)["id"])

    def test_window_excludes_old_and_after_cutoff_runs(self):
        runs = [run(created="2026-10-06T00:59:59Z"), run(2, created="2026-10-07T01:00:00Z")]
        self.assertIsNone(reporter.latest_run(runs, "master", START, NOW))

    def test_junit_errors_failures_and_flaky_passes(self):
        xml = '''<testsuites><testsuite>
          <testcase classname="Example" name="failed"><failure message="bad"/></testcase>
          <testcase classname="Example" name="error"><error>broken</error></testcase>
          <testcase classname="Example" name="passed"/>
          <testcase classname="Example" name="flaky"><flakyFailure/></testcase>
          <testcase classname="Example" name="skipped"><skipped/></testcase>
          <testcase classname="Example" name="failed"><failure/></testcase>
        </testsuite></testsuites>'''
        self.assertEqual(["Example#error", "Example#failed"], reporter.failed_tests(archive(xml)))

    def test_all_requested_sections_and_branch_scope(self):
        queried = []

        def items(path, key, params=None):
            queried.append((path, params))
            return [run(1, "release/10.0"), run(2, "release/12.0"), run(3)]

        with patch.object(reporter, "items", side_effect=items), \
                patch.object(reporter, "selenium_details",
                             return_value=reporter.format_failed_tests(["Example#failed"])):
            text = reporter.report("axonivy-market/portal", NOW)
        self.assertTrue(text.startswith("### Report 2026-10-07\n"))
        self.assertEqual(5, sum(line.startswith("## ") for line in text.splitlines()))
        self.assertEqual(9, text.count("[failed]"))
        self.assertEqual(1, text.count("**LTS10**"))
        self.assertEqual(2, text.count("  ❌ **Failed Tests (1)**\n\n  ```\n  Example\n  - failed\n  ```"))
        self.assertEqual({"event": "workflow_dispatch", "branch": "release/10.0"}, queried[0][1])

    def test_passes_and_empty_sections_are_omitted(self):
        def items(path, key, params=None):
            return [run(conclusion="failure" if "portal-test.yml" in path else "success")]

        with patch.object(reporter, "items", side_effect=items):
            text = reporter.report("axonivy-market/portal", NOW)
        self.assertIn("## Portal Test", text)
        self.assertNotIn("## Documentation", text)
        self.assertNotIn("## Selenium Test", text)
        with patch.object(reporter, "items", return_value=[run(conclusion="success")]):
            self.assertEqual("", reporter.report("axonivy-market/portal", NOW))

    def test_weekend_uses_vietnam_date_and_never_calls_api(self):
        with patch.object(reporter, "items") as items:
            # Friday UTC, Saturday in Vietnam.
            self.assertEqual("", reporter.report("repo", datetime(2026, 10, 9, 18, tzinfo=timezone.utc)))
            self.assertEqual("", reporter.report("repo", datetime(2026, 10, 11, 1, tzinfo=timezone.utc)))
            items.assert_not_called()

    def test_unfinished_latest_run_does_not_fall_back_to_old_failure(self):
        runs = [run(), run(2, status="in_progress", conclusion=None, created="2026-10-07T00:00:00Z")]
        with patch.object(reporter, "items", return_value=runs):
            self.assertEqual("", reporter.report("repo", NOW))

    def test_missing_artifact_and_no_test_failures_are_explicit(self):
        with patch.object(reporter, "items", return_value=[]):
            self.assertIn("missing or expired", reporter.selenium_details("repo", run()))
        artifacts = [{"id": 1, "name": "artifacts", "expired": False, "created_at": "2026-10-06T20:00:00Z"}]
        with patch.object(reporter, "items", return_value=artifacts), \
                patch.object(reporter, "api", return_value=archive('<testsuite><testcase name="ok"/></testsuite>')):
            self.assertIn("No failed test cases", reporter.selenium_details("repo", run()))

    def test_selenium_artifact_names_on_both_branches(self):
        xml = '<testsuite><testcase classname="com.axonivy.portal.selenium.test.dashboard.DashboardCaseWidgetTest" name="testCustomActionButton"><failure/></testcase></testsuite>'
        expected = "❌ **Failed Tests (1)**\n\n```\nDashboardCaseWidgetTest\n- testCustomActionButton\n```"
        for name in ["artifacts", "selenium-test-reports"]:
            with self.subTest(artifact_name=name):
                artifacts = [
                    {"id": 1, "name": name, "expired": False, "created_at": "2026-10-08T00:00:00Z"},
                    {"id": 2, "name": name, "expired": True, "created_at": "2026-10-08T00:30:00Z"},
                    {"id": 3, "name": "screenshots", "expired": False, "created_at": "2026-10-08T00:40:00Z"},
                ]
                with patch.object(reporter, "items", return_value=artifacts), \
                        patch.object(reporter, "api", return_value=archive(xml)) as api:
                    self.assertEqual(expected, reporter.selenium_details("repo", run()))
                    api.assert_called_once_with("repos/repo/actions/artifacts/1/zip", binary=True)

    def test_failed_tests_are_grouped_by_class(self):
        tests = ["pkg.ZTest#second", "pkg.ATest#first", "pkg.ZTest#first"]
        self.assertEqual(
            "❌ **Failed Tests (3)**\n\n```\nATest\n- first\n\nZTest\n- first\n- second\n```",
            reporter.format_failed_tests(tests),
        )

    def test_pagination_reads_all_pages(self):
        with patch.object(reporter, "api", side_effect=[{"runs": list(range(100))}, {"runs": [100]}]) as api:
            self.assertEqual(list(range(101)), list(reporter.items("path", "runs")))
            self.assertIn("page=2", api.call_args[0][0])

    def test_api_failure_stops_report_instead_of_claiming_no_failures(self):
        with patch.object(reporter, "api", side_effect=subprocess.CalledProcessError(1, "gh api")):
            with self.assertRaises(subprocess.CalledProcessError):
                reporter.report("repo", NOW)

    def test_rerun_uses_newest_artifact(self):
        artifacts = [
            {"id": 1, "name": "artifacts", "expired": False, "created_at": "2026-10-06T18:00:00Z"},
            {"id": 2, "name": "artifacts", "expired": False, "created_at": "2026-10-06T20:00:00Z"},
        ]
        with patch.object(reporter, "items", return_value=artifacts), \
                patch.object(reporter, "api", return_value=archive('<testsuite><testcase name="ok"/></testsuite>')) as api:
            reporter.selenium_details("repo", run())
            api.assert_called_once_with("repos/repo/actions/artifacts/2/zip", binary=True)


if __name__ == "__main__":
    unittest.main()
