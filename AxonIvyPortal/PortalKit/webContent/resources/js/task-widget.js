function TaskWidget() {

  return {

    setupHeader : function() {
      var taskListToolKit = TaskListToolKit();
      taskListToolKit.setupHeader();
    },

    setupScrollbar : function() {
      var childElements = $('.js-task-start-list-item');
      var winHeight = window.innerHeight;
      if (childElements.length > 0) {
        var container = $('.js-task-start-list > .ui-datascroller-content');
        var taskWidgetHeaderContainer = $('.js-task-widget-header');
        var announcementMessageContainer = $('.js-announcement-message');
        var taskWidgetSortMenuContainer = $('.js-task-widget-sub-header');
        if (taskWidgetSortMenuContainer.outerHeight(true) == 0) {
          taskWidgetSortMenuContainer = $('.js-task-widget-sort-menu');
        }
        var taskWidgetFilterContainer = $('.js-filter-container');
        var customWidgetContainer = $('.js-custom-widget-container');
        if (customWidgetContainer.height() > 0) {
        	customWidgetContainer = customWidgetContainer.outerHeight(true)||0;
        } else {
        	customWidgetContainer = customWidgetContainer.height()||0;
        }
        var error = 5;
        var globalSearchInput = $('.js-global-search');
        var globalSearchTabHeader = $('.ui-tabs-nav');
        if (globalSearchTabHeader.length > 0) {
          error = 55; // included margin, padding in search page
        }
        var headerHeight = $('#portal-template-header').outerHeight();
        var footerHeight = $('#portal-template-footer').outerHeight();

        var compactProcessWidgetHeight = window.matchMedia("(max-width: 40em)").matches == true ? ($('.compact-process-widget').outerHeight(true) || 0) : 0;
        var taskViewPaddingValue = parseInt($('.js-task-view').css('padding-top'), 10) + parseInt($('.js-task-view').css('padding-bottom'), 10);
        var compactTaskWidgetPadding = ($('.dashboard-task-widget-container').outerHeight(true)||0) - ($('.dashboard-task-widget-container').height()||0);

        var mainScreenHeight = ($('body').outerHeight(true)||0) - ($('.layout-topbar').outerHeight(true)||0);
        var availableHeight = mainScreenHeight - (taskWidgetHeaderContainer.outerHeight(true)||0) - (announcementMessageContainer.outerHeight(true)||0)
            - (taskWidgetSortMenuContainer.outerHeight(true)||0) - (taskWidgetFilterContainer.outerHeight(true)||0)
            - customWidgetContainer - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0) - (globalSearchTabHeader.outerHeight(true)||0) - error
            - compactProcessWidgetHeight - taskViewPaddingValue - headerHeight - footerHeight
            - compactTaskWidgetPadding;
        if (!!availableHeight) {
          container.height(availableHeight);
          if (container.outerHeight(true) > availableHeight) {
            var taskStartItemMarginRight = $('.task-start-list-item').css("margin-right");
            var scrollbarWidth = container.width() - container.find('.ui-datascroller-list').outerWidth(true);
            if (scrollbarWidth > 0) {
              container.css("margin-right", taskStartItemMarginRight);
            }
          }
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
};
function getTaskListScrollPosition() {
  var scrollPos = Math.round($('#task-widget\\:task-view .ui-datascroller-content').scrollTop());
  var scrollPosInputHidden = $('input[id$=scroll-position]');
  scrollPosInputHidden.val(scrollPos);
}
