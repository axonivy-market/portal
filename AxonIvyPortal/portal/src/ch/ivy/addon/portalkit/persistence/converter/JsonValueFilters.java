package ch.ivy.addon.portalkit.persistence.converter;

import com.axonivy.portal.enums.CaseQueryType;

import ch.ivy.addon.portalkit.enums.ProcessSorting;

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
   * For the sorting of a compact process widget, only a sorting differing from the default is
   * written.
   */
  public static class ExcludeAlphabeticalSorting {

    @Override
    public boolean equals(Object value) {
      return ProcessSorting.BY_ALPHABETICALLY.name().equals(value);
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
