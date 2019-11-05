package ch.ivy.addon.portalkit.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.ivyteam.ivy.environment.Ivy;

public class Colors {

  public static final String SEMICOLON = ";";

  private static final String EXCEPTION_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/exception";
  private static final String HIGH_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/high";
  private static final String NORMAL_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/normal";
  private static final String LOW_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/low";

  private static final String CREATED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/caseState/CREATED";
  private static final String RUNNING_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/caseState/RUNNING";
  private static final String DONE_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/caseState/DONE";
  private static final String FAILED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/caseState/FAILED";
  public static final String PRIORITY_COLOR = "PRIORITY_COLOR";
  public static final String STATE_COLOR = "STATE_COLOR";

  private Colors() {}

  /**
   * get color for Task's priority chart
   * Build with correct order: EXCEPTION, HIGH, NORMAL, LOW
   * @param chartData
   * @param colors
   * @return list of colors
   */
  public static List<String> getPriorityColors(Map<String, Number> chartData, StatisticColors colors) {
    Number exceptionPriorities = chartData.get(Ivy.cms().co(EXCEPTION_PRIORITY_KEY));
    Number highPriorities = chartData.get(Ivy.cms().co(HIGH_PRIORITY_KEY));
    Number normalPriorities = chartData.get(Ivy.cms().co(NORMAL_PRIORITY_KEY));
    Number lowPriorities = chartData.get(Ivy.cms().co(LOW_PRIORITY_KEY));

    List<String> seriesColors = new ArrayList<>();
    if (existData(exceptionPriorities)) {
      seriesColors.add(colors.getTaskExceptionPriority());
    }
    if (existData(highPriorities)) {
      seriesColors.add(colors.getTaskHighPriority());
    }
    if (existData(normalPriorities)) {
      seriesColors.add(colors.getTaskNormalPriority());
    }
    if (existData(lowPriorities)) {
      seriesColors.add(colors.getTaskLowPriority());
    }

    return seriesColors;
  }

  /**
   * get color for Case's state chart
   * Build with correct order: CREATED, RUNNING, DONE, FAILED
   * @param chartData
   * @param colors
   * @return list of colors
   */
  public static List<String> getCaseStateColors(Map<String, Number> chartData, StatisticColors colors) {
    Number createdCases = chartData.get(Ivy.cms().co(CREATED_CASE_KEY));
    Number runningCases = chartData.get(Ivy.cms().co(RUNNING_CASE_KEY));
    Number doneCases = chartData.get(Ivy.cms().co(DONE_CASE_KEY));
    Number failedCases = chartData.get(Ivy.cms().co(FAILED_CASE_KEY));

    List<String> seriesColors = new ArrayList<>();
    if (existData(createdCases)) {
      seriesColors.add(colors.getCreatedCase());
    }
    if (existData(runningCases)) {
      seriesColors.add(colors.getRunningCase());
    }
    if (existData(doneCases)) {
      seriesColors.add(colors.getDoneCase());
    }
    if (existData(failedCases)) {
      seriesColors.add(colors.getFailedCase());
    }

    return seriesColors;
  }
  
  private static boolean existData(Number data) {
    return data != null && data.intValue() > 0;
  }
}
