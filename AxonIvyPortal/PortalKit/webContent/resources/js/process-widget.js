function ProcessWidget() {
  
  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }
  
  return {
    clearSearchField : function() {
      $('.js-filter-process-widget-list-item').val('');
    },

    preventBubblingEvent : function(widgetComponentVar) {
      var checkBox = PF(widgetComponentVar).jq.children().last();
      $(checkBox).click(function(event) {
        event.stopPropagation();
      });
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
    }
  }
  
}

$(document).ready(function() {
  processWidget = ProcessWidget();
});