package ch.ivy.addon.portalkit.mapper;

import java.util.Arrays;
import java.util.Calendar;

import ch.ivy.addon.portalkit.bo.RemoteAbsence;
import ch.ivy.ws.addon.Absence;
import ch.ivy.ws.addon.IvyAbsence;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between IvyAbsence or RemoteAbsence.
 * 
 * @author bolt
 */
public class RemoteAbsenceMapper {

  /**
   * Convert from ivyAbsence to RemoteAbsence
   * 
   * @param ivyAbsence Web service absence
   * @return RemoteAbsence remote absence
   * @see IvyAbsence
   * @see RemoteAbsence
   */
  protected static RemoteAbsence mapToRemoteAbsence(IvyAbsence ivyAbsence) {
    RemoteAbsence result = new RemoteAbsence();
    result.setAppName(ivyAbsence.getAppName());
    result.setDescription(ivyAbsence.getDescription());
    result.setStartDateInclusive(ivyAbsence.getStartDateInclusive().getTime());
    result.setStopDateInclusive(ivyAbsence.getStopDateInclusive().getTime());
    return result;
  }

  /**
   * Map list of IvyAbsence to list of RemoteAbsence
   * 
   * @param ivyAbsences list of {@link IvyAbsence}
   * @return list of {@link RemoteAbsence}
   */
  public static List<RemoteAbsence> mapToRemoteAbsences(List<IvyAbsence> ivyAbsences) {
    List<RemoteAbsence> outAbsences = List.create(RemoteAbsence.class);

    for (IvyAbsence absence : ivyAbsences) {
      RemoteAbsence remoteAbsence = mapToRemoteAbsence(absence);
      outAbsences.add(remoteAbsence);
    }

    return outAbsences;
  }

  /**
   * Convert from ivyAbsence to RemoteAbsence.
   * 
   * @param remoteAbsence web service absence
   * @return RemoteAbsence remote absence
   * @see IvyAbsence
   * @see RemoteAbsence
   */
  protected static IvyAbsence mapToIvyAbsence(RemoteAbsence remoteAbsence) {
    IvyAbsence result = new IvyAbsence();
    result.setAppName(remoteAbsence.getAppName());
    result.setDescription(remoteAbsence.getDescription());
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(remoteAbsence.getStartDateInclusive());
    result.setStartDateInclusive(startCal);

    Calendar stopCal = Calendar.getInstance();
    stopCal.setTime(remoteAbsence.getStopDateInclusive());
    result.setStopDateInclusive(stopCal);
    return result;
  }

  /**
   * Maps list of IvyAbsence to list of RemoteAbsence.
   * 
   * @param remoteAbsences list of {@link RemoteAbsence}
   * @return outAbsences list of {@link IvyAbsence}
   * @see IvyAbsence
   * @see RemoteAbsence
   */
  public static List<IvyAbsence> mapToIvyAbsences(List<RemoteAbsence> remoteAbsences) {
    List<IvyAbsence> outAbsences = List.create(IvyAbsence.class);

    for (RemoteAbsence absence : remoteAbsences) {
      IvyAbsence ivyAbsence = mapToIvyAbsence(absence);
      if (null != ivyAbsence) {
        outAbsences.add(ivyAbsence);
      }
    }

    return outAbsences;
  }

  /**
   * Set absences to all application.
   * 
   * @param apps list of application name.
   * @param remoteAbsences - list of remote absence
   * @return list of remote absence.
   */
  public static List<RemoteAbsence> setAbsencesToAllApplication(List<String> apps, List<RemoteAbsence> remoteAbsences) {
    List<RemoteAbsence> outAbsences = List.create(RemoteAbsence.class);
    for (RemoteAbsence remoteAbsence : remoteAbsences) {
      for (String app : apps) {
        outAbsences.add(new RemoteAbsence(app, remoteAbsence.getStartDateInclusive(), remoteAbsence
            .getStopDateInclusive(), remoteAbsence.getDescription()));
      }
    }
    return outAbsences;
  }

  public List<RemoteAbsence> convert(Absence absence) {
    List<IvyAbsence> absences = List.create(IvyAbsence.class);
    List<RemoteAbsence> remoteAbsences = List.create(RemoteAbsence.class);
    if (absence.getAbsences() != null) {
      java.util.List<IvyAbsence> ivyAbsences = Arrays.asList(absence.getAbsences());
      absences.addAll(ivyAbsences);
      remoteAbsences = mapToRemoteAbsences(absences);
      for (RemoteAbsence remoteAbsence : remoteAbsences) {
        remoteAbsence.setUserName(absence.getUserName());
        remoteAbsence.setUserFullName(absence.getUserFullName());
      }
    }
    return remoteAbsences;
  }

  public List<RemoteAbsence> convert(List<Absence> absences) {
    List<RemoteAbsence> remoteAbsences = List.create(RemoteAbsence.class);
    for (Absence absence : absences) {
      remoteAbsences.addAll(convert(absence));
    }
    return remoteAbsences;
  }
}
