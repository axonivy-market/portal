function ProcessWidget() {
  
  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }
  
  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    toggleDeletionCheckbox : function(event, widgetComponentVar) {
      var target = event.target;
      if (!($(target).is(':checkbox') || $(target).hasClass('ui-chkbox-icon'))) {
        PF(widgetComponentVar).toggle();
      }
    },
    
    filter: function () {
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
    }
  }
  
}

$(document).ready(function() {
  processWidget = ProcessWidget();
});