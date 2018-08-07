package portal.guitest.page;

import org.openqa.selenium.WebElement;


public class AdhocPage extends TaskTemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('request-tab')";
  }

  public void enterSubject(String subject) {
    WebElement subjectInput = findElementByCssSelector("*[id$='" + ":dataCaseInfoSubject']");
    type(subjectInput, subject);
  }

  public void addTask() {
    WebElement taskCommand = findElementByCssSelector("*[id$='" + ":add-task']");
    taskCommand.click();
  }

  public void addComment(String comment) {
    WebElement commentInput = findElementByCssSelector("*[id$='" + ":comments']");
    type(commentInput, comment);
  }

  public TaskWidgetPage startWorkflow() {
    WebElement command = findElementByCssSelector("*[id$='" + ":start-workflow']");
    command.click();
    return new TaskWidgetPage();
  }

  public TaskWidgetPage send() {
    WebElement command = findElementByCssSelector("*[id$='" + ":send']");
    command.click();
    return new TaskWidgetPage();
  }


}
