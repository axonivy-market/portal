package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISession;

@ManagedBean
@ViewScoped
public class IvyCurrentSessionBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static IvyCurrentSessionBean instance;

  public static IvyCurrentSessionBean getInstance() {
    if (instance == null) {
      instance = new IvyCurrentSessionBean();
    }
    return instance;
  }

  public boolean isSessionUserUnknown() {
    return session().isSessionUserUnknown();
  }

  private static ISession session() {
    return ISecurityContext.current().sessions().current();
  }
}
