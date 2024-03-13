package com.axonivy.portal.enums.ai;

public enum ToolType {

  IVY("Ivy"), IVY_CALLABLE("Ivy"), RETRIEVAL_QA("Q&A");

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
