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
  const currentBranch = (context.payload.pull_request
    ? context.payload.pull_request.head.ref
    : context.ref.replace('refs/heads/', ''));
  const workflowFile = (context.workflowRef || '').split('@')[0].split('/').pop() || 'ci.yml';

  const currentWarnings = await countWarningsForRun(currentRunId);

  // 1. Try master (default branch) as baseline — the canonical reference.
  const masterRuns = await github.rest.actions.listWorkflowRuns({
    owner,
    repo,
    workflow_id: workflowFile,
    branch: defaultBranch,
    status: 'completed',
    per_page: 30,
  });

  let baselineRun = masterRuns.data.workflow_runs.find(
    run => run.id !== currentRunId && run.conclusion === 'success'
  );
  let baselineSource = `default branch (${defaultBranch})`;

  // 2. Fallback: if no master baseline exists yet (workflow not yet merged to master),
  //    compare against the previous successful run on the current branch.
  //    This catches warning regressions on feature branches before the first master merge.
  if (!baselineRun) {
    const branchRuns = await github.rest.actions.listWorkflowRuns({
      owner,
      repo,
      workflow_id: workflowFile,
      branch: currentBranch,
      status: 'completed',
      per_page: 30,
    });
    baselineRun = branchRuns.data.workflow_runs.find(
      run => run.id !== currentRunId && run.conclusion === 'success'
    );
    if (baselineRun) {
      baselineSource = `previous run on ${currentBranch}`;
    }
  }

  let baselineWarnings = null;
  if (baselineRun) {
    baselineWarnings = await countWarningsForRun(baselineRun.id);
  }

  const delta = baselineWarnings === null ? 0 : currentWarnings - baselineWarnings;
  const increased = baselineWarnings !== null && delta > 0;
  const conclusion = increased ? 'action_required' : 'success';

  const summaryLines = [
    `Warning awareness gate — baseline: ${baselineRun ? `[run #${baselineRun.run_number}](${baselineRun.html_url}) from ${baselineSource}` : 'none found'}.`,
    `- Current warnings: ${currentWarnings}`,
  ];

  if (baselineWarnings === null) {
    summaryLines.push('- Baseline warnings: n/a (no prior successful run found on any branch)');
    summaryLines.push('- Delta: n/a');
  } else {
    const sign = delta > 0 ? '+' : '';
    summaryLines.push(`- Baseline warnings: ${baselineWarnings}`);
    summaryLines.push(`- Delta: ${sign}${delta}`);
  }

  if (increased) {
    summaryLines.push('⚠️ Result: Action required — warning count increased.');
  } else {
    summaryLines.push('✅ Result: No warning increase detected.');
  }

  await github.rest.checks.create({
    owner,
    repo,
    name: 'Compiler Warnings',
    head_sha: context.sha,
    status: 'completed',
    conclusion,
    output: {
      title: increased ? `⚠️ Warning count increased (+${delta})` : '✅ No warning increase',
      summary: summaryLines.join('\n'),
    },
  });

  // Post a PR comment when warnings increased so developers get explicit notification.
  if (increased && context.payload.pull_request) {
    const prNumber = context.payload.pull_request.number;
    const commentLines = [
      `## ⚠️ Compiler Warnings Increased (+${delta})`,
      '',
      `This PR introduces **${delta} new compiler warning${delta === 1 ? '' : 's'}**.`,
      '',
      '| | Count |',
      '|---|---|',
      `| Baseline (${baselineSource}) | ${baselineWarnings} |`,
      `| This run | ${currentWarnings} |`,
      `| **Delta** | **+${delta}** |`,
      '',
      `[View full details in the Compiler Warnings check run](${context.serverUrl}/${owner}/${repo}/actions/runs/${currentRunId})`,
    ];
    await github.rest.issues.createComment({
      owner,
      repo,
      issue_number: prNumber,
      body: commentLines.join('\n'),
    });
  }

  core.setOutput('current', String(currentWarnings));
  core.setOutput('baseline', baselineWarnings === null ? 'n/a' : String(baselineWarnings));
  core.setOutput('delta', baselineWarnings === null ? 'n/a' : String(delta));
  core.setOutput('status', increased ? 'action_required' : 'success');
};
