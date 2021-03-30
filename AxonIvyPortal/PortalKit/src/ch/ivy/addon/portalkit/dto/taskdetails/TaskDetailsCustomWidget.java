package ch.ivy.addon.portalkit.dto.taskdetails;

public class TaskDetailsCustomWidget extends TaskDetailsWidget {

  private static final long serialVersionUID = -1623176520422054253L;

  private TaskDetailsCustomWidgetData data;

  public TaskDetailsCustomWidget() {
  }

  public TaskDetailsCustomWidgetData getData() {
    return data;
  }

  public void setData(TaskDetailsCustomWidgetData data) {
    this.data = data;
  }
}
