var caseDetailsGrid;
$(function () {
  setTimeout(() => {
    loadCaseDetailsGrid();
    fitCaseDetailsTables();
  }, 0);
  handleCaseDetailsTable();
})

$(document).on('pfAjaxComplete', function () {
  fitCaseDetailsTables();
});

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

let caseDetailsTableObserver;
function fitCaseDetailsTables() {
  if (!caseDetailsTableObserver) {
    caseDetailsTableObserver = new ResizeObserver(entries => {
      requestAnimationFrame(() => {
        entries.forEach(entry => fitCaseDetailsCardTable($(entry.target)));
      });
    });
  }
  $('.js-fit-card-height').closest('.grid-stack-item-content.case-detail-card').each(function () {
    caseDetailsTableObserver.observe(this);
    fitCaseDetailsCardTable($(this));
  });
}

function fitCaseDetailsCardTable(card) {
  card.find('.js-fit-card-height .ui-datatable-scrollable-body').each(function () {
    const tableBody = $(this);
    const cardContentBottom = card.offset().top + card.innerHeight() - parseFloat(card.css('padding-bottom'));
    const scrollableFooter = tableBody.siblings('.ui-datatable-scrollable-footer').outerHeight(true) || 0;
    const targetHeight = Math.max(0, Math.floor(cardContentBottom - tableBody.offset().top - scrollableFooter));
    if (Math.round(tableBody.height()) !== targetHeight) {
      tableBody.css({ 'height': targetHeight, 'max-height': targetHeight });
    }
    loadMoreCaseDetailsRowsWhenNotEnough(tableBody);
  });
}

function loadMoreCaseDetailsRowsWhenNotEnough(tableBody) {
  if (tableBody.get(0).scrollHeight > tableBody.get(0).clientHeight) {
    return;
  }
  const table = PrimeFaces.getWidgetById(tableBody.closest('.js-fit-card-height').attr('id'));
  if (table && table.cfg.liveScroll && table.shouldLiveScroll && !table.loadingLiveScroll && !table.allLoadedLiveScroll) {
    table.loadLiveRows();
  }
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
