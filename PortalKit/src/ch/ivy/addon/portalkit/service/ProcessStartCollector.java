package ch.ivy.addon.portalkit.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

public class ProcessStartCollector {

  private final IApplication application;

  public ProcessStartCollector(IApplication application) {
    this.application = application;
  }

  public IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    if (isActive(this.application)) {
      List<IProcessModel> processModels = this.application.getProcessModelsSortedByName();

      for (IProcessModel processModel : processModels) {

        if (isActive(processModel)) {
          IProcessModelVersion processModelVersion = processModel.getReleasedProcessModelVersion();

          if (isActive(processModelVersion)) {
            IWorkflowProcessModelVersion workflowPmv =
                WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
            IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
            if (processStart != null) {
              return processStart;
            }
          }
        }
      }
    }
    return null;
  }

  public String findACMLink() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process =
            collector.findProcessStartByUserFriendlyRequestPath("BusinessProcesses/AdHocWF/start.ivp");
        if (process != null) {
          URI processUri =
              RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(),
                  process);
          return getServerUrl() + processUri.toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }

  private String getServerUrl() {
    UrlDetector urlDetector = new UrlDetector();
    String serverUrl = StringUtils.EMPTY;
    try {
      String baseUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      serverUrl = urlDetector.getHost(baseUrl);
    } catch (MalformedURLException e) {
      Ivy.log().error(e);
    }
    return serverUrl;
  }

  private boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && ActivityState.ACTIVE.equals(processModelVersion.getActivityState());
  }

  private boolean isActive(IProcessModel processModel) {
    return ActivityState.ACTIVE.equals(processModel.getActivityState());
  }

  private boolean isActive(IApplication application) {
    return ActivityState.ACTIVE.equals(application.getActivityState());
  }
}
