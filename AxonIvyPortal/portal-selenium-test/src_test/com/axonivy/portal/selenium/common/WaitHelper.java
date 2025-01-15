package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ZERO;

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

  protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState'][id$='javax.faces.ViewState:1']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(CollectionCondition.sizeLessThanOrEqual(0), DEFAULT_TIMEOUT);
  }

  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    try {
      wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  private static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, DEFAULT_TIMEOUT);
  }

  public static void assertTrueWithWait(Supplier<Boolean> supplier) {
    try {
      wait(WebDriverRunner.getWebDriver()).until(webDriver -> supplier.get());
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  public static void waitForPresenceOfElementLocatedInFrame(String cssSelector) {
    try {
      wait(WebDriverRunner.getWebDriver())
          .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Some UI are the same before and after AJAX, use this method only in that scenario. Ask the team if using this
   * @param cssSelector 
   * @param action 
   */
  public static void waitForActionComplete(String cssSelector, Runnable action) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver())
        .executeScript("$('" + cssSelector.replace("\\", "\\\\") + "').css('background-color', 'rgb(250, 0, 0)')");
    $(cssSelector).getCssValue("background-color");
    $(cssSelector).shouldHave(Condition.cssValue("background-color", "rgb(250, 0, 0)"));
    action.run();
    $(cssSelector).shouldNotHave(Condition.cssValue("background-color", "rgb(250, 0, 0)"), DEFAULT_TIMEOUT);
  }

  /**
   * Use this instead of {@code Assertions} methods so that Selenide would take screenshots if errors. This is a
   * workaround because we cannot use @ExtendWith({ScreenShooterExtension.class}) with
   * `WebDriverRunner.getWebDriver().quit();` in `@AfterEach`
   * @param condition 
   */
  public static void assertTrue(boolean condition) {
    if (!condition) {
      $("ASSERTION FAILED, CHECK STACK TRACE from BaseTest.assertTrue").shouldBe(exist, ZERO);
    }
  }
}
