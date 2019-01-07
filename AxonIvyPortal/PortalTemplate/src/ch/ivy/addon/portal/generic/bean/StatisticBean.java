package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@ManagedBean
@ViewScoped
public class StatisticBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public boolean isHiddenStatisticWidget() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    return globalSettingSerive.findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_STATISTIC_WIDGET);
  }
}
