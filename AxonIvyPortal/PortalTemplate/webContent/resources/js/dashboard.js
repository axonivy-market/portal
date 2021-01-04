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
  var widgetContent = $(element).closest('.widget__header').siblings('.widget__content');

  widgetContent.children('.js-widget-mask').not('.widget__filter-sidebar-mask').hide();
  widgetContent.children('.js-widget-card').not('.widget__filter-sidebar').hide();

  widgetContent.children('.widget__filter-sidebar').toggle('slide', {direction: 'right'});
  widgetContent.children('.widget__filter-sidebar-mask').toggle();
}

function toggleInfoSidebar(element) {
  var widgetContent = $(element).closest('.widget__header').siblings('.widget__content');

  widgetContent.children('.js-widget-mask').not('.widget__info-sidebar-mask').hide();
  widgetContent.children('.js-widget-card').not('.widget__info-sidebar').hide();

  widgetContent.children('.widget__info-sidebar').toggle('slide', {direction: 'right'});
  widgetContent.children('.widget__info-sidebar-mask').toggle();
}
