var filterPanelId = 'widget-configuration-form:new-widget-configuration-component:widget-filter-content';
var filterDialogWidgetVar = 'widget-configuration-filter-dialog';

function closeFilterPanelWithValidation() {
  if (filterHasValidationError()) {
    return;
  }

  closeFilterPanel();
}

function closeFilterPanel() {
  PF(filterDialogWidgetVar).hide();
}

$(document).ready(function () {
  var dialog = document.getElementById(filterPanelId);
  var filterMessages = dialog ? dialog.querySelector('[id$=":filter-messages"]') : null;
  if (filterMessages != null && filterMessages.textContent.trim() !== "" && PF(filterDialogWidgetVar)) {
    PF(filterDialogWidgetVar).show();
  }
});

function filterHasValidationError() {
  var filterPanel = $(document.getElementById(filterPanelId));
  return filterPanel.find('[id $= ":filter-messages"]').find('.ui-messages-error').length != 0;
}
