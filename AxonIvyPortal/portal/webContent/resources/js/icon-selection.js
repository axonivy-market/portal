function updateSelectedIconDisplay(iconCode, type, clientId) {
  var iconDisplayJQ = $(PrimeFaces.escapeClientId(clientId + ":current-icon-display"));
  updateIconDisplay(iconDisplayJQ, iconCode, type);
  var iconDisplayForLink = $(PrimeFaces.escapeClientId(clientId + ":selected-icon-display"));
  updateIconDisplay(iconDisplayForLink, iconCode, type);

  var iconHiddenValueJQ = $(PrimeFaces.escapeClientId(clientId + ":selected-icon-hidden-value"));
  iconHiddenValueJQ.val(iconCode);
}

function updateIconDisplay(iconDisplayJQ, iconCode, type) {
  if (iconDisplayJQ === undefined || iconDisplayJQ.length == 0) {
    return;
  }
  iconDisplayJQ.removeClass();
  iconDisplayJQ.toggleClass(type === "awesome" ? "fa fa-2x" : "si si-lg");
  iconDisplayJQ.toggleClass("vertical-align-middle");
  iconDisplayJQ.addClass(iconCode);
}

function resetIconArrayStyle(arrayIconContainer) {
  if(arrayIconContainer) {
    $(arrayIconContainer).find('a').addClass('opacity-40');
    $(arrayIconContainer).find('a').removeClass('highlight-selected-icon');
  }
}

function highlightSelectedIcon(iconEl) {
  if(iconEl) {
    $(iconEl).removeClass('opacity-40');
    $(iconEl).addClass('highlight-selected-icon');
  }
}

function createAwesomeTag(iconCode, clientId) {
    var container = document.getElementById(clientId + ":custom-array-icon");
    if(container) {
      resetIconArrayStyle(container);
      var iconImage = document.createElement("span");
      iconImage.className = "fa " + iconCode;

      var iconAnchor = document.createElement("a");
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color highlight-selected-icon";
      iconAnchor.appendChild(iconImage);
      iconAnchor.title = iconCode;
      iconAnchor.onclick = function() {
        updateSelectedIconDisplay(iconCode, "awesome", clientId);
        resetIconArrayStyle(container);
        highlightSelectedIcon(this);
      };
      $(container).prepend(iconAnchor);
      $(container).children().last().remove()
    }
}

function reoderSelectedIconOnTop(icons, selectedIconCode, clientId) {
  icons.forEach(function(icon,i){
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    if(iconCode === selectedIconCode){
      icons.splice(i, 1);
      icons.unshift(icon);
    }
  });
  var container = document.getElementById(clientId + ":custom-array-icon");
  var numberVisibleIcon = Math.floor($(container).width()/32);
  icons.splice(numberVisibleIcon, icons.length - numberVisibleIcon);
}

function loadDialogStreamlineIcon(clientId) {
  var iconsStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("StreamlineIcons.css"))[0];
  var icons = Object.values(iconsStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".si-"));
  icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

  var selectionIconDialogId = clientId + "-select-icon-dialog";
  var container = document.getElementById(clientId + ":icons-selection-form:icons");
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "si " + iconCode + " si-lg";

    var iconAnchor = document.createElement("a");
    iconAnchor.className = "icon-selection-dialog-selecting-icon icon-color";
    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "streamline", clientId);
      PF(selectionIconDialogId).hide();
      reloadArrayStreamlineIcon(clientId, iconCode);
      return false;
    };
    
    container.appendChild(iconAnchor);
  });
  
  var searchField = document.getElementById(clientId + ":search-icon-name-field");
  searchIconByName(searchField);
  PF(selectionIconDialogId).initPosition();
}

function reloadArrayStreamlineIcon(clientId, currentIconCode) {
  var container = document.getElementById(clientId + ":custom-array-icon");
  if(container) {
    var iconsStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("StreamlineIcons.css"))[0];
    var icons = Object.values(iconsStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".si-"));
    reoderSelectedIconOnTop(icons, currentIconCode, clientId);
    container.innerHTML = "";
    icons.forEach((icon, index) => {
      var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
      var iconImage = document.createElement("i");
      iconImage.className = "si " + iconCode;
      
      var iconAnchor = document.createElement("a");
      if(currentIconCode == iconCode) {
        iconAnchor.className = "icon-selection-array-selecting-icon icon-color highlight-selected-icon";
      } else {
        iconAnchor.className = "icon-selection-array-selecting-icon icon-color opacity-40";
      }

      iconAnchor.appendChild(iconImage);
      iconAnchor.title = iconCode;

      iconAnchor.onclick = function() {
        updateSelectedIconDisplay(iconCode, "streamline", clientId);
        resetIconArrayStyle(container);
        highlightSelectedIcon(this);
      };
      container.appendChild(iconAnchor);
    });
  }
};

function loadIconsFirstTime(hideIconArray, clientId, currentIcon) {
  if(hideIconArray == true) {
    return;
  }
  reloadArrayStreamlineIcon(clientId, currentIcon);
  if(currentIcon.includes('fa-')) {
    createAwesomeTag(currentIcon, clientId);
  }
}