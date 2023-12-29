// Freya style has the transition delay time is 0.2s when expand/collapse main menu
// We need to delay a bit before calculating scrollbar
var isFinishedRestoreMenuState = false;
var delayTime = 0;

var Portal = {
  init : function(responsiveToolkit) {
    // Swipe on mobile can cause problems with scroll
    PrimeFaces.widget.Paginator.prototype.bindSwipeEvents = function() {}

    if ($('form.login-form').length > 0) {
      return;
    }
    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);

    //Check and add a delay time timeout when Freya restoring Menu state
    if (!isFinishedRestoreMenuState) {
      isFinishedRestoreMenuState = true;
      delayTime = 250;
    }

    setTimeout(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    }, delayTime);

    setTimeout(function() {
      Portal.updateLayoutContent();
      Portal.updateBreadcrumb();
    }, delayTime);

    var resizeTimer;
    // Update screen when window size is changed
    $(window).resize(function() {
      Portal.updateLayoutContent();

      clearTimeout(resizeTimer);
      resizeTimer = setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
    });
  },
  
  updateLayoutContent : function() {
    var $layoutTopbar = $('.js-layout-topbar');
    var $layoutMain = $('.js-layout-main');
    var $portalHeader = $('.js-portal-template-header');
    var $portalFooter = $('.js-portal-template-footer');
    var headerHeight = $portalHeader.outerHeight(true)||0;
    var footerHeight = $portalFooter.outerHeight(true)||0;
    var headerFooterHeight = headerHeight + footerHeight;
    var layoutTopbarHeight = $layoutTopbar.outerHeight(true)||0;
    if ($layoutMain.hasClass('u-invisibility')) {
      var envHeight = $('#portal-environment').outerHeight()||0;
    }

    $layoutTopbar.css('top', headerHeight + 'px');
    if ($(window).width() < 992) { // Handle for mobile view
      const menuTopValue = (headerHeight + layoutTopbarHeight) + 'px';
      const menuHeightValue = 'calc(100vh - ' + (headerFooterHeight + envHeight) + 'px)';
      $('.js-left-sidebar').css({'height': menuHeightValue, 'top': menuTopValue});
    } else {
      $('.js-left-sidebar').css({'height': 'calc(100vh - ' + (headerFooterHeight - envHeight) + 'px)','top': headerHeight + 'px'});
    }

    if (headerFooterHeight === 0 && envHeight === 0) {
      $layoutMain.removeAttr('style');
    } else {
      const layoutMainPaddingTop = headerHeight + layoutTopbarHeight + 20; // By default, Freya buffer 20px from topbar, refer to .layout-main class
      $layoutMain.css({'padding-top': layoutMainPaddingTop + 'px', 'padding-bottom' : footerHeight + 'px'});
    }

    var chatPanel = $('.js-chat-panel');
    if (chatPanel.length > 0) {
      const chatPanelHeight = 'calc(100% - ' + (headerFooterHeight + layoutTopbarHeight + envHeight) + 'px)';
      const chatPanelTop = (headerHeight + layoutTopbarHeight) + 'px';
      chatPanel.css({'height': chatPanelHeight, 'top': chatPanelTop, 'bottom': footerHeight + 'px'});
    }

    let notificationPanel = $('.js-notifications-panel');
    let notificationContentHeight = $('.notifications-item-list').outerHeight();
    if (notificationPanel.length > 0) {
      const notificationPanelHeight = 'calc(100% - ' + (headerFooterHeight + layoutTopbarHeight + envHeight) + 'px)';
      const notificationPanelTop = (headerHeight + layoutTopbarHeight) + 'px';
      notificationPanel.css({
        'height': notificationPanelHeight,
        'top': notificationPanelTop,
        'bottom': footerHeight + 'px'
      });
      $('.notification-scroll .ui-datascroller-content').outerHeight(notificationContentHeight * 0.95 + 'px')
    }
    
    $portalHeader.removeClass('u-invisibility');
    $layoutMain.removeClass('u-invisibility');
    $portalFooter.removeClass('u-invisibility');
  },

  updateBreadcrumb : function() {
    var breadCrumb = $("#top-menu").find("> li.breadcrumb-container");
    var breadCrumbMembers = breadCrumb.find("li");
    if (breadCrumbMembers.length == 0) {
      breadCrumb.css("display", "none");
      return;
    }

    var updateBreadcrumbTimeout;
    clearTimeout(updateBreadcrumbTimeout);

    updateBreadcrumbTimeout = setTimeout(function() {
        var layoutWrapper = $('.js-layout-wrapper');
        var leftSidebarMenu = $(".menu-wrapper.js-left-sidebar");
        var leftTopbar = $(".layout-topbar-left");
        var rightTopbar = $(".layout-topbar-right");
        var breadCrumbMarginLeft = 0;
        if (layoutWrapper.hasClass('layout-static')) {
          breadCrumbMarginLeft = leftSidebarMenu.outerWidth(true) - leftTopbar.outerWidth(true) - parseInt(rightTopbar.css("padding-left")) + "px";
        } else {
          if ($("a.menu-button").is(":visible")) {
            breadCrumbMarginLeft = 0;
          } else {
            breadCrumbMarginLeft = '2rem';
          }
        }

        breadCrumb.css({"display": "flex", "margin-left" : breadCrumbMarginLeft});
        if(!layoutWrapper.hasClass('has-breadcrumb')) {
          layoutWrapper.addClass('has-breadcrumb');
        }

        var currentBreadcrumb = $(breadCrumbMembers.get(breadCrumbMembers.length - 1));
        if (currentBreadcrumb.get(0).offsetWidth == 0) {
          breadCrumb.css("display", "none");
          layoutWrapper.removeClass('has-breadcrumb');
        }
      }, 250);
  },

  changePortalVariableTheme: function(themeMode) {
    let newLayout = '-' + themeMode;
    var linkElement = $('link[href*="portal-variables-"]');
    var href = linkElement.attr('href');
    var currentColor = '-light';
    if (href.includes('portal-variables-dark.css')) {
      currentColor = '-dark';
    }
    PrimeFaces.FreyaConfigurator.replaceLink(linkElement, href.replace(currentColor, newLayout));
  },
 
  switchToThemeMode: function(themeMode) {
    PrimeFaces.FreyaConfigurator.changeLayout('ivy', themeMode);
  },

  changeLogoByThemeMode: function(themeMode) {
    PrimeFaces.FreyaConfigurator.changeLogo(themeMode);
  },
}

