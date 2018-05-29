package ch.ivy.addon.portal.chat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class ChatUtils {

  public static final String MESSAGES_JSON_FILE = "D:\\work\\chat\\messages.json";

  @SuppressWarnings("unchecked")
  public static List<Message> readMessages() {
    List<Message> messages = new ArrayList<>();
    try {
      messages = (new Gson()).fromJson(new FileReader(ChatUtils.MESSAGES_JSON_FILE), List.class);
    } catch (JsonSyntaxException | JsonIOException | IOException ex) {
      ex.printStackTrace();
    }
    return messages;
  }

  @SuppressWarnings("unchecked")
  public static List<Message> readMessages(String recipient) {
    List<Message> messages = new ArrayList<>();
    try {
      messages = (new Gson()).fromJson(new FileReader(ChatUtils.MESSAGES_JSON_FILE + "_" + recipient), List.class);
    } catch (JsonSyntaxException | JsonIOException | IOException ex) {
      ex.printStackTrace();
    }
    return messages;
  }

  public static void saveMessagesToFiles(List<Message> messages) {
    try (FileWriter file = new FileWriter(ChatUtils.MESSAGES_JSON_FILE)) {
      file.write((new Gson()).toJson(messages));
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveMessagesToFiles(List<Message> messages, String recipient) {
    try (FileWriter file = new FileWriter(ChatUtils.MESSAGES_JSON_FILE + "_" + recipient)) {
      file.write((new Gson()).toJson(messages));
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void removeFile() {
    try {
      File file = new File(MESSAGES_JSON_FILE);
      if (file.delete()) {
        System.out.println(file.getName() + " is deleted!");
      } else {
        System.out.println("Delete operation is failed.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void removeFile(String recipient) {
    try {
      File file = new File(MESSAGES_JSON_FILE + "_" + recipient);
      if (file.delete()) {
        System.out.println(file.getName() + " is deleted!");
      } else {
        System.out.println("Delete operation is failed.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
