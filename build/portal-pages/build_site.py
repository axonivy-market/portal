"""Prepare static selectors and navigation for documentation unpacked by Maven."""
import argparse
import html
import json
import re
from pathlib import Path

API_URL = re.compile(r'''url:\s*(["'])/api/docs/[^"']+\1''')


def version_path(version):
    return f"snapshot/{version}" if version == "dev" else f"release/{version}"


def page_url(base_path, version, language):
    return f"{base_path}/{version_path(version)}/{language}/index.html"


def build_site(output, base_path="/portal"):
    versions = json.loads((output / "versions.json").read_text(encoding="utf-8"))
    if not versions:
        raise ValueError("At least one documentation version is required")
    for version, artifact in versions.items():
        if not re.fullmatch(r"\d+\.\d+", version):
            raise ValueError(f"Invalid release directory: {version}")
        if not re.fullmatch(re.escape(version) + r"\.\d+(?:\.\d+)?", artifact):
            raise ValueError(f"Expected a released artifact of {version}, got {artifact}")
    if not re.fullmatch(r"(?:/[A-Za-z0-9_-]+)*", base_path):
        raise ValueError("Base path must be empty or a path such as /portal")

    ordered_versions = sorted(versions, key=lambda version: tuple(map(int, version.split("."))), reverse=True)
    if (output / "snapshot/dev").is_dir():
        ordered_versions.append("dev")
    languages = {}
    for version in ordered_versions:
        destination = output / version_path(version)
        languages[version] = [language for language in ("en", "ja")
                              if (destination / language / "index.html").is_file()]
        if "en" not in languages[version]:
            raise ValueError(f"Missing English documentation for {version}")

    links = []
    for version in ordered_versions:
        for language in languages[version]:
            language_root = output / version_path(version) / language
            selector = {
                "versions": [
                    {"version": other, "url": page_url(base_path, other,
                     language if language in languages[other] else "en")}
                    for other in ordered_versions
                ],
                "languages": [
                    {"language": other, "url": page_url(base_path, version, other)}
                    for other in languages[version]
                ],
            }
            (language_root / "versions.json").write_text(json.dumps(selector, indent=2) + "\n", encoding="utf-8")
            selector_url = f"{base_path}/{version_path(version)}/{language}/versions.json"
            replacements = 0
            for page in language_root.rglob("*.html"):
                content = page.read_text(encoding="utf-8")
                updated, count = API_URL.subn(lambda match: "url: " + json.dumps(selector_url), content)
                if "/api/docs/" in updated:
                    raise ValueError(f"Unrecognized documentation API reference in {page}")
                if count:
                    page.write_text(updated, encoding="utf-8")
                    replacements += count
            if not replacements:
                raise ValueError(f"No supported version selector found for {version}/{language}")
            url = html.escape(page_url(base_path, version, language), quote=True)
            links.append(f'<li><a href="{url}">Portal {version} ({language})</a></li>')

    landing = ('<!doctype html>\n<html lang="en"><head><meta charset="utf-8">'
               '<meta name="viewport" content="width=device-width, initial-scale=1">'
               '<title>Portal documentation</title></head><body>'
               '<h1>Portal documentation</h1><ul>' + "".join(links) + '</ul></body></html>\n')
    (output / "index.html").write_text(landing, encoding="utf-8")
    (output / ".nojekyll").touch()
    (output / "versions.json").write_text(json.dumps(versions, indent=2) + "\n", encoding="utf-8")
    print(f"Prepared documentation versions {', '.join(ordered_versions)} at {output}")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--output", type=Path, default=Path("target/portal-pages/portal"))
    parser.add_argument("--base-path", default="/portal")
    args = parser.parse_args()
    build_site(args.output, args.base_path)


if __name__ == "__main__":
    main()
