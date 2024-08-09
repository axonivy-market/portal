package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class CaseDetailsTest extends BaseTest {
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER = "Take Order";
  private static final String TAKE_ORDER_AND_MAKE_PIZZA = "Take Order and Make Pizza";

  // NOTE CONTENT
  private static final String NOTE_TECHNICAL_CASE = "Note is added on Technical Case";
  private static final String NOTE_BUSINESS_CASE = "Note is added on Business Case";

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  public static final String CUSTOM_CASE_WIDGET_NAME = "Create Event: Test custom case details";
  public static final String CREATE_EVENT_TEST_URL ="portal-developer-examples/17A2C6D73AB4186E/CreateEventTest.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test 
  public void testVisibilityOfNotesWhenAddNoteOnBusinessCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.addNote(NOTE_BUSINESS_CASE);
    caseDetailsPage.getNotesWithContent(NOTE_BUSINESS_CASE).shouldHave(size(1));
    caseDetailsPage.gotoTaskDetailsPageOfRelatedTask(ORDER_PIZZA);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getNotesWithContent(NOTE_BUSINESS_CASE).shouldHave(size(1));
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTechnicalCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.addNote(NOTE_TECHNICAL_CASE);
    caseDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
    caseDetailsPage.gotoTaskDetailsPageOfRelatedTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
    taskDetailsPage.gotoBusinessCase();
    caseDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
  }
  
  @Test
  public void testShareCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantShareLinkCaseDetailsPermission);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getShareButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    caseDetailsPage.getShareDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
      
    redirectToRelativeLink(denyShareLinkCaseDetailsPermission);
    redirectToNewDashBoard();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getShareButton().shouldBe(Condition.disappear);
  }

  @Test
  public void testShowOnlyOpenTasks() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(ORDER_PIZZA);
    caseDetailsPage.clickShowOnlyOpenTasks();
    caseDetailsPage.countRelatedTasks().shouldBe(size(2));
    caseDetailsPage.clickShowOnlyOpenTasks();
    caseDetailsPage.countRelatedTasks().shouldBe(size(3));

  }
}
