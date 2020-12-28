package ch.ivy.addon.portalkit.bean;

import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_OWN_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_OWN_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_SUBSTITUTES;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

@ManagedBean
@ViewScoped
public class AbsenceBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private PermissionCheckerService permissionCheckerService;
  private boolean ownAbsencesReadable;
  private boolean ownAbsencesCreatable;
  private boolean ownAbsencesDeletable;
  private boolean absencesInThePastCreatable;
  private boolean absencesInThePastDeletable;
  private boolean ownSubstituteCreatable;
  private boolean substitutionManagementCapable;

  public AbsenceBean() {
    permissionCheckerService = new PermissionCheckerService(); 
    ownAbsencesReadable = permissionCheckerService.hasAtLeaseOnePermission(USER_READ_OWN_ABSENCES, USER_READ_ABSENCES);
    ownAbsencesCreatable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_OWN_ABSENCE, USER_CREATE_ABSENCE);
    ownAbsencesDeletable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_DELETE_OWN_ABSENCE, USER_DELETE_ABSENCE);

    absencesInThePastCreatable = permissionCheckerService.hasPermission(USER_CREATE_ABSENCE);
    absencesInThePastDeletable = permissionCheckerService.hasPermission(USER_DELETE_ABSENCE);

    ownSubstituteCreatable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_OWN_SUBSTITUTE, USER_CREATE_SUBSTITUTE);
    substitutionManagementCapable =
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

  public boolean isOwnSubstituteCreatable() {
    return ownSubstituteCreatable;
  }

  public boolean isSubstitutionManagementCapable() {
    return substitutionManagementCapable;
  }

  public boolean isAbsenceEditable(IvyAbsence absence) {
    boolean absenceInThePast = AbsenceAndSubstituteUtils.isInThePast(absence);
    if (absenceInThePast) {
      return absencesInThePastCreatable;
    }
    return ownAbsencesCreatable;
  }

  public boolean isAbsenceDeletable(IvyAbsence absence) {
    boolean absenceInThePast = AbsenceAndSubstituteUtils.isInThePast(absence);
    if (absenceInThePast) {
      return absencesInThePastDeletable;
    }
    return ownAbsencesDeletable;
  }

  public String getDisplayedName(IvyAbsence absence) {
    return UserUtils.getDisplayedName(absence.getFullname(), absence.getUsername());
  }

}
