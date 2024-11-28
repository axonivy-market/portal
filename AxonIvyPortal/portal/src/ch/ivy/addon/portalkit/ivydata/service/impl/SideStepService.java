package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;
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

  public List<IvySideStep> findSideStepsByCriteria(SideStepSearchCriteria criteria) {
    return Sudo.get(() -> { 
      ICase iCase = findCase(criteria.getCaseId());
      if (iCase == null) {
        return null;
      }
      
      List<IStartableSideStep> startableSideSteps = getSideSteps(iCase);
      List<IvySideStep> ivySideSteps = startableSideSteps.stream().map(this::toIvySideStep).collect(Collectors.toList());
      
      ivySideSteps.sort((s1, s2) -> StringUtils.compareIgnoreCase(s1.getName(), s2.getName()));
      return ivySideSteps;
    });
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
