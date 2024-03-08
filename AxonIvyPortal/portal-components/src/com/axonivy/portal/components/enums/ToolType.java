package com.axonivy.portal.components.enums;

public enum ToolType {

  IVY("Ivy tool"),
  IVY_CALLABLE("Ivy callable tool"),
  RETRIEVAL_QA("Retrieval tool");

  private String label;

  ToolType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
