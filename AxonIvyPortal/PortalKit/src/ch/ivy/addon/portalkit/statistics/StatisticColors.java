package ch.ivy.addon.portalkit.statistics;

import java.io.Serializable;

public class StatisticColors implements Serializable {

  private static final long serialVersionUID = -3714369331118699382L;
  public static final String DEFAULT_EXCEPTION_PRIORITY_COLOR = "#EE4A52";
  public static final String DEFAULT_HIGH_PRIORITY_COLOR = "#007095";
  public static final String DEFAULT_NORMAL_PRIORITY_COLOR = "#49BAD6";
  public static final String DEFAULT_LOW_PRIORITY_COLOR = "#90DDE9";

  public static final String DEFAULT_CREATED_CASE_COLOR = "#C9C9C9";
  public static final String DEFAULT_RUNNING_CASE_COLOR = "#007095";
  public static final String DEFAULT_DONE_CASE_COLOR = "#48C46B";
  public static final String DEFAULT_FAILED_CASE_COLOR = "#EE4A52";

  public static final String DEFAULT_ELAPSED_TIME_COLOR = "#007095";

  public static final String DEFAULT_TASK_EXPIRIED_COLOR = "#EE4A52";
  public static final String DEFAULT_TASK_TODAY_COLOR = "#007095";
  public static final String DEFAULT_TASK_WEEK_COLOR = "#007095";
  public static final String DEFAULT_TASK_MONTH_COLOR = "#49BAD6";
  public static final String DEFAULT_TASK_YEAR_COLOR = "#49BAD6";

  public static final String DEFAULT_LEGEND_COLOR = "#1B1B1B";
  
  public static final String DEFAULT_CASES_BY_CATEGORY_COLOR = "#007095";

  // Color for Legend and DataSet label
  private String legendColor = DEFAULT_LEGEND_COLOR;

  // For Tasks
  private String taskExceptionPriority = DEFAULT_EXCEPTION_PRIORITY_COLOR;
  private String taskHighPriority = DEFAULT_HIGH_PRIORITY_COLOR;
  private String taskNormalPriority = DEFAULT_NORMAL_PRIORITY_COLOR;
  private String taskLowPriority = DEFAULT_LOW_PRIORITY_COLOR;

  // For Cases
  private String createdCase = DEFAULT_CREATED_CASE_COLOR;
  private String runningCase = DEFAULT_RUNNING_CASE_COLOR;
  private String doneCase = DEFAULT_DONE_CASE_COLOR;
  private String failedCase = DEFAULT_FAILED_CASE_COLOR;

  // For Elapse time
  private String elapsedTime = DEFAULT_ELAPSED_TIME_COLOR;

  // For Task's expire
  private String taskExpiriedColor = DEFAULT_TASK_EXPIRIED_COLOR;
  private String taskExpiriedTodayColor = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedThisWeekColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedThisMonthColor = DEFAULT_TASK_MONTH_COLOR;
  private String taskExpiriedThisYearColor = DEFAULT_TASK_YEAR_COLOR;

  // For Task's expire in day
  private String taskExpiriedTodayBefore8Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday8Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday9Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday10Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday11Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday12Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday13Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday14Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday15Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday16Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedToday17Color = DEFAULT_TASK_TODAY_COLOR;
  private String taskExpiriedTodayAfter18Color = DEFAULT_TASK_TODAY_COLOR;

  // For Task's expire in week
  private String taskExpiriedMonColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedTueColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedWedColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedThuColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedFriColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedSatColor = DEFAULT_TASK_WEEK_COLOR;
  private String taskExpiriedSunColor = DEFAULT_TASK_WEEK_COLOR;

  // For Task's expire in month
  private String taskExpiriedFirstWeekColor = DEFAULT_TASK_MONTH_COLOR;
  private String taskExpiriedSecondWeekColor = DEFAULT_TASK_MONTH_COLOR;
  private String taskExpiriedThirdWeekColor = DEFAULT_TASK_MONTH_COLOR;
  private String taskExpiriedFourthWeekColor = DEFAULT_TASK_MONTH_COLOR;
  private String taskExpiriedFifthWeekColor = DEFAULT_TASK_MONTH_COLOR;

  // For Task's expire in year
  private String taskExpiriedJanColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedFebColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedMarColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedAprColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedMayColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedJuneColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedJulyColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedAugColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedSeptColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedOctColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedNovColor = DEFAULT_TASK_YEAR_COLOR;
  private String taskExpiriedDecColor = DEFAULT_TASK_YEAR_COLOR;
  
