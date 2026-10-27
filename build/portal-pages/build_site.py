"""Assemble released Portal documentation ZIPs into a standalone static site."""
import argparse
import html
import json
import re
import stat
import urllib.request
import zipfile
from pathlib import Path, PurePosixPath

MAX_SITE_BYTES = 1_000_000_000
API_URL = re.compile(r'''url:\s*(["'])/api/docs/[^"']+\1''')
MAVEN_URL = "https://maven.axonivy.com/com/axonivy/portal/portal-guide"


def page_url(base_path, version, language):
    return f"{base_path}/release/{version}/{language}/index.html"


def build_site(versions, archives, output, base_path="/portal"):
    if not versions:
        raise ValueError("At least one documentation version is required")
    for version, artifact in versions.items():
        if not re.fullmatch(r"\d+\.\d+", version):
            raise ValueError(f"Invalid release directory: {version}")
        if not re.fullmatch(re.escape(version) + r"\.\d+(?:\.\d+)?", artifact):
            raise ValueError(f"Expected a released artifact of {version}, got {artifact}")
    if not re.fullmatch(r"(?:/[A-Za-z0-9_-]+)*", base_path):
        raise ValueError("Base path must be empty or a path such as /portal")
    if output.exists() and any(output.iterdir()):
        raise ValueError(f"Output directory must be empty: {output}")

    output.mkdir(parents=True, exist_ok=True)
    archives.mkdir(parents=True, exist_ok=True)
    languages = {}
    total_bytes = 0
    for version, artifact in versions.items():
        archive = archives / f"portal-guide-{artifact}.zip"
        if not archive.exists():
            url = f"{MAVEN_URL}/{artifact}/{archive.name}"
            print(f"Downloading {url}")
            partial = archive.with_suffix(".zip.part")
            urllib.request.urlretrieve(url, partial)
            partial.replace(archive)
        with zipfile.ZipFile(archive) as document:
            for entry in document.infolist():
                path = PurePosixPath(entry.filename)
                if path.is_absolute() or ".." in path.parts or stat.S_ISLNK(entry.external_attr >> 16):
                    raise ValueError(f"Unsafe ZIP entry: {entry.filename}")
                total_bytes += entry.file_size
            if total_bytes > MAX_SITE_BYTES:
                raise ValueError("Documentation exceeds the 1 GB Pages site budget; retain fewer versions")
            destination = output / "release" / version
            document.extractall(destination)
        languages[version] = [language for language in ("en", "ja")
                              if (destination / language / "index.html").is_file()]
        if "en" not in languages[version]:
            raise ValueError(f"Missing English documentation in {archive.name}")

    ordered_versions = sorted(versions, key=lambda version: tuple(map(int, version.split("."))), reverse=True)
    links = []
    for version in ordered_versions:
        for language in languages[version]:
            language_root = output / "release" / version / language
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
            selector_url = f"{base_path}/release/{version}/{language}/versions.json"
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
    size = sum(path.stat().st_size for path in output.rglob("*") if path.is_file())
    if size > MAX_SITE_BYTES:
        raise ValueError("Assembled site exceeds the 1 GB Pages site budget")
    print(f"Built {len(versions)} versions: {size / 1024 ** 2:.2f} MiB at {output}")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--versions", type=Path, default=Path(__file__).with_name("versions.json"))
    parser.add_argument("--archives", type=Path, default=Path("target/portal-pages-cache"))
    parser.add_argument("--output", type=Path, default=Path("target/portal-pages/portal"))
    parser.add_argument("--base-path", default="/portal")
    args = parser.parse_args()
    build_site(json.loads(args.versions.read_text(encoding="utf-8")), args.archives, args.output, args.base_path)


if __name__ == "__main__":
    main()
