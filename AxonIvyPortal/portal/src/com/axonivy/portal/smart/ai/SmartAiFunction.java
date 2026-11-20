package com.axonivy.portal.smart.ai;

public final class SmartAiFunction<T> {

  private final String name;
  private final Class<T> resultType;

  private SmartAiFunction(String name, Class<T> resultType) {
    this.name = name;
    this.resultType = resultType;
  }

  public static <T> SmartAiFunction<T> of(String name, Class<T> resultType) {
    return new SmartAiFunction<>(name, resultType);
  }

  public String name() {
    return name;
  }

  public Class<T> resultType() {
    return resultType;
  }
}
