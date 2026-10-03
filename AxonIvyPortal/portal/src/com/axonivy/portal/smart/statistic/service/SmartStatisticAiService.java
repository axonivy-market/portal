package com.axonivy.portal.smart.statistic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.smart.ai.SmartAiFunction;
import com.axonivy.portal.smart.ai.SmartAiHarness;
import com.axonivy.portal.smart.ai.SmartAiOutcome;
import com.axonivy.portal.smart.statistic.dto.SmartAgentTurn;
import com.axonivy.portal.smart.statistic.dto.SmartChartSpec;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticAgentResult;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticMappingResult;
import com.axonivy.portal.smart.statistic.prompt.ChartPrompt;
import com.axonivy.portal.smart.statistic.prompt.FieldNamingPrompt;
import com.axonivy.portal.smart.statistic.prompt.InsightPrompt;
import com.axonivy.portal.smart.statistic.provider.FieldCatalogueService;
import com.axonivy.portal.smart.statistic.provider.ProviderChartMapper;
import com.axonivy.portal.smart.statistic.provider.ProviderChartService;
import com.axonivy.portal.smart.statistic.provider.ProviderCollector;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.provider.dto.FieldCatalogueResult;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;

import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;

public class SmartStatisticAiService {

  /** Turn a question or a prompt into a chart configuration. */
  static final SmartAiFunction<SmartStatisticAgentResult> CHART_PROPOSAL =
      SmartAiFunction.of("statistic.chart-proposal", SmartStatisticAgentResult.class);

  /** Read a chart's numbers back to the user in a sentence or two. */
  static final SmartAiFunction<SmartStatisticAgentResult> CHART_INSIGHT =
      SmartAiFunction.of("statistic.chart-insight", SmartStatisticAgentResult.class);

  /** Name a data provider's fields, so a picker shows titles rather than paths. */
  static final SmartAiFunction<FieldCatalogueResult> FIELD_TITLING =
      SmartAiFunction.of("statistic.field-titling", FieldCatalogueResult.class);

  private SmartStatisticAiService() {};

  public static boolean isAvailable() {
    return SmartAiHarness.isAvailable();
  }

  public static String failureMessage(SmartAiOutcome<?> outcome) {
    return switch (outcome.failure()) {
      case NOT_AVAILABLE -> "The chart assistant is not available in this installation.";
      case GUARDRAIL_INPUT -> "That request could not be sent to the assistant. Please rephrase it.";
      case GUARDRAIL_OUTPUT -> "The answer from the assistant was rejected before it could be used. Please try again.";
      case CIRCUIT_BREAKER -> "The assistant is temporarily unavailable after repeated failures. Please try again in a few minutes.";
      default -> "Something went wrong while contacting the assistant. Please try again.";
    };
  }

  public static String systemMessage(Locale language) {
    List<SubProcessCallStartEvent> providers = new ProviderCollector().providerStarts();
    return ChartPrompt.systemMessage(language, providers, FieldCatalogueService.byProviderName(providers));
  }

  public static SmartAgentTurn propose(String text, String systemMessage,
      Statistic chart, SmartChartSpec spec, ProviderChartProposal customChart) {
    List<String> warnings = new ArrayList<>();
    if (StringUtils.isBlank(text)) {
      return SmartAgentTurn.rejected(null, warnings);
    }

    // No availability check: the harness finds no callable and reports NOT_AVAILABLE itself, which
    // is the same sentence. Asking the caller would only let a flag cached at page load go stale.
    SmartAiOutcome<SmartStatisticAgentResult> outcome = SmartAiHarness.run(
        CHART_PROPOSAL, buildQuery(text, chart, spec, customChart), systemMessage);
    if (outcome.isFailed()) {
      return SmartAgentTurn.rejected(failureMessage(outcome), warnings);
    }

    SmartStatisticAgentResult result = outcome.result();
    String note = StringUtils.isBlank(result.getReply()) ? "The configuration below was updated." : result.getReply();

    if (StringUtils.isNotBlank(result.getRefusalReason())) {
      warnings.add(result.getRefusalReason());
      return SmartAgentTurn.rejected(note, warnings);
    }
    // When the agent asks a question it often still emits an empty chart object because the
    // field exists in the schema. A configuration is meaningless without a group-by field, so
    // that is the test for a real proposal - mapping the shell would only produce noise.
    if (result.getChart() == null || StringUtils.isBlank(result.getChart().getGroupByField())) {
      // The same shell problem applies to `customChart`, where naming a provider is the substance.
      if (result.getCustomChart() != null && StringUtils.isNotBlank(result.getCustomChart().getProvider())) {
        return proposeCustomChart(result.getCustomChart(), note, warnings, chart);
      }
      return SmartAgentTurn.rejected(note, warnings);
    }

    SmartStatisticMappingResult mapping = SmartChartSpecMapper.toStatistic(result.getChart(), chart);
    warnings.addAll(mapping.getWarnings());
    if (!mapping.isUsable()) {
      return SmartAgentTurn.rejected(note, warnings);
    }
    return SmartAgentTurn.ofChart(note, warnings, mapping.getStatistic(), result.getChart());
  }

  private static SmartAgentTurn proposeCustomChart(ProviderChartProposal proposal, String note,
      List<String> warnings, Statistic chart) {
    Optional<SubProcessCallStartEvent> provider = ProviderChartService.findProvider(proposal.getProvider());
    if (provider.isEmpty()) {
      warnings.add(ProviderChartService.providerUnknown(proposal.getProvider()));
      return SmartAgentTurn.rejected(note, warnings);
    }

    DynamicFieldCatalogue catalogue = FieldCatalogueService.catalogueFor(provider.get(), warnings);
    if (catalogue == null || catalogue.isEmpty()) {
      warnings.add(ProviderChartService.noData(proposal.getProvider()));
      return SmartAgentTurn.rejected(note, warnings);
    }

    SmartStatisticMappingResult mapping =
        ProviderChartMapper.toStatistic(proposal, provider.get(), catalogue, chart);
    warnings.addAll(mapping.getWarnings());
    if (!mapping.isUsable()) {
      return SmartAgentTurn.rejected(note, warnings);
    }
    return SmartAgentTurn.ofCustomChart(note, warnings, mapping.getStatistic(), proposal);
  }

 
  private static String buildQuery(String text, Statistic chart, SmartChartSpec spec,
      ProviderChartProposal customChart) {
    return customChart == null
        ? ChartPrompt.query(text, spec)
        : ChartPrompt.customQuery(text, customChart, chart == null ? null : chart.getCustomChart());
  }

  public static String insight(Statistic chart, Locale language) {
    String reading = SmartChartResultService.readResult(chart);
    if (reading == null) {
      return "This chart has no numbers to read right now.";
    }
    SmartAiOutcome<SmartStatisticAgentResult> outcome = SmartAiHarness.run(
        CHART_INSIGHT,
        InsightPrompt.query(chart, reading),
        InsightPrompt.systemMessage(language));
    if (outcome.isFailed()) {
      return failureMessage(outcome);
    }
    String reply = outcome.result().getReply();
    return StringUtils.isBlank(reply) ? "The assistant could not read this chart." : reply;
  }

  public static FieldCatalogueResult nameFields(SubProcessCallStartEvent provider,
      DynamicFieldCatalogue catalogue, String sampleRows, List<String> languages) {
    return SmartAiHarness.run(
        FIELD_TITLING,
        FieldNamingPrompt.query(provider, catalogue, sampleRows),
        FieldNamingPrompt.systemMessage(languages)).orElse(null);
  }

}
