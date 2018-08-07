package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StatisticWidgetPage extends TemplatePage {
  private WebElement statisticWidget;

  public StatisticWidgetPage() {
    statisticWidget = findElementById("statistics-widget");
  }

  public WebElement getStatisticWidget() {
    return statisticWidget;
  }

  public boolean isCompactMode() {
    waitForElementDisplayed(By.id("statistics-widget:tasks-priority-chart"), true, DEFAULT_TIMEOUT);
    WebElement priorityChart = findElementById("statistics-widget:tasks-priority-chart");
    return priorityChart.isDisplayed();
  }

  public void switchMode() {
    WebElement switchLink = findElementById("statistics-widget:switch-mode-command-value");
    switchLink.click();
  }

  public boolean isFullMode() {
    waitForElementDisplayed(By.id("statistics-widget:tasks-priority-chart"), true, DEFAULT_TIMEOUT);
    waitForElementDisplayed(By.id("statistics-widget:tasks-expiry-chart"), true, DEFAULT_TIMEOUT);

    WebElement priorityChart = findElementById("statistics-widget:tasks-priority-chart");
    WebElement expiryChart = findElementById("statistics-widget:tasks-expiry-chart");
    return priorityChart.isDisplayed() && expiryChart.isDisplayed();
  }
}
