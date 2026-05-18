package com.axonivy.portal.menu.management;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;

import ch.ivyteam.ivy.environment.Ivy;
/**
 * Responsible for managing menu ordering, including reordering based on saved
 * configurations and handling index assignments.
 */
public final class MenuOrderManager implements Serializable {

  private static final long serialVersionUID = 2760396027616082120L;

  /**
   * Applies saved MENU to menuDefinitions. Items not found in MENU keep the natural
   * index assigned by {@code loadMenuDefinitions()} and trigger a re-save so they are tracked
   * on subsequent loads. Returns true when the caller must persist MENU: on first boot
   * (empty saved order) or when a new item has no saved entry.
   */
  public static boolean initOrder(List<PortalMenuItemDefinition> menuDefinitions) {
    List<PortalMenuItemDefinition> savedOrder = PortalMenuItemDefinitionService.getInstance().findAll();

    if (CollectionUtils.isEmpty(savedOrder)) {
      correctMenuIndex(menuDefinitions);
      return true;
    }

    Map<String, Integer> indexById = savedOrder.stream()
        .filter(s -> s.getId() != null && s.getIndex() != null)
        .collect(Collectors.toMap(PortalMenuItemDefinition::getId, PortalMenuItemDefinition::getIndex, (a, b) -> a));

    Map<StandardMenuItemDefinitionType, Integer> indexByStandardType = savedOrder.stream()
        .filter(s -> s instanceof StandardMenuItemDefinition && s.getIndex() != null)
        .collect(Collectors.toMap(
            s -> ((StandardMenuItemDefinition) s).getStandardType(),
            PortalMenuItemDefinition::getIndex, (a, b) -> a));

    Map<String, Integer> indexByDisplayTitle = savedOrder.stream()
        .filter(s -> !(s instanceof StandardMenuItemDefinition))
        .filter(s -> StringUtils.isNotBlank(s.getDisplayTitle()) && s.getIndex() != null)
        .collect(Collectors.toMap(PortalMenuItemDefinition::getDisplayTitle,
            PortalMenuItemDefinition::getIndex, (a, b) -> a));

    boolean hasNewItems = false;
    for (var menu : menuDefinitions) {
      Integer savedIndex = lookupIndex(menu, indexById, indexByStandardType, indexByDisplayTitle);
      if (savedIndex != null) {
        menu.setIndex(savedIndex);
      } else {
        hasNewItems = true;
      }
    }

    correctMenuIndex(menuDefinitions);
    return hasNewItems;
  }

  private static Integer lookupIndex(PortalMenuItemDefinition menu,
      Map<String, Integer> indexById, Map<StandardMenuItemDefinitionType, Integer> indexByStandardType,
      Map<String, Integer> indexByDisplayTitle) {
    if (menu instanceof StandardMenuItemDefinition standard) {
      return indexByStandardType.get(standard.getStandardType());
    }
    Integer byId = menu.getId() != null ? indexById.get(menu.getId()) : null;
    if (byId != null) {
      return byId;
    }
    return StringUtils.isNotBlank(menu.getDisplayTitle()) ? indexByDisplayTitle.get(menu.getDisplayTitle()) : null;
  }

  /**
   * Handles menu ordering by sorting and normalizing indices.
   * 
   * @param menuDefinitions Menu definitions to order
   */
  public static void correctMenuIndex(List<PortalMenuItemDefinition> menuDefinitions) {
    menuDefinitions.sort((menu1, menu2) -> {
      Integer index1 = menu1.getIndex();
      Integer index2 = menu2.getIndex();

      if (index1 == null && index2 == null)
        return 0;
      if (index1 == null)
        return 1;
      if (index2 == null)
        return -1;

      return Integer.compare(index1, index2);
    });

    for (int i = 0; i < menuDefinitions.size(); i++) {
      menuDefinitions.get(i).setIndex(i);
    }
  }

  /**
   * Reorders menu definitions based on drag-and-drop event.
   * 
   * @param menuDefinitions Menu definitions to reorder
   * @param fromIndex       Source index
   * @param toIndex         Destination index
   */
  public static void reorderMenu(List<PortalMenuItemDefinition> menuDefinitions, int fromIndex, int toIndex) {
    if (CollectionUtils.isEmpty(menuDefinitions)) {
      return;
    }

    Ivy.log().info("Menu reorder: position {0} -> {1}", fromIndex, toIndex);

    for (var menu : menuDefinitions) {
      if (menu.getIndex() == null) {
        continue;
      }
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