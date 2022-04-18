var Portal = {
  init : function(responsiveToolkit) {
    if ($('form.login-form').length > 0) {
      return;
    }

    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    
    // Update screen when window size is changed
    $(window).resize(function() {
      setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
    });
    
    //Add very small timeout when page ready, fix responsive problem for IE 11
    setTimeout(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    }, 1);
    
    this.updateLayoutContent();
  },
  
  // Remove u-invisibility class when DOM is pasted already
  updateLayoutContent : function() {
    var ua = window.navigator.userAgent;
    var isIE = /MSIE|Trident/.test(ua);
    var fullHeight= '100vh';
    if (!isIE ) {
      var vh = window.innerHeight * 0.01;
      document.documentElement.style.setProperty('--vh', vh + 'px');
      fullHeight = 'var(--vh, 1vh) * 100';
    }

    var headerHeight = $('#portal-template-header').outerHeight();
    var footerHeight = $('#portal-template-footer').outerHeight();
    var envHeight = $('#portal-environment').outerHeight();
    var layoutTopbarHeight = $('.layout-topbar').outerHeight();
    var headerFooterHeight = headerHeight + footerHeight;
    $('.js-position-topbar').height(layoutTopbarHeight); 
    $('.js-left-sidebar').css('top', headerHeight + 'px').css('height', 'calc(100% - ' + (headerFooterHeight - envHeight) + 'px)');
    $('.js-layout-main').css('margin-top', headerHeight + 'px').css('height', 'calc(100% - ' + headerFooterHeight + 'px)');
    $('.js-layout-content').css('height', 'calc(' + fullHeight + ' - ' + (headerFooterHeight + layoutTopbarHeight) + 'px)');
    $('.js-layout-content').css('padding-top', '0px');
    
    var chatPanel = $('.js-chat-panel');
    if (chatPanel.length == 1) {
      chatPanel.css('height', 'calc(100% - ' + (headerFooterHeight - envHeight) + 'px)');
      chatPanel.css('top', headerHeight + 'px');
      chatPanel.css('bottom', footerHeight + 'px');
    }

    $('.js-layout-wrapper').removeClass('u-invisibility');
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
  urlToMenu : [["Processes.xhtml", "PROCESS"],
      ["PortalTasks.xhtml", "TASK"],
      ["TaskWidget.xhtml", "TASK"],
      ["PortalTaskDetails.xhtml", "TASK"],
      ["PortalCases.xhtml", "CASE"],
      ["CaseWidget.xhtml", "CASE"],
      ["PortalCaseDetails.xhtml", "CASE"],
      ["CaseItemDetails.xhtml", "CASE"],
      ["PortalDashBoard.xhtml", "DASHBOARD"],
      ["TaskAnalysis.xhtml", "DASHBOARD"]],

  init : function(responsiveToolkit) {
    this.highlightMenuItem();
    this.responsiveToolkit = responsiveToolkit;
    this.$mainMenuToggle = $('.sidebar-anchor');
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
    var firstLevelMenu = MainMenu.getMenuBasedOnPageUrl();
    if (firstLevelMenu) {
      var parentActiveMenuId = MainMenu.getFirstParentMenuActive();

      var $activeMenuItem = $(".layout-menu li[id^='" + parentActiveMenuId + "'] .submenu").filter(
          function(index) {
            if (firstLevelMenu) {
              return $(this).hasClass(firstLevelMenu);
            }
          });
      $activeMenuItem.parent().addClass('active-menuitem');
      PF('main-menu').addMenuitem($activeMenuItem.parent().attr('id'));
    }
    else {
      MainMenu.removeActivatedSubMenu(true);
    }
  },

  getFirstParentMenuActive : function() {
    var parentId = "";
    var parentMenuActive = $(".layout-menu .active-menuitem").not(".submenu-container");
    if (parentMenuActive.length > 0) {
      let lastSubMeuItem = MainMenu.removeActivatedSubMenu();
      for (var i = 0; i < parentMenuActive.length; i++) {
        if (lastSubMeuItem.indexOf(parentMenuActive[i].id) >= 0) {
          parentId = parentMenuActive[i].id;
          break;
        }
      }
    }
    return parentId;
  },

  removeActivatedSubMenu : function(keepInCookie) {
    let lastSubMeuItem = "";
    var subMenuActive = $(".layout-menu .active-menuitem.submenu-container");

    for (var i = 0; i < subMenuActive.length; i++) {
      var item = subMenuActive.get(i);
      $(item).removeClass('active-menuitem');
      if (!keepInCookie) {
        PF('main-menu').removeMenuitem(item.id);
      }
      lastSubMeuItem = item.id;
    }
    return lastSubMeuItem;
  },

  getMenuBasedOnPageUrl : function() {
    var pageUrl = window.location.pathname;
    for (var i = 0; i < MainMenu.urlToMenu.length; i++) {
      if (pageUrl.indexOf(MainMenu.urlToMenu[i][0]) > -1) {
        return MainMenu.urlToMenu[i][1];
      }
    }
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
  MainMenu.removeActivatedSubMenu();
  var subMenuActive = [];
  try {
    let serenityExpandedItems = $.cookie('serenity_expandeditems');
    if (serenityExpandedItems) {
      subMenuActive = serenityExpandedItems.split(',');
    }
  } catch (e) {
    console.log('Cannot identify status of Portal menu');
  }
  for (var i = 0; i < subMenuActive.length; i++) {
    PF('main-menu').removeMenuitem(subMenuActive[i]);
  }
}