// Detecting browser type
const isEdge = window.navigator.userAgent.includes('Edge');
const isFireFox = navigator.userAgent.toLowerCase().includes('firefox');
const isIphone = navigator.userAgent.match(/iPhone|iPod/i);

// Global variables
let messages = [];
let currentIndex;
const numberOfLoad = 20;
const numberToApplyLazyLoad = 50;
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
  this.rest = function() {
    return window.location.origin + contextPath + '/api';
  };
}

// ChatBot class for handling chat interactions
function ChatBot(uri, view) {
  this.uri = uri;

  // Bind events for UI elements
  this.bindEvents = function() {
    const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
    if (!textbox) {
      console.error('Textbox is undefined. Make sure to provide a valid element.');
      return;
    }

    // Bind events for text box interactions
    textbox.addEventListener('paste', (event) => {
      // Adjust textarea height after paste event
      view.adjustTextareaHeight(event.srcElement);
    });

    textbox.addEventListener('keydown', (event) => {
      const ctrlPressed = event.ctrlKey || event.metaKey;
      // Ctrl + V (or Cmd + V on Mac) is pressed
      if (ctrlPressed && event.key === 'v') {
        return;
      }

      // Ctrl (or Cmd on Mac) + X, Y, Z is pressed
      if (ctrlPressed && ((event.key === 'z') || (event.key === 'y') || (event.key === 'x'))) {
        view.adjustTextareaHeight(event.srcElement);
        return;
      }

      // Ctrl + Enter (or Cmd + Enter on Mac) is pressed
      if ((event.ctrlKey || event.metaKey) && event.key === 'Enter') {
        // Add new line and adjust textarea height
        view.addNewLineToTextbox(event.srcElement);
        view.adjustTextareaHeight(event.srcElement);
        return;
      }

      // Enter is pressed
      if (event.key === 'Enter') {
        this.sendMessage(event.srcElement);
        return;
      }

      // Adjust textarea height for other cases
      view.adjustTextareaHeight(event.srcElement);
    });
  };

  // Sending a message
  this.sendMessage = async function(textbox) {
    // Get input message value
    const inputMessage = $(textbox).val().trim();
    if (!inputMessage) {
      return;
    }

    // Create message object and send it
    const message = { 'message': inputMessage };
    await sendChatMessage(this.uri, view, message);
  };

  this.onClickSendMessage = function(event) {
    // Send message on click then initialize input textbox
    const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
    this.sendMessage(textbox);
    view.initInputTextbox(textbox);
  };

  // Function to send chat messages
  async function sendChatMessage(uri, view, message) {
    try {
      // Send AJAX request to server
      const response = await $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: uri + '/' + clientId,
        crossDomain: true,
        cache: false,
        headers: { 'X-Requested-By': 'ivy' },
        data: JSON.stringify(message),
      });

      // Check if the response is valid JSON
      let parsedResponse;
      try {
        parsedResponse = JSON.parse(response);
      } catch (parseError) {
        console.error('Error parsing JSON response:', parseError);
        return;
      }

      // Render own message and received message
      view.renderMyMessage(message);
      view.renderMessage(parsedResponse);

      // Clear input textbox and initialize
      const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
      $(textbox).val('');
      view.initInputTextbox(textbox);

      // Scroll to latest user message
      view.scrollToLatestUserMessage();
    } catch (error) {
      console.error('Error sending chat message:', error);
    }
  }
}

