package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.util.BeanUtils;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.jsf.Attrs;

@ManagedBean
@ViewScoped
public class RoleSelectionBean implements Serializable {

  private static final long serialVersionUID = 1518580444649494315L;
  private MethodExpression completeRoleMethod;

  @PostConstruct
  public void init() {
    completeRoleMethod = BeanUtils.createCompleteMethod("#{roleSelectionBean.completeRole}");
  }

  public List<RoleDTO> completeRole(String query) {
    List<String> fromRoles = Attrs.currentContext().getAttribute("#{cc.attrs.fromRoleNames}", List.class);
    List<String> excludedRoleNames = Attrs.currentContext().getAttribute("#{cc.attrs.excludedRoleNames}", List.class);
    return RoleUtils.findRoles(fromRoles, excludedRoleNames, query);
  }

  public MethodExpression getCompleteRoleMethod() {
    return completeRoleMethod;
  }
}
