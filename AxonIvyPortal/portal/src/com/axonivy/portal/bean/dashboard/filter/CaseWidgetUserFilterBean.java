package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;

@ManagedBean
@ViewScoped
public class CaseWidgetUserFilterBean extends AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 7812171996900852992L;

  private List<DashboardFilter> originalUserFilters;

  @Override
  public void preRender(CaseDashboardWidget widget) {
    super.preRender(widget);
    originalUserFilters = widget.getUserFilters();
  }

  @Override
  protected void initFilterTypes() {
    if (Optional.ofNullable(widget).map(CaseDashboardWidget::getFilterableColumns).isPresent()) {
      filterTypes = widget.getFilterableColumns().stream()
          .map(getFilterField())
          .filter(Objects::nonNull)
          .collect(Collectors.toUnmodifiableList());
    }
  }

  private Function<ColumnModel, DashboardStandardCaseColumn> getDashboardStandardCaseColumn() {
    return column -> DashboardStandardCaseColumn.findBy(Optional.ofNullable(column).map(ColumnModel::getField).orElse(""));
  }

  private Function<ColumnModel, FilterField> getFilterField() {
    return column -> FilterFieldFactory
        .findBy(Optional.ofNullable(column).map(ColumnModel::getField).orElse(""));
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
