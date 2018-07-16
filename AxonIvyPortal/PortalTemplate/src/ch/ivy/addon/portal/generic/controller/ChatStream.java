package ch.ivy.addon.portal.generic.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;

import com.google.gson.Gson;

import ch.ivy.addon.portal.chat.ChatContactManager;
import ch.ivy.addon.portal.chat.ChatMessageManager;
import ch.ivy.addon.portal.chat.Command;
import ch.ivy.addon.portal.chat.Message;
import ch.ivyteam.ivy.environment.Ivy;


@Path("chat")
@Singleton
public class ChatStream {

  private static final int SLEEP_TIME = 2000;
  
//  private long pingTime = new DateTime().toNumber();
//  
//  @POST
//  @Path("ping")
//  public Response ping() {
//    pingTime = new DateTime().toNumber();
//    return Response.ok("SUCCESSFUL").build();
//  }

  @POST
  public Response write(Message message) {
    ChatMessageManager.storeUnreadMessageInMemory(message);
    ChatMessageManager.saveMessage(message);
    return Response.ok("SUCCESSFUL").build();
  }

  @GET
  public Response listen() {
    IvyStreamingOutput streamingOutput = new IvyStreamingOutput() {
      @Override
      public void writeInIvyContext(OutputStream stream) throws IOException, WebApplicationException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)), true);
        distributeIncomingMessages(out);
      }

      private void distributeIncomingMessages(PrintWriter out) {
        String sessionUserName = Ivy.session().getSessionUserName();
        boolean isClientDisconntected = false;
        List<String> oldOnlineUsers = ChatContactManager.getOnlineContacts();
//        while (isUserWorking(sessionUserName) && (Math.abs(pingTime - new DateTime().toNumber()) <= 60)) {
        while (!isClientDisconntected) {
          try {
            List<String> onlineUsers = ChatContactManager.getOnlineContacts();
            Map<String, Command> changedUsers = new HashMap<>();
            for (int i = 0; i < Math.max(onlineUsers.size(), oldOnlineUsers.size()); i++) {
              if (i < onlineUsers.size()) {
                if (!changedUsers.containsKey(onlineUsers.get(i))) {
                  changedUsers.put(onlineUsers.get(i), new Command(Command.ADD_CONTACT, onlineUsers.get(i)));
                } else {
                  changedUsers.remove(onlineUsers.get(i));
                }
              }
              if (i < oldOnlineUsers.size()) {
                if (!changedUsers.containsKey(oldOnlineUsers.get(i))) {
                  changedUsers.put(oldOnlineUsers.get(i), new Command(Command.REMOVE_CONTACT, oldOnlineUsers.get(i)));
                } else {
                  changedUsers.remove(oldOnlineUsers.get(i));
                }
              }
            }
            if (!changedUsers.isEmpty()) {
              out.println(new Gson().toJson(changedUsers.values()));
            }
            oldOnlineUsers = onlineUsers;
            
            List<Message> messages = ChatMessageManager.getUnreadMessagesInMemory(Arrays.asList(sessionUserName));
            if (CollectionUtils.isNotEmpty(messages)) {
              out.println(new Gson().toJson(messages));
              ChatMessageManager.clearMessagesInMemory(Arrays.asList(sessionUserName));
            }
            Thread.sleep(SLEEP_TIME);
            isClientDisconntected = out.checkError();
          } catch (Exception e) {
            Ivy.log().error("Error in chat stream", e);
          }
        }
      }
    };
    return Response.ok(streamingOutput).build();
  }
//
//  private boolean isUserWorking(String username) {
//    return Ivy.wf().getSecurityContext().getSessions().stream().anyMatch(s -> StringUtils.equals(s.getSessionUserName(), username));
//  }
}
