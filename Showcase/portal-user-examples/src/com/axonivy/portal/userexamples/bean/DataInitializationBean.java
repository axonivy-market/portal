package com.axonivy.portal.userexamples.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.axonivy.portal.userexamples.util.RoleUtils;

@ManagedBean
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
