package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.BeanUtils;
import ch.ivy.addon.portalkit.util.RoleUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class DeprecatedRoleSelectionBean implements Serializable {

  private static final long serialVersionUID = -6115408539032175241L;
  private MethodExpression completeRoleMethod;

  @PostConstruct
  public void init() {
    completeRoleMethod = BeanUtils.createCompleteMethod("#{deprecatedRoleSelectionBean.completeRole}");
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
