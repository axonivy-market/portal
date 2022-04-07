package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.workflow.CaseState;

public class CaseDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  private int rowsPerPage = 10;
  @JsonIgnore
  private DashboardCaseLazyDataModel dataModel;
  @JsonIgnore
  private Map<CaseState, Long> caseByStateStatistic;
  @JsonIgnore
  private Map<String, Long> caseByCategoryStatistic;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;

  public CaseDashboardWidget() {
    dataModel = new DashboardCaseLazyDataModel();
    setColumns(new ArrayList<>());
  }

  @JsonIgnore
  @Override
  public void buildStatisticInfos() {
    String combinedAjaxCommand = String.format(REMOTE_COMMAND_PATTERN, "buildStatisticCaseStates", id)
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticCaseCategory", id))
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildCaseDefinedFilter", id));
    PrimeFaces.current().executeScript(combinedAjaxCommand);
  }

  @JsonIgnore
  public void buildCaseByStateStatistic() {
    caseByStateStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfCaseByState(dataModel);
  }

  @JsonIgnore
  public void buildCaseByCategoryStatistic() {
    caseByCategoryStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfCaseByCategory(dataModel);
  }

  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<CaseColumnModel> caseColumns) {
    filterableColumns = DashboardWidgetUtils.buildCaseFilterableColumns(caseColumns);
  }

  @JsonIgnore
  public SortMeta getSortBy() {
    List<CaseColumnModel> columnModels = getColumns();
    if (CollectionUtils.isNotEmpty(columnModels)) {
      String sortField = getSortField();
      for (CaseColumnModel caseColumnModel : columnModels) {
        if (caseColumnModel.getField().equalsIgnoreCase(sortField)) {
          return SortFieldUtil.buildSortMeta(caseColumnModel.getField(), isSortDescending());
        }
      }
    }
    return null;
  }

  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }

  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }

  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }

  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardCaseLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(DashboardCaseLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public List<CaseColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }

  public void setColumns(List<CaseColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
  }

  @JsonIgnore
  public int getCaseCount() {
    return getDataModel().getRowCount();
  }

  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }

  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  @JsonIgnore
  public Map<CaseState, Long> getCaseByStateStatistic() {
    return caseByStateStatistic;
  }

  @JsonIgnore
  public Map<String, Long> getCaseByCategoryStatistic() {
    return caseByCategoryStatistic;
  }

  @Override
  public void buildPredefinedFilterData() {
    setHasPredefinedFilter(DashboardWidgetUtils.hasPredefinedCaseFilter(this));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    DashboardWidgetUtils.resetUserFilterOnColumns(getColumns());
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CASE;
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }
}
