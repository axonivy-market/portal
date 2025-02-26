package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static java.time.Duration.ZERO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.project.portal.test.Responsible;

/**
 * A base test that other tests extend it. It will test on browser IE by default. It provides feature to take screenshot
 * of failed tests and utility methods.
 *
 */
public class BaseTest {

  private final static String LOGIN_URL_PATTERN =
      "/PortalKitTestHelper/1636734E13CEC872/login.ivp?username=%s&password=%s";
  protected final static String PORTAL_HOME_PAGE_URL = "/portal/1549F58C18A6C562/DefaultApplicationHomePage.ivp";
  protected final static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public BaseTest() {}

  @AfterEach
  public void tearDown() {
    WebDriverRunner.getWebDriver().quit();
  }

  protected String simplePaymentUrl = "portal-developer-examples/162511D2577DBA88/simplePayment.ivp";
  protected String createTaskWithIframe = "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp";
  protected String processViewerPermissionExampleUrl = "portal-developer-examples/183C12775FE2BAB8/start.ivp";
  protected String testProcessViewerPermissionUrl =
      "portalKitTestHelper/153CACC26D0D4C3D/testProcessViewerPermission.ivp";
  protected String createTestingTasksUrl = "portal-developer-examples/162511D2577DBA88/CategoriedLeaveRequest.ivp";
  protected String createTestDataForCustomFieldsWithCMS = "portal-developer-examples/162511D2577DBA88/createDataForCustomFieldWithCmsValue.ivp";
  protected String createRelatedTaskWithNoResponsible =
      "InternalSupport/14B2FC03D2E87141/TestRelatedTaskWithNoResponsible.ivp";
  protected String createCaseWithTechnicalCaseUrl = "InternalSupport/15B1EA24CCF377E8/OrderPizza.ivp";
  protected String create12CasesWithCategoryUrl = "internalSupport/15C7B30FB93C827E/create12CasesWithCategory.ivp";
  protected String businessCaseUrl = "internalSupport/15B1EA24CCF377E8/updateCheckInTime.ivp";
  protected String hideCaseUrl = "portal-developer-examples/16583F0F73864543/createHiddenTechnicalCase.ivp";
  protected String createSystemTaskUrl = "portal-developer-examples/16583F0F73864543/createSystemTask.ivp";
  protected String createTestingCaseMapUrl = "internalSupport/764871e4-cf70-401f-83fb-9e99fa897fc4.icm";
  protected String createTestingCaseUrlForCustomizationAdditionalCaseDetails =
      "portal-components-examples/176465FBFE257CF3/createInvestmentRequest.ivp";
  protected String createTestingCaseUrlForDefaultAdditionalCaseDetails =
      "internalSupport/14B2FC03D2E87141/DefaultAdditionalCaseDetails.ivp";
  protected String createTestingCaseContainOneTask = "internalSupport/14B2FC03D2E87141/CreateSupportTicket.ivp";
  protected String createTaskWithNotExistedActivatorUrl =
      "internalSupport/14B2FC03D2E87141/createTaskWithNotExistedActivator.ivp";
  protected String createTaskForRoleInvolved = "InternalSupport/171E2BB0DB49C362/roleInvolved.ivp";
  protected String cleanupDataLink = "portalKitTestHelper/1511A66AF619A768/cleanData.ivp";
  protected String complexPaymentUrl = "portal-developer-examples/162511D2577DBA88/complexPayment.ivp";
  protected String createAlphaCompanyUrl = "portal-components-examples/1818977D467E3129/createAlphaCompany.ivp";
  protected String viewAlphaCompanyProcessHistoryUrl =
      "portal-components-examples/1818977D467E3129/viewProcessHistoryOfAlphaCompany.ivp";
  protected String createBetaCompanyUrl = "portal-components-examples/1818977D467E3129/createBetaCompany.ivp";
  protected String viewBetaCompanyProcessHistoryInDialogUrl =
      "portal-components-examples/1818977D467E3129/viewProcessHistoryOfBetaCompany.ivp";
  protected String createNewPaymentUrl = "portal-developer-examples/162511D2577DBA88/createNewPayment.ivp";
  protected String documentTableComponentUrl =
      "portal-components-examples/1818938E7EBC9329/showCustomizedDocumentTableExample.ivp";
  protected String cleanUpAbsencesAndSubstituesLink =
      "portalKitTestHelper/1511A66AF619A768/cleanAbsencesAndSubstitues.ivp";
  protected String createCasesForCaseListCustomization =
      "portal-developer-examples/162511D2577DBA88/createCasesForCaseListCustomization.ivp";
  protected String processChainShowcaseUrl = "portal-components-examples/181897243F2BFDD3/showProcessChainExamples.ivp";
  protected String userSelectionComponentShowcaseUrl =
      "portal-components-examples/18189AF10B521DF4/showUserSelectionExamples.ivp";
  protected String roleSelectionComponentShowcaseUrl =
      "portal-components-examples/181899823E886ABB/showRoleSelectionExamples.ivp";
  protected String startUserExampleProcess = "portal-user-examples/17236DB1D3DA14C0/userExampleGuide.ivp";
  protected String userIsOwnerUrl = "internalSupport/16A68510A341BE6E/userIsOwner.ivp";
  protected String showTaskNoteHistoryUrl = "portal/1549F58C18A6C562/showTaskNoteHistory.ivp?uuid=%s";
  protected String showCaseNoteHistoryUrl = "portal/1549F58C18A6C562/showCaseNoteHistory.ivp?uuid=%s";
  protected String createTaskWithSystemState = "portalKitTestHelper/153CACC26D0D4C3D/createTaskWithSystemState.ivp";
  protected String createTechnicalStateUrl = "portal-developer-examples/162511D2577DBA88/createTechnicalStateTasks.ivp";
  protected String portalKitTestHelperPasswordResetUrl = "portalKitTestHelper/176463FD4BBF6C93/PasswordReset.ivp";
  protected String portalPasswordResetUrl = "portal/1549F58C18A6C562/PasswordResetPage.ivp?token=%s&username=%s";
  protected String cleanSessionCacheUrl =
      "http://localhost:8081/designer/pro/PortalKitTestHelper/17208192E0AF4185/cleanSessionCache.ivp";
  protected String createSampleDashboardUrl = "portalKitTestHelper/17F2050944B46BB0/createSampleDashboard.ivp";
  protected String createTestingEscalationTasksUrl =
      "portal-developer-examples/162511D2577DBA88/CreateTaskForEscalation.ivp";
  protected String createJSonFileUrl = "PortalKitTestHelper/153CACC26D0D4C3D/createJSonFile.ivp?filePath=%s&key=%s";
  protected String grantDashboardWriteOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardWriteOwnPermission.ivp";
  protected String denyDashboardWriteOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardWriteOwnPermission.ivp";
  protected String grantDashboardWritePublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardWritePublicPermission.ivp";
  protected String denyDashboardWritePublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardWritePublicPermission.ivp";
  protected String cleanPortalNewsFeedUrl = "portalKitTestHelper/1511A66AF619A768/cleanNewsFeed.ivp";
  protected String grantDashboardExportOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardExportOwnPermission.ivp";
  protected String denyDashboardExportOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardExportOwnPermission.ivp";
  protected String grantDashboardExportPublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardExportPublicPermission.ivp";
  protected String denyDashboardExportPublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardExportPublicPermission.ivp";
  protected String grantDashboardImportOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardImportOwnPermission.ivp";
  protected String denyDashboardImportOwnPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardImportOwnPermission.ivp";
  protected String grantDashboardImportPublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardImportPublicPermission.ivp";
  protected String denyDashboardImportPublicPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardImportPublicPermission.ivp";
  protected String grantDashboardShareLinkPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/grantDashboardShareLinkPermission.ivp";
  protected String denyDashboardShareLinkPermissionUrl =
      "PortalKitTestHelper/14DE09882B540AD5/denyDashboardShareLinkPermission.ivp";
  protected String grantShareLinkCaseDetailsPermission =
      "PortalKitTestHelper/14DE09882B540AD5/grantCaseDetailsShareLinkPermission.ivp";
  protected String denyShareLinkCaseDetailsPermission =
      "PortalKitTestHelper/14DE09882B540AD5/denyCaseDetailsShareLinkPermission.ivp";
  protected String grantShareLinkTaskDetailsPermission =
      "PortalKitTestHelper/14DE09882B540AD5/grantTaskDetailsShareLinkPermission.ivp";
  protected String denyShareLinkTaskDetailsPermission =
      "PortalKitTestHelper/14DE09882B540AD5/denyTaskDetailsShareLinkPermission.ivp";
  protected String createSampleProcesses = "portalKitTestHelper/153CACC26D0D4C3D/createSampleProcesses.ivp";
  protected String showProcessViewerUrl = "portal/1549F58C18A6C562/PortalProcessViewer.ivp?caseId=%s&processKey=%s";
  protected String processViewerExampleInFrameUrl =
      "portal-components-examples/1821592826979C20/showProcessViewerOfLeaveRequestUsingProcessLink.ivp";
  protected String securityMemberNameAndAvatarExampleInFrameUrl =
      "/portal-components-examples/182A5FCAF7FC6B1A/showSecurityMemberNameAndAvatarExamples.ivp?embedInFrame";
  protected String templateInFrameExampleInFrameUrl =
      "/portal-developer-examples/162511D2577DBA88/createTaskWithFrameTemplate.ivp?embedInFrame";
  protected String createEventTestUrl = "portal-developer-examples/17A2C6D73AB4186E/CreateEventTest.ivp";
  protected String createCustomActionCaseExampleUrl =
      "portal-developer-examples/18B4240043B838C5/CreateCustomActionCaseExample.ivp";
  protected String grantPortalPermission = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
  protected String testCaseListPermission = "internalSupport/14B2FC03D2E87141/TestCaseListPermissions.ivp";
  protected String createDataCreatedDate = "portalKitTestHelper/18B031C59C3C7814/CreateDataForCreatedDate.ivp";
  protected String createDataForStatisticWidget = "InternalSupport/14B2FC03D2E87141/CategoriedLeaveRequest.ivp";
  protected String createDataFinishedDate = "portalKitTestHelper/18B031C59C3C7814/CreateDataForFinishedDate.ivp";
  protected String grantCaseReadAllOwnRoleInvolvedPermission = "PortalKitTestHelper/14DE09882B540AD5/grantCaseReadAllOwnRoleInvolvedPermission.ivp";
  protected String denyCaseReadAllOwnRoleInvolvedPermission = "PortalKitTestHelper/14DE09882B540AD5/denyCaseReadAllOwnRoleInvolvedPermission.ivp";
  protected String multipleOwnersUrl = "InternalSupport/16A68510A341BE6E/multipleOwners.ivp";
  protected String displayCustomFieldCaseOnTaskWidget = "PortalKitTestHelper/153CACC26D0D4C3D/displayCustomFieldCaseOnTaskWidget.ivp";
  
