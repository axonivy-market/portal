package portal.guitest.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Before;
import org.junit.Rule;

import portal.guitest.page.HomePage;
import ch.ivy.addon.portalkit.enums.PortalPermission;
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
  public final static String LOGIN_URL_PATTERN = "portalKitTestHelper/1636734E13CEC872/login.ivp?username=%s&password=%s";
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
  protected String createTestingTasksUrl = "portalExamples/162511D2577DBA88/CategoriedLeaveRequest.ivp";
  protected String businessCaseUrl = "internalSupport/15B1EA24CCF377E8/updateCheckInTime.ivp";
  protected String hideCaseUrl = "portalExamples/16583F0F73864543/createHiddenTechnicalCase.ivp";
  protected String createTestingCaseMapUrl = "internalSupport/764871e4-cf70-401f-83fb-9e99fa897fc4.icm";
  protected String createTestingCaseUrlForCustomizationAdditionalCaseDetails = "portalExamples/1624D1F5CBEA5332/createInvestmentRequest.ivp";
  protected String createTestingCaseUrlForDefaultAdditionalCaseDetails = "internalSupport/14B2FC03D2E87141/DefaultAdditionalCaseDetails.ivp";
  protected String createTestingCaseContainOneTask = "internalSupport/14B2FC03D2E87141/CreateSupportTicket.ivp";

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
  
  public void denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser() {
    String denyDocumentOfInvolvedCaseWritePemissionURL = "portalKitTestHelper/14DE09882B540AD5/denyDocumentOfInvolvedCaseWritePemission.ivp";
    redirectToRelativeLink(denyDocumentOfInvolvedCaseWritePemissionURL);
  }
  
  public void grantDocumentOfInvolvedCaseWritePemissionToCurrentUser() {
    String grantDocumentOfInvolvedCaseWritePemissionURL = "portalKitTestHelper/14DE09882B540AD5/grantDocumentOfInvolvedCaseWritePemission.ivp";
    redirectToRelativeLink(grantDocumentOfInvolvedCaseWritePemissionURL);
  }
  
  public void grantSpecificPortalPermission(PortalPermission portalPermission) {
    String grantSpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, portalPermission.getValue()));
  }

  public void denySpecificPortalPermission(PortalPermission portalPermission) {
    String denySpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, portalPermission.getValue()));
  }

  public void cleanUpGlobalVariables(){
    String cleanUpURL = "portalKitTestHelper/1511A66AF619A768/cleanupGlobalVars.ivp";
    redirectToRelativeLink(cleanUpURL);
  }
  
  public void refreshPage(){
    browser.getDriver().navigate().refresh();
  }
  
  protected void login(TestAccount testAccount) {
    String username;
    String password;
    try {
      username = URLEncoder.encode(testAccount.getUsername(), "UTF-8");
      password = URLEncoder.encode(testAccount.getPassword(), "UTF-8");
      redirectToRelativeLink(String.format(LOGIN_URL_PATTERN, username, password));
      redirectToRelativeLink(String.format(LOGIN_URL_PATTERN, username, password));
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    } catch (UnsupportedEncodingException e) {
      throw new PortalGUITestException(e);
    }
    
  }
  
}
