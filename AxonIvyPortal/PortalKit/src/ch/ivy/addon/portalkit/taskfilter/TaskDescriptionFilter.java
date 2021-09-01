package ch.ivy.addon.portalkit.taskfilter;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

/**
 * @deprecated use TaskDescriptionFilter in package ch.ivy.addon.portalkit.casefilter.impl
 *
 */
@Deprecated(since="8.0.18")
public class TaskDescriptionFilter extends TaskFilter {

  private String description;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
  }

  @Override
  public String value() {
    return StringUtils.isNotBlank(description) ? String.format(DOUBLE_QUOTES, description) : ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    if (StringUtils.isBlank(description)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", description.trim());
    return TaskQuery.create().where().description().isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    description = "";
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
