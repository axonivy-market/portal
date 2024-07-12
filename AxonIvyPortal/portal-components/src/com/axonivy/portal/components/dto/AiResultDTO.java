package com.axonivy.portal.components.dto;

import com.axonivy.portal.components.enums.AiResultState;

public class AiResultDTO {
  private String result;
  private String resultForAI;
  private AiResultState state;
  private Boolean isMemory;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getResultForAI() {
    return resultForAI;
  }

  public void setResultForAI(String resultForAI) {
    this.resultForAI = resultForAI;
  }

  public AiResultState getState() {
    return state;
  }

  public void setState(AiResultState state) {
    this.state = state;
  }

  public Boolean getIsMemory() {
    return isMemory;
  }

  public void setIsMemory(Boolean isMemory) {
    this.isMemory = isMemory;
  }
}
