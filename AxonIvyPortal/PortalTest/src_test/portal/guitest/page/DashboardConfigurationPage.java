package portal.guitest.page;

public class DashboardConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('dashboard-form')";
  }

  public void configureDashboardByIndex(int index) {
    String configIconId = String.format("dashboard-form:dashboard-table:%d:configure-dashboard", index);
    findElementById(configIconId).click();
  }
}
