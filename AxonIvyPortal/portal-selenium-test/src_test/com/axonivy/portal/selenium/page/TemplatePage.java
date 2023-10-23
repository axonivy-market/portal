package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class TemplatePage extends AbstractPage {
  protected static final String LAYOUT_WRAPPER = ".layout-wrapper";
  public static final String ID_PROPERTY = "id";
  public static final String CLASS_PROPERTY = "class";
  public static final String CURRENT_BREADCRUMB_SELECTOR = ".portal-breadcrumb li:last-child .ui-menuitem-link.ui-state-disabled";
  private static final String HOME_BREADCRUMB_SELECTOR = ".portal-breadcrumb .ui-menuitem-link:first-child";
  public static final String PORTAL_GLOBAL_GROWL_ID = "portal-global-growl_container";
  protected static final String COMPONENT_PAGE_LOCATOR = "//*[contains(@id,'theme-selection')]";

  // If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 30L;
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  protected Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  public WebDriver driver = WebDriverRunner.getWebDriver();

  public void switchLastBrowserTab() {
    String oldTab = WebDriverRunner.getWebDriver().getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
    tabs.remove(oldTab);
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(0));
  }

  public void waitUntilElementToBeClickable(SelenideElement element) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
  }

  public void switchToIframeWithId(String id) {
    WebDriverRunner.getWebDriver().switchTo().frame($("iframe[id='" + id + "']"));
  }

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitForAjaxStatusPositionDisappear() {
    $("div.ajax-status-position").shouldBe(disappear, DEFAULT_TIMEOUT);
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
  }

  public void waitForGrowlMessageDisplayClearly() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").hover();
  }

  public SelenideElement waitForElementClickable(SelenideElement element) {
    return element.shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public SelenideElement waitForElementClickable(String cssSelector) {
    return $(cssSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public boolean isElementDisplayed(By element) {
    try {
      return $(element).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  public boolean isElementEnabled(By element) {
    return $(element).shouldBe(appear, DEFAULT_TIMEOUT).isEnabled();
  }

  public void waitForElementClickableThenClick(SelenideElement element) {
    waitForElementClickable(element).click();
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

  public void switchBackToParent() {
    WebDriverRunner.getWebDriver().switchTo().parentFrame();
  }

  public void waitAjaxIndicatorDisappear() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver wdriver) {
        boolean isAjaxFinished = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return jQuery.active == 0").equals(true);
        boolean isLoaderHidden = (boolean) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return $('.spinner').is(':visible') == false");
        return isAjaxFinished && isLoaderHidden;
      }

    });
  }

  public void waitForPageLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  }

  public void waitForGrowlTitleDisappear() {
    $("span.ui-growl-title").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public LoginPage clickOnLogout() {
    $("[id='user-settings-menu']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='logout-setting:logout-menu-item']").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigationToLoginPage(() -> $("[id='logout-setting:logout-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new LoginPage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }

  private void clickUserMenuItem(String menuItemSelector) {
    waitForElementClickableThenClick("[id='user-settings-menu']");
    waitForGrowlTitleDisappear();
    $(By.id(menuItemSelector)).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitAjaxIndicatorDisappear();
    $(By.id("user-setting-container")).shouldBe(disappear, DEFAULT_TIMEOUT);
    waitForPageLoad();
  }

  public SelenideElement findElementById(String selector) {
    return $(String.format("[id$='%s']", selector));
  }

  public boolean isAdminSettingsMenuItemPresent() {
    return $("[id='adminui-menu-item']").is(Condition.exist);
  }

  public boolean isElementDisplayedById(String id) {
    try {
      return findElementById(id).isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  public AdminSettingsPage openAdminSettings() {
    clickUserMenuItem("adminui-menu-item");
    return new AdminSettingsPage();
  }

  public MainMenuPage openMainMenu() {
    if (!isMainMenuOpen()) {
      $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover();
      waitForElementClickableThenClick($(By.xpath("//a[@id='user-menu-required-login:toggle-menu']")));
    }
    return new MainMenuPage();
  }

  public boolean isMainMenuOpen() {
    WebElement mainMenu = $(".layout-wrapper");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("static") > 0;
  }

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    waitAjaxIndicatorDisappear();
    return new UserProfilePage();
  }

  public void clickOnMyProfile() {
    waitForElementClickableThenClick("[id='user-settings-menu']");
    waitForElementClickableThenClick($(By.id("user-profile")));
    WaitHelper.assertTrueWithWait(() -> $("[id$=':logo-task-losing-confirmation-dialog']").isDisplayed());
  }

  public String getAnnouncementMessage() {
    waitForElementDisplayed(By.cssSelector("div[class*='announcement-message-customizable']"), true);
    return WebDriverRunner.getWebDriver().findElement(By.cssSelector("div[class*='announcement-message-customizable']")).getText();
  }

  public boolean isAnnouncementMessageNotDisplayed() {
    if (WebDriverRunner.getWebDriver().findElements(By.cssSelector("div[class*='announcement-message-customizable']")).size() == 0) {
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
      waitForElementDisplayed(By.cssSelector("a[id$='global-search-item']"), true);
      return findElementByCssSelector("a[id$='global-search-item']").isDisplayed();
    }

    public GlobalSearchResultPage inputSearchKeyword(String keyword) {
      waitForElementDisplayed(By.cssSelector(".topbar-item.search-item"), true);
      waitForElementClickableThenClick("a[id$='global-search-item']");
      waitForElementDisplayed(By.cssSelector("input[id$='global-search-component:global-search-data']"), true);
      $(By.cssSelector(GLOBAL_SEARCH_INPUT_SELECTOR)).click();
      getSearchInput().sendKeys(Keys.ENTER.toString());
      try {
        waitForElementDisplayed(By.id("search-results-tabview"), true);
      } catch (Exception e) {
        System.out.println("Exception when waiting for search page displayed, press Enter again.");
        getSearchInput().sendKeys(Keys.ENTER.toString());
      }
      return new GlobalSearchResultPage();
    }

    public boolean isPresent() {
      return isElementPresent(By.cssSelector("a[id$='global-search-item']"));
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
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML") + breadcrumb.findElement(By.cssSelector(".js-count")).getAttribute("innerHTML");
    } else {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML");
    }
    return result;

  }

  public int countBrowserTab() {
    return driver.getWindowHandles().size();
  }

  public TaskWidgetPage selectTaskMenu() {
    WaitHelper.waitForNavigation(() -> $(".layout-menu li[role='menuitem'] a.TASK").click());
    return new TaskWidgetPage();
  }

  public NewDashboardPage goToHomeFromBreadcrumb() {
    waitForElementDisplayed(By.cssSelector(HOME_BREADCRUMB_SELECTOR), true);
    waitForElementClickableThenClick($(By.cssSelector(HOME_BREADCRUMB_SELECTOR)));
    return new NewDashboardPage();
  }

  public CaseWidgetPage openCaseList() {
    return openMainMenu().selectCaseMenu();
  }

  public void clickOnLogo() {
    openMainMenu();
    waitForElementClickableThenClick($("a[id$='logo']"));
    waitAjaxIndicatorDisappear();
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

}
