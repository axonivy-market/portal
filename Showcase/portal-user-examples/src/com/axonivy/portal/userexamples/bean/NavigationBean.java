package com.axonivy.portal.userexamples.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.configuration.UserProcess;

@ManagedBean
@ViewScoped
public class NavigationBean implements Serializable {

  public void startProcess(UserProcess userProcess) throws IOException {
    Objects.requireNonNull(userProcess, "User process must not be null");
    String link = userProcess.getLink();
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
}
