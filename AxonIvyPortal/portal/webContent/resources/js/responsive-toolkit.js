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
    updateLayoutWithAnimation: function () {
      setTimeout(function () {
        updateLayout();
      }, 250);
    },

    updateLayoutWithoutAnimation: function () {
      animateDuration = 0;
      updateLayout();
    }
  };
};

/***************************Handle responsive for Process list**********************************/
function ProcessListScreenHandler() {
  this.updateMainContainer = function () {
    processWidget = ProcessWidget();
    processWidget.setupScrollbar();
  }
};

/***************************Handle responsive for Task list**********************************/
function TaskListScreenHandler() {
  this.updateMainContainer = function () {
    var taskListToolKit = TaskListToolKit();
    taskListToolKit.responsive();
  }
};

/***************************Handle responsive for Case List**********************************/
function CaseListScreenHandler() {
  this.updateMainContainer = function () {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.responsive();
  }
};

/***************************Handle responsive for Dashboard**********************************/
function DashboardScreenHandler() {
  this.updateMainContainer = function () {
    var dashboardToolKit = DashboardToolKit();
    dashboardToolKit.responsive();
  }
};

var PortalLayout = {
  removeLayoutContentPadding: function (removePaddingRight, removePaddingBottom) {
    if (removePaddingRight) {
      this.removeLayoutContentPaddingRight();
    }
    if (removePaddingBottom) {
      this.removeLayoutContentPaddingBottom();
    }
  },

  removeLayoutContentPaddingRight: function () {
    $(".js-layout-content").css({ "padding-right": 0 });
  },

  removeLayoutContentPaddingBottom: function () {
    $(".js-layout-content").css({ "padding-bottom": 0 });
  },

  removeJsStyleOnLayoutContent: function () {
    $(".js-layout-content").removeAttr("style");
  },

  getAvailableHeight: function () {
    var $layoutMain = $('.js-layout-main');
    var layoutMainPadding = parseInt($layoutMain.css('padding-top') || 0) + parseInt($layoutMain.css('padding-bottom') || 0);
    return window.innerHeight - layoutMainPadding;
  },

  getYPaddingLayoutContent: function() {
    var $layoutContent = $(".js-layout-content");
    return parseInt($layoutContent.css('padding-top') || 0) + parseInt($layoutContent.css('padding-bottom') || 0);
  },

  getPaddingRightLayoutContent: function () {
    return $(".js-layout-content").css("padding-right");
  },

  hideHorizontalScrollOnLayoutContent: function () {
    $(".js-layout-content").css("overflow-x", "hidden");
  }
}

/* ResponsiveTable function use to hide the column by the width of the selected table
    There are 4 priorities to control show/hide column
    - 'js-column-priority-1': only display when the availabe width of table is 900px
    - 'js-column-priority-2': only display when the availabe width of table is 600px
    - 'js-column-priority-3': only display when the availabe width of table is 400px
    - 'js-column-priority-4': only display when the availabe width of table is 300px
*/
var ResponsiveTable = {
  init: function (tableIdSelector) {
    if (tableIdSelector) {
      this.hideColumnWhenNotEnoughWidth(tableIdSelector);
    }
    return;
  },

  hideColumnWhenNotEnoughWidth: function (tableIdSelector) {
    const tableSelector = "[id$='" + tableIdSelector + "']";
    var $selectedTable = $(tableSelector);
    if ($selectedTable.length === 0) {
      return;
    }
    const tableWidth = $selectedTable.width();

    var $priorityColumn1 = $selectedTable.find(".js-column-priority-1");
    if ($priorityColumn1.length > 0) {
      if (tableWidth < 900) {
        $priorityColumn1.addClass('u-display-none');
      } else {
        $priorityColumn1.removeClass('u-display-none');
      }
    }

    var $priorityColumn2 = $selectedTable.find(".js-column-priority-2");
    if ($priorityColumn2.length > 0) {
      if (tableWidth < 600) {
        $priorityColumn2.addClass('u-display-none');
      } else {
        $priorityColumn2.removeClass('u-display-none');
      }
    }

    var $priorityColumn3 = $selectedTable.find(".js-column-priority-3");
    if ($priorityColumn3.length > 0) {
      if (tableWidth < 400) {
        $priorityColumn3.addClass('u-display-none');
      } else {
        $priorityColumn3.removeClass('u-display-none');
      }
    }

    var $priorityColumn4 = $selectedTable.find(".js-column-priority-4");
    if ($priorityColumn4.length > 0) {
      if (tableWidth < 300) {
        $priorityColumn4.addClass('u-display-none');
      } else {
        $priorityColumn4.removeClass('u-display-none');
      }
    }
  }
};

var PortalGlobalSearch = {
  isSearchPageOpened: function () {
    var globalSearchTabHeader = $('.ui-tabs-nav');
    return globalSearchTabHeader.length > 0;
  },

  getAvailableHeight: function (tabId) {
    var availableHeight = 0;
    if (this.isSearchPageOpened()) {
      var $globalSearchInput = $('.js-global-search');
      var globalSearchInputHeight = $globalSearchInput.is(":visible") ? $globalSearchInput.outerHeight(true) : 0;

      var $searchResultsContainer = $(".js-search-results-tabview-container");
      var searchTabViewPadding = parseInt($searchResultsContainer.css("padding-top") || 0) + parseInt($searchResultsContainer.css("padding-bottom") || 0);

      var $resultsTabView = $('.js-search-results-tabview');
      var searchResultMarginPadding = ($resultsTabView.outerHeight(true) || 0) - ($resultsTabView.height() || 0);
      var resultsTabViewNavHeight = parseInt($resultsTabView.find(".ui-tabs-nav").outerHeight(true) || 0);

      var resultsTabViewPanelPadding = 0;
      var $resultsTabViewPanel = $resultsTabView.find(".ui-tabs-panels [id$='" + tabId + "']");
      if ($resultsTabViewPanel.length > 0) {
        resultsTabViewPanelPadding = parseInt($resultsTabViewPanel.css('padding-top') || 0) + parseInt($resultsTabViewPanel.css('padding-bottom') || 0);
      }

      availableHeight = globalSearchInputHeight + resultsTabViewNavHeight + resultsTabViewPanelPadding + searchResultMarginPadding + searchTabViewPadding;
    }
    return availableHeight;
  }
};

function isMobileDevices() {
  var ua = window.navigator.userAgent;
  return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(ua);
}