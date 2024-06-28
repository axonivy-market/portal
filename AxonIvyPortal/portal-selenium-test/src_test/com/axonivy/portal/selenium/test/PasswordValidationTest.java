package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.PasswordValidationPage;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PasswordValidationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private AdminSettingsPage adminSettingsPage;
  private PasswordValidationPage passwordValidationPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityForPasswordValidationTab() {
    accessToPasswordValidation();
    assertTrue(passwordValidationPage.isDisplayed());

    denySpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    newDashboardPage = new NewDashboardPage();
    adminSettingsPage = newDashboardPage.openAdminSettings();
    assertFalse(adminSettingsPage.isPasswordValidationTabViewPresent());
  }

  @Test
  public void testVisibilityForEnablePasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    assertTrue(passwordValidationPage.isPasswordValidationTogglePresent());
  }

  @Test
  public void testSaveButtonDisableAsDefault() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    assertFalse(passwordValidationPage.isEnableSaveButton());
  }

  @Test
  public void testSaveButtonEnableAfterSwitchPasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    passwordValidationPage.clickOnPasswordValidationToggle();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> passwordValidationPage.isEnableSaveButton());
    assertTrue(passwordValidationPage.isEnableSaveButton());
  }

  @Test
  public void testEnablePolicyTableAfterEnablePasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    if (!passwordValidationPage.isPasswordValidationToggleChecked()) {
      passwordValidationPage.clickOnPasswordValidationToggle();
    }
    assertTrue(passwordValidationPage.isCheckBoxInTableEnabled());
  }

  @Test
  public void testSaveButtonDisabledAfterClickSaveButton() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    passwordValidationPage.clickOnPasswordValidationToggle();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> passwordValidationPage.isEnableSaveButton());
    passwordValidationPage.clickOnSaveButton();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> !passwordValidationPage.isEnableSaveButton());
  }

  private PasswordValidationPage accessToPasswordValidation() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    NewDashboardPage newDashboard = new NewDashboardPage();
    newDashboard.waitPageLoaded();
    var adminSettingsPage = newDashboard.openAdminSettings();
    assertTrue(adminSettingsPage.isPasswordValidationTabViewPresent());
    passwordValidationPage = adminSettingsPage.openPasswordValidationTab();
    return passwordValidationPage;
  }
}
