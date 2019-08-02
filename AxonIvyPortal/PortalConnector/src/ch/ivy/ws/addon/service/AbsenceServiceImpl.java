package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserSubstitute;
public class AbsenceServiceImpl extends AbstractService implements IAbsenceService {

  @Override
  public AbsenceServiceResult getAbsences(final String username, final List<String> apps) throws WSException {
    try {
      return executeAsSystem(() -> {
          AbsenceServiceResult result = new AbsenceServiceResult();
          List<WSException> errors = new ArrayList<>();
          List<String> availableApps = new ArrayList<>();

          if (StringUtils.isNotBlank(username)) {
            AvailableAppsResult aaResult = findAvailableApplicationsForUser(apps, username);
            errors.addAll(aaResult.getErrors());
            availableApps.addAll(aaResult.getAvailableApps());

            List<Absence> absences = new ArrayList<Absence>();
            for (String applicationName : availableApps) {
              IApplication application = findApplication(applicationName);
              if (application != null) {
                IUser user = application.getSecurityContext().findUser(username);
                if (user != null && user.getAbsences().size() > 0) {
                  absences.add(new Absences().toAbsence(user, application));
                }
              }
            }
            result.setAbsences(absences);
          } else {
            errors.add(createException(WSErrorType.WARNING, 10029, username));
          }

          result.setErrors(errors);
          return result;
      });
    } catch (Exception e) {
      throw new WSException(10034, e);
    }
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
      if (StringUtils.isNotBlank(username)) {
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
          errors.addAll(saveUserAbsence(app, username, null));
        }

        // save absence at each app
        for (String app : absenceMap.keySet()) {
          errors.addAll(saveUserAbsence(app, username, absenceMap.get(app)));
        }
      } else {
        errors.add(createException(WSErrorType.WARNING, 10029, username));
      }
      result.setErrors(errors);
      return result;
    } catch (Exception e) {
      throw new WSException(10035, e);
    }
  }

  @Override
  public SubstituteServiceResult getSubstitutes(final String username, final List<String> apps) throws WSException {
    return StringUtils.isEmpty(username) ? substituteResultWithAnException(username) : getSubStituteOfUserOnApps(username, apps);
  }

  private SubstituteServiceResult getSubStituteOfUserOnApps(final String username, final List<String> apps)
      throws WSException {
    try {
      AvailableAppsResult availableApps = findAvailableApplicationsForUser(apps, username);
      SubstituteServiceResult substituteServiceResult =
          executeAsSystem(new GetSubstituteWebserviceCallable(username, availableApps.getAvailableApps()));
      substituteServiceResult.setErrors(availableApps.getErrors());
      return substituteServiceResult;
    } catch (Exception e) {
      throw new WSException(10036, e);
    }
  }

  private SubstituteServiceResult substituteResultWithAnException(final String username) {
    SubstituteServiceResult substituteServiceResult = new SubstituteServiceResult();
    substituteServiceResult.setErrors(Arrays.asList(createException(WSErrorType.WARNING, 10029, username)));
    return substituteServiceResult;
  }

  @Override
  public SubstituteServiceResult setSubstitutes(String username, List<IvySubstitute> substitutes) throws WSException {
    try {
      SubstituteServiceResult result = new SubstituteServiceResult();
      List<WSException> errors = new ArrayList<>();
      if (StringUtils.isNotBlank(username)) {
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
        for (String app : substitudeMap.keySet()) {
          saveUserSubstitute(app, username, substitudeMap.get(app));
        }
      } else {
        errors.add(createException(WSErrorType.WARNING, 10029, username));
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
   * @throws Exception
   */
  private List<WSException> saveUserSubstitute(final String appName, final String username,
      final List<IvySubstitute> substitutes) throws Exception {
    return executeAsSystem(() -> {
        List<WSException> errors = new ArrayList<>();
        IApplication application = findApplication(appName);
        if (application != null) {
          IUser user = application.getSecurityContext().findUser(username);
          if (user != null) {
            List<IUserSubstitute> currentSubstitutes = new ArrayList<>(user.getSubstitutes());

            for (IvySubstitute ivySubstitute : substitutes) {
              errors.addAll(mergeSubstitute(application, user, currentSubstitutes, ivySubstitute));
            }
            // delete all substitute not set
            for (IUserSubstitute userSubstitute : currentSubstitutes) {
              user.deleteSubstitute(userSubstitute);
            }
          } else {
            errors.add(createException(WSErrorType.WARNING, 10029, username));
          }
        } else {
          errors.add(createException(WSErrorType.WARNING, 10030, appName));
        }
        return errors;
    });
  }

  private List<WSException> mergeSubstitute(IApplication application, IUser user,
      List<IUserSubstitute> currentSubstitutes, IvySubstitute ivySubstitute) throws Exception {
    List<WSException> result = new ArrayList<>();
    
    IUserSubstitute existingSubstitute = findByRole(ivySubstitute, currentSubstitutes);
    if (existingSubstitute != null) {
      currentSubstitutes.remove(existingSubstitute);
      if (StringUtils.isNotBlank(ivySubstitute.getMySubstitute())) {
        result.addAll(updateSubstitute(application, user, existingSubstitute, ivySubstitute));
      } else {
        user.deleteSubstitute(existingSubstitute);
      }
    } else {
      if (StringUtils.isNotBlank(ivySubstitute.getMySubstitute())) {
        result.addAll(createSubstitute(application, user, ivySubstitute));
      }
    }
    return result;
  }

  /**
   * Create a substitute
   * 
   * @param application
   * @param user
   * @param ivySubstitute
   */
  private List<WSException> createSubstitute(IApplication application, IUser user, IvySubstitute ivySubstitute)
      throws Exception {
    List<WSException> errors = new ArrayList<>();
    IUser substituteUser = application.getSecurityContext().findUser(ivySubstitute.getMySubstitute());
    if (substituteUser == null) {
      errors.add(createException(WSErrorType.WARNING, 10029, ivySubstitute.getMySubstitute()));
    }
    IRole role = null;
    String roleName = ivySubstitute.getForThisRole();
    if (roleName != null) {
      role = CollectionUtils.emptyIfNull(user.getRoles())
          .stream()
          .filter(userRole -> StringUtils.equals(userRole.getName(),roleName))
          .findFirst()
          .orElse(null);
      if (role == null) {
        errors.add(createException(WSErrorType.WARNING, 10038, roleName));
      }
    }
    if (errors.size() > 0) {
      return errors;
    }
    user.createSubstitute(substituteUser, role, StringUtils.defaultString(ivySubstitute.getDescription()));
    return errors;
  }

  /**
   * @param application
   * @param user
   * @param userSubstitute
   * @param ivySubstitute
   * @throws Exception
   */
  private List<WSException> updateSubstitute(IApplication application, IUser user, IUserSubstitute userSubstitute,
      IvySubstitute ivySubstitute) throws Exception {
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
    IUserSubstitute returnUserSubstitute = null;
    if (StringUtils.isNotBlank(ivySubstitute.getForThisRole())) {
      returnUserSubstitute = CollectionUtils.emptyIfNull(substitutes)
          .stream()
          .filter(userSubstitute -> userSubstitute.getSubstitutionRole() != null 
          && userSubstitute.getSubstitutionRole().getName().equals(ivySubstitute.getForThisRole()))
          .findFirst()
          .orElse(null);
    } else {
      returnUserSubstitute = CollectionUtils.emptyIfNull(substitutes)
          .stream()
          .filter(userSubstitute -> userSubstitute.getSubstitutionRole() == null)
          .findFirst()
          .orElse(null);
    }
    return returnUserSubstitute;
  }

  /**
   * Save absences for passed user in passed application
   * 
   * @param appName
   * @param username
   * @param absences : list of absences to save
   * @throws Exception
   */
  private List<WSException> saveUserAbsence(final String appName, final String username, final List<IvyAbsence> absences)
      throws Exception {
    return executeAsSystem(() -> {
        List<WSException> errors = new ArrayList<>();
        IApplication application = findApplication(appName);
        if (application != null) {
          IUser user = application.getSecurityContext().findUser(username);
          if (user != null) {
            // delete all absence
            CollectionUtils.emptyIfNull(user.getAbsences())
              .forEach(absence -> user.deleteAbsence(absence));
            CollectionUtils.emptyIfNull(absences)
              .forEach(ivyAbsence -> user.createAbsence(ivyAbsence.getStartDateInclusive(), ivyAbsence.getStopDateInclusive(), ivyAbsence.getDescription()));
          } else {
            errors.add(createException(WSErrorType.WARNING, 10029, username));
          }
        } else {
          errors.add(createException(WSErrorType.WARNING, 10030, appName));
        }
        return errors;
    });
  }
}
