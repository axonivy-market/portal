# Using Portal Selenium Tests as Engine Smoke Tests

A short handover guide for customers who want a minimal automated check that
an Axon Ivy engine + Portal installation is healthy.

This kit runs **two tests** — `LoginTest` and `TopbarTest` — which confirm
that login works and the Portal UI renders. Broader coverage isn't supported
here on purpose; see § 8.

---

## 1. Honest expectations first

The Portal test suite under
[`AxonIvyPortal/portal-selenium-test`](../AxonIvyPortal/portal-selenium-test)
is our **internal regression suite**, not a black-box smoke harness. Before
you adopt it, please read these caveats:

1. **A helper application must be deployed on the target engine.**
   `TopbarTest` extends
   [`BaseTest`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java),
   which uses `PortalKitTestHelper` endpoints
   (`/PortalKitTestHelper/.../login.ivp`, `cleanData.ivp`,
   `updatePortalSetting.ivp`) to log in as `demo`, clean test data, and
   toggle portal settings — without the helper iar deployed, `TopbarTest`
   fails at setup. `LoginTest` exercises Portal's real login UI and
   doesn't strictly need the helper, but the smoke kit ships both tests
   as a pair, so deploy all three iars per § 3.
2. **The two smoke tests authenticate as `admin` and `demo`.** Both users
   are provisioned automatically by the portal iar's
   [`config/users.yaml`](../AxonIvyPortal/portal/config/users.yaml) when
   you deploy it (see § 3) — you do **not** need to create them manually
   in your security system. The wider
   [`TestAccount.java`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/TestAccount.java)
   enum lists more accounts (`david`, `emma`, `huang`, `peter`, `gm1`, …)
   used by the broader test suite, but those live in
   `portal-developer-examples` — which the smoke kit deliberately does
   not deploy (see § 8).
3. **The application name defaults to `demo-portal`.** Override it via
   `-Dtest.engine.app=<your-name>` on the Maven CLI or
   `ENGINE_APP=<your-name>` for
   [`run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh).
   The instructions below assume `demo-portal`.
4. **Mixing the helper app into a production engine is not recommended.**
   It exposes process starts that grant permissions and clean data. Use a
   dedicated, non-production engine instance for smoke testing.

---

## 2. Prerequisites

On the machine that will run the tests:

| Tool   | Version             | Notes                                     |
|--------|---------------------|-------------------------------------------|
| JDK    | 25 (Temurin)        | Matches the CI workflow                   |
| Maven  | 3.9.x               |                                           |
| Firefox| Recent stable       | Default browser; Chrome also works        |
| Network access | to the target engine | over HTTP(S) on the Portal port    |

On the target engine:

- Axon Ivy Engine running, with the Portal application deployed.
- Ability to deploy an additional application (`demo-portal`) onto the
  engine — either via the admin UI, REST API, or by dropping iars into
  `system/demo-applications/demo-portal/`.

---

## 3. One-time setup: build & deploy the test helper

The smoke tests need `PortalKitTestHelper` (our login + cleanup helper)
deployed as an Ivy application called `demo-portal`. `PortalKitTestHelper`
depends only on `portal` and `portal-components`, so three iars cover it.

You need a clone of this repository **and an extracted Axon Ivy engine on
the build machine** — the Ivy build plugin spins up a short-lived compile
engine from that directory. Download the engine zip and extract it:

```bash
ENGINE_DIR="$PWD/build-engine"
mkdir -p "$ENGINE_DIR"
wget https://developer.axonivy.com/permalink/nightly/axonivy-engine.zip -O /tmp/ivy.zip
unzip -q /tmp/ivy.zip -d "$ENGINE_DIR"
```

Then, from the repo root, build the three modules:

```bash
for module in \
  AxonIvyPortal/portal-components \
  AxonIvyPortal/portal \
  AxonIvyPortal/PortalKitTestHelper ; do
  mvn clean install -f $module/pom.xml -Divy.engine.directory="$ENGINE_DIR"
done
```

Output: three iars to deploy.

- `AxonIvyPortal/portal-components/target/portal-components-*.iar`
- `AxonIvyPortal/portal/target/portal-*.iar`
- `AxonIvyPortal/PortalKitTestHelper/target/portalKitTestHelper-*.iar`

Drop them into the target engine's demo-applications folder:

```bash
DEPLOYMENT_DIR=/path/to/engine/system/demo-applications/demo-portal
mkdir -p "$DEPLOYMENT_DIR"
cp AxonIvyPortal/portal-components/target/portal-components-*.iar      "$DEPLOYMENT_DIR/"
cp AxonIvyPortal/portal/target/portal-*.iar                            "$DEPLOYMENT_DIR/"
cp AxonIvyPortal/PortalKitTestHelper/target/portalKitTestHelper-*.iar  "$DEPLOYMENT_DIR/"
# Restart the engine (or trigger deployment via the admin UI)
```

> **Warning** — a stock engine ships with its own `demo-portal` application
> preinstalled. The drop-in above will replace it. Run smoke tests against
> a dedicated, non-production engine instance so you don't lose unrelated
> demo data.

After deployment, confirm the app exists and the helper login URL responds:

```
http://<your-engine>:8080/demo-portal/pro/PortalKitTestHelper/1636734E13CEC872/login.ivp
```

That URL is the one the tests use to log in (see
[`BaseTest.java:37`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java)).

---

## 4. Pointing the tests at your already-running engine

Use the ready-made
[`smoke_pom.xml`](../AxonIvyPortal/portal-selenium-test/smoke_pom.xml) in
the test module — it is a copy of `customized_pom.xml` with the
`start.test.engine` and `stop.test.engine` executions removed, so Maven
will **not** spin up its own engine. The surefire `argLine` already wires
`-Dtest.engine.url=${test.engine.url}` and
`-Dtest.engine.app=${test.engine.app}` through to the JVM, which is what
[`ivy-web-test-api`'s `EngineUrl`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java#L21)
reads. Pass both as `-D` properties on the Maven command line.

---

## 5. Running the smoke tests

The quickest path is the shell wrapper
[`run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) — it
sanity-checks the engine, sanity-checks the helper login URL, then runs the
two smoke tests:

