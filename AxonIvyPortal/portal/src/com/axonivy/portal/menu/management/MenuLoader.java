package com.axonivy.portal.menu.management;

import static com.axonivy.portal.menu.management.enums.MenuSource.CALLABLE;
import static com.axonivy.portal.menu.management.enums.MenuSource.CUSTOM_MENU_CONFIGURATION;
import static com.axonivy.portal.menu.management.enums.MenuSource.DASHBOARD;
import static com.axonivy.portal.menu.management.enums.MenuSource.STANDARD;
import static com.axonivy.portal.menu.management.enums.MenuSource.THIRD_PARTY_APP_CONFIGURATION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.components.comparator.CustomSubMenuItemComparator;
import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.MenuOrderEntry;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.menu.MenuId;
import com.axonivy.portal.menu.management.adapter.CustomMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.DashboardMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ExternalLinkMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.StaticPageMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ThirdPartyAppMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;
import com.axonivy.portal.service.MenuOrderService;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

/**
 * Builds the ordered list of menu items by iterating {@code Portal.Menu.Order} and
 * looking up display data from each source. Items present at a source but absent
 * from the manifest are auto-appended; manifest entries whose source has been
 * removed are auto-pruned. Drift triggers a manifest re-save so the persisted
 * order stays consistent with reality.
 */
public final class MenuLoader implements Serializable {

  private static final long serialVersionUID = 7858045545456575004L;

  private MenuLoader() {}

  public static List<PortalMenuItemDefinition> loadMenuDefinitions() {
    Map<String, PortalMenuItemDefinition> bySourceRef = collectAllSourceItems();
    List<MenuOrderEntry> manifest = MenuOrderService.getInstance().findAll();
    boolean manifestWasEmpty = manifest.isEmpty();
    List<PortalMenuItemDefinition> ordered = new ArrayList<>(bySourceRef.size());
    boolean manifestChanged = false;

    for (MenuOrderEntry entry : manifest) {
      PortalMenuItemDefinition def = bySourceRef.remove(refKey(entry.getSource(), entry.getId()));
      if (def == null) {
        manifestChanged = true;
        continue;
      }
      def.setIndex(ordered.size());
      ordered.add(def);
    }

    for (PortalMenuItemDefinition def : bySourceRef.values()) {
      def.setIndex(ordered.size());
      ordered.add(def);
      manifestChanged = true;
    }

    if (manifestChanged && !manifestWasEmpty) {
      persistManifest(ordered);
    }
    return ordered;
  }

  public static void persistManifest(List<PortalMenuItemDefinition> ordered) {
    List<MenuOrderEntry> manifest = ordered.stream()
        .map(def -> new MenuOrderEntry(def.getId(), def.getSource(), AbstractJsonVersion.LATEST))
        .collect(Collectors.toList());
    MenuOrderService.getInstance().saveAllPublicConfig(manifest);
  }

  private static Map<String, PortalMenuItemDefinition> collectAllSourceItems() {
    Map<String, PortalMenuItemDefinition> all = new LinkedHashMap<>();
    putAll(all, buildStandardItems());
    putAll(all, buildDashboardItems());
    putAll(all, buildCustomMenusMixed());
    putAll(all, buildThirdPartyItems());
    return all;
  }

  private static void putAll(Map<String, PortalMenuItemDefinition> sink, List<PortalMenuItemDefinition> items) {
    for (PortalMenuItemDefinition def : items) {
      if (def != null && StringUtils.isNotBlank(def.getId()) && def.getSource() != null) {
        sink.putIfAbsent(refKey(def.getSource(), def.getId()), def);
      }
    }
  }

  private static String refKey(MenuSource source, String id) {
    return source.name() + "::" + id;
  }

  private static List<PortalMenuItemDefinition> buildStandardItems() {
    return List.of(
        buildStandardItem(StandardMenuItemDefinitionType.DASHBOARD),
        buildStandardItem(StandardMenuItemDefinitionType.PROCESS));
  }

  private static PortalMenuItemDefinition buildStandardItem(StandardMenuItemDefinitionType type) {
    StandardMenuItemDefinition menu = new StandardMenuItemDefinition(type);
    menu.setId(type.name());
    menu.setSource(STANDARD);
    menu.setVersion(AbstractJsonVersion.LATEST);
    menu.setIcon(type.getIcon());
    return menu;
  }

  private static List<PortalMenuItemDefinition> buildDashboardItems() {
    return Optional.ofNullable(DashboardService.getInstance().loadTopMenuDashboards())
        .orElse(List.of()).stream()
        .map(MenuLoader::adaptDashboard)
        .collect(Collectors.toList());
  }

  private static PortalMenuItemDefinition adaptDashboard(Dashboard dashboard) {
    return DashboardMenuItemDefinitionAdapter.getInstance().toMenuDefinition(dashboard, DASHBOARD);
  }

  private static List<PortalMenuItemDefinition> buildCustomMenusMixed() {
    record Tagged(CustomSubMenuItem item, MenuSource source) {}
    List<Tagged> tagged = new ArrayList<>();
    Optional.ofNullable(CustomSubMenuItemService.loadFromSubProcess()).orElse(List.of())
        .forEach(item -> tagged.add(new Tagged(item, CALLABLE)));
    Optional.ofNullable(CustomSubMenuItemService.loadFromConfiguration()).orElse(List.of())
        .forEach(item -> tagged.add(new Tagged(item, CUSTOM_MENU_CONFIGURATION)));

    CustomSubMenuItemComparator comparator = new CustomSubMenuItemComparator();
    tagged.sort((a, b) -> comparator.compare(a.item(), b.item()));

    List<PortalMenuItemDefinition> result = new ArrayList<>();
    for (Tagged t : tagged) {
      CustomSubMenuItem item = t.item();
      if (StringUtils.isBlank(item.getId())) {
        item.setId(MenuId.compute(item));
      }
      if (StringUtils.isBlank(item.getVersion())) {
        item.setVersion(AbstractJsonVersion.LATEST);
      }
      PortalMenuItemDefinition def = adaptCustomMenu(item, t.source());
      if (def != null) {
        result.add(def);
      }
    }
    return result;
  }

  private static PortalMenuItemDefinition adaptCustomMenu(CustomSubMenuItem item, MenuSource source) {
    if (Boolean.TRUE.equals(item.getIsExternalLink())) {
      return ExternalLinkMenuItemDefinitionAdapter.getInstance().toMenuDefinition(item, source);
    }
    if (item.getMenuKind() == MenuKind.STATIC_PAGE) {
      return StaticPageMenuItemDefinitionAdapter.getInstance().toMenuDefinition(item, source);
    }
    return CustomMenuItemDefinitionAdapter.getInstance().toMenuDefinition(item, source);
  }

  private static List<PortalMenuItemDefinition> buildThirdPartyItems() {
    return RegisteredApplicationService.getInstance().findAll().stream()
        .map(app -> ThirdPartyAppMenuItemDefinitionAdapter.getInstance()
            .toMenuDefinition(app, THIRD_PARTY_APP_CONFIGURATION))
        .collect(Collectors.toList());
  }
}
