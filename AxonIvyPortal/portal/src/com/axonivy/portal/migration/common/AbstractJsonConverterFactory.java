package com.axonivy.portal.migration.common;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;

public abstract class AbstractJsonConverterFactory {

  protected static List<IJsonConverter> selectConverters(List<IJsonConverter> converters, AbstractJsonVersion version) {
    return converters.stream()
            .filter(converter -> version.isOlderThan(converter.version()))
            .sorted(olderBeforeNewer())
            .collect(Collectors.toList());
  }

  private static Comparator<? super IJsonConverter> olderBeforeNewer() {
    return (c1,c2) -> c1.version().compareTo(c2.version());
  }

}
