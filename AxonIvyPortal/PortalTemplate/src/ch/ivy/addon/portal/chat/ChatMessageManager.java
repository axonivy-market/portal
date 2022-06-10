package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.log;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;
import static ch.ivy.addon.portalkit.constant.UserProperty.PORTAL_CHAT_UNREAD_MESSAGES;
import static ch.ivy.addon.portalkit.persistence.converter.UserEntityConverter.entitiesToJson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.Gson;

import ch.ivy.addon.portalkit.persistence.converter.UserEntityConverter;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public final class ChatMessageManager {

  private static final String UNDER_SCORE = "_";
  private static final String FILE_NAME_ENCRYPT_ALGORITHM = "MD5";
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String CONVERSATION_FILE_PATH =
      StringUtils.join("conversations", java.io.File.separator, "%s.token");
  private static final String GROUP_CONVERSATION_FILE_PATH =
      StringUtils.join("group_conversations", java.io.File.separator, "%s.txt");
  private static final String UTF_8 = StandardCharsets.UTF_8.name();
  private static final String GROUP_CHAT_FILE_FORMAT = "Case_%s";
  private static final String GROUP_CHAT_PREFIX = "Case-%s";
  private static final String GROUP_CHAT_SENDER_PREFIX = "Case-";

  private static Map<String, Object> lockMap = new HashMap<>();

  private ChatMessageManager() {}

  public static List<ChatMessage> loadPersonalMessages(List<String> participants) {
    String filename = generateFileName(replaceUsernameByUserId(participants));
    String filepath = generateFilePath(filename);
    return loadEncryptedMessagesFromFilePath(filepath);
  }

  public static void savePersonalMessage(ChatMessage message) {
    replaceSenderAndRecipientNamesByIds(message);
    String filename = prepareFilename(message);
    String filepath = generateFilePath(filename);
    encryptAndSaveMessageToFile(message, filepath);
  }

  private static void replaceSenderAndRecipientNamesByIds(ChatMessage message) {
    message.setSender(ChatGroupUtils.findUserByUsername(message.getSender()).getSecurityMemberId());
    message.setRecipients(replaceUsernameByUserId(message.getRecipients()));
  }

  private static List<String> replaceUsernameByUserId(List<String> participants) {
    List<String> updatedParticipants = new ArrayList<>();
    for (String participant : participants) {
      IUser participantUser = ChatGroupUtils.findUserByUsername(participant);
      updatedParticipants.add(participantUser.getSecurityMemberId());
    }
    return updatedParticipants;
  }

  /**
   * Load messages of group by caseID. The file contains chat history will have this format
   * {@value #GROUP_CHAT_FILE_FORMAT}
   * 
   * @param caseId
   * @return list of {@link ChatMessage}
   */
  public static List<ChatMessage> loadGroupMessagesByCaseId(String caseId) {
    String filePath = generateGroupFilePath(generateGroupFileNameByCaseId(caseId));
    return loadGroupMessagesFromFilePath(filePath);
  }

  private static String generateGroupFileNameByCaseId(String caseId) {
    return String.format(GROUP_CHAT_FILE_FORMAT, caseId);
  }

  public static void saveGroupMessage(ChatMessage message, String caseId) {
    String filepath = generateGroupFilePath(generateGroupFileNameByCaseId(caseId));
    saveGroupMessageToFile(message, filepath);
  }

  private static List<ChatMessage> loadEncryptedMessagesFromFilePath(String filepath) {
    List<ChatMessage> messages = new ArrayList<>();
    try {
      File file = new File(filepath);
      String conversation = file.read(UTF_8);
      if (StringUtils.isNotEmpty(conversation)) {
        String[] records = conversation.split(LINE_SEPARATOR);
        for (String record : records) {
          String decryptedRecord = SecureMessage.decrypt(record, java.io.File.separator + filepath);
          ChatMessage message = new Gson().fromJson(decryptedRecord, ChatMessage.class);
          message.setSender(Optional.ofNullable(ChatGroupUtils.findUserByUserId(Long.parseLong(message.getSender())))
              .map(IUser::getName).orElse(""));
          List<String> updatedRecipients = new ArrayList<>();
          for (String recipient : message.getRecipients()) {
            IUser recipientUser = ChatGroupUtils.findUserByUserId(Long.parseLong(recipient));
            if (recipientUser != null) {
              updatedRecipients.add(recipientUser.getName());
            }
          }
          message.setRecipients(updatedRecipients);
          messages.add(message);
        }
      }
    } catch (IOException e) {
      log().warn("Could not load previous conversation. Chat history file could not be decoded", e);
    }
    return messages;
  }

  private static List<ChatMessage> loadGroupMessagesFromFilePath(String filepath) {
    List<ChatMessage> messages = new ArrayList<>();
    try {
      File file = new File(filepath);
      String conversation = file.read(UTF_8);
      if (StringUtils.isNotEmpty(conversation)) {
        String[] records = conversation.split(LINE_SEPARATOR);
        for (String record : records) {
          ChatMessage message = new Gson().fromJson(record, ChatMessage.class);
          message
              .setSender(ChatGroupUtils.findUserByUserId(Long.parseLong(message.getSender().substring(1))).getName());
          messages.add(message);
        }
      }
    } catch (IOException e) {
      log().warn("Chat history file could not be loaded", e);
    }
    return messages;
  }

  private static void saveGroupMessageToFile(ChatMessage message, String filepath) {
    String senderName = message.getSender();
    message.setSender("#".concat(ChatGroupUtils.findUserByUsername(message.getSender()).getSecurityMemberId()));
    String convertedMessage = new Gson().toJson(message);
    try {
      File conversation = new File(filepath);

      if (!conversation.exists()) {
        conversation.createNewFile();
      }
      String currentContent = conversation.read(UTF_8);
      if (StringUtils.isNotEmpty(currentContent)) {
        conversation.write(StringUtils.join(currentContent, LINE_SEPARATOR, convertedMessage), UTF_8);
      } else {
        conversation.write(convertedMessage, UTF_8);
      }
    } catch (IOException e) {
      String template = "Could not save the current conversation from [%s] to file [%s].";
      String errorMessage = String.format(template, senderName, filepath);
      log().warn(errorMessage, e);
    }
    message.setSender(senderName);
  }

  private static void encryptAndSaveMessageToFile(ChatMessage message, String filepath) {
    String convertedMessage = new Gson().toJson(message);
    String encryptedMessage =
        SecureMessage.encrypt(convertedMessage, StringUtils.join(java.io.File.separator, filepath));
    try {
      File conversation = new File(filepath);

      if (!conversation.exists()) {
        conversation.createNewFile();
      }
      String currentContent = conversation.read();
      if (StringUtils.isNotEmpty(currentContent)) {
        conversation.write(StringUtils.join(currentContent, LINE_SEPARATOR, encryptedMessage), UTF_8);
      } else {
        conversation.write(encryptedMessage, UTF_8);
      }
    } catch (IOException e) {
      String template = "Could not save the current conversation between [%s] and [%s].";
      String errorMessage = String.format(template, message.getSender(), message.getRecipients());
      log().warn(errorMessage, e);
    }
  }

  private static String prepareFilename(ChatMessage message) {
    List<String> participants = new ArrayList<>();
    participants.add(message.getSender());
    participants.addAll(message.getRecipients());
    return generateFileName(participants);
  }

  private static String generateFilePath(String filename) {
    return String.format(CONVERSATION_FILE_PATH, filename);
  }

  private static String generateGroupFilePath(String filename) {
    return String.format(GROUP_CONVERSATION_FILE_PATH, filename);
  }

  public static String generateFileName(List<String> participants) {
    participants.sort((first, second) -> first.compareTo(second));
    try {
      MessageDigest md = MessageDigest.getInstance(FILE_NAME_ENCRYPT_ALGORITHM);
      md.update(StringUtils.join(participants.toArray(), UNDER_SCORE).getBytes());
      byte[] digest = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      log().warn("Could not generate file name.", e);
    }
    return String.valueOf(StringUtils.join(participants.toArray()).hashCode());
  }

  public static void storeUnreadMessage(ChatMessage message) {
    String recipient = message.getRecipients().get(0);
    String senderId = getSenderId(message.getSender());
    synchronized (getCommon(recipient)) {
      List<UnreadChatMessage> unreadMessages = getUnreadMessages(recipient);
      if (unreadMessages.stream().noneMatch(msg -> msg.getSenderId().equals(senderId))) {
        unreadMessages.add(new UnreadChatMessage(senderId));
      }
      String messagesAsJson = entitiesToJson(unreadMessages);
      setUnreadMessagesToUserProperty(recipient, messagesAsJson);
    }
  }

  private static String getSenderId(String sender) {
    if (StringUtils.isNotBlank(sender) && !isGroupChat(sender)) {
      return findUser(sender).getSecurityMemberId();
    }
    return sender;
  }

  static String getSender(String senderId) {
    if (StringUtils.isNotBlank(senderId) && !isGroupChat(senderId)) {
      return Optional.ofNullable(findUserById(senderId)).map(IUser::getName).orElse(null);
    }
    return senderId;
  }

  private static IUser findUserById(String senderId) {
    return wf().getSecurityContext().users().findById(senderId);
  }

  public static void storeUnreadMessageForGroupChat(ChatMessage message, long caseId) {
    ChatMessage clonedMessage = message.copy();
    Set<String> usernames = ChatGroupUtils.getUserNamesFromGroup(caseId);
    usernames.remove(clonedMessage.getSender());
    clonedMessage.setSender(String.format(GROUP_CHAT_PREFIX, caseId));
    for (String username : usernames) {
      clonedMessage.setRecipients(Arrays.asList(username));
      storeUnreadMessage(clonedMessage);
    }
  }

  public static List<UnreadChatMessage> getUnreadMessages(String participant) {
    String userProperty = getUnreadMessagesFromUserProperty(participant);
    List<UnreadChatMessage> unreadMessages =
        StringUtils.isNotBlank(userProperty) ? UserEntityConverter.jsonToEntities(userProperty, UnreadChatMessage.class)
            : new ArrayList<>();
    List<UnreadChatMessage> obsoleteUnreadMessages =
        unreadMessages.stream().filter(x -> isObsoleteSender(x.getSenderId())).collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(obsoleteUnreadMessages)) {
      unreadMessages.removeAll(obsoleteUnreadMessages);
      String messagesAsJson = entitiesToJson(unreadMessages);
      setUnreadMessagesToUserProperty(participant, messagesAsJson);
    }
    return unreadMessages;
  }

  private static boolean isObsoleteSender(String senderId) {
    String caseId = getCaseId(senderId);
    if (StringUtils.isNotBlank(caseId)) {
      ICase findcase = findCase(caseId);
      if (findcase != null && (findcase.getState() == CaseState.DESTROYED || findcase.getState() == CaseState.DONE)) {
        return true;
      }
    }
    return false;
  }

  private static ICase findCase(String caseId) {
    if (NumberUtils.isCreatable(caseId)) {
      return IvyExecutor.executeAsSystem(() -> wf().findCase(Long.valueOf(caseId)));
    }
    return null;
  }

  private static String getCaseId(String name) {
    if (isGroupChat(name)) {
      return StringUtils.substringAfter(name, GROUP_CHAT_SENDER_PREFIX);
    }
    return StringUtils.EMPTY;
  }

  public static void deletedReadMessages(String participant, String sender) {
    List<UnreadChatMessage> unreadMessages = getUnreadMessages(participant);
    String senderId = getSenderId(sender);
    unreadMessages.removeAll(
        unreadMessages.stream().filter(message -> message.getSenderId().equals(senderId)).collect(Collectors.toList()));
    String messagesAsJson = entitiesToJson(unreadMessages);
    setUnreadMessagesToUserProperty(participant, messagesAsJson);
  }

  public static void deletedReadMessagesForGroupChat(String participant, String caseId) {
    deletedReadMessages(participant, String.format(GROUP_CHAT_PREFIX, caseId));
  }

  private static Object getCommon(String value) {
    synchronized (lockMap) {
      Object common = lockMap.get(value);
      if (common == null) {
        common = new Object();
        lockMap.put(value, common);
      }
      return common;
    }
  }

  private static String getUnreadMessagesFromUserProperty(String username) {
    IUser user = findUser(username);
    if (user != null) {
      return user.getProperty(PORTAL_CHAT_UNREAD_MESSAGES);
    }
    return "";
  }

  private static void setUnreadMessagesToUserProperty(String username, String jsonMessages) {
    IUser user = findUser(username);
    if (user != null) {
      user.setProperty(PORTAL_CHAT_UNREAD_MESSAGES, jsonMessages);
    }
  }

  private static IUser findUser(String username) {
    return wf().getSecurityContext().users().find(username);
  }

  private static boolean isGroupChat(String sender) {
    return sender.startsWith(GROUP_CHAT_SENDER_PREFIX);
  }
}
