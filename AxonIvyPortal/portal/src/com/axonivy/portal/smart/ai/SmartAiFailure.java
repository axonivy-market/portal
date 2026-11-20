package com.axonivy.portal.smart.ai;

public enum SmartAiFailure {
  NONE,
  NOT_AVAILABLE,
  GUARDRAIL_INPUT,
  GUARDRAIL_OUTPUT,
  CIRCUIT_BREAKER,
  AGENT_ERROR
}
