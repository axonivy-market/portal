package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;

@ManagedBean
@RequestScoped
public class ServerInformationBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String ERROR = "[ERROR]";

  public String getHost() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      Ivy.log().error("Cannot get the hostname", e);
      return ERROR;
    }
  }

  public String getEnvironment() {
    try {
      String activeEnvironment = IApplication.current().getActiveEnvironment();

      if (isNotEmpty(activeEnvironment)) {
        return activeEnvironment;
      }
      return "Default";
    } catch (PersistencyException e) {
      Ivy.log().error("Cannot get the active environment.", e);
      return ERROR;
    }
  }
}
