var filterPanelId = 'widget-configuration-form:new-widget-configuration-component:widget-filter-content';

function openFilterPanel(event) {
  document.getElementById(filterPanelId).style.right = calculateRightPosition();
  event.stopPropagation();
  document.getElementById(filterPanelId).classList.add("active");
}

function closeFilterPanel() {
  document.getElementById(filterPanelId).style.right = calculateRightPosition();
  document.getElementById(filterPanelId).classList.remove("active");
}

$(document).ready(function () {
  registerClickOnPanelEvent();

  document.getElementById(filterPanelId).style.right = calculateRightPosition();
  
  let validationMsg = document.getElementById("widget-configuration-form:new-widget-configuration-component:filter-messages").textContent;
  if (validationMsg !== ""){
    document.getElementById(filterPanelId).classList.add("active");
  }
});

function registerClickOnPanelEvent(){
  document.getElementById('widget-configuration-form:new-widget-configuration-component:widget-preview')
  .addEventListener('click', function (event) {
    if (document.getElementById(filterPanelId).offsetWidth > 0){
      closeFilterPanel();
      document.getElementById("widget-configuration-form:new-widget-configuration-component:preview-button").click();
    }
  });
}

function calculateRightPosition(){
  var x = document.getElementById('new-widget-configuration-dialog_content').clientWidth;
  var y = document.getElementById('widget-configuration-form:new-widget-configuration-component:widget-preview').offsetWidth;
  
  document.getElementById(filterPanelId).style.right = (x > y ? 0 : x-y-20) + "px";
}
