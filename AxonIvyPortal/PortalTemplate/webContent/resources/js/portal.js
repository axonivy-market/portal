var mainMenuMode = "collapsed";
var FIRST_LEVEL_MENU_MODE = 'portal-first-level-menu-mode';
var SECOND_LEVEL_MENU_MODE = 'portal-second-level-menu-mode';
var ACTIVE_MENU_ITEM = 'portal-active-menu-item';

var Portal = {
  init : function(responsiveToolkit, updateFlag) {
    typeof(updateFlag) == "undefinied" ? false : updateFlag;
    if ($('form.login-form').size() > 0) {
      return;
    }

    if (mainMenuMode === "expanded") {
      $('.js-left-sidebar').addClass('in').toggleClass('left-sidebar-animation');
      $('.js-left-sidebar-toggle').addClass('in').toggleClass('left-sidebar-animation');
      $('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
      $('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
      $('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
      $("div[class*='left-sidebar-menu-item-tooltip']").toggleClass('u-invisibility');
      setTimeout(function() {
        $('.js-left-sidebar').toggleClass('left-sidebar-animation');
        $('.js-left-sidebar-toggle').toggleClass('left-sidebar-animation');
        $('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
        $('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
        $('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
        ;
      }, 1);
    }

    setTimeout(function() {
      $('.left-sidebar-menu-header').toggleClass('left-sidebar-animation');
    }, 3);

    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    
    // Update screen when window size is changed
    $(window).resize(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    });

    responsiveToolkit.updateLayoutWithoutAnimation();
    $('#main-area-panel').removeClass('u-invisibility');
    $('#left-menu').removeClass('u-invisibility');
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
  urlToFirstLevelMenu : [["Processes.xhtml", ["Processes", "Prozesse", "Processus", "Procesos"]],
      ["PortalTasks.xhtml", ["Tasks", "Aufgaben", "Tâches", "Tareas"]],
      ["PortalCases.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["PortalDashBoard.xhtml", ["Statistics", "Statistiken", "Statistiques", "Estadísticas"]],
      ["TaskAnalysis.xhtml", ["Statistics", "Statistiken", "Statistiques", "Estadísticas"]],
      ["TaskWidget.xhtml", ["Tasks", "Aufgaben", "Tâches", "Tareas"]],
      ["CaseWidget.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["PortalCaseDetails.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["CaseItemDetails.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]]],

  init : function(responsiveToolkit) {
    this.highlightFirstLevelMenu();
  },

  highlightFirstLevelMenu : function() {
    var firstLevelMenu = MainMenu.getFirstLevelMenuBasedOnPageUrl();
    var parentActiveMenuId = MainMenu.getFirstParentMenuActive();

    var $activeFirstLevelMenu = $(".layout-menu li[id^='" + parentActiveMenuId + "'] .menuitem-text").filter(
        function(index) {
          if (firstLevelMenu) {
            return (firstLevelMenu.indexOf($(this).text()) > -1);
          }
        });
    
    $activeFirstLevelMenu.parent().parent().addClass('active-menuitem');
  },

  getFirstParentMenuActive : function() {
    var parentId = "";
    var parentMenuActive = $(".layout-menu .active-menuitem").not(".submenu-container");
    if (parentMenuActive) {
      parentId = parentMenuActive.get(0).id;
      MainMenu.getFirstSubMenuActive();
    }
    return parentId;
  },

  getFirstSubMenuActive : function() {
    var subMenuActive = $(".layout-menu .active-menuitem.submenu-container");

    for (var i = 0; i < subMenuActive.length; i++) {
      var item = subMenuActive.get(i);
      $(item).removeClass('active-menuitem');
    }

    if (subMenuActive.length > 0) {
      PF('main-menu').clearActiveMenuState();
    }
  },

  getFirstLevelMenuBasedOnPageUrl : function() {
    var pageUrl = window.location.pathname;
    for (var i = 0; i < MainMenu.urlToFirstLevelMenu.length; i++) {
      if (pageUrl.indexOf(MainMenu.urlToFirstLevelMenu[i][0]) > -1) {
        return MainMenu.urlToFirstLevelMenu[i][1];
      }
    }
  }

}
