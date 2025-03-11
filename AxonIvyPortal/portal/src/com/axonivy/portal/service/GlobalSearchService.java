package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.enums.GlobalSearchScopeCategory;
import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.payload.SearchPayload;
import com.axonivy.portal.response.CaseData;
import com.axonivy.portal.response.GlobalSearchResponse;
import com.axonivy.portal.response.ProcessData;
import com.axonivy.portal.response.TaskData;
import com.axonivy.portal.util.BusinessDetailsUtils;
import com.axonivy.portal.util.CaseBehaviorUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class GlobalSearchService {

  private static GlobalSearchService instance;
  private static final int PAGE_SIZE = 3;

  public static GlobalSearchService getInstance() {
    if (instance == null) {
      instance = new GlobalSearchService();
    }
    return GlobalSearchService.instance;
  }

  public GlobalSearchResponse searchTasks(SearchPayload payload) {
    TaskSearchCriteria criteria = buildTaskCriteria(payload);
    IvyTaskResultDTO iTasks = TaskService.newInstance().findGlobalSearchTasksByCriteria(criteria, 0, PAGE_SIZE);
    List<TaskData> results = iTasks.getTasks().stream().map(TaskData::new).toList();
    return new GlobalSearchResponse(results, iTasks.getTotalTasks());
  }

  public GlobalSearchResponse searchCases(SearchPayload payload) {
    CaseSearchCriteria criteria = buildCaseCriteria(payload);
    IvyCaseResultDTO iCases = CaseService.newInstance().findGlobalSearchCasesByCriteria(criteria, 0, PAGE_SIZE);
    boolean canAccessBusinessDetails = CaseBehaviorUtils.canAccessBusinessDetails();
    List<CaseData> results = iCases.getCases().stream().map(caze -> new CaseData(caze, buildCaseDataLink(caze, canAccessBusinessDetails))).toList();
    return new GlobalSearchResponse(results, iCases.getTotalCases());
  }

  private TaskSearchCriteria buildTaskCriteria(SearchPayload payload) {
    TaskSearchCriteria criteria = new TaskSearchCriteria();
    criteria.setKeyword(payload.getQuery());
    criteria.setSortField(TaskSortField.EXPIRY_TIME.toString());
    criteria.setSortDescending(true);
    criteria.setTaskAssigneeType(TaskAssigneeType.ALL);
    criteria.setIncludedStates(new ArrayList<>(TaskSearchCriteria.STANDARD_STATES));
    boolean isAdminQuery = PermissionUtils.checkReadAllTasksPermission();
    criteria.setAdminQuery(isAdminQuery);
    criteria.extendStatesQueryByPermission(isAdminQuery);
    criteria.setSearchScopeTaskFields(getSearchScopeTaskFields());
    return criteria;
  }

  private CaseSearchCriteria buildCaseCriteria(SearchPayload payload) {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setKeyword(payload.getQuery());
    criteria.setSearchScopeCaseFields(getSearchScopeCaseFields());
    criteria.setBusinessCase(true);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    boolean isAdminQuery = PermissionUtils.checkReadAllTasksPermission();
    criteria.setAdminQuery(isAdminQuery);
    criteria.extendStatesQueryByPermission(isAdminQuery);
    boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
    criteria.setAdminQuery(hasReadAllTasksPermisson);
    return criteria;
  }

  public GlobalSearchResponse searchProcesses(SearchPayload payload) {
    String keyword = payload.getQuery().toLowerCase();
    List<IWebStartable> startableProcesses = ProcessService.getInstance().findProcesses();
    List<ProcessData> processes = startableProcesses.stream()
        .filter(process -> process.getName().toLowerCase().contains(keyword)
            || process.getDescription().toLowerCase().contains(keyword))
        .map(ProcessData::new).sorted(Comparator.comparing(ProcessData::getName)).toList();
    List<ProcessData> results = processes.isEmpty() ? processes : processes.subList(0, Math.min(processes.size(), PAGE_SIZE));
    return new GlobalSearchResponse(results, processes.size());
  }
  
  public boolean isShowGlobalSearchByProcesses() {
    boolean isShowFullProcessList = PermissionUtils.checkAccessFullProcessListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.PROCESSES)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullProcessList && isHasSearchScope;
  }

  public boolean isShowGlobalSearchByCases() {
    boolean isShowFullCaseList = PermissionUtils.checkAccessFullCaseListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.CASES)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullCaseList && isHasSearchScope;
  }
  
  public boolean isShowGlobalSearchByTasks() {
    boolean isShowFullTaskList = PermissionUtils.checkAccessFullTaskListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.TASKS)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullTaskList && isHasSearchScope;
  }
  
  private List<SearchScopeTaskField> getSearchScopeTaskFields() {
    String searchScopeTaskFieldsString = Ivy.var().get(GlobalVariable.SEARCH_SCOPE_BY_TASK_FIELDS.getKey());
    if (StringUtils.isNotBlank(searchScopeTaskFieldsString)) {
      List<SearchScopeTaskField> searchScopeTaskFields = new ArrayList<>();
      String[] fieldArray = searchScopeTaskFieldsString.split(",");
      for (String field : fieldArray) {
        SearchScopeTaskField fieldEnum = SearchScopeTaskField.valueOf(field.toUpperCase());
        if (fieldEnum != null) {
          searchScopeTaskFields.add(fieldEnum);
        }
      }
      return searchScopeTaskFields;
    }
    return List.of(SearchScopeTaskField.NAME, SearchScopeTaskField.DESCRIPTION);
  }

  private List<SearchScopeCaseField> getSearchScopeCaseFields() {
    String searchScopeCaseFieldsString = Ivy.var().get(GlobalVariable.SEARCH_SCOPE_BY_CASE_FIELDS.getKey());
    if (StringUtils.isNotBlank(searchScopeCaseFieldsString)) {
      List<SearchScopeCaseField> searchScopeCaseFields = new ArrayList<>();
      String[] fieldArray = searchScopeCaseFieldsString.split(",");
      for (String field : fieldArray) {
        SearchScopeCaseField fieldEnum = SearchScopeCaseField.valueOf(field.toUpperCase());
        if (fieldEnum != null) {
          searchScopeCaseFields.add(fieldEnum);
        }
      }
      return searchScopeCaseFields;
    }
    return List.of(SearchScopeCaseField.NAME, SearchScopeCaseField.DESCRIPTION, SearchScopeCaseField.CUSTOM);
  }

  private String buildCaseDataLink(ICase caze, boolean canAccessBusinessDetails) {
    if (canAccessBusinessDetails) {
      return BusinessDetailsUtils.getAdditionalCaseDetailsPageUri(caze);
    } else {
      return PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(caze.uuid());
    }
  }

}
