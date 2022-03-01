package portal.guitest.page;

import org.openqa.selenium.By;

public class WorkingTaskDialogPageOfApplicationMenu extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('user-menu-required-login:warning-before-leaving-task-component:task-leave-warning-dialog')";
  }

  public HomePage leaveTask() {
    click(By.id("user-menu-required-login:warning-before-leaving-task-component:leave-button"));
    return new HomePage();
  }
}
