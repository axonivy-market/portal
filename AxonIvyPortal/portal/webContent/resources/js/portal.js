// Freya style has the transition delay time is 0.2s when expand/collapse main menu
// We need to delay a bit before calculating scrollbar
var isFinishedRestoreMenuState = false;
var delayTime = 0;
var DEFAULT_SIDEBAR_RAIL_WIDTH = '50px';

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

    let notificationsBadgeValueEl = document.getElementById('notifications-badge-value');
    if (notificationsBadgeValueEl) {
      let notificationsBadgeTemplate = document.getElementById('notifications-badge-label-template');
      let notificationsBadgeLink = document.getElementById('open-notifications-panel');
      if (notificationsBadgeTemplate && notificationsBadgeLink) {
        let count = notificationsBadgeValueEl.value;
        notificationsBadgeLink.setAttribute('aria-label', notificationsBadgeTemplate.value.replace('{0}', count));
      }
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
        } else if (layoutWrapper.hasClass('sidebar-click-mode')) {
          breadCrumbMarginLeft = ($('.js-layout-main').css('padding-left') || DEFAULT_SIDEBAR_RAIL_WIDTH);
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
      ["Processes.xhtml", "PROCESS_LIST"],
      ["PortalTasks.xhtml", "TASK"],
      ["TaskWidget.xhtml", "TASK"],
      ["PortalCases.xhtml", "CASE"],
      ["CaseWidget.xhtml", "CASE"],
      ["PortalCaseDetails.xhtml", "CASE"],
      ["CaseItemDetails.xhtml", "CASE"],
      ["PortalDashboard.xhtml", "DASHBOARD"],
      ["PortalDashboardConfiguration.xhtml", "PORTAL_CONFIGURATION"]],

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
  deleteCookie('freya_expandeditems');
  if (typeof resetSelectedMenuItems === "function") {
    resetSelectedMenuItems();
  }
}

function restorePortalLeftMenuState() {
  let pfMainMenu = PF('main-menu');
  if (pfMainMenu) {
    pfMainMenu.restoreMenuState();
  }
}

function closeUserSettingsMenu() {
  var trigger = $('#user-settings-menu');
  if (!trigger.parent().hasClass('active-topmenuitem')) {
    return false;
  }
  trigger.trigger('click').trigger('focus');
  return true;
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
  const specialKeys = [
    'Control', 'Alt', 'Pause', 'CapsLock', 'Escape',
    'PageUp', 'PageDown', 'PrintScreen', 'Insert', 'Meta',
    'ContextMenu', 'NumLock', 'ScrollLock', 'Home', 'End', 'Tab'
  ];

  return (ctrlPressed && ctrlKeyActions.includes(event.key.toLowerCase()))
      || (shiftPressed && arrowKeys.includes(event.keyCode))
      || arrowKeys.includes(event.keyCode)
      || specialKeys.includes(event.key);
}