function searchIconByName(element) {
  var keyword = element.value.toLowerCase();
  var icons = $(".icon-selection-dialog-selecting-icon");
  for (i = 0; i < icons.length; i++) {
    var icon = icons[i].innerHTML;
    if (icon.indexOf(keyword) > -1 || icon.split("-").join(" ").indexOf(keyword) > -1) {
      icons[i].style.display= "";
    } else {
    icons[i].style.display= "none";
    }
  }
}

var MainMenu = {
  urlToMenu : [["PortalHome.xhtml", "DASHBOARD"],
      ["Processes.xhtml", "PROCESS"],
      ["PortalTasks.xhtml", "TASK"],
      ["TaskWidget.xhtml", "TASK"],
      ["PortalTaskDetails.xhtml", "TASK"],
      ["PortalCases.xhtml", "CASE"],
      ["CaseWidget.xhtml", "CASE"],
      ["PortalCaseDetails.xhtml", "CASE"],
      ["CaseItemDetails.xhtml", "CASE"],
      ["PortalDashBoard.xhtml", "STATISTICS"],
      ["TaskAnalysis.xhtml", "STATISTICS"],
      ["PortalDashboard.xhtml", "DASHBOARD"]],

  init : function(responsiveToolkit) {
    this.highlightMenuItem();
    this.responsiveToolkit = responsiveToolkit;
    this.$mainMenuToggle = $('.sidebar-pin');
    this.menulinks = $('.layout-sidebar .layout-menu a');
    this.bindEvents();
  },
  
  bindEvents : function() {
    var $this = this;
    this.$mainMenuToggle.on('click', function(e) {
      $this.responsiveToolkit.updateLayoutWithAnimation();

      var statisticContainer = $('.js-statistic-widget-container.compact-mode');
      if (statisticContainer.length > 0) {
        updateStatisticCarousel();
      }
    });
  },

  highlightMenuItem : function() {
    let $currentPageMenu = this.getMenuItemByCurrentPage();
    let activeMenuItemList = this.getActiveMenu();

    if ($currentPageMenu.length > 0) {
      var $dashboardGroup = $(".js-dashboard-group");
      if ($currentPageMenu.hasClass("DASHBOARD") && $dashboardGroup.length > 0) {
        $.each( activeMenuItemList, function( i, menuItem ) {
            if (!(menuItem.id.includes('-main-dashboard'))) {
              deactivateMenuItemOnLeftMenu(menuItem.id);
            }
        });
        return;
      }

      if ($currentPageMenu.parent().hasClass('active-menuitem') && activeMenuItemList.length === 1) {
        return;
      }
      this.removeActiveMenu(activeMenuItemList);
      $currentPageMenu.parent().addClass('active-menuitem');
      PF('main-menu').addMenuitem($currentPageMenu.parent().attr('id'));
    }
  },

  removeActiveMenu : function(activeMenuItems) {
    $.each( activeMenuItems, function( i, menuItem ) {
      deactivateMenuItemOnLeftMenu(menuItem.id);
    });
  },

  getCurentPageByPageUrl : function() {
    let pageUrl = window.location.pathname;
    for (var i = 0; i < MainMenu.urlToMenu.length; i++) {
      if (pageUrl.indexOf(MainMenu.urlToMenu[i][0]) > -1) {
        return MainMenu.urlToMenu[i][1];
      }
    }
  },

  getMenuItemByCurrentPage : function() {
    let currentPage = this.getCurentPageByPageUrl();
    return $(".layout-menu").find('li[role="menuitem"] a.' + currentPage);
  },

  getActiveMenu : function() {
    return $(".layout-menu li.active-menuitem");
  },

  isThirdPartyMenu : function(e) {
    let menuClass = e.currentTarget.className;
    if (menuClass && (menuClass.indexOf('THIRD_PARTY') !== -1 || menuClass.indexOf('EXTERNAL_LINK') !== -1)) {
      return true;
    }
    return false;
  }
}

