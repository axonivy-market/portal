package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
  public boolean showFriendlyLogin(){
    return true;
  }
}
