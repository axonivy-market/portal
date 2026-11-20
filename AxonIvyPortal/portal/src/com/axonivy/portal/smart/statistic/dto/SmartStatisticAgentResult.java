package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;

/**
 * The structured result the agent is asked to produce - passed as {@code resultType} to the
 * smart-workflow callable.
 *
 * The coexistence rules between the fields are enforced by the caller, never trusted from the
 * model:
 * <ul>
 * <li>{@code reply} is always rendered as the assistant bubble (a blank one is replaced by a
 * CMS default).</li>
 * <li>A null {@code chart} leaves the current configuration and preview untouched.</li>
 * <li>A non-blank {@code refusalReason} produces a warning bubble and no configuration
 * change.</li>
 * </ul>
 */
public class SmartStatisticAgentResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /** One to three sentences, user-facing, plain text. */
  private String reply;

  private SmartChartSpec chart;

  /**
   * A chart over one of the tagged data providers, filled instead of {@code chart} when the
   * request is about neither tasks nor cases. The two are mutually exclusive and the caller, not
   * the model, enforces that: {@code chart} is tried first, so a model that fills both still gets
   * the task or case chart it was more confident about.
   */
  private ProviderChartProposal customChart;

  /** Set when the request is outside the scope of configuring a chart. */
  private String refusalReason;

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public SmartChartSpec getChart() {
    return chart;
  }

  public void setChart(SmartChartSpec chart) {
    this.chart = chart;
  }

  public ProviderChartProposal getCustomChart() {
    return customChart;
  }

  public void setCustomChart(ProviderChartProposal customChart) {
    this.customChart = customChart;
  }

  public String getRefusalReason() {
    return refusalReason;
  }

  public void setRefusalReason(String refusalReason) {
    this.refusalReason = refusalReason;
  }
}
