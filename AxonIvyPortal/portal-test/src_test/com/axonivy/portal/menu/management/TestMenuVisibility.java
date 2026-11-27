package com.axonivy.portal.menu.management;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.bean.menu.MenuManagementBean;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.service.CustomSubMenuItemService;
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

  @Test
  void lastVisibleMenu_countsDefaultVisibleRows_andAllowsHiddenRows() {
    TestMenuBean bean = new TestMenuBean();
    PortalMenuItemDefinition dashboard = standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD);
    PortalMenuItemDefinition process = standard(StandardMenuItemDefinitionType.PROCESS, MenuSource.STANDARD);
    bean.setMenuDefinitions(List.of(dashboard, process));
    assertThat(bean.isLastVisibleMenu(dashboard)).isFalse();

    process.setVisible(false);
    bean.setMenuDefinitions(List.of(dashboard, process));
    assertThat(bean.isLastVisibleMenu(dashboard)).isTrue();
    assertThat(bean.isLastVisibleMenu(process)).isFalse();
  }

  @Test
  void hideLastVisibleMenu_reloadsState_andLeavesManifestUnchanged() {
    MenuOrderService.getInstance().save(List.of(
        new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD, true),
        new MenuOrderEntry("PROCESS", MenuSource.STANDARD, false)));
    TestMenuBean bean = new TestMenuBean();
    bean.setMenuDefinitions(List.of(
        standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD),
        standard(StandardMenuItemDefinitionType.PROCESS, MenuSource.STANDARD)));

    bean.disableMenu(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD));

    assertThat(bean.lastVisibleMenuWarningShown).isTrue();
    assertThat(bean.isSidebarSettingsSaved()).isFalse();
    assertThat(MenuOrderService.getInstance().findAll()).extracting(MenuOrderEntry::visible)
        .containsExactly(true, false);
  }

  @Test
  void deleteLastVisibleMenu_rejectsBeforeSourceRemoval() {
    ExternalLinkMenuItemDefinition link = new ExternalLinkMenuItemDefinition();
    link.setDisplayTitle("IvyTest-LastVisible");
    link.setUrl("https://example.com");
    link.setSource(MenuSource.CUSTOM_MENU_CONFIGURATION);
    MenuCreationHandler.createMenu(link);
    try {
      MenuOrderService.getInstance().save(List.of(
          new MenuOrderEntry(link.getId(), link.getSource(), true),
          new MenuOrderEntry("PROCESS", MenuSource.STANDARD, false)));
      TestMenuBean bean = new TestMenuBean();
      bean.setSelectedMenuDefinition(link);

      bean.removeMenu();

      assertThat(bean.lastVisibleMenuWarningShown).isTrue();
      assertThat(CustomSubMenuItemService.loadFromConfiguration())
          .anyMatch(item -> link.getId().equals(item.getId()));
      assertThat(MenuOrderService.getInstance().findAll()).extracting(MenuOrderEntry::getId)
          .containsExactly(link.getId(), "PROCESS");
    } finally {
      MenuRemovalHandler.removeMenu(link);
    }
  }

  @Test
  void showAnotherMenu_allowsHidingOriginal_andRepeatedHide() {
    MenuOrderService.getInstance().save(List.of(
        new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD, true),
        new MenuOrderEntry("PROCESS", MenuSource.STANDARD, false)));
    TestMenuBean bean = new TestMenuBean();
    bean.enableMenu(standard(StandardMenuItemDefinitionType.PROCESS, MenuSource.STANDARD));
    bean.disableMenu(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD));
    bean.disableMenu(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD));

    assertThat(bean.lastVisibleMenuWarningShown).isFalse();
    assertThat(MenuOrderService.getInstance().findAll()).extracting(MenuOrderEntry::visible)
        .containsExactly(false, true);
  }

  @Test
  void allHiddenConfiguration_canShowMenu() {
    MenuOrderService.getInstance().save(List.of(
        new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD, false),
        new MenuOrderEntry("PROCESS", MenuSource.STANDARD, false)));
    TestMenuBean bean = new TestMenuBean();

    bean.enableMenu(standard(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD));

    assertThat(bean.isSidebarSettingsSaved()).isTrue();
    assertThat(MenuOrderService.getInstance().findAll()).extracting(MenuOrderEntry::visible)
        .containsExactly(true, false);
  }

  private static class TestMenuBean extends MenuManagementBean {
    private boolean lastVisibleMenuWarningShown;

    @Override
    protected void verifySidebarManagementPermission() {
      // Isolate the guard from permission setup; production handlers retain authorization.
    }

    @Override
    protected void showLastVisibleMenuWarning() {
      lastVisibleMenuWarningShown = true;
    }

    @Override
    public void loadMenus() {
      setMenuDefinitions(MenuOrderService.getInstance().findAll().stream().map(entry -> {
        PortalMenuItemDefinition menu;
        if (entry.getSource() == MenuSource.CUSTOM_MENU_CONFIGURATION) {
          menu = new ExternalLinkMenuItemDefinition();
          menu.setId(entry.getId());
          menu.setSource(entry.getSource());
        } else {
          menu = standard(StandardMenuItemDefinitionType.valueOf(entry.getId()), entry.getSource());
        }
        menu.setVisible(entry.visible());
        return menu;
      }).toList());
    }
  }

  private static StandardMenuItemDefinition standard(StandardMenuItemDefinitionType type, MenuSource source) {
    StandardMenuItemDefinition definition = new StandardMenuItemDefinition(type);
    definition.setId(type.name());
    definition.setSource(source);
    return definition;
  }
}
