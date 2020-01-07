package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.CHAT_RESPONSE_TIMEOUT;

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
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.ISession;
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
 */
@Path("chat")
@Singleton
public class ChatService {

  private static final String CHAT_RESPONSE_TIMEOUT_STATUS = "SERVER_TIMEOUT";
  private static final String ERROR = "ERROR";
  private static final String NO_ASYNC_RESPONSE = "NO_ASYNC_RESPONSE";
  private static final String SUCCESSFUL = "SUCCESSFUL";
  private Map<String, AsyncResponse> messageResponses = new ConcurrentHashMap<>();
  private Map<String, AsyncResponse> userResponses = new ConcurrentHashMap<>();
  private Map<String, AsyncResponse> groupResponses = new ConcurrentHashMap<>();
  private Map<String, List<GroupChat>> usernameToGroupChats = new ConcurrentHashMap<>();

  @POST
  @Path("/messages")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void registerMessage(@Suspended AsyncResponse response) {
    if (ChatServiceContainer.getChatService() == null) {
      ChatServiceContainer.setChatService(this);
      ChatServiceContainer.registerSessionExtension();
    }
    Queue<ChatMessage> messageQueue = ConcurrentChatUtils.getPortalChatMessageQueue(sessionUserName());
    if (messageQueue != null) {
      messageQueue.clear();
    }
    String listener = sessionUserName();
    configureResponseTimeoutIfAny(response, messageResponses);
    messageResponses.put(listener, response);
  }

