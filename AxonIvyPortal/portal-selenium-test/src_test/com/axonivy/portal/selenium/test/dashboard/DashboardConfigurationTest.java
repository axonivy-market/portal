package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;

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

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

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
    newDashboardPage.getDashboardConfigurationMenu().shouldBe(Condition.disappear);
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
    modificationPage.getDashboardRows().shouldHave(size(2));
  }

  @Test
  public void testAddPublicDashboardFromScratch() {
    String name = "New public dashboard";
    String icon = "fa-coffee";
    String description = "New public dashboard description";
    List<String> permissions = Arrays.asList("Cost Object (CostObject)");
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, icon, description, permissions, DashboardDisplayType.SUB_MENU);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardFromScratch() {
    String name = "New private dashboard";
    String icon = "fa-coffee";
    String description = "New private dashboard description";

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.createPrivateDashboardFromScratch(name, icon, description);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testAddPublicDashboardUseTemplate() {
    createPublicDashboardUseTemplate();
    String name = "New public dashboard";
    String icon = "fa-coffee";
    String description = "New public dashboard description";

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(10));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardUseTemplate() {
    createPrivateDashboardUseTemplate();
    String name = "New private dashboard";
    String icon = "fa-coffee";
    String description = "New private dashboard description";

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(10));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testAddPublicDashboardTwoTaskListDashboard() {
    String name = "New public dashboard two task list";
    String icon = "fa-coffee";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromTemplate(name, icon, description, permissions, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, true);
  }

  @Test
  public void testAddPrivateDashboardTwoTaskListDashboard() {
    String name = "New private dashboard two task list";
    String icon = "fa-coffee";
    String description = "New private dashboard description";

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.createPrivateDashboardFromTemplate(name, icon, description, 1);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.size(3));

    goBackConfigurationAndVerifyDashboards(name, description, newDashboardDetailsEditPage, false);
  }

  @Test
  public void testEditPrivateDashboardInfo() {
    redirectToRelativeLink(createSampleDashboardUrl);
    NewDashboardPage newDashboard = new NewDashboardPage();
    newDashboard.waitForPageLoad();

    var modificationPage = navigateToConfigurationAndOpenDashboardModificationPage();
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
    NewDashboardPage newDashboard = new NewDashboardPage();
    newDashboard.waitForPageLoad();

    var modificationPage = navigateToConfigurationAndOpenDashboardModificationPage();
    modificationPage.clickDeleteDashboardByName(PRIVATE_1);
    modificationPage.getDashboardRows().shouldHave(size(1));
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
    newDashboardPage.selectCaseWidget(" ").expand().shouldHave(size(8));
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
    newDashboardPage.selectTaskWidget(" ").expand().shouldHave(size(8));
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
    newDashboardPage.selectTaskWidget(" ").expand().shouldHave(size(8));
  }

  @Test
  public void testDeleteTaskWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    var modificationPage = navigateToConfigurationAndOpenDashboardModificationPage();
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.deleteTaskWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectCaseWidget(" ").expand().shouldHave(size(8));
  }

  @Test
  public void testDeleteCaseWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    var modificationPage = navigateToConfigurationAndOpenDashboardModificationPage();
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);

    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.deleteCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget(" ").expand().shouldHave(size(8));
  }

  @Test
  public void testDeleteProcessesWidgetForPrivateDashboard() {
    testAddPrivateDashboardUseTemplate();
    var modificationPage = navigateToConfigurationAndOpenDashboardModificationPage();
    modificationPage.navigateToEditDashboardDetailsByName(NEW_PRIVATE_DASHBOARD);

    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESS_WIDGET);
    processWidget.deleteProcessWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRemoveWidgetButton();
    newDashboardPage.selectTaskWidget(" ").expand().shouldHave(size(8));
  }

  @Test
  public void testExportOnlyPrivateDashboards() {
    createPrivateDashboardUseTemplate();
    createPublicDashboardUseTemplate();
    redirectToRelativeLink(grantDashboardExportOwnPermissionUrl);
    redirectToRelativeLink(denyDashboardExportPublicPermissionUrl);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    configurationPage.openEditPublicDashboardsPage().getDashboardExportButtonOfDashboard("New public dashboard")
        .shouldBe(Condition.disappear);
    configurationPage.openEditPrivateDashboardsPage().getDashboardExportButtonOfDashboard("New private dashboard")
        .shouldBe(Condition.appear);
  }

  @Test
  public void testCanExportOnlyPublicDashboards() {
    createPrivateDashboardUseTemplate();
    createPublicDashboardUseTemplate();
    redirectToRelativeLink(denyDashboardExportOwnPermissionUrl);
    redirectToRelativeLink(grantDashboardExportPublicPermissionUrl);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    configurationPage.openEditPublicDashboardsPage().getDashboardExportButtonOfDashboard("New public dashboard")
        .shouldBe(Condition.appear);
    configurationPage.openEditPrivateDashboardsPage().getDashboardExportButtonOfDashboard("New private dashboard")
        .shouldBe(Condition.disappear);
  }

  private DashboardModificationPage navigateToConfigurationAndOpenDashboardModificationPage() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    return new DashboardModificationPage();
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

  private void createPrivateDashboardUseTemplate() {
    String name = "New private dashboard";
    String icon = "fa-coffee";
    String description = "New private dashboard description";

    DashboardConfigurationPage configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.createPrivateDashboardFromTemplate(name, icon, description, 0);
  }

  private void createPublicDashboardUseTemplate() {
    String name = "New public dashboard";
    String icon = "fa-coffee";
    String description = "New public dashboard description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Cost Object (CostObject)");

    DashboardConfigurationPage configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromTemplate(name, icon, description, permissions, 0);
  }

  @Test
  public void testVisibleSharingDashboardButton() {
    redirectToRelativeLink(denyDashboardShareLinkPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.getDashboardShareLinkButton().shouldBe(Condition.disappear);

    redirectToRelativeLink(grantDashboardShareLinkPermissionUrl);
    refreshPage();
    DashboardModificationPage modificationPage2 = navigateToConfigurationAndEditDashboards(true);
    modificationPage2.getDashboardShareLinkButton().shouldBe(Condition.appear);
  }

  @Test
  public void testSharingDashboard() {
    redirectToRelativeLink(grantDashboardShareLinkPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = navigateToConfigurationAndEditDashboards(true);
    modificationPage.getDashboardShareLinkDialog();
  }

  @Test
  public void testImportOnlyPublicDashboards() {
    redirectToRelativeLink(grantDashboardImportPublicPermissionUrl);
    redirectToRelativeLink(denyDashboardImportOwnPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.getDashboardImportButtonOfDashboard().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    configurationPage.closeAddDashboardDialog();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.getDashboardImportButtonOfDashboard().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testImportOnlyPrivateDashboards() {
    redirectToRelativeLink(denyDashboardImportPublicPermissionUrl);
    redirectToRelativeLink(grantDashboardImportOwnPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.getDashboardImportButtonOfDashboard().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    configurationPage.closeAddDashboardDialog();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.getDashboardImportButtonOfDashboard().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testImportPrivateDashboard() {
    redirectToRelativeLink(grantDashboardImportOwnPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePrivateDashboardMenu();
    configurationPage.getImportDashboardDialog();
    configurationPage.getDashboardImportSaveButton().shouldBe(Condition.disabled, DEFAULT_TIMEOUT);

    configurationPage.uploadFile("Dashboard_Dashboard_Export.json");
    configurationPage.getDashboardImportSaveButton().shouldBe(Condition.enabled, DEFAULT_TIMEOUT);
    configurationPage.getDashboardImportPermission().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    String name = "New import private dashboard";
    String newGermanName = "German dashboard";
    String icon = "fa-coffee";
    String description = "New import private dashboard description";
    configurationPage.saveImportDashboard(name, newGermanName, description, icon);
  }

  @Test
  public void testImportPublicDashboard() {
    redirectToRelativeLink(grantDashboardImportPublicPermissionUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.getImportDashboardDialog()
        .find("button[id$=':dashboard-import-component:dashboard-detail-save-button']")
        .shouldBe(Condition.disabled, DEFAULT_TIMEOUT);
    configurationPage.getDashboardImportSaveButton().shouldBe(Condition.disabled, DEFAULT_TIMEOUT);

    configurationPage.uploadFile("Dashboard_Dashboard_Export.json");
    configurationPage.getDashboardImportSaveButton().shouldBe(Condition.enabled, DEFAULT_TIMEOUT);
    configurationPage.getDashboardImportPermission().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    String name = "New import public dashboard";
    String newGermanName = "German public dashboard";
    String icon = "fa-coffee";
    String description = "New import public dashboard description";

    configurationPage.saveImportDashboard(name, newGermanName, description, icon);
  }
  
  @Test
  public void testAddNewAcccessibilityDashboard() {
    String name = "Accessibility shortcuts dashboard";
    String icon = "fa-coffee";
    String description = "Accessibility shortcuts dashboard description";
    List<String> permissions = Arrays.asList("Cost Object (CostObject)");
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromTemplate(name, icon, description, permissions, 2);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getIconByIndex(0, icon).shouldBe(Condition.appear);
    assertEquals( newDashboardDetailsEditPage.getWidgets().size(), 4);

    SelenideElement element = newDashboardDetailsEditPage.getAccessibilityWidget();
    assertEquals(element.getAttribute("title"), "Accessibility Shortcuts frame");
  }
  
}
