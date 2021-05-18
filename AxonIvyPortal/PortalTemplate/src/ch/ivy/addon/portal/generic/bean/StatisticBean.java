package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
@ViewScoped
public class StatisticBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String isHideStatisticWidget;

  @PostConstruct
  public void init() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    isHideStatisticWidget =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET);
  }

  public boolean isHiddenStatisticWidget() {
    return StringUtils.isNotBlank(isHideStatisticWidget) ? Boolean.parseBoolean(isHideStatisticWidget) : false;
  }
}
