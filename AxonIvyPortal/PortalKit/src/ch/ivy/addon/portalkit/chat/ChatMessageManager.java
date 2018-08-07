package ch.ivy.addon.portalkit.chat;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.primefaces.push.impl.JSONEncoder;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public final class ChatMessageManager {

  private static final String FILE_NAME_ENCRYPT_ALGORITHM = "MD5";
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String CONVERSATION_FILE_PATH = "conversations" + java.io.File.separator + "%s.token";

  private ChatMessageManager() {
  }

  public static List<Message> loadMessages(List<String> participants) {
    List<Message> messages = new ArrayList<>();
    String fileName = generateFileName(participants);
    try {
      File file = new File(fileName);
      String conversation = file.read(UTF_8);
      if (StringUtils.isNotEmpty(conversation)) {
	String[] records = conversation.split(LINE_SEPARATOR);
	for (String record : records) {
	  String decryptedRecord = SecureMessage.decrypt(record, java.io.File.separator + fileName);
	  Message message = new MessageDecoder().decode(decryptedRecord);
	  messages.add(message);
	}
      }
    } catch (IOException e) {
      Ivy.log().warn("Could not load previous conversation. Chat history file could not be decoded", e);
    }
    return messages;
  }

  public static void saveMessage(Message message) {
    String fileName = prepareFileName(message);
    String convertedMessage = new JSONEncoder().encode(message);
    String encryptedMessage = SecureMessage.encrypt(convertedMessage, java.io.File.separator + fileName);
    try {
      File conversation = new File(fileName);

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

  private static String prepareFileName(Message message) {
    List<String> participants = new ArrayList<>();
    participants.add(message.getSender());
    participants.addAll(message.getRecipients());
    return generateFileName(participants);
  }

  private static String generateFileName(List<String> participants) {
    participants.sort((first, second) -> first.compareTo(second));
    try {
      MessageDigest md = MessageDigest.getInstance(FILE_NAME_ENCRYPT_ALGORITHM);
      md.update(StringUtils.join(participants.toArray()).getBytes());
      byte[] digest = md.digest();
      StringBuffer sb = new StringBuffer();
      for (byte b : digest) {
	sb.append(String.format("%02x", b & 0xff));
      }
      return prependFilePath(sb.toString());
    } catch (NoSuchAlgorithmException e) {
      Ivy.log().warn("Could not generate file name.", e);
    }
    String defaultFileName = String.valueOf(StringUtils.join(participants.toArray()).hashCode());
    return prependFilePath(defaultFileName);
  }

  private static String prependFilePath(String fileName) {
    return String.format(CONVERSATION_FILE_PATH, fileName);
  }

  public static void main(String[] args) {
    Message message = new Message();
    message.setContent("Hello");
    message.setSender("Huy");
    ChatMessageManager.saveMessage(message);
  }
}
