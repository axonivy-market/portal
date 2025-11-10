package com.axonivy.portal.bean.dashboard;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetPriorityFilterBean implements Serializable {

  private static final long serialVersionUID = 5710087841257652703L;

  private static List<FilterOperator> priorityOperators = FilterOperator.PRIORITY_OPERATORS.stream().toList();
  private static List<FilterOperator> statisticOperators = FilterOperator.STATISTIC_TEXT_OPERATORS.stream().toList();
  private static final String TASK_PRIORITY_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/";

  private List<String> priorities;
  private String prioritiesString;

  public void init(BaseFilter filter) {
    List<WorkflowPriority> wfPriority = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH,
        WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    priorities = wfPriority.stream().map(priority -> priority.name()).toList();

    prioritiesString = String.join(", ", filter.getValues());
  }

  public List<FilterOperator> getPriorityOperators() {
    return priorityOperators;
  }

  public List<FilterOperator> getStatisticOperators() {
    return statisticOperators;
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
