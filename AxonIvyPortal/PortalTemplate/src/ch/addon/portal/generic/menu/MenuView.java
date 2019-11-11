package ch.addon.portal.generic.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.button.Button;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

@ManagedBean
@ViewScoped
public class MenuView {

  public final static String LOAD_SUB_MENU_PROCESS = "loadSubMenuItems()";
  public final static String SUB_MENU = "subMenuItems";
  public final static String THIRD_PARTY = "isThirdPartyApp";
  public final static String ACTIVE_MENU = "active-menuitem";
  public final static String APP_NAME = "appName";
  public final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";

  private List<Button> menuItems;
  private List<SubMenuItem> subMenuItems;

  @PostConstruct
  public void init() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<Application> applications = service.findApplicationByUser(Ivy.session().getSessionUserName());

    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    buildMenuView(applications);
  }

  public void buildMenuView(List<Application> applications) {
    menuItems = new ArrayList<>();
    removeAppDataInSession();

    RegisteredApplicationService applicationService = new RegisteredApplicationService();
    int numberOfIvyApplications = (int) applicationService.countIvyApplications(applications);

    for (Application application : applications) {
      Button menuItem = new Button();
      menuItem.setValue(ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application));
      boolean isThirdPartyApp = application.getServerId() == null;
      if (isThirdPartyApp) {
        menuItem.getAttributes().put(THIRD_PARTY, true);
      }
      menuItem.setHref(application.getLink());
      menuItem.getAttributes().put(APP_NAME, application.getName());
      menuItem.setIcon("fa " + application.getMenuIcon());

      menuItems.add(menuItem);
      if (application.getName().equals(Ivy.request().getApplication().getName())
          || (!isThirdPartyApp && numberOfIvyApplications == 1)) {
        menuItem.setStyleClass("active-menuitem");
        updateAppDataToSession(application);
      }
    }

    if (numberOfIvyApplications > 1 || numberOfIvyApplications == 0) {
      Button menuItem = new Button();
      menuItem.setValue(Ivy.cms().co(DASHBOARD));
      menuItem.setIcon("fa fa-home");
      if (numberOfIvyApplications == 0) {
        menuItem.setHref(new PortalNavigator().getPortalStartUrlOfCurrentApplication());
        menuItem.setStyleClass(ACTIVE_MENU);
        menuItems.add(0, menuItem);
      } else {
        IApplication portal = ServerFactory.getServer().getApplicationConfigurationManager()
            .findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
        if (portal != null && portal.getActivityState() != ch.ivyteam.ivy.application.ActivityState.INACTIVE
            && portal.getSecurityContext().findUser(Ivy.session().getSessionUserName()) != null) {
          menuItem.setHref(SecurityServiceUtils.getDefaultPortalStartUrl());
          if (PortalConstants.PORTAL_APPLICATION_NAME.equals(Ivy.request().getApplication().getName())) {
            menuItem.setStyleClass(ACTIVE_MENU);
          }
          menuItems.add(0, menuItem);
        }
      }
    }

    buildSubMenuItems();
  }

  private void updateAppDataToSession(Application application) {
    SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP.toString(), application.getName());
    SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString(),
        application.getDisplayName());
  }

  private void removeAppDataInSession() {
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP.toString());
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
  }

  @SuppressWarnings("unchecked")
  public void buildSubMenuItems() {
    subMenuItems = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess(LOAD_SUB_MENU_PROCESS, null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    try {
      subMenuItems = (List<SubMenuItem>) response.get(SUB_MENU);
    } catch (Exception e) {
      Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
    }
  }

  public List<Button> getMenuItems() {
    return menuItems;
  }

  public List<SubMenuItem> getSubMenuItems() {
    return subMenuItems;
  }


}
