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
property_count=$(grep -Ec "^[[:space:]]*<${property_name}>[^<]*</${property_name}>[[:space:]]*$" "$pom" || true)
if [[ "$property_count" -ne 1 ]]; then
    printf 'Expected exactly one %s property in %s; found %s\n' \
        "$property_name" "$pom" "$property_count" >&2
    exit 1
fi

guide_url="https://maven.axonivy.com/com/axonivy/portal/portal-guide/${artifact_version}/portal-guide-${artifact_version}.zip"
curl --fail --location --retry 3 --head "$guide_url"

mvn -B -ntp -f "$pom" \
    org.codehaus.mojo:versions-maven-plugin:2.23.0:set-property \
    "-Dproperty=$property_name" \
    "-DnewVersion=$artifact_version" \
    -DgenerateBackupPoms=false
