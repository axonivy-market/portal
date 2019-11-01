package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('left-menu')";
  }

  public boolean isProcessesDisplayed() {
    return isMenuItemDisplayed("Processes");
  }

  private boolean isMenuItemDisplayed(String menuItemName) {
    return findListElementsByCssSelector("li.submenu-container").stream()
        .filter(element -> element.getText().equals(menuItemName)).findFirst().map(WebElement::isDisplayed)
        .orElse(false);
  }

  // When hiding all 4 sub menu items: Processes, Cases, Tasks and Statistics.
  public boolean isTasksDisplayed() {
    return isMenuItemDisplayed("Tasks");
  }

  public boolean isCasesDisplayed() {
    return isMenuItemDisplayed("Cases");
  }

  public boolean isStatisticsDisplayed() {
    return isMenuItemDisplayed("Statistics");
  }

  public ProcessWidgetPage selectProcessesMenu() {
    findElementByCssSelector("li.submenu-container:nth-child(2) > a.ripplelink.submenu").click();
    waitForProcessesPageAfterSelectProcessesCategory();
    return new ProcessWidgetPage();
  }

  public TaskWidgetPage selectTaskMenu() {
    findElementByCssSelector("li.submenu-container:nth-child(3) > a.ripplelink.submenu").click();
    return new TaskWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    findElementByCssSelector("li.submenu-container:nth-child(5) > a.ripplelink.submenu").click();
    ensureNoBackgroundRequest();
    waitForPageLoaded();
    return new StatisticWidgetPage();
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public CaseWidgetPage selectCaseMenu() {
    clickByCssSelector("li.submenu-container:nth-child(4) > a.ripplelink.submenu");
    return new CaseWidgetPage();
  }

}
