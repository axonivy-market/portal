package com.axonivy.portal.dto.dashboard.bulkaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.enums.BulkActionType;

import ch.ivyteam.ivy.workflow.ITask;

public class TaskSelectionDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private BulkActionType type;
  private List<ITask> selectedTasks;

  private TaskSelectionDto(BulkActionType type) {
    this.type = type;
    this.selectedTasks = new ArrayList<>();
  }

  public static TaskSelectionDto delegate() {
    return new TaskSelectionDto(BulkActionType.DELEGATE);
  }

  public BulkActionType getType() {
    return type;
  }

  public List<ITask> getSelectedTasks() {
    return selectedTasks;
  }

  public int getSelectedCount() {
    return selectedTasks.size();
  }

  public boolean isSelected(ITask task) {
    return selectedTasks.stream().anyMatch(t -> t.uuid().equals(task.uuid()));
  }

  public void addTask(ITask task) {
    if (!isSelected(task)) {
      selectedTasks.add(task);
    }
  }

  public void removeTask(ITask task) {
    selectedTasks.removeIf(t -> t.uuid().equals(task.uuid()));
  }

  public void clearSelection() {
    selectedTasks.clear();
  }
}
