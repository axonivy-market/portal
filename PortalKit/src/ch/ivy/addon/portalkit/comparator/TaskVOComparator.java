package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.vo.TaskVO;

public class TaskVOComparator implements Comparator<TaskVO> {

  @Override
  public int compare(TaskVO o1, TaskVO o2) {
    return o2.getEdited().compareTo(o1.getEdited());
  }

}