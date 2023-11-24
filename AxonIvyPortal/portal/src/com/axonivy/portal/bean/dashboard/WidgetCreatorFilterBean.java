package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

@ManagedBean
@ViewScoped
public class WidgetCreatorFilterBean implements Serializable {

  private static final long serialVersionUID = -2641889624945089060L;

  private static List<FilterOperator> operators = FilterOperator.CREATOR_OPERATORS.stream().toList();

  private List<SecurityMemberDTO> selectedCreators;

  public void init(DashboardFilter filter) {
    selectedCreators = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(filter.getTexts())) {
      selectedCreators.addAll(filter.getCreators());
    }
  }

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public void onChangeOperator(DashboardFilter filter) {
    filter.setTexts(new ArrayList<>());
  }

  public List<SecurityMemberDTO> getSelectedCreators() {
    return selectedCreators;
  }

  public void setSelectedCreators(List<SecurityMemberDTO> selectedCreators) {
    this.selectedCreators = selectedCreators;
  }

  public void onChangeCreators(DashboardFilter filter) {
    filter.getTexts().clear();
    for(SecurityMemberDTO creator : selectedCreators) {
      filter.getTexts().add(creator.getName());
    }
  }
}
