package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition.MenuSource;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

public final class MenuUtils {
  private static final String DASH = "-";

  public static String generateId() {
    return UUID.randomUUID().toString().replace(DASH, StringUtils.EMPTY);
  }

  public static Map<String, PortalMenuItemDefinition> createMapIdToMenu(List<PortalMenuItemDefinition> menus) {
    Map<String, PortalMenuItemDefinition> idToMenu = new LinkedHashMap<>();
    menus.forEach(menu -> idToMenu.put(menu.getId(), menu));
    return idToMenu;
  }

  /**
   * Initialize permissions and readable permission display string on the menu.
   * 
   * @param menu the target menu definition
   */
  public static void initPermissions(PortalMenuItemDefinition menu) {
    initMenuPermissionDtos(menu);

    List<SecurityMemberDTO> permissionDTOs = menu.getPermissionDTOs();
    if (CollectionUtils.isEmpty(permissionDTOs)) {
      return;
    }

    // Distinct by member name
    List<SecurityMemberDTO> distinctPermissions = permissionDTOs.stream()
        .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, Function.identity(), (first, second) -> first))
        .values().stream().toList();

    // Replace original list
    permissionDTOs.clear();
    permissionDTOs.addAll(distinctPermissions);

    // Set displayed permissions as comma-separated names
    String displayedPermission = distinctPermissions.stream().map(SecurityMemberDTO::getDisplayName)
        .collect(Collectors.joining(", "));

    menu.setDisplayedPermission(displayedPermission);
  }

  /**
   * Populates the {@code permissionDTOs} of a menu from its string-based
   * permission list.
   * 
   * @param menu the target menu definition
   */
  public static void initMenuPermissionDtos(PortalMenuItemDefinition menu) {
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

  /**
   * Converts an application to a Menu Definition
   * 
   * @param app                the registered application
   * @param supportedLanguages list of supported locale tags
   * @return a new menu definition type External Link
   */
  public static PortalMenuItemDefinition fromApplication(Application app, List<String> supportedLanguages) {
    ExternalLinkMenuItemDefinition menuDefinition = new ExternalLinkMenuItemDefinition();

    menuDefinition.setSource(MenuSource.THIRD_PARTY_APP_CONFIGURATION);
    menuDefinition.setIcon(app.getMenuIcon());
    menuDefinition.setUrl(app.getLink());
    menuDefinition.setId(app.getId());
    menuDefinition.setIsPublic(app.getIsPublic());

    Map<String, String> displayNames = DisplayNameConvertor.parseJson(app.getDisplayName()).getDisplayNameAsMap();

    List<DisplayName> titles = supportedLanguages.stream()
        .map(lang -> new DisplayName(Locale.forLanguageTag(lang), displayNames.get(lang))).collect(Collectors.toList());

    menuDefinition.setTitles(titles);
    menuDefinition.setDisplayTitle(getDisplayTitle(menuDefinition));
    return menuDefinition;
  }

  /**
   * Converts a Menu Definition to an application.
   * 
   * @param menuDefinition the Menu Definition to convert
   * @return the corresponding application instance
   */
  public static Application toApplication(PortalMenuItemDefinition menuDefinition) {
    Application app = Optional.ofNullable(RegisteredApplicationService.getInstance().findById(menuDefinition.getId()))
        .orElseGet(() -> new Application());

    app.setIsPublic(menuDefinition.getIsPublic());
    app.setLink(menuDefinition.getUrl());
    app.setMenuIcon(menuDefinition.getIcon());
    app.setVersion(menuDefinition.getVersion());

    DisplayNameConvertor converter = new DisplayNameConvertor();
    for (DisplayName displayName : menuDefinition.getTitles()) {
      converter.add(displayName.getLocale(), displayName.getValue());
    }
    app.setDisplayName(converter.toJson());
    return app;
  }

  /**
   * Converts a Menu Definition to a CustomSubMenuItem.
   * 
   * @param menuDefinition         the Menu Definition
   * @param menusFromConfiguration list of existing submenu configurations
   * @return converted CustomSubMenuItem instance
   */
  public static CustomSubMenuItem toCustomSubMenuItem(PortalMenuItemDefinition menuDefinition,
      List<CustomSubMenuItem> menusFromConfiguration) {

    CustomSubMenuItem item = menusFromConfiguration.stream().filter(menu -> menu.getId() == menuDefinition.getId())
        .findFirst().orElseGet(() -> new CustomSubMenuItem());

    item.setIcon(menuDefinition.getIcon());
    item.setLink(menuDefinition.getUrl());
    item.setIndex(menuDefinition.getIndex());
    item.setVersion(menuDefinition.getVersion());
    item.setTitles(new ArrayList<>());
    for (DisplayName displayName : menuDefinition.getTitles()) {
      com.axonivy.portal.components.dto.DisplayName itemDisplayName = new com.axonivy.portal.components.dto.DisplayName();
      itemDisplayName.setLocale(displayName.getLocale());
      itemDisplayName.setValue(displayName.getValue());
    }
    return item;
  }

  /**
   * Creates a Menu Definition from a CustomSubMenuItem.
   * 
   * @param item               the custom menu item
   * @param isLoadFromCallable whether it was loaded from a callable process
   * @param supportedLanguages list of supported locale tags
   * @return appropriate Menu Definition (external link or custom)
   */
  public static PortalMenuItemDefinition fromCustomSubMenuItem(CustomSubMenuItem item, boolean isLoadFromCallable,
      List<String> supportedLanguages) {
    if (BooleanUtils.isTrue(item.getIsExternalLink())) {
      ExternalLinkMenuItemDefinition externalLink = new ExternalLinkMenuItemDefinition();
      externalLink.setId(item.getId());
      convertFromCustomSubMenuItem(item, externalLink, supportedLanguages);
      externalLink.setSource(isLoadFromCallable ? MenuSource.CALLABLE : MenuSource.CUSTOM_MENU_CONFIGURATION);
      return externalLink;
    } else {
      CustomMenuItemDefinition customMenu = new CustomMenuItemDefinition();
      customMenu.setId(item.getId());
      customMenu.setProcessStartPath(item.getLink());
      convertFromCustomSubMenuItem(item, customMenu, supportedLanguages);
      customMenu.setSource(isLoadFromCallable ? MenuSource.CALLABLE : MenuSource.CUSTOM_MENU_CONFIGURATION);
      return customMenu;
    }
  }

  /**
   * Updates the menu definition fields from the given CustomSubMenuItem.
   * 
   * @param item               the source custom submenu item
   * @param menuDefinition     the target Menu Definition
   * @param supportedLanguages list of supported locale tags
   */
  public static void convertFromCustomSubMenuItem(CustomSubMenuItem item, PortalMenuItemDefinition menuDefinition,
      List<String> supportedLanguages) {
    menuDefinition.setDescription(item.getLabel());
    menuDefinition.setUrl(item.getLink());
    menuDefinition.setIcon(item.getIcon());
    menuDefinition.setIndex(item.getIndex());
    menuDefinition.setVersion(item.getVersion());

    menuDefinition.setTitles(new ArrayList<>());
    if (CollectionUtils.isEmpty(item.getTitles()) && StringUtils.isNotBlank(item.getLabel())) {
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName(Locale.forLanguageTag(language), item.getLabel());
        menuDefinition.getTitles().add(displayName);
        menuDefinition.setDisplayTitle(displayName.getValue());
      }
    } else {
      for (com.axonivy.portal.components.dto.DisplayName itemDisplayName : item.getTitles()) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(itemDisplayName.getLocale());
        displayName.setValue(itemDisplayName.getValue());
        menuDefinition.getTitles().add(displayName);
        menuDefinition.setDisplayTitle(getDisplayTitle(menuDefinition));
      }
    }
  }

  /**
   * Creates a Menu Definition type Dashboard from a Dashboard object.
   * 
   * @param dashboard the dashboard object
   * @return a new Menu Definition
   */
  public static DashboardMenuItemDefinition fromDashboard(Dashboard dashboard) {
    DashboardMenuItemDefinition menuDefinition = new DashboardMenuItemDefinition();
    menuDefinition.setId(dashboard.getId());
    menuDefinition.setSource(MenuSource.DASHBOARD);
    menuDefinition.setVersion(dashboard.getVersion());
    menuDefinition.setDashboard(dashboard);
    menuDefinition.setDashboardId(dashboard.getId());
    menuDefinition.setDescription(dashboard.getDescription());
    menuDefinition.setIcon(dashboard.getIcon());
    menuDefinition.setPermissions(dashboard.getPermissions());
    menuDefinition.setTitles(dashboard.getTitles());
    menuDefinition.setDisplayTitle(getDisplayTitle(menuDefinition));
    return menuDefinition;
  }

  /**
   * Gets the localized display title of a Menu Definition for the current user
   * locale.
   * 
   * @param menu the Menu Definition
   * @return the localized display name or empty string if not found
   */
  public static String getDisplayTitle(PortalMenuItemDefinition menu) {
    if (menu == null || CollectionUtils.isEmpty(menu.getTitles())) {
      return StringUtils.EMPTY;
    }

    String currentLocaleLang = Locales.getCurrentLocale().toLanguageTag();
    return menu.getTitles().stream().filter(title -> currentLocaleLang.equals(title.getLocale().toLanguageTag()))
        .map(DisplayName::getValue).findFirst().orElse(StringUtils.EMPTY);
  }

  /**
   * Reorders the Menu Definitions by shifting their index values based on
   * drag-and-drop style movement.
   * 
   * @param menuDefinitions list of menu definitions
   * @param fromIndex       the original index of the moved item
   * @param toIndex         the new index of the moved item
   */
  public static void reorderMenuDefinitions(List<PortalMenuItemDefinition> menuDefinitions, int fromIndex,
      int toIndex) {
    if (CollectionUtils.isEmpty(menuDefinitions) || fromIndex == toIndex) {
      return;
    }

    for (var menu : menuDefinitions) {
      int index = menu.getIndex();

      if (fromIndex > toIndex) {
        // Item moved up: shift down others in range [toIndex, fromIndex-1]
        if (index >= toIndex && index < fromIndex) {
          menu.setIndex(index + 1);
        } else if (index == fromIndex) {
          menu.setIndex(toIndex);
        }
      } else {
        // Item moved down: shift up others in range [fromIndex+1, toIndex]
        if (index > fromIndex && index <= toIndex) {
          menu.setIndex(index - 1);
        } else if (index == fromIndex) {
          menu.setIndex(toIndex);
        }
      }
    }
  }
}