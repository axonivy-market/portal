package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class DataInitializationBean {

  @PostConstruct
  public void init() {
    RoleUtils.setHidePropertyForDefaultHiddenRoles();
    Ivy.log().error("DATA INITIALIZATION BEAN IS CREATED");
  }
  
  /**
   * The empty method is called in topbar.xhtml to initialize this bean.
   */
  public void run() {
    Ivy.log().error("DATA INITIALIZATION BEAN RUN");
  }
}
