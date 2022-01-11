package portal.guitest.page;

import org.openqa.selenium.By;

public class DefaultExpresTaskPage extends TaskTemplatePage{
  
  @Override
  protected String getLoadedLocator() {
    return "id('form:cancel-btn')";
  }
  
  public void enterTextToDefaultTask(String text) {
    var textArea = findElementByCssSelector("#form\\:user-task-dyna-form textarea[id$='input-text-area']");
    type(textArea, text);
    var textAreaId = textArea.getAttribute(ID_PROPERTY);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, textAreaId.replace(":", "\\\\:"), ID_PROPERTY);
  }
  
  public void finishDefaultTask() {
    click(By.id("form:ok-btn"));
  }
  
}
