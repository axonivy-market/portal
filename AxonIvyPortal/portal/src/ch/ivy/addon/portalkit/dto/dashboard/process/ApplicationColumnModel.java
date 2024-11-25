package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bean.CombinedDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.CompactDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.FullDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.ImageDashboardProcessBean;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

public class ApplicationColumnModel extends ProcessColumnModel implements Serializable {
  private static final long serialVersionUID = -4196645833691283486L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass,
        "dashboard-tasks__priority u-text-align-center widget-column");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority u-text-align-center");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION";
  }

  @JsonIgnore
  public List<String> getApplications() {
    return ListUtilities.transformList(IApplicationRepository.of(ISecurityContext.current()).all(), IApplication::getName);
  }
  
  @JsonIgnore
  public void setApplications(List<String> applications) {
    this.filterList = applications;
  }
  
  @Override
  public Boolean getDefaultSortable() {
    return false;
  }
  
  @Override
  protected int getDefaultColumnWidth() {
    return TINY_WIDTH;
  }
  
  public void initializeApplications(ProcessDashboardWidget widget) {
    if (widget == null) {
      return;
    }
    if (widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) { 
      CompactDashboardProcessBean compactDashboardProcessBean = ManagedBeans.get("compactDashboardProcessBean");
      if (compactDashboardProcessBean != null) {
        compactDashboardProcessBean.onChangeApplications(this.filterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
      CombinedDashboardProcessBean combinedDashboardProcessBean = ManagedBeans.get("combinedDashboardProcessBean");
      if (combinedDashboardProcessBean != null) {
        combinedDashboardProcessBean.onChangeApplications(this.filterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.IMAGE_MODE) {
      ImageDashboardProcessBean imageDashboardProcessBean = ManagedBeans.get("imageDashboardProcessBean");
      if (imageDashboardProcessBean != null) {
        imageDashboardProcessBean.onChangeApplications(this.filterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
      FullDashboardProcessBean fullDashboardProcessBean = ManagedBeans.get("fullDashboardProcessBean");
      if (fullDashboardProcessBean != null) {
        fullDashboardProcessBean.onChangeApplications(this.filterList);
      }
    }
  }
  
  public void updateApplications(ProcessDashboardWidget widget) {
    initializeApplications(widget);

    if (widget instanceof CompactProcessDashboardWidget) {
      ((CompactProcessDashboardWidget) widget).setProcesses(null);
      final List<ColumnModel> filterableColumns = widget.getFilterableColumns();
      for (ColumnModel column : filterableColumns) {
        if (column instanceof CategoryColumnModel) {
          column.setFilterList(new ArrayList<>());
          ((CategoryColumnModel) column).setSelectionCategoryNodes(null);
        }
      }
      CompactDashboardProcessBean dashboardProcessBean = ManagedBeans.get("compactDashboardProcessBean");
      dashboardProcessBean.setPortalCompactProcesses(null);
    } else if (widget instanceof SingleProcessDashboardWidget) {
      ((SingleProcessDashboardWidget) widget).setProcess(null);
    }
    
  }
  
  @Override
  public List<String> getUserFilterListOptions(){
    if (CollectionUtils.isEmpty(this.userFilterListOptions)) {
      this.userFilterListOptions = getApplications();
    }
    
    return this.userFilterListOptions;
  }
}
