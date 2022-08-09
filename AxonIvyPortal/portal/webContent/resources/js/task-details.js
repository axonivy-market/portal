var taskDetailsGrid;
$(function () {
  setTimeout(() => {
    loadTaskDetailsGrid();
  }, 0);
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

function responsiveATableInPanel(widgetElement) {
  let headerArr = widgetElement.getElementsByTagName("th");
  if (!headerArr || headerArr.length === 0) {
    return;
  }

  for (let i = headerArr.length - 1; i > 0; i--) {
    hideColumnWhenNotEnoughWidth(headerArr[i]);
  }
}

function hideColumnWhenNotEnoughWidth(element) {
  removeStyle(element, "width");
  let currentWidth = element.getBoundingClientRect().width;
  element.style.display = "initial";
  let fullWidth = element.getBoundingClientRect().width;
  removeStyle(element, "display");
  if (currentWidth < fullWidth) {
    element.style.width = 0;
  }
}

function removeStyle(element, styleName) {
  if (element.style.removeProperty) {
    element.style.removeProperty(styleName);
  } else {
    element.style.removeAttribute(styleName);
  }
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
    if (widgetType === "document" || widgetType === "history") {
      responsiveATableInPanel(node.el);
    }
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