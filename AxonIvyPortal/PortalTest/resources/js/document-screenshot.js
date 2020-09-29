/***UTILITY FUNCTIONS FOR ANNOTATION AND HIGHLIGHT****/
function appendStepAnnotation($element, number, top, left) {
  var $marker = $("<span></span>", {"class": "marker"}).text(number);
  var markerTop = $element.offset().top;
  var markerLeft = $element.offset().left;
  if(top !== undefined) {
    $marker.css('top', markerTop + top);
  }
  if(left !== undefined) {
    $marker.css('left', markerLeft + left);
  }
  $('body').append($marker);
}

function createRedThickOutline($element) {
  $element.addClass("red-thick-outline");
}

function createRedMediumOutline($element) {
  $element.addClass("red-medium-outline");
}

/***IMPLEMENTATION TO DECORATE PAGES - WHICH ARE CALLED IN SELENIUM****/
function highlightDashboardWidget() {
  createRedMediumOutline($("#task-widget"));
  createRedMediumOutline($("#statistic-widget-container"));
  createRedMediumOutline($("#process-widget"));
}

function highlightAndNumberingDashboardSections() {
  var processWidget = $("#process-widget");
  createRedMediumOutline(processWidget);
  appendStepAnnotation(processWidget, "1", 0 , processWidget.width()/2);
  var taskWidget = $("#task-widget");
  createRedMediumOutline(taskWidget);
  appendStepAnnotation(taskWidget, "2", 0, taskWidget.width()/2);
  var statisticWidget = $("#statistic-widget-container");
  createRedMediumOutline(statisticWidget);
  appendStepAnnotation(statisticWidget, "3", 0, statisticWidget.width()/2);
}

function numberingTaskFilter() {
  var taskOrder = jQuery("#task-widget\\:compact-task-widget-sort-menu");
  appendStepAnnotation(taskOrder, 2, -15,100)
}

function highlightTopBar() {
  createRedMediumOutline($("#top-menu"));
}

function numberingTopBar() {
  var userSetting = $("#user-settings-menu");
  appendStepAnnotation(userSetting, "1", -10, userSetting.width() + 5);
  var globalSearch = $("#global-search-component\\:global-search-data");
  appendStepAnnotation(globalSearch, "2", -10, globalSearch.width()-30);
}

function highlightLogo() {
  createRedMediumOutline($(".portal-home-logo.portal-home-logo-small"));
}

function numberingTaskItem() {
  var taskPriorityFirstRow = jQuery(".priority-cell:eq(0)");
  appendStepAnnotation(taskPriorityFirstRow, "1", -35, 0);
  
  var taskNameFirstRow = jQuery(".task-start-info-task-name:eq(0)");
  appendStepAnnotation(taskNameFirstRow, "2", -10, 160);
  
  var taskIdSecondRow = jQuery(".task-start-info-task-name:eq(1)");
  appendStepAnnotation(taskIdSecondRow, "3", -10, 170);
  
  var taskDatesThirddRow = jQuery(".task-start-info-content:eq(2)");
  appendStepAnnotation(taskDatesThirddRow, "4", -10, -45);
  appendStepAnnotation(taskDatesThirddRow, "5", -10, 120);
  
  var taskDescriptionFourthRow = jQuery(".task-start-info-task-description:eq(3)");
  appendStepAnnotation(taskDescriptionFourthRow, "6", -10, 175);
}

function numberingTaskFilterAndSort() {
  var taskFilter = $("#task-widget\\:filter-form\\:filter-container");
  appendStepAnnotation(taskFilter, 1, -15, taskFilter.width()/2);
  var taskSort = $("#task-widget\\:sort-task-form\\:sort-task-menu");
  appendStepAnnotation(taskSort, 2, -15, taskSort.width()/2);
}

function highlightProcessNavigation() {
  createRedThickOutline($('.submenu.PROCESS'));
  createRedThickOutline($('.widget.process-widget'));
}

function highlightAddExternalLink() {
  var externalLink = $('#process-widget\\:add-external-link-command');
  appendStepAnnotation(externalLink, "1", -10, externalLink.width());
}

function highlightAddExternalDialogItem() {
  var dialogTitle = $('#process-widget\\:add-external-link-dialog_title');
  appendStepAnnotation(dialogTitle, "2", 0, dialogTitle.width());
  
  var linkName = $('#process-widget\\:add-external-link-form\\:external-link-name');
  appendStepAnnotation(linkName, "3", 0, -10);
  
  var startLink = $('#process-widget\\:add-external-link-form\\:external-link');
  appendStepAnnotation(startLink, "4", 0, -10);
  
  var visibility = $('#process-widget\\:add-external-link-form\\:external-link-type-radio');
  appendStepAnnotation(visibility, "5", 0, -10);
  
  var addButton = $("[id$='process-widget:add-external-link-form:adding-new-external-link-command']");
  appendStepAnnotation(addButton, "6", 0, -35);
}

