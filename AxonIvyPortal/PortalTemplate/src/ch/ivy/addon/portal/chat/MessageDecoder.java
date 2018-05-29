package ch.ivy.addon.portal.chat;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.push.impl.JSONDecoder;

public class MessageDecoder {

  private static final String RECIPIENT_KEY = "recipients";
  private static final String TIMESTAMP_KEY = "timestamp";
  private static final String SENDER_KEY = "sender";
  private static final String CONTENT_KEY = "content";

  public Message decode(String s) {
    JSONObject decodedObject = (JSONObject) new JSONDecoder().decode(s);

    Message message = new Message();
    message.setContent(decodedObject.getString(CONTENT_KEY));
    message.setSender(decodedObject.getString(SENDER_KEY));
    message.setTimestamp(decodedObject.getString(TIMESTAMP_KEY));
    JSONArray decodedRecipients = (JSONArray) decodedObject.get(RECIPIENT_KEY);
    message.setRecipients(decodeRecipients(decodedRecipients));

    return message;
  }

  private List<String> decodeRecipients(JSONArray decodedRecipients) {
    List<String> recipients = new ArrayList<>();
    for (int i = 0; i < decodedRecipients.length(); i++) {
      String recipient = decodedRecipients.getString(i);
      recipients.add(recipient);
    }
    return recipients;
  }

}
