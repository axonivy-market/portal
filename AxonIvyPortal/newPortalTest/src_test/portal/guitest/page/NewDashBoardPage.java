package portal.guitest.page;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashBoardPage extends TemplatePage {

  // WIDGET SELECTOR
  private static final String WIDGET_CONTAINER_ID = "#grid-stack";
  private static final String WIDGET_HEADER_TITLE_CLASS = ".widget__header-title";
  private static final String WIDGET_HEADER_COLUMNS_CLASS = "table thead tr th";
  private static final String WIDGET_COLUMNS_CLASS = "table tbody tr td";
  private static final String WIDGET_ROWS_CLASS = "table tbody tr";
  private static final String DASHBOARD_CASE_TABLE_CLASS = ".dashboard-cases--table";
  private static final String DASHBOARD_TASK_TABLE_CLASS = ".dashboard-tasks--table";

  // ATTRIBUTE
  private static final String INNER_HTML = "innerHTML";

  // FILTER WIDGET
  private static final String FILTER_TASK_NAME = "Task name";
  private static final String APPLY_BUTTON = "Apply";

  // TIME OUT
  private final long DEFAULT_TIMEOUT = getTimeOutForLocator() * 1000;
  
  private SelenideElement getWidgetContainer() {
    return $(WIDGET_CONTAINER_ID);
  }
  
  private int getIndexWidgetByColumn(String columnName, String widgetName) {
    ElementsCollection elementsTH =
        getWidgetContainer().$(widgetName).waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_HEADER_COLUMNS_CLASS);
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }
  
  private ElementsCollection getColumnsOfTableWidget(String widgetName) {
    return getWidgetContainer().$(widgetName).waitUntil(appear, DEFAULT_TIMEOUT)
        .$$(WIDGET_COLUMNS_CLASS);
  }
  
  private ElementsCollection getColumnOfTableWidget(String widgetName,int rowIndex) {
    return getWidgetContainer().$(widgetName).waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_ROWS_CLASS)
        .get(rowIndex).$$("td");
  }
  
  public ElementsCollection getWidget(String widgetName) {
    return  getWidgetContainer().waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_HEADER_TITLE_CLASS)
        .filter(attribute(INNER_HTML, widgetName));
  }

  public ElementsCollection getCasesOfCaseWidgetHasName(String caseName) {
    return getColumnsOfTableWidget(DASHBOARD_CASE_TABLE_CLASS).filter(text(caseName));
  }

  public SelenideElement getCaseOfCaseWidgetHasIndex(int index) {
    return getColumnsOfTableWidget(DASHBOARD_CASE_TABLE_CLASS).get(index);
  }

  public SelenideElement getColumnOfCaseHasIndex(int index,String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName,DASHBOARD_CASE_TABLE_CLASS);
    return getColumnOfTableWidget(DASHBOARD_CASE_TABLE_CLASS,0).get(startIndex);
  }

  public ElementsCollection countRelatedTasks() {
    return $("div[id$='related-tasks']").$$("td.related-task-name-column");
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public SelenideElement getFilterWidget(String widgetName) {
    return  getWidgetContainer().$$(".table-widget-form").filter(text(widgetName)).first()
        .$(".widget__filter-sidebar-link").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFilterTasksName(String widgetName) {
    return $(".filter-overlay-panel").waitUntil(appear, DEFAULT_TIMEOUT).$(".filter-overlay-panel__content")
        .$$(".widget-filter-panel .ui-g").filter(text(FILTER_TASK_NAME)).first().$(".ui-inputfield");
  }

  public SelenideElement getApplyButtonFilter(String widgetName) {
    return $(".filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT).$$("button").filter(text(APPLY_BUTTON))
        .first();
  }

  public SelenideElement getTaskOfTaskWidgetByIndex(int index) {
    int startIndex = getIndexWidgetByColumn("Start",DASHBOARD_TASK_TABLE_CLASS);
    return  getWidgetContainer().$(DASHBOARD_TASK_TABLE_CLASS).waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_ROWS_CLASS)
        .get(index).$$("td").get(startIndex);
  }

  public SelenideElement getDestroyLink() {
    return $("a[id$='destroy-case-link']");
  }
  
  public SelenideElement getConfirmDestroyButton() {
    return $("div[id$='destroy-case-confirmation-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction']");
  }
}