function handleError(xhr, renderDetail, isShowErrorLog){
  //From PF 7.0 with new jQuery version, when we call ajax by remote command then navigate when remote command still executing, this request HTML status is abort
  //This make general exception dialog display frequently
  if (xhr.statusText === 'abort') {
    return;
  }
  if (renderDetail) {
    $("a[id$='ajax-indicator:ajax-indicator-show-more']").click(function() {
      $("[id$='ajax-indicator:error-code']").text(xhr.status);
      $("[id$='ajax-indicator:error-text']").text(xhr.statusText);
      $("[id$='ajax-indicator:error-url']").text(xhr.pfSettings.url);
      $("[id$='ajax-indicator:error-ready-state']").text(xhr.readyState);
      $("[id$='ajax-indicator:error-type']").text(xhr.pfSettings.type);
      $("[id$='ajax-indicator:error-args']").text(JSON.stringify(xhr.pfArgs));
      $("[id$='ajax-indicator:pfSettings-source']").text(xhr.pfSettings.source.id);
      $("[id$='ajax-indicator:form-data']").text(decodeURIComponent(xhr.pfSettings.data));
      $("[id$='ajax-indicator:response-text']").text(xhr.responseText);
      $("[id$='ajax-indicator:xhr']").text(JSON.stringify(xhr));
      PF('detail-error-dialog').show();
    });
  }
  PF('error-ajax-dialog').show();
  if(isShowErrorLog){
    var settingsSourceId = "PfSettings.source.id:\n";
    console.log("Status code:\n" + xhr.status);
    console.log("Status text:\n" + xhr.statusText);
    console.log("Url:\n" + xhr.pfSettings.url);
    console.log("Ready state:\n" + xhr.readyState);
    console.log("Type:\n" + xhr.pfSettings.type);
    console.log("PfArgs:\n" + JSON.stringify(xhr.pfArgs));
    if (xhr.pfSettings.source.id) {
      settingsSourceId = settingsSourceId + xhr.pfSettings.source.id;
    }
    console.log(settingsSourceId);
    console.log("Form data:\n" + decodeURIComponent(xhr.pfSettings.data));
    console.log("Response text:\n" + xhr.responseText);
    console.log("PrimeFaces.ajax.Queue.xhrs[0]:\n" + JSON.stringify(xhr));
  }
}

