var grids;
var originalGridstackHeight = 0;
loadGrid();

function loadGrid() {
  grids = GridStack.initAll({
    column: 12,
    cellHeight: 100,
    resizable: {
      handles: 'e, se, s, sw, w'
    }
  });
  
  grids.forEach(function (grid, i) {
    grid.on('change', function() {
      var serializedData = [];
      grid.engine.nodes.forEach(function(node) {
        serializedData.push({
          id: node.id,
          x: node.x,
          y: node.y,
          w: node.w,
          h: node.h
        });
      });
      if (grid.opts.minWidth < grid.el.clientWidth) {
        saveConfigurationCommand([{
          name : 'nodes',
          value : JSON.stringify(serializedData, null, '')
        }]);
      }
    });
  });
  
  // Disable all pointer events of iframes when edit widgets
  if($('div.js-dashboard__body').hasClass('readonly')) {
    enableAllIFrameWhenEditLayout();
  } else {
    disableAllIFrameWhenEditLayout();
  }
}

function handleFilterCategoryHeight(e) {
  var filterTab = $(document.getElementById(e.id)).find("[id$=':filter-tab']");
  if (filterTab.lenght > 0 && filterTab.get(0).ariaHidden == "false") {
    var categoryInfo = filterTab.find(".js-category-filter-info");
    if (categoryInfo.get(0).offsetHeight > 160) {
      $(categoryInfo).toggleClass("minimize-content");
      filterTab.find(".js-category-toggle").toggleClass("si-add-circle").toggleClass("si-subtract-circle");
    }
  }
}

function toggleCategoryInfo(e) {
  var categoryToggle = $(e);
  var categoryInfo = categoryToggle.parent().find(".js-category-filter-info");
  categoryInfo.toggleClass("minimize-content");
  categoryToggle.toggleClass("si-add-circle").toggleClass("si-subtract-circle");
}

function removeWidgetContent(widgetId) {
  var removeWidgetId = '.grid-stack-item[gs-id="' + widgetId + '"]';
  $(removeWidgetId).remove();
  updateDashboardWidget();
}

function DashboardToolKit() {
  return {
    setupScrollbar: function() {
      setupScrollbar();
    },

    responsive: function() {
      this.setupScrollbar();
    }
  };
}

function setupScrollbar() {
  var gridstackItems = $('.grid-stack-item');
  if (gridstackItems.length > 0) {
    var container = $('.js-dashboard__body');
    var headerContainer = ($('.js-dashboard__header').outerHeight(true)||0);
    var announcementMessageContainer = ($('.js-announcement-message').outerHeight(true)||0);
    var layoutContentPadding = ($('.layout-content').outerHeight(true)||0) - ($('.layout-content').height()||0);
    var mainScreenHeight = ($('.js-layout-content').outerHeight(true)||0);

    var availableHeight = mainScreenHeight - headerContainer - announcementMessageContainer - layoutContentPadding;
    if (!!availableHeight) {
      container.outerHeight(availableHeight);
    }
  }
}

function resizeCustomWidget(index, widgetId) {
  var iframe = $($('iframe[name = "custom-widget-iframe-' + index + '"]').get(0));
  var gridstackDiv = $('div.grid-stack-item[gs-id = "' + widgetId + '"]').get(0);
  var header = $(gridstackDiv).find('.widget__header').get(0);
  iframe.height(gridstackDiv.clientHeight - header.clientHeight);
}

function expandFullscreen(index, widgetId) {
  var widget = $('div.grid-stack-item[gs-id = "' + widgetId + '"]');
  widget.addClass('expand-fullscreen');
  widget.get(0).innerWidth = window.innerWidth;
  widget.get(0).outerWidth = window.outerWidth;
  widget.get(0).innerHeight = window.innerHeight;
  widget.get(0).outerHeight = window.outerHeight;

  originalGridstackHeight =  $(widget.get(0)).parent('.grid-stack').height();
  $(widget.get(0)).parent('.grid-stack').height($(widget.get(0)).height());

  resizeCustomWidget(index, widgetId);
}

function collapseFullscreen(index, widgetId) {
  var widget = $('div.grid-stack-item[gs-id = "' + widgetId + '"]');
  widget.removeClass('expand-fullscreen');
  widget.height('100%');

  $(widget.get(0)).parent('.grid-stack').height(originalGridstackHeight);
  resizeCustomWidget(index, widgetId);
}

function disableAllIFrameWhenEditLayout() {
  var iframes = $("iframe");
  if (iframes.length > 0) {
    iframes.css('pointer-events', 'none');
  }
}

function enableAllIFrameWhenEditLayout() {
  var iframes = $("iframe");
  if (iframes.length > 0) {
    iframes.css('pointer-events', 'auto');
  }
}