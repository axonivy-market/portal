package ch.ivy.addon.portalkit.persistence.converter;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Value filter for a boolean property which is enabled by default, so that only the meaningful
 * {@code false} ends up in the JSON. Jackson skips every value this filter is equal to, use it as
 * {@code @JsonInclude(value = Include.CUSTOM, valueFilter = ExcludeTrueFilter.class)}.
 *
 * @see JsonInclude#valueFilter()
 */
public class ExcludeTrueFilter {

  @Override
  public boolean equals(Object value) {
    return Boolean.TRUE.equals(value);
  }

  @Override
  public int hashCode() {
    return Boolean.TRUE.hashCode();
  }
}
