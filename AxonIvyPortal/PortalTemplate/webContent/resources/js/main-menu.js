var mainMenuMode;
var mainMenuSwitcherButton;

function updateMainMenuMode() {
  mainMenuMode = localStorage.getItem("portal-main-menu-mode");
}

function toggleMainMenuMode() {
  updateMainMenuMode();
  localStorage.setItem('portal-main-menu-mode', getReversedState(mainMenuMode));
}

function getReversedState(state) {
  if (state == "expanded") {
    return "collapsed";
  } else {
    return "expanded";
  }
}

$(function() {
  mainMenuSwitcherButton = $('#js-main-menu-switcher');
  updateMainMenuMode();
  if (mainMenuMode == "expanded") {
    mainMenuSwitcherButton.addClass('bt-menu-open bt-menu-trigger');
  } else {
    $('#js-main-menu-panel').hide();
    PF('portal-page-layout').hide('west');
    mainMenuSwitcherButton.addClass('bt-menu-trigger');
  }

  mainMenuSwitcherButton.click(function() {
    mainMenuSwitcherButton.toggleClass('bt-menu-open');
    PF('portal-page-layout').toggle('west');
    toggleMainMenuMode();
  });

  var menuItemElements = $("#js-main-menu-panel tbody tr:not(.default)").get();
  removeExpandedMenuItemsNotIn(menuItemElements);
  recoverExpandedMenuItems(menuItemElements);

  isDocumentReady = true;
});

var isDocumentReady = false;

$(document).on('click', '#js-main-menu-panel tbody tr:not(.default) span.ui-treetable-toggler', function() {
  if (isDocumentReady) {
    var node = createNode(this);
    var isExpanded = true;
    $.each(expandedMenuItems, function(index, item) {
      if (item === node) {
        isExpanded = false;
      }
    });
    if (isExpanded) {
      onNodeExpand(node)
    } else {
      onNodeCollapse(node);
    }
  }
});

var expandedMenuItems = localStorage.getItem("expanded-menu-items") !== null ? JSON.parse(localStorage
    .getItem("expanded-menu-items")) : ["Root"];

function onNodeExpand(node) {
  var reversedMenuItems = expandedMenuItems.slice(0).reverse();
  $.each(reversedMenuItems, function(index, item) {
    if (node.startsWith(item)) {
      if (index === 0) {
        reversedMenuItems.unshift(node);
      } else {
        reversedMenuItems.splice(index - 1, 0, node);
      }
      return false;
    }
  });
  expandedMenuItems = reversedMenuItems.reverse();

  updateExpandedItemsToLocalStorage();
}

function onNodeCollapse(node) {
  for (var i = 0; i < expandedMenuItems.length; i++) {
    if (expandedMenuItems[i].startsWith(node)) {
      expandedMenuItems.splice(i, 1);
      i--;
    }
  }
  updateExpandedItemsToLocalStorage();
}

function createNode(targetElement) {
  var categoryMenuItem = $(targetElement).parents("tr:not(.default)");
  var node = "Root/" + getCategoryStructure(categoryMenuItem);
  return node;
}

function updateExpandedItemsToLocalStorage() {
  localStorage.setItem("expanded-menu-items", JSON.stringify(expandedMenuItems));
}

function removeExpandedMenuItemsNotIn(menuItemElements) {
  // i = 1: Not iterate to root node
  for (var i = 1; i < expandedMenuItems.length; i++) {
    var contains = false;
    $.each(menuItemElements, function(index, item) {
      var itemLevel = $(item).attr("data-rk");
      var hasChildren = $(item).next().attr("data-rk").startsWith(itemLevel);

      if (expandedMenuItems[i] === "Root/" + getCategoryStructure(item) && hasChildren) {
        contains = true;
        return false;
      }
    });

    if (!contains) {
      expandedMenuItems.splice(i, 1);
      updateExpandedItemsToLocalStorage();
      i--;
    }
  }
}

function recoverExpandedMenuItems(menuItemElements) {
  $.each(menuItemElements.reverse(), function(index, item) {
    var contains = false;
    $.each(expandedMenuItems, function(expandedIndex, expandedItem) {
      if (expandedItem === "Root/" + getCategoryStructure(item)) {
        contains = true;
        return false;
      }
    });

    if (!contains) {
      $(item).find("span.ui-icon-triangle-1-s").click();
    }
  });
}

function getCategoryStructure(element) {
  var theClass = $(element).attr("class");
  var category = /(Tasks|Cases)[^\s\\]*\b/.exec(theClass)[0];
  return category;
}

if (!String.prototype.startsWith) {
  String.prototype.startsWith = function(searchString, position) {
    position = position || 0;
    return this.indexOf(searchString, position) === position;
  };
}