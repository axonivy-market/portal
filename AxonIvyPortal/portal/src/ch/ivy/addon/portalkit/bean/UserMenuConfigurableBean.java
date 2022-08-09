package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class UserMenuConfigurableBean implements Serializable {

  private static final long serialVersionUID = 2602764633968843419L;
  private List<UserMenu> menus;
  
  @PostConstruct
  public void initConfigration() {
    this.menus = getUserMenusConfiguration();
  }
  
  private List<UserMenu> getUserMenusConfiguration(){
    String userMenusJson = Ivy.var().get(PortalVariable.USER_MENU.key);
    List<UserMenu> userMenus = BusinessEntityConverter.jsonValueToEntities(userMenusJson, UserMenu.class);
    userMenus.removeIf(userMenu -> {
      List<String> permissions = userMenu.getPermissions();
      if (permissions == null) {
        return false;
      } else {
        for (String permission : permissions) {
          if (isSessionUserHasPermisson(permission)) {
            return false;
          }
        }
      }
      return true;
    });
    return userMenus;
  }
  
  private static boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#") ? StringUtils.equals(Ivy.session().getSessionUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public List<UserMenu> getMenus() {
    return menus;
  }

  public void setMenus(List<UserMenu> menus) {
    this.menus = menus;
  }
}
