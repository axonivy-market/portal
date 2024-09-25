findstr /I "\[WARNING\] Flakes:" consoleText > flaky-tests-log
findstr /I "com.axonivy.portal.selenium" flaky-tests-log > flakyTestsReport
sed '/Time elapsed/s/.*//' flakyTestsReport


this script work, you should relocate the bat file inside the target first, then run the script