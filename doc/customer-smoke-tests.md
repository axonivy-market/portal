# Using Portal Selenium Tests as Engine Smoke Tests

A short handover guide for customers who want to reuse a subset of Axon Ivy
Portal's own Selenium tests to verify that an engine + Portal installation is
healthy.

---

## 1. Honest expectations first

The Portal test suite under
[`AxonIvyPortal/portal-selenium-test`](../AxonIvyPortal/portal-selenium-test)
is our **internal regression suite**, not a black-box smoke harness. Before you
adopt it, please read these caveats:

1. **A helper application must be deployed on the target engine.** Every test
   extends
   [`BaseTest`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java),
   which logs users in by calling a process inside our `PortalKitTestHelper`
   project (`/PortalKitTestHelper/.../login.ivp`) and cleans test data via the
   same project. Without the helper app deployed, **every test fails at
   setup**.
2. **Tests assume a fixed set of test users** (`admin/admin`, `demo/demo`,
   `david/david`, `emma/emma`, `huang/huang`, `peter/peter`, `gm1/gm1`, …)
   defined in
   [`TestAccount.java`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/TestAccount.java).
   These users are provisioned automatically by each helper project's
   [`config/users.yaml`](../Showcase/InternalSupport/config/users.yaml)
   when you deploy `demo-portal` (see § 3) — you do **not** need to create
   them manually in your security system.
3. **The application name is hardcoded to `demo-portal`** in
   [`customized_pom.xml`](../AxonIvyPortal/portal-selenium-test/customized_pom.xml)
   (`-Dtest.engine.app=demo-portal`).
4. **Mixing the helper app into a production engine is not recommended.**
   It exposes process starts that grant permissions, clean data, and create
   sample cases. Use a dedicated, non-production engine instance for smoke
   testing.

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
  engine — either via the admin UI, REST API, or by dropping a zip into
  `system/demo-applications/demo-portal/`.

---

## 3. One-time setup: build & deploy the test helper app

The test helper is built from
[`AxonIvyPortal/portal-selenium-test/deployment-pom.xml`](../AxonIvyPortal/portal-selenium-test/deployment-pom.xml).
It bundles four projects required by the tests:

- `portalKitTestHelper` — login + data-cleanup + permission-grant processes
- `internalSupport` — sample business processes (orders, leave requests…)
- `portal-user-examples` — user-facing example processes
- `portal-components-examples` — UI component showcases

### 3.1 Build the deployment zip

You need a clone of this repository **and an extracted Axon Ivy engine on the
build machine** — the Ivy build plugin spins up a short-lived compile engine
from that directory. Download the engine zip you want to build against (the
nightly is fine) and extract it somewhere local:

```bash
ENGINE_DIR="$PWD/build-engine"
mkdir -p "$ENGINE_DIR"
wget https://developer.axonivy.com/permalink/nightly/axonivy-engine.zip -O /tmp/ivy.zip
unzip -q /tmp/ivy.zip -d "$ENGINE_DIR"
```

Then, from the repo root:

```bash
# Build the Portal modules that the deployment zip depends on
for module in \
  AxonIvyPortal/portal-components \
  AxonIvyPortal/portal \
  AxonIvyPortal/PortalKitTestHelper \
  Showcase/portal-user-examples \
  Showcase/InternalSupport \
  Showcase/portal-components-examples ; do
  mvn clean install -f $module/pom.xml -Divy.engine.directory="$ENGINE_DIR"
done

# Build the deployment zip itself
mvn clean package \
  -f AxonIvyPortal/portal-selenium-test/deployment-pom.xml \
  -Divy.engine.directory="$ENGINE_DIR"
```

The resulting zip will be in
`AxonIvyPortal/portal-selenium-test/target/portal-selenium-test-deployment-*.zip`.

### 3.2 Deploy it to the target engine as `demo-portal`

Pick the deployment method that suits your environment. The simplest, matching
our CI, is the file-drop method:

```bash
DEPLOYMENT_DIR=/path/to/engine/system/demo-applications/demo-portal
mkdir -p "$DEPLOYMENT_DIR"
cp AxonIvyPortal/portal-selenium-test/target/portal-selenium-test-deployment-*.zip \
   "$DEPLOYMENT_DIR/"
# Restart the engine (or trigger deployment via the admin UI)
```

> **Warning** — a stock engine ships with its own `demo-portal` application
> preinstalled. The drop-in above will replace it. Run smoke tests against a
> dedicated, non-production engine instance so you don't lose unrelated demo
> data.

After deployment, confirm the app is named `demo-portal` and that
`http://<your-engine>:8080/demo-portal/pro/PortalKitTestHelper/1636734E13CEC872/login.ivp`
exists. That URL is the one the tests use to log in (see
[`BaseTest.java:37`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java)).

---

## 4. Pointing the tests at your already-running engine

