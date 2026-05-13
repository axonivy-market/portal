package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardColumnManagementTest extends BaseTest {

  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String STATE_FIELD = "state";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testHideUsedField() {
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.openColumnManagementDialog();

    caseEditWidget.removeAddedField("id");
    caseEditWidget.getStandardField("id").shouldBe(Condition.exist);

    caseEditWidget.selectCustomType();
    String addedCustomField1 = caseEditWidget.addFirstCustomField();
    caseEditWidget.getCustomField(addedCustomField1).shouldNotBe(Condition.exist);
    String addedCustomField2 = caseEditWidget.addFirstCustomField();
    caseEditWidget.getCustomField(addedCustomField2).shouldNotBe(Condition.exist);
    caseEditWidget.removeAddedField(addedCustomField1);
    caseEditWidget.getCustomField(addedCustomField1).shouldBe(Condition.exist);

    caseEditWidget.selectStandardType();
    caseEditWidget.getStandardField("id").shouldBe(Condition.exist);

    caseEditWidget.removeAddedField(addedCustomField2);
    caseEditWidget.selectCustomType();
    caseEditWidget.getCustomField(addedCustomField2).shouldBe(Condition.exist);

    caseEditWidget.selectStandardType();
    caseEditWidget.addFirstStandardField();
    caseEditWidget.getStandardField("application").shouldNotBe(Condition.exist);

    caseEditWidget.selectCustomType();
    caseEditWidget.getCustomField(addedCustomField2).shouldBe(Condition.exist);
  }

  @Test
  public void testColumnManagementFilterToggleForCaseStandardAndCustomFields() {
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.openFilter();
    assertThat(caseEditWidget.countFilterSelect().size()).isGreaterThanOrEqualTo(1);
    caseEditWidget.closeFilter();

    caseEditWidget.openColumnManagementDialog();

    String caseStateDisplay = caseEditWidget.getDisplayNameByField(STATE_FIELD);
    String addedCustomField = caseEditWidget.addFirstCustomField();
    String customFieldDisplay = caseEditWidget.getDisplayNameByField(addedCustomField);

    assertThat(caseEditWidget.isFilterClicked(STATE_FIELD)).isTrue();
    assertThat(caseEditWidget.isFilterClicked(addedCustomField)).isTrue();

    caseEditWidget.clickOnFilterCheckBoxByField(STATE_FIELD);
    caseEditWidget.clickOnFilterCheckBoxByField(addedCustomField);
    assertThat(caseEditWidget.isFilterClicked(STATE_FIELD)).isFalse();
    assertThat(caseEditWidget.isFilterClicked(addedCustomField)).isFalse();
    caseEditWidget.saveColumn();

    caseEditWidget.openFilter();
    assertThat(caseEditWidget.countFilterSelect().size()).isZero();
    assertThat(caseEditWidget.isFilterFieldOptionAvailable(caseStateDisplay)).isFalse();
    assertThat(caseEditWidget.isFilterFieldOptionAvailable(customFieldDisplay)).isFalse();
    caseEditWidget.closeFilter();
  }

  @Test
  public void testColumnManagementFilterToggleForTaskStandardAndCustomFields() {
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openFilter();
    assertThat(taskEditWidget.countFilterSelect().size()).isGreaterThanOrEqualTo(1);
    taskEditWidget.closeFilter();

    taskEditWidget.openColumnManagementDialog();

    String taskStateDisplay = taskEditWidget.getDisplayNameByField(STATE_FIELD);
    String addedCustomField = taskEditWidget.addFirstCustomField();
    String customFieldDisplay = taskEditWidget.getDisplayNameByField(addedCustomField);

    assertThat(taskEditWidget.isFilterClicked(STATE_FIELD)).isTrue();
    assertThat(taskEditWidget.isFilterClicked(addedCustomField)).isTrue();

    taskEditWidget.clickOnFilterCheckBoxByField(STATE_FIELD);
    taskEditWidget.clickOnFilterCheckBoxByField(addedCustomField);
    assertThat(taskEditWidget.isFilterClicked(STATE_FIELD)).isFalse();
    assertThat(taskEditWidget.isFilterClicked(addedCustomField)).isFalse();
    taskEditWidget.saveColumn();

    taskEditWidget.openFilter();
    assertThat(taskEditWidget.countFilterSelect().size()).isZero();
    assertThat(taskEditWidget.isFilterFieldOptionAvailable(taskStateDisplay)).isFalse();
    assertThat(taskEditWidget.isFilterFieldOptionAvailable(customFieldDisplay)).isFalse();
    taskEditWidget.closeFilter();
  }

  @Test
  public void testColumnManagementForTaskWidget() {
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    taskEditWidget.removeAddedField("id");
    taskEditWidget.getStandardField("id").shouldBe(Condition.exist);

    taskEditWidget.selectCustomCaseType();
    String addedCustomCaseField1 = taskEditWidget.addFirstCustomField();
    taskEditWidget.getCustomField(addedCustomCaseField1).shouldNotBe(Condition.exist);
    String addedCustomCaseField2 = taskEditWidget.addFirstCustomField();
    taskEditWidget.getCustomField(addedCustomCaseField2).shouldNotBe(Condition.exist);
    taskEditWidget.removeAddedField(addedCustomCaseField1);
    taskEditWidget.getCustomField(addedCustomCaseField1).shouldBe(Condition.exist);

    taskEditWidget.selectCustomType();
    String addedCustomField1 = taskEditWidget.addFirstCustomField();
    taskEditWidget.getCustomField(addedCustomField1).shouldNotBe(Condition.exist);
    String addedCustomField2 = taskEditWidget.addFirstCustomField();
    taskEditWidget.getCustomField(addedCustomField2).shouldNotBe(Condition.exist);
    taskEditWidget.removeAddedField(addedCustomField1);
    taskEditWidget.getCustomField(addedCustomField1).shouldBe(Condition.exist);

    taskEditWidget.selectStandardType();
    taskEditWidget.getStandardField("id").shouldBe(Condition.exist);

    taskEditWidget.removeAddedField(addedCustomField2);
    taskEditWidget.selectCustomType();
    taskEditWidget.getCustomField(addedCustomField2).shouldBe(Condition.exist);

    taskEditWidget.selectStandardType();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.getStandardField("application").shouldNotBe(Condition.exist);

    taskEditWidget.selectCustomType();
    taskEditWidget.getCustomField(addedCustomField2).shouldBe(Condition.exist);
  }
}
