package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class CaseWidgetCustomizationTest extends BaseTest {

	private static final String CREATE_CASES_FOR_CASE_LIST_CUSTOMIZATION =
			"portalExamples/162511D2577DBA88/createCasesForCaseListCustomization.ivp";
	private static final String CUSTOMER_NAME_CASE_LIST_HEADER_ID = 
	    "case-widget:customVarCharField1-column-header:customVarCharField1-column-header";
	private static final String CUSTOMER_NAME_HEADER_TEXT_ID = 
	    "case-widget:case-list-scroller:0:case-item:case-customer-name-component:customVarCharField1-column-case-header-text";
	private static final String STATE_COLUMN_HEADER = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";

	@Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(CREATE_CASES_FOR_CASE_LIST_CUSTOMIZATION);
		redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
		LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
		loginPage.login();
	}
	
	@Test
	public void testShowHideCustomColumnsInCaseWidget() {
	  MainMenuPage mainMenuPage = new MainMenuPage();
	  CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
	  caseWidgetPage.sortCaseListByColumn(CUSTOMER_NAME_CASE_LIST_HEADER_ID);
	  assertFalse(caseWidgetPage.isCaseListColumnExist(STATE_COLUMN_HEADER));
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("CustomVarcharField10".equals(caseWidgetPage.getCaseListCustomCellValue(CUSTOMER_NAME_HEADER_TEXT_ID)));
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
    assertFalse(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    caseWidgetPage.clickColumnsButton();
    caseWidgetPage.clickColumnCheckbox(6);
    caseWidgetPage.clickApplyButton();
    assertTrue(caseWidgetPage.isCaseListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
	}

	@Test
	public void testSortCustomColumnsInCaseListPage() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
		caseWidgetPage
				.sortCaseListByColumn(CUSTOMER_NAME_CASE_LIST_HEADER_ID);
		assertTrue("CustomVarcharField10"
				.equals(caseWidgetPage
						.getCaseListCustomCellValue(CUSTOMER_NAME_HEADER_TEXT_ID)));
	}
}
