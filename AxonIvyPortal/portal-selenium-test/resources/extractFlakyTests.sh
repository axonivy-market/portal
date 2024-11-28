if grep -q "\[WARNING\] Flakes:" logText.txt; then

  awk '/^\[WARNING\] Flakes:.*$/{flag=1}/^\[ERROR\] Tests run:.*Flakes: [0-9]*$/{print;flag=0}flag' logText.txt > flaky-tests-log.txt

  grep "\[WARNING\]" flaky-tests-log.txt | awk '{print}' > testReport.txt

  exit 0
fi