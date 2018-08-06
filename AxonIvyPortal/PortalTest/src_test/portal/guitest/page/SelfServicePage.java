package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelfServicePage extends TemplatePage {

  public void choosePattern(int patternIndex) {
    WebElement selectPatternform = findElementById("form:dataCaseInfoKind");
    List<WebElement> patterns = selectPatternform.findElements(By.cssSelector("div[class*=ui-radiobutton-box]"));
    if(!patterns.isEmpty()) {
      patterns.get(patternIndex).click();
    }
    waitForPageLoaded(1000L);
    waitAjaxIndicatorDisappear();
  }
  
  public String getPatternOfTaskDef() {
    WebElement taskDefForm = findElementById("form:taskDef");
    List<WebElement> taskDefLabels = taskDefForm.findElements(By.cssSelector(".Fleft table label.font-bold"));
    if(taskDefLabels.size() >= 2) {
      return taskDefLabels.get(1).getText();
    }
    return "";
  }
  
  public boolean isAdHocPatternSelected() {
    List<WebElement> taskDefElements = findTaskDefElements();
    if(taskDefElements.size() == 2) {
      WebElement addTaskElement = taskDefElements.get(1);
      return addTaskElement.findElement(By.id("form:add-task")).isDisplayed();
    }
    return false;
  }

  private List<WebElement> findTaskDefElements() {
    WebElement taskDefForm = findElementById("form:taskDef");
    List<WebElement> taskDefElements = taskDefForm.findElements(By.cssSelector(".Fleft"));
    return taskDefElements;
  }
  
  public void openUserListSelectionOfFirstTodoTaskDef() {
    List<WebElement> taskDefElements = findTaskDefElements();
    if(taskDefElements.size() > 1) {
      WebElement firstAddTodoTaskElement = taskDefElements.get(1);
      WebElement userInput = firstAddTodoTaskElement.findElement(By.cssSelector("input:first-of-type"));
      userInput.click();
    }
    waitForPageLoaded();
    waitForElementDisplayed(By.id("userList"), true);
  }
  
  public void inputUser(String username) {
    WebElement userInput = findElementById("userListForm:userList_input");
    userInput.sendKeys(username);
    waitForElementDisplayed(By.id("userListForm:userList_panel"), true);
    WebElement actorSelection = findElementByCssSelector("li[data-item-label='" + username + "']");
    actorSelection.click();
    waitForElementEnabled(By.id("userListForm:addUserButton"), true, 30L);
    addUser();
    waitAjaxIndicatorDisappear();
  }
  
  private void addUser() {
    WebElement addUserButton = findElementById("userListForm:addUserButton");
    addUserButton.click();
  }
  
  public void inputSubject(String subject) {
    WebElement subjectInput = findElementById("form:dataCaseInfoSubject");
    subjectInput.sendKeys(subject);
  }
  
  public void addTask() {
    WebElement addTaskButton = findElementById("form:add-task");
    addTaskButton.click();
    waitAjaxIndicatorDisappear();
  }
  
  public void startWorkflow() {
    WebElement startWorkflowButton = findElementById("form:start-workflow");
    startWorkflowButton.click();
    waitForPageLoaded();
  }
  
  public String getUserListErrorMessage() {
    WebElement errorMessage = findElementByCssSelector("#userList span.ui-messages-error-detail");
    return errorMessage.getText();
  }
}
