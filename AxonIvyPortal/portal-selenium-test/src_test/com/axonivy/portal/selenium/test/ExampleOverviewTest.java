package com.axonivy.portal.selenium.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.CaseMapPage;
import com.axonivy.portal.selenium.page.ExampleOverviewPage;
import com.axonivy.portal.selenium.page.LeaveRequestOverviewPage;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.LendingDetailPage;
import com.axonivy.portal.selenium.page.LendingOverviewPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class ExampleOverviewTest extends BaseTest {

  public final static String PORTAL_EXAMPLES_PROCESS_CHAIN =
      "portal-user-examples/17236DB1D3DA14C0/userExampleGuide.ivp";
  private ExampleOverviewPage exampleOverviewPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLinkWithEmbedInFrame(PORTAL_EXAMPLES_PROCESS_CHAIN);
    exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.switchToIFrameOfTask();
  }

  @Test
  public void testOverviewLeaveRequest() {
    LeaveRequestOverviewPage leaveRequestOverview = exampleOverviewPage.openLeaveRequestOverview();
    exampleOverviewPage.waitForIFrameContentVisible();
    leaveRequestOverview.getHearText().shouldBe(Condition.text("Leave Request"));

    leaveRequestOverview.getStepName(0).shouldBe(Condition.text("Creation"));
    leaveRequestOverview.getStepName(1).shouldBe(Condition.text("Approval"));
    leaveRequestOverview.getStepName(2).shouldBe(Condition.text("Summary"));

    LeaveRequestPage leaveRequest = leaveRequestOverview.start();
    assertEquals("Create leave request - Axon Ivy", leaveRequest.getPageTitle());
  }

  @Test
  public void testOverviewLending() {
    LendingOverviewPage lendingOverview = exampleOverviewPage.openLendingOverview();
    lendingOverview.getHearText().shouldBe(Condition.text("Lending (Case Map)"));

    lendingOverview.getStageName(0).shouldBe(Condition.text("Identification"));
    lendingOverview.getStageName(1).shouldBe(Condition.text("Credit rating"));
    lendingOverview.getStageName(2).shouldBe(Condition.text("Approval"));

    LendingDetailPage creditRating = lendingOverview.navigateToStageDetail(1);
    creditRating.getHearText().shouldBe(Condition.text("Lending (Case Map)"));
    creditRating.getStageName().shouldBe(Condition.text("Stage 2 - Credit rating"));
    creditRating.getProcessName(0).shouldBe(Condition.text("Check Company Register"));
    creditRating.getProcessName(1).shouldBe(Condition.text("Internal Solvency Check"));
    creditRating.getSideStepName(0).shouldBe(Condition.text("External Solvency Check"));


    lendingOverview = creditRating.navigateToLendingOverview();
    lendingOverview.getHearText().shouldBe(Condition.text("Lending (Case Map)"));


    CaseMapPage caseMapPage = lendingOverview.startLendingCase();
    caseMapPage.switchToIFrameOfTask();
    assertEquals("Credit Request", caseMapPage.getHeader());
  }

  @Test
  public void testOverviewDetailNavigate() {
    LendingOverviewPage lendingOverview = exampleOverviewPage.openLendingOverview();

    LendingDetailPage identification = lendingOverview.navigateToStageDetail(0);

    LendingDetailPage creditRating = identification.navigateToNextDetail();
    creditRating.getStageName().shouldBe(Condition.text("Stage 2 - Credit rating"));

    LendingDetailPage approval = creditRating.navigateToNextDetail();
    approval.getStageName().shouldBe(Condition.text("Stage 3 - Approval"));

    creditRating = approval.navigateToPreviousDetail();
    creditRating.getStageName().shouldBe(Condition.text("Stage 2 - Credit rating"));


    identification = creditRating.navigateToPreviousDetail();
    identification.getStageName().shouldBe(Condition.text("Stage 1 - Identification"));
  }
}
