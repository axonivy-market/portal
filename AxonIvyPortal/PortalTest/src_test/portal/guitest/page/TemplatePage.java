package portal.guitest.page;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;
import portal.guitest.common.UrlHelpers;
import portal.guitest.common.WaitHelper;
import vn.wawa.guitest.base.page.AbstractPage;

public abstract class TemplatePage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('global-search-component:global-search-data')";
  public static final String CLASS_PROPERTY = "class";
  private static final String HOME_BREADCRUMB_SELECTOR = ".portal-breadcrumb .ui-menuitem-link:first-child";
  public static final String CURRENT_BREADCRUMB_SELECTOR = ".portal-breadcrumb li:last-child .ui-menuitem-link.ui-state-disabled";

  public TemplatePage() {
    waitForLocatorDisplayed(getLoadedLocator());
  }

  //If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 45L;
  }

  protected void waitForLocatorDisplayed(String locator) {
    waitForElementDisplayed(locator, true, getTimeOutForLocator());
  }


  @Override
  public <T> void waitForElementDisplayed(T locator, boolean expected, long timeout) {
    Awaitility.await().atMost(new Duration(timeout, TimeUnit.SECONDS)).until(() -> {
      try {
        super.waitForElementDisplayed(locator, expected, timeout);
        return;
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element displayed, try again.");
      }
    });
  }

  public void waitForElementExisted(String cssSelector, boolean expected, long timeout) {
    Awaitility.await().atMost(new Duration(timeout, TimeUnit.SECONDS)).until(() -> {
      try {
        return (findListElementsByCssSelector(cssSelector).size() != 0) == expected;
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element existed, try again.");
      }
      return false;
    });
  }
  
  protected boolean isIntegrationTestRun() {
    return StringUtils.isNotEmpty(System.getProperty("test.engine.url"));
  }

  /**
   * This method cannot make sure UI is updated as expected. Instead of using this method, wait for expected UI, e.g. buttons appear, label changed...
   * In case you really want to use it, ask your team first. 
   */
  @Deprecated
  protected void ensureNoBackgroundRequest() {
    ensureNoBackgroundRequest(500, 30);
  }

  private void ensureNoBackgroundRequest(int minMilliSeconds, int timeOutInSeconds) {
    WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds, 200);
    final long startTime = System.currentTimeMillis();
    Function<WebDriver, Boolean> myFunction = webDriver -> {
      if (System.currentTimeMillis() - startTime < minMilliSeconds) {
        return false;
      }
      Object ajaxQueueIsEmpty =
          ((JavascriptExecutor) getDriver()).executeScript("return PrimeFaces.ajax.Queue.isEmpty()");
      if (Boolean.TRUE.toString().equalsIgnoreCase(String.valueOf(ajaxQueueIsEmpty))) {
        return true;
      }
      return false;
    };
    try {
      wait.until(myFunction);
    } catch (WebDriverException e) {
      e.printStackTrace();
      System.out.println("ERROR when ensuring not background request");
    }
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }

  /**
   * This method cannot make sure UI is updated as expected. Instead of using this method, wait for expected UI, e.g. buttons appear, label changed...
   * In case you really want to use it, ask your team first. 
   */
  @Deprecated
  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState;
    try {
      ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator-ajax-indicator_start");
    } catch (Exception e2) {
      System.out.println("ERROR waitAjaxIndicatorDisappear, maybe page is reloading");
      return;
    }
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
    waitForElementDisplayed(locator, expected, getTimeOutForLocator());
  }

  public <T> void waitForElementPresent(T locator, boolean expected) {
    waitForElementPresent(locator, expected, getTimeOutForLocator());
  }

  public int countBrowserTab() {
    return driver.getWindowHandles().size();
  }

  public void switchLastBrowserTab() {
    String oldTab = driver.getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    tabs.remove(oldTab);
    driver.switchTo().window(tabs.get(0));
  }

  public AdminSettingsPage openAdminSettings() {
    clickUserMenuItem("adminui-menu-item");
    waitAjaxIndicatorDisappear();
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

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    return new UserProfilePage();
  }
  
  public void clickOnMyProfile() {
    clickUserMenuItem("user-profile");
    waitAjaxIndicatorDisappear();
  }

  public ProjectVersionPage openProjectVersionPage() {
    clickUserMenuItem("project-info-menu-item");
    return new ProjectVersionPage();
  }

  private void clickUserMenuItem(String menuItemSelector) {
    waitForElementDisplayed(By.id("user-settings-menu"), true);
    click(findElementById("user-settings-menu"));
    waitForElementDisplayed(By.id(menuItemSelector), true);
    click(findElementById(menuItemSelector));
    waitAjaxIndicatorDisappear();
  }

  public boolean isAdminSettingsMenuItemPresent() {
    return isElementPresent("id('adminui-menu-item')");
  }

  public boolean isElementDisplayedById(String id) {
    try {
      findElementById(id);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  @Override
  public <T> boolean isElementDisplayed(T locator) {
    try {
      return super.isElementDisplayed(locator);
    } catch (Exception e) {
      return false;
    }
  }

  public MainMenuPage openMainMenu() {
    WebElement mainMenuToggle = findDisplayedElementByCssSelector("#left-menu");
    if (!isMainMenuOpen()) {
      click(mainMenuToggle);
      click(By.xpath("//a[@id='user-menu-required-login:toggle-menu']"));
    }
    return new MainMenuPage();
  }

  public void clickOnLogo() {
    openMainMenu();
    clickByCssSelector("a[id$='logo']");
    waitAjaxIndicatorDisappear();
  }

  public WebElement findDisplayedElementByCssSelector(String selector) {
    waitForElementDisplayed(By.cssSelector(selector), true);
    WebElement element = findElementByCssSelector(selector);
    return element;
  }

  public void closeMainMenu() {
    findDisplayedElementByCssSelector("#left-menu");
    if (isMainMenuOpen()) {
      click(By.cssSelector("a[id$='toggle-menu']"));
      click(By.id("top-menu"));
      waitForElementDisplayed(By.cssSelector("a[id$='logo-small']"), true);
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
        UrlHelpers.generateAbsoluteProcessStartLink("portal-developer-examples/162511D2577DBA88/CategoriedLeaveRequest.ivp");
    driver.switchTo().window(newPageHandle);
    driver.get(createTestingTasksUrl);

    driver.switchTo().window(hompageHandle);
  }

  public boolean isMainMenuOpen() {
    WebElement mainMenu = findDisplayedElementByCssSelector(".layout-wrapper");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("static") > 0;
  }

  public TaskWidgetPage openTaskList() {
    waitForPageLoaded();
    openMainMenu().selectTaskMenu();
    waitForElementDisplayed(By.cssSelector("[id$='task-config-command']"), true);
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    return openMainMenu().selectCaseMenu();
  }

  public String getGlobalGrowlMessage() {
    return findElementById("portal-global-growl_container").getText();
  }
  
  public void waitForGrowlMessageDisplayClearly() {
    waitForElementDisplayed(By.id("portal-global-growl_container"), true);
    Sleeper.sleep(500);//wait for animation finish to capture screenshot
  }

  public GlobalSearch getGlobalSearch() {
    return new GlobalSearch();
  }

  public class GlobalSearch {

    private static final String GLOBAL_SEARCH_INPUT_SELECTOR = "#global-search-component\\:global-search-data";

    public GlobalSearch() {
    }

    private WebElement getSearchInput() {
      waitForElementDisplayed(By.cssSelector(GLOBAL_SEARCH_INPUT_SELECTOR), true);
      return findElementByCssSelector(GLOBAL_SEARCH_INPUT_SELECTOR);
    }

    public boolean isDisplayed() {
      return getSearchInput().isDisplayed();
    }

    public SearchResultPage inputSearchKeyword(String keyword) {
      click(By.cssSelector(GLOBAL_SEARCH_INPUT_SELECTOR));
      WaitHelper.typeWithRetry(new AbstractPage() {
        @Override
        protected String getLoadedLocator() {
          return TEMPLATE_PAGE_LOCATOR;
        }
      }, GLOBAL_SEARCH_INPUT_SELECTOR, keyword);
      getSearchInput().sendKeys(Keys.ENTER.toString());
      try {
        waitForElementDisplayed(By.id("search-results-tabview"), true);
      } catch (Exception e) {
        System.out.println("Exception when waiting for search page displayed, press Enter again.");
        getSearchInput().sendKeys(Keys.ENTER.toString());
      }
      return new SearchResultPage();
    }
    
    public boolean isPresent() {
      return isElementPresent(By.cssSelector(GLOBAL_SEARCH_INPUT_SELECTOR));
    }
  }
  
  public void clickByCssSelector(String cssSelector) {
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
    click(By.cssSelector(cssSelector));
  }

  protected void refreshAndWaitElement(String cssSelector) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> {
      if ((findListElementsByCssSelector(cssSelector).isEmpty())) {
        WaitHelper.waitForNavigation(this, () -> refresh());
        return false;
      } else {
        return true;
      }
    });
  }

  public HomePage goToHomeFromBreadcrumb() {
    waitForElementDisplayed(By.cssSelector(HOME_BREADCRUMB_SELECTOR), true);
    click(By.cssSelector(HOME_BREADCRUMB_SELECTOR));
    return new HomePage();
  }
  
  public HomePage goToHomeFromBreadcrumbWithWarning() {
    waitForElementDisplayed(By.cssSelector(HOME_BREADCRUMB_SELECTOR), true);
    click(By.cssSelector(HOME_BREADCRUMB_SELECTOR));
    waitForElementDisplayed(By.id("user-menu-required-login:leave-button"), true);
    click(By.id("user-menu-required-login:leave-button"));
    return new HomePage();
  }
  
  public String getTextOfCurrentBreadcrumb() {
    WebElement breadcrumb = findElementByCssSelector(CURRENT_BREADCRUMB_SELECTOR);
    String result = "";
    if (CollectionUtils.isNotEmpty(breadcrumb.findElements(By.cssSelector(".js-count")))) {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML") + breadcrumb.findElement(By.cssSelector(".js-count")).getAttribute("innerHTML");
    } else {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML");
    }
    return result;
        
  }
  
  public String getLoggedInUserFormat() {
    return getText(By.id("user-settings-menu"));
  }

  public  ChatPage getChat() {
    waitForElementDisplayed(By.id("toggle-chat-panel-command"), true, 5);
    click(findElementById("toggle-chat-panel-command"));
    return new ChatPage();
  }  
  public WebElement getAbsenceManagementDialog() {
    return findElementById("absence-management-dialog");
  }
  
  public WebElement getUserSettings() {
    waitForElementDisplayed(By.id("user-settings-menu"), true);
    click(findElementById("user-settings-menu"));
    waitForElementDisplayed(By.id("logout-setting:logout-menu-item"), true);
    return findElementById("user-setting-container");
  }
  
  public WebElement getTopBar() {
    return findElementById("top-menu");
  }
  
  public void waitUntilLayoutWrapperDisplayed() {
    waitForElementDisplayed(By.className("layout-wrapper"), true);
  }
  
  public void waitUntilErrorMessageShowUp() {
    waitForElementDisplayed(By.className("notification-container"), true);
  }
  
  public void clickOnShowMoreLinkOfErrorMessages() {
    click(findElementByCssSelector("a[class$='notification-content-action-more-details']"));
  }
  
  public void waitUntilErrorContainerDisplayed() {
    waitForElementDisplayed(By.className("error-container"), true);
  }

  public boolean isWelcomeDialogExisted() {
    return CollectionUtils.isNotEmpty(findListElementsByCssSelector("div[id$='welcome-portal-guide']"));
  }
}
