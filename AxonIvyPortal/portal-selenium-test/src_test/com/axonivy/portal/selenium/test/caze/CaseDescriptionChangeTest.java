package com.axonivy.portal.selenium.test.caze;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class CaseDescriptionChangeTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private CaseWidgetPage casePage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testUserWithoutPermissionCannotChangeCaseDescription() {
    int caseIndex = 0;
    newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertFalse(detailsPage.isCaseDescriptionChangeComponentPresented(caseIndex));
  }

  public void changeLanguage(int index) {
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    newDashboardPage = userProfilePage.save();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    var mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
  }

  @AfterEach
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
}
