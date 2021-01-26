var grids;
loadGrid();

function loadGrid() {
  grids = GridStack.initAll({
    column: 12, 
    resizable: {
      handles: 'e, se, s, sw, w'
    }
  });
  
  grids.forEach(function (grid, i) {
    grid.on('change', function() {
      var serializedData = [];
      grid.engine.nodes.forEach(function(node) {
        serializedData.push({
          type: node.id.split('_')[0],
          id: node.id,
          axisX: node.x,
          axisY: node.y,
          width: node.width,
          height: node.height
        });
      });
      saveConfigurationCommand([{
        name : 'nodes',
        value : JSON.stringify(serializedData, null, '')
      }]);
    });
  });
}


function handleFilterCategoryHeight(e) {
  var filterTab = $(document.getElementById(e.id)).find("[id$=':filter-tab']");
  if (filterTab.get(0).ariaHidden == "false") {
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