package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class NewDashboardProcessWidgetTest extends BaseTest {
  private static final String SHOWCASE_DATA_TABLE_CATEGORY = "Showcase/DataTable";
  private static final String TEST_FOR_IVYPORTAL_3369 = "Test for IVYPORTAL-3369";
  private static final String MY_FILTER = "My filter";
  private static final String SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME = "Showcase Data Table filter";
  private static final String START_DATA_TABLE_SHOWCASE = "Start DataTable Showcase";
  private static final String CATEGORIED_LEAVE_REQUEST = "Categoried Leave Request";
  private static final String DATA_TABLE = "DataTable";
  private static final String SHOWCASE_DATA_TABLE = "Showcase Data Table";
  private NewDashboardPage newDashboardPage;
  private static final String IMAGE_URI = "PROCESSMODELING";
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
    editProcessWidgetConfiguration.getImageModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    editProcessWidgetConfiguration.getStartButton().shouldBe(Condition.disabled);
    editProcessWidgetConfiguration.getDisabledMoreInformationLink().shouldBe(Condition.appear);
    editProcessWidgetConfiguration.getImageModeProcessImage()
        .shouldHave(Condition.attributeMatching("src", ".*" + IMAGE_URI + ".*"));
  }

  @Test
  public void testChangeImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    checkStartButtonAndImageShown();

    editProcessWidgetConfiguration = newDashboardPage.editImageProcess();
    String newProcessName = "Appraisal";
    editProcessWidgetConfiguration.changeImageModeProcessAndSaveWidget(newProcessName);
    newDashboardPage.getProcessItemName().shouldHave(Condition.exactTextCaseSensitive(newProcessName));
  }

  private void checkStartButtonAndImageShown() {
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.getDisabledMoreInformationLink().shouldBe(Condition.appear);
    newDashboardPage.getProcessImage().shouldHave(Condition.attributeMatching("src", ".*" + IMAGE_URI + ".*"));
  }

  @Test
  public void testDeleteImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageModeAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    checkStartButtonAndImageShown();

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

    // make sure process is started
    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", START_DATA_TABLE_SHOWCASE));
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

    // make sure back and start button is shown
    $("a[id='back-link']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='start-process-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(Condition.enabled);
  }

  // ===================================================================================
  @Test
  public void testPreviewFullModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewFullModeProcess(CATEGORIED_LEAVE_REQUEST);
    editProcessWidgetConfiguration.getFullModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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
    String newProcessName = "Appraisal";
    editProcessWidgetConfiguration.changeFullModeProcessAndSaveWidget(newProcessName);
    newDashboardPage.getFullModeProcessName().shouldHave(Condition.exactTextCaseSensitive(newProcessName));
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
    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", START_DATA_TABLE_SHOWCASE));
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

    $("a[id='back-link']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='start-process-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(Condition.enabled);
  }

  // ===================================================================================
  @Test
  public void testPreviewCombinedModeProcess() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCombinedModeProcess(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);
    editProcessWidgetConfiguration.getCombinedModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    editProcessWidgetConfiguration.getStartButton().is(Condition.disabled);

    editProcessWidgetConfiguration.getSelectedTasksTab().shouldBe(Condition.appear);
    editProcessWidgetConfiguration.getSelectedCasesTab().shouldBe(Condition.disappear);
    editProcessWidgetConfiguration.getFirstTaskDisabledStartAction().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.cssValue("pointer-events", "none"));

    editProcessWidgetConfiguration.getCasesTab().shouldBe(Condition.appear).click();
    editProcessWidgetConfiguration.getFirstCaseName().waitUntil(Condition.exist, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Leave Request Test For IVYPORTAL-3369"));
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
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, null);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.resizeCombinedModeProcess();
    newDashboardPage.getTasksTabDataContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);

    editProcessWidgetConfiguration = newDashboardPage.editCombinedModeProcess();
    String newProcessName = "Appraisal";
    editProcessWidgetConfiguration.changeCombinedModeProcessAndSaveWidget(newProcessName);
    newDashboardPage.getCombinedModeProcessName().shouldHave(Condition.exactTextCaseSensitive(newProcessName));
  }

  @Test
  public void testDeleteCombinedModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, null);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);
    newDashboardPage.resizeCombinedModeProcess();
    newDashboardPage.getTasksTabDataContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);

    newDashboardPage.deleteCombinedModeProcess();
    newDashboardPage.getCombinedModeProcessContainer().shouldNotBe(Condition.exist);
  }

  @Test
  public void testStartCombinedModeProcess() {
    testStartCombinedModeProcess(false);
  }

  @Test
  public void testStartCombinedModeProcessWhenExpanded() {
    testStartCombinedModeProcess(true);
  }

  private void testStartCombinedModeProcess(boolean isExpanded) {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(SHOWCASE_DATA_TABLE, null);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    newDashboardPage.switchToViewMode();
    if (isExpanded) {
      newDashboardPage.expandCombindedModeProcess();
    }
    newDashboardPage.getStartButton().shouldBe(Condition.enabled);
    newDashboardPage.startProcess();

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testStartCombinedModeProcessTask() {
    testStartCombinedModeProcessTask(false);
  }

  @Test
  public void testStartCombinedModeProcessTaskWhenExpanded() {
    testStartCombinedModeProcessTask(true);
  }

  private void testStartCombinedModeProcessTask(boolean isExpanded) {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    if (isExpanded) {
      newDashboardPage.expandCombindedModeProcess();
    }
    newDashboardPage.getCombinedModeProcessFirstTaskStartAction().shouldBe(Condition.appear).click();

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Sick Leave Request"));
  }

  @Test
  public void testOpenCombinedModeProcessTask() {
    testOpenCombinedModeProcessTask(false);
  }

  @Test
  public void testOpenCombinedModeProcessTaskWhenExpanded() {
    testOpenCombinedModeProcessTask(true);
  }

  private void testOpenCombinedModeProcessTask(boolean isExpanded) {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    if (isExpanded) {
      newDashboardPage.expandCombindedModeProcess();
    }
    newDashboardPage.getCombinedModeProcessFirstTaskName().shouldBe(Condition.appear).click();

    $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive("Task: Sick Leave Request"));
  }

  @Test
  public void testOpenCombinedModeProcessCase() {
    testOpenCombinedModeProcessCase(false);
  }

  @Test
  public void testOpenCombinedModeProcessCaseWhenExpanded() {
    testOpenCombinedModeProcessCase(true);
  }

  private void testOpenCombinedModeProcessCase(boolean isExpanded) {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    if (isExpanded) {
      newDashboardPage.expandCombindedModeProcess();
    }
    newDashboardPage.getCasesTab().shouldBe(Condition.appear).click();
    newDashboardPage.getCombinedModeProcessFirstCaseName().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();

    $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive("Case: Leave Request Test For IVYPORTAL-3369"));
  }

  @Test
  public void testExpandCollapseCombinedModeProcess() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST, TEST_FOR_IVYPORTAL_3369);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.getCombinedModeProcessCollapseLink().click();
    newDashboardPage.getCombinedModeProcessExpandLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    newDashboardPage.getCombinedModeProcessCollapseLink().shouldBe(Condition.disappear);
  }

  // ===================================================================================
  @Test
  public void testPreviewCompactModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcess();
    editProcessWidgetConfiguration.getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItem().shouldBe(Condition.appear)
        .shouldHave(Condition.cssValue("pointer-events", "none"));
  }

  @Test
  public void testPreviewCompactModeProcessFilterCategory() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcessFilterCategory(DATA_TABLE);
    editProcessWidgetConfiguration.getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItemName().shouldBe(Condition.appear)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testPreviewCompactModeProcessFilterProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewCompactModeProcessFilterProcess(SHOWCASE_DATA_TABLE);
    editProcessWidgetConfiguration.getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    editProcessWidgetConfiguration.getCompactModeProcessDisabledFirstProcessItemName().shouldBe(Condition.appear)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testChangeCompactModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.changeCompactModeProcessAndSaveWidget(DATA_TABLE, SHOWCASE_DATA_TABLE);

    newDashboardPage.getAllWidgetHeaders().find(Condition.textCaseSensitive(SHOWCASE_DATA_TABLE))
        .shouldBe(Condition.appear);
    newDashboardPage.getCompactModeProcessDisabledFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
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
    testStartCompactModeProcessTest();
  }

  private void testStartCompactModeProcessTest() {
    newDashboardPage.getCompactModeProcessProcessItemName(SHOWCASE_DATA_TABLE)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", START_DATA_TABLE_SHOWCASE));
  }

  @Test
  public void testStartCompactModeProcessWhenExpanded() {
    newDashboardPage.getCompactModeProcessContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    newDashboardPage.expandCompactModeProcessFilterPanel();
    testStartCompactModeProcessTest();
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessName() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    testFilterCompactModeProcessFilterProcessName(false);
  }

  private void testFilterCompactModeProcessFilterProcessName(boolean isExpanded) {
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.filterCompactModeProcessProcessName(isExpanded, SHOWCASE_DATA_TABLE);
    newDashboardPage.applyCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessNameWhenExpanded() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    newDashboardPage.expandCompactModeProcessFilterPanel();
    testFilterCompactModeProcessFilterProcessName(true);
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessType() {
    newDashboardPage.getCompactModeProcessProcessItemName(SHOWCASE_DATA_TABLE).waitUntil(Condition.appear,
        DEFAULT_TIMEOUT);
    testFilterCompactModeProcessFilterProcessType(false);
  }

  private void testFilterCompactModeProcessFilterProcessType(boolean isExpanded) {
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.filterCompactModeProcessProcessType(isExpanded, "EXPRESS_PROCESS");
    newDashboardPage.applyCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessProcessItemName(SHOWCASE_DATA_TABLE).waitUntil(Condition.disappear,
        DEFAULT_TIMEOUT);
  }

  @Test
  public void testFilterCompactModeProcessFilterProcessTypeWhenExpanded() {
    newDashboardPage.getCompactModeProcessProcessItemName(SHOWCASE_DATA_TABLE).waitUntil(Condition.appear,
        DEFAULT_TIMEOUT);
    newDashboardPage.expandCompactModeProcessFilterPanel();
    testFilterCompactModeProcessFilterProcessType(true);
  }

  @Test
  public void testFilterCompactModeProcessFilterCategory() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    testFilterCompactModeProcessFilterCategory(false);
  }

  private void testFilterCompactModeProcessFilterCategory(boolean isExpanded) {
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.filterCompactModeProcessCategory(isExpanded, DATA_TABLE);
    newDashboardPage.applyCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testFilterCompactModeProcessFilterCategoryWhenExpanded() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    newDashboardPage.expandCompactModeProcessFilterPanel();
    testFilterCompactModeProcessFilterCategory(true);
  }

  @Test
  public void testExpandCollapseCompactModeProcess() {
    newDashboardPage.getCompactModeProcessContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    newDashboardPage.expandCompactModeProcessFilterPanel();

    SelenideElement collapseLink = newDashboardPage.getCompactModeProcessCollapseLink();
    collapseLink.click();
    collapseLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    newDashboardPage.getCompactModeProcessExpandLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    newDashboardPage.getCompactModeProcessFilterLink(true).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    newDashboardPage.getCompactModeProcessFilterLink(false).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testSaveResetApplyCompactModeProcessFilter() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    testSaveResetApplyCompactModeProcessFilter(false);
  }

  @Test
  public void testSaveResetApplyCompactModeProcessFilterWhenExpanded() {
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    newDashboardPage.expandCompactModeProcessFilterPanel();
    testSaveResetApplyCompactModeProcessFilter(true);
  }

  private void testSaveResetApplyCompactModeProcessFilter(boolean isExpanded) {
    // Save filter
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.selectCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE, "IVY_PROCESS", DATA_TABLE);
    newDashboardPage.saveCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
    // Apply filter
    newDashboardPage.applyCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    // Reset filter
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.resetCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    // Apply saved filter
    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.selectCompactModeProcessSavedFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    newDashboardPage.applyCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  @Test
  public void testSearchRemoveCompactModeProcessFilter() {
    testSearchRemoveCompactModeProcessFilter(false);
  }

  @Test
  public void testSearchRemoveCompactModeProcessFilterWhenExpanded() {
    testSearchRemoveCompactModeProcessFilter(true);
  }

  private void testSearchRemoveCompactModeProcessFilter(boolean isExpanded) {
    newDashboardPage.getCompactModeProcessContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    if (isExpanded) {
      newDashboardPage.expandCompactModeProcessFilterPanel();
    }

    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    testCreateCompactModeProcessFilters(isExpanded);
    testSearchCompactModeProcessFilters(isExpanded);

    newDashboardPage.openCompactModeProcessManageFilters(isExpanded);
    testRemoveCompactModeProcessFilter();
    newDashboardPage.closeCompactModeProcessManagerFilters();

    newDashboardPage.openCompactModeProcessFilterPanel(isExpanded);
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  private void testCreateCompactModeProcessFilters(boolean isExpanded) {
    newDashboardPage.selectCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE, "IVY_PROCESS", DATA_TABLE);
    newDashboardPage.saveCompactModeProcessFilter(isExpanded, MY_FILTER);
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));

    newDashboardPage.saveCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 1)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
  }

  private void testSearchCompactModeProcessFilters(boolean isExpanded) {
    SelenideElement searchInput = newDashboardPage.getCompactModeProcessFilterPanelSearchInput(isExpanded);
    searchInput.shouldBe(Condition.appear).clear();
    searchInput.sendKeys(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);

    searchInput.shouldBe(Condition.appear).clear();
    newDashboardPage.getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));
  }

  private void testRemoveCompactModeProcessFilter() {
    newDashboardPage.removeCompactModeProcessFilter(MY_FILTER);
    newDashboardPage.getManageWidgetFilterDialogFirstSavedFilter()
        .waitUntil(Condition.attribute("data-rk", SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  @Test
  public void testOpenCompactModeProcessStatistic() {
    testOpenCompactModeProcessStatistic(false);
  }

  @Test
  public void testOpenCompactModeProcessStatisticWhenExpanded() {
    testOpenCompactModeProcessStatistic(true);
  }

  public void testOpenCompactModeProcessStatistic(boolean isExpanded) {
    newDashboardPage.getCompactModeProcessContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    if (isExpanded) {
      newDashboardPage.expandCompactModeProcessFilterPanel();
    }

    newDashboardPage.openCompactModeProcessInforPanel(isExpanded);
    newDashboardPage.getCompactModeProcessInfoProcessTypes(isExpanded).shouldHave(CollectionCondition.size(3),
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
    editProcessWidgetConfiguration.getCompactModeProcessProcessFilter().$("span.ui-selectcheckboxmenu-token-label")
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    // Change to FULL_MODE
    editProcessWidgetConfiguration.selectFullMode();
    editProcessWidgetConfiguration.getFullModeProcessSelectedProcess().$("input[id$=':selected-full-process_input']")
        .shouldHave(Condition.value(CATEGORIED_LEAVE_REQUEST));

    // Change to COMPACT_MODE
    editProcessWidgetConfiguration.selectCompactMode();
    editProcessWidgetConfiguration.getCompactModeProcessCategoryFilter()
        .shouldHave(Condition.value(SHOWCASE_DATA_TABLE_CATEGORY));
    editProcessWidgetConfiguration.getCompactModeProcessProcessFilter().$("span.ui-selectcheckboxmenu-token-label")
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));

    // Change to IMAGE_MODE
    editProcessWidgetConfiguration.selectImageMode();
    editProcessWidgetConfiguration.getImageModeProcessSelectedProcess().$("input[id$=':selected-image-process_input']")
        .shouldHave(Condition.value(CATEGORIED_LEAVE_REQUEST));

    // Change to COMBINED_MODE
    editProcessWidgetConfiguration.selectCombinedMode();
    editProcessWidgetConfiguration.getCombinedModeProcessSelectedProcess().$("input[id$=':selected-combined-process_input']")
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
    editProcessWidgetConfiguration.getCompactModeProcessCategoryFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.value(SHOWCASE_DATA_TABLE_CATEGORY));
    editProcessWidgetConfiguration.getCompactModeProcessProcessFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.ui-selectcheckboxmenu-token-label")
        .shouldNotBe(Condition.exist);

    // Change to COMPACT_MODE from FULL_MODE
    editProcessWidgetConfiguration.changeToCompactModeProcess(DATA_TABLE, SHOWCASE_DATA_TABLE);
    editProcessWidgetConfiguration.clickSaveProcessWidget();
    newDashboardPage.switchToViewMode();

    // Edit COMPACT_MODE and check no FULL_MODE old data
    newDashboardPage.switchToEditMode();
    editProcessWidgetConfiguration = newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectFullMode();
    editProcessWidgetConfiguration.getFullModeProcessSelectedProcess().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$("input[id$=':selected-full-process_input']").shouldNotHave(Condition.value(CATEGORIED_LEAVE_REQUEST));
  }
}
