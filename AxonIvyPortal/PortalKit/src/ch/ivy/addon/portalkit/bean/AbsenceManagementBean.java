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
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.MapUtils;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
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
  private boolean ownSubstituteCreatable;
  private boolean substitutionManagementCapable;
  
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

    ownSubstituteCreatable =
        permissionCheckerService.hasAtLeaseOnePermission(USER_CREATE_OWN_SUBSTITUTE, USER_CREATE_SUBSTITUTE);
    substitutionManagementCapable =
        permissionCheckerService.hasAllPermissions(USER_CREATE_SUBSTITUTE, USER_READ_SUBSTITUTES);
  }
  
  public void onTabChange(TabChangeEvent tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      TabView tabView = (TabView) tabChangeEvent.getComponent();
      Number activeTabIndex = tabView.getIndex();
      
      IvyComponentLogicCaller<String> substitueTabChange = new IvyComponentLogicCaller<>();
      substitueTabChange.invokeComponentLogic("absence-management", "#{logic.onTabChange}", new Object[] { activeTabIndex });
    }
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
  
  public List<IvySubstitute> loadSubstitutesForApp(Map<IvyApplication, List<IvySubstitute>> ivySubtitutesByApp, String application){
    return getSubstituteForApp(ivySubtitutesByApp, application);
  }
  
  public List<IvySubstitute> loadSubstitutionsForApp(Map<IvyApplication, List<IvySubstitute>> ivySubtitutionsByApp, String application){
    return getSubstituteForApp(ivySubtitutionsByApp, application);
  }

  private List<IvySubstitute> getSubstituteForApp(Map<IvyApplication, List<IvySubstitute>> ivySubtitutionsByApp, String application) {
    for (Map.Entry<IvyApplication,List<IvySubstitute>> entry : MapUtils.emptyIfNull(ivySubtitutionsByApp).entrySet()) {
      IvyApplication ivyApplication = entry.getKey();
      if (ivyApplication.getName().equals(application)) {
        return ivySubtitutionsByApp.get(ivyApplication);
      }
    }
    return null;
  }
  
  public void loadData() {
    IvyComponentLogicCaller<String> reserveTask = new IvyComponentLogicCaller<>();
    reserveTask.invokeComponentLogic("absence-management", "#{logic.loadData}", new Object[] {});
  }
}

