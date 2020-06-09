package ch.ivy.addon.portalkit.bo;

import java.util.List;

public class ProcessStep {
  private String name;
  private List<String> descriptions;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public List<String> getDescriptions() {
    return descriptions;
  }
  public void setDescriptions(List<String> descriptions) {
    this.descriptions = descriptions;
  }
}
