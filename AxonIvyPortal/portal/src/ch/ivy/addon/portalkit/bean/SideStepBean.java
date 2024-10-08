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

import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;
import ch.ivy.addon.portalkit.util.UrlUtils;

@ManagedBean
@ViewScoped
public class SideStepBean implements Serializable {

  private static final long serialVersionUID = -786782623972036094L;
  private Map<Long, List<IvySideStep>> sideStepOfTasks = new HashMap<>();
  private Map<Long, List<IvySideStep>> sideStepOfCases = new HashMap<>();
  
  private List<IvySideStep> currentSideSteps = new ArrayList<>(); 
  
  public void getSideSteps(Long taskId, Long caseId) {
    currentSideSteps.clear();
    if(isNullOrZero(taskId)) {
      currentSideSteps.addAll(getSideStepsOfCases(caseId));
    } else {
      currentSideSteps.addAll(getSideStepsOfTasks(caseId));
    }
  }
  
  private List<IvySideStep> getSideStepsOfCases(Long caseId) {
    if (sideStepOfCases.containsKey(caseId)) {
      return sideStepOfCases.get(caseId);
    }
    
    List<IvySideStep> sideSteps = getSideStepsByCriteria(caseId);
    sideStepOfCases.put(caseId, sideSteps);
    return sideSteps;
  }
  
  private List<IvySideStep> getSideStepsOfTasks(Long caseId) {
    if (sideStepOfTasks.containsKey(caseId)) {
      return sideStepOfTasks.get(caseId);
    }
    
    List<IvySideStep> sideSteps = getSideStepsByCriteria(caseId);
    sideStepOfTasks.put(caseId, sideSteps);
    return sideSteps;
  }

  private List<IvySideStep> getSideStepsByCriteria(Long caseId) {
    SideStepSearchCriteria criteria = new SideStepSearchCriteria();
    criteria.setCaseId(caseId);
    
    Map<String, Object> params = new HashMap<>();
    params.put("sideStepSearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcessInProjectAndAllRequired("findSideStepsByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria)", params);
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

  public List<IvySideStep> getCurrentSideSteps() {
    return currentSideSteps;
  }
}
