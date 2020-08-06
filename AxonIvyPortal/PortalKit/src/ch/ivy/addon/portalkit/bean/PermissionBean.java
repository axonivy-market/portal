package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.filter.AbstractFilter.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseStateFilter;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskStateFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;

/**
 * Handle permission to see functionality belonging to Portal Administrator
 * 
 */
@ManagedBean
@RequestScoped
public class PermissionBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String AXONIVY_PORTAL_ADMIN = "AXONIVY_PORTAL_ADMIN";
  private boolean isAdminTaskStateIncluded;
  private boolean isAdminCaseStateIncluded;

  /**
   * If user don't have role ADMIN, then redirect to no permission page
   * 
   * @return true if user has AXONIVY_PORTAL_ADMIN role, otherwise return false.
   */
  public boolean hasAdminPermission() {
    try {
      IWorkflowSession sessionUser = Ivy.session();
      if (sessionUser == null) {
        return false;
      }

      IRole adminRole = Ivy.wf().getSecurityContext().findRole(AXONIVY_PORTAL_ADMIN);
      return sessionUser.hasRole(adminRole, true);
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }

  /**
   * If Task State filter is selecting a ADVANCE_STATES
   * Then disable option save a filter for all user
   * @param taskFilters is selected filters
   */
  public void verifyTaskStateFilter(List<TaskFilter> taskFilters) {
    for (TaskFilter filter : taskFilters) {
      if (filter instanceof TaskStateFilter) {
        TaskStateFilter taskStateFilter = (TaskStateFilter) filter;
        if (!taskStateFilter.value().equals(ALL)) {
          List<TaskState> adminStates = new ArrayList<>(TaskSearchCriteria.ADVANCE_STATES);
          isAdminTaskStateIncluded = adminStates.removeAll(taskStateFilter.getSelectedFilteredStates());
        }
        return;
      }
    }
  }

  /**
   * If Case State filter is selecting a ADVANCE_STATES
   * Then disable option save a filter for all user
   * @param caseFilters is selected filters
   */
  public void verifyCaseStateFilter(List<CaseFilter> caseFilters) {
    for (CaseFilter filter : caseFilters) {
      if (filter instanceof CaseStateFilter) {
        CaseStateFilter caseStateFilter = (CaseStateFilter) filter;
        if (!caseStateFilter.value().equals(ALL)) {
          List<CaseState> adminStates = new ArrayList<>(CaseSearchCriteria.ADVANCE_STATES);
          isAdminCaseStateIncluded = adminStates.removeAll(caseStateFilter.getSelectedFilteredStates());
        }
        return;
      }
    }
  }

  public boolean isAdminTaskStateIncluded() {
    return isAdminTaskStateIncluded;
  }

  public void setAdminTaskStateIncluded(boolean isAdminTaskStateIncluded) {
    this.isAdminTaskStateIncluded = isAdminTaskStateIncluded;
  }

  public boolean isAdminCaseStateIncluded() {
    return isAdminCaseStateIncluded;
  }

  public void setAdminCaseStateIncluded(boolean isAdminCaseStateIncluded) {
    this.isAdminCaseStateIncluded = isAdminCaseStateIncluded;
  }

}
