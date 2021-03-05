debugger;

Object.values(document.styleSheets).forEach(sheet => {
  console.log('hoan: ' + sheet.href);
});

var iconsStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("streamlineicons.css"))[0];
var faIconsStylesheet = Object.values(re).filter(sheet => sheet.href?.includes("font-awesome.css"))[0];
var icons = Object.values(iconsStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".si-"));
icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

var element = document.getElementById("icons");
element.innerHTML = '';

icons.forEach(icon => {
  var iconClass = icon.selectorText.substring(1, icon.selectorText.length - 8);

  var iconImage = document.createElement("i");
  iconImage.className = "si " + iconClass;

  var iconTitle = document.createElement("p");
  iconTitle.appendChild(document.createTextNode("si " + iconClass));
  
  var iconDiv = document.createElement("div");
  iconDiv.className = "p-col icon-block";
  iconDiv.appendChild(iconImage)
  iconDiv.appendChild(iconTitle);
  element.appendChild(iconDiv);
});

function search() {
  var filter = document.querySelector("#search input").value;
  console.log(filter)
  document.querySelectorAll(".icon-block").forEach(i => {
    if (i.querySelector("p").textContent.includes(filter)) {
      i.style.display = "block";
    }
    else {
      i.style.display = "none";
    }
  });
}
