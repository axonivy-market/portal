package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
public class AvatarBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean isShownAvatar;
  
  @PostConstruct
  public void init() {
    isShownAvatar = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR);
  }

  public boolean isShowAvatar() {
    return isShownAvatar;
  }
}
