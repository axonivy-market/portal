package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

public class MainMenuPage extends TemplatePage {
  
  private static String PROCESS_MENU_ITEM_CSS_SELECTOR = ".layout-menu li[role='menuitem'] a.PROCESS";

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
    WaitHelper.waitForNavigation(this, () -> clickByCssSelector(".layout-menu li[role='menuitem'] a.TASK"));
    return new TaskWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    WaitHelper.waitForNavigation(this, () -> clickByCssSelector(".layout-menu li[role='menuitem'] a.STATISTICS"));
    return new StatisticWidgetPage();
  }

  public void clickThirdPartyApp() {
    waitForElementDisplayed(By.cssSelector("li[class*='thirdparty-menu-item'] > a"), true);
    clickByCssSelector("li[class*='thirdparty-menu-item'] > a");
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public CaseWidgetPage selectCaseMenu() {
    WaitHelper.waitForNavigation(this, () -> clickByCssSelector(".layout-menu li[role='menuitem'] a.CASE"));
    return new CaseWidgetPage();
  }

  public WorkingTaskDialogPageOfApplicationMenu selectDashboardMenu() {
    clickByCssSelector(".layout-menu li[role='menuitem'] a.DASHBOARD");
    return new WorkingTaskDialogPageOfApplicationMenu();
  }

  public String getProcessMenuItemText() {
    openMainMenu();
    return findElementByCssSelector(PROCESS_MENU_ITEM_CSS_SELECTOR).getText();
  }

  public HomePage backToHomeByClickOnBreadcrumb() {
    WaitHelper.waitForNavigation(this, () -> clickByCssSelector("a.ui-icon-home"));
    return new HomePage();
  }
}
