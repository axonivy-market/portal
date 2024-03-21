package com.axonivy.portal.selenium.bean;

public class ExpressResponsible {
  
  private String responsibleName;
  private boolean isGroup;
  
  public ExpressResponsible() {
  }


  public ExpressResponsible(String responsibleName, boolean isGroup) {
    super();
    this.responsibleName = responsibleName;
    this.isGroup = isGroup;
  }

  public String getResponsibleName() {
    return responsibleName;
  }

  public boolean getIsGroup() {
    return isGroup;
  }

  public void setResponsibleName(String responsibleName) {
    this.responsibleName = responsibleName;
  }


  public void setIsGroup(boolean isGroup) {
    this.isGroup = isGroup;
  }

}
