package com.axonivy.portal.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ReorderEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.menu.management.MenuLoader;
import com.axonivy.portal.menu.management.MenuOrderManager;
import com.axonivy.portal.menu.management.MenuRemovalHandler;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MenuManagementBean extends AbstractMenuBean implements Serializable {

  private static final long serialVersionUID = -8511047442254110702L;

  private List<PortalMenuItemDefinition> menuDefinitions;
  private PortalMenuItemDefinition selectedMenuDefinition;

  @PostConstruct
  public void init() {
    menuDefinitions = MenuLoader.loadMenuDefinitions();
    menuDefinitions.forEach(menu -> initDisplayPermissions(menu));
    MenuOrderManager.initOrder(menuDefinitions);
    PortalMenuItemDefinitionService.getInstance().saveAllPublicConfig(menuDefinitions);
  }

  public void initDisplayPermissions(PortalMenuItemDefinition menu) {
    initMenuPermissionDtos(menu);

    if (CollectionUtils.isEmpty(menu.getPermissionDTOs())) {
      return;
    }

    List<SecurityMemberDTO> permissions = Optional.ofNullable(menu.getPermissionDTOs())
        .orElse(new ArrayList<>());
    String displayedPermission = StringUtils.EMPTY;

    if (CollectionUtils.isNotEmpty(permissions)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = permissions.stream()
          .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1))
          .values();

      displayedPermission = distinctPermissionDTOs.stream().map(SecurityMemberDTO::getDisplayName)
          .collect(Collectors.joining(Ivy.cms().co("/Labels/Comma")));
    }

    menu.setDisplayedPermission(displayedPermission);
  }

  public void onRowReorder(ReorderEvent event) {
    MenuOrderManager.reorderMenu(menuDefinitions, event.getFromIndex(), event.getToIndex());
    PortalMenuItemDefinitionService.getInstance().saveAllPublicConfig(menuDefinitions);
  }

  public boolean hasAction(PortalMenuItemDefinition menuDefinition) {
    return !MenuKind.STANDARD.equals(menuDefinition.getType());
  }
  
  public int getLastIndex() {
    return menuDefinitions.stream().map(PortalMenuItemDefinition::getIndex).sorted(Comparator.reverseOrder())
        .findFirst().orElse(1);
  }

  public void removeMenu() {
    MenuRemovalHandler.removeMenu(getSelectedMenuDefinition());
    init();
  }

  public List<PortalMenuItemDefinition> getMenuDefinitions() {
    return menuDefinitions;
  }

  public void setMenuDefinitions(List<PortalMenuItemDefinition> menuDefinitions) {
    this.menuDefinitions = menuDefinitions;
  }
  
  public PortalMenuItemDefinition getSelectedMenuDefinition() {
    return selectedMenuDefinition;
  }

  public void setSelectedMenuDefinition(PortalMenuItemDefinition selectedMenuDefinition) {
    this.selectedMenuDefinition = selectedMenuDefinition;
  }
}