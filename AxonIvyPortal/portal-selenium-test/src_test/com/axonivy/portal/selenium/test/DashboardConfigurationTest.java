package com.axonivy.portal.selenium.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
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
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testEditOnlyPrivateDashboards() {
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);
    redirectToRelativeLink(denyDashboardWritePublicPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.getPrivateDashboardConfigurationTypeSelection().shouldBe(Condition.appear);
    configurationPage.getPublicDashboardConfigurationTypeSelection().shouldBe(Condition.disappear);
  }

  @Test
  public void testEditOnlyPublicDashboards() {
    redirectToRelativeLink(denyDashboardWriteOwnPermissionUrl);
    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.getPublicDashboardConfigurationTypeSelection().shouldBe(Condition.appear);
    configurationPage.getPrivateDashboardConfigurationTypeSelection().shouldBe(Condition.disappear);
  }

  @Test
  public void testHideConfigureDashboardButton() {
    redirectToRelativeLink(denyDashboardWriteOwnPermissionUrl);
    redirectToRelativeLink(denyDashboardWritePublicPermissionUrl);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getConfigureDashboardMenu().shouldBe(Condition.disappear);
  }

  @Test
  public void testEditPublicDashboardInfo() {
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.clickEditDashboardByName("Dashboard");

    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");
    modificationPage.editDashboardInfo("Editted Dashboard", "dashboard description", permissions);

    SelenideElement dashboard = modificationPage.getDashboardRowByName("Editted Dashboard");
    dashboard.shouldBe(Condition.appear);
    dashboard.$("td:nth-child(1)").shouldHave(Condition.exactText("Editted Dashboard"));
    dashboard.$("td:nth-child(3)").shouldHave(Condition.exactText("dashboard description"));
  }

  @Test
  public void testDeletePublicDashboard() {
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.clickDeleteDashboardByName("Dashboard");
    modificationPage.getDashboardRows().shouldHaveSize(0);
  }

  @Test
  public void testAddPublishDashboardFromScratch() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = Arrays.asList("Cost Object (CostObject)");
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, description, permissions);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardFromScratch() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.createPrivateDashboardFromScratch(name, description);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testAddPublishDashboardUseTemplate() {
    String name = "New public dashboard";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromTemplate(name, description, permissions, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardUseTemplate() {
    String name = "New private dashboard";
    String description = "New private dashboard description";

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.createPrivateDashboardFromTemplate(name, description, 0);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testAddPublishDashboardTwoTaskListDashboard() {
    String name = "New public dashboard two task list";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromTemplate(name, description, permissions, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardTwoTaskListDashboard() {
    String name = "New private dashboard two task list";
    String description = "New private dashboard description";

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.createPrivateDashboardFromTemplate(name, description, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testEditPrivateDashboardInfo() {
    redirectToRelativeLink(createSampleDashboardUrl);
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(false);
    modificationPage.clickEditDashboardByName(PRIVATE_1);
    modificationPage.editPrivateDashboardInfo(EDITED_PRIVATE_DASHBOARD_1, DASHBOARD_1_DESCRIPTION);
    SelenideElement dashboard = modificationPage.getDashboardRowByName(EDITED_PRIVATE_DASHBOARD_1);
    dashboard.shouldBe(Condition.appear);
    dashboard.$("td:nth-child(1)").shouldHave(Condition.exactText(EDITED_PRIVATE_DASHBOARD_1));
    dashboard.$("td:nth-child(2)").shouldHave(Condition.exactText(DASHBOARD_1_DESCRIPTION));
  }

  @Test
  public void testDeletePrivateDashboard() {
    redirectToRelativeLink(createSampleDashboardUrl);
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(false);
    modificationPage.clickDeleteDashboardByName(PRIVATE_1);
    modificationPage.getDashboardRows().shouldHaveSize(1);
  }

  @Test
  public void testDeleteTaskWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.deleteTaskWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectCaseWidget("").expand().shouldHaveSize(2);
  }

  @Test
  public void testDeleteCaseWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.deleteCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }

  @Test
  public void testDeleteProcessesWidgetForPublicDashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESS_WIDGET);
    processWidget.deleteProcessWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }

  @Test
  public void testDeleteTaskWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(false);
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.deleteTaskWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectCaseWidget("").expand().shouldHaveSize(2);
  }

  @Test
  public void testDeleteCaseWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(false);
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);

    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.deleteCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }

  @Test
  public void testDeleteProcessesWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(false);
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);

    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESS_WIDGET);
    processWidget.deleteProcessWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget("").expand().shouldHaveSize(2);
  }

  private DashboardModificationPage navigateToConfigurationAndEditDashboards(boolean isPublicDashboard) {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    if (isPublicDashboard) {
      return configurationPage.openEditPublicDashboardsPage();
    }
    return configurationPage.openEditPrivateDashboardsPage();
  }

  private void goBackConfigurationAndVerifyDashboards(String name, String description,
      NewDashboardDetailsEditPage newDashboardDetailsEditPage, boolean isPublicDashboard) {
    var configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    DashboardModificationPage modificationPage = null;
    if (isPublicDashboard) {
      modificationPage = configurationPage.openEditPublicDashboardsPage();
    } else {
      modificationPage = configurationPage.openEditPrivateDashboardsPage();
    }
    SelenideElement dashboard = modificationPage.getDashboardRowByName(name);
    dashboard.shouldBe(Condition.appear);
    modificationPage.getDashboardCellByNameAndPosition(name, 1).shouldHave(Condition.exactText(name));
    if (isPublicDashboard) {
      modificationPage.getDashboardCellByNameAndPosition(name, 3).shouldHave(Condition.exactText(description));
    } else {
      modificationPage.getDashboardCellByNameAndPosition(name, 2).shouldHave(Condition.exactText(description));
    }
  }
}
