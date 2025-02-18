package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;

import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivyteam.ivy.environment.Ivy;

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
    this.selectedPermissions = new ArrayList<>();
  }

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
    Ivy.log().warn(ToStringBuilder.reflectionToString(clientStatistic, ToStringStyle.MULTI_LINE_STYLE));
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

}
