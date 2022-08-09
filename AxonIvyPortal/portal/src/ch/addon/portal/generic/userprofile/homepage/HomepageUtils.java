package ch.addon.portal.generic.userprofile.homepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {
  
  @SuppressWarnings("unchecked")
  public static List<Homepage> loadHomepages() {
    List<Homepage> homepages = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess("loadSubMenuItems()", null, Arrays.asList(PortalLibrary.PORTAL.getValue()));
    List<SubMenuItem> subMenuItems = (List<SubMenuItem>) response.get("subMenuItems");
    homepages.add(initDashboard());
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

  private static String findRelativeUrlByKeywork(String keyword) {
    String friendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword(keyword);
    String newDashboardLink = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    return newDashboardLink;
  }
  
  public static Homepage findHomepageInMyProfile() {
    Homepage homepage = new Homepage();
    String homepageName = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if (StringUtils.isBlank(homepageName)) {
      homepage.setName(StringUtils.EMPTY);
      return homepage;
    }
    
    List<Homepage> homepages = loadHomepages();
    homepage.setName(homepageName);
    return homepages.get(homepages.indexOf(homepage));
  }

  public static Homepage findHomepage() {
    List<Homepage> homepages = loadHomepages();
    Boolean isPortalInTeams = (Boolean) SecurityServiceUtils.getSessionAttribute(SessionAttribute.PORTAL_IN_TEAMS.toString());
    Homepage homepage = new Homepage();
    if (BooleanUtils.isTrue(isPortalInTeams)) {
      String homePageName = SecurityServiceUtils.getSessionAttribute(SessionAttribute.DEFAULT_PAGE_IN_TEAMS.toString()).toString();
      homepage.setName(homePageName);
    } else {
      homepage.setName(getHomepageName());
    }
    if (homepages.contains(homepage)) {
      Homepage seletedHomepage = homepages.get(homepages.indexOf(homepage));
      adjustHomepageStartLink(seletedHomepage);
      return seletedHomepage;
    } else {
      return homepage;
    }
  }

  private static void adjustHomepageStartLink(Homepage homepage) {
    String relativeUrl = "";
    switch (homepage.getType()) {
      case PROCESS:
        relativeUrl = findRelativeUrlByKeywork(PortalNavigator.PORTAL_PROCESS_START);
        break;
      case TASK:
        relativeUrl = findRelativeUrlByKeywork(PortalNavigator.PORTAL_TASK_START);
        break;
      case CASE:
        relativeUrl = findRelativeUrlByKeywork(PortalNavigator.PORTAL_CASE_START);
        break;
      case STATISTICS:
        relativeUrl = findRelativeUrlByKeywork(PortalNavigator.PORTAL_STATISTIC_START);
        break;
      default:
        break;
    }
    if (StringUtils.isNotEmpty(relativeUrl)) {
      homepage.setLink(relativeUrl);
    }
  }

  public static String getHomepageName() {
    String homepageName = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if (StringUtils.isBlank(homepageName)) {
      homepageName = findHomepageSetting();
    }
    return homepageName;
  }
  
  public static Homepage findDefaultHomepage() {
    List<Homepage> homepages = loadHomepages();
    Homepage homepage = new Homepage();
    homepage.setName(findHomepageSetting());
    return homepages.get(homepages.indexOf(homepage));
  }

  private static String findHomepageSetting() {
    return new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DEFAULT_HOMEPAGE);
  }
  
  public static Map<String, String> getHomepageOptionsForAdminSettings() {
    Map<String, String> result = new LinkedHashMap<>();
    for (Homepage homepage : HomepageUtils.loadHomepages()) {
      result.put(StringUtils.capitalize(homepage.getName().toLowerCase()), homepage.getLabel());
    }
    return result;
  }
  
  public static boolean isShowDashboard(Homepage homepage, boolean isClickOnDashboard) {
    return homepage == null || homepage.getType() == HomepageType.DASHBOARD || isClickOnDashboard;
  }

  public static boolean isShowLegacyUI() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_LEGACY_UI);
  }
}
