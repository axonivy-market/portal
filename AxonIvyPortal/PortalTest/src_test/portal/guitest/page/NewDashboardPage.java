package portal.guitest.page;

import org.openqa.selenium.By;

public class NewDashboardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('dashboard-body')";
  }

  public void startTask(int index) {
    String cssSelector = String.format("a[id*='task-component:dashboard-tasks:%d']", index);
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
    click(By.cssSelector(cssSelector));
  }

  public void waitForTaskNonStartButtonDisplay(int index) {
    String cssSelector = String.format("span[id*='task-component:dashboard-tasks:%d']", index);
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
  }

  public void waitForTaskStartButtonDisplay(int index) {
    String cssSelector = String.format("a[id*='task-component:dashboard-tasks:%d']", index);
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
  }
}
