package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.inject.Named;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import jakarta.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class AvatarBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public boolean isShowAvatar() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR);
  }
}
