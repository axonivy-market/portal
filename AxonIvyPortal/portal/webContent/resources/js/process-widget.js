var prefixProcessGroup = "js-process-group-";
var prefixProcessStart = "js-process-starts-with-";
var numberOfMaximumCharacters = 26; // There are 24 alphabet characters, one "#" character, and expand/collapse icon.

function ProcessWidget() {

  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }

  return {
    setup : function() {
      this.clearSearchField();
      this.setupScrollbar();
      expandOrCollapseAllCategories(true);
    },

    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    // setup scroll-bar for process list
    setupScrollbar : function() {
      // Check current page, if called from compact widget then don't need to proceed
      if ($('.js-compact-process-widget').length) {
        return;
      }
      this.removeStyleOnProcessStartList();
      var processWidgetMarginPadding = ($('.js-process-widget').outerHeight(true)||0) - ($('.js-process-widget').height()||0);
      var processsHeader = $('.js-process-header');
      var processStartListContainer = $('.js-process-start-list-container');

      var announcementMessageContainer = $('.js-announcement-message');
      var mainScreenHeight = PortalLayout.getAvailableHeight();
      var availableHeight = mainScreenHeight - (announcementMessageContainer.outerHeight(true)||0)
                            - processWidgetMarginPadding - (processsHeader.outerHeight(true)||0);

      if (PortalGlobalSearch.isSearchPageOpened()) {
        availableHeight = availableHeight - PortalGlobalSearch.getAvailableHeight(':process-tab');
      } else {
        if (this.countProcessHeight() > availableHeight) {
          PortalLayout.removeLayoutContentPaddingBottom();
        }
      }

      if (!!availableHeight) {
        availableHeight = availableHeight - PortalLayout.getYPaddingLayoutContent();
        processStartListContainer.outerHeight(availableHeight);
        this.setupProcessNav(processStartListContainer, availableHeight, announcementMessageContainer);
      }
      processStartListContainer.on("scroll", function() {
        $(".js-process-nav-item.selected").removeClass("selected");
      });
    },

    // setup scroll-bar for process navigator
    setupProcessNav : function(processStartListContainer, availableHeight, announcementMessageContainer) {
      var processNav = $('.js-process-nav');
      if (!shouldDisplayProcessNav(processNav, availableHeight)) {
        return;
      }

      // For process list page
      var layoutContent = $('.js-layout-content');
      var processWidget = $('.js-process-widget');
      marginRightProcessWidget = extractRightOffsetOfElement(layoutContent) + extractRightOffsetOfElement(processWidget);
      processStartListContainer.css("width", "calc(100% + " + marginRightProcessWidget + "px)");
      processNav.outerHeight(availableHeight, true);
      
      let processPaddingTop = parseInt($('.js-process-widget').css('padding-top'));
      var availableHeightProcessNavTop = ($('.js-process-header').outerHeight(true)||0) + processPaddingTop;
      processNav.css("top", availableHeightProcessNavTop + "px");

      var numberOfDisplayingCharacters = $('.js-process-nav-item').length;
      let characterContainer = processNav.find('.js-character-container');
      let characterContainerPadding = parseInt(characterContainer.css('padding-top')) + parseInt(characterContainer.css('padding-bottom'));
      processNav.width(characterContainer.width());
      processStartListContainer.css('padding-right', characterContainer.width() + marginRightProcessWidget + 'px');

      // If there is less than 6 characters displayed, calculate height of character container to make UI look better.
      if (numberOfDisplayingCharacters < 6) {
        var heightForEachCharacter = (processNav.get(0).offsetHeight / numberOfMaximumCharacters) * 2;
        var numberOfDisplayingCharacters = $('.js-process-nav-item').length;
        characterContainer.height((numberOfDisplayingCharacters * heightForEachCharacter) - characterContainerPadding);
      } else {
        characterContainer.height(processNav.get(0).offsetHeight - characterContainerPadding);
      }
      $(".js-process-nav-item.selected").removeClass("selected");
    },

    filter : function() {
      // Check current page, if called from compact widget then don't need to proceed
      if ($('.js-compact-process-widget').length) {
        return;
      }

      $(".js-process-nav-item.selected").removeClass("selected");
      var processItems = $('.js-process-start-list-item');
      $(processItems).show();
      $.each(processItems, function(index) {
        var processName = $('.js-process-start-list-item-name', this).text().toLowerCase();
        var keyword = $('.js-filter-process-widget-list-item').val().toLowerCase();
        let descriptionTooltip = $('.js-process-start-list-item-description .ui-tooltip-text');
        if (descriptionTooltip.length > 0) {
          var processDescription = descriptionTooltip.get(index).innerText.toLowerCase();
          if (!contain(processName, keyword) && !contain(processDescription, keyword)) {
            $(this).hide();
          }
        }

        var externalLinkKeyToSearch = "link";
        if (externalLinkKeyToSearch.toLowerCase() === keyword.trim().toLowerCase()
        		&& $(this.children).hasClass("js-external-link-process-item")) {
        	$(this).show();
        }
        
        var categoryElement = $('.js-process-category', this);
        if (categoryElement.length > 0
          && contain(categoryElement[0].textContent.trim().toLowerCase(), keyword.trim().toLowerCase())) {
          $(this).show();
        }
      });

      var processAlphabetGroups = $('.js-process-index-group');
      $(processAlphabetGroups).show();
      expandOrCollapseAllCategories(true);
      $.each(processAlphabetGroups, function() {
        if($(this).find('.ui-icon-plusthick').length) {
          $(this).find('div.ui-fieldset-content').show();
          $(this).find('.ui-icon-plusthick').toggleClass('ui-icon-plusthick ui-icon-minusthick');
        }
        if($(this).find('.js-process-start-list-item:visible').length === 0) {
          $(this).hide();
        }
      });

      var noFoundProcesses = $('.js-no-found-processes');
      var expandCollapseButtons = $('.expand-collapse-btns');
      if (!$(processItems).is(":visible")) {
        $(noFoundProcesses).removeClass('hidden');
        expandCollapseButtons.addClass('hidden');
      } else {
        $(noFoundProcesses).addClass('hidden');
        expandCollapseButtons.removeClass('hidden');
      }

      if (document.getElementsByClassName('process-nav').length !== 0) {
        disableGroupNavigation();
      }
      this.setupScrollbar();
    },

    hideLoadingText: function() {
      $(".js-loading-process-list").parent().addClass("hidden");
      $(".js-process-start-list-container").removeClass("u-invisibility");
      $(".js-process-nav").removeClass("u-invisibility");
    },

    removeStyleOnProcessStartList() {
      var startList = $('.js-process-start-list-container');
      if (startList.length > 0) {
        startList.removeAttr('style');
      }
    },

    countProcessHeight() {
      var compactProcesses = $(".js-process-index-group");
      if (compactProcesses.length > 0) {
        return $(compactProcesses[0]).outerHeight() * compactProcesses.length;
      }

      var processStartItems = $(".js-process-start-list-item");
      if (processStartItems.length > 0) {
        return $(processStartItems[0]).outerHeight() * processStartItems.length;
      }
      return 0;
    }
  };
}