```bash
ENGINE_URL=http://your-engine:8080/ \
  ./AxonIvyPortal/portal-selenium-test/run-smoke.sh
```

Or invoke Maven directly:

```bash
mvn clean test \
  -f AxonIvyPortal/portal-selenium-test/smoke_pom.xml \
  -Dtest.engine.url=http://your-engine:8080/ \
  -Dtest.engine.app=demo-portal \
  -Dtest='LoginTest,TopbarTest' \
  -DbrowserType=FIREFOX \
  -Dcapabilities.unhandledPromptBehavior=accept \
  -DtrimStackTrace=false
```

Optional flags:

- `-Dselenide.remote=http://<selenium-grid>:4444/wd/hub` — drive the browser
  from a Selenium Grid / Docker container instead of the local machine.
- `-DadminUserName=... -DadminUserPassword=...` — override the default test
  credentials (see
  [`TestAccount.java`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/TestAccount.java)
  for the full list of overridable system properties). Only useful if you
  customized the portal iar's `users.yaml`.
- `-Dsurefire.rerunFailingTestsCount=2` — retry transient browser failures
  (matches our CI).

### Reading the results

After the run:

- `AxonIvyPortal/portal-selenium-test/target/surefire-reports/` — JUnit XML
  + text per test class.
- `AxonIvyPortal/portal-selenium-test/target/selenide/reports/` — Selenide
  screenshots and page-source dumps from failures. Open these first when
  diagnosing a red test.

---

## 6. Pick your runner

Two ready-to-use entry points ship in this repo. Both end up calling the
same Maven invocation under the hood, so the result is identical.

| Runner                                                                                     | Best for                                              |
|--------------------------------------------------------------------------------------------|-------------------------------------------------------|
| [`AxonIvyPortal/portal-selenium-test/run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) | Local one-off runs, scripted environments, ad-hoc verification |
| [`.github/workflows/portal-selenium-test.yml`](../.github/workflows/portal-selenium-test.yml)    | GitHub-hosted customers — trigger via `workflow_dispatch`. Leave the engine URL blank to **auto-provision** a nightly engine on the runner (no external setup), or paste your own engine URL to test against an existing engine. |

The shell script accepts `ENGINE_URL`, `ENGINE_APP`, `BROWSER`, and
`SELENIDE_REMOTE` as environment variables. The GitHub workflow exposes
only `engineUrl` (and `engineDownloadURL` for the auto-provision mode);
the other parameters are inlined — fork the workflow and edit it if you
need to change them.

Running on a different CI (Jenkins, GitLab CI, Azure Pipelines, …)? The
GitHub workflow is the easiest reference — its "external engine" branch
is a handful of lines that just invoke `run-smoke.sh` with the engine
URL. Translate that pattern into your own pipeline DSL.

These files are starting points. Modify, extend, or replace them as needed
— we don't provide ongoing support.

---

## 7. Things that will trip you up

- **404 on `/PortalKitTestHelper/.../login.ivp`** → the helper iar isn't
  deployed, or is deployed under a different application name than
  `demo-portal`. Either rename the application or change
  `-Dtest.engine.app=`.
- **Login succeeds in the browser but the test fails at the first assertion**
  → you logged in as a *real* user account that lacks the permissions the
  helper app's grant processes assume. Smoke tests must run against the
  helper-app users.
- **Don't run the full `*Test` pattern** on a customer engine. It includes
  long-running flows that mutate substantial state, plus
  screenshot-generation tests that exist only to produce documentation
  artefacts.

---

## 8. Need broader test coverage?

This kit deliberately stops at "Portal is alive." Anything beyond that —
dashboards, search, task workflows, case widgets — requires deploying
multiple additional Ivy projects (`InternalSupport`,
`portal-developer-examples`, `portal-components-examples`) that ship
substantial sample data and processes meant for *our* CI environment, not
yours. Reimplementing that infrastructure on the customer side is more
work than it's worth.

If you need to validate Portal more deeply, the better path is to refer to
**Axon Ivy Portal's published builds and release artefacts** — our CI
exercises the full suite on every commit, and the published test reports
already cover what you'd be trying to recreate. Use that as your evidence
of correctness, not a re-run on your side.
