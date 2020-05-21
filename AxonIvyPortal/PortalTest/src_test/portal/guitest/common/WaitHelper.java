package portal.guitest.common;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public final class WaitHelper {

  /**
   * Uses this method to wait then assertTrue. Instead of Assert#assertTrue(boolean), this method will wait until the
   * condition is true or timeout
   * 
   * @param supplier
   * @param durationInSeconds
   */
  public static void assertTrueWithWait(Supplier<Boolean> supplier, int durationInSeconds) {
    Awaitility.await().atMost(new Duration(durationInSeconds, TimeUnit.SECONDS)).until(() -> supplier.get());
  }

  /**
   * Uses this method to wait then assertTrue. Instead of Assert#assertTrue(boolean), this method will wait 10 SECONDS
   * until the condition is true or timeout
   * 
   * @param supplier
   */
  public static void assertTrueWithWait(Supplier<Boolean> supplier) {
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> supplier.get());
  }
}
