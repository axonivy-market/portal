$(document).ready(function() {
  
  var animationDuration = 300;
  
  var selectedCase = $('.js-case-item.is-selected');
  if (selectedCase) {
    selectedCase.addClass('mod-expanded');
    $('.js-case-item-body', selectedCase).show();
    $('.js-case-timestamp', selectedCase).hide();
    $('.js-case-description', selectedCase).show();
  }
      
  $('.js-case-item-header').click(function() {
    var item = $(this).parent().parent();
    var itemBody = $('.js-case-item-body', item);
    var timestamp = $('.js-case-timestamp', item);
    var description = $('.js-case-description', item);
    
    $('.js-case-timestamp').not(timestamp).show();
    $('.js-case-description').not(description).hide();
    
    if (item.hasClass('mod-expanded')) {
      timestamp.show();
      description.hide();
      itemBody.slideUp(animationDuration, function() {
        item.removeClass('mod-expanded');
      });
    } else {
      timestamp.hide();
      description.show();
      $('.js-case-item.mod-expanded').removeClass('mod-expanded');
      item.addClass('mod-expanded');
      var itemBodies = $('.js-case-item-body');
      
      if (itemBodies.size() === 1) {
        itemBody.slideDown(animationDuration);
      } else {
        itemBodies.not(itemBody).slideUp(animationDuration, function() {
          itemBody.slideDown(animationDuration);
        });
      }
    }
  });
});