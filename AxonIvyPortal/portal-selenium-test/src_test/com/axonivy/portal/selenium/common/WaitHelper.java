package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.page.AbstractPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;

public final class WaitHelper {

  protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState'][id$='javax.faces.ViewState:1']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(CollectionCondition.sizeLessThanOrEqual(0), DEFAULT_TIMEOUT);
//    $(".layout-menu li[role='menuitem'] a.DASHBOARD").shouldHave(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void waitForNavigationToLoginPage(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState'][id$='javax.faces.ViewState:1']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(CollectionCondition.sizeLessThanOrEqual(0), DEFAULT_TIMEOUT);
    $("[id='login:login-form:username']").shouldHave(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
  }

  public static void waitNumberOfElementsToBe(WebDriver dirver, By selector, Integer expectedSize) {
    wait(dirver).until(ExpectedConditions.numberOfElementsToBe(selector, expectedSize));
  }

  public static void waitAttributeToBe(WebDriver dirver, By selector, String attribute, String value) {
    wait(dirver).until(ExpectedConditions.attributeToBe(selector, attribute, value));
  }

  public static void waitAttributeContains(WebDriver dirver, By selector, String attribute, String value) {
    wait(dirver).until(ExpectedConditions.attributeContains(selector, attribute, value));
  }

  public static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, DEFAULT_TIMEOUT);
  }

  public static void retryAction(Runnable action) {
    int attempts = 0;
    while (attempts < 10) {
      try {
        action.run();
        break;
      } catch (Exception e) {
        System.out.println("ERROR action");
      }
      attempts++;
    }
    if (attempts == 10) {
      action.run();
    }
  }

  public static void assertTrueWithWait(Supplier<Boolean> supplier) {
    wait(WebDriverRunner.getWebDriver()).until(webDriver -> supplier.get());
  }

  public static void assertFalseWithWait(Supplier<Boolean> supplier) {
    wait(WebDriverRunner.getWebDriver()).until(webDriver -> !supplier.get());
  }
  public static void typeWithRetry(AbstractPage page, String cssSelector, String value) {
    WaitHelper.assertTrueWithWait(() -> {
      WebElement input = page.findElementByCssSelector(cssSelector);
      input.clear();
      input.click(); // To make Firefox more stable
      input.sendKeys(value);
      return input.getAttribute("value").equals(value);
    });
  }
}
