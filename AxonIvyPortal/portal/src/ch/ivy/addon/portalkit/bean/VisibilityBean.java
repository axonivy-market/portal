package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
@ViewScoped
public class VisibilityBean implements Serializable {

  private static final long serialVersionUID = 6402332157509278585L;
  private GlobalSettingService globalSettingService;
  private boolean isShownButtonIcon;
  private boolean isEnablePinTask;
  private boolean isEnablePinCase;

  @PostConstruct
  public void init() {
    globalSettingService = GlobalSettingService.getInstance();
    isShownButtonIcon = globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_BUTTON_ICON);
    isEnablePinTask = globalSettingService.isEnablePinTask();
    isEnablePinCase = globalSettingService.isEnablePinCase();
  }

  public boolean isShowButtonIcon() {
    return isShownButtonIcon;
  }

  public String generateButtonIcon(String iconClass) {
    return isShownButtonIcon ? iconClass : StringUtils.EMPTY;
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
    return isEnablePinTask;
  }

  public boolean isEnablePinCase() {
    return isEnablePinCase;
  }
}
