#!/usr/bin/env bash
set -euo pipefail

cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.."
site_directory=target/portal-pages/portal
version=$(jq -er '.["10.0"] | select(test("^10\\.0\\.[0-9]+(\\.[0-9]+)?$"))' "$site_directory/versions.json")
archive=$(mktemp)
trap 'rm -f "$archive"' EXIT

# LTS10 uses the legacy group ID ch.ivyteam.ivy.project.portal, unlike 12.0 and 14.0.
# The pinned release POM has an unresolved ${revision} parent. Fetch the ZIP directly.
guide_url=https://maven.axonivy.com/ch/ivyteam/ivy/project/portal/portal-guide
curl --fail --location --retry 3 "$guide_url/$version/portal-guide-$version.zip" --output "$archive"
python3 -m zipfile -e "$archive" "$site_directory/release/10.0"
