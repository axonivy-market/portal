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

    // Don't update layout if rendering AI result
    if (!$layoutMain.parent('.iframe-body').hasClass('ai-result')) {
      if (headerFooterHeight === 0 && envHeight === 0) {
        $layoutMain.removeAttr('style');
      } else {
        const layoutMainPaddingTop = headerHeight + layoutTopbarHeight + 20; // By default, Freya buffer 20px from topbar, refer to .layout-main class
        $layoutMain.css({'padding-top': layoutMainPaddingTop + 'px', 'padding-bottom' : footerHeight + 'px'});
      }
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
      ["PortalCases.xhtml", "CASE"],
      ["CaseWidget.xhtml", "CASE"],
      ["PortalCaseDetails.xhtml", "CASE"],
      ["CaseItemDetails.xhtml", "CASE"],
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
    });
  },

  highlightMenuItem : function() {
    let $currentPageMenu = this.getMenuItemByCurrentPage();
    let activeMenuItemList = this.getActiveMenu();
    if ($currentPageMenu.length == 0 && window.location.pathname.indexOf("PortalMainDashboard.xhtml") > -1) {
      let selectedMainDashboardId = $("#user-menu-required-login").attr("data-selected-menu");
      $currentPageMenu = $("li[id$='" + selectedMainDashboardId + "-main-dashboard'] > a");
      if ($currentPageMenu.length == 0) {
        $currentPageMenu = $(".layout-menu").find('li[role="menuitem"] a.DASHBOARD');
      }
    }

    if ($currentPageMenu.length > 0) {
      var $dashboardGroup = $(".js-dashboard-group");
      if ($currentPageMenu.hasClass("DASHBOARD") && $dashboardGroup.length > 0) {
        $.each( activeMenuItemList, function( i, menuItem ) {
            if (!(menuItem.id.includes('-parent-dashboard'))) {
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
      let pfMainMenu = PF('main-menu');
      if (pfMainMenu) {
        pfMainMenu.addMenuitem($currentPageMenu.parent().attr('id'));
      }
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

  // Hide AJAX loader that block the error dialog
  $('.portal-ajax-loader').hide();

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
      PF('detail-error-dialog')?.show();
    });
  }
  PF('error-ajax-dialog')?.show();
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
  let pfMainMenu = PF('main-menu');
  if (pfMainMenu) {
    pfMainMenu.addMenuitem(menuItem);
    if (prevMenuItemId !== menuItem) {
      pfMainMenu.removeMenuitem(prevMenuItemId);
    }
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
  let pfMainMenu = PF('main-menu');
  if (pfMainMenu) {
    pfMainMenu.addMenuitem(menuId);
  }
  let $selectedMenu = $("[id$='" + menuId + "']");
  if (!$selectedMenu.hasClass('active-menuitem') && !$selectedMenu.siblings('.active-menuitem').length) {
    $selectedMenu.addClass('active-menuitem');
  }
}

function deactivateMenuItemOnLeftMenu(menuId) {
  let pfMainMenu = PF('main-menu');
  if (pfMainMenu) {
    pfMainMenu.removeMenuitem(menuId);
  }
  let $removedMenu = $("[id$='" + menuId + "']");
  if ($removedMenu.hasClass('active-menuitem')) {
    $removedMenu.removeClass('active-menuitem');
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
}


function reloadIframesInsidePortalAIChat() {
  var iframeInsidePortalAIChat = document.getElementById('iFrame');
  if (iframeInsidePortalAIChat) {
    var contentInsideIframe = iframeInsidePortalAIChat.contentDocument || iframeInsidePortalAIChat.contentWindow.document;
    var iframes = contentInsideIframe.getElementsByClassName('message-iframe');
    for (let item of iframes) {
      item.src = item.src;
    }
  }
}

function handleKeyDown(event) {
  if (event.key === 'Enter') {
    event.preventDefault();
  }
  if (isPressedSpecialKeys(event)) {
    event.stopPropagation();
  }
}

function shouldTriggerAjax(event) {
  return !isPressedSpecialKeys(event);
}

function isPressedSpecialKeys(event) {
  const ctrlPressed = event.ctrlKey || event.metaKey;
  const shiftPressed = event.shiftKey;

  const ctrlKeyActions = ['z', 'y', 'x', 'c', 'v', 'a'];
  const arrowKeys = [37, 38, 39, 40]; // Arrow Left, Arrow Up, Arrow Right, Arrow Down

  if (ctrlPressed && ctrlKeyActions.includes(event.key.toLowerCase())) {
      return true;
  }

  if (shiftPressed && arrowKeys.includes(event.keyCode)) {
      return true;
  }

  if (arrowKeys.includes(event.keyCode)) {
    return true;
  }

  const specialKeys = [
    'Control', 'Alt', 'Pause', 'CapsLock', 'Escape',
    'PageUp', 'PageDown', 'PrintScreen', 'Insert', 'Meta',
    'ContextMenu', 'NumLock', 'ScrollLock', 'Home', 'End'
  ];

  return specialKeys.includes(event.key);
}

function showQuickSearchInput(index) {
  var widgetHeaderQuickSearch = "div[id$='widget-header-quick-search-" + index + "']";
  var widgetHeaderActions = "div[id$='process-panel-group-" + index + "'] div[id$='widget-header-actions']";
  var quickSearchInput = "input[id$='quick-search-input-" + index + "']";
  if ($(widgetHeaderActions).css("display") == "none") {
    showWidgetHeaderActions();
  } else {
    hideWidgetHeaderActions();
  }
  changeQuickSearchIconButton(index);

  function showWidgetHeaderActions() {
    $(widgetHeaderActions).css("display","inline-flex");
    $(widgetHeaderQuickSearch).removeClass("widget-header-quick-search-show");
  }

  function hideWidgetHeaderActions() {
    $(widgetHeaderActions).css("display","none");
    $(widgetHeaderQuickSearch).addClass("widget-header-quick-search-show");
    $(quickSearchInput).css("width","100%");
  }

  function changeQuickSearchIconButton(index) {
    var spanEl = "button[id$='quick-search-icon-" + index + "'] > span";
    var quickSearchButton = "button[id$='quick-search-icon-" + index + "']";
    if ($(spanEl).hasClass("si-search")) {
      $(spanEl).removeClass("si-search");
      $(spanEl).addClass("si-remove");
      $(quickSearchButton).blur();
      $(quickSearchInput).focus();
      $(quickSearchInput).click();  
    } else {
      $(spanEl).removeClass("si-remove");
      $(spanEl).addClass("si-search");
    }
  }
}



/**
 * This constant and functions below are used for accessibility feature for shortcuts navigation.
 * User can press Alt + number to focus on left side menu item or search button, user setting
 *
 */
const singleDashboardId = '[id="user-menu-required-login:main-navigator:main-menu__js__dashboard_0-parent-dashboard"]';
const multipleDashboardId = '[id="user-menu-required-login:main-navigator:main-menu__js__DASHBOARD-parent-dashboard"]';
const processItemId = '[id^="user-menu-required-login:main-navigator:main-menu_process"]';
const taskItemId = '[id="user-menu-required-login:main-navigator:main-menu__js__default-task-list-dashboard-main-dashboard"]';
const caseItemId = '[id="user-menu-required-login:main-navigator:main-menu__js__default-case-list-dashboard-main-dashboard"]';
const searchInputId = '[id="global-search-component:global-search-data"]:visible'
const useSettingMenuId = 'a#user-settings-menu:visible';

$(document).ready(function () {

  const shortcuts = {
    'Digit1': $(singleDashboardId).length ? singleDashboardId : multipleDashboardId,
    'Digit2': processItemId,
    'Digit3': taskItemId,
    'Digit4': caseItemId,
    'Digit5': [searchInputId],
    'Digit6': useSettingMenuId
  };

  $(searchInputId).attr('role', 'combobox');

  function findTargetElementByKey(key) {
    if (key === 'Digit5') {
      return $(shortcuts[key].find(h => $(h).length));
    } else if (key === 'Digit6') {
      return $(shortcuts[key]);
    }
    return $(shortcuts[key]).find('a').first();
  }

  function removeFocusClass(element) {
    if (element) {
      element.removeClass('focused');
      element.blur();
    }
  }

  function addFocusClass(element) {
    if (element) {
      element.addClass('focused');
      element.focus();
    }
  }

  function removeFocusedElements() {
    Object.keys(shortcuts).forEach(function (key) {
      removeFocusClass(findTargetElementByKey(key));
    });

    removeFocusClass(focusedTaskEl);
    removeFocusClass(focusedCaseEl);
    removeFocusClass(focusedProcessEl);
    removeFocusClass(focusedCaseSideStepEl);
    removeFocusClass(focusedResetTaskFormEl);
    removeFocusClass(focusedTaskSideStepEl);
  }

  function handleFocusOnMainElement(event) {
    removeFocusedElements();
    const key = event.code;
    if (shortcuts[key]) {
      addFocusClass(findTargetElementByKey(key));
    }
  }

  function onlyAltPressed(event) {
    return event ? event.altKey && !event.ctrlKey && !event.shiftKey && !event.metaKey : false;
  }

  const iframe = document.getElementById('iFrame');

  if (iframe) {
    iframe.onload = function () {
      const iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
      iframeDocument.addEventListener('keydown', function (event) {
        if (onlyAltPressed(event)) {
          handleFocusOnMainElement(event);
        }
      });
      handleExpandButtonInFilePreview(document.getElementById("iFrame").contentWindow);

    };
  }

  let taskIndex = 0;
  let resetTaskFormIndex = 0;
  let taskSideStepIndex = 0;
  let caseIndex = 0;
  let caseSideStepIndex = 0;
  let processIndex = 0;
  let focusedTaskEl;
  let focusedCaseEl;
  let focusedCaseSideStepEl;
  let focusedProcessEl;
  let focusedResetTaskFormEl = 0;
  let focusedTaskSideStepEl;

  $(document).on('keydown', function (event) {
    var keyCode = event.code;
    if (keyCode === 'Escape') {
      var collapseWidgetBtn = $('[id*="collapse-link"]:visible');
      if (collapseWidgetBtn.length > 0) {
        collapseWidgetBtn.click();
      }

      var caseSideActionCloseBtn = $('[id*="action-steps-panel"]:visible').find('.ui-overlaypanel-close');
      if (caseSideActionCloseBtn.length > 0) {
        caseSideActionCloseBtn.click();

        if (focusedCaseEl) {
          focusedCaseEl.addClass('focused');
          focusedCaseEl.focus();
        }
      }

      var taskSideActionCloseBtn = $('[id*="side-steps-panel"]:visible').find('.ui-overlaypanel-close');
      if (taskSideActionCloseBtn.length > 0) {
        taskSideActionCloseBtn.click();
      }
      return;
    }

    if (keyCode === 'Tab') {
      removeFocusedElements();
      return;
    }

    var caseActionStepsPanel = $('[id*="action-steps-panel"]:visible');
    var caseActionStepsPanelVisible = caseActionStepsPanel.length > 0;

    var resetTaskConfirmForm = $('[id$="task-component:reset-task-confirmation-form"]:visible');
    var resetTaskConfirmFormVisible = resetTaskConfirmForm.length > 0;

    var taskActionStepsPanel = $('[id$=":side-steps-panel"]:visible');
    var taskActionStepsPanelVisible = taskActionStepsPanel.length > 0;

    if (onlyAltPressed(event)) {
      var keyCode = event.code;
      if (shortcuts[keyCode]) {
        event.preventDefault();
        removeFocusedElements();
        taskIndex = 0;
        taskSideStepIndex = 0;
        processIndex = 0;
        if (caseActionStepsPanelVisible) {
          caseSideStepIndex = 0;
        } else {
          caseIndex = 0;
        }
        handleFocusOnMainElement(event);
      } else if (keyCode == 'KeyW') {
        //Short cuts for Task widget
        if (resetTaskConfirmFormVisible) {
          var cancelOk = [
            resetTaskConfirmForm.find('a:first'),
            resetTaskConfirmForm.find('button:first')
          ];

          if (resetTaskFormIndex >= cancelOk.length) {
            resetTaskFormIndex = 0;
          }

          removeFocusedElements();

          focusedResetTaskFormEl = $(cancelOk[resetTaskFormIndex]);
          addFocusClass(focusedResetTaskFormEl);
          resetTaskFormIndex++;
        } else if (taskActionStepsPanelVisible) {

          var steps = taskActionStepsPanel.find('div.ui-overlaypanel-content a');
          if (taskSideStepIndex >= steps.length) {
            taskSideStepIndex = 0;
          }

          removeFocusedElements();

          focusedTaskSideStepEl = $(steps[taskSideStepIndex]);
          addFocusClass(focusedTaskSideStepEl);
          taskSideStepIndex++;
        } else {
          var taskList = $('[id$=":task-component:dashboard-tasks"] table tr td:visible [id$=":start-task"]');
          if (taskIndex >= taskList.length) {
            taskIndex = 0;
          }

          removeFocusedElements();
          processIndex = 0;
          taskSideStepIndex = 0;
          if (caseActionStepsPanelVisible) {
            caseSideStepIndex = 0;
          } else {
            caseIndex = 0;
          }

          focusedTaskEl = $(taskList[taskIndex]);
          addFocusClass(focusedTaskEl);
          taskIndex++;
        }
      } else if (keyCode == 'KeyQ') {
        //Short cuts for Case widget
        if (caseActionStepsPanelVisible) {
          var steps = caseActionStepsPanel.find('div.ui-overlaypanel-content a');

          if (caseSideStepIndex >= steps.length) {
            caseSideStepIndex = 0;
          }

          removeFocusedElements();

          focusedCaseSideStepEl = $(steps[caseSideStepIndex]);
          addFocusClass(focusedCaseSideStepEl);
          caseSideStepIndex++;
        } else {
          var caseList = $('[id$="case-component:dashboard-cases"] table tr td:visible [id$=":dashboard-case-side-steps-menu"]');

          if (caseIndex >= caseList.length) {
            caseIndex = 0;
          }

          removeFocusedElements();
          taskIndex = 0;
          taskSideStepIndex = 0;
          processIndex = 0;
          caseSideStepIndex = 0;

          focusedCaseEl = $(caseList[caseIndex]);
          addFocusClass(focusedCaseEl);
          caseIndex++;
        }
      } else if (keyCode == 'KeyA') {
        //Short cuts for Process widget
        var processList = $('[id$=":process-component:process-list"]').find('a');

        if (processIndex >= processList.length) {
          processIndex = 0;
        }

        removeFocusedElements();
        taskIndex = 0;
        taskSideStepIndex = 0;
        if (caseActionStepsPanelVisible) {
          caseSideStepIndex = 0;
        } else {
          caseIndex = 0;
        }

        focusedProcessEl = $(processList[processIndex]);
        addFocusClass(focusedProcessEl);
        processIndex++;
      }
    }
  });

  // HANDLE EXPAND BUTTON IN FILE REVIEW
  function handleExpandButtonInFilePreview(contentWindow) {
    contentWindow = contentWindow || window;
    var $document = $(contentWindow.document);

    if ($document.find('[id$=":preview-document-dialog"]').length) {
      setTimeout(function () {
        var dialog = contentWindow.PF('preview-document-dialog');
        const dialogElement = dialog.jq.get(0);

        dialogElement.addEventListener('transitionend', adjustMediaHeight);
        dialogElement.addEventListener('animationend', adjustMediaHeight);

        const resizeObserver = new ResizeObserver(adjustMediaHeight);
        resizeObserver.observe(dialogElement);

        $document.on('click', '.ui-dialog-titlebar-maximize, .ui-dialog-titlebar-restore', function () {
          setTimeout(adjustMediaHeight, 100);
        });

        function adjustMediaHeight() {
          var dialog = contentWindow.PF('preview-document-dialog').jq;
          var media = dialog.find('.ui-widget-content').find('.ui-g-12 object');

          if (dialog.hasClass('ui-dialog-maximized')) {
            var newHeight = dialog.height() - 130;
            media.css('height', newHeight + 'px');
          } else {
            media.css('height', '600px');
          }
        }

/*        contentWindow.PF('preview-document-dialog').jq.on('dialogclose', function () {
          resizeObserver.disconnect();
        });*/

      }, 200);
    }
  }

  handleExpandButtonInFilePreview();

  // END OF HANDLE EXPAND BUTTON IN FILE REVIEW
});
// End of accessibility for shortcuts navigation

