package portal.guitest.common;

public final class Sleeper {
  public static final void sleep(long milis) {
    try {
      org.openqa.selenium.support.ui.Sleeper.SYSTEM_SLEEPER.sleep(java.time.Duration.ofMillis(milis));
    } catch (InterruptedException e) {
      throw new PortalGUITestException(e);
    }
  }
}
