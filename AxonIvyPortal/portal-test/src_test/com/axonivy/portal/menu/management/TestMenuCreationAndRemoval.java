package com.axonivy.portal.menu.management;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestMenuCreationAndRemoval {

  private static final String TEST_LABEL_PREFIX = "IvyTest-";

  @AfterEach
  void tearDown() {
    Optional.ofNullable(CustomSubMenuItemService.loadFromConfiguration()).orElse(List.of()).stream()
        .filter(item -> item.getLabel() != null && item.getLabel().startsWith(TEST_LABEL_PREFIX))
        .forEach(CustomSubMenuItemService::removeConfiguration);
  }

  @Test
  void createExternalLink_assignsId_and_persists() {
    ExternalLinkMenuItemDefinition def = externalLink("IvyTest-Create", "https://ivy-test.example.com");

    MenuCreationHandler.createMenu(def);

    assertThat(def.getId()).isNotBlank();
    assertThat(loadedLabels()).contains("IvyTest-Create");
  }

  @Test
  void createExternalLink_thenRemove_leavesNoTrace() {
    ExternalLinkMenuItemDefinition def = externalLink("IvyTest-Remove", "https://ivy-test-remove.example.com");

    MenuCreationHandler.createMenu(def);
    assertThat(loadedLabels()).contains("IvyTest-Remove");

    MenuRemovalHandler.removeMenu(def);

    assertThat(loadedLabels()).doesNotContain("IvyTest-Remove");
  }

  @Test
  void createExternalLink_updatedByModificationHandler() {
    ExternalLinkMenuItemDefinition def = externalLink("IvyTest-Update", "https://original.example.com");
    MenuCreationHandler.createMenu(def);

    def.setUrl("https://updated.example.com");
    def.setDisplayTitle("IvyTest-Updated");
    MenuModificationHandler.updateMenu(def);

    List<CustomSubMenuItem> loaded = Optional
        .ofNullable(CustomSubMenuItemService.loadFromConfiguration()).orElse(List.of());
    CustomSubMenuItem updated = loaded.stream()
        .filter(item -> "IvyTest-Updated".equals(item.getLabel()))
        .findFirst().orElse(null);

    assertThat(updated).isNotNull();
    assertThat(updated.getLink()).isEqualTo("https://updated.example.com");
  }

  @Test
  void createMultipleExternalLinks_allPersisted() {
    ExternalLinkMenuItemDefinition first = externalLink("IvyTest-Multi-1", "https://first.example.com");
    ExternalLinkMenuItemDefinition second = externalLink("IvyTest-Multi-2", "https://second.example.com");

    MenuCreationHandler.createMenu(first);
    MenuCreationHandler.createMenu(second);

    assertThat(loadedLabels()).contains("IvyTest-Multi-1", "IvyTest-Multi-2");
    assertThat(first.getId()).isNotEqualTo(second.getId());
  }

  @Test
  void createStaticPage_assignsId_and_persists() {
    StaticPageMenuItemDefinition def = staticPage("IvyTest-StaticPage", "test-pages/about");

    MenuCreationHandler.createMenu(def);

    assertThat(def.getId()).isNotBlank();
    List<CustomSubMenuItem> loaded = Optional
        .ofNullable(CustomSubMenuItemService.loadFromConfiguration()).orElse(List.of());
    assertThat(loaded).anyMatch(item -> "IvyTest-StaticPage".equals(item.getLabel()));
  }

  @Test
  void createStaticPage_thenRemove_leavesNoTrace() {
    StaticPageMenuItemDefinition def = staticPage("IvyTest-StaticPage-Remove", "test-pages/remove");

    MenuCreationHandler.createMenu(def);
    assertThat(loadedLabels()).contains("IvyTest-StaticPage-Remove");

    MenuRemovalHandler.removeMenu(def);

    assertThat(loadedLabels()).doesNotContain("IvyTest-StaticPage-Remove");
  }

  private static ExternalLinkMenuItemDefinition externalLink(String label, String url) {
    ExternalLinkMenuItemDefinition def = new ExternalLinkMenuItemDefinition();
    def.setDisplayTitle(label);
    def.setUrl(url);
    def.setOpenInNewTab(true);
    def.setSource(MenuSource.CUSTOM_MENU_CONFIGURATION);
    return def;
  }

  private static StaticPageMenuItemDefinition staticPage(String label, String path) {
    StaticPageMenuItemDefinition def = new StaticPageMenuItemDefinition();
    def.setDisplayTitle(label);
    def.setUrl(path);
    def.setSource(MenuSource.CUSTOM_MENU_CONFIGURATION);
    return def;
  }

  private static List<String> loadedLabels() {
    return Optional.ofNullable(CustomSubMenuItemService.loadFromConfiguration()).orElse(List.of())
        .stream()
        .map(CustomSubMenuItem::getLabel)
        .filter(StringUtils::isNotBlank)
        .toList();
  }
}
