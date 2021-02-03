// Define CaseWidget for sorting event callback
var caseWidget = new CaseWidget();

/* For opening ProcessHistory component in a dialog
    - Check the case list data then hide the header if data empty
    - Setup table header by call CaseWidget.setupHeader()
*/
var processHistory = {
  setup : function(emptyRow) {
    this.hideListIfEmptyCase(emptyRow);
    // Setup case widget header
    caseWidget.setupHeader();
  },

  hideListIfEmptyCase : function(emptyRow) {
    if(emptyRow) {
      $header = $('.js-case-widget-column-header');
      $list = $('.process-history-list');
      $header.hide();
      $list.hide();
    }
  },

  // For binding event of toggle menu
  updateLayoutWithAnimation : function() {
    setupCaseHeader();
  }
}

Portal.updateLayoutContent();
setupCaseHeader();

var resizeTimer;
// Update screen when window size is changed
$(window).resize(function() {
  Portal.updateLayoutContent();

  clearTimeout(resizeTimer);
  resizeTimer = setupCaseHeader();
});

function setupCaseHeader() {
  // Wait for Main layout is updated, then run setup header
  setTimeout(function() {
    caseWidget.setupHeader();
  }, 250);
}

// Add a ScreenHandler listener to Portal Menu event
MainMenu.init(processHistory);