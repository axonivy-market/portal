package ch.ivy.addon.portalkit.exporter;

import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.businessCase;
import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.subCaseOf;
import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.task;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

/**
 * The export has to agree with what the widget renders, otherwise an exported sheet shows values
 * for rows the column's own filter excludes. See {@code TestTaskColumnModel} for that rule.
 */
class TestTaskDashboardExporter {

  private static final IBusinessCase BUSINESS_CASE = businessCase(Map.of("CustomerName", "AxonIvy"));
  private static final ITask TASK_ON_BUSINESS_CASE = task(BUSINESS_CASE, Map.of());
  private static final ITask TASK_ON_SUB_CASE =
      task(subCaseOf(BUSINESS_CASE, Map.of("CustomerType", "Enterprise")), Map.of());

  @ParameterizedTest(name = "{0} on a task of the {1} case exports {2}")
  @CsvSource(nullValues = "NOTHING", value = {
      "STRING__CustomerName__Customer name__CUSTOM_CASE,          business, NOTHING",
      "STRING__CustomerType__Customer type__CUSTOM_CASE,          sub,      Enterprise",
      "STRING__CustomerName__Customer name__CUSTOM_BUSINESS_CASE, business, AxonIvy",
      "STRING__CustomerName__Customer name__CUSTOM_BUSINESS_CASE, sub,      AxonIvy"})
  void caseCustomFieldColumn_exportsTheSameValueTheWidgetRenders(String column, String caseKind,
      String expected) {
    ITask task = "business".equals(caseKind) ? TASK_ON_BUSINESS_CASE : TASK_ON_SUB_CASE;
    TaskDashboardExporter exporter =
        new TaskDashboardExporter(List.of(column), List.<ColumnModel>of(), "Your Tasks");

    assertThat(exporter.getColumnValue(column, task)).isEqualTo(expected);
  }
}
