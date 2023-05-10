package portalmigration.version112.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import portalmigration.version112.datamodel.DashboardCaseLazyDataModel;
import portalmigration.version112.dto.casecolumn.CaseColumnModel;
import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.util.DashboardWidgetUtils;

public class CaseDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  private int rowsPerPage = 5;
  @JsonIgnore
  private DashboardCaseLazyDataModel dataModel;
  @JsonIgnore
  private Map<CaseBusinessState, Long> caseByStateStatistic;
  @JsonIgnore
  private Map<String, Long> caseByCategoryStatistic;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;

  public CaseDashboardWidget() {
    dataModel = new DashboardCaseLazyDataModel();
    setColumns(new ArrayList<>());
  }

  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<CaseColumnModel> caseColumns) {
    filterableColumns = DashboardWidgetUtils.buildCaseFilterableColumns(caseColumns);
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
  public Map<CaseBusinessState, Long> getCaseByStateStatistic() {
    return caseByStateStatistic;
  }

  @JsonIgnore
  public Map<String, Long> getCaseByCategoryStatistic() {
    return caseByCategoryStatistic;
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
