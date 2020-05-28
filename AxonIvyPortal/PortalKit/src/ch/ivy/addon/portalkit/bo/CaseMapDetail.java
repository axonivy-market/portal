package ch.ivy.addon.portalkit.bo;

import java.util.List;
import java.util.Map;

public class CaseMapDetail {
  private String name;
  private String description;
  private List<String> stages; 
  private Map<String, List<String>> stageDetail;
  private Map<String, List<String>> processDetail;
  private Map<String, String> stageIcon;
  
  public Map<String, List<String>> getStageDetail() {
    return stageDetail;
  }
  public void setStageDetail(Map<String, List<String>> stageDetail) {
    this.stageDetail = stageDetail;
  }
  public List<String> getStages() {
    return stages;
  }
  public void setStages(List<String> stages) {
    this.stages = stages;
  }
  public Map<String, List<String>> getProcessDetail() {
    return processDetail;
  }
  public void setProcessDetail(Map<String, List<String>> processDetail) {
    this.processDetail = processDetail;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Map<String, String> getStageIcon() {
    return stageIcon;
  }
  public void setStageIcon(Map<String, String> stageIcon) {
    this.stageIcon = stageIcon;
  }
}
