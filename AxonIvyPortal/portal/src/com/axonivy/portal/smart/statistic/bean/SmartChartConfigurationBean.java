package com.axonivy.portal.smart.statistic.bean;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.bean.StatisticConfigurationBean.DEFAULT_COLORS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.bo.ThresholdStatisticChart;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.enums.statistic.ConditionBasedColoringScope;
import com.axonivy.portal.enums.statistic.OperatorFieldStatistic;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.service.multilanguage.AbstractMultilanguageService;
import com.axonivy.portal.service.multilanguage.StatisticDescriptionMultilanguageService;
import com.axonivy.portal.service.multilanguage.StatisticNameMultilanguageService;
import com.axonivy.portal.smart.statistic.dto.SmartAgentTurn;
import com.axonivy.portal.smart.statistic.dto.SmartChartSpec;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticMappingResult;
import com.axonivy.portal.smart.statistic.provider.ProviderChartMapper;
import com.axonivy.portal.smart.statistic.provider.ProviderChartService;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartForm;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;
import com.axonivy.portal.smart.statistic.service.ChartAppearanceService;
import com.axonivy.portal.smart.statistic.service.SmartChartResultService;
import com.axonivy.portal.smart.statistic.service.SmartChartSpecMapper;
import com.axonivy.portal.smart.statistic.service.SmartStatisticAiService;
import com.axonivy.portal.smart.statistic.service.SmartStatisticScopeService;
import com.axonivy.portal.smart.statistic.service.SmartStatisticVocabularyService;
import com.axonivy.portal.smart.statistic.service.StatisticFilterFieldService;
import com.axonivy.portal.smart.statistic.service.StatisticLifecycleService;
import com.axonivy.portal.smart.statistic.service.StatisticPermissionService;
import com.axonivy.portal.util.filter.field.FilterField;

import ch.ivy.addon.portal.generic.bean.IMultiLanguage;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ViewScoped
@Named
public class SmartChartConfigurationBean implements Serializable, IMultiLanguage {

  private static final String DEFAULT_THRESHOLD_BACKGROUND_COLOR = "#6299f7";
  private static final long serialVersionUID = 1L;
  /** The page that opened this editor; told when the editor is done. */
  @Inject
  private SmartStatisticManagementBean management;

  private Statistic statistic;
  private String statisticId;
  private List<DisplayName> xTitles;
  private String xTitle;
  private List<DisplayName> yTitles;
  private String yTitle;
  private List<String> selectedPermissions;
  private List<String> backgroundColors;
  private boolean isEditMode;
  /** Whether the editor has a chart worth drawing the moment it opens; read by the view. */
  private boolean previewOnOpen;
  private boolean refreshIntervalEnabled;
  private List<FilterField> filterFields;
  private String currentCustomFieldDescription;
  private boolean isDateTimeSelected;
  private AggregationInterval aggregationInterval;
  private List<String> categoryData;
  private boolean isCategoryDataAvailable;

  // --- natural-language layer -------------------------------------------------------
  private String prompt;
  private String assistantNote;
  private List<String> assistantWarnings = new ArrayList<>();
  private boolean agentAvailable;
  private String systemMessage;
  private SmartChartSpec currentSpec;
  /** The provider-backed counterpart of {@link #currentSpec}, replayed the same way. */
  private ProviderChartProposal currentCustomChart;

  // --- data source ------------------------------------------------------------------
  /** "TASK", "CASE", or the provider prefix plus its name. A String, so the menu needs no converter. */
  private String dataSource;
  /** Built once per view: providerStarts() searches the process model. */
  private List<SelectItem> dataSources;
  /**
   * The editable provider spec. Kept off the Statistic because an agent turn replaces that object
   * wholesale, which would leave the form bound to a discarded instance.
   */
  private ProviderChartForm providerForm = new ProviderChartForm();

  private StatisticNameMultilanguageService nameMultilanguageService;
  private StatisticDescriptionMultilanguageService descriptionMultilanguageService;
  // StatisticX/YTitleMultilanguageService are typed to the classic bean, so this page supplies
  // its own adapters rather than widening shared production code.
  private AbstractMultilanguageService xTitleMultilanguageService;
  private AbstractMultilanguageService yTitleMultilanguageService;

  /**
   * Only what a chart-less page needs. The form itself is built when the page actually opens a
   * chart - see {@link #openNew()} and {@link #openExisting(Statistic, String)} - so arriving at
   * the board costs nothing.
   */
  @PostConstruct
  public void init() {
    agentAvailable = SmartStatisticAiService.isAvailable();
    if (agentAvailable) {
      systemMessage = SmartStatisticAiService.systemMessage(getSupportedUserLanguage());
    }
    initNewStatistic();
  }

  // ==========================================================================
  // Opened by the page
  // ==========================================================================

  /** True while the editor is the mode the page is showing. */
  public boolean isOpen() {
    return !management.isViewMode();
  }

  /**
   * True when the editor was opened onto a chart that already describes something.
   *
   * The preview is drawn by an AJAX round trip, and the page can only start one once the modal is
   * in the DOM - so the markup carries this out to the browser and the mode-switch handler asks
   * for the preview when it is set. A blank new chart is deliberately left undrawn, exactly as the
   * classic configurator's {@code autoRun} does.
   */
  public boolean isPreviewOnOpen() {
    return previewOnOpen;
  }

