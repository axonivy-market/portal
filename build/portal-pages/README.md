# Portal documentation on GitHub Pages

Maven writes the release manifest and optionally resolves/unpacks development
documentation from its published SNAPSHOT. The workflow downloads retained
release ZIPs with `curl` and extracts them with Python's standard-library ZIP
command. A small Python helper prepares static version/language selectors and
the site's entry page. Pages assembly does not rebuild the documentation or
change Market hosting.

## Release configuration

`pom.xml` defines the exact documentation artifact versions. `versions.json`
maps release directories to Maven property placeholders; Maven filters this
template and writes the resolved manifest into the site. For example, `12.0`
serves artifact `12.0.18.1` at `release/12.0/`.

To update a release, change its version property in `pom.xml`. To add or remove a
release, update the version properties and manifest template; the workflow reads
the resolved manifest to download each configured ZIP. Patch versions replace
content behind the same major.minor URL; this does not create immutable
patch-version URLs.

## Unreleased development documentation

The workflow includes dev docs by default. It evaluates `revision` from the
checkout's `Documentation/pom.xml` and enables the Maven `dev` profile with that
SNAPSHOT version. The standard dependency plugin resolves and unpacks the latest
published `com.axonivy.portal:portal-guide:zip` for that version. Maven runs with
`-U` to check for updated SNAPSHOTs; no separate dev-version property is maintained
in the Pages project. The job summary reports the Maven coordinate and resolved
timestamped build, not an inferred Git commit or workflow run.

The complete ZIP contains English/Japanese guides, screenshots, and Javadoc.
"Dev" means the latest published build of the configured revision, not necessarily
the current master commit. Publication provenance belongs to the producer;
Maven coordinates alone do not establish a source branch or commit.

The existing `portal-release-snapshot` pipeline already invokes Documentation
with `deploySnapshot` set from `deployToRepo`. Manual Documentation runs keep
`deploySnapshot=false` by default. Pages does not change these controls or trigger
a source build/publication. Publish a corrected SNAPSHOT from the intended master
revision before consuming it, following the producer's screenshot prerequisites.

The site keeps all configured releases and adds `snapshot/dev/{en,ja}/`. Version
selectors link between releases and dev. A non-SNAPSHOT revision or unresolvable
dev package fails the build before deployment rather than silently publishing
an incomplete site.

For a release-only site, uncheck `include_dev` or pass `-f include_dev=false`.
Because each deployment replaces the whole site, that explicitly removes any
previously published dev directory.

## Try it locally

From the repository root, with Bash, Java, Maven, Python 3, `curl`, and `jq`
installed:

```bash
python3 -B -m unittest discover -s build/portal-pages -v
mvn -B -ntp clean generate-resources -f build/portal-pages/pom.xml
SITE=target/portal-pages/portal
ARCHIVES=target/portal-pages/archives
mkdir -p "$ARCHIVES"
while IFS=$'\t' read -r VERSION ARTIFACT; do
  ARCHIVE="$ARCHIVES/portal-guide-$ARTIFACT.zip"
  curl --fail --location --retry 3 \
    "https://maven.axonivy.com/com/axonivy/portal/portal-guide/$ARTIFACT/portal-guide-$ARTIFACT.zip" \
    --output "$ARCHIVE"
  python3 -m zipfile -e "$ARCHIVE" "$SITE/release/$VERSION"
done < <(jq -r 'to_entries[] | [.key, .value] | @tsv' "$SITE/versions.json")
python3 -B build/portal-pages/build_site.py
python3 -m http.server 8000 --directory target/portal-pages
```

Open `http://localhost:8000/portal/` yourself. The generated layout is:

```text
target/portal-pages/portal/
  index.html                          # forwards to latest released English guide
  versions.json                       # resolved artifact mapping
  release/12.0/{en,ja}/index.html
  release/12.0/{en,ja}/versions.json    # static selector data
  release/14.0/{en,ja}/index.html
  release/14.0/{en,ja}/versions.json
  snapshot/dev/{en,ja}/index.html      # when dev input is supplied
  snapshot/dev/{en,ja}/versions.json
```

