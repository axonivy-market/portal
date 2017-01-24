package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class UserFormatBean {

  public String format(String fullName, String username) {
    if (StringUtils.isBlank(fullName)) {
      return username;
    }
    return fullName;
  }
  
  public String formatWithTip(String fullName, String username) {
    if (StringUtils.isBlank(fullName)) {
      return "<" + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName") + ">" + " (" + username + ")";
    }
    return fullName + " (" + username + ")"; 
  }
}
