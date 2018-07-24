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
	public void testSortCustomColumnsInTaskListPage() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage caseWidgetPage = mainMenuPage.selectCaseMenu();
		caseWidgetPage.waitAjaxIndicatorDisappear();
		caseWidgetPage
				.sortCaseListByColumn("case-widget:customVarCharField1-column-case-list-header:customVarCharField1-column-case-list-header");
		assertTrue("CustomVarcharField10"
				.equals(caseWidgetPage
						.getCaseListCustomCellValue("case-widget:case-list-scroller:0:case-item:customVarCharField1-column-case-header-text")));
	}
}
