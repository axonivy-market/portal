 "use strict";
var animateDuration = 0;
function ResponsiveToolkit(largeScreenToolkit, mediumScreenToolkit, smallScreenToolkit) {
  var VALID_METHOD = "updateMainContainer";
  
  function validateArguments(){
	  
	  var errorMessageFormat = "{0} have to implement {1} method";
	  
	  if(!largeScreenToolkit.hasOwnProperty(VALID_METHOD)){
		  throw Error(errorMessageFormat.replace("{0}", "largeScreenToolkit").replace("{1}", VALID_METHOD));
	  }
	  
	  if(!mediumScreenToolkit.hasOwnProperty(VALID_METHOD)){
		  throw Error(errorMessageFormat.replace("{0}", "mediumScreenToolkit").replace("{1}", VALID_METHOD));
		  
	  }
	  
	  if(!smallScreenToolkit.hasOwnProperty(VALID_METHOD)){
		  throw Error(errorMessageFormat.replace("{0}", "smallScreenToolkit").replace("{1}", VALID_METHOD));
	  }
  }
	
  function updateLayout() {
	validateArguments();
	if (viewPort.isLargeScreen()) {
		largeScreenToolkit.updateMainContainer();
	}
	    
	if (viewPort.isMediumScreen()) {
	    mediumScreenToolkit.updateMainContainer();
	}
	    
	if (viewPort.isSmallScreen()) {
	    smallScreenToolkit.updateMainContainer();
	}
    
  }
  
  return {
    updateLayoutWithAnimation : function () {
      animateDuration = 800;
      updateLayout();
    },
    
    updateLayoutWithoutAnimation: function(){
    	animateDuration = 0;
    	updateLayout();
    }
  };
};

/***************************Handle responsive for Task list**********************************/
function TaskListLargeScreenHandler() {
    var marginValues = {
      marginValWhenAllMenuClose : 0,
      marginValWhenMainMenuOpen : 210,
      marginValWhenSecondMenuOpen : 280,
      marginValWhenAllMenuOpen : 410,
    }

    
    function animateTaskList($widget, properties) {
      $widget.animate(properties, animateDuration, function() {
        var taskListToolKit = TaskListToolKit();
          taskListToolKit.responsive();
      });
   }
    
    this.updateMainContainer = function(){
      var $mainMenu = $('.js-left-sidebar');
      var $announcement = $('.js-announcement-message');
      var $taskListContainer = $('.js-task-list-container');

      var isSearchResultPage = $taskListContainer.hasClass('js-for-search-result');

      if ($mainMenu.hasClass('in')) {
          animateTaskList($announcement, { marginLeft : marginValues.marginValWhenMainMenuOpen });
          animateTaskList($taskListContainer, { marginLeft : marginValues.marginValWhenMainMenuOpen });  
          if (isSearchResultPage) {
            var simpleLargeScreen = new SimpleLargeScreen();
            simpleLargeScreen.updateMainContainer();
          }
      } else {
          animateTaskList($announcement, { marginLeft : marginValues.marginValWhenAllMenuClose });
          animateTaskList($taskListContainer, { marginLeft : marginValues.marginValWhenAllMenuClose });
          if (isSearchResultPage) {
            var simpleLargeScreen = new SimpleLargeScreen();
            simpleLargeScreen.updateMainContainer();
          }
      }
    }
}

function TaskListMediumScreenHandler() {
     var marginValues = {
        marginValWhenAllMenuClose : 0,
        marginValWhenMainMenuOpen : 190,
        marginValWhenSecondMenuOpen : 260,
        marginValWhenAllMenuOpen : 380,
    }

    function animateTaskList($widget, properties) {
        $widget.animate(properties, animateDuration, function() {
        	var taskListToolKit = TaskListToolKit();
            taskListToolKit.responsiveInMediumScreen();
        });
     }

    this.updateMainContainer = function(){
        var $mainMenu = $('.js-left-sidebar');
        var $announcement = $('.js-announcement-message');
        var $taskListContainer = $('.js-task-list-container');

        var isSearchResultPage = $taskListContainer.hasClass('js-for-search-result');

        if ($mainMenu.hasClass('in')) {
            // Open main menu when second menu is closed
            animateTaskList($announcement, {marginLeft : marginValues.marginValWhenMainMenuOpen});
            animateTaskList($taskListContainer, {marginLeft : marginValues.marginValWhenMainMenuOpen});
            if (isSearchResultPage) {
              var simpleMediumScreen = new SimpleMediumScreen();
              simpleMediumScreen.updateMainContainer();
            }
        } else {
            // Close main menu when second menu is closed
            animateTaskList($announcement, {marginLeft : marginValues.marginValWhenAllMenuClose});
            animateTaskList($taskListContainer, {marginLeft : marginValues.marginValWhenAllMenuClose});
            if (isSearchResultPage) {
              var simpleMediumScreen = new SimpleMediumScreen();
              simpleMediumScreen.updateMainContainer();
            }
        }
    }
};