  /** A brand new chart. */
  public void openNew() {
    statisticId = null;
    statistic = null;
    isEditMode = false;
    previewOnOpen = false;
    initNewStatistic();
    currentSpec = null;
    currentCustomChart = null;
    prepareForm();
  }

  /** An existing chart, to be edited in place. */
  public void openExisting(Statistic chart, String chartId) {
    statistic = chart;
    statisticId = chartId;
    previewOnOpen = true;
    initExistedStatistic();
    prepareForm();
  }

  /** The proposal already on the board, opened for adjustment by hand. */
  public void openProposal() {
    isEditMode = false;
    previewOnOpen = true;
    prepareForm();
  }

  /**
   * Answers a question with a brand new chart.
   *
   * A question is not a refinement of whatever was configured last, so this starts from a fresh
   * Statistic and a null spec. The proposal is written into {@link #statistic} and rendered
   * unsaved: nothing reaches {@code Portal.CustomStatistic} until the user keeps it.
   *
   * @return true when a usable chart configuration was produced
   */
  public boolean proposeFresh(String text) {
    statisticId = null;
    statistic = null;
    isEditMode = false;
    initNewStatistic();
    currentSpec = null;
    currentCustomChart = null;
    if (!runAgent(text)) {
      return false;
    }
    getPreviewData();
    return true;
  }

  /** Throws the proposal away and leaves a blank chart behind it. */
  public void discard() {
    clearNote();
    statistic = null;
    initNewStatistic();
    currentSpec = null;
    currentCustomChart = null;
  }

  /** Lets the page put its own message where the editor's notes appear. */
  public void note(String message) {
    assistantNote = message;
  }

  public void clearNote() {
    assistantNote = null;
    assistantWarnings = new ArrayList<>();
  }

  private void initMultilanguageServices() {
    nameMultilanguageService = new StatisticNameMultilanguageService(statistic);
    descriptionMultilanguageService = new StatisticDescriptionMultilanguageService(statistic);
    xTitleMultilanguageService = new AbstractMultilanguageService() {
      @Override
      protected String getValue() {
        return getxTitle();
      }

      @Override
      protected void setValue(String value) {
        setxTitle(value);
      }

      @Override
      protected List<DisplayName> getValues() {
        return getConfigxTitles();
      }
    };
    yTitleMultilanguageService = new AbstractMultilanguageService() {
      @Override
      protected String getValue() {
        return getyTitle();
      }

      @Override
      protected void setValue(String value) {
        setyTitle(value);
      }

      @Override
      protected List<DisplayName> getValues() {
        return getConfigyTitles();
      }
    };
  }

