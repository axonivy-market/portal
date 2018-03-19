package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.SideStepServiceResult;
import ch.ivy.ws.addon.transformer.IvySideStepTransformer;
import ch.ivy.ws.addon.types.IvySideStep;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
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
          if(wfCase != null && searchCriteria.hasInvolvedUsername()){
	          IApplication application = wfCase.getApplication();
	          IUser user = application.getSecurityContext().findUser(searchCriteria.getInvolvedUsername());
	          ICaseMapService caseMapService = ICaseMapService.get().getCaseMapService(wfCase.getBusinessCase(), user.getUserToken());
	        	  List<IStartableSideStep> sideStepProcesses = caseMapService.findStartableSideSteps();
	        	  sideStepProcesses.forEach(process -> sideSteps.add(new IvySideStepTransformer(isUrlBuiltFromSystemProperties).transform(process)));
          }
          if (!searchCriteria.isAdhocExcluded()) {
            IvySideStep adhocSideStep = createAdhocSideStep(wfCase, isUrlBuiltFromSystemProperties);
            Optional.ofNullable(adhocSideStep).ifPresent(adhoc -> sideSteps.add(adhoc));
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

  public boolean hasSideSteps(ICase wfCase, boolean isAdhocIncluded) throws Exception {
    if (isAdhocIncluded) {
      IvySideStep adhocSideStep = createAdhocSideStep(wfCase, false);
      if (adhocSideStep != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return null if cannot find adhoc, otherwise return adhoc process
   */
  private IvySideStep createAdhocSideStep(ICase wfCase, boolean isUrlBuiltFromSystemProperties) throws Exception {
    if (wfCase != null){
      IApplication application = wfCase.getApplication();
      ProcessStartCollector collector = new ProcessStartCollector(application);
      String acmLink = collector.findACMLink();
      if (StringUtils.EMPTY.equals(acmLink)) {
        return null;
      }
      IvySideStep adhoc = new IvySideStep();
      adhoc.setName(Ivy.cms().co("/ch/ivy/addon/portalconnector/sidestep/addAdhocTask"));
      adhoc.setStartRequestUri(ServerUrlUtils.getStartLink(acmLink, isUrlBuiltFromSystemProperties));
      adhoc.setIsAdhoc(true);
      return adhoc;
    } else {
      return null;
    }
  }
}
