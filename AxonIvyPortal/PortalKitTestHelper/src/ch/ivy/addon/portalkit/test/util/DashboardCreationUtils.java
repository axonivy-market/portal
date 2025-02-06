package ch.ivy.addon.portalkit.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.axonivy.portal.dto.News;
import com.axonivy.portal.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class DashboardCreationUtils {
  private static String PRIVATE_CONFIG =
      "[{\"id\":\"af93bcf30eac4f328ae6ced3aaa5e2f9\",\"title\":\"private 1\",\"permissions\":[\"Everybody\"]},{\"id\":\"1fdb4cbd0d844f9e90273eab22040d71\",\"title\":\"private 2\",\"permissions\":[\"Everybody\"]}]";

  private static String PUBLIC_CONFIG =
      "[{\"id\":\"0b37be74a50b4ce0a8c8e8abbd54e24f\",\"title\":\"public 1\",\"permissions\":[\"Everybody\"]},{\"id\":\"6365e9439db84bd3a8a18f8d47529413\",\"title\":\"public 2\",\"permissions\":[\"Everybody\"]},{\"id\":\"2d0a8d2838ae428c9272ff71dbd93ba1\",\"title\":\"public 3\",\"permissions\":[\"CostObject\"]}]";

  public static void createDashboard() {
//    JSONPObject privateObject = new JS
    ObjectMapper mapper = new ObjectMapper();
    ArrayNode privateRootNode = mapper.createArrayNode();

    ObjectNode childNode1 = mapper.createObjectNode();
    childNode1.put("id", "af93bcf30eac4f328ae6ced3aaa5e2f9");
    childNode1.put("title", "private 1");
    childNode1.put("permissions", "Everybody");

    privateRootNode.add(childNode1);

    ObjectNode childNode2 = mapper.createObjectNode();
    childNode2.put("id", "1fdb4cbd0d844f9e90273eab22040d71");
    childNode2.put("title", "private 2");
    childNode2.put("permissions", "Everybody");

    privateRootNode.add(childNode2);
    
    // PUBLIC
    ArrayNode publicRootNode = mapper.createArrayNode();

    ObjectNode publicChildNode1 = mapper.createObjectNode();
    publicChildNode1.put("id", "0b37be74a50b4ce0a8c8e8abbd54e24f");
    publicChildNode1.put("title", "public 1");
    publicChildNode1.put("permissions", "Everybody");

    publicRootNode.add(publicChildNode1);

    ObjectNode publicChildNode2 = mapper.createObjectNode();
    publicChildNode2.put("id", "6365e9439db84bd3a8a18f8d47529413");
    publicChildNode2.put("title", "public 2");
    publicChildNode2.put("permissions", "Everybody");

    publicRootNode.add(publicChildNode2);

    ObjectNode publicChildNode3 = mapper.createObjectNode();
    publicChildNode3.put("id", "2d0a8d2838ae428c9272ff71dbd93ba1");
    publicChildNode3.put("title", "public 3");
    publicChildNode3.put("permissions", "CostObject");

    publicRootNode.add(publicChildNode3);

    String privateConfig = null;   
    String publicConfig = null;
    try {
      privateConfig = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(privateRootNode);
      publicConfig =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(publicRootNode);
      System.out.println(privateConfig);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    Ivy.var().set(PortalVariable.DASHBOARD.key, publicConfig);
    Ivy.session().getSessionUser().setProperty(PortalVariable.DASHBOARD.key, privateConfig);
  }

  public static void createDummyDataForNewsFeed() {
    List<News> newsList = new ArrayList<>();
    Locale sessionLocale = Ivy.session().getContentLocale();
    var news = new News();
    news.setLocale(sessionLocale);
    news.setIcon("si-send-email");
    news.setName("Welcome to Portal News feed");
    news.setDescription("This is a great place to share relevant information as News Feed into Axon Ivy.");
    newsList.add(news);
    NewsService.getInstance().saveOrUpdate(newsList);
  }
  
  public static void main(String[] args) {
    ObjectMapper mapper = new ObjectMapper();
    ArrayNode rootNode = mapper.createArrayNode();

    ObjectNode childNode1 = mapper.createObjectNode();
    childNode1.put("id", "af93bcf30eac4f328ae6ced3aaa5e2f9");
    childNode1.put("title", "private 1");
    childNode1.put("permissions", "Everybody");

    rootNode.add(childNode1);

    ObjectNode childNode2 = mapper.createObjectNode();
    childNode2.put("id", "1fdb4cbd0d844f9e90273eab22040d71");
    childNode2.put("title", "private 2");
    childNode2.put("permissions", "Everybody");

    rootNode.add(childNode2);


    String jsonString;
    try {
      jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
      System.out.println(jsonString);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
