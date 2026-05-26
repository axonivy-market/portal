#!/usr/bin/env bash
# Portal smoke-test runner.
#
# Runs LoginTest + TopbarTest against an already-running engine. The engine
# must have Portal installed and the PortalKitTestHelper iar deployed under
# app name demo-portal (see doc/customer-smoke-tests.md).
#
# Configurable via environment variables:
#   ENGINE_URL       Target engine root URL.        Default: http://localhost:8080/
#   ENGINE_APP       Deployed helper app name.      Default: demo-portal
#   TEST_PATTERN     Surefire -Dtest value.         Default: LoginTest,TopbarTest
#   BROWSER          Selenide -DbrowserType value.  Default: FIREFOX
#   SELENIDE_REMOTE  Selenium Grid URL (optional).  Default: empty (local browser)
#   MAVEN            Maven executable.              Default: mvn
#
# Exit code is the Maven exit code. Reports land under:
#   AxonIvyPortal/portal-selenium-test/target/surefire-reports/
#   AxonIvyPortal/portal-selenium-test/target/selenide/reports/

set -euo pipefail

ENGINE_URL="${ENGINE_URL:-http://localhost:8080/}"
ENGINE_APP="${ENGINE_APP:-demo-portal}"
TEST_PATTERN="${TEST_PATTERN:-LoginTest,TopbarTest}"
BROWSER="${BROWSER:-FIREFOX}"
SELENIDE_REMOTE="${SELENIDE_REMOTE:-}"
MAVEN="${MAVEN:-mvn}"

ENGINE_URL="${ENGINE_URL%/}/"

if [[ ! "$ENGINE_URL" =~ ^https?:// ]]; then
  echo "ERROR: ENGINE_URL must start with http:// or https:// (got: $ENGINE_URL)" >&2
  exit 1
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
SMOKE_POM="$SCRIPT_DIR/smoke_pom.xml"

if [[ ! -f "$SMOKE_POM" ]]; then
  echo "ERROR: $SMOKE_POM not found. Run this from a clone of the Portal repo." >&2
  exit 1
fi

echo "==> Checking engine is reachable at $ENGINE_URL"
if ! curl -fsS -o /dev/null --connect-timeout 5 --max-time 15 "$ENGINE_URL"; then
  echo "ERROR: cannot reach $ENGINE_URL — check that the engine is running and the URL is correct." >&2
  exit 2
fi

LOGIN_PROBE="${ENGINE_URL}${ENGINE_APP}/pro/PortalKitTestHelper/1636734E13CEC872/login.ivp?username=admin&password=admin"
echo "==> Checking helper app is deployed (probing $LOGIN_PROBE)"
probe_status="$(curl -fsS -o /dev/null -w '%{http_code}' --connect-timeout 5 --max-time 30 -L "$LOGIN_PROBE" || true)"
if [[ ! "$probe_status" =~ ^[23][0-9][0-9]$ ]]; then
  echo "ERROR: helper login URL returned '${probe_status:-no response}' — the demo-portal helper application does not appear to be deployed or the engine is not healthy."
  echo "       See doc/customer-smoke-tests.md section 3 for how to build and deploy it."
  exit 3
fi
echo "    helper login URL responded ($probe_status)"

MVN_ARGS=(
  clean test
  -f "$SMOKE_POM"
  "-Dtest=$TEST_PATTERN"
  "-Dtest.engine.url=$ENGINE_URL"
  "-Dtest.engine.app=$ENGINE_APP"
  "-DbrowserType=$BROWSER"
  -Dcapabilities.unhandledPromptBehavior=accept
  -DtrimStackTrace=false
  -Dsurefire.rerunFailingTestsCount=2
)

if [[ -n "$SELENIDE_REMOTE" ]]; then
  MVN_ARGS+=("-Dselenide.remote=$SELENIDE_REMOTE")
fi

echo "==> $MAVEN ${MVN_ARGS[*]}"
set +e
( cd "$REPO_ROOT" && "$MAVEN" "${MVN_ARGS[@]}" )
status=$?
set -e

echo
echo "Reports:"
echo "  Surefire:  $SCRIPT_DIR/target/surefire-reports/"
echo "  Selenide:  $SCRIPT_DIR/target/selenide/reports/"
exit "$status"
