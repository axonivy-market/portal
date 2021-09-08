package portal.guitest.page;

public class NewDashboardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('dashboard-body')";
  }
}
