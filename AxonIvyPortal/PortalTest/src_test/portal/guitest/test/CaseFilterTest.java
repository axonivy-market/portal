package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.ENABLE_CASE_OWNER;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseFilterTest extends BaseTest {

  @Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(createTestingTasksUrl);
	}

  @Test
  public void testCaseOwnerFilter() {
    updatePortalSetting(ENABLE_CASE_OWNER.getKey(), "true");
    redirectToRelativeLink(userIsOwnerUrl);
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Owner", "owner");
    casePage.filterByOwner("Demo");
    assertEquals(1, casePage.getNumberOfCases());
    updatePortalSetting(ENABLE_CASE_OWNER.getKey(), "false");
  }

  @Test
  public void testCaseAdvancedFilter() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("random text");
    WaitHelper.assertTrueWithWait(() -> {
      return casePage.getNumberOfCases() == 0;
    });

    casePage.filterByDescription("Leave Request Description");
    WaitHelper.assertTrueWithWait(() -> {
      return casePage.getNumberOfCases() == 1;
    });
  }

	@Test
	public void testSaveFilter() {
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		MainMenuPage mainMenuPage = homePage.openMainMenu();
		CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

		casePage.openAdvancedFilter("Description", "description");
		casePage.filterByDescription("leave");
		String filterName = "leave";
		casePage.saveFilter(filterName);

		mainMenuPage.selectTaskMenu();
		casePage = mainMenuPage.openCaseList();
		assertEquals(filterName, casePage.getFilterName().toLowerCase());
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

		casePage = mainMenuPage.selectCaseMenu();
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
}
