package portalmigration.version112.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import portalmigration.version112.enums.StatisticTimePeriodSelection;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticFilter implements Cloneable {
  
  private StatisticTimePeriodSelection timePeriodSelection = StatisticTimePeriodSelection.CUSTOM;
  private Date createdDateFrom;
  private Date createdDateTo;

  private List<String> selectedCaseCategories = new ArrayList<>();
  private boolean isAllCategoriesSelected;

  private List<String> selectedRoles = new ArrayList<>();
  private boolean isAllRolesSelected = true;

  private List<CaseState> selectedCaseStates = new ArrayList<>();
  private boolean isAllCaseStatesSelected = true;

  private List<WorkflowPriority> selectedTaskPriorities = new ArrayList<>();
  private boolean isAllTaskPrioritiesSelected = true;
  private Map<String, List<String>> customFieldFilters;
  
  private List<String> selectedCustomVarCharFields1 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields2 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields3 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields4 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields5 = new ArrayList<>();

  public Date getCreatedDateFrom() {
    return createdDateFrom;
  }

  public void setCreatedDateFrom(Date createdDateFrom) {
    this.createdDateFrom = createdDateFrom;
  }

  public Date getCreatedDateTo() {
    return createdDateTo;
  }

  public void setCreatedDateTo(Date createdDateTo) {
    this.createdDateTo = createdDateTo;
  }

  public List<String> getSelectedRoles() {
    return selectedRoles;
  }

  public void setSelectedRoles(List<String> selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public List<CaseState> getSelectedCaseStates() {
    return selectedCaseStates;
  }

  public void setSelectedCaseStates(List<CaseState> selectedCaseStates) {
    this.selectedCaseStates = selectedCaseStates;
  }

  public List<WorkflowPriority> getSelectedTaskPriorities() {
    return selectedTaskPriorities;
  }

  public void setSelectedTaskPriorities(List<WorkflowPriority> selectedTaskPriorities) {
    this.selectedTaskPriorities = selectedTaskPriorities;
  }

  public String getCaseStateName(CaseState state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state);
  }

  public String getPriorityName(WorkflowPriority priority) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority);
  }

  public StatisticTimePeriodSelection getTimePeriodSelection() {
    return timePeriodSelection;
  }

  public void setTimePeriodSelection(StatisticTimePeriodSelection timePeriodSelection) {
    this.timePeriodSelection = timePeriodSelection;
  }

  @Deprecated
  public boolean getIsAllCategoriesSelected() {
    return isAllCategoriesSelected;
  }

  @Deprecated
  public void setIsAllCategoriesSelected(boolean isAllCategoriesSelected) {
    this.isAllCategoriesSelected = isAllCategoriesSelected;
  }

  public List<String> getSelectedCaseCategories() {
    return selectedCaseCategories;
  }

  public void setSelectedCaseCategories(List<String> selectedCaseCategories) {
    this.selectedCaseCategories = selectedCaseCategories;
  }

  public boolean getIsAllRolesSelected() {
    return isAllRolesSelected;
  }

  public void setIsAllRolesSelected(boolean isAllRolesSelected) {
    this.isAllRolesSelected = isAllRolesSelected;
  }
  
  public boolean getIsAllCaseStatesSelected() {
    return isAllCaseStatesSelected;
  }

  public void setIsAllCaseStatesSelected(boolean isAllCaseStatesSelected) {
    this.isAllCaseStatesSelected = isAllCaseStatesSelected;
  }
  
  public boolean getIsAllTaskPrioritiesSelected() {
    return isAllTaskPrioritiesSelected;
  }

  public void setIsAllTaskPrioritiesSelected(boolean isAllTaskPrioritiesSelected) {
    this.isAllTaskPrioritiesSelected = isAllTaskPrioritiesSelected;
  }

  @Override
  public Object clone() throws CloneNotSupportedException { 
    return super.clone();
  }

  public Map<String, List<String>> getCustomFieldFilters() {
    return customFieldFilters;
  }

  public void setCustomFieldFilters(Map<String, List<String>> customFieldFilters) {
    this.customFieldFilters = customFieldFilters;
  }
  
  public List<String> getCustomFieldFilter(String customFieldName) {
    return this.customFieldFilters.get(customFieldName);
  }
  
  public List<String> findSavedCustomFields(){
    if (customFieldFilters == null) {
      initCustomFieldFromDeprecatedCustomVarChar();
    }
    return customFieldFilters.entrySet()
        .stream()
        .filter(item -> CollectionUtils.isNotEmpty(item.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }
  
  @Deprecated
  public List<String> getSelectedCustomVarCharFields1() {
    return selectedCustomVarCharFields1;
  }

  @Deprecated
  public void setSelectedCustomVarCharFields1(List<String> selectedCustomVarCharFields1) {
    this.selectedCustomVarCharFields1 = selectedCustomVarCharFields1;
  }

  @Deprecated
  public List<String> getSelectedCustomVarCharFields2() {
    return selectedCustomVarCharFields2;
  }

  @Deprecated
  public void setSelectedCustomVarCharFields2(List<String> selectedCustomVarCharFields2) {
    this.selectedCustomVarCharFields2 = selectedCustomVarCharFields2;
  }

  @Deprecated
  public List<String> getSelectedCustomVarCharFields3() {
    return selectedCustomVarCharFields3;
  }

  @Deprecated
  public void setSelectedCustomVarCharFields3(List<String> selectedCustomVarCharFields3) {
    this.selectedCustomVarCharFields3 = selectedCustomVarCharFields3;
  }

  @Deprecated
  public List<String> getSelectedCustomVarCharFields4() {
    return selectedCustomVarCharFields4;
  }

  @Deprecated
  public void setSelectedCustomVarCharFields4(List<String> selectedCustomVarCharFields4) {
    this.selectedCustomVarCharFields4 = selectedCustomVarCharFields4;
  }

  @Deprecated
  public List<String> getSelectedCustomVarCharFields5() {
    return selectedCustomVarCharFields5;
  }

  @Deprecated
  public void setSelectedCustomVarCharFields5(List<String> selectedCustomVarCharFields5) {
    this.selectedCustomVarCharFields5 = selectedCustomVarCharFields5;
  }
  
  private void initCustomFieldFromDeprecatedCustomVarChar() {
    customFieldFilters = new HashMap<>();
    if (CollectionUtils.isNotEmpty(selectedCustomVarCharFields1)) {
      customFieldFilters.put("CustomVarCharFields1", selectedCustomVarCharFields1);
    }
    if (CollectionUtils.isNotEmpty(selectedCustomVarCharFields2)) {
      customFieldFilters.put("CustomVarCharFields2", selectedCustomVarCharFields2);
    }
    if (CollectionUtils.isNotEmpty(selectedCustomVarCharFields3)) {
      customFieldFilters.put("CustomVarCharFields3", selectedCustomVarCharFields3);
    }
    if (CollectionUtils.isNotEmpty(selectedCustomVarCharFields4)) {
      customFieldFilters.put("CustomVarCharFields4", selectedCustomVarCharFields4);
    }
    if (CollectionUtils.isNotEmpty(selectedCustomVarCharFields5)) {
      customFieldFilters.put("CustomVarCharFields5", selectedCustomVarCharFields5);
    }
  }
}