function highlightProcessItems() {
  var processSearch = $('#process-widget\\:process-search');
  createRedMediumOutline(processSearch);
  appendStepAnnotation(processSearch, "1", -5, processSearch.width() - 40);
  
  var expressLogo = $('.express-process-logo.ivyicon-startup-launch');
  createRedMediumOutline(expressLogo);
  appendStepAnnotation(expressLogo, "2", -45, -10);
  
  var externalLink = $('.ivyicon-hyperlink-3.external-link-icon:eq(0)');
  createRedMediumOutline(externalLink);
  appendStepAnnotation(externalLink, "3", -45, -10);
}

function numberingStatisticWidget() {
  var chartInfo = $('.chart-info:eq(0)');
  appendStepAnnotation(chartInfo, "1", -10, 25);
  var chartCanvas = $('.chartjs-render-monitor');
  appendStepAnnotation(chartCanvas, "2", 0, chartCanvas.height()/2 - 30);
  appendStepAnnotation(chartCanvas, "3", chartCanvas.width()/4, chartCanvas.height()/2 + 10);
}

function highlightAdminSettings() {
  createRedMediumOutline($("#adminui-menu-item"));
}

function highlightCaseMenuItem() {
  createRedThickOutline($('.submenu.CASE'));
}

function highlightShowMoreNoteLink() {
  createRedMediumOutline($('#case-item-details\\:case-histories\\:show-more-note-link'));
}

function highlightStatisticNavigation() {
   createRedMediumOutline($('.submenu.STATISTICS'));
   createRedMediumOutline($('#statistics-widget\\:statistic-link\\:statistic-link'));
}

function numberingChartPanel() {
  var chartName = $('.chart-name:eq(1)');
  appendStepAnnotation(chartName, "1", -30, -25);
  var chartInfo = $('.chart-info:eq(1)');
  appendStepAnnotation(chartInfo, "2", -20, 25);
  var chartActions = $('.chart-actions-container:eq(1)');
  appendStepAnnotation(chartActions, "3", chartActions.height(), chartActions.width()/2);
  var chartCanvas = $('.chartjs-render-monitor:eq(1)');
  appendStepAnnotation(chartCanvas, "4", chartCanvas.width()/4, chartCanvas.height()/2 + 10);
  appendStepAnnotation(chartCanvas, "5", 15, chartCanvas.height()/2 - 50);
}

function highlightCustomCaseList() {
  var caseHeader = $("[id$='case-widget:widget-column-header']");
  createRedMediumOutline(caseHeader);
  appendStepAnnotation(caseHeader, "1", -15, caseHeader.width()/2);
  
  var action = $(".case-header-container.case-header-data:eq(1)");
  createRedMediumOutline(action);
  appendStepAnnotation(action, "2", -15, caseHeader.width()/2);
}

function highlightCustomColumnsConfigOnCaseList() {
  var customColumnHeader = $(".ui-commandlink.customized-case-header-column:eq(0)");
  createRedMediumOutline(customColumnHeader)
  appendStepAnnotation(customColumnHeader, "1", -30, -20);
  
  var columnsCheckbox = $("[id$=':select-columns-form:columns-checkbox']");
  var customCheckbox = columnsCheckbox.find("td:eq(5)");
  createRedMediumOutline(customCheckbox);
  appendStepAnnotation(customCheckbox, "2", -20, customCheckbox.width() - 10);
  
  var caseHeader = $(".case-info-row.js-case-start-link:eq(6)");
  var customCaseItemCell = caseHeader.find("span.customized-case-header-column:eq(0)");
  createRedMediumOutline(customCaseItemCell);
  appendStepAnnotation(customCaseItemCell, "3", -20, - 20);
}

function highlightCaseCreatorFilter() {
  var filterLabel = $("[id$=':creator-filter:filter-open-form:advanced-filter-command']").find("span.ui-button-text");
  createRedMediumOutline(filterLabel);
  appendStepAnnotation(filterLabel, "1", -25, -30);
  
  var removeFilterCommand = $("[id$=':creator-filter:filter-open-form:advanced-filter-remove-command']");
  createRedMediumOutline(removeFilterCommand);
  appendStepAnnotation(removeFilterCommand, "2", -25, removeFilterCommand.width());
  
  var updateCommand = $("[id$=':creator-filter:filter-input-form:update-command']");
  createRedMediumOutline(updateCommand);
  appendStepAnnotation(updateCommand, "3", -2, -40)
}

