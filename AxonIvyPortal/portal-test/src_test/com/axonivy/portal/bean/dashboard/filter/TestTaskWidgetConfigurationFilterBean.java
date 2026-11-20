package com.axonivy.portal.bean.dashboard.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;

import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StateColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestTaskWidgetConfigurationFilterBean {

  @Test
  void stateColumnOffersBusinessAndTechnicalStateFilters() {
    TaskDashboardWidget widget = taskWidgetWithStateColumn(true);
    TaskWidgetConfigurationFilterBean bean = new TaskWidgetConfigurationFilterBean();

    bean.preRender(widget);

    assertThat(bean.getFilterFields())
        .extracting(field -> field.getName())
        .contains(DashboardStandardTaskColumn.STATE.getField(), BaseFilter.TECHNICAL_STATE);
  }

  @Test
  void technicalStateFilterIsIndependentFromStateColumn() {
    TaskDashboardWidget widget = taskWidgetWithStateColumn(false);
    TaskWidgetConfigurationFilterBean bean = new TaskWidgetConfigurationFilterBean();

    bean.preRender(widget);

    assertThat(bean.getFilterFields())
        .extracting(field -> field.getName())
        .contains(BaseFilter.TECHNICAL_STATE)
        .doesNotContain(DashboardStandardTaskColumn.STATE.getField());
  }

  private static TaskDashboardWidget taskWidgetWithStateColumn(boolean enableFilter) {
    StateColumnModel stateColumn = new StateColumnModel();
    stateColumn.initDefaultValue();
    stateColumn.setEnableFilter(enableFilter);

    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId("task-widget");
    widget.setColumns(List.of(stateColumn));
    widget.buildFilterableColumns(widget.getColumns());
    return widget;
  }
}
