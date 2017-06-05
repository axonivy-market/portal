package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.SideStepServiceResult;
import ch.ivy.ws.addon.types.IvySideStep;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.ICaseQueryExecutor;

/**
 * Default implementation for the side step service
 */
public class SideStepServiceImpl extends AbstractService implements ISideStepService {

  @Override
  public SideStepServiceResult findSideStepsByCriteria(SideStepSearchCriteria searchCriteria, String language,
      Boolean isUrlBuiltFromSystemProperties) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<SideStepServiceResult>() {
        @Override
        public SideStepServiceResult call() throws Exception {
          Ivy.session().setContentLocale(new Locale(language));
          final ICaseQueryExecutor caseQueryExecutor = Ivy.wf().getGlobalContext().getCaseQueryExecutor();
          ICase wfCase =
              caseQueryExecutor.getFirstResult(caseQueryExecutor.createCaseQuery().where().caseId()
                  .isEqual(searchCriteria.getCaseId()));
          List<IvySideStep> sideSteps = new ArrayList<>();
          if (!searchCriteria.isAdhocExcluded()) {
            sideSteps.add(createAdhocSideStep(wfCase));
          }
          SideStepServiceResult result = new SideStepServiceResult();
          result.setSideSteps(sideSteps);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10046, e);
    }
  }

  private IvySideStep createAdhocSideStep(ICase wfCase) throws Exception {
    IApplication application = wfCase.getApplication();
    ProcessStartCollector collector = new ProcessStartCollector(application);
    String acmLink = collector.findACMLink();
    IvySideStep adhoc = new IvySideStep();
    adhoc.setName(Ivy.cms().co("/ch/ivy/addon/portalconnector/sidestep/addAdhocTask"));
    adhoc.setStartLink(acmLink);
    adhoc.setStartRequestUri("add-adhoc-task");
    return adhoc;
  }
}