function highlightAddFavoriteProcess() {
  var addProcessCommand = $("[id$=':user-process-action-form:show-adding-dialog-commmand']");
  appendStepAnnotation(addProcessCommand, "1", -10, addProcessCommand.width() + 2);
  
  var addNewProcessTitle = $("[id$='process-widget:add-new-process-dialog_title']");
  appendStepAnnotation(addNewProcessTitle, "2", 0, addNewProcessTitle.width() + 2);
  
  var addNewProcessName = $("label[for$='process-widget:new-process-name_input']");
  appendStepAnnotation(addNewProcessName, "3", -10, addNewProcessName.width() + 2);
  
  var addProcessStartLink = $("label[for$='process-widget:process-start-link']");
  appendStepAnnotation(addProcessStartLink, "4", -10, addProcessStartLink.width() + 2);
  
  var addProcessIcon = $(".ui-commandlink.select-awesome-icon-button");
  appendStepAnnotation(addProcessIcon, "5", -10, addProcessIcon.width() + 2)
  
  appendStepAnnotation($("[id$='process-widget:add-process-command']"), "6", -5, -40);
}

function highlightEditSwitchProcessButton() {
  var editSwitchCommand = $("[id$='process-widget:user-process-action-form:editing-switch-command']");
  createRedMediumOutline(editSwitchCommand);
  appendStepAnnotation(editSwitchCommand, "1", -45, editSwitchCommand.width()/2);
}

function highlightEditStepUserProcess(isDeleteProcess) {
  var userProcessList = $("[id$='process-widget:edit-process-item-form:order-list-items']");
  var userProcessItem = userProcessList.find("li.ui-orderlist-item.ui-sortable-handle:eq(0)");
  if (isDeleteProcess) {
    var processDeleteLink = userProcessItem.find("[id$=':process-delete-link']");
    createRedMediumOutline(processDeleteLink);
    appendStepAnnotation(processDeleteLink, "2", -40, processDeleteLink.width()/2);
  } else {
    appendStepAnnotation(userProcessItem, "2", 4, userProcessItem.width()/2);
  }
  
  var processSaveLink = $("[id$='process-widget:user-process-action-form:save-process-command']");
  createRedMediumOutline(processSaveLink);
  appendStepAnnotation(processSaveLink, "3", -40, -20);
}

function highlightSortUserProcess() {
  createRedMediumOutline($("[id$=':user-process-action-form:name-sort-command']"));
}

// Task
function highlightCustomTaskList() {
  var caseHeader = $("[id$='task-widget:task-widget-sub-header']");
  createRedMediumOutline(caseHeader);
  appendStepAnnotation(caseHeader, "1", -15, caseHeader.width()/2);
  
  var action = $(".full-mode.task-start-list-item.js-task-start-list-item:eq(2)");
  createRedMediumOutline(action);
  appendStepAnnotation(action, "2", -15, caseHeader.width()/2);
}

function highlightCustomColumnsConfigOnTaskList() {
  var customColumnHeader = $("[id$=':task-custom']")[0];
  createRedMediumOutline($(customColumnHeader))
  appendStepAnnotation($(customColumnHeader), "1", -30, -20);
  
  var columnsCheckbox = $("[id$='task-columns-configuration:select-columns-form:columns-checkbox']");
  var customCheckbox = columnsCheckbox.find("td:eq(6)");
  createRedMediumOutline(customCheckbox);
  appendStepAnnotation(customCheckbox, "2", -20, customCheckbox.width() - 10);
  
  var taskHeader = $(".task-start-link.js-task-start-link:eq(4)");
  var customTaskItemCell = taskHeader.find(".task-custom-field-cell");
  createRedMediumOutline(customTaskItemCell);
  appendStepAnnotation(customTaskItemCell, "3", -20, - 20);
}


function highlightTaskStateFilter() {
  var filterLabel = $("[id$=':state-filter:filter-open-form:advanced-filter-command']").find("span.ui-button-text");
  createRedMediumOutline(filterLabel);
  appendStepAnnotation(filterLabel, "1", -25, -30);
  
  var removeFilterCommand = $("[id$=':state-filter:filter-open-form:advanced-filter-remove-command']");
  createRedMediumOutline(removeFilterCommand);
  appendStepAnnotation(removeFilterCommand, "2", -25, removeFilterCommand.width());
  
  var updateCommand = $("[id$=':state-filter:filter-input-form:update-command']");
  createRedMediumOutline(updateCommand);
  appendStepAnnotation(updateCommand, "3", -2, -40)
}

