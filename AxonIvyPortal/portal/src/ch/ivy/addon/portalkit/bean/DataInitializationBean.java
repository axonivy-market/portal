package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import ch.ivy.addon.portalkit.util.RoleUtils;

@Named
@SessionScoped
public class DataInitializationBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @PostConstruct
  public void init() {
    RoleUtils.setHidePropertyForDefaultHiddenRoles();
  }

  public void run() {
    // The empty method is called in topbar.xhtml to initialize this bean.
  }
}
