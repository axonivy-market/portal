package ch.ivy.addon.portalkit.statistics;

public class Colors {
  private static final String COMMA = ",";

  private static final String EXCEPTION_PRIORITY_COLOR = "ff5a5a";
  private static final String HIGH_PRIORITY_COLOR = "ffc0c0";
  private static final String NORMAL_PRIORITY_COLOR = "96dc82";
  private static final String LOW_PRIORITY_COLOR = "d4d4d4";

  private static final String CREATED_CASE_COLOR = "96dc82";
  private static final String RUNNING_CASE_COLOR = "4bb2c5";
  private static final String DONE_CASE_COLOR = "eaa228";
  private static final String FAILED_CASE_COLOR = "c5b47f";

  public static String getPriorityColors() {
    return String.join(COMMA,
        EXCEPTION_PRIORITY_COLOR,
        HIGH_PRIORITY_COLOR,
        NORMAL_PRIORITY_COLOR,
        LOW_PRIORITY_COLOR);
  }

  public static String getCaseStateColors() {
    return String.join(COMMA,
        CREATED_CASE_COLOR,
        RUNNING_CASE_COLOR,
        DONE_CASE_COLOR,
        FAILED_CASE_COLOR);
  }
}