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
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest(headless = false)
public class NewDashboardProcessWidgetTest extends BaseTest {
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
        .shouldHave(Condition.attribute("title", "Start DataTable Showcase"));
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
        .shouldHave(Condition.attribute("title", "Start DataTable Showcase"));
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
    editProcessWidgetConfiguration.previewCombinedModeProcess(CATEGORIED_LEAVE_REQUEST);
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
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

    editProcessWidgetConfiguration = newDashboardPage.editCombinedModeProcess();
    String newProcessName = "Appraisal";
    editProcessWidgetConfiguration.changeCombinedModeProcessAndSaveWidget(newProcessName);
    newDashboardPage.getCombinedModeProcessName().shouldHave(Condition.exactTextCaseSensitive(newProcessName));
  }

  @Test
  public void testDeleteCombinedModeProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);
    newDashboardPage.getStartButton().shouldBe(Condition.disabled);

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

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Start DataTable Showcase"));
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

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Start DataTable Showcase"));
  }

  @Test
  public void testStartCombinedModeProcessTask() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getCombinedModeProcessFirstTaskStartAction().shouldBe(Condition.appear).click();

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Sick Leave Request"));
  }

  @Test
  public void testStartCombinedModeProcessTaskWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.getCombinedModeProcessFirstTaskStartAction().shouldBe(Condition.appear).click();

    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.attribute("title", "Sick Leave Request"));
  }

  @Test
  public void testOpenCombinedModeProcessTask() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getCombinedModeProcessFirstTaskName().shouldBe(Condition.appear).click();

    $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive("Task: Sick Leave Request"));
  }

  @Test
  public void testOpenCombinedModeProcessTaskWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
    newDashboardPage.getCombinedModeProcessFirstTaskName().shouldBe(Condition.appear).click();

    $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive("Task: Sick Leave Request"));
  }

  @Test
  public void testOpenCombinedModeProcessCase() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.getCasesTab().shouldBe(Condition.appear).click();
    newDashboardPage.getCombinedModeProcessFirstCaseName().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();

    $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive("Case: Leave Request Test For IVYPORTAL-3369"));
  }

  @Test
  public void testOpenCombinedModeProcessCaseWhenExpanded() {
    createCategoriedLeaveRequestTask();

    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration =
        newDashboardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

    newDashboardPage.switchToViewMode();
    newDashboardPage.expandCombindedModeProcess();
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
    editProcessWidgetConfiguration.selectCombinedModeProcessAndSaveWidget(CATEGORIED_LEAVE_REQUEST);

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
}
