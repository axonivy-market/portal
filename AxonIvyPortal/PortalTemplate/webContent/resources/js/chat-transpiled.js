"use strict";

function asyncGeneratorStep(gen, resolve, reject, _next, _throw, key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { Promise.resolve(value).then(_next, _throw); } }

function _asyncToGenerator(fn) { return function () { var self = this, args = arguments; return new Promise(function (resolve, reject) { var gen = fn.apply(self, args); function _next(value) { asyncGeneratorStep(gen, resolve, reject, _next, _throw, "next", value); } function _throw(err) { asyncGeneratorStep(gen, resolve, reject, _next, _throw, "throw", err); } _next(undefined); }); }; }

var isEdge = window.navigator.userAgent.indexOf("Edge") > -1;
var isFireFox = navigator.userAgent.toLowerCase().indexOf("firefox") > -1;
var isIE11 = navigator.userAgent.toLowerCase().indexOf("trident") > -1 && navigator.userAgent.toLowerCase().indexOf("rv:11") > -1;
var isIphone = navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i);
var messages = [];
var currentIndex;
var numberOfLoad = 20;
var numberToApplyLazyLoad = 50;
var recipientName;
var originalMessageTemplate;
var jsMessageList;
var clientId = new Date().getTime();
var isChatDeactivated = false;
var hasPendingRequestForSendersOfUnreadMessages = false;
var hidden, visibilityChange;

if (typeof document.hidden !== "undefined") {
  // Opera 12.10 and Firefox 18 and later support 
  hidden = "hidden";
  visibilityChange = "visibilitychange";
} else if (typeof document.msHidden !== "undefined") {
  hidden = "msHidden";
  visibilityChange = "msvisibilitychange";
} else if (typeof document.webkitHidden !== "undefined") {
  hidden = "webkitHidden";
  visibilityChange = "webkitvisibilitychange";
}

function IvyUri() {
  this.rest = function () {
    var baseUri = window.location.origin; // e.g. http://localhost:8080

    return baseUri + contextPath + "/api";
  };
}

