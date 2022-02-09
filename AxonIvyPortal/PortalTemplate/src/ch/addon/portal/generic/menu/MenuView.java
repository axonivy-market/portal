package ch.addon.portal.generic.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.PrimeFaces;
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
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@ManagedBean
@ViewScoped
public class MenuView implements Serializable {

  private static final long serialVersionUID = -3573569104295708900L;
  public final static String LOAD_SUB_MENU_PROCESS = "loadSubMenuItems()";
  public final static String SUB_MENU = "subMenuItems";
  public final static String THIRD_PARTY = "isThirdPartyApp";
  public final static String ACTIVE_MENU = "active-menuitem";
  public final static String APP_NAME = "appName";
  public final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";
  public final static String SELECTED_MENU_ID = "selectedMenuId";
  public final static String PREV_SELECTED_MENU_ID = "prevSelectedMenuId";
  public final static String IS_WORKING_ON_TASK = "isWorkingOnATask";
  public final static String IS_OPEN_NEW_TAB = "isOpenOnNewTab";
  public final static String CLICK_ON_MENU_ITEM_PATTERN = "fireEventClickOnMenuItem('%s', '%s')";

  private List<Button> menuItems;
  private List<SubMenuItem> subMenuItems;

  @PostConstruct
  public void init() {
    removeAppDataInSession();
    menuItems = new ArrayList<>();
    subMenuItems = new ArrayList<>();
    buildMenuView();
  }

  public void buildMenuView() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<Application> applications = service.findApplicationByUser(session().getSessionUserName());
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    buildMainMenuItems(applications);
    buildSubMenuItems();
  }

  public void buildMenuView(List<Application> applications) {
    buildMainMenuItems(applications);
  }

  private void buildMainMenuItems(List<Application> applications) {
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
            && portal.getSecurityContext().findUser(session().getSessionUserName()) != null) {
          menuItem.setHref(SecurityServiceUtils.getDefaultPortalStartUrl());
          if (PortalConstants.PORTAL_APPLICATION_NAME.equals(Ivy.request().getApplication().getName())) {
            menuItem.setStyleClass(ACTIVE_MENU);
          }
          menuItems.add(0, menuItem);
        }
      }
    }
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
  private void buildSubMenuItems() {
    subMenuItems = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess(LOAD_SUB_MENU_PROCESS, null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    try {
      subMenuItems = (List<SubMenuItem>) response.get(SUB_MENU);
    } catch (Exception e) {
      Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
    }
  }

  public void storeSelectedMenuItems() {
    var requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    var selectedMenuItemId = Optional.ofNullable(requestParamMap.get(SELECTED_MENU_ID)).orElse("");
    var isWorkingOnATask = Optional.ofNullable(requestParamMap.get(IS_WORKING_ON_TASK)).map(BooleanUtils::toBoolean).orElse(false);
    var isOpenOnNewTab =  Optional.ofNullable(requestParamMap.get(IS_OPEN_NEW_TAB)).map(BooleanUtils::toBoolean).orElse(false);
    session().setAttribute(SELECTED_MENU_ID, selectedMenuItemId);
    if (!isWorkingOnATask && !isOpenOnNewTab) {
      session().setAttribute(PREV_SELECTED_MENU_ID, selectedMenuItemId);
    }

    if (isOpenOnNewTab) {
      var prevSelectedMenuItemId = session().getAttribute(PREV_SELECTED_MENU_ID);
      if (prevSelectedMenuItemId == null) {
        prevSelectedMenuItemId = "";
      }
      PrimeFaces.current().executeScript(String.format(CLICK_ON_MENU_ITEM_PATTERN,
            prevSelectedMenuItemId, session().getAttribute(SELECTED_MENU_ID)));
    }
  }

  private IWorkflowSession session() {
    return Ivy.session();
  }

  public List<Button> getMenuItems() {
    return menuItems;
  }

  public List<SubMenuItem> getSubMenuItems() {
    return subMenuItems;
  }

}
