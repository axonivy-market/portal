package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PortalPackageTest extends BaseTest {

  private DashboardConfigurationPage dashboardConfigurationPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testVisibilityForPackageManagementTab() {
    permissions().grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    assertTrue(dashboardConfigurationPage.isPackageManagementTypeDisplayed(),
        "PackageManagement tab is not displayed");

    permissions().denySpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    assertFalse(dashboardConfigurationPage.isPackageManagementTypeDisplayed(),
        "PackageManagement tab is displayed");
  }

  @Test
  public void testImportInvalidPackageShowsNoValidFilesMessage() {
    permissions().grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    dashboardConfigurationPage.selectPackageManagementType();
    dashboardConfigurationPage.openImportPackageDialog();
    dashboardConfigurationPage.uploadPackageFile("Portal_Package_Invalid.zip");

    dashboardConfigurationPage.getImportPreviewNoValidFilesMessage()
        .shouldHave(Condition.text("No recognized Portal configuration files found"), DEFAULT_TIMEOUT);
    assertTrue(dashboardConfigurationPage.isConfirmImportButtonDisabled(),
        "Import button should be disabled when no valid files are found");
  }

  @Test
  public void testImportMixedValidAndInvalidFilesShowsPerFileIcons() {
    permissions().grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    dashboardConfigurationPage.selectPackageManagementType();
    dashboardConfigurationPage.openImportPackageDialog();
    dashboardConfigurationPage.uploadPackageFile("Portal_Package_Mixed.zip");

    dashboardConfigurationPage.getImportPreviewEntryRow("Portal_ExternalLinks.json")
        .$("i.ti-circle-check").shouldBe(appear, DEFAULT_TIMEOUT);
    dashboardConfigurationPage.getImportPreviewEntryRow("Random_Unknown_File.json")
        .$("i.ti-alert-triangle").shouldBe(appear, DEFAULT_TIMEOUT);
    assertFalse(dashboardConfigurationPage.isConfirmImportButtonDisabled(),
        "Import button should stay enabled when at least one valid file is found");
  }

  @Test
  public void testImportPartialFailureShowsPerFileResults() {
    permissions().grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    dashboardConfigurationPage.selectPackageManagementType();
    dashboardConfigurationPage.openImportPackageDialog();
    dashboardConfigurationPage.uploadPackageFile("Portal_Package_PartialFailure.zip");

    dashboardConfigurationPage.confirmImportPackage();

    dashboardConfigurationPage.getImportDialogMessages()
        .shouldHave(Condition.text("Some files could not be imported"), DEFAULT_TIMEOUT);
    dashboardConfigurationPage.getImportPreviewEntryRow("Portal_CustomMenuItems.json")
        .$("i.ti-circle-check").shouldBe(appear, DEFAULT_TIMEOUT);
    dashboardConfigurationPage.getImportPreviewEntryRow("Portal_ExternalLinks.json")
        .$("i.ti-circle-x").shouldBe(appear, DEFAULT_TIMEOUT);
    dashboardConfigurationPage.getImportPreviewEntryRow("Random_Unknown_File.json")
        .$("i.ti-alert-triangle").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testImportValidPackage() {
    permissions().grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    dashboardConfigurationPage.selectPackageManagementType();
    dashboardConfigurationPage.openImportPackageDialog();
    dashboardConfigurationPage.uploadPackageFile("Portal_Package_Valid.zip");

    $("i[class*='ti-circle-check']").shouldBe(appear, DEFAULT_TIMEOUT);

    dashboardConfigurationPage.confirmImportPackage();

    dashboardConfigurationPage.getImportDialogMessages()
        .shouldHave(Condition.text("All files were imported successfully"), DEFAULT_TIMEOUT);
  }

  private DashboardConfigurationPage openDashboardConfiguration() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    return new NewDashboardPage().openDashboardConfigurationPage();
  }

}
