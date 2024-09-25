pwd
if grep -q "\[WARNING\] Flakes:" AxonIvyPortal/portal-selenium-test/target/consoleText; then

  awk '/^\[WARNING\] Flakes:.*$/{flag=1}/^\[ERROR\] Tests run:.*Flakes: [0-9]*$/{print;flag=0}flag' AxonIvyPortal/portal-selenium-test/target/consoleText > AxonIvyPortal/portal-selenium-test/target/flaky-tests-log.txt

  grep "\[WARNING\]" AxonIvyPortal/portal-selenium-test/target/flaky-tests-log.txt | awk '{print}' > AxonIvyPortal/portal-selenium-test/target/flakyTests.txt

  exit 1
fi