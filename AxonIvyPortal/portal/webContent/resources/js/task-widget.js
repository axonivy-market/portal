function TaskWidget() {

  return {

    setupHeader: function() {
      var taskListToolKit = TaskListToolKit();
      taskListToolKit.setupHeader();
    },

    setupScrollbar: function() {
      let needShowScrollbar = false;
      var childElements = $('.js-task-start-list-item');
      if (childElements.length > 0) {
        var container = $('.js-task-start-list > .ui-datascroller-content');
        var taskWidgetHeaderContainer = $('.js-task-widget-header');

        // temporary hide mobile title to calculate
        var mobileTitle = taskWidgetHeaderContainer.find(".js-widget-title-mobile");
        if (mobileTitle.length > 0) {
          $(".js-layout-content").css("overflow-y", "hidden");
          mobileTitle.addClass("hidden");
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

        var compactProcessWidgetHeight = window.matchMedia("(max-width: 40em)").matches == true ? ($('.js-compact-process-widget-panel').outerHeight(true)||0) : 0;
        var compactTaskWidgetPadding = ($('.js-compact-task-widget').outerHeight(true)||0) - ($('.js-compact-task-widget').height()||0);
        var taskViewPadding = ($('.js-task-view').outerHeight(true)||0) - ($('.js-task-view').height()||0);

        var mainScreenHeight = PortalLayout.getAvailableHeight();

        // When open task list inside case information dialog, back link is displayed. So we should subtract its height also
        var backlinkHeight = $('.js-back-link').outerHeight(true)||0;

        var availableHeight = mainScreenHeight - (taskWidgetHeaderContainer.outerHeight(true)||0)
                              - (announcementMessageContainer.outerHeight(true)||0) - (taskWidgetSortMenuContainer.outerHeight(true)||0)
                              - customWidgetContainer
                              - taskViewPadding
                              - compactTaskWidgetPadding - compactProcessWidgetHeight - backlinkHeight;

        if (PortalGlobalSearch.isSearchPageOpened()) {
          availableHeight = availableHeight - PortalGlobalSearch.getAvailableHeight(':task-tab');
        } else {
          needShowScrollbar = (childElements.length * $(childElements[0]).outerHeight(true)||1) > availableHeight;
          if (needShowScrollbar && !isMobileDevices()) {
            PortalLayout.removeLayoutContentPaddingBottom();
            $('.js-task-list-container').css('margin-right', '-' + PortalLayout.getPaddingRightLayoutContent());
          }
        }

        if (!!availableHeight) {
          availableHeight = availableHeight - PortalLayout.getYPaddingLayoutContent();
          container.outerHeight(availableHeight);
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
          mobileTitle.removeClass("hidden");
        }
      }
      if (!needShowScrollbar) {
        PortalLayout.removeJsStyleOnLayoutContent();
      }
    },

    toggleTaskFilter: function() {
      $('.js-advanced-filter-component').toggleClass('u-hidden-md-down');
      $('.js-filter-add-action').toggleClass('u-hidden-md-down');
      $('.js-filter-reset-action').toggleClass('u-hidden-md-down');
      $('.js-filter-save-action').toggleClass('u-hidden-md-down');
    },

    showTaskFilter: function() {
      $('.js-advanced-filter-component').removeClass('u-hidden-md-down');
      $('.js-filter-add-action').removeClass('u-hidden-md-down');
      $('.js-filter-reset-action').removeClass('u-hidden-md-down');
      $('.js-filter-save-action').removeClass('u-hidden-md-down');
    },

    updateTaskCountToBreadcrumb: function() {
      var $hiddenTaskCountElem = $(".js-hidden-task-count");
      // $hiddenTaskCountElem is not rendered if task count is disabled. So that do not update task count to breadcrumb.
      if ($hiddenTaskCountElem.length === 0) {
        return;
      }

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

      let taskCountMobile = $(".js-task-count-mobile");
      if (taskCountMobile.length > 0) {
        taskCountMobile.get(0).innerHTML = " (" + $(".js-hidden-task-count").get(0).innerHTML + ")";
      }
    }
  };
}

function TaskListToolKit() {

  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("hidden");
  }

  return {
    setupHeader: function() {
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
        $.each(taskSortMenu.children('a, .js-unsortable-header-cell'), function(i, header) {
          var cell = $(taskEntry).children().get(i);
          $(header).outerWidth($(cell).outerWidth());
        });
      }
    },

    setupScrollbar: function() {
      var taskWidget = new TaskWidget();
      taskWidget.setupScrollbar();
    },

    showHideColumnWhenMenuToggle: function() {
      var $layout = $('.js-layout-wrapper');
      var remainingWidth = $('.js-layout-content').outerWidth(true);
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $layout.hasClass('layout-static')) {
        hideColumnWhenExpandMenu($hiddenColumns);
      } else {
        displayColumnWhenCollapseMenu($hiddenColumns);
      }
    },

    responsive: function() {
      this.setupScrollbar();
      this.setupHeader();
    }
  };
}

function getTaskListScrollPosition() {
  var scrollPos = Math.round($('#task-widget\\:task-view .ui-datascroller-content').scrollTop());
  var scrollPosInputHidden = $('input[id$=scroll-position]');
  scrollPosInputHidden.val(scrollPos);
}
