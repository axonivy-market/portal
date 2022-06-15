package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.BeanUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class DeprecatedUserSelectionBean implements Serializable {

  private static final long serialVersionUID = -6115408539032175241L;
  private MethodExpression completeUserMethod;
  
  @PostConstruct
  public void init() {
    completeUserMethod = BeanUtils.createCompleteMethod("#{deprecatedUserSelectionBean.completeUser}");
  }

  public List<UserDTO> completeUser(String query) {
    int maxResults = Attrs.currentContext().getAttribute("#{cc.attrs.maxResults}", Integer.class);
    List<String> roleNames = Attrs.currentContext().getAttribute("#{cc.attrs.fromRoleNames}", List.class);
    List<String> excludedUsernames = Attrs.currentContext().getAttribute("#{cc.attrs.excludedUsernames}", List.class);
    return UserUtils.findUsers(query, 0, maxResults + 1, roleNames, excludedUsernames);
  }

  public MethodExpression getCompleteUserMethod() {
    return completeUserMethod;
  }
}
