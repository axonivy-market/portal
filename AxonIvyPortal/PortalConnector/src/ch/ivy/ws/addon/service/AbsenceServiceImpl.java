package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.SubstituteServiceResult;
import ch.ivy.ws.addon.service.callable.GetSubstituteWebserviceCallable;
import ch.ivy.ws.addon.types.Absence;
import ch.ivy.ws.addon.types.IvyAbsence;
import ch.ivy.ws.addon.types.IvySubstitute;
import ch.ivy.ws.addon.util.Absences;
import ch.ivy.ws.addon.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.security.IUserSubstitute;

public class AbsenceServiceImpl extends AbstractService implements IAbsenceService {

  @Override
  public AbsenceServiceResult getAbsences(final String username, final List<String> apps) throws WSException {
    try {
      return executeAsSystem(new Callable<AbsenceServiceResult>() {
        @Override
        public AbsenceServiceResult call() throws Exception {

          AbsenceServiceResult result = new AbsenceServiceResult();
          if (StringUtils.isBlank(username)) {
            // Username not given
            result.setErrors(createExceptions(WSErrorType.WARNING, 10039, username));
            return result;
          }
          AvailableAppsResult aaResult = findAvailableApplicationsForUser(apps, username); 
          result.setErrors(aaResult.getErrors());
          result.setAbsences(new ArrayList<>());
          for (String applicationName : aaResult.getAvailableApps()) {
            Absence absence = getAbsences(applicationName, username);
            if (absence != null) {
              result.getAbsences().add(absence);
            }
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10034, e);
    }
  }
  
  private Absence getAbsences(String appName, String username) {
    IApplication app = findApplication(appName);
    IUser user = app != null ? app.getSecurityContext().findUser(username) : null;
    if (user != null && CollectionUtils.isNotEmpty(user.getAbsences())) {
      return new Absences().toAbsence(user, app);
    }
    return null;
  }

  @Override
  public AbsenceServiceResult getAbsences(List<String> applicationNames) throws WSException {
    try {
      return executeAsSystem(new GetAbsencesMappedByUserNamesCommand(applicationNames));
    } catch (Exception e) {
      throw new WSException(10034, e);
    }
  }

  @Override
  public AbsenceServiceResult setAbsences(List<String> apps, String username, List<IvyAbsence> absences)
      throws WSException {
    try {
      AbsenceServiceResult result = new AbsenceServiceResult();
      List<WSException> errors = new ArrayList<>();
      if (StringUtils.isBlank(username)) {
        // Username not given
        result.setErrors(createExceptions(WSErrorType.WARNING, 10039, username));
        return result;
      }
      // app list of absences
      Map<String, List<IvyAbsence>> absenceMap = new HashMap<>();
      for (IvyAbsence ivyAbsence : absences) {
        List<IvyAbsence> absList = absenceMap.get(ivyAbsence.getAppName());
        if (absList == null) {
          absList = new ArrayList<>();
          absenceMap.put(ivyAbsence.getAppName(), absList);
        }
        absList.add(ivyAbsence);
      }
      // delete all absences in passed apps
      for (String app : apps) {
        List<WSException> errorlist = saveUserAbsence(app, username, null);
        if (!errorlist.isEmpty()) {
          errors.addAll(errorlist);
        }
      }

      // save absence at each app
      for (Entry<String, List<IvyAbsence>> entry : absenceMap.entrySet()) {
        List<WSException> errorlist = saveUserAbsence(entry.getKey(), username, entry.getValue());
        if (!errorlist.isEmpty()) {
          errors.addAll(errorlist);
        }
      }
      result.setErrors(errors);
      return result;
    } catch (Exception e) {
      throw new WSException(10035, e);
    }
  }

  @Override
  public SubstituteServiceResult getSubstitutes(final String username, final List<String> apps) throws WSException {
    if (StringUtils.isEmpty(username)) {
      return substituteResultWithAnException(username);
    }
    return getSubStituteOfUserOnApps(username, apps);
  }

  private SubstituteServiceResult getSubStituteOfUserOnApps(final String username, final List<String> apps)
      throws WSException {
    try {
      AvailableAppsResult availableApps = findAvailableApplicationsForUser(apps, username); 
      SubstituteServiceResult substituteServiceResult = executeAsSystem(new GetSubstituteWebserviceCallable(username, availableApps.getAvailableApps()));
      substituteServiceResult.setErrors(availableApps.getErrors());
      return substituteServiceResult;
    } catch (Exception e) {
      throw new WSException(10036, e);
    }
  }

  private SubstituteServiceResult substituteResultWithAnException(final String username) {
    SubstituteServiceResult substituteServiceResult = new SubstituteServiceResult();
    substituteServiceResult.setErrors(createExceptions(WSErrorType.WARNING, 10039, username));
    return substituteServiceResult;
  }

  @Override
  public SubstituteServiceResult setSubstitutes(String username, List<IvySubstitute> substitutes) throws WSException {
    try {
      SubstituteServiceResult result = new SubstituteServiceResult();
      List<WSException> errors = new ArrayList<>();
      if (StringUtils.isBlank(username)) {
        result.setErrors(createExceptions(WSErrorType.WARNING, 10039, username));
        return result;
      }
      // group list of substitutes by appName
      Map<String, List<IvySubstitute>> substitudeMap = new HashMap<>();
      for (IvySubstitute ivySubstitute : substitutes) {
        List<IvySubstitute> subsList = substitudeMap.get(ivySubstitute.getAppName());
        if (subsList == null) {
          subsList = new ArrayList<>();
          substitudeMap.put(ivySubstitute.getAppName(), subsList);
        }
        subsList.add(ivySubstitute);
      }

      // save substitute at each app
      for (Entry<String, List<IvySubstitute>> entry : substitudeMap.entrySet()) {
        saveUserSubstitute(entry.getKey(), username, entry.getValue());
      }
      result.setErrors(errors);
      return result;
    } catch (Exception e) {
      throw new WSException(10037, e);
    }
  }

  /**
   * Save substitutes for passed user in a passed application
   * 
   * @param appName application name
   * @param username
   * @param substitutes : list of {@link IvySubstitute} to save
   * 
   */
  private List<WSException> saveUserSubstitute(final String appName, final String username,
      final List<IvySubstitute> substitutes) {
    return IvyExecutor.executeAsSystem(() -> {
      List<WSException> errors = new ArrayList<>();
      IApplication application = findApplication(appName);
      if (application == null) {
        return createExceptions(WSErrorType.WARNING, 10030, appName);
      }
      IUser user = application.getSecurityContext().findUser(username);
      if (user == null) {
        return createExceptions(WSErrorType.WARNING, 10029, username);
      }
      List<IUserSubstitute> currentSubstitutes = new ArrayList<>(user.getSubstitutes());
      errors.addAll(saveUserSubstitutes(substitutes, application, user, currentSubstitutes));
      deleteUserSubstitutes(user, currentSubstitutes); // delete all substitutes not set
        return errors;
      });
  }

  private List<WSException> saveUserSubstitutes(final List<IvySubstitute> newSubstitutes, IApplication application,
      IUser user, List<IUserSubstitute> currentSubstitutes) {
    List<WSException> errors = new ArrayList<>();
    for (IvySubstitute ivySubstitute : newSubstitutes) {
      IUserSubstitute existingSubstitute = findByRole(ivySubstitute, currentSubstitutes);
      if (existingSubstitute != null) {
        currentSubstitutes.remove(existingSubstitute);
        if (StringUtils.isNotBlank(ivySubstitute.getMySubstitute())) {
          errors.addAll(updateSubstitute(application, user, existingSubstitute, ivySubstitute));
        } else {
          user.deleteSubstitute(existingSubstitute);
        }
      } else {
        if (StringUtils.isNotBlank(ivySubstitute.getMySubstitute())) {
          errors.addAll(createSubstitute(application, user, ivySubstitute));
        }
      }
    }
    return errors;
  }

  private void deleteUserSubstitutes(IUser user, List<IUserSubstitute> currentSubstitutes) {
    for (IUserSubstitute userSubstitute : currentSubstitutes) {
      user.deleteSubstitute(userSubstitute);
    }
  }

  /**
   * Create a substitute
   * 
   * @param application
   * @param user
   * @param ivySubstitute
   */
  private List<WSException> createSubstitute(IApplication application, IUser user, IvySubstitute ivySubstitute) {
    List<WSException> errors = new ArrayList<>();
    IUser substituteUser = application.getSecurityContext().findUser(ivySubstitute.getMySubstitute());
    if (substituteUser == null) {
      List<Object> userText = new ArrayList<>();
      userText.add(ivySubstitute.getMySubstitute());
      errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
    }
    IRole role = null;
    if (ivySubstitute.getForThisRole() != null) {
      for (IRole userRole : user.getRoles()) {
        if (userRole.getName().equals(ivySubstitute.getForThisRole())) {
          role = userRole;
          break;
        }
      }
      if (role == null) {
        List<Object> userText = new ArrayList<>();
        userText.add(ivySubstitute.getForThisRole());
        errors.add(new WSException(WSErrorType.WARNING, 10038, userText, null));
      }
    }
    if (!errors.isEmpty()) {
      return errors;
    }
    user.createSubstitute(substituteUser, role,
        (ivySubstitute.getDescription() == null ? "" : ivySubstitute.getDescription()));
    return errors;
  }

  /**
   * @param application
   * @param user
   * @param userSubstitute
   * @param ivySubstitute
   */
  private List<WSException> updateSubstitute(IApplication application, IUser user, IUserSubstitute userSubstitute,
      IvySubstitute ivySubstitute) {
    user.deleteSubstitute(userSubstitute);
    return createSubstitute(application, user, ivySubstitute);
  }

  /**
   * Find {@link IUserSubstitute} in a list has same role with passed {@link IvySubstitute}
   * 
   * @param ivySubstitute
   * @param substitutes
   * @return role matched {@link IUserSubstitute}, return null if not matched
   */
  private IUserSubstitute findByRole(IvySubstitute ivySubstitute, List<IUserSubstitute> substitutes) {
    if (ivySubstitute.getForThisRole() != null && StringUtils.isNotBlank(ivySubstitute.getForThisRole())) {
      for (IUserSubstitute userSubstitute : substitutes) {
        final IRole substitutionRole = userSubstitute.getSubstitutionRole();
        if (substitutionRole != null && StringUtils.equals(substitutionRole.getName(),ivySubstitute.getForThisRole())) {
          return userSubstitute;
        }
      }
    } else {
      for (IUserSubstitute userSubstitute : substitutes) {
        if (userSubstitute.getSubstitutionRole() == null) {
          return userSubstitute;
        }
      }
    }
    return null;
  }

  /**
   * Save absences for passed user in passed application
   * 
   * @param appName
   * @param username
   * @param absences : list of absences to save
   */
  private List<WSException> saveUserAbsence(final String appName, final String username, final List<IvyAbsence> absences) {
    return executeAsSystem(() -> {
      List<WSException> errors = new ArrayList<>();
      IApplication application = findApplication(appName);
      if (application == null) {
        return createExceptions(WSErrorType.WARNING, 10030, appName);
      }
      IUser user = application.getSecurityContext().findUser(username);
      if (user == null) {
        return createExceptions(WSErrorType.WARNING, 10029, username);
      }
      deleteUserAbsences(user);
      createUserAbsences(user, absences);
      return errors;
    });
  }

  private void createUserAbsences(IUser user, final List<IvyAbsence> absences) {
    if (absences != null && !absences.isEmpty()) {
      for (IvyAbsence absence : absences) {
        user.createAbsence(absence.getStartDateInclusive(), absence.getStopDateInclusive(), absence.getDescription());
      }
    }
  }

  private void deleteUserAbsences(IUser user) {
    List<IUserAbsence> currentAbsences = user.getAbsences();
    for (IUserAbsence absence : currentAbsences) {
      user.deleteAbsence(absence);
    }
  }
}
