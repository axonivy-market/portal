findstr /l "\[WARNING\] Flakes:" logText > flaky-tests-log
findstr /l "com.axonivy.portal.selenium" flaky-tests-log > flakyTestsReport