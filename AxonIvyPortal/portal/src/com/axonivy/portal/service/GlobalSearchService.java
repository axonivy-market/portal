package com.axonivy.portal.service;

import java.util.Comparator;
import java.util.List;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessViewerUtils;
import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.payload.SearchPayload;
import com.axonivy.portal.response.CaseData;
import com.axonivy.portal.response.ProcessData;
import com.axonivy.portal.response.TaskData;

import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;
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

  public List<TaskData> searchTasks(SearchPayload payload) {
    TaskSearchCriteria query = new TaskSearchCriteria();
    query.setKeyword(payload.getQuery());
    query.setSortField(TaskSortField.EXPIRY_TIME.toString());
    query.setSortDescending(true);
    query.setQuickGlobalSearch(true);
    query.setSearchScopeTaskFields(List.of(SearchScopeTaskField.NAME, SearchScopeTaskField.DESCRIPTION));
    IvyTaskResultDTO iTasks = TaskService.newInstance().findTasksByCriteria(query, 0, PAGE_SIZE);
    return iTasks.getTasks().stream().map(TaskData::new).toList();
  }

  public List<CaseData> searchCases(SearchPayload payload) {
    CaseSearchCriteria query = new CaseSearchCriteria();
    query.setKeyword(payload.getQuery());
    query.setSearchScopeCaseFields(List.of(SearchScopeCaseField.NAME, SearchScopeCaseField.DESCRIPTION, SearchScopeCaseField.CUSTOM));
    IvyCaseResultDTO iCases = CaseService.newInstance().findCasesByCriteria(query, 0, PAGE_SIZE);
    return iCases.getCases().stream().map(CaseData::new).toList();
  }

  public List<ProcessData> searchProcesses(SearchPayload payload) {
    List<IWebStartable> startableProcesses = ProcessService.getInstance().findProcesses().getProcesses();
    List<ProcessData> processes = startableProcesses.stream()
        .filter(process -> ProcessViewerUtils.isViewerAllowed(process)
            && (process.getName().toLowerCase().contains(payload.getQuery().toLowerCase())
                || process.getDescription().toLowerCase().contains(payload.getQuery().toLowerCase())))
        .map(ProcessData::new).sorted(Comparator.comparing(ProcessData::getName)).toList();
    return processes.isEmpty() ? processes : processes.subList(0, Math.min(processes.size(), PAGE_SIZE));
  }

}
