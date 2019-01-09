package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.IProcessService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService implements IProcessService {

  private static final String PORTAL_START_REQUEST_PATH = "/PortalStart.ivp";

  private ProcessService() {}

  public static ProcessService newInstance() {
    return new ProcessService();
  }

  @Override
  public IvyProcessResultDTO findProcesses(ProcessSearchCriteria criteria) throws Exception {
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyProcessResultDTO result = new IvyProcessResultDTO();
      if (criteria == null || CollectionUtils.isEmpty(criteria.getApps())) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IWebStartable> webStartables = new ArrayList<>();
      criteria.getApps().stream().forEach(app -> {
        try {
          IApplication application = ServiceUtilities.findApp(app);
          IUser user = ServiceUtilities.findUser(criteria.getUsername(), application);
          webStartables.addAll(findStartablesWithoutPortalHome(user));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting processes of user {0} within app {1}", ex, criteria.getUsername(), app);
          errors.add(new PortalIvyDataException(app, PortalIvyDataErrorType.FAIL_TO_LOAD_PROCESS.toString()));
        }
      });
      result.setErrors(errors);
      result.setProcesses(webStartables);
      return result;
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHome(IUser user) {
    return Ivy.wf().getStartables(user).stream()
        .filter(process -> !process.getLink().getAbsoluteEncoded().endsWith(PORTAL_START_REQUEST_PATH))
        .collect(Collectors.toList());
  }
}
