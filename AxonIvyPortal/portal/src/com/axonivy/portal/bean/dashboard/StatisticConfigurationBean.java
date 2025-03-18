package com.axonivy.portal.bean.dashboard;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.enums.statistic.ChartType.NUMBER;
import static com.axonivy.portal.enums.statistic.ChartType.PIE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.enums.statistic.ChartAggregates;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.util.DisplayNameUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.custom.field.configuration.internal.CustomFieldMeta;
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
  private String customFieldAggregate;
  private String currentCustomField;
  private CustomFieldType currentCustomFieldType;


  private static final String CHART_AGGREGATES_CMS_PATH = "/Dialogs/com/axonivy/portal/page/StatisticConfiguration/ChartAggregates/";

  @PostConstruct
  public void init() {
    statisticId = Attrs.currentContext().getAttribute("#{data.id}", String.class);
    if (StringUtils.isNotEmpty(statisticId)) {
      statistic = StatisticService.getInstance().findByIdCustomStatistic(statisticId);
    }
    callbackDashboardId = Attrs.currentContext().getAttribute("#{data.callbackDashboardId}", String.class);
    if (statistic == null) {
      statistic = new Statistic();
      statistic.setAggregates(ChartAggregates.PRIORITY.name());
      statistic.setNames(new ArrayList<>());
      statistic.setDescriptions(new ArrayList<>());
      statistic.setRefreshInterval(0);
      statistic.setChartTarget(ChartTarget.TASK);
      statistic.setChartType(ChartType.BAR);
      statistic.setNumberChartConfig(new NumberChartConfig());
      statistic.setBarChartConfig(new BarChartConfig());
      statistic.setLineChartConfig(new LineChartConfig());
      statistic.setPieChartConfig(new PieChartConfig() {});
      statistic.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
      xTitles = new ArrayList<>();
      yTitles = new ArrayList<>();
      backgroundColors = new ArrayList<>();
      // TODO z1 init category, value. Add action when select chart type
    } else { // existed statistic
      isEditMode = true;
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
        ColumnChartConfig config = BAR == statistic.getChartType() ? statistic.getBarChartConfig()
            : statistic.getLineChartConfig();
        xTitles = config.getxTitles() != null ? config.getxTitles() : new ArrayList<>();
        yTitles = config.getyTitles() != null ? config.getyTitles() : new ArrayList<>();
        backgroundColors = config.getBackgroundColors() != null ? config.getBackgroundColors() : new ArrayList<>();
      } else if (PIE == statistic.getChartType()) {
        PieChartConfig config = statistic.getPieChartConfig();
        backgroundColors = config.getBackgroundColors() != null ? config.getBackgroundColors() : new ArrayList<>();
      } else {
        backgroundColors = new ArrayList<>();
      }
      if (CollectionUtils.isEmpty(statistic.getPermissions())) {
        statistic.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
      }
      if (statistic.getPermissionDTOs() == null) {
        statistic.setPermissionDTOs(Arrays.asList(SecurityMemberDTOMapper.mapFromRoleDTO(
            new RoleDTO(ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME)))));
      }
    }
    populateBackgroundColorsIfMissing();
    initPermissions();
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
    handleCustomFieldAggregation();
    syncUIConfigWithChartConfig();
    resetRedundantChartConfigs(statistic.getChartType(), true);
    saveStatisticJson();
    resetRedundantChartConfigs(statistic.getChartType(), false);
    populateBackgroundColorsIfMissing();
    backToDashboardDetailsPageIfPossible();
  }

  private void syncUIConfigWithChartConfig() {
    List<SecurityMemberDTO> responsibles = statistic.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    // String displayedPermission = ""; // TODO z1 check out saveDashboardDetail
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs =
          responsibles.stream().collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      // displayedPermission =
      // responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
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
  private void resetRedundantChartConfigs(ChartType chartType, boolean isChartConfigAsNull) {
    if (BAR != chartType) {
      statistic.setBarChartConfig(isChartConfigAsNull ? null : new BarChartConfig());
    }
    if (LINE != chartType) {
      statistic.setLineChartConfig(isChartConfigAsNull ? null : new LineChartConfig());
    }
    if (PIE != chartType) {
      statistic.setPieChartConfig(isChartConfigAsNull ? null : new PieChartConfig());
    }
    if (NUMBER != chartType) {
      statistic.setNumberChartConfig(isChartConfigAsNull ? null : new NumberChartConfig());
    }
  }

  private void saveStatisticJson() {
    List<Statistic> statistics = StatisticService.getInstance().getCustomStatistic();

    // Statistic existedStatistic =
    // StatisticService.getInstance().findByIdCustomStatistic(statistic.getId());
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

//  public List<String> completeAggregates(String query) {
//    List<String> allAggregates = getAllAvailableAggregates();
//    List<String> filteredAggregates = new ArrayList<>();
//
//    for (String aggregate : allAggregates) {
//      if (aggregate.toLowerCase().contains(query.toLowerCase())) {
//        filteredAggregates.add(aggregate);
//      }
//    }
//
//    return filteredAggregates;
//  }
  
  public String getUserFriendlyAggregateName(ChartAggregates selectedAggregate) {
    if(selectedAggregate == null) {
      return EMPTY;
    }
    String displayAggregateName = Ivy.cms().co(CHART_AGGREGATES_CMS_PATH + selectedAggregate);
    return displayAggregateName;
  }

  public List<ChartAggregates> getAllAvailableAggregates() {
    List<ChartAggregates> aggregations = filterAggregatesForChartTarget(statistic.getChartTarget());
    
//    List<String> chartAggregations = new ArrayList<>();
//    aggregations.forEach(agg -> chartAggregations.add(agg.getName()));
    return aggregations;
  }
  
  private List<ChartAggregates> filterAggregatesForChartTarget(ChartTarget currentChartTarget) {
    if(ChartTarget.CASE == currentChartTarget) {
      return collectAggregatesForCase(statistic.getChartType());
    }

    return collectAggregatesForTask(statistic.getChartType());
  }  
  
  private List<ChartAggregates> collectAggregatesForCase(ChartType currentChartType){
    if(ChartType.NUMBER == currentChartType) {
      return ChartAggregates.CASE_NUMBER_AGGREGATES.stream().toList();
    }
    
    return ChartAggregates.CASE_AGGREGATES.stream().toList();
  }

  private List<ChartAggregates> collectAggregatesForTask(ChartType currentChartType){
    if(ChartType.NUMBER == currentChartType) {
      return ChartAggregates.TASK_NUMBER_AGGREGATES.stream().toList();
    }
    
    return ChartAggregates.TASK_AGGREGATES.stream().toList();
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
    this.selectedPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForDashboard(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedPermissions.remove(selectedItem.getName());
  }

  public void getPreviewData() {
    handleCustomFieldAggregation();
    syncUIConfigWithChartConfig();
    StatisticService statisticService = StatisticService.getInstance();
    statistic.setAdditionalConfigs(new ArrayList<>());
    statistic.getAdditionalConfigs().addAll(statisticService.getAdditionalConfig());
    statistic.getAdditionalConfigs().add(statisticService.getManipulateValueBy(statistic));
    AggregationResult result = statisticService.getChartData(statistic);
    PrimeFaces.current().ajax().addCallbackParam("jsonResponse",
        BusinessEntityConverter.entityToJsonValue(new StatisticResponse(result, statistic)));
    populateBackgroundColorsIfMissing();
  }
  
  public boolean isCustomFieldsSelected() {
    return statistic.getAggregates().contains("customFields");
  }
  
  private void handleCustomFieldAggregation() {
    if (!isCustomFieldsSelected()) {
      Ivy.log().info("NO");
      Ivy.log().info(statistic.getAggregates());
      return;
    }
      Ivy.log().info("YES");
      Ivy.log().info(statistic.getAggregates());
    /**
     * when user choose custom field, handle here
     */
//    String statisticCustomfieldAggregation = statistic.getAggregates().replace("*", customFieldAggregate);
//    statistic.setAggregates(statisticCustomfieldAggregation);
  }

  public void updateNameForCurrentLanguage() {
    updateForCurrentLanguage(statistic.getNames(), Statistic::setName);
  }

  public void updateDescriptionForCurrentLanguage() {
    updateForCurrentLanguage(statistic.getDescriptions(), Statistic::setDescription);
  }

  public void updateCategoryTitleForCurrentLanguage() {
    updateForCurrentLanguageForColumnChartConfig(xTitles, StatisticConfigurationBean::setxTitle);
  }

  public void updateValueTitleForCurrentLanguage() {
    updateForCurrentLanguageForColumnChartConfig(yTitles, StatisticConfigurationBean::setyTitle);
  }

  private void updateForCurrentLanguage(List<DisplayName> names, BiConsumer<Statistic, String> setNameFunction) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        names.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    optional.ifPresent(displayName -> setNameFunction.accept(statistic, displayName.getValue()));
  }

  private void updateForCurrentLanguageForColumnChartConfig(List<DisplayName> names,
      BiConsumer<StatisticConfigurationBean, String> setNameFunction) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        names.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    optional.ifPresent(displayName -> setNameFunction.accept(this, displayName.getValue()));
  }

  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(statistic.getNames(), statistic.getName());
    initAndSetValue(currentName, statistic.getNames());
  }

  public void updateDescriptionByLocale() {
    String currentDescription =
        LanguageUtils.getLocalizedName(statistic.getDescriptions(), statistic.getDescription());
    initAndSetValue(currentDescription, statistic.getDescriptions());
  }

  public void updateCategoryTitleByLocale() {
    String currentName = LanguageUtils.getLocalizedName(xTitles, getxTitle());
    initAndSetValue(currentName, xTitles);
  }

  public void updateValueTitleByLocale() {
    String currentName = LanguageUtils.getLocalizedName(yTitles, getyTitle());
    initAndSetValue(currentName, yTitles);
  }

  private void initAndSetValue(String value, List<DisplayName> values) {
    DisplayNameConvertor.initMultipleLanguages(value, values);
    DisplayNameConvertor.setValue(value, values);
  }

  public List<DisplayName> getNames() {
    if (statistic == null) {
      return new ArrayList<>();
    }

    if (statistic.getNames().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        statistic.getNames().add(displayName);
      }
    }
    return statistic.getNames();
  }

  public List<DisplayName> getDescriptions() {
    if (statistic == null) {
      return new ArrayList<>();
    }

    if (statistic.getDescriptions().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        statistic.getDescriptions().add(displayName);
      }
    }
    return statistic.getDescriptions();
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
    if (xTitles == null) {
      return new ArrayList<>();
    }

    if (xTitles.isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        xTitles.add(displayName);
      }
    }
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
    if (yTitles == null) {
      return new ArrayList<>();
    }

    if (yTitles.isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        yTitles.add(displayName);
      }
    }
    return yTitles;
  }

  public void setyTitles(List<DisplayName> yTitles) { // TODO z1 should delete?
    this.yTitles = yTitles;
    yTitle = DisplayNameUtils.findDisplayNameOfUserLanguage(yTitles);
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

  protected List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
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

  public void onSelectChartType(ChartType chartType) {
    // TODO z1 consider to remove
  }
  
  public void onSelectChartTarget(ChartTarget newChartTarget) {
    statistic.setChartTarget(newChartTarget);
  }


  private void initPermissions() {
    statistic.setPermissionDTOs(Optional.ofNullable(statistic).map(Statistic::getPermissions)
        .orElse(new ArrayList<>()).stream().filter(Objects::nonNull).distinct()
        .map(permission -> findSecurityMemberDtoByName(permission)).collect(Collectors.toList()));

    selectedPermissions = Optional.ofNullable(statistic).map(Statistic::getPermissionDTOs)
        .orElse(new ArrayList<>()).stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  public void cancel() {
    backToDashboardDetailsPageIfPossible();
  }

  private void backToDashboardDetailsPageIfPossible() {
    if (StringUtils.isEmpty(callbackDashboardId)) {
      PortalNavigatorAPI.navigateToPortalHome();
    } else {
      PortalNavigator.navigateToDashboardDetailsPage(callbackDashboardId, true);
    }
  }

  public String getCustomFieldAggregate() {
    return customFieldAggregate;
  }

  public void setCustomFieldAggregate(String customFieldAggregate) {
    this.customFieldAggregate = customFieldAggregate;
  }
  
  public List<String> getCustomFieldNames() {
    Ivy.log().info("getCustomFieldNames");
    Set<ICustomFieldMeta> customFieldList  = statistic.getChartTarget() == ChartTarget.TASK ? ICustomFieldMeta.tasks()
        : ICustomFieldMeta.cases();
    List<String> newList = new ArrayList<>();
    customFieldList.forEach(customField -> {
      newList.add(customField.name());
    });

    return newList;
  }

  public void onSelectCustomField() {
    Ivy.log().info("onSelectCustomField");
    Ivy.log().info(currentCustomField);
    /**
     * Try to see if this findCustomFieldMeta is working?
     */
    findCustomFieldMeta().ifPresent(meta -> {
      Ivy.log().info(meta.toString());
      this.currentCustomField = meta.name();
      this.currentCustomFieldType = meta.type();
    });
    
    handleCustomFieldAggregateForStatistic();
  }
  
  /**
   * WORKING HERE
   */
  private void handleCustomFieldAggregateForStatistic() {
    if(CustomFieldType.STRING.equals(this.currentCustomFieldType)) {
      statistic.setAggregates("customFields.strings." + currentCustomField);
      return;
    }

    if(CustomFieldType.NUMBER.equals(this.currentCustomFieldType)) {
      statistic.setAggregates("customFields.numbers." + currentCustomField);
      return;
    }

    statistic.setAggregates("customFields.timestamps." + currentCustomField);
  }
  
  public Optional<ICustomFieldMeta> findCustomFieldMeta() {
    Ivy.log().info("findCustomFieldMeta");
    Optional<ICustomFieldMeta> metaData = Optional.empty();

    Set<ICustomFieldMeta> customFieldList  = statistic.getChartTarget() == ChartTarget.TASK ? ICustomFieldMeta.tasks()
        : ICustomFieldMeta.cases();

    
    metaData = customFieldList.stream().filter(meta -> meta.name().equals(currentCustomField)).findFirst();
    Ivy.log().info("metaData");
    Ivy.log().info(metaData.get());
    
    return metaData;
  }


  public String getCurrentCustomField() {
    return currentCustomField;
  }

  public void setCurrentCustomField(String currentCustomField) {
    this.currentCustomField = currentCustomField;
  }
  
  public List<String> completeCustomFields(String query) {
    return null;
  }

}
