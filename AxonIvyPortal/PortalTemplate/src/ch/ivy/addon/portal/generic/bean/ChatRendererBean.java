package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ChatRendererBean implements Serializable {

  private static final long serialVersionUID = 4691697531600235758L;
  private static final String EXPRESS_CREATION_TASK = "isExpressCreationTask";
  
  private Boolean isGroupChatRendered;
  private Boolean isPrivateChatRendered;
  
  public boolean getIsChatRendered() {
    return getIsGroupChatRendered() || getIsPrivateChatRendered();
  }

  public boolean getIsGroupChatRendered() {
    if (isGroupChatRendered == null) {
      GlobalSettingService globalSettingService = new GlobalSettingService();
      String enableGroupChatCreation = globalSettingService.findGlobalSettingValue(GlobalVariable.ENABLE_GROUP_CHAT.toString());
      if (StringUtils.isBlank(enableGroupChatCreation)) {
        Map<String, Object> response = IvyAdapterService.startSubProcess("activateGroupChat()", null,
            Arrays.asList(PortalLibrary.PORTAL_KIT.getValue()));
        isGroupChatRendered = Boolean.parseBoolean(response.get("isActivated").toString());
      } else {
        isGroupChatRendered = Boolean.parseBoolean(enableGroupChatCreation);
      }
    }
    return isGroupChatRendered;
  }
  
  public boolean getIsPrivateChatRendered() {
    if (isPrivateChatRendered == null) {
      GlobalSettingService globalSettingService = new GlobalSettingService();
      String enablePrivateChat = globalSettingService.findGlobalSettingValue(GlobalVariable.ENABLE_PRIVATE_CHAT.toString());
      isPrivateChatRendered = Boolean.parseBoolean(enablePrivateChat);
    }
    return isPrivateChatRendered;
  }
  
  public void getGroupChatName() {
    Map<String, Object> response = IvyAdapterService.startSubProcess("setGroupChatName()", null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    String groupChatName = response.get("name").toString();
    PrimeFaces.current().executeScript("var groupChatFormat = '" + groupChatName + "'");
  }

  public boolean isExpressCreationTask() {
    return Ivy.wfTask().customFields().stringField(EXPRESS_CREATION_TASK).get().isPresent();
  }
}
