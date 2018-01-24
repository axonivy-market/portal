package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelfServiceTaskPage extends TemplatePage {

  public void insertTask(String taskDescription, String actorUsername) {
    waitForElementDisplayed(By.id("caseform:insert-task-button"), true);
    WebElement insertTaskButton = findElementById("caseform:insert-task-button");
    insertTaskButton.click();
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("addTask"), true);
    
    inputTaskDescription(taskDescription);
    selectTaskActorForInsertedTask(actorUsername);
  }
  
  public void appendTask(String taskDescription, String actorUsername) {
    waitForElementDisplayed(By.id("caseform:append-task-button"), true);
    WebElement insertTaskButton = findElementById("caseform:append-task-button");
    insertTaskButton.click();
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("addTask"), true);
    
    inputTaskDescription(taskDescription);
    selectTaskActorForAppendedTask(actorUsername);
  }
  
  public void inputTaskDescription(String taskDescription) {
    WebElement taskDescriptionInput = findElementById("addform:text");
    taskDescriptionInput.clear();
    taskDescriptionInput.sendKeys(taskDescription);
  }
  
  public void selectTaskActorForInsertedTask(String actorUsername) {
    selectTaskActor(actorUsername);
    WebElement insertTaskButton = findElementById("addform:insert-task-button");
    insertTaskButton.click();
    waitForElementDisplayed(new HomePage().getLoadedLocator(), true);
  }
  
  public void selectTaskActorForAppendedTask(String actorUsername) {
    selectTaskActor(actorUsername);
    WebElement insertTaskButton = findElementById("addform:append-task-button");
    insertTaskButton.click();
    waitAjaxIndicatorDisappear();
  }

  public void inputTaskComment(String comment) {
    WebElement taskCommentInput = findElementById("form2:comments");
    taskCommentInput.clear();
    taskCommentInput.sendKeys(comment);
  }
  
  public void clickSendButton() {
    WebElement sendButton = findElementById("form2:send");
    sendButton.click();
    waitForElementDisplayed(new HomePage().getLoadedLocator(), true);
  }
  
  public void clickDoneButton() {
    WebElement doneButton = findElementById("form2:done-button");
    doneButton.click();
    waitForElementDisplayed(new HomePage().getLoadedLocator(), true);
  } 
  
  private void selectTaskActor(String actorUsername) {
    WebElement taskDescriptionInput = findElementById("addform:actor_input");
    taskDescriptionInput.clear();
    taskDescriptionInput.sendKeys(actorUsername);
    
    waitForElementDisplayed(By.id("addform:actor_panel"), true);
    WebElement actorSelection = findElementByCssSelector("li[data-item-label='" + actorUsername + "']");
    actorSelection.click();
    waitAjaxIndicatorDisappear();
  }
  
}
