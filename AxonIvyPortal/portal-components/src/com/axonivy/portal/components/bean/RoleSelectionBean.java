package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.jsf.Attrs;
import com.axonivy.portal.components.util.BeanUtils;
import com.axonivy.portal.components.util.RoleUtils;

import ch.ivyteam.ivy.environment.Ivy;

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
    fromRoles.forEach(x->Ivy.log().error("from value : {0}",x));
    List<String> excludedRoleNames = Attrs.currentContext().getAttribute("#{cc.attrs.excludedRoleNames}", List.class);
    if (excludedRoleNames != null) {
      
      excludedRoleNames.forEach(x->Ivy.log().error("exclude value : {0}",x));
    }
    Ivy.log().error("query is {0}", query);
    final var roles = RoleUtils.findRoles(fromRoles, excludedRoleNames, query);
    if (CollectionUtils.isNotEmpty(roles)) {
      roles.forEach(x->Ivy.log().error("return value value : {0}",x));      
    } else {
      Ivy.log().error("EMPTY ROLES");
    }
    return roles;
  }

  public MethodExpression getCompleteRoleMethod() {
    return completeRoleMethod;
  }
}
