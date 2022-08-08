package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.log;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;
import static ch.ivy.addon.portalkit.enums.GlobalVariable.CHAT_MAX_CONNECTION;
import static ch.ivy.addon.portalkit.enums.GlobalVariable.CHAT_RESPONSE_TIMEOUT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.HashSet;
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
import java.util.function.Supplier;
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

import com.axonivy.portal.component.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.RedeploymentUtils;
import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.cluster.restricted.IClusterManager;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.server.restricted.EngineMode;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import io.swagger.v3.oas.annotations.Hidden;

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
 * <li>Each action is recorded in chat history, e.g. sending messages, marking chat as read, user online/offline. The
 * history helps prevent actions lost if unavailable AsyncResponse.</li>
 * </ul>
 */
@SuppressWarnings("restriction")
@Hidden
@Path("chat")
@Singleton
public class ChatService {

  private static final String UPDATE_USER_STATUS_ACTION = "updateUserStatus";
  private static final String GET_GROUPS_ACTION = "getGroups";
  private static final String GET_USERS_ACTION = "getUsers";
  private static final String GET_MESSAGES_ACTION = "getMessages";
  private static final String READ_GROUP_MESSAGE_ACTION = "readGroupMessage";
  private static final String READ_PRIVATE_MESSAGE_ACTION = "readPrivateMessage";
  private static final List<String> ACTIONS_FOR_ONE_CLIENT = Arrays.asList(GET_USERS_ACTION, GET_GROUPS_ACTION);
  private static final String CHAT_RESPONSE_TIMEOUT_STATUS = "SERVER_TIMEOUT";
  private static final String CHAT_RESPONSE_CHAT_REACHED_LIMITED_CONNECTION_STATUS = "CHAT_REACHED_LIMITED_CONNECTION";
  private static final String CHAT_RESPONSE_DEACTIVATE_CHAT_STATUS = "DEACTIVATE_CHAT";
  private static final String ERROR = "ERROR";
  private static final String SUCCESSFUL = "SUCCESSFUL";
  private Map<String, Queue<ResponseInfo>> messageResponses = new ConcurrentHashMap<>();
  private Map<String, List<GroupChat>> usernameToGroupChats = new ConcurrentHashMap<>();
  private Map<String, Integer> reachedLimitedConnectionCounters = new ConcurrentHashMap<>();
  /** Only necessary if in cluster */
  private static String nodeName;
  public static final boolean IS_STANDARD_MODE = EngineMode.isNot(EngineMode.ENTERPRISE);

  @POST
  @Path("/messages/{clientId}/{lastResponseId}/{lastResponseStatus}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void registerMessage(@Suspended AsyncResponse response, @PathParam("clientId") String clientId) {
    resetReachedLimitedConnectionCounter();
    if (ChatReferencesContainer.getChatService() == null) {
      ChatReferencesContainer.setChatService(this);
      ChatReferencesContainer.registerIvyExtension();
      if (!IS_STANDARD_MODE) {
        ClusterChatEventListener.register();
        nodeName = DiCore.getGlobalInjector().getInstance(IClusterManager.class).getLocalClusterNode().getName();
      }
    }
    Queue<ResponseInfo> responses = getResponses();
    // unhandled chat responses happen because of clicking chat button before async response is created
    ChatResponse unhandledChatResponse =
        getChatResponseFromHistory(() -> getUnhandledResponseWhenRegisteringMessage(clientId), sessionUserName());
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
      increaseReachedLimitedConnectionCounter();
    }
    if (getReachedLimitedConnectionCounter() >= getMaxChatConnectionPerUser()) {
      resetReachedLimitedConnectionCounter();
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
    List<UnreadChatMessage> unreadMessages = ChatMessageManager.getUnreadMessages(sessionUserName());
    return ListUtils.emptyIfNull(unreadMessages).stream().map(UnreadChatMessage::getSenderId)
        .map(senderId -> ChatMessageManager.getSender(senderId)).filter(Objects::nonNull).distinct()
        .collect(Collectors.toList());
  }

