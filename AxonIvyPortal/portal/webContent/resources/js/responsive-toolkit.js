 "use strict";
var animateDuration = 0;
function ResponsiveToolkit(responsiveToolkit) {
  var VALID_METHOD = "updateMainContainer";

  function validateArguments() {

    var errorMessageFormat = "{0} have to implement {1} method";

    if (!responsiveToolkit.hasOwnProperty(VALID_METHOD)) {
      throw Error(errorMessageFormat.replace("{0}", "responsiveToolkit").replace("{1}", VALID_METHOD));
    }
  }

  function updateLayout() {
    validateArguments();
    responsiveToolkit.updateMainContainer();
  }

  return {
    updateLayoutWithAnimation : function() {
      setTimeout(function() {
        updateLayout();
      }, 250);
    },

    updateLayoutWithoutAnimation : function() {
      animateDuration = 0;
      updateLayout();
    }
  };
};

/***************************Handle responsive for Process list**********************************/
function ProcessListScreenHandler() {
  this.updateMainContainer = function(){
    processWidget = ProcessWidget();
    processWidget.setupScrollbar();
  }
};

/***************************Handle responsive for Task list**********************************/
function TaskListScreenHandler() {
  this.updateMainContainer = function(){
    var taskListToolKit = TaskListToolKit();
    taskListToolKit.responsive();
  }
};

/***************************Handle responsive for Case List**********************************/
function CaseListScreenHandler() {
  this.updateMainContainer = function(){
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.responsive();
  }
};

/***************************Handle responsive for Dashboard**********************************/
function DashboardScreenHandler() {
  this.updateMainContainer = function(){
    var dashboardToolKit = DashboardToolKit();
    dashboardToolKit.responsive();
  }
};