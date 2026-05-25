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
   These users are provisioned automatically by the helper application's
   `config/users.yaml` when you deploy it (see § 4) — you do **not** need to
   create them manually in your security system.
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
  engine — either via the admin UI, REST API, or by dropping files into
  `system/demo-applications/demo-portal/`.

---

## 3. Pick your tier

The 8 originally-recommended smoke tests don't all carry the same setup cost.
Some only need the login helper; others need additional Ivy projects deployed
to produce sample tasks, cases, and search data. Pick the tier that fits how
much setup you're willing to do.

| Tier | Tests included | Helper projects required on engine | Runtime | What it tells you |
|------|----------------|-------------------------------------|---------|---------------------|
| **1 — "Is Portal alive?"** *(default)* | `LoginTest`, `TopbarTest` | `PortalKitTestHelper` only | ~2–3 min | Login works, Portal UI renders, top nav resolves |
| **2 — "+ navigation & dashboard"** | Tier 1 + `MenuTest`, `UserHomepageTest` | Tier 1 + `portal-developer-examples` | ~5–7 min | + side menu navigates, default dashboard renders with sample tasks |
| **3 — "Full curated smoke"** | Tier 2 + `DashboardTaskWidgetTest`, `DashboardCaseWidgetTest`, `GlobalSearchTest`, `QuickSearchTest` | Tier 2 + `InternalSupport` + `portal-components-examples` | ~15–25 min | + task/case widgets, global search, quick search |

