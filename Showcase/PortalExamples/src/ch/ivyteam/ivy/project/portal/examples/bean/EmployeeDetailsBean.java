package ch.ivyteam.ivy.project.portal.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@ManagedBean
@ViewScoped
public class EmployeeDetailsBean implements Serializable {
  private static final long serialVersionUID = 5155631623709862316L;

  public ICase findCaseByEmployeeId(Integer employeeId) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField("employeeId").isEqual(employeeId);
    return query.executor().firstResult();
  }
}
