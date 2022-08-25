package com.axonivy.portal.selenium.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.WelcomeEditWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.WelcomeWidgetNewDashboardPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest(headless = false)
public class DashboardWelcomeWidgetTest extends BaseTest {

  private static final String WIDGET_ID = "welcome-widget1";
  private NewDashboardPage newDashboardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testEditWelcomeWidget() {
    login(TestAccount.ADMIN_USER);
    resetLanguageOfCurrentUser();
    createJSonFile("dashboard-has-welcome-widget.json", PortalVariable.DASHBOARD.key);
    redirectToNewDashBoard();
    WelcomeEditWidgetNewDashboardPage editPage = newDashboardPage.editWelcomeWidgetConfiguration(WIDGET_ID);

    editPage.selectTextSize("Heading 3");
    editPage.selectTextPosition("Top-right corner");
    List<String> welcomeTexts = new ArrayList<>();
    welcomeTexts.add("new widget (English)");
    welcomeTexts.add("new widget (France)");
    welcomeTexts.add("new widget (German)");
    welcomeTexts.add("new widget (Spanish)");
    editPage.inputWelcomeTexts(welcomeTexts);

    editPage.uploadImage("test-welcome-widget-image.jpg");
    editPage.save();

    WelcomeWidgetNewDashboardPage welcomePage = new WelcomeWidgetNewDashboardPage();
    welcomePage.getWelcomeText(WIDGET_ID).shouldHave(Condition.text("new widget (English)"));
    welcomePage.getWelcomeText(WIDGET_ID).shouldHave(Condition.cssClass("HEADING_3"));
    welcomePage.getWelcomeText(WIDGET_ID).shouldHave(Condition.cssClass("top")).shouldHave(Condition.cssClass("right"));

    setLanguageOfCurrentUserToGerman();
    redirectToNewDashBoard();
    welcomePage = new WelcomeWidgetNewDashboardPage();
    welcomePage.getWelcomeText(WIDGET_ID).shouldHave(Condition.text("new widget (German)"));

    resetLanguageOfCurrentUser();
  }
}