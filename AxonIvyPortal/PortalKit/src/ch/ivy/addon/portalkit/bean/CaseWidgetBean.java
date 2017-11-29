package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class CaseWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long expandedCaseId;
  private RemoteCase deletingCase;

  public CaseWidgetBean() {
    expandedCaseId = -1L;
  }

  public Long getExpandedCaseId() {
    return expandedCaseId;
  }

  public void setExpandedCaseId(Long expandedCaseId, boolean alreadyExpanded) {
    if (alreadyExpanded) {
      this.expandedCaseId = 0L;
    } else {
      this.expandedCaseId = expandedCaseId;
    }
  }

  public RemoteCase getDeletingCase() {
    return deletingCase;
  }

  public void setDeletingCase(RemoteCase deletingCase) {
    this.deletingCase = deletingCase;
  }

  public boolean isDeleteFilterEnabledFor(CaseFilterData filterData) { //TODO z1
    if (FilterType.ONLY_ME.equals(filterData.getType())) {
      return true;
    } else {
      boolean isOwnerOfFilter =
          Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(-1L)
              .equals(filterData.getUserId());
      boolean isAdmin = new PermissionBean().hasAdminPermission();
      if (isOwnerOfFilter || isAdmin) {
        return true;
      } else {
        return false;
      }
    }
  }

}
