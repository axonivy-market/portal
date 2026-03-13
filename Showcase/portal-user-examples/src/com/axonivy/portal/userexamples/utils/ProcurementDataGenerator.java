package com.axonivy.portal.userexamples.utils;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.userexamples.SampleStatisticDashboardData;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.DateTime;

public class ProcurementDataGenerator {

  private static final String SIGNAL_CODE = "com:axonivy:portal:portaluserexamples:createProcurementKPIcase";

  private static final List<String> DEPARTMENTS = Arrays.asList("DepA", "DepB", "DepC", "DepD");
  private static final List<String> ITEMS = Arrays.asList("Laptop", "Mobile", "Keyboard", "Tablet");
  private static final List<Integer> MONTHS = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
  private static final List<Double> SPEND_AMOUNTS = Arrays.asList(250.5, 300.5, 400.5, 122.5, 234.5);
  private static final List<Integer> DAYS_TO_DECISIONS = Arrays.asList(2, 5, 6, 2, 7, 4, 9, 4);
  private static final List<String> DECISIONS = Arrays.asList("Approved", "Rejected", "Pending");
  private static final List<String> REWORK_CAUSES = Arrays.asList("MissingInfo", "NotEligible", "TooExpensive");
  private static final List<Integer> REWORK_NO_LOOPS = Arrays.asList(0, 1, 2, 0, 0);
  private static final List<String> REWORK_OCCURRED = Arrays.asList("Yes", "No");

  public static void generateAndSend(int count) {
    for (int i = 0; i < count; i++) {
      SampleStatisticDashboardData data = createRandomInstance();
      Ivy.wf().signals().create().data(data).send(SIGNAL_CODE);
    }
  }

  private static SampleStatisticDashboardData createRandomInstance() {
    SampleStatisticDashboardData data = new SampleStatisticDashboardData();
    data.setDepartment(pickRandom(DEPARTMENTS));
    data.setItem(pickRandom(ITEMS));
    int month = pickRandom(MONTHS);
    data.setMonthOfYear(new DateTime(2025, month, 1, 7, 1, 1));
    data.setSpendAmount(pickRandom(SPEND_AMOUNTS));
    data.setDaysToDecision(pickRandom(DAYS_TO_DECISIONS));
    data.setDecision(pickRandom(DECISIONS));
    data.setReworkCauses(pickRandom(REWORK_CAUSES));
    data.setReworkNoLoops(pickRandom(REWORK_NO_LOOPS));
    data.setReworkOccurred(pickRandom(REWORK_OCCURRED));
    return data;
  }

  private static <T> T pickRandom(List<T> list) {
    return list.get((int) (Math.random() * list.size()));
  }
}
