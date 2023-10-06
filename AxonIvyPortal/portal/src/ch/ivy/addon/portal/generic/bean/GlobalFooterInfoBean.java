package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
@RequestScoped
public class GlobalFooterInfoBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String info;

  @PostConstruct
  public void init() {
    this.info = HtmlParser.sanitize(
        GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.GLOBAL_FOOTER_INFO).toString());
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

}