function onClickMenuItem(menuItem, isWorkingOnATask, isOpenOnNewTab) {
  if(event !== undefined
      && (typeof isWorkingOnATask === 'boolean' && isWorkingOnATask === true
          || typeof isOpenOnNewTab === 'boolean' && isOpenOnNewTab === true)) {
    $(menuItem).unbind('click', event.handler);
    executeStoreMenuRemoteCommand(menuItem, isWorkingOnATask, isOpenOnNewTab);
    event.stopImmediatePropagation();
  }
  else {
    executeStoreMenuRemoteCommand(menuItem, isWorkingOnATask, isOpenOnNewTab);
  }
}

function executeStoreMenuRemoteCommand(menuItem, isWorkingOnATask, isOpenOnNewTab) {
  storeSelectedMenuItems([{
    name : 'selectedMenuId',
    value : $(menuItem).closest("li").attr("id")
  },
  {
    name : 'isWorkingOnATask',
    value : isWorkingOnATask
  },
  {
    name : 'isOpenOnNewTab',
    value : isOpenOnNewTab
  }]);
}

function fireEventClickOnMenuItem(menuItem, prevMenuItemId) {
  PF('main-menu').addMenuitem(menuItem);
  if (prevMenuItemId !== menuItem) {
    PF('main-menu').removeMenuitem(prevMenuItemId);
  }
}

function resetPortalLeftMenuState() {
  $.removeCookie('freya_expandeditems', {path: '/'});
  if (typeof resetSelectedMenuItems === "function") {
    resetSelectedMenuItems();
  }
}

function hideDashboardOverlayPanels() {
  $(".js-dashboard-overlay-panel").each(function(){
    if ($(this).hasClass("ui-overlay-visible")) {
      $(this).removeClass("ui-overlay-visible").addClass("ui-overlay-hidden");
    }
  });
}

function highlightDashboardItem(menuId) {
  var $board = $("[id*='_js__" + menuId + "']");
  if ($board.length > 0) {
    activeMenuItemOnLeftMenu($board.attr("id"));
  }
}

function activeMenuItemOnLeftMenu(menuId) {
  PF('main-menu').addMenuitem(menuId);
  let $selectedMenu = $("[id$='" + menuId + "']");
  if (!$selectedMenu.hasClass('active-menuitem')) {
    $selectedMenu.addClass('active-menuitem');
  }
}

function deactivateMenuItemOnLeftMenu(menuId) {
  PF('main-menu').removeMenuitem(menuId);
  let $removedMenu = $("[id$='" + menuId + "']");
  if ($removedMenu.hasClass('active-menuitem')) {
    $removedMenu.removeClass('active-menuitem');
  }
}

function changeThemeModeRefeshCharts() {
  let charts = $("div[id*='task_by_priority_chart-']");
  updateChartLegendLabelColor(charts);

  charts = $("div[id*='chart-statistic']");
  updateChartLegendLabelColor(charts);

  charts = $("div[id*='statistics-widget']");
  updateChartLegendLabelColor(charts);
}

function updateChartLegendLabelColor(charts) {
  for (const chart of charts) {
    let widgetVar = getWidgetVarById(chart.id);
    if(widgetVar && widgetVar.cfg && widgetVar.cfg.config && widgetVar.cfg.config.options) {
      let options = jQuery.extend(true, {}, widgetVar.cfg.config.options);
        options = {
          plugins: {
              legend: {    
                labels: {
                  color: getColor('--chart-default-legend-color')
                }
              }
          }
        };

        jQuery.extend(true, widgetVar.cfg.config.options, options);
        widgetVar.refresh(widgetVar.cfg);
    }
  }
}

function getWidgetVarById(id) {
  for (var propertyName in PrimeFaces.widgets) {
    var widget = PrimeFaces.widgets[propertyName];
    if (widget && widget.id === id) {
      return widget;
    }  
  }
  return null;
}/***UTILITY FUNCTIONS FOR ANNOTATION AND HIGHLIGHT****/
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
  var topMenu = $("#top-menu");
  var searchIcon = topMenu.find("li.topbar-item.search-item");
  $(searchIcon).addClass("red-topbottomleft-medium-border");
  var themeSwitchIcon = topMenu.find("li.topbar-item.themeswitch-item");
  $(themeSwitchIcon).addClass("red-topbottom-medium-border");
  var chatItem = topMenu.find("li.topbar-item.chat-item");
  $(chatItem).addClass("red-topbottom-medium-border");
  var userNameItem = topMenu.find("li.topbar-item.user-profile.setting-container");
  $(userNameItem).addClass("red-topbottomright-medium-border");
}

