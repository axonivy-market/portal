package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseStateFilter extends CaseFilter {

  private List<CaseState> filteredStates;
  private List<CaseState> selectedFilteredStates;
  private List<CaseState> selectedFilteredStatesAtBeginning;

  /**
   * Initialize the values of filteredStates: CREATED, RUNNING
   */
  public CaseStateFilter() {
    this.filteredStates = Arrays.asList(CaseState.CREATED, CaseState.RUNNING);
    this.selectedFilteredStatesAtBeginning = new ArrayList<>(filteredStates);
    this.selectedFilteredStates = new ArrayList<>();
  }

  public CaseStateFilter(List<CaseState> filteredStates, List<CaseState> selectedFilteredStates) {
    this.filteredStates = distinct(filteredStates);
    this.selectedFilteredStates = distinct(selectedFilteredStates);
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/state");
  }

  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) || filteredStates.equals(selectedFilteredStates)) {
      return ALL;
    }
    String value = userFriendlyState(selectedFilteredStates.get(0));
    for (int i = 1; i < selectedFilteredStates.size(); i++) {
      value += COMMA + userFriendlyState(selectedFilteredStates.get(i));
    }
    return value;
  }

  @Override
  public CaseQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedFilteredStates)) {
      selectedFilteredStates = new ArrayList<>(filteredStates);
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    selectedFilteredStates.forEach(state -> filterQuery.or().state().isEqual(state));
    return query;
  }

  @Override
  public void resetValues() {
    selectedFilteredStates = new ArrayList<>(selectedFilteredStatesAtBeginning);
  }

  public String userFriendlyState(CaseState state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state);
  }

  public List<CaseState> getSelectedFilteredStates() {
    return selectedFilteredStates;
  }

  public void setSelectedFilteredStates(List<CaseState> selectedFilteredStates) {
    this.selectedFilteredStates = selectedFilteredStates;
  }

  public List<CaseState> getFilteredStates() {
    return filteredStates;
  }

  public void setFilteredStates(List<CaseState> filteredStates) {
    this.filteredStates = filteredStates;
  }

  private List<CaseState> distinct(List<CaseState> filteredStates) {
    if (filteredStates != null) {
      return filteredStates.stream().collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public List<CaseState> getSelectedFilteredStatesAtBeginning() {
    return selectedFilteredStatesAtBeginning;
  }

  public void setSelectedFilteredStatesAtBeginning(List<CaseState> selectedFilteredStatesAtBeginning) {
    this.selectedFilteredStatesAtBeginning = selectedFilteredStatesAtBeginning;
  }

}