CSS, scripts, images, downloads, search files, and Javadoc stay in each language
directory. The selector keeps its existing UI but reads local JSON instead of
Marketplace's `/api/docs/...` endpoint. Its links include the `/portal` prefix.
The helper fails if the upstream selector format changes instead of silently
publishing a broken selector. The root manifest describes released artifacts;
per-language selector JSON also includes dev when present.

The root `/portal/` page immediately forwards to the highest configured release
in English, currently `release/14.0/en/index.html`. The helper fills the
`@latest@` placeholder in `index.html` at build time, following the reference
project-build-plugin pattern. This is an HTML meta refresh, not a server-side
HTTP redirect; a fallback link is included. Releases are sorted numerically,
and dev is never selected as the default. Other versions and languages remain
available through the documentation's selector.

The local commands above build a release-only site. To include Maven dev docs,
replace the manifest-generation command with:

```sh
DEV_VERSION=$(mvn help:evaluate -Dexpression=revision -q -DforceStdout -f Documentation/pom.xml)
mvn -B -ntp -U clean generate-resources -f build/portal-pages/pom.xml \
  -Pdev "-Dportal.dev.version=$DEV_VERSION"
```

Then continue with release downloads and selector preparation. The workflow also
checks that the evaluated revision ends in `-SNAPSHOT`. Run `clean` before dev
unpacking and release extraction, not afterward: it removes the entire site
output, preventing stale or removed versions from lingering. Use fresh extraction
when rerunning the helper because it rewrites the original selectors.

## Why direct ZIP downloads instead of dependency:unpack?

The published `portal-guide-12.0.18.1.pom` and `portal-guide-14.0.0.pom` both
reference `com.axonivy.portal:doc-config` with the literal parent version
`${revision}`. `maven-dependency-plugin:3.11.0:unpack` reads that descriptor and
fails resolving the parent with HTTP 400, even though the ZIP itself is readable.
The `resolve` goal also fails with `excludeTransitive=true`,
`includeParents=false`, and a matching `-Drevision` value; these options do not
bypass parent resolution.

Producer-side flattening fixes future publications, not these existing release
POMs. Direct ZIP downloads preserve the retained versions without modifying
Nexus metadata. Retained releases therefore use existing command-line tools
rather than Ant or a third-party downloader. Maven still filters the release
manifest, and the `dev` profile uses `dependency:unpack` for corrected SNAPSHOT
publications. This removes `maven-antrun-plugin`, but does not apply
`dependency:unpack` to the broken legacy release POMs. Python's ZIP command
extracts entries without recreating ZIP symlinks; it is already available for
static site assembly.

## GitHub Pages publication

The dedicated `portal-documentation-pages.yml` workflow appears as
**Publish Documentation Pages** in Actions. It runs manually and defaults to
build-only. A11y Report is unchanged. Merging alone does not publish a site.

For validation without publication:

```sh
gh workflow run portal-documentation-pages.yml --repo axonivy-market/portal \
  --ref master
```

Download the `github-pages` artifact to inspect the assembled site. Uploading an
artifact alone does not publish a website. The artifact expires after one day;
this does not remove an already-deployed site.

For public hosting, set Settings > Pages > Source to GitHub Actions. The
`github-pages` environment must permit `master`. Run the workflow on `master`
with `publish` enabled, or use:

```sh
gh workflow run portal-documentation-pages.yml --repo axonivy-market/portal \
  --ref master -f publish=true
```

Only the separate deploy job receives Pages publishing permissions. It requires
an explicit publishing request from `master`; PRs cannot deploy. No `gh-pages`
content branch is needed. Each deployment replaces the complete site, so all
retained releases must be assembled together.

The expected URL is `https://axonivy-market.github.io/portal/`; the deployment
job reports the actual URL. This is public hosting, not an isolated preview.
To take it offline, use Settings > Pages > Unpublish site and avoid another
publishing run. No repository settings, Marketplace links, old URL redirects,
or DocFactory documentation are changed by this build.

Both dev and retained releases consume published Maven ZIPs; only dev uses Maven
artifact resolution because the retained release POMs remain incompatible.
Rebuilding historical releases, changing the existing producer pipeline, and
automatically deploying Pages after producer completion are outside this change.

GitHub Pages enforces its own site-size limit. The helper does not duplicate
that limit or automatically delete old versions.
