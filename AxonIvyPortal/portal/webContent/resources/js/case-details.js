var caseDetailsGrid;
$(function () {
  setTimeout(() => {
    loadCaseDetailsGrid();
  }, 0);
  handleCaseDetailsTable();
})

function loadCaseDetailsGrid() {
  initCaseDetailsGrid();
}

function initCaseDetailsGrid() {
  caseDetailsGrid = GridStack.init({
    column: 12,
    cellHeight: 20,
    resizable: {
      handles: "e, se, s, sw, w"
    },
    alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
  });
  
  caseDetailsGrid.on('resize resizestop', function (event, element) {
    var elementId = element.gridstackNode.id;
    if (elementId === 'document') {
      ResponsiveTable.init(":document:case-details-documents");
    }
    if (elementId === 'history') {
      ResponsiveTable.init(":case-histories:case-histories");
    }
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
  });
  saveConfigurationCommand([{
    name: "nodes",
    value: JSON.stringify(serializedData, null, "")
  }]);
}

function getCaseDetailsWidgetType(caseDetailsWidgetName) {
  const knownTypes = ["summary", "information", "document", "technicalCase",
    "businessDetails", "relatedTask", "history", "custom"];
  if (knownTypes.includes(caseDetailsWidgetName)) {
    return caseDetailsWidgetName;
  }

  let type = "";
  switch (caseDetailsWidgetName) {
    case "SummaryWidget": type = "summary"; break;
    case "InformationWidget": type = "information"; break;
    case "DocumentWidget": type = "document"; break;
    case "TechnicalCaseWidget": type = "technicalCase"; break;
    case "BusinessDetailsWidget": type = "businessDetails"; break;
    case "RelatedTaskWidget": type = "relatedTask"; break;
    case "HistoryWidget": type = "history"; break;
    case "CustomWidget": type = "custom"; break;
    default:
  }

  return type;
}

$(window).on('resize', function () {
  handleCaseDetailsTable();
});

function handleCaseDetailsTable() {
  var documentTable = $("[id$=':document:case-details-documents']");
  if (documentTable && documentTable.length > 0) {
    ResponsiveTable.init(":document:case-details-documents");
  }
  var historyTable = $("[id$=':case-histories:case-histories']");
  if (historyTable && historyTable.length > 0) {
    ResponsiveTable.init(":case-histories:case-histories");
  }
}

function checkShareCaseDetailsCopyFeature() {
  if (navigator.clipboard === undefined) {
    $("button[id$=':share-link-copy-button']").remove();
  }
}

function copyShareCaseDetailsLink(text) {
  navigator.clipboard.writeText(text);
  PF('share-case-details-panel').hide();
}