function highlightJoinGroupChatOption() {
  createRedMediumOutline($('#horizontal-task-action-form\\:chat-group').parent());
}

function highlightShowMoreTaskHistories() {
  createRedMediumOutline($("[id$=':task-notes:show-more-note-link']"));
}

function highlightShowWorkflowEvents() {
  createRedMediumOutline($("a[id$=':show-workflow-event']"));
}

function highlightCreateExpressWorkflow() {
  createRedMediumOutline($("[id$='process-widget:create-express-link']"));
}

function highlightExpressTaskResponsible(stepNumber) {
  var taskResponsible = '.task-responsible:eq(' + stepNumber + ')';
  createRedMediumOutline($(taskResponsible));
}

function highlightManagementExpressTab() {
  createRedMediumOutline($("a[href$=':adminTabView:express-management-tab']").parent());
}

function highlightDeployExpress() {
  createRedMediumOutline($(".ui-fileupload-upload"));
}

function highlightExportExpress() {
  var expressTable = $("[id$=':express-management-component:express-management-form:express-workflow-summary-table']");
  var checkAll = expressTable.find(".express-selection-column:eq(0)").find(".ui-chkbox-box");
  createRedMediumOutline(checkAll);
  appendStepAnnotation(checkAll, 1, -10, checkAll.width() + 10);
  
  var exportButton = $("[id$=':express-management-component:express-management-form:export-express-btn']");
  appendStepAnnotation(exportButton, 2, -10, exportButton.width() + 15);
}

function cleanHighlightExportExpress() {
  var expressTable = $("[id$=':express-management-component:express-management-form:express-workflow-summary-table']");
  var checkAll = expressTable.find(".express-selection-column:eq(0)").find(".ui-chkbox-box");
  checkAll.removeClass("red-medium-outline");
  $('.marker').remove();
}

function scrollToBottomOfLayoutContent() {
  var layoutContent = $('.js-layout-content'); 
  layoutContent.scrollTop(layoutContent[0].scrollHeight);
}

function highlightCustomCaseDetail() {
  var customTop = $('.custom-task-details-panel-top');
  appendStepAnnotation(customTop, 1, -45, customTop.width()/2);
  
  var customMidle = $('.custom-task-details-panel-middle');
  appendStepAnnotation(customMidle, 2, -45, customMidle.width()/2);
  
  var customBottom = $('.custom-task-details-panel');
  appendStepAnnotation(customBottom, 3, -45, customBottom.width()/2);
}

function highlightCustomTaskDetail() {
  var customTop = $('.custom-task-details-panel-top');
  appendStepAnnotation(customTop, 1, -45, customTop.width()/2);
  
  var customBottom = $('.custom-task-details-panel-bottom');
  appendStepAnnotation(customBottom, 2, -45, customBottom.width()/2);
}

function highlightCaseDetailComponents() {
  var general = $('#case-general-information-card');
  appendStepAnnotation(general, 1, 0, general.width()/2);
  
  var histories = $('#case-item-details\\:history-container');
  appendStepAnnotation(histories, 2, 0, histories.width()/2);
  
  var runningTask = $('#case-item-details\\:case-details-related-running-tasks-card');
  appendStepAnnotation(runningTask, 3, 0, runningTask.width()/2);
  
  var document = $('#case-item-details\\:case-details-document-card');
  appendStepAnnotation(document, 4, 0, document.width()/2);
}

function highlightTaskDetailComponent() {
  var histories = $('#task-detail-template\\:task-detail-note-container');
  appendStepAnnotation(histories, 1, 0, histories.width()/2);
  
  var document = $('#task-detail-template\\:task-detail-document-container');
  appendStepAnnotation(document, 2, 0, document.width()/2);
}

function highlightUserExampleCard(cardIndex) {
  var userCardId = '#panel-' + cardIndex;
  createRedThickOutline($(userCardId).parent());
}

function highlightTaskActionItem(taskIndex, actionIndex) {
  var taskActionPanelId = ':' + taskIndex + ':task-item:task-action:additional-options:task-additional-actions';
  var actionItemId = '.option-item:eq(' + actionIndex + ')';
  var actionItem = $("[id$='" + taskActionPanelId + "']").find(actionItemId);
  createRedMediumOutline(actionItem);
}

function highlightShowAllProcesses() {
  createRedThickOutline($('.submenu.PROCESS'));
  createRedMediumOutline($("[id$='process-widget:process-link:process-link']"));
}

function highlightShowAdditionalLink() {
  createRedMediumOutline($("[id$='case-item:case-item-action-form:action-step-component:show-additional-case-details-link']"));
}