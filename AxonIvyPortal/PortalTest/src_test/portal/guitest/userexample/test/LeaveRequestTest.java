package portal.guitest.userexample.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.userexamples.page.LeaveRequestPage;
import portal.guitest.userexamples.page.UserExamplesEndPage;

public class LeaveRequestTest extends BaseTest {
  private static final String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private LeaveRequestPage leaveRequestPage;
  private TaskWidgetPage taskWidgetPage;
  
  @Before
  @Override
  public void setup() {
    super.setup();
  }
  
  @Test
  public void testLeaveRequestValidation() {
    leaveRequestPage = startLeaveRequestProcess();
    Assert.assertEquals("Leave type: This field is required"
        + ",From: This field is required,"
        + "To: This field is required,"
        + "Approver: This field is required,"
        + "Requester comment: This field is required", 
        leaveRequestPage.clickSubmitAndGetValidationMsg());
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterday =  LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", today, yesterday, TestAccount.DEMO_USER.getFullName(), "requester comment");
    Assert.assertEquals("'To' must be later than 'From'.", leaveRequestPage.clickSubmitAndGetValidationMsg());
  }
 
  @Test
  public void testApproveScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    Assert.assertEquals("Create leave request", leaveRequestPage.getPageTitle());
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.waitForIFrameContentVisible();
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", yesterday, today, TestAccount.ADMIN_USER.getFullName(), "requester comment");
    leaveRequestPage.clickSubmitButton();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    WaitHelper.assertTrueWithWait(() -> "Approval".equals(leaveRequestPage.getPageTitle()));
    leaveRequestPage.enterApproverComment("Approved");
    leaveRequestPage.clickApproveBtn();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Your leave request is approved");
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    WaitHelper.assertTrueWithWait(() -> "Approval Result".equals(leaveRequestPage.getPageTitle()));
    UserExamplesEndPage userExamplesEndPage = leaveRequestPage.finishLeaveRequest();
    CaseDetailsPage caseDetailsPage = userExamplesEndPage.goToCaseDetail();
    Assert.assertEquals("Leave Request", caseDetailsPage.getCaseName());
  }

  @Test
  public void testRejectScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    Assert.assertEquals("Create leave request", leaveRequestPage.getPageTitle());
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday =  LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", yesterday, today, TestAccount.ADMIN_USER.getFullName(), "requester comment");
    leaveRequestPage.clickSubmitButton();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    WaitHelper.assertTrueWithWait(() -> "Approval".equals(leaveRequestPage.getPageTitle()));
    leaveRequestPage.enterApproverComment("Rejected");
    leaveRequestPage.clickRejectBtn();
    leaveRequestPage.clickOnLogout();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Your leave request is rejected");
    taskWidgetPage.startTask(0);
    taskWidgetPage.switchToIFrameOfTask();
    WaitHelper.assertTrueWithWait(() -> "Approval Result".equals(leaveRequestPage.getPageTitle()));
  }
  
  private LeaveRequestPage startLeaveRequestProcess() {
    redirectToRelativeLinkWithEmbedInFrame(LEAVE_REQUEST_START_LINK);
    new MainMenuPage().switchToIFrameOfTask();
    return new LeaveRequestPage();
  }
}
