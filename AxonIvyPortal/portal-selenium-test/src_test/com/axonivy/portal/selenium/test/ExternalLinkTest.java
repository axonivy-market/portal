package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class ExternalLinkTest extends BaseTest {

  @Test
  public void createExternalLink() {
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openProcessList();
    ProcessWidgetPage processPage = new ProcessWidgetPage();
    processPage.waitForStartListShow();
    String iconClass = "si-server-search";
    processPage.addExternalLink("Search page", "https://www.google.com", iconClass);
    
    $("i." + iconClass).should(Condition.appear);
  }
}
