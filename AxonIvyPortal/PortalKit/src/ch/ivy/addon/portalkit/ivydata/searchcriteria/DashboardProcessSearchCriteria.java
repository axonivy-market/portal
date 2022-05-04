package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.CATEGORY;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.NAME;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.TYPE;
import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.COMPACT_MODE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.TypeColumnModel;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.util.CategoryUtils;

public class DashboardProcessSearchCriteria {

  private boolean isInConfiguration;
  private boolean isSelectedAllProcess;
  private List<String> categories;

  public List<DashboardProcess> searchProcessesByFilters(CompactProcessDashboardWidget widget) {
    if (widget == null || COMPACT_MODE != widget.getDisplayMode()) {
      return new ArrayList<>();
    }

    List<DashboardProcess> displayProcesses = widget.getOriginalDisplayProcesses();
    for (var column : widget.getFilterableColumns()) {
      if (NAME.getField().equalsIgnoreCase(column.getField()) && StringUtils.isNotEmpty(column.getUserFilter())) {
        displayProcesses = displayProcesses.stream()
            .filter(process -> StringUtils.containsIgnoreCase(process.getName(), column.getUserFilter()))
            .collect(Collectors.toList());
      }
      if (TYPE.getField().equalsIgnoreCase(column.getField()) && CollectionUtils.isNotEmpty(column.getUserFilterList())) {
        List<ProcessType> typeFilters = ((TypeColumnModel) column).getUserProcessTypes();
        displayProcesses = displayProcesses.stream()
            .filter(process -> typeFilters.contains(process.getType()))
            .collect(Collectors.toList());
      }
      if (CATEGORY.getField().equalsIgnoreCase(column.getField())) {
        List<String> categories = isInConfiguration ? column.getFilterList() : column.getUserFilterList();
        if (CollectionUtils.isEmpty(categories)) {
          continue;
        }
        displayProcesses = displayProcesses.stream()
            .filter(process -> isProcessMatchedCategory(process, categories))
            .collect(Collectors.toList());
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
}
