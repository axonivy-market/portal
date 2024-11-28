var filterPanelId = 'widget-configuration-form:new-widget-configuration-component:widget-filter-content';

function openFilterPanel(event) {
  event.stopPropagation();
  var filterPanel = document.getElementById(filterPanelId);
  filterPanel.classList.add("active");
  $('#new-widget-configuration-dialog').find('#widget-configuration-save-button').hide();
  calculateFilterPaneltPosition();
}

function closeFilterPanelWithValidation() {
  if (filterHasValidationError()) {
    return;
  }

  closeFilterPanel();
}

function closeFilterPanel() {
  $('#new-widget-configuration-dialog').find('#widget-configuration-save-button').show();
  document.getElementById(filterPanelId).classList.remove("active");
}

$(document).ready(function () {
  registerClickOnPanelEvent();

  calculateFilterPaneltPosition();
  
  let validationMsg = document.getElementById("widget-configuration-form:new-widget-configuration-component:filter-messages").textContent;
  if (validationMsg !== ""){
    document.getElementById(filterPanelId).classList.add("active");
  }
});

function registerClickOnPanelEvent(){
  document.getElementById('widget-configuration-form:new-widget-configuration-component:widget-preview')
  .addEventListener('click', function (event) {

    if (document.getElementById(filterPanelId).offsetWidth > 0){
      document.getElementById("widget-configuration-form:new-widget-configuration-component:preview-button").click();
    }
  });
}

function filterHasValidationError() {
  var filterPanel = $(document.getElementById(filterPanelId));
  return filterPanel.find('[id $= ":filter-messages"]').find('.ui-messages-error').length != 0;
}

function calculateFilterPaneltPosition(){
  var newWidgetDialog = document.getElementById('new-widget-configuration-dialog');
  var filterPanel = document.getElementById(filterPanelId);

  filterPanel.style.right = newWidgetDialog.style.left;
  filterPanel.style.top = newWidgetDialog.style.top;
  filterPanel.style.height = newWidgetDialog.clientHeight + 'px';
}