  // For Cases by category
  private String casesByCategoryColor = DEFAULT_CASES_BY_CATEGORY_COLOR;

  public String getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(String elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public String getTaskExceptionPriority() {
    return taskExceptionPriority;
  }

  public void setTaskExceptionPriority(String taskExceptionPriority) {
    this.taskExceptionPriority = taskExceptionPriority;
  }

  public String getTaskHighPriority() {
    return taskHighPriority;
  }

  public void setTaskHighPriority(String taskHighPriority) {
    this.taskHighPriority = taskHighPriority;
  }

  public String getTaskNormalPriority() {
    return taskNormalPriority;
  }

  public void setTaskNormalPriority(String taskNormalPriority) {
    this.taskNormalPriority = taskNormalPriority;
  }

  public String getTaskLowPriority() {
    return taskLowPriority;
  }

  public void setTaskLowPriority(String taskLowPriority) {
    this.taskLowPriority = taskLowPriority;
  }

  public String getCreatedCase() {
    return createdCase;
  }

  public void setCreatedCase(String createdCase) {
    this.createdCase = createdCase;
  }

  public String getRunningCase() {
    return runningCase;
  }

  public void setRunningCase(String runningCase) {
    this.runningCase = runningCase;
  }

  public String getDoneCase() {
    return doneCase;
  }

  public void setDoneCase(String doneCase) {
    this.doneCase = doneCase;
  }

  public String getFailedCase() {
    return failedCase;
  }

  public void setFailedCase(String failedCase) {
    this.failedCase = failedCase;
  }

  public String getLegendColor() {
    return legendColor;
  }

  public void setLegendColor(String legendColor) {
    this.legendColor = legendColor;
  }

  public String getTaskExpiriedColor() {
    return taskExpiriedColor;
  }

  public void setTaskExpiriedColor(String taskExpiriedColor) {
    this.taskExpiriedColor = taskExpiriedColor;
  }

  public String getTaskExpiriedTodayColor() {
    return taskExpiriedTodayColor;
  }

  public void setTaskExpiriedTodayColor(String taskExpiriedTodayColor) {
    this.taskExpiriedTodayColor = taskExpiriedTodayColor;
  }

  public String getTaskExpiriedThisWeekColor() {
    return taskExpiriedThisWeekColor;
  }

  public void setTaskExpiriedThisWeekColor(String taskExpiriedThisWeekColor) {
    this.taskExpiriedThisWeekColor = taskExpiriedThisWeekColor;
  }

  public String getTaskExpiriedThisMonthColor() {
    return taskExpiriedThisMonthColor;
  }

  public void setTaskExpiriedThisMonthColor(String taskExpiriedThisMonthColor) {
    this.taskExpiriedThisMonthColor = taskExpiriedThisMonthColor;
  }

  public String getTaskExpiriedThisYearColor() {
    return taskExpiriedThisYearColor;
  }

  public void setTaskExpiriedThisYearColor(String taskExpiriedThisYearColor) {
    this.taskExpiriedThisYearColor = taskExpiriedThisYearColor;
  }

  public String getTaskExpiriedToday8Color() {
    return taskExpiriedToday8Color;
  }

  public void setTaskExpiriedToday8Color(String taskExpiriedToday8Color) {
    this.taskExpiriedToday8Color = taskExpiriedToday8Color;
  }

  public String getTaskExpiriedToday9Color() {
    return taskExpiriedToday9Color;
  }

  public void setTaskExpiriedToday9Color(String taskExpiriedToday9Color) {
    this.taskExpiriedToday9Color = taskExpiriedToday9Color;
  }

  public String getTaskExpiriedToday10Color() {
    return taskExpiriedToday10Color;
  }

  public void setTaskExpiriedToday10Color(String taskExpiriedToday10Color) {
    this.taskExpiriedToday10Color = taskExpiriedToday10Color;
  }

  public String getTaskExpiriedToday11Color() {
    return taskExpiriedToday11Color;
  }

  public void setTaskExpiriedToday11Color(String taskExpiriedToday11Color) {
    this.taskExpiriedToday11Color = taskExpiriedToday11Color;
  }

  public String getTaskExpiriedToday12Color() {
    return taskExpiriedToday12Color;
  }

  public void setTaskExpiriedToday12Color(String taskExpiriedToday12Color) {
    this.taskExpiriedToday12Color = taskExpiriedToday12Color;
  }

  public String getTaskExpiriedToday13Color() {
    return taskExpiriedToday13Color;
  }

  public void setTaskExpiriedToday13Color(String taskExpiriedToday13Color) {
    this.taskExpiriedToday13Color = taskExpiriedToday13Color;
  }

  public String getTaskExpiriedToday14Color() {
    return taskExpiriedToday14Color;
  }

  public void setTaskExpiriedToday14Color(String taskExpiriedToday14Color) {
    this.taskExpiriedToday14Color = taskExpiriedToday14Color;
  }

  public String getTaskExpiriedToday15Color() {
    return taskExpiriedToday15Color;
  }

  public void setTaskExpiriedToday15Color(String taskExpiriedToday15Color) {
    this.taskExpiriedToday15Color = taskExpiriedToday15Color;
  }

  public String getTaskExpiriedToday16Color() {
    return taskExpiriedToday16Color;
  }

  public void setTaskExpiriedToday16Color(String taskExpiriedToday16Color) {
    this.taskExpiriedToday16Color = taskExpiriedToday16Color;
  }

  public String getTaskExpiriedToday17Color() {
    return taskExpiriedToday17Color;
  }

  public void setTaskExpiriedToday17Color(String taskExpiriedToday17Color) {
    this.taskExpiriedToday17Color = taskExpiriedToday17Color;
  }

  public String getTaskExpiriedTodayBefore8Color() {
    return taskExpiriedTodayBefore8Color;
  }

  public void setTaskExpiriedTodayBefore8Color(String taskExpiriedTodayBefore8Color) {
    this.taskExpiriedTodayBefore8Color = taskExpiriedTodayBefore8Color;
  }

  public String getTaskExpiriedTodayAfter18Color() {
    return taskExpiriedTodayAfter18Color;
  }

  public void setTaskExpiriedTodayAfter18Color(String taskExpiriedTodayAfter18Color) {
    this.taskExpiriedTodayAfter18Color = taskExpiriedTodayAfter18Color;
  }

  public String getTaskExpiriedMonColor() {
    return taskExpiriedMonColor;
  }

  public void setTaskExpiriedMonColor(String taskExpiriedMonColor) {
    this.taskExpiriedMonColor = taskExpiriedMonColor;
  }

  public String getTaskExpiriedTueColor() {
    return taskExpiriedTueColor;
  }

  public void setTaskExpiriedTueColor(String taskExpiriedTueColor) {
    this.taskExpiriedTueColor = taskExpiriedTueColor;
  }

  public String getTaskExpiriedWedColor() {
    return taskExpiriedWedColor;
  }

  public void setTaskExpiriedWedColor(String taskExpiriedWedColor) {
    this.taskExpiriedWedColor = taskExpiriedWedColor;
  }

  public String getTaskExpiriedThuColor() {
    return taskExpiriedThuColor;
  }

  public void setTaskExpiriedThuColor(String taskExpiriedThuColor) {
    this.taskExpiriedThuColor = taskExpiriedThuColor;
  }

  public String getTaskExpiriedFriColor() {
    return taskExpiriedFriColor;
  }

  public void setTaskExpiriedFriColor(String taskExpiriedFriColor) {
    this.taskExpiriedFriColor = taskExpiriedFriColor;
  }

  public String getTaskExpiriedSatColor() {
    return taskExpiriedSatColor;
  }

  public void setTaskExpiriedSatColor(String taskExpiriedSatColor) {
    this.taskExpiriedSatColor = taskExpiriedSatColor;
  }

  public String getTaskExpiriedSunColor() {
    return taskExpiriedSunColor;
  }

  public void setTaskExpiriedSunColor(String taskExpiriedSunColor) {
    this.taskExpiriedSunColor = taskExpiriedSunColor;
  }

  public String getTaskExpiriedFirstWeekColor() {
    return taskExpiriedFirstWeekColor;
  }

  public void setTaskExpiriedFirstWeekColor(String taskExpiriedFirstWeekColor) {
    this.taskExpiriedFirstWeekColor = taskExpiriedFirstWeekColor;
  }

  public String getTaskExpiriedSecondWeekColor() {
    return taskExpiriedSecondWeekColor;
  }

  public void setTaskExpiriedSecondWeekColor(String taskExpiriedSecondWeekColor) {
    this.taskExpiriedSecondWeekColor = taskExpiriedSecondWeekColor;
  }

  public String getTaskExpiriedThirdWeekColor() {
    return taskExpiriedThirdWeekColor;
  }

  public void setTaskExpiriedThirdWeekColor(String taskExpiriedThirdWeekColor) {
    this.taskExpiriedThirdWeekColor = taskExpiriedThirdWeekColor;
  }

  public String getTaskExpiriedFourthWeekColor() {
    return taskExpiriedFourthWeekColor;
  }

  public void setTaskExpiriedFourthWeekColor(String taskExpiriedFourthWeekColor) {
    this.taskExpiriedFourthWeekColor = taskExpiriedFourthWeekColor;
  }

  public String getTaskExpiriedFifthWeekColor() {
    return taskExpiriedFifthWeekColor;
  }

  public void setTaskExpiriedFifthWeekColor(String taskExpiriedFifthWeekColor) {
    this.taskExpiriedFifthWeekColor = taskExpiriedFifthWeekColor;
  }

  public String getTaskExpiriedJanColor() {
    return taskExpiriedJanColor;
  }

  public void setTaskExpiriedJanColor(String taskExpiriedJanColor) {
    this.taskExpiriedJanColor = taskExpiriedJanColor;
  }

  public String getTaskExpiriedFebColor() {
    return taskExpiriedFebColor;
  }

  public void setTaskExpiriedFebColor(String taskExpiriedFebColor) {
    this.taskExpiriedFebColor = taskExpiriedFebColor;
  }

  public String getTaskExpiriedMarColor() {
    return taskExpiriedMarColor;
  }

  public void setTaskExpiriedMarColor(String taskExpiriedMarColor) {
    this.taskExpiriedMarColor = taskExpiriedMarColor;
  }

  public String getTaskExpiriedAprColor() {
    return taskExpiriedAprColor;
  }

  public void setTaskExpiriedAprColor(String taskExpiriedAprColor) {
    this.taskExpiriedAprColor = taskExpiriedAprColor;
  }

  public String getTaskExpiriedMayColor() {
    return taskExpiriedMayColor;
  }

  public void setTaskExpiriedMayColor(String taskExpiriedMayColor) {
    this.taskExpiriedMayColor = taskExpiriedMayColor;
  }

  public String getTaskExpiriedJuneColor() {
    return taskExpiriedJuneColor;
  }

  public void setTaskExpiriedJuneColor(String taskExpiriedJuneColor) {
    this.taskExpiriedJuneColor = taskExpiriedJuneColor;
  }

  public String getTaskExpiriedJulyColor() {
    return taskExpiriedJulyColor;
  }

  public void setTaskExpiriedJulyColor(String taskExpiriedJulyColor) {
    this.taskExpiriedJulyColor = taskExpiriedJulyColor;
  }

  public String getTaskExpiriedAugColor() {
    return taskExpiriedAugColor;
  }

  public void setTaskExpiriedAugColor(String taskExpiriedAugColor) {
    this.taskExpiriedAugColor = taskExpiriedAugColor;
  }

  public String getTaskExpiriedSeptColor() {
    return taskExpiriedSeptColor;
  }

  public void setTaskExpiriedSeptColor(String taskExpiriedSeptColor) {
    this.taskExpiriedSeptColor = taskExpiriedSeptColor;
  }

  public String getTaskExpiriedOctColor() {
    return taskExpiriedOctColor;
  }

  public void setTaskExpiriedOctColor(String taskExpiriedOctColor) {
    this.taskExpiriedOctColor = taskExpiriedOctColor;
  }

  public String getTaskExpiriedNovColor() {
    return taskExpiriedNovColor;
  }

  public void setTaskExpiriedNovColor(String taskExpiriedNovColor) {
    this.taskExpiriedNovColor = taskExpiriedNovColor;
  }

  public String getTaskExpiriedDecColor() {
    return taskExpiriedDecColor;
  }

  public void setTaskExpiriedDecColor(String taskExpiriedDecColor) {
    this.taskExpiriedDecColor = taskExpiriedDecColor;
  }

  public String getCasesByCategoryColor() {
    return casesByCategoryColor;
  }

  public void setCasesByCategoryColor(String casesByCategoryColor) {
    this.casesByCategoryColor = casesByCategoryColor;
  }

}
