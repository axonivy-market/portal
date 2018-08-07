package portal.page;

import org.openqa.selenium.WebElement;

import ch.xpertline.base.pages.AbstractPage;

public class CaseDocumentPage extends AbstractPage {
  public boolean containsFileUploadComponent() {
    WebElement fileUpload = findElementByXpath("//input[@type='file']");
    return fileUpload.isEnabled();
  }

  @Override
  protected String getLoadedLocator() {
    return "id('document-tab')";
  }

}
