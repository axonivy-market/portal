"""Download all published schema versions into the Pages site. Run from the repository root."""

import base64
import os
import re
import subprocess
import xml.etree.ElementTree as ET
from pathlib import Path
from urllib.request import Request, urlopen


def main():
    site = Path("target/portal-pages/portal")
    metadata_url = (
        "https://maven.pkg.github.com/axonivy-market/portal/"
        "com/axonivy/portal/portal-schema/maven-metadata.xml"
    )
    credentials = base64.b64encode(
        f"{os.environ['GITHUB_ACTOR']}:{os.environ['GITHUB_TOKEN']}".encode("utf-8")
    ).decode("ascii")
    request = Request(metadata_url, headers={"Authorization": f"Basic {credentials}"})
    with urlopen(request, timeout=60) as response:
        metadata = ET.fromstring(response.read())
    versions = list(dict.fromkeys(
        node.text.strip()
        for node in metadata.findall("./versioning/versions/version")
        if node.text and node.text.strip()
    ))
    if not versions:
        raise SystemExit("No published schema versions found in Maven metadata")
    for version in versions:
        if not re.fullmatch(r"[A-Za-z0-9][A-Za-z0-9._-]*", version):
            raise SystemExit(f"Unsafe schema version in Maven metadata: {version!r}")
    print(f"Discovered schema versions: {', '.join(versions)}", flush=True)

    for version in versions:
        subprocess.run([
            "mvn", "-B", "-ntp", "-U",
            "-f", ".github/scripts/schema/pom.xml",
            "org.apache.maven.plugins:maven-dependency-plugin:3.8.1:unpack",
            f"-Dartifact=com.axonivy.portal:portal-schema:{version}:zip",
            f"-DoutputDirectory={(site / 'schemas' / version).resolve()}",
        ], check=True)
        print(f"Added schemas for Portal {version}", flush=True)


if __name__ == "__main__":
    main()
