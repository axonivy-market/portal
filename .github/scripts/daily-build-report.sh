#!/usr/bin/env bash
set -euo pipefail

# Report the latest completed dispatched run created per branch in the preceding 24 hours.
# Failures and cancellations go into the summary; branches without a completed run are warnings.
repo=${GITHUB_REPOSITORY:?}
now=$(date --universal --date "${REPORT_NOW:-now}" +%s)
local_day=$(TZ=Asia/Ho_Chi_Minh date --date "@$now" +%u)
if (( local_day >= 6 )); then
  echo 'Weekend in Vietnam; no report.'
  exit 0
fi

start=$((now - 86400))
start_iso=$(date --universal --date "@$start" +%Y-%m-%dT%H:%M:%SZ)
report_date=$(TZ=Asia/Ho_Chi_Minh date --date "@$now" +%Y-%m-%d)
workdir=$(mktemp --directory)
# Remove temporary files when the script exits, including on errors.
trap 'rm --recursive --force "$workdir"' EXIT

latest_completed_run() {
  local workflow=$1 branch=$2
  # Request completed runs from every API page; each response contains a workflow_runs array.
  # Emit its individual runs, then collect them into one array for precise time filtering.
  gh api --paginate --method GET "repos/$repo/actions/workflows/$workflow/runs" \
    --raw-field event=workflow_dispatch --raw-field status=completed --raw-field branch="$branch" \
    --raw-field "created=>=$start_iso" --raw-field per_page=100 \
    --jq '.workflow_runs[]' |
    jq --slurp --arg branch "$branch" --argjson start "$start" --argjson end "$now" '
      map(select(.head_branch == $branch and .event == "workflow_dispatch" and .status == "completed")
        | select((.created_at | fromdateiso8601) >= $start
          and (.created_at | fromdateiso8601) < $end))
      | max_by([.created_at, .id]) // empty' # Output nothing when there is no matching run.
}

failed_tests() {
  local archive=$1 cases=$workdir/cases.xml.txt name failure_count
  unzip -tqq "$archive" || return 1
  : > "$cases"
  while IFS= read -r name; do
    [[ ${name##*/} == TEST-*.xml ]] || continue
    failure_count=$(unzip -p "$archive" "$name" |
      xmlstarlet sel -T -t -v 'count(//testcase[failure or error])') || return 1
    [[ $failure_count =~ ^[0-9]+$ ]] || return 1
    (( failure_count == 0 )) && continue
    unzip -p "$archive" "$name" |
      xmlstarlet sel -t -m '//testcase[failure or error]' \
        -v '@classname' -o $'\t' -v '@name' -n >> "$cases" || return 1
  done < <(unzip -Z1 "$archive")
  jq --raw-input --raw-output --slurp '
    split("\n") | map(select(length > 0) | split("\t")
      | {full: (if .[0] == "" then "UnknownClass" else .[0] end),
         method: (if .[1] == "" then "UnknownTest" else .[1] end)})
    | unique_by(.full, .method) | sort_by((.full | split(".") | last), .full, .method)
    | if length == 0 then "No failed test cases recorded; check the build log."
      else "❌ **Failed Tests (\(length))**\n\n```\n" +
        (group_by(.full) | sort_by((.[0].full | split(".") | last), .[0].full) | map(
          (.[0].full | split(".") | last) + "\n" +
          (map("- " + .method) | join("\n"))) | join("\n\n")) + "\n```" end
  ' "$cases"
}

selenium_details() {
  local run_id=$1 artifact_id archive=$workdir/report.zip
  # Collect artifacts from all pages and select the newest unexpired test report's ID.
  if ! artifact_id=$(gh api --paginate "repos/$repo/actions/runs/$run_id/artifacts?per_page=100" \
      --jq '.artifacts[]' | jq --slurp --raw-output '
        map(select((.name == "artifacts" or .name == "selenium-test-reports") and .expired == false))
        | max_by([.created_at, .id]) | .id // empty'); then
    echo "::warning::Unable to read Selenium test details for run $run_id: artifact lookup failed." >&2
    echo 'Failed test details unavailable; check the build log.'
    return
  fi
  # -z tests whether the artifact ID is an empty string.
  if [[ -z $artifact_id ]]; then
    echo 'Failed test details unavailable: test artifact missing or expired.'
    return
  fi
  if ! gh api "repos/$repo/actions/artifacts/$artifact_id/zip" > "$archive" ||
     ! failed_tests "$archive"; then
    echo "::warning::Unable to read Selenium test details for run $run_id: invalid artifact or XML." >&2
    echo 'Failed test details unavailable; check the build log.'
  fi
}

sections=()
# Read each literal BUILDS line into three fields separated by |; -r preserves backslashes.
while IFS='|' read -r title workflow branches; do
  entries=()
  for pair in $branches; do
    label=${pair%%:*}
    branch=${pair#*:}
    run=$(latest_completed_run "$workflow" "$branch")
    if [[ -z $run || $(jq --raw-output '.status' <<< "$run") != completed ]]; then
      echo "::warning::$title $label: no completed run in the reporting window."
      continue
    fi
    conclusion=$(jq --raw-output '.conclusion' <<< "$run")
    run_id=$(jq --raw-output '.id' <<< "$run")
    case $conclusion in
      failure|timed_out|startup_failure|action_required) result=failed ;;
      cancelled) result=cancelled ;;
      success) continue ;;
      *) echo "::warning::$title $label: $conclusion (run $run_id)."; continue ;;
    esac
    link="[$result]($(jq --raw-output '.html_url' <<< "$run"))"
    case $title in
      'Document Screenshot') entry=$(printf -- '- **%s**\n  - %s' "$label" "$link") ;;
      'A11y Report') entry=$link ;;
      *) entry="- **$label** $link" ;;
    esac
    if [[ $title == 'Selenium Test' && $conclusion != cancelled ]]; then
      details=$(selenium_details "$run_id")
      entry+=$'\n\n'"$(sed '/./s/^/  /' <<< "$details")"
    fi
    entries+=("$entry")
  done
  if (( ${#entries[@]} )); then
    section="## $title"
    for entry in "${entries[@]}"; do section+=$'\n\n'"$entry"; done
    sections+=("$section")
  fi
done <<'BUILDS'
Document Screenshot|portal-document-screenshot-test.yml|LTS10:release/10.0 LTS12:release/12.0 MASTER:master
Documentation|portal-documentation.yml|LTS10:release/10.0 LTS12:release/12.0 MASTER:master
Selenium Test|portal-selenium-test.yml|LTS12:release/12.0 MASTER:master
Portal Test|portal-test.yml|MASTER:master
A11y Report|a11y-report.yml|MASTER:master
BUILDS

if (( ${#sections[@]} == 0 )); then
  echo 'No failed or cancelled builds to report.'
  exit 0
fi
summary="### Report $report_date"
for section in "${sections[@]}"; do summary+=$'\n\n'"$section"; done
printf '%s\n' "$summary" | tee --append "${GITHUB_STEP_SUMMARY:?}"
