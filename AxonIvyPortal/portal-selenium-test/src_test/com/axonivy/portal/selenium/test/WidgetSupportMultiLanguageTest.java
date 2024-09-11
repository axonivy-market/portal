package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.UserMenuPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class WidgetSupportMultiLanguageTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testAddNewCaseList() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.waitPreviewTableLoaded();
    String widgetTitle = "Your New Cases";
    String updatedTitle = "German Your New Cases";
    newCaseWidget.changeWidgetTitle(widgetTitle);
    var addLanguageButton = newCaseWidget.getAddLanguageButton();
    addLanguageButton.shouldBe(Condition.visible, DEFAULT_TIMEOUT).click();
    var multipleLanguageDialog = newCaseWidget.getMultipleLanguageDialog();
    multipleLanguageDialog.getText();
    var elementsInput = multipleLanguageDialog.$$("td input");
    elementsInput.get(0).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(1).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(2).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(3).shouldBe(Condition.value(widgetTitle));

    elementsInput.get(2).setValue(updatedTitle);
    elementsInput.get(1).click();

    multipleLanguageDialog.$("button[type='submit']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    newCaseWidget.save();

    changeUserLanguage();
    var caseWidget = newDashboardPage.selectCaseWidget(updatedTitle);
    caseWidget.expand().shouldHave(size(1));
  }

  @Test
  public void testAddNewTaskList() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    newTaskWidget.waitPreviewTableLoaded();
    String widgetTitle = "Your New Tasks";
    String updatedTitle = "German Your New Tasks";
    newTaskWidget.changeWidgetTitle(widgetTitle);
    var addLanguageButton = newTaskWidget.getAddLanguageButton();
    addLanguageButton.click();
    var multipleLanguageDialog = newTaskWidget.getMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");
    elementsInput.get(0).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(1).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(2).shouldBe(Condition.value(widgetTitle));
    elementsInput.get(3).shouldBe(Condition.value(widgetTitle));

    elementsInput.get(2).setValue(updatedTitle);
    elementsInput.get(1).click();

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    newTaskWidget.save();

    changeUserLanguage();
    var taskWidget = newDashboardPage.selectTaskWidget(updatedTitle);
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    taskWidget.expand().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

  @Test
  public void testAddNewProcessList() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    ProcessEditWidgetNewDashBoardPage newProcessWidget = newDashboardDetailsEditPage.addNewProcessWidget();
    String processTitle = "Your New Processes";
    String updatedTitle = "German Your New Processes";
    newProcessWidget.changeProcessTitle(processTitle);
    var addLanguageButton = newProcessWidget.getAddLanguageButton();
    addLanguageButton.click();
    var multipleLanguageDialog = newProcessWidget.getMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");
    elementsInput.get(0).shouldBe(Condition.value(processTitle));
    elementsInput.get(1).shouldBe(Condition.value(processTitle));
    elementsInput.get(2).shouldBe(Condition.value(processTitle));
    elementsInput.get(3).shouldBe(Condition.value(processTitle));

    elementsInput.get(2).setValue(updatedTitle);
    elementsInput.get(1).click();

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    newProcessWidget.save();

    changeUserLanguage();
    var processWidget = newDashboardPage.selectProcessWidget(processTitle);
    processWidget.expand().shouldHave(size(1));
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }

  private void changeUserLanguage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    var configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    configurationPage.backToHomePage();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openUserSettingMenu();
    mainMenuPage.waitCaseContainerAppear();
    UserMenuPage userMenuPage = new UserMenuPage();
    userMenuPage.accessMenu("My profile");
    UserProfilePage userProfilePage = new UserProfilePage();
    userProfilePage.selectLanguage(3);
    userProfilePage.save();
  }

  @AfterEach
  public void resetUserLanguage() {
    resetLanguageOfCurrentUser();
  }
}
