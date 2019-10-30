package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
  public void testFindTaskByName() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());
    
    String taskName = "Annual Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(taskName);
    searchResultPage.openTaskTab();
    assertEquals(taskName, searchResultPage.getTaskResult(0));
  }
}
