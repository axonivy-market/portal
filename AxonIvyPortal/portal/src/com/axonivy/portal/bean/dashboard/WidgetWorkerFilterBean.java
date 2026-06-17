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
public class WidgetWorkerFilterBean implements Serializable {

  private static final long serialVersionUID = -2641889624945089060L;
  public static final String FILTER = "filter";

  private List<FilterOperator> resolvedOperators;

  private List<SecurityMemberDTO> selectedWorkers;

  @PostConstruct
  public void initOperators() {
    resolvedOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.WORKER_OPERATORS.stream().toList());
  }

  public void init(DashboardFilter filter) {
    selectedWorkers = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(filter.getValues())) {
      selectedWorkers.addAll(filter.getUsers());
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

  public List<SecurityMemberDTO> getSelectedWorkers() {
    return selectedWorkers;
  }

  public void onSelectWorker(SelectEvent<Object> event) {
    DashboardFilter filter = (DashboardFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeWorkers(filter);
  }

  public void onUnselectWorker(UnselectEvent<Object> event) {
    DashboardFilter filter = (DashboardFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeWorkers(filter);
  }

  public void setSelectedWorkers(List<SecurityMemberDTO> selectedWorkers) {
    List<SecurityMemberDTO> uniqueSelectedWorkers = selectedWorkers.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SecurityMemberDTO::getSecurityMemberId))),
            ArrayList::new));

    this.selectedWorkers = uniqueSelectedWorkers;
  }

  public void onChangeWorkers(DashboardFilter filter) {
    if (filter.getValues() == null) {
      filter.setValues(new ArrayList<>());
    }
    filter.getValues().clear();
    for (SecurityMemberDTO worker : selectedWorkers) {
      filter.getValues().add(worker.getMemberName());
    }
  }
}