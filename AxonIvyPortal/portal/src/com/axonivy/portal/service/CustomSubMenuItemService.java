package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.comparator.CustomSubMenuItemComparator;
import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStart;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.exec.Sudo;

public class CustomSubMenuItemService {

  public final static String SUB_MENU = "subMenuItems";
  public final static String DEFAULT_ICON = "si si si-hierarchy-6 si-rotate-270";

  public static List<SubMenuItem> findAll() {
    List<CustomSubMenuItem> customMenus = loadFromSubProcess();
    customMenus.addAll(loadFromConfiguration());

    return customMenus.stream()
        .sorted(new CustomSubMenuItemComparator())
        .map(convertToSubmenuItem())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private static List<CustomSubMenuItem> loadFromSubProcess() {
    return Sudo.get(() -> {
      var filter = SubProcessSearchFilter.create()
          .setSearchScope(SearchScope.SECURITY_CONTEXT)
          .setSignature(PortalCustomSignature.LOAD_SUB_MENU_ITEMS.getSignature()).toFilter();

      var subProcessStartList = SubProcessCallStart.find(filter);
      if (CollectionUtils.isEmpty(subProcessStartList)) {
        return new ArrayList<>();
      }

      List<CustomSubMenuItem> customSubMenuItems = new ArrayList<>();
      subProcessStartList.forEach(process -> {
        Map<String, Object> subMenuList = new HashMap<>(process.call().asMap());
        customSubMenuItems.addAll((List<CustomSubMenuItem>)subMenuList.get(SUB_MENU));
      });

      return customSubMenuItems;
    });
  }

  private static Function<CustomSubMenuItem, SubMenuItem> convertToSubmenuItem() {
    return customMenu -> {
      if (Optional.ofNullable(customMenu).isEmpty()
          || StringUtils.isBlank(customMenu.getLink())
          || StringUtils.isBlank(customMenu.getLabel())) {
        return null;
      }

      SubMenuItem result = new SubMenuItem();
      result.setLink(customMenu.getLink());
      result.setLabel(customMenu.getLabel());
      result.setName(HomepageType.CUSTOM.name());
      result.setIcon(StringUtils.defaultIfBlank(customMenu.getIcon(), DEFAULT_ICON));

      result.setMenuKind(Optional.ofNullable(customMenu)
          .map(CustomSubMenuItem::getIsExternalLink)
          .orElse(false) ? 
              MenuKind.EXTERNAL_LINK : MenuKind.CUSTOM);

      return result;
    };
  }

  private static List<CustomSubMenuItem> loadFromConfiguration() {
    String menuJson = Ivy.var().get(PortalVariable.CUSTOM_MENU_ITEMS.key);
    return BusinessEntityConverter.jsonValueToEntities(menuJson, CustomSubMenuItem.class);
  }
}