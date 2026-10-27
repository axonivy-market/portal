"""Exercise the publisher with tiny ZIPs; no network or Ivy engine required."""
import json
import tempfile
import unittest
import zipfile
from pathlib import Path
from unittest.mock import patch

from build_site import build_site


class TestBuildSite(unittest.TestCase):
    def setUp(self):
        self.directory = tempfile.TemporaryDirectory()
        self.addCleanup(self.directory.cleanup)
        self.root = Path(self.directory.name)
        self.archives = self.root / "archives"
        self.archives.mkdir()
        self.output = self.root / "site"
        self.versions = {"12.0": "12.0.18.1", "14.0": "14.0.0"}
        for version, artifact in self.versions.items():
            with zipfile.ZipFile(self.archives / f"portal-guide-{artifact}.zip", "w") as archive:
                for language in ("en", "ja"):
                    page = ('<html><script>const request = {url: '
                            f'"/api/docs/Portal Guide/{version}/{language}"'
                            '};</script></html>')
                    archive.writestr(f"{language}/index.html", page)
                    archive.writestr(f"{language}/guide/deep-page.html", page)
                    archive.writestr(f"{language}/_static/theme.css", "body {}")
                    archive.writestr(f"{language}/searchindex.js", "Search.setIndex({});")
                    archive.writestr(f"{language}/portal-developer-guide/public-api/index.html", "Javadoc")

    def test_builds_both_versions_and_languages_without_losing_resources(self):
        build_site(self.versions, self.archives, self.output)
        for version in self.versions:
            for language in ("en", "ja"):
                root = self.output / "release" / version / language
                self.assertEqual((root / "_static/theme.css").read_text(), "body {}")
                self.assertTrue((root / "searchindex.js").is_file())
                self.assertEqual((root / "portal-developer-guide/public-api/index.html").read_text(), "Javadoc")
                selector = json.loads((root / "versions.json").read_text())
                self.assertEqual([item["version"] for item in selector["versions"]], ["14.0", "12.0"])
                self.assertEqual([item["language"] for item in selector["languages"]], ["en", "ja"])
                for item in selector["versions"] + selector["languages"]:
                    self.assertTrue((self.output / item["url"].removeprefix("/portal/")).is_file())
                for page in [root / "index.html", root / "guide/deep-page.html"]:
                    self.assertIn(f'url: "/portal/release/{version}/{language}/versions.json"', page.read_text())
                    self.assertNotIn("/api/docs/", page.read_text())
        self.assertIn("Portal 14.0 (ja)", (self.output / "index.html").read_text())
        self.assertTrue((self.output / ".nojekyll").is_file())

    def test_supports_a_different_project_prefix(self):
        build_site(self.versions, self.archives, self.output, "/preview/portal")
        content = (self.output / "release/12.0/en/index.html").read_text()
        self.assertIn('url: "/preview/portal/release/12.0/en/versions.json"', content)

    def test_refuses_to_overwrite_existing_output(self):
        self.output.mkdir()
        (self.output / "keep.txt").write_text("unrelated")
        with self.assertRaisesRegex(ValueError, "must be empty"):
            build_site(self.versions, self.archives, self.output)
        self.assertEqual((self.output / "keep.txt").read_text(), "unrelated")

    def test_rejects_a_zip_path_outside_the_site(self):
        with zipfile.ZipFile(self.archives / "portal-guide-12.0.18.1.zip", "a") as archive:
            archive.writestr("../../escaped.txt", "unsafe")
        with self.assertRaisesRegex(ValueError, "Unsafe ZIP entry"):
            build_site(self.versions, self.archives, self.output)
        self.assertFalse((self.root / "escaped.txt").exists())

    def test_stops_before_extracting_an_oversized_site(self):
        with patch("build_site.MAX_SITE_BYTES", 1):
            with self.assertRaisesRegex(ValueError, "1 GB"):
                build_site(self.versions, self.archives, self.output)
        self.assertFalse((self.output / "release").exists())

    def test_fails_if_the_upstream_selector_format_changes(self):
        with zipfile.ZipFile(self.archives / "portal-guide-12.0.18.1.zip", "w") as archive:
            archive.writestr("en/index.html", '<script>fetch("/api/docs/new-format")</script>')
        with self.assertRaisesRegex(ValueError, "Unrecognized documentation API reference"):
            build_site(self.versions, self.archives, self.output)

    def test_rejects_a_mismatched_artifact_version(self):
        with self.assertRaisesRegex(ValueError, "Expected a released artifact"):
            build_site({"12.0": "14.0.0"}, self.archives, self.output)


if __name__ == "__main__":
    unittest.main()
