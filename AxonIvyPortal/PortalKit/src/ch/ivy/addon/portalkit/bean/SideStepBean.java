package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.UrlUtils;

@ManagedBean
@ViewScoped
public class SideStepBean implements Serializable {

  private static final long serialVersionUID = -786782623972036094L;
  private Map<Long, List<IvySideStep>> sideStepOfTasks = new HashMap<>();
  private Map<Long, List<IvySideStep>> sideStepOfCases = new HashMap<>();
  
  private List<IvySideStep> currentSideSteps = new ArrayList<>(); 
  
  public void getSideSteps(Long taskId, Long caseId, boolean isAdhocExcluded) {
    currentSideSteps.clear();
    if(isNullOrZero(taskId)) {
      currentSideSteps.addAll(getSideStepsOfCases(caseId, isAdhocExcluded));
    } else {
      currentSideSteps.addAll(getSideStepsOfTasks(caseId, isAdhocExcluded));
    }
  }
  
  private List<IvySideStep> getSideStepsOfCases(Long caseId, boolean isAdhocExcluded) {
    if (sideStepOfCases.containsKey(caseId)) {
      return sideStepOfCases.get(caseId);
    }
    
    List<IvySideStep> sideSteps = getSideStepsByCriteria(caseId, isAdhocExcluded);
    sideStepOfCases.put(caseId, sideSteps);
    return sideSteps;
  }
  
  private List<IvySideStep> getSideStepsOfTasks(Long caseId, boolean isAdhocExcluded) {
    if (sideStepOfTasks.containsKey(caseId)) {
      return sideStepOfTasks.get(caseId);
    }
    
    List<IvySideStep> sideSteps = getSideStepsByCriteria(caseId, isAdhocExcluded);
    sideStepOfTasks.put(caseId, sideSteps);
    return sideSteps;
  }

  private List<IvySideStep> getSideStepsByCriteria(Long caseId, boolean isAdhocExcluded) {
    SideStepSearchCriteria criteria = new SideStepSearchCriteria();
    criteria.setCaseId(caseId);
    criteria.setAdhocExcluded(isAdhocExcluded);
    
    Map<String, Object> params = new HashMap<>();
    params.put("sideStepSearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcess("findSideStepsByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria)", params, null);
    @SuppressWarnings("unchecked")
    List<IvySideStep> sideSteps =  (List<IvySideStep>) response.get("sideSteps");
    return sideSteps;
  }
  
  private boolean isNullOrZero(Long value) {
    return Objects.isNull(value) || value.equals(0L); 
  }
  
  public void startSideStep(IvySideStep sideStep) throws IOException {
    String url = UrlUtils.formatLinkWithEmbedInFrameParam(sideStep.getStartLink());
    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
  }
  
  public void startAdhoc(IvySideStep sideStep, long taskId) throws IOException {
    String url = sideStep.getStartLink() + "?originalTaskId=" + taskId;
    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
  }

  public List<IvySideStep> getCurrentSideSteps() {
    return currentSideSteps;
  }
}
