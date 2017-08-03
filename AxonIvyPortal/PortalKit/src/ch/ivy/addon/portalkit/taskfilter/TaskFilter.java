package ch.ivy.addon.portalkit.taskfilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public abstract class TaskFilter {

  private String label;

  public TaskFilter() {
    label = label();
  }

  protected static final String ALL = "All";
  protected static final String COMMA = ", ";
  protected static final String GREATER_EQUAL = ">= %s";
  protected static final String LESS_EQUAL = "<= %s";
  protected static final String DASH = "%s - %s";
  protected static final String DOUBLE_QUOTES = "\"%s\"";

  /**
   * @return the string label is displayed in filter item.
   */
  public abstract String label();

  /**
   * @return the string value is displayed in filter item.
   */
  public abstract String value();

  /**
   * @return must be null if filtered values are empty.
   */
  public abstract TaskQuery buildQuery();

  /**
   * Reset values after close filter item.
   */
  public abstract void resetValues();

  /**
   * Override this method if need to validates filtered values.
   */
  public void validate() {};

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
