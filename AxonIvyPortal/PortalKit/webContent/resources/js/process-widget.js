var prefixProcessGroup = "js-process-group-";
var prefixProcessStart = "js-process-starts-with-";

function ProcessWidget() {

  var excludeMarginBottom = 30;

  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }

  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    // setup scroll-bar for process list
    setupScrollbar : function () {
      // Check current page, if called from compact widget then don't need to proceed
      if ($('.js-compact-process-widget').length) {
        return;
      }

      var processsHeader = $('.js-process-header');
      var processStartListContainer = $('.js-process-start-list-container');
      var error = 0;
      var globalSearchInput = $('.js-global-search');
      var globalSearchTabHeader = $('.ui-tabs-nav');
      var globalSearchBottom = 30;
      if (globalSearchTabHeader.length > 0) {
    	globalSearchBottom = 0;
        error = 55; // included margin, padding in search page
      }
      var announcementMessageContainer = $('.js-announcement-message');
      var mainScreenHeight = $('body').outerHeight() - $('.layout-topbar').outerHeight() - globalSearchBottom;
      var processHeaderHeight = $('#portal-template-header').outerHeight();
      var footerHeight = $('#portal-template-footer').outerHeight();
      var availableHeight = mainScreenHeight - (announcementMessageContainer.outerHeight(true)||0)
                              - (processsHeader.outerHeight(true)||0) - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0)
                              - (globalSearchTabHeader.outerHeight(true)||0) - error
                              - processHeaderHeight - footerHeight;
      if (!!availableHeight) {
        processStartListContainer.css("max-height", availableHeight + "px");
        this.setupProcessNav(processStartListContainer, availableHeight, announcementMessageContainer);
      }
      processStartListContainer.on("scroll", function() {
        $(".process-nav-item.selected").removeClass("selected");
      });
    },

    // setup scroll-bar for process navigator
    setupProcessNav: function(processStartListContainer, availableHeight, announcementMessageContainer) {
      var processNav = $('.js-process-nav');
      if (!shouldDisplayProcessNav(processNav, availableHeight)) {
        return;
      }

      var searchTab = $('.search-results-tabview');
      var marginRightProcessWidget = 0;
        // For search form tab
        if (searchTab.length > 0) {
          marginRightProcessWidget = 20;
          if (searchTab.width() > processStartListContainer.width()) {
            marginRightProcessWidget = extractRightOffsetOfElement(searchTab) + extractRightOffsetOfElement(processStartListContainer);
            // if first time call, ignore outer padding
            if (processStartListContainer.css("margin-right") != "0px") {
              marginRightProcessWidget = marginRightProcessWidget + 8;
            }
          }
        } else {
          // For process list page
          var layoutContent = $('.layout-content');
          var processWidget = $('.process-widget');
          marginRightProcessWidget = extractRightOffsetOfElement(layoutContent) + extractRightOffsetOfElement(processWidget);

          var scrollBarWidth = this.detechScrollBarWidth();
          processNav.css("right", scrollBarWidth + "px");
        }
        processStartListContainer.css("width", "calc(100% + " + marginRightProcessWidget + "px)");

      processNav.css("height", (availableHeight  - excludeMarginBottom) + "px");
      
      var processHeaderHeight = $('#portal-template-header').outerHeight();
      var availableHeightProcessNavTop = (($('.js-process-header').outerHeight(true)||0) + ($('.layout-topbar').outerHeight(true)||0)
                                          + (announcementMessageContainer.outerHeight(true)||0) + excludeMarginBottom + processHeaderHeight);
      processNav.css("top", availableHeightProcessNavTop + "px");
    },

    detechScrollBarWidth : function() {
      var scrollbarWidth = 0;
      var processWidget = document.getElementById("process-widget:process-list");
      if (processWidget !== null) {
        scrollbarWidth= processWidget.offsetWidth - processWidget.clientWidth;
      }
      return scrollbarWidth;
    },

    filter : function() {
      // Check current page, if called from compact widget then don't need to proceed
      if ($('.js-compact-process-widget').length) {
        return;
      }

      $(".process-nav-item.selected").removeClass("selected");
      var processItems = $('.js-process-start-list-item');
      $(processItems).show();
      $.each(processItems, function(index) {
        var processName = $('.js-process-start-list-item-name', this).text().toLowerCase();
        var processDescription = $('.js-process-start-list-item-description .ui-tooltip-text').get(index).innerText.toLowerCase();
        var keyword = $('.js-filter-process-widget-list-item').val().toLowerCase();
        if (!contain(processName, keyword) && !contain(processDescription, keyword)) {
          $(this).hide();
        }
        var expressKeyToSearch = "express";
        if (expressKeyToSearch.toLowerCase() === keyword.trim().toLowerCase()
        		&& $(this.children).hasClass("express-workflow")) {
        	$(this).show();
        }
        
        var externalLinkKeyToSearch = "link";
        if (externalLinkKeyToSearch.toLowerCase() === keyword.trim().toLowerCase()
        		&& $(this.children).hasClass("js-external-link-process-item")) {
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
        $(noFoundProcesses).removeClass('u-display-none');
        expandCollapseButtons.addClass('u-display-none');
      } else {
        $(noFoundProcesses).addClass('u-display-none');
        expandCollapseButtons.removeClass('u-display-none');
      }
      
      if (document.getElementsByClassName('process-nav').length !== 0) {
        disableGroupNavigation();
      }
    }
  };
}

