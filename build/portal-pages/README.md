# Portal documentation on GitHub Pages

Collect published documentation ZIPs and adapt their navigation for GitHub Pages.
The code and configuration below define how assembly and publication work.

## Local preview

Requires Bash, Java, Maven, and Python 3 on your machine. Maven resolves and unpacks both retained release ZIPs and, optionally, the development SNAPSHOT.
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

## Where to look

- [preview-documentation.sh](preview-documentation.sh): local preparation and serving.
- [pom.xml](pom.xml) and [versions.json](versions.json): artifact versions and Maven preparation.
- [build_site.py](build_site.py): static selectors and navigation.
- [Pages workflow](../../.github/workflows/portal-documentation-pages.yml): CI assembly and publication controls.
