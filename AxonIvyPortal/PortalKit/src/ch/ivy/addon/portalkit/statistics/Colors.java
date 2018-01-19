package ch.ivy.addon.portalkit.statistics;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class Colors {
  private static final String COMMA = ",";

  private static final String EXCEPTION_PRIORITY_COLOR = "ff5a5a";
  private static final String HIGH_PRIORITY_COLOR = "ffc0c0";
  private static final String NORMAL_PRIORITY_COLOR = "96dc82";
  private static final String LOW_PRIORITY_COLOR = "d4d4d4";

  private static final String EXCEPTION_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/exception";
  private static final String HIGH_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/high";
  private static final String NORMAL_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/normal";
  private static final String LOW_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/low";

  private static final String CREATED_CASE_COLOR = "96dc82";
  private static final String RUNNING_CASE_COLOR = "4bb2c5";
  private static final String DONE_CASE_COLOR = "eaa228";
  private static final String FAILED_CASE_COLOR = "c5b47f";

  private static final String CREATED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/created";
  private static final String RUNNING_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/running";
  private static final String DONE_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/done";
  private static final String FAILED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/failed";

  public static String getPriorityColors(Map<String, Number> chartData) {
    Number exceptionPriorities = chartData.get(Ivy.cms().co(EXCEPTION_PRIORITY_KEY));
    Number highPriorities = chartData.get(Ivy.cms().co(HIGH_PRIORITY_KEY));
    Number normalPriorities = chartData.get(Ivy.cms().co(NORMAL_PRIORITY_KEY));
    Number lowPriorities = chartData.get(Ivy.cms().co(LOW_PRIORITY_KEY));

    String seriesColors = StringUtils.EMPTY;
    if (existData(exceptionPriorities)) {
      seriesColors += EXCEPTION_PRIORITY_COLOR + COMMA;
    }
    if (existData(highPriorities)) {
      seriesColors += HIGH_PRIORITY_COLOR + COMMA;
    }
    if (existData(normalPriorities)) {
      seriesColors += NORMAL_PRIORITY_COLOR + COMMA;
    }
    if (existData(lowPriorities)) {
      seriesColors += LOW_PRIORITY_COLOR;
    }
    if (seriesColors.endsWith(COMMA)) {
      seriesColors = seriesColors.substring(0, seriesColors.length() - 1);
    }
    return seriesColors;
  }

  public static String getCaseStateColors(Map<String, Number> chartData) {
    Number exceptionPriorities = chartData.get(Ivy.cms().co(CREATED_CASE_KEY));
    Number highPriorities = chartData.get(Ivy.cms().co(RUNNING_CASE_KEY));
    Number normalPriorities = chartData.get(Ivy.cms().co(DONE_CASE_KEY));
    Number lowPriorities = chartData.get(Ivy.cms().co(FAILED_CASE_KEY));

    String seriesColors = StringUtils.EMPTY;
    if (existData(exceptionPriorities)) {
      seriesColors += CREATED_CASE_COLOR + COMMA;
    }
    if (existData(highPriorities)) {
      seriesColors += RUNNING_CASE_COLOR + COMMA;
    }
    if (existData(normalPriorities)) {
      seriesColors += DONE_CASE_COLOR + COMMA;
    }
    if (existData(lowPriorities)) {
      seriesColors += FAILED_CASE_COLOR;
    }
    if (seriesColors.endsWith(COMMA)) {
      seriesColors = seriesColors.substring(0, seriesColors.length() - 1);
    }
    return seriesColors;
  }

  private static boolean existData(Number data) {
    return data != null && data.intValue() > 0;
  }
}
