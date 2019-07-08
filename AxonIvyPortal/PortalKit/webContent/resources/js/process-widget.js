function ProcessWidget() {

  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }

  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },
    
    setupScrollbar : function () {
    	var processsHeader = $('.js-process-header');
    	var processStartListContainer = $('.js-process-start-list-container');
    	var mainAreaPanel = $('#main-area-panel');
    	var error = 0;
        var globalSearchInput = $('.js-global-search');
        var globalSearchTabHeader = $('.ui-tabs-nav');
        if (globalSearchTabHeader.length > 0) {
          error = 55; // included margin, padding in search page
        }
        var announcementMessageContainer = $('.js-announcement-message');
    	var  availableHeight = mainAreaPanel.outerHeight()||0 - announcementMessageContainer.outerHeight(true)||0
    							- processsHeader.outerHeight(true)||0 
    							- globalSearchInput.outerHeight(true)||0 - globalSearchTabHeader.outerHeight(true)||0
    							- error;
    	if (!!availableHeight) {
    		processStartListContainer.css("max-height", availableHeight + "px");
    	}
    	processStartListContainer.on("scroll", function() {
    		$(".process-nav-item.selected").removeClass("selected");
    	});
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
        if (contain(expressKeyToSearch, keyword.trim()) && $(this).hasClass("express-workflow")) {
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
  document.getElementsByClassName("js-process-group-" + clickedCharacter)[0].scrollIntoView();
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 1);
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