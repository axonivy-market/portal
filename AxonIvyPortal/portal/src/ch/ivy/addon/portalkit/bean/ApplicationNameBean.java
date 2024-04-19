package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ApplicationNameBean implements Serializable {
  private static final String APPLICATION_NAME = GlobalVariable.APPLICATION_NAME.getKey();

  public ApplicationNameBean() {
  }

  public String getApplicationName() {
    String value = Ivy.var().get(APPLICATION_NAME);
    return value;
  }
}
