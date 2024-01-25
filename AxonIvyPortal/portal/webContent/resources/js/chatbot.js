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
function ChatBot(ivyUri, uri, view) {
  this.uri = uri;
  this.ivyUri = ivyUri;

  // Bind events for UI elements
  this.bindEvents = function() {
    // Bind event for window
    window.addEventListener('resize', (event) => {
      view.init();
    });

    // Bind events for text box interactions
    const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
    if (!textbox) {
      console.error('Textbox is undefined. Make sure to provide a valid element.');
      return;
    }

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
        event.preventDefault();
        this.sendMessage(event.srcElement);
        return;
      }

      // Adjust textarea height for other cases
      view.adjustTextareaHeight(event.srcElement);
    });

    // Bind event for candidate questions
    const candidateQuestionBlocks = $('.js-candidate-question-block');
    if (!candidateQuestionBlocks || candidateQuestionBlocks.length == 0) {
      return;
    }

    for (let i = 0; i < candidateQuestionBlocks.length; i++) {
      candidateQuestionBlocks.get(i).addEventListener('click', (event) => {

        // Create message object and send it
        const inputMessage = $(event.currentTarget).find('.js-candidate-question').text();
        const selectedMessage = { 'message': inputMessage };

        // Render own message
        view.renderMyMessage(selectedMessage);

        // Clear input textbox and initialize
        textbox.value = '';
        view.initInputTextbox(textbox);

        // Scroll to latest message
        view.scrollToLatestMessage();

        sendChatMessage(this.ivyUri, this.uri, view, inputMessage);
      });
    }
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

    // Render own message
    view.renderMyMessage(message);

    // Clear input textbox and initialize
    textbox.value = '';
    view.initInputTextbox(textbox);

    // Scroll to latest message
    view.scrollToLatestMessage();

    await sendChatMessage(this.ivyUri, this.uri, view, inputMessage);
  };

  this.onClickSendMessage = function(event) {
    // Send message on click then initialize input textbox
    const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
    this.sendMessage(textbox);
  };

  // Function to send chat messages
  async function sendChatMessage(ivyUri, uri, view, message) {
    if (message == 'image' || message == 'json' || message == 'link' || message == 'frame') {
      try {
        // Send AJAX request to server
        const response = await $.ajax({
          type: 'POST',
          contentType: 'application/json',
          url: ivyUri + '/' + clientId,
          crossDomain: true,
          cache: false,
          headers: { 'X-Requested-By': 'ivy' },
          data: JSON.stringify({ 'message': message }),
        });
  
        // Check if the response is valid JSON
  
        // Render the response message
        const parsedResponse = JSON.parse(response);
        view.renderMessage(parsedResponse.message);
  
        // Scroll to latest message
        view.scrollToLatestMessage();
      } catch (error) {
        console.error('Error sending chat message:', error);
      }
    } else {
      try {
        // Send AJAX request to the chatbot server
        const response = await fetch(uri + '/chat/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'X-Requested-By': 'ivy',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST',
          },
          body: JSON.stringify({ 'message': message, 'session_id': '123' }),
        });
  
        // Read the response as a stream of data
        const reader = response.body.getReader();
        const decoder = new TextDecoder("utf-8");
        result = '';
  
        while (true) {
          const { done, value } = await reader.read();
          if (done) {
            break;
          }
  
          // Massage and parse the chunk of data
          const chunk = decoder.decode(value);
          result += chunk;
          view.renderMessage(result);
          view.scrollToLatestMessage();
        }
      } catch (error) {
        console.error('Error sending chat message:', error);
      }
    }
  }
}

// View class for rendering chat messages
function View(uri) {
  this.init = function() {
    $('.js-layout-main').addClass('chatbot-layout-main');
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
    const message = isMyMessage ? messageWrapper.message : parseMessage(messageWrapper);

    // Set message content
    cloneTemplate.getElementsByClassName('js-message')[0].innerHTML = message;
    $(cloneTemplate).removeClass('u-hidden').removeClass('js-message-template');

    // Set width to auto if has iframe inside the chat message
    if (cloneTemplate.querySelector('iframe') != null) {
      $(cloneTemplate).find('.chatbot-meta').get(0).style.width = 'auto';
    }

    if (isMyMessage) {
      // Add class for own message
      $(cloneTemplate).addClass('my-message');
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
  this.scrollToLatestMessage = function() {
    const $messageList = $('.js-message-list');
    // Scroll to the bottom of the message list with animation
    $messageList.animate({
      scrollTop: $messageList[0].scrollHeight
    }, 500);
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
    // Initial height of the textbox equals to 5 times font size
    const initialHeight = 5 * parseFloat(getComputedStyle(textbox).fontSize);
    const paddingTop = parseFloat(getComputedStyle(textbox).paddingTop);
    const paddingBottom = parseFloat(getComputedStyle(textbox).paddingBottom);
    textbox.style.height = initialHeight + paddingTop + paddingBottom + 'px';
  }
}