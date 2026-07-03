const fs = require('fs');
const path = require('path');
const { createHtmlReport } = require('axe-html-reporter');

const targetDir = 'AxonIvyPortal/portal-selenium-test/target/';
if (!fs.existsSync(targetDir)) {
  console.log(`Target directory not found: ${targetDir}`);
  process.exit(0);
}
const files = fs.readdirSync(targetDir).filter(f => f.startsWith('axe-results-') && f.endsWith('.json'));

if (files.length === 0) {
  console.log('No axe-results JSON files found.');
  process.exit(0);
}

// Combine results for a single report or generate multiple
// createHtmlReport natively supports multiple files by just calling it multiple times or we can put them in axe-reports

let a11ySummary = '## ♿ A11y Audit Report\n\n| Report Name | Violations | Status |\n|---|---|---|\n';

for (const file of files) {
  const raw = fs.readFileSync(path.join(targetDir, file), 'utf8');
  const data = JSON.parse(raw);
  
  let violationsCount = 0;
  if (data.violations && Array.isArray(data.violations)) {
     violationsCount = data.violations.length;
  } else if (Array.isArray(data)) {
     data.forEach(d => {
        if (d.violations && Array.isArray(d.violations)) violationsCount += d.violations.length;
     });
  }

  const status = violationsCount === 0 ? '✅' : '❌';
  const reportName = file.replace('axe-results-', '').replace(/\.json$/, '');
  a11ySummary += '| ' + reportName + ' | ' + violationsCount + ' | ' + status + ' |\n';

  createHtmlReport({
    results: data,
    options: {
      projectKey: reportName,
      outputDir: path.join(targetDir, 'axe-reports'),
      reportFileName: file.replace(/\.json$/, '.html')
    }
  });
  console.log('Generated HTML report for: ' + file);
}

const summaryPath = process.env.GITHUB_STEP_SUMMARY;
if (summaryPath) {
   fs.appendFileSync(summaryPath, a11ySummary + '\n');
}
