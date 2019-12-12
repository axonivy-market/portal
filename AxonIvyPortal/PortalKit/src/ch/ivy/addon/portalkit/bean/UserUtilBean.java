package ch.ivy.addon.portalkit.bean;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserUtilBean {
  
  public String generateDisplayName(String userName) {
    IUser creator = Ivy.wf().getSecurityContext().findUser(userName);
    if(creator == null) {
      return StringUtils.EMPTY;
    }
    UserFormatBean userFormatBean = new UserFormatBean();
    return userFormatBean.format(creator.getFullName(), creator.getName());
  }
}
