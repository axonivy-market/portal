package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;


/**
 * @deprecated Use {@link SecurityMemberDisplayNameFormatBean} instead
 *
 */

@ManagedBean
@ApplicationScoped
@Deprecated(forRemoval = true, since = "9.1")
public class UserFormatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String format(String fullName, String username) {
    if (StringUtils.isBlank(username)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
    }
      
    if (StringUtils.isBlank(fullName)) {
      return username.startsWith("#") ? username.substring(1) : username;
    }
    return fullName;
  }
  
  public String formatWithTip(String fullName, String username) {
    if (StringUtils.isBlank(username)) {
      return StringUtils.EMPTY;
    }
    
    String formattedUsername = username.startsWith("#") ? username.substring(1) : username;
    if (StringUtils.isBlank(fullName)) {
      return String.format("<%s> (%s)", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName"), formattedUsername);
    }
    return String.format("%s (%s)", fullName, formattedUsername); 
  }
}