  protected void redirectToNewDashBoard() {
    open(EngineUrl.createProcessUrl(PORTAL_HOME_PAGE_URL));
  }

  /**
   * Default setup for each test It will clean up all test data and login with account demo
   */
  public void setup() {
    launchBrowserAndGotoRelativeLink(cleanupDataLink);
    createJSonFile("default-dashboard.json", PortalVariable.DASHBOARD.key);
  }

  /**
   * Alternative setup, just login with input account, don't cleanup anything
   * 
   * @param relativePath
   * @param account
   */
  public void setupWithAlternativeLinkAndAccount(String relativePath, TestAccount account) {
    launchBrowserAndGotoRelativeLink(relativePath);
    login(account);
  }

  public void launchBrowserAndGotoRelativeLink(String relativeProcessStartLink) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartLink));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public void redirectToRelativeLink(String relativeProcessStartUrl) {
    LinkNavigator.redirectToRelativeLink(relativeProcessStartUrl);
  }

  /**
   * {@link #launchBrowserAndGotoRelativeLink(String)} will open link using existing browser. This function will launch
   * new window/tab and focus on that window/tab
   * @param type 
   * 
   * @return String
   */
  public String openNewTabOrWindow(WindowType type) {
    WebDriverRunner.getWebDriver().switchTo().newWindow(type);
    return WebDriverRunner.getWebDriver().getWindowHandle();
  }

  public void launchBrowserAndLogoutInDesigner() {
    try {
      open(UrlHelpers.getLogoutLink());
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public void createTestingTasks() {
    redirectToRelativeLink(createTestingTasksUrl);
  }

  public void createRelatedTaskWithNoResponsible() {
    redirectToRelativeLink(createRelatedTaskWithNoResponsible);
  }

  public void grantTaskReadAllPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL =
        "portalKitTestHelper/14DE09882B540AD5/grantTaskReadAllPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  public void grantTaskReadOwnCaseTaskPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL =
        "portalKitTestHelper/14DE09882B540AD5/grantTaskReadOwnCaseTaskPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  public void denyReadAllPermissionFromCurrentUser() {
    String grantAllPermissionsForAdminUserURL =
        "portalKitTestHelper/14DE09882B540AD5/denyReadAllPermissionFromCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  public void denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser() {
    String denyDocumentOfInvolvedCaseWritePemissionURL =
        "portalKitTestHelper/14DE09882B540AD5/denyDocumentOfInvolvedCaseWritePemission.ivp";
    redirectToRelativeLink(denyDocumentOfInvolvedCaseWritePemissionURL);
  }

  public void grantDocumentOfInvolvedCaseWritePemissionToCurrentUser() {
    String grantDocumentOfInvolvedCaseWritePemissionURL =
        "portalKitTestHelper/14DE09882B540AD5/grantDocumentOfInvolvedCaseWritePemission.ivp";
    redirectToRelativeLink(grantDocumentOfInvolvedCaseWritePemissionURL);
  }

  public void grantSpecificPortalPermission(PortalPermission portalPermission) {
    String grantSpecificPortalPermissionLink =
        "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, portalPermission.getValue()));
  }

  public void denySpecificPortalPermission(PortalPermission portalPermission) {
    String denySpecificPortalPermissionLink =
        "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, portalPermission.getValue()));
  }

  public void cleanUpGlobalVariables() {
    String cleanUpURL = "portalKitTestHelper/1511A66AF619A768/cleanupGlobalVars.ivp";
    redirectToRelativeLink(cleanUpURL);
  }

  public void resetLanguageOfCurrentUser() {
    redirectToRelativeLink("portalKitTestHelper/1511A66AF619A768/resetLanguageOfCurrentUser.ivp");
  }

  public void setLanguageOfCurrentUserToGerman() {
    redirectToRelativeLink("PortalKitTestHelper/17208192E0AF4185/setLanguageOfCurrentUserToGerman.ivp");
  }

  public void createThirdPartyApp() {
    redirectToRelativeLink("PortalKitTestHelper/153CACC26D0D4C3D/createThirdPartyApp.ivp");

  }

  public void refreshPage() {
    refresh();
  }

  protected void login(TestAccount testAccount) {
    String username;
    String password;
    try {
      username = URLEncoder.encode(testAccount.getUsername(), "UTF-8");
      password = URLEncoder.encode(testAccount.getPassword(), "UTF-8");
      try {
        open(EngineUrl.createProcessUrl(String.format(LOGIN_URL_PATTERN, username, password)));
        $(".js-dashboard__wrapper").shouldBe(Condition.exist);
      } catch (Error e) {
        open(EngineUrl.createProcessUrl(String.format(LOGIN_URL_PATTERN, username, password)));
      }
    } catch (UnsupportedEncodingException e) {
      throw new PortalGUITestException(e);
    }
    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);
  }

  public void updatePortalSetting(String portalSettingName, String portalSettingValue) {
    try {
      String encodeSettingName = URLEncoder.encode(portalSettingName, "UTF-8");
      String encodeSettingValue = URLEncoder.encode(portalSettingValue, "UTF-8");
      String updatePortalSettingLink =
          "portalKitTestHelper/17208192E0AF4185/updatePortalSetting.ivp?settingName=%s&settingValue=%s";
      redirectToRelativeLink(String.format(updatePortalSettingLink, encodeSettingName, encodeSettingValue));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public void updateGlobalVariable(String variableName, String variableValue) {
    try {
      String encodeVariableName = URLEncoder.encode(variableName, "UTF-8");
      String encodeVariableValue = URLEncoder.encode(variableValue, "UTF-8");
      String updateGlobalVariableLink =
          "portalKitTestHelper/1749B87B8C1B77BE/updateGlobalVariable.ivp?variableName=%s&variableValue=%s";
      redirectToRelativeLink(String.format(updateGlobalVariableLink, encodeVariableName, encodeVariableValue));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public void goToTaskNoteHistoryPage(String taskId) {
    redirectToRelativeLink(String.format(showTaskNoteHistoryUrl, taskId));
  }

  public void goToCaseNoteHistoryPage(String caseId) {
    redirectToRelativeLink(String.format(showCaseNoteHistoryUrl, caseId));
  }

  public void createJSonFile(String jsonFile, String key) {
    var path = FileHelper.getAbsolutePathToTestFile(jsonFile);
    String filepath = "";
    try {
      filepath = URLEncoder.encode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    redirectToRelativeLink(String.format(createJSonFileUrl, filepath, key));
  }
  
  protected void showNewDashboard() {
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
  }

  public void redirectToRelativeLinkWithEmbedInFrame(String relativeProcessStartUrl) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl) + "?embedInFrame");
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public WebDriverWait webDriverWait() {
    return new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT);
  }

  public Responsible setResponsible(String userName, boolean isGroup) {
    Responsible user = new Responsible();
    user.setResponsibleName(userName);
    user.setIsGroup(isGroup);
    return user;
  }

  public void resizeBrowserTo2kResolution() {
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
  }

  public void assertTrue(boolean condition) {
    assertTrue(condition, "");
  }

  /**
   * Use this instead of {@code Assertions} methods so that Selenide would take screenshots if errors. This is a
   * workaround because we cannot use @ExtendWith({ScreenShooterExtension.class}) with
   * `WebDriverRunner.getWebDriver().quit();` in `@AfterEach`
   * @param condition 
   * @param message 
   */
  public void assertTrue(boolean condition, String message) {
    if (!condition) {
      System.out.println(message);
      $("ASSERTION FAILED, CHECK STACK TRACE from BaseTest.assertTrue").shouldBe(exist, ZERO);
    }
  }

  public void assertFalse(boolean condition) {
    assertTrue(!condition);
  }

  public void assertFalse(boolean condition, String message) {
    assertTrue(!condition, message);
  }

  public void assertEquals(Object a, Object b) {
    assertTrue(Objects.equals(a, b));
  }

  public void assertEquals(Object a, Object b, String message) {
    assertTrue(Objects.equals(a, b), message);
  }

  public void assertNotEquals(Object a, Object b) {
    assertTrue(!Objects.equals(a, b));
  }

}
