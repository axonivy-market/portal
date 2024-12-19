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

function clearRedMediumOutline($element) {
  $element.removeClass("red-medium-outline");
}

function createRedMediumBorder($element) {
  $element.addClass("red-medium-border");
}

function createRedThickOutlineWithOffset($element) {
  $element.addClass("red-thick-outline");
  $element.addClass("red-outline-thick-offset");
}

/***IMPLEMENTATION TO DECORATE PAGES - WHICH ARE CALLED IN SELENIUM****/
function highlightTopBar() {
  var topMenu = $("#top-menu");
  var searchIcon = topMenu.find("li.topbar-item.search-item");
  $(searchIcon).addClass("red-topbottomleft-medium-border");
  var themeSwitchIcon = topMenu.find("li.topbar-item.themeswitch-item");
  $(themeSwitchIcon).addClass("red-topbottom-medium-border");
  var chatItem = topMenu.find("li.topbar-item.chat-item");
  $(chatItem).addClass("red-topbottom-medium-border");
  var notificationIcon = $("#open-notifications-panel")[0].parentElement;
  $(notificationIcon).addClass("red-topbottom-medium-border");
  var userNameItem = topMenu.find("li.topbar-item.user-profile.setting-container");
  $(userNameItem).addClass("red-topbottomright-medium-border");
}

function highlightChatIcon() {
  var topMenu = $("#top-menu");
  var chatItem = topMenu.find("li.topbar-item.chat-item");
  $(chatItem).addClass("red-medium-border");
}

function numberingTopBar() {
  var userSetting = $("#user-settings-menu");
  appendStepAnnotation(userSetting, "1", -10, userSetting.width() - 10);
  var globalSearch = $("#global-search-component\\:global-search-data");
  appendStepAnnotation(globalSearch, "2", -10, globalSearch.width()-30);
}

function highlightLogo() {
  createRedMediumOutline($(".portal-home-logo.portal-home-logo-small"));
}

function highlightProcessNavigation() {
  createRedThickOutlineWithOffset($('.layout-menu .PROCESS'));
}

function highlightAddExternalLink() {
  var externalLink = $('#process-widget\\:add-external-link-command');
  appendStepAnnotation(externalLink, "1", -10, externalLink.width());
}

function highlightAddExternalDialogItem() {
  var dialogTitle = $('#process-widget\\:add-external-link-dialog_title');
  appendStepAnnotation(dialogTitle, "2", 0, dialogTitle.width());
  
  var linkName = $("label[for$='add-external-link-form:external-link-name']");
  appendStepAnnotation(linkName, "3", -5, linkName.width());
  
  var translateName = $("button[id$='process-widget:add-external-link-form:add-name-language-button']");
  appendStepAnnotation(translateName, "4", -5, translateName.width());
  
  var startLink = $("label[for$='add-external-link-form:external-link']");
  appendStepAnnotation(startLink, "5", -5, startLink.width());
  
  var linkDescription = $("label[for$='process-widget:add-external-link-form:external-description']");
  appendStepAnnotation(linkDescription, "6", -5, linkDescription.width());
  
  var translateDescription = $("button[id$='process-widget:add-external-link-form:add-description-language-button']");
  appendStepAnnotation(translateDescription, "7", -5, translateDescription.width());
  
  var visibility = $("label[for$='add-external-link-form:external-link-type-radio']");
  appendStepAnnotation(visibility, "8", -5, visibility.width());
  
  var icon = $("[id$='add-external-link-form:external-link-icon:awesome-icon-selection']");
  appendStepAnnotation(icon, "9", -10, icon.width());
  
  var uploadImage = $("[id$='add-external-link-form:external-link-image-label']");
  appendStepAnnotation(uploadImage, "10", 0, 40);
  
  var addButton = $("[id$='process-widget:adding-new-external-link-command']");
  appendStepAnnotation(addButton, "11", -25, -5);
}

