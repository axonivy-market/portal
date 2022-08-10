var caseDetailsGrid;
$(function () {
  setTimeout(() => {
    loadCaseDetailsGrid();
  }, 0);
})

function loadCaseDetailsGrid() {
  initCaseDetailsGrid();
}

function initCaseDetailsGrid() {
  caseDetailsGrid = GridStack.init({
    column: 12,
    cellHeight: 100,
    resizable: {
      handles: "e, se, s, sw, w"
    },
    alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
  });
  
}

function saveCaseDetailsGrid() {
  if (!caseDetailsGrid) {
    return;
  }

  let serializedData = [];
  caseDetailsGrid.engine.nodes.forEach((node) => {
    let widgetType = getCaseDetailsWidgetType(node.el.getAttribute("widget-type"));
    serializedData.push({
      type: widgetType,
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

function getCaseDetailsWidgetType(caseDetailsWidgetName) {
  let type = "";
  switch (caseDetailsWidgetName) {
    case "InformationWidget": type = "information"; break;
    case "DocumentWidget": type = "document"; break;
    case "TechnicalCaseWidget": type = "technicalCase"; break;
    case "RelatedTaskWidget": type = "relatedTask"; break;
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
