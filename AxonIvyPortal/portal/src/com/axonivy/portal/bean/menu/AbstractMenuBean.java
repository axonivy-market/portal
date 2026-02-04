package com.axonivy.portal.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.components.util.SecurityMemberDisplayNameUtils;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.util.MenuUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

public abstract class AbstractMenuBean implements Serializable {

  private static final long serialVersionUID = 6836359211082018597L;

  public String getDisplayTitle(PortalMenuItemDefinition menu) {
    return MenuUtils.getDisplayTitle(menu);
  }

  public List<String> getSupportedLanguage() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }

  public void initMenuPermissionDtos(PortalMenuItemDefinition menu) {
    if (menu.getPermissionDTOs() == null) {
      menu.setPermissionDTOs(new ArrayList<>());
    }
    if (CollectionUtils.isNotEmpty(menu.getPermissions())) {
      for (String permission : menu.getPermissions()) {
        if (permission.startsWith("#")) {
          menu.getPermissionDTOs().add(SecurityMemberDTOMapper
              .mapFromUserDTO(new UserDTO(UserUtils.findUserByUsername(permission.substring(1)))));
        } else {
          menu.getPermissionDTOs()
              .add(SecurityMemberDTOMapper.mapFromRoleDTO(new RoleDTO(RoleUtils.findRole(permission))));
        }
      }
    }
  }

  public String getDisplayMenuTitle(PortalMenuItemDefinition menu) {
    if (Optional.ofNullable(menu).map(PortalMenuItemDefinition::getType).isEmpty()) {
      return StringUtils.EMPTY;
    }

    if (StringUtils.isNotBlank(menu.getDisplayTitle())) {
      return menu.getDisplayTitle();
    }

    if (menu.getType() == MenuKind.STANDARD) {
      StandardMenuItemDefinition standardMenu = (StandardMenuItemDefinition) menu;
      return Ivy.cms().co(standardMenu.getStandardType().getCmsUri());
    }

    String currentLanguage = UserUtils.getUserLanguage();

    return menu.getTitles().stream().filter(m -> m.getLocale().toLanguageTag().contentEquals(currentLanguage))
        .findFirst().map(DisplayName::getValue).orElseGet(() -> "");
  }

  public String getMenuKindLabel(MenuKind kind) {
    return Ivy.cms().co(kind.getCmsUri());
  }

  public String formatName(SecurityMemberDTO responsible) {
    return SecurityMemberDisplayNameUtils.generateDisplayNameForSecurityMemberDTO(responsible);
  }
}
