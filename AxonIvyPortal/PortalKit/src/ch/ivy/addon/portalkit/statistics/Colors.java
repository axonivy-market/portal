package ch.ivy.addon.portalkit.statistics;

import java.util.ArrayList;
import java.util.List;

public class Colors {

  public static final String SEMICOLON = ";";

  public static final String PRIORITY_COLOR = "PRIORITY_COLOR";
  public static final String STATE_COLOR = "STATE_COLOR";

  private Colors() {}

  /**
   * get color for Task's priority chart
   * Build with correct order: EXCEPTION, HIGH, NORMAL, LOW
   * @param colors
   * @return list of colors
   */
  public static List<String> getPriorityColors(StatisticColors colors) {
    List<String> seriesColors = new ArrayList<>();
    seriesColors.add(colors.getTaskExceptionPriority());
    seriesColors.add(colors.getTaskHighPriority());
    seriesColors.add(colors.getTaskNormalPriority());
    seriesColors.add(colors.getTaskLowPriority());
    return seriesColors;
  }

  /**
   * get color for Case's state chart
   * Build with correct order: CREATED, RUNNING, DONE, FAILED
   * @param colors
   * @return list of colors
   */
  public static List<String> getCaseStateColors(StatisticColors colors) {
    List<String> seriesColors = new ArrayList<>();
    seriesColors.add(colors.getCreatedCase());
    seriesColors.add(colors.getRunningCase());
    seriesColors.add(colors.getDoneCase());
    seriesColors.add(colors.getFailedCase());
    return seriesColors;
  }
}
