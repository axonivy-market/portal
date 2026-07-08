package com.axonivy.portal.service;

import static com.axonivy.portal.util.TestPortalPackageUtils.buildDashboard;
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

import org.junit.jupiter.api.Test;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.PortalPackageFile;
import ch.ivy.addon.portalkit.service.PortalPackageService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
public class TestPortalPackageService {
  PortalPackageService service = PortalPackageService.getInstance();

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