  private void initExistedStatistic() {
    isEditMode = true;
    StatisticLifecycleService.ensureFormDefaults(statistic);
    StatisticLifecycleService.ensureDefaultPermission(statistic);
    if (statistic.getRefreshInterval() != null && statistic.getRefreshInterval() >= StatisticLifecycleService.MIN_REFRESH_INTERVAL_IN_SECONDS) {
      refreshIntervalEnabled = true;
    }
    ColumnChartConfig columnConfig = ChartAppearanceService.columnConfigOf(statistic);
    if (columnConfig != null) {
      xTitles = columnConfig.getxTitles() != null ? columnConfig.getxTitles() : new ArrayList<>();
      yTitles = columnConfig.getyTitles() != null ? columnConfig.getyTitles() : new ArrayList<>();
    }
    List<String> storedColors = ChartAppearanceService.storedColors(statistic);
    backgroundColors = storedColors.isEmpty() ? new ArrayList<>(DEFAULT_COLORS) : storedColors;
    if(statistic.getStatisticAggregation() != null) {
      StatisticService statisticService = StatisticService.getInstance();
      StatisticAggregation agg = statistic.getStatisticAggregation();
      statisticService.convertAggregatesFromChartAggregation(statistic);
      if(agg.getType() == DashboardColumnType.CUSTOM) {
        seedCustomFieldIfUnset();

        findCustomFieldMeta().ifPresent(meta -> {
          this.currentCustomFieldDescription = meta.description();
        });
      }
      this.setDateTimeSelected(agg.getInterval() != null);
      this.aggregationInterval = agg.getInterval(); 
    } else {
      StatisticAggregation statisticAggregation = new StatisticAggregation();
      statisticAggregation.setField(AggregationField.PRIORITY.getName());
      statistic.setStatisticAggregation(statisticAggregation);
    }
    if (statistic.getConditionBasedColoringEnabled()) {
      fetchCategoryData();
      if (CollectionUtils.isEmpty(categoryData)) {
        updateIsCategoryDataAvailable();
        List<String> targetValuesFromThresholds = statistic.getThresholdStatisticCharts().stream().map(ThresholdStatisticChart::getTargetValue).filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(targetValuesFromThresholds)) {
          setCategoryData(targetValuesFromThresholds);
        } else {
          setCategoryData(new ArrayList<String>());
          statistic.setThresholdStatisticCharts(new ArrayList<>());
        }
      }
    }
  }

  private void initNewStatistic() {
    statistic = StatisticLifecycleService.newDraft();
    xTitles = new ArrayList<>();
    yTitles = new ArrayList<>();
    backgroundColors = new ArrayList<>(DEFAULT_COLORS);
    refreshIntervalEnabled = false;
  }

  private void initFilterFields() {
    filterFields = StatisticFilterFieldService.catalogueFor(statistic);
  }

  private void initFilters() {
    StatisticFilterFieldService.bindExisting(statistic, filterFields);
  }

  private void populateBackgroundColorsIfMissing() {
    ChartAppearanceService.padColors(backgroundColors);
  }

  public Statistic getStatistic() {
    return statistic;
  }

  public void setStatistic(Statistic statistic) {
    this.statistic = statistic;
  }

  public String getStatisticId() {
    return statisticId;
  }

  public void setStatisticId(String statisticId) {
    this.statisticId = statisticId;
  }

  public void save() {
    Optional<String> intervalError = StatisticLifecycleService.refreshIntervalError(statistic, refreshIntervalEnabled);
    if (intervalError.isPresent()) {
      reportInvalid(intervalError.get());
      return;
    }
    if (statistic.isProviderBacked() && !applyProviderForm()) {
      return;
    }
    // Read before the page clears it: a draft is already drawn on the board.
    boolean keptFromDraft = management.isAnswerActive();
    // All four normalise the task and case aggregation - custom fields, date intervals, threshold
    // colouring - none of which a provider-backed chart has. Its aggregation is synthetic and must
    // survive untouched, because statistic.js reads it at render time.
    if (!statistic.isProviderBacked()) {
      handleCustomFieldAggregation();
      handleAggregateWithDateTimeInterval();
      StatisticLifecycleService.applyColoringScope(statistic);
      if (isCustomFieldsSelected()) {
        statistic.getStatisticAggregation().setField(statistic.getStatisticAggregation().getCustomFieldValue());
      }
    }

    syncUIConfigWithChartConfig();
    StatisticLifecycleService.prepare(statistic, refreshIntervalEnabled);
    nameMultilanguageService.initMultipleLanguagesForName(statistic.getName());
    descriptionMultilanguageService.initMultipleLanguagesForName(statistic.getDescription());
    if (BAR == statistic.getChartType() || LINE == statistic.getChartType()) {
      xTitleMultilanguageService.initMultipleLanguagesForName(getxTitle());
      yTitleMultilanguageService.initMultipleLanguagesForName(getyTitle());
    }
    StatisticLifecycleService.store(statistic);
    // Keeping a chart puts it on this user's page and returns there, instead of leaving the
    // page the way the classic configurator does. Shared by the form's Create button and by
    // "Keep" on an answer card, so a proposal is saved through exactly the same validation.
    // A draft is already drawn on the board, so it keeps the box it is standing in rather than
    // being appended below at the default size - otherwise keeping it moves and resizes it.
    management.placeOnBoard(statistic.getId(), statistic.getChartType(), keptFromDraft);
    management.returnToView();
  }
  
  public boolean isOutdatedChart(Statistic chart) {
    return StatisticLifecycleService.isOutdated(chart);
  }

  private void syncUIConfigWithChartConfig() {
    StatisticPermissionService.dedupeAndApply(statistic);

    // Takes its own copy of the colours, so the picker's list keeps its empty slots and stays
    // safe to go on editing - it is no longer the list the chart will be saved with.
    ChartAppearanceService.updateTitlesAndColors(statistic, xTitles, yTitles, backgroundColors);
  }

  public List<ChartType> getAllChartTypes() {
    return Arrays.asList(ChartType.values());
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    return StatisticPermissionService.completeRoles(selectedPermissions, query);
  }
  
  public void onSelectPermissionForDashboard(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    selectedPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForDashboard(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    selectedPermissions.remove(selectedItem.getName());
  }

  public void getPreviewData() {
    if (statistic.isProviderBacked()) {
      // No jsonResponse callback param on failure, so handlePreviewChart leaves the last chart up.
      if (!applyProviderForm()) {
        return;
      }
    } else {
      handleCustomFieldAggregation();
      handleAggregateWithDateTimeInterval();
    }
    syncUIConfigWithChartConfig();
    StatisticLifecycleService.cleanUpFilter(statistic);

    PrimeFaces.current().ajax().addCallbackParam("jsonResponse", SmartChartResultService.previewJson(statistic));
    populateBackgroundColorsIfMissing();
  }

  public void fetchCategoryData() {
    handleCustomFieldAggregation();
    handleAggregateWithDateTimeInterval();
    StatisticLifecycleService.cleanUpFilter(statistic);

    setCategoryData(SmartChartResultService.categoryValues(statistic, isDateTimeSelected));
    // The date-bucketed path deliberately leaves the availability flag as it was.
    if (!isDateTimeSelected) {
      updateIsCategoryDataAvailable();
    }
  }
  
  public void onToggleConditionBasedColoring() {
    if (statistic.getConditionBasedColoringEnabled()) {
      StatisticLifecycleService.resetColoring(statistic);
    }
  }
  
  private void updateIsCategoryDataAvailable() {
    if (CollectionUtils.isEmpty(categoryData)) {
      setIsCategoryDataAvailable(false);
    } else {
      setIsCategoryDataAvailable(true);
    }
  }
  
  public void resetConditionBasedColoring() {
    if (statistic.getConditionBasedColoringEnabled()) {
      statistic.setConditionBasedColoringEnabled(false);
    }
  }

  public void updateNameForCurrentLanguage() {
    nameMultilanguageService.updateNameForCurrentLanguage();
  }

  public void updateDescriptionForCurrentLanguage() {
    descriptionMultilanguageService.updateNameForCurrentLanguage();
  }

  public void updateXTitleForCurrentLanguage() {
    xTitleMultilanguageService.updateNameForCurrentLanguage();
  }

  public void updateYTitleForCurrentLanguage() {
    yTitleMultilanguageService.updateNameForCurrentLanguage();
  }

  public void updateNameByLocale() {
    nameMultilanguageService.updateNameByLocale();
  }

  public void updateDescriptionByLocale() {
    descriptionMultilanguageService.updateNameByLocale();
  }

  public void updateXTitleByLocale() {
    xTitleMultilanguageService.updateNameByLocale();
  }

  public void updateYTitleByLocale() {
    yTitleMultilanguageService.updateNameByLocale();
  }

  public List<DisplayName> getNames() {
    return nameMultilanguageService.getNames();
  }

  public List<DisplayName> getDescriptions() {
    return descriptionMultilanguageService.getNames();
  }

  public String getxTitle() {
    return LanguageUtils.getLocalizedName(xTitles, xTitle);
  }

  public void setxTitle(String xTitle) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(xTitles, xTitle);
    this.xTitles = nameResult.names();
    this.xTitle = nameResult.name();
  }

  public List<DisplayName> getxTitles() {
    return xTitleMultilanguageService.getNames();
  }

  public List<DisplayName> getConfigxTitles() {
    return xTitles;
  }

  public String getyTitle() {
    return LanguageUtils.getLocalizedName(yTitles, yTitle);
  }

  public void setyTitle(String yTitle) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(yTitles, yTitle);
    this.yTitles = nameResult.names();
    this.yTitle = nameResult.name();
  }

  public List<DisplayName> getyTitles() {
    return yTitleMultilanguageService.getNames();
  }

  public List<DisplayName> getConfigyTitles() {
    return yTitles;
  }

  public List<String> getBackgroundColors() {
    return backgroundColors;
  }

  public boolean isEditMode() {
    return isEditMode;
  }

  /** Save for a chart that already exists, Create for one that does not. */
  public String getSaveActionLabel() {
    return Ivy.cms().co(isEditMode()
        ? "/Labels/Save"
        : "/Dialogs/com/axonivy/portal/page/StatisticConfiguration/Create");
  }

  public void setEditMode(boolean isEditMode) {
    this.isEditMode = isEditMode;
  }

  public boolean isRefreshIntervalEnabled() {
    return refreshIntervalEnabled;
  }

  public void setRefreshIntervalEnabled(boolean refreshIntervalEnabled) {
    this.refreshIntervalEnabled = refreshIntervalEnabled;
  }

  public void onToggleRefreshInterval() {
    Integer refreshIntervalInSeconds = null;
    if (refreshIntervalEnabled) {
      refreshIntervalInSeconds = StatisticLifecycleService.DEFAULT_REFRESH_INTERVAL_IN_SECONDS;
    }
    statistic.setRefreshInterval(refreshIntervalInSeconds);
  }

  private void initPermissions() {
    StatisticPermissionService.applyDtos(statistic);
    selectedPermissions = StatisticPermissionService.selectedNames(statistic);
  }

  /** Abandons the form and returns to the grid; {@link #close()} leaves the page. */
  public void cancel() {
    management.returnToView();
  }

  /** Removes the chart being edited from this user's page, then returns to the grid. */
  public void removeCurrentChart() {
    management.removeChart(statisticId);
    management.returnToView();
  }

  public int getMaxRefreshIntervalInSeconds() {
    return StatisticLifecycleService.MAX_REFRESH_INTERVAL_IN_SECONDS;
  }

  public int getMinRefreshIntervalInSeconds() {
    return StatisticLifecycleService.MIN_REFRESH_INTERVAL_IN_SECONDS;
  }

  /** Marks the submit failed and puts the reason where the form shows its messages. */
  private void reportInvalid(String reason) {
    FacesContext.getCurrentInstance().validationFailed();
    FacesContext.getCurrentInstance().addMessage(null,
        FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR, reason, ""));
  }
  
  public List<AggregationField> getAllAvailableAggregationField() {
    return SmartStatisticVocabularyService.availableFormFields(statistic.getChartTarget(), statistic.getChartType());
  }
  
  public List<OperatorFieldStatistic> getAllOperatorField() {
    return OperatorFieldStatistic.OPERATORS.stream().toList();
  }
  
  public boolean isCustomFieldsSelected() {
    return SmartStatisticVocabularyService.isCustomField(statistic.getStatisticAggregation().getField());
  }
  
  private void resetCustomFieldAndDateTimeInterval() {
      statistic.getStatisticAggregation().setCustomFieldValue(null);
      statistic.getStatisticAggregation().setType(DashboardColumnType.STANDARD);
      if(!isDateTimeSelected) {
        statistic.getStatisticAggregation().setInterval(null);
        this.aggregationInterval = null;
      }
    }
  
  private void handleCustomFieldAggregation() {
    if (isCustomFieldsSelected()) {
      return;
    }

    resetCustomFieldAndDateTimeInterval();
  }
  public void handleAggregateWithDateTimeInterval() {
    if (aggregationInterval == null) {
      statistic.getStatisticAggregation().setInterval(null);
      return;
    }
    if (!SmartStatisticVocabularyService.looksLikeTimestampField(statistic.getStatisticAggregation().getField())
        && !isDateTimeSelected) {
      this.setAggregationInterval(null);
      statistic.getStatisticAggregation().setInterval(null);
      return;
    }

    statistic.getStatisticAggregation().setInterval(aggregationInterval);
  }

  public String getCurrentCustomFieldDescription() {
    return currentCustomFieldDescription;
  }

  public void setCurrentCustomFieldDescription(String currentCustomFieldDescription) {
    this.currentCustomFieldDescription = currentCustomFieldDescription;
  }

  public boolean isDateTimeSelected() {
    return isDateTimeSelected;
  }

  public void setDateTimeSelected(boolean isDateTimeSelected) {
    this.isDateTimeSelected = isDateTimeSelected;
  }
  
  public void onSelectAggregationField() {
    this.setDateTimeSelected(
        SmartStatisticVocabularyService.looksLikeTimestampField(statistic.getStatisticAggregation().getField()));
  }

  public void onSelectCustomField() {
    statistic.getStatisticAggregation().setType(DashboardColumnType.CUSTOM);

    findCustomFieldMeta().ifPresent(meta -> {
      this.currentCustomFieldDescription = meta.description();
      this.isDateTimeSelected = meta.type() == CustomFieldType.TIMESTAMP;
    });
  }

  public Optional<ICustomFieldMeta> findCustomFieldMeta() {
    return SmartStatisticVocabularyService.findCustomField(statistic.getChartTarget(),
        statistic.getStatisticAggregation().getCustomFieldValue());
  }

  public List<AggregationInterval> getAvailableIntervals() {
    return SmartStatisticVocabularyService.intervals();
  }

  public void onSelectInterval() {
    statistic.getStatisticAggregation().setInterval(aggregationInterval);
  }

  /**
   * The group-by options, and - when nothing is picked yet - the first of them.
   *
   * The seeding is deliberate and load-bearing: nothing else fills customFieldValue when the user
   * first switches the aggregation to a custom field, and this getter is also what JSF evaluates
   * during postback validation, which needs the submitted value to be one of these options.
   */
  public List<String> getCustomFieldNames() {
    List<String> names = SmartStatisticVocabularyService.groupableCustomFieldNames(statistic.getChartTarget());
    seedCustomFieldIfUnset();
    return names;
  }

  /**
   * Seeded from the very list the dropdown shows, and from its first entry.
   *
   * Both halves of that matter. Seeding out of every custom field the target declares could pick
   * a numeric one, which is not groupable and so is not among the options - leaving JSF to reject
   * the submitted value as invalid. And {@link ICustomFieldMeta} hands back a Set whose iteration
   * order is undefined, so "the first one" is only a real position once the list is sorted, which
   * {@code groupableCustomFieldNames} is.
   */
  private void seedCustomFieldIfUnset() {
    StatisticAggregation aggregation = statistic.getStatisticAggregation();
    if (aggregation.getCustomFieldValue() != null) {
      return;
    }
    ChartTarget target = statistic.getChartTarget();
    List<String> groupable = SmartStatisticVocabularyService.groupableCustomFieldNames(target);
    // An installation may declare none at all, and the aggregation dropdown offers "custom field"
    // regardless - so there is nothing to seed rather than a first entry to take.
    if (groupable.isEmpty()) {
      return;
    }
    String first = groupable.get(0);
    aggregation.setCustomFieldValue(first);
    aggregation.setType(DashboardColumnType.CUSTOM);
    setCurrentCustomFieldDescription(SmartStatisticVocabularyService.findCustomField(target, first)
        .map(ICustomFieldMeta::description).orElse(null));
  }

  public List<String> getNumericCustomFieldNames() {
    return SmartStatisticVocabularyService.numericCustomFieldNames(statistic.getChartTarget());
  }

  public List<SelectItem> getAggregationMethods() {
    return SmartStatisticVocabularyService.aggregationMethods();
  }

  public void onSelectChartType(ChartType newChartType) {
    if (statistic.isProviderBacked()) {
      // resetAggregateValues() would overwrite the synthetic aggregation statistic.js renders from.
      ProviderChartService.normalise(providerForm, statistic.getChartType());
      return;
    }
    if (ChartType.NUMBER == statistic.getChartType()) {
      resetAggregateValues();
      resetCustomFieldAndDateTimeInterval();
      this.setDateTimeSelected(false);
    }
    resetConditionBasedColoring();
  }

  public void onSelectColoringScope() {
    statistic.setThresholdStatisticCharts(new ArrayList<>());
    if (ConditionBasedColoringScope.SPECIFIC.equals(statistic.getConditionBasedColoringScope())) {
      fetchCategoryData();
      updateIsCategoryDataAvailable();
    }
  }
    
  public void resetAggregateValues() {
    statistic.getStatisticAggregation().setField(AggregationField.PRIORITY.getName());
    this.currentCustomFieldDescription = null;
    statistic.getStatisticAggregation().setInterval(null);
  }

  public String displayThresholdTargetValue(String value) {
    return SmartChartResultService.formatCategoryKey(value, isDateTimeSelected);
  }

  public List<FilterField> getFilterFields() {
    return filterFields;
  }

  public void setFilterFields(List<FilterField> filterFields) {
    this.filterFields = filterFields;
  }
  
  public void onSelectFilter(DashboardFilter filter) {
    String field = Optional.ofNullable(filter).map(DashboardFilter::getFilterField).map(FilterField::getName)
        .orElse(StringUtils.EMPTY);

    FilterField filterField = StatisticFilterFieldService.findBy(statistic.getChartTarget(), field);
    if (filterField != null && filterField.getName().contentEquals(BaseFilter.DEFAULT)) {
      filterField.addNewFilter(filter);
      return;
    }

    filter.getFilterField().addNewFilter(filter);
    resetConditionBasedColoring();
  }
  
  public void addNewFilter() {
    if (statistic.getFilters() == null) {
      statistic.setFilters(new ArrayList<>());
    }

    DashboardFilter newFilter = new DashboardFilter();
    statistic.getFilters().add(newFilter);
  }
  
  public void addNewThreshold() {
    if (CollectionUtils.isEmpty(statistic.getThresholdStatisticCharts())) {
      statistic.setThresholdStatisticCharts(new ArrayList<>());
    }
    
    ThresholdStatisticChart newThreshold = new ThresholdStatisticChart();
    newThreshold.setBackgroundColor(DEFAULT_THRESHOLD_BACKGROUND_COLOR);
    statistic.getThresholdStatisticCharts().add(newThreshold);
    }
  
  public void removeFilter(DashboardFilter filter) {
    statistic.getFilters().remove(filter);
  }
  
  public void removeThreshold(ThresholdStatisticChart threshod) {
    if (CollectionUtils.isEmpty(statistic.getThresholdStatisticCharts())) {
      return;
    }
    statistic.getThresholdStatisticCharts().remove(threshod);
  }
  
  public List<SecurityMemberDTO> completeOwners(String query) {
    return StatisticPermissionService.completeMembers(query);
  }

  public AggregationInterval getAggregationInterval() {
    return aggregationInterval;
  }

  public void setAggregationInterval(AggregationInterval aggregationInterval) {
    this.aggregationInterval = aggregationInterval;
  }

  public List<SecurityMemberDTO> completeCreators(String query) {
    return StatisticPermissionService.completeUsers(query);
  }

  public void setCategoryData(List<String> data) {
    this.categoryData = data;
  }
  
  public List<String> getCategoryData() {
    return this.categoryData;
  }

  public boolean getIsCategoryDataAvailable() {
    return isCategoryDataAvailable;
  }

  public void setIsCategoryDataAvailable(boolean isCategoryDataAvailable) {
    this.isCategoryDataAvailable = isCategoryDataAvailable;
  }
  
  public List<ConditionBasedColoringScope> getConditionBasedColoringScopes() {
    return ConditionBasedColoringScope.SCOPES.stream().collect(Collectors.toList());
  }

  // ==========================================================================
  // Personal chart grid
  //
  // View mode renders every chart through initClientCharts(), which fetches each one by id
  // from the REST endpoint. Configuration mode renders exactly one preview canvas through
  // previewChart(). The two must never be in the DOM at the same time: previewChart() targets
  // charts[0], so a second .js-statistic-chart would make it draw into the wrong element.
  // That is why the markup uses rendered= rather than hiding a panel with CSS.
  // ==========================================================================

  /** Builds every control the form binds to, for whichever chart is now loaded. */
  private void prepareForm() {
    populateBackgroundColorsIfMissing();
    initPermissions();
    initMultilanguageServices();
    initFilterFields();
    initFilters();
    prompt = StringUtils.EMPTY;
    clearNote();
    currentSpec = toSpecIfTaskOrCase(statistic);
    currentCustomChart = ProviderChartMapper.toProposal(statistic);
    syncSourceStateFromStatistic();
  }

  /**
   * {@code toSpec} reads the chart target and aggregation to rebuild the agent's vocabulary, and a
   * provider-backed chart has neither. Those charts replay through {@link #currentCustomChart}.
   */
  private static SmartChartSpec toSpecIfTaskOrCase(Statistic chart) {
    return chart == null || chart.isProviderBacked() ? null : SmartChartSpecMapper.toSpec(chart);
  }

  // --- data source ------------------------------------------------------------------

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public List<SelectItem> getDataSources() {
    if (dataSources == null) {
      dataSources = ProviderChartService.dataSourceGroups();
    }
    return dataSources;
  }

  public ProviderChartForm getProviderForm() {
    return providerForm;
  }

  /** Re-seeds the source controls from whatever the chart now is - including after an agent turn. */
  private void syncSourceStateFromStatistic() {
    dataSource = ProviderChartService.sourceKeyOf(statistic);
    providerForm = ProviderChartService.toForm(statistic);
    if (!statistic.isProviderBacked()) {
      return;
    }
    ProviderChartService.ensureCatalogue(providerForm, assistantWarnings);
    // Before normalise(), which would quietly swap a field the data no longer has.
    ProviderChartService.verifyFields(providerForm);
    if (providerForm.isFieldsChanged()) {
      providerForm.setMaxLimit(ProviderChartService.maxLimit(statistic.getChartType()));
      assistantWarnings.add(ProviderChartService.fieldsChanged(providerForm));
      return;
    }
    ProviderChartService.normalise(providerForm, statistic.getChartType());
  }

  /**
   * Switches between tasks and cases, or converts the chart onto a data provider. Only reachable
   * while the chart is still task- or case-backed: once it is on a provider the field is read-only,
   * because the group-by, measure and filters are all built against that provider's schema.
   *
   * A provider that cannot be read is not something the user can fix from this form, so the
   * selection reverts rather than leaving a chart pointing at data that is not there.
   */
  public void onSelectDataSource() {
    assistantWarnings = new ArrayList<>();
    if (!ProviderChartService.isProviderKey(dataSource)) {
      ChartTarget target = ChartTarget.valueOf(dataSource);
      // Re-picking the target already in use is not a change; resetting there would throw away
      // the aggregation and filters for nothing.
      if (target == statistic.getChartTarget()) {
        return;
      }
      ProviderChartService.toStandard(statistic, target);
      providerForm = new ProviderChartForm();
      aggregationInterval = null;
      isDateTimeSelected = false;
      currentCustomFieldDescription = null;
      initFilterFields();
      resetConditionBasedColoring();
      return;
    }

    Optional<String> failure = ProviderChartService.switchToProvider(statistic, dataSource, assistantWarnings);
    if (failure.isPresent()) {
      assistantWarnings.add(failure.get());
      dataSource = ProviderChartService.sourceKeyOf(statistic);
      return;
    }

    providerForm = ProviderChartService.toForm(statistic);
    ProviderChartService.normalise(providerForm, statistic.getChartType());
    // Free here: choosing a provider is already the moment its data gets read.
    ProviderChartService.loadResult(providerForm);
    // save() validates the interval before anything else; a leftover true with no interval would
    // block the save citing a control that is no longer on screen.
    refreshIntervalEnabled = false;
    aggregationInterval = null;
    isDateTimeSelected = false;
    filterFields = new ArrayList<>();
  }

  public void onSelectProviderLabel() {
    ProviderChartService.normalise(providerForm, statistic.getChartType());
  }

  public void onSelectProviderAggregation() {
    ProviderChartService.normalise(providerForm, statistic.getChartType());
  }

  public void onSelectProviderDateBucket() {
    ProviderChartService.normalise(providerForm, statistic.getChartType());
  }

  /**
   * Reads the provider again and shows what it returned. On demand rather than on render: this
   * runs the callable, which is far too expensive to repeat every time the form is drawn.
   */
  public void loadProviderResult() {
    ProviderChartService.loadResult(providerForm).ifPresent(this::providerFormFailed);
  }

  // --- field catalogue --------------------------------------------------------------

  public List<DynamicField> getCatalogueFields() {
    return providerForm.getAllFields();
  }

  public String describeMeasures(DynamicField field) {
    return ProviderChartService.describeMeasures(field);
  }

  /** Re-reads the provider and derives its fields again, replacing the catalogue on screen. */
  public void regenerateCatalogue() {
    assistantWarnings = new ArrayList<>();
    ProviderChartService.regenerate(providerForm, statistic.getChartType(), assistantWarnings)
        .ifPresent(this::providerFormFailed);
  }

  public void addProviderFilter() {
    providerForm.getFilters().add(new ProviderChartSpec.Filter());
  }

  public void removeProviderFilter(ProviderChartSpec.Filter filter) {
    providerForm.getFilters().remove(filter);
  }

  public List<SelectItem> getProviderAggregations() {
    return ProviderChartService.aggregationsFor(providerForm.getCatalogue(), providerForm.getValuePath());
  }

  public List<SelectItem> getProviderDateBuckets() {
    return ProviderChartService.dateBuckets();
  }

  public List<SelectItem> getProviderSortOrders() {
    return ProviderChartService.sortOrders();
  }

  public List<SelectItem> getProviderFilterOperators() {
    return ProviderChartService.filterOperators();
  }

  public String describeField(DynamicField field) {
    return ProviderChartService.describe(field);
  }

  /**
   * Validates the provider form through the same mapper the agent path uses, and takes only the
   * spec and the aggregation back: the name, description, permissions and colours on the chart are
   * the form's, and {@code assemble()} would overwrite all four.
   *
   * @return false when the configuration cannot be drawn, leaving the modal open with the reason.
   */
  private boolean applyProviderForm() {
    // Before normalise(), which would otherwise repair the chart and let it save cleanly.
    if (providerForm.isFieldsChanged()) {
      return providerFormFailed(ProviderChartService.fieldsChanged(providerForm));
    }
    ProviderChartService.normalise(providerForm, statistic.getChartType());
    var provider = ProviderChartService.findProvider(providerForm.getProviderName());
    if (provider.isEmpty()) {
      return providerFormFailed(ProviderChartService.providerUnknown(providerForm.getProviderName()));
    }
    if (!ProviderChartService.ensureCatalogue(providerForm, assistantWarnings)) {
      return providerFormFailed(ProviderChartService.noData(providerForm.getProviderName()));
    }

    SmartStatisticMappingResult mapping =
        ProviderChartService.map(statistic, providerForm, provider.get(), getxTitle(), getyTitle());
    assistantWarnings = new ArrayList<>(mapping.getWarnings());
    if (!mapping.isUsable()) {
      return providerFormFailed(String.join(" ", mapping.getWarnings()));
    }

    Statistic validated = mapping.getStatistic();
    statistic.setCustomChart(validated.getCustomChart());
    statistic.setStatisticAggregation(validated.getStatisticAggregation());
    // Reflect the mapper's clamps back, so the form shows what was actually stored.
    providerForm.apply(validated.getCustomChart());
    return true;
  }

  private boolean providerFormFailed(String reason) {
    reportInvalid(reason);
    return false;
  }

  // ==========================================================================
  // Natural-language layer
  //
  // The form below is the single source of truth. A prompt does not produce a
  // separate configuration to look at - it writes straight into the same
  // Statistic the form is bound to, so every field, filter and colour picker
  // simply re-renders with the new values and stays editable by hand.
  // ==========================================================================

  /**
   * Turns the typed sentence into form values.
   *
   * Note this deliberately reuses {@link #getPreviewData()} afterwards, so the prompt path and
   * the manual "generate preview" path render through exactly the same pipeline.
   */
  public void applyPrompt() {
    if (!runAgent(prompt)) {
      return;
    }
    prompt = StringUtils.EMPTY;
    getPreviewData();
  }

  /**
   * Runs one agent turn and writes the result into {@link #statistic}.
   *
   * Shared by the ask bar above the charts and the prompt bar inside the form so both paths
   * classify failures, surface warnings and validate the proposal identically.
   *
   * @return true when a usable chart configuration was produced
   */
  private boolean runAgent(String text) {
    SmartAgentTurn turn = SmartStatisticAiService.propose(text, systemMessage, statistic,
        currentSpec, currentCustomChart);
    assistantNote = turn.getNote();
    assistantWarnings = turn.getWarnings();
    if (!turn.isUsable()) {
      return false;
    }

    statistic = turn.getStatistic();
    currentSpec = turn.getSpec();
    currentCustomChart = turn.getCustomChart();
    syncUiStateFromStatistic();
    return true;
  }

  /**
   * Rebuilds the UI-mirror fields the form binds to but the Statistic does not carry:
   * axis titles, the colour picker list, the filter field catalogue, the refresh toggle and
   * the interval dropdown. Without this the form would still show the previous chart's values
   * even though the underlying configuration had changed.
   */
  private void syncUiStateFromStatistic() {
    StatisticAggregation aggregation = statistic.getStatisticAggregation();
    StatisticLifecycleService.ensureFormDefaults(statistic);

    ColumnChartConfig columnConfig = ChartAppearanceService.columnConfigOf(statistic);
    xTitles = columnConfig != null && columnConfig.getxTitles() != null
        ? new ArrayList<>(columnConfig.getxTitles()) : new ArrayList<>();
    yTitles = columnConfig != null && columnConfig.getyTitles() != null
        ? new ArrayList<>(columnConfig.getyTitles()) : new ArrayList<>();
    xTitle = LanguageUtils.getLocalizedName(xTitles, null);
    yTitle = LanguageUtils.getLocalizedName(yTitles, null);

    List<String> colors = ChartAppearanceService.storedColors(statistic);
    backgroundColors = colors.isEmpty() ? new ArrayList<>(DEFAULT_COLORS) : colors;
    populateBackgroundColorsIfMissing();

    refreshIntervalEnabled = statistic.getRefreshInterval() != null
        && statistic.getRefreshInterval() >= StatisticLifecycleService.MIN_REFRESH_INTERVAL_IN_SECONDS;

    aggregationInterval = aggregation == null ? null : aggregation.getInterval();
    isDateTimeSelected = aggregationInterval != null;
    currentCustomFieldDescription = null;
    if (aggregation != null && DashboardColumnType.CUSTOM == aggregation.getType()) {
      findCustomFieldMeta().ifPresent(meta -> currentCustomFieldDescription = meta.description());
    }

    initPermissions();
    initMultilanguageServices();
    // Both catalogues are task and case filter fields, and both pick their factory off the chart
    // target - which a provider-backed chart does not have. Its filters live in the custom spec.
    if (!statistic.isProviderBacked()) {
      initFilterFields();
      initFilters();
    } else {
      filterFields = new ArrayList<>();
    }
    syncSourceStateFromStatistic();
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getAssistantNote() {
    return assistantNote;
  }

  public List<String> getAssistantWarnings() {
    return assistantWarnings;
  }

  public boolean isAgentAvailable() {
    return agentAvailable;
  }

  /** The proposal's scope caption, built from the same configuration a saved card uses. */
  public String getAnswerScope() {
    return SmartStatisticScopeService.describe(statistic);
  }

}
