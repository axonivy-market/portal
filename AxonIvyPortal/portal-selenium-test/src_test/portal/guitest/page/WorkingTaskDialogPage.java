package portal.guitest.page;

import org.openqa.selenium.By;

public class WorkingTaskDialogPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-leave-warning-component:logo-task-losing-confirmation-dialog')";
  }

  public HomePage leaveTask() {
    click(By.id("task-leave-warning-component:leave-button"));
    return new HomePage();
  }
  
  public HomePage reserveTask() {
    click(By.id("task-leave-warning-component:reserve-task-button"));
    return new HomePage();
  }
}
