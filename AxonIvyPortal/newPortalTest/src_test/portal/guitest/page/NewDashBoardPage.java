package portal.guitest.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashBoardPage extends TemplatePage {

  // WIDGET SELECTOR
  private static final String WIDGET_CONTAINER = "#grid-stack";
  private static final String WIDGET_HEADER_TITLE = "span.widget__header-title";
  private static final String WIDGET_HEADER_COLUMNS = "table thead tr th";
  private static final String WIDGET_COLUMNS_CLASS = "table tbody tr td";
  private static final String WIDGET_ROWS_CLASS = "table tbody tr";
  private static final String DASHBOARD_CASE_TABLE = "div[id$='dashboard-cases']";
  private static final String DASHBOARD_TASK_TABLE = "div[id$='dashboard-tasks']";

  // ATTRIBUTE
  private static final String INNER_HTML = "innerHTML";

  // FILTER WIDGET
  private static final String APPLY_BUTTON = "Apply";

  // TIME OUT
  private final long DEFAULT_TIMEOUT = getTimeOutForLocator() * 1000;
  
  private SelenideElement getWidgetContainer() {
    return $(WIDGET_CONTAINER);
  }
  
  private int getIndexWidgetByColumn(String columnName, String widgetName) {
    ElementsCollection elementsTH =
        getWidgetContainer().$(widgetName).waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_HEADER_COLUMNS);
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
    return  getWidgetContainer().waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_HEADER_TITLE)
        .filter(attribute(INNER_HTML, widgetName));
  }

  public ElementsCollection getCasesOfCaseWidgetHasName(String caseName) {
    return getColumnsOfTableWidget(DASHBOARD_CASE_TABLE).filter(text(caseName));
  }

  public SelenideElement getCaseOfCaseWidgetHasIndex(int index) {
    return getColumnsOfTableWidget(DASHBOARD_CASE_TABLE).get(index);
  }

  public SelenideElement getColumnOfCaseHasIndex(int index,String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName,DASHBOARD_CASE_TABLE);
    return getColumnOfTableWidget(DASHBOARD_CASE_TABLE,index).get(startIndex);
  }
  

  public SelenideElement getTaskOfTaskWidgetByIndex(int index,String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName,DASHBOARD_TASK_TABLE);
    return getColumnOfTableWidget(DASHBOARD_TASK_TABLE,index).get(startIndex);
  }

  public ElementsCollection countRelatedTasks() {
    return $("div[id$='related-tasks']").$$("td.related-task-name-column");
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public SelenideElement getFilterWidget(String widgetName) {
    return  getWidgetContainer().$$("form.table-widget-form").filter(text(widgetName)).first()
        .$("a.widget__filter-sidebar-link").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFilterInput(String widgetName,String inputField) {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.widget-filter-panel div.ui-g").filter(text(inputField)).first().$("input.ui-inputfield");
  }
  
  public SelenideElement getFilterCheckBox(String widgetName,String inputField) {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.widget-filter-panel div.ui-g").filter(text(inputField)).first();
  }
  
  public SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("a.ui-selectcheckboxmenu-close");
  }
  
  public SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-items-wrapper").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  public SelenideElement getApplyButtonFilter(String widgetName) {
    return $("div.filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']").filter(text(APPLY_BUTTON))
        .first();
  }

  public SelenideElement getDestroyLink() {
    return $("a[id$='destroy-case-link']");
  }
  
  public SelenideElement getConfirmDestroyButton() {
    return $("div[id$='destroy-case-confirmation-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction']");
  }
  
  public SelenideElement getAdditionalCaseDetailsPage() {
    return $("a[id$='additional-case-details-link']");
  }
  
  public ElementsCollection getAdditionalFieldsPage() {
    return $("div[id$='additional-case-detail-table']").waitUntil(appear, DEFAULT_TIMEOUT).$$(WIDGET_ROWS_CLASS);
  }
  
  public SelenideElement getNextPageWidget(String widgetName) {
    return  getWidgetContainer().$$("form.table-widget-form").filter(text(widgetName)).first()
        .$("a.ui-paginator-next");
  }
}
