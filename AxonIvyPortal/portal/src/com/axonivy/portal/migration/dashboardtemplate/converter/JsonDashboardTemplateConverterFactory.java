package com.axonivy.portal.migration.dashboardtemplate.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;

/**
 * Converter chain for dashboard TEMPLATE json ({@code Portal.DashboardTemplates}), separate from
 * {@link com.axonivy.portal.migration.dashboard.converter.JsonDashboardConverterFactory} (saved
 * dashboards) since the two schemas evolve independently.
 */
public class JsonDashboardTemplateConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE112 = List.of(
      new com.axonivy.portal.migration.dashboardtemplate.converter.v112.DashboardTemplateCaseWidgetConverter(),
      new com.axonivy.portal.migration.dashboardtemplate.converter.v112.DashboardTemplateTaskWidgetConverter());

  private static final List<IJsonConverter> LE113 = List.of(
      new com.axonivy.portal.migration.dashboardtemplate.converter.v113.DashboardTemplateCaseWidgetConverter(),
      new com.axonivy.portal.migration.dashboardtemplate.converter.v113.DashboardTemplateTaskWidgetConverter());

  // v120/v131 reuse the plain-dashboard converters: the template-specific versions assumed a
  // collection to iterate rather than one dashboard-shaped node, so they never actually worked.
  private static final List<IJsonConverter> LE120 = List
      .of(new com.axonivy.portal.migration.dashboard.converter.v120.DashboardProcessWidgetConverter());

  private static final List<IJsonConverter> LE131 = List
      .of(new com.axonivy.portal.migration.dashboard.converter.v131.DashboardConverter());

  static {
    CONVERTERS.addAll(LE112);
    CONVERTERS.addAll(LE113);
    CONVERTERS.addAll(LE120);
    CONVERTERS.addAll(LE131);
  }
}
