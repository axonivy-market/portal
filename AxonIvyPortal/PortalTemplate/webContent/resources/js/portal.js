var mainMenuMode = "collapsed";
var FIRST_LEVEL_MENU_MODE = 'portal-first-level-menu-mode';
var storageType = detectStorage();

var Portal = {
  init : function(responsiveToolkit) {
    if ($('form.login-form').size() > 0) {
      return;
    }
    updateMainMenuMode();
    if (mainMenuMode === "expanded") {
      $('.js-left-sidebar').addClass('in');
    }

    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    
    // Update screen when window size is changed
    $(window).resize(function() {
      setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
    });

    responsiveToolkit.updateLayoutWithoutAnimation();
    this.updateLayoutContent();
  },
  
  // Remove u-invisibility class when DOM is pasted already
  updateLayoutContent : function() {
    $('#main-area-panel').removeClass('u-invisibility');
    $("[id$='main-navigator']").removeClass('u-invisibility');
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
  urlToMenu : [["Processes.xhtml", ["Processes", "Prozesse", "Processus", "Procesos"]],
      ["PortalTasks.xhtml", ["Tasks", "Aufgaben", "Tâches", "Tareas"]],
      ["PortalCases.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["PortalDashBoard.xhtml", ["Statistics", "Statistiken", "Statistiques", "Estadísticas"]],
      ["TaskAnalysis.xhtml", ["Statistics", "Statistiken", "Statistiques", "Estadísticas"]],
      ["TaskWidget.xhtml", ["Tasks", "Aufgaben", "Tâches", "Tareas"]],
      ["CaseWidget.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["PortalCaseDetails.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["CaseItemDetails.xhtml", ["Cases", "Vorgänge", "Dossiers", "Casos"]],
      ["PortalTaskDetails.xhtml", ["Tasks", "Aufgaben", "Tâches", "Tareas"]]],

  init : function(responsiveToolkit) {
    this.highlightMenuItem();
    this.responsiveToolkit = responsiveToolkit;
    this.$mainMenu = $('.js-left-sidebar');
    this.$mainMenuToggle = $('.sidebar-anchor');
    this.bindEvents();
  },
  
  showMainMenu : function() {
    this.$mainMenu.toggleClass('in');
  },
  
  bindEvents : function() {
    var $this = this;
    this.$mainMenuToggle.on('click', function(e) {
      $this.showMainMenu();
      // Toggle menu state
      toggleMainMenuMode();
      $this.responsiveToolkit.updateLayoutWithAnimation();
    });
  },

  highlightMenuItem : function() {
    var firstLevelMenu = MainMenu.getMenuBasedOnPageUrl();
    var parentActiveMenuId = MainMenu.getFirstParentMenuActive();

    var $activeMenuItem = $(".layout-menu li[id^='" + parentActiveMenuId + "'] .menuitem-text").filter(
        function(index) {
          if (firstLevelMenu) {
            return (firstLevelMenu.indexOf($(this).text()) > -1);
          }
        });
    
    $activeMenuItem.parent().parent().addClass('active-menuitem');
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

    if (subMenuActive.length > 0) {
      PF('main-menu').clearActiveMenuState();
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

function toggleMainMenuMode() {
  updateMainMenuMode();
  storeItemToStorage(FIRST_LEVEL_MENU_MODE, getReversedState(mainMenuMode));
}

function getReversedState(state) {
  if (state == "expanded") {
    return "collapsed";
  } else {
    return "expanded";
  }
}

function updateMainMenuMode() {
  mainMenuMode = getItemFromStorage(FIRST_LEVEL_MENU_MODE);
}

function detectStorage(){
  if (typeof(Storage) !== "undefined") {
    return lsTest();
  } else {
    return '';
  }
}

function lsTest(){
  var test = 'test';
  try {
      localStorage.setItem(test, test);
      localStorage.removeItem(test);
      return 'localStorage';
  } catch(e) {
      return ssTest();
  }
}

function ssTest(){
  var test = 'test';
  try {
    sessionStorage.setItem(test, test);
    sessionStorage.removeItem(test);
    return 'sessionStorage';
  } catch(e) {
    return '';
  }
}

function storeItemToStorage(item, value){
  if (storageType === 'localStorage'){
    localStorage.setItem(item, value);
  } else if (storageType === 'sessionStorage'){
    sessionStorage.setItem(item, value);
  } 
}

function getItemFromStorage(item){
  if (storageType === 'localStorage'){
    return localStorage.getItem(item);
  } else if (storageType === 'sessionStorage'){
    return sessionStorage.getItem(item);
  }
  return null;
}