> All three runners ([`run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh),
> [the GitHub workflow](../.github/workflows/portal-selenium-test.yml), and
> [`Jenkinsfile.smoke`](../Jenkinsfile.smoke)) default to **Tier 1**. To run a
> higher tier, override the `TEST_PATTERN` parameter (see § 6).

**Recommendation:** start with Tier 1. It catches the failure mode customers
care most about — "I upgraded the engine and Portal won't even load" — without
forcing you to deploy Ivy projects that mimic our internal Showcase apps. Add
Tier 2/3 later if you want broader coverage.

---

## 4. One-time setup: build & deploy the test helper app(s)

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

### 4.1 Tier 1 build — minimal (`PortalKitTestHelper` only)

```bash
for module in \
  AxonIvyPortal/portal-components \
  AxonIvyPortal/portal \
  AxonIvyPortal/PortalKitTestHelper ; do
  mvn clean install -f $module/pom.xml -Divy.engine.directory="$ENGINE_DIR"
done
```

Output you need to deploy (three iars):

- `AxonIvyPortal/portal-components/target/portal-components-*.iar`
- `AxonIvyPortal/portal/target/portal-*.iar`
- `AxonIvyPortal/PortalKitTestHelper/target/portalKitTestHelper-*.iar`

### 4.2 Tier 2 build — add the dashboard sample data

In addition to the Tier 1 modules:

```bash
mvn clean install -f Showcase/portal-developer-examples/pom.xml \
  -Divy.engine.directory="$ENGINE_DIR"
```

Extra iar to deploy: `Showcase/portal-developer-examples/target/portal-developer-examples-*.iar`.

### 4.3 Tier 3 build — full deployment zip

This packs every helper iar into one deployment zip — the same one we use in
our own CI. Builds all four Showcase/helper modules, then assembles the zip:

```bash
for module in \
  AxonIvyPortal/portal-components \
  AxonIvyPortal/portal \
  AxonIvyPortal/PortalKitTestHelper \
  Showcase/portal-developer-examples \
  Showcase/InternalSupport \
  Showcase/portal-components-examples ; do
  mvn clean install -f $module/pom.xml -Divy.engine.directory="$ENGINE_DIR"
done

mvn clean package \
  -f AxonIvyPortal/portal-selenium-test/deployment-pom.xml \
  -Divy.engine.directory="$ENGINE_DIR"
```

Output: `AxonIvyPortal/portal-selenium-test/target/portal-selenium-test-deployment-*.zip`.

### 4.4 Deploy to the engine as the `demo-portal` application

The simplest method (matching our CI) is dropping files into the engine's
demo-applications folder, which the engine picks up on startup.

**For Tiers 1 & 2 (individual iars):**

```bash
DEPLOYMENT_DIR=/path/to/engine/system/demo-applications/demo-portal
mkdir -p "$DEPLOYMENT_DIR"
# Tier 1
cp AxonIvyPortal/portal-components/target/portal-components-*.iar      "$DEPLOYMENT_DIR/"
cp AxonIvyPortal/portal/target/portal-*.iar                            "$DEPLOYMENT_DIR/"
cp AxonIvyPortal/PortalKitTestHelper/target/portalKitTestHelper-*.iar  "$DEPLOYMENT_DIR/"
# Tier 2 also adds:
cp Showcase/portal-developer-examples/target/portal-developer-examples-*.iar "$DEPLOYMENT_DIR/"
# Restart the engine (or trigger deployment via the admin UI)
```

**For Tier 3 (deployment zip):**

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

After deployment, confirm the app exists and the helper login URL responds:

```
http://<your-engine>:8080/demo-portal/pro/PortalKitTestHelper/1636734E13CEC872/login.ivp
```

That URL is the one the tests use to log in (see
[`BaseTest.java:37`](../AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/common/BaseTest.java)).

---

## 5. Pointing the tests at your already-running engine

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

## 6. Running the smoke tests

The quickest path is the shell wrapper
[`run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) — it
sanity-checks the engine, sanity-checks the helper login URL, then runs the
tier you pick.

### Tier 1 (default)

```bash
ENGINE_URL=http://your-engine:8080/ \
  ./AxonIvyPortal/portal-selenium-test/run-smoke.sh
```

### Tier 2

```bash
ENGINE_URL=http://your-engine:8080/ \
TEST_PATTERN='LoginTest,TopbarTest,MenuTest,UserHomepageTest' \
  ./AxonIvyPortal/portal-selenium-test/run-smoke.sh
```

### Tier 3

```bash
ENGINE_URL=http://your-engine:8080/ \
TEST_PATTERN='LoginTest,TopbarTest,MenuTest,UserHomepageTest,DashboardTaskWidgetTest,DashboardCaseWidgetTest,GlobalSearchTest,QuickSearchTest' \
  ./AxonIvyPortal/portal-selenium-test/run-smoke.sh
```

### Or invoke Maven directly

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
the result is identical. All three default to **Tier 1** — override
`TEST_PATTERN` to run a higher tier.

| Runner                                                                                     | Best for                                              |
|--------------------------------------------------------------------------------------------|-------------------------------------------------------|
| [`AxonIvyPortal/portal-selenium-test/run-smoke.sh`](../AxonIvyPortal/portal-selenium-test/run-smoke.sh) | Local one-off runs, scripted environments, ad-hoc verification |
| [`.github/workflows/portal-selenium-test.yml`](../.github/workflows/portal-selenium-test.yml)    | GitHub-hosted customers — trigger via `workflow_dispatch`. Leave the engine URL blank to **auto-provision** a nightly engine on the runner (no external setup), or paste your own engine URL to test against an existing engine. |
| [`Jenkinsfile.smoke`](../Jenkinsfile.smoke)                                                | Jenkins-hosted customers — parameterised pipeline you can copy into your own job |

All three accept the same parameters (`ENGINE_URL`, `ENGINE_APP`,
`TEST_PATTERN`, `BROWSER`, `SELENIDE_REMOTE`), so switching between them is
just a matter of where the job runs. The Jenkinsfile also offers an optional
`BUILD_HELPER_ZIP` step that performs § 4.3 for you and publishes the
resulting deployment zip as a job artefact — you still have to drop it onto
your engine (§ 4.4) yourself.

These files are starting points. Modify, extend, or replace them as needed —
we don't provide ongoing support.

---

## 8. Things that will trip you up

- **404 on `/PortalKitTestHelper/.../login.ivp`** → the helper app isn't
  deployed, or is deployed under a different application name than
  `demo-portal`. Either rename the application or change
  `-Dtest.engine.app=`.
- **Tier 2/3 tests fail with "process not found" / 404 during setup** → you
  didn't deploy the helper projects that tier needs. Re-check § 3 vs § 4 — a
  Tier 3 test pattern on a Tier 1 deployment will fail because the showcase
  processes the tests invoke aren't on the engine.
- **Login succeeds in the browser but the test fails at the first assertion**
  → you logged in as a *real* user account that lacks the permissions the
  helper app's grant processes assume. Smoke tests must run against the
  helper-app users.
- **Don't run the full `*Test` pattern** on a customer engine. It includes
  long-running flows that mutate substantial state, plus
  screenshot-generation tests that exist only to produce documentation
  artefacts.
