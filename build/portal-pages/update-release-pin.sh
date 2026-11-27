#!/usr/bin/env bash
set -euo pipefail

usage() {
    printf 'Usage: %s <release-line> <artifact-version>\n' "$0"
}

if [[ $# -ne 2 ]]; then
    usage >&2
    exit 2
fi

release_line=$1
artifact_version=$2
if [[ ! "$release_line" =~ ^[0-9]+\.[0-9]+$ ]]; then
    printf 'Invalid release line: %s\n' "$release_line" >&2
    exit 1
fi
if [[ ! "$artifact_version" =~ ^[0-9]+\.[0-9]+\.[0-9]+(\.[0-9]+)?$ ]] || \
    [[ "$artifact_version" != "$release_line".* ]]; then
    printf 'Artifact version %s does not match release line %s\n' \
        "$artifact_version" "$release_line" >&2
    exit 1
fi

major=${release_line%%.*}
property_name="portal${major}.version"
pom=build/portal-pages/pom.xml
mapfile -t current_versions < <(sed -nE "s|^[[:space:]]*<${property_name}>([^<]*)</${property_name}>[[:space:]]*$|\1|p" "$pom")
if [[ ${#current_versions[@]} -ne 1 ]]; then
    printf 'Expected exactly one %s property in %s; found %s\n' \
        "$property_name" "$pom" "${#current_versions[@]}" >&2
    exit 1
fi

current_version=${current_versions[0]}
if [[ "$artifact_version" == "$current_version" ]] ||
   [[ $(printf '%s\n' "$current_version" "$artifact_version" | sort -V | tail -n 1) != "$artifact_version" ]]; then
    printf 'Keeping %s for %s; %s is not newer\n' "$current_version" "$release_line" "$artifact_version"
    exit 0
fi

group_path=com/axonivy/portal
if [[ "$release_line" == 10.0 ]]; then
    group_path=ch/ivyteam/ivy/project/portal
fi
guide_url="https://maven.axonivy.com/$group_path/portal-guide/$artifact_version/portal-guide-$artifact_version.zip"
archive_file=$(mktemp)
trap 'rm -f "$archive_file"' EXIT
curl --fail --location --retry 3 "$guide_url" --output "$archive_file"
unzip -tqq "$archive_file"

mvn -B -ntp -f "$pom" \
    org.codehaus.mojo:versions-maven-plugin:2.23.0:set-property \
    "-Dproperty=$property_name" \
    "-DnewVersion=$artifact_version" \
    -DgenerateBackupPoms=false
