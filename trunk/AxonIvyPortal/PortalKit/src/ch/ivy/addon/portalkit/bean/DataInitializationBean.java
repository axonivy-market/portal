package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.util.RoleUtils;

@ManagedBean
@SessionScoped
public class DataInitializationBean {

  @PostConstruct
  public void init() {
    RoleUtils.setHidePropertyForDefaultHiddenRoles();
  }

  /**
   * The empty method is called in topbar.xhtml to initialize this bean.
   */
  public void run() {}
}
