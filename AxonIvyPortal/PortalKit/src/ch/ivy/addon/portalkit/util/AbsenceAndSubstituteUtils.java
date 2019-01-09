package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.SubstituteNode;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public final class AbsenceAndSubstituteUtils {

  private AbsenceAndSubstituteUtils() {
  }

  public static Set<IvyAbsence> flatIvyAbsenceMap(Map<String, Set<IvyAbsence>> ivyAbsencesByUser) {
    return ivyAbsencesByUser.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
  }
  
  /**
   * Build Substitute treeNode for applications
   * 
   * @param substitutedUser selected user to set substitutes
   * @param ivySubtitutesByApp
   * @param usersByApp
   * @return TreeNode
   */
  public static TreeNode buildSustitute(IUser substitutedUser, Map<IvyApplication, List<IvySubstitute>> ivySubtitutesByApp, Map<String, List<IUser>> usersByApp) {
    TreeNode substituteRoot = new DefaultTreeNode(new SubstituteNode(), null);

    for (Map.Entry<IvyApplication,List<IvySubstitute>> entry : ivySubtitutesByApp.entrySet()) {
      IvyApplication ivyApplication = entry.getKey();
      TreeNode appNode =
          new DefaultTreeNode(new SubstituteNode(ivyApplication.getDisplayName(), null, null, false), substituteRoot);
      appNode.setExpanded(true);

      for (IvySubstitute ivySubstitute : entry.getValue()) {
        createSubstituteNode(substitutedUser, appNode, usersByApp.get(ivyApplication.getName()), ivySubstitute);
      }
    }
    return substituteRoot;
  }

  private static DefaultTreeNode createSubstituteNode(IUser substitutedUser, TreeNode appNode, List<IUser> users, IvySubstitute ivySubstitute) {
    String nodeName = ivySubstitute.getSubstitionRoleDisplayName();
    String name = StringUtils.isNotBlank(nodeName) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/taskForRole") + nodeName : Ivy.cms().co(
        "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTask");
    List<IUser> usersExceptSubstitutedUser = users.stream().filter(user -> !StringUtils.equals(user.getName(), substitutedUser.getName())).collect(Collectors.toList());
    return new DefaultTreeNode(new SubstituteNode(name, ivySubstitute, usersExceptSubstitutedUser, true), appNode);
  }

  /**
   * Check validation if from bigger than till
   * 
   * @param remoteAbsence remote absence need to check
   * @return boolean : true if from bigger than till
   */
  public static boolean checkFromBiggerThanTill(IvyAbsence remoteAbsence) {
    if (remoteAbsence == null || remoteAbsence.getFrom() == null || remoteAbsence.getUntil() == null) {
      return false;
    }

    Date startDate = setTimeToMidnight(remoteAbsence.getFrom());
    Date stopDate = setTimeToMidnight(remoteAbsence.getUntil());

    if (remoteAbsence.getUntil() != null && remoteAbsence.getFrom() != null && (startDate.compareTo(stopDate) > 0)) {
      FacesContext.getCurrentInstance().addMessage(null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/fromBiggerThanTill"), ""));
      return true;
    }

    return false;
  }

  /**
   * Remove the absence in the past
   * 
   * @param absences list of remote absence to remove the old in the past
   * @return List absences which already remove absence in the past
   */
  public static List<IvyAbsence> removeAbsenceHasTillInThePast(List<IvyAbsence> absences) {
    List<IvyAbsence> result = new ArrayList<>();
    for (IvyAbsence remoteAbsence : absences) {
      boolean tillInThePast = isInThePast(remoteAbsence);
      if (!tillInThePast) {
        result.add(remoteAbsence);
      }
    }
    return result;
  }

  public static boolean isInThePast(IvyAbsence remoteAbsence) {
    if (remoteAbsence == null) {
      return false;
    }

    Date today = setTimeToMidnight(new Date());
    Date stopDate = setTimeToMidnight(remoteAbsence.getUntil());
    return today.compareTo(stopDate) > 0;
  }

  /**
   * Set time to mid night
   * 
   * @param date date need to set time to mid night
   * @return Date : Date with time already set to midnight
   */
  public static Date setTimeToMidnight(Date date) {
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  /**
   * Check if deputy value to disable comment field
   * 
   * @param deputyValue deputy to check comment disabled or not
   * @return boolean
   */
  public static boolean isCommentDisabled(String deputyValue) {
    return StringUtils.isEmpty(deputyValue);
  }

  /**
   * The description will be set to null when deputy changed to no deputy
   * 
   * @param substituteNode substitute node to set description
   * @param deputyValue deputy to check
   * 
   */
  public static void deputyChanged(SubstituteNode substituteNode, String deputyValue) {
    if (isCommentDisabled(deputyValue)) {
      substituteNode.getSubstitute().setDescription(null);
    }
  }

  /**
   * Get names of applications that support absences setting
   * 
   * @param apps
   * @param applications
   * @return List<String>
   */
  public static List<String> getAbsencesSettingSupportedApps(List<String> apps, List<Application> applications) {
    List<String> absencesSettingSupportedApps = new ArrayList<>();
    for (String appName : apps) {
      for (Application application : applications) {
        if (appName.equals(application.getName()) && application.getIsVisible()
            && application.getIsSupportAbsenceSettings() && !absencesSettingSupportedApps.contains(appName)) {
          absencesSettingSupportedApps.add(appName);
        }
      }
    }
    return absencesSettingSupportedApps;
  }

  public static boolean doesNewAbsenceOverlap(Set<IvyAbsence> absences, IvyAbsence newAbsence) {
    for (IvyAbsence absence : absences) {
      boolean isTwoAbsenceOverlaped = isTwoAbsenceOverlaped(newAbsence, absence);
      if (isTwoAbsenceOverlaped) {
        return true;
      }
    }
    return false;
  }

  private static boolean isTwoAbsenceOverlaped(IvyAbsence firstAbsence, IvyAbsence secondAbsence) {
    if (firstAbsence == secondAbsence || isNotAbsenceOfSameUser(firstAbsence, secondAbsence)) {
      return false;
    }
    Date newAbsenceStartDate = firstAbsence.getFrom();
    Date newAbsenceEndDate = firstAbsence.getUntil();
    Date absenceStartDate = secondAbsence.getFrom();
    Date absenceEndDate = secondAbsence.getUntil();
    return Dates.isTwoPeriodsOfDateOverlapped(newAbsenceStartDate, newAbsenceEndDate, absenceStartDate, absenceEndDate);
  }

  private static boolean isNotAbsenceOfSameUser(IvyAbsence firstAbsence, IvyAbsence secondAbsence) {
    return firstAbsence.getUsername() == null
        || !(firstAbsence.getUsername().equalsIgnoreCase(secondAbsence.getUsername()));
  }
}
