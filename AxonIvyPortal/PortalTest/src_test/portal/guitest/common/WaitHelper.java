package portal.guitest.common;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.page.TemplatePage;
import vn.wawa.guitest.base.page.AbstractPage;

public final class WaitHelper {

  /**
   * Uses this method to wait then assertTrue. Instead of Assert#assertTrue(boolean), this method will wait until the
   * condition is true or timeout
   * 
   * @param supplier
   * @param durationInSeconds
   */
  public static void assertTrueWithWait(Supplier<Boolean> supplier, int durationInSeconds) {
    Awaitility.await().atMost(new Duration(durationInSeconds, TimeUnit.SECONDS)).until(() -> {
      try {
        return supplier.get();
      } catch (Exception e) {
        System.out.println("ERROR when assertTrueWithWait");
      }
      return false;
    });
  }

  /**
   * Uses this method to wait then assertTrue. Instead of Assert#assertTrue(boolean), this method will wait 10 SECONDS
   * until the condition is true or timeout
   * 
   * @param supplier
   */
  public static void assertTrueWithWait(Supplier<Boolean> supplier) {
    assertTrueWithWait(supplier, 10);
  }

  public static void assertTrueWithRefreshPage(TemplatePage page, Supplier<Boolean> supplier) {
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> {
      try {
        boolean result = supplier.get();
        if (!result) {
          Sleeper.sleep(1000); // Wait for latest data
          result = supplier.get();
          if (!result) {
            System.out.println("Refresh page for latest data");
            waitForNavigation(page, () -> page.refresh());
          }
        }
        return supplier.get();
      } catch (Exception e) {
        System.out.println("ERROR when assertTrueWithRefreshPage");
        Sleeper.sleep(1000);
        try {
          supplier.get();
        } catch (Exception e1) {
          waitForNavigation(page, () -> page.refresh());
        }
      }
      return false;
    });
  }

  public static void waitForNavigation(TemplatePage page, Runnable navigationAcion) {
    String viewState = page.findElementByCssSelector("input[name='javax.faces.ViewState']").getAttribute("value");
    navigationAcion.run();
    page.waitForElementPresent(By.cssSelector("input[value='" + viewState + "']"), false);
    page.waitForElementDisplayed("id('global-search-item')", true);
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

  public static void typeWithRetry(AbstractPage page, String cssSelector, String value) {
    WaitHelper.assertTrueWithWait(() -> {
      WebElement input = page.findElementByCssSelector(cssSelector);
      input.clear();
      input.click(); // To make Firefox more stable
      input.sendKeys(value);
      return input.getAttribute("value").equals(value);
    });
  }
  
  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
  }
  
  public static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, 45);
  }
  
  public static void waitForPresenceOfElementLocatedInFrame(WebDriver driver, String cssSelector) {
    wait(driver).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
  }
  
  public static void waitForVisibilityOfElementLocated(WebDriver driver, String cssSelector) {
    wait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
  }
}
