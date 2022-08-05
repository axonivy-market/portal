package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bean.CombinedDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.CompactDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.FullDashboardProcessBean;
import ch.ivy.addon.portalkit.bean.ImageDashboardProcessBean;
import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

public class ApplicationColumnModel extends ProcessColumnModel implements Serializable {
  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION");
    this.field = DashboardStandardProcessColumn.APPLICATION.getField();
    this.style = defaultIfEmpty(this.style, TINY_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority u-text-align-center");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION";
  }
  
  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }
  
  @JsonIgnore
  public List<String> getApplications() {
    return ListUtilities.transformList(IApplicationRepository.instance().allOf(ISecurityContext.current()), IApplication::getName);
  }
  
  @JsonIgnore
  public void setApplications(List<String> applications) {
    this.filterList = applications;
  }
  
  @JsonIgnore
  public List<String> getUserFilterApplicationOptions() {
    return getApplications();
  }
  
  @JsonIgnore
  public List<String> getUserFilterApplications() {
    return this.userFilterList;
  }
  
  @JsonIgnore
  public void setUserFilterApplications(List<String> applications) {
    this.userFilterList = applications;
  }
  
  @Override
  public Boolean getDefaultSortable() {
    return false;
  }
  
  public void updateApplications(ProcessDashboardWidget widget) {
    
    if (widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) { 
      CompactDashboardProcessBean compactDashboardProcessBean = ManagedBeans.get("compactDashboardProcessBean");
      if (compactDashboardProcessBean != null) {
        compactDashboardProcessBean.onChangeApplications(this.userFilterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
      CombinedDashboardProcessBean combinedDashboardProcessBean = ManagedBeans.get("combinedDashboardProcessBean");
      if (combinedDashboardProcessBean != null) {
        combinedDashboardProcessBean.onChangeApplications(this.userFilterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.IMAGE_MODE) {
      ImageDashboardProcessBean imageDashboardProcessBean = ManagedBeans.get("imageDashboardProcessBean");
      if (imageDashboardProcessBean != null) {
        imageDashboardProcessBean.onChangeApplications(this.userFilterList);
      }
    } else if (widget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
      FullDashboardProcessBean fullDashboardProcessBean = ManagedBeans.get("fullDashboardProcessBean");
      if (fullDashboardProcessBean != null) {
        fullDashboardProcessBean.onChangeApplications(this.userFilterList);
      }
    }

    if (widget instanceof CompactProcessDashboardWidget) {
      ((CompactProcessDashboardWidget) widget).setProcesses(null);
      final List<ColumnModel> filterableColumns = ((CompactProcessDashboardWidget) widget).getFilterableColumns();
      for (ColumnModel column : filterableColumns) {
        if (column instanceof CategoryColumnModel) {
          ((CategoryColumnModel) column).setFilterList(new ArrayList<>());
          ((CategoryColumnModel) column).setSelectionCategoryNodes(null);
        }
      }
    } else if (widget instanceof SingleProcessDashboardWidget) {
      ((SingleProcessDashboardWidget) widget).setProcess(null);
    }
    
  }
}
