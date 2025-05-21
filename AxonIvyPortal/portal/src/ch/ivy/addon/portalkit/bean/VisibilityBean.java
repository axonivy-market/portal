package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
@ViewScoped
public class VisibilityBean implements Serializable {

  private static final long serialVersionUID = 6402332157509278585L;
  private GlobalSettingService globalSettingService;

  @PostConstruct
  public void init() {
    globalSettingService = GlobalSettingService.getInstance();
  }

  public boolean isShowButtonIcon() {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_BUTTON_ICON);
  }

  public String generateButtonIcon(String iconClass) {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_BUTTON_ICON) ? iconClass : StringUtils.EMPTY;
  }

  public boolean isShowLoginFooter() {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_LOGIN_FOOTER);
  }

  public boolean isHideCaseCreator() {
    return globalSettingService.isHideCaseCreator();
  }

  public String delegationAppendOption() {
    return globalSettingService.findGlobalSettingValue(GlobalVariable.DELEGATION_APPEND_OPTION);
  }

  public boolean isEnablePinTask() {
    return globalSettingService.isEnablePinTask();
  }

  public boolean isEnablePinCase() {
    return globalSettingService.isEnablePinCase();
  }
}
