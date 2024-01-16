// Detecting browser type
const isEdge = window.navigator.userAgent.includes('Edge');
const isFireFox = navigator.userAgent.toLowerCase().includes('firefox');
const isIphone = navigator.userAgent.match(/iPhone|iPod/i);

// Global variables
let messages = [];
let currentIndex;
const numberOfLoad = 20;
const numberToApplyLazyLoad = 50;
let recipientName;
let originalMessageTemplate;
let jsMessageList;
const clientId = new Date().getTime();
let isChatDeactivated = false;
let hasPendingRequestForSendersOfUnreadMessages = false;
let hidden, visibilityChange;

// Handling browser visibility change
if (typeof document.hidden !== 'undefined') {
  hidden = 'hidden';
  visibilityChange = 'visibilitychange';
} else if (typeof document.msHidden !== 'undefined') {
  hidden = 'msHidden';
  visibilityChange = 'msvisibilitychange';
} else if (typeof document.webkitHidden !== 'undefined') {
  hidden = 'webkitHidden';
  visibilityChange = 'webkitvisibilitychange';
}

// Helper class for constructing RESTful URIs
function IvyUri() {
  this.rest = function () {
    const baseUri = window.location.origin;
    return `${baseUri}${contextPath}/api`;
  };
}

// ChatBot class for handling chat interactions
function ChatBot(uri, view) {
  this.uri = uri;

  // Sending a message
  this.sendMessage = async function () {
    const inputMessage = $('.js-chatbot-input-message').val().trim();
    if (!inputMessage) {
      return;
    }

    const message = { 'message': inputMessage };
    sendChatMessage(uri, view, message);
  };

  // Handling key events, e.g., Enter key
  this.onKeyEvent = function (e) {
    if (e.ctrlKey && e.keyCode == 13) {
      adjustMessageInputForNewline();
    } else if (e.keyCode === 13) {
      e.preventDefault();
      this.sendMessage();
    }
  };
}

// View class for rendering chat messages
function View(uri) {
  // Initializing message components
  function setValueForMessageComponent() {
    originalMessageTemplate = document.getElementsByClassName('js-message-template')[0];
    jsMessageList = document.getElementsByClassName('js-chatbot-message-list')[0];
  }

  // Rendering received messages
  this.renderMessage = function (message) {
    setValueForMessageComponent();
    renderMessageFunc(message, false);
  };

  // Rendering user's own messages
  this.renderMyMessage = function (message) {
    setValueForMessageComponent();
    renderMessageFunc(message, true);
  };

  // Helper function for rendering messages
  function renderMessageFunc(messageWrapper, isMyMessage) {
    const cloneTemplate = originalMessageTemplate.cloneNode(true);
    const message = isMyMessage ? messageWrapper.message : parseMessage(messageWrapper.message);

    cloneTemplate.getElementsByClassName('js-message')[0].innerHTML = message;
    $(cloneTemplate).removeClass('u-hidden').removeClass('js-message-template');

    const senderElem = cloneTemplate.getElementsByClassName('js-sender')[0];

    if (isMyMessage) {
      $(cloneTemplate).addClass('my-message');
      senderElem.innerText = 'You';
      $(cloneTemplate).find('.js-chatbot-avatar').get(0).remove();
    } else {
      senderElem.innerText = 'Portal';
      $(cloneTemplate).find('.js-user-avatar').get(0).remove();
    }

    appendMessageToList(cloneTemplate);
    return cloneTemplate;
  }

  // Helper function for appending messages to the message list
  function appendMessageToList(cloneTemplate) {
    const $messageList = $(jsMessageList);
    $messageList.append(cloneTemplate);

    if ($(cloneTemplate).hasClass('my-message')) {
      const $chatMessages = $('.js-chatbot-message-list').find('.chat-message');
      setTimeout(function () {
        $messageList.animate({
          scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
        }, 0);
      }, 0);
    }
  }
}

// Function for toggling full-screen mode
function toggleFullScreen(e) {
  const container = $(e).parent('.js-image-container');
  container.hasClass('expand-fullscreen') ?
    container.removeClass('expand-fullscreen') :
    container.addClass('expand-fullscreen');
}

// Helper function to send chat messages
async function sendChatMessage(uri, view, message) {
  jQuery.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: `${uri}/${clientId}`,
    crossDomain: true,
    cache: false,
    headers: { 'X-Requested-By': 'ivy' },
    data: message.message,
    complete: function (response) {
      view.renderMyMessage(message);
      view.renderMessage(JSON.parse(response.responseText));
      $('.js-chatbot-input-message').val('');
      scrollToLatestUserMessage();
    }
  });
}

// Helper function to adjust message input for a new line
function adjustMessageInputForNewline() {
  const messageInputField = document.getElementById('message-input-field');
  const position = messageInputField.selectionEnd;
  messageInputField.value = messageInputField.value.substring(0, position) + '\n' + messageInputField.value.substring(position);
  messageInputField.selectionEnd = position + 1;
  messageInputField.seletionStart = position + 1;
  messageInputField.scrollTop = messageInputField.scrollHeight;
}

function scrollToLatestUserMessage() {
    var $messageList = $('.js-message-list');
    var $latestMessage = $('.my-message').last();

    if ($latestMessage.length > 0) {
        $messageList.animate({
            scrollTop: $latestMessage.offset().top - $messageList.offset().top + $messageList.scrollTop()
        }, 300);
    }
}
