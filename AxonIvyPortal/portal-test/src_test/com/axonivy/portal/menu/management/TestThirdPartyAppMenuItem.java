package com.axonivy.portal.menu.management;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.menu.management.adapter.ThirdPartyAppMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.ivy.addon.portalkit.configuration.Application;

class TestThirdPartyAppMenuItem {

  @Test
  void nullMenuOrdinal_isPreservedByAdapter_andFailsWhenPassedToBuilder() {
    ExternalLinkMenuItemDefinition definition = toDefinition(null);

    assertThat(definition.getIndex()).isNull();
    // The sidebar must assign a rendering index before passing this definition to the builder.
    assertThatThrownBy(() -> buildMenuItem(definition))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void nonNullMenuOrdinal_isUsedForRenderedMenuId() {
    ExternalLinkMenuItemDefinition definition = toDefinition(7);

    PortalMenuItem item = buildMenuItem(definition);

    assertThat(item.getIndex()).isEqualTo(7);
    assertThat(item.getId()).isEqualTo("third_party_7");
  }

  @Test
  void nullMenuOrdinal_withAssignedRenderingIndex_buildsSuccessfully() {
    ExternalLinkMenuItemDefinition definition = toDefinition(null);
    definition.setIndex(2);

    PortalMenuItem item = buildMenuItem(definition);

    assertThat(item.getIndex()).isEqualTo(2);
    assertThat(item.getId()).isEqualTo("third_party_2");
  }

  @Test
  void assignedRenderingIndices_avoidCollisionsWithPublicAndPrivateItems() {
    ExternalLinkMenuItemDefinition publicDefinition = toDefinition(0);
    ExternalLinkMenuItemDefinition firstPrivateDefinition = toDefinition(0);
    ExternalLinkMenuItemDefinition secondPrivateDefinition = toDefinition(0);
    // Public definitions already have sequential indices from MenuLoader.
    publicDefinition.setIndex(0);
    int nextIndex = 1;
    firstPrivateDefinition.setIndex(nextIndex++);
    secondPrivateDefinition.setIndex(nextIndex++);

    assertThat(buildMenuItem(publicDefinition).getId()).isEqualTo("third_party_0");
    assertThat(buildMenuItem(firstPrivateDefinition).getId()).isEqualTo("third_party_1");
    assertThat(buildMenuItem(secondPrivateDefinition).getId()).isEqualTo("third_party_2");
  }

  @Test
  void assignedRenderingIndex_doesNotChangeStoredMenuOrdinal() {
    Application application = new Application();
    application.setId("test-private-app");
    application.setMenuOrdinal(7);
    ExternalLinkMenuItemDefinition definition = ThirdPartyAppMenuItemDefinitionAdapter.getInstance()
        .toMenuDefinition(application, MenuSource.THIRD_PARTY_APP_CONFIGURATION);
    definition.setIndex(2);

    assertThat(buildMenuItem(definition).getId()).isEqualTo("third_party_2");
    assertThat(application.getMenuOrdinal()).isEqualTo(7);
  }

  private static ExternalLinkMenuItemDefinition toDefinition(Integer menuOrdinal) {
    Application application = new Application();
    application.setId("test-third-party-app");
    application.setLink("https://example.com");
    application.setMenuOrdinal(menuOrdinal);
    return ThirdPartyAppMenuItemDefinitionAdapter.getInstance()
        .toMenuDefinition(application, MenuSource.THIRD_PARTY_APP_CONFIGURATION);
  }

  private static PortalMenuItem buildMenuItem(ExternalLinkMenuItemDefinition definition) {
    return new PortalMenuBuilder(definition.getDisplayTitle(), MenuKind.THIRD_PARTY, false)
        .menuIndex(definition.getIndex())
        .url(definition.getUrl())
        .cleanParam(true)
        .build();
  }
}
