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
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.enums.PortalCustomSignature;
import com.axonivy.portal.menu.MenuId;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.exec.Sudo;

public class CustomSubMenuItemService {

  public final static String SUB_MENU = "subMenuItems";
  public final static String DEFAULT_ICON = "ti ti-sitemap ti-rotate-270";

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
  public static List<CustomSubMenuItem> loadFromSubProcess() {
    return Sudo.get(() -> {
      var filter = SubProcessSearchFilter.create()
          .setSearchScope(SearchScope.SECURITY_CONTEXT)
          .setSignature(PortalCustomSignature.LOAD_SUB_MENU_ITEMS.getSignature()).toFilter();

      var subProcessStartList = SubProcessCallStartEvent.find(filter);
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
      String link = customMenu.getLink();
      if (customMenu.getMenuKind() == MenuKind.STATIC_PAGE) {
        link = PortalNavigatorAPI.buildPortalStaticPageUrl(link);
      }
      result.setLink(link);
      result.setLabel(customMenu.getLabel());
      result.setIcon(StringUtils.defaultIfBlank(customMenu.getIcon(), DEFAULT_ICON));

      if (customMenu.getMenuKind() != null) {
        result.setMenuKind(customMenu.getMenuKind());
      } else {
        result.setMenuKind(Optional.ofNullable(customMenu).map(CustomSubMenuItem::getIsExternalLink).orElse(false)
            ? MenuKind.EXTERNAL_LINK
            : MenuKind.CUSTOM);
      }

      String hashId = MenuId.compute(customMenu);
      result.setId(StringUtils.isNotBlank(customMenu.getId()) ? customMenu.getId() : hashId);
      result.setName(HomepageUtils.generateHomepageId(result.getMenuKind(), result.getId()));

      return result;
    };
  }

  public static List<CustomSubMenuItem> loadFromConfiguration() {
    String menuJson = Ivy.var().get(PortalVariable.CUSTOM_MENU_ITEMS.key);
    return BusinessEntityConverter.jsonValueToEntities(menuJson, CustomSubMenuItem.class);
  }

  public static CustomSubMenuItem saveConfiguration(CustomSubMenuItem entity) {
    List<CustomSubMenuItem> existedEntities = loadFromConfiguration();
    String entityKey = identityKey(entity);
    existedEntities.removeIf(e -> Objects.nonNull(e) && entityKey.equals(identityKey(e)));
    existedEntities.add(entity);
    Ivy.var().set(PortalVariable.CUSTOM_MENU_ITEMS.key, BusinessEntityConverter.entityToJsonValue(existedEntities));
    return entity;
  }

  public static void removeConfiguration(CustomSubMenuItem entity) {
    List<CustomSubMenuItem> existedEntities = loadFromConfiguration();
    String entityKey = identityKey(entity);
    existedEntities.removeIf(e -> Objects.nonNull(e) && entityKey.equals(identityKey(e)));
    Ivy.var().set(PortalVariable.CUSTOM_MENU_ITEMS.key, BusinessEntityConverter.entityToJsonValue(existedEntities));
  }

  /**
   * Stable identity for matching persisted entries. Legacy {@code Portal.CustomMenuItems}
   * entries predate the {@code id} field and deserialize with {@code id == null}, so we
   * fall back to the deterministic {@link MenuId#compute(CustomSubMenuItem)} hash to keep
   * edits/deletes from leaving duplicates or undeletable items.
   */
  private static String identityKey(CustomSubMenuItem item) {
    return StringUtils.isNotBlank(item.getId()) ? item.getId() : MenuId.compute(item);
  }
}