function highlightChatIcon() {
  var topMenu = $("#top-menu");
  var chatItem = topMenu.find("li.topbar-item.chat-item");
  $(chatItem).addClass("red-medium-border");
}

function highlightNotificationFullPageIcon(){
  var icon = $("#notification-full-page");
  icon.addClass("red-medium-border");
}

function highlightNotificationIcon(){
  var icon = $("#open-notifications-panel");
  icon.addClass("red-medium-border");
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
  
  var startLink = $("label[for$='add-external-link-form:external-link']");
  appendStepAnnotation(startLink, "4", -5, startLink.width());
  
  var visibility = $("label[for$='add-external-link-form:external-link-type-radio']");
  appendStepAnnotation(visibility, "5", -5, visibility.width());
  
  var icon = $("[id$='add-external-link-form:external-link-icon:awesome-icon-selection']");
  appendStepAnnotation(icon, "6", -10, icon.width());
  
  var uploadButton = $("[id$='add-external-link-form:external-link-image-upload']");
  appendStepAnnotation(uploadButton, "7", 0, 80);
  
  var addButton = $("[id$='process-widget:adding-new-external-link-command']");
  appendStepAnnotation(addButton, "8", -25, -5);
}

function highlightProcessItems() {
  var processSearch = $('[id$="process-widget:process-search"]');
  createRedMediumOutline(processSearch);
  appendStepAnnotation(processSearch, "1", -5, processSearch.width() - 40);
  
  var expressLogo = $('.express-workflow .image-process-icon:eq(0)');
  createRedMediumOutline(expressLogo);
  appendStepAnnotation(expressLogo, "2", -15, -30);
  
  var externalLink = $('.js-external-link-process-item .image-process-icon:eq(0)');
  createRedMediumOutline(externalLink);
  appendStepAnnotation(externalLink, "3", -15, -30);
  
  createRedMediumOutline($('[id$="process-widget:process-view-mode:view-mode-selection"]'));
  appendStepAnnotation($("[id$='process-widget:process-view-mode:view-mode-selection'] div[class$='ui-state-active']"), "4", -10, -40);
}

