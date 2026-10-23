# Portal Pages experiment

Maven fetches and unpacks released documentation. A small Python helper prepares
static version/language selectors and the site's entry page. The existing
Sphinx, screenshot, and Javadoc build, Maven publication, and Market hosting
remain unchanged.

## Release configuration

`pom.xml` defines the exact documentation artifact versions and their destination
release directories. `versions.json` is a template: Maven replaces its property
placeholders with those versions and writes the resolved manifest into the site.
For example, `12.0` serves artifact `12.0.18.1` at `release/12.0/`.

To update a release, change its version property in `pom.xml`. To add or remove a
release, update both the Maven download/unzip tasks and the manifest template. Patch
versions replace content behind the same major.minor URL; this does not create
immutable patch-version URLs.

## Try it locally

From the repository root, with Java, Maven, and Python 3 installed:

```sh
python3 -B -m unittest discover -s build/portal-pages -v
mvn -B -ntp clean generate-resources -f build/portal-pages/pom.xml
python3 -B build/portal-pages/build_site.py
python3 -m http.server 8000 --directory target/portal-pages
```

Open `http://localhost:8000/portal/` yourself. The generated layout is:

```text
target/portal-pages/portal/
  index.html
  versions.json                       # resolved artifact mapping
  release/12.0/{en,ja}/index.html
  release/12.0/{en,ja}/versions.json    # static selector data
  release/14.0/{en,ja}/index.html
  release/14.0/{en,ja}/versions.json
```

CSS, scripts, images, downloads, search files, and Javadoc stay in each language
directory. The selector keeps its existing UI but reads local JSON instead of
Marketplace's `/api/docs/...` endpoint. Its links include the `/portal` prefix.
The helper fails if the upstream selector format changes instead of silently
publishing a broken selector.

Maven's standard Ant `get`/`unzip` tasks download the ZIPs into
`target/portal-pages/archives/` and unpack them. Direct ZIP retrieval avoids
resolving older published POMs whose parent version is still `${revision}`;
`maven-dependency-plugin:unpack` fails on that metadata for `12.0.18.1`.
`clean` removes only this build's `target/portal-pages/` output before assembling
the complete site, preventing stale or removed releases from lingering.

## GitHub Pages publication

The dedicated `portal-documentation-pages.yml` workflow appears as
**Documentation Pages experiment** in Actions. It runs manually and defaults to
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

Connecting fresh Sphinx/screenshots/Javadoc output directly to Pages is a
follow-up; this release-only build still consumes published ZIPs.

The helper currently checks the complete uncompressed site against a 1 GB budget
before deployment; it does not automatically delete old versions.
