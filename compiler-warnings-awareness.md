# Code Quality Gates — Compiler Warnings & @SuppressWarnings

## Idea

Adapted from [axonivy-market/smart-workflow#220](https://github.com/axonivy-market/smart-workflow/pull/220).

Keep the Portal codebase free from unnoticed code-quality regressions. Two automated
GitHub check runs gate every PR:

| Check run | What it detects |
|---|---|
| **Compiler Warnings** | New `[WARNING]` lines emitted by the Maven / ECJ build |
| **Suppress Warnings** | New `@SuppressWarnings` annotations added to Java sources |

Both checks compare against the latest successful `master` run / tree and fail the PR
when the count increases — giving reviewers an early, precise signal with a list of the
exact locations introduced.

---

## How It Works

### `build` job

- Sets up Java 25 (Temurin), Maven 3.9.8, downloads the nightly Axonivy Engine.
- Builds all portal modules with `mvn clean install -DskipTests`.
- Maven output is piped through two `sed` transforms that convert warning lines to
  GitHub annotation format so they appear inline on the PR diff:
  - `[WARNING]` → `::warning::`
  - ECJ `WARNING in File.java (at line N)` → `::warning file=…,line=N::`

### `code-quality-gates` job (runs after `build`)

Two parallel jobs load `.github/scripts/warnings-awareness.cjs` via `actions/github-script@v8`:

#### Job: Compiler Warnings
1. Iterates every job in the current run, collects all annotations with
   `annotation_level === 'warning'` via the GitHub checks API.
2. Fetches the same annotations from the last successful `ci.yml` run on `master` as
   the baseline.
3. **Deduplicates** using `path|start_line|message` key — same warning at different
   lines is counted as distinct.
4. Computes delta. If `delta > 0`, sets job status to **failure** and lists the
   specific new annotations (`file:line — message`).

#### Job: Suppress Warnings
1. Checkout includes `fetch-depth: 100` to get enough history for `git merge-base`
   (not needed for API-only compiler warnings job).
2. Runs `git fetch origin master --depth=1` to ensure the branch ref is available.
3. Counts `@SuppressWarnings` in the current HEAD and in `origin/master` via
   `git grep … | wc -l || true` (the `|| true` suppresses exit code 1 on zero matches).
4. If `delta > 0`, parses `git diff origin/master HEAD -- "*.java"` to extract the
   actual new lines with their file paths.
5. Sets job status to **failure** listing each new suppression as `file: @SuppressWarnings(…)`.

---

## Files

```
.github/
├── scripts/
│   └── warnings-awareness.cjs   ← single script — both checks, shared utilities
└── workflows/
    └── ci.yml                   ← two-job workflow: build + code-quality-gates
```

### Internal structure of `warnings-awareness.cjs`

| Function | Responsibility |
|---|---|
| `exec(cmd, cwd)` | Run shell command via `execSync`, throws on non-zero exit code |
| `execCount(cmd, cwd)` | Parse command output as integer count (0 if parse fails) |
| `getWarningAnnotations(runId)` | Fetch all warning annotations from a workflow run (100 jobs × 100 annotations per job) |
| `findLastSuccessfulRun(…)` | Find the last successful run on the default branch (queries 30 most recent) |
| `formatDelta(n)` | Format delta as `+N` (positive) or `N` (negative/zero) |
| `buildCheckSummary(…)` | Generate check run summary with current/baseline/delta and newly introduced list |
| `resolveRunContext(…)` | Retrieve owner, repo, workspace, default branch, workflow file name from context |
| `findNewWarnings(current, baseline)` | Deduplicate annotations by `path\|start_line\|message`; return newly introduced |
| `findNewSuppressions(diff)` | Parse unified diff to extract new `@SuppressWarnings` lines |
| `checkCompilerWarnings()` | Entry point for compiler warnings check |
| `checkSuppressWarnings()` | Entry point for suppression check; includes `git fetch` |

**Key design decisions:**
- **No try/catch in `exec()`** — failures throw immediately, revealing real problems (e.g., missing refs, network issues) instead of silently returning empty strings.
- **Deduplication by `path|start_line|message`** — same warning message in different locations or lines is counted as distinct, avoiding false negatives when the same warning type appears multiple times.
- **`|| true` in git grep** — handles the case where `git grep` returns exit code 1 for "no matches", allowing the count to correctly report 0 instead of crashing.
- **fetch-depth: 100** — Sufficient for typical workflows (handles ~100 commits of history); faster than full history, but still enough for `git merge-base`.

---

## Required Permissions

```yaml
permissions:
  actions: read   # list workflow runs and jobs
  contents: read  # checkout
  checks: read    # read annotations from check runs
```

> Only `read` permissions are needed since the workflow uses the **job-as-check** pattern:
> each GitHub Actions job automatically creates its own check run, eliminating the need
> for `checks: write` or manual `github.rest.checks.create()` calls.

---

## Prerequisites — GitHub CLI (`gh`)

Install once (no sudo needed):

```bash
GH_VERSION=$(curl -s https://api.github.com/repos/cli/cli/releases/latest \
  | grep '"tag_name"' | cut -d'"' -f4 | sed 's/v//')
curl -sL "https://github.com/cli/cli/releases/download/v${GH_VERSION}/gh_${GH_VERSION}_linux_amd64.tar.gz" \
  -o /tmp/gh.tar.gz
tar -xzf /tmp/gh.tar.gz -C /tmp
mkdir -p ~/.local/bin
cp /tmp/gh_${GH_VERSION}_linux_amd64/bin/gh ~/.local/bin/gh
export PATH="$HOME/.local/bin:$PATH"   # also add to ~/.zshrc to persist
```

Authenticate (one-time):

```bash
gh auth login --web --hostname github.com
# Copy the one-time code displayed, open https://github.com/login/device in a browser,
# enter the code, then press Enter in the terminal.
```

---

## Triggering a Run

### Automatic

The workflow fires on every push to `master` or any `feature/**` branch, and on all
pull requests. Just push a commit.

### Manual dispatch (after workflow is on `master`)

```bash
export PATH="$HOME/.local/bin:$PATH"
BRANCH=$(git branch --show-current)
gh api repos/axonivy-market/portal/actions/workflows/ci.yml/dispatches \
  --method POST -f ref="$BRANCH"
```

> `workflow_dispatch` requires the workflow to exist on the **default branch**.
> Until merged, use the `push` event by committing and pushing.

### Monitoring

```bash
export PATH="$HOME/.local/bin:$PATH"

# List recent runs
gh run list --repo axonivy-market/portal --workflow ci.yml --limit 5

# Watch latest run live (refreshes every 15 s)
RUN_ID=$(gh run list --repo axonivy-market/portal --workflow ci.yml \
  --limit 1 --json databaseId -q '.[0].databaseId')
gh run watch "$RUN_ID" --repo axonivy-market/portal --interval 15

# Open in browser
gh run view "$RUN_ID" --repo axonivy-market/portal --web
```

---

## How to Check a PR

1. Open the PR → scroll to **Checks**.
2. Look for **"Compiler Warnings"** and **"Suppress Warnings"**:
   - ✅ **Success** — count did not increase vs. `master` baseline. Safe to merge.
   - ❌ **Failure** — new warnings were introduced. Click the check to see the summary:

```
Compiler Warnings gate against latest successful default-branch run.
- Current: 5
- Baseline: 3
- Delta: +2

Newly introduced in this PR:
  • AxonIvyPortal/portal/src/.../TaskService.java:75 — The value of the local variable i is not used
  • AxonIvyPortal/portal/src/.../TaskService.java:58 — Unnecessary @SuppressWarnings("deprecation")
```

```
Suppress Warnings gate against latest successful default-branch run.
- Current: 12
- Baseline: 10
- Delta: +2

Newly introduced in this PR:
  • AxonIvyPortal/portal-components/src/.../SomeBean.java: @SuppressWarnings("unchecked")
  • AxonIvyPortal/portal/src/.../OtherService.java: @SuppressWarnings({"rawtypes", "unchecked"})
```

Check via CLI:

```bash
export PATH="$HOME/.local/bin:$PATH"
RUN_ID=$(gh run list --repo axonivy-market/portal --workflow ci.yml \
  --limit 1 --json databaseId -q '.[0].databaseId')
gh run view "$RUN_ID" --repo axonivy-market/portal
```

---

## How to Test

### 1 — Simulate locally (compiler warnings)

```bash
mvn clean install -DskipTests -f AxonIvyPortal/portal-components/pom.xml \
  -Divy.engine.directory=/path/to/engine 2>&1 | \
  sed -E "s|[0-9]+\. (WARNING) in ([^ ]*) \(at line ([0-9]+)\)(.*)|::\L\1 file=\2,line=\3::build issue|g" | \
  sed -E "s|\[WARNING\] |::warning::|g"
```

Lines starting with `::warning::` become GitHub annotations.

### 2 — Simulate locally (@SuppressWarnings)

```bash
# Count on current HEAD
git grep "@SuppressWarnings" HEAD -- "*.java" | wc -l || true

# Count on master
git fetch origin master --depth=1
git grep "@SuppressWarnings" origin/master -- "*.java" | wc -l || true

# List newly added suppressions vs master
git merge-base HEAD origin/master  # ensure common ancestor exists
git diff origin/master HEAD -- "*.java" | grep "^+" | grep "@SuppressWarnings"
```

> The `|| true` after `wc -l` ensures the command returns 0 on success (when there are no matches)
> instead of failing with exit code 1 from `git grep`.

### 3 — Trigger a build

Push any commit to the feature branch:

```bash
git commit --allow-empty -m "chore: trigger CI"
git push
```

Monitor:

```bash
export PATH="$HOME/.local/bin:$PATH"
gh run list --repo axonivy-market/portal --workflow ci.yml --limit 3
```

### 4 — Verify a compiler warning regression is caught

1. Add an unused import or call a `@Deprecated` method in any Java file.
2. Push. The `build` job emits `::warning::` annotations.
3. The `code-quality-gates` job fails with `delta > 0` and lists the new warnings.

### 5 — Verify a @SuppressWarnings regression is caught

1. Add `@SuppressWarnings("unchecked")` to any Java file and push.
2. The `code-quality-gates` job fails and lists the new suppression with its file path.

### 6 — Verify a clean build passes

Revert the introduced change and push. Both checks should report `Delta: 0` and conclude `success`.

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---|---|---|
| Check runs never appear | Workflow file not found or malformed | Validate `.github/workflows/ci.yml` syntax |
| `workflow_dispatch` returns 404 | Workflow not yet on `master` | Trigger via push instead |
| Baseline is `n/a` | No prior successful `ci.yml` run on `master` | Merge to `master` first, get a green run |
| Warning count unexpectedly high | Maven plugin/lifecycle `[WARNING]` lines are counted | Inspect the step log; those are typically not actionable — consider filtering by module |
| `exec()` throws when running suppression check | `git fetch`, `git merge-base`, or `git grep` failed | Network access to GitHub? Valid default branch ref? Sufficient history with `fetch-depth: 100`? |
| Zero `@SuppressWarnings` count causes crash | Old code without `\|\| true` in git grep | Update to latest script — `|| true` handles no-match exit code 1 |
| `gh: command not found` | PATH not updated | `export PATH="$HOME/.local/bin:$PATH"` — or add to `~/.zshrc` |

