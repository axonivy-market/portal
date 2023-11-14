package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.dto.IvySideStepResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.ICaseQueryExecutor;

public class SideStepService {

  private SideStepService() {}

  public static SideStepService newInstance() {
    return new SideStepService();
  }

  public IvySideStepResultDTO findSideStepsByCriteria(SideStepSearchCriteria criteria) {
    return Sudo.get(() -> { 
      IvySideStepResultDTO result = new IvySideStepResultDTO();
      ICase iCase = findCase(criteria.getCaseId());
      if (iCase == null) {
        return null;
      }
      
      List<IStartableSideStep> startableSideSteps = getSideSteps(iCase);
      List<IvySideStep> ivySideSteps = startableSideSteps.stream().map(this::toIvySideStep).collect(Collectors.toList());
      
      if (!criteria.isAdhocExcluded()) {
        IvySideStep adhocSideStep = createAdhocSideStep();
        if (adhocSideStep != null) {
          ivySideSteps.add(adhocSideStep);
        }
      }
      
      ivySideSteps.sort((s1, s2) -> StringUtils.compareIgnoreCase(s1.getName(), s2.getName()));
      result.setSideSteps(ivySideSteps);
      return result;
    });
  }

  private IvySideStep createAdhocSideStep() {
    String expressAdhocWFLink = ExpressProcessService.getInstance().findExpressAdhocWFLink();
    if (StringUtils.isBlank(expressAdhocWFLink)) {
      return null;
    }
    
    IvySideStep adhoc = new IvySideStep();
    adhoc.setName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/sidestep/addAdhocTask"));
    adhoc.setStartLink(expressAdhocWFLink);
    adhoc.setAdhoc(true);
    return adhoc;
  }

  private IvySideStep toIvySideStep(IStartableSideStep startableSideStep) {
    IvySideStep ivySideStep = new IvySideStep();
    ivySideStep.setName(startableSideStep.getName());
    ivySideStep.setStartLink(startableSideStep.getStartLink().getRelativeEncoded());
    return ivySideStep;
  }

  private List<IStartableSideStep> getSideSteps(ICase iCase) {
    IUser user = Ivy.session().getSessionUser();
    ICaseMapService caseMapService =
        ICaseMapService.current().getCaseMapService(iCase.getBusinessCase(), user.getUserToken());
    return caseMapService.findStartableSideSteps();
  }

  private ICase findCase(Long caseId) {
    ICaseQueryExecutor caseQueryExecutor = Ivy.wf().getCaseQueryExecutor();
    return caseQueryExecutor.createCaseQuery().where().caseId().isEqual(caseId).executor().firstResult();
  }
}
