package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
public class NewDashboardDetailsEditPage extends TemplatePage {

  public static final String TASK_WIDGET = "Task List";
  public static final String CASE_WIDGET = "Case List";
  public static final String PROCESS_WIDGET = "Process List";
  public static final String PROCESS_VIEWER_WIDGET = "Process Viewer";
  public static final String CUSTOM_WIDGET = "Custom Widget";
  public static final String WELCOME_WIDGET = "Welcome widget";
  public static final String NEWS_WIDGET = "News feed widget";
  public static final String TASK_BY_PRIORITY = "Tasks by Priority";
  public static final String NOTIFICATION_WIDGET = "Notifications";

  @Override
  protected String getLoadedLocator() {
    return "#add-button";
  }

  public WebElement addWidget() {
    $("button[id='add-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("div[id$='new-widget-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void collapseStandardWidgets() {
    $(".ui-fieldset-legend.ui-corner-all.ui-state-default").click();
    $(".ui-fieldset-content").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public TaskEditWidgetNewDashBoardPage addNewTaskWidget() {
    addWidgetByName("Task List");
    $("div[id$=':task-widget-preview:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition());
    return new TaskEditWidgetNewDashBoardPage();
  }

  public CaseEditWidgetNewDashBoardPage addNewCaseWidget() {
    addWidgetByName("Case List");
    $("div[id$=':case-widget-preview:dashboard-cases']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition());
    return new CaseEditWidgetNewDashBoardPage();
  }

  public ProcessEditWidgetNewDashBoardPage addNewProcessWidget() {
    addWidgetByName("Process List");
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public ProcessViewerWidgetNewDashBoardPage addNewProcessViewerWidget() {
    addWidgetByName("Process Viewer");
    return new ProcessViewerWidgetNewDashBoardPage();
  }

  public CustomWidgetNewDashBoardPage addNewCustomrWidget(String processName) {
    addCustomWidgetByName(processName);
    return new CustomWidgetNewDashBoardPage();
  }

  public CustomWidgetNewDashBoardPage addExternalPageWidget() {
    addWidgetByName("External Page");
    return new CustomWidgetNewDashBoardPage();
  }

  public void addWidgetByName(String name) {
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.new-widget-dialog__item")
        .filter(text(name)).first().$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition())
        .click();
  }

  public void addStatisticWidgetByName(String name) {
    $("[id $= 'search-input']").shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(name);
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.new-widget-dialog__item").filter(text(name)).first()
        .$("button[id^='new-statistic-widget-dialog-content']").shouldBe(getClickableCondition()).click();
    $("[id='new-widget-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  private void addCustomWidgetByName(String name) {
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.new-widget-dialog__item")
        .filter(text(name)).first().$("button[id^='new-custom-widget-dialog-content']")
        .shouldBe(getClickableCondition()).click();
  }

  public DashboardConfigurationPage backToConfigurationPage() {
    clickByJavaScript($("[id='back-to-configuration']"));
    return new DashboardConfigurationPage();
  }

  public void deleteCompactModeProcess() {
    $("[id$=':delete-widget-2']").shouldBe(Condition.appear).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteImageModeProcess() {
    getProcessActionMenu().shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("span.si-bin-1")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  private SelenideElement getProcessActionMenu() {
    if (!$("[id$=':process-action-menu']").isDisplayed()) {
      $("button[id$=':process-action-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
      .click();
    }
    return $("div[id$='process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("[id$=':process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("span.si-bin-1")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteCombinedModeProcess() {
    try {
      openDeleteCombinedModeProcessDialog();
    } catch (Exception e) {
      openDeleteCombinedModeProcessDialog();
    }
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void clickOnRemoveWidgetButton() {
    getRemoveWidgetButton().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private void openDeleteCombinedModeProcessDialog() {
    $(".process-grid-item__action--combined .si-bin-1").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editFullModeProcess() {
    getProcessActionMenu();
    $("[id$=':process-item:grid-process-action-component:edit-process']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.si-graphic-tablet-drawing-pen").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }

  private SelenideElement getRemoveWidgetDialog() {
    return $("div[id='remove-widget-dialog']");
  }

  private SelenideElement getRemoveWidgetButton() {
    return $("button[id='remove-widget-button']");
  }

  public StatisticEditWidgetNewDashboardPage addNewStatisticWidget() {
    addWidgetByName("Statistic chart");
    return new StatisticEditWidgetNewDashboardPage();
  }

  public SelenideElement getTitleByIndex(int index) {
    return $("a[id='dashboard-title-" + index + "']");
  }

  public SelenideElement getIconByIndex(int index, String icon) {
    return $("a[id='dashboard-title-" + index + "'] span." + icon);
  }

  public ElementsCollection getWidgets() {
    return $("div[id='dashboard-body']").$$("div.grid-stack-item");
  }

  public void clickOnRestoreDashboard() {
    $("[id$='restore-button-group']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .shouldBe(Condition.enabled, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getRestoreDashboardMessage() {
    return $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("span.dashboard-template-name-message").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void restoreDashboardToStandard() {
    $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='reset-dashboard-button']")
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getRestoreDashboardButton() {
    return $("[id$='restore-button-group']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }

  public DashboardNewsWidgetConfigurationPage addNewsFeedWidget() {
    addWidgetByName("News feed widget");
    return new DashboardNewsWidgetConfigurationPage();
  }

  public DashboardNotificationWidgetConfigurationPage addNotificationWidget() {
    addWidgetByName(NOTIFICATION_WIDGET);
    return new DashboardNotificationWidgetConfigurationPage();
  }

  public void waitForCaseWidgetLoaded() {
    $("div[id$='dashboard-cases-container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='dashboard-cases']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void clickToAddStatisticWidget() {
    $("button[id$='new-statistic-widget-dialog-content:0:add-widget']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    
  }

  public ElementsCollection countStatisticCharts() {
    return $("div[id='dashboard-body']").$$(".statistic-chart-widget__chart");
  }

  public SelenideElement getStatisticWidgetConfigurationDialog() {
    $("[id$=':case-component:dashboard-cases']").shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement statisticElement =
        $("div[id^='statistic-client_statistic']").shouldBe(appear, DEFAULT_TIMEOUT).scrollTo();
    $("canvas").shouldBe(appear, DEFAULT_TIMEOUT);
    return statisticElement;
  }
  
  public void waitForTaskWidgetLoaded() {
    $("div[id$='dashboard-tasks-container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='dashboard-tasks']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void editWidgetById(int id) {
    $(String.format("[id$=':edit-widget-%s']", id)).shouldBe(getClickableCondition()).click();
  }
  
  public void scrollToStatistic() {
    $(byText("Statistic Widgets")).shouldBe(Condition.appear, DEFAULT_TIMEOUT).scrollIntoView("{block: \"start\", inline: \"start\"}");
  }
  
  public SelenideElement getAccessibilityWidget() {
    return $("[id^='id-custom-widget-iframe']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void openCloneFromDialog() {
    $("div.new-widget-dialog__content").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id $= 'clone-from']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    $("[id $= 'clone-widget-from-dashboard:clone-widget-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void fillCloneWidgetDialog(String dashboardName, String widgetName) {
    $("[id $= 'clone-widget-from-dashboard:clone-widget-form:clone-dashboard']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id $= 'clone-widget-from-dashboard:clone-widget-form:clone-dashboard_items']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("li")
        .filter(Condition.text(dashboardName)).get(0).click();

    $("[id $= 'clone-widget-from-dashboard:clone-widget-form:clone-widget']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id $= 'clone-widget-from-dashboard:clone-widget-form:clone-widget_items']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("li")
        .filter(Condition.text(widgetName)).get(0).click();
  }

  public void cloneWidgetFromDashboard(String dashboardName, String widgetName,
      String newWidgetTitle, boolean isStatisticWidget) {
    openCloneFromDialog();

    fillCloneWidgetDialog(dashboardName, widgetName);

    if (isStatisticWidget) {
      $("[id $= 'clone-widget-from-dashboard:clone-button-statistic']")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    } else {
      $("[id $= 'clone-widget-from-dashboard:clone-button']")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }

    $("[id $= 'clone-widget-from-dashboard:clone-widget-dialog']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);

    if (isStatisticWidget) {
      return;
    }

    $("[id $= 'new-widget-configuration-dialog']").shouldBe(appear,
        DEFAULT_TIMEOUT);

    String titleInputQuery = "[id $= 'widget-configuration-form:new-widget-configuration-component:widget-title']";
    $(titleInputQuery).shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $(titleInputQuery).shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(newWidgetTitle);

    $("[id $= 'widget-configuration-save-button']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id $= 'new-widget-configuration-dialog']").shouldBe(disappear,
        DEFAULT_TIMEOUT);
  }

  public boolean hasWidgetWithName(String name) {
    return $$("span.widget__header-title").asFixedIterable().stream()
        .filter(widgetTitle -> widgetTitle.is(Condition.text(name))).findFirst()
        .isPresent();
  }

  public void openCloneWidgetDialog(String name, int index) {
    SelenideElement titleElem = $$("span.widget__header-title")
        .filter(Condition.text(name)).get(0);
    titleElem.parent().$(String.format("[id$='clone-widget-%d']", index))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeCloneWidgetDialog() {
    $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-widget-header a")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void chooseDashboardToClone(String dashboardName) {
    $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT);

    $("[id$='clone-widget-component:clone-widget-form:clone-dashboard']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id$='clone-widget-component:clone-widget-form:clone-dashboard_items']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("li")
        .filter(Condition.text(dashboardName)).get(0).click();
  }

  public void openWelcomeWidgetActionMenu(String widgetId) {
    $(String.format(
        "[id $= 'welcome-%s:welcome-widget-action-group-form:welcome-widget-action-button']",
        widgetId)).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $(String.format(
        "a[id $= '%s:welcome-widget-action-group-form:clone-welcome-widget']",
        widgetId))
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getWelcomeWidgetActionMenu(String widgetId) {
    return $(String.format(
        "[id $= '%s:welcome-widget-action-group-form:welcome-widget-action-menu']",
        widgetId)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCloneButtonByIndex(int index) {
    return $(String.format("[id$=':clone-widget-%d']", index))
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void waitCloneButtonClickable() {
    $("[id$='clone-widget-component:clone-button']")
        .shouldNot(Condition.cssClass("ui-state-disabled"), DEFAULT_TIMEOUT);
    return;
  }

  public void waitCloneFromButtonClickable() {
    $("[id$='clone-widget-from-dashboard:clone-button']")
        .shouldNot(Condition.cssClass("ui-state-disabled"), DEFAULT_TIMEOUT);
    return;
  }

  public SelenideElement getNewWidgetDialog() {
    return $("[id$='new-widget-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCloneWidgetDialog() {
    return $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCloneWidgetFromDialog() {
    return $("[id $= 'clone-widget-from-dashboard:clone-widget-dialog']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void cloneWidget(String dashboardName) {
    chooseDashboardToClone(dashboardName);

    $("[id$='clone-widget-component:clone-button']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='clone-widget-component:clone-to-dashboard-dialog']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}
