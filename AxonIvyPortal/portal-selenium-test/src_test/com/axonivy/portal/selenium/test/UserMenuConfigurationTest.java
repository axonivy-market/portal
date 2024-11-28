package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.UserMenuPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class UserMenuConfigurationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    mainMenuPage = new MainMenuPage();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testUserMenuExternalLink() {
    login(TestAccount.DEMO_USER);
    createJSonFile("custom-user-menu.json", PortalVariable.USER_MENU.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    mainMenuPage.openUserSettingMenu();
    UserMenuPage userMenu = new UserMenuPage();
    userMenu.findMenu("Axon Ivy").shouldBe(Condition.appear);
  }

  @Test
  public void testUserMenuIvyProcessStarts() {
    login(TestAccount.ADMIN_USER);
    createJSonFile("custom-user-menu.json", PortalVariable.USER_MENU.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    mainMenuPage.openUserSettingMenu();
    UserMenuPage userMenu = new UserMenuPage();
    userMenu.findMenu("Create Investment").shouldBe(Condition.appear);
    userMenu.accessMenu("Create Investment");
    TaskTemplatePage templatePage = new TaskTemplatePage();
    templatePage.switchToIFrameOfTask();
    templatePage.getElementInPortalIFramTask("[id$=':investment-table']").shouldBe(Condition.appear);
  }
}
