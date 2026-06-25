package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

/**
 * Portal API for sanitize String
 * 
 */
public class SanitizeAPI {

  /**
   * Escape Javascript String in order to prevent XSS attacks and Javascript
   * injections. Intended for use in single-quoted or double-quoted JavaScript
   * string contexts. Do not place the result inside a template literal
   * (backtick string) — even with this escaping applied, backtick contexts are
   * discouraged because nested expressions and future maintainers can easily
   * reintroduce {@code ${...}} interpolation hazards.
   *
   * @param value the Javascript String to escape
   * @return escaped Javascript String
   */
  public static String escapeForJavascript(String value) {
    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    StringBuilder builder = new StringBuilder();
    for (char character : value.toCharArray()) {
      builder.append(switch (character) {
        case '\\' -> "\\\\"; // Escape backslashes
        case '\"' -> "\\\""; // Escape double quotes
        case '\'' -> "\\'"; // Escape single quotes
        case '\n' -> "\\n"; // Escape new lines
        case '\r' -> "\\r"; // Escape carriage return
        case '\t' -> "\\t"; // Escape tab characters
        case '\u2028' -> "\\u2028"; // Escape Unicode line separator (JS line terminator)
        case '\u2029' -> "\\u2029"; // Escape Unicode paragraph separator (JS line terminator)
        case '<' -> "\\u003C"; // Escape `<` (XSS prevention)
        case '>' -> "\\u003E"; // Escape `>` (XSS prevention)
        case '/' -> "\\/"; // Escape `/` (prevents `</script>` issues)
        case '`' -> "\\`"; // Escape backtick (template literals)
        case '$' -> "\\u0024"; // Escape `$` (XSS prevention — blocks ${} interpolation in template literals)
        case '{' -> "\\u007B"; // Escape `{` (XSS prevention — blocks ${} interpolation in template literals)
        default -> String.valueOf(character);
      });
    }

    return builder.toString();
  }
}
