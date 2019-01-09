package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class SideStepBean {

  private Map<Long, List<IvySideStep>> sideStepsByCase = new HashMap<>();
  
  public boolean hasSideSteps(Long caseId, boolean isAdhocExcluded) {
    return CollectionUtils.isNotEmpty(getSideSteps(caseId, isAdhocExcluded));
  }
  
  public List<IvySideStep> getSideSteps(Long caseId, boolean isAdhocExcluded) {
    if (sideStepsByCase.containsKey(caseId)) {
      return sideStepsByCase.get(caseId);
    }
    
    SideStepSearchCriteria criteria = new SideStepSearchCriteria();
    criteria.setCaseId(caseId);
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    criteria.setAdhocExcluded(isAdhocExcluded);
    
    Map<String, Object> params = new HashMap<>();
    params.put("sideStepSearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcess("findSideStepsByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria)", params, null);
    @SuppressWarnings("unchecked")
    List<IvySideStep> sideSteps =  (List<IvySideStep>) response.get("sideSteps");
    sideStepsByCase.put(caseId, sideSteps);
    return sideSteps;
  }
  
  public void startSideStep(IvySideStep sideStep) throws IOException {
    String url = sideStep.getStartLink();
    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
  }
  
  public void startAdhoc(IvySideStep sideStep, long taskId) throws IOException {
    String url = sideStep.getStartLink() + "?originalTaskId=" + taskId;
    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
  }
}
