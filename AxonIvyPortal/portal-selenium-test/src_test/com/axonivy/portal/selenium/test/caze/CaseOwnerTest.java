package com.axonivy.portal.selenium.test.caze;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class CaseOwnerTest extends BaseTest {

  private static final String USER_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/userIsOwner.ivp";
  private static final String ROLE_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/roleIsOwner.ivp";

  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "true");
  }

  @AfterEach
  public void destroy() {
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "false");
  }

  @Test
  public void testUserIsOwner() {
    redirectToRelativeLink(USER_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("demo user is owner"));
  }

  @Test
  public void testRoleIsOwner() {
    login(TestAccount.CASE_OWNER_USER);
    redirectToRelativeLink(ROLE_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("Test role is owner"));
  }
}
