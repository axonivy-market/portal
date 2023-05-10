package portalmigration.version112.searchcriteria;

import static portalmigration.version112.enums.DashboardStandardProcessColumn.APPLICATION;
import static portalmigration.version112.enums.DashboardStandardProcessColumn.CATEGORY;
import static portalmigration.version112.enums.DashboardStandardProcessColumn.NAME;
import static portalmigration.version112.enums.DashboardStandardProcessColumn.TYPE;
import static portalmigration.version112.enums.ProcessWidgetMode.COMPACT_MODE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import portalmigration.version112.dto.CompactProcessDashboardWidget;
import portalmigration.version112.dto.DashboardProcess;
import portalmigration.version112.dto.process.TypeColumnModel;
import portalmigration.version112.enums.ProcessType;
import portalmigration.version112.util.DashboardWidgetUtils;
import portalmigration.version112.util.ListUtilities;

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
        displayProcesses = ListUtilities.filterList(displayProcesses, process -> DashboardWidgetUtils.isProcessMatchedCategory(process, categories));
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
