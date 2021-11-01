var grids;
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
}

function getPortalGridsCurrentRow(widgetType) {
  let currentRowNumber = 0;
  let dashboard = $("#dashboard-body");
  if (dashboard.length > 0) {
    let gridStack = dashboard.find("#grid-stack");
    if (gridStack.length > 0) {
      currentRowNumber = gridStack.attr("gs-current-row");
    }
  }
  addNewDashboardWidgetCommand([{
    name : 'portalGridsCurrentRow',
    value : currentRowNumber
  }]);
}

function scrollToWidget(widgetId) {
  let widgetClass = ".js-dashboard-widget-" + widgetId;
  $(widgetClass);
  console.log("widget seleted " + $(widgetClass));

  let $container = $("#dashboard-body");
  let $scrollTo = $(widgetClass);
  $container.scrollTop(
    $scrollTo.offset().top - $container.offset().top + $container.scrollTop() - 10
);
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
