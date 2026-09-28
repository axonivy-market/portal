package com.axonivy.portal.smart.statistic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticGridCard;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticGridItem;
import com.axonivy.portal.smart.statistic.service.SmartStatisticAiService;
import com.axonivy.portal.smart.statistic.service.SmartStatisticGridService;

import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The Smart Statistic page: the board of charts, and the conversation above it.
 *
 * Owns what the page is doing - which charts are on it, how they are arranged, whether a question
 * has produced a draft, and which of the two modes is showing. It does not own the chart being
 * edited: that belongs to {@link SmartChartConfigurationBean}, which this bean opens, hands a
 * chart to, and is called back by when the editor is done.
 *
 * View mode renders every chart through initClientCharts(), which fetches each one by id from the
 * REST endpoint. Configuration mode renders exactly one preview canvas through previewChart().
 * The two must never be in the DOM at the same time: previewChart() targets charts[0], so a
 * second .js-statistic-chart would make it draw into the wrong element. That is why the markup
 * uses rendered= rather than hiding a panel with CSS.
 */
@ViewScoped
@Named
public class SmartStatisticManagementBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private SmartChartConfigurationBean chartConfiguration;

  private List<SmartStatisticGridItem> gridItems = new ArrayList<>();
  /** The agent's reading of each chart, kept here rather than on the card: cards are rebuilt per render. */
  private Map<String, String> insights = new HashMap<>();

  private boolean viewMode = true;
  private String statisticApiUri;
  private boolean agentAvailable;

  private String question;
  private String answerQuestion;
  private boolean answerActive;

  private String callbackDashboardId;

  @PostConstruct
  public void init() {
    String statisticId = Attrs.currentContext().getAttribute("#{data.id}", String.class);
    callbackDashboardId = Attrs.currentContext().getAttribute("#{data.callbackDashboardId}", String.class);

    gridItems = SmartStatisticGridService.load();
    statisticApiUri = FacesContext.getCurrentInstance().getExternalContext()
        .getRequestContextPath() + "/api/statistics/data";
    agentAvailable = SmartStatisticAiService.isAvailable();

    // Entering with an explicit chart id means "edit this one", so skip the board.
    if (StringUtils.isNotEmpty(statisticId)) {
      editChart(statisticId);
    }
  }

  // ==========================================================================
  // The two modes
  // ==========================================================================

  public boolean isViewMode() {
    return viewMode;
  }

  /** The editor, for the page to hand to the configuration component. */
  public SmartChartConfigurationBean getChartConfiguration() {
    return chartConfiguration;
  }

  /** Called by the editor when it is finished, whether it saved, cancelled or removed. */
  public void returnToView() {
    viewMode = true;
    answerActive = false;
  }

  /** Leaves the page entirely, as opposed to returning to the board. */
  public void close() {
    backToDashboardDetailsPageIfPossible();
  }

  void backToDashboardDetailsPageIfPossible() {
    if (StringUtils.isNotBlank(callbackDashboardId)) {
      chartConfiguration.navigateToDashboard(callbackDashboardId);
    }
  }

  // ==========================================================================
  // The board
  // ==========================================================================

  public String getStatisticApiUri() {
    return statisticApiUri;
  }

  public boolean isGridEmpty() {
    return CollectionUtils.isEmpty(gridItems);
  }

  /** Everything already kept, pinned or not, so the board header can count the draft against it. */
  public int getSavedChartCount() {
    return gridItems == null ? 0 : gridItems.size();
  }

  /** The draft box is sized from the same numbers it will be stored with, so keeping it is a no-op. */
  public int getDraftWidth() {
    return SmartStatisticGridItem.DRAFT_WIDTH;
  }

  public int getDraftHeight() {
    return SmartStatisticGridItem.DRAFT_HEIGHT;
  }

  public boolean isPinnedEmpty() {
    return SmartStatisticGridService.pinnedCount(gridItems) == 0;
  }

  public boolean isOtherChartsEmpty() {
    return SmartStatisticGridService.unpinned(gridItems).isEmpty();
  }

  /** True once the pinned row is full, so the view can grey out the remaining pin actions. */
  public boolean isPinFull() {
    return SmartStatisticGridService.isPinFull(gridItems);
  }

  public int getMaxPinned() {
    return SmartStatisticGridService.MAX_PINNED;
  }

  public List<SmartStatisticGridCard> getPinnedCards() {
    return toCards(SmartStatisticGridService.pinned(gridItems));
  }

  public List<SmartStatisticGridCard> getOtherCards() {
    return toCards(SmartStatisticGridService.unpinned(gridItems));
  }

  /** Resolves each stored id against the saved charts so the cards can show a real title. */
  private List<SmartStatisticGridCard> toCards(List<SmartStatisticGridItem> items) {
    List<SmartStatisticGridCard> cards = new ArrayList<>();
    for (SmartStatisticGridItem item : items) {
      SmartStatisticGridCard card = new SmartStatisticGridCard(item,
          StatisticService.getInstance().findByIdCustomStatistic(item.getId()));
      card.setInsight(insights.get(item.getId()));
      cards.add(card);
    }
    return cards;
  }

  public void pinChart(String chartId) {
    if (SmartStatisticGridService.isPinFull(gridItems)) {
      chartConfiguration.note("You can pin " + SmartStatisticGridService.MAX_PINNED
          + " charts. Unpin one first.");
      return;
    }
    gridItems = SmartStatisticGridService.pin(gridItems, chartId);
    SmartStatisticGridService.save(gridItems);
  }

  public void unpinChart(String chartId) {
    gridItems = SmartStatisticGridService.unpin(gridItems, chartId);
    SmartStatisticGridService.save(gridItems);
  }

  /** Removes the chart from this user's page. The saved chart itself is left alone. */
  public void removeChart(String chartId) {
    gridItems = SmartStatisticGridService.remove(gridItems, chartId);
    SmartStatisticGridService.save(gridItems);
  }

  /** Not via the editor's save(): that normalises from form state the board has not loaded. */
  public void toggleAxes(String chartId) {
    Statistic chart = StatisticService.getInstance().findByIdCustomStatistic(chartId);
    if (chart == null || ChartType.BAR != chart.getChartType()) {
      return;
    }
    if (chart.getBarChartConfig() == null) {
      chart.setBarChartConfig(new BarChartConfig());
    }
    chart.getBarChartConfig().setSwapAxes(!chart.getBarChartConfig().isSwapAxes());
    storeChart(chartId, chart);
  }

  private static void storeChart(String chartId, Statistic chart) {
    List<Statistic> statistics = StatisticService.getInstance().getCustomStatistic();
    for (int i = 0; i < statistics.size(); i++) {
      if (chartId.equals(statistics.get(i).getId())) {
        statistics.set(i, chart);
      }
    }
    StatisticService.getInstance().saveJsonToVariable(statistics);
  }

  /**
   * Persists the geometry gridstack posted back after a drag or a resize, in the same
   * {@code nodes} request-parameter format the Portal dashboard uses.
   */
  public void saveGridLayout() {
    var posted = DashboardWidgetUtils.getWidgetLayoutFromRequest(
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap());
    gridItems = SmartStatisticGridService.applyGeometry(gridItems, posted);
    SmartStatisticGridService.save(gridItems);
  }

  /**
   * Called by the editor once it has saved, so the chart appears on the board.
   *
   * A draft is already drawn, so it keeps the box it is standing in rather than being appended
   * below at the default size - otherwise keeping it would move and resize it.
   */
  void placeOnBoard(String chartId, ChartType chartType, boolean keptFromDraft) {
    gridItems = keptFromDraft
        ? SmartStatisticGridService.addAtDraftPosition(gridItems, chartId)
        : SmartStatisticGridService.addIfAbsent(gridItems, chartId, chartType);
    SmartStatisticGridService.save(gridItems);
    answerQuestion = null;
  }

  // ==========================================================================
  // Opening the editor
  // ==========================================================================

  /** Leaves the editor ready for a brand new chart. */
  public void addChart() {
    answerActive = false;
    chartConfiguration.openNew();
    viewMode = false;
  }

  public void editChart(String chartId) {
    Statistic existing = StatisticService.getInstance().findByIdCustomStatistic(chartId);
    if (existing == null) {
      // The chart was deleted from the shared store after it was added to this board.
      removeChart(chartId);
      return;
    }
    chartConfiguration.openExisting(existing, chartId);
    viewMode = false;
  }

  // ==========================================================================
  // The conversation
  // ==========================================================================

  /** Asks the agent what the chart's current numbers mean. */
  public void generateInsight(String chartId) {
    if (StringUtils.isBlank(chartId) || !agentAvailable) {
      return;
    }
    Statistic chart = StatisticService.getInstance().findByIdCustomStatistic(chartId);
    if (chart == null) {
      return;
    }
    String text = SmartStatisticAiService.insight(chart, chartConfiguration.getSupportedUserLanguage());
    insights.put(chartId, text);
    PrimeFaces.current().ajax().addCallbackParam("insightText", text);
  }

  /**
   * Answers a question typed above the charts by proposing a brand new one.
   *
   * A question is not a refinement of whatever was configured last, so the editor starts from a
   * fresh Statistic. The proposal is rendered unsaved: nothing reaches
   * {@code Portal.CustomStatistic} until the user keeps it.
   */
  public void askQuestion() {
    answerActive = false;
    if (StringUtils.isBlank(question)) {
      chartConfiguration.clearNote();
      return;
    }
    if (!chartConfiguration.proposeFresh(question)) {
      return;
    }
    answerQuestion = question;
    answerActive = true;
    question = StringUtils.EMPTY;
  }

  /** Keeps the proposed chart: saves it and puts it on the board, exactly like the editor does. */
  public void keepAnswer() {
    chartConfiguration.save();
  }

  /** Opens the proposal in the full editor so it can be adjusted by hand. */
  public void refineAnswer() {
    answerActive = false;
    chartConfiguration.openProposal();
    viewMode = false;
  }

  public void dismissAnswer() {
    answerActive = false;
    answerQuestion = null;
    chartConfiguration.discard();
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswerQuestion() {
    return answerQuestion;
  }

  public boolean isAnswerActive() {
    return answerActive;
  }

  /** The board steps back while a draft is sitting on it. */
  public String getPageStyleClass() {
    return isAnswerActive() ? "smart-page--drafting" : StringUtils.EMPTY;
  }

  /** Suggestion chips fold away once a first question has been answered. */
  public String getSuggestionChipsStyleClass() {
    return isAnswerActive() ? "smart-ask__chips--collapsed" : StringUtils.EMPTY;
  }

  /**
   * Example questions offered under the ask bar.
   *
   * Each one is deliberately answerable from the charts Portal ships by default, so a first-time
   * user cannot pick a suggestion the agent then has to refuse. They double as a hint about the
   * kind of question this page understands.
   */
  public List<String> getSuggestions() {
    return List.of(
        "How many of my tasks are due today?",
        "Show the tasks I can work on, by priority",
        "How many cases are still running?",
        "Which case categories take longest on average?");
  }

  public boolean isAgentAvailable() {
    return agentAvailable;
  }

  // ==========================================================================
  // Read through to the chart being drafted
  //
  // The draft card shows the proposal the editor is holding. These are pass-throughs rather than
  // state of their own, so the board and the editor can never disagree about it.
  // ==========================================================================

  public Statistic getStatistic() {
    return chartConfiguration.getStatistic();
  }

  public String getAnswerScope() {
    return chartConfiguration.getAnswerScope();
  }

  public String getAssistantNote() {
    return chartConfiguration.getAssistantNote();
  }

  public List<String> getAssistantWarnings() {
    return chartConfiguration.getAssistantWarnings();
  }

  public boolean isEditMode() {
    return chartConfiguration.isEditMode();
  }

  public void getPreviewData() {
    chartConfiguration.getPreviewData();
  }

  public void resetConditionBasedColoring() {
    chartConfiguration.resetConditionBasedColoring();
  }

  /** Read by the page's script block, which hands it to the chart renderer. */
  public Locale getSupportedUserLanguage() {
    return chartConfiguration.getSupportedUserLanguage();
  }
}
