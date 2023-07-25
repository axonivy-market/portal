package com.axonivy.portal.migration.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.JsonVersion;
import com.axonivy.portal.migration.converter.v112.DashboardCaseWidgetConverter;

public class JsonDashboardTemplateConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(JsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new DashboardCaseWidgetConverter()
    );

  static {
    CONVERTERS.addAll(LE112);
  }
}
