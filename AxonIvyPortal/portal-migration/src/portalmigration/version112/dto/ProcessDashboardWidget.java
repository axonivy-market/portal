package portalmigration.version112.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import portalmigration.version112.dto.process.ProcessColumnModel;
import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.enums.ProcessWidgetMode;
import portalmigration.version112.searchcriteria.DashboardProcessSearchCriteria;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private ProcessWidgetMode displayMode;
  @JsonIgnore
  protected List<ColumnModel> filterableColumns;
  @JsonIgnore
  private boolean isPreview;
  @JsonIgnore
  protected DashboardProcessSearchCriteria criteria;

  public ProcessDashboardWidget() {
    criteria = new DashboardProcessSearchCriteria();
  }

  public ProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    criteria = widget.getCriteria();
    displayMode = widget.getDisplayMode();
    isPreview = widget.isPreview();
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS;
  }

  public ProcessWidgetMode getDisplayMode() {
    return displayMode;
  }

  public void setDisplayMode(ProcessWidgetMode displayMode) {
    this.displayMode = displayMode;
  }

  @JsonIgnore
  public boolean isPreview() {
    return isPreview;
  }

  @JsonIgnore
  public void setPreview(boolean isPreview) {
    this.isPreview = isPreview;
  }

  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  public void setFilterableColumns(List<ColumnModel> columns) {
    this.filterableColumns = columns;
  }

  public void buildFilterableColumns(List<ProcessColumnModel> columns) {
    this.filterableColumns = columns.stream().collect(Collectors.toList());
  }

  public DashboardProcessSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardProcessSearchCriteria criteria) {
    this.criteria = criteria;
  }
  
  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.criteria.setInConfiguration(isInConfiguration);
  }
  
  public List<String> getApplications() {
    return this.criteria.getApplications();
  }
  
  public void setApplications(List<String> applications) {
    this.criteria.setApplications(applications);
  }
}
