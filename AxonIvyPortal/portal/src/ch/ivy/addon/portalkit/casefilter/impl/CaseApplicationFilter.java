package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseApplicationFilter extends CaseFilter {
  @JsonIgnore
  private List<String> filteredApplications;
  private List<String> selectedFilteredApplications;
  @JsonIgnore
  private boolean isSelectedAll;
  
  public CaseApplicationFilter() {
    this.filteredApplications = IApplicationRepository.instance().allOf(ISecurityContext.current()).stream().map(IApplication::getName).collect(Collectors.toList());
    this.selectedFilteredApplications = new ArrayList<>();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION");
  }

  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredApplications)) {
      return noSelectionLabel();
    } else if (isAllApplicationsSelected()) {
      isSelectedAll = true;
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/All");
    }
    isSelectedAll = false;
    return selectedFilteredApplications.stream().collect(Collectors.joining(","));
  }
  
  private boolean isAllApplicationsSelected() {
    return CollectionUtils.isEqualCollection(filteredApplications, selectedFilteredApplications);
  }

  @Override
  public CaseQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedFilteredApplications) && filteredApplications.size() == 1) {
      return null;
    } else if (CollectionUtils.isEmpty(selectedFilteredApplications)) {
      selectedFilteredApplications = new ArrayList<>(filteredApplications);
    }

    CaseQuery query = CaseUtils.createBusinessCaseQuery();
    IFilterQuery filterQuery = query.where();
    selectedFilteredApplications.forEach(applicationName -> {
      final List<IApplication> allApps = IApplicationRepository.instance().allOf(ISecurityContext.current());
      for (IApplication app : allApps) {
        if (app.getName().equals(applicationName)) {
          filterQuery.or().applicationId().isEqual(app.getId());
        }
      }
    });
    return query;
  }

  @Override
  public void resetValues() {
    this.selectedFilteredApplications = new ArrayList<>(this.filteredApplications);
  }

  public boolean isSelectedAll() {
    return isSelectedAll;
  }

  public void setSelectedAll(boolean isSelectedAll) {
    this.isSelectedAll = isSelectedAll;
  }

  @Override
  public boolean defaultFilter() {
    return false;
 }
  
  public void onSelectedAllApplications() {
    this.selectedFilteredApplications = isSelectedAll ? new ArrayList<>(filteredApplications) : new ArrayList<>();
  }
  
  public void onSelectApplication() {
    isSelectedAll = isAllApplicationsSelected();
  }
  
  public List<String> getSelectedFilteredApplications() {
    return selectedFilteredApplications;
  }

  public void setSelectedFilteredApplications(List<String> selectedFilteredApplications) {
    this.selectedFilteredApplications = selectedFilteredApplications;
  }

  public List<String> getFilteredApplications() {
    return filteredApplications;
  }

  public void setFilteredApplications(List<String> filteredApplications) {
    this.filteredApplications = filteredApplications;
  }

}
