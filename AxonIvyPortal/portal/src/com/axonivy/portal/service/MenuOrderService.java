package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.dto.menu.MenuOrder;
import com.axonivy.portal.dto.menu.MenuOrderEntry;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;

public class MenuOrderService {

  private static MenuOrderService instance;

  public static MenuOrderService getInstance() {
    if (instance == null) {
      instance = new MenuOrderService();
    }
    return instance;
  }

  /** The ordered menu entries, or an empty list when nothing has been persisted yet. */
  public List<MenuOrderEntry> findAll() {
    String jsonValue = Ivy.var().get(PortalVariable.MENU_ORDER.key);
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    MenuOrder menuOrder = BusinessEntityConverter.jsonValueToEntity(jsonValue, MenuOrder.class);
    return Optional.ofNullable(menuOrder).map(MenuOrder::getMenus).orElseGet(ArrayList::new);
  }

  /** Persists the given order, wrapping it in a {@link MenuOrder} object stamped with the latest version. */
  public void save(List<MenuOrderEntry> menus) {
    MenuOrder menuOrder = new MenuOrder(AbstractJsonVersion.LATEST, menus);
    Ivy.var().set(PortalVariable.MENU_ORDER.key, BusinessEntityConverter.entityToJsonValue(menuOrder));
  }
}
