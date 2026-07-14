package com.axonivy.portal.bean.dashboard;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.annotation.PostConstruct;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.GlobalOperatorPolicyService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class WidgetPriorityFilterBean implements Serializable {

  private static final long serialVersionUID = 5710087841257652703L;

  private List<FilterOperator> resolvedPriorityOperators;
  private static final String TASK_PRIORITY_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/";

  private List<String> priorities;
  private String prioritiesString;

  @PostConstruct
  public void initOperators() {
    resolvedPriorityOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.PRIORITY_OPERATORS.stream().toList());
  }

  public void init(DashboardFilter filter) {
    List<WorkflowPriority> wfPriority = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH,
        WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    priorities = wfPriority.stream().map(priority -> priority.name()).toList();

    prioritiesString = String.join(", ", filter.getValues());
  }

  public List<FilterOperator> getPriorityOperators() {
    return resolvedPriorityOperators;
  }

  public List<String> getPriorities() {
    return priorities;
  }

  public String getPrioritiesString() {
    return prioritiesString;
  }

  public void setprioritiesString(String prioritiesString) {
    this.prioritiesString = prioritiesString;
  }

  public void updatePrioritiesString(List<String> priorities) {
    prioritiesString = String.join(", ", priorities);
  }
  
  public String getUserFriendlyTaskPriority(String priority) {
    if (priority == null) {
      return EMPTY;
    }
    return Ivy.cms().co(TASK_PRIORITY_CMS_PATH + priority + "_LOWERCASE");
  }
}
