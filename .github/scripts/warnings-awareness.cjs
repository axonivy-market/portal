module.exports = async ({ github, context, core }) => {
  const owner = context.repo.owner;
  const repo = context.repo.repo;
  const currentRunId = context.runId;

  async function countWarningsForRun(runId) {
    let total = 0;
    let page = 1;

    while (true) {
      const { data } = await github.rest.actions.listJobsForWorkflowRun({
        owner,
        repo,
        run_id: runId,
        per_page: 100,
        page,
      });

      for (const job of data.jobs) {
        const match = job.check_run_url && job.check_run_url.match(/\/check-runs\/(\d+)$/);
        if (!match) {
          continue;
        }

        const checkRunId = Number(match[1]);
        let annPage = 1;

        while (true) {
          const annotations = await github.rest.checks.listAnnotations({
            owner,
            repo,
            check_run_id: checkRunId,
            per_page: 100,
            page: annPage,
          });

          const currentBatch = annotations.data;
          total += currentBatch.filter(a => a.annotation_level === 'warning').length;

          if (currentBatch.length < 100) {
            break;
          }

          annPage += 1;
        }
      }

      if (data.jobs.length < 100) {
        break;
      }

      page += 1;
    }

    return total;
  }

  const repoInfo = await github.rest.repos.get({ owner, repo });
  const defaultBranch = repoInfo.data.default_branch;
  const workflowFile = (context.workflowRef || '').split('@')[0].split('/').pop() || 'ci.yml';

  const currentWarnings = await countWarningsForRun(currentRunId);

  const workflowRuns = await github.rest.actions.listWorkflowRuns({
    owner,
    repo,
    workflow_id: workflowFile,
    branch: defaultBranch,
    status: 'completed',
    per_page: 30,
  });

  const baselineRun = workflowRuns.data.workflow_runs.find(
    run => run.id !== currentRunId && run.conclusion === 'success'
  );

  let baselineWarnings = null;
  if (baselineRun) {
    baselineWarnings = await countWarningsForRun(baselineRun.id);
  }

  const delta = baselineWarnings === null ? 0 : currentWarnings - baselineWarnings;
  const increased = baselineWarnings !== null && delta > 0;
  const conclusion = increased ? 'action_required' : 'success';

  const summaryLines = [
    'Warning awareness gate against latest successful default-branch run.',
    `- Current warnings: ${currentWarnings}`,
  ];

  if (baselineWarnings === null) {
    summaryLines.push('- Baseline warnings: n/a (no successful baseline run found)');
    summaryLines.push('- Delta: n/a');
  } else {
    const sign = delta > 0 ? '+' : '';
    summaryLines.push(`- Baseline warnings: ${baselineWarnings}`);
    summaryLines.push(`- Delta: ${sign}${delta}`);
  }

  if (increased) {
    summaryLines.push('Result: Action required - warning count increased.');
  } else {
    summaryLines.push('Result: No warning increase detected.');
  }

  await github.rest.checks.create({
    owner,
    repo,
    name: 'Compiler Warnings',
    head_sha: context.sha,
    status: 'completed',
    conclusion,
    output: {
      title: increased ? 'Warning count increased' : 'No warning increase',
      summary: summaryLines.join('\n'),
    },
  });

  core.setOutput('current', String(currentWarnings));
  core.setOutput('baseline', baselineWarnings === null ? 'n/a' : String(baselineWarnings));
  core.setOutput('delta', baselineWarnings === null ? 'n/a' : String(delta));
  core.setOutput('status', increased ? 'action_required' : 'success');
};
