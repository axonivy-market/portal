package com.axonivy.portal.selenium.test.caze;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class CaseFilterTest extends BaseTest {

  private static final String EMPTY = "";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testFilterCasesByCreatedDate() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    caseWidgetPage.filterCasesByCreatedDate(fromInputText, EMPTY);
    caseWidgetPage.countCases().shouldHave(sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testFilterCasesByApplication() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    int before = caseWidgetPage.countCases().size();
    caseWidgetPage.openAdvancedFilter("Application", "application");
    caseWidgetPage.filterFirstApp();
    int after = caseWidgetPage.countCases().size();
    Assertions.assertEquals(before, after);
  }

  @Test
  public void testCaseOwnerFilter() {
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "true");
    redirectToRelativeLink(userIsOwnerUrl);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Owner", "owner");
    casePage.filterByOwner("Demo");
    casePage.getCasesList().shouldHave(size(1), DEFAULT_TIMEOUT);
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "false");
  }

  @Test
  public void testCaseAdvancedFilter() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("random text");
    casePage.getCasesList().shouldHave(size(0), DEFAULT_TIMEOUT);
    casePage.filterByDescription("Leave Request Description");
    casePage.getCasesList().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

  @Test
  public void testSaveFilter() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
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
  public void testCategory() {
    login(TestAccount.DEMO_USER);
    CaseWidgetPage casePage = NavigationHelper.navigateToCaseList();

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
    login(TestAccount.DEMO_USER);
    // Prepare 2 filter
    String filterResponsible = "Responsible";
    String filterMaternity = "Maternity";

    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage casePage = mainMenuPage.openCaseList();
    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription(filterMaternity);
    casePage.saveFilter(filterMaternity);

    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    mainMenuPage.waitPageLoaded();
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
    login(TestAccount.DEMO_USER);
    CaseWidgetPage casePage = NavigationHelper.navigateToCaseList();
    assertTrue(casePage.getFilterName().contains("Default filter"));
  }

  @Test
  public void testNoSelectionWhenChangeFilter() {
    login(TestAccount.DEMO_USER);
    String filterMaternity = "Maternity";
    CaseWidgetPage casePage = NavigationHelper.navigateToCaseList();
    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription(filterMaternity);
    casePage.saveFilter(filterMaternity);
    casePage.openAdvancedFilter("Creator", "creator");
    casePage.filterByCreator("Demo");

    assertTrue(casePage.getFilterName().contains("No Selection"));
  }
  
  @Test
  public void testSaveCaseFilterOnDifferentCaseList() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    String filterName = "MyFilter";

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("Sick");
    casePage.saveFilter(filterName);

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    mainMenuPage = new MainMenuPage();
    casePage = mainMenuPage.selectCaseMenu();

    assertTrue(casePage.getFilterName().contains("Default filter"));

    String secondFilterName = "MyFilter2";
    casePage.openAdvancedFilter("Creator", "creator");
    casePage.filterByCreator("Demo");
    casePage.saveFilter(secondFilterName);

    mainMenuPage.selectTaskMenu();
    casePage = mainMenuPage.openCaseList();
    casePage.openSavedFilters(secondFilterName);

    assertEquals(secondFilterName, casePage.getFilterName()); 
  }

}
