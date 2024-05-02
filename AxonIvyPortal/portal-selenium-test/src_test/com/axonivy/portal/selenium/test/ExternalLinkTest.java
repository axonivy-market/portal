package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class ExternalLinkTest extends BaseTest {

  private static final String TEST_PROCESS = "Search page";
  private static final String DEFAULT_IMAGE = "Images/process/PROCESSMODELING.svg";

  public ExternalLinkTest() {
    super.setup();
  }

  @Test
  public void createExternalLink() {
    login(TestAccount.DEMO_USER);
    setUpExternalLink();
  }

  @Test
  public void addExternalLinkToDashboardProcessImageMode() {
    login(TestAccount.ADMIN_USER);
    setUpExternalLink();
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(TEST_PROCESS);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getStartButton().shouldBe(Condition.appear);
    SelenideElement image = newDashboardPage.getFirstImageProcess();
    assertFalse(image.shouldBe(Condition.attribute("src")).getAttribute("src").contains(DEFAULT_IMAGE));
  }

  private void setUpExternalLink() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.waitForGrowlMessageDisappear();
    mainMenuPage.openProcessList();
    ProcessWidgetPage processPage = new ProcessWidgetPage();
    processPage.waitForStartListShow();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    String iconClass = "si-server-search";
    processPage.addExternalLink(TEST_PROCESS, "https://www.google.com", iconClass, "test-welcome-widget-image.jpg");

    $("i." + iconClass).should(Condition.appear);
  }
}
