# Compiler Warnings Awareness

## Idea

Adapted from [axonivy-market/smart-workflow#220](https://github.com/axonivy-market/smart-workflow/pull/220).

The goal is to keep the Portal codebase free from unnoticed compiler warning regressions.
Any push or pull request that **increases** the number of compiler warnings compared to a
baseline is automatically flagged via:

1. A dedicated GitHub **check run** named `Compiler Warnings` (visible in the PR Checks section) — marks the run as `action_required` if warnings increased.
2. A **PR comment** posted automatically when warnings increase on a pull request — unmissable, no need to dig into the checks panel.

### Differences from smart-workflow

The original smart-workflow script only compares against the latest successful run
on the **default branch (`master`)**. This works well once the workflow is merged to
`master` (so a baseline exists), but is **silent** on feature branches before the
first merge — there is no master baseline, so the script always reports "success" even
when warnings increase commit-by-commit.

**Portal's additions:**
- Dual-baseline strategy: tries `master` first, falls back to the previous successful
  run on the **same branch** when no master baseline exists.
- PR comment posted by the script when `increased === true` during a `pull_request` event.

---

## What It Handles

| Concern | How it is handled |
|---|---|
| New `[WARNING]` lines introduced by Maven | Converted to `::warning::` GitHub annotations at build time via `sed` |
| ECJ-style `WARNING in File.java (at line N)` messages | Converted to `::warning file=…,line=N::` annotations |
| Warning count delta — master baseline | `warnings-awareness.cjs` tries the last successful `master` run first |
| Warning count delta — branch fallback | If no master baseline found, falls back to the previous successful run on the **same branch** (catches regressions before the first master merge) |
| Visible gate on every PR (checks) | A `Compiler Warnings` check run is created: `action_required` if warnings increased, `success` otherwise |
| Explicit PR notification | A PR comment is posted when warnings increase during a `pull_request` event |

---

## Files Added

```
.github/
├── scripts/
│   └── warnings-awareness.cjs   ← Node.js script (runs via actions/github-script)
└── workflows/
    └── ci.yml                   ← New GitHub Actions workflow
```

### `.github/scripts/warnings-awareness.cjs`

A CommonJS module executed by `actions/github-script`. It:

1. Iterates over every job in the **current** workflow run and collects all annotations
   with `annotation_level === 'warning'` (every `::warning::` line emitted by the build,
   including Node.js deprecation notices from runner actions).
2. Determines the baseline using a **two-step priority**:
   - **Step 1 — master baseline**: looks for the last successful run of `ci.yml` on
     the default branch. This is the canonical reference once the workflow is merged.
   - **Step 2 — branch fallback**: if no master baseline exists (e.g., the workflow has
     never run on `master` yet), looks for the previous successful run on the **current
     branch**. This ensures regressions are caught on feature branches from the very
     first run.
3. Computes the delta (`current − baseline`).
4. Creates a GitHub **check run** (`github.rest.checks.create`) with:
   - `conclusion: 'success'` — if the warning count did not increase.
   - `conclusion: 'action_required'` — if new warnings were introduced.
5. **Posts a PR comment** when `increased === true` and the event is `pull_request`,
   so developers don't need to actively look for the check run result.
6. Exposes `current`, `baseline`, `delta`, and `status` as step outputs for the
   summary step.

### `.github/workflows/ci.yml`

A two-job workflow:

#### `build` job
- Checks out the repository, sets up Java 25 (Temurin) and Maven 3.9.8.
- Downloads and extracts the Axonivy Engine (nightly) into `$GITHUB_WORKSPACE/ivy/engine`.
- Builds all portal modules sequentially (`portal-components` → `portal` →
  `PortalKitTestHelper` → Showcase modules) with `mvn clean install -DskipTests`.
- Maven output is piped through two `sed` transforms that convert warning lines to
  GitHub annotation format (`::warning::` or `::warning file=…,line=N::`).

#### `warnings-awareness` job
- Runs **after** `build` and only when the build succeeded.
- Executes `warnings-awareness.cjs` via `actions/github-script@v8`.
- Appends a summary table (current / baseline / delta / status) to the
  `$GITHUB_STEP_SUMMARY`.

---

## Required Permissions

The workflow uses minimal permissions:

```yaml
permissions:
  actions: read        # list workflow runs and jobs
  contents: read       # checkout
  checks: write        # create the "Compiler Warnings" check run
  pull-requests: write # post the PR comment when warnings increase
```

> Removing any of these will break the corresponding feature (e.g., removing
> `checks: write` causes a 403 on `github.rest.checks.create`; removing
> `pull-requests: write` prevents the PR comment).

---

## Prerequisites — GitHub CLI (`gh`)

Install `gh` once (no sudo needed):

```bash
GH_VERSION=$(curl -s https://api.github.com/repos/cli/cli/releases/latest \
  | grep '"tag_name"' | cut -d'"' -f4 | sed 's/v//')
curl -sL "https://github.com/cli/cli/releases/download/v${GH_VERSION}/gh_${GH_VERSION}_linux_amd64.tar.gz" \
  -o /tmp/gh.tar.gz
tar -xzf /tmp/gh.tar.gz -C /tmp
mkdir -p ~/.local/bin
cp /tmp/gh_${GH_VERSION}_linux_amd64/bin/gh ~/.local/bin/gh
export PATH="$HOME/.local/bin:$PATH"   # add this to ~/.zshrc to persist
```

Authenticate (a one-time step):

```bash
gh auth login --web --hostname github.com
# When prompted: copy the displayed code, open https://github.com/login/device,
# paste the code in the browser, then press Enter back in the terminal.
```

---

## How to Trigger a Run (CLI)

### On any `feature/**` or `master` push (automatic)

The workflow fires automatically on every `git push` to `master` or any `feature/**`
branch. Just push a commit and the run starts.

### Manual dispatch on any ref

```bash
export PATH="$HOME/.local/bin:$PATH"

# Dispatch on current branch
BRANCH=$(git branch --show-current)
gh api repos/axonivy-market/portal/actions/workflows/ci.yml/dispatches \
  --method POST -f ref="$BRANCH"
```

> **Note:** `workflow_dispatch` only works once the workflow exists on the
> **default branch** (`master`). Until then, use commits/pushes to trigger it.

### Watch the run live

```bash
export PATH="$HOME/.local/bin:$PATH"

# List recent ci.yml runs
gh run list --repo axonivy-market/portal --workflow ci.yml --limit 5

# Watch the latest run until it finishes (refreshes every 15 s)
RUN_ID=$(gh run list --repo axonivy-market/portal --workflow ci.yml \
  --limit 1 --json databaseId -q '.[0].databaseId')
gh run watch "$RUN_ID" --repo axonivy-market/portal --interval 15

# Or open the run directly in the browser
gh run view "$RUN_ID" --repo axonivy-market/portal --web
```

---

## How to Check (Reviewing a PR)

### Via the PR Checks section

1. Open the PR on GitHub.
2. Scroll to the **Checks** section at the bottom of the conversation.
3. Look for the **"Compiler Warnings"** check:
   - ✅ **Success** — warning count is equal to or below the baseline. Safe to merge.
   - ❌ **Action required** — the PR introduced new warnings. The check run's summary
     shows the exact delta and which baseline was used (master or branch fallback).
4. Click the check to see the full output in the **Actions** tab.

### Via PR comments (when warnings increase)

When the warning count increases on a `pull_request` event, the script automatically
posts a comment on the PR:

```
## ⚠️ Compiler Warnings Increased (+2)

This PR introduces 2 new compiler warnings.

| | Count |
|---|---|
| Baseline (previous run on feature/...) | 3 |
| This run | 5 |
| **Delta** | **+2** |

[View full details in the Compiler Warnings check run](...)
```

You can also check via CLI:

```bash
export PATH="$HOME/.local/bin:$PATH"
RUN_ID=$(gh run list --repo axonivy-market/portal --workflow ci.yml \
  --limit 1 --json databaseId -q '.[0].databaseId')
gh run view "$RUN_ID" --repo axonivy-market/portal
```

---

## How to Test

### 1 — Local simulation

Run the Maven build locally with the same `sed` transforms to see which lines would
become annotations:

```bash
mvn clean install -DskipTests -f AxonIvyPortal/portal-components/pom.xml \
  -Divy.engine.directory=/path/to/engine 2>&1 | \
  sed -E "s|[0-9]+\. (WARNING) in ([^ ]*) \(at line ([0-9]+)\)(.*)|::\L\1 file=\2,line=\3::build issue|g" | \
  sed -E "s|\[WARNING\] |::warning::|g"
```

Every line starting with `::warning::` would become a GitHub warning annotation.

### 2 — Trigger the workflow

Push any commit to the feature branch. Example:

```bash
git commit --allow-empty -m "chore: trigger CI run"
git push
```

Monitor it:

```bash
export PATH="$HOME/.local/bin:$PATH"
gh run list --repo axonivy-market/portal --workflow ci.yml --limit 3
```

### 3 — Verify baseline strategy

The step summary in the `Compiler Warnings` job shows which baseline was used:

```
Warning awareness gate — baseline: run #N from previous run on feature/...
- Current warnings: 5
- Baseline warnings: 5
- Delta: 0
✅ Result: No warning increase detected.
```

To confirm the master baseline takes over: once `ci.yml` is merged to `master` and a
green run completes on `master`, subsequent feature branch runs will compare against
that master run instead of the branch's previous run.

### 4 — Verify a regression is caught

The feature branch already has a commit ("Add some warnings for testing") that
intentionally introduced warnings (`i`, `j` variables, unnecessary `@SuppressWarnings`).
To test the regression flow manually:

1. Add an unused variable or call a `@Deprecated` method without `@SuppressWarnings`
   in any Java source file.
2. Push the commit.
3. The `build` job emits `::warning::` annotations for those lines.
4. The `warnings-awareness` job detects the delta and:
   - Sets the check run conclusion to `action_required`.
   - Posts a comment on any open PR for this branch.

### 5 — Note on warning count

The script counts **all** `annotation_level === 'warning'` annotations from all jobs,
including runner infrastructure warnings (e.g., Node.js deprecation notice from
`stCarolas/setup-maven@v5`). These infrastructure warnings are **stable** (always
present, same count), so they don't affect the delta. Only new code-level warnings
cause the delta to increase.

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---|---|---|
| `Compiler Warnings` check always `success`, summary shows `Baseline: n/a` | No prior successful run found on master OR current branch | Push a first succeeding commit on the branch; once it runs once, subsequent runs have a baseline |
| `Compiler Warnings` check fires `action_required` on clean branch | Infrastructure warning count changed (e.g., a new runner action was added) | The delta is from structural changes, not code; review the check run summary for details |
| No PR comment posted despite `action_required` check | `pull-requests: write` permission is missing, or event is `push` (not `pull_request`) | Restore permission in `ci.yml`; note comments only appear on PR events |
| `workflow_dispatch` returns 404 | Workflow not yet on default branch | Push to trigger via the `push` event instead |
| Baseline is `n/a` after many runs | Unlikely — only when ALL previous runs on master AND current branch failed | Check Actions tab for failed runs; fix whatever broke them |
| `gh: command not found` | PATH not updated | Run `export PATH="$HOME/.local/bin:$PATH"` or add it to `~/.zshrc` |
| Build fails before annotations appear | Engine download timed out or Ivy project parent unresolvable | Check engine URL; ensure `maven.axonivy.com` is reachable from the runner |
