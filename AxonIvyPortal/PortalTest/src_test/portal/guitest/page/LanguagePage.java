package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LanguagePage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return "id('language-setting-dialog_title')";
  }


	public void selectLanguage(int newLanguage) {
	   waitForElementDisplayed(By.cssSelector("div[id$='selection'] div.ui-selectonemenu-trigger"), true);
	   clickByCssSelector("div[id$='selection'] div.ui-selectonemenu-trigger");
	   clickByCssSelector("li[id$='selection_"+newLanguage+"']"); 
	}
  public void save() {
    WebElement save = findElementById("language-settings-form:language-settings:save-settings");
    save.click();
    waitAjaxIndicatorDisappear();
  }
}
