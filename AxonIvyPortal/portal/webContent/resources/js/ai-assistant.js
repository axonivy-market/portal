// Detecting browser type
const isEdge = window.navigator.userAgent.includes('Edge');
const isFireFox = navigator.userAgent.toLowerCase().includes('firefox');
const isIphone = navigator.userAgent.match(/iPhone|iPod/i);
const IVY = 'IVY';
const RETRIEVAL_QA = 'RETRIEVAL_QA';
const VALIDATE_ERROR = 'ERROR';

// Global variables
let streaming = false;

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
    return window.location.origin + contextPath + '/api';
  };
}

// ChatBot class for handling chat interactions
function Assistant(ivyUri, uri, view, assistant, username) {
  this.uri = uri;
  this.ivyUri = ivyUri;
  this.assistant = assistant;
  this.username = username;

  // Bind events for UI elements
  this.bindEvents = function () {
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

        startThread(this.ivyUri, this.uri, view, inputMessage, this.assistant, this.username);
      });
    }
  };

  // Sending a message
  this.sendMessage = async function (textbox) {
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

    // If thread ID is empty, start thread
    const threadId = $('.js-thread-id');
    if (threadId.length == 0 || threadId.val() == '') {
      await startThread(this.ivyUri, this.uri, view, inputMessage, this.assistant, this.username);
    } else {
      // Otherwise, send message to continue the thread
    }

  };

  this.onClickSendMessage = function (event) {
    // Send message on click then initialize input textbox
    const textbox = $('.js-chat-send-form .js-chatbot-input-message').get(0);
    this.sendMessage(textbox);
  };

  // Function to start the chat thread
  async function startThread(ivyUri, uri, view, request, assistant, username) {
    view.disableSendButton();

    try {
      // Send AJAX request to the chatbot server
      const response = await callFetchApi(
        uri + 'startThread/',
        JSON.stringify({
          'request': request,
          'assistant': assistant,
          'username': username,
          'threadId': '',
          'session_id': '123'
        }));

      result = await getResultFromStreamingResponse(response);
      result = $.parseJSON(result);

      // If an error occurred when choosing tool,
      // show the error and end thread. 
      if (result.toolType == VALIDATE_ERROR) {
        view.renderMessage(result.response);
        view.enableSendButton();
        return;
      }

      // Handle result based on the tool type.
      if (result.toolType == RETRIEVAL_QA) {
        handleRetrievalQATool(uri, view, request, result, $.parseJSON(assistant));
      } else if (result.toolType == IVY) {
        proceedIvyTool(uri, ivyUri, view, result.response, assistant, result.threadId);
      } else {
        view.renderMessage(result);
      }
    } catch (error) {
      console.error('Error sending chat message:', error);
      view.enableSendButton();
    }
  }

  // Function to handle retrieval QA tool
  async function handleRetrievalQATool(uri, view, request, result, assistantObj) {
    // Get the result tool from AI server
    const resultTool = $.parseJSON(result.response);

    // Loop the tools of the assistant to choose the correct tool.
    var selectedTool = null;
    for (const tool of assistantObj.toolkit) {
      if (tool.id == resultTool.id) {
        selectedTool = tool;
        break;
      }
    }

    // Handle the selected tool,
    // If the selected tool is found and has the correct type, call AI server to answer the question
    // Otherwise, show error.
    if (selectedTool == null || selectedTool.type != RETRIEVAL_QA) {
      view.renderMessage("Cannot find proper tool to fulfill your request");
      view.enableSendButton();
    } else {
      answerQA(uri, view, request, selectedTool.collection, result);
    }
  }

  // Function to use Retrieval QA tool to answer user questions
  async function answerQA(uri, view, request, collection, info) {
    try {
      // Send AJAX request to the chatbot server
      const response = await callFetchApi(
        uri + 'answerQA/',
        JSON.stringify({
          'request': request,
          'collectionName': collection,
          'session_id': '123'
        }));

        const reader = response.body.getReader();
        const decoder = new TextDecoder("utf-8");
        result = '';
    
        while (true) {
          streaming = true;
          const { done, value } = await reader.read();
          if (done) {
            streaming = false;
            view.removeStreamingClassFromMessage();
            break;
          }
    
          // Message and parse the chunk of data
          const chunk = decoder.decode(value);
          result += chunk;

          view.renderMessage(result);
        }

        updateThread(uri, result, info.assistantId, info.username, info.threadId);

    } catch (error) {
      console.error('Error sending chat message:', error);
      view.enableSendButton();
    }
  }

  // Function to update thread
  async function updateThread(uri, result, assistantId, username, threadId) {
    try {
      // Send AJAX request to the chatbot server
      const response = await callFetchApi(
        uri + 'updateThread/',
        JSON.stringify({
          'response': result,
          'threadId': threadId,
          'assistantId': assistantId,
          'username': username,
          'session_id': '123'
        }));
    } catch (error) {
      console.error('Error when save thread message:', error);
      view.enableSendButton();
    }
  }

  // Function to proceed the ivy tool on Ivy engine
  async function proceedIvyTool(uri, ivyUri, view, toolJson, assistant, threadId) {
    try {
      // Send AJAX request to the AI server
      const response = await callJQueryAjaxToIvy(
        ivyUri + 'assistant/ivyTool',
        JSON.stringify({ 'toolJson': toolJson })
      );

      // After get the result from ivy, use the response to continue the thread on the AI server
      continueThread(uri, response, threadId, assistant, view);
    } catch (error) {
      console.error('Error sending chat message:', error);
    }
  }

  // Function to continue the thread on the AI server
  async function continueThread(uri, request, threadId, assistant, view) {
    try {
      // Send AJAX request to the AI server
      const response = await callFetchApi(
        uri + 'continueThread/',
        JSON.stringify({
          'request': request,
          'assistant': assistant,
          'username': username,
          'threadId': threadId,
          'session_id': '123'
        }));
      await showResultFromStreamingResponse(response);
    } catch (error) {
      console.error('Error sending chat message:', error);
    }
  }

  // Function to get the streaming result from an AJAX response
  async function getResultFromStreamingResponse(response) {
    // Read the response as a stream of data
    const reader = response.body.getReader();
    const decoder = new TextDecoder("utf-8");
    result = '';

    while (true) {
      const { done, value } = await reader.read();
      if (done) {
        break;
      }

      // Message and parse the chunk of data
      const chunk = decoder.decode(value);
      result += chunk;
    }
    return result;
  }

  async function showResultFromStreamingResponse(response) {
    const result = await getResultFromStreamingResponse(response);
    view.renderMessage(result);
    view.removeStreamingClassFromMessage();
  }
}

