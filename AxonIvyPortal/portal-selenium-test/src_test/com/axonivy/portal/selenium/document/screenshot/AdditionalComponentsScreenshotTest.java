package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessHistoryPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
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
    ScreenshotUtils.resizeBrowser(new Dimension(1300, 1150));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.COMPONENTS_FOLDER + "process-chain");
  }

  @Test
  public void captureScreenshotGrowlMessage() throws IOException {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "true");
    redirectToRelativeLink(createTestingCaseContainOneTask);
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1500));
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskWidgetLoaded();
    newDashboardPage.startTask(0);
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 800));
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtils
        .captureHalfTopPageScreenShot(ScreenshotUtils.COMPONENTS_FOLDER + "example-global-growl-finished-task");

    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1500));
    redirectToRelativeLink(cleanupDataLink);
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
    WaitHelper.waitForNavigation(() -> new NewDashboardPage().startTask(1));
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.switchToIFrameOfTask();
    taskTemplatePage.clickCancelButton();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 800));
    taskTemplatePage.switchToDefaultContent();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    ScreenshotUtils
        .captureHalfTopPageScreenShot(ScreenshotUtils.COMPONENTS_FOLDER + "example-global-growl-cancelled-task");
  }

  @Test
  public void captureScreenshotProcessHistoryComponent() throws IOException {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(createBetaCompanyUrl);

    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 600));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.COMPONENTS_FOLDER + "process-history-example");

    redirectToRelativeLink(viewBetaCompanyProcessHistoryInDialogUrl);
    processHistoryPage = new ProcessHistoryPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processHistoryPage.getProcessHistoryDialog(),
        ScreenshotUtils.COMPONENTS_FOLDER + "process-history-dialog-example", new ScreenshotMargin(200, 200));
  }


  @Test
  public void captureScreenshotDocumentTableComponent() throws IOException {
    redirectToRelativeLink(documentTableComponentUrl);
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.COMPONENTS_FOLDER + "document-table");
  }

  @Test
  public void captureScreenshotUserSelectionComponent() throws IOException {
    redirectToRelativeLink(userSelectionComponentShowcaseUrl);
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();

    userSelectionComponentPage.selectFirstItemForNormalUserSelectionComponent("Backend Developer 1");
    ScreenshotUtils.captureElementScreenshot(userSelectionComponentPage.getNormalUserSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "user-selection-component");

    userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        userSelectionComponentPage.getFloatingLabelUserSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "user-selection-component-floating-label",
        new ScreenshotMargin(30, 0, 0, 0));

    userSelectionComponentPage.selectFirstItemForAjaxEventUserSelectionComponent("Backend Developer 1");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        userSelectionComponentPage.getAjaxEventUserSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "user-selection-component-ajax-event-selected-message",
        new ScreenshotMargin(20, 0));
    userSelectionComponentPage.openSelectionPanelForAjaxEventUserSelectionComponent("Backend Developer 1");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        userSelectionComponentPage.getAjaxEventUserSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "user-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));

  }

  @Test
  public void captureScreenshotRoleSelectionComponent() throws IOException {
    redirectToRelativeLink(roleSelectionComponentShowcaseUrl);
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();

    roleSelectionComponentPage.selectFirstItemForDefaultRoleSelectionComponent("Everybody");
    ScreenshotUtils.captureElementScreenshot(roleSelectionComponentPage.getDefaultRoleSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "role-selection-component");

    roleSelectionComponentPage.selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("Everybody");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getFloatingLabelAndExcludeRoleSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "role-selection-component-floating-label-and-exclude-role",
        new ScreenshotMargin(30, 0, 0, 0));

    roleSelectionComponentPage.selectFirstItemForRoleFromDefinedRoleSelectionComponent("Backend Developer");
    ScreenshotUtils.captureElementScreenshot(roleSelectionComponentPage.getRoleFromDefinedRoleSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "role-selection-component-from-defined-role");

    roleSelectionComponentPage.selectFirstItemForAjaxEventRoleSelectionComponent("Backend Developer");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "role-selection-component-ajax-event-selected-message",
        new ScreenshotMargin(10, 0, 0, 0));

    roleSelectionComponentPage.openSelectionPanelForAjaxEventRoleSelectionComponent("Backend Developer");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        roleSelectionComponentPage.getAjaxEventRoleSelectionComponent(),
        ScreenshotUtils.COMPONENTS_FOLDER + "role-selection-component-ajax-expand", new ScreenshotMargin(20, 0, 60, 0));
  }

  @Test
  public void captureScreenshotProcessViewerPage() throws IOException {
    createTestingTasks();
    showNewDashboard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    var caseWidgetPage = mainMenuPage.openCaseList();
    var caseId = caseWidgetPage.getCaseId(0);
    redirectToRelativeLink(String.format(showProcessViewerUrl, caseId, ""));
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(!processViewerPage.getProcessRequestPath().isEmpty());
    processViewerPage.waitForSprottyToolDisplayed();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_WIDGET_FOLDER + "portal-process-viewer");
  }

  @Test
  public void captureScreenshotProcessViewerComponent() throws IOException {
    redirectToRelativeLink(processViewerExampleInFrameUrl);
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ProcessViewerComponentPage processViewerPage = new ProcessViewerComponentPage();
    processViewerPage.waitForSprottyToolDisplayed();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.COMPONENTS_FOLDER + "portal-process-viewer-component");
  }

  @Test
  public void captureScreenshotSecurityMemberNameAndAvatarComponent() throws IOException {
    redirectToRelativeLink(securityMemberNameAndAvatarExampleInFrameUrl);
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    SecurityMemberNameAndAvatarComponentPage securityMemberNameAndAvatarComponentPage =
        new SecurityMemberNameAndAvatarComponentPage();
    ScreenshotUtils.captureElementScreenshot(
        securityMemberNameAndAvatarComponentPage.getCurrentSessionUserSecurityMemberNameAndAvatarComponentContainer(),
        ScreenshotUtils.COMPONENTS_FOLDER + "session-user-security-member-name-and-avatar");
    ScreenshotUtils.captureElementScreenshot(
        securityMemberNameAndAvatarComponentPage.getCurrentSessionRoleSecurityMemberNameAndAvatarComponentContainer(),
        ScreenshotUtils.COMPONENTS_FOLDER + "session-role-security-member-name-and-avatar");
  }

}
