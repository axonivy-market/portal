package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.SortDirection;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.CaseColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseLazyDataModel extends LazyDataModel<ICase> {
  public static final String DESCRIPTION = "DESCRIPTION";

  private static final long serialVersionUID = 1L;

  protected final List<ICase> data;

  protected String caseWidgetComponentId;
  protected int rowIndex;
  protected CaseSearchCriteria criteria;

  protected boolean isNotKeepFilter = false;
  protected Long filterGroupId;

  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  private List<String> portalDefaultColumns;
  private List<String> portalRequiredColumns = Arrays.asList(CaseSortField.NAME.name());

  private boolean isAutoHideColumns;
  private boolean isDisableSelectionCheckboxes;

  protected boolean disableCaseCount;
  protected Boolean isSelectedDefaultFilter;
  protected boolean isSelectedAllFilters;

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    initColumnsConfiguration();
    buildCriteria();
    setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
  }

  public void updateDisableCaseCount() {
    disableCaseCount = false;
  }

  @Override
  public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      initializedDataModel();
      if (!disableCaseCount) {
        PrimeFaces.current().executeScript("updateCaseCount()");
      }
    }

    List<ICase> foundCases = findCases(criteria, first, pageSize);
    if (disableCaseCount) {
      int rowCount = 0;
      if (foundCases.size() >= pageSize) {
        rowCount = first + pageSize + 1;
      } else {
        rowCount = first + foundCases.size();
      }
      setRowCount(rowCount);
      PrimeFaces.current().executeScript("PF('case-list-scroller').cfg.totalSize = " + rowCount);
    }
    data.addAll(foundCases);
    return foundCases;
  }

  public void setSorting(String sortedField, boolean descending) {
    updateSortCriteria(sortedField, descending, true);
  }

  private void updateSortCriteria(String sortedField, boolean descending, boolean updateCache) {
    criteria.setSortField(sortedField);
    criteria.setSortDescending(descending);
    if (updateCache) {
      UserUtils.setSessionCaseSortAttribute(SortFieldUtil.buildSortField(sortedField, descending));
    }
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.extendStatesQueryByPermission(isAdminQuery);
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
    criteria.setIncludedStates(new ArrayList<>());
  }

  private List<ICase> findCases(CaseSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ICase>> findCaseCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    return findCaseCaller.invokeComponentLogic(componentId, "#{logic.findCases}",
        new Object[] {criteria, startIndex, count});
  }

  private void initializedDataModel() {
    data.clear();
    if (disableCaseCount) {
      setRowCount(0);
    } else {
      setRowCount(getCaseCount(criteria));
    }
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    Long caseCount = countCaseCaller.invokeComponentLogic(componentId, "#{logic.countCases}", new Object[] { criteria });
    return caseCount.intValue();
  }

  private void buildCriteria() {
    criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    buildSort();
    if (!isNotKeepFilter) {
      criteria.setKeyword(UserUtils.getSessionCaseKeywordFilterAttribute());
    }
  }

  private void buildSort() {
    String sortField = UserUtils.getSessionCaseSortAttribute();
    String sortColumn = SortFieldUtil.extractSortColumn(sortField);
    if (StringUtils.isBlank(sortColumn) || !getAllColumns().contains(sortColumn)) {
      updateSortCriteria(getDefaultSortField(), isSortedDescendingByDefault(), false);
      return;
    }
    setSorting(sortColumn, !SortFieldUtil.isAscendingSort(sortField));
  }

  private String getDefaultSortField() {
    return CaseSortField.ID.name(); // set default value instead of variable
   }

  private boolean isSortedDescendingByDefault() {
    return !SortFieldUtil.isAscendingSort(SortDirection.DESC.name());
  }

  public void initColumnsConfiguration() {
    if (GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), 
                                      CaseSortField.ID.name(), 
                                      CaseSortField.CREATOR.name(), 
                                      CaseSortField.OWNER.name(), 
                                      CaseSortField.CREATION_TIME.name(),
                                      CaseSortField.FINISHED_TIME.name(), 
                                      CaseSortField.STATE.name(), 
                                      CaseSortField.CATEGORY.name());
    } else {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), 
                                      CaseSortField.ID.name(), 
                                      CaseSortField.CREATOR.name(), 
                                      CaseSortField.CREATION_TIME.name(), 
                                      CaseSortField.FINISHED_TIME.name(),
                                      CaseSortField.STATE.name(), 
                                      CaseSortField.CATEGORY.name());
    }
    if (GlobalSettingService.getInstance().isHideCaseCreator()) {
      portalDefaultColumns = portalDefaultColumns.stream()
          .filter(column -> !column.contains(CaseSortField.CREATOR.name())).collect(Collectors.toList());
    }
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(getDefaultColumns());
      allColumns.add(CaseSortField.APPLICATION.name());
      initSelectedColumns();
    }
  }

  protected void initSelectedColumns() {
    CaseColumnsConfigurationService service = CaseColumnsConfigurationService.getInstance();
    Long userId = Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(null);
    Long applicationId = Ivy.request().getApplication().getId();
    Long processModelId = Ivy.request().getProcessModel().getId();
    if (userId != null) {
      CaseColumnsConfiguration configData = service.getConfiguration(applicationId, userId, processModelId);
      if (configData != null) {
        selectedColumns = configData.getSelectedColumns();
      }
    }
    if (selectedColumns.isEmpty()) {
      selectedColumns.addAll(getDefaultColumns());
      isAutoHideColumns = true;
    }
    setDisableSelectionCheckboxes(isAutoHideColumns);
  }
  
  public void saveColumnsConfiguration() {
    // avoid duplicating
    for (String requiredColumn : portalRequiredColumns) {
      if (!selectedColumns.contains(requiredColumn)) {
        selectedColumns.add(requiredColumn);
      }
    }
    setAutoHideColumns(isDisableSelectionCheckboxes);
    CaseColumnsConfigurationService service = CaseColumnsConfigurationService.getInstance();
    Long processModelId = Ivy.request().getProcessModel().getId();
    Long applicationId = Ivy.request().getApplication().getId();
    CaseColumnsConfiguration caseColumnsConfiguration = service.getConfiguration(applicationId,
        Ivy.session().getSessionUser().getId(), processModelId);
    if (caseColumnsConfiguration != null) {
      updateCaseColumnsConfiguration(caseColumnsConfiguration);
    } else {
      caseColumnsConfiguration = createNewCaseColumnsConfigurationData();
    }
    service.save(caseColumnsConfiguration);
    
    initSelectedColumns();
  }

  private CaseColumnsConfiguration createNewCaseColumnsConfigurationData() {
    CaseColumnsConfiguration caseColumnsConfiguration = new CaseColumnsConfiguration();
    caseColumnsConfiguration.setProcessModelId(Ivy.request().getProcessModel().getId());
    caseColumnsConfiguration.setUserId(Ivy.session().getSessionUser().getId());
    caseColumnsConfiguration.setSecurityMemberId(Ivy.session().getSessionUser().getSecurityMemberId());
    caseColumnsConfiguration.setApplicationId(Ivy.request().getApplication().getId());
    caseColumnsConfiguration.setSelectedColumns(new ArrayList<>());
    updateCaseColumnsConfiguration(caseColumnsConfiguration);
    return caseColumnsConfiguration;
  }

  private void updateCaseColumnsConfiguration(CaseColumnsConfiguration caseColumnsConfigurationData) {
    caseColumnsConfigurationData.setAutoHideColumns(isAutoHideColumns);
    caseColumnsConfigurationData.getSelectedColumns().clear();
    if (isAutoHideColumns) {
      caseColumnsConfigurationData.getSelectedColumns().addAll(getDefaultColumns());
    } else {
      caseColumnsConfigurationData.getSelectedColumns().addAll(selectedColumns);
    }
  }


  /**
   * <p>
   * Your customized data model needs to override this method if your customized case list has new columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   *
   * return Arrays.asList("NAME", "ID" , "CREATOR", "CREATION_TIME", "FINISHED_TIME", "CustomerName", "CustomerType");
   *
   * </pre></code> This list is the list of sortFields in CaseColumnHeader Portal component when you use it to add new
   * column headers also the list of checkboxes in config columns panel
   * </p>
   *
   * @return default columns
   */
  public List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  /**
   * <p>
   * In case you adds new columns, these columns need cms to show in config columns panel
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalKit or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   *
   * @param column column name
   *
   * @return column label
   */
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + column);
  }

  public boolean isDisableSelectionCheckboxes() {
    return isDisableSelectionCheckboxes;
  }

  public void setDisableSelectionCheckboxes(boolean isDisableSelectionCheckboxes) {
    this.isDisableSelectionCheckboxes = isDisableSelectionCheckboxes;
  }

  public boolean isNotKeepFilter() {
    return isNotKeepFilter;
  }

  public void setNotKeepFilter(boolean isNotKeepFilter) {
    this.isNotKeepFilter = isNotKeepFilter;
    this.isSelectedDefaultFilter = false;
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public String getSortField() {
    return criteria.getSortField();
  }

  public boolean isSortDescending() {
    return criteria.isSortDescending();
  }

  public List<String> getSelectedColumns() {
    return selectedColumns;
  }

  public void setSelectedColumns(List<String> selectedColumns) {
    this.selectedColumns = selectedColumns;
  }

  public List<String> getAllColumns() {
    return allColumns;
  }

  public List<String> getPortalRequiredColumns() {
    return portalRequiredColumns;
  }

  public boolean isAutoHideColumns() {
    return isAutoHideColumns;
  }

  public void setAutoHideColumns(boolean isAutoHideColumns) {
    this.isAutoHideColumns = isAutoHideColumns;
  }

  /**
   * Check if your column is selected
   * @param column column name
   * @return is column selected
   */
  public boolean isSelectedColumn(String column) {
    return selectedColumns.stream().anyMatch(selectedcolumn -> selectedcolumn.equalsIgnoreCase(column));
  }

  public void setIsAdminQuery(boolean isAdminQuery) {
    criteria.setAdminQuery(isAdminQuery);
  }

  /**
   * Getter for case search criteria
   * @return criteria search criteria type {@link CaseSearchCriteria}
   */
  public CaseSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(CaseSearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public void setRowIndex(int index) {
    int idx = index;
    if (idx >= data.size()) {
      idx = -1;
    }
    this.rowIndex = idx;
  }

  @Override
  public ICase getRowData() {
    return data.get(rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }

  public boolean getDisableCaseCount() {
    return disableCaseCount;
  }

  public void setDisableCaseCount(boolean disableCaseCount) {
    this.disableCaseCount = disableCaseCount;
  }

  public boolean isSelectedDefaultFilter() {
    return isSelectedDefaultFilter;
  }

  public void setSelectedDefaultFilter(boolean isSelectedDefaultFilter) {
    this.isSelectedDefaultFilter = isSelectedDefaultFilter;
  }

  public boolean isSelectedAllFilters() {
    return isSelectedAllFilters;
  }

  public void setSelectedAllFilters(boolean isSelectedAllFilters) {
    this.isSelectedAllFilters = isSelectedAllFilters;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }
}
