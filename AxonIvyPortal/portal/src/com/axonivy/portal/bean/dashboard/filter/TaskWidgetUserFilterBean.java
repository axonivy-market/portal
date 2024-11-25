package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;

@ManagedBean
@ViewScoped
public class TaskWidgetUserFilterBean extends AbstractTaskWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 2329063671455796253L;

  private List<DashboardFilter> originalUserFilters;

  @Override
  public void preRender(TaskDashboardWidget widget) {
    super.preRender(widget);
    initUserFilters();
    originalUserFilters = widget.getUserFilters();
  }

  private void initUserFilters() {
    if (CollectionUtils.isEmpty(Optional.ofNullable(this.widget).map(TaskDashboardWidget::getUserFilters).get())) {
      return;
    }

    for (DashboardFilter filter : this.widget.getUserFilters()) {
      if (Optional.ofNullable(filter).map(DashboardFilter::getFilterType)
          .isEmpty()) {
        return;
      }

      FilterField filterField = TaskFilterFieldFactory
          .findBy(Optional.ofNullable(filter).map(DashboardFilter::getField)
              .orElse(""), filter.getFilterType());
      if (filterField != null) {
        filterField.initFilter(filter);
      }
    }
  }

  @Override
  public void removeFilter(TaskDashboardWidget widget, DashboardFilter filter) {
    widget.getUserFilters().remove(filter);
    widget.getUserFilterCollection().getSelectedWidgetFilters().clear();
  }

  @Override
  public void addNewFilter(TaskDashboardWidget widget) {
    if (widget.getUserFilters() == null) {
      widget.setUserFilters(new ArrayList<>());
    }

    DashboardFilter newFilter = new DashboardFilter();
    newFilter.setTemp(true);
    widget.getUserFilters().add(newFilter);
  }

  public void onCancelUserFilters() {
    widget.setUserFilters(originalUserFilters);
  }

  @Override
  public void resetTaskWidgetFilter(TaskDashboardWidget widget) { }

  @Override
  public void resetCaseWidgetFilter(CaseDashboardWidget widget) { }
}
