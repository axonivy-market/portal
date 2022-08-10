var portal = (function() {
  return {
    notifyError : function(message, actionLinkText) {
      var notifier = new Notifier();
      notifier.createNotificationContainerIfNotExist();
      notifier.notify(message, actionLinkText);
    }
  }
})();

function Notifier() {

  return {
    createNotificationContainerIfNotExist: function() {
      if ($('.js-notification-container')[0]) {
        return;
      }
      var container = $(document.createElement('div'));
      container.addClass('notification-container js-notification-container u-shadow-effect');
      $('body').append(container);
    },
    
    notify : function(message, actionLinkText) {
      var notification = $(document.createElement('div'));
      notification.addClass('notification');

      var notificationContent = $(document.createElement('div'));
      notificationContent.addClass('notification-content');

      var notificationContentMessage = $(document.createElement('div'));
      notificationContentMessage.addClass('notification-content-message');

      var notificationContentMessageSpan = $(document.createElement('span'));
      notificationContentMessageSpan.text(message);
      
      var moreDetailsLink = $(document.createElement('a'));
      moreDetailsLink.text(actionLinkText ? actionLinkText : 'See more details...');
      moreDetailsLink.attr('href', '#');
      moreDetailsLink.addClass('notification-content-action-more-details');
      moreDetailsLink.click(function() {
        PF('exception-dialog').show();
      });

      var notificationContentAction = $(document.createElement('div'));
      notificationContentAction.addClass('notification-content-action');

      var notificationContentActionClose = $(document.createElement('span'));
      notificationContentActionClose.addClass('notification-content-action-close');
      notificationContentActionClose.click(function() {
        $(this).parent().parent().parent().remove();
      });

      notification.append(notificationContent);

      notificationContent.append(notificationContentMessage);
      notificationContent.append(notificationContentAction);
      notificationContentMessage.append(notificationContentMessageSpan);
      notificationContentAction.append(moreDetailsLink);
      notificationContentAction.append(notificationContentActionClose);

      $('.js-notification-container').append(notification);
    }
  }
}