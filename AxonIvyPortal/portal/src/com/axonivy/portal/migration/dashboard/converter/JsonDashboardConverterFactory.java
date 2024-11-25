package com.axonivy.portal.migration.dashboard.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;

public class JsonDashboardConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new com.axonivy.portal.migration.dashboard.converter.v112.DashboardCaseWidgetConverter(),
      new com.axonivy.portal.migration.dashboard.converter.v112.DashboardTaskWidgetConverter());

  private static final List<IJsonConverter> LE113 = List.of(
      new com.axonivy.portal.migration.dashboard.converter.v113.DashboardCaseWidgetConverter(),
      new com.axonivy.portal.migration.dashboard.converter.v113.DashboardTaskWidgetConverter());
  
  private static final List<IJsonConverter> LE114 = List
      .of(new com.axonivy.portal.migration.dashboard.converter.v114.DashboardProcessWidgetConverter()); 
  
  private static final List<IJsonConverter> LE120 = List
      .of(new com.axonivy.portal.migration.dashboard.converter.v120.DashboardStatisticWidgetConverter());


  static {
    CONVERTERS.addAll(LE112);
    CONVERTERS.addAll(LE113);
    CONVERTERS.addAll(LE114);
    CONVERTERS.addAll(LE120);
  }
}
