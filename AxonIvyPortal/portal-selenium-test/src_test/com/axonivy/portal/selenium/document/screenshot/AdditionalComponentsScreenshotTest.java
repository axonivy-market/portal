package com.axonivy.portal.selenium.document.screenshot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessHistoryPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.component.DocumentTableComponentPage;
import com.axonivy.portal.selenium.page.component.ProcessViewerComponentPage;
import com.axonivy.portal.selenium.page.component.RoleSelectionComponentPage;
import com.axonivy.portal.selenium.page.component.SecurityMemberNameAndAvatarComponentPage;
import com.axonivy.portal.selenium.page.component.UserSelectionComponentPage;

@IvyWebTest
public class AdditionalComponentsScreenshotTest extends ScreenshotBaseTest {
  
  @Test
  public void captureScreenshotForProcessChainComponent() throws IOException {
    redirectToRelativeLink(processChainShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1300, 1150));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "process-chain");
  }

  @Test
  public void captureScreenshotGrowlMessage() throws IOException {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "true");
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "false");
    redirectToRelativeLink(createTestingCaseContainOneTask);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1500));

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    WaitHelper.waitForNavigation(() -> new NewDashboardPage().startTask(0));
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "example-global-growl-finished-task");
    
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1500));
    redirectToRelativeLink(cleanupDataLink);
    redirectToRelativeLink(createTestingTasksUrl);
    WaitHelper.waitForNavigation(() -> new NewDashboardPage().startTask(1));
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    WaitHelper.waitForNavigation(() -> taskTemplatePage.clickCancelButton());
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskStartButtonDisplay(1);
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.COMPONENTS_FOLDER + "example-global-growl-cancelled-task");
  }
  
  @Test
  public void captureScreenshotProcessHistoryComponent() throws IOException {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(createBetaCompanyUrl);
    
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 600));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "process-history-example");
    
    redirectToRelativeLink(viewBetaCompanyProcessHistoryInDialogUrl);
    processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processHistoryPage.getProcessHistoryDialog(), ScreenshotUtil.COMPONENTS_FOLDER + "process-history-dialog-example", new ScreenshotMargin(200, 200));
  }
  
  
  @Test
  public void captureScreenshotDocumentTableComponent() throws IOException {
    redirectToRelativeLink(documentTableComponentUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "document-table");
  }
  
  @Test
  public void captureScreenshotTaskAnalysisComponent() throws IOException {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);   
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    StatisticWidgetPage statisticWidgetDashboardPage = mainMenuPage.openStatisticPage();
    statisticWidgetDashboardPage.navigateToTaskAnalysisPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "task-analysis");
  }

  @Test
  public void captureScreenshotUserSelectionComponent() throws IOException {
    redirectToRelativeLink(userSelectionComponentShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    
    userSelectionComponentPage.selectFirstItemForNormalUserSelectionComponent("Backend Developer 1");
    ScreenshotUtil.captureElementScreenshot(userSelectionComponentPage.getNormalUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component");
    
    userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getFloatingLabelUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-floating-label", new ScreenshotMargin(30, 0 , 0 ,0));
    
    userSelectionComponentPage.selectFirstItemForAjaxEventUserSelectionComponent("Backend Developer 1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getAjaxEventUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-ajax-event-selected-message", new ScreenshotMargin(20,0));
    userSelectionComponentPage.openSelectionPanelForAjaxEventUserSelectionComponent("Backend Developer 1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(userSelectionComponentPage.getAjaxEventUserSelectionComponent(), ScreenshotUtil.COMPONENTS_FOLDER + "user-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));
    
  }
  
  @Test
  public void captureScreenshotRoleSelectionComponent() throws IOException {
    redirectToRelativeLink(roleSelectionComponentShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();

    roleSelectionComponentPage.selectFirstItemForDefaultRoleSelectionComponent("Everybody");
    ScreenshotUtil.captureElementScreenshot(roleSelectionComponentPage.getDefaultRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component");

    roleSelectionComponentPage.selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("Everybody");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getFloatingLabelAndExcludeRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-floating-label-and-exclude-role",
        new ScreenshotMargin(30, 0, 0, 0));

    roleSelectionComponentPage.selectFirstItemForRoleFromDefinedRoleSelectionComponent("Backend Developer");
    ScreenshotUtil.captureElementScreenshot(roleSelectionComponentPage.getRoleFromDefinedRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-from-defined-role");

    roleSelectionComponentPage.selectFirstItemForAjaxEventRoleSelectionComponent("Backend Developer");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-ajax-event-selected-message",
        new ScreenshotMargin(10, 0, 0, 0));

    roleSelectionComponentPage.openSelectionPanelForAjaxEventRoleSelectionComponent("Backend Developer");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtil.COMPONENTS_FOLDER + "role-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));
  }
  
  @Test
  public void captureScreenshotProcessViewerPage() throws IOException {
    createTestingTasks();
    MainMenuPage mainMenuPage = new MainMenuPage();
    var caseWidgetPage = mainMenuPage.openCaseList();
    var caseId = caseWidgetPage.getCaseId(0);
    redirectToRelativeLink(String.format(showProcessViewerUrl, caseId, ""));
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(!processViewerPage.getProcessRequestPath().isEmpty());
    processViewerPage.waitForSprottyToolDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_WIDGET_FOLDER + "portal-process-viewer");
  }

  @Test
  public void captureScreenshotProcessViewerComponent() throws IOException {
    redirectToRelativeLink(processViewerExampleInFrameUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    ProcessViewerComponentPage processViewerPage = new ProcessViewerComponentPage();
    processViewerPage.waitForSprottyToolDisplayed(); 
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "portal-process-viewer-component");
  }

  @Test
  public void captureScreenshotSecurityMemberNameAndAvatarComponent() throws IOException {
    redirectToRelativeLink(securityMemberNameAndAvatarExampleInFrameUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    SecurityMemberNameAndAvatarComponentPage securityMemberNameAndAvatarComponentPage = new SecurityMemberNameAndAvatarComponentPage();
    ScreenshotUtil.captureElementScreenshot(
        securityMemberNameAndAvatarComponentPage.getCurrentSessionUserSecurityMemberNameAndAvatarComponentContainer(),
        ScreenshotUtil.COMPONENTS_FOLDER + "session-user-security-member-name-and-avatar");
    ScreenshotUtil.captureElementScreenshot(
        securityMemberNameAndAvatarComponentPage.getCurrentSessionRoleSecurityMemberNameAndAvatarComponentContainer(),
        ScreenshotUtil.COMPONENTS_FOLDER + "session-role-security-member-name-and-avatar");
  }

}
