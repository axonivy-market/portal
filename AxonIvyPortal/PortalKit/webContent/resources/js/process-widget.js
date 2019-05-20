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
    	var  availableHeight = mainAreaPanel.outerHeight() - announcementMessageContainer.outerHeight(true)
    							- processsHeader.outerHeight(true) 
    							- globalSearchInput.outerHeight(true) - globalSearchTabHeader.outerHeight(true)
    							- error;
    	if (!!availableHeight) {
    		processStartListContainer.height(availableHeight);
    	}
    	processStartListContainer.on("scroll", function() {
    		$(".process-nav-item.selected").removeClass("selected");
    	});
    },

    filter : function() {
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
      if (!$(processItems).is(":visible")) {
        $(noFoundProcesses).removeClass('u-display-none');
      } else {
        $(noFoundProcesses).addClass('u-display-none');
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
    var collapsed = $(this).find("input[id$='alphabet-process-index-fieldset_collapsed']").val();
    if ((shouldExpand && collapsed == 'true') || (!shouldExpand && collapsed == 'false')) {
      var categoryButton = $(this).find("span[class*='ui-fieldset-toggler']");
      $(categoryButton).click();
    }
  });
}

function jumpToProcessGroupByCharacter(event) {
  $(".process-nav-item.selected").removeClass("selected");
  var clickedCharacter = event.target.text;
  var selectedItem = document.getElementById(event.target.id);
  document.getElementsByClassName("js-process-group-" + clickedCharacter)[0].scrollIntoView();
  setTimeout(function(){ selectedItem.classList.add("selected"); }, 1);
}