package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.PasswordValidationPage;

public class PasswordValidationTest extends BaseTest {

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private PasswordValidationPage passwordValidationPage;

  @Before
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
  }

  @Test
  public void testVisibilityForPasswordValidationTab() {
    accessToPasswordValidation();
    assertTrue("PasswordValidation tab is not displayed", passwordValidationPage.isDisplayed());

    denySpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    homePage = new HomePage();
    adminSettingsPage = homePage.openAdminSettings();
    assertFalse("PasswordValidation tab is displayed", adminSettingsPage.isPasswordValidationTabViewPresent());
  }

  @Test
  public void testVisibilityForEnablePasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    assertTrue("Enable Password Validation toggle is not displayed",
        passwordValidationPage.isPasswordValidationTogglePresent());
  }

  @Test
  public void testSaveButtonDisableAsDefault() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    assertFalse("Save button is not disabled as default", passwordValidationPage.isEnableSaveButton());
  }

  @Test
  public void testSaveButtonEnableAfterSwitchPasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    passwordValidationPage.clickOnPasswordValidationToggle();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> passwordValidationPage.isEnableSaveButton());
    assertTrue("Save button is not enabled after click on enable password validation toggle",
        passwordValidationPage.isEnableSaveButton());
  }

  @Test
  public void testEnablePolicyTableAfterEnablePasswordValidationToggle() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    if (!passwordValidationPage.isPasswordValidationToggleChecked()) {
      passwordValidationPage.clickOnPasswordValidationToggle();
    }
    assertTrue("Password policy table is not enabled", passwordValidationPage.isCheckBoxInTableEnabled());
  }

  @Test
  public void testSaveButtonDisabledAfterClickSaveButton() {
    grantSpecificPortalPermission(PortalPermission.PASSWORD_VALIDATION);
    accessToPasswordValidation();
    passwordValidationPage.clickOnPasswordValidationToggle();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> passwordValidationPage.isEnableSaveButton());
    passwordValidationPage.clickOnSaveButton();
    assertFalse("Save button is not enabled after click on enable password validation toggle",
        passwordValidationPage.isEnableSaveButton());
  }

  private PasswordValidationPage accessToPasswordValidation() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    var adminSettingsPage = new HomePage().openAdminSettings();
    assertTrue("PasswordValidation tab is NOT displayed", adminSettingsPage.isPasswordValidationTabViewPresent());
    passwordValidationPage = adminSettingsPage.openPasswordValidationTab();
    return passwordValidationPage;
  }
}