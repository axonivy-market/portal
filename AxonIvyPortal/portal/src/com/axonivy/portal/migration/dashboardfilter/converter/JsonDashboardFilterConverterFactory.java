package com.axonivy.portal.migration.dashboardfilter.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;

public class JsonDashboardFilterConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new com.axonivy.portal.migration.dashboardfilter.converter.v112.DashboardCaseWidgetFilterConverter(),
      new com.axonivy.portal.migration.dashboardfilter.converter.v112.DashboardTaskWidgetFilterConverter()
    );

  private static final List<IJsonConverter> LE113 = List.of(
      new com.axonivy.portal.migration.dashboardfilter.converter.v113.DashboardCaseWidgetFilterConverter()
    );

  static {
    CONVERTERS.addAll(LE112);
    CONVERTERS.addAll(LE113);
  }

  
}
