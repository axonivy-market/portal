function ProcessWidget() {

  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }

  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    filter : function() {
      var processItems = $('.js-process-start-list-item');
      $(processItems).show();
      $.each(processItems, function() {
        var processName = $('.js-process-start-list-item-name', this).text().toLowerCase();
        var keyword = $('.js-filter-process-widget-list-item').val().toLowerCase();
        if (!contain(processName, keyword)) {
          $(this).hide();
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
  var clickedCharacter = event.target.text;
  document.getElementsByClassName("js-process-group-" + clickedCharacter)[0].scrollIntoView();
}