// View class for rendering chat messages
function ViewAI(uri) {
  this.init = function () {
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
  this.renderMessage = function (message) {
    // Set value for message components
    setValueForMessageComponent();
    // Render the message
    if (streaming) {
      updateMessageFunc(message);
    } else {
      renderNewMessageFunc(message, false);
    }
    this.scrollToLatestMessage();
  };

  // Rendering user's own messages
  this.renderMyMessage = function (message) {
    // Set value for message components
    setValueForMessageComponent();
    // Render own message
    renderNewMessageFunc(message, true);
  };

  // Helper function for rendering messages
  function renderNewMessageFunc(messageWrapper, isMyMessage) {
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

  // Helper function for update message
  function updateMessageFunc(messageWrapper) {
    // Clone message template
    const cloneTemplate = originalMessageTemplate.cloneNode(true);
    const message = parseMessage(messageWrapper);

    // Set message content
    cloneTemplate.getElementsByClassName('js-message')[0].innerHTML = message;
    $(cloneTemplate).removeClass('u-hidden').removeClass('js-message-template');

    // Set width to auto if has iframe inside the chat message
    if (cloneTemplate.querySelector('iframe') != null) {
      $(cloneTemplate).find('.chatbot-meta').get(0).style.width = 'auto';
    }

    // Update the streaming message
    updateStreamingMessage(cloneTemplate);
    return cloneTemplate;
  }

  // Helper function for appending messages to the message list
  function appendMessageToList(cloneTemplate) {
    const $messageList = $(jsMessageList);
    $messageList.append(cloneTemplate);

    if ($(cloneTemplate).hasClass('my-message')) {
      // If own message, scroll to the latest message
      const $chatMessages = $('.js-chatbot-message-list').find('.chat-message');
      setTimeout(function () {
        $messageList.animate({
          scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
        }, 0);
      }, 0);
    }
  }

  // Function to update streaming message
  function updateStreamingMessage(cloneTemplate) {
    const messageList = $(jsMessageList);
    const streamingMessage = messageList.find('.chat-message-container.streaming');

    // Create streaming message if not exist
    if (streamingMessage.length == 0) {
      $(cloneTemplate).addClass('streaming');
      messageList.append(cloneTemplate);
      return;
    }

    // Update existing streaming message
    streamingMessage.get(0).innerHTML = cloneTemplate.innerHTML;
  }

  // Function to remove the 'streaming' class from a message
  // after the streaming process is done.
  this.removeStreamingClassFromMessage = function () {
    const messageList = $(jsMessageList);
    const streamingMessage = messageList.find('.chat-message-container.streaming');
    streamingMessage.removeClass('streaming');
    this.enableSendButton();
  }

  // Add new line to the textbox
  this.addNewLineToTextbox = function (textbox) {
    const position = textbox.selectionEnd;
    // Insert new line and adjust height
    textbox.value = textbox.value.substring(0, position) + '\n' + textbox.value.substring(position);
    textbox.selectionEnd = position + 1;
    textbox.selectionStart = position + 1;
    textbox.scrollTop = textbox.scrollHeight;
  };

  // Scroll to the latest message
  this.scrollToLatestMessage = function () {
    const $messageList = $('.js-message-list');
    // Scroll to the bottom of the message list with animation
    $messageList.animate({
      scrollTop: $messageList[0].scrollHeight
    }, 0);
  };

  // Adjust textarea height based on content
  this.adjustTextareaHeight = function (textbox) {
    setTimeout(function () {
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
  this.initInputTextbox = function (textbox) {
    initTextbox(textbox);
  };

  function initTextbox(textbox) {
    // Initial height of the textbox equals to 5 times font size
    const initialHeight = 5 * parseFloat(getComputedStyle(textbox).fontSize);
    const paddingTop = parseFloat(getComputedStyle(textbox).paddingTop);
    const paddingBottom = parseFloat(getComputedStyle(textbox).paddingBottom);
    textbox.style.height = initialHeight + paddingTop + paddingBottom + 'px';
  }

  // Disable the send button, input text
  // and show typing dots.
  this.disableSendButton = function() {
    $('.js-chat-send-form').find('.ui-button').addClass('ui-state-disabled');
    $('.js-chat-send-form').find('.js-chatbot-input-message').addClass('ui-state-disabled');
    createTypingDots('js-chat-send-form');
  }

  // Enable the send button, input text
  // and show typing dots.
  this.enableSendButton = function() {
    $('.js-chat-send-form').find('.ui-button').removeClass('ui-state-disabled');
    $('.js-chat-send-form').find('.js-chatbot-input-message').removeClass('ui-state-disabled');
    removeTypingDots('js-chat-send-form');
  }
}