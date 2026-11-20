package com.axonivy.portal.smart.statistic.prompt;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PromptFormatter {

  private static final Pattern PLACEHOLDER = Pattern.compile("\\{\\{(\\w+)\\}\\}");

  private final String template;
  private final Set<String> placeholders;

  private PromptFormatter(String template) {
    this.template = template;
    this.placeholders = placeholdersOf(template);
  }

  public static PromptFormatter from(String template) {
    if (template == null) {
      throw new IllegalArgumentException("A prompt template cannot be null");
    }
    return new PromptFormatter(template);
  }

  /** @param keyValues name, value, name, value - as many pairs as the template has slots (unbraced names) */
  public String apply(Object... keyValues) {
    return apply(toMap(keyValues));
  }

  public String apply(Map<String, Object> values) {
    Map<String, Object> given = values == null ? Map.of() : values;
    verify(given.keySet());

    Matcher placeholder = PLACEHOLDER.matcher(template);
    StringBuilder out = new StringBuilder();
    while (placeholder.find()) {
      Object value = given.get(placeholder.group(1));
      placeholder.appendReplacement(out, Matcher.quoteReplacement(String.valueOf(value)));
    }
    placeholder.appendTail(out);
    return out.toString();
  }

  private void verify(Set<String> given) {
    Set<String> missing = new LinkedHashSet<>(placeholders);
    missing.removeAll(given);
    if (!missing.isEmpty()) {
      throw new IllegalArgumentException("No value given for " + missing);
    }
    Set<String> unused = new LinkedHashSet<>(given);
    unused.removeAll(placeholders);
    if (!unused.isEmpty()) {
      throw new IllegalArgumentException("No placeholder for " + unused);
    }
  }

  private static Set<String> placeholdersOf(String template) {
    Set<String> names = new LinkedHashSet<>();
    Matcher placeholder = PLACEHOLDER.matcher(template);
    while (placeholder.find()) {
      names.add(placeholder.group(1));
    }
    return Collections.unmodifiableSet(names);
  }

  private static Map<String, Object> toMap(Object... keyValues) {
    if (keyValues.length % 2 != 0) {
      throw new IllegalArgumentException("Expected key/value pairs, got " + keyValues.length + " arguments");
    }
    Map<String, Object> values = new HashMap<>();
    for (int i = 0; i < keyValues.length; i += 2) {
      values.put(String.valueOf(keyValues[i]), keyValues[i + 1]);
    }
    return values;
  }
}
