package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

@ManagedBean
@ViewScoped
public class EmailSettingBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public Integer countApplication() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    return service.findAllIvyApplications().size();
  }
  
  public static void addSettingsToCallbackParam(Boolean isEmailSettingsEmpty, Boolean settingForAllApp) {
    PrimeFaces primeFaces = PrimeFaces.current();
    primeFaces.ajax().addCallbackParam("settingEmpty", isEmailSettingsEmpty);
    primeFaces.ajax().addCallbackParam("settingForAllApp", settingForAllApp);
  }
  
}
