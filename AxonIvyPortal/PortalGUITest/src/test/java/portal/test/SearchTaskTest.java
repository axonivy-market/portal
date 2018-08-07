package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.SearchResultPage;
import portal.page.TemplatePage.GlobalSearch;

public class SearchTaskTest extends BaseTest {
  private HomePage homePage;

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }

  @Test
  public void testFindTaskByNameAndOpenTaskList() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());
    
    globalSearch.clickOnGlobalSearchIcon();
    homePage.waitForElementDisplayed(globalSearch.getSearchContainer(), true);
    
    String taskName = "Annual Leave Request";
    globalSearch.inputSearchKeyword(taskName);
    
    homePage.waitAjaxIndicatorDisappear();

    assertEquals(1, globalSearch.countFoundTasks());

    int numberOfTasks = globalSearch.countFoundTasks();
    SearchResultPage resultPage = globalSearch.startTaskOnGlobalSearch(taskName);
    assertEquals(numberOfTasks, resultPage.countTasks());
    assertTrue(resultPage.isTaskItemSelected(0));
  }

  @Test
  public void testFindTaskByCustomVarchar() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();

    globalSearch.clickOnGlobalSearchIcon();
    String taskName = "Other Leave";

    globalSearch.inputSearchKeyword(taskName);

    assertEquals(2, globalSearch.countFoundTasks());
  }
}
