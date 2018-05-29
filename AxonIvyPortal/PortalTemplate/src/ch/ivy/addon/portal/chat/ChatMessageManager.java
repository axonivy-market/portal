package ch.ivy.addon.portal.chat;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

import com.google.gson.Gson;

public final class ChatMessageManager {

  private static final String UNDER_SCORE = "_";
  private static final String TEMP_CONVERSATIONS_FOLDER = "temp_conversations";
  private static final String FILE_NAME_ENCRYPT_ALGORITHM = "MD5";
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String CONVERSATION_FILE_PATH = "conversations" + java.io.File.separator + "%s.token";
  private static final String TEMP_CONVERSATION_FILE_PATH = TEMP_CONVERSATIONS_FOLDER + java.io.File.separator + "%s.token";

  private ChatMessageManager() {}

  public static List<Message> loadMessages(List<String> participants) {
    String filename = generateFileName(participants);
    String filepath = generateFilePath(filename);
    return loadMessagesFromFilePath(filepath);
  }

  public static List<Message> loadTempMessagesAndDeleteTempMessages(String filename) {
    return loadTempMessagesFromFileAndDelete(filename);
  }

  public static void saveMessage(Message message) {
    String filename = prepareFilename(message);
    String filepath = generateFilePath(filename);
    saveMessageToFile(message, filepath);
  }

  public static void saveTempMessage(Message message) {
    String filename = prepareTempFilename(message) + UNDER_SCORE + UUID.randomUUID().toString();
    String filepath = generateTempFilePath(filename);
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
          // Message message = new MessageDecoder().decode(decryptedRecord);
          Message message = new Gson().fromJson(decryptedRecord, Message.class);
          messages.add(message);
        }
      }
    } catch (IOException e) {
      Ivy.log().warn("Could not load previous conversation. Chat history file could not be decoded", e);
    }
    return messages;
  }

  private static List<Message> loadTempMessagesFromFileAndDelete(String filename) {
    List<Message> messages = new ArrayList<>();
    try {
      File folder = new File(TEMP_CONVERSATIONS_FOLDER);
      for (final File file : folder.listFiles()) {
        String filepath = file.getPath();
        if (file.exists() && filepath.contains(filename + UNDER_SCORE)) {
          String conversation = file.read(UTF_8);
          file.delete();
          if (StringUtils.isNotEmpty(conversation)) {
            String[] records = conversation.split(LINE_SEPARATOR);
            for (String record : records) {
              String decryptedRecord = SecureMessage.decrypt(record, java.io.File.separator + filepath);
              // Message message = new MessageDecoder().decode(decryptedRecord);
              Message message = new Gson().fromJson(decryptedRecord, Message.class);
              messages.add(message);
            }
          }
        }
      }
    } catch (IOException e) {
      Ivy.log().warn("Could not load previous conversation. Chat history file could not be decoded", e);
    }
    return messages;
  }

  private static void saveMessageToFile(Message message, String filepath) {
    // String convertedMessage = new JSONEncoder().encode(message);
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

  private static String prepareTempFilename(Message message) {
    List<String> participants = new ArrayList<>();
    participants.addAll(message.getRecipients());
    String filename = generateFileName(participants);
    return filename;
  }

  private static String generateFilePath(String filename) {
    return String.format(CONVERSATION_FILE_PATH, filename);
  }

  public static String generateTempFilePath(String filename) {
    return String.format(TEMP_CONVERSATION_FILE_PATH, filename);
  }

  public static String generateFileName(List<String> participants) {
    participants.sort((first, second) -> first.compareTo(second));
    try {
      MessageDigest md = MessageDigest.getInstance(FILE_NAME_ENCRYPT_ALGORITHM);
      md.update(StringUtils.join(participants.toArray(), UNDER_SCORE).getBytes());
      byte[] digest = md.digest();
      StringBuffer sb = new StringBuffer();
      for (byte b : digest) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      Ivy.log().warn("Could not generate file name.", e);
    }
    String defaultFileName = String.valueOf(StringUtils.join(participants.toArray()).hashCode());
    return defaultFileName;
  }
}
