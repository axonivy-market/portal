var caseDetailsGrid;
$(function () {
  setTimeout(() => {
    loadCaseDetailsGrid();
  }, 0);
})

function loadCaseDetailsGrid() {
  initCaseDetailsGrid();
  saveChangedPosition();
}

function initCaseDetailsGrid() {
  caseDetailsGrid = GridStack.init({
    column: 12,
    resizable: {
      handles: "e, se, s, sw, w"
    },
    alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
  });
}

function saveChangedPosition() {
  if (!caseDetailsGrid) {
    return;
  }

  caseDetailsGrid.on("change", function () {
    var serializedData = [];
    caseDetailsGrid.engine.nodes.forEach((node) => {
      let widgetType = getcaseDetailsWidgetType(node.el.getAttribute("widget-type"));
      serializedData.push({
        type: widgetType,
        id: node.id,
        axisX: node.x,
        axisY: node.y,
        width: node.width,
        height: node.height
      });
      if (widgetType === "document" || widgetType === "history") {
        responsiveATableInPanel(node.el);
      }
    });
    saveConfigurationCommand([{
      name: "nodes",
      value: JSON.stringify(serializedData, null, "")
    }]);
  });
}

function getCaseDetailsWidgetType(caseDetailsWidgetName) {
  let type = "";
  switch (caseDetailsWidgetName) {
    case "CaseDetailsInformationWidget": type = "information"; break;
    case "CaseDetailsDocumentWidget": type = "document"; break;
    case "CaseDetailsHistoryWidget": type = "history"; break;
    case "CaseDetailsCustomWidget": type = "custom"; break;
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