// View class for rendering chat messages
function View(uri) {
  this.init = function() {
    // Set chatbot panel height to content height
    const contentHeight = $(".js-layout-content").get(0).offsetHeight;
    $(".js-chatbot-panel").height(contentHeight);
    // Initialize textbox element
    this.initInputTextbox($('.js-chat-send-form .js-chatbot-input-message').get(0));
  };

  // Initializing message components
  function setValueForMessageComponent() {
    originalMessageTemplate = document.getElementsByClassName('js-message-template')[0];
    jsMessageList = document.getElementsByClassName('js-chatbot-message-list')[0];
  }

  // Rendering received messages
  this.renderMessage = function(message) {
    // Set value for message components
    setValueForMessageComponent();
    // Render the message
    renderMessageFunc(message, false);
  };

  // Rendering user's own messages
  this.renderMyMessage = function(message) {
    // Set value for message components
    setValueForMessageComponent();
    // Render own message
    renderMessageFunc(message, true);
  };

  // Helper function for rendering messages
  function renderMessageFunc(messageWrapper, isMyMessage) {
    // Clone message template
    const cloneTemplate = originalMessageTemplate.cloneNode(true);
    const message = isMyMessage ? messageWrapper.message : parseMessage(messageWrapper.message);

    // Set message content
    cloneTemplate.getElementsByClassName('js-message')[0].innerHTML = message;
    $(cloneTemplate).removeClass('u-hidden').removeClass('js-message-template');

    const senderElem = cloneTemplate.getElementsByClassName('js-sender')[0];

    if (isMyMessage) {
      // Add class for own message and set sender as 'You'
      $(cloneTemplate).addClass('my-message');
      senderElem.innerText = 'You';
      $(cloneTemplate).find('.js-chatbot-avatar').get(0).remove();
    } else {
      // Set sender as 'Portal' for received message
      senderElem.innerText = 'Portal';
      $(cloneTemplate).find('.js-user-avatar').get(0).remove();
    }

    // Append message to the list
    appendMessageToList(cloneTemplate);
    return cloneTemplate;
  }

  // Helper function for appending messages to the message list
  function appendMessageToList(cloneTemplate) {
    const $messageList = $(jsMessageList);
    $messageList.append(cloneTemplate);

    if ($(cloneTemplate).hasClass('my-message')) {
      // If own message, scroll to the latest message
      const $chatMessages = $('.js-chatbot-message-list').find('.chat-message');
      setTimeout(function() {
        $messageList.animate({
          scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
        }, 0);
      }, 0);
    }
  }

  // Add new line to the textbox
  this.addNewLineToTextbox = function(textbox) {
    const position = textbox.selectionEnd;
    // Insert new line and adjust height
    textbox.value = textbox.value.substring(0, position) + '\n' + textbox.value.substring(position);
    textbox.selectionEnd = position + 1;
    textbox.selectionStart = position + 1;
    textbox.scrollTop = textbox.scrollHeight;
  };

  // Scroll to the latest user message
  this.scrollToLatestUserMessage = function() {
    const $messageList = $('.js-message-list');
    const $latestMessage = $('.my-message').last();

    if ($latestMessage.length > 0) {
      // Scroll to the latest message with animation
      $messageList.animate({
        scrollTop: $latestMessage.offset().top - $messageList.offset().top + $messageList.scrollTop()
      }, 0);
    }
  };

  // Adjust textarea height based on content
  this.adjustTextareaHeight = function(textbox) {
    setTimeout(function() {
      initTextbox(textbox);
      const scrollHeight = textbox.scrollHeight;
      const maxHeight = 200;
  
      if (scrollHeight > maxHeight) {
        // If content exceeds max height, set max height and show scrollbar
        textbox.style.maxHeight = maxHeight + 'px';
        textbox.style.height = maxHeight + 'px';
        textbox.style.overflow = 'auto';
      } else {
        // Set height based on content and hide scrollbar
        textbox.style.height = scrollHeight + 'px';
        textbox.style.overflow = 'hidden';
      }
    }, 0);
  };

  // Initialize input textbox height
  this.initInputTextbox = function(textbox) {
    initTextbox(textbox);
  };
  
  function initTextbox(textbox) {
    const initialHeight = 2 * parseFloat(getComputedStyle(textbox).fontSize);
    const paddingTop = parseFloat(getComputedStyle(textbox).paddingTop);
    const paddingBottom = parseFloat(getComputedStyle(textbox).paddingBottom);
    textbox.style.height = initialHeight + paddingTop + paddingBottom + 'px';
  }
}