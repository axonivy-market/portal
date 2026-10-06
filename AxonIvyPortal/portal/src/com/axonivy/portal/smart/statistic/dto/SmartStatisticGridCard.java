package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.enums.statistic.CardLayout;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.smart.statistic.service.SmartStatisticScopeService;

/**
 * One card of the Smart Statistic page: the stored entry plus the chart it points at.
 *
 * The chart may be missing when it was deleted from {@code Portal.CustomStatistic} after being
 * added to someone's page, so callers must tolerate a null {@link #getStatistic()}.
 */
public class SmartStatisticGridCard implements Serializable {

  private static final long serialVersionUID = 1L;

  private final SmartStatisticGridItem item;
  private final Statistic statistic;
  /**
   * The chart's caption, resolved once at construction from its own configuration - the view
   * reads it per card on every render. This stands in for the chart's stored description on the
   * Smart Statistic page: mechanical and always accurate, rather than prose the agent wrote once
   * and that can drift out of sync with the chart it describes.
   */
  private final String description;
  /** Held by the bean, not by the card: the card is rebuilt on every render. */
  private String insight;

  public SmartStatisticGridCard(SmartStatisticGridItem item, Statistic statistic) {
    this.item = item;
    this.statistic = statistic;
    this.description = SmartStatisticScopeService.describe(statistic);
  }

  public SmartStatisticGridItem getItem() {
    return item;
  }

  public Statistic getStatistic() {
    return statistic;
  }

  public String getChartId() {
    return item == null ? StringUtils.EMPTY : StringUtils.defaultString(item.getId());
  }

  public boolean isPinned() {
    return item != null && item.isPinned();
  }

  public boolean isAvailable() {
    return statistic != null;
  }

  public String getTitle() {
    return statistic == null ? StringUtils.EMPTY : StringUtils.defaultString(statistic.getName());
  }

  public String getDescription() {
    return description;
  }

  /** The agent's reading of this chart's numbers. Blank until the user asks for one. */
  public String getInsight() {
    return StringUtils.defaultString(insight);
  }

  public void setInsight(String insight) {
    this.insight = insight;
  }

  /** Width on the 12-column board. */
  public int getColumns() {
    return item == null ? SmartStatisticGridItem.DEFAULT_WIDTH : item.getWidth();
  }

  /**
   * Rendered server-side from the stored width so the card is arranged on first paint. The browser
   * takes over from there: a card that is resized, or a pinned card in a row that reflows, is
   * measured and re-tagged without a round trip.
   */
  public CardLayout getLayout() {
    return CardLayout.forColumns(getColumns());
  }

  public boolean isInsightShown() {
    return StringUtils.isNotBlank(insight);
  }

  public boolean isAnyTextShown() {
    return isInsightShown();
  }

  public boolean isBarChart() {
    return statistic != null && ChartType.BAR == statistic.getChartType();
  }

  public boolean isSwapAxes() {
    return isBarChart() && statistic.getBarChartConfig() != null
        && statistic.getBarChartConfig().isSwapAxes();
  }

  public String getIcon() {
    return statistic == null || StringUtils.isBlank(statistic.getIcon())
        ? "ti ti-chart-pie"
        : statistic.getIcon();
  }

  /**
   * Refresh once there is a reading to replace, Generate before that. One label for both the
   * tooltip and the accessible name, so the two can never disagree.
   */
  public String getInsightActionLabel() {
    return isInsightShown() ? "Read these numbers again" : "Explain these numbers";
  }

  /** Points the way the bars will run after the swap, not the way they run now. */
  public String getSwapAxesIcon() {
    return isSwapAxes() ? "ti ti-arrows-vertical" : "ti ti-arrows-horizontal";
  }

  /** The body only reserves room for the texts once there is something to put in it. */
  public String getLayoutStyleClass() {
    return isAnyTextShown() ? "smart-layout--texts" : StringUtils.EMPTY;
  }

  /** The other half of {@link #getLayoutStyleClass()}: the texts hide until they have content. */
  public String getTextsStyleClass() {
    return isAnyTextShown() ? StringUtils.EMPTY : "smart-layout__texts--hidden";
  }

  /**
   * A chart that could still be pinned: on the board and not pinned already. Whether the pinned
   * row has room is the page's business, not the card's, so it is not asked here.
   */
  public boolean isPinnable() {
    return !isPinned() && isAvailable();
  }
}
