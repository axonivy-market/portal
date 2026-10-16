package com.axonivy.portal.smart.statistic.prompt;

import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;
import com.axonivy.portal.smart.statistic.service.SmartStatisticVocabularyService;
import com.axonivy.portal.smart.statistic.service.StatisticFilterFieldService;
import com.axonivy.portal.util.filter.field.FilterField;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * The "Allowed values" section of the chart prompt, rendered from the same sets the mapper
 * enforces so the prompt cannot promise something the mapper then rejects.
 */
public final class VocabularyBlock {

  private VocabularyBlock() {}

  /**
   * Renders the whole catalogue as markdown for the system prompt. Cache the result per view
   * scope - it is several kilobytes and nothing in it changes mid-session.
   */
  public static String render() {
    StringBuilder out = new StringBuilder();

    out.append("## Allowed values\n\n");
    out.append("chartType: ").append(names(ChartType.values(), ChartType::getName)).append('\n');
    out.append("target: ").append(names(ChartTarget.values(), ChartTarget::getName)).append("\n\n");

    out.append("### groupByField, per target and chart type\n");
    for (ChartTarget target : ChartTarget.values()) {
      appendGroupBy(out, target, ChartType.BAR, "bar / line / pie");
      appendGroupBy(out, target, ChartType.NUMBER, "number");
    }
    out.append('\n');

    out.append("Timestamp group-by fields (interval REQUIRED for these, FORBIDDEN otherwise): ")
        .append(String.join(", ", AggregationField.TIMESTAMP_AGGREGATES)).append('\n');
    out.append("interval: ")
        .append(names(AggregationInterval.DATE_TIME_INTERVALS.toArray(new AggregationInterval[0]),
            AggregationInterval::getName))
        .append("\n\n");

    appendCustomFields(out);
    appendFilterFields(out);
    appendEnumeratedValues(out);

    return out.toString();
  }

  private static void appendGroupBy(StringBuilder out, ChartTarget target, ChartType type, String label) {
    out.append("- ").append(target.getName()).append(" + ").append(label).append(": ")
        .append(SmartStatisticVocabularyService.availableGroupByFields(target, type).stream()
            .map(AggregationField::getName).sorted().collect(Collectors.joining(", ")))
        .append('\n');
  }

  private static void appendCustomFields(StringBuilder out) {
    out.append("### Custom fields\n");
    out.append("Use groupByField=\"").append(AggregationField.CUSTOM_FIELD.getName())
        .append("\" together with customFieldName.\n");
    for (ChartTarget target : ChartTarget.values()) {
      List<String> groupable = SmartStatisticVocabularyService.groupableCustomFieldNames(target);
      List<String> numeric = SmartStatisticVocabularyService.numericCustomFieldNames(target);
      out.append("- ").append(target.getName()).append(" customFieldName: ")
          .append(groupable.isEmpty() ? "(none)" : String.join(", ", groupable)).append('\n');
      out.append("- ").append(target.getName()).append(" kpiField: ")
          .append(String.join(", ", SmartStatisticVocabularyService.BUILT_IN_KPI_FIELDS));
      if (!numeric.isEmpty()) {
        out.append(", ").append(String.join(", ", numeric));
      }
      out.append('\n');
    }
    out.append("kpiMethod applies only when kpiField is set: sum, avg, max, min.\n")
        .append("Leave kpiField blank (or \"").append(SmartStatisticVocabularyService.COUNTING).append("\") to count records.\n\n");
  }

  private static void appendFilterFields(StringBuilder out) {
    out.append("### Filter fields (NOTE: filter names differ from groupByField names)\n");
    GlobalOperatorPolicyService policy = new GlobalOperatorPolicyService();
    for (ChartTarget target : ChartTarget.values()) {
      out.append("- ").append(target.getName()).append(":\n");
      for (FilterField field : StatisticFilterFieldService.filterFields(target)) {
        List<FilterOperator> operators = StatisticFilterFieldService.statisticOperatorsFor(field, policy);
        if (operators.isEmpty()) {
          continue;
        }
        out.append("    ").append(field.getName())
            .append(" (\"").append(StringUtils.defaultString(field.getLabel())).append("\")")
            .append(" - operators: ")
            .append(operators.stream().map(Enum::name).collect(Collectors.joining(", ")))
            .append('\n');
      }
    }
    out.append("Set custom=true when the filter field is a custom field.\n")
        .append("IN / NOT_IN need values; BETWEEN needs from and to as \"MM/dd/yyyy HH:mm\"; ")
        .append("LAST / NEXT need periods and periodType; CURRENT needs periodType ")
        .append("(WEEK, MONTH or YEAR only); CURRENT_USER, TODAY, YESTERDAY and NO_CATEGORY ")
        .append("need nothing else.\n\n");
  }

  private static void appendEnumeratedValues(StringBuilder out) {
    out.append("### Enumerated filter values\n");
    out.append("- task state: ")
        .append(names(TaskBusinessState.values(), Enum::name)).append('\n');
    out.append("- case state: ")
        .append(names(CaseBusinessState.values(), Enum::name)).append('\n');
    out.append("- priority: ")
        .append(names(WorkflowPriority.values(), Enum::name)).append('\n');
  }

  private static <T> String names(T[] values, java.util.function.Function<T, String> toName) {
    return Arrays.stream(values).map(toName).collect(Collectors.joining(", "));
  }
}
