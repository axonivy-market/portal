var filterPanelId = 'widget-configuration-form:new-widget-configuration-component:filter-panel';
function openFilterPanel(event) {
  console.log('click on button show filter');
  event.stopPropagation();
  document.getElementById(filterPanelId).classList.add("active");
}

function closeFilterPanel() {
    document.getElementById(filterPanelId).style.right = "0px";
    document.getElementById(filterPanelId).classList.remove("active");
}



$(document).ready(function () {
  registerClickOnPanelEvent();
//  document.getElementById('widget-configuration-form:new-widget-configuration-component:widget-preview')
//        .addEventListener('click', function (event) {
//           console.log('click on panel');
//           closeFilterPanel();
//        });
  document.getElementById(filterPanelId).style.right = "0px";
  let validationMsg = document.getElementById("widget-configuration-form:new-widget-configuration-component:filter-messages").textContent;
  if (validationMsg === ""){
    closeFilterPanel();
  }
  console.log(document.getElementById("widget-configuration-form:new-widget-configuration-component:filter-messages").textContent);
//  closeFilterPanel();
//  let filterPanel = document.getElementById(filterPanelId);
  
//    document.addEventListener('click', event => {
//        const isClickInside = filterPanel.contains(event.target);
//        if (!isClickInside) {
//          closeFilterPanel();  
//        } 
//    })
});

function registerClickOnPanelEvent(){
  document.getElementById('widget-configuration-form:new-widget-configuration-component:widget-preview')
        .addEventListener('click', function (event) {
           console.log('click on panel');
           closeFilterPanel();
        });
}
