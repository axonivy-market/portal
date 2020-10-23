package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationBean {
  private List<TaskState> filteredStates;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> responsibles;
  private CheckboxTreeNode categoryTree;
  private CheckboxTreeNode[] categoryNodes;
  private TaskDashboardWidget widget;

  @PostConstruct
  public void init() {
    this.filteredStates = Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE);
    this.priorities =
        Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    this.responsibles = new ArrayList<>();
  }
  
  public void preRender(TaskDashboardWidget widget) {
    this.widget = widget;
    buildCategoryTree();
  }
  
  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    this.widget.setCategories(CategoryUtils.getCategoryPaths(categoryNodes));
  }
  
  private void buildCategoryTree() {
    this.categoryTree = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
    this.categoryNodes = CategoryUtils.recoverSelectedCategories(this.categoryTree, this.widget.getCategories());
  }

  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = "";
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = responsible.getDisplayName() + " (" + responsible.getName() + ")";
      }
      return responsible.isEnabled()? responsibleName : Ivy.cms().co("/Labels/disabledUserPrefix") + " " + responsibleName;
    }
    return responsibleName;
  }
  
  public List<SecurityMemberDTO> completeResponsibles(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    switch (state) {
      case SUSPENDED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/SUSPENDED");
      case CREATED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/CREATED");
      case RESUMED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESUMED");
      case DONE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE");
      case PARKED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/PARKED");
      case DESTROYED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED");
      case ZOMBIE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/ZOMBIE");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
    }
  }

  public String getUserFriendlyTaskPriority(WorkflowPriority priority) {
    if (priority == null) {
      return StringUtils.EMPTY;
    }
    switch (priority) {
      case LOW:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/LOW_LOWERCASE");
      case NORMAL:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/NORMAL_LOWERCASE");
      case HIGH:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/HIGH_LOWERCASE");
      case EXCEPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/EXCEPTION_LOWERCASE");
      default:
        return StringUtils.EMPTY;
    }
  }

  public List<TaskState> getFilteredStates() {
    return filteredStates;
  }

  public void setFilteredStates(List<TaskState> filteredStates) {
    this.filteredStates = filteredStates;
  }

  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<SecurityMemberDTO> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<SecurityMemberDTO> responsibles) {
    this.responsibles = responsibles;
  }
  
  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }
  
  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }
}
