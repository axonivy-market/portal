package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
public class AvatarBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public boolean isShowAvatar() {
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR);
  }
}
