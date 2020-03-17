package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class MainMenuPage extends TemplatePage {

  private final static String TASK_MENU_ICON_POSITION = "2";
  private final static String CASE_MENU_ICON_POSITION = "3";
  private final static String DASHBOARD_MENU_ICON_POSITION = "4";
  private final static String CASE_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_2";

  @Override
  protected String getLoadedLocator() {
    return "id('left-menu')";
  }

  public void moveToTaskList() {
    openTaskList();
  }

  public void toggleTaskMenu() {
	clickByCssSelector("a.second-level-menu-header");
    Sleeper.sleepTight(1000);
  }

  public void openTaskMenu() {
    if (!isTaskMenuOpen()) {
      toggleTaskMenu();
    }
  }

  public void closeTaskMenu() {
    if (isTaskMenuOpen()) {
      toggleTaskMenu();
    }
  }
  
  public ProcessWidgetPage selectProcessesMenu() {
	clickByCssSelector("span[class*='left-sidebar-sub-menu-item-0-0']");  
    waitForProcessesPageAfterSelectProcessesCategory();
    return new ProcessWidgetPage();
  }
  
  public TaskWidgetPage selectTaskMenu() {
    findElementByCssSelector("a.left-sidebar-sub-menu-item:nth-of-type(" + TASK_MENU_ICON_POSITION + ")").click();
    waitForTasksLoadedAfterSelectTaskCategory();
    return new TaskWidgetPage();
  }

  public TaskWidgetPage selectTaskCategory(String category) {
    List<WebElement> taskCategoryMenuItems = findListElementsByCssSelector("span.js-second-level-menu");
    for (WebElement item : taskCategoryMenuItems) {
      if (item.getText().equalsIgnoreCase(category)) {
        click(item);
        Sleeper.sleepTight(5000);
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
    Sleeper.sleepTight(1000);
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
	waitForElementDisplayed(By.id("process-widget:process-search"), true);  
  }
  
  private void waitForTasksLoadedAfterSelectTaskCategory() {
    waitForElementDisplayed(By.cssSelector("*[id$='0:task-item:task-action:task-delegate-command']"), true);
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    findElementByCssSelector("a.left-sidebar-sub-menu-item:nth-of-type(" + DASHBOARD_MENU_ICON_POSITION + ")").click();
    waitForElementDisplayed(By.id("statistics-widget:create-chart-link-label"), true);
    return new StatisticWidgetPage();
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

    boolean isReadAllCasePermission =
        ("My Cases".equalsIgnoreCase(firstSubCaseMenuItemLable) && "All cases"
            .equalsIgnoreCase(secondSubCaseMenuItemLable));

    return isReadAllCasePermission;
  }

  public CasePage selectCaseMenu() {
    findElementByCssSelector("a.left-sidebar-sub-menu-item:nth-of-type(" + CASE_MENU_ICON_POSITION + ")").click();
    return new CasePage();
  }

  public void toggleCaseMenu() {
    getPlusIconOnCaseMenu().click();
    waitForElementDisplayed(By.id(CASE_MENU_ID + "_0"), true);
  }

  public boolean isMenuItemHighlighted(int menuItemPosition) {
    return findElementByCssSelector("a.left-sidebar-sub-menu-item:nth-of-type(" + menuItemPosition + ")").getCssValue(
        "opacity").equals("1");
  }

  private WebElement getPlusIconOnCaseMenu() {
    return findElementByXpath("id('" + CASE_MENU_ID + "')//span[contains(@class, 'ui-treetable-toggler')]");
  }

  public boolean isTaskMenuOpen() {
    WebElement taskMenu = findElementById("second-level-menu");
    return taskMenu.getAttribute(CLASS_PROPERTY).contains(" on");
  }
}
