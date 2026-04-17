const { execSync } = require('child_process');

function exec(cmd, cwd) {
  return execSync(cmd, { encoding: 'utf8', cwd }).trim();
}

function execCount(cmd, cwd) {
  return parseInt(exec(cmd, cwd), 10) || 0;
}

async function getWarningAnnotations(github, owner, repo, runId) {
  const { data } = await github.rest.actions.listJobsForWorkflowRun({
    owner, repo, run_id: runId, per_page: 100,
  });
  const annotations = [];
  for (const job of data.jobs) {
    const checkRunId = job.check_run_url?.match(/\/check-runs\/(\d+)$/)?.[1];
    if (!checkRunId) continue;
    const { data: jobAnnotations } = await github.rest.checks.listAnnotations({
      owner, repo, check_run_id: Number(checkRunId), per_page: 100,
    });
    annotations.push(...jobAnnotations.filter(annotation => annotation.annotation_level === 'warning'));
  }
  return annotations;
}

async function findLastSuccessfulRun(github, owner, repo, workflowFile, branch, currentRunId) {
  const { data } = await github.rest.actions.listWorkflowRuns({
    owner, repo, workflow_id: workflowFile, branch,
    status: 'completed', per_page: 30,
  });
  return data.workflow_runs.find(run => run.id !== currentRunId && run.conclusion === 'success') ?? null;
}

function formatDelta(n) { return n > 0 ? `+${n}` : String(n); }

function buildCheckSummary({ label, current, baseline, newlyIntroduced }) {
  const lines = [`${label} gate against latest successful default-branch run.`];
  lines.push(`- Current: ${current}`);
  if (baseline === null) {
    lines.push('- Baseline: n/a (no successful baseline run found)');
    lines.push('- Delta: n/a');
  } else {
    lines.push(`- Baseline: ${baseline}`);
    lines.push(`- Delta: ${formatDelta(current - baseline)}`);
  }
  if (newlyIntroduced?.length) {
    lines.push('', 'Newly introduced in this PR:');
    newlyIntroduced.forEach(item => lines.push(`  • ${item}`));
  }
  return lines.join('\n');
}

async function resolveRunContext(github, context) {
  const { owner, repo } = context.repo;
  const workspace = process.env.GITHUB_WORKSPACE || '.';
  const { data: repoInfo } = await github.rest.repos.get({ owner, repo });
  const defaultBranch = repoInfo.default_branch;
  const workflowFile = (context.workflowRef || '').split('@')[0].split('/').pop() || 'ci.yml';
  exec(`git fetch origin ${defaultBranch} --depth=1`, workspace);
  return { owner, repo, workspace, defaultBranch, workflowFile };
}

function findNewWarnings(currentAnnotations, baselineAnnotations) {
  const baselineKeys = new Set(baselineAnnotations.map(a => `${a.path}|${a.start_line}|${a.message}`));
  return currentAnnotations
    .filter(annotation => !baselineKeys.has(`${annotation.path}|${annotation.start_line}|${annotation.message}`))
    .map(annotation => {
      if (annotation.path && annotation.path !== '.github') {
        return `${annotation.path}:${annotation.start_line} — ${annotation.message}`;
      }
      return annotation.message.replace(/^\/home\/runner\/work\/[^/]+\/[^/]+\//, '');
    });
}

function findNewSuppressions(diff) {
  const result = [];
  let currentFile = '';
  for (const line of diff.split('\n')) {
    if (line.startsWith('+++ b/')) {
      currentFile = line.slice(6);
    } else if (line.startsWith('+') && !line.startsWith('+++') && line.includes('@SuppressWarnings')) {
      result.push(`${currentFile}: ${line.slice(1).trim()}`);
    }
  }
  return result;
}

module.exports.checkCompilerWarnings = async ({ github, context, core }) => {
  const { owner, repo, workspace, defaultBranch, workflowFile } = await resolveRunContext(github, context);
  const baselineRun = await findLastSuccessfulRun(github, owner, repo, workflowFile, defaultBranch, context.runId);

  const currentAnnotations = await getWarningAnnotations(github, owner, repo, context.runId);
  const baselineAnnotations = baselineRun ? await getWarningAnnotations(github, owner, repo, baselineRun.id) : null;

  const current = currentAnnotations.length;
  const baseline = baselineAnnotations !== null ? baselineAnnotations.length : null;
  const delta = baseline !== null ? current - baseline : 0;
  const regressed = baseline !== null && delta > 0;
  const newlyIntroduced = regressed ? findNewWarnings(currentAnnotations, baselineAnnotations) : [];

  core.setOutput('current', String(current));
  core.setOutput('baseline', baseline === null ? 'n/a' : String(baseline));
  core.setOutput('delta', baseline === null ? 'n/a' : formatDelta(delta));
  core.setOutput('status', regressed ? 'failure' : 'success');
  await core.summary.addRaw(
    buildCheckSummary({ label: 'Compiler Warnings', current, baseline, newlyIntroduced }) + '\n'
  ).write();

  if (regressed) {
    core.setFailed(`${delta} new compiler warning(s) introduced`);
  }
};

module.exports.checkSuppressWarnings = async ({ github, context, core }) => {
  const { workspace, defaultBranch } = await resolveRunContext(github, context);

  const mergeBase = exec(`git merge-base HEAD origin/${defaultBranch}`, workspace) || `origin/${defaultBranch}`;
  const current = execCount(`git grep "@SuppressWarnings" HEAD -- "*.java" | wc -l`, workspace);
  const baseline = execCount(`git grep "@SuppressWarnings" ${mergeBase} -- "*.java" | wc -l`, workspace);
  const delta = current - baseline;
  const regressed = delta > 0;
  const newlyIntroduced = regressed
    ? findNewSuppressions(exec(`git diff ${mergeBase} HEAD -- "*.java"`, workspace))
    : [];

  core.setOutput('current', String(current));
  core.setOutput('baseline', String(baseline));
  core.setOutput('delta', formatDelta(delta));
  core.setOutput('status', regressed ? 'failure' : 'success');
  await core.summary.addRaw(
    buildCheckSummary({ label: 'Suppress Warnings', current, baseline, newlyIntroduced }) + '\n'
  ).write();

  if (regressed) {
    core.setFailed(`${delta} new SuppressWarnings introduced`);
  }
};