// Calculate a minimum height for process navigator
// Then compare with availableHeight of process widget body
// Return true if enough space to display navigator
function shouldDisplayProcessNav(processNav, availableHeight) {
  if (processNav.find('.js-character-container').length) {
    var processNavElement = processNav.find('.js-character-container')[0];
    if (!processNavElement.childElementCount) {
      processNav.css("visibility", "hidden");
      return false;
    }

    var processNavIndexHeight = $(processNavElement.firstElementChild).outerHeight(true);
    var minProcessNavHeight = processNavIndexHeight * processNavElement.childElementCount;
    if (availableHeight < minProcessNavHeight) {
      processNav.css("visibility", "hidden");
      return false;
    }

    processNav.css("visibility", "visible");
    return true;
  }
}

// Finding the offset-right of element
// Offset-right is total of padding right and margin right of element
function extractRightOffsetOfElement(containerElement) {
  var paddingRight = $(containerElement).css('padding-right');
  if (!!paddingRight) {
    paddingRight = parseInt(paddingRight);
  }
  var marginRight = $(containerElement).css('margin-right');
  if (!!marginRight) {
    marginRight = parseInt(marginRight);
  }
  return paddingRight + marginRight;
}

$(document).ready(function() {
  var processWidget = ProcessWidget();
  processWidget.filter();
});

$(window).resize(function() {
  var processWidget = ProcessWidget();
  processWidget.setupScrollbar();
});

function expandOrCollapseAllCategories(shouldExpand) {
  var allFieldsets = $("fieldset[id$='alphabet-process-index-fieldset']");
  allFieldsets.each(function() {
    var categoryButton;

    if(shouldExpand && $(this).find('.ui-icon-plusthick').length) {
      categoryButton = $(this).find("span[class*='ui-fieldset-toggler']");
      $(categoryButton).click();
    }

    if(!shouldExpand && $(this).find('.ui-icon-minusthick').length) {
      categoryButton = $(this).find("span[class*='ui-fieldset-toggler']");
      $(categoryButton).click();
    }
  });
  
  if (shouldExpand) {
    $('[id $= "expand-all-btn"]').hide();
    $('[id $= "collapse-all-btn"]').show();
  } else {
    $('[id $= "expand-all-btn"]').show();
    $('[id $= "collapse-all-btn"]').hide();
  }
}

