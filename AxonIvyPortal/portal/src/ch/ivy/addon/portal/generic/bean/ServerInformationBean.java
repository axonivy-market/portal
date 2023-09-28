package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.HtmlParser;

@ManagedBean
@RequestScoped
public class ServerInformationBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getHost() {
    String host = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.GLOBAL_HOST_FOOTER_INFO)
        .toString();
    return StringUtils.defaultIfBlank(host, GlobalVariable.GLOBAL_HOST_FOOTER_INFO.getDefaultValue());
  }

  public String getEnvironment() {
    String environment = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.GLOBAL_ENV_FOOTER_INFO).toString();
    String sanitizedText = HtmlParser.sanitize(environment);
    return StringUtils.defaultIfBlank(sanitizedText, GlobalVariable.GLOBAL_ENV_FOOTER_INFO.getDefaultValue());
  }

}
