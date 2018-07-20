package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

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
          List<WSException> errors = new ArrayList<WSException>();
          List<String> availableApps = new ArrayList<String>();

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
            // Username not given
            List<Object> userText = new ArrayList<Object>();
            userText.add(username);
            errors.add(new WSException(WSErrorType.WARNING, 10039, userText, null));
          }

          result.setErrors(errors);
          return result;
        }
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
      List<WSException> errors = new ArrayList<WSException>();
      if (username != null && !username.trim().equals("")) {
        // app list of absences
        Map<String, List<IvyAbsence>> absenceMap = new HashMap<String, List<IvyAbsence>>();
        for (IvyAbsence ivyAbsence : absences) {
          List<IvyAbsence> absList = absenceMap.get(ivyAbsence.getAppName());
          if (absList == null) {
            absList = new ArrayList<IvyAbsence>();
            absenceMap.put(ivyAbsence.getAppName(), absList);
          }
          absList.add(ivyAbsence);
        }
        // delete all absences in passed apps
        for (String app : apps) {
          List<WSException> errorlist = saveUserAbsence(app, username, null);
          if (errorlist.size() > 0) {
            errors.addAll(errorlist);
          }
        }

        // save absence at each app
        for (String app : absenceMap.keySet()) {
          List<WSException> errorlist = saveUserAbsence(app, username, absenceMap.get(app));
          if (errorlist.size() > 0) {
            errors.addAll(errorlist);
          }
        }
      } else {
        // Username not given
        List<Object> userText = new ArrayList<Object>();
        userText.add(username);
        errors.add(new WSException(WSErrorType.WARNING, 10039, userText, null));
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
    substituteServiceResult.setErrors(Arrays.asList(new WSException(WSErrorType.WARNING, 10039,
        Arrays.asList(username), null)));
    return substituteServiceResult;
  }

  @Override
  public SubstituteServiceResult setSubstitutes(String username, List<IvySubstitute> substitutes) throws WSException {
    try {
      SubstituteServiceResult result = new SubstituteServiceResult();
      List<WSException> errors = new ArrayList<WSException>();
      if (username != null && !username.trim().equals("")) {
        Map<String, List<IvySubstitute>> substitudeMap = new HashMap<String, List<IvySubstitute>>();// group
                                                                                                    // list
                                                                                                    // of
                                                                                                    // substitutes
                                                                                                    // by
                                                                                                    // appName
        for (IvySubstitute ivySubstitute : substitutes) {
          List<IvySubstitute> subsList = substitudeMap.get(ivySubstitute.getAppName());
          if (subsList == null) {
            subsList = new ArrayList<IvySubstitute>();
            substitudeMap.put(ivySubstitute.getAppName(), subsList);
          }
          subsList.add(ivySubstitute);
        }

        // save substitute at each app
        for (String app : substitudeMap.keySet()) {
          saveUserSubstitute(app, username, substitudeMap.get(app));
        }
      } else {
        // Username not given
        List<Object> userText = new ArrayList<Object>();
        userText.add(username);
        errors.add(new WSException(WSErrorType.WARNING, 10039, userText, null));
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
    return executeAsSystem(new Callable<List<WSException>>() {
      @Override
      public List<WSException> call() throws Exception {
        List<WSException> errors = new ArrayList<WSException>();
        IApplication application = findApplication(appName);
        if (application != null) {
          IUser user = application.getSecurityContext().findUser(username);
          if (user != null) {
            List<IUserSubstitute> currentSubstitutes = new ArrayList<IUserSubstitute>(user.getSubstitutes());

            for (IvySubstitute ivySubstitute : substitutes) {

              IUserSubstitute existingSubstitute = findByRole(ivySubstitute, currentSubstitutes);

              if (existingSubstitute != null) {
                currentSubstitutes.remove(existingSubstitute);
                if (!StringUtils.isBlank(ivySubstitute.getMySubstitute())) {
                  errors.addAll(updateSubstitute(application, user, existingSubstitute, ivySubstitute));
                } else {
                  user.deleteSubstitute(existingSubstitute);
                }
              } else {
                if (!StringUtils.isBlank(ivySubstitute.getMySubstitute())) {
                  errors.addAll(createSubstitute(application, user, ivySubstitute));
                }
              }
            }
            // delete all substitu not set
            for (IUserSubstitute userSubstitute : currentSubstitutes) {
              user.deleteSubstitute(userSubstitute);
            }
          } else {
            List<Object> userText = new ArrayList<Object>();
            userText.add(username);
            errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
          }
        } else {
          List<Object> userText = new ArrayList<Object>();
          userText.add(appName);
          errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
        }
        return errors;
      }
    });
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
    List<WSException> errors = new ArrayList<WSException>();
    IUser substituteUser = application.getSecurityContext().findUser(ivySubstitute.getMySubstitute());
    if (substituteUser == null) {
      List<Object> userText = new ArrayList<Object>();
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
        List<Object> userText = new ArrayList<Object>();
        userText.add(ivySubstitute.getForThisRole());
        errors.add(new WSException(WSErrorType.WARNING, 10038, userText, null));
      }
    }
    if (errors.size() > 0) {
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
    if (ivySubstitute.getForThisRole() != null && !StringUtils.isEmpty(ivySubstitute.getForThisRole().trim())) {
      for (IUserSubstitute userSubstitute : substitutes) {
        if (userSubstitute.getSubstitutionRole() != null
            && userSubstitute.getSubstitutionRole().getName().equals(ivySubstitute.getForThisRole())) {
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
   * @throws Exception
   */
  private List<WSException> saveUserAbsence(final String appName, final String username, final List<IvyAbsence> absences)
      throws Exception {
    return executeAsSystem(new Callable<List<WSException>>() {
      @Override
      public List<WSException> call() throws Exception {
        List<WSException> errors = new ArrayList<WSException>();
        IApplication application = findApplication(appName);
        if (application != null) {
          IUser user = application.getSecurityContext().findUser(username);
          if (user != null) {
            // delete all absence
            List<IUserAbsence> currentAbsences = user.getAbsences();
            for (IUserAbsence absence : currentAbsences) {
              user.deleteAbsence(absence);
            }
            if (absences != null && absences.size() > 0) {
              // add new absences
              for (IvyAbsence ivyAbsence : absences) {
                user.createAbsence(ivyAbsence.getStartDateInclusive(), ivyAbsence.getStopDateInclusive(),
                    ivyAbsence.getDescription());
              }
            }
          } else {
            List<Object> userText = new ArrayList<Object>();
            userText.add(username);
            errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
          }

        } else {
          List<Object> userText = new ArrayList<Object>();
          userText.add(appName);
          errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
        }
        return errors;
      }
    });
  }

}
