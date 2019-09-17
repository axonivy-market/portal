function TaskWidget() {

  return {

    setupHeader : function() {
      var taskListToolKit = TaskListToolKit();
      taskListToolKit.setupHeader();
    },

    setupScrollbar : function() {
      var childElements = $('.js-task-start-list-item');
      if (childElements.length > 0) {
        var container = $('.js-task-start-list > .ui-datascroller-content');
        var mainAreaPanel = $('#main-area-panel');
        var taskWidgetHeaderContainer = $('.js-task-widget-header');
        var announcementMessageContainer = $('.js-announcement-message');
        var taskWidgetSortMenuContainer = $('.js-task-widget-sub-header');
        if (taskWidgetSortMenuContainer.outerHeight(true) == 0) {
          taskWidgetSortMenuContainer = $('.js-task-widget-sort-menu');
        }
        var taskWidgetFilterContainer = $('.js-filter-container');
        var customWidgetContainer = $('.js-custom-widget-container');
        if (customWidgetContainer.height() > 0) {
        	customWidgetContainer = customWidgetContainer.outerHeight(true);
        } else {
        	customWidgetContainer = customWidgetContainer.height();
        }
        var error = 5;
        var globalSearchInput = $('.js-global-search');
        var globalSearchTabHeader = $('.ui-tabs-nav');
        if (globalSearchTabHeader.length > 0) {
          error = 55; // included margin, padding in search page
        }
        var mainScreenHeight = mainAreaPanel.outerHeight(true) - $('.layout-topbar').outerHeight(true) - 15; //minus 15 to remove 2nd scroll bar
        var availableHeight = mainScreenHeight - taskWidgetHeaderContainer.outerHeight(true) - announcementMessageContainer.outerHeight(true)
            - taskWidgetSortMenuContainer.outerHeight(true) - taskWidgetFilterContainer.outerHeight(true)
            - customWidgetContainer - globalSearchInput.outerHeight(true) - globalSearchTabHeader.outerHeight(true) - error;
        if (!!availableHeight) {
          container.height(availableHeight);
        }
      }
    },
    
    toggleTaskFilter: function(toggler) {
      $('.js-filter-container').toggleClass('u-hidden-md-down');
    },
  }
}

function TaskListToolKit() {

  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("ui-hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("ui-hidden");
  }

  return {
    setupHeader : function() {
      var taskSortMenu = $('.js-task-widget-sort-menu');
      var taskEntry = $('.js-task-start-link').first();
      var noEntry = taskEntry.length == 0;
      this.showHideColumnWhenMenuToggle();
      if (taskSortMenu.hasClass('full-mode')) {
        if (noEntry) {
          $(taskSortMenu).hide();
        } else {
          $(taskSortMenu).show();
        }
        $.each(taskSortMenu.children('a'), function(i, header) {
          var cell = $(taskEntry).children().get(i);
          $(header).outerWidth($(cell).outerWidth());
        });
      }
    },
    
    setupScrollbar : function() {
      var taskWidget = new TaskWidget();
      taskWidget.setupScrollbar();
    },

    showHideColumnWhenMenuToggle: function() {
      var $mainMenu = $('.js-left-sidebar');
      var remainingWidth = $('body').width() - $mainMenu.outerWidth() - 75;//exclude padding and scroll bar
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $mainMenu.hasClass('in')) {
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
};
function getTaskListScrollPosition() {
  var scrollPos = $('#task-widget\\:task-view .ui-datascroller-content').scrollTop();
  var scrollPosInputHidden = $('input[id$=scroll-position]');
  scrollPosInputHidden.val(scrollPos);
}
