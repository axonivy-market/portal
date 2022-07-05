package com.axonivy.portal.selenium.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class DashboardConfigurationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private static final String PRIVATE_1 = "private 1";
  private static final String EDITED_PRIVATE_DASHBOARD_1 = "Editted Private Dashboard 1";
  private static final String DASHBOARD_1_DESCRIPTION = "dashboard 1 description";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_PROCESS_WIDGET = "Your Processes";
  private static final String NEW_PRIVATE_DASHBOARD = "New private dashboard";

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
    dashboard.$("td:nth-child(3)").shouldHave(Condition.exactText("dashboard description"));
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
    checkPrivateDashboardExistOnList(configPage, name, description);
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
    checkPrivateDashboardExistOnList(configPage, name, description);
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
    checkPrivateDashboardExistOnList(configPage, name, description);
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
    checkPrivateDashboardExistOnList(configPage, name, description);
  }
  
  @Test
  public void testAddPublishDashboardTwoTaskListDashboard() {
    String name = "New public dashboard two task list";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromTemplate(name, description, permissions, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testAddPrivateDashboardTwoTaskListDashboard() {
    String name = "New private dashboard two task list";
    String description = "New private dashboard description";

    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePrivateDashboardMenu();
    newDashboardPage.createPrivateDashboardFromTemplate(name, description, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    NewDashboardConfigurationPage configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkPrivateDashboardExistOnList(configPage, name, description);
  }
  
  @Test
  public void testOpenConfigurationPageThenAddPublishDashboardTwoTaskListDashboard() {
    String name = "New public dashboard two task list";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    LinkNavigator.redirectToEditPublicDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.createPublicDashboardFromTemplate(name, description, permissions, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkDashboardExistOnList(configPage, name, description);
  }

  @Test
  public void testOpenConfigurationPageThenAddPrivateDashboardTwoTaskListDashboard() {
    String name = "New private dashboard two task list";
    String description = "New private dashboard description";

    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.createPrivateDashboardFromTemplate(name, description, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    configPage = newDashboardDetailsEditPage.backToConfigurationPage();
    checkPrivateDashboardExistOnList(configPage, name, description);
  }
  
  @Test
  public void testEditPrivateDashboardInfo() {
    redirectToRelativeLink(createSampleDashboardUrl);
    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.clickEditDashboardByName(PRIVATE_1);
    configPage.editPrivateDashboardInfo(EDITED_PRIVATE_DASHBOARD_1, DASHBOARD_1_DESCRIPTION);
    SelenideElement dashboard = configPage.getDashboardRowByName(EDITED_PRIVATE_DASHBOARD_1);
    dashboard.shouldBe(Condition.appear);
    dashboard.$("td:nth-child(1)").shouldHave(Condition.exactText(EDITED_PRIVATE_DASHBOARD_1));
    dashboard.$("td:nth-child(2)").shouldHave(Condition.exactText(DASHBOARD_1_DESCRIPTION));
  }

  @Test
  public void testDeletePrivateDashboard() {
    redirectToRelativeLink(createSampleDashboardUrl);
    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.clickDeleteDashboardByName(PRIVATE_1);
    configPage.getDashboardRows().shouldHaveSize(1);
  }
  
  @Test
  public void testDeleteTaskWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    LinkNavigator.redirectToEditPublicDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.deleteTaskWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  @Test
  public void testDeleteCaseWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    LinkNavigator.redirectToEditPublicDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName("Dashboard");
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.deleteCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  @Test
  public void testDeleteProcessesWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    LinkNavigator.redirectToEditPublicDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName("Dashboard");
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESS_WIDGET);
    processWidget.deleteProcessWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  @Test
  public void testDeleteTaskWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.deleteTaskWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  @Test
  public void testDeleteCaseWidgetForPrivateDashboard() {   
    testAddPrivateDashboardUseTemplate();
    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);
    
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.deleteCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  @Test
  public void testDeleteProcessesWidgetForPrivateDashboard() {   
    testAddPrivateDashboardUseTemplate();
    LinkNavigator.redirectToEditPrivateDashboard();
    NewDashboardConfigurationPage configPage = new NewDashboardConfigurationPage();
    configPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);
    
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESS_WIDGET);
    processWidget.deleteProcessWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }
  
  private void checkDashboardExistOnList(NewDashboardConfigurationPage configPage, String name, String description) {
    SelenideElement dashboard = configPage.getDashboardRowByName(name);
    dashboard.shouldBe(Condition.appear);
    configPage.getDashboardCellByNameAndPosition(name, 1).shouldHave(Condition.exactText(name));
    configPage.getDashboardCellByNameAndPosition(name, 3).shouldHave(Condition.exactText(description));
  }

  private void checkPrivateDashboardExistOnList(NewDashboardConfigurationPage configPage, String name, String description) {
    SelenideElement dashboard = configPage.getDashboardRowByName(name);
    dashboard.shouldBe(Condition.appear);
    configPage.getDashboardCellByNameAndPosition(name, 1).shouldHave(Condition.exactText(name));
    configPage.getDashboardCellByNameAndPosition(name, 2).shouldHave(Condition.exactText(description));
  }
}
