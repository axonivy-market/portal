package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.ISubProcessStart;
import ch.ivyteam.ivy.process.call.SubProcessRunner;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.Builder;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.service.ServiceException;

/**
 * This class is to implement method to get information of Ivy
 */
public class IvyAdapterService {
  private static final String PORTAL_CALL_WEBSERVICE_MAX_RETRY = "PortalCallWebserviceMaxRetry";

  /**
   * Get maximum time global variable that portal can retry to synchronize data to other portals,
   * return 0 if global variable is not defined or the value is not correct format
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

  /**
   * Calls the sub process with the given subProcessSignature with the given parameters. Exactly one
   * sub process with the given signature is expected.
   * 
   * @param subProcessSignature The signature of the sub process to be triggered.
   * @param paramters The parameters to pass to the process.
   * @return The response of the process execution.
   */
  public static Map<String, Object> startSubProcess(String subProcessSignature, Map<String, Object> parameters) {
    FindSubProcessStartCallable findSubProcessStartCallable = new FindSubProcessStartCallable(subProcessSignature);
    ISubProcessStart subProcessStart = executeCallableAsSystem(findSubProcessStartCallable);

    SubProcessCallerCallable subprocessCallable = new SubProcessCallerCallable(subProcessStart, parameters);
    Map<String, Object> response = executeCallableAsSystem(subprocessCallable);

    return response;
  }

  /**
   * {@link Callable} that finds an {@link ISubProcessStart}.
   */
  public static class FindSubProcessStartCallable implements Callable<ISubProcessStart> {

    private final static String PORTALKIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
    private String subprocessSignature;

    public FindSubProcessStartCallable(String subprocessSignature) {
      this.subprocessSignature = subprocessSignature;
    }

    /**
     * {@inheritDoc}<br />
     * <b>This implementation:</b><br />
     * Finds a {@link ISubProcessStart} with the given subprocess signature.
     */
    @Override
    public ISubProcessStart call() {
      Builder subprocessFilter = SubProcessSearchFilter.create();
      SubProcessSearchFilter filter =
          subprocessFilter.setSignature(subprocessSignature).setSearchInAllProjects(true)
              .setSearchInDependentProjects(false).toFilter();
      ISubProcessStart subProcessStart = findSubprocess(filter);
      return subProcessStart;
    }

    private ISubProcessStart findSubprocess(SubProcessSearchFilter filter) {
      List<ISubProcessStart> subProcessStarts = SubProcessRunner.findSubProcessStarts(filter);
      ISubProcessStart foundSubProcessStart = null;
      ISubProcessStart subProcessStartOfPortalKit = null;
      for (ISubProcessStart subProcessStart : subProcessStarts) {
        String libraryId = subProcessStart.getProcessModelVersion().getLibrary().getId();
        if (!PORTALKIT_LIBRARY.equals(libraryId)) {
          if (subProcessStart.getProcessModelVersion().equals(Ivy.request().getProcessModelVersion())) {
            return subProcessStart;
          }
          foundSubProcessStart = subProcessStart;
        } else {
          subProcessStartOfPortalKit = subProcessStart;
        }
      }
      return foundSubProcessStart != null ? foundSubProcessStart : subProcessStartOfPortalKit;
    }
  }

  /**
   * Calls a given sub process with parameters.
   */
  public static class SubProcessCallerCallable implements Callable<Map<String, Object>> {
    private ISubProcessStart subProcessStart;
    private Map<String, Object> parameters;

    public SubProcessCallerCallable(ISubProcessStart subProcessStart, Map<String, Object> parameters) {
      this.subProcessStart = subProcessStart;
      this.parameters = parameters;
    }

    /**
     * {@inheritDoc}<br />
     * <b>This implementation:</b><br />
     * Executes the given sub process with the given parameters.
     */
    @Override
    public Map<String, Object> call() {
      try {
        Map<String, Object> response = SubProcessRunner.execute(subProcessStart, parameters);
        return response;
      } catch (Exception e) {
        String message = "Unable to execute subprocess.";
        Ivy.log().error(message, e);
        throw new ServiceException(message, e);
      }
    }
  }
}
