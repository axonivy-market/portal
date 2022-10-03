package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.APPLICATION;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.CATEGORY;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.NAME;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.TYPE;
import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.COMPACT_MODE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.TypeColumnModel;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.ListUtilities;

public class DashboardProcessSearchCriteria {

  private boolean isInConfiguration;
  private boolean isSelectedAllProcess;
  private List<String> categories;
  private List<String> applications;

  public List<DashboardProcess> searchProcessesByFilters(CompactProcessDashboardWidget widget) {
    if (widget == null || COMPACT_MODE != widget.getDisplayMode()) {
      return new ArrayList<>();
    }

    List<DashboardProcess> displayProcesses = widget.getOriginalDisplayProcesses();
    for (var column : widget.getFilterableColumns()) {
      if (NAME.getField().equalsIgnoreCase(column.getField()) && StringUtils.isNotEmpty(column.getUserFilter())) {
        displayProcesses = ListUtilities.filterList(displayProcesses, process -> StringUtils.containsIgnoreCase(process.getName(), column.getUserFilter()));
      } else if (TYPE.getField().equalsIgnoreCase(column.getField()) && CollectionUtils.isNotEmpty(column.getUserFilterList())) {
        List<ProcessType> typeFilters = ((TypeColumnModel) column).getUserProcessTypes();
        displayProcesses = ListUtilities.filterList(displayProcesses, process -> typeFilters.contains(process.getType()));
      } else if (CATEGORY.getField().equalsIgnoreCase(column.getField())) {
        List<String> categories = isInConfiguration ? column.getFilterList() : column.getUserFilterList();
        if (CollectionUtils.isEmpty(categories)) {
          continue;
        }
        displayProcesses = ListUtilities.filterList(displayProcesses, process -> isProcessMatchedCategory(process, categories));
      } else if (APPLICATION.getField().equalsIgnoreCase(column.getField())) {
        List<String> applications = isInConfiguration ? column.getFilterList() : column.getUserFilterList();
        if (!isInConfiguration && CollectionUtils.isEmpty(applications)) {
          applications = column.getFilterList();
        }
        
        if (CollectionUtils.isNotEmpty(applications)) {
          final List<String> apps = applications;
          displayProcesses = ListUtilities.filterList(displayProcesses, process -> apps.contains(process.getApplication()));
        } 
      }
      
    }
    return displayProcesses;
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  public boolean isInConfiguration() {
    return isInConfiguration;
  }

  public void setInConfiguration(boolean isInConfiguration) {
    this.isInConfiguration = isInConfiguration;
  }

  public boolean isSelectedAllProcess() {
    return isSelectedAllProcess;
  }

  public void setSelectedAllProcess(boolean isSelectedAllProcess) {
    this.isSelectedAllProcess = isSelectedAllProcess;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getApplications() {
    return applications;
  }

  public void setApplications(List<String> applications) {
    this.applications = applications;
  }
}
