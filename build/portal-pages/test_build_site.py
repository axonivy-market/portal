"""Exercise static navigation with unpacked documentation; no network required."""
import json
import tempfile
import unittest
from pathlib import Path
from unittest.mock import patch

from build_site import build_site


class TestBuildSite(unittest.TestCase):
    def setUp(self):
        self.directory = tempfile.TemporaryDirectory()
        self.addCleanup(self.directory.cleanup)
        self.output = Path(self.directory.name) / "site"
        self.output.mkdir()
        self.versions = {"12.0": "12.0.18.1", "14.0": "14.0.0"}
        (self.output / "versions.json").write_text(json.dumps(self.versions))
        for version in self.versions:
            for language in ("en", "ja"):
                self.create_documentation(self.output / "release" / version / language, version, language)

    def create_documentation(self, root, version, language):
        page = ('<html><script>const request = {url: '
                f'"/api/docs/Portal Guide/{version}/{language}"'
                '};</script></html>')
        resources = {
            "index.html": page,
            "guide/deep-page.html": page,
            "_static/theme.css": "body {}",
            "searchindex.js": "Search.setIndex({});",
            "portal-developer-guide/public-api/index.html": "Javadoc",
        }
        for name, content in resources.items():
            path = root / name
            path.parent.mkdir(parents=True, exist_ok=True)
            path.write_text(content)

    def test_builds_both_versions_and_languages_without_losing_resources(self):
        build_site(self.output)
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
        build_site(self.output, "/preview/portal")
        content = (self.output / "release/12.0/en/index.html").read_text()
        self.assertIn('url: "/preview/portal/release/12.0/en/versions.json"', content)

    def test_requires_english_documentation(self):
        (self.output / "release/12.0/en/index.html").unlink()
        with self.assertRaisesRegex(ValueError, "Missing English documentation"):
            build_site(self.output)

    def test_rejects_an_oversized_site(self):
        with patch("build_site.MAX_SITE_BYTES", 1):
            with self.assertRaisesRegex(ValueError, "1 GB"):
                build_site(self.output)

    def test_fails_if_the_upstream_selector_format_changes(self):
        (self.output / "release/12.0/en/index.html").write_text('<script>fetch("/api/docs/new-format")</script>')
        with self.assertRaisesRegex(ValueError, "Unrecognized documentation API reference"):
            build_site(self.output)

    def test_rejects_a_mismatched_artifact_version(self):
        (self.output / "versions.json").write_text(json.dumps({"12.0": "14.0.0"}))
        with self.assertRaisesRegex(ValueError, "Expected a released artifact"):
            build_site(self.output)


if __name__ == "__main__":
    unittest.main()
