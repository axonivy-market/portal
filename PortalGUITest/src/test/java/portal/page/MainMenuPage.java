package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends TemplatePage {

  private final static String TASK_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_1";
  private final static String CASE_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_2";
  private final static String DASHBOARD_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_3";

  @Override
  protected String getLoadedLocator() {
    return "id('js-main-menu-panel')";
  }

  public void toggleTaskMenu() {
    findElementByXpath("id('" + TASK_MENU_ID + "')//span[contains(@class, 'ui-treetable-toggler')]").click();
    waitForElementDisplayed(By.id(TASK_MENU_ID + "_0"), true);
  }

  public TaskWidgetPage selectTaskMenu() {
    findElementByCssSelector("tr[id='" + TASK_MENU_ID + "'] > td").click();
    waitForTasksLoadedAfterSelectTaskCategory();
    return new TaskWidgetPage();
  }

  public TaskWidgetPage selectTaskCategory(String category) {
    List<WebElement> taskCategoryMenuItems = findListElementsByCssSelector("tr[id^='" + TASK_MENU_ID + "_']");
    for (WebElement item : taskCategoryMenuItems) {
      if (item.getText().equalsIgnoreCase(category)) {
        click(findChildElementByTagName(item, "td"));
        waitForTasksLoadedAfterSelectTaskCategory();
        return new TaskWidgetPage();
      }
    }
    return null;
  }

  private void waitForTasksLoadedAfterSelectTaskCategory() {
    waitForElementPresent(By.cssSelector("*[id$='0:task-delegate-command']"), true);
  }

  public DashboardPage selectDashboard() {
    WebElement dashboardMenu = findElementById(DASHBOARD_MENU_ID);
    click(findChildElementByTagName(dashboardMenu, "td"));
    return new DashboardPage();
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
    WebElement caseMenuItem = findElementById(CASE_MENU_ID);
    findChildElementByTagName(caseMenuItem, "td").click();
    return new CasePage();
  }

  public void toggleCaseMenu() {
    getPlusIconOnCaseMenu().click();
    waitForElementDisplayed(By.id(CASE_MENU_ID + "_0"), true);
  }

  private WebElement getPlusIconOnCaseMenu() {
    return findElementByXpath("id('" + CASE_MENU_ID + "')//span[contains(@class, 'ui-treetable-toggler')]");
  }
}