function TaskListSmallScreenHandler() {
    var marginValues = {
        marginValWhenAllMenuClose : 0,
        marginValWhenMainMenuOpen : 190,
        marginValWhenSecondMenuOpen : 260,
        marginValWhenAllMenuOpen : 380,
    }

    function animateTaskList($widget, properties) {
        $widget.animate(properties, animateDuration, function() {
        	var taskListToolKit = TaskListToolKit();
        	taskListToolKit.responsiveInSmallScreen();
        });
     }

    this.updateMainContainer = function(){
        var $mainMenu = $('.js-left-sidebar');
        var $announcement = $('.js-announcement-message');
        var $taskListContainer = $('.js-task-list-container');

        var isSearchResultPage = $taskListContainer.hasClass('js-for-search-result');

        if ($mainMenu.hasClass('in')) {
            // Open main menu when second menu is closed
            animateTaskList($announcement, {marginLeft : marginValues.marginValWhenMainMenuOpen});
            animateTaskList($taskListContainer, {marginLeft : marginValues.marginValWhenMainMenuOpen});
            if (isSearchResultPage) {
              var simpleSmallScreen = new SimpleSmallScreen();
              simpleSmallScreen.updateMainContainer();
            }
        } else {
            // Close main menu when second menu is closed
            animateTaskList($announcement, {marginLeft : marginValues.marginValWhenAllMenuClose});
            animateTaskList($taskListContainer, {marginLeft : marginValues.marginValWhenAllMenuClose});
            if (isSearchResultPage) {
              var simpleSmallScreen = new SimpleSmallScreen();
              simpleSmallScreen.updateMainContainer();
            }
        }
    }
};

/***************************Handle responsive for Simple Screen**********************************/	
function SimpleLargeScreen(){
  var marginValWhenMainMenuOpen = 210;
  var marginValWhenTwoMenuClose = 0;

  this.updateMainContainer = function() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $simpleMainColumn = $('.js-simple-main-col');

    if ($mainMenu.hasClass('in')) {
      $announcement.animate({ marginLeft : marginValWhenMainMenuOpen }, animateDuration);
      $simpleMainColumn.animate({ marginLeft : marginValWhenMainMenuOpen }, animateDuration);
    } else { 
      $announcement.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
      $simpleMainColumn.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
    }
  }
}

function SimpleMediumScreen() {
  var marginValWhenMainMenuOpen = 190;
  var marginValWhenTwoMenuClose = 0;

  this.updateMainContainer = function() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $simpleMainColumn = $('.js-simple-main-col');

    if ($mainMenu.hasClass('in')) {
      $announcement.animate({marginLeft : marginValWhenMainMenuOpen}, animateDuration);
      $simpleMainColumn.animate({marginLeft : marginValWhenMainMenuOpen}, animateDuration);
    } else {
      $announcement.animate({marginLeft : marginValWhenTwoMenuClose}, animateDuration);
      $simpleMainColumn.animate({marginLeft : marginValWhenTwoMenuClose}, animateDuration);
    }
  }
}

function SimpleSmallScreen() {
  var marginValWhenMainMenuOpen = 130;
  var marginValWhenMenuClose = 0;

  this.updateMainContainer = function() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $simpleMainColumn = $('.js-simple-main-col');

    if ($mainMenu.hasClass('in')) {
      $announcement.animate({marginLeft : marginValWhenMainMenuOpen}, animateDuration);
      $simpleMainColumn.animate({marginLeft : marginValWhenMainMenuOpen}, animateDuration);
    } else {
      $announcement.animate({marginLeft : marginValWhenMenuClose}, animateDuration);
      $simpleMainColumn.animate({marginLeft : marginValWhenMenuClose}, animateDuration);
    }
  };
}

