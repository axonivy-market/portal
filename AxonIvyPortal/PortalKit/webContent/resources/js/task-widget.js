function TaskWidget(elements, elementsToDisplay) {

  var numberOfElements = elements;
  var numberOfElementsToDisplay = elementsToDisplay;

  var items = $('.js-widget-header-menu li');
  $.each(items, function() {
    $(this).click(function() {
      if (!$(this).hasClass('js-ignore-selected-state')) {
        $(items).removeClass('is-selected');
        $(this).addClass('is-selected');
      }
    });
  });
  
  return {
    
    setupScrollbar: function() {
      
      if (numberOfElements <= numberOfElementsToDisplay) {
        return;
      }
      
      var container = $('.js-task-start-list');
      var childElement = $('.js-task-start-list-item');
      var scrollUpButton = $('.js-task-start-list-scroll-up');
      var scrollDownButton = $('.js-task-start-list-scroll-down');
      
      var taskElementHeight = childElement.outerHeight(true);
      
      container.height(taskElementHeight * numberOfElementsToDisplay);
      
      container.perfectScrollbar({
        suppressScrollX: true
      });
      
      scrollUpButton.click(function() {
        container.scrollTop(container.scrollTop() - taskElementHeight);
      });
      scrollUpButton.show();
      
      scrollDownButton.click(function() {
        container.scrollTop(container.scrollTop() + taskElementHeight);
      });
      scrollDownButton.show();
    },
  
    hideScrollbar: function() {
      $('.js-task-start-list-scroll-up').hide();
      $('.js-task-start-list-scroll-down').hide();
    }
    
  }
  
}