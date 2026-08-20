package com.axonivy.portal.migration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboard.converter.JsonDashboardConverterFactory;
import com.axonivy.portal.migration.dashboard.converter.v140.DashboardTaskWidgetFilterTypeConverter;
import com.axonivy.portal.migration.dashboardfilter.converter.JsonDashboardFilterConverterFactory;
import com.axonivy.portal.migration.dashboardfilter.converter.v140.SavedTaskWidgetFilterTypeConverter;

/**
 * A converter only runs when its version is newer than the version stamped on the configuration AND
 * not newer than {@link AbstractJsonVersion#LATEST}. Getting either side wrong makes the migration a
 * silent no-op, which is invisible at runtime, so it is pinned down here.
 */
class TestFilterTypeConverterSelection {

  private static final String CORRECTION_VERSION = "14.0.0";

  /**
   * The migrators drop every converter newer than {@link AbstractJsonVersion#LATEST}, so LATEST must
   * have moved with them. Asserted through the version objects rather than the constant itself, because
   * a {@code static final String} is inlined at compile time and would compare against itself.
   */
  @Test
  void latestVersion_coversTheFilterTypeCorrection() {
    assertThat(DashboardJsonVersion.LATEST_VERSION.getValue()).isEqualTo(CORRECTION_VERSION);
    assertThat(DashboardFilterJsonVersion.LATEST_VERSION.getValue()).isEqualTo(CORRECTION_VERSION);
  }

  @Test
  void dashboardConverter_declaresTheCorrectionVersion() {
    assertThat(new DashboardTaskWidgetFilterTypeConverter().version().getValue())
        .isEqualTo(CORRECTION_VERSION);
  }

  @Test
  void savedFilterConverter_declaresTheCorrectionVersion() {
    assertThat(new SavedTaskWidgetFilterTypeConverter().version().getValue())
        .isEqualTo(CORRECTION_VERSION);
  }

  @Test
  void dashboardConverter_isSelectedForEveryOlderConfiguration() {
    for (String storedVersion : List.of("10.0.0", "11.2.0", "11.3.0", "12.0.0", "13.1.0", "13.2.0")) {
      assertThat(dashboardConverterTypes(storedVersion))
          .as("dashboard stored as %s must be migrated", storedVersion)
          .contains(DashboardTaskWidgetFilterTypeConverter.class);
    }
  }

  @Test
  void dashboardConverter_isNotSelectedForAnAlreadyMigratedConfiguration() {
    assertThat(dashboardConverterTypes(CORRECTION_VERSION))
        .doesNotContain(DashboardTaskWidgetFilterTypeConverter.class);
  }

  @Test
  void savedFilterConverter_isSelectedForEveryOlderConfiguration() {
    for (String storedVersion : List.of("10.0.0", "11.2.0", "11.3.0", "12.0.0", "13.1.0", "13.2.0")) {
      assertThat(savedFilterConverterTypes(storedVersion))
          .as("saved filter stored as %s must be migrated", storedVersion)
          .contains(SavedTaskWidgetFilterTypeConverter.class);
    }
  }

  @Test
  void savedFilterConverter_isNotSelectedForAnAlreadyMigratedConfiguration() {
    assertThat(savedFilterConverterTypes(CORRECTION_VERSION))
        .doesNotContain(SavedTaskWidgetFilterTypeConverter.class);
  }

  @Test
  void bothConverters_passTheMigratorsLatestVersionGate() {
    assertThat(new DashboardTaskWidgetFilterTypeConverter().version()
        .compareTo(DashboardJsonVersion.LATEST_VERSION)).isLessThanOrEqualTo(0);
    assertThat(new SavedTaskWidgetFilterTypeConverter().version()
        .compareTo(DashboardFilterJsonVersion.LATEST_VERSION)).isLessThanOrEqualTo(0);
  }

  @Test
  void dashboardConverter_runsAfterTheOlderConverters() {
    List<Class<?>> converters = dashboardConverterTypes("10.0.0");

    assertThat(converters).last().isEqualTo(DashboardTaskWidgetFilterTypeConverter.class);
  }

  /**
   * The same correction shipped on the 12.0 LTS line as JSON version 12.0.17. Reusing that string here
   * would be a silent no-op, because versions compare part by part and 13 already beats 12.
   */
  @Test
  void ltsVersionString_wouldNotHaveCoveredA13xConfiguration() {
    assertThat(new DashboardJsonVersion("13.2.0").isOlderThan(new DashboardJsonVersion("12.0.17")))
        .isFalse();
    assertThat(new DashboardJsonVersion("13.2.0").isOlderThan(new DashboardJsonVersion(CORRECTION_VERSION)))
        .isTrue();
  }

  private static List<Class<?>> dashboardConverterTypes(String storedVersion) {
    return toTypes(JsonDashboardConverterFactory.getConverters(new DashboardJsonVersion(storedVersion)));
  }

  private static List<Class<?>> savedFilterConverterTypes(String storedVersion) {
    return toTypes(
        JsonDashboardFilterConverterFactory.getConverters(new DashboardFilterJsonVersion(storedVersion)));
  }

  private static List<Class<?>> toTypes(List<IJsonConverter> converters) {
    return converters.stream().<Class<?>>map(IJsonConverter::getClass).toList();
  }
}
