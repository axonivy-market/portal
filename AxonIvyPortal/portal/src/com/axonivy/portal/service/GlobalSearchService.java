package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessViewerUtils;
import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.payload.SearchPayload;
import com.axonivy.portal.response.CaseData;
import com.axonivy.portal.response.GlobalSearchResponse;
import com.axonivy.portal.response.ProcessData;
import com.axonivy.portal.response.TaskData;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;
import ch.ivyteam.ivy.environment.Ivy;
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
    TaskSearchCriteria query = new TaskSearchCriteria();
    query.setKeyword(payload.getQuery());
    query.setSortField(TaskSortField.EXPIRY_TIME.toString());
    query.setSortDescending(true);
    query.setQuickGlobalSearch(true);
    query.setSearchScopeTaskFields(getSearchScopeTaskFields());
    IvyTaskResultDTO iTasks = TaskService.newInstance().findGlobalSearchTasksByCriteria(query, 0, PAGE_SIZE);
    List<TaskData> results = iTasks.getTasks().stream().map(TaskData::new).toList();
    return new GlobalSearchResponse(results, iTasks.getTotalTasks());
  }

  public GlobalSearchResponse searchCases(SearchPayload payload) {
    CaseSearchCriteria query = new CaseSearchCriteria();
    query.setKeyword(payload.getQuery());
    query.setSearchScopeCaseFields(getSearchScopeCaseFields());
    IvyCaseResultDTO iCases = CaseService.newInstance().findGlobalSearchCasesByCriteria(query, 0, PAGE_SIZE);
    List<CaseData> results = iCases.getCases().stream().map(CaseData::new).toList();
    return new GlobalSearchResponse(results, iCases.getTotalCases());
  }

  public GlobalSearchResponse searchProcesses(SearchPayload payload) {
    String keyword = payload.getQuery().toLowerCase();
    List<IWebStartable> startableProcesses = ProcessService.getInstance().findProcesses();
    List<ProcessData> processes = startableProcesses.stream()
        .filter(process -> ProcessViewerUtils.isViewerAllowed(process)
            && (process.getName().toLowerCase().contains(keyword) || process.getDescription().toLowerCase().contains(keyword)))
        .map(ProcessData::new).sorted(Comparator.comparing(ProcessData::getName)).toList();
    List<ProcessData> results = processes.isEmpty() ? processes : processes.subList(0, Math.min(processes.size(), PAGE_SIZE));
    return new GlobalSearchResponse(results, processes.size());
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

}
