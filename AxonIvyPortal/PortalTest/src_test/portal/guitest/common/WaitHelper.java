package portal.guitest.common;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.openqa.selenium.By;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.page.TemplatePage;

public final class WaitHelper {

  /**
   * Uses this method to wait then assertTrue. Instead of Assert#assertTrue(boolean), this method will wait until the
   * condition is true or timeout
   * 
   * @param supplier
   * @param durationInSeconds
   */
  private static void assertTrueWithWait(Supplier<Boolean> supplier, int durationInSeconds) {
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
    page.waitForElementDisplayed("id('global-search-component:global-search-data')", true);
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
}
