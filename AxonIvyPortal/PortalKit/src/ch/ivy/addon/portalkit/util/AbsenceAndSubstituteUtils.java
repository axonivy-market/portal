package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.RemoteAbsence;
import ch.ivy.addon.portalkit.bo.RemoteApplicationUser;
import ch.ivy.addon.portalkit.bo.RemoteSubstitute;
import ch.ivy.addon.portalkit.bo.ServerApplication;
import ch.ivy.addon.portalkit.bo.SubstituteNode;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.environment.Ivy;

public final class AbsenceAndSubstituteUtils {

  private AbsenceAndSubstituteUtils() {

  }

  /**
   * Build Substitute treeNode for an application
   * 
   * @param ivySubtitutes : List Substitute need to built
   * @param ivyUsers : List ivy user in current system
   * @return TreeNode : Substitute treeNode for an application
   */
  public static TreeNode buildSustitute(List<RemoteSubstitute> ivySubtitutes, List<RemoteApplicationUser> ivyUsers) {
    // Add Group list Ivy user to map by application name
    Map<String, List<RemoteApplicationUser>> mapUser = new HashMap<String, List<RemoteApplicationUser>>();
    List<String> apps = new ArrayList<String>();
    Map<String, String> appDisplayNameMap = new HashMap<String, String>();
    for (RemoteApplicationUser remoteUser : ivyUsers) {
      String key = remoteUser.getAppName() + " - " + remoteUser.getAppDisplayName();
      if (mapUser.get(key) == null) {
        mapUser.put(key, new ArrayList<RemoteApplicationUser>());
        apps.add(key);
      }
      mapUser.get(key).add(remoteUser);
      if (appDisplayNameMap.get(key) == null) {
        appDisplayNameMap.put(key, remoteUser.getAppDisplayName());
      }
    }

    TreeNode substituteRoot = new DefaultTreeNode(new SubstituteNode(), null);

    for (String app : apps) {

      TreeNode appNode =
          new DefaultTreeNode(new SubstituteNode(appDisplayNameMap.get(app), null, null, false), substituteRoot);
      appNode.setExpanded(true);

      List<RemoteApplicationUser> users = mapUser.get(app);
      RemoteApplicationUser emptySelection = new RemoteApplicationUser();
      emptySelection.setAppDisplayName(app);
      emptySelection.setAppName(app);
      emptySelection.setDisplayName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/noDeputy"));
      emptySelection.setMemberName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/noDeputy"));
      
      users.add(0, emptySelection);
      
      String appName = app.substring(0, app.indexOf(" - "));
      for (RemoteSubstitute remoteSubstitute : ivySubtitutes) {
        if (remoteSubstitute != null && appName.equals(remoteSubstitute.getAppName()) && StringUtils.isEmpty(remoteSubstitute.getForThisRole())) {
          new DefaultTreeNode(new SubstituteNode(Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTask"), remoteSubstitute, users, true),
              appNode);
          break;
        }
      }

      for (RemoteSubstitute remoteSubstitute : ivySubtitutes) {

        if (remoteSubstitute != null && appName.equals(remoteSubstitute.getAppName())
            && StringUtils.isNotEmpty(remoteSubstitute.getForThisRole())) {
          String nodeName = getNodeName(remoteSubstitute);
          new DefaultTreeNode(new SubstituteNode(Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/taskForRole")
              + nodeName, remoteSubstitute, users, true), appNode);
        }
      }
    }
    return substituteRoot;
  }
  
  private static String getNodeName(RemoteSubstitute remoteSubstitute) {
    return StringUtils.defaultIfBlank(remoteSubstitute.getRoleDisplayName(), remoteSubstitute.getForThisRole());
  }

  /**
   * Convert substitute tree to substitute list
   * 
   * @param root : treenode of substitute
   * @return List<RemoteSubstitute> List substitute
   */
  public static List<RemoteSubstitute> convertToSubstitueList(TreeNode root) {
    List<RemoteSubstitute> outSubstitues = new ArrayList<RemoteSubstitute>();

    for (TreeNode appNode : root.getChildren()) {
      for (TreeNode leafNode : appNode.getChildren()) {
        SubstituteNode substituteNode = (SubstituteNode) leafNode.getData();
        if (substituteNode != null) {
          outSubstitues.add(substituteNode.getRemoteSubstitute());
        }
      }
    }

    return outSubstitues;

  }

  /**
   * Remove all absence have same StartDateInclusive & StopDateInclusive
   * 
   * @param remoteAbsences list of remote absence
   * @return List<RemoteAbsence> : Remote Absences after remove absence have same StartDateInclusive & StopDateInclusive
   */
  public static List<RemoteAbsence> mergeRemoteAbsenceListInApplications(List<RemoteAbsence> remoteAbsences) {
    List<RemoteAbsence> result = new ArrayList<RemoteAbsence>();
    for (RemoteAbsence remoteAbsence : remoteAbsences) {
      RemoteAbsence atTarget = null;
      for (RemoteAbsence targetAbsence : result) {
        if (targetAbsence != null && targetAbsence.getUserName().equals(remoteAbsence.getUserName())
            && targetAbsence.getStartDateInclusive().equals(remoteAbsence.getStartDateInclusive())
            && targetAbsence.getStopDateInclusive().equals(remoteAbsence.getStopDateInclusive())) {
          atTarget = targetAbsence;
          break;
        }
      }
      if (atTarget == null) {
        result.add(remoteAbsence);
      }
    }
    return result;
  }

  /**
   * Check for absences of each application have the same items in merged absences.
   * 
   * @param applications Application list
   * @param allAbsences Absences of all application
   * @param mergedAbsences Absences
   */
  public static void checkSameAbsencesInApplications(List<ServerApplication> applications,
      List<RemoteAbsence> allAbsences, List<RemoteAbsence> mergedAbsences) {
    Boolean result = true;
    for (ServerApplication serverApplication : applications) {
      for (RemoteAbsence mergedAbsence : mergedAbsences) {
        Boolean contains = false;
        for (RemoteAbsence absence : allAbsences) {
          if (serverApplication.getAppName().equals(absence.getAppName())) {
            if (absence.getStartDateInclusive() != null && absence.getStopDateInclusive() != null
                && absence.getStartDateInclusive().equals(mergedAbsence.getStartDateInclusive())
                && absence.getStopDateInclusive().equals(mergedAbsence.getStopDateInclusive())) {
              contains = true; // found, check next absence
              break;
            }
          }
        }
        if (!contains) {
          result = false;
          break;
        }
      }
      if (!result) {
        break;
      }
    }
    if (!result) {
      if (FacesContext.getCurrentInstance() != null) {
        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(FacesMessage.SEVERITY_WARN, null, Ivy.cms().co(
                "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/differentAbsencesInApplications")));
      }
    }
  }

