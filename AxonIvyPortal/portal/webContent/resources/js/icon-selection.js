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
  if (type === "awesome") {
    iconDisplayJQ.toggleClass("fa fa-2x");
  } else if (type === "tabler-filled") {
    iconDisplayJQ.toggleClass("tif");
  } else if (type === "streamline") {
    iconDisplayJQ.toggleClass("si");
  } else {
    iconDisplayJQ.toggleClass("ti");
  }
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

function loadDialogTablerIconAll(clientId) {
  loadDialogTablerIcon(clientId);
  loadDialogTablerFilledIcon(clientId);
  loadDialogStreamlineIcon(clientId);

  var searchField = document.getElementById(clientId + ":search-icon-name-field");
  searchIconByName(searchField);
  var selectionIconDialogId = clientId + "-select-icon-dialog";
  PF(selectionIconDialogId).initPosition();
}

function loadDialogTablerIcon(clientId) {
  var outlineStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("tabler-icons.min.css") && !sheet.href?.includes("filled"))[0];
  if (!outlineStylesheet) return;
  var icons = Object.values(outlineStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".ti-") && rule.selectorText?.endsWith("::before"));
  icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

  var selectionIconDialogId = clientId + "-select-icon-dialog";
  var container = document.getElementById(clientId + ":icons-selection-form:tabler-icons");
  if (!container) return;
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "ti " + iconCode + " ti-lg";

    var iconAnchor = document.createElement("a");
    iconAnchor.className = "icon-selection-dialog-selecting-icon icon-color";
    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "tabler", clientId);
      reloadArrayTablerIcon(clientId, iconCode);
      PF(selectionIconDialogId).hide();
      return false;
    };

    container.appendChild(iconAnchor);
  });
}

function loadDialogTablerFilledIcon(clientId) {
  var filledStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("tabler-icons-filled.min.css"))[0];
  if (!filledStylesheet) return;
  var icons = Object.values(filledStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".tif-") && rule.selectorText?.endsWith("::before"));
  icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

  var selectionIconDialogId = clientId + "-select-icon-dialog";
  var container = document.getElementById(clientId + ":icons-selection-form:tabler-filled-icons");
  if (!container) return;
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "tif " + iconCode + " ti-lg";

    var iconAnchor = document.createElement("a");
    iconAnchor.className = "icon-selection-dialog-selecting-icon icon-color";
    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "tabler-filled", clientId);
      reloadArrayTablerFilledIcon(clientId, iconCode);
      PF(selectionIconDialogId).hide();
      return false;
    };

    container.appendChild(iconAnchor);
  });
}

function loadDialogStreamlineIcon(clientId) {
  var streamlineStylesheet = Object.values(document.styleSheets).filter(sheet => sheet.href?.includes("streamline"))[0];
  if (!streamlineStylesheet) return;
  var icons;
  try {
    icons = Object.values(streamlineStylesheet.rules).filter(rule => rule.selectorText?.startsWith(".si-") && rule.selectorText?.endsWith("::before"));
  } catch(e) {
    return;
  }
  if (!icons || icons.length === 0) return;
  icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);

  var selectionIconDialogId = clientId + "-select-icon-dialog";
  var container = document.getElementById(clientId + ":icons-selection-form:streamline-icons");
  if (!container) return;
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "si " + iconCode;

    var iconAnchor = document.createElement("a");
    iconAnchor.className = "icon-selection-dialog-selecting-icon icon-color";
    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "streamline", clientId);
      reloadArrayStreamlineIcon(clientId, iconCode);
      PF(selectionIconDialogId).hide();
      return false;
    };

    container.appendChild(iconAnchor);
  });
}

function reloadArrayStreamlineIcon(clientId, currentIconCode) {
  var container = document.getElementById(clientId + ":custom-array-icon");
  if (!container) return;
  var streamlineStylesheet = Object.values(document.styleSheets).filter(
    sheet => sheet.href?.includes("streamline")
  )[0];
  if (!streamlineStylesheet) return;
  var icons;
  try {
    icons = Object.values(streamlineStylesheet.rules).filter(
      rule => rule.selectorText?.startsWith(".si-") && rule.selectorText?.endsWith("::before")
    );
  } catch(e) {
    return;
  }
  reoderSelectedIconOnTop(icons, currentIconCode, clientId);
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "si " + iconCode;

    var iconAnchor = document.createElement("a");
    if (currentIconCode === iconCode) {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color highlight-selected-icon";
    } else {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color opacity-40";
    }

    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "streamline", clientId);
      resetIconArrayStyle(container);
      highlightSelectedIcon(this);
    };
    container.appendChild(iconAnchor);
  });
}

function reloadArrayTablerIcon(clientId, currentIconCode) {
  var container = document.getElementById(clientId + ":custom-array-icon");
  if (!container) return;
  var outlineStylesheet = Object.values(document.styleSheets).filter(
    sheet => sheet.href?.includes("tabler-icons.min.css") && !sheet.href?.includes("filled")
  )[0];
  if (!outlineStylesheet) return;
  var icons = Object.values(outlineStylesheet.rules).filter(
    rule => rule.selectorText?.startsWith(".ti-") && rule.selectorText?.endsWith("::before")
  );
  reoderSelectedIconOnTop(icons, currentIconCode, clientId);
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "ti " + iconCode;

    var iconAnchor = document.createElement("a");
    if (currentIconCode === iconCode) {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color highlight-selected-icon";
    } else {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color opacity-40";
    }

    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "tabler", clientId);
      resetIconArrayStyle(container);
      highlightSelectedIcon(this);
    };
    container.appendChild(iconAnchor);
  });
};

function reloadArrayTablerFilledIcon(clientId, currentIconCode) {
  var container = document.getElementById(clientId + ":custom-array-icon");
  if (!container) return;
  var filledStylesheet = Object.values(document.styleSheets).filter(
    sheet => sheet.href?.includes("tabler-icons-filled.min.css")
  )[0];
  if (!filledStylesheet) return;
  var icons = Object.values(filledStylesheet.rules).filter(
    rule => rule.selectorText?.startsWith(".tif-") && rule.selectorText?.endsWith("::before")
  );
  reoderSelectedIconOnTop(icons, currentIconCode, clientId);
  container.innerHTML = "";
  icons.forEach(icon => {
    var iconCode = icon.selectorText.substring(1, icon.selectorText.length - 8);
    var iconImage = document.createElement("i");
    iconImage.className = "tif " + iconCode;

    var iconAnchor = document.createElement("a");
    if (currentIconCode === iconCode) {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color highlight-selected-icon";
    } else {
      iconAnchor.className = "icon-selection-array-selecting-icon icon-color opacity-40";
    }

    iconAnchor.appendChild(iconImage);
    iconAnchor.title = iconCode;
    iconAnchor.tabindex = "0";
    iconAnchor.onclick = function() {
      updateSelectedIconDisplay(iconCode, "tabler-filled", clientId);
      resetIconArrayStyle(container);
      highlightSelectedIcon(this);
    };
    container.appendChild(iconAnchor);
  });
}

function loadIconsFirstTime(hideIconArray, clientId, currentIcon) {
  if(hideIconArray == true) {
    return;
  }
  if(currentIcon.includes('si-')) {
    reloadArrayStreamlineIcon(clientId, currentIcon);
  } else if (currentIcon.includes('tif-')) {
    reloadArrayTablerFilledIcon(clientId, currentIcon);
  } else {
    reloadArrayTablerIcon(clientId, currentIcon);
  }
  if(currentIcon.includes('fa-')) {
    createAwesomeTag(currentIcon, clientId);
  }
}