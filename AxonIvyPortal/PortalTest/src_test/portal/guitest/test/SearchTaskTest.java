package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchTaskTest extends BaseTest {
  private HomePage homePage;

  @Override
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
    
    String taskName = "Annual Leave Request";
    globalSearch.inputSearchKeyword(taskName);
    
    homePage.waitAjaxIndicatorDisappear();

    assertEquals(1, globalSearch.countFoundTasks());

    int numberOfTasks = globalSearch.countFoundTasks();
    SearchResultPage resultPage = globalSearch.startTaskOnGlobalSearch(taskName);
    assertEquals(numberOfTasks, resultPage.countTasks());
    assertTrue(resultPage.isTaskItemSelected(0));
  }
}
