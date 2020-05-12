package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.menuitem.UIMenuItem;

import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;


@ManagedBean
@ViewScoped
public class ApplicationSelectionMenuBean {

  private SubMenuItem selectedSubMenuItem;
  private String applicationUrl;
  private BreadCrumbKind breadcrumbKind;
  private PortalNavigator navigator;

  @PostConstruct
  public void init() {
    navigator = new PortalNavigator();
  }

  public void openApp(String applicationUrl, ITask workingTask) {
    this.applicationUrl = applicationUrl;
    this.selectedSubMenuItem = null;

    if (isDisplayWarningDialog(workingTask)) {
      showWarningDialog();
    } else {
      navigate();
    }
  }

  public void reserveTask(ITask workingTask) {
    TaskUtils.parkTask(workingTask != null ? workingTask : Ivy.wfTask());
    displayMessageAfterLeaveOrFinishTask();
    navigate();
  }

  public void leaveTask(ITask workingTask) {
    TaskUtils.resetTask(workingTask != null ? workingTask : Ivy.wfTask());
    displayMessageAfterLeaveOrFinishTask();
    navigate();
  }

  public void onClickSubMenuItem(ActionEvent event) {
    event.getSource();
    if (event.getSource() instanceof UIMenuItem) {
      UIMenuItem item = (UIMenuItem) event.getSource();
      selectedSubMenuItem = (SubMenuItem) item.getAttributes().get("selectedSubMenuItem");

      ITask workingTask = (ITask) item.getAttributes().get("task");
      if (isDisplayWarningDialog(workingTask)) {
        showWarningDialog();
      } else {
        navigate();
      }
    }
  }

  public void navigateFromBreadcrumb(Boolean isWorkingOnATask, BreadCrumbKind breadcrumbKind) {
    this.breadcrumbKind = breadcrumbKind;
    if (isWorkingOnATask) {
      showWarningDialogWhenNavigateFromBreadcrumb();
    } else {
      navigateBreadcrumb();
    }
  }

  public void continueWorkingOnTask() {
    this.selectedSubMenuItem = null;
    this.breadcrumbKind = null;
  }

  private void showWarningDialogWhenNavigateFromBreadcrumb() {
    PrimeFaces.current().executeScript("PF('task-losing-confirmation-dialog').show()");
  }

  public void showWarningDialog() {
    PrimeFaces.current().executeScript("removeHighlightedMenuItem(); PF('task-losing-confirmation-dialog').show()");
  }

  private static void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  private boolean isDisplayWarningDialog(ITask workingTask) {
    Boolean isWorkingOnATask = Attrs.currentContext().getAttribute("#{data.isWorkingOnATask}", Boolean.class);
    return isWorkingOnATask && workingTask.getState() != ch.ivyteam.ivy.workflow.TaskState.DONE;
  }

  private static void displayMessageAfterLeaveOrFinishTask() {
    String displayMessageAfterFinishOrLeaveTaskVariable =
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
    boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable)
        ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable)
        : true;
    if (displayMessageAfterFinishOrLeaveTask && !Ivy.session().isSessionUserUnknown()) {
      Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
      if (!flash.containsKey("overridePortalGrowl")) {
        Boolean isTaskFinished = Attrs.currentContext().getAttribute("#{data.isTaskFinished}", Boolean.class);
        FacesMessage message = new FacesMessage(
            isTaskFinished ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully")
                : Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
        FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
      }
      flash.setRedirect(true);
      flash.setKeepMessages(true);
    }
  }

  private void navigate() {
    navigator = new PortalNavigator();

    // Open application
    if (selectedSubMenuItem == null && breadcrumbKind == null) {
      redirect(applicationUrl);
    }

    if (breadcrumbKind != null) {
      navigateBreadcrumb();
      return;
    }

    if (selectedSubMenuItem != null) {
      navigateSubMenuItem();
    }
  }

  private void navigateBreadcrumb() {
    switch (breadcrumbKind) {
      case TASK:
        breadcrumbKind = null;
        navigator.navigateToPortalTask();
        break;
      case HOME:
        breadcrumbKind = null;
        navigator.navigateToPortalHome();
        break;
      default:
        break;
    }
  }

  private void navigateSubMenuItem() {
    switch (selectedSubMenuItem.getMenuKind()) {
      case PROCESS:
        navigator.navigateToPortalProcess();
        break;
      case TASK:
        navigator.navigateToPortalTask();
        break;
      case CASE:
        navigator.navigateToPortalCase();
        break;
      case DASHBOARD:
        navigator.navigateToPortalStatistic();
        break;
      case CUSTOM:
        redirect(selectedSubMenuItem.getExternalLink());
        break;
      default:
        navigator.navigateToPortalHome();
        break;
    }
  }

}