  @POST
  @Path("/messages-next")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void reRegisterMessage(@Suspended AsyncResponse response) {
    Queue<ChatMessage> messageQueue = ConcurrentChatUtils.getPortalChatMessageQueue(sessionUserName());
    if (CollectionUtils.isEmpty(messageQueue)) {
      String listener = sessionUserName();
      configureResponseTimeoutIfAny(response, messageResponses);
      messageResponses.put(listener, response);
    } else {
      ChatMessage message = messageQueue.remove();
      response.resume(message);

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
  @Path("/read/{participant}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response readMessage(@PathParam("participant") String participant) {
    ChatMessageManager.deletedReadMessagesInMemory(Arrays.asList(sessionUserName()), participant);
    return Response.ok(SUCCESSFUL).build();
  }
  
  @POST
  @Path("/group/read/{caseId}")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response readGroupMessage(@PathParam("caseId") String caseId) {
    ChatMessageManager.deletedReadMessagesInMemoryForGroupChat(Arrays.asList(sessionUserName()), caseId);
    
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
   * @return response successful
   */
  @POST
  @Path("/{receiver}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response sendPrivateMessage(String messageText, @PathParam("receiver") String receiver) {
    AsyncResponse receiverResponse = messageResponses.remove(receiver);
    ChatMessage message = new ChatMessage(sessionUserName(), Arrays.asList(receiver), messageText);
    ChatMessageManager.storeUnreadMessageInMemory(message);

    // If receiver is online, send message directly to receiver's response.
    if (receiverResponse != null && receiverResponse.isSuspended()) {
      receiverResponse.resume(message);
    } else if (ConcurrentChatUtils.isUserOnline(receiver)){
      Queue<ChatMessage> queue = ConcurrentChatUtils.getPortalChatMessageQueueOrInitIfNull(receiver);
      queue.add(message);
    }

    ChatMessageManager.savePersonalMessage(message);
    return Response.ok(SUCCESSFUL).build();
  }

  private String sessionUserName() {
    return Ivy.session().getSessionUserName();
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
   * @return response
   */
  @POST
  @Path("/group/{caseId}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response sendGroupMessage(String messageText, @PathParam("caseId") String caseId) {
    String currentUsername = sessionUserName();
    List<GroupChat> availableGroups = usernameToGroupChats.get(currentUsername);

    if (availableGroups.stream().anyMatch(group -> group.getCaseId() == Long.parseLong(caseId))) {
      ChatMessage message = new ChatMessage(currentUsername, messageText, caseId);
      ChatMessageManager.saveGroupMessage(message, caseId);
      Set<String> members = ChatGroupUtils.getUserNamesFromGroup(Long.parseLong(caseId));

      //Find online users of current group chat to resume new message
      List<AsyncResponse> onlineMembers = new ArrayList<>();
      Map<String, ISession> userNameToSession = null;
      for (String member : members) {
        if (messageResponses.containsKey(member) && !member.equals(currentUsername)) {
          onlineMembers.add(messageResponses.remove(member));
        } else if (!messageResponses.containsKey(member) && !member.equals(currentUsername)) {
          if (userNameToSession == null) {
            userNameToSession = ConcurrentChatUtils.getUserNameToSession();
          }
          if (userNameToSession.containsKey(member)) { // user online but no AsyncResponse
            Queue<ChatMessage> queue = ConcurrentChatUtils.getPortalChatMessageQueueOrInitIfNull(member);
            queue.add(message);
          }
        } 
      }

      for (AsyncResponse asyncResponse : onlineMembers) {
        if (asyncResponse != null && asyncResponse.isSuspended()) {
          asyncResponse.resume(message);
        }
      }

      ChatMessageManager.storeUnreadMessageInMemoryForGroupChat(message, Long.parseLong(caseId));
      return Response.ok(SUCCESSFUL).build();
    }
    return Response.ok(ERROR).build();
  }

  @POST
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void registerUserResponse(@Suspended AsyncResponse response) {
    String listener = sessionUserName();
    configureResponseTimeoutIfAny(response, userResponses);
    userResponses.put(listener, response);
  }

  /**
   * Send user status to other users
   * 
   * @return response successful
   */
  @GET
  @Path("/users")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response getUsers() {
    List<ChatContact> contacts = ChatContactManager.loadOnlineContacts();
    ChatResponse chatResponse = new ChatResponse();
    chatResponse.setAction("getUsers");
    chatResponse.setContent(contacts);
    String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(chatResponse);
    AsyncResponse response = userResponses.remove(sessionUserName());
    if (response != null) {
      response.resume(json);
      return Response.ok(SUCCESSFUL).build();
    } else {
      return Response.ok(NO_ASYNC_RESPONSE).build();
    }
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
    userResponses.remove(username);
    updateUserStatus(username, false);
  }
  
  @POST
  @Path("/groups")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void registerGroupResponse(@Suspended AsyncResponse response) {
    String listener = sessionUserName();
    configureResponseTimeoutIfAny(response, groupResponses);
    groupResponses.put(listener, response);
  }
  
  @GET
  @Path("/groups")
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized Response loadAllGroupChat() {
    String sessionUserName = sessionUserName();
    List<GroupChat> groupChats = findAllChatGroups();
    AsyncResponse response = groupResponses.remove(sessionUserName);
    if (response != null) {
      response.resume(groupChats);
      usernameToGroupChats.put(sessionUserName, groupChats);
      return Response.ok(SUCCESSFUL).build();
    } else {
      return Response.ok(NO_ASYNC_RESPONSE).build();
    }
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
        AsyncResponse response = groupResponses.get(userName);
        if (response != null) {
            response.resume(groupChats);
        }
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
          } catch (PersistencyException | EnvironmentNotAvailableException | IOException e) {
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
    String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(contact);
    contactStrings.add(json);
    ChatResponse chatResponse = new ChatResponse();
    chatResponse.setAction("updateUserStatus");
    chatResponse.setContent(contact);
    String jsonChat = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(chatResponse);
    for (Iterator<AsyncResponse> iterator = userResponses.values().iterator(); iterator.hasNext();) {
      AsyncResponse response = iterator.next();
      iterator.remove();
      response.resume(jsonChat);
    }
  }

  private void configureResponseTimeoutIfAny(AsyncResponse response, Map<String, AsyncResponse> userToResponse) {
    if (isChatResponseTimeoutConfigured()) {
      Long chatResponseTimeout = Long.parseLong(getChatResponseTimeoutValue());
      response.setTimeout(chatResponseTimeout, TimeUnit.SECONDS);
      response.setTimeoutHandler(asyncResponse -> {
        userToResponse.entrySet().removeIf(entry -> entry.getValue().equals(asyncResponse));
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setStatus(CHAT_RESPONSE_TIMEOUT_STATUS);
        String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(chatResponse);
        response.resume(json);
      });
    }
  }

  private boolean isChatResponseTimeoutConfigured() {
    String chatResponseTimeout = getChatResponseTimeoutValue();
    try {
      return StringUtils.isNotBlank(chatResponseTimeout) && Long.parseLong(chatResponseTimeout) > 0;
    } catch (NumberFormatException e) {
      Ivy.log().error("Chat response timeout must be a Long number", e);
    }
    return false;
  }

  private String getChatResponseTimeoutValue() {
    return new GlobalSettingService().findGlobalSettingValue(CHAT_RESPONSE_TIMEOUT.toString());
  }
}