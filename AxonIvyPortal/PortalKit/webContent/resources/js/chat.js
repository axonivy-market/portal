$(document).ready(function() {
  chatHandler = new ChatHandler();
  chatHandler.setHeightForContactList();
  $(window).resize(function() {
    chatHandler.setHeightForContactList();
  });
});

function ChatHandler() {
  
  var sender = $('.js-user-name').text();
  
  $('.js-toggle-chat-panel').click(function() {
    $('.js-chat-panel').toggleClass('active');
    $('.js-top-menu').toggleClass('mod-chat-active');
    closeChatMessagePanel();
    
    var cover = $('<div class="u-cover"></div>');
    cover.click(function() {
      $('.js-chat-panel').removeClass('active');
      $('.js-top-menu').removeClass('mod-chat-active');
      closeChatMessagePanel();
      $(this).remove();
    });
    $('.js-chat-panel').parent().append(cover);
  });
  
  $('.js-show-chat-message').click(function() {
    addActionListenerFosShowChatMessageButton($(this));
  });
  
  $('.js-close-message-list').click(function() {
    closeChatMessagePanel();
  });
  
  function addActionListenerFosShowChatMessageButton(element) {
    $('.js-show-chat-message').not(element).removeClass('active');
    $(element).addClass('active');
    $('.js-chat-panel').addClass('message-displayed');
    $('.js-top-menu').addClass('mod-chat-message-displayed');
    
    $(element).find('.js-show-contact-message-unread').removeAttr('unread-message');
    updateUnreadUserBadge();
    
    var recipient = $('.js-contact-card-name', '.js-show-chat-message.active').text();
    loadPreviouseConversation([{name: 'recipients', value: recipient}]);
  }
  
  function closeChatMessagePanel() {
    $('.js-chat-panel').removeClass('message-displayed');
    $('.js-top-menu').removeClass('mod-chat-message-displayed');
    $('.js-show-chat-message').removeClass('active');
  }
  
  function updateUnreadUserBadge() {
    var numberOfUnreadUsers = $('.js-show-chat-message .js-show-contact-message-unread').filter(function() {return $(this).attr('unread-message') !== undefined}).length;
    if(numberOfUnreadUsers) {
      $('.js-toggle-chat-panel').attr('data-badge', numberOfUnreadUsers);
    } else {
      $('.js-toggle-chat-panel').removeAttr('data-badge');
    }
  }
  
  function setContactOnline(contactName) {
    var contact = $(findContact(contactName));
    contact.removeClass('is-offline');
    contact.find('.js-contact-card-status').removeClass('is-offline');
    var siblings = $(contact.siblings()).filter(function() { return !$(this).hasClass('is-offline')});
    if (siblings.size() == 0) {
      contact.prependTo(contact.parent());
    }
    for (var i = 0; i < siblings.size(); i++) {
      if (contactName.localeCompare($(siblings[i]).find('.js-contact-card-name').text()) == -1) {
        contact.insertBefore(siblings[i]);
        break;
      } else if (i == siblings.size() - 1) {
        contact.insertAfter(siblings[i]);
      }
    }
  }
  
  function setContactOffline(contactName) {
    var contact = $(findContact(contactName));
    contact.addClass('is-offline');
    contact.find('.js-contact-card-status').addClass('is-offline');
    var siblings = $(contact.siblings()).filter(function() { return $(this).hasClass('is-offline')});
    for (var i = 0; i < siblings.size(); i++) {
      if (contactName.localeCompare($(siblings[i]).find('.js-contact-card-name').text()) == -1) {
        contact.insertBefore(siblings[i]);
        break;
      } else if (i == siblings.size() - 1) {
        contact.insertAfter(siblings[i]);
      }
    }
  }
  
  function findContact(contactName) {
    return $('.js-show-chat-message').filter(function() {return $(this).find('.js-contact-card-name').text() === contactName}).get(0);
  }
  
  function showMessage(message) {
    var messageCard = createMessageCard(message.content);
    $(messageCard).addClass('received');
    $('.js-message-card-list').append(messageCard);
  }
  
  function showNotification(contactName) {
    var contact = $(findContact(contactName));
    var badgeValue = $('.js-show-contact-message-unread', contact).attr('unread-message');
    if (badgeValue) {
      var newBadgeValue = parseInt(badgeValue, 10) + 1;
      $('.js-show-contact-message-unread', contact).attr('unread-message', newBadgeValue);
    } else {
      $('.js-show-contact-message-unread', contact).attr('unread-message', 1);
    }
    
    updateUnreadUserBadge();
  }
  
  function sendMessage() {
    var message = $('.js-input-message').val().trim();
    if (message) {
      var messageCard = createMessageCard(message);
      $(messageCard).addClass('sent');
      $('.js-message-card-list').append(messageCard);
      $('.js-input-message').val('');
      $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
      
      var recipient = $('.js-contact-card-name', '.js-show-chat-message.active').text();
      handleChatMessageSending([{name: 'sender', value: sender}, {name: 'recipients', value: recipient}, {name: 'content', value: message}, {name: 'timestamp', value: new Date().toISOString()}]);
    }
  }
  
  function createMessageCard(message) {
    var div = document.createElement('div');
    $(div).addClass('message-card');
    var p = document.createElement('p');
    $(p).text(message);
    $(div).append(p);
    return div;
  }
  
  return {
    
    setHeightForContactList: function() {
      $('.js-chat-contact-list').height($('.js-chat-panel').height() - $('.js-chat-contact-header').height() - $('.js-filter-chat-contact').height());
    },
    
    scrollMessagePanelToBottom: function() {
      $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
    },
    
    onSend: function(e) {
      e.preventDefault();
      sendMessage();
    },
    
    onKeyEvent: function(e) {
      if (e.keyCode === 13) {
        e.preventDefault();
        sendMessage();
      }
    },
    
    onMessage: function(message) {
      
      if (message.action) {
        if (message.action === 'REMOVE_CONTACT') {
          setContactOffline(message.data);
        } else if (message.action === 'ADD_CONTACT') {
          setContactOnline(message.data);
        }
      } else if (message.sender) {
        if (!$(findContact(message.sender)).hasClass('active')) {
          showNotification(message.sender);
        } else {
          showMessage(message);
          // notifyMessageHasBeenRead();
        }
        
        // TODO assume that whenever the chat message panel is active then the user already read the message.
        $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
      }
    }
  }
}