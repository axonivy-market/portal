# Portal documentation on GitHub Pages

Collect published documentation ZIPs and adapt their navigation for GitHub Pages.
The code and configuration below define how assembly and publication work.

## Local preview

Requires Bash, Java, Maven, Python 3, curl, and jq. Maven unpacks the 12.0 and 14.0 releases and, optionally, the development SNAPSHOT. The preview downloads the English-only 10.0 archive directly because its published Maven POM cannot be resolved.
From the repository root:

```sh
./build/portal-pages/preview-documentation.sh
```

To include development documentation:

```sh
./build/portal-pages/preview-documentation.sh -DincludeDev=true
```

Open <http://localhost:8000/portal/> yourself; stop the preview with Ctrl+C.
Use the script's `--help` option for usage.

## Release publication

1. Publish stable documentation from the matching branch: `master` (14.0), `release/12.0`, or `release/10.0`. The Documentation workflow proposes a Pages pin PR on `master` after the guide ZIP is published. Verify the guide on Nexus and merge the PR.
2. Run Jenkins `create-release` on the same branch with `deployToRepo=true`. After deploying the release, Jenkins starts the Pages workflow on `master`, which requires the exact release pin.

Merging a pin does not trigger Pages. A successful development documentation SNAPSHOT publication from `master` refreshes Pages directly, without a pin PR. Automated runs keep the live stable pins for lines they are not releasing, so a dev refresh cannot publish a staged stable guide early.

## Where to look

- [preview-documentation.sh](preview-documentation.sh): local preparation and serving.
- [pom.xml](pom.xml) and [versions.json](versions.json): artifact versions and Maven preparation.
- [build_site.py](build_site.py): static selectors and navigation.
- [Pages workflow](../../.github/workflows/portal-documentation-pages.yml): CI assembly and publication controls.
