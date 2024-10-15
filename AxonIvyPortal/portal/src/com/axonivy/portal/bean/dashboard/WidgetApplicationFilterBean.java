package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

@ManagedBean
@ViewScoped
public class WidgetApplicationFilterBean implements Serializable {

  private static final long serialVersionUID = 4458514225804204212L;

  private static List<FilterOperator> operators = FilterOperator.APPLICATION_OPERATORS.stream().toList();
  private List<String> applications;
  private String applicationString;

  @SuppressWarnings("unchecked")
  public void init(DashboardFilter filter) {
    this.applications = ListUtilities.transformList(IApplicationRepository.of(ISecurityContext.current()).all(),
        IApplication::getName);

    this.applicationString = String.join(", ",
        new ArrayList<>(CollectionUtils.intersection(applications, filter.getValues())));
  }

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public List<String> getApplications() {
    return applications;
  }

  public void updateApplicationsString(List<String> apps) {
    applicationString = String.join(", ", apps);
  }

  public String getApplicationString() {
    return applicationString;
  }

  public void setApplicationString(String applicationString) {
    this.applicationString = applicationString;
  }
}