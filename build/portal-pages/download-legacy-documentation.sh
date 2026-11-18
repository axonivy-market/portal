#!/usr/bin/env bash
set -euo pipefail

# Download retained release ZIPs without resolving their incompatible Maven POMs.
SITE_DIRECTORY=target/portal-pages/portal
RELEASE_MANIFEST="$SITE_DIRECTORY/versions.json"
ARCHIVE_DIRECTORY=target/portal-pages/archives
GUIDE_REPOSITORY_URL=https://maven.axonivy.com/com/axonivy/portal/portal-guide

usage() {
    printf '%s\n' \
        "Usage: $0 [--help]" \
        '' \
        'Download and extract releases listed in the Maven-generated versions.json.' \
        'Run the Pages Maven preparation step first.' \
        '' \
        'Options:' \
        '  -h, --help    Show this help.'
}

download_legacy_releases() {
    local release_version artifact_version archive_path
    mkdir -p "$ARCHIVE_DIRECTORY"

    # These published release POMs have unresolved ${revision} parents.
    # Download their ZIPs directly; corrected dev SNAPSHOTs use Maven dependency:unpack.

    # jq reads JSON. List release names (12.0, 14.0), one per line.
    # --raw-output removes JSON quotes so Bash can read each name directly.
    jq --raw-output 'keys[]' "$RELEASE_MANIFEST" |
        while read -r release_version; do
            # Look up the artifact version for this release: 12.0 -> 12.0.18.1.
            # --arg passes the release name into the JSON query as $release.
            artifact_version=$(jq --raw-output --arg release "$release_version" \
                '.[$release]' "$RELEASE_MANIFEST")
            archive_path="$ARCHIVE_DIRECTORY/portal-guide-$artifact_version.zip"
            printf 'Downloading Portal documentation %s for release/%s\n' \
                "$artifact_version" "$release_version"
            curl --fail --location --retry 3 \
                "$GUIDE_REPOSITORY_URL/$artifact_version/portal-guide-$artifact_version.zip" \
                --output "$archive_path"
            # Use Python's standard-library extractor without recreating ZIP symlinks.
            python3 -m zipfile -e "$archive_path" "$SITE_DIRECTORY/release/$release_version"
        done
}

main() {
    case "${1:-}" in
        -h|--help)
            usage
            return
            ;;
        '') ;;
        *)
            printf 'Unknown option: %s\n' "$1" >&2
            usage >&2
            return 1
            ;;
    esac

    cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.."
    if [ ! -f "$RELEASE_MANIFEST" ]; then
        printf 'Missing release manifest: %s. Run the Pages Maven preparation step first.\n' \
            "$RELEASE_MANIFEST" >&2
        return 1
    fi
    download_legacy_releases
}

main "$@"