  /**
   * Check validation if from bigger than till
   * 
   * @param remoteAbsence remote absence need to check
   * @return boolean : true if from bigger than till
   */
  public static boolean checkFromBiggerThanTill(RemoteAbsence remoteAbsence) {
    if (remoteAbsence == null || remoteAbsence.getStopDateInclusive() == null
        || remoteAbsence.getStartDateInclusive() == null) {
      return false;
    }

    Date startDate = setTimeToMidnight(remoteAbsence.getStartDateInclusive());
    Date stopDate = setTimeToMidnight(remoteAbsence.getStopDateInclusive());

    if (remoteAbsence.getStopDateInclusive() != null
        && remoteAbsence.getStartDateInclusive() != null && (startDate.compareTo(stopDate) > 0)) {
      FacesContext.getCurrentInstance().addMessage(
          null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/Messages/fromBiggerThanTill"), null));
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
  public static List<RemoteAbsence> removeAbsenceHasTillInThePast(List<RemoteAbsence> absences) {
    return CollectionUtils.emptyIfNull(absences)
        .stream()
        .filter(remoteAbsence -> !isInThePast(remoteAbsence))
        .collect(Collectors.toList());
  }

  public static boolean isInThePast(RemoteAbsence remoteAbsence) {
    if (remoteAbsence == null) {
      return false;
    }

    Date today = setTimeToMidnight(new Date());
    Date stopDate = setTimeToMidnight(remoteAbsence.getStopDateInclusive());
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
      substituteNode.getRemoteSubstitute().setDescription(null);
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
    return CollectionUtils.emptyIfNull(apps)
        .stream()
        .filter(appName -> checkAppSupportAbsence(applications, appName))
        .distinct()
        .collect(Collectors.toList());
  }
  
  private static boolean checkAppSupportAbsence(List<Application> applications, String appName){
    return CollectionUtils.emptyIfNull(applications)
        .stream()
        .anyMatch(application -> appName.equals(application.getName()) && application.getIsVisible() && application.getIsSupportAbsenceSettings());
  }

  public static boolean doesNewAbsenceOverlap(List<RemoteAbsence> absences, RemoteAbsence newAbsence) {    
    return CollectionUtils.emptyIfNull(absences)
        .stream()
        .anyMatch(remoteAbsence -> isTwoAbsenceOverlaped(newAbsence, remoteAbsence));
  }

  private static boolean isTwoAbsenceOverlaped(RemoteAbsence firstAbsence, RemoteAbsence secondAbsence) {
    if (firstAbsence == secondAbsence || isNotAbsenceOfSameUser(firstAbsence, secondAbsence)) {
      return false;
    }
    Date newAbsenceStartDate = firstAbsence.getStartDateInclusive();
    Date newAbsenceEndDate = firstAbsence.getStopDateInclusive();
    Date absenceStartDate = secondAbsence.getStartDateInclusive();
    Date absenceEndDate = secondAbsence.getStopDateInclusive();
    return Dates.isTwoPeriodsOfDateOverlapped(newAbsenceStartDate, newAbsenceEndDate, absenceStartDate, absenceEndDate);
  }

  private static boolean isNotAbsenceOfSameUser(RemoteAbsence firstAbsence, RemoteAbsence secondAbsence) {
    return firstAbsence.getUserName() == null
        || !(firstAbsence.getUserName().equalsIgnoreCase(secondAbsence.getUserName()));
  }

}
