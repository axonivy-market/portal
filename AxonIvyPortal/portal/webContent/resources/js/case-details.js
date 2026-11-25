var caseDetailsGrid;

const caseDetailsTableRowHeight = 53;
const minCaseDetailsTableChunkSize = 25;
const maxCaseDetailsTableChunkSize = 100;

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

function calculateCaseDetailsTableChunkSize() {
  const rows = Math.ceil(window.innerHeight / caseDetailsTableRowHeight) + 2;
  return Math.min(maxCaseDetailsTableChunkSize, Math.max(minCaseDetailsTableChunkSize, rows));
}

function addCaseDetailsTableChunkSize(config) {
  if (!config.params) {
    config.params = [];
  }
  config.params.push({name: 'chunkSize', value: calculateCaseDetailsTableChunkSize()});
}

function loadCaseDetailsTableFirstTime(loadingClass, containerClass, widgetVar) {
  setTimeout(function() {
    $('.' + loadingClass).addClass('hidden');
    $('.' + containerClass).removeClass('hidden');
    const table = PF(widgetVar);
    if (table) {
      table.renderDeferred();
    }
    resizeCaseDetailsTableBody();
  }, 50);
}

function getCaseDetailsCard(tableBody) {
  return tableBody.closest('.grid-stack-item-content.case-detail-card');
}

function getReservedHeightForCaseDetailsTableBody(tableBody) {
  const card = getCaseDetailsCard(tableBody);
  const widgetHeader = card.find('.portal-detail-widget-header').outerHeight(true) || 0;
  const scrollableHeader = tableBody.siblings('.ui-datatable-scrollable-header').outerHeight(true) || 0;
  const scrollableFooter = tableBody.siblings('.ui-datatable-scrollable-footer').outerHeight(true) || 0;
  return widgetHeader + scrollableHeader + scrollableFooter;
}

function updateCaseDetailsTableBodyHeight(tableBody) {
  const parentHeight = getCaseDetailsCard(tableBody).height();
  if (!parentHeight) {
    return;
  }

  const targetHeight = Math.max(0, Math.round(parentHeight - getReservedHeightForCaseDetailsTableBody(tableBody)));
  if (Math.round(tableBody.height()) !== targetHeight) {
    tableBody.css({'height': targetHeight, 'max-height': targetHeight});
  }
  loadMoreCaseDetailsRowsWhenNotEnough(tableBody);
}

let caseDetailsTableResizeObserver;
function resizeCaseDetailsTableBody() {
  if (!caseDetailsTableResizeObserver) {
    caseDetailsTableResizeObserver = new ResizeObserver(entries => {
      requestAnimationFrame(() => {
        // The body has a fixed pixel height, so a vertical resize of the card alone does not
        // resize the body. Both the card and its bodies are observed; collect the distinct
        // bodies affected and recalculate each one once.
        const tableBodies = new Set();
        entries.forEach(entry => {
          if (entry.target.classList.contains('case-detail-card')) {
            entry.target.querySelectorAll('.ui-datatable-scrollable-body').forEach(body => tableBodies.add(body));
          } else {
            tableBodies.add(entry.target);
          }
        });
        tableBodies.forEach(body => updateCaseDetailsTableBodyHeight($(body)));
      });
    });
  }
  document.querySelectorAll('.case-detail-card').forEach(card => {
    if (card.querySelector('.ui-datatable-scrollable-body')) {
      caseDetailsTableResizeObserver.observe(card);
    }
  });
  document.querySelectorAll('.case-detail-card .ui-datatable-scrollable-body').forEach(scrollableBody => {
    caseDetailsTableResizeObserver.observe(scrollableBody);
  });
}

function loadMoreCaseDetailsRowsWhenNotEnough(tableBody) {
  setTimeout(function() {
    const scrollableBody = tableBody.get(0);
    if (!scrollableBody || scrollableBody.scrollHeight > scrollableBody.clientHeight) {
      return;
    }

    const widgetName = getCaseDetailsCard(tableBody).find('.js-table-widget-var').val();
    if (!widgetName) {
      return;
    }

    const table = PF(widgetName);
    if (table && table.cfg.liveScroll && table.shouldLiveScroll && !table.loadingLiveScroll && !table.allLoadedLiveScroll) {
      table.loadLiveRows();
    }
  }, 200);
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
