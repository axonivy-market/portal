package portal.guitest.page;

import org.openqa.selenium.WebElement;

public class TaskInformationPage extends TemplatePage {
  private final String TASK_INFORMATION_PAGE_LOCATOR = "id('task-information')";

  private final WebElement TASK_INFORMATION_PAGE;

  public TaskInformationPage() {
    TASK_INFORMATION_PAGE = findElementByXpath("id('task-information')");
  }

  @Override
  protected String getLoadedLocator() {
    return TASK_INFORMATION_PAGE_LOCATOR;
  }

  public String getTaskDescription() {
    WebElement taskDescription =
        findChildElementById(TASK_INFORMATION_PAGE, "task-information:task-details-form:description");
    return taskDescription.getText();
  }
}
