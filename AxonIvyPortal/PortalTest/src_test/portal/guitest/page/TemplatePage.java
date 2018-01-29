package portal.guitest.page;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

import portal.guitest.common.UrlHelpers;
import ch.xpertline.base.pages.AbstractPage;

public abstract class TemplatePage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('logo')";
  public static final String CLASS_PROPERTY = "class";
  protected static final String ENGINE_URL_LOCAL = "http://localhost:8081/ivy";

  public TemplatePage() {
    // instead of using waitForPageLoaded(), wait for displaying instead of waiting for presenting
    String engineUrl = System.getProperty("engineUrl");
    if (ENGINE_URL_LOCAL.equals(engineUrl)) {
        waitForElementDisplayed(getLoadedLocator(), true, 300L);
    } else {
        waitForElementDisplayed(getLoadedLocator(), true, 30L);
    }
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
    clickOnLogo();
    boolean hasLeaveButton = getDriver().findElements(By.id("task-leave-warning-component:leave-button")).size() > 0;

    if (hasLeaveButton) {
      WebElement leaveButton = findElementById("task-leave-warning-component:leave-button");
      leaveButton.click();
    }

    waitForPageLoaded();
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
  
  public ChangePasswordPage openChangePasswordPage() {
    clickUserMenuItem("change-password-menu-item");
    return new ChangePasswordPage();
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
    WebElement mainMenuToggle = findDisplayedElementBySelector(".js-left-sidebar-toggle");
    if (!isMainMenuOpen()) {
      click(mainMenuToggle);
    }
    return new MainMenuPage();
  }
  
  public void clickOnLogo() {
    click(By.id("logo"));
    waitAjaxIndicatorDisappear();
  }

  public WebElement findDisplayedElementById(String id) {
    waitForElementDisplayed(By.id(id), true);
    WebElement element = findElementById(id);
    return element;
  }

  public WebElement findDisplayedElementBySelector(String selector) {
    waitForElementDisplayed(By.cssSelector(selector), true);
    WebElement element = findElementByCssSelector(selector);
    return element;
  }

  public void closeMainMenu() {
    WebElement mainMenuToggle = findDisplayedElementBySelector(".js-left-sidebar-toggle");
    if (isMainMenuOpen()) {
      click(mainMenuToggle);
      waitForPageLoaded(2);
    }
  }

  public void createTestingTasksInNewWindow() {
    Set<String> windows = driver.getWindowHandles();
    String hompageHandle = driver.getWindowHandle();

    ((JavascriptExecutor) driver).executeScript("window.open();");
    Set<String> newWindows = driver.getWindowHandles();

    newWindows.removeAll(windows);
    String newPageHandle = ((String) newWindows.toArray()[0]);

    String createTestingTasksUrl =
        UrlHelpers.generateAbsoluteProcessStartLink("internalSupport/14B2FC03D2E87141/CategoriedLeaveRequest.ivp");
    driver.switchTo().window(newPageHandle);
    driver.get(createTestingTasksUrl);

    driver.switchTo().window(hompageHandle);
  }

  public boolean isMainMenuOpen() {
    WebElement mainMenu = findDisplayedElementBySelector(".js-left-sidebar-toggle");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("in") > 0;
  }

  public TaskWidgetPage openTaskList() {
    openMainMenu();
    WebElement taskListToggle = findListElementsByCssSelector("a.left-sidebar-sub-menu-item").get(1);
    taskListToggle.click();
    waitForElementPresent(By.cssSelector("div.js-task-list-container"), true);
    return new TaskWidgetPage();
  }

  public CasePage openCaseList() {
    openMainMenu();
    WebElement caseListToggle = findListElementsByCssSelector("a.left-sidebar-sub-menu-item").get(2);
    caseListToggle.click();
    waitForElementPresent(By.cssSelector("div.js-case-default-widget-container"), true);
    return new CasePage();
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
      waitForElementDisplayed(getSearchContainer(), true);
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
    }

    public void startProcessOnGlobalSearch(String name) {
      WebElement processResult = getProcessResultContainer();
      WebElement webElement = findChildElementByLinkText(processResult, name);
      click(webElement);
      Sleeper.sleepTight(5000);
    }

    public SearchResultPage startTaskOnGlobalSearch(String name) {
      WebElement taskResult = getTaskResultContainer();
      WebElement webElement = findChildElementByLinkText(taskResult, name);
      click(webElement);
      Sleeper.sleepTight(5000);
      return new SearchResultPage("task");
    }

    public CasePage startCaseOnGlobalSearch(String name) {
      WebElement caseResult = getCaseResultContainer();
      WebElement webElement = findChildElementByLinkText(caseResult, name);
      click(webElement);
      return new CasePage();
    }
  }
}
