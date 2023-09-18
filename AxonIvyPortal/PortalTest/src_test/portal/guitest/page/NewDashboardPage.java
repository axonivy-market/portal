package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

public class NewDashboardPage extends TemplatePage {
  private static final String CONFIGURATION_DIALOG_ID = "new-widget-configuration-dialog";
  private static final String ADD_WIDGET_DIALOG_ID = "new-widget-dialog";
  private static final String ADD_CUSTOM_WIDGET_BUTTON_ID = "new-custom-widget-dialog-content:0:add-widget";
  private static final String ADD_EXTERNAL_PAGE_WIDGET_BUTTON_ID = "new-widget-dialog-content:1:add-widget";
  private static final String CUSTOM_WIDGET_TYPE_DROPDOWN_ID = "widget-configuration-form:new-widget-configuration-component:custom-widget-type_label";
  private static final String CUSTOM_WIDGET_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-process";
  private static final String NEWS_FEED_WIDGET_ID = "[class*='js-dashboard-widget-news_']";
  private static final String NEWS_FEED_TITLE_INPUT_ID = "input[id$=':manage-news-tabview:0:news-title']";
  private static final String MANAGE_NEWS_TABVIEW_FORMAT = "[id$=':manage-news-tabview:%s:%s']";
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
  
  public void addNewExternalPageWidget() {
    clickAddWidget();
    waitForElementDisplayed(By.id("new-widget-dialog"), true);
    click(By.id(ADD_EXTERNAL_PAGE_WIDGET_BUTTON_ID));
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
    findElementByCssSelector("[id$='filter-sidebar-link-" + index + "']").click();
    waitForElementDisplayed(By.cssSelector("div[id$=':filter-form-" + index + ":widget-filter-content']"), true);
  }

  public void clickWidgetInfo(int index) {
    findElementByCssSelector("[id$='info-sidebar-link-" + index + "']").click();
    waitForElementDisplayed(By.cssSelector("div[id$=':info-overlay-panel-" + index + "']"), true);
  }

  public void waitForTaskWidgetLoading() {
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

  public void waitForStatisticChartWidgetLoading() {
    WaitHelper.assertTrueWithWait(() -> {
      var taskLoading = findElementByCssSelector(".statistic-chart-widget__content [id$=':loading']");
      return taskLoading.getAttribute(CLASS_PROPERTY).contains("u-display-none");
    });
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-chart statistic-chart-widget__canvas", CLASS_PROPERTY);
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

  public WebElement getStatisticChartWidget() {
    return findElementByCssSelector("div[gs-id*='statistic']");
  }

  public WebElement getFirstNewsFeedWidget() {
    waitForElementDisplayed(By.cssSelector(NEWS_FEED_WIDGET_ID), true);
    return findElementByCssSelector(NEWS_FEED_WIDGET_ID);
  }

  public void waitForNewsWidgetLoadedData() {
    getFirstNewsFeedWidget();
    WaitHelper.assertTrueWithWait(() -> {
      var widgetLoading = findElementByCssSelector(NEWS_FEED_WIDGET_ID + " [id*=':loading-news_']");
      return widgetLoading.getAttribute(CLASS_PROPERTY).contains("u-display-none");
    });
    waitForElementDisplayed(By.cssSelector("[class*='js-loading-news_']"), false);
    waitForElementDisplayed(By.cssSelector("[id$=':news-widget__content']"), true);
  }

  public void openManageNewsDialog() {
    waitForNewsWidgetLoadedData();
    waitForElementDisplayed(By.cssSelector("a[id$=':add-news-button']"), true);
    clickByCssSelector("a[id$=':add-news-button']");
    waitForElementDisplayed(By.cssSelector("[id$=':manage-news-dialog']"), true);
    waitForElementDisplayed(By.cssSelector(".management-news__title-input"), true);
    waitForElementReallyDisplayed(By.cssSelector(NEWS_FEED_TITLE_INPUT_ID), true);
  }

  public WebElement getManageNewsDialog() {
    waitForElementDisplayed(By.cssSelector("[id$=':manage-news-dialog']"), true);
    waitForElementDisplayed(By.cssSelector(".management-news__title-input"), true);
    return findElementByCssSelector("[id$=':manage-news-dialog']");
  }

  public void enterNewsTitle(String newsTitle) {
    waitForElementReallyDisplayed(By.cssSelector(NEWS_FEED_TITLE_INPUT_ID), true);
    WebElement newsTitleInput = findElementByCssSelector(NEWS_FEED_TITLE_INPUT_ID);
    newsTitleInput.clear();
    newsTitleInput.sendKeys(newsTitle);
  }

  public String selectNewsLanguage(String languageTag) {
    var languageTabClass = "li.ui-tabs-header.news-language-tab-" + languageTag;
    findElementByCssSelector(languageTabClass).click();
    return findElementByCssSelector(languageTabClass).getAttribute("data-index");
  }

  public void clickOnTitle(String tabIndex) {
    String tabLanguage = "input" + String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-title");
    waitForElementDisplayed(By.cssSelector(tabLanguage), true);
    findElementByCssSelector(tabLanguage).click();
  }

  public WebElement getTranslationOverlayPanel(int index) {
    WebElement translationOverlay = findElementByCssSelector(
        String.format("div[id$=':%s:overlay-panel-input']", index));
    waitForElementDisplayed(translationOverlay, true);
    return translationOverlay;
  }

  public void findTranslationButton(String tabIndex) {
    findElementByCssSelector(String.format("[id$=':%s:translate-language-button']", tabIndex)).click();
  }
}
