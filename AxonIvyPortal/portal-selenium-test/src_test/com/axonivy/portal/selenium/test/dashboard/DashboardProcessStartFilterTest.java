package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.UserMenuPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardProcessStartFilterTest extends BaseTest {
  private static final String MY_PROFILE = "My profile";
  private static final String SHOWCASE = "Showcase";
  private static final String CUSTOMIZED = "Customized";
  private static final String CUSTOMIZE_FILTER = "Customize Filter";
  private NewDashboardPage newDashboardPage;
  private UserProfilePage userProfilePage;
  private UserMenuPage userMenuPage;
  private MainMenuPage mainMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testChangeProcessWidgetLanguage() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.changeCompactModeProcessAndSaveWidget(SHOWCASE);
    backToNewDashboardPage();
    newDashboardPage.getCompactModeProcessElementList().shouldHave(size(5));
    mainMenuPage = new MainMenuPage();
    mainMenuPage.openUserSettingMenu();
    userMenuPage = new UserMenuPage();
    userMenuPage.accessMenu(MY_PROFILE);
    userProfilePage = new UserProfilePage();
    userProfilePage.selectLanguage(3);
    userProfilePage.save();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getCompactModeProcessElementList().shouldHave(size(5));
  }

  @Test
  public void testProcessWidgetFilter() {
    newDashboardPage.openCompactModeProcessFilterPanel();
    newDashboardPage.selectCompactModeProcessFilter(CUSTOMIZED);
    newDashboardPage.saveCompactModeProcessFilter(CUSTOMIZE_FILTER);
    newDashboardPage.applyCompactModeProcessFilterPanel();
    newDashboardPage.getCompactModeProcessElementList().shouldHave(size(2));
    login(TestAccount.DEMO_USER);
    newDashboardPage.getWidgetNoti().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    login(TestAccount.ADMIN_USER);
    newDashboardPage.getWidgetNoti().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    newDashboardPage.openCompactModeProcessFilterPanel();
    newDashboardPage.selectCompactModeProcessSavedFilter(CUSTOMIZE_FILTER);
    newDashboardPage.applyCompactModeProcessFilterPanel();
    newDashboardPage.getWidgetNoti().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    newDashboardPage.getCompactModeProcessElementList().shouldHave(size(2));
  }

  private void backToNewDashboardPage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    var configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    newDashboardPage = configurationPage.backToHomePage();
  }

  @AfterEach
  public void resetUserLanguage() {
    resetLanguageOfCurrentUser();
  }
}
