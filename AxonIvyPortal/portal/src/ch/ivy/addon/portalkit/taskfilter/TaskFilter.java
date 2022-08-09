package ch.ivy.addon.portalkit.taskfilter;

import ch.ivy.addon.portalkit.filter.AbstractFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

/**
 * Extend this class for custom task filter for example task name, task state..
 * Refer for {@link TaskNameFilter} for sample
 *
 */
public abstract class TaskFilter extends AbstractFilter<TaskQuery> {
}
