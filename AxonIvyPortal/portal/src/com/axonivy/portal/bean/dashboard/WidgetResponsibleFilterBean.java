package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.GlobalOperatorPolicyService;

@ManagedBean
@ViewScoped
public class WidgetResponsibleFilterBean implements Serializable {

  private static final long serialVersionUID = -2641889624945089060L;
  public static final String FILTER = "filter";

  private List<FilterOperator> resolvedOperators;

  private List<SecurityMemberDTO> selectedResponsibles;

  @PostConstruct
  public void initOperators() {
    resolvedOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.RESPONSIBLE_OPERATORS.stream().toList());
  }

  public void init(DashboardFilter filter) {
    selectedResponsibles = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(filter.getValues())) {
      selectedResponsibles.addAll(filter.getResponsibles());
    }
  }

  public List<FilterOperator> getOperators() {
    return resolvedOperators;
  }

  public void onChangeOperator(DashboardFilter filter) {
    if (filter.getOperator() == FilterOperator.CURRENT_USER) {
      filter.setValues(new ArrayList<>());
    }
  }

  public List<SecurityMemberDTO> getSelectedResponsibles() {
    return selectedResponsibles;
  }

  public void onSelectResponsible(SelectEvent<Object> event) {
    DashboardFilter filter = (DashboardFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeResponsibles(filter);
  }

  public void onUnselectResponsible(UnselectEvent<Object> event) {
    DashboardFilter filter = (DashboardFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeResponsibles(filter);
  }

  public void setSelectedResponsibles(List<SecurityMemberDTO> selectedResponsibles) {
    List<SecurityMemberDTO> uniqueSelectedResponsibles = selectedResponsibles.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SecurityMemberDTO::getSecurityMemberId))),
            ArrayList::new));

    this.selectedResponsibles = uniqueSelectedResponsibles;
  }

  public void onChangeResponsibles(DashboardFilter filter) {
    if (filter.getValues() == null) {
      filter.setValues(new ArrayList<>());
    }
    filter.getValues().clear();
    for (SecurityMemberDTO responsible : selectedResponsibles) {
      filter.getValues().add(responsible.getMemberName());
    }
  }
}
