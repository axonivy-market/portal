package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseWidgetCustomizationTest extends BaseTest {

  private static final String CUSTOMER_NAME_CASE_LIST_HEADER_ID = "case-widget:customer-name-column-header:customer-name-column-header";
  private static final String STATE_COLUMN_HEADER = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";
  private static final String CUSTOMER_NAME_COLUMN_VALUE = "Customer name 11";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createCasesForCaseListCustomization);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
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
  public void testEnableAndDisableColumnsInCaseWidget() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    caseWidgetPage.clickColumnsButton();
    caseWidgetPage.clickDefaultCheckbox();
    caseWidgetPage.clickColumnCheckbox(6);
    caseWidgetPage.clickApplyButton();
    WaitHelper.assertTrueWithWait(() -> !caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    caseWidgetPage.clickColumnsButton();
    caseWidgetPage.clickColumnCheckbox(6);
    caseWidgetPage.clickApplyButton();
    WaitHelper.assertTrueWithWait(() -> caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
  }

  @Test
  public void testSortCustomColumnsInCaseListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
    caseWidgetPage.sortCaseListByColumn(CUSTOMER_NAME_CASE_LIST_HEADER_ID);
    assertEquals(CUSTOMER_NAME_COLUMN_VALUE, caseWidgetPage.getCaseListFirstCustomCellValue());
  }
}
