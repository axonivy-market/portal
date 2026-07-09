package com.axonivy.portal.service;

import static com.axonivy.portal.util.TestPortalPackageUtils.buildApplication;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildCaseDetails;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildCustomSubMenuItem;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildDashboard;
import static com.axonivy.portal.util.TestPortalPackageUtils.buildExternalLink;
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

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.PortalPackageFile;
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
  void importPackage_customStatistic_validJson_setsIvyVariable() throws IOException {
    Statistic statistic = buildStatistic("statistic-1", "My Statistic", "priority");
    String json = toJson(List.of(statistic));
    byte[] zipBytes = buildZip(Map.of(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), json));

    Map<String, Boolean> results = service.importPackage(zipBytes);

    assertThat(results).containsEntry(PortalPackageFile.CUSTOM_STATISTIC.getFilename(), true);
    assertThat(Ivy.var().get(PortalPackageFile.CUSTOM_STATISTIC.getVariableKey())).isEqualTo(json);
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
}
