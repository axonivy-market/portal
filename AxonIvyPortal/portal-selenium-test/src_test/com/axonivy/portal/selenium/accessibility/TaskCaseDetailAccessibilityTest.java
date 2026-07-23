package com.axonivy.portal.selenium.accessibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.AccessibilityHelpers;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class TaskCaseDetailAccessibilityTest extends BaseTest{
  private static final String EAT_PIZZA = "Eat Pizza";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    permissions().grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    permissions().grantSpecificPortalPermission(PortalPermission.TASK_DISPLAY_CUSTOM_FIELDS_ACTION);
    permissions().grantSpecificPortalPermission(PortalPermission.TASK_WRITE_EXPIRY_ACTIVATOR);
  }

  @Test
  public void taskDetail() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails(EAT_PIZZA);
    var taskDetailsPage = new TaskDetailsPage();
    AccessibilityHelpers.makeA11yReport();

    taskDetailsPage.openAddNoteDialog();
    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.getAddNoteDialog(), "add-note-dialog");
    taskDetailsPage.closeAddNoteDialog();

    taskDetailsPage.openAddAttachmentDialog();
    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.getAddAttachmentDialog(), "add-attachment-dialog");
    taskDetailsPage.closeAddAttachmentDialog();

    taskDetailsPage.openWorkflowEventDialog();
    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.getWorkflowEventsTable(), "workflow-event-dialog");
    taskDetailsPage.closeWorkflowEventDialog();

    taskDetailsPage.openAfterEscalationDialog();
    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.getAfterEscalationDialog(), "after-escalation-dialog");
    taskDetailsPage.closeAfterEscalationDialog();

    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.openDestroyDialog(), "destroy-dialog");
    taskDetailsPage.closeDestroyDialog();

    AccessibilityHelpers.makeElementA11yReport(taskDetailsPage.openDelegateDialog(), "delegate-dialog");
    // taskDetailsPage.closeDelegateDialog();
  }

  @Test
  public void caseDetail() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetNewDashBoardPage caseWidgetPage = new CaseWidgetNewDashBoardPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.clickChangeDesc();
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    AccessibilityHelpers.makeA11yReport();
  }
}
