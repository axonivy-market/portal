package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessInformationPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest(headless = false)
public class NewDashboardProcessWidgetTest extends BaseTest {
  private static final String EXPRESS_PROCESS = "EXPRESS_PROCESS";
  private static final String CASE_LEAVE_REQUEST_TEST_FOR_IVYPORTAL_3369 =
      "Case: Leave Request Test For IVYPORTAL-3369";
  private static final String TASK_SICK_LEAVE_REQUEST = "Task: Sick Leave Request";
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String NONE = "none";
  private static final String POINTER_EVENTS_PROPERTY = "pointer-events";
  private static final String TITLE_ATTRIBUTE = "title";
  private static final String APPRAISAL = "Appraisal";
  private static final String SHOWCASE_DATA_TABLE_CATEGORY = "Showcase/DataTable";
  private static final String LEAVE_REQUEST_TEST_FOR_IVYPORTAL_3369 = "Leave Request Test For IVYPORTAL-3369";
  private static final String TEST_FOR_IVYPORTAL_3369 = "Test for IVYPORTAL-3369";
  private static final String START_DATA_TABLE_SHOWCASE = "Start DataTable Showcase";
  private static final String CATEGORIED_LEAVE_REQUEST = "Categoried Leave Request";
  private static final String DATA_TABLE = "DataTable";
  private static final String SHOWCASE_DATA_TABLE = "Showcase Data Table";
  private NewDashboardPage newDashboardPage;
  private static final long DEFAULT_TIMEOUT = 45000;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
  }

  @Test
  public void testPreviewButtonImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewImageModeProcess(CATEGORIED_LEAVE_REQUEST);
    editProcessWidgetConfiguration.getStartButton().shouldBe(Condition.disabled);
    editProcessWidgetConfiguration.getDisabledMoreInformationLink().shouldBe(Condition.appear);
    editProcessWidgetConfiguration.getImageModeProcessImage()
        .shouldHave(Condition.attributeMatching(NewDashboardPage.SRC_ATTRIBUTE, NewDashboardPage.IMAGE_URI_PATTERN));
  }

  @Test
  public void testChangeImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.checkStartButtonAndImageShown();

    editProcessWidgetConfiguration = newDashboardPage.editImageModeProcess();
    editProcessWidgetConfiguration.changeImageModeProcessAndSaveWidget(APPRAISAL);
    newDashboardPage.getProcessItemName().shouldHave(Condition.exactTextCaseSensitive(APPRAISAL));
  }

  @Test
  public void testDeleteImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.checkStartButtonAndImageShown();

    newDashboardPage.deleteImageModeProcess();
    newDashboardPage.getImageContainer().shouldNotBe(Condition.exist);
  }

  @Test
  public void testStartImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getStartButton().shouldBe(Condition.enabled);
    newDashboardPage.startProcess();

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testMoreInfoLinkImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getMoreInformationLink().shouldBe(Condition.appear);
    newDashboardPage.startMoreInfoLink();

    ProcessInformationPage processInformationPage = new ProcessInformationPage();
    processInformationPage.checkBackLinkAndStartButtonShown();
  }

  // ===================================================================================
  @Test
  public void testPreviewFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewFullModeProcess(CATEGORIED_LEAVE_REQUEST);
    editProcessWidgetConfiguration.getStartButton().shouldBe(Condition.disabled);
    editProcessWidgetConfiguration.getDisabledMoreInformationLink().shouldBe(Condition.appear);
  }

  @Test
  public void testChangeFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);

    editProcessWidgetConfiguration = newDashboardPage.editFullModeProcess();
    editProcessWidgetConfiguration.changeFullModeProcessAndSaveWidget(APPRAISAL);
    newDashboardPage.getFullModeProcessName().shouldHave(Condition.exactTextCaseSensitive(APPRAISAL));
  }

  @Test
  public void testDeleteFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);

    newDashboardPage.deleteFullModeProcess();
    newDashboardPage.getFullModeProcessContainer().shouldNotBe(Condition.exist);
  }

  @Test
  public void testStartFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getStartButton().shouldBe(Condition.enabled);
    newDashboardPage.startProcess();
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testMoreInfoLinkFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getMoreInformationLink().shouldBe(Condition.appear);
    newDashboardPage.startMoreInfoLink();

    ProcessInformationPage processInformationPage = new ProcessInformationPage();
    processInformationPage.checkBackLinkAndStartButtonShown();
  }

  // ===================================================================================
  @Test
  public void testPreviewCombinedModeProcess() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCombinedModeProcess(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);
    editProcessWidgetConfiguration.getStartButton().is(Condition.disabled);

    editProcessWidgetConfiguration.getSelectedTasksTab().shouldBe(Condition.appear);
    editProcessWidgetConfiguration.getSelectedCasesTab().shouldBe(Condition.disappear);
    editProcessWidgetConfiguration.getFirstTaskDisplayedDisabledStartAction()
        .shouldHave(Condition.cssValue(POINTER_EVENTS_PROPERTY, NONE));

    editProcessWidgetConfiguration.selectCasesTab();
    editProcessWidgetConfiguration.getFirstDisplayedCaseName()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, LEAVE_REQUEST_TEST_FOR_IVYPORTAL_3369));
    editProcessWidgetConfiguration.getSelectedTasksTab().shouldBe(Condition.disappear);
    editProcessWidgetConfiguration.getSelectedCasesTab().shouldBe(Condition.appear);
  }

  private void createCategoriedLeaveRequestTask() {
    createRelatedTaskWithNoResponsible();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
  }

  @Test
  public void testChangeCombinedModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.resizeCombinedModeProcess();
    newDashboardPage.checkTasksTabDisplayedDataContainer();

    editProcessWidgetConfiguration = newDashboardPage.editCombinedModeProcess();
    editProcessWidgetConfiguration.changeCombinedModeProcessAndSaveWidget(APPRAISAL);
    newDashboardPage.getCombinedModeProcessName().shouldHave(Condition.exactTextCaseSensitive(APPRAISAL));
  }

  @Test
  public void testDeleteCombinedModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.resizeCombinedModeProcess();
    newDashboardPage.checkTasksTabDisplayedDataContainer();

    newDashboardPage.deleteCombinedModeProcess();
    newDashboardPage.getCombinedModeProcessContainer().shouldNotBe(Condition.exist);
  }

  @Test
  public void testStartCombinedModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getStartButton().shouldBe(Condition.enabled);
    newDashboardPage.startProcess();

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testStartCombinedModeProcessWhenExpanded() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(SHOWCASE_DATA_TABLE);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.getStartButton().shouldBe(Condition.enabled);
    newDashboardPage.startProcess();

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testStartCombinedModeProcessTask() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.startCombinedModeProcessFirstTask();

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle().shouldHave(Condition.attribute(TITLE_ATTRIBUTE, SICK_LEAVE_REQUEST));
  }

  @Test
  public void testStartCombinedModeProcessTaskWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.startCombinedModeProcessFirstTask();

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle().shouldHave(Condition.attribute(TITLE_ATTRIBUTE, SICK_LEAVE_REQUEST));
  }

  @Test
  public void testOpenCombinedModeProcessTask() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.openCombinedModeProcessFirstTask();

    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getBreadcrumbLastDisplayedItem()
        .shouldHave(Condition.exactTextCaseSensitive(TASK_SICK_LEAVE_REQUEST));
  }

  @Test
  public void testOpenCombinedModeProcessTaskWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.openCombinedModeProcessFirstTask();

    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getBreadcrumbLastDisplayedItem()
        .shouldHave(Condition.exactTextCaseSensitive(TASK_SICK_LEAVE_REQUEST));
  }

  @Test
  public void testOpenCombinedModeProcessCase() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.openCombinedModeProcessFirstCase();

    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getBreadcrumbLastDisplayedItem()
        .shouldHave(Condition.exactTextCaseSensitive(CASE_LEAVE_REQUEST_TEST_FOR_IVYPORTAL_3369));
  }

  @Test
  public void testOpenCombinedModeProcessCaseWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.openCombinedModeProcessFirstCase();

    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getBreadcrumbLastDisplayedItem()
        .shouldHave(Condition.exactTextCaseSensitive(CASE_LEAVE_REQUEST_TEST_FOR_IVYPORTAL_3369));
  }

  @Test
  public void testExpandCollapseCombinedModeProcess() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST,
        TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.collapseCombinedModeProcess();
    newDashboardPage.getCombinedModeProcessCollapseLink().shouldBe(Condition.disappear);
  }

  // ===================================================================================
  @Test
  public void testPreviewCompactModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcess();
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItem().shouldBe(Condition.appear)
        .shouldHave(Condition.cssValue(POINTER_EVENTS_PROPERTY, NONE));
  }

  @Test
  public void testPreviewCompactModeProcessFilterCategory() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcessFilterCategory(DATA_TABLE);
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItemName().shouldBe(Condition.appear)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testPreviewCompactModeProcessFilterProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcessFilterProcess(SHOWCASE_DATA_TABLE);
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItemName().shouldBe(Condition.appear)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testChangeCompactModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.changeCompactModeProcessAndSaveWidget(DATA_TABLE, SHOWCASE_DATA_TABLE);

    newDashboardPage.getWidgetByName(SHOWCASE_DATA_TABLE).shouldBe(Condition.appear);
    newDashboardPage.getCompactModeProcessDisplayedDisabledFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testDeleteCompactModeProcess() {
    newDashboardPage.switchToEditMode();
    newDashboardPage.deleteCompactModeProcess();
    newDashboardPage.getCompactModeProcessContainer().shouldNotBe(Condition.exist);
  }

  @Test
  public void testStartCompactModeProcess() {
    newDashboardPage.startCompactModeProcessByProcessName(SHOWCASE_DATA_TABLE);

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testStartCompactModeProcessWhenExpanded() {
    newDashboardPage.checkDisplayedCompactModeProcessContainer();
    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.startCompactModeProcessByProcessName(SHOWCASE_DATA_TABLE);

    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.getDisplayedTaskTitle()
        .shouldHave(Condition.attribute(TITLE_ATTRIBUTE, START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessName() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    newDashboardPage.applyFilterCompactModeProcessProcessName(SHOWCASE_DATA_TABLE);

    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessNameWhenExpanded() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.applyFilterCompactModeProcessProcessNameWhenExpanded(SHOWCASE_DATA_TABLE);

    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessType() {
    newDashboardPage.checkCompactModeProcessDisplayedProcessItem(SHOWCASE_DATA_TABLE);

    newDashboardPage.applyFilterCompactModeProcessProcessType(EXPRESS_PROCESS);

    newDashboardPage.checkCompactModeProcessDisappearedProcessItem(SHOWCASE_DATA_TABLE);
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessTypeWhenExpanded() {
    newDashboardPage.checkCompactModeProcessDisplayedProcessItem(SHOWCASE_DATA_TABLE);

    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.applyFilterCompactModeProcessProcessTypeWhenExpanded(EXPRESS_PROCESS);

    newDashboardPage.checkCompactModeProcessDisappearedProcessItem(SHOWCASE_DATA_TABLE);
  }

  @Test
  public void testFilterCompactModeProcessFilterCategory() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    newDashboardPage.applyFilterCompactModeProcessCategory(DATA_TABLE);

    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testFilterCompactModeProcessFilterCategoryWhenExpanded() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.applyFilterCompactModeProcessCategoryWhenExpanded(DATA_TABLE);

    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testExpandCollapseCompactModeProcess() {
    newDashboardPage.checkDisplayedCompactModeProcessContainer();
    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.collapseCompactModeProcess();
    newDashboardPage.getCompactModeProcessCollapseLink().shouldBe(Condition.disappear);
  }

  @Test
  public void testSaveResetApplyCompactModeProcessFilter() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    newDashboardPage.testSaveResetApplyCompactModeProcessFilter();
  }

  @Test
  public void testSaveResetApplyCompactModeProcessFilterWhenExpanded() {
    newDashboardPage.getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    newDashboardPage.expandCompactModeProcess();
    newDashboardPage.testSaveResetApplyCompactModeProcessFilterWhenExpanded();
  }

  @Test
  public void testSearchRemoveCompactModeProcessFilter() {
    newDashboardPage.testSearchRemoveCompactModeProcessFilter();
  }

  @Test
  public void testSearchRemoveCompactModeProcessFilterWhenExpanded() {
    newDashboardPage.testSearchRemoveCompactModeProcessFilterWhenExpanded();
  }

  @Test
  public void testOpenCompactModeProcessStatistic() {
    newDashboardPage.checkDisplayedCompactModeProcessContainer();

    newDashboardPage.openCompactModeProcessInforPanel();
    newDashboardPage.getCompactModeProcessInfoProcessTypes().shouldHave(CollectionCondition.size(3), DEFAULT_TIMEOUT);
  }

  @Test
  public void testOpenCompactModeProcessStatisticWhenExpanded() {
    newDashboardPage.checkDisplayedCompactModeProcessContainer();
    newDashboardPage.expandCompactModeProcess();

    newDashboardPage.openCompactModeProcessInforPanelWhenExpanded();
    newDashboardPage.getCompactModeProcessInfoProcessTypesWhenExpanded().shouldHave(CollectionCondition.size(3),
        DEFAULT_TIMEOUT);
  }

  // ===================================================================================
  @Test
  public void testChangeProcessDisplayMode() {
    // Add FULL_MODE widget
    newDashboardPage.switchToEditMode();
    newDashboardPage.addWidget();
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashboardPage.addNewProcessWidget();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);
    newDashboardPage.switchToViewMode();

    // Change to COMPACT_MODE
    newDashboardPage.switchToEditMode();
    editProcessWidgetConfiguration = newDashboardPage.editFullModeProcess();
    editProcessWidgetConfiguration.changeToCompactModeProcess(DATA_TABLE, SHOWCASE_DATA_TABLE);
    editProcessWidgetConfiguration.getCompactModeProcessCategoryFilter()
        .shouldHave(Condition.value(SHOWCASE_DATA_TABLE_CATEGORY));
    editProcessWidgetConfiguration.getCompactModeProcessSelectedProcess()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    // Change to FULL_MODE
    editProcessWidgetConfiguration.selectFullMode();
    editProcessWidgetConfiguration.getFullModeProcessSelectedProcessInput()
        .shouldHave(Condition.value(CATEGORIED_LEAVE_REQUEST));

    // Change to COMPACT_MODE
    editProcessWidgetConfiguration.selectCompactMode();
    editProcessWidgetConfiguration.getCompactModeProcessCategoryFilter()
        .shouldHave(Condition.value(SHOWCASE_DATA_TABLE_CATEGORY));
    editProcessWidgetConfiguration.getCompactModeProcessSelectedProcess()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    // Change to IMAGE_MODE
    editProcessWidgetConfiguration.selectImageMode();
    editProcessWidgetConfiguration.getImageModeProcessSelectedProcessInput()
        .shouldHave(Condition.value(CATEGORIED_LEAVE_REQUEST));

    // Change to COMBINED_MODE
    editProcessWidgetConfiguration.selectCombinedMode();
    editProcessWidgetConfiguration.getCombinedModeProcessSelectedProcessInput()
        .shouldHave(Condition.value(CATEGORIED_LEAVE_REQUEST));
  }

  @Test
  public void testSavedDataWhenChangeProcessDisplayMode() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.changeCompactModeProcessAndSaveWidget(DATA_TABLE, SHOWCASE_DATA_TABLE);
    newDashboardPage.switchToViewMode();

    // Change to FULL_MODE from COMPACT_MODE
    newDashboardPage.switchToEditMode();
    editProcessWidgetConfiguration = newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.switchToViewMode();

    // Edit FULL_MODE and check no COMPACT_MODE old data
    newDashboardPage.switchToEditMode();
    editProcessWidgetConfiguration = newDashboardPage.editFullModeProcess();
    editProcessWidgetConfiguration.selectCompactMode();
    editProcessWidgetConfiguration.getCompactModeProcessDisplayedCategoryFilter()
        .shouldNotHave(Condition.value(SHOWCASE_DATA_TABLE_CATEGORY));
    editProcessWidgetConfiguration.getCompactModeProcessSelectedProcess().shouldNotBe(Condition.exist);

    // Change to COMPACT_MODE from FULL_MODE
    editProcessWidgetConfiguration.changeToCompactModeProcess(DATA_TABLE, SHOWCASE_DATA_TABLE);
    editProcessWidgetConfiguration.clickSaveProcessWidget();
    newDashboardPage.switchToViewMode();

    // Edit COMPACT_MODE and check no FULL_MODE old data
    newDashboardPage.switchToEditMode();
    editProcessWidgetConfiguration = newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullMode();
    editProcessWidgetConfiguration.getFullModeProcessSelectedProcessInput()
        .shouldNotHave(Condition.value(CATEGORIED_LEAVE_REQUEST));
  }
}
