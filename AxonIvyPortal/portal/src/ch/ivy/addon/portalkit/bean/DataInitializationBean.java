package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.RoleUtils;

@ManagedBean
@SessionScoped
public class DataInitializationBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static volatile boolean sidebarMigrated = false;

  @PostConstruct
  public void init() {
    RoleUtils.setHidePropertyForDefaultHiddenRoles();
    if (!sidebarMigrated) {
      GlobalSettingService.getInstance().migrateSidebarSetting();
      sidebarMigrated = true;
    }
  }

  public void run() {
    // The empty method is called in topbar.xhtml to initialize this bean.
  }
}
