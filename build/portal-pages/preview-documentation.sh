#!/usr/bin/env bash
set -euo pipefail

# Assemble published Portal documentation and serve it locally. Nothing is published.

usage() {
    printf '%s\n' \
        "Usage: $0 [Maven options]" \
        '' \
        'Assemble the retained releases and preview them at http://localhost:8000/portal/.' \
        'Add -DincludeDev=true to include the latest published documentation SNAPSHOT.' \
        'Additional options are passed to Maven. Stop the preview with Ctrl+C.' \
        '' \
        'Options:' \
        '  -h, --help    Show this help without building or starting a server.'
}

main() {
    case "${1:-}" in
        -h|--help)
            usage
            return
            ;;
    esac

    # Run relative to the repository root, regardless of the caller's directory.
    cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.."

    printf '\nTesting static site assembly...\n'
    python3 -B -m unittest discover -s build/portal-pages -v

    printf '\nPreparing the release manifest and optional dev documentation with Maven...\n'
    mvn -U clean generate-resources -f build/portal-pages/pom.xml "$@"

    printf '\nDownloading and extracting legacy releases...\n'
    ./build/portal-pages/download-legacy-documentation.sh

    printf '\nPreparing static selectors and navigation...\n'
    python3 -B build/portal-pages/build_site.py

    printf '\nPreview: http://localhost:8000/portal/ (Ctrl+C to stop)\n'
    exec python3 -m http.server 8000 --bind 127.0.0.1 --directory target/portal-pages
}

main "$@"
