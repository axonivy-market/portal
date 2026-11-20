package com.axonivy.portal.smart.ai;

import java.io.Serializable;

public class SmartAiOutcome<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private final T result;
  private final SmartAiFailure failure;
  private final String detail;

  private SmartAiOutcome(T result, SmartAiFailure failure, String detail) {
    this.result = result;
    this.failure = failure;
    this.detail = detail;
  }

  public static <T> SmartAiOutcome<T> success(T result) {
    return new SmartAiOutcome<>(result, SmartAiFailure.NONE, null);
  }

  public static <T> SmartAiOutcome<T> failed(SmartAiFailure failure, String detail) {
    return new SmartAiOutcome<>(null, failure, detail);
  }

  public T result() {
    return result;
  }

  public T orElse(T fallback) {
    return isFailed() ? fallback : result;
  }

  public SmartAiFailure failure() {
    return failure;
  }

  public String detail() {
    return detail;
  }

  public boolean isFailed() {
    return failure != SmartAiFailure.NONE;
  }
}
