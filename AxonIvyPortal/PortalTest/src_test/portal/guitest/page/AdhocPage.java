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
    clickByCssSelector("*[id$='" + ":add-task']");
  }

  public void addComment(String comment) {
    WebElement commentInput = findElementByCssSelector("*[id$='" + ":comments']");
    type(commentInput, comment);
  }

  public TaskWidgetPage startWorkflow() {
    clickByCssSelector("*[id$='" + ":start-workflow']");
    return new TaskWidgetPage();
  }

  public TaskWidgetPage send() {
    clickByCssSelector("*[id$='" + ":send']");
    return new TaskWidgetPage();
  }


}
