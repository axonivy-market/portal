package portal.guitest.page;

import org.openqa.selenium.By;

public class WorkingTaskDialogPageOfApplicationMenu extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('user-menu-required-login:task-leave-warning-dialog')";
  }

  public HomePage leaveTask() {
    click(By.id("user-menu-required-login:leave-button"));
    return new HomePage();
  }
}