  @POST
  @Path("/read/{participant}/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response readMessage(@PathParam("participant") String participant, @PathParam("clientId") String clientId) {
    handleAction(() -> performReadingMessage(participant, clientId, sessionUserName()),
        () -> ClusterChatEventSender.readMessage(participant, clientId, sessionUserName()));
    return Response.ok(SUCCESSFUL).build();
  }

  public synchronized void performReadingMessage(String participant, String clientId, String actor) {
    ChatMessageManager.deletedReadMessages(actor, participant);
    ChatResponse lastChatResponse =
        getChatResponseFromHistory(() -> ConcurrentChatUtils.getRecentChatResponseHistory(actor).peekLast(), actor);
    if (lastChatResponse != null && !isDuplicatedAction(participant, lastChatResponse, READ_PRIVATE_MESSAGE_ACTION)) {
      resumeAsyncResponse(actor, new ChatResponse(READ_PRIVATE_MESSAGE_ACTION, participant), clientId, actor);
    }
  }

  @POST
  @Path("/group/read/{caseId}/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response readGroupMessage(@PathParam("caseId") String caseId, @PathParam("clientId") String clientId) {
    handleAction(() -> performReadingGroupMessage(caseId, clientId, sessionUserName()),
        () -> ClusterChatEventSender.readGroupMessage(caseId, clientId, sessionUserName()));
    return Response.ok(SUCCESSFUL).build();
  }