// Calculate a minimum height for process navigator
// Then compare with availableHeight of process widget body
// Return true if enough space to display navigator
function shouldDisplayProcessNav(processNav, availableHeight) {
  if (processNav.length) {
    var processNavElement = processNav[0];
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

//Update scroll-bar when window size is changed
var resizeTimer;

$(window).resize(function() {
  var processWidget = ProcessWidget();
  processWidget.setupScrollbar();

  // To prevent execute much time, just do this function when resizing has "stopped"
  clearTimeout(resizeTimer);
  resizeTimer = setTimeout(function() {
    FavouritesProcess.setUpScrollBarForCompactProcessLists(true);
  }, 200);
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

function jumpToProcessGroupByCharacter(event) {
  var clickedCharacter = getClassNameStartsWith(event.target.className, prefixProcessStart).slice(prefixProcessStart.length);
  $(".process-nav-item.selected").removeClass("selected");
  var selectedItem = document.getElementById(event.target.id);
  var processGroupSeleted = document.getElementsByClassName(prefixProcessGroup + clickedCharacter)[0];
  
  processGroupSeleted.parentNode.scrollTop = processGroupSeleted.offsetTop - processGroupSeleted.parentNode.offsetTop;
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

var compactProcessWidgetClass = '.compact-process-widget';
var processStartItemClass = '.process-start-list-item';
var mobileViewPort = 640;

var FavouritesProcess = {

    setUpScrollBarForCompactProcessLists : function(isResize) {
      if ($(compactProcessWidgetClass).length === 0) {
        return;
      }
      this.setupScrollbar(isResize);
    },
    
    setupScrollbar : function(isResize) {

      var availableHeight = this.calculateHeightForFavorites();
      
      // Check if viewport is mobile screen
      if ($(window).width() <= mobileViewPort) {
        // Always show max 7 processes for user/application favorites on mobile screen
        availableHeight = this.getHeightOfProcessStartItem() * 7;
      }

      availableHeight = availableHeight < 0 ? 0 : availableHeight;
      // If there is available height, don't setup scroll-bar for compact process widget
      if ($(compactProcessWidgetClass).height() < availableHeight && !isResize) {
        return;
      }

      var appProcessList = $('[id$="process-widget:user-default-process-list"]');
      var userProcessList = $('[id$="process-widget:user-process-container"]');
      var appFavoritesHeight = this.getHeightOfAppFavorites(appProcessList);
      var userFavoritesHeight = this.getHeightOfUserFavorites(userProcessList);
      var userFavoritesMarginBottom = this.getUserFavoritesMarginBottom(userProcessList);

      // If both application process height and user process height are bigger than available height
      // or window is resizing
      // then set up scroll-bar for each of them
      var isContentOverPanelContainer = userFavoritesHeight + appFavoritesHeight > availableHeight;
      if (isResize || isContentOverPanelContainer) {
        availableHeight -= userFavoritesMarginBottom;

        if (appFavoritesHeight >= availableHeight/2 && userFavoritesHeight >= availableHeight/2) {
          maxHeightUserProcessList = availableHeight/2;
          maxHeightAppProcessList = availableHeight/2;
        }

        // if application process height is greater than user process height
        else if (appFavoritesHeight > userFavoritesHeight) {
          maxHeightAppProcessList = availableHeight - userFavoritesHeight;
          maxHeightUserProcessList = userFavoritesHeight;
        }

        else if (appFavoritesHeight < userFavoritesHeight) {
          var maxHeightAppProcessList = appFavoritesHeight;
          var maxHeightUserProcessList = availableHeight - appFavoritesHeight;
          if (appFavoritesHeight === 0) {
            maxHeightUserProcessList += userFavoritesMarginBottom;
            userFavoritesMarginBottom = 0;
          } else {
            maxHeightUserProcessList -= userFavoritesMarginBottom;
          }
        }
      }

      if (!isContentOverPanelContainer) {
        maxHeightUserProcessList = userFavoritesHeight;
        maxHeightAppProcessList = appFavoritesHeight;
        if (userFavoritesHeight + appFavoritesHeight === availableHeight) {
          maxHeightUserProcessList -= userFavoritesMarginBottom;
        }
      }

      userProcessList.css('margin-bottom', userFavoritesMarginBottom);
      userProcessList.find('.compact-processes-container').css('height', maxHeightUserProcessList);
      appProcessList.find('.js-user-default-process-list-content').css('height', maxHeightAppProcessList);
    },

    calculateHeightForFavorites : function() {
      var mainContentHeight = $(window).outerHeight() - ($('.layout-topbar').outerHeight(true) || 0);
      var processHeaderHeight = $('.js-process-widget-header').outerHeight(true) || 0;
      var favoriteProcessHeaderHeight = $('.js-favorite-process-header').outerHeight(true) || 0;
      var appFavoritesProcessHeaderHeight = $('.js-user-default-process-list-header').outerHeight(true) || 0;
      var headerComponentHeight = $('#portal-template-header').outerHeight(true) || 0;
      var footerComponentHeight = $('#portal-template-footer').outerHeight(true) || 0;
      var announcementMessageHeight = $('.js-announcement-message').outerHeight(true) || 0;
      var customizedContentHeight = $('.js-custom-widget-container').outerHeight(true) || 0;
      var paddingValues = parseInt($(compactProcessWidgetClass).css('padding-top'), 0) || 0;

      return mainContentHeight - announcementMessageHeight - paddingValues
             - processHeaderHeight - favoriteProcessHeaderHeight - appFavoritesProcessHeaderHeight
             - customizedContentHeight - headerComponentHeight - footerComponentHeight;
    },
    
    getHeightOfUserFavorites : function(userProcessList) {
      userProcessList.find('.compact-processes-container').css('height', '');
      return userProcessList.find('.compact-processes-container').outerHeight(true) || 0;
    },
    
    getHeightOfAppFavorites : function(appProcessList) {
      appProcessList.find('.js-user-default-process-list-content').css('height', '');
      return appProcessList.find('.js-user-default-process-list-content').outerHeight(true) || 0;
    },
    
    getHeightOfProcessStartItem : function() {
      return $(processStartItemClass).outerHeight(true) || 0;
    },
    
    getUserFavoritesMarginBottom : function(userProcessList) {
      return parseInt(userProcessList.css('margin-bottom'), 0) || 0;
    }

  };
