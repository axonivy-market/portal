var Portal = {
  init : function(responsiveToolkit) {
    if ($('form.login-form').length > 0) {
      return;
    }
    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    this.updateBreadcrumb();
    
    // Update screen when window size is changed
    $(window).resize(function() {
      Portal.updateLayoutContent();
      setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
      Portal.updateGuide();
    });
    
    //Add very small timeout when page ready, fix responsive problem for IE 11
    setTimeout(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    }, 1);
    
    this.updateLayoutContent();
    this.updateBreadcrumb();
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
    var headerHeight = $('#portal-template-header').outerHeight();
    var footerHeight = $('#portal-template-footer').outerHeight();
    var headerFooterHeight = headerHeight + footerHeight;
    var layoutTopbarHeight = $('.layout-topbar').outerHeight();
    $('.js-position-topbar').height(layoutTopbarHeight);
    if ($('.js-layout-wrapper').hasClass('u-invisibility')) {
      var envHeight = $('#portal-environment').outerHeight();
      var announcementMessageContainer = $('.js-announcement-message');
    }

    $('.js-left-sidebar').css('top', headerHeight + 'px').css('height', 'calc(100vh - ' + (headerFooterHeight - envHeight) + 'px)');
    $('.js-layout-main').css('margin-top', headerHeight + 'px').css('height', 'calc(100vh - ' + headerFooterHeight + 'px)');
    $('.js-layout-wrapper').removeClass('u-invisibility');

    var topbarWithHeaderFooterHeight = layoutTopbarHeight + headerFooterHeight;
    $('.js-layout-content').css('height', 'calc(100vh - ' + topbarWithHeaderFooterHeight + 'px)');
    var chatPanel = $('.js-chat-panel');
    if (chatPanel.length == 1) {
      chatPanel.css('height', 'calc(100% - ' + (headerFooterHeight - envHeight) + 'px)');
      chatPanel.css('top', headerHeight + 'px');
      chatPanel.css('bottom', footerHeight + 'px');
    }
  },

  updateBreadcrumb : function() {
    var topMenuElements = $("#top-menu").find("> li");
    var breadCrumb = $("#top-menu").find("> li.breadcrumb-container");
    var breadCrumbMembers = breadCrumb.find("li");
    var taskName = $('.js-task-name-details');

    if (breadCrumbMembers.length == 0) {
      return;
    }

    setTimeout(function() {
        var usedWidthOfTopMenu = 0;
        topMenuElements.each(function(i, val) {
          if (!val.classList.contains("breadcrumb-container")) {
            usedWidthOfTopMenu += val.offsetWidth + 22;
          }
        });

        usedWidthOfTopMenu = usedWidthOfTopMenu + 2;
        var breadCrumbWidth = "calc(100% - " + usedWidthOfTopMenu + "px)";
        breadCrumb.css({"display": "block", "width" : breadCrumbWidth});
        if (taskName.length > 0) {
          taskName.css({"display": "none"});
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
          if (taskName.length > 0) {
              taskName.css({"display": "flex"});
            }
        }
      }, 1);
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
    var parentActiveMenuId = MainMenu.getFirstParentMenuActive();

    var $activeMenuItem = $(".layout-menu li[id^='" + parentActiveMenuId + "'] .submenu").filter(
        function(index) {
          if (firstLevelMenu) {
            return $(this).hasClass(firstLevelMenu);
          }
        });
    $activeMenuItem.parent().addClass('active-menuitem');
  },

  getFirstParentMenuActive : function() {
    var parentId = "";
    var parentMenuActive = $(".layout-menu .active-menuitem").not(".submenu-container");
    if (parentMenuActive.length > 0) {
      parentId = parentMenuActive[0].id;
      MainMenu.getSubMenuActive();
    }
    return parentId;
  },

  getSubMenuActive : function() {
    var subMenuActive = $(".layout-menu .active-menuitem.submenu-container");

    for (var i = 0; i < subMenuActive.length; i++) {
      var item = subMenuActive.get(i);
      $(item).removeClass('active-menuitem');
    }

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
