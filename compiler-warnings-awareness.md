# Compiler Warnings Awareness

## Idea

Adapted from [axonivy-market/smart-workflow#220](https://github.com/axonivy-market/smart-workflow/pull/220).

The goal is to keep the Portal codebase free from unnoticed compiler warning regressions.
Any pull request that **increases** the number of compiler warnings compared to the latest
successful `master` run is automatically flagged via a dedicated GitHub check run named
**"Compiler Warnings"**, giving reviewers an early, automated signal before merging.

---

## What It Handles

| Concern | How it is handled |
|---|---|
| New `[WARNING]` lines introduced by Maven | Converted to `::warning::` GitHub annotations at build time via `sed` |
| ECJ-style `WARNING in File.java (at line N)` messages | Converted to `::warning file=…,line=N::` annotations |
| Warning count delta against `master` | `warnings-awareness.cjs` compares current run vs. last successful run on the default branch |
| Visible gate on every PR | A dedicated check run (`Compiler Warnings`) is created with conclusion `action_required` if warnings increased, or `success` otherwise |

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
   with `annotation_level === 'warning'` (i.e., every `::warning::` line emitted by the
   build).
2. Finds the last **successful** run of `ci.yml` on the default branch to use as the
   baseline.
3. Computes the delta (`current − baseline`).
4. Creates a GitHub **check run** (`github.rest.checks.create`) with:
   - `conclusion: 'success'` — if the warning count did not increase.
   - `conclusion: 'action_required'` — if new warnings were introduced.
5. Exposes `current`, `baseline`, `delta`, and `status` as step outputs for the
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
  actions: read   # list workflow runs and jobs
  contents: read  # checkout
  checks: write   # create the "Compiler Warnings" check run
```

> These must **not** be reduced; removing `checks: write` will cause the
> `github.rest.checks.create` call in the script to fail with a 403.

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

1. Open the PR on GitHub.
2. Scroll to the **Checks** section at the bottom of the conversation.
3. Look for the **"Compiler Warnings"** check:
   - ✅ **Success** — warning count is equal to or below the master baseline. Safe to merge.
   - ❌ **Action required** — the PR introduced new warnings. The check run's summary
     shows the exact delta:
     ```
     Warning awareness gate against latest successful default-branch run.
     - Current warnings: 5
     - Baseline warnings: 3
     - Delta: +2
     Result: Action required - warning count increased.
     ```
4. Click the check to see the full output in the **Actions** tab.

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

### 3 — Verify a regression is caught

The feature branch `feature/IVYPORTAL-20070-Reduce-Warnings-in-Portal-Part-1-3`
already has a commit ("Add some warnings for testing") that intentionally introduces
warnings. The `warnings-awareness` job should detect an increased count vs. the
`master` baseline and set the check conclusion to `action_required`.

To test this manually on any branch:

1. Add an unused import or call a `@Deprecated` method without `@SuppressWarnings`
   in any Java source file.
2. Push the commit.
3. The `build` job emits `::warning::` annotations.
4. The `warnings-awareness` job detects the delta and marks the check
   `action_required`.

### 4 — Verify a clean build passes

1. Revert the introduced warning and push.
2. The `warnings-awareness` check should report `Delta: 0` and conclude `success`.

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---|---|---|
| `Compiler Warnings` check never appears | `checks: write` permission is missing | Restore permission in `ci.yml` |
| `workflow_dispatch` returns 404 | Workflow not on default branch yet | Push to trigger via the `push` event instead |
| Baseline is `n/a` | No prior successful `ci.yml` run on `master` | Merge the workflow to `master` first with a green run |
| Warning count unexpectedly high | Maven lifecycle / plugin warnings counted as `[WARNING]` | Inspect the step log; use `-q` flag or filter specific modules if needed |
| Build fails before annotations appear | Engine download timed out or Ivy project parent unresolvable | Check engine URL; ensure `maven.axonivy.com` is reachable from the runner |
| `gh: command not found` | PATH not updated | Run `export PATH="$HOME/.local/bin:$PATH"` or add it to `~/.zshrc` |
