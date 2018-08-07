package portal.page;

import org.openqa.selenium.By;

public class DashboardPage extends TemplatePage {

  private final static String TASK_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_1";
  
  @Override
  protected String getLoadedLocator() {
    return "id('statistics-widget:tasks-expiry-chart')";
  }

  public boolean isTaskMenuOpen() {
    return isElementDisplayed(By.id(TASK_MENU_ID + "_0"));
  }
}
