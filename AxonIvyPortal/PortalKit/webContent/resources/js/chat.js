$(document).ready(function() {
	chatHandler = new ChatHandler();
//	chatHandler.stream();
	chatHandler.setHeightForContactList();
  $(window).resize(function() {
    chatHandler.setHeightForContactList();
  });
});

function ChatHandler() {
	
	var streamingData = false;
  
  function contain(s, keyword) {
    return s.indexOf(keyword) >= 0;
  }
  
  var sender = $('.js-user-name').text();
  var ivyPath = window.location.pathname.split("/")[1];
  var ivyApplication = window.location.pathname.split("/")[4]
  
  $('.js-toggle-chat-panel').click(function() {
    $('.js-chat-panel').toggleClass('active');
    $('.js-top-menu').toggleClass('mod-chat-active');
    closeChatMessagePanel();
    
    var cover = $('<div class="u-cover"></div>');
    cover.click(function() {
    	streamingData = false;
      $('.js-chat-panel').removeClass('active');
      $('.js-top-menu').removeClass('mod-chat-active');
      closeChatMessagePanel();
      $(this).remove();
    });
    $('.js-chat-panel').parent().append(cover);
    chatHandler.stream();
    streamingData = true;
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
    var messageContent = $('.js-input-message').val().trim();
    if (messageContent) {
      var messageCard = createMessageCard(messageContent);
      $(messageCard).addClass('sent');
      $('.js-message-card-list').append(messageCard);
      $('.js-input-message').val('');
      $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
      
      var recipient = $('.js-contact-card-name', '.js-show-chat-message.active').text();
      $.ajax({
          type: 'POST',
          contentType: 'application/json',
          //"Chat" is application name, will be replaced in the real functions
          url: window.location.origin + "/"+ window.location.pathname.split("/")[1] + "/api/" + window.location.pathname.split("/")[4] +  "/chatcontroller/write",
          headers: {
              "X-Requested-By": "IVY"
          },
          data: messageToJson(sender, messageContent, recipient),
          
//          beforeSend: function(xhr) { 
//        	  xhr.setRequestHeader("Authorization", "Basic " + btoa("Developer" + ":" + "Developer")); 
//          },
          success: function(data, textStatus, jqXHR){
              
          },
          error: function(jqXHR, textStatus, errorThrown){
              alert('Error: ' + textStatus);
          }
      });
    }
  }
  
  function messageToJson(sender, message, recipient) {
	    return JSON.stringify({
	        "sender": sender,
	        "content": message,
	        "recipients": [recipient],
	        "timestamp": new Date().toISOString()
	    });
	}
  
  function createMessageCard(message) {
    var div = document.createElement('div');
    $(div).addClass('message-card');
    var p = document.createElement('p');
    $(p).text(message);
    $(div).append(p);
    return div;
  }
  
  function addMessageCardToConversation(message){
	  var messageCard = createMessageCard(message.content)
	  var recipient = $('.js-contact-card-name', '.js-show-chat-message.active').text();
	  if (message.sender === sender){
		  $(messageCard).addClass('sent');
	  } else {
		  $(messageCard).addClass('received');
	  }
      $('.js-message-card-list').append(messageCard);
      $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
  }
  
  return {
	stream : function () {
			if (!window.XMLHttpRequest) {
				alert('Browser is not supported XMLHttpRequest!');
				return;
			}
			try {
				var xhr = new XMLHttpRequest();
				xhr.onerror = function() {
					alert('Error streaming1!');
				};
				xhr.onreadystatechange = function() {
					  if(xhr.readyState == 3) {
					    var newData = xhr.response.substr(xhr.seenBytes);
					    console.log(newData);
					    var messages = JSON.parse(newData);
					    if (messages.uuid === null || messages.uuid === undefined){
					    	for (var i = 0; i < messages.length; i++) {
						    	var message = messages[i];
						    	if (message.sender !== sender && !$(findContact(message.sender)).hasClass('active')) {
						            showNotification(message.sender);
						        } else if (message.sender !== sender){
						        	addMessageCardToConversation(message);
						        }
						    }
					    } else {
					    	if (streamingData){
					    		// send request to let server know we still working with Chat
						    	$.ajax({
						            type: 'POST',
						            contentType: 'application/json',
						            //"Chat" is application name, will be replaced in the real functions
						            url: window.location.origin + "/"+ window.location.pathname.split("/")[1] + "/api/" + window.location.pathname.split("/")[4] +  "/chatcontroller/updateStreamingChatUUID",
						            headers: {
						                "X-Requested-By": "IVY"
						            },
						            data: JSON.stringify(messages),
						            success: function(data, textStatus, jqXHR){
						            },
						            error: function(jqXHR, textStatus, errorThrown){
						                alert('Error in updateStreamingChatUUID: ' + textStatus);
						            }
						        });
					    	}
					    }
					    xhr.seenBytes = xhr.responseText.length;
					  }
				};
				xhr.onload = function(){
					var newData = xhr.response;
				};
				xhr.onprogress = function(event) {
					  if(event.lengthComputable) {
						  console.log(event);
					  }
				};
				//"Chat" is application name, will be replaced in the real functions
				//xhr.open("GET", "http://localhost:8081/ivy/api/designer/chatcontroller/get", true, "Developer", "Developer");
				//xhr.open("GET", "http://localhost:8081/ivy/api/designer/chatcontroller/get", true);
				xhr.open("GET", window.location.origin + "/"+ window.location.pathname.split("/")[1] + "/api/" + window.location.pathname.split("/")[4] + "/chatcontroller/get", true);
				xhr.setRequestHeader("X-Requested-By", "IVY");
				//xhr.setRequestHeader("Content-Length", "0");
				xhr.send(null);
			} catch (e) {
				alert('Error streaming2!');
			}
		},
		
    filter: function() {
      var chatMessages = $('.js-show-chat-message');
      $(chatMessages).show();
      $.each(chatMessages, function() {
        var contactName = $('.js-contact-card-name', this).text().toLowerCase();
        var keyword = $('.js-chat-filter-user').val().toLowerCase();
        if (!contain(contactName, keyword)) {
          $(this).hide();
        }
      });
    },
    
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
//           notifyMessageHasBeenRead();
        }
        
        // TODO assume that whenever the chat message panel is active then the user already read the message.
        $('.js-message-card-list').scrollTop($('.js-message-card-list').prop('scrollHeight'));
      }
    }
  }
}