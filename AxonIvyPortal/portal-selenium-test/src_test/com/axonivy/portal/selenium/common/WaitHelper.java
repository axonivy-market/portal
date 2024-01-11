package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ZERO;
import static com.codeborne.selenide.Condition.exist;
import java.time.Duration;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;


public final class WaitHelper {

//  protected static final long  DEFAULT_TIMEOUT = 45000;
  protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState'][id$='javax.faces.ViewState:1']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(CollectionCondition.sizeLessThanOrEqual(0), DEFAULT_TIMEOUT);
    $(".layout-menu li[role='menuitem'] a.DASHBOARD").shouldHave(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
  }

  public static void waitNumberOfElementsToBe(WebDriver dirver, By selector, Integer expectedSize) {
    wait(dirver).until(ExpectedConditions.numberOfElementsToBe(selector, expectedSize));
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
  
  /**
   * Some UI are the same before and after AJAX, use this method only in that scenario. Ask the team if using this
   */
  public static void waitForActionComplete(String cssSelector, Runnable action) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver())
        .executeScript("$(\"" + cssSelector + "\").css('background-color', 'rgb(250, 0, 0)')");
    $(cssSelector).shouldHave(Condition.cssValue("background-color", "rgb(250, 0, 0)"));
    action.run();
    $(cssSelector).shouldNotHave(Condition.cssValue("background-color", "rgb(250, 0, 0)"));
  }

  public static void assertTrueWithWait(Supplier<Boolean> supplier) {
    try {
      wait(WebDriverRunner.getWebDriver()).until(webDriver -> supplier.get());
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Use this instead of {@code Assertions} methods so that Selenide would take screenshots if errors. This is a
   * workaround because we cannot use @ExtendWith({ScreenShooterExtension.class}) with
   * `WebDriverRunner.getWebDriver().quit();` in `@AfterEach`
   */
  public static void assertTrue(boolean condition) {
    if (!condition) {
      $("ASSERTION FAILED, CHECK STACK TRACE from BaseTest.assertTrue").shouldBe(exist, ZERO);
    }
  }

}
