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
  this.updateMainContainer = function(){
    var taskListToolKit = TaskListToolKit();
    taskListToolKit.responsive();
  }
}

function TaskListMediumScreenHandler() {
  this.updateMainContainer = function(){
    var taskListToolKit = TaskListToolKit();
    taskListToolKit.responsiveInMediumScreen();
  }
};

function TaskListSmallScreenHandler() {
    this.updateMainContainer = function(){
      var taskListToolKit = TaskListToolKit();
      taskListToolKit.responsiveInSmallScreen();
   }
};

/***************************Handle responsive for Case List**********************************/
function CaseListLargeScreenHandler() {
  this.updateMainContainer = function(){
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.responsive();
  }
}

function CaseListMediumScreenHandler() {
  this.updateMainContainer = function(){
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.responsiveInMediumScreen();
  }
};

function CaseListSmallScreenHandler() {
  this.updateMainContainer = function(){
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.responsiveInSmallScreen();
  }
};

/***************************Handle responsive for Dashboard**********************************/
function DashboardLargeScreen() {
  var marginValWhenTwoMenuClose = 0;

  this.updateMainContainer = function() {
    var $announcement = $('.js-announcement-message');
    var $dashboard = $('.js-dashboard-default-widget-container');
    $announcement.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
    $dashboard.animate({ marginLeft : marginValWhenTwoMenuClose }, animateDuration);
  }
}

function DashboardMediumScreen() {
  var marginValWhenMainMenuClose = 0;

  this.updateMainContainer = function() {
    updateDashboard();
  }

  function updateDashboard() {
    var $announcement = $('.js-announcement-message');
    var $dashboard = $('.js-dashboard-default-widget-container');

    $announcement.animate({ marginLeft : marginValWhenMainMenuClose }, animateDuration);
    $dashboard.animate({ marginLeft : marginValWhenMainMenuClose }, animateDuration);
  }
}

function DashboardSmallScreen() {
  this.updateMainContainer = function() {
    updateDashboard();
  }

  function moveStatisticsToFirstCol() {
    $('.js-dashboard-main-content-1st-col').append($('.js-statistic-widget'));
    $('.js-dashboard-main-content-3rd-col').addClass('u-hidden');
  }

  function updateDashboard() {
    moveStatisticsToFirstCol();
  }
}