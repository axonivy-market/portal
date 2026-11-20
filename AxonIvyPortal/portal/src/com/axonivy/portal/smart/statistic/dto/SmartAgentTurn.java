package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;

/**
 * What one turn with the agent produced.
 *
 * A turn always has something to say - a sentence for the user and any caveats - whether or not
 * it ended in a chart. The chart is only present when the proposal survived validation, which is
 * what {@link #isUsable()} means: the caller may adopt {@link #getStatistic()} and replay from
 * the spec it came with.
 */
public class SmartAgentTurn implements Serializable {

  private static final long serialVersionUID = 1L;

  private final boolean usable;
  private final String note;
  private final List<String> warnings;
  private final Statistic statistic;
  private final SmartChartSpec spec;
  private final ProviderChartProposal customChart;

  private SmartAgentTurn(boolean usable, String note, List<String> warnings, Statistic statistic,
      SmartChartSpec spec, ProviderChartProposal customChart) {
    this.usable = usable;
    this.note = note;
    this.warnings = warnings == null ? new ArrayList<>() : warnings;
    this.statistic = statistic;
    this.spec = spec;
    this.customChart = customChart;
  }

  /** Nothing to configure from: the note says why, the warnings say what was wrong with it. */
  public static SmartAgentTurn rejected(String note, List<String> warnings) {
    return new SmartAgentTurn(false, note, warnings, null, null, null);
  }

  /** A task or case chart the caller may adopt, replayable from its spec. */
  public static SmartAgentTurn ofChart(String note, List<String> warnings, Statistic statistic,
      SmartChartSpec spec) {
    return new SmartAgentTurn(true, note, warnings, statistic, spec, null);
  }

  /** A provider-backed chart, replayable from its proposal rather than from a spec. */
  public static SmartAgentTurn ofCustomChart(String note, List<String> warnings, Statistic statistic,
      ProviderChartProposal customChart) {
    return new SmartAgentTurn(true, note, warnings, statistic, null, customChart);
  }

  public boolean isUsable() {
    return usable;
  }

  public String getNote() {
    return note;
  }

  public List<String> getWarnings() {
    return warnings;
  }

  public Statistic getStatistic() {
    return statistic;
  }

  public SmartChartSpec getSpec() {
    return spec;
  }

  public ProviderChartProposal getCustomChart() {
    return customChart;
  }
}
