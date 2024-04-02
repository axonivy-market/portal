package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class TaskDetailsTest extends BaseTest {

  // TASK NAME
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER = "Take Order";

  // NOTE CONTENT
  private static final String NOTE_TASK_DETAIL_TECHNICAL_CASE =
      "Note is added on Task Details and the task has Technical Case";
  private static final String NOTE_TASK_DETAIL_BUSINESS_CASE =
      "Note is added on Task Details and the task has only Business Case";

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(ORDER_PIZZA);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_BUSINESS_CASE);
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
    taskDetailsPage.gotoBusinessCase();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_TECHNICAL_CASE);
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    CaseDetailsPage caseDetailsPage = taskDetailsPage.gotoTechnicalCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    caseDetailsPage.gotoBusinessCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
  }

  @Test
  public void testShareTaskDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getShareButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    taskDetailsPage.getShareDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToRelativeLink(denyShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    mainMenuPage.openTaskList();
    taskWidgetPage.openTask(TAKE_ORDER);
    taskDetailsPage.getShareButton().shouldBe(Condition.disappear);
  }
}
