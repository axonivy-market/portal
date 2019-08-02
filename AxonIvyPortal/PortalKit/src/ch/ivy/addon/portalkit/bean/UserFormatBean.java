package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class UserFormatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String format(String fullName, String username) {
    return StringUtils.defaultIfBlank(fullName, username);
  }
  
  public String formatWithTip(String fullName, String username) {
    if (StringUtils.equals(username, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/noDeputy"))){
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/noDeputy");
    }
    if (StringUtils.isBlank(fullName)) {
      return "<" + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName") + ">" + " (" + username + ")";
    }
    return fullName + " (" + username + ")"; 
  }
}
