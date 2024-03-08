package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.ai.assistant.PortalAssitant;
import com.axonivy.portal.components.bean.ai.AssistantBean;

import ch.ivy.addon.portalkit.enums.ChatbotCandidateQuestion;

@ManagedBean
@ViewScoped
public class PortalSupportBean extends AssistantBean implements Serializable {

  private static final long serialVersionUID = -894113970760912023L;

  private ChatbotCandidateQuestion[] candidateQuestions = ChatbotCandidateQuestion.values();

  public PortalSupportBean() {
    this.setAssistant(new PortalAssitant());
  }

  public ChatbotCandidateQuestion[] getCandidateQuestions() {
    return candidateQuestions;
  }
}
