package portal.guitest.page;

import org.openqa.selenium.By;

import vn.wawa.guitest.base.page.AbstractPage;

public class AutoDialogPage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('closeButton')";

  public AutoDialogPage() {
    waitForLocatorDisplayed(getLoadedLocator());
  }

  private void waitForLocatorDisplayed(String locator) {
    waitForElementDisplayed(locator, true, DEFAULT_TIMEOUT);
  }

  public void proceed() {
    click(By.cssSelector("#closeButton"));
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }


}
