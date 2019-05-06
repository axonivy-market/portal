package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.ivydata.service.impl.ApplicationService;

@ManagedBean
@ViewScoped
public class EmailSettingBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public Integer countApplication() {
    return ApplicationService.getInstance().findAll().size();
  }
}
