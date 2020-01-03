function ProcessWidget() {

  excludeMarginBottom = 30;

  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }

  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    // setup scroll-bar for process list
    setupScrollbar : function () {
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
      var availableHeight = mainScreenHeight - (announcementMessageContainer.outerHeight(true)||0)
                              - (processsHeader.outerHeight(true)||0) - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0)
                              - (globalSearchTabHeader.outerHeight(true)||0) - error;
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
      var searchTab = $('.search-results-tabview');
      var marginRightProcessWidget = 0;
        // For search form tab
        if (searchTab.length > 0) {
          marginRightProcessWidget = 20;
          if (searchTab.width() > processStartListContainer.width()) {
            marginRightProcessWidget = ((searchTab.width()||0) - (processStartListContainer.width()||0))/2;
            // if first time call, ignore outer padding
            if (processStartListContainer.css("margin-right") != "0px") {
              marginRightProcessWidget = marginRightProcessWidget + 8;
            }
          }
        } else {
          // For process list page
          var layoutContent = $('.layout-content');
          var processWidget = $('.process-widget');
          marginRightProcessWidget = ((layoutContent.outerWidth(true)||0) - (layoutContent.width()||0))/2
                                       + ((processWidget.outerWidth(true)||0) - (processWidget.width()||0))/2;

          var scrollBarWidth = this.detechScrollBarWidth();
          processNav.css("right", scrollBarWidth + "px");
        }
        processStartListContainer.css("width", "calc(100% + " + marginRightProcessWidget + "px)");

      processNav.css("height", (availableHeight  - excludeMarginBottom) + "px");
      
      var availableHeightProcessNavTop = (($('.js-process-header').outerHeight(true)||0) + ($('.layout-topbar').outerHeight(true)||0)
                                          + (announcementMessageContainer.outerHeight(true)||0) + excludeMarginBottom);
      processNav.css("top", availableHeightProcessNavTop + "px");
    },

    detechScrollBarWidth : function() {
      var scrollbarWidth = 0;
      var processWidget = document.getElementById("process-widget:process-list");
      if (processWidget != null) {
        scrollbarWidth= processWidget.offsetWidth - processWidget.clientWidth;
      }
      return scrollbarWidth;
    },

    filter : function() {
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
      
      if (document.getElementsByClassName('process-nav').length != 0) {
        disableGroupNavigation();
      }
    }
  }
}

$(document).ready(function() {
  processWidget = ProcessWidget();
  processWidget.filter();
});

//Update scroll-bar when window size is changed
$(window).resize(function() {
  processWidget = ProcessWidget();
  processWidget.setupScrollbar();
});

function expandOrCollapseAllCategories(shouldExpand) {
  var allFieldsets = $("fieldset[id$='alphabet-process-index-fieldset']");
  allFieldsets.each(function() {
    if(shouldExpand && $(this).find('.ui-icon-plusthick').length) {
      var categoryButton = $(this).find("span[class*='ui-fieldset-toggler']");
      $(categoryButton).click();
    }

    if(!shouldExpand && $(this).find('.ui-icon-minusthick').length) {
      var categoryButton = $(this).find("span[class*='ui-fieldset-toggler']");
      $(categoryButton).click();
    }
  });
}

function jumpToProcessGroupByCharacter(event) {
  var prefix = "js-process-starts-with-";
  var clickedCharacter = getClassNameStartsWith(event.target.className, prefix).slice(prefix.length);
  $(".process-nav-item.selected").removeClass("selected");
  var selectedItem = document.getElementById(event.target.id);
  var processGroupSeleted = document.getElementsByClassName("js-process-group-" + clickedCharacter)[0];
  
  processGroupSeleted.parentNode.scrollTop = processGroupSeleted.offsetTop - processGroupSeleted.parentNode.offsetTop;
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 100);
}

function getClassNameStartsWith(classList, prefix) {
  return $.grep(classList.split(" "), function(className, index){
    return className.indexOf(prefix) === 0;
  }).join();
}

function disableGroupNavigation() {
  var prefix ="js-process-group-";
  var processIndexGroups = document.getElementsByClassName("js-process-index-group");

  var hiddenIndexGroups = getElementsHaveClassName(processIndexGroups, false);
  hiddenIndexGroups.forEach(function(e) {
    var indexNav = getClassNameStartsWith(e.className, prefix).slice(prefix.length);
    var hidden = document.getElementsByClassName('js-process-starts-with-' + indexNav)[0];
    hidden.classList.add('disabled');
  });

  var displayedIndexGroups = getElementsHaveClassName(processIndexGroups, true);
  displayedIndexGroups.forEach(function(e) {
    var indexNav = getClassNameStartsWith(e.className, prefix).slice(prefix.length);
    var hidden = document.getElementsByClassName('js-process-starts-with-' + indexNav)[0];
    if ($(hidden).hasClass('disabled')) {
      hidden.classList.remove('disabled');
    }
  });
}

function getElementsHaveClassName(displayedFieldSets, invert) {
  return $.grep(displayedFieldSets, function(fieldSet, index){
    return fieldSet.style.display === "none";
  }, invert);
}

var maxItems = 7;
var processItemHeight = 55;

var favouritesProcess = {

  init : function() {
	if( window.innerWidth < 1024 &&  window.innerWidth > 640 && window.innerHeight > 640 ) {
		maxItems = 5;
	} else if(window.innerWidth <= 640 | window.innerHeight <= 640 ) {
		maxItems = 3;
	}
  },

  setUpScrollBarUserProcesses : function() {
    this.scrollBarForUserProcesses();
  },
  
  setUpScrollBarApplicationProcesses : function() {
	this.scrollBarForApplicationProcesses();
  },

  scrollBarForUserProcesses : function() {
    var userProcessList = $('[id$="process-widget:user-process-container"]');
    var userProcessListBody = userProcessList.find('.compact-processes-container.widget-content');
    var userProcessSize = userProcessList.find("form").length;
    var scrollHeightForProcess = 0;
    if (userProcessSize > maxItems) {
    	scrollHeightForProcess = processItemHeight*maxItems + 2;
    	userProcessListBody.css("max-height", scrollHeightForProcess);
    }
  },
  
  scrollBarForApplicationProcesses : function() {
    var appProcessList = $('[id$="process-widget:user-default-process-list"]');
    var appProcessListBody = appProcessList.find('.compact-default-processes-container');
    var appProcessSize = appProcessList.find("form").length;
    var scrollHeightForProcess = 0;
    if (appProcessSize > maxItems) {
    	scrollHeightForProcess = processItemHeight*maxItems + 2;
    	appProcessListBody.css("max-height", scrollHeightForProcess);
    }
  }
}
