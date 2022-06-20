package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.BeanUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

@ManagedBean
@ViewScoped
public class UserSelectionBean implements Serializable {

  private static final long serialVersionUID = -6115408539032175241L;
  private MethodExpression completeUserMethod;
  
  @PostConstruct
  public void init() {
    completeUserMethod = BeanUtils.createCompleteMethod("#{userSelectionBean.completeUser}");
  }
  
  public List<UserDTO> completeUser(String query) {
    int maxResults = Attrs.currentContext().getAttribute("#{cc.attrs.maxResults}", Integer.class);
    List<String> fromRoles = Attrs.currentContext().getAttribute("#{cc.attrs.fromRoleNames}", List.class);
    List<String> excludedUsernames = Attrs.currentContext().getAttribute("#{cc.attrs.excludedUsernames}", List.class);
    return UserUtils.findUsers(query, 0, maxResults + 1, fromRoles, excludedUsernames);
  }
  
  public MethodExpression getCompleteUserMethod() {
    return completeUserMethod;
  }
}
