function TaskWidget(outerPanelId) {
  var items = $('#' + outerPanelId + ' .js-widget-header-menu li');
  $.each(items, function() {
    $(this).click(function() {
      if (!$(this).hasClass('js-ignore-selected-state')) {
        $(items).removeClass('is-selected');
        $(this).addClass('is-selected');
      }
    });
  });

  return {

    setupHeader : function() {
      var taskListToolKit = TaskListToolKit();
      taskListToolKit.setupHeader();
    },

    setupScrollbar : function() {

      var childElements = $('.js-task-start-list-item');
      if (childElements.length > 0) {
        var container = $('.js-task-start-list > .ui-datascroller-content');
        var mainAreaPanel = $('#' + outerPanelId);
        var taskWidgetHeaderContainer = $('.js-task-widget-header');
        var announcementMessageContainer = $('.js-announcement-message');
        var taskWidgetSortMenuContainer = $('.js-task-widget-sub-header');
        if (taskWidgetSortMenuContainer.outerHeight(true) == 0) {
          taskWidgetSortMenuContainer = $('.js-task-widget-sort-menu');
        }
        var taskWidgetFilterContainer = $('.js-filter-container');
        var customWidgetContainer = $('.js-custom-widget-container');
        var error = 5;
        var globalSearchInput = $('.js-global-search');
        var globalSearchTabHeader = $('.ui-tabs-nav');
        if (globalSearchTabHeader.length > 0) {
          error = 55; // included margin, padding in search page
        }
        var availableHeight = mainAreaPanel.outerHeight() - taskWidgetHeaderContainer.outerHeight(true) - announcementMessageContainer.outerHeight(true)
            - taskWidgetSortMenuContainer.outerHeight(true) - taskWidgetFilterContainer.outerHeight(true)
            - customWidgetContainer.outerHeight(true) - globalSearchInput.outerHeight(true) - globalSearchTabHeader.outerHeight(true) - error;

        if (!!availableHeight) {
          container.height(availableHeight);
        }
      }
    },
  }
}

function TaskListToolKit() {

  function hideColumnInMediumScreen($header, $cell) {
    $header.addClass("hidden-md");
    $cell.addClass("hidden-md");
  }

  function displayColumnInMediumScreen($header, $cell) {
    $header.removeClass("hidden-md");
    $cell.removeClass("hidden-md");
  }

  function hideColumnInSmallScreen($header, $cell) {
    $header.addClass("hidden-sm");
    $cell.addClass("hidden-sm");
  }

  function displayColumnInSmallScreen($header, $cell) {
    $header.removeClass("hidden-sm");
    $cell.removeClass("hidden-sm");
  }

  return {
    setupHeader : function() {
      var taskSortMenu = $('.js-task-widget-sort-menu');
      var taskEntry = $('.js-task-start-link').first();
      var noEntry = taskEntry.length == 0;
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

    responsiveInMediumScreen : function() {
      var $mainMenu = $('.js-left-sidebar');
      var $responsibleColumnHeader = $('.js-responsible-column-header');
      var $responsibleCell = $('.js-responsible-cell');

      if ($mainMenu.hasClass('in')) {
        hideColumnInMediumScreen($responsibleColumnHeader, $responsibleCell);
      } else {
        displayColumnInMediumScreen($responsibleColumnHeader, $responsibleCell);
      }
      this.setupHeader();
    },

    responsiveInSmallScreen : function() {
      var $mainMenu = $('.js-left-sidebar');
      var $idColumnHeader = $('.js-id-column-header');
      var $idCell = $('.js-id-cell');
      var $createColumnHeader = $('.js-create-column-header');
      var $createCell = $('.js-create-cell');
      var $expiryColumnHeader = $('.js-expiry-column-header');
      var $expiryCell = $('.js-expiry-cell');

      if ($mainMenu.hasClass('in')) {
        hideColumnInSmallScreen($idColumnHeader, $idCell);
        displayColumnInSmallScreen($createColumnHeader, $createCell);
        displayColumnInSmallScreen($expiryColumnHeader, $expiryCell);
      } else {
        displayColumnInSmallScreen($idColumnHeader, $idCell);
        displayColumnInSmallScreen($createColumnHeader, $createCell);
        displayColumnInSmallScreen($expiryColumnHeader, $expiryCell);
      }
      this.setupHeader();
    },

    responsive : function() {
      if (viewPort.isMediumScreen()) {
        this.responsiveInMediumScreen();
      } else if (viewPort.isSmallScreen()) {
        this.responsiveInSmallScreen();
      } else {
        this.setupHeader();
      }
    }
  }
};
function getTaskListScrollPosition() {
  var scrollPos = $('#task-widget\\:task-view .ui-datascroller-content').scrollTop();
  var scrollPosInputHidden = $('input[id$=scroll-position]');
  scrollPosInputHidden.val(scrollPos);
}
