package com.axonivy.portal.smart.ai;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Reads an enum out of whatever the model actually wrote.
 *
 * Models express "this field does not apply" in three ways - by omitting it, by sending null, and
 * by sending an empty string - and Jackson accepts only the first two. An empty string aborts the
 * whole response with {@code InvalidFormatException}, so a single blank optional field loses a
 * chart that was otherwise complete.
 *
 * Fixing it at the mapper with {@code ACCEPT_EMPTY_STRING_AS_NULL_OBJECT} is not open to us: the
 * {@code ObjectMapper} that parses these belongs to langchain4j inside the smart-workflow project,
 * and Portal never sees it. A {@code @JsonCreator} on each enum travels with the class instead, so
 * it holds whoever does the parsing.
 *
 * Unknown constants come back as null rather than throwing, for the same reason: the mappers
 * already default or warn on a missing value, and a chart missing one field beats no chart at all.
 */
public class AgentEnums {

  private AgentEnums() {}

  public static <E extends Enum<E>> E parse(Class<E> type, String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    String wanted = value.trim();
    for (E constant : type.getEnumConstants()) {
      if (constant.name().equalsIgnoreCase(wanted)) {
        return constant;
      }
    }
    // Nulled rather than thrown, but said out loud: a model that keeps writing a value the enum
    // does not have is a prompt worth fixing, and silence makes that invisible.
    Ivy.log().warn("Smart AI: " + type.getSimpleName() + " has no constant for \"" + wanted + "\"");
    return null;
  }
}
