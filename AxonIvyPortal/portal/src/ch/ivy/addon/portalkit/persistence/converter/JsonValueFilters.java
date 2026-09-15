package ch.ivy.addon.portalkit.persistence.converter;

import com.axonivy.portal.enums.CaseQueryType;

public final class JsonValueFilters {

  private JsonValueFilters() {}

  /**
   * For a boolean property which is enabled by default, only the meaningful false is written.
   */
  public static class ExcludeTrue {

    @Override
    public boolean equals(Object value) {
      return Boolean.TRUE.equals(value);
    }
  }

  /**
   * For the query type of a case widget, only a type differing from the default is written.
   */
  public static class ExcludeBusinessCaseQueryType {

    @Override
    public boolean equals(Object value) {
      return CaseQueryType.BUSINESS_CASE.equals(value);
    }
  }
}
