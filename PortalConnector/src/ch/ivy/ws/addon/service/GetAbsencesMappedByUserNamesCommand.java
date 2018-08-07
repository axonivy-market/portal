package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.transformer.IvyAbsenceTransformer;
import ch.ivy.ws.addon.types.Absence;
import ch.ivy.ws.addon.types.IvyAbsence;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;

public class GetAbsencesMappedByUserNamesCommand implements Callable<AbsenceServiceResult> {

  private List<String> applicationNames;

  public GetAbsencesMappedByUserNamesCommand(List<String> applicationNames) {
    this.applicationNames = applicationNames;
  }

  @Override
  public AbsenceServiceResult call() throws Exception {
    
    Map<IUser, List<IvyAbsence>> absencesMappedByUser = new HashMap<>();
    List<String> inactiveApplicationNames = new ArrayList<>();
    List<String> notFoundApplicationNames = new ArrayList<>();

    for (String applicationName : applicationNames) {
      IApplication application = getServer().getApplicationConfigurationManager().findApplication(applicationName);

      if (application == null) {
        notFoundApplicationNames.add(applicationName);
        continue;
      }

      if (notActivated(application)) {
        inactiveApplicationNames.add(applicationName);
        continue;
      }
      
      List<IUser> users = application.getSecurityContext().getUsers();
      for (IUser user : users) {
        List<IvyAbsence> absences = new IvyAbsenceTransformer().transform(user.getAbsences(), application);
        List<IvyAbsence> existingAbsences = absencesMappedByUser.get(user);
        if( existingAbsences != null) {
          absences.addAll(existingAbsences);
        }
        absencesMappedByUser.put(user, absences);
      }
    }

    Set<IUser> keys = absencesMappedByUser.keySet();
    List<Absence> absences = new ArrayList<>();
    for(IUser user: keys) {
      Absence absence = new Absence();
      absence.setUserName(user.getName());
      absence.setUserFullName(user.getFullName());
      absence.setAbsences(absencesMappedByUser.get(user));
      absences.add(absence);
    }

    AbsenceServiceResult result = new AbsenceServiceResult();
    result.setAbsences(absences);

    List<WSException> exceptions = prepareException(notFoundApplicationNames, inactiveApplicationNames);
    result.setErrors(exceptions);
    return result;
  }

  private List<WSException> prepareException(List<String> notFoundApplicationNames,
      List<String> inactiveApplicationNames) {
    List<WSException> exceptions = new ArrayList<>();

    List<Object> errorMessages;
    String errorMessage;

    if (!notFoundApplicationNames.isEmpty()) {
      errorMessages = new ArrayList<>();
      errorMessage = StringUtils.join(notFoundApplicationNames, ", ");
      errorMessages.add(errorMessage);
      exceptions.add(new WSException(WSErrorType.WARNING, 10030, errorMessages, null));
    }

    if (!inactiveApplicationNames.isEmpty()) {
      errorMessages = new ArrayList<>();
      errorMessage = StringUtils.join(inactiveApplicationNames, ", ");
      errorMessages.add(errorMessage);
      exceptions.add(new WSException(WSErrorType.WARNING, 10023, errorMessages, null));
    }

    return exceptions;
  }

  private boolean notActivated(IApplication application) {
    return application.getActivityOperationState() != ActivityOperationState.ACTIVE;
  }

  private IServer getServer() {
    return ServerFactory.getServer();
  }

}
