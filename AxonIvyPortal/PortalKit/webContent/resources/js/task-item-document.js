$(function () {
  setTimeout(() => {
    responsiveTableInDocumentPanel();
  }, 0);
})

$(window).resize(() => {
  responsiveTableInDocumentPanel();
});

function responsiveTableInDocumentPanel() {
  let documentPanelElement = document.getElementById("task-details-document-panel");
  if (documentPanelElement) {
    responsiveATableInPanel(documentPanelElement);
  }
}
