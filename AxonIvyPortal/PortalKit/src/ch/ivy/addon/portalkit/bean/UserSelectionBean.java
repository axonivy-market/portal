package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;

@ManagedBean
@ViewScoped
public class UserSelectionBean {

  private int maxResults;
  private List<String> roleNames;
  
  public List<UserDTO> completeUser(String query) {
    return UserUtils.findUsers(query, 0, maxResults + 1, roleNames);
  }
  
  public int getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(int maxResults) {
    this.maxResults = maxResults;
  }

  public List<String> getRoleNames() {
    return roleNames;
  }

  public void setRoleNames(List<String> roleNames) {
    this.roleNames = roleNames;
  }
}
