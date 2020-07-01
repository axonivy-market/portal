package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseStateFilter extends CaseFilter {

  @JsonIgnore
  private List<CaseState> filteredStates;
  private List<CaseState> selectedFilteredStates;
  @JsonIgnore
  private List<CaseState> selectedFilteredStatesAtBeginning;

  /**
   * Initialize the values of filteredStates: CREATED, RUNNING, DONE
   * Advance note: if current user is Administrator, will consider to add system states
   */
  public CaseStateFilter() {
    this.filteredStates = new ArrayList<>(CaseSearchCriteria.STANDARD_STATES);
    if (PermissionUtils.checkReadAllCasesPermission()) {
      this.filteredStates.addAll(CaseSearchCriteria.ADVANCE_STATES);
    } else {
      this.filteredStates.add(CaseState.DONE);
    }
    this.selectedFilteredStatesAtBeginning = new ArrayList<>(filteredStates);
    this.selectedFilteredStates = new ArrayList<>();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/STATE");
  }

  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) || isAllStatesSelected()) {
      return ALL;
    }
    String value = userFriendlyState(selectedFilteredStates.get(0));
    for (int i = 1; i < selectedFilteredStates.size(); i++) {
      if (filteredStates.contains(selectedFilteredStates.get(i))) {
        value += COMMA + userFriendlyState(selectedFilteredStates.get(i));
      }
    }
    return value;
  }

  private boolean isAllStatesSelected() {
    return filteredStates.equals(selectedFilteredStates)
    // In case the filter is a saved filter from a user who can filter more state
        || (filteredStates.size() < selectedFilteredStates.size() && selectedFilteredStates.containsAll(filteredStates));
  }

  @Override
  public CaseQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedFilteredStates)) {
      selectedFilteredStates = new ArrayList<>(filteredStates);
    }

    CaseQuery query = CaseUtils.createBusinessCaseQuery();
    IFilterQuery filterQuery = query.where();
    selectedFilteredStates.forEach(state -> filterQuery.or().state().isEqual(state));
    return query;
  }

  @Override
  public void resetValues() {
    selectedFilteredStates = new ArrayList<>(selectedFilteredStatesAtBeginning);
  }
  
  @Override
  public boolean defaultFilter() {
    return true;
 }

  @Override
  public boolean reloadView() {
    return true;
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

  public List<CaseState> getSelectedFilteredStatesAtBeginning() {
    return selectedFilteredStatesAtBeginning;
  }

  public void setSelectedFilteredStatesAtBeginning(List<CaseState> selectedFilteredStatesAtBeginning) {
    this.selectedFilteredStatesAtBeginning = selectedFilteredStatesAtBeginning;
  }

}
