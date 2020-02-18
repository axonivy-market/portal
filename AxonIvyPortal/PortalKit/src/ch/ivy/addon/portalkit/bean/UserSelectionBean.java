package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.UserUtils;

@ManagedBean
@RequestScoped
public class UserSelectionBean {

  public List<UserDTO> completeUser(String query) {
    int maxResults = Attrs.currentContext().getAttribute("#{cc.attrs.maxResults}", Integer.class);
    List<String> roleNames = Attrs.currentContext().getAttribute("#{cc.attrs.hasRoleNames}", List.class);
    return UserUtils.findUsers(query, 0, maxResults + 1, roleNames);
  }
}
