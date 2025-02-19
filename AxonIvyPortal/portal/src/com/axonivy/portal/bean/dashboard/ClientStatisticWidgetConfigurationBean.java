package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.service.ClientStatisticService;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.statistics.ClientStatisticResponse;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;

@ViewScoped
@ManagedBean
public class ClientStatisticWidgetConfigurationBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private ClientStatistic clientStatistic;

  private List<String> selectedPermissions;

  @PostConstruct
  public void init() {
    clientStatistic = new ClientStatistic();
    clientStatistic.setAggregates("priority");
    selectedPermissions = new ArrayList<>();
    clientStatistic.setNames(new ArrayList<>());
    clientStatistic.setDescriptions(new ArrayList<>());

  }

  public ClientStatistic getClientStatistic() {
    return clientStatistic;
  }

  public void setClientStatistic(ClientStatistic clientStatistic) {
    this.clientStatistic = clientStatistic;
  }

  public void save() {
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
    Ivy.log().warn(BusinessEntityConverter.entityToJsonValue(clientStatistic));
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
    ClientStatisticService clientStatisticService = ClientStatisticService.getInstance();
    AggregationResult result = clientStatisticService.getChartData(clientStatistic);
    PrimeFaces.current().ajax().addCallbackParam("jsonResponse",
        BusinessEntityConverter.entityToJsonValue(new ClientStatisticResponse(result, clientStatistic)));
  }

  public void updateNameForCurrentLanguage() {
    List<DisplayName> languages = clientStatistic.getNames();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        languages.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      clientStatistic.setName(optional.get().getValue());
    }
  }

  public void updateDescriptionForCurrentLanguage() {
    List<DisplayName> languages = clientStatistic.getDescriptions();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        languages.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      clientStatistic.setDescription(optional.get().getValue());
    }
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

}
