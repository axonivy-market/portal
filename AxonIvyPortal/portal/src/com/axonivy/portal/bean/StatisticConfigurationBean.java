package com.axonivy.portal.bean;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.enums.statistic.ChartType.NUMBER;
import static com.axonivy.portal.enums.statistic.ChartType.PIE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.service.multilanguage.StatisticDescriptionMultilanguageService;
import com.axonivy.portal.service.multilanguage.StatisticNameMultilanguageService;
import com.axonivy.portal.service.multilanguage.StatisticXTitleMultilanguageService;
import com.axonivy.portal.service.multilanguage.StatisticYTitleMultilanguageService;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

@ViewScoped
@ManagedBean
public class StatisticConfigurationBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final int MIN_REFRESH_INTERVAL_IN_SECONDS = 60;
  private static final int MAX_REFRESH_INTERVAL_IN_SECONDS = 1000000;
  private static final int DEFAULT_REFRESH_INTERVAL_IN_SECONDS = 300;
  private static final String TIMESTAMP = "timestamp";
  private static final List<String> DEFAULT_COLORS =
      Arrays.asList("#6299f7", "#8dc261", "#98bffa", "#bee3cb", "#c8befa", "#f5bf9f", "#f8da96", "#f9908c");
  private Statistic statistic;
  private String statisticId;
  private String callbackDashboardId;
  private List<DisplayName> xTitles;
  private String xTitle;
  private List<DisplayName> yTitles;
  private String yTitle;
  private List<String> selectedPermissions;
  private List<String> backgroundColors;
  private boolean isEditMode;
  private boolean refreshIntervalEnabled;
  private List<FilterField> filterFields;
  private String currentCustomField;
  private CustomFieldType currentCustomFieldType;
  private String currentCustomFieldDescription;
  private boolean isDateTimeSelected;
  private AggregationInterval aggregationInterval;

  private StatisticNameMultilanguageService nameMultilanguageService;
  private StatisticDescriptionMultilanguageService descriptionMultilanguageService;
  private StatisticXTitleMultilanguageService xTitleMultilanguageService;
  private StatisticYTitleMultilanguageService yTitleMultilanguageService;

  @PostConstruct
  public void init() {
    statisticId = Attrs.currentContext().getAttribute("#{data.id}", String.class);
    if (StringUtils.isNotEmpty(statisticId)) {
      statistic = StatisticService.getInstance().findByIdCustomStatistic(statisticId);
    }
    callbackDashboardId = Attrs.currentContext().getAttribute("#{data.callbackDashboardId}", String.class);
    if (statistic == null) {
      initNewStatistic();
    } else {
      initExistedStatistic();
    }
    populateBackgroundColorsIfMissing();
    initPermissions();
    initMultilanguageServices();
    initFilterFields();
    initFilters();
  }

  private void initMultilanguageServices() {
    nameMultilanguageService = new StatisticNameMultilanguageService(statistic);
    descriptionMultilanguageService = new StatisticDescriptionMultilanguageService(statistic);
    xTitleMultilanguageService = new StatisticXTitleMultilanguageService(this);
    yTitleMultilanguageService = new StatisticYTitleMultilanguageService(this);
  }

  private void initExistedStatistic() {
    isEditMode = true;
    if (statistic.getNames() == null) {
      statistic.setNames(new ArrayList<>());
    }
    if (statistic.getDescriptions() == null) {
      statistic.setDescriptions(new ArrayList<>());
    }
    if (statistic.getRefreshInterval() != null && statistic.getRefreshInterval() >= MIN_REFRESH_INTERVAL_IN_SECONDS) {
      refreshIntervalEnabled = true;
    }
    if (statistic.getNumberChartConfig() == null) {
      statistic.setNumberChartConfig(new NumberChartConfig());
    }
    if (statistic.getBarChartConfig() == null) {
      statistic.setBarChartConfig(new BarChartConfig());
    }
    if (statistic.getLineChartConfig() == null) {
      statistic.setLineChartConfig(new LineChartConfig());
    }
    if (statistic.getPieChartConfig() == null) {
      statistic.setPieChartConfig(new PieChartConfig() {});
    }
    if (BAR == statistic.getChartType() || LINE == statistic.getChartType()) {
      ColumnChartConfig config =
          BAR == statistic.getChartType() ? statistic.getBarChartConfig() : statistic.getLineChartConfig();
      xTitles = config.getxTitles() != null ? config.getxTitles() : new ArrayList<>();
      yTitles = config.getyTitles() != null ? config.getyTitles() : new ArrayList<>();
      backgroundColors =
          config.getBackgroundColors() != null ? config.getBackgroundColors() : new ArrayList<>(DEFAULT_COLORS);
    } else if (PIE == statistic.getChartType()) {
      PieChartConfig config = statistic.getPieChartConfig();
      backgroundColors =
          config.getBackgroundColors() != null ? config.getBackgroundColors() : new ArrayList<>(DEFAULT_COLORS);
    } else {
      backgroundColors = new ArrayList<>(DEFAULT_COLORS);
    }
    if (CollectionUtils.isEmpty(statistic.getPermissions())) {
      statistic.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    }
    if (statistic.getPermissionDTOs() == null) {
      statistic.setPermissionDTOs(Arrays.asList(SecurityMemberDTOMapper.mapFromRoleDTO(
          new RoleDTO(ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME)))));
    }
    if(statistic.getStatisticAggregation() != null) {
      this.currentCustomFieldType = statistic.getStatisticAggregation().getCustomFieldType();
      this.currentCustomField = statistic.getStatisticAggregation().getCustomFieldValue();
      this.setDateTimeSelected(CustomFieldType.TIMESTAMP == this.currentCustomFieldType
          || statistic.getStatisticAggregation().getAggregationField().getName().toLowerCase().contains(TIMESTAMP));
      if (isDateTimeSelected && null != statistic.getStatisticAggregation().getInterval()) {
        this.aggregationInterval = statistic.getStatisticAggregation().getInterval();
      }
    }
  }

  private void initNewStatistic() {
    statistic = new Statistic();
    statistic.setStatisticAggregation(new StatisticAggregation());
    statistic.getStatisticAggregation().setAggregationField(AggregationField.PRIORITY);
    statistic.setNames(new ArrayList<>());
    statistic.setDescriptions(new ArrayList<>());
    statistic.setChartTarget(ChartTarget.TASK);
    statistic.setChartType(ChartType.BAR);
    statistic.setNumberChartConfig(new NumberChartConfig());
    statistic.setBarChartConfig(new BarChartConfig());
    statistic.setLineChartConfig(new LineChartConfig());
    statistic.setPieChartConfig(new PieChartConfig() {});
    statistic.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    xTitles = new ArrayList<>();
    yTitles = new ArrayList<>();
    backgroundColors = new ArrayList<>(DEFAULT_COLORS);
    refreshIntervalEnabled = false;
  }
  
  private void initFilterFields() {
    filterFields = new ArrayList<>();
    filterFields.add(TaskFilterFieldFactory.getDefaultFilterField());
    filterFields.addAll(TaskFilterFieldFactory.getStandardFilterableFields());
  }

  private void initFilters() {
    if (CollectionUtils.isEmpty(statistic.getFilters())) {
      return;
    }
    
    // If the filter available in the filter list, initialize it
    for (StatisticFilter filter : statistic.getFilters()) {
      if (isFilterAvaliable(filter)) {
        FilterField filterField = TaskFilterFieldFactory
            .findBy(Optional.ofNullable(filter).map(StatisticFilter::getField).orElse(StringUtils.EMPTY),
                Optional.ofNullable(filter).map(StatisticFilter::getFilterType).orElse(null));
        if (filterField != null) {
          filterField.initFilter(filter);
        }
      }
    }
  }
  
  private boolean isFilterAvaliable(StatisticFilter filter) {
    return Optional.ofNullable(filter).map(StatisticFilter::getField).isPresent() && filterFields.stream()
        .filter(field -> filter.getField().contentEquals(filter.getField())).findFirst().isPresent();
  }

  private void populateBackgroundColorsIfMissing() {
    while (backgroundColors.size() < 8) {
      backgroundColors.add(null);
    }
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
    if (isRefreshIntervalInValid()) {
      return;
    }
    handleCustomFieldAggregation();
    handleAggregateWithDateTimeInterval();
  
    if(!isCustomFieldsSelected()) {
      statistic.getStatisticAggregation().setCustomFieldType(currentCustomFieldType);
      statistic.getStatisticAggregation().setCustomFieldValue(currentCustomField);
    }
    syncUIConfigWithChartConfig();
    cleanUpRedundantChartConfigs(statistic.getChartType());
    cleanUpConfiguration();
    cleanUpFilter();
    nameMultilanguageService.initMultipleLanguagesForName(statistic.getName());
    descriptionMultilanguageService.initMultipleLanguagesForName(statistic.getDescription());
    if (BAR == statistic.getChartType() || LINE == statistic.getChartType()) {
      xTitleMultilanguageService.initMultipleLanguagesForName(getxTitle());
      yTitleMultilanguageService.initMultipleLanguagesForName(getyTitle());
    }
    saveStatisticJson();
    backToDashboardDetailsPageIfPossible();
  }

  private void cleanUpConfiguration() {
    if (!refreshIntervalEnabled || statistic.getRefreshInterval() == null
        || (statistic.getRefreshInterval() < MIN_REFRESH_INTERVAL_IN_SECONDS)) {
      statistic.setRefreshInterval(null);
    }
    statistic.setAdditionalConfigs(null);
  }
  
  private void cleanUpFilter() {
    if (CollectionUtils.isNotEmpty(statistic.getFilters())) {
      statistic.getFilters().removeIf(filter -> filter.getField() == null);
    } else {
      statistic.setFilter(null);
    }
  }

  private void syncUIConfigWithChartConfig() {
    List<SecurityMemberDTO> responsibles = statistic.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs =
          responsibles.stream().collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
      statistic.setPermissions(permissions);
    }
    backgroundColors.removeIf(Objects::isNull);
    if (BAR == statistic.getChartType()) {
      statistic.setBarChartConfig(new BarChartConfig());
      statistic.getBarChartConfig().setxTitles(xTitles);
      statistic.getBarChartConfig().setyTitles(yTitles);
      statistic.getBarChartConfig().setBackgroundColors(backgroundColors);
    } else if (LINE == statistic.getChartType()) {
      statistic.setLineChartConfig(new LineChartConfig());
      statistic.getLineChartConfig().setxTitles(xTitles);
      statistic.getLineChartConfig().setyTitles(yTitles);
      statistic.getLineChartConfig().setBackgroundColors(backgroundColors);
    } else if (PIE == statistic.getChartType()) {
      statistic.getPieChartConfig().setBackgroundColors(backgroundColors);
    }
    backgroundColors = new ArrayList<>(backgroundColors);
  }

  private void cleanUpRedundantChartConfigs(ChartType chartType) {
    if (BAR != chartType) {
      statistic.setBarChartConfig(null);
    }
    if (LINE != chartType) {
      statistic.setLineChartConfig(null);
    }
    if (PIE != chartType) {
      statistic.setPieChartConfig(null);
    }
    if (NUMBER != chartType) {
      statistic.setNumberChartConfig(null);
    }
  }

  private void saveStatisticJson() {
    List<Statistic> statistics = StatisticService.getInstance().getCustomStatistic();
    Statistic oldStatistic = null;
    for (int i = 0; i < statistics.size(); i++) {
      if (statistic.getId().equals(statistics.get(i).getId())) {
        oldStatistic = statistics.set(i, statistic);
      }
    }
    if (oldStatistic == null) {
      statistic.setVersion(StatisticJsonVersion.LATEST_VERSION.getValue());
      statistics.add(statistic);
    }
    String statisticsJson = BusinessEntityConverter.entityToJsonValue(statistics);
    Ivy.var().set(PortalVariable.CUSTOM_STATISTIC.key, statisticsJson);
  }

  public List<ChartTarget> getAllChartTargets() {
    return Arrays.asList(ChartTarget.values());
  }

  public List<ChartType> getAllChartTypes() {
    return Arrays.asList(ChartType.values());
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    return RoleUtils.findRoles(null, selectedPermissions, query).stream().map(SecurityMemberDTOMapper::mapFromRoleDTO)
        .collect(Collectors.toList());
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
    handleCustomFieldAggregation();
    handleAggregateWithDateTimeInterval();
    syncUIConfigWithChartConfig();
    cleanUpFilter();
    StatisticService statisticService = StatisticService.getInstance();
    statistic.setAdditionalConfigs(new ArrayList<>());
    statistic.getAdditionalConfigs().addAll(statisticService.getAdditionalConfig());
    statistic.getAdditionalConfigs().add(statisticService.getManipulateValueBy(statistic));
    AggregationResult result = statisticService.getChartData(statistic);
    PrimeFaces.current().ajax().addCallbackParam("jsonResponse",
        BusinessEntityConverter.entityToJsonValue(new StatisticResponse(result, statistic)));
    populateBackgroundColorsIfMissing();
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

  public void setEditMode(boolean isEditMode) {
    this.isEditMode = isEditMode;
  }

  public boolean isRefreshIntervalEnabled() {
    return refreshIntervalEnabled;
  }

  public void setRefreshIntervalEnabled(boolean refreshIntervalEnabled) {
    this.refreshIntervalEnabled = refreshIntervalEnabled;
  }

  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }

  public boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }

  public boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  public void onToggleRefreshInterval() {
    Integer refreshIntervalInSeconds = null;
    if (refreshIntervalEnabled) {
      refreshIntervalInSeconds = DEFAULT_REFRESH_INTERVAL_IN_SECONDS;
    }
    statistic.setRefreshInterval(refreshIntervalInSeconds);
  }

  private void initPermissions() {
    statistic.setPermissionDTOs(Optional.ofNullable(statistic).map(Statistic::getPermissions).orElse(new ArrayList<>())
        .stream().filter(Objects::nonNull).distinct().map(permission -> findSecurityMemberDtoByName(permission))
        .collect(Collectors.toList()));

    selectedPermissions = Optional.ofNullable(statistic).map(Statistic::getPermissionDTOs).orElse(new ArrayList<>())
        .stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  public void cancel() {
    backToDashboardDetailsPageIfPossible();
  }

  public int getMaxRefreshIntervalInSeconds() {
    return MAX_REFRESH_INTERVAL_IN_SECONDS;
  }

  public int getMinRefreshIntervalInSeconds() {
    return MIN_REFRESH_INTERVAL_IN_SECONDS;
  }

  private void backToDashboardDetailsPageIfPossible() {
    if (StringUtils.isEmpty(callbackDashboardId)) {
      PortalNavigatorAPI.navigateToPortalHome();
    } else {
      PortalNavigator.navigateToDashboardDetailsPage(callbackDashboardId, true);
    }
  }

  private boolean isRefreshIntervalInValid() {
    if (refreshIntervalEnabled
        && (statistic.getRefreshInterval() == null || statistic.getRefreshInterval() < MIN_REFRESH_INTERVAL_IN_SECONDS
            || statistic.getRefreshInterval() > MAX_REFRESH_INTERVAL_IN_SECONDS)) {
      FacesContext.getCurrentInstance().validationFailed();
      FacesContext.getCurrentInstance().addMessage(null,
          FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
              Ivy.cms().co(
                  "/Dialogs/com/axonivy/portal/page/StatisticConfiguration/RefreshIntervalInSecondsValidationMessage",
                  Arrays.asList(MIN_REFRESH_INTERVAL_IN_SECONDS, MAX_REFRESH_INTERVAL_IN_SECONDS)),
              ""));
      return true;
    }
    return false;
  }
  
  public List<AggregationField> getAllAvailableAggregationField() {
    List<AggregationField> aggregations = filterAggregatesForChartTarget(statistic.getChartTarget());

    return aggregations;
  }
  
  private List<AggregationField> filterAggregatesForChartTarget(ChartTarget currentChartTarget) {
    if(ChartTarget.CASE == currentChartTarget) {
      return collectAggregatesForCase(statistic.getChartType());
    }

    return collectAggregatesForTask(statistic.getChartType());
  }  
  
  private List<AggregationField> collectAggregatesForCase(ChartType currentChartType){
    if(ChartType.NUMBER == currentChartType) {
      return AggregationField.CASE_NUMBER_AGGREGATES.stream().toList();
    }
    boolean hidingCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
    List<AggregationField> aggregates = AggregationField.CASE_AGGREGATES.stream()
        .filter(caseAggregate -> !hidingCaseCreator || !caseAggregate.equals(AggregationField.CREATOR_NAME))
        .toList();
        
    return aggregates;
  }

  private List<AggregationField> collectAggregatesForTask(ChartType currentChartType){
    if(ChartType.NUMBER == currentChartType) {
      return AggregationField.TASK_NUMBER_AGGREGATES.stream().toList();
    }
    
    return AggregationField.TASK_AGGREGATES.stream().toList();
  }
  
  public boolean isCustomFieldsSelected() {
    return statistic.getStatisticAggregation().getAggregationField().name().contains("CUSTOM_FIELD");
  }
  
  private void resetCustomFieldAndDateTimeInterval() {
      this.currentCustomField = null;
      this.currentCustomFieldType = null;
      if(!isDateTimeSelected) {
        this.aggregationInterval = null;
      }
    }
  
  private void handleCustomFieldAggregation() {
    if(!isCustomFieldsSelected()) {
      resetCustomFieldAndDateTimeInterval();
      return;
    }
    
    initValueForStatisticAggregation(AggregationField.CUSTOM_FIELD,
        currentCustomFieldType,
        currentCustomField,
        aggregationInterval);
    switch (this.currentCustomFieldType) {
    case CustomFieldType.STRING: {
      return;
    } 
    case CustomFieldType.NUMBER: {
      return;
    }
    case CustomFieldType.TIMESTAMP:
    {
      return;
    }
    default: { }
    }
    
    return;
  }
  
  public void initValueForStatisticAggregation(AggregationField chartAggregates, CustomFieldType customFieldType,
      String customFieldValue, AggregationInterval dateTimeOperator) {
      statistic.getStatisticAggregation().setAggregationField(chartAggregates);
      statistic.getStatisticAggregation().setCustomFieldType(customFieldType);
      statistic.getStatisticAggregation().setCustomFieldValue(customFieldValue);
      statistic.getStatisticAggregation().setInterval(dateTimeOperator);
  }

  public void handleAggregateWithDateTimeInterval() {
    if (aggregationInterval == null) {
      statistic.getStatisticAggregation().setInterval(null);
      return;
    }
    if(!statistic.getStatisticAggregation().getAggregationField().getName().toLowerCase().contains(TIMESTAMP) && !isDateTimeSelected) {
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
    this.setDateTimeSelected(statistic.getStatisticAggregation().getAggregationField().getName().toLowerCase().contains(TIMESTAMP));
  }

  public void onSelectCustomField() {
    statistic.getStatisticAggregation().setCustomFieldValue(currentCustomField);
    findCustomFieldMeta().ifPresent(meta -> {
      this.currentCustomField = meta.name();
      this.currentCustomFieldType = meta.type();
      this.setCurrentCustomFieldDescription(meta.description());
    });
    
    this.setDateTimeSelected(this.currentCustomFieldType.equals(CustomFieldType.TIMESTAMP));
    
    handleCustomFieldAggregation();
  }

  public Optional<ICustomFieldMeta> findCustomFieldMeta() {
    Optional<ICustomFieldMeta> metaData = Optional.empty();
    Set<ICustomFieldMeta> customFieldList = statistic.getChartTarget() == ChartTarget.TASK ? ICustomFieldMeta.tasks()
        : ICustomFieldMeta.cases();

    metaData = customFieldList.stream().filter(meta -> meta.name().equals(currentCustomField)).findFirst();

    return metaData;
  }

  public List<AggregationInterval> getAvailableIntervals() {
    List<AggregationInterval> intervals = AggregationInterval.DATE_TIME_INTERVALS.stream().toList();

    return intervals;
  }

  public void onSelectInterval() {
    statistic.getStatisticAggregation().setInterval(aggregationInterval);
  }

  public List<String> getCustomFieldNames() {
    Set<ICustomFieldMeta> customFieldList = statistic.getChartTarget() == ChartTarget.TASK ? ICustomFieldMeta.tasks()
        : ICustomFieldMeta.cases();
    List<String> customFieldNameList = new ArrayList<>();
    customFieldList.stream().filter(cf -> !cf.type().equals(CustomFieldType.NUMBER)).forEach(customField -> {
      customFieldNameList.add(customField.name());
    });

    return customFieldNameList;
  }

  public String getCurrentCustomField() {
    return currentCustomField;
  }

  public void setCurrentCustomField(String currentCustomField) {
    this.currentCustomField = currentCustomField;
  }
  
  public void onSelectChartType(ChartType newChartType) {
    if (ChartType.NUMBER == statistic.getChartType()) {
      resetAggregateValues();
      resetCustomFieldAndDateTimeInterval();
      this.setDateTimeSelected(false);
    }
  }


  public void onSelectChartTarget(ChartTarget newChartTarget) {
    if (statistic.getChartTarget() != null && statistic.getChartTarget() == newChartTarget) {
      resetAggregateValues();
      resetFitlerValues();
      resetCustomFieldAndDateTimeInterval();
      this.setDateTimeSelected(
          statistic.getStatisticAggregation().getAggregationField().getName().toLowerCase().contains(TIMESTAMP));
      initFilterFields();
      this.statistic.setFilters(new ArrayList<>());
    }
    statistic.setChartTarget(newChartTarget);
  }
  
  public void resetAggregateValues() {
    statistic.getStatisticAggregation().setAggregationField(AggregationField.PRIORITY);
    statistic.getStatisticAggregation().setCustomFieldType(null);
    statistic.getStatisticAggregation().setCustomFieldValue(null);
    this.currentCustomFieldDescription = null;
    statistic.getStatisticAggregation().setInterval(null);
  }

  public void resetFitlerValues() {
    if (statistic.getFilters() != null) {
      statistic.getFilters().clear();
    }
  }

  public List<FilterField> getFilterFields() {
    return filterFields;
  }

  public void setFilterFields(List<FilterField> filterFields) {
    this.filterFields = filterFields;
  }
  
  public void onSelectFilter(StatisticFilter filter) {
    String field = Optional.ofNullable(filter).map(StatisticFilter::getFilterField).map(FilterField::getName)
        .orElse(StringUtils.EMPTY);

    FilterField filterField = TaskFilterFieldFactory.findBy(field);

    if (filterField.getName()
        .contentEquals(BaseFilter.DEFAULT)) {
      filterField.addNewFilter(filter);
      return;
    }

    filter.getFilterField().addNewFilter(filter);
  }
  
  public void addNewFilter() {
    if (statistic.getFilters() == null) {
      statistic.setFilters(new ArrayList<>());
    }

    StatisticFilter newFilter = new StatisticFilter();
    statistic.getFilters().add(newFilter);
  }
  
  public void removeFilter(StatisticFilter filter) {
    statistic.getFilters().remove(filter);
  }
  
  public List<SecurityMemberDTO> completeOwners(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public AggregationInterval getAggregationInterval() {
    return aggregationInterval;
  }

  public void setAggregationInterval(AggregationInterval aggregationInterval) {
    this.aggregationInterval = aggregationInterval;
  }

}
