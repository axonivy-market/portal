package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.CHAT_RESPONSE_TIMEOUT;
import static ch.ivy.addon.portalkit.enums.GlobalVariable.CHAT_MAX_CONNECTION;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

/**
 * Chat service uses asynchronous REST communication:
 * 
 * <ul>
 * <li>Users that join the chat fire an asynchronous (@GET) request and wait for new messages.</li>
 * <li>New messages are sent synchronous (@Post) and will be distributed to asynchronous listeners.</li>
 * </ul>
 * 
 * Mechanism for multi-tab support:
 * <ul>
 * <li>Only one long-polling request for each tab</li>
 * <li>Each user has a list of AsyncResponse for multi-tab</li>
 * <li>Each action is recorded in chat history, e.g. sending messages, marking chat as read, user online/offline. The history helps prevent actions lost if unavailable AsyncResponse.</li>
 * </ul>
 */
@Path("chat")
@Singleton
public class ChatService {

  private static final String READ_GROUP_MESSAGE_ACTION = "readGroupMessage";
  private static final String READ_PRIVATE_MESSAGE_ACTION = "readPrivateMessage";
  private static final String CHAT_RESPONSE_TIMEOUT_STATUS = "SERVER_TIMEOUT";
  private static final String CHAT_RESPONSE_CHAT_REACHED_LIMITED_CONNECTION_STATUS = "CHAT_REACHED_LIMITED_CONNECTION";
  private static final String CHAT_RESPONSE_DEACTIVATE_CHAT_STATUS = "DEACTIVATE_CHAT";
  private static final String ERROR = "ERROR";
  private static final String SUCCESSFUL = "SUCCESSFUL";
  private Map<String, Queue<ResponseInfo>> messageResponses = new ConcurrentHashMap<>();
  private Map<String, List<GroupChat>> usernameToGroupChats = new ConcurrentHashMap<>();
  private int reachedLimitedConnectionCounter;