function Chat(uri, view) {
  // current service uri: e.g. "http://localhost:8081/ivy/api/designer/chat"
  this.uri = uri;
  var chatGroupMemoryPrefix = "Case-";

  function getGroups() {
    jQuery.ajax({
      type: "GET",
      contentType: "text/plain",
      url: uri + "/groups/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      }
    });
  }

  this.getSendersOfUnreadMessages = function () {
    if (hasPendingRequestForSendersOfUnreadMessages === true) {
      return;
    }

    hasPendingRequestForSendersOfUnreadMessages = true;
    setTimeout(function () {
      jQuery.ajax({
        type: "GET",
        url: uri + "/unread/senders",
        crossDomain: true,
        async: true,
        cache: false,
        headers: {
          "X-Requested-By": "ivy"
        },
        success: function success(response) {
          view.clearAllUnreadBadge();

          if (response.length) {
            var chatGroupMemoryPrefixPattern = "^" + chatGroupMemoryPrefix;

            for (var i = 0; i < response.length; i++) {
              if (response[i].match(chatGroupMemoryPrefixPattern)) {
                var caseId = response[i].replace(chatGroupMemoryPrefix, "");
                view.updateGroupNotification(caseId);
              } else {
                view.updatePrivateNotification(response[i]);
              }
            }
          } else {
            $("#toggle-chat-panel-command").removeAttr("data-badge");
          }
        }
      });
      hasPendingRequestForSendersOfUnreadMessages = false;
    }, 300);
  };

  this.markReadGroupMessages = function (caseId) {
    jQuery.ajax({
      type: "POST",
      contentType: "text/plain",
      url: uri + "/group/read/" + caseId + "/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      }
    });
  };

  this.reloadChat = function () {
    clientId = new Date().getTime();
    this.listen(true, "INITIAL_RESPONSE_ID", "CHAT_LONG_POLLING_REQUEST");
    isChatDeactivated = false;

    if ($(".js-top-menu").hasClass("mod-chat-active")) {
      if (isChatGroupEnabled === 'true') {
        getGroups();
      }

      if (isChatPrivateEnabled === 'true') {
        getUsers();
      }
    }

    if ($(".js-show-chat-message.active.loaded").length == 1) {
      this.loadChat($(".js-show-chat-message.active.loaded").get(0), true);
      chat.markReadMessages();
    }

    if ($(".js-show-group-chat-message.active.loaded").length == 1) {
      var caseId = $(".js-show-group-chat-message.active.loaded").find("input[class='js-case-id']").get(0).value;
      chat.loadChatGroup(caseId, true);
      chat.markReadGroupMessages(caseId);
    }

    this.getSendersOfUnreadMessages();
  };

  this.listen = /*#__PURE__*/function () {
    var _ref = _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee(isFirstCall, lastResponseId, lastResponseStatus) {
      var path, jsonResponse, response, currentResponseId, _sender, _sender2, _messages, responseRecipients, sender, responseCaseId, $activeGroup, activeGroupCaseId, recipient;

      return regeneratorRuntime.wrap(function _callee$(_context) {
        while (1) {
          switch (_context.prev = _context.next) {
            case 0:
              path = "messages";

              if (isFirstCall !== true) {
                path += "-next";
              }

              path += "/" + clientId + "/" + lastResponseId + "/" + lastResponseStatus;
              _context.next = 5;
              return fetch(uri + "/" + path, {
                method: "POST",
                mode: 'cors',
                credentials: "include",
                headers: {
                  "X-Requested-By": "ivy",
                  "Content-Type": "application/json",
                  "Accept": "application/json"
                }
              });

            case 5:
              jsonResponse = _context.sent;

              if (!(jsonResponse.status >= 400)) {
                _context.next = 9;
                break;
              }

              console.error("Response error with HTTP code: " + jsonResponse.status + ", the browser stops sending long-polling request for messages.");
              return _context.abrupt("return");

            case 9:
              _context.next = 11;
              return jsonResponse.json();

            case 11:
              response = _context.sent;
              currentResponseId = response.id;

              if (!(response.status === "CHAT_REACHED_LIMITED_CONNECTION" || response.status === "SERVER_TIMEOUT")) {
                _context.next = 17;
                break;
              }

              currentResponseId = lastResponseId;
              _context.next = 20;
              break;

            case 17:
              if (!(response.status === "DEACTIVATE_CHAT")) {
                _context.next = 20;
                break;
              }

              if (!document[hidden]) {
                setTimeout(function () {
                  chat.reloadChat();
                }, 5000 - (new Date().getTime() - clientId));
              } else {
                isChatDeactivated = true;
              }

              return _context.abrupt("return");

            case 20:
              this.listen(false, currentResponseId, response.status); // wait for next update

              if (!(response.action === "updateUserStatus")) {
                _context.next = 25;
                break;
              }

              view.updateUserOnlineStatus(response.content);
              _context.next = 53;
              break;

            case 25:
              if (!(response.action === "getUsers")) {
                _context.next = 30;
                break;
              }

              view.renderUsers(response.content);
              chat.getSendersOfUnreadMessages();
              _context.next = 53;
              break;

            case 30:
              if (!(response.action === "getGroups")) {
                _context.next = 34;
                break;
              }

              view.renderGroupList(response.content);
              _context.next = 53;
              break;

            case 34:
              if (!(response.action === "readPrivateMessage")) {
                _context.next = 40;
                break;
              }

              _sender = response.content;
              view.hideNotificationForReadMessages(_sender);
              view.updateUnreadUserBadge();
              _context.next = 53;
              break;

            case 40:
              if (!(response.action === "readGroupMessage")) {
                _context.next = 46;
                break;
              }

              _sender2 = response.content;
              view.hideNotificationForReadGroupMessages(_sender2);
              view.updateUnreadUserBadge();
              _context.next = 53;
              break;

            case 46:
              if (!(response.action === "getMessages")) {
                _context.next = 53;
                break;
              }

              _context.next = 49;
              return response.content;

            case 49:
              _messages = _context.sent;
              responseRecipients = _messages["recipients"][0];
              sender = _messages["sender"];

              if (responseRecipients.indexOf(chatGroupMemoryPrefix) != -1) {
                // group chat
                responseCaseId = responseRecipients.replace(chatGroupMemoryPrefix, "");
                $activeGroup = $(".js-show-group-chat-message.active");

                if ($activeGroup.length == 0 && sender != userName) {
                  // do not have active group chat: handle notification
                  view.updateGroupNotification(responseCaseId); // update notification
                }

                if ($activeGroup.length > 0) {
                  // have active group chat:
                  activeGroupCaseId = $(".js-show-group-chat-message.active").find("input[class='js-case-id']").get(0).value;

                  if (responseCaseId == activeGroupCaseId && $(".js-chat-panel").hasClass("message-displayed")) {
                    // message sent from active group: render message
                    view.renderGroupMessage(_messages);
                    this.markReadGroupMessages(responseCaseId);
                    view.updateUnreadUserBadge();
                  } else if (sender != userName) {
                    // message sent from another group or from another user: handle notification
                    view.updateGroupNotification(responseCaseId); // update notification
                  }
                }
              } else {
                // private chat
                recipient = $(".js-contact-card-name", ".js-show-chat-message.active").text();

                if (sender == recipient) {
                  view.renderMessage(_messages); // update UI

                  chat.markReadMessages();
                  view.updateUnreadUserBadge();
                } else {
                  //render message for active chat boxes with recipient in other tabs 
                  if (userName == sender && $(".js-chat-panel").hasClass("active") && recipient == responseRecipients) {
                    view.renderMyMessage(_messages);
                  }

                  if (userName != sender) {
                    $("#toggle-chat-panel-command").attr("data-badge", " ");

                    if ($(".js-chat-panel").hasClass("active")) {
                      view.updatePrivateNotification(sender); // update notification
                    }
                  }
                }
              }

            case 53:
            case "end":
              return _context.stop();
          }
        }
      }, _callee, this);
    }));

    return function (_x, _x2, _x3) {
      return _ref.apply(this, arguments);
    };
  }();

  this.loadChat = function (recipient, isInputMessageKept) {
    var recipientName = $(recipient).find(".js-contact-card-name", ".js-show-chat-message.active").text();
    var $notification = $(recipient).find(".js-notification");

    if (!$notification.hasClass("u-hidden")) {
      $notification.empty().addClass("u-hidden");
    }

    jQuery.ajax({
      type: "GET",
      contentType: "application/json",
      url: uri + "/messages/" + recipientName,
      crossDomain: true,
      async: true,
      cache: false,
      success: function success(response) {
        view.clearMessages(isInputMessageKept);
        view.renderMessageList(response, recipientName);
        view.updateUnreadUserBadge();
      }
    });
  };

  function sendPrivateMessage() {
    if ($(".js-input-message").val() === "") {
      return;
    }

    var message = {
      "message": $(".js-input-message").val().trim()
    };
    var recipient = $(".js-contact-card-name", ".js-show-chat-message.active").text();
    jQuery.ajax({
      type: "POST",
      contentType: "text/plain",
      url: uri + "/" + recipient + "/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      },
      data: message.message,
      complete: function complete() {
        view.renderMyMessage(message);
        $(".js-input-message").val(""); // Clear input chat
      }
    });
  }

  function sendGroupMessage() {
    if ($(".js-input-message").val() === "") {
      return;
    }

    var message = {
      "message": $(".js-input-message").val().trim()
    };
    var caseId = $(".js-show-group-chat-message.active").find("input[class='js-case-id']").get(0).value;
    var recipient = $(".js-contact-card-name", ".js-show-chat-message.active").text();
    jQuery.ajax({
      type: "POST",
      contentType: "text/plain",
      url: uri + "/group/" + caseId + "/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      },
      data: message.message,
      complete: function complete() {
        view.renderMyMessage(message);
        $(".js-input-message").val(""); // Clear input chat
      }
    });
  }

  this.markReadMessages = function () {
    var sender = $(".js-contact-card-name", ".js-show-chat-message.active").text();
    jQuery.ajax({
      type: "POST",
      contentType: "text/plain",
      url: uri + "/read/" + sender + "/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      }
    });
  };

  function getUsers() {
    jQuery.ajax({
      type: "GET",
      contentType: "text/plain",
      url: uri + "/users/" + clientId,
      crossDomain: true,
      cache: false,
      headers: {
        "X-Requested-By": "ivy"
      }
    });
  }

  $("#toggle-chat-panel-command").off().click(function (e) {
    e.stopImmediatePropagation();
    view.renderChatMessagePanelUIWhenOpen(); // Hide environment info when open chat

    var portalEnvironment = $('.js-portal-environment');

    if (portalEnvironment.length > 0) {
      portalEnvironment.addClass('u-hidden');
    }

    if ($(".js-top-menu").hasClass("mod-chat-active")) {
      if (isChatGroupEnabled === 'true') {
        getGroups();
      }

      if (isChatPrivateEnabled === 'true') {
        getUsers();
      }
    }

    return false;
  });
  this.sendMessage = /*#__PURE__*/_asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee2() {
    return regeneratorRuntime.wrap(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            $(".js-contact-card-name", ".js-show-chat-message.active").length > 0 ? sendPrivateMessage() : sendGroupMessage();

          case 1:
          case "end":
            return _context2.stop();
        }
      }
    }, _callee2);
  }));

  this.onKeyEvent = function (e) {
    if (e.ctrlKey && e.keyCode == 13) {
      var messageInputField = document.getElementById("message-input-field");
      var position = messageInputField.selectionEnd;
      messageInputField.value = messageInputField.value.substring(0, position) + "\n" + messageInputField.value.substring(position);
      messageInputField.selectionEnd = position + 1;
      messageInputField.seletionStart = position + 1;
      messageInputField.scrollTop = messageInputField.scrollHeight;
    } else if (e.keyCode === 13) {
      e.preventDefault();
      chat.sendMessage();
    }
  };

  this.loadChatGroup = function (caseId, isInputMessageKept) {
    var $groupNotification = $(".js-case-id:hidden[value='" + caseId + "']").closest(".js-show-group-chat-message").find(".js-notification");
    ;

    if (!$groupNotification.hasClass("u-hidden")) {
      $groupNotification.addClass("u-hidden");
    }

    jQuery.ajax({
      type: "GET",
      contentType: "application/json",
      url: uri + "/group/" + caseId,
      crossDomain: true,
      async: true,
      cache: false,
      success: function success(response) {
        view.clearMessages(isInputMessageKept);
        view.renderMessageListForGroup(response, userName);
        view.updateUnreadUserBadge();
      }
    });
  };

  this.getParticipantsForGroupChat = /*#__PURE__*/_asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee3() {
    var caseId;
    return regeneratorRuntime.wrap(function _callee3$(_context3) {
      while (1) {
        switch (_context3.prev = _context3.next) {
          case 0:
            caseId = $('.js-show-group-chat-message.active').find("input[class='js-case-id']").get(0).value;
            jQuery.ajax({
              type: "GET",
              url: uri + "/group/participants/" + caseId,
              crossDomain: true,
              async: true,
              cache: false,
              success: function success(response) {
                view.renderParticipantsForGroup(response);
              }
            });

          case 2:
          case "end":
            return _context3.stop();
        }
      }
    }, _callee3);
  }));
}

