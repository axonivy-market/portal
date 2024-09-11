package com.axonivy.portal.selenium.test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class LeaveRequestTest extends BaseTest {
  private static final String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private LeaveRequestPage leaveRequestPage;
  private TaskWidgetPage taskWidgetPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
  }

  @Test
  public void testLeaveRequestValidation() {
    leaveRequestPage = startLeaveRequestProcess();
    assertEquals(
        "Leave type: This field is required" + ",From: This field is required," + "To: This field is required,"
            + "Approver: This field is required," + "Requester comment: This field is required",
        leaveRequestPage.clickSubmitAndGetValidationMsg());
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterday =
        LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", today, yesterday,
        TestAccount.DEMO_USER.getFullName(), "requester comment");
    assertEquals("'To' must be later than 'From'.", leaveRequestPage.clickSubmitAndGetValidationMsg());
  }

  @Test
  public void testApproveScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    String today =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.waitForIFrameContentVisible();
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", yesterday, today,
        TestAccount.ADMIN_USER.getFullName(), "requester comment");
    leaveRequestPage.clickSubmitButton();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    leaveRequestPage.enterApproverComment("Approved");
    leaveRequestPage.clickApproveBtn();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Your leave request is approved");
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTaskWithWaitHelper();
//    UserExamplesEndPage userExamplesEndPage = leaveRequestPage.finishLeaveRequest();
//    CaseDetailsPage caseDetailsPage = userExamplesEndPage.goToCaseDetail();
    leaveRequestPage.finishLeaveRequest();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    assertEquals("Leave Request", caseDetailsPage.getCaseName());
  }

  @Test
  public void testRejectScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    String today =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.waitForIFrameContentVisible();
    leaveRequestPage.enterMaternityLeaveRequestInformation(yesterday, today, TestAccount.ADMIN_USER.getFullName(),
        "requester comment");
    leaveRequestPage.clickSubmitButton();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    leaveRequestPage.enterApproverComment("Rejected");
    leaveRequestPage.clickRejectBtn();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Your leave request is rejected");
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTaskWithWaitHelper();
//    UserExamplesEndPage userExamplesEndPage = leaveRequestPage.finishLeaveRequest();
//    CaseDetailsPage caseDetailsPage = userExamplesEndPage.goToCaseDetail();
//    = userExamplesEndPage.goToCaseDetail();
//    assertEquals("Leave Request", caseDetailsPage.getCaseName());
    leaveRequestPage.finishLeaveRequest();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    assertEquals("Leave Request", caseDetailsPage.getCaseName());
  }

  private LeaveRequestPage startLeaveRequestProcess() {
    redirectToRelativeLinkWithEmbedInFrame(LEAVE_REQUEST_START_LINK);
    leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.switchToIFrameOfTask();
    return leaveRequestPage;
  }
}
