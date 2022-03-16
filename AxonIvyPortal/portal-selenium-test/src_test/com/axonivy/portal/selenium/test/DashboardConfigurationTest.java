package com.axonivy.portal.selenium.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class DashboardConfigurationTest extends BaseTest {

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
    newDashboardPage.getConfigureDashboardMenu().shouldBe(Condition.disappear);
  }

  @Test
  public void testEditPublicDashboardInfo() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.clickEditDashboardByName("Dashboard");

    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");
    configPage.editDashboardInfo("Editted Dashboard", "dashboard description", permissions);

    SelenideElement dashboard = configPage.getDashboardRowByName("Editted Dashboard");
    dashboard.shouldBe(Condition.appear);
    dashboard.$("td:nth-child(1)").shouldHave(Condition.exactText("Editted Dashboard"));
    dashboard.$("td:nth-child(2)").shouldHave(Condition.exactText("dashboard description"));
  }

  @Test
  public void testDeletePublicDashboard() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.clickDeleteDashboardByName("Dashboard");
    configPage.getDashboardRows().shouldHaveSize(0);
  }
}
