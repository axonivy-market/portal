package com.axonivy.portal.service;

import static com.axonivy.portal.util.TestPortalPackageUtils.buildApplication;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildCaseDetails;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildCustomSubMenuItem;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildDashboard;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildDashboardWithWelcomeWidgetImage;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildExternalLink;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildExternalLinkWithImage;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildMenuOrder;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildStatistic;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildUserMenu;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildZip;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildZipWithDirectory;
import static com.axonivy.portal.util.TestPortalPackageUtils.toJson;
import static com.axonivy.portal.util.TestPortalPackageUtils.zipEntryContent;
import static com.axonivy.portal.util.TestPortalPackageUtils.zipEntryNames;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.model.StreamedContent;

import com.fasterxml.jackson.databind.JsonNode;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPackageFile;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.PortalPackageService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.dto.menu.MenuOrder;

@IvyTest
public class TestPortalPackageService {
  private static final String MALFORMED_JSON = "{not-valid-json";

  PortalPackageService service = PortalPackageService.getInstance();

  @BeforeEach
  void clearVariables() {
    for (PortalPackageFile file : PortalPackageFile.values()) {
      Ivy.var().set(file.getVariableKey(), "");
    }
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getDefaultValue());
  }

  @Test
  void exportPackage_containsNonEmptyFiles() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), toJson(List.of(userMenu)));
    Dashboard dashboard = buildDashboard("dashboard-1", "My Dashboard", "task_1", "Your Tasks");
    Ivy.var().set(PortalPackageFile.DASHBOARD.getVariableKey(), toJson(List.of(dashboard)));

    StreamedContent content = service.exportPackage();

    assertThat(content.getContentType()).isEqualTo("application/zip");
    assertThat(content.getName()).isEqualTo(PortalPackageService.getPackageName());
    List<String> names = zipEntryNames(content);
    assertThat(names).contains(PortalPackageFile.USER_MENU.getFilename(), PortalPackageFile.DASHBOARD.getFilename());
  }

  @Test
  void exportPackage_KeepExactValue() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String json = toJson(List.of(userMenu));
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), json);
    StreamedContent content = service.exportPackage();
    String zipContent = zipEntryContent(content, PortalPackageFile.USER_MENU.getFilename());
    assertThat(zipContent).isEqualTo(json);
  }

  @Test
  void exportPackage_ExcludeEmptyVariables() throws IOException {
    String json = "[]";
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), json);
    StreamedContent content = service.exportPackage();
    List<String> names = zipEntryNames(content);
    assertFalse(names.contains(PortalPackageFile.USER_MENU.getFilename()));
  }

  @Test
  void exportPackage_ExcludeEmptyVariables_wrapperShapeWithNoItems() throws IOException {
    String json = """
        {"version":"1.0","items":[]}""";
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), json);
    StreamedContent content = service.exportPackage();
    List<String> names = zipEntryNames(content);
    assertFalse(names.contains(PortalPackageFile.USER_MENU.getFilename()));
  }

  @Test
  void exportPackage_IncludesWrapperShapeWithItems() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String json = toJson(List.of(userMenu));
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), json);
    StreamedContent content = service.exportPackage();
    List<String> names = zipEntryNames(content);
    assertThat(names).contains(PortalPackageFile.USER_MENU.getFilename());
  }

  @Test
  void exportPackage_packageContentType_isZipType() throws IOException {
    StreamedContent content = service.exportPackage();
    assertThat(content.getContentType().equals("application/zip"));
  }

  @Test
  void importPackage_userMenu_setsIvyVariable() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String json = toJson(List.of(userMenu));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.USER_MENU.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.USER_MENU.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.USER_MENU.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_oneMalformedEntry_doesNotAbortOtherEntries() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String validJson = toJson(List.of(userMenu));
    byte[] zipBytes = buildZip(Map.of(
        PortalPackageFile.USER_MENU.getFilename(), validJson,
        "test.json", validJson));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.USER_MENU.getFilename(), true);
    assertThat(results).doesNotContainKey("test.json");
    assertThat(Ivy.var().get(PortalPackageFile.USER_MENU.getVariableKey())).isEqualTo(validJson);
  }

  @Test
  void importPackage_dashboard_validJson_setsIvyVariable() throws IOException {
    Dashboard dashboard = buildDashboard("dashboard-1", "My Dashboard", "task_1", "Your Tasks");
    String json = toJson(List.of(dashboard));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.DASHBOARD.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.DASHBOARD.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.DASHBOARD.getVariableKey())).isEqualTo(json);
  }

  @Test
  void writeDashboards_perItemVersionIsNeverPresentInExportedJson() throws IOException {
    // TestPortalPackageUtils#buildDashboard() explicitly stamps a per-item version via
    // Dashboard#setVersion() (leftover from before versioning moved to the wrapper container), but
    // JsonListWrapper's constructor now strips it on every item it wraps - it belongs to the
    // "version" field of the {"version": ..., "items": [...]} wrapper, not to individual entities.
    Dashboard dashboard = buildDashboard("dashboard-1", "My Dashboard", "task_1", "Your Tasks");
    Ivy.var().set(PortalPackageFile.DASHBOARD.getVariableKey(), toJson(List.of(dashboard)));

    StreamedContent content = service.exportPackage();
    String exportedJson = zipEntryContent(content, PortalPackageFile.DASHBOARD.getFilename());

    JsonNode wrapper = BusinessEntityConverter.getObjectMapper().readTree(exportedJson);
    assertThat(wrapper.get("version").asText()).isEqualTo("14.0.0");
    assertThat(wrapper.get("items").get(0).has("version")).isFalse();
  }

  @Test
  void exportThenImport_dashboardTaskWidgetWithoutConfiguredColumns_doesNotThrow() throws IOException {
    // A re-imported dashboard is already in the canonical wrapper shape (no per-item "version"),
    // so JsonDashboardMigrator.migrate() now short-circuits entirely for it - the per-item
    // converter chain (v112 through v140) does not run at all here, so this no longer exercises
    // the "columns" field NPE that v113's task/case widget converters used to hit when a widget had
    // no configured columns (TaskDashboardWidget/CaseDashboardWidget omit "columns" entirely from
    // JSON when empty, see @JsonInclude(NON_EMPTY) on DashboardWidget). That guard is still in
    // place in v113 for genuinely legacy (unwrapped, unversioned) dashboards - see
    // TestJsonDashboardMigrator for coverage of the legacy path itself. This test now only protects
    // the general export-then-import round-trip.
    Dashboard dashboard = buildDashboard("dashboard-1", "My Dashboard", "task_1", "Your Tasks");
    Ivy.var().set(PortalPackageFile.DASHBOARD.getVariableKey(), toJson(List.of(dashboard)));
    StreamedContent content = service.exportPackage();
    String exportedJson = zipEntryContent(content, PortalPackageFile.DASHBOARD.getFilename());
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.DASHBOARD.getFilename(), exportedJson));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.DASHBOARD.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.DASHBOARD.getVariableKey())).isNotBlank();
  }

  @Test
  void importPackage_dashboard_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.DASHBOARD.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.DASHBOARD.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.DASHBOARD.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_userMenu_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.USER_MENU.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.USER_MENU.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.USER_MENU.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_dashboard_oversizedWelcomeImage_doesNotUpdateVariable() throws IOException {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    Dashboard dashboard =
        buildDashboardWithWelcomeWidgetImage("dashboard-1", "My Dashboard", "welcome_1", "QUJDREVGRw==", "png");
    String json = toJson(List.of(dashboard));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.DASHBOARD.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.DASHBOARD.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.DASHBOARD.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_externalLink_validJson_setsIvyVariable() throws IOException {
    ExternalLink externalLink = buildExternalLink("link-1", "My Link", "https://example.com");
    String json = toJson(List.of(externalLink));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.EXTERNAL_LINK.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.EXTERNAL_LINK.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.EXTERNAL_LINK.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_externalLink_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.EXTERNAL_LINK.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.EXTERNAL_LINK.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.EXTERNAL_LINK.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_externalLink_oversizedImage_doesNotUpdateVariable() throws IOException {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    ExternalLink externalLink =
        buildExternalLinkWithImage("link-1", "My Link", "https://example.com", "QUJDREVGRw==", "png");
    String json = toJson(List.of(externalLink));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.EXTERNAL_LINK.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.EXTERNAL_LINK.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.EXTERNAL_LINK.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_customStatistic_validJson_setsIvyVariable() throws IOException {
    Statistic statistic = buildStatistic("statistic-1", "My Statistic", "priority");
    String json = toJson(List.of(statistic));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.CUSTOM_STATISTIC.getVariableKey())).isEqualTo(json);
  }

  @Test
  void exportPackage_customStatistic_wrapperShapeWithLingeringPerItemVersion_stripsVersion() throws IOException {
    // Reproduces data already stored in the canonical wrapper shape but with a stale per-item
    // "version" - e.g. written before per-item versioning was removed, or by other code that still
    // stamps one. The wrapper-shape passthrough must not re-export this unchanged; it has to detect
    // and strip the lingering per-item version.
    String json = """
        {"version":"14.0.0","items":[{"id":"statistic-1","version":"14.0.0","name":"My Statistic"}]}""";
    Ivy.var().set(PortalPackageFile.CUSTOM_STATISTIC.getVariableKey(), json);

    StreamedContent content = service.exportPackage();
    String exportedJson = zipEntryContent(content, PortalPackageFile.CUSTOM_STATISTIC.getFilename());

    JsonNode wrapper = BusinessEntityConverter.getObjectMapper().readTree(exportedJson);
    assertThat(wrapper.get("version").asText()).isEqualTo("14.0.0");
    assertThat(wrapper.get("items").get(0).has("version")).isFalse();
    assertThat(wrapper.get("items").get(0).get("id").asText()).isEqualTo("statistic-1");
  }

  @Test
  void importPackage_customStatistic_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.CUSTOM_STATISTIC.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_caseDetail_validJson_setsIvyVariable() throws IOException {
    CaseDetails caseDetails = buildCaseDetails("case-details-1");
    String json = toJson(List.of(caseDetails));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CASE_DETAIL.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CASE_DETAIL.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.CASE_DETAIL.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_caseDetail_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CASE_DETAIL.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CASE_DETAIL.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.CASE_DETAIL.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_thirdPartyApp_validJson_setsIvyVariable() throws IOException {
    Application application = buildApplication("app-1", "myApp", "My App", "https://example.com");
    String json = toJson(List.of(application));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.THIRD_PARTY_APP.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.THIRD_PARTY_APP.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.THIRD_PARTY_APP.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_thirdPartyApp_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.THIRD_PARTY_APP.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.THIRD_PARTY_APP.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.THIRD_PARTY_APP.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_customMenuItems_validJson_setsIvyVariable() throws IOException {
    CustomSubMenuItem item = buildCustomSubMenuItem("item-1", "My Item", "https://example.com");
    String json = toJson(List.of(item));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CUSTOM_MENU_ITEMS.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CUSTOM_MENU_ITEMS.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.CUSTOM_MENU_ITEMS.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_customMenuItems_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CUSTOM_MENU_ITEMS.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CUSTOM_MENU_ITEMS.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.CUSTOM_MENU_ITEMS.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_menuOrder_validJson_setsIvyVariable() throws IOException {
    MenuOrder menuOrder = buildMenuOrder("1", "menu-entry-1");
    String json = toJson(menuOrder);
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.MENU_ORDER.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.MENU_ORDER.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.MENU_ORDER.getVariableKey())).isEqualTo(json);
  }

  @Test
  void importPackage_menuOrder_malformedJson_doesNotUpdateVariable() throws IOException {
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.MENU_ORDER.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.MENU_ORDER.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.MENU_ORDER.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_menuOrder_jsonArrayInsteadOfObject_doesNotUpdateVariable() throws IOException {
    // MENU_ORDER deserializes to a single MenuOrder object; a JSON array is the wrong shape.
    String json = toJson(List.of(buildMenuOrder("1", "menu-entry-1")));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.MENU_ORDER.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.MENU_ORDER.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.MENU_ORDER.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_malformedEntry_preservesPreviousValidValue() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String originalJson = toJson(List.of(userMenu));
    Ivy.var().set(PortalPackageFile.USER_MENU.getVariableKey(), originalJson);

    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.USER_MENU.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.USER_MENU.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.USER_MENU.getVariableKey())).isEqualTo(originalJson);
  }

  @Test
  void importPackage_mixedValidAndInvalidAcrossTypes_appliesOnlyValid() throws IOException {
    Dashboard dashboard = buildDashboard("dashboard-1", "My Dashboard", "task_1", "Your Tasks");
    String validDashboardJson = toJson(List.of(dashboard));
    byte[] zipBytes = buildZip(Map.of(
        PortalPackageFile.DASHBOARD.getFilename(), validDashboardJson,
        PortalPackageFile.CASE_DETAIL.getFilename(), MALFORMED_JSON,
        PortalPackageFile.MENU_ORDER.getFilename(), MALFORMED_JSON));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.DASHBOARD.getFilename(), true);
    assertThat(results).containsEntry(PortalPackageFile.CASE_DETAIL.getFilename(), false);
    assertThat(results).containsEntry(PortalPackageFile.MENU_ORDER.getFilename(), false);
    assertThat(Ivy.var().get(PortalPackageFile.DASHBOARD.getVariableKey())).isEqualTo(validDashboardJson);
    assertThat(Ivy.var().get(PortalPackageFile.CASE_DETAIL.getVariableKey())).isBlank();
    assertThat(Ivy.var().get(PortalPackageFile.MENU_ORDER.getVariableKey())).isBlank();
  }

  @Test
  void importPackage_directoryEntry_isSkippedWithoutError() throws IOException {
    UserMenu userMenu = buildUserMenu("menu-1", "My Menu", "https://example.com");
    String validJson = toJson(List.of(userMenu));
    byte[] zipBytes = buildZipWithDirectory("testDirectory/",
        Map.of(PortalPackageFile.USER_MENU.getFilename(), validJson));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.USER_MENU.getFilename(), true);
    assertThat(results).doesNotContainKey("testDirectory/");
    assertThat(Ivy.var().get(PortalPackageFile.USER_MENU.getVariableKey())).isEqualTo(validJson);
  }

  @Test
  void exportThenImport_caseDetail_legacyBareObject_roundTripsWithoutDataLoss() throws IOException {
    // Regression coverage: the shipped default CaseDetails.json is exactly this shape - a single
    // bare object, not an array, not wrapped. JsonCaseDetailsMigrator.migrate() returns it
    // unchanged (no array to iterate), so writeRawVariable's export path must itself normalize it
    // into a one-element items array. Before that normalization, export produced
    // {"version": ..., "items": {...}} - items as an object, not an array - which isListWrapper()
    // (correctly) does not recognize as a wrapper on the next import, so the import silently
    // discarded the whole configuration instead of failing loudly.
    CaseDetails caseDetails = buildCaseDetails("default-case-detail");
    String rawLegacyJson = BusinessEntityConverter.entityToJsonValue(caseDetails);
    Ivy.var().set(PortalPackageFile.CASE_DETAIL.getVariableKey(), rawLegacyJson);

    StreamedContent content = service.exportPackage();
    String exportedJson = zipEntryContent(content, PortalPackageFile.CASE_DETAIL.getFilename());
    JsonNode wrapper = BusinessEntityConverter.getObjectMapper().readTree(exportedJson);
    assertThat(wrapper.get("items").isArray()).isTrue();
    assertThat(wrapper.get("items")).hasSize(1);

    Ivy.var().set(PortalPackageFile.CASE_DETAIL.getVariableKey(), "");
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CASE_DETAIL.getFilename(), exportedJson));
    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CASE_DETAIL.getFilename(), true);
    JsonNode reimported =
        BusinessEntityConverter.getObjectMapper().readTree(Ivy.var().get(PortalPackageFile.CASE_DETAIL.getVariableKey()));
    assertThat(reimported.get("items")).hasSize(1);
    assertThat(reimported.get("items").get(0).get("id").asText()).isEqualTo("default-case-detail");
  }

  @Test
  void exportThenImport_thirdPartyApp_legacyDynamicRootKey_roundTripsWithoutDataLoss() throws IOException {
    // Regression coverage: JsonThirdPartyApplicationMigrator.migrate() returns the legacy
    // {"third-party-application": [...]} shape unchanged (only the nested applications are
    // mutated in place). Exporting that shape directly as "items" produced
    // {"version": ..., "items": {"third-party-application": [...]}} - items as an object wrapping
    // the real array, not the array itself - which is silently discarded on the next import for
    // the same reason as the CaseDetails case above.
    Application application = buildApplication("app-1", "My App", "My App", "https://example.com");
    String legacyJson = """
        {"third-party-application":[%s]}""".formatted(BusinessEntityConverter.entityToJsonValue(application));
    Ivy.var().set(PortalPackageFile.THIRD_PARTY_APP.getVariableKey(), legacyJson);

    StreamedContent content = service.exportPackage();
    String exportedJson = zipEntryContent(content, PortalPackageFile.THIRD_PARTY_APP.getFilename());
    JsonNode wrapper = BusinessEntityConverter.getObjectMapper().readTree(exportedJson);
    assertThat(wrapper.get("items").isArray()).isTrue();
    assertThat(wrapper.get("items")).hasSize(1);

    Ivy.var().set(PortalPackageFile.THIRD_PARTY_APP.getVariableKey(), "");
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.THIRD_PARTY_APP.getFilename(), exportedJson));
    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.THIRD_PARTY_APP.getFilename(), true);
    JsonNode reimported = BusinessEntityConverter.getObjectMapper()
        .readTree(Ivy.var().get(PortalPackageFile.THIRD_PARTY_APP.getVariableKey()));
    assertThat(reimported.get("items")).hasSize(1);
    assertThat(reimported.get("items").get(0).get("id").asText()).isEqualTo("app-1");
  }
}
