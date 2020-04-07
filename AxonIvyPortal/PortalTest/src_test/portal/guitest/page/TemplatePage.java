package portal.guitest.page;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;
import portal.guitest.common.UrlHelpers;
import vn.wawa.guitest.base.page.AbstractPage;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public abstract class TemplatePage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('logo')";
  public static final String CLASS_PROPERTY = "class";

  public TemplatePage() {
    // instead of using waitForPageLoaded(), wait for displaying instead of waiting for presenting
    if (isIntegrationTestRun()) {
        waitForElementDisplayed(getLoadedLocator(), true, 600L);
    } else {
        waitForElementDisplayed(getLoadedLocator(), true, 60L);
    }
  }

  protected boolean isIntegrationTestRun() {
    String engineUrl = System.getProperty("test.engine.url");
    return engineUrl != null;
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }

  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator_start");
    boolean displayed = false;
    try {
      displayed = ajaxIndicatorStartState.isDisplayed();
    } catch (Exception e) {
      try {
        displayed = ajaxIndicatorStartState.isDisplayed();
      } catch (Exception e1) {
        System.out.println("Cannot check if ajax indicator is displayed");
      }
    }
    if (displayed) {
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
    boolean hasLeaveButton = false;
    try {
      hasLeaveButton = getDriver().findElements(By.id("task-leave-warning-component:leave-button")).size() > 0;
    } catch (NoSuchElementException e) {
      // This should not happen, but at least it happens when running preintegration test on ivy 7
    }

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
  
  public ProjectVersionPage openProjectVersionPage() {
    clickUserMenuItem("project-info-menu-item");
    return new ProjectVersionPage();
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
    if (!isMainMenuOpen()) {
      clickByCssSelector(".js-left-sidebar-toggle");
    }
    return new MainMenuPage();
  }
  
  public void clickOnLogo() {
    clickByCssSelector("a[id$='logo']");
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
    clickByCssSelector("span[class*='left-sidebar-sub-menu-item-1-0']");
    waitForElementPresent(By.cssSelector("div.js-task-list-container"), true);
    return new TaskWidgetPage();
  }
  
  public CasePage openCaseList() {
    openMainMenu();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
    .until(() -> findListElementsByCssSelector("a.left-sidebar-sub-menu-item").get(2).isDisplayed());
    WebElement caseListToggle = findListElementsByCssSelector("a.left-sidebar-sub-menu-item").get(2);
    caseListToggle.click();
    waitForElementPresent(By.cssSelector("div.js-case-default-widget-container"), true);
    return new CasePage();
  }
  
  public String getGlobalGrowlMessage() {
	Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
	.until(() -> findElementById("portal-global-growl_container").getText().length()>1);  
    return findElementById("portal-global-growl_container").getText();
  }

  public void clickByCssSelector(String cssSelector) {
      waitForElementDisplayed(By.cssSelector(cssSelector), true);
      click(By.cssSelector(cssSelector));
   }
  
  public void refreshAndWaitElement(String cssSelector) {
	Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> {
	  if ((findListElementsByCssSelector(cssSelector).isEmpty())) {
	     refresh();
	     return false;
	    } else {
	      return true;
	     }
	  });
	}
  
  public GlobalSearch getGlobalSearch() {
    return new GlobalSearch();
  }

  public class GlobalSearch {
    private static final String GLOBAL_SEARCH_RESULT_CONTAINER_ELEMENT_ID = "global-search-result-container";
    private static final String GLOBAL_SEARCH_DATA_ELEMENT_ID = "global-search-data";
    private static final String EMPTY_SEARCH_RESULT_ELEMENT_ID = "empty-search-result";
    private static final String GLOBAL_TASK_RESULT_ELEMENT_ID = "global-task-result";
    private static final String GLOBAL_PROCESS_RESULT_ELEMENT_ID = "global-process-result";
    private static final String GLOBAL_CASE_RESULT_ELEMENT_ID = "global-case-result";
    private WebElement searchWebElement;

    public GlobalSearch() {
      final String SELECT_PARENT_NODE_XPATH = "..";
      searchWebElement = findElementById(GLOBAL_SEARCH_DATA_ELEMENT_ID).findElement(By.xpath(SELECT_PARENT_NODE_XPATH));
    }

    public boolean isDisplayed() {
      return searchWebElement.isDisplayed();
    }

    public WebElement getSearch() {
      return searchWebElement;
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
      Sleeper.sleep(5000);
    }

    public SearchResultPage startTaskOnGlobalSearch(String name) {
      WebElement taskResult = getTaskResultContainer();
      WebElement webElement = findChildElementByLinkText(taskResult, name);
      click(webElement);
      Sleeper.sleep(5000);
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
