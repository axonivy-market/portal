$(function () {
  setTimeout(() => {
    responsiveRelatedCasePanelHeader();
  }, 0);
})

function responsiveRelatedCasePanelHeader() {
  let relatedCasePanel = document.getElementById("case-details-technicalCase-panel");
  if (relatedCasePanel) {
    let relatedCaseColumnConfig = document.getElementById("related-case-column-config");
    // 770 is width of case detail related case panel that have enough size to contains link column config
    relatedCaseColumnConfig.style.display = relatedCasePanel.clientWidth < 770 ? "none" : "block";
    // 420 is width of case detail related case panel that have enough size to contains link export to excel
    var relatedCaseExportToExcelForm = $('[id$=":related-cases-widget:related-cases-form"]');
    relatedCaseExportToExcelForm[0].style.display = relatedCasePanel.clientWidth < 420 ? "none" : "block";
  }
}

$(window).resize(() => {
  responsiveRelatedCasePanelHeader();
});
