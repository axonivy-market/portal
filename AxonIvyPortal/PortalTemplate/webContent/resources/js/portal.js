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
      ["TaskAnalysis.xhtml", "STATISTICS"]],

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

    this.menulinks.on('click', function(e) {
      // If click on Thirdparty menu -> remove active class of itself
      if (MainMenu.isThirdPartyMenu(e)) {
        MainMenu.highlightMenuItem();
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
    // Remove active class for thirdparty menu
    this.removeActiveOnExternalMenu();
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

  removeActiveOnExternalMenu : function() {
    // Thirdparty
    let thirdPartyActiveMenus = $(".layout-menu li.active-menuitem[class*='thirdparty-menu-item']");
    this.removeActiveMenu(thirdPartyActiveMenus);

    // External
    let exteralActiveMenus = $(".layout-menu li.active-menuitem[class*='external-menu-item-']");
    this.removeActiveMenu(exteralActiveMenus);
  },

  isThirdPartyMenu : function(e) {
    let menuClass = e.currentTarget.className;
    if (menuClass && (menuClass.indexOf('THIRD_PARTY') !== -1 || menuClass.indexOf('EXTERNAL_LINK') !== -1)) {
      return true;
    }
    return false;
  }
}

function handleError(xhr, renderDetail){
  //From PF 7.0 with new jQuery version, when we call ajax by remote command then navigate when remote command still executing, this request HTML status is abort
  //This make general exception dialog display frequently
  if (xhr.statusText === 'abort') {
    return;
  }
  if (renderDetail){
    document.getElementById('ajax-indicator:show-more').onclick = function (){
      document.getElementById('ajax-indicator:error-code').innerHTML = xhr.status;
      document.getElementById('ajax-indicator:error-text').innerHTML = xhr.statusText;
      document.getElementById('ajax-indicator:error-url').innerHTML = xhr.pfSettings.url;
      document.getElementById('ajax-indicator:error-ready-state').innerHTML = xhr.readyState;
      document.getElementById('ajax-indicator:error-type').innerHTML = xhr.pfSettings.type;
      document.getElementById('ajax-indicator:error-args').innerHTML = JSON.stringify(xhr.pfArgs);
      document.getElementById('ajax-indicator:pfSettings-source').innerHTML = xhr.pfSettings.source.id;
      document.getElementById('ajax-indicator:form-data').innerHTML = decodeURIComponent(xhr.pfSettings.data);
      document.getElementById('ajax-indicator:response-text').innerHTML = xhr.responseText;
      document.getElementById('ajax-indicator:xhr').innerHTML = JSON.stringify(xhr);
      PF('detail-error-dialog').show();
    }
  }
  PF('error-ajax-dialog').show();
}
