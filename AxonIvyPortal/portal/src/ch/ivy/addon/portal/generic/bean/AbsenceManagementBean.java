package ch.ivy.addon.portal.generic.bean;

import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_OWN_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_OWN_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_OWN_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_OWN_SUBSTITUTES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_SUBSTITUTES;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class AbsenceManagementBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private boolean ownAbsencesReadable;
  private boolean ownAbsencesCreatable;
  private boolean ownAbsencesDeletable;
  private boolean absencesCreatable;
  private boolean absencesDeletable;
  private boolean substitutionCapable;

  private boolean ownSubstitutionCreatable;
  private boolean substitutionCreatable;
  private boolean ownSubstitutionReadable;
  private boolean substitutionReadable;
  

  @PostConstruct
  public void init() {
    ownAbsencesReadable = PermissionUtils.hasAtLeastOnePermission(USER_READ_OWN_ABSENCES, USER_READ_ABSENCES);
    ownAbsencesCreatable = PermissionUtils.hasPermission(USER_CREATE_OWN_ABSENCE);
    ownAbsencesDeletable = PermissionUtils.hasPermission(USER_DELETE_OWN_ABSENCE);

    absencesCreatable = PermissionUtils.hasPermission(USER_CREATE_ABSENCE);
    absencesDeletable = PermissionUtils.hasPermission(USER_DELETE_ABSENCE);

    substitutionCapable = PermissionUtils.hasAtLeastOnePermission(USER_CREATE_OWN_SUBSTITUTE, USER_CREATE_SUBSTITUTE);
    ownSubstitutionCreatable = PermissionUtils.hasPermission(USER_CREATE_OWN_SUBSTITUTE);
    substitutionCreatable = PermissionUtils.hasPermission(USER_CREATE_SUBSTITUTE);

    substitutionReadable = PermissionUtils.hasPermission(USER_READ_SUBSTITUTES);
    ownSubstitutionReadable = PermissionUtils.hasPermission(USER_READ_OWN_SUBSTITUTES);
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

  public boolean isAbsenceEditable(IvyAbsence absence) {
    return isAbsencesCreatable() || (ownAbsencesCreatable && isOwnAbsence(absence));
  }

  public boolean isAbsenceDeletable(IvyAbsence absence) {
    return isAbsencesDeletable() || (ownAbsencesDeletable && isOwnAbsence(absence));
  }

  public boolean canCreateSubstitute(UserDTO selectedUser) {
    boolean isLoginUser = selectedUser.getName().contentEquals(Ivy.session().getSessionUserName());
    return substitutionCreatable || (ownSubstitutionCreatable && isLoginUser);
  }

  public boolean canReadSubstitute(UserDTO selectedUser) {
    boolean isLoginUser = selectedUser.getName().contentEquals(Ivy.session().getSessionUserName());
    return substitutionReadable || (ownSubstitutionReadable && isLoginUser);
  }

  private boolean isOwnAbsence (IvyAbsence absence) {
    return absence.getUsername().contentEquals(Ivy.session().getSessionUserName());
  }

  public String getDisplayedName(IvyAbsence absence) {
    return UserUtils.getDisplayedName(absence.getFullname(), absence.getUsername());
  }
  
  public String findNextAbsence(IUser iUser) {
    return iUser != null ? UserUtils.findNextAbsenceOfUser(iUser) : "";
  }

  public boolean isSubstitutionCapable() {
    return substitutionCapable;
  }

  public void setSubstitutionCapable(boolean substitutionCapable) {
    this.substitutionCapable = substitutionCapable;
  }

  public boolean isAbsencesCreatable() {
    return absencesCreatable;
  }

  public void setAbsencesCreatable(boolean absencesCreatable) {
    this.absencesCreatable = absencesCreatable;
  }

  public boolean isAbsencesDeletable() {
    return absencesDeletable;
  }

  public void setAbsencesDeletable(boolean absencesDeletable) {
    this.absencesDeletable = absencesDeletable;
  }
}

