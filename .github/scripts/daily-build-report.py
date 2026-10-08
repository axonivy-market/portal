"""Report latest dispatched builds in the 24 hours ending at 08:00 Vietnam time.

Only failed builds appear in GITHUB_STEP_SUMMARY. Missing/incomplete runs are
warnings in the job log. Manual runs use today's date and also skip weekends.
Requires the GitHub CLI and GH_TOKEN with actions:read access.
"""

import io
import json
import os
import subprocess
import xml.etree.ElementTree as ET
import zipfile
from datetime import datetime, timedelta, timezone
from pathlib import Path
from urllib.parse import urlencode
from zoneinfo import ZoneInfo


VIETNAM = ZoneInfo("Asia/Ho_Chi_Minh")
BUILDS = [
    ("Document Screenshot", "portal-document-screenshot-test.yml",
     [("LTS10", "release/10.0"), ("LTS12", "release/12.0"), ("MASTER", "master")]),
    ("Documentation", "portal-documentation.yml",
     [("LTS12", "release/12.0"), ("MASTER", "master")]),
    ("Selenium Test", "portal-selenium-test.yml",
     [("LTS12", "release/12.0"), ("MASTER", "master")]),
    ("Portal Test", "portal-test.yml", [("MASTER", "master")]),
    ("A11y Report", "a11y-report.yml", [("MASTER", "master")]),
]
FAILED = {"failure", "timed_out", "startup_failure", "action_required"}


def api(path, binary=False):
    result = subprocess.run(
        ["gh", "api", path], check=True, stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    return result.stdout if binary else json.loads(result.stdout)


def items(path, key, params=None):
    page = 1
    while True:
        query = urlencode({**(params or {}), "per_page": 100, "page": page})
        batch = api(f"{path}?{query}")[key]
        yield from batch
        if len(batch) < 100:
            return
        page += 1


def timestamp(value):
    return datetime.fromisoformat(value.replace("Z", "+00:00"))


def latest_run(runs, branch, start, end):
    # Filter locally too: the repository's documentation job notes stale branch
    # results from the API. Do not filter by conclusion: a newer pass wins.
    candidates = [run for run in runs
                  if run["head_branch"] == branch
                  and run["event"] == "workflow_dispatch"
                  and start <= timestamp(run["created_at"]) < end]
    return max(candidates, key=lambda run: (run["created_at"], run["id"]), default=None)


def failed_tests(archive):
    failures = set()
    with zipfile.ZipFile(io.BytesIO(archive)) as reports:
        for name in reports.namelist():
            if not Path(name).name.startswith("TEST-") or not name.endswith(".xml"):
                continue
            suite = ET.fromstring(reports.read(name))
            for test in suite.iter("testcase"):
                if test.find("failure") is not None or test.find("error") is not None:
                    failures.add(f"{test.get('classname', 'UnknownClass')}#{test.get('name', 'UnknownTest')}")
    return sorted(failures)


def selenium_details(repo, run):
    try:
        artifacts = list(items(f"repos/{repo}/actions/runs/{run['id']}/artifacts", "artifacts"))
        artifacts = [artifact for artifact in artifacts
                     if artifact["name"] in {"artifacts", "selenium-test-reports"} and not artifact["expired"]]
        if not artifacts:
            return "Failed test details unavailable: test artifact missing or expired."
        # Re-runs can leave multiple artifacts; use the newest report only.
        artifact = max(artifacts, key=lambda value: (value["created_at"], value["id"]))
        tests = failed_tests(api(f"repos/{repo}/actions/artifacts/{artifact['id']}/zip", binary=True))
        return "\n".join(tests) or "No failed test cases recorded; check the build log."
    except (subprocess.CalledProcessError, zipfile.BadZipFile, ET.ParseError) as error:
        print(f"::warning::Unable to read Selenium test details for run {run['id']}: {type(error).__name__}")
        return "Failed test details unavailable; check the build log."


def report(repo, now):
    local = now.astimezone(VIETNAM)
    if local.weekday() >= 5:
        print("Weekend in Vietnam; no report.")
        return ""
    end = local.replace(hour=8, minute=0, second=0, microsecond=0)
    start = end - timedelta(days=1)
    utc = lambda value: value.astimezone(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    sections = []
    for title, workflow, branches in BUILDS:
        runs = list(items(f"repos/{repo}/actions/workflows/{workflow}/runs", "workflow_runs",
                          {"event": "workflow_dispatch", "created": f"{utc(start)}..{utc(end)}"}))
        entries = []
        for label, branch in branches:
            run = latest_run(runs, branch, start, end)
            if run is None or run["status"] != "completed":
                print(f"::warning::{title} {label}: no completed latest run in the reporting window.")
                continue
            if run["conclusion"] not in FAILED:
                if run["conclusion"] != "success":
                    print(f"::warning::{title} {label}: {run['conclusion']} (run {run['id']}).")
                continue
            link = f"[failed]({run['html_url']})"
            if title == "Document Screenshot":
                entry = f"- **{label}**\n  - {link}"
            elif title == "A11y Report":
                entry = link
            else:
                entry = f"- **{label}** {link}"
            if title == "Selenium Test":
                # Keep names inside an indented code block, including any backticks.
                details = selenium_details(repo, run)
                entry += "\n\n" + "\n".join("      " + line for line in details.splitlines())
            entries.append(entry)
        if entries:
            sections.append(f"## {title}\n\n" + "\n\n".join(entries))
    if not sections:
        print("No failed builds to report.")
        return ""
    return f"### Report {local:%Y-%m-%d}\n\n" + "\n\n".join(sections) + "\n"


def main():
    summary = report(os.environ["GITHUB_REPOSITORY"], datetime.now(timezone.utc))
    if summary:
        with open(os.environ["GITHUB_STEP_SUMMARY"], "a", encoding="utf-8") as output:
            output.write(summary)
        print(summary)


if __name__ == "__main__":
    main()
