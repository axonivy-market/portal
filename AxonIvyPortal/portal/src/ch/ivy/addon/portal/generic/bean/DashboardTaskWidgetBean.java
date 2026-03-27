package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.components.util.HtmlUtils;
import com.axonivy.portal.enums.BulkActionType;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardTaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1098133393632524689L;

  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private TaskEmptyMessage noTasksMessage;

  private boolean showDelegationColumn;

  public boolean isShowDelegationColumn() {
    return showDelegationColumn;
  }

  public void setShowDelegationColumn(boolean showDelegationColumn) {
    this.showDelegationColumn = showDelegationColumn;
  }

  @PostConstruct
  public void init() {
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(
            GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }

  public void resetAndOpenTask() throws IOException {
    TaskUtils.resetTask(selectedTask);
    FacesContext.getCurrentInstance().getExternalContext()
        .redirect(selectedTask.getStartLinkEmbedded().getRelative());
  }

  public void handleRowSelectEventOnTaskWidget(SelectEvent<Object> event)
      throws IOException {
    ITask task = ((ITask) event.getObject());
    handleSelectedTask(task);
  }

  private void handleSelectedTask(ITask task) throws IOException {
    if (isRunningTaskWhenClickingOnTaskInList) {
      handleStartTask(task);
    } else {
      navigateToSelectedTaskDetails(task);
    }
  }

  public void handleStartTask(ITask task) throws IOException {
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    String selectedDashboardId =
        Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardId).orElse(null);
    selectedTask = task;
    TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE,
        PortalConstants.RESET_TASK_CONFIRMATION_DIALOG, selectedDashboardId);
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    String uuid = ((ITask) event.getObject()).uuid();
    PortalNavigator.navigateToPortalTaskDetails(uuid);
  }

  public void navigateToSelectedTaskDetails(ITask task) {
    PortalNavigator.navigateToPortalTaskDetails(task.uuid());
  }

  public String createParseTextFromHtml(String text) {
    return HtmlUtils.parseTextFromHtml(text);
  }

  public TaskEmptyMessage getNoTasksMessage() {
    if (noTasksMessage == null) {
      List<TaskEmptyMessage> messages = Stream.of(TaskEmptyMessage.values())
          .collect(Collectors.toList());
      Random random = new Random();
      int index = random.ints(0, messages.size()).findFirst().getAsInt();
      noTasksMessage = messages.get(index);
    }
    return noTasksMessage;
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public void toggleDelegationColumn(TaskDashboardWidget widget) {
    widget.setShowSelection(!widget.isShowSelection());
    if (!widget.isShowSelection()) {
      widget.setBulkActionType(BulkActionType.NONE);
    } else {
      widget.setBulkActionType(BulkActionType.DELEGATE);
    }
  }

  public void onSelectTask(BulkActionType bulkActionType, ITask task, List<ITask> selectedTasksForBulkDelegation, Map<String, Boolean> taskSelectionMap) {
    boolean isSelected = Boolean.TRUE.equals(taskSelectionMap.get(task.uuid()));
    // switch (bulkActionType) {
    //   case DELEGATE:
        
    //     break;
    
    //   default:
    //     break;
    // }

    if (isSelected) {
      selectedTasksForBulkDelegation.add(task);
    } else {
      selectedTasksForBulkDelegation.remove(task);
    }
    // Ivy.log().info(selectedTasksForBulkDelegation);
  }
}
