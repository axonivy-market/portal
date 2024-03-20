package com.axonivy.portal.enums.ai;

public enum ToolType {

  IVY("Ivy", "success"), RETRIEVAL_QA("Q&A", "info"),
  COMPOSITE("Composite", "warning");

  private String label;
  private String tagSeverity;

  ToolType(String label, String tagSeverity) {
    this.label = label;
    this.tagSeverity = tagSeverity;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getTagSeverity() {
    return tagSeverity;
  }

  public void setTagSeverity(String tagSeverity) {
    this.tagSeverity = tagSeverity;
  }
}
