package com.axonivy.portal.components.enums.ai;

public enum IvyToolResultType {

  JSON("<json>%s</json>"), IFRAME("<iframe>%s</iframe>"), ERROR("<error>%s</error>");

  private IvyToolResultType(String resultPattern) {
    this.resultPattern = resultPattern;
  }

  private String resultPattern;

  public String getResultPattern() {
    return resultPattern;
  }

  public void setResultPattern(String resultPattern) {
    this.resultPattern = resultPattern;
  }

  public String format(String content) {
    return String.format(this.resultPattern, content);
  }
}
