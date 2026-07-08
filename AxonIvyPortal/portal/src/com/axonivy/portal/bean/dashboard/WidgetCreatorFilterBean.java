package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;

@Named
@ViewScoped
public class WidgetCreatorFilterBean implements Serializable {

  private static final long serialVersionUID = -2641889624945089060L;
  public static final String FILTER = "filter";

  private final GlobalOperatorPolicyService globalOperatorPolicyService = new GlobalOperatorPolicyService();
  private List<FilterOperator> statisticOperators;

  @PostConstruct
  public void initOperators() {
    statisticOperators = globalOperatorPolicyService.keepGloballyEnabledOperators(
        FilterOperator.STATISTIC_CREATOR_OPERATORS.stream().toList());
  }

  private List<SecurityMemberDTO> selectedCreators;

  public void init(BaseFilter filter) {
    selectedCreators = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(filter.getValues())) {
      selectedCreators.addAll(filter.getCreators());
    }
  }

  public List<FilterOperator> getStatisticOperators() {
    return statisticOperators;
  }

  public void onChangeOperator(BaseFilter filter) {
    if (filter.getOperator() == FilterOperator.CURRENT_USER) {
      filter.setValues(new ArrayList<>());
    }
  }

  public List<SecurityMemberDTO> getSelectedCreators() {
    return selectedCreators;
  }

  public void onSelectCreator(SelectEvent<Object> event) {
    BaseFilter filter = (BaseFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeCreators(filter);
  }

  public void onUnselectCreator(UnselectEvent<Object> event) {
    BaseFilter filter = (BaseFilter) event.getComponent().getAttributes().get(FILTER);
    onChangeCreators(filter);
  }

  public void setSelectedCreators(List<SecurityMemberDTO> selectedCreators) {
    List<SecurityMemberDTO> uniqueSelectedCreators = selectedCreators.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SecurityMemberDTO::getSecurityMemberId))),
            ArrayList::new));

    this.selectedCreators = uniqueSelectedCreators;
  }

  public void onChangeCreators(BaseFilter filter) {
    if (filter.getValues() == null) {
      filter.setValues(new ArrayList<>());
    }
    filter.getValues().clear();
    for (SecurityMemberDTO creator : selectedCreators) {
      filter.getValues().add(creator.getName());
    }
  }
}
