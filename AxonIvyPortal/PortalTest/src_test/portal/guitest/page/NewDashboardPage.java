package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewDashboardPage extends TemplatePage {
  private static final String CONFIGURATION_DIALOG_ID = "new-widget-configuration-dialog";
  private static final String ADD_CUSTOM_WIDGET_BUTTON_ID = "new-widget-dialog-content:4:add-widget";
  private static final String CUSTOM_WIDGET_TYPE_DROPDOWN_ID = "widget-configuration-form:new-widget-configuration-component:custom-widget-type_label";
  private static final String CUSTOM_WIDGET_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-process";
  
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

  public void switchToEditMode() {
    waitForElementDisplayed(By.cssSelector("button[id='switch-to-edit-mode']"), true);
    click(By.cssSelector("button[id='switch-to-edit-mode']"));
  }
  
  public void clickAddWidget() {
    waitForElementDisplayed(By.cssSelector("button[id='add-button']"), true);
    click(By.cssSelector("button[id='add-button']"));
  }
  
  public void addNewCustomWidget() {
    clickAddWidget();
    waitForElementDisplayed(By.id("new-widget-dialog"), true);
    click(By.id(ADD_CUSTOM_WIDGET_BUTTON_ID));
    waitForElementDisplayed(By.id("new-widget-configuration-dialog"), true);
  }
  
  public void selectCustomWidgetTypeProcess() {
    waitForElementDisplayed(By.id(CUSTOM_WIDGET_TYPE_DROPDOWN_ID), true);
    click(By.id(CUSTOM_WIDGET_TYPE_DROPDOWN_ID));
    waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:custom-widget-type_items"), true);
    WebElement typePanel = findElementById("widget-configuration-form:new-widget-configuration-component:custom-widget-type_items");
    click(By.id(typePanel.findElement(By.cssSelector("li:nth-child(2)")).getAttribute("id")));
  }
  
  public void selectIvyProcessForCustomWidget(String keyword) {
    waitForElementDisplayed(By.id(CUSTOM_WIDGET_PROCESS_SELECTION_ID), true);
    WebElement processNameTextField = findElementById("widget-configuration-form:new-widget-configuration-component:selected-process_input");
    type(processNameTextField, keyword);
    waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:selected-process_panel"), true);
    click(By.xpath("//*[@id='widget-configuration-form:new-widget-configuration-component:selected-process_panel']/table/tbody/tr"));
    waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:parameters-fieldset"), true);
  }

  public WebElement getConfigurationDialog() {
    waitForElementDisplayed(By.id(CONFIGURATION_DIALOG_ID), true);
    return findElementById(CONFIGURATION_DIALOG_ID);
  }
}
