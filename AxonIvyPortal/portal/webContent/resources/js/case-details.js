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
    // 20, down from the original 100, so a vertical resize snaps in 20px steps instead of
    // whole 100px jumps. Every default `y`/`h` in config/variables/Portal/CaseDetails.json (and in
    // the Showcase + Selenium layout fixtures) was multiplied by 5 to match, so the
    // out-of-the-box layout stays pixel-identical: y*20 == the old y*100 / 5.
    // Beware: a user can now drag a card down to h=1, i.e. 20px, which the 10px inset on
    // each side leaves with zero content height.
    // `column` cannot be raised the same way: gridstack.min.css bakes in the 1/12 geometry
    // (min-width:8.3333333333% and gs-w/gs-x rules for 1..12) and gridstack-extra.min.css
    // only ships .grid-stack-2 .. .grid-stack-11, so `column: 24` would add a
    // .grid-stack-24 class that no stylesheet defines and every card would fall back to 1/12.
    cellHeight: 20,
    // No `margin` here on purpose: GridStack's own default is 10px, which insets every card
    // by 10px on all four sides and so leaves a 20px gutter between neighbours. Deliberately
    // NOT rem — the inset does not need to follow html{font-size}. One thing is pinned to it:
    // --portal-detail-grid-inset (portal-variables-*.css), which .portal-detail-toolbar reads
    // so the bar lines up with the outer cards and sits one gutter above the first one.
    // Change this and you must change that token too.
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

// The `widget-type` attribute rendered by CaseItemDetails.xhtml is `#{widget.type}`, which
// AbstractConfigurableContentBean.updateWidgetsType() has already normalised to a WidgetType
// constant ("summary", "information", ...). The class-name cases below are the pre-normalisation
// values and are kept only as a fallback for any stale markup.
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