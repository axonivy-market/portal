var Portal = {
  init : function(responsiveToolkit) {
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
    var ua = window.navigator.userAgent;
    var isIE = /MSIE|Trident/.test(ua);
    var fullHeight= '100vh';
    if (!isIE) {
      var vh = window.innerHeight * 0.01;
      document.documentElement.style.setProperty('--vh', vh + 'px');
      fullHeight = 'var(--vh, 1vh) * 100';
    }

    var headerHeight = $('.js-portal-template-header').outerHeight(true)||0;
    var footerHeight = $('.js-portal-template-footer').outerHeight(true)||0;
    var headerFooterHeight = headerHeight + footerHeight;
    var layoutTopbarHeight = $('.layout-topbar').outerHeight(true)||0;
    $('.js-position-topbar').height(layoutTopbarHeight);
    if ($('.js-layout-wrapper').hasClass('u-invisibility')) {
      var envHeight = $('#portal-environment').outerHeight();
    }

    $('.js-left-sidebar').css('top', headerHeight + 'px').css('height', 'calc(100vh - ' + (headerFooterHeight - envHeight) + 'px)');
    $('.js-layout-main').css('margin-top', headerHeight + 'px').css('height', 'calc(100vh - ' + headerFooterHeight + 'px)');
    $('.js-layout-wrapper').removeClass('u-invisibility');

    var topbarWithHeaderFooterHeight = (layoutTopbarHeight + headerFooterHeight);
    $('.js-layout-content').css('height', 'calc(' + fullHeight + ' - ' + topbarWithHeaderFooterHeight + 'px)');
    var chatPanel = $('.js-chat-panel');
    if (chatPanel.length > 0) {
      chatPanel.css('height', 'calc(100% - ' + (headerFooterHeight - envHeight) + 'px)');
      chatPanel.css('top', headerHeight + 'px');
      chatPanel.css('bottom', footerHeight + 'px');
    }
  },

  updateBreadcrumb : function() {
    var topMenuElements = $("#top-menu").find("> li");
    var breadCrumb = $("#top-menu").find("> li.breadcrumb-container");
    var breadCrumbMembers = breadCrumb.find("li");

    if (breadCrumbMembers.length == 0) {
      return;
    }

    var updateBreadcrumbTimeout;
    clearTimeout(updateBreadcrumbTimeout);

    updateBreadcrumbTimeout = setTimeout(function() {
        var usedWidthOfTopMenu = 0;
        var layoutWrapper = $('.js-layout-wrapper');
        topMenuElements.each(function(i, val) {
          if (!val.classList.contains("breadcrumb-container")) {
            usedWidthOfTopMenu += $(val).outerWidth(true);
          }
        });

        var toggleMenuIcon = $('.left-sidebar-menu-icon');
        if (toggleMenuIcon.is(":visible")) {
          usedWidthOfTopMenu += toggleMenuIcon.outerWidth(true);
        }

        var breadCrumbWidth = "calc(100% - " + usedWidthOfTopMenu + "px)";
        breadCrumb.css({"display": "block", "width" : breadCrumbWidth});
        if(!layoutWrapper.hasClass('has-breadcrumb')) {
          layoutWrapper.addClass('has-breadcrumb');
        }

        var breadcrumbWidthWithoutCurrentStep = 0;
        breadCrumbMembers.each(function(i, val) {
          if (i != breadCrumbMembers.length - 1) {
            breadcrumbWidthWithoutCurrentStep += val.offsetWidth;
          }
        });
        var currentBreadcrumb = $(breadCrumbMembers.get(breadCrumbMembers.length - 1));
        currentBreadcrumb.css("max-width", "calc(100% - " + breadcrumbWidthWithoutCurrentStep + "px)");
        if (currentBreadcrumb.get(0).offsetWidth == 0) {
          breadCrumb.css("display", "none");
          layoutWrapper.removeClass('has-breadcrumb');
        }
      }, 100);
  }
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
    this.$mainMenuToggle = $('.sidebar-anchor');
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
      $(menuItem).removeClass('active-menuitem');
      PF('main-menu').removeMenuitem(menuItem.id);
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
    return $(".layout-menu").find("a.ripplelink." + currentPage);
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
  $.removeCookie('serenity_expandeditems', {path: '/'});
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

function showCreateDashboardSection() {
  $("[id$='dashboard-configuration-close-button']").addClass("u-hidden");
  $("[id$='create-dashboard-close-button']").removeClass("u-hidden");
  
  $("[id$='configuration-form']").addClass("u-hidden");
  $("[id$='create-new-dashboard-form']").removeClass("u-hidden");
}

function hideCreateDashboardSection() {
  $("[id$='create-dashboard-close-button']").addClass("u-hidden");
  $("[id$='dashboard-configuration-close-button']").removeClass("u-hidden");
  
  $("[id$='create-new-dashboard-form']").addClass("u-hidden");
  $("[id$='configuration-form']").removeClass("u-hidden");
}