package portal.guitest.page;

import org.openqa.selenium.By;

public class WorkingTaskDialogFromUserProfilePage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return "id('task-leave-warning-component:logo-task-losing-confirmation-dialog')";
  }

  public UserProfilePage leaveTask() {
    click(By.id("task-leave-warning-component:leave-button"));
    return new UserProfilePage();
  }
  
  public UserProfilePage reserveTask() {
    click(By.id("task-leave-warning-component:reserve-task-button"));
    return new UserProfilePage();
  }
}
