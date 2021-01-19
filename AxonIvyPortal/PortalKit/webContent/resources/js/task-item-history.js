$(function () {
  setTimeout(() => {
    responsiveTableInHistoryPanel();
  }, 0);
})

$(window).resize(() => {
  responsiveTableInHistoryPanel();
});

function responsiveTableInHistoryPanel() {
  let historyPanelElement = document.getElementById("task-details-history-panel");
  if (historyPanelElement) {
    responsiveATableInPanel(historyPanelElement);
  }
}
