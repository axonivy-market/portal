package com.axonivy.portal.menu.management;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.bean.menu.MenuManagementBean;
import com.axonivy.portal.dto.menu.MenuOrderEntry;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.MenuOrderService;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestMenuVisibility {

  @AfterEach
  void tearDown() {
    Ivy.var().set(PortalVariable.MENU_ORDER.key, "");
  }

  @Test
  void persistManifest_staleReorder_keepsPersistedHiddenState() {
    MenuOrderService.getInstance().save(List.of(
        new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD, false),
        new MenuOrderEntry("PROCESS", MenuSource.STANDARD, true)));

    // The stale in-memory view still has Dashboard visible; the reorder must not revert it.
    MenuLoader.persistManifest(List.of(
        standard(StandardMenuItemDefinitionType.PROCESS, MenuSource.STANDARD),
        standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD)));

    List<MenuOrderEntry> saved = MenuOrderService.getInstance().findAll();
    assertThat(saved).extracting(MenuOrderEntry::getId).containsExactly("PROCESS", "DASHBOARD");
    assertThat(saved).extracting(MenuOrderEntry::visible).containsExactly(true, false);
  }

  @Test
  void persistManifest_unpersistedEntry_defaultsToVisible() {
    MenuOrderService.getInstance().save(List.of());
    PortalMenuItemDefinition discovered = standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD);
    discovered.setVisible(false);

    MenuLoader.persistManifest(List.of(discovered));

    assertThat(MenuOrderService.getInstance().findAll()).extracting(MenuOrderEntry::visible).containsExactly(true);
  }

  @Test
  void canToggleMenuVisibility_everyRowWithIdentity() {
    MenuManagementBean bean = new MenuManagementBean();

    assertThat(bean.canToggleMenuVisibility(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD)))
        .isTrue();
    assertThat(bean.canToggleMenuVisibility(standard(StandardMenuItemDefinitionType.PROCESS, MenuSource.STANDARD)))
        .isTrue();
    assertThat(bean.canToggleMenuVisibility(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.CALLABLE)))
        .isTrue();
    assertThat(bean.canToggleMenuVisibility(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.CUSTOM_MENU_CONFIGURATION)))
        .isTrue();
  }

  private static StandardMenuItemDefinition standard(StandardMenuItemDefinitionType type, MenuSource source) {
    StandardMenuItemDefinition definition = new StandardMenuItemDefinition(type);
    definition.setId(type.name());
    definition.setSource(source);
    return definition;
  }
}
