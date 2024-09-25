findstr /I "\[WARNING\] Flakes:" AxonIvyPortal/portal-selenium-test/target/consoleText > AxonIvyPortal/portal-selenium-test/target/flaky-tests-log
findstr /I "com.axonivy.portal.selenium" AxonIvyPortal/portal-selenium-test/target/flaky-tests-log > AxonIvyPortal/portal-selenium-test/target/flakyTestsReport
sed '/Time elapsed/s/.*//' AxonIvyPortal/portal-selenium-test/target/flakyTestsReport > AxonIvyPortal/portal-selenium-test/target/report

