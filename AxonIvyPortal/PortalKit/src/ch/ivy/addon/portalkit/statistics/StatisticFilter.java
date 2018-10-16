package ch.ivy.addon.portalkit.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatisticFilter implements Cloneable {
  
  private StatisticTimePeriodSelection timePeriodSelection;
  @JsonIgnore
  private List<StatisticTimePeriodSelection> allTimePeriodSelection;
  private Date createdDateFrom;
  private Date createdDateTo;

  @JsonIgnore
  private List<String> caseCategories = new ArrayList<>();
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

  @JsonIgnore
  private static final String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";
  
  private List<String> selectedCustomVarCharFields1 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields2 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields3 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields4 = new ArrayList<>();
  private List<String> selectedCustomVarCharFields5 = new ArrayList<>();
  
  @SuppressWarnings("unchecked")
  public StatisticFilter() {
    // Initialize list of available roles
    try {
      List<RemoteRole> remoteRoles =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteRole>>() {
            @Override
            public List<RemoteRole> call() throws Exception {
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllRoles").call()
                  .get("roles", List.class);
            }
          });

      ISecurityContext securityContext = Ivy.request().getApplication().getSecurityContext();
      List<RemoteRole> distinctRoles =
          remoteRoles.stream()
          .filter(role -> {
            IRole ivyRole = securityContext.findRole(role.getName());
            return ivyRole != null && Ivy.session().hasRole(ivyRole, false);
            }
          )
          .collect(
              Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RemoteRole::getMemberName))),
                  ArrayList::new));

      this.roles.add(Ivy.session().getSessionUser());
      this.roles.addAll(distinctRoles);
      
      this.selectedRoles = new ArrayList<>(distinctRoles.stream().map(RemoteRole::getMemberName).collect(Collectors.toList()));
      this.selectedRoles.add(0, Ivy.session().getSessionUser().getMemberName());
    } catch (Exception e) {
      Ivy.log().error("Can't get list roles statistic filter", e);
    }

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
    params.put("jsonQuery", query.asJson());

    Map<String, Object> response = IvyAdapterService.startSubProcess("findCaseCategories(String)", params,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    this.caseCategories = ((List<CategoryData>) response.get("result")).stream().map(CategoryData::getPath).collect(Collectors.toList());
    this.caseCategories = caseCategories.stream().distinct()
        .collect(Collectors.toList());
    caseCategories.add(StringUtils.EMPTY);
    this.selectedCaseCategories = new ArrayList<>(this.caseCategories);
    this.timePeriodSelection = StatisticTimePeriodSelection.CUSTOM;
    this.allTimePeriodSelection = Arrays.asList(StatisticTimePeriodSelection.CUSTOM, StatisticTimePeriodSelection.LAST_WEEK, StatisticTimePeriodSelection.LAST_MONTH, StatisticTimePeriodSelection.LAST_6_MONTH);
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

  public List<String> getCaseCategories() {
    return caseCategories;
  }

  public void setCaseCategories(List<String> caseCategories) {
    this.caseCategories = caseCategories;
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
  public Object clone() throws CloneNotSupportedException { //NOSONAR
    return super.clone();
  }

}