function View(uri) {
  var existingUsers = [];
  var existingGroups = [];

  this.updateUserOnlineStatus = function (user) {
    updateUserOnline(user);
  };

  function updateUserOnline(user) {
    if (user) {
      var contactCardStatus = $(".contact-card.js-show-chat-message").find(".js-contact-card-name").filter(function () {
        return $(this).text() === user.name;
      }).parent().find(".js-contact-card-status");

      if (user.isOnline) {
        contactCardStatus.removeClass("is-offline");
      } else {
        contactCardStatus.addClass("is-offline");
      }
    }
  }

  this.renderGroupList = function (groupChats) {
    var groupChatContainer = document.getElementsByClassName("js-group-chat-container")[0];
    var groupTemplate = document.getElementsByClassName("js-group-template")[0];
    updateExistingGroups();
    var deletedGroupIds = [];
    var groupChatIds = groupChats.map(function (groupChat) {
      return groupChat.caseId;
    });
    existingGroups.forEach(function (existingGroupId) {
      if (groupChatIds.indexOf(existingGroupId) == -1) {
        deletedGroupIds.push(existingGroupId);
      }
    });

    if (groupChats.length > 0) {
      $('.js-no-group-chat-template').addClass('u-hidden');
      groupChats.forEach(function (groupChat) {
        if (existingGroups.length == 0 || existingGroups.indexOf(groupChat.caseId) == -1) {
          var groupChatName = groupChatFormat;
          var cloneGroup = initCloneGroup(groupTemplate, groupChat.caseId);
          Object.keys(groupChat).forEach(function (key) {
            groupChatName = groupChatName.replace("{" + key + "}", groupChat[key]);

            if (key === 'params' && groupChat[key] !== null) {
              Object.keys(groupChat[key]).forEach(function (param) {
                groupChatName = groupChatName.replace("{" + param + "}", groupChat[key][param]);
              });
            }
          });
          cloneGroup.getElementsByClassName("js-group-card-name")[0].innerText = groupChatName;
          cloneGroup.getElementsByClassName("js-group-card-name")[0].title = groupChatName;
          groupChatContainer.appendChild(cloneGroup);
        }
      });
    } else {
      var noGroupChatTemplate = document.getElementsByClassName('js-no-group-chat-template')[0];
      noGroupChatTemplate.classList.remove("u-hidden");
      groupChatContainer.appendChild(noGroupChatTemplate);
    } //remove deleted groups


    $(".js-show-group-chat-message").each(function (index) {
      var $caseId = $(this).find("input[class='js-case-id']");

      if ($caseId.length > 0) {
        var currentGroupId = parseInt($caseId[0].value);

        if (!isNaN(currentGroupId) && deletedGroupIds.indexOf(currentGroupId) > -1) {
          $(this).remove();
        }
      }
    });

    if ($("#chat-panel").hasClass("active")) {
      chat.getSendersOfUnreadMessages();
    }

    $(".js-close-message-list").off("click").click(closeChatMessagePanel);
    $("#chat-message-list").scroll(scrollChatPanel);
  };

  this.renderUsers = function (users) {
    var contactList = document.getElementsByClassName("js-private-chat-container")[0];
    var contactTemplate = document.getElementsByClassName("js-contact-template")[0];
    var i = 0;
    updateExistingUsers();

    if (users.length > 0) {
      users.forEach(function (user) {
        var userName = user.name;

        if (existingUsers.length == 0 || existingUsers.indexOf(userName) == -1) {
          var cloneContact = initCloneUser(contactTemplate);
          cloneContact.id = cloneContact.id + "-" + i++;
          cloneContact.getElementsByClassName("js-contact-card-name")[0].innerText = userName;

          if (user.isOnline == false) {
            $(cloneContact.getElementsByClassName("js-contact-card-status")[0]).addClass("is-offline");
          }

          contactList.appendChild(cloneContact);
        } else {
          updateUserOnline(user);
        }
      });
    }

    $(".js-close-message-list").off("click").click(closeChatMessagePanel);
    $("#chat-message-list").scroll(scrollChatPanel);
  };

  function scrollChatPanel() {
    var messagePanel = document.getElementById("chat-message-list");

    if (messages.length > numberToApplyLazyLoad && messagePanel.scrollTop == 0 && currentIndex >= 0) {
      setValueForMessageComponent();

      if (currentIndex - numberOfLoad <= 0) {
        for (var i = currentIndex - 1; i >= 0; i--) {
          renderMessageFunc(messages[i], messages[i].sender == recipientName, false);
        }
      } else {
        for (var i = currentIndex - 1; i >= currentIndex - numberOfLoad; i--) {
          renderMessageFunc(messages[i], messages[i].sender == recipientName, false);
        }

        messagePanel.scrollTop = 10;
      }

      currentIndex -= numberOfLoad;
      updateMessageListForIE11();
    }
  }

  function setValueForMessageComponent() {
    originalMessageTemplate = document.getElementsByClassName("js-message-template")[0];
    jsMessageList = document.getElementsByClassName("js-message-list")[0];
  }

  this.renderMessage = function (message) {
    setValueForMessageComponent();
    renderMessageFunc(message, false, true);
    updateMessageListForIE11();
  };

  this.renderMyMessage = function (message) {
    setValueForMessageComponent();
    renderMessageFunc(message, true, true);
    updateMessageListForIE11();
  };

  this.renderGroupMessage = function (message) {
    setValueForMessageComponent();
    renderMessageFunc(message, message.sender === userName, true);
    updateMessageListForIE11();
  };

  this.renderMessageListForGroup = function (messageList, recipientName) {
    recipientName = recipientName;
    setValueForMessageComponent();

    if (messageList.length <= numberToApplyLazyLoad) {
      messages = [];
      messageList.forEach(function (message) {
        renderMessageFunc(message, message.sender == recipientName, true);
      });
    } else {
      messages = messageList;
      currentIndex = messages.length - numberOfLoad;
      messages.slice(currentIndex, messages.length).forEach(function (message) {
        renderMessageFunc(message, message.sender == recipientName, true);
      });
      var messagePanel = document.getElementById("chat-message-list");
      messagePanel.scrollTop = messagePanel.scrollHeight;
    }

    updateMessageListForIE11();
  };

  this.renderMessageList = function (messageList, recipientName) {
    recipientName = recipientName;
    setValueForMessageComponent();

    if (messageList.length <= numberToApplyLazyLoad) {
      messages = [];
      messageList.forEach(function (message) {
        renderMessageFunc(message, message.sender != recipientName, true);
      });
    } else {
      messages = messageList;
      currentIndex = messages.length - numberOfLoad;
      messages.slice(currentIndex, messages.length).forEach(function (message) {
        renderMessageFunc(message, message.sender != recipientName, true);
      });
      var messagePanel = document.getElementById("chat-message-list");
      messagePanel.scrollTop = messagePanel.scrollHeight;
    }

    updateMessageListForIE11();
  };

  function renderMessageFunc(message, isMyMessage, isRenderedAtTheEnd) {
    var cloneTemplate = originalMessageTemplate.cloneNode(true);
    cloneTemplate.getElementsByClassName("js-message")[0].innerText = message.message;
    $(cloneTemplate).removeClass("u-hidden").removeClass("js-message-template");
    var timeElem = cloneTemplate.getElementsByClassName("js-time")[0];
    timeElem.innerText = generateTimestamp(message);
    var tooltipElem = cloneTemplate.getElementsByClassName("js-tooltip")[0];
    tooltipElem.innerText = generateTimestampWithDate(message);
    timeElem.innerText = ", " + timeElem.innerText;
    var senderElem = cloneTemplate.getElementsByClassName("js-sender")[0];
    var senderOfPreviousMessage = getSenderOfPreviousMessage();
    var timeOfPreviousMessage = getTimestampOfPreviousMessage();
    senderElem.innerText = message.sender;

    if (!(senderOfPreviousMessage === undefined || senderOfPreviousMessage.innerText != message.sender)) {
      if (timeOfPreviousMessage !== undefined && timeElem.innerHTML == timeOfPreviousMessage.innerText) {
        $(senderElem).addClass("u-hidden");
        $(timeElem).addClass("u-hidden");
      }
    }

    if (isMyMessage) {
      $(cloneTemplate).addClass("my-message");
      $(senderElem).addClass("u-hidden");
      $(timeElem)[0].innerHTML = $(timeElem)[0].innerHTML.replace(", ", "");

      if (timeOfPreviousMessage !== undefined && timeElem.innerHTML == timeOfPreviousMessage.innerText) {
        $(timeElem).addClass("u-hidden");
      }
    }

    appendMessageToList(cloneTemplate, isRenderedAtTheEnd);
    return cloneTemplate;
  }

  function appendMessageToList(cloneTemplate, isRenderedAtTheEnd) {
    var $messageList = $(jsMessageList);

    if (isEdge || isFireFox || isIE11) {
      isRenderedAtTheEnd ? $messageList.append(cloneTemplate) : $messageList.prepend(cloneTemplate);

      if (!$(cloneTemplate).hasClass("my-message") && isFireFox) {
        var $chatMessages = $(".js-chat-panel.active").find(".js-message-list").find(".chat-message");
        $messageList.animate({
          scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
        }, 0);
      }
    } else {
      isRenderedAtTheEnd ? $messageList.prepend(cloneTemplate) : $messageList.append(cloneTemplate);
    }

    if ($(cloneTemplate).hasClass("my-message") || isIE11) {
      var $chatMessages = $(".js-chat-panel.active").find(".js-message-list").find(".chat-message");
      setTimeout(function () {
        if (isEdge || isFireFox || isIE11) {
          $messageList.animate({
            scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
          }, 0);
        } else {
          $messageList.animate({
            scrollTop: $messageList.scrollTop() + $chatMessages.first().offset().top
          }, 0);
        }
      }, 0);
    }

    if ($(cloneTemplate).hasClass("my-message") && isIphone) {
      var $chatMessages = $(".js-chat-panel.active").find(".js-message-list").find(".chat-message");
      $messageList.animate({
        scrollTop: $messageList.scrollTop() + $chatMessages.last().offset().top
      }, 0);
    }
  }

  this.clearMessages = function (isInputMessageKept) {
    var messageList = document.getElementsByClassName("js-message-list")[0];
    messageList.innerText = "";

    if (!isInputMessageKept) {
      $(".js-input-message").val("");
    }
  };

  this.renderChatMessagePanelUIWhenOpen = function () {
    $("#chat-panel").toggleClass("active");
    $(".js-top-menu").toggleClass("mod-chat-active");
    $(".js-chat-contact-panel").toggleClass("active");
  };

  this.renderNotifications = function (unreadCounter) {
    var $notifications = $(".js-show-chat-message").find(".js-notification");

    if (!$notifications.hasClass("u-hidden")) {
      $notifications.empty().addClass("u-hidden");
    }

    jQuery.each(unreadCounter, function (index, counterJson) {
      var counter = jQuery.parseJSON(counterJson);
      var userTemplate = $(".js-contact-card-name:contains('" + counter["key"] + "')").filter(function () {
        return $(this).text() === counter["key"];
      }).closest(".js-show-chat-message");
      var notification = $(userTemplate.get(0)).find(".js-notification");
      notification.removeClass("u-hidden");
    });
    view.updateUnreadUserBadge();
  };

  this.updateGroupNotification = function (sender) {
    var groupNotification = $(".js-case-id:hidden[value='" + sender + "']").closest(".js-show-group-chat-message").find(".js-notification");
    groupNotification.removeClass("u-hidden");
    $("#toggle-chat-panel-command").attr("data-badge", " ");
  };

  this.hideNotificationForReadMessages = function (sender) {
    var userNotification = $(".js-contact-card-name:contains('" + sender + "')").filter(function () {
      return $(this).text() === sender;
    }).closest(".js-show-chat-message").find(".js-notification");
    userNotification.addClass("u-hidden");
  };

  this.hideNotificationForReadGroupMessages = function (sender) {
    var groupNotification = $(".js-case-id:hidden[value='" + sender + "']").closest(".js-show-group-chat-message").find(".js-notification");
    groupNotification.addClass("u-hidden");
  };

  this.updatePrivateNotification = function (sender) {
    var userNotification = $(".js-contact-card-name:contains('" + sender + "')").filter(function () {
      return $(this).text() === sender;
    }).closest(".js-show-chat-message").find(".js-notification");
    userNotification.removeClass("u-hidden");
    $("#toggle-chat-panel-command").attr("data-badge", " ");
  };

  this.renderParticipantsForGroup = function (participants) {
    renderUserParticipantGroup(participants.users);
    renderRoleParticipantGroup(participants);
  };

  function renderUserParticipantGroup(users) {
    var $usersParticipants = $('.js-users-participants>.participants-list');
    $usersParticipants.find('li').remove();

    if (users !== undefined && users.length > 0) {
      $('.js-users-participants-header').removeClass('u-hidden');
      $('.js-users-participants').removeClass('u-hidden');
      $(users).each(function (index, user) {
        var userDom = document.createElement('li');
        userDom.innerHTML = user;
        $usersParticipants.get(0).appendChild(userDom);
      });
    } else {
      $('.js-users-participants-header').addClass('u-hidden');
      $('.js-users-participants').addClass('u-hidden');
    }
  }

  function renderRoleParticipantGroup(participants) {
    var $rolesParticipants = $('.js-roles-participants>.participants-list');
    $rolesParticipants.get(0).innerHTML = '';

    for (var key in participants) {
      if (key != 'users') {
        var participant = participants[key];
        var roleGroupDom = document.createElement('li');
        var roleName = document.createElement('span');
        roleName.className = 'js-role-name role-name';
        roleName.innerHTML = key;
        roleGroupDom.appendChild(roleName);
        var userList = document.createElement('ul');
        userList.className = 'users-in-role';

        if (participant.length != 0) {
          for (var username in participant) {
            var userDom = document.createElement('li');
            userDom.innerHTML = participant[username];
            userList.appendChild(userDom);
          }
        } else {
          var userDom = document.createElement('li');
          userDom.innerHTML = $('.js-no-users-of-role').val();
          userList.appendChild(userDom);
        }

        roleGroupDom.appendChild(userList);
        $rolesParticipants.get(0).appendChild(roleGroupDom);
      }
    }

    PF('participants-list-dialog').initPosition();
  }

  function initCloneGroup(groupTemplate, groupId) {
    var cloneGroup = groupTemplate.cloneNode(true);
    $(cloneGroup).bind("click", function () {
      addActionListenerForShowChatMessageButton($(this));

      if (!$(this).hasClass("loaded")) {
        var chat = new Chat(uri, view);
        $(this).toggleClass("loaded");

        if (!$(".js-chat-message-container").hasClass("active")) {
          $(".js-chat-message-container").toggleClass("active");
          $(".js-chat-contact-panel").toggleClass("hidden-chat-contact");
        }
      }

      var $chatMessageContainer = $(".js-chat-message-container", "#chat-panel");

      if (!$chatMessageContainer.hasClass('group-chat-panel')) {
        $chatMessageContainer.addClass('group-chat-panel');
      }
    });
    var hiddenInput = $(cloneGroup).find("input[class='js-case-id']").get(0);
    $(hiddenInput).val(groupId);
    $(cloneGroup).removeClass("u-hidden").removeClass("js-group-template");
    return cloneGroup;
  }

  function initCloneUser(contactTemplate) {
    var cloneContact = contactTemplate.cloneNode(true);
    var view = new View(uri);
    $(cloneContact).bind("click", function () {
      // Use class "loaded" to prevent unnecessary web service call when
      // user click on active chat contact
      $(".js-show-chat-message").not(this).removeClass("loaded");
      addActionListenerForShowChatMessageButton($(this));

      if (!$(this).hasClass("loaded")) {
        var chat = new Chat(uri, view);
        chat.loadChat(this);
        chat.markReadMessages();
        $(this).toggleClass("loaded");

        if (!$(".js-chat-message-container").hasClass("active")) {
          $(".js-chat-message-container").toggleClass("active");
          $(".js-chat-contact-panel").toggleClass("hidden-chat-contact");
        }
      }

      $(".js-chat-message-container", "#chat-panel").removeClass('group-chat-panel');
    });
    $(cloneContact).removeClass("u-hidden").removeClass("js-contact-template");
    return cloneContact;
  }

  function addActionListenerForShowChatMessageButton(element) {
    // group chat
    if ($(element).hasClass("js-show-group-chat-message")) {
      var caseId = $(element).find("input[class='js-case-id']").get(0).value;
      chat.loadChatGroup(caseId);
      chat.markReadGroupMessages(caseId);
    }

    $(".js-show-chat-message").not(element).removeClass("active").removeClass("loaded");
    $(".js-show-group-chat-message").not(element).removeClass("active").removeClass("loaded");
    $(element).addClass("active");
    $("#chat-panel").addClass("message-displayed");
    $(".js-top-menu").addClass("mod-chat-message-displayed");
    $(".js-message-chat-header-name").text($(element).text());
  }

  function closeChatMessagePanel() {
    $("#chat-panel").removeClass("message-displayed");
    $(".js-chat-contact-panel").removeClass("hidden-chat-contact");
    $(".js-top-menu").removeClass("mod-chat-message-displayed");
    $(".js-show-chat-message").removeClass("active loaded");
    $(".js-show-group-chat-message").removeClass("active loaded");
    $(".js-chat-message-container").removeClass("active");
  }

  this.closeChatPanel = function () {
    closeChatMessagePanel();
    this.renderChatMessagePanelUIWhenOpen(); // Show environment info when open chat

    var portalEnvironment = $('.js-portal-environment');

    if (portalEnvironment.length > 0) {
      portalEnvironment.removeClass('u-hidden');
    }
  };

  function updateExistingGroups() {
    var groupContainer = document.getElementsByClassName("js-group-chat-container")[0];
    var groupId = groupContainer.getElementsByClassName("js-case-id");

    if (groupId.length > 0) {
      for (var i = 0; i < groupId.length; i++) {
        var caseId = groupId[i];

        if (existingGroups.indexOf(parseInt(caseId.value)) == -1) {
          existingGroups.push(parseInt(caseId.value));
        }
      }
    }
  }

  function updateExistingUsers() {
    var contactList = document.getElementsByClassName("js-private-chat-container")[0];
    var contactNames = contactList.getElementsByClassName("js-contact-card-name");

    for (var i = 0; i < contactNames.length; i++) {
      var name = contactNames[i];

      if (existingUsers.indexOf(name.textContent) == -1) {
        existingUsers.push(name.textContent);
      }
    }
  }

  function generateTimestamp(message) {
    var sentDate = message.sentDate === undefined ? new Date() : new Date(message.sentDate);
    var amOrPm = sentDate.getHours() < 12 ? "AM" : "PM";
    var hour = sentDate.getHours() < 12 ? sentDate.getHours() : sentDate.getHours() - 12;
    var formattedHour = hour < 10 ? '0' + hour : hour;
    var formattedMinute = sentDate.getMinutes() < 10 ? '0' + sentDate.getMinutes() : sentDate.getMinutes();
    return formattedHour + ":" + formattedMinute + " " + amOrPm;
  }

  function generateTimestampWithDate(message) {
    var sentDate = message.sentDate === undefined ? new Date() : new Date(message.sentDate);
    var date = sentDate.getDate();
    var month = sentDate.getMonth() + 1;
    var year = sentDate.getFullYear();

    if (date < 10) {
      date = '0' + date;
    }

    if (month < 10) {
      month = '0' + month;
    }

    return date + '.' + month + '.' + year + ' ' + generateTimestamp(message);
  }

  function getSenderOfPreviousMessage() {
    if (isEdge || isFireFox || isIE11) {
      var senders = $(".js-message-list")[0].getElementsByClassName("js-sender");
      return senders[senders.length - 1];
    } else {
      return $(".js-message-list")[0].getElementsByClassName("js-sender")[0];
    }
  }

  function getTimestampOfPreviousMessage() {
    if (isEdge || isFireFox || isIE11) {
      var previousTimeElem = $(".js-message-list")[0].getElementsByClassName("js-time");
      return previousTimeElem[previousTimeElem.length - 1];
    } else {
      return $(".js-message-list")[0].getElementsByClassName("js-time")[0];
    }
  }

  function updateMessageListForIE11() {
    if (isIE11) {
      $(".js-message-list").css("height", "auto");
      var margin = $('.message-list-content').get(0).offsetHeight - $(".js-message-list").get(0).scrollHeight - $('.js-chat-send-form').outerHeight(true);

      if (margin < 0) {
        $(".js-message-list").css("margin-top", "");
        $(".js-message-list").css("height", "calc(100% - 235px)");
      } else {
        $(".js-message-list").css("height", "auto");
        $(".js-message-list").css("margin-top", margin + "px");
      }
    }
  }

  this.updateUnreadUserBadge = function () {
    $("#toggle-chat-panel-command").removeAttr("data-badge");
    $(".js-notification").each(function () {
      if (!$(this).hasClass("u-hidden")) {
        $("#toggle-chat-panel-command").attr("data-badge", " ");
        return;
      }
    });
  };

  this.clearAllUnreadBadge = function () {
    $("#toggle-chat-panel-command").removeAttr("data-badge");
    $(".js-notification").each(function () {
      if (!$(this).hasClass("u-hidden")) {
        $(this).addClass("u-hidden");
      }
    });
  };

  $(window).resize(function () {
    updateMessageListForIE11();
    scrollToLastedMessage();
  });

  function scrollToLastedMessage() {
    var margin = $('.message-list-content').get(0).offsetHeight - $(".js-message-list").get(0).scrollHeight - $('.js-chat-send-form').outerHeight(true);

    if (margin < 0) {
      var messagePanel = document.getElementById("chat-message-list");
      messagePanel.scrollTop = messagePanel.scrollHeight;
    }
  }
}