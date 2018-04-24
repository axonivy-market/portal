package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.vo.CaseVO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IQueryResult;
import ch.ivyteam.ivy.persistence.OrderDirection;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IProcessStart;
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

  /** default key to store case details process start link in ICase object */
  public static final String CASE_DETAIL_PROCESS = "CASE_DETAIL_PROCESS";
  private static String fullNameFormat = "%s (%s)";
  private static final String HIDE = "HIDE";

  /**
   * This function is o find the running case of session user by process category
   * 
   * @param category String Process Category Code of Case
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesRunningOfSessionUserByCategory(String category) {
    List<ICase> cases = findICasesRunningOfSessionUserByCategory(category);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find the running case by process category
   * 
   * @param category String Process Category Code of Case
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesRunningByCategory(String category) {
    List<ICase> cases = findICasesRunningByCategory(category);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find the finished case of session user by process category
   * 
   * @param category String Process Category Code of Case
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesFinishedOfSessionUserByCategory(String category) {
    List<ICase> cases = findICasesFinishedOfSessionUserByCategory(category);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find the finished case by process category
   * 
   * @param category String Process Category Code of Case
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesFinishedByCategory(String category) {
    List<ICase> cases = findICasesFinishedByCategory(category);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
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
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesRunningOfSessionUser() {
    List<ICase> cases = findICasesRunningOfSessionUser();
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find all running cases.
   * 
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesRunning() {
    List<ICase> cases = findICasesRunning();
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find all running, finished and destroyed cases of session user.
   * 
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesNotZombieOfSessionUser() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DESTROYED.intValue());

    List<ICase> cases = findICasesOfSessionUser(filter);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find all running, finished and destroyed cases.
   * 
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesNotZombie() {
    IPropertyFilter<CaseProperty> filter =
        Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.CREATED.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE.intValue());
    filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DESTROYED.intValue());

    List<ICase> cases = findICases(filter);
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find all finished cases of session user.
   * 
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesFinishedOfSessionUser() {
    List<ICase> cases = findICasesFinishedOfSessionUser();
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
  }

  /**
   * This function is o find all finished cases
   * 
   * @return List of CaseVO List of case vo
   */
  public static List<CaseVO> findCasesFinished() {
    List<ICase> cases = findICasesFinished();
    List<CaseVO> result = convertICasesToCaseVOs(cases);
    return result;
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
    boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
    List<ICase> cases;
    if (!hasReadAllCasesPermission) {
      cases = Ivy.session().findInvolvedCases(filter, null, 0, -1, true).getResultList();
    } else {
      cases = Ivy.wf().findCases(filter, null, 0, -1, true).getResultList();
    }
    return cases;
  }

  /**
   * Convert ICase object to CaseVO object
   * 
   * @param iCase ICase to convert
   * @return CaseVO object hold data of ICase
   */
  public static CaseVO convertToCaseVO(ICase iCase) {
    CaseVO caseVO = new CaseVO();
    if (iCase != null){
      String caseDetailStartProcess = getCaseDetailProcess(iCase);
      caseVO.setProcessCaseDetails(caseDetailStartProcess);
      caseVO.setCreatedAt(iCase.getStartTimestamp());
      caseVO.setId(iCase.getId());
      caseVO.setDescription(iCase.getDescription());
      caseVO.setTitle(iCase.getName());
      CaseState state = Optional.ofNullable(iCase).map(ICase::getState).orElse(null);
      if (CaseState.CREATED.equals(state)) {
        caseVO.setStatus(CaseState.RUNNING.name());
      } else {
        caseVO.setStatus(state == null ? null : state.name());
      }
      if (iCase.getCreatorUser() != null && iCase.getCreatorUser().getFullName() != null) {
        caseVO.setCreator(String.format(fullNameFormat, iCase.getCreatorUser().getFullName(), iCase.getCreatorUser()
            .getName()));
      } else {
        caseVO.setCreator(iCase.getCreatorUserName());
      }
      Contact contact = new Contact(getEmailAddress(iCase));
      String phone = getPhone(iCase);
      if (phone != null) {
        contact.setPhone(phone);
      }
      String mobile = getMobile(iCase);
      if (mobile != null) {
        contact.setMobilePhone(mobile);
      }
  
      caseVO.setCreatorContact(contact);
    } 
    return caseVO;
  }


  /**
   * Get the case Details process start link by key CASE_DETAILS_PROCESS in additional property
   * 
   * @param iCase ICase object
   * @return String the start link of case details
   */
  public static String getCaseDetailProcess(ICase iCase) {
    if (iCase == null || iCase.getAdditionalProperty(CASE_DETAIL_PROCESS) == null) {
      return "";
    }
    return iCase.getAdditionalProperty(CASE_DETAIL_PROCESS);
  }

  /**
   * Destroy the case with specified caseVO
   * 
   * @param caseVO caseVO need to destroy
   * @return boolean true is destroy success, other wise it return false, execute under system permission
   */
  public static boolean deleteCase(final CaseVO caseVO) {
    final List<ICase> cases =
        Ivy.wf()
            .findCases(Ivy.wf().createCasePropertyFilter(CaseProperty.ID, RelationalOperator.EQUAL, caseVO.getId()),
                null, 0, -1, true).getResultList();
    if (cases != null && cases.size() > 0) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Boolean>() {
          @Override
          public Boolean call() {
            try {
              ICase ic = cases.get(0);
              ic.destroy();
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
    } else {
      return false;
    }
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
   * Set case details process for case
   * 
   * @param iCase ICase need to set addition field
   * @param value String value to set in case details process
   * @return boolean true is set success, other wise it return false, execute under system permission
   */
  public static boolean setCaseDetailsProcess(final ICase iCase, final String value) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Boolean>() {
        @Override
        public Boolean call() {
          try {
            iCase.setAdditionalProperty(CASE_DETAIL_PROCESS, value);
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
   * Converts list of {@link ICase} to list of {@link CaseVO}
   * 
   * @param iCases list of {@link ICase}
   * @return list of {@link CaseVO}
   */
  public static List<CaseVO> convertICasesToCaseVOs(List<ICase> iCases) {
    List<CaseVO> caseVOs = new ArrayList<CaseVO>();
    if (iCases != null && iCases.size() > 0) {
      for (ICase iCase : iCases) {
        if (iCase != null)
          caseVOs.add(convertToCaseVO(iCase));
      }
    }
    return caseVOs;
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
  
  /**
   * Sets the "HIDE" additional property to the given case to hide it in case list of Portal.
   * @param iCase
   */
  public static void setHidePropertyToHideInPortal(ICase iCase) {
    iCase.setAdditionalProperty(HIDE, HIDE);
  }
  
  /**
   * Removes the "HIDE" additional property to the given case to display it in case list of Portal.
   * @param iCase
   */
  public static void removeHidePropertyToDisplayInPortal(ICase iCase) {
    iCase.setAdditionalProperty(HIDE, null);
  }
  
  public static String getProcessStartUriWithCaseParameters(RemoteCase remoteCase, String requestPath) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    String urlParameters = "?caseId=" + remoteCase.getId() + "&serverId=" + remoteCase.getServer().getId();
    try {
      return collector.findLinkByFriendlyRequestPath(requestPath) + urlParameters;
    } catch (Exception e) {
      Ivy.log().error(e);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(requestPath);
      return RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString() + urlParameters;
    }
  }
}