function jumpToProcessGroupByCharacter(event, isCompactMode) {
  if (isCompactMode === false) {
    jumpToProcessGroupByCharacterForGridProcess(event);
    return;
  }

  var clickedCharacter = getClassNameStartsWith(event.target.className, prefixProcessStart).slice(prefixProcessStart.length);
  $(".js-process-nav-item.selected").removeClass("selected");
  var selectedItem = document.getElementById(event.target.id);
  var processGroupSeleted = document.getElementsByClassName(prefixProcessGroup + clickedCharacter)[0];
  
  processGroupSeleted.parentNode.scrollTop = processGroupSeleted.offsetTop - processGroupSeleted.parentNode.offsetTop;
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 100);
}

function resetGridViewProcesses(event) {
  let processList = $('.js-process-start-list-item.js-grid-process-index-group');
  processList.show();
  $(".js-process-nav-item.selected").removeClass("selected");
  let selectedItem = document.getElementById(event.target.id);
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 100);

  let processWidget = new ProcessWidget();
  processWidget.setupScrollbar();
  processWidget.clearSearchField();
}

function jumpToProcessGroupByCharacterForGridProcess(event) {
  let processList = $('.js-process-start-list-item.js-grid-process-index-group');
  processList.show();

  let clickedCharacter = getClassNameStartsWith(event.target.className, prefixProcessStart).slice(prefixProcessStart.length);
  $(".js-process-nav-item.selected").removeClass("selected");
  let selectedItem = document.getElementById(event.target.id);
  let processGroupSeleted = document.getElementsByClassName(prefixProcessGroup + clickedCharacter);

  processList.not(processGroupSeleted).hide();
  let processWidget = new ProcessWidget();
  processWidget.setupScrollbar();
  processWidget.clearSearchField();
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 100);
}

function getClassNameStartsWith(classList, prefix) {
  return $.grep(classList.split(" "), function(className) {
    return className.indexOf(prefix) === 0;
  }).join();
}

function disableGroupNavigation() {
  var processIndexGroups = document.getElementsByClassName("js-process-index-group");

  var hiddenIndexGroups = getElementsHaveClassName(processIndexGroups, false);
  hiddenIndexGroups.forEach(function(e) {
    var indexNav = getClassNameStartsWith(e.className, prefixProcessGroup).slice(prefixProcessGroup.length);
    var hidden = document.getElementsByClassName(prefixProcessStart + indexNav)[0];
    hidden.classList.add('disabled');
  });

  var displayedIndexGroups = getElementsHaveClassName(processIndexGroups, true);
  displayedIndexGroups.forEach(function(e) {
    var indexNav = getClassNameStartsWith(e.className, prefixProcessGroup).slice(prefixProcessGroup.length);
    var hidden = document.getElementsByClassName(prefixProcessStart + indexNav)[0];
    if ($(hidden).hasClass('disabled')) {
      hidden.classList.remove('disabled');
    }
  });
}

function getElementsHaveClassName(displayedFieldSets, invert) {
  return $.grep(displayedFieldSets, function(fieldSet) {
    return fieldSet.style.display === "none";
  }, invert);
}

var compactProcessWidgetClass = '.js-compact-process-widget-panel';
var processStartItemClass = '.js-process-start-list-item';

function showLoadingText() {
  $('.js-loading-text').show();
}

function hideLoadingText() {
  $('.js-loading-text').hide();
}

function hideExtendLinkPreviewLabel() {
  $('span.ui-external-link-preview-image-label').css('display', 'none');
}

function handleUploadFileFail() {
  let errorMessageElement = document.getElementById('error-message');
  let uploadErrorElement = document.getElementsByClassName('ui-messages ui-widget ui-helper-hidden ui-fileupload-messages')[1];
  errorMessageElement.style.display = 'block';
  errorMessageElement.appendChild(uploadErrorElement);
}

function handleEditExternalLinkUploadFileFail() {
  let errorMessageElement = document.getElementById('edit-external-link-error-message');
  let uploadErrorElement = document.getElementsByClassName('ui-messages ui-widget ui-helper-hidden ui-fileupload-messages')[0];
  errorMessageElement.style.display = 'block';
  errorMessageElement.appendChild(uploadErrorElement);
}