Use the ready-made
[`smoke_pom.xml`](../AxonIvyPortal/portal-selenium-test/smoke_pom.xml) in the
test module — it is a copy of `customized_pom.xml` with the `start.test.engine`
and `stop.test.engine` executions removed, so Maven will **not** spin up its
own engine. The surefire `argLine` already wires
`-Dtest.engine.url=${test.engine.url}` and
`-Dtest.engine.app=${test.engine.app}` through to the JVM, which is what
[`ivy-web-test-api`'s `EngineUrl`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java#L21)
reads. Pass both as `-D` properties on the Maven command line.

---

## 5. The recommended smoke-test subset

The tests below are a balance of "broad coverage" and "minimal stateful
setup". Combined runtime: ~15–25 minutes on a warm engine. All live under
`com.axonivy.portal.selenium.test`.

| Test class                  | What it covers                                   |
|-----------------------------|--------------------------------------------------|
| `LoginTest`                 | Form login, failed login, forgot-password flow   |
| `TopbarTest`                | Top navigation bar renders & links work          |
| `MenuTest`                  | Side menu items navigate correctly               |
| `UserHomepageTest`          | Default user dashboard renders                   |
| `dashboard.DashboardTaskWidgetTest`  | Task list widget loads tasks            |
| `dashboard.DashboardCaseWidgetTest`  | Case list widget loads cases            |
| `globalsearch.GlobalSearchTest`      | Global search returns results           |
| `QuickSearchTest`           | Quick-search dropdown                            |

Source for all of these is under
[`AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/test/`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/test).

### Why these specifically

They exercise authentication, top-level navigation, the two core widgets every
Portal install ships with, and search — the things that, if broken, mean
Portal is unusable. They avoid the long-running scenario tests (escalation
flows, cascade permission grants, document-table customization, screenshot
generation) that need substantial sample data and are flaky outside our CI
environment.

---

## 6. Running the subset

The quickest path is the shell wrapper
[`run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) — it
sanity-checks the engine, sanity-checks the helper login URL, then runs the
subset:

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
  -Dtest='LoginTest,TopbarTest,MenuTest,UserHomepageTest,DashboardTaskWidgetTest,DashboardCaseWidgetTest,GlobalSearchTest,QuickSearchTest' \
  -DbrowserType=FIREFOX \
  -Dcapabilities.unhandledPromptBehavior=accept \
  -DtrimStackTrace=false
```

The `-Dtest` list uses simple class names — surefire resolves them across
sub-packages (`dashboard.*`, `globalsearch.*`). Switch to fully-qualified
class names if you customise the subset and run into ambiguity.

Optional flags:

- `-Dselenide.remote=http://<selenium-grid>:4444/wd/hub` — drive the browser
  from a Selenium Grid / Docker container instead of the local machine.
- `-DadminUserName=... -DadminUserPassword=...` — override the default test
  credentials (see
  [`TestAccount.java`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/TestAccount.java)
  for the full list of overridable system properties). Only useful if you
  customized the helper app's `users.xml`.
- `-Dsurefire.rerunFailingTestsCount=2` — retry transient browser failures
  (matches our CI).

### Reading the results

After the run:

- `AxonIvyPortal/portal-selenium-test/target/surefire-reports/` — JUnit XML +
  text per test class.
- `AxonIvyPortal/portal-selenium-test/target/selenide/reports/` — Selenide
  screenshots and page-source dumps from failures. Open these first when
  diagnosing a red test.

---

## 7. Pick your runner

Three ready-to-use entry points ship in this repo. Pick whichever fits your CI
setup. All three end up calling the same Maven invocation under the hood, so
the result is identical.

| Runner                                                                                     | Best for                                              |
|--------------------------------------------------------------------------------------------|-------------------------------------------------------|
| [`AxonIvyPortal/portal-selenium-test/run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) | Local one-off runs, scripted environments, ad-hoc verification |
| [`.github/workflows/portal-smoke-test.yml`](../.github/workflows/portal-smoke-test.yml)    | GitHub-hosted customers — trigger via `workflow_dispatch` with the engine URL as input |
| [`Jenkinsfile.smoke`](../Jenkinsfile.smoke)                                                | Jenkins-hosted customers — parameterised pipeline you can copy into your own job |

All three accept the same parameters (`ENGINE_URL`, `ENGINE_APP`,
`TEST_PATTERN`, `BROWSER`, `SELENIDE_REMOTE`), so switching between them is
just a matter of where the job runs. The GitHub workflow and Jenkinsfile also
offer an optional `BUILD_HELPER_ZIP` step that performs § 3.1 for you and
publishes the resulting deployment zip as a job artefact — you still have to
drop it onto your engine (§ 3.2) yourself.

These files are starting points. Modify, extend, or replace them as needed —
we don't provide ongoing support.

---

## 8. Things that will trip you up

- **404 on `/PortalKitTestHelper/.../login.ivp`** → the helper app isn't
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
