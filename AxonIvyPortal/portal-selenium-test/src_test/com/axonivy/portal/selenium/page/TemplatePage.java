package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.webdriver;

import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.WebElementCondition;

public abstract class TemplatePage extends AbstractPage {
  private static final int IFRAME_SCREENSHOT_FILE_SIZE_AT_MINIMUM = 10000;
  protected static final String LAYOUT_WRAPPER = ".layout-wrapper";
  public static final String ID_PROPERTY = "id";
  public static final String CLASS_PROPERTY = "class";
  public static final String CURRENT_BREADCRUMB_SELECTOR =
      ".portal-breadcrumb li:last-child .ui-menuitem-link.ui-state-disabled";
  private static final String HOME_BREADCRUMB_SELECTOR = ".portal-breadcrumb .ui-menuitem-link:first-child";
  public static final String PORTAL_GLOBAL_GROWL_ID = "portal-global-growl_container";
  protected static final String COMPONENT_PAGE_LOCATOR = "//*[contains(@id,'theme-selection')]";

  // If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 45L;
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  public void assertPageTitle(String titleExpect) {
    webdriver().shouldHave(WebDriverConditions.title(titleExpect));
  }

  protected WebElementCondition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  protected WebElementCondition  clickable() {
    return and("should be clickable", visible, enabled);
  }

  public WebDriver driver = WebDriverRunner.getWebDriver();

