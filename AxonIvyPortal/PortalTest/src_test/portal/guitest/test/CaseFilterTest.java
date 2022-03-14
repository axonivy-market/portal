package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseFilterTest extends BaseTest {
	@Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(createTestingTasksUrl);
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
	}

	@Test
	public void testCaseAdvancedFilter() {
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		MainMenuPage mainMenuPage = homePage.openMainMenu();
		CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription("random text");
		assertEquals(0, casePage.getNumberOfCases());

		casePage.filterByDescription("Leave Request Description");
		assertEquals(1, casePage.getNumberOfCases());
	}

	@Test
	public void testSaveFilter() {
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		MainMenuPage mainMenuPage = homePage.openMainMenu();
		CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription("Leave");
		String filterName = "Leave";
		casePage.saveFilter(filterName);

		mainMenuPage.selectTaskMenu();
		casePage = mainMenuPage.openCaseList();
		assertEquals(filterName, casePage.getFilterName());
	}

	@Test
	public void testSaveCaseFilterOnDifferentCaseList() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

		String filterName = "MyFilter";

		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription("Sick");
		casePage.saveFilter(filterName);

		redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
		casePage = mainMenuPage.selectCaseMenu();

		assertTrue(casePage.getFilterName().contains("Default filter"));

		casePage.filterByDescription("Leave");
		casePage.saveFilter(filterName);

		mainMenuPage.selectTaskMenu();
		casePage = mainMenuPage.openCaseList();
		assertEquals(filterName, casePage.getFilterName());
	}

	@Test
	public void testCategory() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage casePage = mainMenuPage.openCaseList();

		String caseCategoryId = "case-category";
		casePage.openAdvancedFilter("Case category", caseCategoryId);
		assertEquals("Case category: All", casePage.getFilterValue(caseCategoryId + "-filter"));
		casePage.openCategoryFilter();
		assertTrue(casePage.isAllCategoriesSelected());

		casePage.toggleNoCategory();
		assertTrue(casePage.isAllCategoriesUnselected());

		casePage.applyCategoryFilter();
		assertFalse(StringUtils.equals("Case category: All", casePage.getFilterValue(caseCategoryId + "-filter")));
	}

	@Test
	public void testRemoveResponsibleAndSwitchFilter() {
		// Prepare 2 filter
		String filterResponsible = "Responsible";
		String filterMaternity = "Maternity";

		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage casePage = mainMenuPage.openCaseList();
		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription(filterMaternity);
		casePage.saveFilter(filterMaternity);

		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

		casePage = mainMenuPage.openCaseList();
		casePage.openAdvancedFilter("Creator", "creator");
		casePage.filterByCreator("Demo");
		casePage.saveFilter(filterResponsible);
		// Switch filter and remove responsible
		casePage.openSavedFilters(filterMaternity);
		casePage.openSavedFilters(filterResponsible);
		casePage.removeResponsibleFilter();
		casePage.openSavedFilters(filterMaternity);
		casePage.openSavedFilters(filterResponsible);

		assertTrue(casePage.getCreator().contains("Portal Demo User"));
	}

	@Test
	public void testDefaultFilter() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
		assertTrue(casePage.getFilterName().contains("Default filter"));
	}

	@Test
	public void testNoSelectionWhenChangeFilter() {
		String filterMaternity = "Maternity";
		MainMenuPage mainMenuPage = new MainMenuPage();
		CaseWidgetPage casePage = mainMenuPage.openCaseList();
		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription(filterMaternity);
		casePage.saveFilter(filterMaternity);
		casePage.openAdvancedFilter("Creator", "creator");
		casePage.filterByCreator("Demo");
		
		assertTrue(casePage.getFilterName().contains("No Selection"));
	}

  @Test
  public void testCreatedDateFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage casePage = mainMenuPage.openCaseList();
    String fromInputText = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    casePage.filterByCreatedDate(fromInputText);
    String expectedCreatedDateForm = casePage.getCreatedDateFrom();
    assertTrue(expectedCreatedDateForm.contains(fromInputText));
  }
  
  @Test
  public void testCaseIDFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage casePage = mainMenuPage.openCaseList();
    // Not found case with ID 99999999
    casePage.openAdvancedFilter("Case Id", "case-id");
    casePage.filterCasesByID("99999999");
    assertTrue(casePage.isResultEmpty());

    // Clean case ID filter
    casePage.removeCaseIdFilter();
    casePage.refresh();
    casePage.openAdvancedFilter("Case Id", "case-id");
    assertEquals("Case Id: All", casePage.getFilterValue("case-id-filter"));

    // Found one case with ID
    var caseID = casePage.getCaseId(0);
    casePage.filterCasesByID(caseID);
    assertEquals(1, casePage.getCaseCount().intValue());
  }
}
