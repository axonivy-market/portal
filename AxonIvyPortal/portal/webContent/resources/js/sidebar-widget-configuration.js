var filterPanelId = 'widget-configuration-form:new-widget-configuration-component:filter-panel';
function openFilterPanel(event) {
  event.stopPropagation();
  document.getElementById(filterPanelId).classList.add("active");
}

function closeFilterPanel() {
  document.getElementById(filterPanelId).style.right = "0px";
  document.getElementById(filterPanelId).classList.remove("active");
}



$(document).ready(function () {
  registerClickOnPanelEvent();

  document.getElementById(filterPanelId).style.right = "0px";
  let validationMsg = document.getElementById("widget-configuration-form:new-widget-configuration-component:filter-messages").textContent;
  if (validationMsg === ""){
    closeFilterPanel();
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
