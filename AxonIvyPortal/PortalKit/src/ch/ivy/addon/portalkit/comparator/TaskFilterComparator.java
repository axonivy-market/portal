package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;

public class TaskFilterComparator implements Comparator<TaskFilter> {

  @Override
  public int compare(TaskFilter first, TaskFilter second) {
    return first.label().compareTo(second.label());
  }

}
