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
          x: node.x,
          y: node.y,
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

function toggleFilterSidebar(element) {
  $(element).closest('.widget__header').siblings('.widget__content').children('.widget__filter-sidebar').toggle('slide', {direction: 'right'});
  $(element).closest('.widget__header').siblings('.widget__content').children('.widget__filter-sidebar-mask').toggle();
}
