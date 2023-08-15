package ch.ivy.addon.portalkit.filter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.Query;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

/**
 * Base class for Filter
 *
 * @param <T> {@link TaskQuery} or {@link CaseQuery}
 */
public abstract class AbstractFilter<T extends Query<?>> {

  /**
   * All options in filter are selected e.g all states
   */
  public static final String ALL = "All";
  protected static final String COMMA = ", ";
  protected static final String GREATER_EQUAL = ">= %s";
  protected static final String LESS_EQUAL = "<= %s";
  protected static final String DASH = "%s - %s";
  protected static final String DOUBLE_QUOTES = "\"%s\"";

  /**
   * <p>
   * The string label is displayed in filter item and checkbox filter item.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * State: SUSPENDED, RESUMED
   * Label is "state", and value is "SUSPENDED, RESUMED".
   * </pre></code>
   * </p>
   * 
   * @return label: String
   */
  public abstract String label();

  /**
   * <p>
   * The string value is displayed in filter item.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * State: SUSPENDED, RESUMED
   * Label is "state", and value is "SUSPENDED, RESUMED".
   * </pre></code>
   * </p>
   * <p>
   * <b>Format: </b> <br/>
   * To make it consistent on UI, use the constants in {@link AbstractFilter} to display.<br/>
   * Return the constant ALL if filtered values are empty or full list of suggestion selections.
   * </p>
   * 
   * @return value: String
   */
  public abstract String value();

  /**
   * <p>
   * Build Query with filtered values.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if (StringUtils.isBlank(description)) {
   *   return null;
   * }
   * String containingKeyword = String.format("%%%s%%", description.trim());
   * return TaskQuery.create().where().description().isLikeIgnoreCase(containingKeyword);
   * </pre></code>
   * </p>
   * 
   * @return must be null if filtered values are empty.
   */
  public abstract T buildQuery();

  /**
   * <p>
   * Set filtered variables to new values (mostly empty) after close/deselect filter item on UI.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * In DescriptionFilter: description = "";
   * In StateFilter: selectedStates = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);
   * </pre></code>
   * </p>
   */
  public abstract void resetValues();

  /**
   * <p>
   * Override this method if need to validate filtered values. If values are incorrect, use the methods:
   * validationFailed and addMessage with clientId is "advanced-filter-error-messages"
   * </p>
   * <p>
   * <b>Example: </b> <code>
   * <pre>
   * String message = ...;
   * FacesContext.getCurrentInstance().validationFailed();
   * FacesContext.getCurrentInstance().addMessage("advanced-filter-error-messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
   * </pre>
   * </code>
   * </p>
   */
  public void validate() {}
  
  /**
   * <p>
   * Check filter category show default in Task/Case List
   * </p>
   * <p>
   * <b>Example: </b>
   * Default categories of Task list: Expiry, Responsible, State
   * Default categories of Case list: Created, Finished, State
   * </p>
   * @return is default filter
   */
  public boolean defaultFilter() {
    return false;
  }

  /**
   * <p>
   * Override this method if need to reload data list (such as case list, task list) after add/remove new filter
   * </p>
   * <p>
   * <b>Example: </b>
   * After add/remove Expiry filter, we don't need to reload page because default value of Expiry filter is ALL
   * After add/remove State filter, we need to reload page because default value of State filter is based on user's permission
   * </p>
   * @return is reload view
   */
  public boolean reloadView() {
    return false;
  }

  /**
   * No option in filter are selected e.g No states
   */
  protected String noSelectionLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/NoSelection");
  }
  
  protected String getAllLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/All");
  }

  /**
   * <p>
   * Update filter when open filter content
   * </p>
   */
  public void update() {};
}
