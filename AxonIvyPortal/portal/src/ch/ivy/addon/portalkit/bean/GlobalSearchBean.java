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

import com.axonivy.portal.bean.dashboard.filter.AbstractCaseWidgetFilterBean;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.PortalSystemMessage;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreator;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class GlobalSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String input;
    private CaseDashboardWidget caseWidget;
    protected Map<String, CaseColumnModel> mapHeaders;
    protected List<FilterField> filterFields;

    public void preRender(CaseDashboardWidget caseWidget) {
        this.caseWidget = caseWidget;
        this.mapHeaders = caseWidget.getColumns().stream()
                .collect(Collectors.toMap(CaseColumnModel::getField, Function.identity()));
        initFilterFields();
        initFilters();
    }

    public CaseDashboardWidget getCaseWidget() {
        return caseWidget;
    }

    public void setCaseWidget(CaseDashboardWidget caseWidget) {
        this.caseWidget = caseWidget;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void updateCaseWidget(String keyword) {
        caseWidget.getUserFilters().clear();
        String portalSignature = "invokeAgent(String,String,List<String>,String,String,Class,String)";
        String question = keyword;
        String systemMessage = PortalSystemMessage.FILTER_CREATION_INSTRUCTION.getMessage();
        Map<String, Object> params = new HashMap<>();
        params.put("query", question);
        params.put("systemMessage", systemMessage);
        params.put("resultType", String.class);
        Map<String, Object> result = new HashMap<>();
        try {
            result = IvyAdapterService.startSubProcessInSecurityContext(portalSignature, params);
            List<DashboardFilter> filters = BusinessEntityConverter
                    .jsonValueToEntities(result.get("resultObject").toString(), DashboardFilter.class);
            filters.forEach(this::buildFilterFieldsForCase);
            updateFilterFieldLabels(filters.stream().map(DashboardFilter::getFilterField).collect(Collectors.toList()));
            WidgetFilterService widgetFilterService = WidgetFilterService.getInstance();
            caseWidget.setUserFilters(filters);
            WidgetFilterModel filterModel = widgetFilterService.prepareSaveFilter(caseWidget);
            caseWidget.setSavedFilters(List.of(filterModel));
            Map<String, SortMeta> sortBy = new HashMap<>();  
            SortMeta sortMeta = SortMeta.builder()  
                .field("id")  
                .order(SortOrder.ASCENDING)  
                .build();  
            sortBy.put("id", sortMeta);  
            caseWidget.getDataModel().load(0, 5, sortBy, null);
        } catch (Exception e) {
            Ivy.log().error(e.getCause());
        }
    }

    private void buildFilterFieldsForCase(DashboardFilter filter) {
        FilterField filterField = FilterFieldFactory.findBy(caseWidget.getId(), filter.getField());
        if (filterField != null) {
            filterField.initFilter(filter);
            filter.setFilterField(filterField);
        }
    }

    private String getFilterFieldLabel(String fieldName) {
        return filterFields.stream().filter(field -> field.getName().equals(fieldName)).findFirst()
                .map(FilterField::getLabel).orElse(fieldName);
    }

    private void updateFilterFieldLabels(List<FilterField> filterFields) {
        for (FilterField filter : filterFields) {
            CaseColumnModel caseColumnModel = this.mapHeaders.get(filter.getName());
            if (caseColumnModel != null) {
                filter.setLabel(caseColumnModel.getHeaderText());
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
                    CaseColumnModel caseColumnModel = this.mapHeaders.get(filterField.getName());
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
            CaseColumnModel caseColumnModel = this.mapHeaders.get(filter.getName());
            if (caseColumnModel != null) {
                filter.setLabel(caseColumnModel.getHeaderText());
            }
        }
    }
}