function highlightEditProcessIcon() {
  appendStepAnnotation($("[id$='process-widget:edit-process-form:edit-process-icon:awesome-icon-selection']"), "4", -10, 100);
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

function numberingStatisticWidget() {
  var chartInfo = $('.chart-info:eq(0)');
  appendStepAnnotation(chartInfo, "1", -10, 25);
  var chartCanvas = $('.ui-carousel-items-content');
  appendStepAnnotation(chartCanvas, "2", chartCanvas.width()/4, chartCanvas.height()/2 + 10);
  appendStepAnnotation(chartCanvas, "3", 15, chartCanvas.width()*0.75);
}

function highlightAdminSettings() {
  createRedMediumOutline($("#adminui-menu-item"));
}

function highlightMobileApp() {
  createRedMediumOutline($("#mobile-app-item"));
}

function highlightUserMenuConfiguration() {
  createRedMediumOutline($("a[id$='menu-configuration-0']"));
  createRedMediumOutline($("a[id$='menu-configuration-1']"));
}

function highlightCaseMenuItem() {
  createRedThickOutlineWithOffset($('.layout-menu .CASE'));
}

function highlightShowMoreNoteLink() {
  createRedMediumOutline($('a.js-note-show-more-link'));
}

function highlightStatisticNavigation() {
   createRedThickOutlineWithOffset($('.layout-menu .STATISTICS'));
}

function highlightTaskAnalysisNavigationLink() {
  createRedMediumOutline($("a[id$=':task-analysis-page-navigation-link']"));
}

function numberingChartPanel() {
  var chartName = $('.chart-name:eq(1)');
  appendStepAnnotation(chartName, "1", -30, -25);
  var chartInfo = $('.chart-info:eq(1)');
  appendStepAnnotation(chartInfo, "2", -20, 25);
  var chartActions = $('.chart-actions-container:eq(0)');
  appendStepAnnotation(chartActions, "3", -20, 0);
  var chartCanvas = $('.statistic-chart:eq(1)');
  appendStepAnnotation(chartCanvas, "4", chartCanvas.width()/4, chartCanvas.height()/2 + 10);
  appendStepAnnotation(chartCanvas, "5", 15, chartCanvas.width()*0.8);
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
  var filterLabel = $("[id$=':creator-filter:filter-open-form:advanced-filter-command']");
  createRedMediumOutline(filterLabel);
  appendStepAnnotation(filterLabel, "1", -25, -30);
  
  var removeFilterCommand = $("[id$=':creator-filter:filter-open-form:advanced-filter-remove-command']");
  createRedMediumOutline(removeFilterCommand);
  appendStepAnnotation(removeFilterCommand, "2", -25, removeFilterCommand.width());
  
  var updateCommand = $("[id$=':creator-filter:filter-input-form:update-command']");
  createRedMediumOutline(updateCommand);
  appendStepAnnotation(updateCommand, "3", -2, -40)
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
  var customCheckbox = columnsCheckbox.find("td:eq(7)");
  createRedMediumOutline(customCheckbox);
  appendStepAnnotation(customCheckbox, "2", -20, customCheckbox.width() - 10);
  
  var taskHeader = $(".task-start-link.js-task-start-link:eq(4)");
  var customTaskItemCell = taskHeader.find(".task-custom-field-cell");
  createRedMediumOutline(customTaskItemCell);
  appendStepAnnotation(customTaskItemCell, "3", -20, - 20);
}


function highlightTaskStateFilter() {
  var filterLabel = $("[id$=':state-filter:filter-open-form:advanced-filter-command']");
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

function highlightCreateExpressWorkflow() {
  createRedMediumOutline($("[id$='process-widget:create-express-link']"));
}

function highlightExpressTaskResponsible(stepNumber) {
  var taskResponsible = '.task-responsible:eq(' + stepNumber + ')';
  createRedMediumOutline($(taskResponsible));
}

function highlightManagementExpressTab() {
  $("a[href$=':adminTabView:express-management-tab']").parent().addClass("red-medium-border");
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

function scrollToMiddleOfLayoutContent() {
  window.scrollTo(0, document.body.scrollHeight/2);
}

function scrollToMiddleOfLayoutContent2() {
  window.scrollTo(0, document.body.scrollHeight/4);
}

function scrollToBottomOfLayoutContent() {
  window.scrollTo(0, document.body.scrollHeight);
}

function highlightCustomTaskDetail() {
  var customTop = $('.custom-task-details-panel-top');
  appendStepAnnotation(customTop, 1, -45, customTop.width()/2);
  
  var customBottom = $('.custom-task-details-panel-bottom');
  appendStepAnnotation(customBottom, 2, -45, customBottom.width()/2);
}

function highlightCustomTaskDetailWithNewStyle() {
  var customPanel1 = $('.custom-task-panel-1');
  appendStepAnnotation(customPanel1, 1, -10, customPanel1.width()/2);
  
  var customPanel2 = $('.custom-task-panel-2');
  appendStepAnnotation(customPanel2, 2, -10, customPanel2.width()/2);

  var customPanel3 = $('.custom-task-panel-3');
  appendStepAnnotation(customPanel3, 3, -10, customPanel3.width()/2);
  
  var customPanel4 = $('.custom-task-panel-4');
  appendStepAnnotation(customPanel4, 4, -10, customPanel4.width()/2);
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
  createRedThickOutlineWithOffset($('.layout-menu .PROCESS'));
}

function highlightShowAdditionalLink() {
  createRedMediumOutline($("[id$='case-item:case-item-action-form:action-step-component:show-additional-case-details-link']"));
}

function highlightTaskExportToExcelButton() {
  createRedMediumOutline($("[id$=':task-export-to-excel-form']"));
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

function addStepToCustomWidgetTopTaskDetails() {
  var topWidget = $(".custom-task-details-panel-top");
  appendStepAnnotation(topWidget, 1, -30, topWidget.width()/2)
}

function addStepTCustomWidgetTopTaskDetails() {
  var bottomWidget = $(".custom-task-details-panel-bottom");
  appendStepAnnotation(bottomWidget, 2, -30, bottomWidget.width()/2)
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

function highlightServerInfo() {
  createRedMediumOutline($("#server-infor"));
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