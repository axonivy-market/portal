package com.axonivy.portal.migration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboard.converter.JsonDashboardConverterFactory;
import com.axonivy.portal.migration.dashboard.converter.v140.DashboardTaskWidgetFilterTypeConverter;
import com.axonivy.portal.migration.dashboardfilter.converter.JsonDashboardFilterConverterFactory;
import com.axonivy.portal.migration.dashboardfilter.converter.v140.SavedTaskWidgetFilterTypeConverter;

class TestFilterTypeConverterSelection {

  private static final String CORRECTION_VERSION = "14.0.0";

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
    List<IJsonConverter> converters =
        JsonDashboardConverterFactory.getConverters(new DashboardJsonVersion("10.0.0"));

    int correctionIndex = toTypes(converters).indexOf(DashboardTaskWidgetFilterTypeConverter.class);
    assertThat(correctionIndex).as("the correction must be selected").isNotNegative();

    // Converters sharing a version keep their registration order, so this asserts the correction runs
    // after every older converter without demanding it be the very last one - other converters may be
    // registered at the same version.
    assertThat(versionsOf(converters.subList(0, correctionIndex)))
        .as("only converters older than %s may run before the correction", CORRECTION_VERSION)
        .doesNotContain(CORRECTION_VERSION);
  }

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

  private static List<String> versionsOf(List<IJsonConverter> converters) {
    return converters.stream().map(converter -> converter.version().getValue()).toList();
  }
}
