package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public class RelatedCaseLazyDataModel extends LazyDataModel7<ICase> {

  private static final long serialVersionUID = 1L;

  public static final String DESCRIPTION = "DESCRIPTION";

  private final List<ICase> data;

  private String caseWidgetComponentId;
  private CaseSearchCriteria criteria;
  private List<String> allColumns = new ArrayList<>();
  private List<String> selectedColumns = new ArrayList<>();
  private List<String> portalRequiredColumns = Arrays.asList(CaseSortField.NAME.name());
  private List<String> portalDefaultColumns;
  private boolean isDisableSelectionCheckboxes;
  private boolean isAutoHideColumns;

  public RelatedCaseLazyDataModel(Long businessCaseId) {
    super();
    data = new ArrayList<>();
    caseWidgetComponentId = "related-cases-widget";
    buildCriteria(businessCaseId);
    initColumnsConfiguration();
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);

    if (first == 0) {
      initializedDataModel();
    }
    setRowCount(getCaseCount(criteria));

    List<ICase> foundCases = findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  public void initColumnsConfiguration() {
    if (new GlobalSettingService().isCaseOwnerEnabled()) {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), CaseSortField.ID.name(), CaseSortField.CREATOR.name(), CaseSortField.OWNER.name(), CaseSortField.CREATION_TIME.name(),
          CaseSortField.FINISHED_TIME.name(), CaseSortField.STATE.name(), CaseSortField.CATEGORY.name());
    } else {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), CaseSortField.ID.name(), CaseSortField.CREATOR.name(), CaseSortField.CREATION_TIME.name(), CaseSortField.FINISHED_TIME.name(),
          CaseSortField.STATE.name(), CaseSortField.CATEGORY.name());
    }
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(portalDefaultColumns);
      initSelectedColumns();
    }
  }

  private void initSelectedColumns() {
    if (selectedColumns.isEmpty()) {
      selectedColumns.addAll(getDefaultColumns());
      isAutoHideColumns = true;
    }
    setDisableSelectionCheckboxes(isAutoHideColumns);
  }

  private List<ICase> findCases(CaseSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ICase>> findCaseCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    UIComponent component = findRelatedCaseComponent();
    if (component != null) {
      return findCaseCaller.invokeComponentLogic(component, "#{logic.findCases}", new Object[] {criteria, startIndex, count});
    }
    return new ArrayList<>();
  }

  private UIComponent findRelatedCaseComponent() {
    List<UIComponent> children = FacesContext.getCurrentInstance().getViewRoot().findComponent("case-item-details:case-details-container:widgets:case-details-technical-case-card").getChildren();
    return children.stream().filter(child -> child.getId().equals(caseWidgetComponentId)).findFirst().orElse(null);
  }

  private void initializedDataModel() {
    data.clear();
    criteria.setFinalCaseQuery(null);
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    UIComponent component = findRelatedCaseComponent();
    if (component != null) {
      Long caseCount = countCaseCaller.invokeComponentLogic(component, "#{logic.countCases}", new Object[] {criteria});
      return caseCount.intValue();
    }
    return 0;
  }

  private void buildCriteria(Long businessCaseId) {
    criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(false);
    criteria.setTechnicalCase(true);
    criteria.setBusinessCaseId(businessCaseId);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    criteria.setSortField(CaseSortField.ID.toString());
    criteria.setSortDescending(false);
  }

  public List<String> getAllColumns() {
    return allColumns;
  }

  public void setSelectedColumns(List<String> selectedColumns) {
    this.selectedColumns = selectedColumns;
  }

  public List<String> getSelectedColumns() {
    return selectedColumns;
  }

  public List<String> getPortalRequiredColumns() {
    return portalRequiredColumns;
  }

  public List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  public String getColumnLabel(String column) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + column);
  }

  public void saveColumnsConfiguration() {
    setAutoHideColumns(isDisableSelectionCheckboxes);
    if (getAutoHideColumns()) {
      selectedColumns.clear();
      selectedColumns.addAll(getDefaultColumns());
    } else {
      // avoid duplicating
      for (String requiredColumn : portalRequiredColumns) {
        if (!selectedColumns.contains(requiredColumn)) {
          selectedColumns.add(requiredColumn);
        }
      }
    }
    initSelectedColumns();
  }

  public void setAutoHideColumns(boolean isAutoHideColumns) {
    this.isAutoHideColumns = isAutoHideColumns;
  }

  public boolean getAutoHideColumns() {
    return this.isAutoHideColumns;
  }

  public boolean isDisableSelectionCheckboxes() {
    return isDisableSelectionCheckboxes;
  }

  public void setDisableSelectionCheckboxes(boolean isDisableSelectionCheckboxes) {
    this.isDisableSelectionCheckboxes = isDisableSelectionCheckboxes;
  }

  @Override
  public String getRowKey(ICase icase) {
    return String.valueOf(icase.getId());
  }

  @Override
  public ICase getRowData(String rowKey) {
    for (ICase icase : data) {
      if (icase.getId() == Long.valueOf(rowKey)) {
        return icase;
      }
    }
    return null;
  }
}
