package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.fixture.Fakes.fake;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.process.beans.IPoller;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;

@IvyTest
class TestCronByGlobalVariableTriggerStartEventBean {

  private static final String ENABLED = "PortalDeleteAllFinishedHiddenCases";
  private static final String SCHEDULE = "PortalStartTimeCleanObsoletedDataExpression";
  private static final String CRON = "0 23 31 12 *";

  @ParameterizedTest
  @CsvSource({"true, " + CRON + ", " + CRON, "false, " + CRON + ", disabled", "true, '', disabled"})
  void initializeUsesConfiguredCleanupSchedule(String enabled, String schedule, String expected) {
    List<String> calls = new ArrayList<>();
    IPoller poller = fake(IPoller.class, Map.of(
        "asDefinedByExpression", (InvocationHandler) (_, _, args) -> {
          calls.add((String) args[0]);
          return null;
        },
        "disable", (InvocationHandler) (_, _, _) -> {
          calls.add("disabled");
          return null;
        }));
    var runtime = fake(IProcessStartEventBeanRuntime.class, Map.of("poll", poller));

    var variables = Ivy.var();
    String originalEnabled = variables.get(ENABLED);
    String originalSchedule = variables.get(SCHEDULE);
    try {
      variables.set(ENABLED, enabled);
      variables.set(SCHEDULE, schedule);
      new CronByGlobalVariableTriggerStartEventBean().initialize(runtime, Map.of("variable", SCHEDULE)::get);

      assertThat(calls).containsExactly(expected);
    } finally {
      variables.set(ENABLED, originalEnabled);
      variables.set(SCHEDULE, originalSchedule);
    }
  }
}
