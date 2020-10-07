package ch.addon.portal.generic.userprofile.homepage;

import java.util.List;
import java.util.stream.Collectors;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portalkit.enums.MenuKind;

public class HomepageMapper {

  public static List<Homepage> toHomepages(List<SubMenuItem> menuItems) {
    return menuItems.stream().map(HomepageMapper::toHomepage).collect(Collectors.toList());
  }
  
  public static Homepage toHomepage(SubMenuItem menuItem) {
    Homepage homepage = new Homepage();
    homepage.setName(menuItem.getName());
    homepage.setLabel(menuItem.getLabel());
    homepage.setLink(menuItem.buildLink());
    if (menuItem.getMenuKind() == MenuKind.PROCESS) {
      homepage.setType(HomepageType.PROCESS);
    } else if (menuItem.getMenuKind() == MenuKind.TASK) {
      homepage.setType(HomepageType.TASK);
    } else if (menuItem.getMenuKind() == MenuKind.CASE) {
      homepage.setType(HomepageType.CASE);
    } else if (menuItem.getMenuKind() == MenuKind.STATISTICS) {
      homepage.setType(HomepageType.STATISTICS);
    } else if (menuItem.getMenuKind() == MenuKind.CUSTOM) {
      homepage.setType(HomepageType.CUSTOM);
    } else {
      homepage.setType(HomepageType.DASHBOARD);
    }
    return homepage;
  }
}