  @POST
  @Path("/messages/{clientId}/{lastResponseId}/{lastResponseStatus}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void registerMessage(@Suspended AsyncResponse response, @PathParam("clientId") String clientId) {
    reachedLimitedConnectionCounter = 0;
    if (ChatServiceContainer.getChatService() == null) {
      ChatServiceContainer.setChatService(this);
      ChatServiceContainer.registerSessionExtension();
    }
    Queue<ResponseInfo> responses = getResponses();
    // unhandled chat responses happen because of clicking chat button before async response is created
    ChatResponse unhandledChatResponse = ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).stream().filter(entry -> clientId.equals(entry.getClientId())).findFirst().orElse(null);
    if (unhandledChatResponse != null) {
      response.resume(toJson(unhandledChatResponse));
    } else {
      configureResponseTimeoutIfAny(response, messageResponses);
      responses.add(new ResponseInfo(response, clientId));
      informClientIfReachLimitedConnection(responses);
    }
  }

  @POST
  @Path("/messages-next/{clientId}/{lastResponseId}/{lastResponseStatus}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void reRegisterMessage(@Suspended AsyncResponse response, @PathParam("clientId") String clientId,
      @PathParam("lastResponseId") String lastResponseId, @PathParam("lastResponseStatus") String lastResponseStatus) {
    if (lastResponseStatus.equals(CHAT_RESPONSE_CHAT_REACHED_LIMITED_CONNECTION_STATUS)) {
      reachedLimitedConnectionCounter++;
    }
    if (reachedLimitedConnectionCounter >= getMaxChatConnectionPerUser()) {
      reachedLimitedConnectionCounter = 0;
      deactivateChat(response, clientId);
      return;
    }
    ChatResponse unhandledResponse = getUnhandledResponse(clientId, lastResponseId);
    if (unhandledResponse == null) { // no pending AsyncResponse
      configureResponseTimeoutIfAny(response, messageResponses);
      Queue<ResponseInfo> responses = getResponses();
      responses.add(new ResponseInfo(response, clientId));
      informClientIfReachLimitedConnection(responses);
    } else {
      response.resume(toJson(unhandledResponse));
    }
  }

  @GET
  @Path("/unread/senders")
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> getSendersOfUnreadMessages() {
    List<String> participants = Arrays.asList(sessionUserName());
    List<ChatMessage> unreadMessagesInMemory = ChatMessageManager.getUnreadMessagesInMemory(participants);
    
    return ListUtils.emptyIfNull(unreadMessagesInMemory)
      .stream()
      .map(ChatMessage::getSender)
      .distinct()
      .collect(Collectors.toList());
  }
  
  @POST
  @Path("/read/{participant}/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response readMessage(@PathParam("participant") String participant, @PathParam("clientId") String clientId) {
    ChatMessageManager.deletedReadMessagesInMemory(Arrays.asList(sessionUserName()), participant);
    ChatResponse lastChatResponse = ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).getLast();
    if (!isDuplicatedAction(participant, lastChatResponse, READ_PRIVATE_MESSAGE_ACTION)) {
      resumeAsyncResponse(sessionUserName(), new ChatResponse(READ_PRIVATE_MESSAGE_ACTION, participant), clientId);
    }
    return Response.ok(SUCCESSFUL).build();
  }

  @POST
  @Path("/group/read/{caseId}/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response readGroupMessage(@PathParam("caseId") String caseId, @PathParam("clientId") String clientId) {
    ChatMessageManager.deletedReadMessagesInMemoryForGroupChat(Arrays.asList(sessionUserName()), caseId);
    ChatResponse lastChatResponse = ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).getLast();
    if (!isDuplicatedAction(caseId, lastChatResponse, READ_GROUP_MESSAGE_ACTION)) {
      resumeAsyncResponse(sessionUserName(), new ChatResponse(READ_GROUP_MESSAGE_ACTION, caseId), clientId);
    }
    return Response.ok(SUCCESSFUL).build();
  }

  /**
   * Load previous messages between login user and another user
   * 
   * @param participant
   * @return previous messages
   */
  @GET
  @Path("/messages/{participant}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized List<ChatMessage> loadPreviousMessages(@PathParam("participant") String participant) {
    List<String> participants = Arrays.asList(sessionUserName(), participant);
    return ChatMessageManager.loadPersonalMessages(participants);
  }

  /**
   * Send message to another user
   * 
   * @param messageText
   * @param receiver
   * @param clientId id from browser tab
   * @return response successful
   */
  @POST
  @Path("/{receiver}/{clientId}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response sendPrivateMessage(String messageText, @PathParam("receiver") String receiver, @PathParam("clientId") String clientId) {
    ChatMessage message = new ChatMessage(sessionUserName(), Arrays.asList(receiver), messageText);
    ChatMessageManager.storeUnreadMessageInMemory(message);

    ChatResponse chatResponse = new ChatResponse("getMessages", message);
    // If receiver is online, send message directly to receiver's response.
    resumeAsyncResponse(receiver, chatResponse, clientId);
    resumeAsyncResponse(sessionUserName(), chatResponse, clientId);
    ChatMessageManager.savePersonalMessage(message);
    return Response.ok(SUCCESSFUL).build();
  }

  /**
   * Load all message for a group chat
   * @param caseId
   * @return message list
   */
  @GET
  @Path("/group/{caseId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized List<ChatMessage> loadPreviousGroupMessages(@PathParam("caseId") String caseId) {
    List<ChatMessage> messages = new ArrayList<>();
    List<GroupChat> availableGroups = usernameToGroupChats.get(sessionUserName());
    if (availableGroups.stream().anyMatch(group -> group.getCaseId() == Long.parseLong(caseId))) {
      messages = ChatMessageManager.loadGroupMessagesByCaseId(caseId);
    }
    return messages;
  }
  
  /**
   * Send message to a group chat for case
   * @param messageText
   * @param caseId
   * @param clientId id from browser tab 
   * @return response
   */
  @POST
  @Path("/group/{caseId}/{clientId}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response sendGroupMessage(String messageText, @PathParam("caseId") String caseId, @PathParam("clientId") String clientId) {
    String currentUsername = sessionUserName();
    List<GroupChat> availableGroups = usernameToGroupChats.get(currentUsername);

    if (CollectionUtils.isNotEmpty(availableGroups) && availableGroups.stream().anyMatch(group -> group.getCaseId() == Long.parseLong(caseId))) {
      ChatMessage message = new ChatMessage(currentUsername, messageText, caseId);
      ChatMessageManager.saveGroupMessage(message, caseId);
      Set<String> members = ChatGroupUtils.getUserNamesFromGroup(Long.parseLong(caseId));

      //Find online users of current group chat to resume new message
      ChatResponse chatResponse = new ChatResponse("getMessages", message);
      for (String member : members) {
        resumeAsyncResponse(member, chatResponse, clientId);
      }
      ChatMessageManager.storeUnreadMessageInMemoryForGroupChat(message, Long.parseLong(caseId));
      return Response.ok(SUCCESSFUL).build();
    }
    return Response.ok(ERROR).build();
  }

  /**
   * Send user status to other users
   * @param clientId id from browser tab 
   * 
   * @return response successful
   */
  @GET
  @Path("/users/{clientId}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response getUsers(@PathParam("clientId") String clientId) {
    List<ChatContact> contacts = ChatContactManager.loadOnlineContacts();
    ChatResponse chatResponse = new ChatResponse("getUsers", contacts);
    chatResponse.setClientId(clientId);
    resumeAsyncResponseForOneClient(sessionUserName(), chatResponse, clientId);
    return Response.ok(SUCCESSFUL).build();
  }
  
  @GET
  @Path("/group/participants/{caseId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Set<String>> getParticipantsForGroupChat(@PathParam("caseId") String caseId) {
    return ChatGroupUtils.getParticipantsFromGroup(Long.parseLong(caseId)); 
  }

  public synchronized void handleUserOnline(String username) {
    updateUserStatus(username, true);
  }

  public synchronized void handleUserOffline(String username) {
    messageResponses.remove(username);
    updateUserStatus(username, false);
  }

  @GET
  @Path("/groups/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response loadAllGroupChat(@PathParam("clientId") String clientId) {
    String sessionUserName = sessionUserName();
    List<GroupChat> groupChats = findAllChatGroups();
    ChatResponse chatResponse = new ChatResponse("getGroups", groupChats);
    chatResponse.setClientId(clientId);
    resumeAsyncResponseForOneClient(sessionUserName, chatResponse, clientId);
    usernameToGroupChats.put(sessionUserName, groupChats);
    return Response.ok(SUCCESSFUL).build();
  }

  public synchronized void updateGroupList(GroupChat groupChat) {
    Iterator<Entry<String, List<GroupChat>>> it = usernameToGroupChats.entrySet().iterator();
    Set<String> groupUserNames = ChatGroupUtils.getAllUsersFromAssigneeNames(groupChat.getAssigneeNames());
    while (it.hasNext()) {
      Entry<String, List<GroupChat>> pair = it.next();
      String userName = pair.getKey();
      if (groupUserNames.contains(userName)) {
        List<GroupChat> groupChats = pair.getValue();
        groupChats.add(groupChat);
        ChatResponse chatResponse = new ChatResponse("getGroups", groupChats);
        resumeAsyncResponse(userName, chatResponse, EMPTY);
      }
    }
  }

  private List<GroupChat> findAllChatGroups() {
    ObjectMapper mapper = new ObjectMapper();
    CaseQuery caseQuery = buildCaseQuery();
    List<ICase> caseWithNoneEmptyGroupChatInfo = Ivy.wf().getCaseQueryExecutor().getResults(caseQuery);
    
    return caseWithNoneEmptyGroupChatInfo.stream()
        .filter(iCase -> isUserInvolvedInGroup(iCase.getId(), sessionUserName()))
        .map(iCase -> {
          try {
            return mapper.readValue(iCase.customFields().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).get().orElse(StringUtils.EMPTY), GroupChat.class);
          } catch (PersistencyException | IOException e) {
            Ivy.log().error(e);
            return null;
          }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private CaseQuery buildCaseQuery() {
    return CaseUtils.createBusinessCaseQuery().where()
        .customField().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).isNotNull()
        .and().state().isNotEqual(CaseState.DONE)
        .and().state().isNotEqual(CaseState.DESTROYED);
  }
  
  private boolean isUserInvolvedInGroup(long caseId, String userName) {
      Set<String> groupChatUserNames = ChatGroupUtils.getUserNamesFromGroup(caseId);
      for (String name : groupChatUserNames) {
        if (name.equals(userName)) {
            return true;
        }
      }
      return false;
  }
  
  private synchronized void updateUserStatus(String username, boolean isOnline) {
    List<String> contactStrings = new ArrayList<>();
    ChatContact contact = new ChatContact(username, isOnline);
    contactStrings.add(toJson(contact));
    ChatResponse chatResponse = new ChatResponse("updateUserStatus", contact);
    for (String receiver : messageResponses.keySet()) {
      resumeAsyncResponse(receiver, chatResponse, EMPTY);
    }
  }

  private void resumeAsyncResponse(String username, ChatResponse chatResponse, String excludedClientId) {
    ConcurrentChatUtils.getRecentChatResponseHistory(username).add(chatResponse);
    Queue<ResponseInfo> responses = getResponses(username);
    if (CollectionUtils.isEmpty(responses)) {
      return;
    }
    Queue<ResponseInfo> clonedResponses = new ConcurrentLinkedQueue<>(responses);
    responses.clear();
    if (username.equals(sessionUserName())) {
      clonedResponses.stream().filter(info -> excludedClientId.equals(info.getClientId())).findFirst().ifPresent(responseInfo -> {
        responses.add(responseInfo);
        clonedResponses.remove(responseInfo);
      });
    }
    
    String jsonChat = toJson(chatResponse);
    for (ResponseInfo responseInfo : clonedResponses) {
      AsyncResponse asyncResponse = responseInfo.getAsyncResponse();
      if (asyncResponse != null && asyncResponse.isSuspended()) {
        asyncResponse.resume(jsonChat);
      }
    }
  }

  private String toJson(Object object) {
    return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(object);
  }

  private void resumeAsyncResponseForOneClient(String username, ChatResponse chatResponse, String clientId) {
    ConcurrentChatUtils.getRecentChatResponseHistory(username).add(chatResponse);
    ResponseInfo responseInfo =
        getResponses().stream().filter(info -> clientId.equals(info.getClientId())).findFirst().orElse(null);
    getResponses().remove(responseInfo);
    if (responseInfo != null) {
      String json = toJson(chatResponse);
      responseInfo.getAsyncResponse().resume(json);
    }
  }
  
  private void configureResponseTimeoutIfAny(AsyncResponse response, Map<String, Queue<ResponseInfo>> userToResponse) {
    if (isChatResponseTimeoutConfigured()) {
      Long chatResponseTimeout = Long.parseLong(getChatResponseTimeoutValue());
      response.setTimeout(chatResponseTimeout, TimeUnit.SECONDS);
      response.setTimeoutHandler(asyncResponse -> {
        for (Queue<ResponseInfo> queue : userToResponse.values()) {
          ResponseInfo responseInfo =
              queue.stream().filter(info -> info.getAsyncResponse().equals(response)).findFirst().orElse(null);
          if (responseInfo != null) {
            queue.remove(responseInfo);
            break;
          }
        }
        ChatResponse chatResponse = new ChatResponse(CHAT_RESPONSE_TIMEOUT_STATUS);
        response.resume(toJson(chatResponse));
      });
    }
  }

  private boolean isChatResponseTimeoutConfigured() {
    String chatResponseTimeout = getChatResponseTimeoutValue();
    try {
      return StringUtils.isNotBlank(chatResponseTimeout) && Long.parseLong(chatResponseTimeout) > 0;
    } catch (NumberFormatException e) {
      Ivy.log().error("Chat response timeout must be a Long number, check Portal settings again.", e);
    }
    return false;
  }

  private String getChatResponseTimeoutValue() {
    return new GlobalSettingService().findGlobalSettingValue(CHAT_RESPONSE_TIMEOUT.toString());
  }

  private int getMaxChatConnectionPerUser() {
    String maxConnection = new GlobalSettingService().findGlobalSettingValue(CHAT_MAX_CONNECTION.toString());
    int maxConnectionNumber = 0;
    try {
      if (StringUtils.isNotBlank(maxConnection)) {
        int configuredMaxConnection = Integer.parseInt(maxConnection);
        if (configuredMaxConnection > 0 && configuredMaxConnection < 6) {
          maxConnectionNumber = configuredMaxConnection;
        }
      }
    } catch (NumberFormatException e) {
      Ivy.log().error("Chat max connection per user must be an Integer number, check Portal settings again.", e);
    }
    if (maxConnectionNumber == 0) {
      maxConnectionNumber = Integer.parseInt(CHAT_MAX_CONNECTION.getDefaultValue());
    }
    return maxConnectionNumber;
  }
  
  private Queue<ResponseInfo> getResponses() {
    return getResponses(sessionUserName());
  }

  private Queue<ResponseInfo> getResponses(String username) {
    Queue<ResponseInfo> queue = messageResponses.get(username);
    if (queue == null) {
      queue = new ConcurrentLinkedQueue<>();
      messageResponses.put(username, queue);
    }
    return queue;
  }

    private String sessionUserName() {
      return Ivy.session().getSessionUserName();
    }

    private void informClientIfReachLimitedConnection(Queue<ResponseInfo> responses) {
      int maxConnection = getMaxChatConnectionPerUser();
      if (responses.size() > maxConnection) {
        int numberOfResponsesToResume = responses.size() - maxConnection;
        ChatResponse chatResponse = new ChatResponse(CHAT_RESPONSE_CHAT_REACHED_LIMITED_CONNECTION_STATUS);
        String json = toJson(chatResponse);
        for (int i = 0; i < numberOfResponsesToResume; i++) {
          responses.poll().getAsyncResponse().resume(json);
        }
      }
    }

    private void deactivateChat(AsyncResponse response, String clientId) {
      ChatResponse chatResponse = new ChatResponse(CHAT_RESPONSE_DEACTIVATE_CHAT_STATUS);
      Queue<ResponseInfo> responses = getResponses();
      responses.add(new ResponseInfo(response, clientId));
      ResponseInfo responseInfoToDeactivate = responses.stream().min((info1, info2) -> Long.valueOf(info1.getClientId()).compareTo(Long.valueOf(info2.getClientId()))).get();
      responses.remove(responseInfoToDeactivate);
      responseInfoToDeactivate.getAsyncResponse().resume(toJson(chatResponse));
    }

    private ChatResponse getUnhandledResponse(String clientId, String lastResponseId) {
      ChatResponse lastUnhandledHistoryEntry = null;
      // lastResponseId = "INITIAL_RESPONSE_ID" when chat button is not clicked yet, but chat is deactivated due to too many tabs, then the tab is selected again.
      if (!lastResponseId.equals("INITIAL_RESPONSE_ID")) {
        Iterator<ChatResponse> it = ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).descendingIterator();
        ChatResponse currentHistoryEntry = null;
        while (it.hasNext()) {
          if (isHistoryEntryRelatedToCurrentRequest(clientId, currentHistoryEntry)) {
            lastUnhandledHistoryEntry = currentHistoryEntry;
          }
          currentHistoryEntry = it.next();
          if (lastResponseId.equals(currentHistoryEntry.getId())) {
            break;
          }
        }
      }
      return lastUnhandledHistoryEntry;
    }

    private boolean isHistoryEntryRelatedToCurrentRequest(String clientId, ChatResponse historyEntry) {
      return historyEntry != null && (historyEntry.getClientId() == null || historyEntry.getClientId().equals(clientId));
    }

    private boolean isDuplicatedAction(String content, ChatResponse lastChatResponse, String action) {
      return lastChatResponse != null && action.equals(lastChatResponse.getAction()) && content.equals(lastChatResponse.getContent());
    }

}