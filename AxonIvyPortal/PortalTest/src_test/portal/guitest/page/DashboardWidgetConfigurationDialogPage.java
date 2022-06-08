package portal.guitest.page;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardWidgetConfigurationDialogPage extends TemplatePage {

  private static final String MANAGE_COLUMN_LINK_ID = "widget-configuration-form:new-widget-configuration-component:%s-widget-preview:column-toggler";
  private static final String MANAGE_COLUMN_DIALOG_ID = "widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-dialog";
  private static final String MANAGE_COLUMN_TABLE_CONTENT_ID = "widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-form:column-management-datatable_data";
  private static final String MANAGE_COLUMN_SAVE_BUTTON_ID = "widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-save-btn";
  private static final String MANAGE_COLUMN_CANCEL_LINK_ID = "widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-cancel-link";

  private static final String CONFIGURATION_DIALOG_ID = "new-widget-configuration-dialog";
  private static final String CONFIGURATION_FILTER_ID = "widget-configuration-form:new-widget-configuration-component:filter-container";
  private static final String PREVIEW_BUTTON_ID = "widget-configuration-form:new-widget-configuration-component:preview-button";

  private static final String PROCESS_DISPLAY_MODE_ID = "widget-configuration-form:new-widget-configuration-component:process-display-mode";
  private static final String PROCESS_DISPLAY_MODE_PANEL_ID = "widget-configuration-form:new-widget-configuration-component:process-display-mode_panel";
  private static final String COMBINED_MODE_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-combined-process_input";
  private static final String COMBINED_MODE_PROCESS_SELECTION_PANEL_ID = "widget-configuration-form:new-widget-configuration-component:selected-combined-process_panel";
  private static final String PROCESSES_LIST_ID = "widget-configuration-form:new-widget-configuration-component:processes-list";
  private static final String PROCESSES_LIST_PANEL_ID = "widget-configuration-form:new-widget-configuration-component:processes-list_panel";
  private static final String FULL_MODE_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-full-process_input";
  private static final String FULL_MODE_PROCESS_SELECTION_PANEL_ID = "widget-configuration-form:new-widget-configuration-component:selected-full-process_panel";
  private static final String IMAGE_MODE_PROCESS_SELECTION_ID = "widget-configuration-form:new-widget-configuration-component:selected-image-process_input";
  private static final String IMAGE_MODE_PROCESS_SELECTION_PANEL_ID = "widget-configuration-form:new-widget-configuration-component:selected-image-process_panel";

  @Override
  protected String getLoadedLocator() {
    return "id('new-widget-configuration-dialog')";
  }

  public WebElement openManageColumnDialog(boolean isTask) {
    String manageColumnLinkId = isTask ? String.format(MANAGE_COLUMN_LINK_ID, "task") : String.format(MANAGE_COLUMN_LINK_ID, "case");
    waitForElementDisplayed(By.id(manageColumnLinkId), true);
    findElementById(manageColumnLinkId).click();
    waitForElementDisplayed(By.id(MANAGE_COLUMN_DIALOG_ID), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT,
        "widget-configuration-form\\\\:new-widget-configuration-component\\\\:column-management-component\\\\:column-management-form\\\\:field-type-selection",
        ID_PROPERTY);
    return findElementById(MANAGE_COLUMN_DIALOG_ID);
  }

  public void closeManageColumnDialog() {
    findElementById(MANAGE_COLUMN_CANCEL_LINK_ID).click();
    waitForElementDisplayed(By.id(MANAGE_COLUMN_DIALOG_ID), false);
  }

  public void closeConfigurationDialog() {
    findElementById("task-configuration-cancel-link").click();
    waitForElementDisplayed(By.id(CONFIGURATION_DIALOG_ID), false);
  }

  public void uncheckTaskColumn(List<String> columns, boolean isTask) {
    if (CollectionUtils.isEmpty(columns)) {
      return;
    }

    WebElement configurationDialog = openManageColumnDialog(isTask);
    List<WebElement> columnRows =
       configurationDialog.findElement(By.id(MANAGE_COLUMN_TABLE_CONTENT_ID)).findElements(By.cssSelector("tr"));

    for (int i = 0; i < columnRows.size(); i++) {
      int indexOfRow = i + 1;
      WebElement columnRow =
          findElementByCssSelector(
              "#" + MANAGE_COLUMN_TABLE_CONTENT_ID.replace(":", "\\:") + " tr:nth-child(" + indexOfRow + ")");

      WebElement nameCell = columnRow.findElements(By.cssSelector("td")).get(1);
      if (columns.contains(nameCell.getText())) {
        WebElement checkbox =
            columnRow.findElements(By.cssSelector("td")).get(0).findElement(By.className("ui-chkbox"));
        String checkboxId = checkbox.getAttribute("id");
        checkbox.click();
        waitForElementPresent(By.cssSelector("#" + checkboxId.replace(":", "\\:") + " .ui-icon-blank"), true,
            DEFAULT_TIMEOUT);
      }
    }

    findElementById(MANAGE_COLUMN_SAVE_BUTTON_ID).click();
    waitForElementDisplayed(By.id(MANAGE_COLUMN_DIALOG_ID), false);
  }

  public WebElement getConfigurationDialog() {
    waitForElementDisplayed(By.id(CONFIGURATION_DIALOG_ID), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ajax-indicator\\\\:ajax-indicator-ajax-indicator_start", ID_PROPERTY);
    return findElementById(CONFIGURATION_DIALOG_ID);
  }

  public WebElement getConfigurationFilter() {
    waitForElementDisplayed(By.id(CONFIGURATION_FILTER_ID), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "widget-configuration-form\\\\:new-widget-configuration-component\\\\:widget-title", ID_PROPERTY);
    return findElementById(CONFIGURATION_FILTER_ID);
  }

  public void clickProcessDisplayMode() {
    waitForElementDisplayed(By.id(PROCESS_DISPLAY_MODE_ID), true);
    findElementById(PROCESS_DISPLAY_MODE_ID).click();
    waitForElementDisplayed(By.id(PROCESS_DISPLAY_MODE_PANEL_ID), true);
  }
  
  public void selectProcessMode(String name) {
    clickProcessDisplayMode();
    WebElement processDisplayModePanel = findElementById(PROCESS_DISPLAY_MODE_PANEL_ID);
    List<WebElement> modes = processDisplayModePanel.findElements(By.className("ui-selectonemenu-item"));
    for (WebElement mode : modes) {
      if (mode.getText().contentEquals(name)) {
        mode.click();
        waitUntilAnimationFinished(45, "ajax-indicator:ajax-indicator-ajax-indicator_start", "id");
        break;
      }
    }
  }

  public void selectProcessForCombinedModeProcessWidget(String processName) {
    waitForElementDisplayed(By.id(COMBINED_MODE_PROCESS_SELECTION_ID), true);
    findElementById(COMBINED_MODE_PROCESS_SELECTION_ID).clear();
    findElementById(COMBINED_MODE_PROCESS_SELECTION_ID).sendKeys(processName);
    waitForElementDisplayed(By.id(COMBINED_MODE_PROCESS_SELECTION_PANEL_ID), true);
    findElementById(COMBINED_MODE_PROCESS_SELECTION_PANEL_ID).findElements(By.className("ui-autocomplete-item")).get(0).click();
  }

  public void selectProcessForFullModeProcessWidget(String processName) {
    waitForElementDisplayed(By.id(FULL_MODE_PROCESS_SELECTION_ID), true);
    findElementById(FULL_MODE_PROCESS_SELECTION_ID).clear();
    findElementById(FULL_MODE_PROCESS_SELECTION_ID).sendKeys(processName);
    waitForElementDisplayed(By.id(FULL_MODE_PROCESS_SELECTION_PANEL_ID), true);
    findElementById(FULL_MODE_PROCESS_SELECTION_PANEL_ID).findElements(By.className("ui-autocomplete-item")).get(0).click();
  }

  public void selectProcessForImageModeProcessWidget(String processName) {
    waitForElementDisplayed(By.id(IMAGE_MODE_PROCESS_SELECTION_ID), true);
    findElementById(IMAGE_MODE_PROCESS_SELECTION_ID).clear();
    findElementById(IMAGE_MODE_PROCESS_SELECTION_ID).sendKeys(processName);
    waitForElementDisplayed(By.id(IMAGE_MODE_PROCESS_SELECTION_PANEL_ID), true);
    findElementById(IMAGE_MODE_PROCESS_SELECTION_PANEL_ID).findElements(By.className("ui-autocomplete-item")).get(0).click();
  }

  public void selectProcessesForCompactProcessWidget(List<String> processes) {
    waitForElementDisplayed(By.id(PROCESSES_LIST_ID), true);
    findElementById(PROCESSES_LIST_ID).click();
    if (processes == null) {
      return;
    }

    waitForElementDisplayed(By.id(PROCESSES_LIST_PANEL_ID), true);
    List<WebElement> options = findElementById(PROCESSES_LIST_PANEL_ID).findElements(By.className("ui-selectcheckboxmenu-item"));
    for(WebElement option : options) {
      if (processes.contains(option.findElement(By.cssSelector("label")).getText())) {
        option.findElement(By.className("ui-chkbox")).click();
        waitUntilAnimationFinished(45, "ajax-indicator:ajax-indicator-ajax-indicator_start", "id");
      }
    }
  }

  public void clickPreviewButton() {
    waitForElementDisplayed(By.id(PREVIEW_BUTTON_ID), true);
    findElementById(PREVIEW_BUTTON_ID).click();
  }

  public void waitForCombinedProcessLoadedAfterClickPreview() {
    waitForElementDisplayed(By.cssSelector("[id$=':process-task-widget-component:dashboard-process-tasks_data']"), true);
  }
  
  public void waitForCompactProcessLoadedAfterClickPreview() {
    waitForElementDisplayed(By.cssSelector("form[id$=':process-list'] .process-start-list-item"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ajax-indicator\\\\:ajax-indicator-ajax-indicator_start", ID_PROPERTY);
    waitUntilAnimationFinished(45, "process-start-list-item", "class");
  }

  public void waitForFullProcessLoadedAfterClickPreview() {
    waitForElementDisplayed(By.cssSelector("form[id$=':process-grid-item:process-item']"), true);
  }

  public void waitForImageProcessLoadedAfterClickPreview() {
    waitForElementDisplayed(By.id("process-actions"), true);
  }
}
