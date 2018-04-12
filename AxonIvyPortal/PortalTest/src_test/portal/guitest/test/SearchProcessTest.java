package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
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

    globalSearch.inputSearchKeyword("Employee Leave Request");

    homePage.waitAjaxIndicatorDisappear();

    String processName = "Employee Leave Request";
    assertEquals(processName, globalSearch.getProcessResult());
    
    globalSearch.startProcessOnGlobalSearch(processName);
    TaskTemplatePage taskPage = new TaskTemplatePage();
    assertTrue(taskPage.getPageUrl().contains("ProcessLeaves2"));
  }
  
  @Test
  public void testFindCaseMapByName() {
    String caseMapName = "Case Map Leave";
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.inputSearchKeyword(caseMapName);

    homePage.waitAjaxIndicatorDisappear();

    assertEquals(caseMapName, globalSearch.getProcessResult());
  }

  @Test
  public void testFindNotFoundOutData() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.inputSearchKeyword("no process found");

    homePage.waitAjaxIndicatorDisappear();
    WebElement processResult = globalSearch.getEmptySearchResult();

    assertEquals("No records found.", processResult.getText());
  }
}
