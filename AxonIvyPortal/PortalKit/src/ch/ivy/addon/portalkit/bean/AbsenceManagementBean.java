package ch.ivy.addon.portalkit.bean;

import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_OWN_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_SUBSTITUTES;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class AbsenceManagementBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private PermissionCheckerService permissionCheckerService;
  private boolean ownAbsencesReadable;
  private boolean ownAbsencesCreatable;
  private boolean ownAbsencesDeletable;
  private boolean absencesInThePastCreatable;
  private boolean absencesInThePastDeletable;
  private boolean substitutionManagementCapable;
  private boolean substitutionCapable;
  
  @PostConstruct
  public void init() {
    permissionCheckerService = new PermissionCheckerService();
    ownAbsencesReadable = permissionCheckerService.hasAtLeaseOnePermission(USER_READ_OWN_ABSENCES, USER_READ_ABSENCES);
    ownAbsencesCreatable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_OWN_ABSENCE, USER_CREATE_ABSENCE);
    ownAbsencesDeletable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_DELETE_OWN_ABSENCE, USER_DELETE_ABSENCE);

    absencesInThePastCreatable = permissionCheckerService.hasPermission(USER_CREATE_ABSENCE);
    absencesInThePastDeletable = permissionCheckerService.hasPermission(USER_DELETE_ABSENCE);

    substitutionManagementCapable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_SUBSTITUTE, USER_READ_SUBSTITUTES);
    
    substitutionCapable =
        permissionCheckerService.hasAllPermissions(USER_CREATE_SUBSTITUTE, USER_READ_SUBSTITUTES);
  }

  public boolean isOwnAbsencesReadable() {
    return ownAbsencesReadable;
  }

  public boolean isOwnAbsencesCreatable() {
    return ownAbsencesCreatable;
  }

  public boolean isOwnAbsencesDeletable() {
    return ownAbsencesDeletable;
  }

  public boolean isSubstitutionManagementCapable() {
    return substitutionManagementCapable;
  }

  public boolean isAbsenceEditable(IvyAbsence absence) {
    boolean absenceInThePast = AbsenceAndSubstituteUtils.isInThePast(absence);
    return absenceInThePast ? absencesInThePastCreatable : ownAbsencesCreatable;
  }

  public boolean isAbsenceDeletable(IvyAbsence absence) {
    boolean absenceInThePast = AbsenceAndSubstituteUtils.isInThePast(absence);
    return absenceInThePast ? absencesInThePastDeletable : ownAbsencesDeletable;
  }

  public String getDisplayedName(IvyAbsence absence) {
    return UserUtils.getDisplayedName(absence.getFullname(), absence.getUsername());
  }
  
  public String findNextAbsence(IUser iUser) {
    return iUser != null ? UserUtils.findNextAbsenceOfUser(iUser) : "";
  }
  
  public void loadData() {
    IvyComponentLogicCaller<String> reserveTask = new IvyComponentLogicCaller<>();
    reserveTask.invokeComponentLogic("absence-management", "#{logic.loadData}", new Object[] {});
  }

  public boolean isSubstitutionCapable() {
    return substitutionCapable;
  }

  public void setSubstitutionCapable(boolean substitutionCapable) {
    this.substitutionCapable = substitutionCapable;
  }
}

