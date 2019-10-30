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
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchProcessTest extends BaseTest {

  private HomePage homePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }

  @Test
  public void testFindAndStartProcessByName() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    String processName = "Employee Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
    assertEquals(processName, searchResultPage.getProcessResult(processName));
    assertTrue(searchResultPage.isProcessGroupDisplay("E"));
    
    searchResultPage.startProcess(processName);
    TaskTemplatePage taskPage = new TaskTemplatePage();
    assertTrue(taskPage.getPageUrl().contains("ProcessLeaves2"));
  }
  
  @Test
  public void testFindCaseMapByName() {
    String caseMapName = "Case Map Leave";
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseMapName);
    assertEquals(caseMapName, searchResultPage.getProcessResult(caseMapName));
  }
}
