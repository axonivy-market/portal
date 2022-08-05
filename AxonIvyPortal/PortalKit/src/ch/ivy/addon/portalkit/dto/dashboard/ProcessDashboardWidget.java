package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.process.ApplicationColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private ProcessWidgetMode displayMode;
  @JsonIgnore
  private boolean isPreview;
  private ApplicationColumnModel applicationFilter;

  public ProcessDashboardWidget() {}

  public ProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    applicationFilter = new ApplicationColumnModel();
    applicationFilter.initDefaultValue();
    displayMode = widget.getDisplayMode();
    isPreview = widget.isPreview();
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime() {
    DashboardWidgetUtils.loadProcessesOfWidget(this);
  }

  @Override
  public void buildStatisticInfos() {
    PrimeFaces.current().executeScript(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticProcessTypes", id));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {}

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

  @JsonIgnore
  public SortMeta getSortByName() {
    return SortFieldUtil.buildSortMeta("name", false);
  }

  public ApplicationColumnModel getApplicationFilter() {
    return applicationFilter;
  }

  public void setApplicationFilter(ApplicationColumnModel applicationFilter) {
    this.applicationFilter = applicationFilter;
  }

}