/***************************Handle responsive for Case List**********************************/
function CaseListLargeScreenHandler() {
  var marginValues = {
    marginValWhenAllMenuClose : 0,
    marginValWhenMainMenuOpen : 210,
    marginValWhenSecondMenuOpen : 280,
    marginValWhenAllMenuOpen : 410,
  }
  
  function animateCaseList($widget, properties) {
    $widget.animate(properties, animateDuration, function() {
      var caseListToolKit = CaseListToolKit();
      caseListToolKit.responsive();
    });
  }

  this.updateMainContainer = function(){
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $caseListContainer = $('.js-case-default-widget-container');

    var isSearchResultPage = $caseListContainer.hasClass('js-for-search-result');

    if ($mainMenu.hasClass('in')) {
        animateCaseList($announcement, { marginLeft : marginValues.marginValWhenMainMenuOpen });
        animateCaseList($caseListContainer, { marginLeft : marginValues.marginValWhenMainMenuOpen });
        if (isSearchResultPage) {
          var simpleLargeScreen = new SimpleLargeScreen();
          simpleLargeScreen.updateMainContainer();
        }
    } else {
        animateCaseList($announcement, { marginLeft : marginValues.marginValWhenAllMenuClose });
        animateCaseList($caseListContainer, { marginLeft : marginValues.marginValWhenAllMenuClose });
        if (isSearchResultPage) {
          var simpleLargeScreen = new SimpleLargeScreen();
          simpleLargeScreen.updateMainContainer();
        }
    }
  }
}

function CaseListMediumScreenHandler() {
  var marginValues = {
    marginValWhenAllMenuClose : 0,
    marginValWhenMainMenuOpen : 190,
    marginValWhenSecondMenuOpen : 260,
    marginValWhenAllMenuOpen : 380,
  }

  function animateCaseList($widget, properties) {
    $widget.animate(properties, animateDuration, function() {
      var caseListToolKit = CaseListToolKit();
      caseListToolKit.responsiveInMediumScreen();
    });
  }

  this.updateMainContainer = function(){
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $caseListContainer = $('.js-case-default-widget-container');

    var isSearchResultPage = $caseListContainer.hasClass('js-for-search-result');

    if ($mainMenu.hasClass('in')) {
        // Open main menu when second menu is closed
        animateCaseList($announcement, {marginLeft : marginValues.marginValWhenMainMenuOpen});
        animateCaseList($caseListContainer, {marginLeft : marginValues.marginValWhenMainMenuOpen});
        if (isSearchResultPage) {
          var simpleMediumScreen = new SimpleMediumScreen();
          simpleMediumScreen.updateMainContainer();
        }
    } else {
          // Close main menu when second menu is closed
          animateCaseList($announcement, {marginLeft : marginValues.marginValWhenAllMenuClose});
          animateCaseList($caseListContainer, {marginLeft : marginValues.marginValWhenAllMenuClose});
          if (isSearchResultPage) {
            var simpleMediumScreen = new SimpleMediumScreen();
            simpleMediumScreen.updateMainContainer();
          }
    }
  }
};

