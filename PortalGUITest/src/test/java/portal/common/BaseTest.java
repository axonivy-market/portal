package portal.common;

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
  private String ieDriverPath = "./src/test/resources/IEDriverServer.exe";
  private String createTestingTasksUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";

  @Rule
  public ScreenshotFailedTestRule screenshotTestRule = new ScreenshotFailedTestRule();

  @Before
  public void setup() {
    browser = Browser.getBrowser();
    destroyAllTasksCases();
    cleanAllFavoriteProcesses();
    grantAllPermissionsForAdminUserIfRunningInDesigner();
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

  private void grantAllPermissionsForAdminUserIfRunningInDesigner() {
    if (!SystemProperties.isInServerMode()) {
      String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPermissionsToAdmin.ivp";
      redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
    }
  }

  public void createTestingTasks() {
    redirectToRelativeLink(createTestingTasksUrl);
  }
}
