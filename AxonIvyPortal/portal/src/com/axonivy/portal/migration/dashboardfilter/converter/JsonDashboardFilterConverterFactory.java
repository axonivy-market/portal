package com.axonivy.portal.migration.dashboardfilter.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboardfilter.converter.v112.DashboardCaseWidgetFilterConverter;
import com.axonivy.portal.migration.dashboardfilter.converter.v112.DashboardTaskWidgetFilterConverter;

public class JsonDashboardFilterConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new DashboardCaseWidgetFilterConverter(),
      new DashboardTaskWidgetFilterConverter()
    );

  static {
    CONVERTERS.addAll(LE112);
  }

  
}
