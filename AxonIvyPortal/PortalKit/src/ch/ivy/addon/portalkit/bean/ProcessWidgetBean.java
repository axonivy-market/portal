package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable {

  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";
  private static final String EXPRESS_WORKFLOW_ID_PARAM ="?workflowID=";
  private static final long serialVersionUID = -5889375917550618261L;
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private List<UserProcess> userProcesses;
  private boolean compactMode;
  private boolean deleteMode;
  private String userName;
  private List<UserProcess> selectedUserProcesses;
  private List<RemoteWebStartable> webStartables;
  private String processWidgetComnponentId;
  private IProcessStart createExpressWorkflowProcessStart;

  @PostConstruct
  public void init() {
    processWidgetComnponentId = Attrs.currentContext().getBuildInAttribute("id");
    userProcessService = new UserProcessService();
    String compactModeAttribute = Attrs.currentContext().get("compactMode");
    compactMode = compactModeAttribute.isEmpty() ? true : Boolean.valueOf(compactModeAttribute);
    deleteMode = false;
    selectedUserProcesses = new ArrayList<UserProcess>();
    userName = UserUtils.getSessionUserName();
    userProcesses = findUserProcessBaseOnUIMode(compactMode);

    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    try {
      createExpressWorkflowProcessStart = collector.findCreateExpressWorlflowProcess();
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  private List<UserProcess> findFavoriteProcessUserCanStart() {

    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();

    List<UserProcess> userProcesses =
        ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectDefaultProcesses}",
            new Object[] {});

    userProcesses.forEach(defaultProcess -> {
        defaultProcess.setUserName(userName);
        defaultProcess.setDefaultProcess(true);
    });

    userProcesses.addAll(userProcessService.findByUserName(userName));
    return userProcesses;
  }

  private List<UserProcess> findAllProcesses() {
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    return ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectAllProcesses}",
        new Object[] {});
  }

  public void preRenderProcessAutoComplete(List<RemoteWebStartable> webStartables) {
    if (this.webStartables == null) {
      this.webStartables = webStartables;
    }
  }

  public String getDisplayTextForSwitchModeButton() {
    return compactMode ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/processwidget/seeAllProcesses") : Ivy.cms().co(
        "/ch.ivy.addon.portalkit.ui.jsf/processwidget/backToOverview");
  }

  public void switchMode() {
    compactMode = !compactMode;
    userProcesses = findUserProcessBaseOnUIMode(compactMode);
  }

  private List<UserProcess> findUserProcessBaseOnUIMode(Boolean compactMode) {
    List<UserProcess> userProcesses = compactMode ? findFavoriteProcessUserCanStart() : findAllProcesses();
    sortUserProcessList(userProcesses);
    return userProcesses;
  }

  public void addNewUserProcess(String clientId) {
    this.editingProcess = new UserProcess();
    RequestContext.getCurrentInstance().reset(clientId + ":add-new-process-dialog");
  }

  public void saveNewUserProcess() {
    correctProcessLink();
    editingProcess = userProcessService.save(editingProcess);
    userProcesses.add(editingProcess);
    sortUserProcessList(userProcesses);
  }

  private void correctProcessLink() {
    String processLink = editingProcess.getLink().trim();
    if (!isValidProcessLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
      editingProcess.setLink(processLink);
    }
  }

  private boolean isValidProcessLink(String processLink) {
    String linkInLowerCase = processLink.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }

  public List<UserProcess> completeUserProcess(String query) {
    List<UserProcess> filteredUserProcesses =
        webStartables
            .stream()
            .filter(
                webStartable -> StringUtils.containsIgnoreCase(webStartable.getDisplayName(), query)
                    && !isUserProcess(webStartable))
            .map(
                webStartable -> new UserProcess(stripHtmlTags(webStartable.getDisplayName()), userName, webStartable
                    .getStartLink())).collect(Collectors.toList());
    filteredUserProcesses.addAll(getFilteredExpressWorkflows(query));
    sortUserProcessList(filteredUserProcesses);
    return filteredUserProcesses;
  }

  private void sortUserProcessList(List<UserProcess> userProcesses) {
    userProcesses.sort((process1, process2) -> process1.getProcessName().toLowerCase()
        .compareTo(process2.getProcessName().toLowerCase()));
  }

  private List<UserProcess> getFilteredExpressWorkflows(String query) {
    List<UserProcess> workflow = new ArrayList<>();
    List<ExpressProcess> workflows =
        ExpressServiceRegistry.getProcessService().findAllOrderByName().stream().filter(wf -> !isUserProcess(wf))
            .collect(Collectors.toList());
    for (ExpressProcess wf : workflows) {
      if (canStartWorkflow(wf) && StringUtils.containsIgnoreCase(wf.getProcessName(), query)) {
        workflow.add(new UserProcess(wf.getProcessName(), userName, generateWorkflowStartLink(wf)));
      }
    }
    return workflow;
  }

  private boolean canStartWorkflow(ExpressProcess workflow) {
    boolean isWorkflowAssignee = false;
    ISecurityMember permittedRole = Ivy.request().getApplication().getSecurityContext().findSecurityMember(workflow.getProcessPermission());
    if(!Objects.isNull(permittedRole)) {
      isWorkflowAssignee = permittedRole.isUser() ? Ivy.session().canActAsUser((IUser) permittedRole) : Ivy.session().hasRole((IRole) permittedRole, false); 
    }
    IUser owner = Ivy.request().getApplication().getSecurityContext().findUser(workflow.getProcessOwner().substring(1));
    return isWorkflowAssignee
        || Ivy.session().hasRole(Ivy.request().getApplication().getSecurityContext().findRole(ADMIN_ROLE), false)
        || Ivy.session().canActAsUser(owner);
  }

  private String generateWorkflowStartLink(ExpressProcess wf) {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.request().getApplication());
    try {
      return processStartCollector.findExpressWorkflowStartLink() + EXPRESS_WORKFLOW_ID_PARAM + wf.getId();
    } catch (Exception e) {
      Ivy.log().error(e);
      return "";
    }
  }

  private boolean isUserProcess(RemoteWebStartable webStartable) {
    return userProcesses.stream().anyMatch(
        userProcess -> ((userProcess.getLink().toLowerCase()).contains(webStartable.getStartLink().toLowerCase())));
  }

  private boolean isUserProcess(ExpressProcess workflow) {
    return userProcesses.stream().anyMatch(
        userProcess -> ((userProcess.getLink().toLowerCase()).contains(generateWorkflowStartLink(workflow)
            .toLowerCase())));
  }

  public String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }

  public String getProcessDescription(String userProcessName) {
    for (IWebStartable webStartable : webStartables) {
      if (webStartable.getDisplayName().equals(userProcessName)) {
        return webStartable.getDescription();
      }
    }
    return StringUtils.EMPTY;
  }

  public UserProcess getEditingProcess() {
    return editingProcess;
  }

  public void setEditingProcess(UserProcess editingProcess) {
    this.editingProcess = editingProcess;
  }

  public List<UserProcess> getUserProcesses() {
    return userProcesses;
  }

  public boolean isCompactMode() {
    return compactMode;
  }

  public void switchDeleteMode() {
    deleteMode = !deleteMode;
    clearSelectedUserProcess();
  }

  public boolean isDeleteMode() {
    return deleteMode;
  }

  public void deleteSelectedProcess() {
    List<UserProcess> defaultUserProcesses =
        selectedUserProcesses.stream().filter(userProcess -> userProcess.isDefaultProcess())
            .collect(Collectors.toList());
    if (!defaultUserProcesses.isEmpty()) {
      userProcessService.saveAll(defaultUserProcesses);
    }

    @SuppressWarnings("unchecked")
    List<UserProcess> nonDefaultUserProcesses =
        (List<UserProcess>) CollectionUtils.subtract(selectedUserProcesses, defaultUserProcesses);
    if (!nonDefaultUserProcesses.isEmpty()) {
      userProcessService.deleteAll(nonDefaultUserProcesses);
    }

    userProcesses.removeAll(selectedUserProcesses);
    deleteMode = false;
  }

  private void clearSelectedUserProcess() {
    selectedUserProcesses.clear();
  }

  public void selectDeletingProcess(AjaxBehaviorEvent event) {
    SelectBooleanCheckbox booleanCheckbox = (SelectBooleanCheckbox) event.getComponent();
    UserProcess selectedUserProcess = (UserProcess) booleanCheckbox.getAttributes().get("selectedProcess");
    if (booleanCheckbox.isSelected()) {
      boolean processExisted =
          selectedUserProcesses.stream()
              .filter(userProcess -> userProcess.getLink().equals(selectedUserProcess.getLink())).findAny().isPresent();
      if (!processExisted) {
        selectedUserProcesses.add(selectedUserProcess);
      }
    } else {
      selectedUserProcesses.removeIf(userProcess -> userProcess.getLink().equals(selectedUserProcess.getLink()));
    }
  }

  public void cancelDeletingProcess() {
    deleteMode = false;
    clearSelectedUserProcess();
  }

  public List<UserProcess> getSelectedUserProcesses() {
    return selectedUserProcesses;
  }

  public void setSelectedUserProcesses(List<UserProcess> selectedUserProcesses) {
    this.selectedUserProcesses = selectedUserProcesses;
  }

  public boolean isExpressWorkflow (UserProcess process) {
    return !StringUtils.isBlank(process.getWorkflowId());
  }

  public String getEditLinkOfExpressWorkflow (UserProcess process) {
    String editLink = Ivy.html().startref("Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp");
    return editLink + EXPRESS_WORKFLOW_ID_PARAM + process.getWorkflowId();
  }

  public void deleteExpressWorkflow () {
    String workflowId = editingProcess.getWorkflowId();
    ExpressServiceRegistry.getProcessService().delete(workflowId);
    ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(workflowId);
    ExpressServiceRegistry.getFormElementService().deleteByProcessId(workflowId);

    userProcesses.remove(editingProcess);
  }

  public String getCreateExpessWorkflowLink() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        if (createExpressWorkflowProcessStart != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), createExpressWorkflowProcessStart).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }

  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

  public void setCreateExpressWorkflowProcessStart(
      IProcessStart createExpressWorkflowProcessStart) {
    this.createExpressWorkflowProcessStart = createExpressWorkflowProcessStart;
  }

  public boolean canCreateExpessWorkflow() {
    boolean result = !compactMode && createExpressWorkflowProcessStart != null;
    return result;
  }

  public boolean isExpressWorkflowLink (String link) {
    return !StringUtils.isBlank(link) && link.contains(EXPRESS_WORKFLOW_ID_PARAM);
  }
}
