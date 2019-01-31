package ch.ivy.addon.portalkit.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class StatisticFilter implements Cloneable {
  
  private StatisticTimePeriodSelection timePeriodSelection;
  @JsonIgnore
  private List<StatisticTimePeriodSelection> allTimePeriodSelection;
  private Date createdDateFrom;
  private Date createdDateTo;

  @JsonIgnore
  private CategoryTree caseCategoryTree;
  private List<String> selectedCaseCategories = new ArrayList<>();
  private boolean isAllCategoriesSelected = true;

  @JsonIgnore
  private List<Object> roles = new ArrayList<>();
  private List<String> selectedRoles = new ArrayList<>();
  private boolean isAllRolesSelected = true;

  @JsonIgnore
  private List<CaseState> caseStates = new ArrayList<>();
  private List<CaseState> selectedCaseStates = new ArrayList<>();
  private boolean isAllCaseStatesSelected = true;
  
  @JsonIgnore
  private List<WorkflowPriority> taskPriorities = new ArrayList<>();
  private List<WorkflowPriority> selectedTaskPriorities = new ArrayList<>();
  private boolean isAllTaskPrioritiesSelected = true;

  private List<String> selectedCustomVarCharFields1 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields2 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields3 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields4 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields5 = new ArrayList<>();
  
  public StatisticFilter() {
    List<IRole> distinctRoles = findRolesByCallableProcess().stream()
        .filter(role -> role != null && Ivy.session().hasRole(role, false))
        .sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()))
        .collect(Collectors.toList());

    this.roles.add(Ivy.session().getSessionUser());
    this.roles.addAll(distinctRoles);
    
    this.selectedRoles = new ArrayList<>(distinctRoles.stream().map(IRole::getMemberName).collect(Collectors.toList()));
    this.selectedRoles.add(0, Ivy.session().getSessionUser().getMemberName());

    // Initialize list of case states
    this.caseStates = Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE);
    this.selectedCaseStates = new ArrayList<>(this.caseStates);

    // Initialize list of task priorities
    this.taskPriorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    this.selectedTaskPriorities = new ArrayList<>(this.taskPriorities);

    // Initialize list of case categories
    Map<String, Object> params = new HashMap<>();
    CaseQuery query = CaseQuery.create();
    query.where().state().isNotEqual(CaseState.ZOMBIE).and().state().isNotEqual(CaseState.DESTROYED);
    CaseCategorySearchCriteria criteria = new CaseCategorySearchCriteria();
    criteria.setCustomCaseQuery(query);
    params.put("caseCategorySearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcess("findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria)", params,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    this.caseCategoryTree = (CategoryTree) response.get("categoryTree");
    if (this.caseCategoryTree != null) {
      this.selectedCaseCategories = this.caseCategoryTree.getAllChildren().stream().map(CategoryTree::getRawPath).collect(Collectors.toList());
    }
    this.selectedCaseCategories.add(StringUtils.EMPTY);

    this.timePeriodSelection = StatisticTimePeriodSelection.CUSTOM;
    this.allTimePeriodSelection = Arrays.asList(StatisticTimePeriodSelection.CUSTOM, StatisticTimePeriodSelection.LAST_WEEK, StatisticTimePeriodSelection.LAST_MONTH, StatisticTimePeriodSelection.LAST_6_MONTH);
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

  public CategoryTree getCaseCategoryTree() {
    return caseCategoryTree;
  }

  public void setCaseCategoryTree(CategoryTree caseCategoryTree) {
    this.caseCategoryTree = caseCategoryTree;
  }

  public List<String> getSelectedCaseCategories() {
    return selectedCaseCategories;
  }

  public void setSelectedCaseCategories(List<String> selectedCaseCategories) {
    this.selectedCaseCategories = selectedCaseCategories;
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

  public List<String> getSelectedCustomVarCharFields1() {
    return selectedCustomVarCharFields1;
  }

  public void setSelectedCustomVarCharFields1(List<String> selectedCustomVarCharFields1) {
    this.selectedCustomVarCharFields1 = selectedCustomVarCharFields1;
  }

  public List<String> getSelectedCustomVarCharFields2() {
    return selectedCustomVarCharFields2;
  }

  public void setSelectedCustomVarCharFields2(List<String> selectedCustomVarCharFields2) {
    this.selectedCustomVarCharFields2 = selectedCustomVarCharFields2;
  }

  public List<String> getSelectedCustomVarCharFields3() {
    return selectedCustomVarCharFields3;
  }

  public void setSelectedCustomVarCharFields3(List<String> selectedCustomVarCharFields3) {
    this.selectedCustomVarCharFields3 = selectedCustomVarCharFields3;
  }

  public List<String> getSelectedCustomVarCharFields4() {
    return selectedCustomVarCharFields4;
  }

  public void setSelectedCustomVarCharFields4(List<String> selectedCustomVarCharFields4) {
    this.selectedCustomVarCharFields4 = selectedCustomVarCharFields4;
  }

  public List<String> getSelectedCustomVarCharFields5() {
    return selectedCustomVarCharFields5;
  }

  public void setSelectedCustomVarCharFields5(List<String> selectedCustomVarCharFields5) {
    this.selectedCustomVarCharFields5 = selectedCustomVarCharFields5;
  }

  public boolean getIsAllCategoriesSelected() {
    return isAllCategoriesSelected;
  }

  public void setIsAllCategoriesSelected(boolean isAllCategoriesSelected) {
    this.isAllCategoriesSelected = isAllCategoriesSelected;
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
  public Object clone() throws CloneNotSupportedException { // NOSONAR
    return super.clone();
  }

}
