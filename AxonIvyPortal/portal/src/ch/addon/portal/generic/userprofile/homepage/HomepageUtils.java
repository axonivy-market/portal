package ch.addon.portal.generic.userprofile.homepage;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.addon.portal.generic.menu.PortalMenuNavigator;
import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {

  public static final String HOMEPAGE_ID_PATTERN = "%s_%s";

  public static List<Homepage> loadHomepages() {
    List<Homepage> homepages = new ArrayList<>();
    List<SubMenuItem> subMenuItems = PortalMenuNavigator.callSubMenuItemsProcess();
    homepages.add(defaultHompage());
    for (SubMenuItem item : subMenuItems) {
      if (item.getMenuKind() != MenuKind.EXTERNAL_LINK) {
        homepages.add(HomepageMapper.toHomepage(item));
      }
    }
    return homepages;
  }

  private static Homepage defaultHompage() {
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
    String homepageName = getHomepageId();
    if (StringUtils.isBlank(homepageName)) {
      homepage.setName(StringUtils.EMPTY);
      return homepage;
    }

    List<Homepage> homepages = loadHomepages();
    homepage.setName(homepageName);
    int index = homepages.indexOf(homepage);
    if (index == -1) {
      index = 0;
    }
    return homepages.get(index);
  }

  public static String generateHomepageId(MenuKind menuKind, String dashboardId) {
    return String.format(HOMEPAGE_ID_PATTERN, menuKind.name(), dashboardId);
  }

  public static String generateDefaultCaseListDashboardHomepageId() {
    return generateHomepageId(MenuKind.MAIN_DASHBOARD, DEFAULT_CASE_LIST_DASHBOARD);
  }

  public static String generateDefaultTaskListDashboardHomepageId() {
    return generateHomepageId(MenuKind.MAIN_DASHBOARD, DEFAULT_TASK_LIST_DASHBOARD);
  }

  private static String getHomepageId() {
    String originHomepage = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if ("TASK".equals(originHomepage)) { // for backward compatible
      return generateDefaultTaskListDashboardHomepageId();
    } else if ("CASE".equals(originHomepage)) { // for backward compatible
      return generateDefaultCaseListDashboardHomepageId();
    }
    return originHomepage;
  }

  public static Homepage findHomepage() {
    List<Homepage> homepages = loadHomepages();
    Boolean isPortalInTeams =
        (Boolean) SecurityServiceUtils.getSessionAttribute(SessionAttribute.PORTAL_IN_TEAMS.toString());
    Homepage homepage = new Homepage();
    if (BooleanUtils.isTrue(isPortalInTeams)) {
      String homePageName =
          SecurityServiceUtils.getSessionAttribute(SessionAttribute.DEFAULT_PAGE_IN_TEAMS.toString()).toString();
      homepage.setName(homePageName);
    } else {
      homepage.setName(getHomepageName());
    }
    if (homepages.contains(homepage)) {
      Homepage seletedHomepage = homepages.get(homepages.indexOf(homepage));
      adjustHomepageStartLink(seletedHomepage);
      return seletedHomepage;
    } else {
      return defaultHompage();
    }
  }

  private static void adjustHomepageStartLink(Homepage homepage) {
    String relativeUrl = switch (homepage.getType()) {
      case PROCESS -> findRelativeUrlByKeywork(PortalNavigator.PORTAL_PROCESS_START);
      default -> "";
    };
    if (StringUtils.isNotEmpty(relativeUrl)) {
      homepage.setLink(relativeUrl);
    }
  }

  public static String getHomepageName() {
    String homepageName = getHomepageId();
    if (StringUtils.isBlank(homepageName)) {
      homepageName = findHomepageSetting();
    }
    return homepageName;
  }

  public static Homepage findDefaultHomepage() {
    List<Homepage> homepages = loadHomepages();
    Homepage homepage = new Homepage();
    homepage.setName(findHomepageSetting());
    int index = homepages.indexOf(homepage);
    if (index == -1) {
      index = 0;
    }
    return homepages.get(index);
  }

  private static String findHomepageSetting() {
    return GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.DEFAULT_HOMEPAGE);
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

}
