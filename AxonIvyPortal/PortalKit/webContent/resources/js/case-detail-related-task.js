$(function () {
  setTimeout(() => {
    responsiveRelatedTaskPanelHeader();
  }, 0);
})

function responsiveRelatedTaskPanelHeader() {
  let relatedTaskPanel = document.getElementById("case-details-relatedTask-panel");
  if (relatedTaskPanel) {
    let relatedTaskColumnConfig = document.getElementById("related-task-column-config");
    // 770 is width of case detail related task panel that have enough size to contains link column config
    relatedTaskColumnConfig.style.display = relatedTaskPanel.clientWidth < 770 ? "none" : "block";
    // 420 is width of case detail related task panel that have enough size to contains link export to excel
    var relatedTaskExportToExcelForm = $('[id$=":task-widget:task-export-to-excel-form"]');
    relatedTaskExportToExcelForm[0].style.display = relatedTaskPanel.clientWidth < 420 ? "none" : "block";
  }
}

$(window).resize(() => {
  responsiveRelatedTaskPanelHeader();
});
