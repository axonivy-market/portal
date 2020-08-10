package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends TemplatePage {
  
  private static String PROCESS_MENU_ITEM_CSS_SELECTOR = "li.submenu-container:nth-child(2) > a.ripplelink.submenu";

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
    clickByCssSelector(PROCESS_MENU_ITEM_CSS_SELECTOR);
    waitForProcessesPageAfterSelectProcessesCategory();
    return new ProcessWidgetPage();
  }

  public TaskWidgetPage selectTaskMenu() {
    clickByCssSelector("li.submenu-container:nth-child(3) > a.ripplelink.submenu");
    ensureNoBackgroundRequest();
    return new TaskWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    clickByCssSelector("li.submenu-container:nth-child(5) > a.ripplelink.submenu");
    waitForElementDisplayed(By.className("statistic-dashboard-expand-mode"), true);
    return new StatisticWidgetPage();
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public CaseWidgetPage selectCaseMenu() {
    clickByCssSelector("li.submenu-container:nth-child(4) > a.ripplelink.submenu");
    return new CaseWidgetPage();
  }
  
  public String getProcessMenuItemText() {
    openMainMenu();
    return findElementByCssSelector(PROCESS_MENU_ITEM_CSS_SELECTOR).getText();
  }

}
