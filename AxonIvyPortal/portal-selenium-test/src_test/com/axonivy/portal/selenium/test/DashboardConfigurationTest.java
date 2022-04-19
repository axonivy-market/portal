package com.axonivy.portal.selenium.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
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
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWritePublicPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWriteOwnPermission.ivp");
    redirectToNewDashBoard();
  }

  @Test
  public void testEditOnlyPrivateDashboards() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWriteOwnPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWritePublicPermission.ivp");
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.getPrivateDashboardConfigurationSection().shouldBe(Condition.appear);
    newDashboardPage.getPublicDashboardConfigurationSection().shouldBe(Condition.disappear);
  }

  @Test
  public void testEditOnlyPublicDashboards() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyDashboardWriteOwnPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWritePublicPermission.ivp");
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
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
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.clickDeleteDashboardByName("Dashboard");
    configPage.getDashboardRows().shouldHaveSize(0);
  }

  @Test
  public void testAddPublishDashboardFromScratch() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromScratch(name, description, permissions);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testAddPrivateDashboardFromScratch() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePrivateDashboardMenu();
    newDashboardPage.createPrivateDashboardFromScratch(name, description);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testOpenConfigurationPageThenPublishDashboardFromScratch() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.createPublicDashboardFromScratch(name, description, permissions);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testOpenConfigurationPageThenAddPrivateDashboardFromScratch() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPrivateDashboardPage();
    configPage.createPrivateDashboardFromScratch(name, description);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testAddPublishDashboardUseTemplate() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromTemplate(name, description, permissions, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testAddPrivateDashboardUseTemplate() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePrivateDashboardMenu();
    newDashboardPage.createPrivateDashboardFromTemplate(name, description, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testOpenConfigurationPageThenPublishDashboardUseTemplate() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.createPublicDashboardFromTemplate(name, description, permissions, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testOpenConfigurationPageThenAddPrivateDashboardUseTemplate() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPrivateDashboardPage();
    configPage.createPrivateDashboardFromTemplate(name, description, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  private void checkDashboardExistOnList(NewDashboardConfigurationPage configPage, String name, String description) {
    SelenideElement dashboard = configPage.getDashboardRowByName(name);
    dashboard.shouldBe(Condition.appear);
    dashboard.$("td:nth-child(1)").shouldHave(Condition.exactText(name));
    dashboard.$("td:nth-child(2)").shouldHave(Condition.exactText(description));
  }
}
