package com.axonivy.portal.bean.dashboard;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.enums.statistic.ChartType.NUMBER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
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
import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.service.ClientStatisticService;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.util.DisplayNameUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.statistics.ClientStatisticResponse;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;

@ViewScoped
@ManagedBean
public class ClientStatisticWidgetConfigurationBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private ClientStatistic clientStatistic;
  private String statisticWidgetId;
  private List<DisplayName> xTitles;
  private String xTitle;
  private List<DisplayName> yTitles;
  private String yTitle;
  private List<String> selectedPermissions;

  @PostConstruct
  public void init() {
    statisticWidgetId = Attrs.currentContext().getAttribute("#{data.id}", String.class);
    if (StringUtils.isNotEmpty(statisticWidgetId)) {
      clientStatistic = ClientStatisticService.getInstance().findByIdCustomClientStatistic(statisticWidgetId);
    }
    if (clientStatistic == null) {
      clientStatistic = new ClientStatistic();
      clientStatistic.setAggregates("priority");
      selectedPermissions = new ArrayList<>();
      clientStatistic.setNames(new ArrayList<>());
      clientStatistic.setDescriptions(new ArrayList<>());
      xTitles = new ArrayList<>();
      yTitles = new ArrayList<>();
      clientStatistic.setRefreshInterval(0L);
      clientStatistic.setChartTarget(ChartTarget.TASK);
      clientStatistic.setChartType(ChartType.BAR);

      // TODO z1 init category, value
      // add action when select chart type
      clientStatistic.setNumberChartConfig(new NumberChartConfig());
      clientStatistic.setBarChartConfig(new BarChartConfig());
      clientStatistic.setLineChartConfig(new LineChartConfig());
    } else {

    }
    if (clientStatistic.getPermissionDTOs() == null) {
      clientStatistic.setPermissionDTOs(Arrays.asList(SecurityMemberDTOMapper.mapFromRoleDTO(
          new RoleDTO(ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME)))));
    }
    if (clientStatistic.getBackgroundColors() == null) {
      clientStatistic.setBackgroundColors(new ArrayList<String>());
    }
    if (BAR == clientStatistic.getChartType() || LINE == clientStatistic.getChartType()) {
      ColumnChartConfig config = BAR == clientStatistic.getChartType() ? clientStatistic.getBarChartConfig()
          : clientStatistic.getLineChartConfig();
      xTitles = config.getxTitles();
      yTitles = config.getyTitles();
    }
    while (clientStatistic.getBackgroundColors().size() < 8) {
      clientStatistic.getBackgroundColors().add(null);
    }

  }

  public ClientStatistic getClientStatistic() {
    return clientStatistic;
  }

  public void setClientStatistic(ClientStatistic clientStatistic) {
    this.clientStatistic = clientStatistic;
  }

  public String getStatisticWidgetId() {
    return statisticWidgetId;
  }

  public void setStatisticWidgetId(String statisticWidgetId) {
    this.statisticWidgetId = statisticWidgetId;
  }

  public void save() {
    syncUIConfigWithChartConfig();
    resetRedundantChartConfigs(clientStatistic.getChartType(), true);

    Ivy.log().warn(BusinessEntityConverter.entityToJsonValue(clientStatistic));
    saveStatisticJson();
    resetRedundantChartConfigs(clientStatistic.getChartType(), false);
  }

  private void syncUIConfigWithChartConfig() {
    List<SecurityMemberDTO> responsibles = clientStatistic.getPermissionDTOs();
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
      clientStatistic.setPermissions(permissions);
    }
    if (ChartType.BAR == clientStatistic.getChartType()) {
      clientStatistic.setBarChartConfig(new BarChartConfig());
      clientStatistic.getBarChartConfig().setxTitles(xTitles);
      clientStatistic.getBarChartConfig().setyTitles(yTitles);
    } else if (ChartType.LINE == clientStatistic.getChartType()) {
      clientStatistic.setLineChartConfig(new LineChartConfig());
      clientStatistic.getLineChartConfig().setxTitles(xTitles);
      clientStatistic.getLineChartConfig().setyTitles(yTitles);
    }
  }

  private void resetRedundantChartConfigs(ChartType chartType, boolean isChartConfigAsNull) {
    if (BAR != chartType) {
      clientStatistic.setBarChartConfig(isChartConfigAsNull ? null : new BarChartConfig());
    }
    if (LINE != chartType) {
      clientStatistic.setLineChartConfig(isChartConfigAsNull ? null : new LineChartConfig());
    }
    if (NUMBER != chartType) {
      clientStatistic.setNumberChartConfig(isChartConfigAsNull ? null : new NumberChartConfig());
    }
  }

  private void saveStatisticJson() {
    List<ClientStatistic> clientStatistics = ClientStatisticService.getInstance().getCustomStatistic();

    // ClientStatistic existedStatistic =
    // ClientStatisticService.getInstance().findByIdCustomClientStatistic(clientStatistic.getId());
    ClientStatistic oldStatistic = null;
    for (int i = 0; i < clientStatistics.size(); i++) {
      if (clientStatistic.getId().equals(clientStatistics.get(i).getId())) {
        oldStatistic = clientStatistics.set(i, clientStatistic);
      }
    }
    if (oldStatistic == null) {
      clientStatistics.add(clientStatistic);
    }
    for (ClientStatistic clientStatistic : clientStatistics) {
      clientStatistic.getBackgroundColors().removeIf(Objects::isNull);
    }
    addIconTypeIfMissing(clientStatistics);
    String statisticsJson = BusinessEntityConverter.entityToJsonValue(clientStatistics);
    Ivy.var().set(PortalVariable.CUSTOM_CLIENT_STATISTIC.key, statisticsJson);
  }

  private void addIconTypeIfMissing(List<ClientStatistic> clientStatistics) { // TODO z1 consider to remove
    for (ClientStatistic clientStatistic : clientStatistics) {
      String icon = clientStatistic.getIcon();
      if (StringUtils.length(icon) > 3) {
        String iconType = icon.substring(0, 3);
        if (iconType.equals("si-") || iconType.equals("fa-")) {
          clientStatistic.setIcon(iconType + " " + icon.substring(0, 2));
        }
      }
    }
  }

  public List<String> completeAggregates(String query) {
    List<String> allAggregates = getAllAvailableAggregates();
    List<String> filteredAggregates = new ArrayList<>();

    for (String aggregate : allAggregates) {
      if (aggregate.toLowerCase().contains(query.toLowerCase())) {
        filteredAggregates.add(aggregate);
      }
    }

    return filteredAggregates;
  }

  private List<String> getAllAvailableAggregates() {
    List<String> aggregation = List.of("state", "businessState", "priority", "category", "isExpired", "worker.name",
        "activator.name", "originalActivator.name", "businessRuntime", "workingTime", "numberOfResumes",
        "startTimestamp", "modifiedTimestamp", "endTimestamp", "expiryTimestamp", "customFields.strings.*",
        "customFields.numbers.*", "customFields.timestamps.*");
    return aggregation;
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
    syncUIConfigWithChartConfig();
    ClientStatisticService clientStatisticService = ClientStatisticService.getInstance();
    AggregationResult result = clientStatisticService.getChartData(clientStatistic);
    PrimeFaces.current().ajax().addCallbackParam("jsonResponse",
        BusinessEntityConverter.entityToJsonValue(new ClientStatisticResponse(result, clientStatistic)));
  }

  public void updateNameForCurrentLanguage() {
    updateForCurrentLanguage(clientStatistic.getNames(), ClientStatistic::setName);
  }

  public void updateDescriptionForCurrentLanguage() {
    updateForCurrentLanguage(clientStatistic.getDescriptions(), ClientStatistic::setDescription);
  }

  public void updateCategoryTitleForCurrentLanguage() {
    updateForCurrentLanguageForColumnChartConfig(xTitles, ClientStatisticWidgetConfigurationBean::setxTitle);
  }

  public void updateValueTitleForCurrentLanguage() {
    updateForCurrentLanguageForColumnChartConfig(yTitles, ClientStatisticWidgetConfigurationBean::setyTitle);
  }

  private void updateForCurrentLanguage(List<DisplayName> names, BiConsumer<ClientStatistic, String> setNameFunction) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        names.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    optional.ifPresent(displayName -> setNameFunction.accept(clientStatistic, displayName.getValue()));
  }

  private void updateForCurrentLanguageForColumnChartConfig(List<DisplayName> names,
      BiConsumer<ClientStatisticWidgetConfigurationBean, String> setNameFunction) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        names.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    optional.ifPresent(displayName -> setNameFunction.accept(this, displayName.getValue()));
  }

  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(clientStatistic.getNames(), clientStatistic.getName());
    initAndSetValue(currentName, clientStatistic.getNames());
  }

  public void updateDescriptionByLocale() {
    String currentDescription =
        LanguageUtils.getLocalizedName(clientStatistic.getDescriptions(), clientStatistic.getDescription());
    initAndSetValue(currentDescription, clientStatistic.getDescriptions());
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
    if (clientStatistic == null) {
      return new ArrayList<>();
    }

    if (clientStatistic.getNames().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        clientStatistic.getNames().add(displayName);
      }
    }
    return clientStatistic.getNames();
  }

  public List<DisplayName> getDescriptions() {
    if (clientStatistic == null) {
      return new ArrayList<>();
    }

    if (clientStatistic.getDescriptions().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        clientStatistic.getDescriptions().add(displayName);
      }
    }
    return clientStatistic.getDescriptions();
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

  public void setyTitles(List<DisplayName> yTitles) {
    this.yTitles = yTitles;
    yTitle = DisplayNameUtils.findDisplayNameOfUserLanguage(yTitles);
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
    switch (chartType) {
      case NUMBER -> clientStatistic.setNumberChartConfig(new NumberChartConfig());
      default -> {
      }
    }
  }
}
