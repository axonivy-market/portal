package portal.guitest.page;

import org.openqa.selenium.By;

public class DashboardPage extends TemplatePage {

  private final static String TASK_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_1";
  private final static String SHOW_ALL_PROCESSES_ID = "process-widget:process-link:process-link";
  private final static String SHOW_FULL_TASK_LIST_ID = "task-widget:task-list-link:task-list-link";
  private final static String SHOW_ALL_CHARTS = "statistics-widget:statistic-link:statistic-link";
  
  @Override
  protected String getLoadedLocator() {
    return "id('statistics-widget:tasks-expiry-chart')";
  }

  public boolean isTaskMenuOpen() {
    return isElementDisplayed(By.id(TASK_MENU_ID + "_0"));
  }
  
  public boolean isShowAllProcessesDisplayed() {
    return isElementDisplayed(By.id(SHOW_ALL_PROCESSES_ID));
  }
  
  public boolean isShowFullTaskListDisplayed() {
    return isElementDisplayed(By.id(SHOW_FULL_TASK_LIST_ID));
  }
  
  public boolean isShowAllChartsDisplayed() {
    return isElementDisplayed(By.id(SHOW_ALL_CHARTS));
  }
}
