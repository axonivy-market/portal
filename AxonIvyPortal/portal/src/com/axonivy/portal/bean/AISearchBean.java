package com.axonivy.portal.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.dto.AISearchResponse;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.PortalCustomSignature;
import com.axonivy.portal.enums.PortalSystemMessage;
import com.axonivy.portal.page.AISearch.SearchResult;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.datamodel.GlobalSearchAiResultModel;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class AISearchBean {

  private String rephrased;
  private double confidence;
  private boolean hasTaskFilters;
  private boolean hasCaseFilters;
  private boolean searched;
  private int taskCount;
  private int caseCount;
  private List<SearchResult> results = new ArrayList<>();
  private TaskDashboardWidget taskWidget;
  private CaseDashboardWidget caseWidget;
  protected Map<String, TaskColumnModel> taskMapHeaders;
  protected Map<String, CaseColumnModel> caseMapHeaders;
  private Map<String, SortMeta> sortBy = new HashMap<>();
  private List<GlobalSearchAiResultModel> globalSearchResults = new ArrayList<>();

  @PostConstruct
  public void init() {
    taskWidget = new TaskDashboardWidget() {
      private static final long serialVersionUID = 1L;

      @Override
      public Integer[] loadingItems() {
        return searched ? super.loadingItems() : new Integer[0];
      }
    };
    taskWidget.setId("ai-task-widget");
    taskWidget.setName("Tasks");

    ch.ivy.addon.portalkit.dto.WidgetLayout layout = new ch.ivy.addon.portalkit.dto.WidgetLayout();
    layout.setWidth(12);
    layout.setHeight(5);
    taskWidget.setLayout(layout);

    taskWidget.setAutoPosition(true);
    taskWidget.setSortField(ch.ivy.addon.portalkit.enums.TaskSortField.ID.toString());
    taskWidget.setSortDescending(true);

    taskWidget.setDataModel(new ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel() {
      private static final long serialVersionUID = 1L;

      @Override
      public void loadFirstTime() {
        if (hasTaskFilters) {
          super.loadFirstTime();
        }
      }

      @Override
      public List<ITask> load(int first, int pageSize, Map<String, SortMeta> sortBy, java.util.Map<String, org.primefaces.model.FilterMeta> filterBy) {
        if (!hasTaskFilters) {
          setRowCount(0);
          return new ArrayList<>();
        }
        return super.load(first, pageSize, sortBy, filterBy);
      }

      @Override
      public int getRowCount() {
        return hasTaskFilters ? super.getRowCount() : 0;
      }
    });

    taskWidget.setColumns(DashboardWidgetUtils.initStandardTaskColumns());
    taskWidget.setFilters(new ArrayList<>());
    taskWidget.setUserFilters(new ArrayList<>());
    DashboardWidgetUtils.buildTaskColumns(taskWidget);

    if (taskWidget.getColumns() != null) {
      taskMapHeaders = taskWidget.getColumns().stream()
          .collect(Collectors.toMap(TaskColumnModel::getField, c -> c));
    } else {
      taskMapHeaders = new HashMap<>();
    }

    caseWidget = new CaseDashboardWidget() {
      private static final long serialVersionUID = 1L;

      @Override
      public Integer[] loadingItems() {
        return searched ? super.loadingItems() : new Integer[0];
      }
    };
    caseWidget.setId("ai-case-widget");
    caseWidget.setName("Cases");
    caseWidget.setLayout(layout);
    caseWidget.setAutoPosition(true);
    caseWidget.setSortField(ch.ivy.addon.portalkit.enums.CaseSortField.ID.toString());
    caseWidget.setSortDescending(true);

    caseWidget.setDataModel(new ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel() {
      private static final long serialVersionUID = 1L;

      @Override
      public void loadFirstTime() {
        if (hasCaseFilters) {
          super.loadFirstTime();
        }
      }

      @Override
      public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, java.util.Map<String, org.primefaces.model.FilterMeta> filterBy) {
        if (!hasCaseFilters) {
          setRowCount(0);
          return new ArrayList<>();
        }
        return super.load(first, pageSize, sortBy, filterBy);
      }

      @Override
      public int getRowCount() {
        return hasCaseFilters ? super.getRowCount() : 0;
      }
    });

    caseWidget.setColumns(DashboardWidgetUtils.initStandardColumns());
    caseWidget.setFilters(new ArrayList<>());
    caseWidget.setUserFilters(new ArrayList<>());
    DashboardWidgetUtils.buildCaseColumns(caseWidget);

    if (caseWidget.getColumns() != null) {
      caseMapHeaders = caseWidget.getColumns().stream()
          .collect(Collectors.toMap(CaseColumnModel::getField, c -> c));
    } else {
      caseMapHeaders = new HashMap<>();
    }
  }

  public void searchWithAI(String keyword) {
    // Ivy.log().info();
    searched = true;
    hasTaskFilters = false;
    hasCaseFilters = false;
    taskCount = 0;
    caseCount = 0;
    results.clear();

    try {
      AISearchResponse response = getAIResponse(keyword);
      applyResponse(response);
    } catch (Exception e) {
      Ivy.log().error("AI search failed for keyword: " + keyword, e);
    }
  }

  private void applyResponse(AISearchResponse response) {
    rephrased = response.getRephrased();
    confidence = response.getConfidence();
    populateResults(response);
  }

  private void populateResults(AISearchResponse response) {
    results.clear();
    Ivy.log().info(
        "Applying filters — taskFilters: " + response.getTaskFilters().toString() + ", caseFilters: "
            + response.getCaseFilters().toString());
    applyTaskFilters(response.getTaskFilters());
    applyCaseFilters(response.getCaseFilters());
  }

  private AISearchResponse getAIResponse(String query) throws Exception {
    String portalSignature = PortalCustomSignature.INVOKE_SMART_WORKFLOW_AGENT.getSignature();
    String systemMessage = PortalSystemMessage.FILTER_CREATION_INSTRUCTION1.getMessage();

    Map<String, Object> params = new HashMap<>();
    params.put("query", query);
    params.put("systemMessage", systemMessage);
    params.put("resultType", String.class);

    Map<String, Object> result = IvyAdapterService.startSubProcessInSecurityContext(portalSignature, params);
    String resultJson = result.get("resultObject").toString();

    return BusinessEntityConverter.jsonValueToEntity(resultJson, AISearchResponse.class);
  }

  private void applyTaskFilters(List<DashboardFilter> filters) {
    if (taskWidget.getUserFilters() != null) {
      taskWidget.getUserFilters().clear();
    }

    if (CollectionUtils.isEmpty(filters)) {
      taskWidget.setUserFilters(new ArrayList<>());
      taskWidget.getDataModel().setRowCount(0);
      return;
    }
    
    hasTaskFilters = true;

    filters.forEach(this::buildFilterFieldsForTask);
    updateTaskFilterLabels(filters.stream()
        .map(DashboardFilter::getFilterField)
        .collect(Collectors.toList()));

    taskWidget.setUserFilters(filters);
    List<ITask> taskResults = loadTaskWidgetData();
    taskCount = DashboardTaskService.getInstance().countDashboardTaskByCriteria(taskWidget.getDataModel().getCriteria()).intValue();
    
    if (CollectionUtils.isNotEmpty(taskResults)) {
      taskResults.forEach(item -> {
        globalSearchResults.add(new GlobalSearchAiResultModel(
            item.getId(),
            item.getName(),
            item.getDescription(),
            "TASK"));
      });
    }
  }

  private void applyCaseFilters(List<DashboardFilter> filters) {
    if (caseWidget.getUserFilters() != null) {
      caseWidget.getUserFilters().clear();
    }

    if (CollectionUtils.isEmpty(filters)) {
      caseWidget.setUserFilters(new ArrayList<>());
      caseWidget.getDataModel().setRowCount(0);
      return;
    }
    
    hasCaseFilters = true;

    filters.forEach(this::buildFilterFieldsForCase);
    updateCaseFilterLabels(filters.stream()
        .map(DashboardFilter::getFilterField)
        .collect(Collectors.toList()));

    caseWidget.setUserFilters(filters);
    List<ICase> caseResults = loadCaseWidgetData();
    caseCount = DashboardCaseService.getInstance().countByCaseQuery(caseWidget.getDataModel().getCriteria().buildQuery()).intValue();
    
    if (CollectionUtils.isNotEmpty(caseResults)) {
      caseResults.forEach(item -> {
        globalSearchResults.add(new GlobalSearchAiResultModel(
            item.getId(),
            item.getName(),
            item.getDescription(),
            "CASE"));
      });
    }
  }

  private void buildFilterFieldsForTask(DashboardFilter filter) {
    FilterField filterField = TaskFilterFieldFactory.findBy(filter.getField());
    if (filterField != null) {
      filterField.initFilter(filter);
      filter.setFilterField(filterField);
    }
  }

  private void buildFilterFieldsForCase(DashboardFilter filter) {
    FilterField filterField = FilterFieldFactory.findBy(filter.getField());
    if (filterField != null) {
      filterField.initFilter(filter);
      filter.setFilterField(filterField);
    }
  }

  private void updateTaskFilterLabels(List<FilterField> filterFields) {
    for (FilterField filter : filterFields) {
      if (filter != null) {
        TaskColumnModel taskColumnModel = this.taskMapHeaders.get(filter.getName());
        if (taskColumnModel != null) {
          filter.setLabel(taskColumnModel.getHeaderText());
        }
      }
    }
  }

  private void updateCaseFilterLabels(List<FilterField> filterFields) {
    for (FilterField filter : filterFields) {
      if (filter != null) {
        CaseColumnModel caseColumnModel = this.caseMapHeaders.get(filter.getName());
        if (caseColumnModel != null) {
          filter.setLabel(caseColumnModel.getHeaderText());
        }
      }
    }
  }

  private List<ITask> loadTaskWidgetData() {
    taskWidget.getDataModel().getResults().clear();
    return taskWidget.getDataModel().load(0, 5, sortBy, null);
  }

  private List<ICase> loadCaseWidgetData() {
    caseWidget.getDataModel().getResults().clear();
    return caseWidget.getDataModel().load(0, 5, sortBy, null);
  }

  public String getRephrased() {
    return rephrased;
  }

  public void setRephrased(String rephrased) {
    this.rephrased = rephrased;
  }

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public boolean isSearched() {
    return searched;
  }

  public void setSearched(boolean searched) {
    this.searched = searched;
  }

  public List<SearchResult> getResults() {
    return results;
  }

  public void setResults(List<SearchResult> results) {
    this.results = results;
  }

  public TaskDashboardWidget getTaskWidget() {
    return taskWidget;
  }

  public void setTaskWidget(TaskDashboardWidget taskWidget) {
    this.taskWidget = taskWidget;
  }

  public boolean isHasTaskFilters() {
    return hasTaskFilters;
  }

  public boolean isHasCaseFilters() {
    return hasCaseFilters;
  }

  public int getTaskCount() {
    return taskCount;
  }

  public void setTaskCount(int taskCount) {
    this.taskCount = taskCount;
  }

  public int getCaseCount() {
    return caseCount;
  }

  public void setCaseCount(int caseCount) {
    this.caseCount = caseCount;
  }

  public CaseDashboardWidget getCaseWidget() {
    return caseWidget;
  }

  public void setCaseWidget(CaseDashboardWidget caseWidget) {
    this.caseWidget = caseWidget;
  }
}
