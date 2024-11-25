package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.AiProcessService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class ChatRendererBean implements Serializable {

  private static final long serialVersionUID = 4691697531600235758L;
  
  private Boolean isGroupChatRendered;
  private Boolean isPrivateChatRendered;
  private IProcessStart assistantDashboardProcess;
  
  public boolean getIsChatRendered() {
    return getIsGroupChatRendered() || getIsPrivateChatRendered();
  }

  public boolean getIsGroupChatRendered() {
    if (isGroupChatRendered == null) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String enableGroupChatCreation = globalSettingService.findGlobalSettingValue(GlobalVariable.ENABLE_GROUP_CHAT);
      if (StringUtils.isBlank(enableGroupChatCreation)) {
        Map<String, Object> response = IvyAdapterService.startSubProcessInSecurityContext("activateGroupChat()", null);
        isGroupChatRendered = Boolean.parseBoolean(response.get("isActivated").toString());
      } else {
        isGroupChatRendered = Boolean.parseBoolean(enableGroupChatCreation);
      }
    }
    return isGroupChatRendered;
  }
  
  public boolean getIsPrivateChatRendered() {
    if (isPrivateChatRendered == null) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String enablePrivateChat = globalSettingService.findGlobalSettingValue(GlobalVariable.ENABLE_PRIVATE_CHAT);
      isPrivateChatRendered = Boolean.parseBoolean(enablePrivateChat);
    }
    return isPrivateChatRendered;
  }
  
  public void getGroupChatName() {
    String groupChatName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/case") + "-{caseId}" + " {caseName}";
    PrimeFaces.current().executeScript("var groupChatFormat = '" + groupChatName + "'");
  }

  public boolean getIsAssistantDashboardRendered() {
    if (this.assistantDashboardProcess == null) {
      this.assistantDashboardProcess = AiProcessService.getInstance()
          .findAssistantDashboardProcess();
    }
    return StringUtils.isNotBlank(Optional.ofNullable(assistantDashboardProcess)
        .map(IProcessStart::getLinkEmbedded).map(WebLink::getRelative)
        .orElse(""));
  }

}
