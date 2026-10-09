#!/usr/bin/env bash
set -euo pipefail

report_script=$(cd "$(dirname "$0")" && pwd)/daily-build-report.sh
tmp=$(mktemp -d)
trap 'rm -rf "$tmp"' EXIT
mkdir "$tmp/reports"
cat > "$tmp/reports/TEST-example.xml" <<'XML'
<testsuite>
  <testcase classname="pkg.ZTest" name="second"><error/></testcase>
  <testcase classname="pkg.ATest" name="first"><failure/></testcase>
  <testcase classname="pkg.ZTest" name="first"><failure/></testcase>
  <testcase classname="pkg.ZTest" name="first"><failure/></testcase>
  <testcase classname="pkg.ZTest" name="passed"/>
  <testcase classname="pkg.ZTest" name="flaky"><flakyFailure/></testcase>
</testsuite>
XML
(cd "$tmp" && zip -q artifact.zip reports/TEST-example.xml)

cat > "$tmp/gh" <<'GH'
#!/usr/bin/env bash
set -euo pipefail
path=
for arg in "$@"; do
  [[ $arg == repos/* ]] && path=$arg
done
if [[ ${RUN_SCENARIO:-} == api_error ]]; then exit 1; fi
if [[ $path == */zip ]]; then
  cat "$TEST_TMP/artifact.zip"
  exit
fi
if [[ $path == */artifacts* ]]; then
  if [[ ${RUN_SCENARIO:-} == missing_artifact ]]; then
    json='{"artifacts":[]}'
  else
    json='{"artifacts":[{"id":100,"name":"artifacts","expired":false,"created_at":"2026-10-06T18:00:00Z"}]}'
  fi
else
  workflow=${path#*/actions/workflows/}
  workflow=${workflow%%/*}
  branch=${path##*branch=}
  branch=${branch%%&*}
  branch=${branch//%2F/\/}
  case ${RUN_SCENARIO:-all_failed} in
    no_runs) json='{"workflow_runs":[]}' ;;
    *)
      conclusion=failure
      status=completed
      created=2026-10-06T17:30:00Z
      started=$created
      if [[ ${RUN_SCENARIO:-} == incomplete && $workflow == portal-test.yml ]]; then
        status=in_progress
      fi
      if [[ ${RUN_SCENARIO:-} == old_creation ]]; then
        created=2026-10-01T00:00:00Z
      fi
      if [[ ${RUN_SCENARIO:-} == outside_window ]]; then
        started=2026-10-06T00:59:59Z
      fi
      json=$(jq -nc --arg branch "$branch" --arg status "$status" --arg conclusion "$conclusion" \
          --arg created "$created" --arg started "$started" '
        {workflow_runs:[{id:10, head_branch:$branch, event:"workflow_dispatch",
          status:$status, conclusion:$conclusion, created_at:$created,
          run_started_at:$started,
          html_url:"https://github.com/axonivy-market/portal/actions/runs/10"}]}')
      if [[ ${RUN_SCENARIO:-} == rerun_success && $workflow == portal-test.yml ]]; then
        json=$(jq -c '.workflow_runs += [(.workflow_runs[0] | .id = 11
          | .conclusion = "success" | .run_started_at = "2026-10-07T00:00:00Z")]' <<< "$json")
      fi ;;
  esac
fi
while (( $# )); do
  if [[ $1 == --jq ]]; then
    jq -c "$2" <<< "$json"
    exit
  fi
  shift
done
printf '%s\n' "$json"
GH
chmod +x "$tmp/gh"

export PATH="$tmp:$PATH"
export TEST_TMP="$tmp"
export GITHUB_REPOSITORY=axonivy-market/portal
export GITHUB_STEP_SUMMARY="$tmp/summary.md"
export REPORT_NOW=2026-10-07T01:00:00Z

bash "$report_script" > "$tmp/log"
grep -Fx '### Report 2026-10-07' "$GITHUB_STEP_SUMMARY" > /dev/null
[[ $(grep -c '^## ' "$GITHUB_STEP_SUMMARY") == 5 ]]
[[ $(grep -c '\[failed\]' "$GITHUB_STEP_SUMMARY") == 9 ]]
[[ $(grep -c '❌ \*\*Failed Tests (3)\*\*' "$GITHUB_STEP_SUMMARY") == 2 ]]
grep -F -- '- **LTS10**' "$GITHUB_STEP_SUMMARY" > /dev/null
grep -Fx -- '  - first' "$GITHUB_STEP_SUMMARY" > /dev/null

export RUN_SCENARIO=rerun_success
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
! grep -q '^## Portal Test' "$GITHUB_STEP_SUMMARY"

export RUN_SCENARIO=old_creation
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
[[ $(grep -c '\[failed\]' "$GITHUB_STEP_SUMMARY") == 9 ]]

export RUN_SCENARIO=outside_window
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
[[ ! -e $GITHUB_STEP_SUMMARY ]]

export RUN_SCENARIO=incomplete
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
grep -F '::warning::Portal Test MASTER: no completed latest run' "$tmp/log" > /dev/null
! grep -q '^## Portal Test' "$GITHUB_STEP_SUMMARY"

export RUN_SCENARIO=missing_artifact
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
grep -F 'test artifact missing or expired' "$GITHUB_STEP_SUMMARY" > /dev/null

export RUN_SCENARIO=no_runs
rm -f "$GITHUB_STEP_SUMMARY"
bash "$report_script" > "$tmp/log"
[[ ! -e $GITHUB_STEP_SUMMARY ]]
grep -Fx 'No failed builds to report.' "$tmp/log" > /dev/null

export RUN_SCENARIO=api_error
if bash "$report_script" > "$tmp/log" 2>&1; then
  echo 'API failure unexpectedly succeeded' >&2
  exit 1
fi

export REPORT_NOW=2026-10-09T18:00:00Z
bash "$report_script" > "$tmp/log"
grep -Fx 'Weekend in Vietnam; no report.' "$tmp/log" > /dev/null
echo 'Daily build report tests passed.'
