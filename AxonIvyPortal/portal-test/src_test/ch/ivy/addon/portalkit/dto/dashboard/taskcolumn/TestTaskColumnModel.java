package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.businessCase;
import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.subCaseOf;
import static ch.ivy.addon.portalkit.fixture.WorkflowFakes.task;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

/**
 * A CUSTOM_CASE column is filtered with {@code CaseQuery.subCases()}, which scopes the query to
 * cases that have a business case of their own. A task sitting directly on the business case is
 * therefore never matched by that filter, and must not render a value either.
 */
class TestTaskColumnModel {

  private static final IBusinessCase BUSINESS_CASE = businessCase(Map.of("CustomerName", "AxonIvy"));
  private static final ITask TASK_ON_BUSINESS_CASE = task(BUSINESS_CASE, Map.of());
  private static final ITask TASK_ON_SUB_CASE =
      task(subCaseOf(BUSINESS_CASE, Map.of("CustomerType", "Enterprise")), Map.of());

  @ParameterizedTest(name = "{0} column reading {2} on a task of the {1} case shows {3}")
  @CsvSource(nullValues = "NOTHING", value = {
      "CUSTOM_CASE,          business, CustomerName, NOTHING",
      "CUSTOM_CASE,          sub,      CustomerType, Enterprise",
      "CUSTOM_CASE,          sub,      CustomerName, NOTHING",
      "CUSTOM_BUSINESS_CASE, business, CustomerName, AxonIvy",
      "CUSTOM_BUSINESS_CASE, sub,      CustomerName, AxonIvy",
      "CUSTOM_BUSINESS_CASE, sub,      CustomerType, NOTHING"})
  void caseCustomFieldColumn_readsOnlyTheCaseItIsScopedTo(DashboardColumnType type, String caseKind,
      String field, String expected) {
    ITask task = "business".equals(caseKind) ? TASK_ON_BUSINESS_CASE : TASK_ON_SUB_CASE;

    assertThat(column(type, field).display(task)).isEqualTo(expected);
  }

  @Test
  void taskCustomFieldColumn_readsTheTaskItself() {
    ITask task = task(BUSINESS_CASE, Map.of("CustomerName", "from task"));

    assertThat(column(DashboardColumnType.CUSTOM, "CustomerName").display(task)).isEqualTo("from task");
  }

  private static TaskColumnModel column(DashboardColumnType type, String field) {
    TaskColumnModel column = new TaskColumnModel();
    column.setType(type);
    column.setField(field);
    column.setFormat(DashboardColumnFormat.STRING);
    return column;
  }
}
