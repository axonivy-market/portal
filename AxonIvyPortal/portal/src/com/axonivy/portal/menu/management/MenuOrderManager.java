package com.axonivy.portal.menu.management;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;
import com.axonivy.portal.util.MenuUtils;

/**
 * Responsible for managing menu ordering, including reordering based on saved
 * configurations and handling index assignments.
 */
public final class MenuOrderManager implements Serializable {

  private static final long serialVersionUID = 2760396027616082120L;

  /**
   * Reorders menu definitions based on saved snapshots.
   * 
   * @param menuDefinitions Current menu definitions to reorder
   */
  public static void initOrder(List<PortalMenuItemDefinition> menuDefinitions) {
    List<PortalMenuItemDefinition> snapshotMenuDefinitions = PortalMenuItemDefinitionService.getInstance().findAll();

    for (var menu : menuDefinitions) {
      for (var menuOrder : snapshotMenuDefinitions) {
        menuOrder.setDisplayTitle(MenuUtils.getDisplayTitle(menuOrder));
        matchAndSetIndex(menu, menuOrder);
      }
    }

    correctMenuIndex(menuDefinitions);
  }

  private static void matchAndSetIndex(PortalMenuItemDefinition menu, PortalMenuItemDefinition menuOrder) {
    switch (menu.getType()) {
      case MAIN_DASHBOARD:
        // For dashboard menu: compare the menu ID
        if (menu.getId().contentEquals(menuOrder.getId())) {
          menu.setIndex(menuOrder.getIndex());
        }
        break;
  
      case STANDARD:
        // For standard menu: compare the standard type (dashboard, task, case, or
        // process)
        if (menuOrder.getType() == MenuKind.STANDARD) {
          StandardMenuItemDefinition standardMenu = (StandardMenuItemDefinition) menu;
          StandardMenuItemDefinition standardOrder = (StandardMenuItemDefinition) menuOrder;
          if (standardMenu.getStandardType() == standardOrder.getStandardType()) {
            menu.setIndex(menuOrder.getIndex());
          }
        }
        break;
  
      case CUSTOM:
      case THIRD_PARTY:
      case EXTERNAL_LINK:
        // For other menu: compare the display title because menu of these types don't
        // have ID to compare
        if (menu.getDisplayTitle().contentEquals(menuOrder.getDisplayTitle())) {
          menu.setIndex(menuOrder.getIndex());
        }
        break;
  
      default:
        break;
    }
  }

  /**
   * Handles menu ordering by sorting and normalizing indices.
   * 
   * @param menuDefinitions Menu definitions to order
   */
  public static void correctMenuIndex(List<PortalMenuItemDefinition> menuDefinitions) {
    // Sort by current index to maintain relative order
    menuDefinitions.sort((menu1, menu2) -> {
      Integer index1 = menu1.getIndex();
      Integer index2 = menu2.getIndex();

      // Handle null indices
      if (index1 == null && index2 == null)
        return 0;
      if (index1 == null)
        return 1;
      if (index2 == null)
        return -1;

      return Integer.compare(index1, index2);
    });

    // Eliminates duplicate indices by reassigning indices by their position in the
    // list
    // Example: [1, 2, 2, 3] becomes [1, 2, 3, 4]
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