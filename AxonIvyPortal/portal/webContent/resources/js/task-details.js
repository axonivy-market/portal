var taskDetailsGrid;
$(function () {
  setTimeout(() => {
    loadTaskDetailsGrid();
  }, 0);
  
  handleTaskDetailsTables();
})

function loadTaskDetailsGrid() {
  initTaskDetailsGrid();
}

function initTaskDetailsGrid() {
  taskDetailsGrid = GridStack.init({
    column: 12,
    cellHeight: 100,
    resizable: {
      handles: "e, se, s, sw, w"
    },
    alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
  });

  taskDetailsGrid.on('resize resizestop', function (event, element) {
    var elementId = element.gridstackNode.id;
    if (elementId === 'document') {
      ResponsiveTable.init(":task-details-documents");
    }
    if (elementId === 'history') {
      ResponsiveTable.init(":task-note-table");
    }
  });
}

function getTaskDetailsWidgetType(taskDetailsWidgetName) {
  let type = "";
  switch (taskDetailsWidgetName) {
    case "InformationWidget": type = "information"; break;
    case "DocumentWidget": type = "document"; break;
    case "HistoryWidget": type = "history"; break;
    case "CustomWidget": type = "custom"; break;
    default:
  }

  return type;
}

function saveTaskDetailsGrid() {
  if (!taskDetailsGrid) {
    return;
  }

  let serializedData = [];
  taskDetailsGrid.engine.nodes.forEach((node) => {
    let widgetType = getTaskDetailsWidgetType(node.el.getAttribute("widget-type"));
    serializedData.push({
      id: node.id,
      x: node.x,
      y: node.y,
      w: node.w,
      h: node.h
    });
  });

  saveConfigurationCommand([{
    name: "nodes",
    value: JSON.stringify(serializedData, null, "")
  }]);
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

function backToPrevPage() {
  var iframes = $('.js-task-details-widgets').find('iframe');
  if (iframes.length > 0) {
    iframes.remove();
  }
  window.history.back();
}

$(window).on('resize', function () {
  handleTaskDetailsTables();
});

function handleTaskDetailsTables() {
  var documentTable = $("[id$='task-details-documents']");
  if (documentTable && documentTable.length > 0) {
    ResponsiveTable.init(":task-details-documents");
  }
  var historyTable = $("[id$='task-note-table']");
  if (historyTable && historyTable.length > 0) {
    ResponsiveTable.init(":task-note-table");
  }
}