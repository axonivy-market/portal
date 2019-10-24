package portal.guitest.common;

import org.junit.Before;
import org.junit.Rule;

import ch.xpertline.base.client.Browser;
import ch.xpertline.base.enums.BrowserType;

import com.thoughtworks.selenium.SeleneseTestBase;

/**
 * A base test that other tests extend it. It will test on browser IE by default. It provides feature to take screenshot
 * of failed tests and utility methods.
 *
 */
public class BaseTest extends SeleneseTestBase {
  private Browser browser;

  private String designerLogoutUrl = "http://localhost:8081/ivy/wf/logout.jsp";
  private BrowserType browserType = BrowserType.IE;
  private String ieDriverPath = getInternetExprorerDriverPath();

  public Browser getBrowser() {
    return browser;
  }
  
  public void setBrowser(Browser browser) {
    this.browser = browser;
  }
  
  public String getIeDriverPath() {
    return ieDriverPath;
  }

  public void setIeDriverPath(String ieDriverPath) {
    this.ieDriverPath = ieDriverPath;
  }

  private String getInternetExprorerDriverPath() {
    String vmArgPath = System.getProperty("ieDriverPath");
    if (vmArgPath != null) {
      return vmArgPath;
    }
    return "./resources/IEDriverServer.exe";
  }
  
  protected String createTestingTasksUrl = "internalSupport/14B2FC03D2E87141/CategoriedLeaveRequest.ivp";
  protected String businessCaseUrl = "internalSupport/15B1EA24CCF377E8/updateCheckInTime.ivp";
  protected String hideCaseUrl = "internalSupport/15C7B30FB93C827E/repairComputer.ivp";
  protected String createTestingCaseMapUrl = "internalSupport/764871e4-cf70-401f-83fb-9e99fa897fc4.icm";
  protected String createTestingCaseUrlForCustomizationAdditionalCaseDetails = "portalExamples/1624D1F5CBEA5332/createInvestmentRequest.ivp";
  protected String createTestingCaseUrlForDefaultAdditionalCaseDetails = "internalSupport/14B2FC03D2E87141/DefaultAdditionalCaseDetails.ivp";
  protected String createUnassignedTaskUrl = "internalSupport/14B2FC03D2E87141/createUnassignedTask.ivp";

  @Rule
  public ScreenshotFailedTestRule screenshotTestRule = new ScreenshotFailedTestRule();

  @Before
  public void setup() {
    browser = Browser.getBrowser();
    destroyAllTasksCases();
    cleanAllFavoriteProcesses();
    updatePermissionOfTestUsers();
    cleanUpGlobalVariables();
  }

  public void launchBrowserAndGotoRelativeLink(String relativeProcessStartLink) {
    try {
      browser.launch(browserType, UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartLink), ieDriverPath);
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public void redirectToRelativeLink(String relativeProcessStartUrl) {
    try {
      browser.goHome(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public void launchBrowserAndLogoutInDesigner() {
    try {
      browser.launch(browserType, designerLogoutUrl, ieDriverPath);
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  protected void navigateToUrl(String relativeProcessStartUrl) {
    if (SystemProperties.isInServerMode()) {
      redirectToRelativeLink(relativeProcessStartUrl);
    } else {
      logoutDesigner();
      redirectToRelativeLink(relativeProcessStartUrl);
    }
  }

  private void logoutDesigner() {
    try {
      browser.goHome(designerLogoutUrl);
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  private void destroyAllTasksCases() {
    String destroyAllTaskCaseURL = "portalKitTestHelper/1511A66AF619A768/cleanupCases.ivp";
    launchBrowserAndGotoRelativeLink(destroyAllTaskCaseURL);
  }

  private void cleanAllFavoriteProcesses() {
    String cleanAllFavoriteProcessesURL = "portalKitTestHelper/1511A66AF619A768/CleanFavoriteProcess.ivp";
    navigateToUrl(cleanAllFavoriteProcessesURL);
  }

  private void updatePermissionOfTestUsers() {
    redirectToRelativeLink("portalKitTestHelper/14DE09882B540AD5/updatePermissionsOfTestUsers.ivp");
  }

  public void createTestingTasks() {
    redirectToRelativeLink(createTestingTasksUrl);
  }
  
  public void grantAllPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantAllPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
  
  public void grantTaskReadAllPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantTaskReadAllPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
  
  public void grantCaseReadAllPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantCaseReadAllPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
  
  public void grantTaskReadOwnCaseTaskPermissionsToCurrentUser() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantTaskReadOwnCaseTaskPermissionsToCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  public void denyReadAllPermissionFromCurrentUser() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/denyReadAllPermissionFromCurrentUser.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
  
  public void cleanUpGlobalVariables(){
    String cleanUpURL = "portalKitTestHelper/1511A66AF619A768/cleanupGlobalVars.ivp";
    redirectToRelativeLink(cleanUpURL);
  }
  
  public void refreshPage(){
    browser.getDriver().navigate().refresh();
  }

}
