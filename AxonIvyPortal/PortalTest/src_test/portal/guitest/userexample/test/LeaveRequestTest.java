package portal.guitest.userexample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.userexamples.page.LeaveRequestPage;

public class LeaveRequestTest extends BaseTest {
  private static String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private LeaveRequestPage leaveRequestPage;
  
  @Before
  @Override
  public void setup() {
    super.setup();
  }
  @Test
  public void testLeaveRequestValidation() {
    leaveRequestPage = startLeaveRequestProcess();
    Assert.assertEquals("Create leave request", leaveRequestPage.getPageTitle());
    Assert.assertEquals("Leave type: This field is required"
        + ",From: This field is required,"
        + "To: This field is required,"
        + "Approver: This field is required,"
        + "Requester comment: This field is required", 
        leaveRequestPage.clickSubmitAndGetValidationMsg());
  }
  
  private LeaveRequestPage startLeaveRequestProcess() {
    redirectToRelativeLink(LEAVE_REQUEST_START_LINK);
    return new LeaveRequestPage();
  }
}
