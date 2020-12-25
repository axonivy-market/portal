package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends TemplatePage {
  
  private static String PROCESS_MENU_ITEM_CSS_SELECTOR = ".layout-menu li[role='menuitem'] a.ripplelink.PROCESS";

  @Override
  protected String getLoadedLocator() {
    return "id('left-menu')";
  }

  public boolean isProcessesDisplayed() {
    return isMenuItemDisplayed("Processes");
  }

  private boolean isMenuItemDisplayed(String menuItemName) {
    return findListElementsByCssSelector("li[role='menuitem']").stream()
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
    clickByCssSelector(".layout-menu li[role='menuitem'] a.ripplelink.TASK");
    ensureNoBackgroundRequest();
    return new TaskWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    clickByCssSelector(".layout-menu li[role='menuitem'] a.ripplelink.STATISTICS");
    waitForElementDisplayed(By.className("statistic-dashboard-expand-mode"), true);
    return new StatisticWidgetPage();
  }

  public void clickThirdPartyAppByIndex(int index) {
    String thirdpartyCssSelector = String.format("li[class*='thirdparty-menu-item-%d'] > a", index);
    waitForElementDisplayed(By.cssSelector(thirdpartyCssSelector), true);
    clickByCssSelector(thirdpartyCssSelector);
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public CaseWidgetPage selectCaseMenu() {
    clickByCssSelector(".layout-menu li[role='menuitem'] a.ripplelink.CASE");
    return new CaseWidgetPage();
  }

  public WorkingTaskDialogPageOfApplicationMenu selectDashboardMenu() {
    clickByCssSelector(".layout-menu li[role='menuitem'] a.ripplelink.DASHBOARD");
    return new WorkingTaskDialogPageOfApplicationMenu();
  }

  public String getProcessMenuItemText() {
    openMainMenu();
    return findElementByCssSelector(PROCESS_MENU_ITEM_CSS_SELECTOR).getText();
  }

  public HomePage backToHomeByClickOnBreadcrumb() {
    click(findElementByCssSelector("a.ui-menuitem-link.ui-icon.ui-icon-home"));
    return new HomePage();
  }
}
