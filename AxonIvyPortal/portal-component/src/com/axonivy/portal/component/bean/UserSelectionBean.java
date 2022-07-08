package com.axonivy.portal.component.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.component.dto.UserDTO;
import com.axonivy.portal.component.util.BeanUtils;
import com.axonivy.portal.component.util.UserUtils;
import com.axonivy.portal.component.jsf.Attrs;

@ManagedBean
@ViewScoped
public class UserSelectionBean implements Serializable {

  private static final long serialVersionUID = -9101747687046832589L;
  private MethodExpression completeUserMethod;

  @PostConstruct
  public void init() {
    completeUserMethod = BeanUtils.createCompleteMethod("#{userSelectionBean.completeUser}");
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
