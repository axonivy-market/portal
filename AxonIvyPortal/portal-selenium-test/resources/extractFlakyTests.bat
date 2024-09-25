findstr /I "\[WARNING\] Flakes:" consoleText > flaky-tests-log
findstr /I "com.axonivy.portal.selenium" flaky-tests-log > flakyTestsReport
sed '/Time elapsed/s/.*//' flakyTestsReport > report

