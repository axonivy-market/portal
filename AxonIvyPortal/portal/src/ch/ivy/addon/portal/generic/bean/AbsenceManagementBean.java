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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.PrimeFacesContext;

import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.DeputyRoleUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SubstitutionType;

@ManagedBean
@ViewScoped
public class AbsenceManagementBean implements Serializable {
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
  private boolean supervisor;
  private Map<String, List<ISecurityMember>> selectedDeputyMap;

  // Absence form state
  private IvyAbsence selectedAbsence;
  private IvyAbsence backupAbsence;
  private UserDTO selectedUser;
  private boolean createMode;
  private boolean validationError;
  private Map<String, Set<IvyAbsence>> absencesByUser;
  private List<IvyAbsence> displayedAbsences;
  private boolean absenceInThePastShown;
  private List<DeputyRole> deputyRoles;
  private DeputyRole selectedDeputyRole;
  private UserDTO selectedDeputy;
  private List<ISecurityMember> selectedDeputies;
  private List<ISecurityMember> onAbsenceDeputies;
  private List<ISecurityMember> permanentDeputies;
  private List<IvySubstitute> substitutes;
  private List<IvySubstitute> substitutions;
  private boolean canCreateAbsenceForOtherUsers;
  private UserDTO selectedAbsenceUser;
  private String message;
  private boolean createDeputyMode;

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
    selectedDeputyMap = new HashMap<>();
    absencesByUser = new HashMap<>();
    displayedAbsences = new ArrayList<>();
    selectedDeputies = new ArrayList<>();
    substitutes = new ArrayList<>();
    deputyRoles = new ArrayList<>();
    backupAbsence = new IvyAbsence();
    selectedAbsence = new IvyAbsence();
    supervisor = PermissionUtils.hasPermission(USER_READ_ABSENCES);
    selectedAbsenceUser = new UserDTO(Ivy.session().getSessionUser());
    canCreateAbsenceForOtherUsers = absencesCreatable;
    substitutions = new ArrayList<>();
    createDeputyMode = true;
    loadAbsencesAndSubstitutes();
    initDeputyLists();
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
    selectedUser = getCurrentUserAsDefaultIfEmpty(selectedUser);
    boolean isLoginUser = selectedUser.getName().contentEquals(Ivy.session().getSessionUserName());
    return substitutionCreatable || (ownSubstitutionCreatable && isLoginUser);
  }

  public boolean canReadSubstitute(UserDTO selectedUser) {
    selectedUser = getCurrentUserAsDefaultIfEmpty(selectedUser);
    boolean isLoginUser = selectedUser.getName().contentEquals(Ivy.session().getSessionUserName());
    return substitutionReadable || (ownSubstitutionReadable && isLoginUser);
  }

  private boolean isOwnAbsence(IvyAbsence absence) {
    return absence.getUsername().contentEquals(Ivy.session().getSessionUserName());
  }

  public String getDisplayedName(IvyAbsence absence) {
    return UserUtils.getDisplayedName(absence.getFullname(), absence.getUsername());
  }

  public String findNextAbsence(IUser iUser) {
    return iUser != null ? UserUtils.findNextAbsenceOfUser(iUser) : "";
  }

  public String findActiveAbsence(IUser iUser) {
    return iUser != null ? UserUtils.findActiveAbsenceOfUser(iUser) : "";
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

  private UserDTO getCurrentUserAsDefaultIfEmpty(UserDTO selectedUser) {
    if (selectedUser == null || selectedUser.getName() == null) {
      return new UserDTO(Ivy.session().getSessionUser());
    }
    return selectedUser;
  }

  // AbsenceManagementBean.java
  public List<ISecurityMember> getPersonalTaskDeputies(List<DeputyRole> deputyRoles) {
    DeputyRole role = DeputyRoleUtils.findDeputyRoleByType(deputyRoles, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    return role != null ? role.getDeputies() : Collections.emptyList();
  }

  public List<DeputyRole> getDeputyRolesBasedOnPermissions(List<DeputyRole> list, UserDTO selectedAbsenceUser) {
    boolean isTheCurrentUser = Ivy.session().getSessionUserName().contentEquals(selectedAbsenceUser.getName());
    boolean isAbsenceReadable = PermissionUtils.hasPermission(USER_READ_ABSENCES);
    if (isAbsenceReadable && substitutionReadable && substitutionCreatable && !isTheCurrentUser) {
      return list;
    }
    return CollectionUtils.emptyIfNull(list).stream()
        .filter(item -> !DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE.equals(item.getDeputyRoleType()))
        .collect(Collectors.toList());
  }

  public boolean canAddAbsence(UserDTO selectedAbsenceUser) {
    boolean isTheCurrentUser = Ivy.session().getSessionUserName().contentEquals(selectedAbsenceUser.getName());
    if (isTheCurrentUser) {
      return absencesCreatable || ownAbsencesCreatable;
    }
    return absencesCreatable;
  }

  public void updateDeputyMap(List<ISecurityMember> permanentList, List<ISecurityMember> duringAbsenceList) {
    this.selectedDeputyMap.put(SubstitutionType.PERMANENT.name(), permanentList);
    this.selectedDeputyMap.put(SubstitutionType.ON_ABSENCE.name(), duringAbsenceList);
  }

  public Map<String, List<ISecurityMember>> getSelectedDeputyMap() {
    return this.selectedDeputyMap;
  }

  public List<DeputyRole> getSelectableDeputyRoles() {
    return deputyRoles;
  }

  public void onDeputyRoleTypeChanged() {
    if (createDeputyMode) {
      selectedDeputies = new ArrayList<>();
    }
  }

  public void findAbsencesAndSubstitutes() {
    loadAbsencesAndSubstitutes();
  }

  private void loadAbsencesAndSubstitutes() {
    if (selectedAbsenceUser == null) {
      return;
    }
    String username = selectedAbsenceUser.getName();

    absencesByUser = AbsenceService.newInstance().findAbsences(username);

    displayedAbsences.clear();
    Set<IvyAbsence> userAbsences = absencesByUser.get(username);
    if (userAbsences != null) {
      if (!absenceInThePastShown) {
        for (IvyAbsence absence : userAbsences) {
          if (!AbsenceAndSubstituteUtils.isInThePast(absence)) {
            displayedAbsences.add(absence);
          }
        }
      } else {
        displayedAbsences.addAll(userAbsences);
      }
    }
    AbsenceAndSubstituteUtils.sortAbsences(displayedAbsences);

    List<IvySubstitute> foundSubstitutes = SubstituteService.newInstance().findSubstitutes(username);
    substitutes = foundSubstitutes;
    deputyRoles = DeputyRoleUtils.getDeputyRolesFromSubstitutes(foundSubstitutes);
    deputyRoles.forEach(item -> Ivy.log().info(item.getDeputies()));
    substitutions = SubstituteService.newInstance().findSubstitutions(username);
  }

  public void initAbsenceForm() {
    UserDTO currentUserDTO = canCreateAbsenceForOtherUsers && selectedAbsenceUser != null
        ? new UserDTO(selectedAbsenceUser)
        : new UserDTO(Ivy.session().getSessionUser());

    selectedAbsence = new IvyAbsence();
    selectedAbsence.setFrom(new Date());
    selectedAbsence.setUntil(new Date());
    selectedAbsence.setUser(currentUserDTO);

    selectedUser = currentUserDTO;
    selectedDeputy = null;
    validationError = false;
    createMode = true;

    selectedDeputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    permanentDeputies = new ArrayList<>();
    initDeputyLists();
  }

  private void initDeputyLists() {
    if (selectedDeputyRole != null) {
      selectedDeputies = DeputyRoleUtils.cloneDeputyList(selectedDeputyRole.getDeputies());
    } else {
      selectedDeputies = new ArrayList<>();
    }
  }

  public void preDelete(IvyAbsence absence) {
    this.selectedAbsence = absence;
  }

  public void deleteAbsence() {
    AbsenceService.newInstance().deleteAbsence(selectedAbsence);

    displayedAbsences.remove(selectedAbsence);
    Set<IvyAbsence> userAbsences = absencesByUser.get(selectedAbsence.getUsername());
    if (userAbsences != null) {
      userAbsences.remove(selectedAbsence);
    }

    message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceManagement/Messages/removedAbsences",
        Arrays.asList(selectedAbsence.getUser().getName()));
    showMessage();
  }

  public void editAbsence(IvyAbsence absence) {
    this.selectedAbsence = absence;
    this.validationError = false;

    backupAbsence.setUser(absence.getUser());
    backupAbsence.setFrom(absence.getFrom());
    backupAbsence.setUntil(absence.getUntil());
    backupAbsence.setComment(absence.getComment());

    selectedUser = absence.getUser();
    createMode = false;

    initDeputyLists();
  }

  public void saveAbsence() {
    if (createMode) {
      saveCreateAbsence();
    } else {
      saveEditAbsence();
    }
  }

  private void saveCreateAbsence() {
    selectedAbsence.setUntil(Dates.toEndOfDate(selectedAbsence.getUntil()));
    absencesByUser = AbsenceService.newInstance().findAbsences(selectedAbsence.getUsername());

    validationError = false;
    selectedAbsence.setUser(selectedUser);
    String username = selectedUser.getName();

    if (AbsenceAndSubstituteUtils.checkFromBiggerThanTill(selectedAbsence)) {
      validationError = true;
    }
    if (AbsenceAndSubstituteUtils.doesNewAbsenceOverlap(absencesByUser.get(username), selectedAbsence)) {
      validationError = true;
      FacesContext.getCurrentInstance().addMessage(null, FacesMessageUtils.sanitizedMessage(
          FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/overlappingAbsence"), ""));
    }
    if (validationError) {
      FacesContext.getCurrentInstance().validationFailed();
      return;
    }

    AbsenceService.newInstance().createAbsence(selectedAbsence);

    saveSubstitutesOnAddNewAbsence();

    boolean showNewAbsence = selectedAbsence.getUser().getName().equals(
        selectedAbsenceUser != null ? selectedAbsenceUser.getName() : selectedUser.getName());
    if (showNewAbsence && (!AbsenceAndSubstituteUtils.isInThePast(selectedAbsence) || absenceInThePastShown)) {
      displayedAbsences.add(selectedAbsence);
      Set<IvyAbsence> userAbsences = absencesByUser
          .get(selectedAbsenceUser != null ? selectedAbsenceUser.getName() : selectedUser.getName());
      if (userAbsences != null) {
        userAbsences.add(selectedAbsence);
      }
      AbsenceAndSubstituteUtils.sortAbsences(displayedAbsences);
    }

    message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceManagement/Messages/addNewAbsence",
        Arrays.asList(selectedAbsence.getUser().getName()));
    showMessage();
  }

  private void saveSubstitutesOnAddNewAbsence() {
    substitutes = new ArrayList<>();
    if (selectedDeputies.size() > 0) {
      filterSelectedDeputiesIfPermamnentDeputiesExists();
      if (selectedDeputies.size() > 0) {
        DeputyRole duringAbsenceRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
            DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
        duringAbsenceRole.setDeputies(selectedDeputies);
        substitutes.addAll(DeputyRoleUtils.getSubstitutesFromDeputyRole(duringAbsenceRole));
      }
    }
    if (permanentDeputies.size() > 0) {
      DeputyRole permanentDeputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
          DeputyRoleType.PERSONAL_TASK_PERMANENT);
      permanentDeputies.addAll(permanentDeputyRole.getDeputies());
      permanentDeputyRole.setDeputies(permanentDeputies);
      substitutes.addAll(DeputyRoleUtils.getSubstitutesFromDeputyRole(permanentDeputyRole));
    }
    SubstituteService.newInstance().saveSubstitutes(selectedAbsenceUser, substitutes);
  }

  private void saveSubstitutes(List<ISecurityMember> selectedDeputies, DeputyRole deputyRole) {
    deputyRole.setDeputies(selectedDeputies);
    substitutes = DeputyRoleUtils.getSubstitutesFromDeputyRole(deputyRole);
    SubstituteService.newInstance().saveSubstitutes(selectedAbsenceUser, substitutes);
  }

  private void filterSelectedDeputiesIfPermamnentDeputiesExists() {
    for (ISecurityMember member : permanentDeputies) {
      if (selectedDeputies.contains(member)) {
        selectedDeputies.remove(member);
      }
    }
  }

  public boolean isPermanentDeputy(ISecurityMember member) {
    DeputyRole permanentDeputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
        DeputyRoleType.PERSONAL_TASK_PERMANENT);

    return permanentDeputyRole.getDeputies().contains(member);
  }

  private void saveEditAbsence() {
    selectedAbsence.setUntil(Dates.toEndOfDate(selectedAbsence.getUntil()));

    String username = selectedAbsenceUser != null ? selectedAbsenceUser.getName() : selectedUser.getName();
    AbsenceService.newInstance().updateAbsences(username, absencesByUser.get(username));

    Set<IvyAbsence> userAbsences = absencesByUser.get(selectedUser.getName());
    if (userAbsences != null) {
      userAbsences.add(selectedAbsence);
    }
    AbsenceAndSubstituteUtils.sortAbsences(displayedAbsences);

    if (selectedDeputyRole != null) {
      substitutes = new ArrayList<>();
      if (!onAbsenceDeputies.isEmpty()) {
        DeputyRole duringAbsenceRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
            DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
        if (duringAbsenceRole != null) {
          duringAbsenceRole.setDeputies(onAbsenceDeputies);
          substitutes = new ArrayList<>(DeputyRoleUtils.getSubstitutesFromDeputyRole(duringAbsenceRole));
        }
      }
      if (!permanentDeputies.isEmpty()) {
        DeputyRole permanentRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
            DeputyRoleType.PERSONAL_TASK_PERMANENT);
        if (permanentRole != null) {
          permanentRole.setDeputies(permanentDeputies);
          substitutes.addAll(DeputyRoleUtils.getSubstitutesFromDeputyRole(permanentRole));
        }
        SubstituteService.newInstance().saveSubstitutes(selectedAbsenceUser, substitutes);
      }
    }

    message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceManagement/Messages/updateAbsence");
    showMessage();
  }

  private void showMessage() {
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message);
    PrimeFacesContext.getCurrentInstance().addMessage("absences-management-info", facesMessage);
  }

  public void cancelAbsence() {
    selectedAbsence.setUser(backupAbsence.getUser());
    selectedAbsence.setFrom(backupAbsence.getFrom());
    selectedAbsence.setUntil(backupAbsence.getUntil());
    selectedAbsence.setComment(backupAbsence.getComment());

    DeputyRole duringAbsenceRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
        DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    selectedDeputies = duringAbsenceRole != null ? duringAbsenceRole.getDeputies() : new ArrayList<>();
    permanentDeputies = new ArrayList<>();
  }

  public void addDeputy() {
    String messageId = "absence-form:absence-messages";
    ISecurityMember selectedAssignee = selectedDeputy != null
        ? ISecurityContext.current().members().find(selectedDeputy.getMemberName())
        : null;

    if (selectedAssignee == null || selectedDeputies.contains(selectedAssignee)) {
      FacesContext.getCurrentInstance().addMessage(messageId, FacesMessageUtils.sanitizedMessage(
          FacesMessage.SEVERITY_ERROR, "",
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/errorSelectInvalidDeputy")));
    } else if (selectedDeputyRole != null
        && DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE.equals(selectedDeputyRole.getDeputyRoleType())
        && DeputyRoleUtils.isSecurityMemberSelectedInDeputyRoleByType(deputyRoles,
            DeputyRoleType.PERSONAL_TASK_PERMANENT, selectedAssignee)) {
      String errorMessage = Ivy.cms().co(
          "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/errorSelectedInPersonalTaskPermanentDeputies",
          Arrays.asList(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTaskPermanentDeputies")));
      FacesContext.getCurrentInstance().addMessage(messageId, FacesMessageUtils.sanitizedMessage(
          FacesMessage.SEVERITY_ERROR, "", errorMessage));
    } else if (selectedDeputyRole != null
        && DeputyRoleType.PERSONAL_TASK_PERMANENT.equals(selectedDeputyRole.getDeputyRoleType())
        && DeputyRoleUtils.isSecurityMemberSelectedInDeputyRoleByType(deputyRoles,
            DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE, selectedAssignee)) {
      String errorMessage = Ivy.cms().co(
          "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/errorSelectedInPersonalTaskPermanentDeputies",
          Arrays.asList(
              Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTaskDuringAbsenceDeputies")));
      FacesContext.getCurrentInstance().addMessage(messageId, FacesMessageUtils.sanitizedMessage(
          FacesMessage.SEVERITY_ERROR, "", errorMessage));
    } else {
      selectedDeputies.add(selectedAssignee);
    }
  }

  public List<ISecurityMember> fetchDisplaySubstitutesOnAbsenceTab() {
    DeputyRole duringDeputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
        DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    List<ISecurityMember> displayDeputies = duringDeputyRole != null
        ? DeputyRoleUtils.cloneDeputyList(duringDeputyRole.getDeputies())
        : new ArrayList<>();
    DeputyRole permanentDeputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
        DeputyRoleType.PERSONAL_TASK_PERMANENT);
    List<ISecurityMember> permanentDisplayDeputies = duringDeputyRole != null
        ? DeputyRoleUtils.cloneDeputyList(permanentDeputyRole.getDeputies())
        : new ArrayList<>();
    displayDeputies.addAll(permanentDisplayDeputies);
    return displayDeputies;
  }

  public void removeDeputy(ISecurityMember deputy) {
    selectedDeputies.remove(deputy);
  }

  public void closeDialog() {
    selectedDeputy = null;
    if (selectedDeputyRole != null) {
      selectedDeputies = DeputyRoleUtils.cloneDeputyList(selectedDeputyRole.getDeputies());
    } else {
      selectedDeputies = new ArrayList<>();
    }
  }

  public void initSelectedDeputies(DeputyRole deputyRole) {
    this.selectedDeputyRole = deputyRole;
    this.selectedDeputies = DeputyRoleUtils.cloneDeputyList(deputyRole.getDeputies());
    this.selectedDeputy = null;
    this.createDeputyMode = false;
  }

  public void initAddSubstituteDialog() {
    this.createDeputyMode = true;
    this.selectedDeputies = new ArrayList<>();
    this.selectedDeputyRole = deputyRoles.stream()
        .filter(item -> DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE.equals(item.getDeputyRoleType())).findFirst().get();
    this.selectedDeputy = null;
  }

  public List<ISecurityMember> getDeputyListDisplayedOnAbsenceTable() {
    List<ISecurityMember> displayDeputyList = new ArrayList<>();
    DeputyRole deputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles,
        DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    if (deputyRole != null) {
      displayDeputyList.addAll(deputyRole.getDeputies());
    }
    deputyRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    displayDeputyList.addAll(deputyRole.getDeputies());
    return displayDeputyList;
  }

  public void setAsPermanent(ISecurityMember selectedMember, boolean isPermanent) {
    if (isPermanent) {
      permanentDeputies.remove(selectedMember);
    } else {
      permanentDeputies.add(selectedMember);
    }
  }

  public void onFromDateChange() {
    validationError = false;
    if (AbsenceAndSubstituteUtils.isFromDateAfterTill(selectedAbsence)) {
      selectedAbsence.setUntil(selectedAbsence.getFrom());
    }
    validateOverlap();
  }

  public void onDateChange() {
    validationError = false;
    selectedAbsence.setUser(selectedUser);
    if (AbsenceAndSubstituteUtils.checkFromBiggerThanTill(selectedAbsence)) {
      validationError = true;
      FacesContext.getCurrentInstance().validationFailed();
    }
    validateOverlap();
  }

  public void onUserSelectInAbsence() {
    onDateChange();
  }

  private void validateOverlap() {
    if (selectedUser == null) {
      return;
    }
    String username = selectedUser.getName();
    if (AbsenceAndSubstituteUtils.doesNewAbsenceOverlap(absencesByUser.get(username), selectedAbsence)) {
      validationError = true;
      FacesContext.getCurrentInstance().addMessage(null, FacesMessageUtils.sanitizedMessage(
          FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/overlappingAbsence"), ""));
      FacesContext.getCurrentInstance().validationFailed();
    }
  }

  public IvyAbsence getSelectedAbsence() {
    return selectedAbsence;
  }

  public void setSelectedAbsence(IvyAbsence selectedAbsence) {
    this.selectedAbsence = selectedAbsence;
  }

  public IvyAbsence getBackupAbsence() {
    return backupAbsence;
  }

  public void setBackupAbsence(IvyAbsence backupAbsence) {
    this.backupAbsence = backupAbsence;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public boolean isCreateMode() {
    return createMode;
  }

  public void setCreateMode(boolean createMode) {
    this.createMode = createMode;
  }

  public boolean isValidationError() {
    return validationError;
  }

  public void setValidationError(boolean validationError) {
    this.validationError = validationError;
  }

  public Map<String, Set<IvyAbsence>> getAbsencesByUser() {
    return absencesByUser;
  }

  public void setAbsencesByUser(Map<String, Set<IvyAbsence>> absencesByUser) {
    this.absencesByUser = absencesByUser;
  }

  public List<IvyAbsence> getDisplayedAbsences() {
    return displayedAbsences;
  }

  public void setDisplayedAbsences(List<IvyAbsence> displayedAbsences) {
    this.displayedAbsences = displayedAbsences;
  }

  public boolean isAbsenceInThePastShown() {
    return absenceInThePastShown;
  }

  public void setAbsenceInThePastShown(boolean absenceInThePastShown) {
    this.absenceInThePastShown = absenceInThePastShown;
  }

  public List<DeputyRole> getDeputyRoles() {
    return deputyRoles;
  }

  public void setDeputyRoles(List<DeputyRole> deputyRoles) {
    this.deputyRoles = deputyRoles;
  }

  public DeputyRole getSelectedDeputyRole() {
    return selectedDeputyRole;
  }

  public void setSelectedDeputyRole(DeputyRole selectedDeputyRole) {
    this.selectedDeputyRole = selectedDeputyRole;
  }

  public UserDTO getSelectedDeputy() {
    return selectedDeputy;
  }

  public void setSelectedDeputy(UserDTO selectedDeputy) {
    this.selectedDeputy = selectedDeputy;
  }

  public List<ISecurityMember> getSelectedDeputies() {
    return selectedDeputies;
  }

  public void setSelectedDeputies(List<ISecurityMember> selectedDeputies) {
    this.selectedDeputies = selectedDeputies;
  }

  public List<ISecurityMember> getOnAbsenceDeputies() {
    return onAbsenceDeputies;
  }

  public void setOnAbsenceDeputies(List<ISecurityMember> onAbsenceDeputies) {
    this.onAbsenceDeputies = onAbsenceDeputies;
  }

  public List<ISecurityMember> getPermanentDeputies() {
    return permanentDeputies;
  }

  public void setPermanentDeputies(List<ISecurityMember> permanentDeputies) {
    this.permanentDeputies = permanentDeputies;
  }

  public List<IvySubstitute> getSubstitutes() {
    return substitutes;
  }

  public void setSubstitutes(List<IvySubstitute> substitutes) {
    this.substitutes = substitutes;
  }

  public boolean isCanCreateAbsenceForOtherUsers() {
    return canCreateAbsenceForOtherUsers;
  }

  public void setCanCreateAbsenceForOtherUsers(boolean canCreateAbsenceForOtherUsers) {
    this.canCreateAbsenceForOtherUsers = canCreateAbsenceForOtherUsers;
  }

  public UserDTO getSelectedAbsenceUser() {
    return selectedAbsenceUser;
  }

  public void setSelectedAbsenceUser(UserDTO selectedAbsenceUser) {
    this.selectedAbsenceUser = selectedAbsenceUser;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSupervisor() {
    return supervisor;
  }

  public void setSupervisor(boolean supervisor) {
    this.supervisor = supervisor;
  }

  public List<IvySubstitute> getSubstitutions() {
    return substitutions;
  }

  public void setSubstitutions(List<IvySubstitute> substitutions) {
    this.substitutions = substitutions;
  }

  public boolean isCreateDeputyMode() {
    return createDeputyMode;
  }

  public void setCreateDeputyMode(boolean createDeputyMode) {
    this.createDeputyMode = createDeputyMode;
  }

}
