package portal.guitest.page;

import org.openqa.selenium.By;

public class DefaultExpresTaskPage extends TaskTemplatePage{
  
  @Override
  protected String getLoadedLocator() {
    return "id('form:cancel-btn')";
  }
  
  public void enterTextToDefaultTask(String text) {
    type(By.cssSelector("#form\\:user-task-dyna-form textarea[id$='input-text-area']"), text);
  }
  
  public void finishDefaultTask() {
    click(By.id("form:ok-btn"));
  }
  
}
