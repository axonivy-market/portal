package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogFromUserProfilePage;

@IvyWebTest
public class LanguageSettingTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestData();
  }

  @Test
  public void testChangeLanguageWhenWorkingOnTask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskIFrameByIndex(0);
    taskWidget.switchBackToParent();
    taskWidget.clickOnMyProfile();
    WorkingTaskDialogFromUserProfilePage workingTaskDialogPage = new WorkingTaskDialogFromUserProfilePage();
    workingTaskDialogPage.leaveTask();
    UserProfilePage userProfilePage = new UserProfilePage();
    assertEquals("Language", userProfilePage.getLanguageSettingTitle());
  }

  @Test
  public void testChangeLanguage() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    // select German
    userProfilePage.selectLanguage(3);
    newDashboardPage = userProfilePage.save();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    assertEquals("Prozesse", mainMenuPage.getProcessMenuItemText());
    userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    userProfilePage.save();
    mainMenuPage = userProfilePage.openMainMenu();
    mainMenuPage.waitPageLoaded();
    assertEquals("Processes", mainMenuPage.getProcessMenuItemText());
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }
}
