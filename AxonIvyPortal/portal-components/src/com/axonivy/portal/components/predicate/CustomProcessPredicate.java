package com.axonivy.portal.components.predicate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import com.axonivy.portal.components.enums.CustomProcessStatus;

public class CustomProcessPredicate implements Predicate<Map<String, Object>> {

  private static final String STATUS = "status";

  @Override
  public boolean test(Map<String, Object> value) {
    return Optional.ofNullable(value)
        .map(map -> map.get(STATUS))
        .map(Objects::toString)
        .map(CustomProcessStatus::toEnum).get() != CustomProcessStatus.SKIP;
  }
}
