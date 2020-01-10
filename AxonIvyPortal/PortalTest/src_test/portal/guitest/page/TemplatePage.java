package portal.guitest.page;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
import portal.guitest.common.SystemProperties;
import portal.guitest.common.UrlHelpers;
import vn.wawa.guitest.base.page.AbstractPage;

public abstract class TemplatePage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('global-search-component:global-search-data')";
  public static final String CLASS_PROPERTY = "class";
  private static final String ENGINE_URL_LOCAL = "http://localhost:8081/ivy";

  public TemplatePage() {
    waitForLocatorDisplayed(getLoadedLocator());
  }

  protected long getTimeOutForLocator() {
    if (!SystemProperties.isInServerMode()) {
      return 10L;
    } else {
      return 100L;
    }
  }

  protected void waitForLocatorDisplayed(String locator) {
    // instead of using waitForPageLoaded(), wait for displaying instead of waiting for presenting
    if (isIntegrationTestRun()) {
      waitForElementDisplayed(locator, true, getTimeOutForLocator() + 200L);
    } else {
      waitForElementDisplayed(locator, true, getTimeOutForLocator());
    }
  }


  @Override
  public <T> void waitForElementDisplayed(T locator, boolean expected, long timeout) {
    Awaitility.await().atMost(new Duration(timeout, TimeUnit.SECONDS)).until(() -> {
      try {
        super.waitForElementDisplayed(locator, expected, timeout);
        return;
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting, try again.");
        e.printStackTrace();
      }
    });
  }

  protected boolean isIntegrationTestRun() {
    String engineUrl = System.getProperty("engineUrl");
    return ENGINE_URL_LOCAL.equals(engineUrl);
  }

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
      System.out.println("Error when ensuring not background request");
      e.printStackTrace();
    }
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }

  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator-ajax-indicator_start");
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

  public LanguagePage openLanguagePage() {
    clickUserMenuItem("language-setting-menu-item");
    return new LanguagePage();
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
        UrlHelpers.generateAbsoluteProcessStartLink("portalExamples/162511D2577DBA88/CategoriedLeaveRequest.ivp");
    driver.switchTo().window(newPageHandle);
    driver.get(createTestingTasksUrl);

    driver.switchTo().window(hompageHandle);
  }

  public boolean isMainMenuOpen() {
    WebElement mainMenu = findDisplayedElementByCssSelector(".layout-wrapper");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("static") > 0;
  }

  public TaskWidgetPage openTaskList() {
    openMainMenu();
    clickByCssSelector("li.submenu-container:nth-child(3) > a.ripplelink.submenu");
    Sleeper.sleep(5000);
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    return openMainMenu().selectCaseMenu();
  }

  public String getGlobalGrowlMessage() {
    return findElementById("portal-global-growl_container").getText();
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
      type(getSearchInput(), keyword + Keys.ENTER);
      return new SearchResultPage();
    }
  }
  
  public void clickByCssSelector(String cssSelector) {
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
    click(By.cssSelector(cssSelector));
  }

  protected void refreshAndWaitElement(String cssSelector) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> {
      if ((findListElementsByCssSelector(cssSelector).isEmpty())) {
        refresh();
        return false;
      } else {
        return true;
      }
    });
  }
 
}
