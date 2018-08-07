$(document).ready(scrollToSelectedCaseItem);

function scrollToSelectedCaseItem() {
  var expandedCaseItem = document.getElementsByClassName('mod-expanded')[0];
  if (expandedCaseItem) {
    expandedCaseItem.scrollIntoView(false);
  }
}