function highlightProcessItems() {
  var processSearch = $('[id$="process-widget:process-search"]');
  createRedMediumOutline(processSearch);
  appendStepAnnotation(processSearch, "1", -5, processSearch.width() - 40);
  
  var externalLink = $('.js-external-link-process-item .image-process-icon:eq(0)');
  createRedMediumOutline(externalLink);
  appendStepAnnotation(externalLink, "2", -15, -30);
  
  createRedMediumOutline($('[id$="process-widget:process-view-mode:view-mode-selection"]'));
  appendStepAnnotation($("[id$='process-widget:process-view-mode:view-mode-selection'] div[class$='ui-state-active']"), "3", -10, -40);
  
  var processActions = $('[id$="image-process-action-component:process-action-button"]').eq(0);
  createRedMediumOutline(processActions);
  appendStepAnnotation(processActions, "4", -5, processActions.width() - 80);
}

function highlightEditProcessIcon() {
  appendStepAnnotation($("[id$='process-widget:edit-process-form:edit-process-icon:awesome-icon-selection']"), "4", -40, 110);
}

function highlightEditProcessDialog() {
  var dialogTitle = $('#process-widget\\:edit-process-dialog_title');
  appendStepAnnotation(dialogTitle, "3", 0, dialogTitle.width());
}

function highlightEditProcessLink() {
  let editProcessLink = $('[id$=":0:image-processes:0:process-item:image-process-action-component:edit-process"]');
  createRedMediumBorder(editProcessLink);
  appendStepAnnotation(editProcessLink, "2", -10, -40);
}

function highlightProcessMoreMenuButton() {
  let processMoreMenuButton = $('[id$=":0:image-processes:0:process-item:image-process-action-component:process-action-button"]');
  createRedMediumBorder(processMoreMenuButton);
  appendStepAnnotation(processMoreMenuButton, "1", -10, -40);
}

function highlightAdminSettings() {
  createRedMediumOutline($("#adminui-menu-item"));
}

function highlightUserMenuConfiguration() {
  createRedMediumOutline($("a[id$='menu-configuration-0']"));
}

function highlightCaseMenuItem() {
  createRedThickOutlineWithOffset($('.layout-menu .CASE'));
}

function highlightShowMoreNoteLink() {
  createRedMediumOutline($('a.js-note-show-more-link'));
}

function highlightProcessMoreInformationLink() {
  var displayingProcessItem = $(".js-process-start-list-item").filter(function() {
    if($(this).css('display') != 'none')
           return $(this);
  });
  displayingProcessItem.find(".process-more-info-link").css('padding-bottom', '0.3rem');
  displayingProcessItem.find(".process-more-info-link").css('padding-top', '0.3rem');
  displayingProcessItem.find(".process-more-info-link").css('margin-top', '0.2rem');
  createRedMediumOutline(displayingProcessItem.find(".process-more-info-link"));
}

// Task
function highlightJoinGroupChatOption() {
  createRedMediumOutline($('#horizontal-task-action-form\\:chat-group').parent());
}

function highlightShowMoreTaskHistories() {
  createRedMediumOutline($("[id$=':task-notes:show-more-note-link']"));
}

function highlightSwitchToEditMode() {
  createRedMediumOutline($("[id$=':switch-to-edit-mode-button']"));
}

function highlightSwitchToViewMode() {
  createRedMediumOutline($("[id$=':switch-to-view-mode-button']"));
}

function highlightResetToDefault() {
  createRedMediumOutline($("[id$=':reset-details-settings-button']"));
}

function highlightShowWorkflowEvents() {
  createRedMediumOutline($("a[id$=':task-workflow-event-command']"));
}

function scrollToMiddleOfLayoutContent() {
  window.scrollTo(0, document.body.scrollHeight/2);
}

function scrollToBottomOfLayoutContent() {
  window.scrollTo(0, document.body.scrollHeight);
}

function highlightCaseDetailComponents() {
  var general = $('#case-details-information-panel');
  appendStepAnnotation(general, 1, 0, general.width()/2);
  
  var document = $('#case-details-document-panel');
  appendStepAnnotation(document, 2, 0, document.width()/2);
  
  var technicalCase = $('#case-details-technicalCase-panel');
  appendStepAnnotation(technicalCase, 3, 0, technicalCase.width()/2);
  
  var relatedTask = $('#case-details-relatedTask-panel');
  appendStepAnnotation(relatedTask, 4, 0, relatedTask.width()/2);
  
  var histories = $('#case-details-history-panel');
  appendStepAnnotation(histories, 5, 0, histories.width()/2);
}

