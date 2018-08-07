package ch.ivy.addon.portalkit.service;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.service.ServiceException;

/**
 * This class is to implement method to get information of Ivy
 */
public class IvyAdapterService {
  private static final String PORTAL_CALL_WEBSERVICE_MAX_RETRY = "PortalCallWebserviceMaxRetry";

  /**
   * Get maximum time global variable that portal can retry to synchronize data to other portals, return 0 if global
   * variable is not defined or the value is not correct format
   * 
   * @return maximum times
   */
  public Integer getMaximumRetryPortalDataSynchonizationTimes() {
    String retryTimesLiteral = Ivy.var().get(PORTAL_CALL_WEBSERVICE_MAX_RETRY);
    try {
      return Integer.valueOf(retryTimesLiteral);
    } catch (NumberFormatException e) {
      String message = String.format("Value of global variable %s  is not number", PORTAL_CALL_WEBSERVICE_MAX_RETRY);
      Ivy.log().error(message, e);
    }
    return 0;
  }
  
  /**
   * Executes the given {@link Callable} as System-User.
   * 
   * @param callable The {@link Callable} to be executed.
   * 
   * @return The return value of the {@link Callable}.
   */
  public static <V> V executeCallableAsSystem(Callable<V> callable) {
    try {
      ISecurityManager securityManager = SecurityManagerFactory.getSecurityManager();
      V callableResult = (V) securityManager.executeAsSystem(callable);
      return callableResult;
    } catch (EnvironmentNotAvailableException e) {
      String message = "Environment not available.";
      Ivy.log().error(message);
      throw new ServiceException(message, e);
    } catch (NoSuchFieldException e) {
      String message = "Field not found.";
      Ivy.log().error(message);
      throw new ServiceException(message, e);
    } catch (Exception e) {
      String message = "There was an unspecific error.";
      Ivy.log().error(message);
      throw new ServiceException(message, e);
    }
  }

}
