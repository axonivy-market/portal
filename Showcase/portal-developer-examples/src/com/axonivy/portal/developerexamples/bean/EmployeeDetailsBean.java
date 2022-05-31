package com.axonivy.portal.developerexamples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@ManagedBean
@ViewScoped
public class EmployeeDetailsBean implements Serializable {
  private static final long serialVersionUID = 3045572476200946770L;

  public ICase findCaseByEmployeeId(Integer employeeId) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField("employeeId").isEqual(employeeId);
    return query.executor().firstResult();
  }
}
