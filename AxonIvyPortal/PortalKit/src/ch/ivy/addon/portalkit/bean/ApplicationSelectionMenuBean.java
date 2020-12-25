package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import ch.addon.portal.generic.menu.MenuView;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@ManagedBean
@ViewScoped
public class ApplicationSelectionMenuBean implements Serializable {

  private static final long serialVersionUID = -6664035964520390108L;

  public static final String MENU_COMMAND_METHOD = "#{applicationSelectionMenuBean.onClickMenuItem}";

  private boolean isClickOnBreadcrumb;
  private boolean isWorkingOnATask;
  private TaskState workingTaskState;
  private String destinationBreadcrumbPage;
  private MenuKind selectedMenuKind;
  private Map<String, List<String>> params;

  /**
   * This method handles the actionListener of Portal MenuItem
   * <ul>
   *    <li>Extract the Menu parameters</li>
   *    <li>Navigate to target page or show the warning dialog</li>
   * </ul><br/>
   * 
   * This will throw IOException if provided URL is incorrect.
   * See more on javax.faces.context.ExternalContext.redirect
   * @param event is {@code ActionEvent}
   * @throws IOException
   */
  public void onClickMenuItem(ActionEvent event) throws IOException {
    extractMenuParams(event);
    if (showWarningDialog()) {
      return;
    }
    navigateToTargetPage();
  }

  /**
   * <b>** INTERNAL METHOD</b> - Portal uses for internal purpose.
   * <p>
   * <b>** DO NOT USE THIS METHOD FOR CUSTOMER PROJECT</b>
   * </p>
   * @param isClickOnBreadcrumb for Breadcrumb behavior
   * @param destinationPage for Breadcrumb behavior
   * @throws IOException
   */
  public void navigateToTargetPage(boolean isClickOnBreadcrumb, String destinationPage) throws IOException {
    this.isClickOnBreadcrumb = isClickOnBreadcrumb;
    this.destinationBreadcrumbPage = destinationPage;
    navigateToTargetPage();
  }

  private void navigateToTargetPage() throws IOException {
    if (isClickOnBreadcrumb) {
      setClickOnBreadcrumb(false);
      if (BreadCrumbKind.TASK.name().equals(this.destinationBreadcrumbPage)) {
        PortalNavigator.navigateToPortalTask();
      }
      else if (BreadCrumbKind.HOME.name().equals(this.destinationBreadcrumbPage)) {
        PortalNavigator.navigateToPortalHome();
      }
      else {
        redirectToSelectedMenuUrl();
      }
      setDestinationBreadcrumbPage(null);
    }

    this.selectedMenuKind = MenuKind.getKind(getMenuParam(MenuView.MENU_KIND));
    if (this.selectedMenuKind != null) {
      navigateToPortalPage();
    }
  }

  private void navigateToPortalPage() throws IOException {
    switch (this.selectedMenuKind) {
      case DASHBOARD:
      case CUSTOM:
      case EXTERNAL_LINK:
        redirectToSelectedMenuUrl();
        break;
      case PROCESS:
        PortalNavigator.navigateToPortalProcess();
        break;
      case TASK:
        PortalNavigator.navigateToPortalTask();
        break;
      case CASE:
        PortalNavigator.navigateToPortalCase();
        break;
      case STATISTICS:
        PortalNavigator.navigateToPortalStatistic();
        break;
      default:
        break;
    }
  }

  private void redirectToSelectedMenuUrl() throws IOException {
    String menuUrl = getMenuParam(MenuView.MENU_URL);
    FacesContext.getCurrentInstance().getExternalContext().redirect(menuUrl);
  }

  private boolean showWarningDialog() {
    this.isWorkingOnATask = Boolean.valueOf(getMenuParam(MenuView.IS_WORKING_ON_TASK));
    getWorkingTaskState();
    if (isWorkingOnATask && workingTaskState != TaskState.DONE) {
      PrimeFacesUtils.executeScript("PF('task-losing-confirmation-dialog').show()");
      return true;
    }
    return false;
  }

  private void getWorkingTaskState() {
    ITask workingTask = TaskUtils.findTaskById(Long.valueOf(getMenuParam(MenuView.TASK_ID)));
    this.workingTaskState = Objects.isNull(workingTask) ? Ivy.wfTask().getState() : workingTask.getState();
  }

  private String getMenuParam(String key) {
    if (this.params != null) {
      List<String> values = this.params.get(key);
      return CollectionUtils.isEmpty(values) ? null : values.get(0);
    }
    return EMPTY;
  }

  private void extractMenuParams(ActionEvent event) {
    MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
    this.params = menuItem.getParams();
  }

  public boolean isWorkingOnATask() {
    return isWorkingOnATask;
  }

  public void setWorkingOnATask(boolean isWorkingOnATask) {
    this.isWorkingOnATask = isWorkingOnATask;
  }

  public String getDestinationBreadcrumbPage() {
    return destinationBreadcrumbPage;
  }

  public void setDestinationBreadcrumbPage(String destinationBreadcrumbPage) {
    this.destinationBreadcrumbPage = destinationBreadcrumbPage;
  }

  public boolean isClickOnBreadcrumb() {
    return isClickOnBreadcrumb;
  }

  public void setClickOnBreadcrumb(boolean isClickOnBreadcrumb) {
    this.isClickOnBreadcrumb = isClickOnBreadcrumb;
  }
}
