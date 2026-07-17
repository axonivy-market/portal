package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.ApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class WidgetApplicationFilterBean implements Serializable {

  private static final long serialVersionUID = 4458514225804204212L;

  private static List<FilterOperator> operators = FilterOperator.APPLICATION_OPERATORS.stream().toList();
  private List<String> applications;
  private String applicationString;

  public void init(DashboardFilter filter) {
    this.applications = ListUtilities.transformList(ApplicationRepository.of(ISecurityContext.current()).all(), IApplication::name);
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