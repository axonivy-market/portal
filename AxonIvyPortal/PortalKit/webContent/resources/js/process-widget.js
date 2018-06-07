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