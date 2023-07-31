package com.axonivy.portal.migration.dashboardtemplate.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboard.converter.v112.DashboardCaseWidgetConverter;

public class JsonDashboardTemplateConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new DashboardCaseWidgetConverter()
    );

  static {
    CONVERTERS.addAll(LE112);
  }
}
