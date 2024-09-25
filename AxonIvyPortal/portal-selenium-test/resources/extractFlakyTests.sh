if grep -q "\[WARNING\] Flakes:" ../target/consoleText; then

  awk '/^\[WARNING\] Flakes:.*$/{flag=1}/^\[ERROR\] Tests run:.*Flakes: [0-9]*$/{print;flag=0}flag' ../target/consoleText > ../target/flaky-tests-log.txt

  grep "\[WARNING\]" ../target/flaky-tests-log.txt | awk '{print}' > ../target/flakyTests.txt

  exit 1
fi