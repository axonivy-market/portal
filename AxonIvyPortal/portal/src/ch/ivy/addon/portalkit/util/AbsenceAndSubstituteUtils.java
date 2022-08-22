package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivyteam.ivy.environment.Ivy;

public final class AbsenceAndSubstituteUtils {

  private AbsenceAndSubstituteUtils() {
  }

  /**
   * Check validation if from bigger than till
   * 
   * @param ivyAbsence absence need to check
   * @return boolean : true if from bigger than till
   */
  public static boolean checkFromBiggerThanTill(IvyAbsence ivyAbsence) {
    if (ivyAbsence == null || ivyAbsence.getFrom() == null || ivyAbsence.getUntil() == null) {
      return false;
    }

    Date startDate = setTimeToMidnight(ivyAbsence.getFrom());
    Date stopDate = setTimeToMidnight(ivyAbsence.getUntil());

    if (ivyAbsence.getUntil() != null && ivyAbsence.getFrom() != null && (startDate.compareTo(stopDate) > 0)) {
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
   * @param absences list of absence to remove the old in the past
   * @return List absences which already remove absence in the past
   */
  public static List<IvyAbsence> removeAbsenceHasTillInThePast(List<IvyAbsence> absences) {
    List<IvyAbsence> result = new ArrayList<>();
    for (IvyAbsence absence : absences) {
      boolean tillInThePast = isInThePast(absence);
      if (!tillInThePast) {
        result.add(absence);
      }
    }
    return result;
  }

  public static boolean isInThePast(IvyAbsence absence) {
    if (absence == null) {
      return false;
    }

    Date today = setTimeToMidnight(new Date());
    Date stopDate = setTimeToMidnight(absence.getUntil());
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

  public static boolean doesNewAbsenceOverlap(Set<IvyAbsence> absences, IvyAbsence newAbsence) {
    return CollectionUtils.emptyIfNull(absences)
        .stream()
        .anyMatch(absence -> isTwoAbsenceOverlaped(newAbsence, absence));
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

  public static void sortAbsences(List<IvyAbsence> absences) {
    absences.sort((l1, l2) -> l1.getFrom().compareTo(l2.getFrom()));
  }
}
