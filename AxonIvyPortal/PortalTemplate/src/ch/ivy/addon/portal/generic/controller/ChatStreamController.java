package ch.ivy.addon.portal.generic.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.chat.ChatMessageManager;
import ch.ivy.addon.portal.chat.Message;
import ch.ivy.addon.portal.chat.SessionAttributeInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;

import com.google.gson.Gson;


@Path("chatcontroller")
@Singleton
public class ChatStreamController {

  private static final int SLEEP_TIME = 2000;
  public static final String DELIMITER = "[MSG_END]";

  @POST
  @Path("updateStreamingChatUUID")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_HTML)
  public Response updateStreamingChatUUID(SessionAttributeInfo info) {
    ISession session = Ivy.wf().getSecurityContext().findSession(info.getSessionId());
    session.setAttribute("StreamingChatUUID", info.getUuid());
    return Response.ok("SUCCESSFUL").build();
  }

  @POST
  @Path("write")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_HTML)
  public Response write(Message message) {
    ChatMessageManager.saveMessage(message);
    ChatMessageManager.saveTempMessage(message);
    return Response.ok("SUCCESSFUL").build();
  }

  @GET
  @Path("get")
  public Response listen() {
    IvyStreamingOutput streamingOutput = new IvyStreamingOutput() {
      @Override
      public void writeInIvyContext(OutputStream stream) throws IOException, WebApplicationException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)), true);
        distributeIncomingMessages(out);
      }

      private void distributeIncomingMessages(PrintWriter out) {
        String sessionUserName = Ivy.session().getSessionUserName();
        int sessionId = Ivy.session().getIdentifier();
        String uuid = UUID.randomUUID().toString();
        // To make sure only one streaming for a session user, save memory for server
        // In order to stop streaming, we should clean this attribute
        Ivy.session().setAttribute("StreamingChatUUID", uuid);
        int timeToUpdateStreamingChatUUID = 0;
        // for demo lets limit to 60 seconds, in real world could run forever
        while (isUserWorking(sessionUserName) && StringUtils.equals(uuid, Ivy.session().getAttribute("StreamingChatUUID").toString())) {
          try {
            // after n (current = 30) times, we should ask client to make sure client is still in connection
            // if client doesn't update StreamingChatUUID, should stop connection
            if (timeToUpdateStreamingChatUUID == 5) { // n = 5, real is 50 minutes
              uuid = UUID.randomUUID().toString();
              out.println(new Gson().toJson(new SessionAttributeInfo(sessionId, uuid)));
              timeToUpdateStreamingChatUUID = 0;
              Thread.sleep(2 * SLEEP_TIME); // waiting for client update StreamingChatUUID
            }
            Ivy.log().error("taint " + Ivy.session().getSessionUserName() + " " + Ivy.session().getIdentifier() + " " + uuid);
            String filename = ChatMessageManager.generateFileName(Arrays.asList(sessionUserName));
            List<Message> messages = ChatMessageManager.loadTempMessagesAndDeleteTempMessages(filename);
            if (CollectionUtils.isNotEmpty(messages)) {
              messages.sort(new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                  return StringUtils.compare(m1.getTimestamp(), m2.getTimestamp());
                }
              });
              out.println(new Gson().toJson(messages));
            }
            messages = Collections.emptyList();
            Thread.sleep(SLEEP_TIME);
            ++timeToUpdateStreamingChatUUID;
          } catch (Exception e) {
            Ivy.log().error("Log For Chatting: {0}", e);
          }
        }
        Ivy.log().error("taint " + sessionUserName + " " + "STOP");
      }
    };
    return Response.ok(streamingOutput).build();
  }

  private boolean isUserWorking(String username) {
    return Ivy.wf().getSecurityContext().getSessions().stream().anyMatch(s -> StringUtils.equals(s.getSessionUserName(), username));
  }
}
