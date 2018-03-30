package portal.guitest.page;

import org.openqa.selenium.support.Color;


public class HomePage extends TemplatePage {

  public final static String PORTAL_HOME_PAGE_URL = "portalTemplate/1549F58C18A6C562/PortalStart.ivp";
  public final static String INTERNAL_SUPPORT_HOME_PAGE_URL = "internalSupport/15B0ED067061F4DE/PortalStart.ivp";
  public final static String PORTAL_EXAMPLES_HOME_PAGE_URL = "portalExamples/1624CFBB822FB1C9/PortalStart.ivp";
  public final static String TASK_SWITCH_MODE_BTN_LOCATOR = "id('task-widget:task-list-link:task-list-link')";

  @Override
  protected String getLoadedLocator() {
    return TASK_SWITCH_MODE_BTN_LOCATOR;
  }

  public TaskWidgetPage getTaskWidget() {
    return new TaskWidgetPage();
  }

  public ProcessWidgetPage getProcessWidget() {
    return new ProcessWidgetPage();
  }

  public StatisticWidgetPage getStatisticsWidget() {
    return new StatisticWidgetPage();
  }
  
  public String getMainColor() {
    return Color.fromString(findElementById("app-menu-panel").getCssValue("background-color")).asHex().substring(1);
  }
}
