package ch.ivy.addon.portalkit.statistics;

import static ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection.CUSTOM;
import static ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection.LAST_6_MONTH;
import static ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection.LAST_MONTH;
import static ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection.LAST_WEEK;
import static ch.ivyteam.ivy.workflow.CaseState.CREATED;
import static ch.ivyteam.ivy.workflow.CaseState.DONE;
import static ch.ivyteam.ivy.workflow.CaseState.RUNNING;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.EXCEPTION;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.HIGH;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.LOW;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.NORMAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class StatisticFilter implements Cloneable {
  
  private StatisticTimePeriodSelection timePeriodSelection;
  @JsonIgnore
  private List<StatisticTimePeriodSelection> allTimePeriodSelection = Arrays.asList(CUSTOM, LAST_WEEK, LAST_MONTH, LAST_6_MONTH);;
  private Date createdDateFrom;
  private Date createdDateTo;

  @JsonIgnore
  private StatisticCaseCategoryFilter caseCategories = new StatisticCaseCategoryFilter();
  private List<String> selectedCaseCategories = new ArrayList<>();
  private boolean isAllCategoriesSelected;

  @JsonIgnore
  private List<Object> roles = new ArrayList<>();
  private List<String> selectedRoles = new ArrayList<>();
  private boolean isAllRolesSelected = true;

  @JsonIgnore
  private List<CaseState> caseStates = Arrays.asList(CREATED, RUNNING, DONE);;
  private List<CaseState> selectedCaseStates = new ArrayList<>();
  private boolean isAllCaseStatesSelected = true;
  
  @JsonIgnore
  private List<WorkflowPriority> taskPriorities = Arrays.asList(EXCEPTION, HIGH, NORMAL, LOW);
  private List<WorkflowPriority> selectedTaskPriorities = new ArrayList<>();
  private boolean isAllTaskPrioritiesSelected = true;
  private Map<String, List<String>> customFieldFilters;
  
  private List<String> selectedCustomVarCharFields1 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields2 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields3 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields4 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields5 = new ArrayList<>();
  
  public void init() {
    initCustomFieldFromDeprecatedCustomVarChar();
    List<IRole> distinctRoles = findDistinctRoles();

    this.roles.add(Ivy.session().getSessionUser());
    this.roles.addAll(distinctRoles);
    
    this.selectedRoles = new ArrayList<>(distinctRoles.stream().map(IRole::getMemberName).collect(Collectors.toList()));
    this.selectedRoles.add(0, Ivy.session().getSessionUser().getMemberName());

    // Initialize list of case states
    this.selectedCaseStates = new ArrayList<>(this.caseStates);

    // Initialize list of task priorities
    this.selectedTaskPriorities = new ArrayList<>(this.taskPriorities);

    // Initialize list of case categories
    caseCategories = new StatisticCaseCategoryFilter();

      this.timePeriodSelection = StatisticTimePeriodSelection.CUSTOM;
  }

  private List<IRole> findDistinctRoles() {
    List<IRole> distinctRoles = findRolesByCallableProcess().stream()
        .filter(role -> role != null && Ivy.session().hasRole(role, false))
        .sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()))
        .collect(Collectors.toList());
    return distinctRoles;
  }
  
  public void initRoles() {
    this.roles.add(Ivy.session().getSessionUser());
    this.roles.addAll(findDistinctRoles());
  }
  
  @SuppressWarnings("unchecked")
  private List<IRole> findRolesByCallableProcess() {
    return IvyExecutor.executeAsSystem(() -> {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        Map<String, List<IRole>> rolesByApp =
            SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findRolesOverAllApplications")
                .call(Ivy.session().getSessionUserName())
                .get("rolesByApp", Map.class);
        return rolesByApp.values().stream()
            .flatMap(List::stream)
            .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(IRole::getName))), ArrayList::new));
      }

      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findRoles")
          .call(Ivy.request().getApplication())
          .get("roles", List.class);
    });
  }

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

  public StatisticCaseCategoryFilter getCaseCategories() {
    return caseCategories;
  }

  public void setCaseCategories(StatisticCaseCategoryFilter caseCategories) {
    this.caseCategories = caseCategories;
  }

  public List<Object> getRoles() {
    return roles;
  }

  public void setRoles(List<Object> roles) {
    this.roles = roles;
  }

  public List<String> getSelectedRoles() {
    return selectedRoles;
  }

  public void setSelectedRoles(List<String> selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public List<CaseState> getCaseStates() {
    return caseStates;
  }

  public void setCaseStates(List<CaseState> caseStates) {
    this.caseStates = caseStates;
  }

  public List<CaseState> getSelectedCaseStates() {
    return selectedCaseStates;
  }

  public void setSelectedCaseStates(List<CaseState> selectedCaseStates) {
    this.selectedCaseStates = selectedCaseStates;
  }

  public List<WorkflowPriority> getTaskPriorities() {
    return taskPriorities;
  }

  public void setTaskPriorities(List<WorkflowPriority> taskPriorities) {
    this.taskPriorities = taskPriorities;
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

  public List<StatisticTimePeriodSelection> getAllTimePeriodSelection() {
    return allTimePeriodSelection;
  }

  public void setAllTimePeriodSelection(List<StatisticTimePeriodSelection> allTimePeriodSelection) {
    this.allTimePeriodSelection = allTimePeriodSelection;
  }

  @Deprecated
  public boolean getIsAllCategoriesSelected() {
    return isAllCategoriesSelected;
  }

  @Deprecated
  public void setIsAllCategoriesSelected(boolean isAllCategoriesSelected) {
    this.isAllCategoriesSelected = isAllCategoriesSelected;
    // For migration
    if (isAllCategoriesSelected && this.caseCategories != null) {
      this.caseCategories.setCategoryPaths(new ArrayList<>());
    }
  }

  public List<String> getSelectedCaseCategories() {
    return selectedCaseCategories;
  }

  public void setSelectedCaseCategories(List<String> selectedCaseCategories) {
    this.selectedCaseCategories = selectedCaseCategories;
    if (this.caseCategories != null) {
      this.caseCategories.setCategoryPaths(selectedCaseCategories);
    }
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
