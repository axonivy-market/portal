package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bean.ai.AssistantBean;
import com.axonivy.portal.components.util.SecurityMemberDisplayNameUtils;
import com.axonivy.portal.enums.ai.ToolType;

import ch.ivy.addon.portalkit.enums.ChatbotCandidateQuestion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ViewScoped
public class PortalSupportBean extends AssistantBean implements Serializable {

  private static final long serialVersionUID = -894113970760912023L;

  private ChatbotCandidateQuestion[] candidateQuestions = ChatbotCandidateQuestion
      .values();

  @PostConstruct
  public void init() {
    super.initBean();
  }

  public ChatbotCandidateQuestion[] getCandidateQuestions() {
    return candidateQuestions;
  }

  public String getToolSeverity(String type) {
    ToolType typeEnum = ToolType.valueOf(type);
    return switch (typeEnum) {
    case IVY -> "success";
    case IVY_CALLABLE -> "success";
    case RETRIEVAL_QA -> "info";
    default -> "warning";
    };
  }
  
  public String renderPermissions(List<String> permissions) {
    String result = "";

    if (CollectionUtils.isEmpty(permissions)) {
      return result;
    }

    for (String permission : permissions) {
      if (permissions.indexOf(permission) != 0) {
        result += ", ";
      }
      ISecurityMember mem = Ivy.security().members().find(permission);
      result += SecurityMemberDisplayNameUtils
          .generateBriefDisplayNameForSecurityMember(mem, permission);
    }
    return result;
  }
}
