package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.datamodel.LiveScrollLazyModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.jsf.primefaces.sort.SortMetaConverter;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public class RelatedCaseLazyDataModel extends LiveScrollLazyModel<ICase> {

  private static final long serialVersionUID = 1L;

  public static final String DESCRIPTION = "DESCRIPTION";

  private final List<ICase> cases;
  private final Map<Long, ICase> mapCases;
  private List<ICase> foundCases;
  private boolean isFirstTime = true;
  private List<ICase> firstChunk;

  private CaseSearchCriteria criteria;
  private List<String> allColumns = new ArrayList<>();
  private List<String> selectedColumns = new ArrayList<>();
  private List<String> portalRequiredColumns = Arrays.asList(CaseSortField.NAME.name());
  private List<String> portalDefaultColumns;
  private boolean isDisableSelectionCheckboxes;
  private boolean isAutoHideColumns;

  public RelatedCaseLazyDataModel(Long businessCaseId) {
    super();
    cases = new ArrayList<>();
    mapCases = new HashMap<>();
    foundCases = new ArrayList<>();
    buildCriteria(businessCaseId);
    setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    initColumnsConfiguration();
  }

  @Override
  public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      PrimeFaces.current().executeScript("resizeCaseDetailsTableBody();");
    }
    if (isFirstTime) {
      isFirstTime = false;
      foundCases = firstChunk == null ? new ArrayList<>() : firstChunk;
      firstChunk = null;
      cases.clear();
      mapCases.clear();
    } else {
      if (first == 0) {
        cases.clear();
        mapCases.clear();
        SortMetaConverter sort = new SortMetaConverter(sortBy);
        criteria.setSortField(sort.toField());
        criteria.setSortDescending(sort.toOrder() == SortOrder.DESCENDING);
      }
      foundCases = findCases(first, pageSize);
    }
    cases.addAll(foundCases);
    mapCases.putAll(foundCases.stream().collect(Collectors.toMap(ICase::getId, Function.identity())));

    if (foundCases.size() >= pageSize) {
      setRowCount(first + pageSize + 1);
    } else {
      setRowCount(first + foundCases.size());
    }
    return foundCases;
  }

  public void loadFirstTime() {
    readChunkSizeFromRequest();
    criteria.setSortField(CaseSortField.ID.name());
    criteria.setSortDescending(true);
    firstChunk = findCases(0, getChunkSize());
    isFirstTime = true;
  }

  private void setAdminQuery(boolean isAdminQuery) {
    criteria.extendStatesQueryByPermission(isAdminQuery);
  }

  public void initColumnsConfiguration() {
    if (GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
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

  private List<ICase> findCases(int first, int pageSize) {
    criteria.setColumns(allColumns);
    return CaseService.newInstance().findCasesByCriteria(criteria, Math.max(first, 0), pageSize).getCases();
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
    return mapCases.get(Long.valueOf(rowKey));
  }

  @Override
  public List<ICase> getResults() {
    return cases;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return getRowCount();
  }
}
