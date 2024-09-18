package com.axonivy.portal.selenium.test.caze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;


@IvyWebTest
public class CaseWidgetCustomizationTest extends BaseTest {
  private static final String CUSTOMER_NAME_CASE_LIST_HEADER_ID = "case-widget:customer-name-column-header:customer-name-column-header";
  private static final String STATE_COLUMN_HEADER = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";
  private static final String CUSTOMER_NAME_COLUMN_VALUE = "Customer name 0";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createCasesForCaseListCustomization);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
  }



  @Test
  public void testEnableAndDisableColumnsInCaseWidget() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    caseWidgetPage.clickColumnsButton();
    caseWidgetPage.clickDefaultCheckbox();
    caseWidgetPage.clickColumnCheckboxByName(CUSTOMER_NAME_COLUMN_HEADER);
    caseWidgetPage.clickApplyButton();
    assertTrue(!caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    caseWidgetPage.clickColumnsButton();
    caseWidgetPage.clickColumnCheckboxByName(CUSTOMER_NAME_COLUMN_HEADER);
    caseWidgetPage.clickApplyButton();
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
  }
  
  @Test
  public void testShowHideCustomColumnsInCaseWidget() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
    caseWidgetPage.sortCaseListByColumn(CUSTOMER_NAME_CASE_LIST_HEADER_ID);
    assertFalse(caseWidgetPage.isCaseListColumnExist(STATE_COLUMN_HEADER));
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertEquals(CUSTOMER_NAME_COLUMN_VALUE, caseWidgetPage.getCaseListFirstCustomCellValue());
  }
  
  @Test
  public void testSortCustomColumnsInCaseListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
    caseWidgetPage.sortCaseListByColumn(CUSTOMER_NAME_CASE_LIST_HEADER_ID);
    assertEquals(CUSTOMER_NAME_COLUMN_VALUE, caseWidgetPage.getCaseListFirstCustomCellValue());
  }
}
