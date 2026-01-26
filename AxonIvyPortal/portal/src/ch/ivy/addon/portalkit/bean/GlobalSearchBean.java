package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import ch.ivy.addon.portal.generic.dashboard.component.GlobalSearchAIResult.GlobalSearchAIResultData;
import com.axonivy.portal.bean.dashboard.filter.AbstractCaseWidgetFilterBean;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.PortalSystemMessage;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreator;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;

import ch.ivy.addon.portalkit.datamodel.GlobalSearchAiResultModel;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class GlobalSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String input;
    private CaseDashboardWidget caseWidget;
    private TaskDashboardWidget taskWidget;
    protected Map<String, CaseColumnModel> caseMapHeaders;
    protected Map<String, TaskColumnModel> taskMapHeaders;
    protected List<FilterField> filterFields;
    private List<GlobalSearchAiResultModel> results = new ArrayList<>();
    private Map<String, SortMeta> sortBy = new HashMap<>();
    
    public void preRender(CaseDashboardWidget caseWidget, TaskDashboardWidget taskWidget) {
        this.caseWidget = caseWidget;
        this.taskWidget = taskWidget;
        this.sortBy = getSortMeta();
        this.caseMapHeaders = caseWidget.getColumns().stream()
                .collect(Collectors.toMap(CaseColumnModel::getField, Function.identity()));
        this.taskMapHeaders = taskWidget.getColumns().stream()
                .collect(Collectors.toMap(TaskColumnModel::getField, Function.identity()));
        initFilterFields();
        initFilters();
        this.caseWidget.getDataModel().load(0, 5, sortBy, null);
        this.taskWidget.getDataModel().load(0, 5, sortBy, null);
    }

    public CaseDashboardWidget getCaseWidget() {
        return caseWidget;
    }

    public void setCaseWidget(CaseDashboardWidget caseWidget) {
        this.caseWidget = caseWidget;
    }

    public TaskDashboardWidget getTaskWidget() {
        return taskWidget;
    }

    public void setTaskWidget(TaskDashboardWidget taskWidget) {
        this.taskWidget = taskWidget;
    }

    public List<GlobalSearchAiResultModel> getResults() {
        return results;
    }

    public void setResults(List<GlobalSearchAiResultModel> results) {
        this.results = results;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void updateWidgetsWithAI(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return;
        }

        try {
            results.clear();
            GlobalSearchAIResultData aiResults = getAIFilterResults(keyword);
            
            if (CollectionUtils.isNotEmpty(aiResults.getCaseFilters())) {
                applyCaseFilters(aiResults.getCaseFilters());
            }
            
            if (CollectionUtils.isNotEmpty(aiResults.getTaskFilters())) {
                applyTaskFilters(aiResults.getTaskFilters());
            }

        } catch (Exception e) {
            Ivy.log().error("Failed to update widgets with AI filters", e.getMessage());
        }
    }
    

    private GlobalSearchAIResultData getAIFilterResults(String keyword) throws Exception {
        String portalSignature = "invokeAgent(String,String,List<String>,String,String,Class,String)";
        String systemMessage = PortalSystemMessage.FILTER_CREATION_INSTRUCTION.getMessage();
        
        Map<String, Object> params = new HashMap<>();
        params.put("query", keyword);
        params.put("systemMessage", systemMessage);
        params.put("resultType", String.class);
        
        Map<String, Object> result = IvyAdapterService.startSubProcessInSecurityContext(portalSignature, params);
        String resultJson = result.get("resultObject").toString();
        
        return BusinessEntityConverter.jsonValueToEntity(resultJson, GlobalSearchAIResultData.class);
    }
    

    private void applyCaseFilters(List<DashboardFilter> filters) {
        caseWidget.getUserFilters().clear();
        
        filters.forEach(this::buildFilterFieldsForCase);
        updateCaseFilterLabels(filters.stream()
            .map(DashboardFilter::getFilterField)
            .collect(Collectors.toList()));
        
        caseWidget.setUserFilters(filters);
        List<ICase> caseResults = loadCaseWidgetData();
        if (CollectionUtils.isNotEmpty(caseResults)) {
            caseResults.forEach(item -> {
                results.add(new GlobalSearchAiResultModel(
                    item.getId(), 
                    item.getName(), 
                    item.getDescription(), 
                    "CASE"));
            });
        }
    }

    private void applyTaskFilters(List<DashboardFilter> filters) {
        taskWidget.getUserFilters().clear();
        
        filters.forEach(this::buildFilterFieldsForTask);
        updateTaskFilterLabels(filters.stream()
            .map(DashboardFilter::getFilterField)
            .collect(Collectors.toList()));
        
        taskWidget.setUserFilters(filters);
        List<ITask> taskResults = loadTaskWidgetData();
        if (CollectionUtils.isNotEmpty(taskResults)) {
            taskResults.forEach(item -> {
                results.add(new GlobalSearchAiResultModel(
                    item.getId(), 
                    item.getName(), 
                    item.getDescription(), 
                    "TASK"));
            });
        }
    }

    // private void saveCaseWidgetFilters() {
    //     WidgetFilterService widgetFilterService = WidgetFilterService.getInstance();
    //     WidgetFilterModel filterModel = widgetFilterService.prepareSaveFilter(caseWidget);
    //     caseWidget.setSavedFilters(List.of(filterModel));
    // }

    // private void saveTaskWidgetFilters() {
    //     WidgetFilterService widgetFilterService = WidgetFilterService.getInstance();
    //     WidgetFilterModel filterModel = widgetFilterService.prepareSaveFilter(taskWidget);
    //     taskWidget.setSavedFilters(List.of(filterModel));
    // }

    private List<ICase> loadCaseWidgetData() {
        
        caseWidget.getDataModel().getResults().clear();
        return caseWidget.getDataModel().load(0, 5, sortBy, null);
    }

    private Map<String, SortMeta> getSortMeta() {
        Map<String, SortMeta> sortBy = new HashMap<>();
        SortMeta sortMeta = SortMeta.builder()
            .field("id")
            .order(SortOrder.ASCENDING)
            .build();
        sortBy.put("id", sortMeta);
        return sortBy;
    }

    private List<ITask> loadTaskWidgetData() {
        taskWidget.getDataModel().getResults().clear();
        return taskWidget.getDataModel().load(0, 5, sortBy, null);
    }

    private void buildFilterFieldsForCase(DashboardFilter filter) {
        FilterField filterField = FilterFieldFactory.findBy(caseWidget.getId(), filter.getField());
        if (filterField != null) {
            filterField.initFilter(filter);
            filter.setFilterField(filterField);
        }
    }

    private void buildFilterFieldsForTask(DashboardFilter filter) {
        FilterField filterField = FilterFieldFactory.findBy(taskWidget.getId(), filter.getField());
        if (filterField != null) {
            filterField.initFilter(filter);
            filter.setFilterField(filterField);
        }
    }

    private String getFilterFieldLabel(String fieldName) {
        return filterFields.stream().filter(field -> field.getName().equals(fieldName)).findFirst()
                .map(FilterField::getLabel).orElse(fieldName);
    }

    /**
     * Update labels for case filter fields based on case column headers
     * @param filterFields List of filter fields to update labels for
     */
    private void updateCaseFilterLabels(List<FilterField> filterFields) {
        for (FilterField filter : filterFields) {
            CaseColumnModel caseColumnModel = this.caseMapHeaders.get(filter.getName());
            if (caseColumnModel != null) {
                filter.setLabel(caseColumnModel.getHeaderText());
            }
        }
    }
    
    /**
     * Update labels for task filter fields based on task column headers
     * @param filterFields List of filter fields to update labels for
     */
    private void updateTaskFilterLabels(List<FilterField> filterFields) {
        for (FilterField filter : filterFields) {
            TaskColumnModel taskColumnModel = this.taskMapHeaders.get(filter.getName());
            if (taskColumnModel != null) {
                filter.setLabel(taskColumnModel.getHeaderText());
            }
        }
    }

    private void initFilters() {
        if (CollectionUtils.isEmpty(Optional.ofNullable(this.caseWidget).map(CaseDashboardWidget::getFilters).get())) {
            return;
        }

        // If the filter available in the filter list, initialize it
        for (DashboardFilter filter : this.caseWidget.getFilters()) {
            if (isFilterAvailable(filter)) {
                FilterField filterField = FilterFieldFactory.findBy(this.caseWidget.getId(),
                        Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(""));
                if (filterField != null) {
                    CaseColumnModel caseColumnModel = this.caseMapHeaders.get(filterField.getName());
                    if (caseColumnModel != null) {
                        filter.setLabel(caseColumnModel.getHeaderText());
                    }
                    filterField.initFilter(filter);
                }
            }
        }
    }

    private void initFilterFields() {
        this.filterFields = new ArrayList<>();
        this.filterFields.add(FilterFieldFactory.getDefaultFilterField());
        this.filterFields.addAll(FilterFieldFactory.getStandardFilterableFields(this.caseWidget.getId()));

        // Remove Case Creator filter when the HideCaseCreator variable is enable.
        this.filterFields = filterFields.stream().filter(
                field -> !(field instanceof CaseFilterFieldCreator
                        && GlobalSettingService.getInstance().isHideCaseCreator()))
                .collect(Collectors.toList());

        updateFilterLabels();
        // Add custom fields which are selected by user.
        this.caseWidget.getFilterableColumns().stream().filter(col -> col.getType() == DashboardColumnType.CUSTOM)
                .map(col -> FilterFieldFactory.findCustomFieldBy(col.getField())).filter(Objects::nonNull)
                .forEach(this.filterFields::add);

    }

    /**
     * Check if the filter is existing in the filter list or not
     * 
     * @param filter
     */
    private boolean isFilterAvailable(DashboardFilter filter) {
        return Optional.ofNullable(filter).map(DashboardFilter::getField).isPresent() && filterFields.stream()
                .filter(field -> filter.getField().contentEquals(filter.getField())).findFirst().isPresent();
    }

    private void updateFilterLabels() {
        for (FilterField filter : filterFields) {
            CaseColumnModel caseColumnModel = this.caseMapHeaders.get(filter.getName());
            if (caseColumnModel != null) {
                filter.setLabel(caseColumnModel.getHeaderText());
            }
        }
    }
}
