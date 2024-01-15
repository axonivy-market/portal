package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.HtmlParser;

@ManagedBean
@ViewScoped
public class VisibilityBean implements Serializable {

  private static final long serialVersionUID = 6402332157509278585L;
  private GlobalSettingService globalSettingService;
  
  @PostConstruct
  public void init() {
    globalSettingService = new GlobalSettingService();
  }
  
  public boolean isShowButtonIcon() {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_BUTTON_ICON);
  }
  
  public String generateButtonIcon(String iconClass) {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_BUTTON_ICON) ? iconClass
        : StringUtils.EMPTY;
  }

  public boolean isShowLoginFooter() {
    return globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_LOGIN_FOOTER);
  }
  
  public String sanitizeContent(String content) {
    return HtmlParser.sanitize(content);
  }
}
