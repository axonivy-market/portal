package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.SelenideElement;

public class HomePage extends TemplatePage {
  public final static String PORTAL_HOME_PAGE_URL = "portal/1549F58C18A6C562/DefaultApplicationHomePage.ivp";
  public final static String PORTAL_EXAMPLES_HOME_PAGE_URL = "portal-developer-examples/164211E97C598DAA/DefaultApplicationHomePage.ivp";
  public final static String PORTAL_EXAMPLES_EMPLOYEE_SEARCH = "portal-developer-examples/180D50804A2BF9E9/employeeSearch.ivp";
  public final static String PORTAL_INTERNAL_HOME_PAGE_URL = "internalSupport/164211FF9482BB44/DefaultApplicationHomePage.ivp";

  @Override
  protected String getLoadedLocator() {
    return "[id='task-widget:task-list-link:task-list-link']";
  }
  
  public WebElement getStatisticWidgetElement() {
    // We use Sleeper here to wait for chart render completely, because the
    // statistic dialog was render with an animation by canvas.
    Sleeper.sleep(800);

    return findElementById("statistics-widget");
  }
  
  public WebElement getProcessWidgetElement() {
    return findElementById("process-widget");
  }
  
  public ProcessWidgetPage getProcessWidget() {
    return new ProcessWidgetPage();
  }

  public WebElement getTaskWidgetElement() {
    waitForElementDisplayed(By.id("task-widget"), true);
    WaitHelper.assertTrueWithWait(() -> {
      List<SelenideElement> taskElements = $$(".task-start-list-item");
      return taskElements.size() != 0;
    });
    return findElementById("task-widget");
  }


  public void waitForWidgetsInDashboardLoaded() {
    $("div[class*='home-page-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[class*='compact-process-widget']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[class*='dashboard-task-widget-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[class*='statistic-widget-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitUntilProcessWidgetDisplayed() {
    $("div[class*='process-widget'").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[class*='js-loading-text'").shouldNotBe(appear);
    $("div[class*='user-process-container'").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitUtilProcessWidgetUserFavoriteDisplayed() {
    waitForElementDisplayed(By.id("process-widget:user-process-container"), true);
  }

  public void clickEditSwitchLink() {
    $(By.cssSelector("[id$='editing-switch-command']")).shouldBe(getClickableCondition()).click();
    $(By.cssSelector("[id$='edit-process-item-form']")).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public UserFavoriteProcessPage openNewProcessDialog() {
    $("a[id$='show-adding-dialog-commmand']").shouldBe(getClickableCondition()).click();

    return new UserFavoriteProcessPage();
  }
  
  public void waitUntilStatisticLoaded() {
    $("div[id$='statistic-carousel']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$='process-widget:default-user-processes-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$='process-widget:process-list']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public TaskWidgetPage getTaskWidget() {
    return new TaskWidgetPage();
  }
}
