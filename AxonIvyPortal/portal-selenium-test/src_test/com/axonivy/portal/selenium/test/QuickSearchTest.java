package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class QuickSearchTest extends BaseTest {

  // WIDGET
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityOfQuickSearchOnConfiguration() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    taskWidget.isQuickSearchInputShow("0");

    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    assertFalse(taskWidget.isQuickSearchInputShow("0"));
  }

  @Test
  public void testQuickSearchIsSelectedByDefaultOnNameAndDescriptionField() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    assertTrue(taskEditWidget.isQuickSearchClicked("name"));
    assertTrue(taskEditWidget.isQuickSearchClicked("description"));

  }

  @Test
  public void testQuickSearchOnColumnManagement() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    assertTrue(taskEditWidget.isQuickSearchClicked("activator"));
    assertTrue(taskEditWidget.isQuickSearchClicked("category"));
    assertTrue(taskEditWidget.isQuickSearchClicked("application"));
  }

  @Test
  public void testQuickSearchForStandardFieldsOnDashboardConfigurationPage() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("id");
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("engine");
    assertTrue(taskWidget.isEmptyMessageAppear());

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("TestCase1");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("everybody");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("demo user");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(2));
  }

  @Test
  public void testQuickSearchForStandardFieldsOnDashboardPage() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("id");
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.waitPageLoaded();

    taskWidget.setInputForQuickSearch("engine");
    assertTrue(taskWidget.isEmptyMessageAppear());
    redirectToNewDashBoard();
    assertEquals("engine", taskWidget.getQuickSearchInput());

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("TestCase1");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("everybody");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));
    taskWidget.clickOnButtonExpandTaskWidget();
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));
    taskWidget.clickOnButtonCollapseTaskWidget();
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(3));
  }

  @Test
  public void testQuickSearchKeywordIsKeptDuringUserSession() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.waitPageLoaded();

    taskWidget.setInputForQuickSearch("engine");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskEditWidget.returnToDashboardPage();
    assertEquals("engine", taskWidget.getQuickSearchInput());
  }

  @Test
  public void testQuickSearchForCustomFieldsOnDashboardConfigurationPage() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();

    List<String> customFields = List.of("CustomerName", "CustomerType", "CustomerAddress", "CustomerEmail");

    customFields.stream().forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom field", item));
    customFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.setInputForQuickSearch("tung le");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("interior");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("test@email.com");
    assertTrue(taskWidget.isEmptyMessageAppear());

    taskWidget.setInputForQuickSearch("ho chi minh city");
    assertTrue(taskWidget.isEmptyMessageAppear());
  }

  @Test
  public void testQuickSearchForCustomCaseFieldsOnDashboardConfigurationPage() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();

    List<String> customCaseFields = List.of("CustomerName", "CustomerType", "SupportData");

    customCaseFields.stream()
        .forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom case field", item));
    customCaseFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.setInputForQuickSearch("leave request");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("supportdata");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(0));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("support data");
    assertTrue(taskWidget.isEmptyMessageAppear());
  }

  @Test
  public void testQuickSearchForCustomFieldsOnDashboardPage() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();

    List<String> customFields = List.of("CustomerName", "CustomerType", "CustomerAddress", "CustomerEmail");

    customFields.stream().forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom field", item));
    customFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.setInputForQuickSearch("tung le");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    redirectToNewDashBoard();
    assertEquals("tung le", taskWidget.getQuickSearchInput());
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("interior");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("test@email.com");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("ho chi minh city");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clickOnButtonExpandTaskWidget();
    assertTrue(taskWidget.isEmptyMessageAppear());
  }


}