function highlightTaskDetailComponent() {
  var information = $('#task-details-information-panel');
  appendStepAnnotation(information, 1, 0, information.width()/2);

  var document = $('#task-details-document-panel');
  appendStepAnnotation(document, 2, 0, document.width()/2);

  var histories = $('#task-details-history-panel');
  appendStepAnnotation(histories, 3, 0, histories.width()/2);
}

function highlightTaskStatusBanner() {
  createRedMediumOutline($("[id$=':task-status-banner']"));
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

function highlightCaseExportToExcelButton() {
  createRedMediumOutline($("a[id$=':case-export-to-excel']"));
}

function highlightWidgetExportToExcelLinkForTask() {
  createRedMediumOutline($("form[id$=':export-to-excel-form-0'] > a"));
}

function highlightWidgetExportToExcelLinkForCase() {
  createRedMediumOutline($("form[id$=':export-to-excel-form-1'] > a"));
}

function highlightProcessOverviewLink() {
  $(".task-detail-section-title").removeClass("u-truncate-text");
  $(".case-history-button-container").removeClass("u-truncate-text");
  var processOverviewLink = $("a[id$=':show-process-overview-link']");
  processOverviewLink.css("outline-width", "thin");
  processOverviewLink.css("outline-offset", "-1px")
  createRedMediumOutline(processOverviewLink);
}

function highlightCustomWidgetInCaseDetails() {
  createRedMediumOutline($(".custom-widget-card"));
}

function highlightIFrameWidgetTaskDetails() {
  createRedMediumOutline($(".custom-widget-card.task-detail-card"));
}

function highlightNotificationChannelSettings() {
  createRedMediumOutline($("div[id$='notification-channels']"));
}

function highlightUserName() {
  createRedMediumOutline($('#user-settings-menu'));
}

function clearHighlightUserName() {
  clearRedMediumOutline($('#user-settings-menu'));
}

function highlightUserExampleNavigation() {
  createRedThickOutlineWithOffset($('.layout-menu .CUSTOM'));
}

function highlightProcessDisplayModePanel() {
  createRedMediumOutline($("div[id $='process-display-mode_panel']"));
}

function highlightDashboardConfiguration() {
   createRedMediumOutline($("#dashboard-configuration"));
}

function highlightSharePageButton() {
  createRedMediumOutline($("button[id$=':share-page-button']"));
}

function removeHighlightSharePageButton() {
  clearRedMediumOutline($("[id$=':share-page-button']"));
}

function highlightAndNumberingTaskTemplate() {
  $(".task-template-title-horizontal-container").css("padding-top", "10px");

  var title = $("#task-template-title");
  title.css("padding-left", "10px");

  appendStepAnnotation(title, "1", 0 , title.width()/2 + 70);

  var processChain = $(".process-chain-container");
  appendStepAnnotation(processChain, "2", 0, processChain.width()/2 - 150);

  var other = $("#horizontal-task-template-tasks-command");
  createRedMediumOutline(other);
  appendStepAnnotation(other, "3", 27, -20);
}

function highlightNotificationIcon(){
  var icon = $("#open-notifications-panel");
  icon.addClass("red-medium-border");
  appendStepAnnotation(icon, "1", 20, -20);

  var moreOptionsIcon = $("button[id$=':notification-more-option_button']");
  icon.addClass("red-medium-border");
  appendStepAnnotation(moreOptionsIcon, "2", 20, -20);

  var fullPageOption = $("a[id$=':notification-full-page']");
  icon.addClass("red-medium-border");
  appendStepAnnotation(fullPageOption, "3", 20, -20);
}

function highlightMobileApp() {
  createRedMediumOutline($("#mobile-app-item"));
}

function highlightShowFilterButton() {
  createRedMediumOutline($("button[id$=':show-filter']"));
}

function removeHighlightShowFilterButton() {
  clearRedMediumOutline($("button[id$=':show-filter']"));
}

function highlightQuickSearchTextbox() {
  createRedMediumOutline($(".widget__header .widget-header-quick-search > form"));
}

function createBlackThinOutline($element) {
  $element.addClass("black-thin-outline");
}

function createBlackMediumOutline($element) {
  $element.addClass("black-medium-outline");
}

function highlightCasePreviewDocument(){
  createRedMediumOutline($("a[id$=':preview-file']"));
}