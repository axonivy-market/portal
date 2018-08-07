package portal.page;

import org.openqa.selenium.By;

public class AdminSettingsPage extends TemplatePage {
  
  private static final String DIALOG_TITLE = "admin-ui-dialog_title";
  
  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(DIALOG_TITLE), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('" + DIALOG_TITLE + "')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(DIALOG_TITLE).isDisplayed();
  }

}
