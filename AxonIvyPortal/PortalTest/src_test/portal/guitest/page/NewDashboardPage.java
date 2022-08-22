package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

public class NewDashboardPage extends TemplatePage {
  private static final String CONFIGURATION_DIALOG_ID = "new-widget-configuration-dialog";
  private static final String ADD_WIDGET_DIALOG_ID = "new-widget-dialog";
  private static final String ADD_CUSTOM_WIDGET_BUTTON_ID = "new-widget-dialog-content:4:add-widget";
  private static final String CUSTOM_WIDGET_TYPE_DROPDOWN_ID = "widget-configuration-form:new-widget-configuration-component:custom-widget-type_label";
  private static final String CUSTOM_WIDGET_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-process";
  
  @Override
  protected String getLoadedLocator() {
    return "id('dashboard-body')";
  }

  public void startTask(int index) {
    String cssSelector = String.format("a[id$='task-component:dashboard-tasks:%d:dashboard-tasks-columns:0:start-task']", index);
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

  public void waitForTaskWidgetEmptyMessage() {
    String cssSelector = "tbody[id*='task-component:dashboard-tasks_data']";
    waitForElementDisplayed(By.cssSelector(cssSelector), true);
    WebElement taskComponentTable = findElementByCssSelector(cssSelector);
    waitForElementDisplayed(taskComponentTable.findElement(By.cssSelector(".ui-datatable-empty-message")), true);
    
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

  public WebElement getFilterOverlayPanel(int index) {
    String panelSelector = "div[id$=':filter-overlay-panel-" + index +"']";
    waitForElementDisplayed(By.cssSelector(panelSelector), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-inputfield.saved-filter__search", CLASS_PROPERTY);
    return findElementByCssSelector(panelSelector);
  }

  public WebElement getAddWidgetDialog() {
    waitForElementDisplayed(By.id(ADD_WIDGET_DIALOG_ID), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "new-widget-dialog", ID_PROPERTY);
    return findElementById(ADD_WIDGET_DIALOG_ID);
  }

  public WebElement getInfoOverlayPanel(int index) {
    String panelSelector = "div[id$=':info-overlay-panel-" + index +"']";
    waitForElementDisplayed(By.cssSelector(panelSelector), true);
    return findElementByCssSelector(panelSelector);
  }

  public void clickWidgetFilter(int index) {
    findElementByCssSelector("a[id$='filter-sidebar-link-" + index + "']").click();
    waitForElementDisplayed(By.cssSelector("div[id$=':filter-form-" + index + ":widget-filter-content']"), true);
  }

  public void clickWidgetInfo(int index) {
    findElementByCssSelector("a[id$='info-sidebar-link-" + index + "']").click();
    waitForElementDisplayed(By.cssSelector("div[id$=':info-overlay-panel-" + index + "']"), true);
  }

  public void waitForTaskWidgetLoading() {
    WaitHelper.assertTrueWithWait(() -> {
      var taskLoading = findElementByCssSelector("[id$=':task-component:loading']");
      return taskLoading.getAttribute(CLASS_PROPERTY).contains("u-display-none");
    });
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-datatable.dashboard-tasks--table", CLASS_PROPERTY);
  }

  public void waitForProcessViewerWidgetLoading() {
    WaitHelper.assertTrueWithWait(() -> {
      var widgetLoading = findElementByCssSelector(".process-viewer-widget-panel [id$='loading']");
      return widgetLoading.getAttribute(CLASS_PROPERTY).contains("u-display-none");
    });
    driver.switchTo().frame("process-viewer");
    waitForElementDisplayed(By.id("sprotty"), true);
    driver.switchTo().defaultContent();
  }

  public void waitForWidgetInfoLoading(WebElement taskInfoOverlayPanel) {
    WaitHelper.assertTrueWithWait(() -> {
      var widgetInfo = taskInfoOverlayPanel.findElements(By.cssSelector("[class*='js-loading-']")).get(0);
      return widgetInfo.getAttribute(CLASS_PROPERTY).contains("u-display-none");
    });
  }

  public WebElement getProcessViewerWidget() {
    return findElementByCssSelector("div[gs-id*='process_viewer']");
  }
}
