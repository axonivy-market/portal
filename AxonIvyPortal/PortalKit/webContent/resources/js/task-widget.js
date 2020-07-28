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
        var container = $('.js-task-start-list:not(.js-is-guide) > .ui-datascroller-content');
        var taskWidgetHeaderContainer = $('.js-task-widget-header');

        // temporary hide mobile title to calculate
        var mobileTitle = taskWidgetHeaderContainer.find(".js-widget-title-mobile");
        if (mobileTitle.length > 0) {
          $(".js-layout-content").css("overflow-y", "hidden");
          mobileTitle.addClass("u-hidden");
        }

        var announcementMessageContainer = $('.js-announcement-message');
        var taskWidgetSortMenuContainer = $('.js-task-widget-sub-header');
        if (taskWidgetSortMenuContainer.outerHeight(true) == 0) {
          taskWidgetSortMenuContainer = $('.js-task-widget-sort-menu');
        }
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

        var compactProcessWidgetHeight = window.matchMedia("(max-width: 40em)").matches == true ? ($('.compact-process-widget').outerHeight(true) || 0) : 0;
        var compactTaskWidgetPadding = $('.compact-task-widget').outerHeight(true) - $('.compact-task-widget').height()||0;
        var taskViewPadding = $('.js-task-view').outerHeight(true) - $('.js-task-view').height()||0;
        var layoutContentPadding = $('.layout-content').outerHeight(true) - $('.layout-content').height()||0;
        var containerPadding = container.outerHeight(true) - container.height()||0;

        var mainScreenHeight = ($('.js-layout-content').outerHeight(true)||0);
        var availableHeight = mainScreenHeight - (taskWidgetHeaderContainer.outerHeight(true)||0)
            - (announcementMessageContainer.outerHeight(true)||0) - (taskWidgetSortMenuContainer.outerHeight(true)||0)
            - error - customWidgetContainer
            - taskViewPadding - layoutContentPadding - compactTaskWidgetPadding - compactProcessWidgetHeight;
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

        // show mobile title after calculate
        if (mobileTitle.length > 0) {
          mobileTitle.removeClass("u-hidden");
        }
      }
    },
    
    toggleTaskFilter: function(toggler) {
      $('.js-advanced-filter-component').toggleClass('u-hidden-md-down');
      $('.js-filter-add-action').toggleClass('u-hidden-md-down');
      $('.js-filter-reset-action').toggleClass('u-hidden-md-down');
      $('.js-filter-save-action').toggleClass('u-hidden-md-down');
    },

    updateTaskCountToBreadcrumb : function() {
      var $breadCrumbTaskElem = $("[id $= ':breadcrumb'] li").last().find(".ui-menuitem-link");
      if ($breadCrumbTaskElem.length == 0) {
        return;
      }

      if ($breadCrumbTaskElem.find(".js-count").length == 0) {
        $breadCrumbTaskElem.find("span").addClass("has-count");
        $breadCrumbTaskElem.append('<span class="js-count has-count"> (' + $(".js-hidden-task-count").get(0).innerHTML + ')</span>');
      } else {
    	  $breadCrumbTaskElem.find(".js-count").get(0).innerHTML = " (" + $(".js-hidden-task-count").get(0).innerHTML + ")";
      }

      $(".js-task-count-mobile").get(0).innerHTML = " (" + $(".js-hidden-task-count").get(0).innerHTML + ")";
    }
  }
}

function TaskListToolKit() {

  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("u-hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("u-hidden");
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
  var scrollPos = $('#task-widget\\:task-view .ui-datascroller-content').scrollTop();
  var scrollPosInputHidden = $('input[id$=scroll-position]');
  scrollPosInputHidden.val(scrollPos);
}
