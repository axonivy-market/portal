package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest(headless = false)
public class NewDashboardConfigurationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
  }

  @Test
  public void testEditOnlyPrivateDashboards() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWriteOwnPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWritePublicPermission.ivp");
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.getPrivateDashboardConfigurationSection().shouldBe(Condition.appear);
    newDashboardPage.getPublicDashboardConfigurationSection().shouldBe(Condition.disappear);
  }

  @Test
  public void testEditOnlyPublicDashboards() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWriteOwnPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWritePublicPermission.ivp");
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.getPublicDashboardConfigurationSection().shouldBe(Condition.appear);
    newDashboardPage.getPrivateDashboardConfigurationSection().shouldBe(Condition.disappear);
  }

  @Test
  public void testHideConfigureDashboardButton() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWriteOwnPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWritePublicPermission.ivp");
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getConfigureDashboardButton().shouldBe(Condition.disappear);
  }
}
