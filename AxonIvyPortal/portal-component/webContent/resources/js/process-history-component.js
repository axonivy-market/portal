var Portal = {
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

function CaseWidget() {
  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }

  this.setUpScrollbar = function() {
    var childElements = $('.js-case-item');
    if (childElements.length > 0) {
      var container = $('.js-case-list > .ui-datascroller-content');
      var caseWidgetHeaderContainer = $('.widget-header');
      var announcementMessageContainer = $('.js-announcement-message');
      var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
      var caseWidgetFilterContainer = $('.js-filter-container');
      var error = 5;
      var globalSearchInput = $('.js-global-search');
      var globalSearchTabHeader = $('.ui-tabs-nav');
      if (globalSearchTabHeader.length > 0) {
        error = 55; // included margin, padding in search page
      }
      var headerHeight = $('#portal-template-header').outerHeight();
      var footerHeight = $('#portal-template-footer').outerHeight();

      var mainScreenHeight = ($('body').outerHeight(true)||0) - ($('.layout-topbar').outerHeight(true)||0);
      var availableHeight = mainScreenHeight - (caseWidgetHeaderContainer.outerHeight(true)||0)
          - (caseWidgetSortMenuContainer.outerHeight(true)||0) - (caseWidgetFilterContainer.outerHeight(true)||0)
          - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0) - (globalSearchTabHeader.outerHeight(true)||0)
          - (announcementMessageContainer.outerHeight(true)||0) - error
          - headerHeight - footerHeight;

      if (!!availableHeight) {
        container.height(availableHeight);
      }
    }
  };
}

function CaseListToolKit() {
  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("ui-hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("ui-hidden");
  }
  
  return {
    setupHeader : function() {
      var caseSortMenu = $('.js-case-widget-column-header');
      var caseEntry = $('.js-case-start-link').first();
      var noEntry = caseEntry.length == 0;
      this.showHideColumnWhenMenuToggle();
      if (noEntry) {
        $(caseSortMenu).hide();
      } else {
        $(caseSortMenu).show();
      }
      
      $.each(caseSortMenu.children('form'), function(i, header) {
        var cell = $(caseEntry).children().get(i);
        $(header).outerWidth($(cell).outerWidth());
      });
     
    },
    
    setupScrollbar : function() {
      var caseWidget = new CaseWidget();
      caseWidget.setUpScrollbar();
    },
    
    showHideColumnWhenMenuToggle: function() {
      var $layout = $('.js-layout-wrapper');
      var $mainMenu = $('.js-left-sidebar');
      var remainingWidth = $('body').width() - $mainMenu.outerWidth() - 75;//exclude padding and scroll bar
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $layout.hasClass('layout-wrapper-static')) {
        hideColumnWhenExpandMenu($hiddenColumns);
      } else {
        displayColumnWhenCollapseMenu($hiddenColumns);
      }
    },
    
    responsive : function() {
        this.setupScrollbar();
        this.setupHeader();
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

// Define CaseWidget for sorting event callback
var caseWidget = new CaseWidget();

/* For opening ProcessHistory component in a dialog
    - Check the case list data then hide the header if data empty
    - Setup table header by call CaseWidget.setupHeader()
*/
var processHistory = {
  setup : function(emptyRow) {
    this.hideListIfEmptyCase(emptyRow);
    // Setup case widget header
    caseWidget.setupHeader();
  },

  hideListIfEmptyCase : function(emptyRow) {
    if(emptyRow) {
      $header = $('.js-case-widget-column-header');
      $list = $('.process-history-list');
      $header.hide();
      $list.hide();
    }
  },

  // For binding event of toggle menu
  updateLayoutWithAnimation : function() {
    setupCaseHeader();
  }
}

Portal.updateLayoutContent();
setupCaseHeader();

var resizeTimer;
// Update screen when window size is changed
$(window).resize(function() {
  Portal.updateLayoutContent();

  clearTimeout(resizeTimer);
  resizeTimer = setupCaseHeader();
});

function setupCaseHeader() {
  // Wait for Main layout is updated, then run setup header
  setTimeout(function() {
    caseWidget.setupHeader();
  }, 250);
}

// Add a ScreenHandler listener to Portal Menu event
MainMenu.init(processHistory);
