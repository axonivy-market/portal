package com.axonivy.portal.developerexamples.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@Named
@ViewScoped
public class EmployeeDetailsBean implements Serializable {
  private static final long serialVersionUID = 3045572476200946770L;

  public ICase findCaseByEmployeeId(Integer employeeId) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField("employeeId").isEqual(employeeId);
    return query.executor().firstResult();
  }
}
