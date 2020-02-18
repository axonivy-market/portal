package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.BeanUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

@ManagedBean
@ViewScoped
public class UserSelectionBean {

  private MethodExpression completeUserMethod;
  
  @PostConstruct
  public void init() {
    completeUserMethod = BeanUtils.createCompleteMethod("#{userSelectionBean.completeUser}");
  }
  
  public List<UserDTO> completeUser(String query) {
    int maxResults = Attrs.currentContext().getAttribute("#{cc.attrs.maxResults}", Integer.class);
    List<String> roleNames = Attrs.currentContext().getAttribute("#{cc.attrs.hasRoleNames}", List.class);
    return UserUtils.findUsers(query, 0, maxResults + 1, roleNames);
  }
  
  public MethodExpression getCompleteUserMethod() {
    return completeUserMethod;
  }
}
