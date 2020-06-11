package portal.guitest.userexample.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.ExampleOverviewPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LeaveRequestOverviewPage;
import portal.guitest.page.LendingDetailPage;
import portal.guitest.page.LendingOverviewPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.userexamples.page.CaseMapPage;
import portal.guitest.userexamples.page.LeaveRequestPage;

public class ExampleOverviewTest extends BaseTest {
  private HomePage homePage;
  private ProcessWidgetPage processWidget;
  String EXAMPLE_PROCESS_NAME = "User example guide";
  
  @Before
  @Override
  public void setup() {
    super.setup();
    homePage = new HomePage();
  }
  
  @Test
  public void testOverviewLeaveRequest() {
    processWidget = homePage.getProcessWidget();
    ExampleOverviewPage exampleOverviewPage = processWidget.openExampleOverviewPage(EXAMPLE_PROCESS_NAME);
    LeaveRequestOverviewPage leaveRequestOverview = exampleOverviewPage.openLeaveRequestOverview();
    assertEquals("Leave Request", leaveRequestOverview.getHearText());
    assertEquals("Creation", leaveRequestOverview.getStepName(0));
    assertEquals("Approval", leaveRequestOverview.getStepName(1));
    assertEquals("Summary", leaveRequestOverview.getStepName(2));
    
    LeaveRequestPage leaveRequest = leaveRequestOverview.start();
    Assert.assertEquals("Create leave request", leaveRequest.getPageTitle());
  }
  
  @Test
  public void testOverviewLending() {
    processWidget = homePage.getProcessWidget();
    ExampleOverviewPage exampleOverviewPage = processWidget.openExampleOverviewPage(EXAMPLE_PROCESS_NAME);
    
    LendingOverviewPage lendingOverview = exampleOverviewPage.openLendingOverview();
    assertEquals("Lending (Case Map)", lendingOverview.getHearText());
    assertEquals("Identification", lendingOverview.getStageName(0));
    assertEquals("Credit rating", lendingOverview.getStageName(1));
    assertEquals("Approval", lendingOverview.getStageName(2));
    
    LendingDetailPage creditRating = lendingOverview.navigateToStageDetail(1);
    assertEquals("Lending (Case Map)", creditRating.getHearText());
    assertEquals("Stage 2 - Credit rating", creditRating.getStageName());
    assertEquals("Check Company Register", creditRating.getProcessName(0));
    assertEquals("Internal Solvency Check", creditRating.getProcessName(1));
    assertEquals("External Solvency Check", creditRating.getSideStepName(0));
     
    lendingOverview = creditRating.navigateToLendingOverview();
    assertEquals("Lending (Case Map)", lendingOverview.getHearText());
    
    CaseMapPage caseMapPage = lendingOverview.startLendingCase();
    assertEquals("Collect Personal Data", caseMapPage.getTitle());
  }
  
  @Test
  public void testOverviewDetailNavigate() {
    processWidget = homePage.getProcessWidget();
    ExampleOverviewPage exampleOverviewPage = processWidget.openExampleOverviewPage(EXAMPLE_PROCESS_NAME);
    
    LendingOverviewPage lendingOverview = exampleOverviewPage.openLendingOverview();
    
    LendingDetailPage identification = lendingOverview.navigateToStageDetail(0);
     
    LendingDetailPage creditRating = identification.navigateToNextDetail();
    assertEquals("Stage 2 - Credit rating", creditRating.getStageName());
    
    LendingDetailPage approval = creditRating.navigateToNextDetail();
    assertEquals("Stage 3 - Approval", approval.getStageName());
    
    creditRating = approval.navigateToPreviousDetail();
    assertEquals("Stage 2 - Credit rating", creditRating.getStageName());
    
    identification = creditRating.navigateToPreviousDetail();
    assertEquals("Stage 1 - Identification", identification.getStageName());
  }
}