function CaseListSmallScreenHandler() {
  var marginValues = {
    marginValWhenAllMenuClose : 0,
    marginValWhenMainMenuOpen : 190,
    marginValWhenSecondMenuOpen : 260,
    marginValWhenAllMenuOpen : 380,
  }

  function animateCaseList($widget, properties) {
    $widget.animate(properties, animateDuration, function() {
      var caseListToolKit = CaseListToolKit();
      caseListToolKit.responsiveInSmallScreen();
    });
   }

  this.updateMainContainer = function(){
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $caseListContainer = $('.js-case-default-widget-container');

    var isSearchResultPage = $caseListContainer.hasClass('js-for-search-result');

    if ($mainMenu.hasClass('in')) {
        // Open main menu when second menu is closed
        animateCaseList($announcement, {marginLeft : marginValues.marginValWhenMainMenuOpen});
        animateCaseList($caseListContainer, {marginLeft : marginValues.marginValWhenMainMenuOpen});
        if (isSearchResultPage) {
          var simpleSmallScreen = new SimpleSmallScreen();
          simpleSmallScreen.updateMainContainer();
        }
    } else {
          // Close main menu when second menu is closed
          animateCaseList($announcement, {marginLeft : marginValues.marginValWhenAllMenuClose});
          animateCaseList($caseListContainer, {marginLeft : marginValues.marginValWhenAllMenuClose});
          if (isSearchResultPage) {
            var simpleSmallScreen = new SimpleSmallScreen();
            simpleSmallScreen.updateMainContainer(isSearchResultPage);
          }
    }
  }
};

/***************************Handle responsive for Dashboard**********************************/
function DashboardLargeScreen() {
  var marginValWhenMainMenuOpen = 210;
  var marginValWhenTwoMenuClose = 0;

  this.updateMainContainer = function() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $dashboard = $('.js-dashboard-default-widget-container');

    if ($mainMenu.hasClass('in')) {
      $announcement.animate({ marginLeft : marginValWhenMainMenuOpen }, animateDuration);
      $dashboard.animate({ marginLeft : marginValWhenMainMenuOpen }, animateDuration);
    } else {
      $announcement.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
      $dashboard.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
    }
  }
}

function DashboardMediumScreen() {
  var firstCol = {
    marginValWhenMainMenuOpen : 190,
    marginValWhenMainMenuClose : 0
  };

  this.updateMainContainer = function() {
    updateDashboard();
  }

  function updateDashboard() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $dashboard = $('.js-dashboard-default-widget-container');
    if ($mainMenu.hasClass('in')) {
      $announcement.animate({ marginLeft : firstCol.marginValWhenMainMenuOpen }, animateDuration);
      $dashboard.animate({ marginLeft : firstCol.marginValWhenMainMenuOpen }, animateDuration);
    } else {
      $announcement.animate({ marginLeft : firstCol.marginValWhenMainMenuClose }, animateDuration);
      $dashboard.animate({ marginLeft : firstCol.marginValWhenMainMenuClose }, animateDuration);
    }
  }
}

function DashboardSmallScreen() {
  var firstCol = {
    marginValWhenMenuOpen : 110,
    marginValWhenMenuClose : 0
  };

  var secondCol = {
    secondColumnSmallSize : 480,
    secondColumnLargeSize : 540,
    marginValWhenMenuOpen : 20,
    marginValWhenMenuClose : 45
  };

  this.updateMainContainer = function() {
    updateDashboard();
  }

  function moveStatisticsToFirstCol() {
    $('.js-dashboard-main-content-1st-col').append($('.js-statistic-widget'));
    $('.js-dashboard-main-content-3rd-col').addClass('u-hidden');
  }

  function updateDashboard() {
    var $mainMenu = $('.js-left-sidebar');
    var $announcement = $('.js-announcement-message');
    var $dashboardFirstCol = $('.js-dashboard-main-content-1st-col');
    var $dashboardSecondCol = $('.js-dashboard-main-content-2nd-col');
    var paddingLeft = 10;
    moveStatisticsToFirstCol();
    if ($mainMenu.hasClass('in')) {
      // Open main menu
      $dashboardFirstCol.animate({ marginLeft : firstCol.marginValWhenMenuOpen }, animateDuration);
      $dashboardSecondCol.animate({ width : secondCol.secondColumnSmallSize, marginLeft : secondCol.marginValWhenMenuOpen }, animateDuration);
      $announcement.animate({ marginLeft : firstCol.marginValWhenMenuOpen + paddingLeft }, animateDuration);
    } else {
      // Close main menu
      $dashboardFirstCol.animate({ marginLeft : firstCol.marginValWhenMenuClose }, animateDuration);
      $dashboardSecondCol.animate({ width : secondCol.secondColumnLargeSize, marginLeft : secondCol.marginValWhenMenuClose }, animateDuration);
      $announcement.animate({ marginLeft : firstCol.marginValWhenMenuClose + paddingLeft }, animateDuration);
    }
  }
}