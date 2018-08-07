package portal.page;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import portal.common.UrlHelpers;
import ch.xpertline.base.pages.AbstractPage;

public abstract class TemplatePage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('logo')";

  public TemplatePage() {
    waitForPageLoaded();
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }

  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator_start");
    if (ajaxIndicatorStartState.isDisplayed()) {
      waitForElementDisplayed(ajaxIndicatorStartState, false);
    }
  }

  public <T> void waitForElementDisplayed(T locator, boolean expected) {
    waitForElementDisplayed(locator, expected, DEFAULT_TIMEOUT);
  }

  public <T> void waitForElementPresent(T locator, boolean expected) {
    waitForElementPresent(locator, expected, DEFAULT_TIMEOUT);
  }

  public int countBrowserTab() {
    return driver.getWindowHandles().size();
  }

  public HomePage goToHomePage() {
    click(By.id("logo"));
    return new HomePage();
  }

  public void switchLastBrowserTab() {
    String oldTab = driver.getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    tabs.remove(oldTab);
    driver.switchTo().window(tabs.get(0));
  }

  public void navigateBack() {
    driver.navigate().back();
  }

  public AdminSettingsPage openAdminSettings() {
    clickUserMenuItem("adminui-menu-item");
    return new AdminSettingsPage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }

  private void clickUserMenuItem(String menuItemSelector) {
    waitForElementDisplayed(By.id("user-settings-menu"), true);
    findElementById("user-settings-menu").click();
    waitForElementDisplayed(By.id(menuItemSelector), true);
    findElementById(menuItemSelector).click();
    waitAjaxIndicatorDisappear();
  }

  public boolean isAdminSettingsMenuItemPresent() {
    return isElementPresent("id('adminui-menu-item')");
  }

  public MainMenuPage openMainMenu() {
    WebElement mainMenuSwitcher = findElementById("js-main-menu-switcher");
    if (!isMainMenuOpen()) {
      click(mainMenuSwitcher);
    }
    return new MainMenuPage();
  }

  public void closeMainMenu() {
    WebElement mainMenuSwitcher = findElementById("js-main-menu-switcher");
    if (isMainMenuOpen()) {
      click(mainMenuSwitcher);
      waitForElementDisplayed(By.id("js-main-menu-panel"), false);
    }
  }
  
  public void createTestingTasksInNewWindow() {
    Set<String> windows = driver.getWindowHandles();
    String hompageHandle = driver.getWindowHandle();

    ((JavascriptExecutor) driver).executeScript("window.open();");
    Set<String> newWindows = driver.getWindowHandles();

    newWindows.removeAll(windows);
    String newPageHandle = ((String) newWindows.toArray()[0]);

    String createTestingTasksUrl = UrlHelpers.generateAbsoluteProcessStartLink("internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp");
    driver.switchTo().window(newPageHandle);
    driver.get(createTestingTasksUrl);

    driver.switchTo().window(hompageHandle);
  }

  public boolean isMainMenuOpen() {
    return isElementDisplayed(By.id("js-main-menu-panel"));
  }

  public GlobalSearch getGlobalSearch() {
    return new GlobalSearch();
  }

  public class GlobalSearch {
    private static final String GLOBAL_SEARCH_RESULT_CONTAINER_ELEMENT_ID = "global-search-result-container";
    private static final String GLOBAL_SEARCH_DATA_ELEMENT_ID = "global-search-data";
    private static final String GLOBAL_SEARCH_CONTAINER_ELEMENT_ID = "global-search-container";
    private static final String GLOBAL_SEARCH_ELEMENT_ID = "global-search";
    private static final String EMPTY_SEARCH_RESULT_ELEMENT_ID = "empty-search-result";
    private static final String GLOBAL_TASK_RESULT_ELEMENT_ID = "global-task-result";
    private static final String GLOBAL_PROCESS_RESULT_ELEMENT_ID = "global-process-result";
    private static final String GLOBAL_CASE_RESULT_ELEMENT_ID = "global-case-result";
    private WebElement searchWebElement;

    private String keyword;

    public GlobalSearch() {
      final String SELECT_PARENT_NODE_XPATH = "..";
      searchWebElement = findElementById(GLOBAL_SEARCH_ELEMENT_ID).findElement(By.xpath(SELECT_PARENT_NODE_XPATH));
    }

    public boolean isDisplayed() {
      return searchWebElement.isDisplayed();
    }

    public WebElement getSearch() {
      return searchWebElement;
    }

    public void clickOnGlobalSearchIcon() {
      click(getGlobalSearchIcon());
    }

    public WebElement getGlobalSearchIcon() {
      return findChildElementById(searchWebElement, GLOBAL_SEARCH_ELEMENT_ID);
    }

    public WebElement getSearchContainer() {
      return findChildElementById(searchWebElement, GLOBAL_SEARCH_CONTAINER_ELEMENT_ID);
    }

    public WebElement getSearchInputData() {
      return findChildElementById(searchWebElement, GLOBAL_SEARCH_DATA_ELEMENT_ID);
    }

    public WebElement getSearchResultContainer() {
      return findChildElementById(searchWebElement, GLOBAL_SEARCH_RESULT_CONTAINER_ELEMENT_ID);
    }

    public WebElement getProcessResultContainer() {
      waitForElementPresent(By.id(GLOBAL_PROCESS_RESULT_ELEMENT_ID), true);
      return findChildElementById(searchWebElement, GLOBAL_PROCESS_RESULT_ELEMENT_ID);
    }

    public String getProcessResult() {
      return getProcessResultContainer().findElement(By.tagName("a")).getText();
    }

    public int countFoundProcesses() {
      return getProcessResultContainer().findElements(By.tagName("a")).size();
    }

    public WebElement getTaskResultContainer() {
      waitForElementPresent(By.id(GLOBAL_TASK_RESULT_ELEMENT_ID), true);
      return findChildElementById(searchWebElement, GLOBAL_TASK_RESULT_ELEMENT_ID);
    }

    public int countFoundTasks() {
      return getTaskResultContainer().findElements(By.tagName("a")).size();
    }

    public WebElement getCaseResultContainer() {
      waitForElementPresent(By.id(GLOBAL_CASE_RESULT_ELEMENT_ID), true);
      return findChildElementById(searchWebElement, GLOBAL_CASE_RESULT_ELEMENT_ID);
    }

    public String getCaseResult() {
      return getCaseResultContainer().findElement(By.tagName("a")).getText();
    }

    public int countFoundCases() {
      return getCaseResultContainer().findElements(By.tagName("a")).size();
    }

    public WebElement getEmptySearchResult() {
      waitForElementPresent(By.id(EMPTY_SEARCH_RESULT_ELEMENT_ID), true);
      return findChildElementById(searchWebElement, EMPTY_SEARCH_RESULT_ELEMENT_ID);
    }

    public void inputSearchKeyword(String keyword) {
      type(getSearchInputData(), keyword);
      this.keyword = keyword;
    }

    public SearchResultPage startProcessOnGlobalSearch(String name) {
      WebElement processResult = getProcessResultContainer();
      WebElement webElement = findChildElementByLinkText(processResult, name);
      click(webElement);
      return new SearchResultPage("Process", keyword);
    }

    public SearchResultPage startTaskOnGlobalSearch(String name) {
      WebElement taskResult = getTaskResultContainer();
      WebElement webElement = findChildElementByLinkText(taskResult, name);
      click(webElement);
      return new SearchResultPage("Task", keyword);
    }

    public SearchResultPage startCaseOnGlobalSearch(String name) {
      WebElement caseResult = getCaseResultContainer();
      WebElement webElement = findChildElementByLinkText(caseResult, name);
      click(webElement);
      return new SearchResultPage("Case", keyword);
    }
  }
}
