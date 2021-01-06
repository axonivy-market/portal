package portal.guitest.page;

import vn.wawa.guitest.base.page.AbstractPage;

public class AutoDialogPage extends AbstractPage {

  private static final String TEMPLATE_PAGE_LOCATOR = "id('Caption')";

  public AutoDialogPage() {
    waitForLocatorDisplayed(getLoadedLocator());
  }

  private void waitForLocatorDisplayed(String locator) {
    waitForElementDisplayed(locator, true, DEFAULT_TIMEOUT);
  }

  public void waitContentDisplayed() {
    waitForElementDisplayedByCssSelector("#Content");
  }

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }


}
