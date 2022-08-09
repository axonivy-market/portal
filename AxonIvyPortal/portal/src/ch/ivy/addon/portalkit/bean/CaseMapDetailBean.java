package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.CaseMapDetail;

@ManagedBean(name = "caseMapDetailBean")
@ViewScoped
public class CaseMapDetailBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private CaseMapDetail caseMapDetail;

  public CaseMapDetail getCaseMapDetail() {
    return caseMapDetail;
  }

  public void setCaseMapDetail(CaseMapDetail caseMapDetail) {
    this.caseMapDetail = caseMapDetail;
  }
  
  public List<String> getProcessOfStage(CaseMapDetail detail, String stage){
    List<String> processes = new ArrayList<>(); 
    processes.addAll(detail.getStageDetail().get(stage));
    return processes;
  }
  
  public List<String> getProcessOfStage(CaseMapDetail detail, int stageIndex){
    String stage = detail.getStages().get(stageIndex);
    return getProcessOfStage(detail, stage);
  }
  
  public List<String> getSideStepOfStage(CaseMapDetail detail, String stage){
    List<String> sideSteps = new ArrayList<>(); 
    sideSteps.addAll(CollectionUtils.emptyIfNull(detail.getSideStepDetail().get(stage)));
    return sideSteps;
  }
  
  public List<String> getSideStepOfStage(CaseMapDetail detail, int stageIndex){
    String stage = detail.getStages().get(stageIndex);
    return getSideStepOfStage(detail, stage);
  }
  
  public String getIconOfStage(CaseMapDetail detail, String stage){
    return detail.getStageIcon().get(stage);
  }
  
  public List<String> getProcessDescription(CaseMapDetail detail, String process){
    return detail.getProcessDetail().get(process);
  }
  
  public String getDisplayStage(CaseMapDetail detail, int stageIndex){
    return String.format("Stage %d - %s", stageIndex + 1, detail.getStages().get(stageIndex));
  }
  
  public void startCaseMap(String link) throws IOException {
    if (StringUtils.isNotEmpty(link)) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
    }
  }
  
  public boolean renderNextLink(CaseMapDetail detail, int stageIndex) {
    return stageIndex < detail.getStages().size() - 1;
  }
  
  public boolean renderPreCondition(CaseMapDetail detail, String processName) {
    return detail.getPreConditionDetail().containsKey(processName);
  }
}
