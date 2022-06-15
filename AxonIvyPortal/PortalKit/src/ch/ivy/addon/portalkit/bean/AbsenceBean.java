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

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class AbsenceBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private PermissionCheckerService permissionCheckerService;
  private boolean ownAbsencesReadable;
  private boolean ownAbsencesCreatable;
  private boolean ownAbsencesDeletable;
  private boolean absencesCreatable;
  private boolean absencesDeletable;
  private boolean ownSubstituteCreatable;
  private boolean substitutionReadable;
  private boolean substitutionCreatable;
  private boolean ownSubstitutionCreatable;

  public AbsenceBean() {
    permissionCheckerService = new PermissionCheckerService(); 
    ownAbsencesReadable = permissionCheckerService.hasAtLeaseOnePermission(USER_READ_OWN_ABSENCES, USER_READ_ABSENCES);
    ownAbsencesCreatable = permissionCheckerService.hasPermission(USER_CREATE_OWN_ABSENCE);
    ownAbsencesDeletable = permissionCheckerService.hasPermission(USER_DELETE_OWN_ABSENCE);

    absencesCreatable = permissionCheckerService.hasPermission(USER_CREATE_ABSENCE);
    absencesDeletable = permissionCheckerService.hasPermission(USER_DELETE_ABSENCE);

    ownSubstituteCreatable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_OWN_SUBSTITUTE, USER_CREATE_SUBSTITUTE);
    substitutionReadable =
        permissionCheckerService.hasPermission(USER_READ_SUBSTITUTES);
    substitutionCreatable = permissionCheckerService.hasPermission(USER_CREATE_SUBSTITUTE);
    ownSubstitutionCreatable = permissionCheckerService.hasPermission(USER_CREATE_OWN_SUBSTITUTE);
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

  public boolean isSubstitutionReadable() {
    return substitutionReadable;
  }

  public boolean isAbsenceEditable(IvyAbsence absence) {
    return isAbsencesCreatable() || (ownAbsencesCreatable && isOwnAbsence(absence));
  }

  public boolean isAbsenceDeletable(IvyAbsence absence) {
    return absencesDeletable || (ownAbsencesDeletable && isOwnAbsence(absence));
  }

  private boolean isOwnAbsence (IvyAbsence absence) {
    return absence.getUsername().contentEquals(Ivy.session().getSessionUserName());
  }

  public String getDisplayedName(IvyAbsence absence) {
    return UserUtils.getDisplayedName(absence.getFullname(), absence.getUsername());
  }

  public boolean canCreateSubstitute(UserDTO selectedUser) {
    boolean isLoginUser = selectedUser.getName().contentEquals(Ivy.session().getSessionUserName());
    return substitutionCreatable || (ownSubstitutionCreatable && isLoginUser);
  }

  public boolean isSubstitutionCreatable() {
    return substitutionCreatable;
  }

  public boolean isAbsencesCreatable() {
    return absencesCreatable;
  }

  public void setAbsencesCreatable(boolean absencesCreatable) {
    this.absencesCreatable = absencesCreatable;
  }

}
