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
        System.out.println("Exception when assertTrueWithWait");
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

  public static void waitForNavigation(TemplatePage page, Runnable navigationAcion) {
    String viewState = page.findElementByCssSelector("input[name='javax.faces.ViewState']").getAttribute("value");
    navigationAcion.run();
    page.waitForElementPresent(By.cssSelector("input[value='" + viewState + "']"), false);

  }

}
