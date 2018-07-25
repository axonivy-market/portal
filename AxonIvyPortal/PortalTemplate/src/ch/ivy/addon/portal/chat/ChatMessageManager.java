package ch.ivy.addon.portal.chat;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public final class ChatMessageManager {

  private static final String UNDER_SCORE = "_";
  private static final String FILE_NAME_ENCRYPT_ALGORITHM = "MD5";
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String CONVERSATION_FILE_PATH = "conversations" + java.io.File.separator + "%s.token";
  private static final String UTF_8 = "UTF-8";
  
  private static Map<String, Object> lockMap = new HashMap<>();

  private ChatMessageManager() {}

  public static List<Message> loadMessages(List<String> participants) {
    String filename = generateFileName(participants);
    String filepath = generateFilePath(filename);
    return loadMessagesFromFilePath(filepath);
  }

  public static void saveMessage(Message message) {
    String filename = prepareFilename(message);
    String filepath = generateFilePath(filename);
    saveMessageToFile(message, filepath);
  }

  private static List<Message> loadMessagesFromFilePath(String filepath) {
    List<Message> messages = new ArrayList<>();
    try {
      File file = new File(filepath);
      String conversation = file.read(UTF_8);
      if (StringUtils.isNotEmpty(conversation)) {
        String[] records = conversation.split(LINE_SEPARATOR);
        for (String record : records) {
          String decryptedRecord = SecureMessage.decrypt(record, java.io.File.separator + filepath);
          Message message = new Gson().fromJson(decryptedRecord, Message.class);
          messages.add(message);
        }
      }
    } catch (IOException e) {
      Ivy.log().warn("Could not load previous conversation. Chat history file could not be decoded", e);
    }
    return messages;
  }

  private static void saveMessageToFile(Message message, String filepath) {
    String convertedMessage = new Gson().toJson(message);
    String encryptedMessage = SecureMessage.encrypt(convertedMessage, java.io.File.separator + filepath);
    try {
      File conversation = new File(filepath);

      if (!conversation.exists()) {
        conversation.createNewFile();
      }
      String currentContent = conversation.read();
      if (StringUtils.isNotEmpty(currentContent)) {
        conversation.write(currentContent + LINE_SEPARATOR + encryptedMessage, UTF_8);
      } else {
        conversation.write(encryptedMessage, UTF_8);
      }
    } catch (IOException e) {
      String template = "Could not save the current conversation between [%s] and [%s].";
      String errorMessage = String.format(template, message.getSender(), message.getRecipients());
      Ivy.log().warn(errorMessage, e);
    }
  }

  private static String prepareFilename(Message message) {
    List<String> participants = new ArrayList<>();
    participants.add(message.getSender());
    participants.addAll(message.getRecipients());
    return generateFileName(participants);
  }

  private static String generateFilePath(String filename) {
    return String.format(CONVERSATION_FILE_PATH, filename);
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
      Ivy.log().warn("Could not generate file name.", e);
    }
    return String.valueOf(StringUtils.join(participants.toArray()).hashCode());
  }
  
  public static void storeUnreadMessageInMemory(Message message) {
    String attrName = messageAttribute(message.getRecipients());
    synchronized (getCommon(attrName)) {
      List<Message> messages = getUnreadMessagesInMemory(message.getRecipients());
      messages.add(message);
      Ivy.wf().getApplication().setAttribute(attrName, messages);
    }
  }
  
  @SuppressWarnings("unchecked")
  public static List<Message> getUnreadMessagesInMemory(List<String> participants) {
    String attrName = messageAttribute(participants);
    return (List<Message>) ObjectUtils.defaultIfNull(Ivy.wf().getApplication().getAttribute(attrName), new ArrayList<>());
  }
  
  public static void clearMessagesInMemory(List<String> participants) {
    String attrName = messageAttribute(participants);
    Ivy.wf().getApplication().removeAttribute(attrName);
  }

  private static String messageAttribute(List<String> participants) {
    return "Portal_Chat_" + generateFileName(participants);
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
}
