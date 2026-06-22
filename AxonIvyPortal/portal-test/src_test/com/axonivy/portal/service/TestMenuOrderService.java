package com.axonivy.portal.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.menu.MenuOrderEntry;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestMenuOrderService {

  MenuOrderService service = MenuOrderService.getInstance();

  @AfterEach
  void tearDown() {
    Ivy.var().set(PortalVariable.MENU_ORDER.key, "");
  }

  @Test
  void findAll_returnsEmptyListWhenNothingPersisted() {
    assertThat(service.findAll()).isEmpty();
  }

  @Test
  void save_thenFindAll_roundTripsIdAndSource() {
    List<MenuOrderEntry> entries = List.of(
        new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD),
        new MenuOrderEntry("PROCESS", MenuSource.STANDARD));

    service.save(entries);

    List<MenuOrderEntry> loaded = service.findAll();
    assertThat(loaded).hasSize(2);
    assertThat(loaded.get(0).getId()).isEqualTo("DASHBOARD");
    assertThat(loaded.get(0).getSource()).isEqualTo(MenuSource.STANDARD);
    assertThat(loaded.get(1).getId()).isEqualTo("PROCESS");
    assertThat(loaded.get(1).getSource()).isEqualTo(MenuSource.STANDARD);
  }

  @Test
  void save_preservesOrderOfEntries() {
    List<MenuOrderEntry> entries = List.of(
        new MenuOrderEntry("A", MenuSource.CUSTOM_MENU_CONFIGURATION),
        new MenuOrderEntry("B", MenuSource.DASHBOARD),
        new MenuOrderEntry("C", MenuSource.STANDARD));

    service.save(entries);

    List<MenuOrderEntry> loaded = service.findAll();
    assertThat(loaded).extracting(MenuOrderEntry::getId)
        .containsExactly("A", "B", "C");
  }

  @Test
  void save_overwritesPreviousOrder() {
    service.save(List.of(new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD)));
    service.save(List.of(new MenuOrderEntry("PROCESS", MenuSource.STANDARD)));

    List<MenuOrderEntry> loaded = service.findAll();
    assertThat(loaded).hasSize(1);
    assertThat(loaded.get(0).getId()).isEqualTo("PROCESS");
  }

  @Test
  void save_emptyList_thenFindAll_returnsEmpty() {
    service.save(List.of(new MenuOrderEntry("DASHBOARD", MenuSource.STANDARD)));
    service.save(List.of());

    assertThat(service.findAll()).isEmpty();
  }

  @Test
  void save_preservesDifferentSourceTypes() {
    List<MenuOrderEntry> entries = List.of(
        new MenuOrderEntry("std-1", MenuSource.STANDARD),
        new MenuOrderEntry("dash-1", MenuSource.DASHBOARD),
        new MenuOrderEntry("custom-1", MenuSource.CUSTOM_MENU_CONFIGURATION),
        new MenuOrderEntry("callable-1", MenuSource.CALLABLE),
        new MenuOrderEntry("thirdparty-1", MenuSource.THIRD_PARTY_APP_CONFIGURATION));

    service.save(entries);

    List<MenuOrderEntry> loaded = service.findAll();
    assertThat(loaded).hasSize(5);
    assertThat(loaded).extracting(MenuOrderEntry::getSource)
        .containsExactly(
            MenuSource.STANDARD,
            MenuSource.DASHBOARD,
            MenuSource.CUSTOM_MENU_CONFIGURATION,
            MenuSource.CALLABLE,
            MenuSource.THIRD_PARTY_APP_CONFIGURATION);
  }
}
