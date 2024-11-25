package com.axonivy.portal.components.dto;

import com.axonivy.portal.components.enums.AIState;

public class AiResultDTO {
  private String result;
  private String resultForAI;
  private AIState state;

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

  public AIState getState() {
    return state;
  }

  public void setState(AIState state) {
    this.state = state;
  }
}
