package com.axonivy.portal.selenium.test.caze;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.AdditionalCaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.GlobalSearchResultPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class CaseBehaviorsTest extends BaseTest {
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER_AND_MAKE_PIZZA = "Take Order and Make Pizza";
  private static final String ACCESS_CASE_DETAILS = "ACCESS_CASE_DETAILS";
  private static final String ACCESS_BUSINESS_DETAILS = "ACCESS_BUSINESS_DETAILS";
  private com.axonivy.portal.selenium.page.NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
  }

  @AfterEach
  public void cleanUp() {
    updateGlobalVariable(Variable.CASE_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_CASE_LIST.getKey(), ACCESS_CASE_DETAILS);
  }

  @Test
  public void testShowCaseDetailsWhenClickOnCaseInCaseList() {
    updateGlobalVariable(Variable.CASE_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_CASE_LIST.getKey(), ACCESS_CASE_DETAILS);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    // show case details
    assertTrue(caseDetailsPage.isDisplayed());
    // show case details of related case
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
  }

  @Test
  public void testShowCaseDetailsWhenClickOnCaseInGlobalSearch() {
    updateGlobalVariable(Variable.CASE_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_CASE_LIST.getKey(), ACCESS_CASE_DETAILS);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(ORDER_PIZZA);
    resultPage.openCaseTab();
    String firstCaseName = resultPage.getNameOfCase(0);
    assertEquals(ORDER_PIZZA, firstCaseName);
    resultPage.clickOnCase(0);
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.waitForCaseDetailsDisplay();
    assertEquals(ORDER_PIZZA, caseDetailsPage.getCaseName());
  }


  @Test
  public void testShowBusinessDetailsWhenClickOnCaseInCaseList() {
    updateGlobalVariable(Variable.CASE_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_CASE_LIST.getKey(), ACCESS_BUSINESS_DETAILS);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    caseWidgetPage.clickOnCase(ORDER_PIZZA);
    AdditionalCaseDetailsPage additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    // show business details
    assertTrue(additionalCaseDetailsPage.isDisplayed());
    redirectToNewDashBoard();
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(ORDER_PIZZA);
    resultPage.openCaseTab();
    String firstCaseName = resultPage.getNameOfCase(0);
    assertEquals(ORDER_PIZZA, firstCaseName);
    resultPage.clickOnCase(0);
    additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    assertTrue(additionalCaseDetailsPage.isDisplayed());

    // show business details of related case
    redirectToNewDashBoard();
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsViaAction(0);
    caseDetailsPage.clickOnRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    assertTrue(additionalCaseDetailsPage.isDisplayed());
  }
}
