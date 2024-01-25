package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.ChatbotCandidateQuestion;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ChatDashboardBean implements Serializable {

  private static final long serialVersionUID = -894113970760912023L;

  private static ChatbotCandidateQuestion[] candidateQuestions = ChatbotCandidateQuestion.values();
  private String chatbotEndpoint;

  public ChatbotCandidateQuestion[] getCandidateQuestions() {
    return candidateQuestions;
  }

  public String getChatbotEndpoint() {
    if (StringUtils.isBlank(chatbotEndpoint)) {
      chatbotEndpoint = Ivy.var().get(PortalVariable.CHATBOT_ENDPOINT.key);
    }
    return chatbotEndpoint;
  }

  public void setChatbotEndpoint(String chatbotEndpoint) {
    this.chatbotEndpoint = chatbotEndpoint;
  }
}