function showQuickSearchInput(index) {
  var widgetHeaderQuickSearch = "div[id$='widget-header-quick-search-" + index + "']";
  var quickSearchInput = "input[id$='quick-search-input-" + index + "']";

  $(widgetHeaderQuickSearch).toggleClass("widget-header-quick-search-show");
  changeQuickSearchIconButton(index);

  function changeQuickSearchIconButton(index) {
    var spanEl = "button[id$='quick-search-icon-" + index + "'] > span";
    var quickSearchButton = "button[id$='quick-search-icon-" + index + "']";
    if ($(spanEl).hasClass("ti-search")) {
      $(spanEl).removeClass("ti-search");
      $(spanEl).addClass("ti-x");
      $(quickSearchButton).blur();
      $(quickSearchInput).focus();
      $(quickSearchInput).click();
    } else {
      $(spanEl).removeClass("ti-x");
      $(spanEl).addClass("ti-search");
      if ($(quickSearchInput).val() !== "") {
        $(quickSearchInput).val("").trigger("cut");
      }
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
const pinButton = 'a[id="user-menu-required-login:toggle-menu"]';
let isKeyboardShortcutsEnabled = false;

function initKeyboardShortcutsEnabledValue(value) {
  if (typeof value === 'boolean') {
    isKeyboardShortcutsEnabled = value;
  }
}

$(document).ready(function () {
  initFocusManagament(window);
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

  function toggleLeftMenu(key) {
    if (key === 'Digit7') {
      if (SidebarClickMode.mode === 'CLICK') {
        SidebarClickMode.toggle();
      } else {
        addFocusClass($(pinButton));
        $(pinButton).trigger('click');
      }
      return true;
    }
    removeFocusClass($(pinButton));
    return false;
  }

  function removeFocusedElements() {
    Object.keys(shortcuts).forEach(function (key) {
      removeFocusClass(findTargetElementByKey(key));
    });

    removeFocusClass(focusedTaskEl);
    removeFocusClass(focusedCaseEl);
    removeFocusClass(focusedProcessEl);
    removeFocusClass(focusedResetTaskFormEl);
  }

  function handleFocusOnMainElement(event) {
    removeFocusedElements();
    const key = event.code;
    if (shortcuts[key]) {
      addFocusClass(findTargetElementByKey(key));
    }
  }

  function initShortcutsNavigationOnIframe(iframe) {
    const iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
    iframeDocument.addEventListener('keydown', function (event) {
      if (isKeyboardShortcutsEnabled && onlyAltPressed(event)) {
        if (event.code === 'KeyW' || event.code === 'KeyQ' || event.code === 'KeyA') {
          event.preventDefault();
          window.focus();
          document.body.focus();
          
          const parentEvent = new KeyboardEvent('keydown', {
            code: event.code,
            key: event.key,
            altKey: event.altKey,
          });
          
          document.dispatchEvent(parentEvent);
          return;
        }
        
        if(toggleLeftMenu(event.code)) {
          return;
        }
        handleFocusOnMainElement(event);
      }
    });
  }

  function onlyAltPressed(event) {
    return event ? event.altKey && !event.ctrlKey && !event.shiftKey && !event.metaKey : false;
  }

  function hasVisibleOverlayOnTop() {
    return $('.ui-dialog:visible, .ui-overlaypanel:visible, .ui-menu-overlay:visible').length > 0;
  }

  function collapseExpandedWidget() {
    if (hasVisibleOverlayOnTop()) {
      return;
    }

    var collapseWidgetBtn = $('[id*="collapse-link"]:visible');
    if (collapseWidgetBtn.length > 0) {
      collapseWidgetBtn.click();
      return;
    }

    var expandedWidget = $('.grid-stack-item.expand-fullscreen').first();
    if (expandedWidget.length > 0 && typeof toggleFullscreen === 'function') {
      var widgetIndex = expandedWidget.attr('data-index');
      toggleFullscreen(widgetIndex, expandedWidget.attr('gs-id'));
      $('.actions-menu-button-' + widgetIndex + ':visible').first().trigger('focus');
    }
  }

  const iframes = document.getElementsByTagName('iframe');
  
  if (iframes.length > 0) {
    Array.from(iframes).forEach(function(iframe) {
      iframe.onload = function () {
        initShortcutsNavigationOnIframe(iframe);
        handleExpandButtonInFilePreview(iframe.contentWindow);
      }
    });
  }

  let taskIndex = 0;
  let resetTaskFormIndex = 0;
  let caseIndex = 0;
  let processIndex = 0;
  let focusedTaskEl;
  let focusedCaseEl;
  let focusedProcessEl;
  let focusedResetTaskFormEl = 0;

  $(document).on('keydown', function (event) {

    var keyCode = event.code;
    if (keyCode === 'Escape') {
      hideVisibleTooltips();
      if (hideOpenOverlayMenus()) {
        event.preventDefault();
        return;
      }
      if (event.isDefaultPrevented() || isEscapeHandledByDialog()) {
        return;
      }
      if (closeUserSettingsMenu()) {
        return;
      }
      collapseExpandedWidget();
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

    if (onlyAltPressed(event) && isKeyboardShortcutsEnabled) {
      var keyCode = event.code;
      if(toggleLeftMenu(keyCode)) {
        return;
      }
      if (shortcuts[keyCode]) {
        event.preventDefault();
        removeFocusedElements();
        taskIndex = 0;
        processIndex = 0;
        if (!caseActionStepsPanelVisible) {
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
        } else if (!taskActionStepsPanelVisible) {
          var taskList = $('[id$=":task-component:dashboard-tasks"] table tr td:visible [id$=":start-task"]');
          if (taskIndex >= taskList.length) {
            taskIndex = 0;
          }

          removeFocusedElements();
          processIndex = 0;
          if (!caseActionStepsPanelVisible) {
            caseIndex = 0;
          }

          focusedTaskEl = $(taskList[taskIndex]);
          addFocusClass(focusedTaskEl);
          taskIndex++;
        }
      } else if (keyCode == 'KeyQ') {
        //Short cuts for Case widget
        if (!caseActionStepsPanelVisible) {
          var caseList = $('[id$="case-component:dashboard-cases"] table tr td:visible [id$=":dashboard-case-side-steps-menu"]');

          if (caseIndex >= caseList.length) {
            caseIndex = 0;
          }

          removeFocusedElements();
          taskIndex = 0;
          processIndex = 0;

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
        if (!caseActionStepsPanelVisible) {
          caseIndex = 0;
        }

        focusedProcessEl = $(processList[processIndex]);
        addFocusClass(focusedProcessEl);
        processIndex++;
      }
    }
  });

  // HANDLE EXPAND BUTTON IN FILE PREVIEW
  function handleExpandButtonInFilePreview(contentWindow) {
    contentWindow = contentWindow || window;
    var $document = $(contentWindow.document);

    if ($document.find('[id$=":preview-document-dialog"]').length) {
      setTimeout(function () {

        $document.on('click', '.ui-dialog-titlebar-maximize, .ui-dialog-titlebar-restore', function () {
          setTimeout(adjustMediaHeight, 100);
        });

        function adjustMediaHeight() {
          var dialog = contentWindow.PF('preview-document-dialog').jq;
          var content = dialog.find('.ui-dialog-content');
          var media = content.find('.ui-g-12 object');
          if (dialog.hasClass('ui-dialog-maximized')) {
            var newHeight = dialog.height() - 130;
            content.css('width', '');
            media.css('height', newHeight + 'px');
          } else {
            content.css('width', '');
            media.css('height', '600px');
          }
        }

      }, 200);
    }
  }

  handleExpandButtonInFilePreview();

  // END OF HANDLE EXPAND BUTTON IN FILE PREVIEW

  // START: FIX ACCESSIBILITY ISSUES
  setTimeout(function () {
    let combobox = $("span[role='combobox']");
    combobox.each((index, item) => {
      if ($(item).attr('aria-label') === undefined) {
        $(item).attr('aria-label', $(item).text());
      }
    });
	
  }, 200);

  syncAriaExpandedWithClass(document.querySelector('.layout-topbar-left a.menu-button'), document.querySelector('.layout-wrapper'), 'layout-mobile-active');
  const userSettingsMenu = document.getElementById('user-settings-menu');
  syncAriaExpandedWithClass(userSettingsMenu, userSettingsMenu && userSettingsMenu.closest('li'), 'active-topmenuitem');
  focusFirstItemWhenOpened(userSettingsMenu, userSettingsMenu && userSettingsMenu.closest('li'), 'active-topmenuitem', document.getElementById('user-setting-container'));
  fixDynamicContentAccessibility();
  $(document).on('pfAjaxComplete', fixDynamicContentAccessibility);
});

function focusFirstItemWhenOpened(trigger, observedElement, expandedClass, menu) {
  if (!trigger || !observedElement || !menu) {
    return;
  }
  let wasExpanded = observedElement.classList.contains(expandedClass);
  new MutationObserver(() => {
    const isExpanded = observedElement.classList.contains(expandedClass);
    if (isExpanded && !wasExpanded && document.activeElement === trigger) {
      $(menu).find('a[href]').filter(':visible').first().trigger('focus');
    }
    wasExpanded = isExpanded;
  }).observe(observedElement, { attributes: true, attributeFilter: ['class'] });
}

function syncAriaExpandedWithClass(trigger, observedElement, expandedClass) {
  if (!trigger || !observedElement) {
    return;
  }
  const sync = () => trigger.setAttribute('aria-expanded', String(observedElement.classList.contains(expandedClass)));
  new MutationObserver(sync).observe(observedElement, { attributes: true, attributeFilter: ['class'] });
  sync();
}

function fixSelectOneButtonAccessibility() {
  $('.ui-selectonebutton').each((index, group) => {
    $(group).attr('role', 'radiogroup');
    $(group).find('input[type="radio"]').attr('inert', '');
    let label = group.id ? document.querySelector(`label[for="${CSS.escape(group.id)}"]`) : null;
    if (label && !$(group).attr('aria-label')) {
      $(group).attr('aria-label', label.textContent.trim());
    }
  });
}

function applyAccessibleNameToInputs() {
  $('[data-accessible-name]').each((index, element) => {
    $(element).find('input').first().attr('aria-label', $(element).attr('data-accessible-name'));
  });
}

function labelInplaceEditorButtons() {
  $('.ui-inplace-save, .ui-inplace-cancel').each((index, button) => {
    let label = $(button).attr('title');
    if (label && $(button).attr('aria-label') !== label) {
      $(button).attr('aria-label', label);
    }
  });
}

function fixDynamicContentAccessibility() {
  labelInplaceEditorButtons();
  applyAccessibleNameToInputs();
  fixSelectOneButtonAccessibility();
}

/**
 * Focuses the first visible element matching the selector in a PrimeFaces overlay panel.
 * @param {string} widgetVar - The widgetVar of the PrimeFaces overlay panel.
 * @param {string} selector - The jQuery selector for the element(s) to focus.
 */
function focusFirstVisibleElementInPanel(widgetVar, selector) {
  var widget = widgetVar ? PF(widgetVar) : null;
  if (!widget) {
    return;
  }
  var panel = widget.jq;
 
  var first;
  var destructionWords = ['remove', 'destroy', 'delete', 'confirmation', 'confirm', 'deletion', 'reset'];
  
  if (destructionWords.some(word => widgetVar.includes(word))) {
    first = panel.find('a').first();
  } else {
  	first = panel.find(selector).first();
  }
  
  if (first.length) {
    first.focus();
  }
}

function hideVisibleTooltips() {
  if (!PrimeFaces.widget.Tooltip) {
    return;
  }
  for (var widgetVar in PrimeFaces.widgets) {
    var widget = PrimeFaces.widgets[widgetVar];
    if (widget instanceof PrimeFaces.widget.Tooltip && widget.jq && widget.jq.is(':visible')) {
      widget.hide();
    }
  }
}

function isEscapeHandledByDialog() {
  for (var widgetVar in PrimeFaces.widgets) {
    var widget = PrimeFaces.widgets[widgetVar];
    if (widget && widget.cfg && widget.cfg.closeOnEscape && widget.jq && widget.jq.hasClass('ui-dialog') && widget.jq.is(':visible')) {
      return true;
    }
  }
  return false;
}

function isOpenOverlayMenu(widget) {
  var widgets = PrimeFaces.widget;
  if (widgets.MenuButton && widget instanceof widgets.MenuButton) {
    return widget.menu && widget.menu.is(':visible');
  }
  if (widgets.PlainMenu && widget instanceof widgets.PlainMenu) {
    return widget.cfg.overlay && widget.trigger && widget.jq.is(':visible');
  }
  return false;
}

function hideOpenOverlayMenus() {
  var hidden = false;
  for (var widgetVar in PrimeFaces.widgets) {
    var widget = PrimeFaces.widgets[widgetVar];
    if (widget && isOpenOverlayMenu(widget)) {
      widget.hide();
      let button = widget.trigger[0];
      setTimeout(function () {
        button.focus();
      }, 0);
      hidden = true;
    }
  }
  return hidden;
}

function updateMainMenuAriaLabel() {
  let parentMenu = $("[id$='user-menu-required-login:main-navigator:main-menu']");
  if (parentMenu) {
    if (parentMenu.attr('role') === undefined) {
      parentMenu.attr('role', 'menu');
    }
    parentMenu.find('li').each((__, item) => {
      let linkItem = $(item).find('a');
      if (linkItem && linkItem.attr('aria-label') === undefined) {
        if (linkItem.length > 1) {
          $(linkItem).each((__, link) => {
            if ($(link).attr('aria-label') === undefined) { 
              $(link).attr('aria-label', $(link).text());
            }
          })
        } else {
          linkItem.attr('aria-label', linkItem.text());
        }
      }
    })
  }
}

function focusElementWithId(elementId) {
    var element = document.querySelector('[id$="' + elementId + '"]');
    if (element) { element.focus(); }
}

function addMissingAttr(query, attrName, attrValue) {
  $(query).each((index, btn) => {
    if ($(btn).attr(attrName) === undefined) {
      $(btn).attr(attrName, attrValue);
    }
  });
}

function isTopMostPanel(panel, targetWindow) {
  var maxZIndex = -1;
  var topPanel = null;
  var widgets = targetWindow.PrimeFaces.widgets;
  for (var key in widgets) {
    var w = widgets[key];
    if (w instanceof targetWindow.PrimeFaces.widget.OverlayPanel && w.isVisible()) {
      var zIndex = parseInt(w.jq.css('z-index'), 10) || 0;
      if (zIndex > maxZIndex) {
        maxZIndex = zIndex;
        topPanel = w;
      }
    }
  }
  return topPanel === panel;
}

function initFocusManagament(targetWindow) {
  if (!targetWindow || !targetWindow.PrimeFaces) {
    return;
  }
  var lastFocusedElements = [];

  // OverlayPanel - only extend once per window
  if (targetWindow.PrimeFaces.widget.OverlayPanel && !targetWindow.PrimeFaces.widget.OverlayPanel._focusManaged) {
    targetWindow.PrimeFaces.widget.OverlayPanel = targetWindow.PrimeFaces.widget.OverlayPanel.extend({
        init: function(cfg) {
          this._super(cfg);

          this.originalOnHide = cfg.onHide;
          this.originalOnShow = cfg.onShow;
          var self = this;

          cfg.onShow = function() {
            if (self.originalOnShow) {
              self.originalOnShow.call(this);
            }

            try {
              if (this.targetElement && this.targetElement.length > 0) {
                let targetElement = this.targetElement[0];
                storeFocusedElement(targetWindow.document, lastFocusedElements, this.cfg.id, targetElement);
              }
            } catch(e) {
              console.warn("Cannot store focused element");
            }

            moveFocusIntoOverlayPanel(self, targetWindow);

            if (self.escHandler) {
              targetWindow.document.removeEventListener('keydown', self.escHandler);
            }
            var panel = self;
            self.escHandler = function(e) {
              if (e.key === 'Escape' && panel.isVisible() && isTopMostPanel(panel, targetWindow)) {
                panel.hide();
                e.preventDefault();
              }
            };
            targetWindow.document.addEventListener('keydown', self.escHandler);
          };

          cfg.onHide = function() {            
              if (self.originalOnHide) {
                  self.originalOnHide.call(this);
              }
              try {
                restoreFocusedElement(targetWindow.document, lastFocusedElements, this.cfg.id);
              } catch (e) {
                console.warn("Cannot focus on last element");
              }
              if (self.escHandler) {
                targetWindow.document.removeEventListener('keydown', self.escHandler);
                self.escHandler = null;
              }
          };
      }
    })
    targetWindow.PrimeFaces.widget.OverlayPanel._focusManaged = true;
  }

}

function moveFocusIntoOverlayPanel(panel, targetWindow) {
  var targetElement = panel.targetElement && panel.targetElement[0];
  if (!targetElement || $(targetElement).is('input, textarea, [contenteditable="true"]')) {
    return;
  }
  var activeElement = targetWindow.document.activeElement;
  if (activeElement !== targetElement && activeElement !== targetWindow.document.body) {
    return;
  }
  setTimeout(function () {
    if (panel.jq[0].contains(targetWindow.document.activeElement)) {
      return;
    }
    var first = panel.jq.find('a[href], button:not([disabled]), input:not([disabled]):not([type="hidden"]), select, textarea, [tabindex]:not([tabindex="-1"])')
      .filter(':visible').first();
    if (first.length) {
      first.trigger('focus');
    }
  }, 50);
}

function storeFocusedElement(targetDocument, focusElements, containerId, targetElement) {
  if (targetElement && targetElement !== targetDocument.body && targetElement.tagName !== 'HTML') {
    var item = {"containerId": containerId, "activeElement": targetElement};

    if (focusElements.length === 0 || 
        focusElements[focusElements.length - 1].containerId !== containerId ||
        focusElements[focusElements.length - 1].activeElement !== targetElement) {
      focusElements.push(item);
    }
  }
}

function restoreFocusedElement(targetDocument, focusElements, containerId) {
  if (focusElements.length === 0) return;

  const itemIndex = focusElements.findIndex(item => item.containerId === containerId);

  if (itemIndex === -1) {
    return;
  }

  const item = focusElements[itemIndex];
  const lastEl = targetDocument.getElementById(item.activeElement.id);
  focusElements.splice(itemIndex, 1);

  if (lastEl && targetDocument.contains(lastEl) && lastEl.offsetParent !== null && !lastEl.disabled && lastEl.tabIndex !== -1) {
    lastEl.focus();
  }
}

function initIframeFocusManagement(iframe) {
  if (!iframe || !iframe.contentWindow) {
    return;
  }
  
  try {
    var iframeWindow = iframe.contentWindow;
    initFocusManagament(iframeWindow);
    console.log('Focus management initialized for iframe');
  } catch (e) {
    console.warn('Cannot initialize focus management for iframe:', e.message);
  }
}

function bindDialogKeysInIframe(iframe, widgetVar) {
  try {
    var iframeWindow = iframe.contentWindow;
    iframeWindow.document.addEventListener('keydown', function (event) {
      if (event.defaultPrevented || iframeWindow.$('.ui-dialog:visible').length) {
        return;
      }
      if (event.key === 'Escape') {
        PF(widgetVar).hide();
      } else if (event.key === 'Tab' && !event.shiftKey && iframeWindow.$.expr.pseudos.tabbable
          && event.target === iframeWindow.$(':tabbable').last()[0]) {
        var first = PF(widgetVar).jq.find(':tabbable').first();
        if (first.length) {
          event.preventDefault();
          first.trigger('focus');
        }
      }
    });
    PF(widgetVar).jq.off('keydown.iframeTab').on('keydown.iframeTab', function (event) {
      if (event.key !== 'Tab' || !iframeWindow.$ || !iframeWindow.$.expr.pseudos.tabbable) {
        return;
      }
      var dialogItems = $(this).find(':tabbable');
      var isLeavingDialogItems = event.shiftKey ? event.target === dialogItems.first()[0] : event.target === dialogItems.last()[0];
      var iframeItems = iframeWindow.$(':tabbable');
      var next = event.shiftKey ? iframeItems.last() : iframeItems.first();
      if (isLeavingDialogItems && next.length) {
        event.preventDefault();
        event.stopPropagation();
        next.trigger('focus');
      }
    });
  } catch (e) {
    console.warn('Cannot handle dialog keys in iframe:', e.message);
  }
}

function handleFocusOnElementsInCaseDetailsPanel() {
  const caseInfoDialog = document.getElementById('case-info-dialog');
  if (caseInfoDialog.innerHTML.includes('i-frame-case-details')) {
    const iframe = document.getElementById('i-frame-case-details');
    setTimeout(() => {
      initIframeFocusManagement(iframe);
    }, 2000)
  }
}

// END: FIX ACCESSIBILITY ISSUES  
