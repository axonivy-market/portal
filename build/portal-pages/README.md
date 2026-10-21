# Portal Pages experiment

This is a hosting prototype, not a replacement for the documentation build.
It assembles existing released ZIPs so we can examine static hosting separately
from Sphinx, screenshots, and Marketplace changes.

## Read these files in order

1. `versions.json`: the release directories and exact documentation artifacts to publish.
2. `build_site.py`: unpack the ZIPs, replace the selector's API URL with static JSON,
   generate that JSON and a landing page, and check the total site size.
3. `../../.github/workflows/portal-documentation-pages.yml`: build an artifact by default;
   deploy it only when explicitly requested from `master`.

For example, `"12.0": "12.0.18.1"` means the `12.0` documentation URL serves
artifact `12.0.18.1`. Updating that artifact replaces the content behind the
same stable URL. This prototype does not publish immutable patch-version URLs
or snapshots.

## Try it locally

From the repository root:

```sh
python3 -B -m unittest discover -s build/portal-pages -v
python3 -B build/portal-pages/build_site.py
python3 -m http.server 8000 --directory target/portal-pages
```

Open `http://localhost:8000/portal/` yourself. The generated layout is:

```text
target/portal-pages/portal/
  index.html
  versions.json                       # exact artifact mapping
  release/12.0/{en,ja}/index.html
  release/12.0/{en,ja}/versions.json    # static selector data
  release/14.0/{en,ja}/index.html
  release/14.0/{en,ja}/versions.json
```

All original CSS, scripts, images, downloads, search files, and Javadoc remain
inside each language directory. The selector keeps its existing UI but reads
static JSON instead of Marketplace's `/api/docs/...` endpoint. Each selector
JSON contains links to all configured releases and that release's languages.
No Marketplace documentation API is required by the generated selector.

ZIPs are cached under `target/portal-pages-cache/`. The output directory must
be empty before a rebuild; remove only `target/portal-pages/portal/` to rebuild.
The workflow uses a fresh runner each time and assembles the entire configured
site, so publishing one release does not silently discard the others.

## What this does not change

- The existing Sphinx/screenshot workflows, Maven ZIP publication, and Market hosting.
- GitHub Pages settings, Marketplace links, or old documentation URL redirects.
- Other branches or the DocFactory documentation.

The new workflow is manual and defaults to **build only**. Its build job can
read the repository; only its separate deploy job receives Pages publishing
permissions. Publishing requires `master`, an explicit `publish` input, and
Pages configured to use GitHub Actions. No `gh-pages` content branch is needed:
`actions/deploy-pages` publishes the assembled artifact directly.

The generated-HTML URL replacement is deliberately limited to the existing
selector format and fails if that format changes. If this hosting approach is
accepted, integrating static selector generation into the source build is a
separate follow-up, not something this experiment silently changes.

The 1 GB check uses the **uncompressed, complete site**, not ZIP download sizes.
Exceeding it fails the build before deployment; the publisher does not delete
old versions automatically or claim compression bypasses the hosting limit.
