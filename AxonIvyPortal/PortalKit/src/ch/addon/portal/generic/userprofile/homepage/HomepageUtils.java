package ch.addon.portal.generic.userprofile.homepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {
  
  @SuppressWarnings("unchecked")
  public static List<Homepage> loadHomepages() {
    List<Homepage> homepages = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess("loadSubMenuItems()", null, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    List<SubMenuItem> subMenuItems = (List<SubMenuItem>) response.get("subMenuItems");
    Homepage dashboard = initDashboard();
    homepages.add(dashboard);
    for (SubMenuItem item : subMenuItems) {
      if (item.getMenuKind() != MenuKind.EXTERNAL_LINK) {
        homepages.add(HomepageMapper.toHomepage(item));
      }
    }
    return homepages;
  }

  private static Homepage initDashboard() {
    Homepage dashboard = new Homepage();
    dashboard.setName(HomepageType.DASHBOARD.name());
    dashboard.setLabel(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard"));
    dashboard.setLink(PortalNavigator.getPortalStartUrl());
    dashboard.setType(HomepageType.DASHBOARD);
    return dashboard;
  }

  public static Homepage getHomepage() {
    List<Homepage> homepages = loadHomepages();
    Homepage homepage = new Homepage();
    homepage.setName(getHomepageName());
    return homepages.get(homepages.indexOf(homepage));
  }

  private static String getHomepageName() {
    String homepageName = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if (StringUtils.isBlank(homepageName)) {
      homepageName = findHomepageSetting();
    }
    return homepageName;
  }
  
  public static Homepage getHomepageForAdminSettings() {
    List<Homepage> homepages = loadHomepages();
    Homepage homepage = new Homepage();
    homepage.setName(findHomepageSetting());
    return homepages.get(homepages.indexOf(homepage));
  }

  private static String findHomepageSetting() {
    return new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DEFAULT_HOMEPAGE.toString());
  }
  
  public static Map<String, String> getHomepageOptionsForAdminSettings() {
    Map<String, String> result = new LinkedHashMap<>();
    for (Homepage homepage : HomepageUtils.loadHomepages()) {
      result.put(StringUtils.capitalize(homepage.getName().toLowerCase()), homepage.getLabel());
    }
    return result;
  }
}
