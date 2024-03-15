package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bean.ai.AssistantBean;
import com.axonivy.portal.components.util.SecurityMemberDisplayNameUtils;

import ch.ivy.addon.portalkit.enums.ChatbotCandidateQuestion;
import ch.ivy.addon.portalkit.service.AssistantService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ViewScoped
public class AssistantModificationBean extends AssistantBean
    implements Serializable {

  private static final long serialVersionUID = -894113970760912023L;

  private boolean isAddTool;

  private ChatbotCandidateQuestion[] candidateQuestions = ChatbotCandidateQuestion
      .values();

  @PostConstruct
  public void init() {
    super.initBean();
    isAddTool = false;
  }

  public ChatbotCandidateQuestion[] getCandidateQuestions() {
    return candidateQuestions;
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

  public boolean isAddTool() {
    return isAddTool;
  }

  public void setAddTool(boolean isAddTool) {
    this.isAddTool = isAddTool;
  }

  public void onAddTool() {
    setAddTool(true);
  }

  public void onBack() {
    setAddTool(false);
  }

  @Override
  public void save() {
    AssistantService.getInstance()
        .saveAllPublicConfig(Arrays.asList(getAssistant()));
    setAssistantJson(getAssistant().buildDetailsJson());
    onCancel();
  }

  @Override
  public void onCancel() {
    setAddTool(false);
    init();
  }
}
