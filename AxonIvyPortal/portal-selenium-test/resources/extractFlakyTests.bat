findstr /l "\[WARNING\] Flakes:" consoleText > flaky-tests-log
findstr /l "com.axonivy.portal.selenium" flaky-tests-log > flakyTestsReport
get-content flakyTestsReport | %{$_ -replace "Time elapsed",""}