  public synchronized void performReadingGroupMessage(String caseId, String clientId, String actor) {
    ChatMessageManager.deletedReadMessagesForGroupChat(actor, caseId);
    ChatResponse lastChatResponse =
        getChatResponseFromHistory(() -> ConcurrentChatUtils.getRecentChatResponseHistory(actor).peekLast(), actor);
    if (lastChatResponse != null && !isDuplicatedAction(caseId, lastChatResponse, READ_GROUP_MESSAGE_ACTION)) {
      resumeAsyncResponse(actor, new ChatResponse(READ_GROUP_MESSAGE_ACTION, caseId), clientId, actor);
    }
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
  public Response sendPrivateMessage(String messageText, @PathParam("receiver") String receiver,
      @PathParam("clientId") String clientId) {
    handleAction(() -> performSendingPrivateMessage(messageText, receiver, clientId, sessionUserName(), nodeName),
        () -> ClusterChatEventSender.sendPrivateMessage(messageText, receiver, clientId, nodeName));
    return Response.ok(SUCCESSFUL).build();
  }

  public synchronized void performSendingPrivateMessage(String messageText, String receiver, String clientId,
      String actor, String nodeName) {
    if (ChatGroupUtils.findUserByUsername(receiver) == null) {
      return;
    }
    ChatMessage message = new ChatMessage(actor, Arrays.asList(receiver), messageText);
    ChatMessageManager.storeUnreadMessage(message);

    ChatResponse chatResponse = new ChatResponse(GET_MESSAGES_ACTION, message, clientId);
    // If receiver is online, send message directly to receiver's response.
    resumeAsyncResponse(receiver, chatResponse, clientId, actor);
    resumeAsyncResponse(actor, chatResponse, clientId, actor);
    if (StringUtils.equals(ChatService.nodeName, nodeName)) {
      ChatMessageManager.savePersonalMessage(message);
    }
  }

  /**
   * Load all message for a group chat
   * 
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
   * 
   * @param messageText
   * @param caseId
   * @param clientId id from browser tab
   * @return response
   */
  @POST
  @Path("/group/{caseId}/{clientId}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public Response sendGroupMessage(String messageText, @PathParam("caseId") String caseId,
      @PathParam("clientId") String clientId) {
    List<GroupChat> availableGroups = usernameToGroupChats.get(sessionUserName());

    if (CollectionUtils.isNotEmpty(availableGroups)
        && availableGroups.stream().anyMatch(group -> group.getCaseId() == Long.parseLong(caseId))) {
      handleAction(() -> performSendingGroupMessage(messageText, caseId, clientId, sessionUserName(), nodeName),
          () -> ClusterChatEventSender.sendGroupMessage(messageText, caseId, clientId, nodeName));
      return Response.ok(SUCCESSFUL).build();
    }
    return Response.ok(ERROR).build();
  }

  public synchronized void performSendingGroupMessage(String messageText, String caseId, String clientId, String actor,
      String nodeName) {
    ChatMessage message = new ChatMessage(actor, messageText, caseId);
    if (StringUtils.equals(ChatService.nodeName, nodeName)) {
      ChatMessageManager.saveGroupMessage(message, caseId);
    }
    Set<String> members = ChatGroupUtils.getUserNamesFromGroup(Long.parseLong(caseId));

    // Find online users of current group chat to resume new message
    ChatResponse chatResponse = new ChatResponse(GET_MESSAGES_ACTION, message, clientId);
    for (String member : members) {
      resumeAsyncResponse(member, chatResponse, clientId, actor);
    }
    ChatMessageManager.storeUnreadMessageForGroupChat(message, Long.parseLong(caseId));
  }

  /**
   * Send user status to other users
   * 
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
    ChatResponse chatResponse = new ChatResponse(GET_USERS_ACTION, contacts, clientId);
    resumeAsyncResponseForOneClient(sessionUserName(), chatResponse, clientId);
    return Response.ok(SUCCESSFUL).build();
  }

  @GET
  @Path("/group/participants/{caseId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Set<String>> getParticipantsForGroupChat(@PathParam("caseId") String caseId) {
    return ChatGroupUtils.getParticipantsFromGroup(Long.parseLong(caseId));
  }

  public void handleUserOnline(String username) {
    handleAction(() -> performUpdatingUserStatus(username, true),
        () -> ClusterChatEventSender.handleUserOnline(username));
  }

  public void handleUserOffline(String username) {
    handleAction(() -> performHandlingUserOffline(username), () -> ClusterChatEventSender.handleUserOffline(username));
  }

  public synchronized void performHandlingUserOffline(String username) {
    messageResponses.remove(username);
    usernameToGroupChats.remove(username);
    performUpdatingUserStatus(username, false);
  }

  @GET
  @Path("/groups/{clientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response loadAllGroupChat(@PathParam("clientId") String clientId) {
    String sessionUserName = sessionUserName();
    List<GroupChat> groupChats = findAllChatGroups();
    ChatResponse chatResponse = new ChatResponse(GET_GROUPS_ACTION, groupChats, clientId);
    resumeAsyncResponseForOneClient(sessionUserName, chatResponse, clientId);
    usernameToGroupChats.put(sessionUserName, groupChats);
    return Response.ok(SUCCESSFUL).build();
  }

  public void updateGroupList(GroupChat groupChat) {
    handleAction(() -> performUpdatingGroupList(groupChat), () -> ClusterChatEventSender.updateGroupList(groupChat));
  }

  public synchronized void performUpdatingGroupList(GroupChat groupChat) {
    Iterator<Entry<String, List<GroupChat>>> it = usernameToGroupChats.entrySet().iterator();
    Set<String> groupUserNames = ChatGroupUtils.getAllUsersFromUserIdsAndRoleNames(groupChat.getAssigneeNames());
    while (it.hasNext()) {
      Entry<String, List<GroupChat>> pair = it.next();
      String userName = pair.getKey();
      if (groupUserNames.contains(userName)) {
        List<GroupChat> groupChats = pair.getValue();
        groupChats.add(groupChat);
        ChatResponse chatResponse = new ChatResponse(GET_GROUPS_ACTION, groupChats);
        resumeAsyncResponse(userName, chatResponse, EMPTY, EMPTY);
      }
    }
  }

  private List<GroupChat> findAllChatGroups() {
    ObjectMapper mapper = new ObjectMapper();
    CaseQuery caseQuery = buildCaseQuery();
    List<ICase> caseWithNoneEmptyGroupChatInfo = wf().getCaseQueryExecutor().getResults(caseQuery);

    List<GroupChat> result = caseWithNoneEmptyGroupChatInfo.stream()
        .filter(iCase -> isUserInvolvedInGroup(iCase.getId(), sessionUserName())).map(iCase -> {
          try {
            return mapper.readValue(iCase.customFields()
                .stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).get().orElse(StringUtils.EMPTY),
                GroupChat.class);
          } catch (PersistencyException | IOException e) {
            log().error(e);
            return null;
          }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    for (GroupChat group : result) {
      Set<SecurityMemberDTO> assignees = new HashSet<>();
      assignees.addAll(group.getAssignees());
      Set<String> assigneeNames = new HashSet<>();
      assigneeNames
          .addAll(group.getAssigneeNames().stream().filter(name -> name.startsWith("#")).collect(Collectors.toList()));
      assignees.stream().filter(assignee -> assignee.isUser()).forEach(assignee -> {
        for (String name : assigneeNames) {
          if (assignee.getId() == Long.parseLong(name.substring(1))) {
            group.getAssigneeNames().remove(name);
            group.getAssigneeNames().add(assignee.getName());
          }
        }
      });
    }

    return result;
  }

  private CaseQuery buildCaseQuery() {
    return CaseUtils.createBusinessCaseQuery().where().customField()
        .stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).isNotNull().and().state()
        .isNotEqual(CaseState.DONE).and().state().isNotEqual(CaseState.DESTROYED);
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

  public synchronized void performUpdatingUserStatus(String username, boolean isOnline) {
    List<String> contactStrings = new ArrayList<>();
    ChatContact contact = new ChatContact(username, isOnline);
    contactStrings.add(toJson(contact));
    ChatResponse chatResponse = new ChatResponse(UPDATE_USER_STATUS_ACTION, contact);
    for (String receiver : messageResponses.keySet()) {
      resumeAsyncResponse(receiver, chatResponse, EMPTY, EMPTY);
    }
  }

  private void resumeAsyncResponse(String username, ChatResponse chatResponse, String excludedClientId, String actor) {
    ConcurrentChatUtils.getRecentChatResponseHistory(username).add(chatResponse);
    Queue<ResponseInfo> responses = getResponses(username);
    if (CollectionUtils.isEmpty(responses)) {
      return;
    }
    Queue<ResponseInfo> clonedResponses = new ConcurrentLinkedQueue<>(responses);
    responses.clear();
    if (username.equals(actor)) {
      clonedResponses.stream().filter(info -> excludedClientId.equals(info.getClientId())).findFirst()
          .ifPresent(responseInfo -> {
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
    return new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).setPrettyPrinting()
        .disableHtmlEscaping().create().toJson(object);
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
      log().error("Chat response timeout must be a Long number, check Portal settings again.", e);
    }
    return false;
  }

  private String getChatResponseTimeoutValue() {
    return new GlobalSettingService().findGlobalSettingValue(CHAT_RESPONSE_TIMEOUT);
  }

  private int getMaxChatConnectionPerUser() {
    String maxConnection = new GlobalSettingService().findGlobalSettingValue(CHAT_MAX_CONNECTION);
    int maxConnectionNumber = 0;
    try {
      if (StringUtils.isNotBlank(maxConnection)) {
        int configuredMaxConnection = Integer.parseInt(maxConnection);
        if (configuredMaxConnection > 0 && configuredMaxConnection < 6) {
          maxConnectionNumber = configuredMaxConnection;
        }
      }
    } catch (NumberFormatException e) {
      log().error("Chat max connection per user must be an Integer number, check Portal settings again.", e);
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

  private int getReachedLimitedConnectionCounter() {
    Integer counter = reachedLimitedConnectionCounters.get(sessionUserName());
    if (counter == null) {
      counter = 0;
      reachedLimitedConnectionCounters.put(sessionUserName(), counter);
    }
    return counter;
  }

  private void increaseReachedLimitedConnectionCounter() {
    reachedLimitedConnectionCounters.put(sessionUserName(), getReachedLimitedConnectionCounter() + 1);
  }

  private void resetReachedLimitedConnectionCounter() {
    reachedLimitedConnectionCounters.put(sessionUserName(), 0);
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
    ResponseInfo responseInfoToDeactivate = responses.stream()
        .min((info1, info2) -> Long.valueOf(info1.getClientId()).compareTo(Long.valueOf(info2.getClientId()))).get();
    responses.remove(responseInfoToDeactivate);
    responseInfoToDeactivate.getAsyncResponse().resume(toJson(chatResponse));
  }

  private ChatResponse getUnhandledResponse(String clientId, String lastResponseId) {
    ChatResponse lastUnhandledHistoryEntry = null;
    // lastResponseId = "INITIAL_RESPONSE_ID" when chat button is not clicked yet, but chat is deactivated due to too
    // many tabs, then the tab is selected again.
    if (!lastResponseId.equals("INITIAL_RESPONSE_ID")) {
      Iterator<ChatResponse> it =
          ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).descendingIterator();
      ChatResponse currentHistoryEntry = null;
      while (it.hasNext()) {
        if (isHistoryEntryRelatedToCurrentRequest(clientId, currentHistoryEntry)) {
          lastUnhandledHistoryEntry = currentHistoryEntry;
        }
        try {
          currentHistoryEntry = it.next();
        } catch (ClassCastException e) {
          log().info("PMV could be redeployed", e);
          continue;
        }
        if (lastResponseId.equals(currentHistoryEntry.getId())) {
          break;
        }
      }
    }
    return lastUnhandledHistoryEntry;
  }

  private boolean isHistoryEntryRelatedToCurrentRequest(String clientId, ChatResponse historyEntry) {
    return historyEntry != null && (historyEntry.getClientId() == null
        || (historyEntry.getClientId().equals(clientId) && ACTIONS_FOR_ONE_CLIENT.contains(historyEntry.getAction()))
        || (!historyEntry.getClientId().equals(clientId)
            && !ACTIONS_FOR_ONE_CLIENT.contains(historyEntry.getAction())));
  }

  private boolean isHistoryEntryOnlyForThisClient(String clientId, ChatResponse entry) {
    return clientId.equals(entry.getClientId()) && ACTIONS_FOR_ONE_CLIENT.contains(entry.getAction());
  }

  private boolean isDuplicatedAction(String content, ChatResponse lastChatResponse, String action) {
    return lastChatResponse != null && action.equals(lastChatResponse.getAction())
        && content.equals(lastChatResponse.getContent());
  }

  private ChatResponse getUnhandledResponseWhenRegisteringMessage(String clientId) {
    return ConcurrentChatUtils.getRecentChatResponseHistory(sessionUserName()).stream()
        .filter(entry -> isHistoryEntryOnlyForThisClient(clientId, entry)).findFirst().orElse(null);
  }

  private void handleAction(Runnable actionOnStandardMode, Runnable actionOnClusterMode) {
    if (IS_STANDARD_MODE) {
      actionOnStandardMode.run();
    } else {
      actionOnClusterMode.run();
    }
  }

  private ChatResponse getChatResponseFromHistory(Supplier<ChatResponse> supplier, String actor) {
    try {
      return supplier.get();
    } catch (ClassCastException e) {
      log().info("PMV could be redeployed", e);
      Deque<ChatResponse> history = ConcurrentChatUtils.getRecentChatResponseHistory(actor);
      RedeploymentUtils.filterObjectOfCurrentClassLoader(history, ChatResponse.class);
      return supplier.get();
    }
  }

}
