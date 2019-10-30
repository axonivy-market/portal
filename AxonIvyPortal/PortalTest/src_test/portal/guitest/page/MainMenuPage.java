package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class MainMenuPage extends TemplatePage {

  private final static String CASE_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_2";

  @Override
  protected String getLoadedLocator() {
    return "id('left-menu')";
  }

  public void moveToTaskList() {
    openTaskList();
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
    waitAjaxIndicatorDisappear();
    return new TaskWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    findElementByCssSelector("li.submenu-container:nth-child(5) > a.ripplelink.submenu").click();
    ensureNoBackgroundRequest();
    waitForPageLoaded();
    return new StatisticWidgetPage();
  }

  public TaskWidgetPage selectTaskCategory(String category) {
    List<WebElement> taskCategoryMenuItems = findListElementsByCssSelector("span.js-second-level-menu");
    for (WebElement item : taskCategoryMenuItems) {
      if (item.getText().equalsIgnoreCase(category)) {
        click(item);
        Sleeper.sleep(5000);
        return new TaskWidgetPage();
      }
    }
    return null;
  }

  public void expandTaskCategory(String firstCategory, String... categories) {
    String path = firstCategory;
    findElementByCssSelector(getCategoryTogglerCssSelector(path)).click();
    if (categories.length > 0) {
      for (String category : categories) {
        path = path.concat("\\/" + category);
        waitForElementPresent(By.cssSelector(getCategoryTogglerCssSelector(path)), true);
        findElementByCssSelector(getCategoryTogglerCssSelector(path)).click();
      }
    }
    Sleeper.sleep(1000);
  }

  public boolean isTaskCategoryPathExpandedAndHighlighted(String firstCategory, String... categories) {
    String path = firstCategory;
    if (!isElementDisplayed(By.cssSelector(getHighlightedCategoryTaskItemCssSelector(path)))) {
      return false;
    }
    if (categories.length > 0) {
      for (String category : categories) {
        path = path.concat("\\/" + category);
        if (!isElementDisplayed(By.cssSelector(getHighlightedCategoryTaskItemCssSelector(path)))) {
          System.out.println(getHighlightedCategoryTaskItemCssSelector(path));
          return false;
        }
      }
      return true;
    }
    return true;
  }

  private String getCategoryTogglerCssSelector(String path) {
    return ".second-level-menu-body .ui-treetable-data ." + path + " span.ui-treetable-toggler";
  }

  private String getHighlightedCategoryTaskItemCssSelector(String path) {
    return ".second-level-menu-body .ui-treetable-data ." + path + ".on";
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public boolean hasReadAllCasePermission() {
    WebElement plusIcon = getPlusIconOnCaseMenu();
    if (!plusIcon.isDisplayed()) {
      return false;
    }

    toggleCaseMenu();
    WebElement firstSubCaseMenuItem = findElementById(CASE_MENU_ID + "_0");
    String firstSubCaseMenuItemLable = findChildElementsByTagName(firstSubCaseMenuItem, "span").get(2).getText();

    WebElement secondSubCaseMenuItem = findElementById(CASE_MENU_ID + "_1");
    String secondSubCaseMenuItemLable = findChildElementsByTagName(secondSubCaseMenuItem, "span").get(2).getText();

    boolean isReadAllCasePermission = ("My Cases".equalsIgnoreCase(firstSubCaseMenuItemLable)
        && "All cases".equalsIgnoreCase(secondSubCaseMenuItemLable));

    return isReadAllCasePermission;
  }

  public CaseWidgetPage selectCaseMenu() {
    clickByCssSelector("li.submenu-container:nth-child(4) > a.ripplelink.submenu");
    return new CaseWidgetPage();
  }

  public void toggleCaseMenu() {
    getPlusIconOnCaseMenu().click();
    waitForElementDisplayed(By.id(CASE_MENU_ID + "_0"), true);
  }

  public boolean isMenuItemHighlighted(int menuItemPosition) {
    return findElementByCssSelector("a.left-sidebar-sub-menu-item:nth-of-type(" + menuItemPosition + ")")
        .getCssValue("opacity").equals("1");
  }

  private WebElement getPlusIconOnCaseMenu() {
    return findElementByXpath("id('" + CASE_MENU_ID + "')//span[contains(@class, 'ui-treetable-toggler')]");
  }
}
