package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

@IvyWebTest(headless = false)
public class PortalExpressTest extends BaseTest{
  protected static final int USER_TASK_INDEX = 0;
  protected static final int USER_TASK_WITH_EMAIL_INDEX = 1;
  protected static final int INFORMATION_EMAIL_INDEX = 2;
  protected static final int APPROVAL_INDEX = 3;

  protected static final int INPUT_TEXT_TYPE_INDEX = 0;
  protected static final int INPUT_NUMBER_TYPE_INDEX = 1;
  protected static final int INPUT_DATE_TYPE_INDEX = 2;

  protected NewDashboardPage newDashboardPage;
  protected ProcessWidgetPage processWidget;
  protected TaskWidgetPage taskWidgetPage;

  ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
  ExpressResponsible groupHr = setExpressResponsible("Human resources department", true);

  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(grantPortalPermission);
    newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testOpenProcessWidgetWithoutCreateExpressWorkflowPermission() throws Exception {
    String denyAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyAllPermissionsForAdminUserURL);
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    processWidget = mainMenuPage.selectProcessesMenu();
    assertEquals(false, processWidget.hasCreateNewExpressWorkflowLink());
    // run process to grant permission back to normal
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
}
