package ch.ivy.addon.portalkit.taskfilter;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

/**
 * @deprecated use TaskNameFilter in package ch.ivy.addon.portalkit.casefilter.impl
 *
 */
@Deprecated(since="8.0.18")
public class TaskNameFilter extends TaskFilter {

  private String name;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/name");
  }

  @Override
  public String value() {
    return StringUtils.isNotBlank(name) ? String.format(DOUBLE_QUOTES, name) : ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    if (StringUtils.isBlank(name)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", name.trim());
    return TaskQuery.create().where().name().isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    name = "";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
