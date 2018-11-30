package ch.ivyteam.ivy.project.portal.guitest.mobile.implementation;

import java.net.MalformedURLException;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.PortalMobileGUITestException;
import ch.ivyteam.ivy.project.portal.guitest.mobile.common.PropertyLoader;
import ch.ivyteam.ivy.project.portal.guitest.mobile.common.SystemProperties;
import ch.ivyteam.ivy.project.portal.guitest.mobile.common.TestAccount;
import ch.ivyteam.ivy.project.portal.guitest.mobile.common.UrlHelpers;
import ch.ivyteam.ivy.project.portal.guitest.mobile.core.Device;
import ch.ivyteam.ivy.project.portal.guitest.mobile.enums.DeviceType;
import ch.ivyteam.ivy.project.portal.guitest.mobile.page.MobileLoginPage;
import ch.ivyteam.ivy.project.portal.guitest.mobile.page.MobileTaskPage;

public class MobileBaseTest {
  
  private Device device;
  public final static String PORTAL_HOME_PAGE_URL = "portalTemplate/1549F58C18A6C562/PortalStart.ivp";
  public final static String CREATE_TESTING_TASK_URL = "internalSupport/14B2FC03D2E87141/CategoriedLeaveRequest.ivp";
  public final static String DESIGNER_LOGOUT_URL = "http://localhost:8081/ivy/wf/logout.jsp";
  
  @Rule
  public TestRule watcher = new TestWatcher() {

    @Override
    protected void starting(Description description) {
      System.out.println("Starting test: " + description.getMethodName());
    }

  };
  
  public void setup() {
    device = Device.getDevice();
    destroyAllTasksCases();
    updatePermissionOfTestUsers();
    cleanUpGlobalVariables();
    if(!SystemProperties.isInServerMode()) {
      logoutInDesigner();
    }
  }
  
  private void destroyAllTasksCases() {
    String destroyAllTaskCaseURL = "portalKitTestHelper/1511A66AF619A768/cleanupCases.ivp";
    launchDeviceAndGotoRelativeLink(destroyAllTaskCaseURL);
  }
  
  public void launchDeviceAndGotoRelativeLink(String relativeProcessStartLink) {
      try {
        device.initAppiumDriver(DeviceType.ANDROID, UrlHelpers.getAppiumServerUrl(), PropertyLoader.getDeviceName());
        redirectToRelativeLink(relativeProcessStartLink);
      } catch (MalformedURLException e) {
        System.out.println("Can not init driver");
      }
  }
  
  public void redirectToRelativeLink(String relativeProcessStartUrl) {
    try {
      device.goToURL(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalMobileGUITestException(e);
    }
  }
  
  private void updatePermissionOfTestUsers() {
    redirectToRelativeLink("portalKitTestHelper/14DE09882B540AD5/updatePermissionsOfTestUsers.ivp");
  }
  
  public void cleanUpGlobalVariables(){
    String cleanUpURL = "portalKitTestHelper/1511A66AF619A768/cleanupGlobalVars.ivp";
    redirectToRelativeLink(cleanUpURL);
  }
  
  public void createTestingTasks() {
    redirectToRelativeLink(CREATE_TESTING_TASK_URL);
  }
  
  public MobileTaskPage login(TestAccount account) {
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    MobileLoginPage mobileLoginPage = new MobileLoginPage(account);
    mobileLoginPage.login();
    return new MobileTaskPage();
  }
  
  public void logoutInDesigner() {
    try {
      device.goToURL(UrlHelpers.getLogoutURLOnDesigner());
    } catch (Exception e) {
      throw new PortalMobileGUITestException(e);
    }
  }
  
  @AfterEach
  public void tearDown() {
    device.shutdown();
  }
}
