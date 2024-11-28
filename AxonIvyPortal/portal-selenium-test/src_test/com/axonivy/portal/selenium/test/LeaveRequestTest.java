package com.axonivy.portal.selenium.test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest
public class LeaveRequestTest extends BaseTest {
  private static final String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private static final String APPROVAL_RESULT_TITLE = "Approval Result - Axon Ivy";
  private static final String APPROVAL_TITLE = "Approval - Axon Ivy";
  private static final String CREATE_LEAVE_REQUEST_TITLE = "Create leave request - Axon Ivy";
  
  private LeaveRequestPage leaveRequestPage;

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

  @SuppressWarnings("deprecation")
  @Test
  public void testApproveScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    assertEquals(CREATE_LEAVE_REQUEST_TITLE, leaveRequestPage.getPageTitle());
    String today =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.waitForIFrameContentVisible();
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", yesterday, today,
        TestAccount.ADMIN_USER.getFullName(), "requester comment");
    leaveRequestPage.clickSubmitButton();

    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
    dashboardPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);

    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskIFrameByIndex(0);
    leaveRequestPage.assertPageTitle(APPROVAL_TITLE);
    leaveRequestPage.enterApproverComment("Approved");
    leaveRequestPage.clickApproveBtn();

    taskWidgetPage = new NewDashboardPage();
    taskWidgetPage.waitPageLoaded();
    taskWidgetPage.clickOnLogout();
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Your leave request is approved");
    taskWidget.applyFilter();
    taskWidget.startTaskIFrameByIndex(0);
    leaveRequestPage.assertPageTitle(APPROVAL_RESULT_TITLE);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testRejectScenario() {
    leaveRequestPage = startLeaveRequestProcess();
    leaveRequestPage.assertPageTitle(CREATE_LEAVE_REQUEST_TITLE);

    String today =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    String yesterday = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN, new Locale("en")));
    leaveRequestPage.enterLeaveRequestInformation("Maternity Leave", yesterday, today,
        TestAccount.ADMIN_USER.getFullName(), "requester comment");
    leaveRequestPage.clickSubmitButton();

    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
    dashboardPage.clickOnLogout();
    login(TestAccount.ADMIN_USER);

    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskIFrameByIndex(0);
    leaveRequestPage.assertPageTitle(APPROVAL_TITLE);

    leaveRequestPage.enterApproverComment("Rejected");
    leaveRequestPage.clickRejectBtn();

    taskWidgetPage = new NewDashboardPage();
    taskWidgetPage.waitPageLoaded();
    taskWidgetPage.clickOnLogout();
    login(TestAccount.DEMO_USER);

    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Your leave request is rejected");
    taskWidget.applyFilter();
    taskWidget.startTaskIFrameByIndex(0);
    leaveRequestPage.assertPageTitle(APPROVAL_RESULT_TITLE);
  }

  private LeaveRequestPage startLeaveRequestProcess() {
    redirectToRelativeLinkWithEmbedInFrame(LEAVE_REQUEST_START_LINK);
    leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.switchToIFrameOfTask();
    return leaveRequestPage;
  }
}
