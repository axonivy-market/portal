package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.FileHelper;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.DocumentTableComponentPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessHistoryPage;
import portal.guitest.page.RoleSelectionComponentPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserSelectionComponentPage;

public class AdditionalComponentsScreenshotTest extends ScreenshotTest {

  @Test
  public void captureScreenshotForProcessChainComponent() throws IOException {
    redirectToRelativeLink(processChainShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 950));
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "process-chain");
  }
  
  @Test
  public void captureScreenshotGrowlMessage() throws IOException {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting("DISPLAY_MESSAGE_AFTER_FINISH_TASK", "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingCaseContainOneTask);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTaskWithoutUI(0);
    HomePage homePage = new HomePage();
    homePage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "example-global-growl-finished-task");
    
    taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    homePage = taskTemplatePage.clickCancelAndLeftButton();
    homePage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "example-global-growl-cancelled-task");
  }
  
  @Test
  public void captureScreenshotProcessHistoryComponent() throws IOException {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(createBetaCompanyUrl);
    redirectToRelativeLink(createBetaCompanyUrl);
    
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "process-history-example");
    
    redirectToRelativeLink(viewBetaCompanyProcessHistoryUrl);
    processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processHistoryPage.getProcessHistoryDialog(), ScreenshotUtil.COMPONENTS_FOLDER + "process-history-dialog-example", new ScreenshotMargin(100, 30));
    
  }
  
  @Test
  public void captureScreenshotDocumentTableComponent() throws IOException {
    redirectToRelativeLink(documentTableComponentUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    ScreenshotUtil.captureElementScreenshot(documentTableComponentPage.getDocumentTableComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "document-table");
  }
  
  @Test
  public void captureScreenshotTaskAnalysisComponent() throws IOException {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.navigateToTaskAnalysisPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "task-analysis");
  }
  
  @Test
  public void captureScreenshotUserSelectionComponenet() throws IOException {
    redirectToRelativeLink(userSelectionComponentShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    
    userSelectionComponentPage.selectFirstItemForNormalUserSelectionComponent("1");
    ScreenshotUtil.captureElementScreenshot(userSelectionComponentPage.getNormalUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component");
    
    userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getFloatingLabelUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-floating-label", new ScreenshotMargin(30, 0 , 0 ,0));
    
    userSelectionComponentPage.selectFirstItemForAjaxEventUserSelectionComponent("1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getAjaxEventUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-ajax-event-selected-message", new ScreenshotMargin(20,0));
    userSelectionComponentPage.openSelectionPanelForAjaxEventUserSelectionComponent(" ");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getAjaxEventUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));
    
  }

  @Test
  public void captureScreenshotRoleSelectionComponenet() throws IOException {
    redirectToRelativeLink(roleSelectionComponentShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();

    roleSelectionComponentPage.selectFirstItemForDefaultRoleSelectionComponent("E");
    ScreenshotUtil.captureElementScreenshot(roleSelectionComponentPage.getDefaultRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component");

    roleSelectionComponentPage.selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("E");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getFloatingLabelAndExcludeRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-floating-label-and-exclude-role",
        new ScreenshotMargin(30, 0, 0, 0));

    roleSelectionComponentPage.selectFirstItemForRoleFromDefinedRoleSelectionComponent("E");
    ScreenshotUtil.captureElementScreenshot(roleSelectionComponentPage.getRoleFromDefinedRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-from-defined-role");

    roleSelectionComponentPage.selectFirstItemForAjaxEventRoleSelectionComponent("E");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-ajax-event-selected-message",
        new ScreenshotMargin(10, 0, 0, 0));

    roleSelectionComponentPage.openSelectionPanelForAjaxEventRoleSelectionComponent(" ");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));
  }
}
