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
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;

@ManagedBean
@ViewScoped
public class CaseWidgetUserFilterBean extends AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 7812171996900852992L;

  private List<DashboardFilter> originalUserFilters;

  @Override
  public void preRender(CaseDashboardWidget widget) {
    super.preRender(widget);
    initUSerFilters();
    widget.setInConfiguration(false);
    originalUserFilters = widget.getUserFilters();
  }

  private void initUSerFilters() {
    if (CollectionUtils.isEmpty(Optional.ofNullable(this.widget)
        .map(CaseDashboardWidget::getUserFilters).get())) {
      return;
    }

    for (DashboardFilter filter : this.widget.getUserFilters()) {
      FilterField filterField = FilterFieldFactory.findBy(Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(""));
      if (filterField != null) {
        filterField.initFilter(filter);
      }
    }
  }

  @Override
  public void removeFilter(CaseDashboardWidget widget, DashboardFilter filter) {
    widget.getUserFilters().remove(filter);
    widget.getUserFilterCollection().getSelectedWidgetFilters().clear();
  }

  @Override
  public void addNewFilter(CaseDashboardWidget widget) {
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
}
