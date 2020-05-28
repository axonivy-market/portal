package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.CaseMapDetail;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean(name = "caseMapDetailBean")
@ViewScoped
public class CaseMapDetailBean implements Serializable {
  private CaseMapDetail caseMapDetail;

  public CaseMapDetail getCaseMapDetail() {
    return caseMapDetail;
  }

  public void setCaseMapDetail(CaseMapDetail caseMapDetail) {
    this.caseMapDetail = caseMapDetail;
  }
  
  public List<String> getProcessOfStage(CaseMapDetail detail, String stage){
    return detail.getStageDetail().get(stage);
  }
  
public String getIconOfStage(CaseMapDetail detail, String stage){
    
    String x = detail.getStageIcon().get(stage);
    Ivy.log().error("{0}", x);
    return detail.getStageIcon().get(stage);
  }
}
