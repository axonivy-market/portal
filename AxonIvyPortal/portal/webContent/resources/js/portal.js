var Portal = {
  init : function(responsiveToolkit) {
    // Swipe on mobile can cause problems with scroll
    PrimeFaces.widget.Paginator.prototype.bindSwipeEvents = function() {}

    if ($('form.login-form').length > 0) {
      return;
    }
    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    
    //Add very small timeout when page ready, fix responsive problem for IE 11
    setTimeout(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    }, 1);
    
    this.updateLayoutContent();
    this.updateBreadcrumb();

    var resizeTimer;
    // Update screen when window size is changed
    $(window).resize(function() {
      Portal.updateLayoutContent();

      clearTimeout(resizeTimer);
      resizeTimer = setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
      Portal.updateGuide();
    });
  },
  
  updateGuide : function() {
    var $guidePanel = $('.guide-panel:visible');
    if ($guidePanel.length > 0) {
      var id = $guidePanel.attr('id');
      if (id !== undefined) {
        var guidePanelObject = window[id.substring(id.lastIndexOf(':') + 1)];
        if (guidePanelObject !== undefined) {
          guidePanelObject.show();
        }
      }
    }
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
      }, 300);
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
            if (!(menuItem.id.includes('-sub-dashboard') || menuItem.id.includes('-main-dashboard'))) {
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