  public void switchLastBrowserTab() {
    switchToDefaultContent();
    String oldTab = WebDriverRunner.getWebDriver().getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
    tabs.remove(oldTab);
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(0));
  }

  public void waitUntilElementToBeClickable(SelenideElement element) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.elementToBeClickable(element));
  }

  public void switchToIframeWithId(String id) {
    WebDriverRunner.getWebDriver().switchTo().frame($("iframe[id='" + id + "']"));
  }

  public void switchToIframeWithNameOrId(String value) {
    WebDriverRunner.getWebDriver().switchTo().frame(value);
  }

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").$("div.ui-growl-message").shouldBe(disappear, Duration.ofSeconds(45));
  }

  public void waitForElementDisplayed(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(appear, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementExisted(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(exist, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(Condition.not(exist), DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementEnabled(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(enabled, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(disabled, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementDisplayed(SelenideElement element, boolean expected) {
    if (expected) {
      element.shouldBe(appear, DEFAULT_TIMEOUT);
    } else {
      element.shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementDisplayed(By element, boolean expected, long timeout) {
    if (expected) {
      $(element).shouldBe(appear, Duration.ofSeconds(timeout));
    } else {
      $(element).shouldBe(disappear, Duration.ofSeconds(timeout));
    }
    $("div[id='portal-global-growl_container']").shouldBe(exist, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitForGrowlMessageDisplayClearly() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").hover();
  }

  public SelenideElement waitForElementClickable(SelenideElement element) {
    return element.shouldBe(clickable(), DEFAULT_TIMEOUT);
  }

  public SelenideElement waitForElementClickable(String cssSelector) {
    return $(cssSelector).shouldBe(clickable(), DEFAULT_TIMEOUT);
  }

  public boolean isElementDisplayed(By element) {
    try {
      waitPageLoaded();
      return $(element).shouldBe(appear, DEFAULT_TIMEOUT).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  public boolean isElementDisplayed(By element, boolean expected) {
    if (expected) {
      return isElementDisplayed(element);
    } else {
      return !$(element).shouldBe(disappear, DEFAULT_TIMEOUT).isDisplayed();
    }
  }

  public boolean isElementEnabled(By element) {
    return $(element).shouldBe(appear, DEFAULT_TIMEOUT).isEnabled();
  }

  public void waitForElementClickableThenClick(SelenideElement element) {
    waitForElementClickable(element).click();
  }

  public void waitForElementClickableThenClick(By by) {
    waitForElementClickable($(by)).click();
  }

  public void waitForElementPresent(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(exist, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldNotBe(exist, DEFAULT_TIMEOUT);
    }
  }

  public boolean isElementPresent(By element) {
    return $(element).is(visible);
  }

  public void waitForElementClickableThenClick(String cssSelector) {
    waitForElementClickable(cssSelector).click();
  }

  public void switchToIFrameOfTask() {
    switchToIframeWithId("iFrame");
  }

  public void switchToIFrameOfTask2() {
    driver.switchTo().defaultContent();
    WaitHelper.waitForIFrameAvailable(driver, "iFrame");
  }

  public void switchBackToParent() {
    WebDriverRunner.getWebDriver().switchTo().parentFrame();
  }

  public void openUserSettingMenu() {
    // waitForElementDisplayed(By.id("user-settings-menu"), true);
    clickByJavaScript(findElementById("user-settings-menu"));
    $("[id='user-setting-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForPageLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
        webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
  }

  public void waitForGrowlTitleDisappear() {
    if ($(".ui-growl-item").isDisplayed() && $(".ui-growl-item").exists()) {
      $(".ui-growl-item").$(".ui-growl-icon-close.ui-icon.ui-icon-closethick").hover().click();
    }
    $("span.ui-growl-title").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public LoginPage clickOnLogout() {
    openUserSettingMenu();
    $("[id='logout-setting:logout-menu-item']").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(
        () -> $("[id='logout-setting:logout-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new LoginPage();
  }

  private void clickUserMenuItem(String menuItemSelector) {
    waitForElementDisplayed(By.id("user-settings-menu"), true);
    try {
      clickByJavaScript(findElementById("user-settings-menu"));
      $("ul[id$='user-setting-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    } catch (Error e) {
      clickByJavaScript(findElementById("user-settings-menu"));
    }
    waitForElementDisplayed(By.id(menuItemSelector), true);
    clickByJavaScript(findElementById(menuItemSelector));
    waitForPageLoad();
  }

  public SelenideElement findElementById(String selector) {
    return $(String.format("[id$='%s']", selector)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public boolean isAdminSettingsMenuItemPresent() {
    return $("[id='adminui-menu-item']").is(Condition.exist);
  }

  public boolean isElementDisplayedById(String id) {
    return $(String.format("[id$='%s']", id)).isDisplayed();
  }

  public MainMenuPage openMainMenu() {
    if (!isMainMenuOpen()) {
      waitForElementDisplayed(By.id("left-menu"), true);
      $("div[id*='left-menu']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.sidebar-logo").shouldBe(appear, DEFAULT_TIMEOUT).hover();
      waitForElementClickableThenClick($(By.id("user-menu-required-login:toggle-menu")));
    }
    return new MainMenuPage();
  }

  public boolean isMainMenuOpen() {
    WebElement mainMenu = $(".layout-wrapper");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("static") > 0;
  }

  public void clickOnMyProfile() {
    waitForElementClickableThenClick("[id='user-settings-menu']");
    waitForElementClickableThenClick($(By.id("user-profile")));
    WaitHelper.assertTrueWithWait(() -> $("[id$=':logo-task-losing-confirmation-dialog']").isDisplayed());
  }

  public String getAnnouncementMessage() {
    waitForElementDisplayed(By.cssSelector("div[class*='announcement-message-customizable']"), true);
    return WebDriverRunner.getWebDriver().findElement(By.cssSelector("div[class*='announcement-message-customizable']"))
        .getText();
  }

  public boolean isAnnouncementMessageNotDisplayed() {
    if (WebDriverRunner.getWebDriver().findElements(By.cssSelector("div[class*='announcement-message-customizable']"))
        .size() == 0) {
      return true;
    }
    return false;
  }

  public TaskDetailsPage openTaskDetails(int index) {
    waitForElementDisplayed(By.cssSelector("div.js-task-start-list"), true);
    return clickOnTaskEntryInFullMode(index);
  }

  private TaskDetailsPage clickOnTaskEntryInFullMode(int index) {
    waitForElementClickableThenClick($(By.cssSelector("div[id$='" + index + "\\:task-item\\:task-info']")));
    return new TaskDetailsPage();
  }

  public TaskDetailsPage openDashboardTaskDetails(String taskName) {
    $("div[id$=':task-component:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td span")
        .filter(text(taskName)).first().click();
    return new TaskDetailsPage();
  }


  public GlobalSearch getGlobalSearch() {
    return new GlobalSearch();
  }

  public class GlobalSearch {

    public GlobalSearch() {}

    public boolean isDisplayed() {
      waitForElementDisplayed(By.cssSelector("input[id$='global-search-component:global-search-data']"), true);
      return findElementByCssSelector("input[id$='global-search-component:global-search-data']").isDisplayed();
    }

    public SearchResultPage inputSearchKeyword(String keyword) {
      searchByKeywordInput(keyword);
      waitForElementDisplayed(By.id("search-results-tabview"), true);
      return new SearchResultPage();
    }

    private void searchByKeywordInput(String keyword) {
      SelenideElement searchInput = $(By.cssSelector("input[id$='global-search-component:global-search-data']")).shouldBe(appear, DEFAULT_TIMEOUT);
      searchInput.click();
      searchInput.sendKeys(keyword);
      searchInput.click();
      searchInput.sendKeys(Keys.RETURN);
    }

    public boolean isPresent() {
      return isElementPresent(By.cssSelector("input[id$='global-search-component:global-search-data']"));
    }

    public void waitUtilProcessWidgetDisplayed() {
      waitForElementDisplayed(By.className("process-widget"), true);
      waitForElementDisplayed(By.className("js-loading-process-list"), false);
      waitForElementDisplayed(By.className("js-process-start-list-container"), true);
    }
  }

  public String getTextOfCurrentBreadcrumb() {
    WebElement breadcrumb = findElementByCssSelector(CURRENT_BREADCRUMB_SELECTOR);
    String result = "";
    if (CollectionUtils.isNotEmpty(breadcrumb.findElements(By.cssSelector(".js-count")))) {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML")
          + breadcrumb.findElement(By.cssSelector(".js-count")).getAttribute("innerHTML");
    } else {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML");
    }
    return result;

  }

  public NewDashboardPage selectTaskMenu() {
    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    return taskWidgetPage;
  }

  public NewDashboardPage goToHomeFromBreadcrumb() {
    waitForElementDisplayed(By.cssSelector(HOME_BREADCRUMB_SELECTOR), true);
    waitForElementClickableThenClick($(By.cssSelector(HOME_BREADCRUMB_SELECTOR)));
    return new NewDashboardPage();
  }

  public CaseWidgetNewDashBoardPage openCaseList() {
    return openMainMenu().selectCaseMenu();
  }

  public void clickOnLogo() {
    switchBackToParent();
    openMainMenu();
    waitForElementClickableThenClick($("a[id$='logo']"));
  }

  protected void refreshAndWaitElement(String cssSelector) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((webDriver) -> {
      if (($$(cssSelector).isEmpty())) {
        WaitHelper.waitForNavigation(() -> refresh());
        return false;
      } else {
        return true;
      }
    });
  }

  public ElementsCollection findChildElementsByTagName(SelenideElement parent, String tagName) {
    return parent.$$(By.tagName(tagName));
  }

  public SelenideElement findDisplayedElementByCssSelector(String selector) {
    waitForElementDisplayed(By.cssSelector(selector), true);
    return findElementByCssSelector(selector);
  }

  public void closeMainMenu() {
    findDisplayedElementByCssSelector("#left-menu");
    if (isMainMenuOpen()) {
      waitForElementClickableThenClick($(By.cssSelector("a[id$='toggle-menu']")));
      waitForElementClickableThenClick($(By.id("top-menu")));
      waitForElementDisplayed(By.cssSelector("a[id$='logo-small']"), true);
    }
  }

  public String getDescription() {
    return $(By.cssSelector("[id$='case-description-output']")).shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void waitForElementValueChanged(String cssSelector, String expectedValue) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), expectedValue));
  }

  public void waitForGlobalGrowlDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public AdminSettingsPage openAdminSettings() {
    openUserSettingMenu();
    clickByJavaScript($("[id='adminui-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT));
    return new AdminSettingsPage();
  }

  public ChangePasswordPage openChangePasswordPage() {
    clickUserMenuItem("change-password-menu-item");
    return new ChangePasswordPage();
  }

  public ProjectVersionPage openProjectVersionPage() {
    clickUserMenuItem("project-info-menu-item");
    return new ProjectVersionPage();
  }

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    return new UserProfilePage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }

  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public int countBrowserTab() {
    return getDriver().getWindowHandles().size();
  }

  public void waitForNewTabOpen() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.numberOfWindowsToBe(2));
  }

  public NewDashboardPage openTaskList() {
    switchToDefaultContent();
    return openMainMenu().selectTaskMenu();
  }

  public void waitForTaskTitleAppear() {
    waitForElementDisplayed(By.id("title"), true);
  }

  public void waitForIFrameContentVisible() {
    waitForIFrameScreenshotSizeGreaterThan(IFRAME_SCREENSHOT_FILE_SIZE_AT_MINIMUM);
  }

  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  public void waitForIFrameScreenshotSizeGreaterThan(long fileSizeInBytes) {
    switchToDefaultContent();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((driver) -> {
      return $("iFrame").getScreenshotAs(OutputType.FILE).length() > fileSizeInBytes;
    });
    switchToIFrameOfTask();
  }


  public ChatPage getChat() {
    waitForElementDisplayed(By.id("toggle-chat-panel-command"), true, 5);
    waitForElementClickableThenClick("[id$='toggle-chat-panel-command']");
    return new ChatPage();
  }

  public NewDashboardPage goToHomeFromBreadcrumbWithWarning() {
    waitForElementDisplayed(By.cssSelector(HOME_BREADCRUMB_SELECTOR), true);
    waitForElementClickableThenClick(By.cssSelector(HOME_BREADCRUMB_SELECTOR));
    waitForElementDisplayed(By.id("user-menu-required-login:warning-before-leaving-task-component:leave-button"), true);
    waitForElementClickableThenClick(
        By.id("user-menu-required-login:warning-before-leaving-task-component:leave-button"));
    return new NewDashboardPage();
  }

  public SelenideElement getGlobalGrowlMessage() {
    return findElementById(PORTAL_GLOBAL_GROWL_ID).find(By.cssSelector(".ui-growl-message"));
  }

  public GlobalSearchResultPage inputGlobalSearchKeyword(String keyword) {
    $("input[id$='global-search-component:global-search-data']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(editable,
        DEFAULT_TIMEOUT);
    getGlobalSearchInput().click();
    getGlobalSearchInput().sendKeys(keyword);
    getGlobalSearchInput().sendKeys(Keys.ENTER.toString());
    $("#search-results-tabview").shouldBe(appear, DEFAULT_TIMEOUT);
    return new GlobalSearchResultPage();
  }

  private SelenideElement getGlobalSearchInput() {
    return $("#global-search-component\\:global-search-data").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public QRCodePage openQRCode() {
    openUserSettingMenu();
    $("[id='mobile-app-item']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return new QRCodePage();
  }

  public void sendKeys(String cssSelector, String value) {
    SelenideElement element = $(cssSelector).shouldBe(Condition.appear).shouldBe(Condition.editable);
    element.click();
    element.clear();
    element.sendKeys(value);
  }
}
