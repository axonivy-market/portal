package ch.addon.portal.generic.userprofile.homepage;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.addon.portal.generic.menu.PortalMenuNavigator;
import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import com.axonivy.portal.components.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {

  public static final String HOMEPAGE_ID_PATTERN = "%s_%s";
  private static final String USER_GUIDE_HOMEPAGE_NAME = "MAIN_DASHBOARD_default-user-guide-dashboard";

  /**
   * Visible and accessible homepage candidates in sidebar order. Empty when no candidate is available.
   */
  public static List<Homepage> loadHomepages() {
    List<Homepage> homepages = new ArrayList<>();
    for (SubMenuItem item : PortalMenuNavigator.callSubMenuItemsProcess()) {
      if (item.getMenuKind() != MenuKind.EXTERNAL_LINK) {
        homepages.add(HomepageMapper.toHomepage(item));
      }
    }
    return homepages;
  }

  private static String findRelativeUrlByKeywork(String keyword) {
    String friendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword(keyword);
    String newDashboardLink = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    return newDashboardLink;
  }

  public static Homepage findHomepageInMyProfile() {
    String homepageName = getHomepageId();
    Homepage homepage = StringUtils.isBlank(homepageName) ? null : findVisibleHomepage(loadHomepages(), homepageName);
    return homepage != null ? homepage : new Homepage();
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
    Homepage homepage = findVisibleHomepage(homepages, findSelectedHomepageName());
    if (homepage == null) {
      homepage = findGlobalOrFirst(homepages);
    }
    if (homepage != null) {
      adjustHomepageStartLink(homepage);
    }
    return homepage;
  }

  private static String findSelectedHomepageName() {
    Boolean isPortalInTeams =
        (Boolean) SecurityServiceUtils.getSessionAttribute(SessionAttribute.PORTAL_IN_TEAMS.toString());
    if (BooleanUtils.isTrue(isPortalInTeams)) {
      Object defaultPageInTeams =
          SecurityServiceUtils.getSessionAttribute(SessionAttribute.DEFAULT_PAGE_IN_TEAMS.toString());
      return defaultPageInTeams == null ? null : defaultPageInTeams.toString();
    }
    return getHomepageName();
  }

  private static Homepage findVisibleHomepage(List<Homepage> homepages, String homepageName) {
    if (StringUtils.isBlank(homepageName)) {
      return null;
    }
    Homepage probe = new Homepage();
    probe.setName(homepageName);
    int index = homepages.indexOf(probe);
    return index == -1 ? null : homepages.get(index);
  }

  private static Homepage findGlobalOrFirst(List<Homepage> homepages) {
    Homepage homepage = findVisibleHomepage(homepages, findHomepageSetting());
    if (homepage == null && !homepages.isEmpty()) {
      return homepages.getFirst();
    }
    return homepage;
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
    String showUserGuideProp = Ivy.session().getSessionUser().getProperty(UserProperty.PORTAL_SHOW_USER_GUIDE);
    boolean shouldShowUserGuide = StringUtils.isEmpty(showUserGuideProp);

    String homepageName = getHomepageId();
    if (StringUtils.isNotBlank(homepageName)) {
      return homepageName;
    }
    if (!shouldShowUserGuide) {
      return findHomepageSetting();
    }

    Ivy.session().getSessionUser().setProperty(UserProperty.PORTAL_SHOW_USER_GUIDE, "false");
    return USER_GUIDE_HOMEPAGE_NAME;
  }


  public static Homepage findDefaultHomepage() {
    return findGlobalOrFirst(loadHomepages());
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

  /**
   * No homepage candidate is available for an automatic route. Explicit dashboard requests keep their own routing.
   */
  public static boolean isNoHomepageAvailable(Homepage homepage, boolean isClickOnDashboard,
      boolean isOpenDashboardFromLink) {
    return homepage == null && !isClickOnDashboard && !isOpenDashboardFromLink;
  }

}
