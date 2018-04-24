package ch.ivy.gawfs.portalKitCopies;

import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IQueryResult;
import ch.ivyteam.ivy.persistence.OrderDirection;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.ivy.workflow.PropertyOrder;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.logicalexpression.RelationalOperator;

/**
 * This class is to build some function related to case such as find finish cases, find running case, filter by
 * category.
 */

@SuppressWarnings("deprecation")
public final class CaseUtils {

  private CaseUtils() {

  }

  /**
   * This function is o find the running case of session user by process category
   * 
   * @param category String Process Category of Case
   * @return List of ICase List of ICase
   */
  public static List<ICase> findICasesRunningOfSessionUserByCategory(String category) {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());
    filter = filter.and(CaseProperty.PROCESS_CATEGORY_CODE, RelationalOperator.EQUAL_IGNORE_CASE, category);

    List<ICase> cases = findICasesOfSessionUser(filter);
    return cases;
  }

  /**
   * This function is o find the running case by process category
   * 
   * @param category String Process Category of Case
   * @return List of ICase List of ICase
   */
  public static List<ICase> findICasesRunningByCategory(String category) {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());
    filter = filter.and(CaseProperty.PROCESS_CATEGORY_CODE, RelationalOperator.EQUAL_IGNORE_CASE, category);

    List<ICase> cases = findICases(filter);
    return cases;
  }

  /**
   * This function is o find the finished case of session user by process category
   * 
   * @param category String Process Category of Case
   * @return List of ICase List of ICase
   */
  public static List<ICase> findICasesFinishedOfSessionUserByCategory(String category) {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());
    filter = filter.and(CaseProperty.PROCESS_CATEGORY_CODE, RelationalOperator.EQUAL_IGNORE_CASE, category);

    List<ICase> cases = findICasesOfSessionUser(filter);
    return cases;
  }

  /**
   * This function is o find the finished case by process category
   * 
   * @param category String Process Category of Case
   * @return List of ICase List of ICase
   */
  public static List<ICase> findICasesFinishedByCategory(String category) {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());
    filter = filter.and(CaseProperty.PROCESS_CATEGORY_CODE, RelationalOperator.EQUAL_IGNORE_CASE, category);

    List<ICase> cases = findICases(filter);
    return cases;
  }

  /**
   * This function is o find all running cases of session user.
   * 
   * @return List of CaseVO List of ICase
   */
  public static List<ICase> findICasesRunningOfSessionUser() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());

    List<ICase> cases = findICasesOfSessionUser(filter);
    return cases;
  }

  /**
   * This function is o find all running cases.
   * 
   * @return List of CaseVO List of ICase
   */
  public static List<ICase> findICasesRunning() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());

    List<ICase> cases = findICases(filter);
    return cases;
  }

  /**
   * This function is o find all finished cases of session user.
   * 
   * @return List of CaseVO List of ICase
   */
  public static List<ICase> findICasesFinishedOfSessionUser() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());

    List<ICase> cases = findICasesOfSessionUser(filter);
    return cases;
  }

  /**
   * This function is o find all finished cases.
   * 
   * @return List of CaseVO List of ICase
   */
  public static List<ICase> findICasesFinished() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());

    List<ICase> cases = findICases(filter);
    return cases;
  }


  /**
   * Get list of {@link ICase} of session user by {@link IPropertyFilter}
   * 
   * @param filter object as {@link IPropertyFilter}
   * @return list {@link ICase}
   */
  public static List<ICase> findICasesOfSessionUser(IPropertyFilter<CaseProperty> filter) {
    List<ICase> cases = Ivy.session().findInvolvedCases(filter, null, 0, -1, true).getResultList();
    return cases;
  }

  /**
   * Get list of {@link ICase} by {@link IPropertyFilter}
   * 
   * @param filter object as {@link IPropertyFilter}
   * @return list {@link ICase}
   */
  public static List<ICase> findICases(IPropertyFilter<CaseProperty> filter) {
    boolean hasReadAllCasesPermission = checkReadAllCasesPermission();
    List<ICase> cases;
    if (!hasReadAllCasesPermission) {
      cases = Ivy.session().findInvolvedCases(filter, null, 0, -1, true).getResultList();
    } else {
      cases = Ivy.wf().findCases(filter, null, 0, -1, true).getResultList();
    }
    return cases;
  }

  /**
   * Get email address of creator user in ICase object
   * 
   * @param iCase ICase object to get the email
   * @return String get the email of creator of case, execute under system permission
   */
  public static String getEmailAddress(final ICase iCase) {

    if (iCase != null && iCase.getCreatorUser() != null) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          @Override
          public String call() {
            try {
              return iCase.getCreatorUser().getEMailAddress();
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Get email address of creator user in ICase object
   * 
   * @param iCase ICase object to get the email
   * @return String get the email of creator of case, execute under system permission
   */
  public static String getMobile(final ICase iCase) {

    if (iCase != null && iCase.getCreatorUser() != null) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          @Override
          public String call() {
            try {
              return iCase.getCreatorUser().getProperty(UserUtils.MOBILE);
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Get email address of creator user in ICase object
   * 
   * @param iCase ICase object to get the email
   * @return String get the email of creator of case, execute under system permission
   */
  public static String getPhone(final ICase iCase) {

    if (iCase != null && iCase.getCreatorUser() != null) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          @Override
          public String call() {
            try {
              return iCase.getCreatorUser().getProperty(UserUtils.PHONE);
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Find case by specified caseId
   * 
   * @param caseId case Id
   * @return ICase
   */
  public static ICase getCaseById(final Integer caseId) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<ICase>() {
        @Override
        public ICase call() {
          try {
            return Ivy.wf().findCase(new Long(caseId));
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }

  }

  /**
   * Get display name if provider both full name and name, if full name is empty then return user name, otherwise return
   * full name
   * 
   * @param fullName String, name : string,
   * @param name String user name
   * @return String display name
   */
  public static String getDisplayNameInFormat(String fullName, String name) {
    if (fullName != null && !fullName.isEmpty()) {
      return fullName;
    } else {
      return name;
    }
  }

  /**
   * Set case main contact folder id for case
   * 
   * @param iCase ICase need to set addition field
   * @param value String value to set in case details process
   * @return boolean true is set success, other wise it return false, execute under system permission
   */
  public static boolean setCaseMainContactFolderId(final ICase iCase, final String value) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Boolean>() {
        @Override
        public Boolean call() {
          try {
            iCase.setBusinessMainContactFolderId(value);
            return true;
          } catch (Exception e) {
            Ivy.log().error(e);
            return false;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }


  /**
   * Check if current session has read all cases permission
   * 
   * @return hasReadAkkCasesPermission True : has read all cases permission, False : do not have read all cases
   *         permission
   */
  private static boolean checkReadAllCasesPermission() {
    ISecurityContext securityContext = Ivy.session().getSecurityContext();
    ISecurityDescriptor securityDescriptor = Ivy.request().getApplication().getSecurityDescriptor();
    boolean hasReadAllCasesPermission = securityContext.hasPermission(securityDescriptor, IPermission.CASE_READ_ALL);
    return hasReadAllCasesPermission;
  }

  /**
   * Find the case by case query
   * 
   * @param caseQuery
   * @return Recordset cases list fiter by case query
   */
  public static Recordset findcases(final CaseQuery caseQuery) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Recordset>() {
        @Override
        public Recordset call() throws Exception {
          return Ivy.wf().getCaseQueryExecutor().getRecordset(caseQuery);
        }
      });

    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * Find the case by case query
   * 
   * @param id
   * @return ICase case list fiter by case query
   */
  public static ICase findcase(final long id) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<ICase>() {
        @Override
        public ICase call() throws Exception {
          return Ivy.wf().findCase(id);
        }
      });

    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * Find the case by case query
   * 
   * @param caseFilter
   * @return IQueryResult<ICase>
   */
  public static IQueryResult<ICase> findcases(final IPropertyFilter<CaseProperty> caseFilter) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IQueryResult<ICase>>() {
        @Override
        public IQueryResult<ICase> call() throws Exception {
          return Ivy.wf().findCases(caseFilter, PropertyOrder.create(CaseProperty.ID, OrderDirection.DESCENDING), 0, 1,
              true);
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }
}
