package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.comparator.UserProcessIndexComparator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable, Converter {

  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  private static final long serialVersionUID = -5889375917550618261L;
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private List<UserProcess> userProcesses;
  private List<UserProcess> defaultUserProcesses;
  private List<UserProcess> expressProcesses;
  private boolean compactMode;
  private boolean editMode;
  private String userName;
  private List<UserProcess> selectedUserProcesses;
  private List<RemoteWebStartable> webStartables;
  private String processWidgetComponentId;
  private IProcessStart createExpressWorkflowProcessStart;
  private boolean isUserFavoritesEnabled;

  @PostConstruct
  public void init() {
    processWidgetComponentId = Attrs.currentContext().getBuildInAttribute("clientId");
    userProcessService = new UserProcessService();
    String compactModeAttribute = Attrs.currentContext().get("compactMode");
    compactMode = compactModeAttribute == null || compactModeAttribute.isEmpty() ? true : Boolean.valueOf(compactModeAttribute);
    editMode = false;
    selectedUserProcesses = new ArrayList<>();
    userName = UserUtils.getSessionUserName();
    if (compactMode) {
      defaultUserProcesses = findDefaultProcessUserCanStart();
    }

    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    try {
      createExpressWorkflowProcessStart = collector.findCreateExpressWorlflowProcess();
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    
    String isUserFavoritesEnabledGlobalVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.ENABLE_USER_FAVORITES.toString());
    isUserFavoritesEnabled = StringUtils.isNotBlank(isUserFavoritesEnabledGlobalVariable) ? Boolean.parseBoolean(isUserFavoritesEnabledGlobalVariable) : true;
    
    userProcesses = findUserProcessBaseOnUIMode(compactMode);
    userProcesses.stream().filter(userProcess -> !AwesomeIcon.exists(userProcess.getIcon())).forEach(this::updateNotExistedIcons);
    expressProcesses = userProcesses.stream().filter(this::isExpressWorkflow).collect(Collectors.toList());
    userProcesses.removeAll(expressProcesses);
    
  }

  private List<UserProcess> findDefaultProcessUserCanStart() {
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    List<UserProcess> processes =
        ivyComponentLogicCaller.invokeComponentLogic(processWidgetComponentId, "#{logic.collectDefaultProcesses}",
            new Object[] {});
    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    return processes;
  }

  private List<UserProcess> findAllProcesses() {
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    return ivyComponentLogicCaller.invokeComponentLogic(processWidgetComponentId, "#{logic.collectAllProcesses}",
        new Object[] {});
  }

  public void preRenderProcessAutoComplete(List<RemoteWebStartable> remoteWebStartables) {
    if (this.webStartables == null) {
      this.webStartables = remoteWebStartables;
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

  private List<UserProcess> findUserProcessBaseOnUIMode(Boolean isCompactMode) {
    if (isCompactMode && !isUserFavoritesEnabled) {
      return new ArrayList<>();
    }
    
    List<UserProcess> processes = isCompactMode ? userProcessService.findByUserName(userName) : findAllProcesses();
    if (!isCompactMode) {
      sortUserProcessList(processes);
    } else {
      processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    }
    return processes;
  }

  private void setIndex(List<UserProcess> userProcesses) {
    int index = 0;
    for (UserProcess process : userProcesses) {
      process.setIndex(index);
      index++;
    }
  }

  public void addNewUserProcess(String clientId) {
    this.editingProcess = new UserProcess();
    RequestContext.getCurrentInstance().reset(clientId + ":add-new-process-dialog");
  }

  public void saveNewUserProcess() {
    correctProcessLink();
    editingProcess.setIndex(userProcesses.size());
    editingProcess = userProcessService.save(editingProcess);
    userProcesses.add(editingProcess);
  }

  private void updateNotExistedIcons(UserProcess userProcess) {
    userProcess.setIcon(AwesomeIcon.DEFAULT_ICON);
    userProcessService.save(userProcess);
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
                    && !isUserProcess(webStartable) && !isDefaultUserProcess(webStartable))
            .map(
                webStartable -> new UserProcess(stripHtmlTags(webStartable.getDisplayName()), userName, webStartable
                    .getStartLink())).collect(Collectors.toList());
    filteredUserProcesses.addAll(getFilteredExpressWorkflows(query));
    sortUserProcessList(filteredUserProcesses);
    return filteredUserProcesses;
  }

  public void sortUserProcessList(List<UserProcess> processes) {
    processes.sort((process1, process2) -> process1.getProcessName().toLowerCase()
        .compareTo(process2.getProcessName().toLowerCase()));
  }

  private List<UserProcess> getFilteredExpressWorkflows(String query) {
    List<UserProcess> workflow = new ArrayList<>();
    List<ExpressProcess> workflows =
        ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName().stream()
            .filter(wf -> !isUserProcess(wf) && !isDefaultUserProcess(wf)).collect(Collectors.toList());
    for (ExpressProcess wf : workflows) {
      if (PermissionUtils.canStartExpressWorkflow(wf) && StringUtils.containsIgnoreCase(wf.getProcessName(), query)) {
        workflow.add(new UserProcess(wf.getProcessName(), userName, generateWorkflowStartLink(wf)));
      }
    }
    return workflow;
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

  private boolean isDefaultUserProcess(RemoteWebStartable webStartable) {
    return defaultUserProcesses.stream().anyMatch(
        userProcess -> ((userProcess.getLink().toLowerCase()).contains(webStartable.getStartLink().toLowerCase())));
  }

  private boolean isUserProcess(ExpressProcess workflow) {
    return userProcesses.stream().anyMatch(
        userProcess -> ((userProcess.getLink().toLowerCase()).contains(generateWorkflowStartLink(workflow)
            .toLowerCase())));
  }

  private boolean isDefaultUserProcess(ExpressProcess workflow) {
    return defaultUserProcesses.stream().anyMatch(
        userProcess -> ((userProcess.getLink().toLowerCase()).contains(generateWorkflowStartLink(workflow)
            .toLowerCase())));
  }

  public String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }

  public String getProcessDescription(String userProcessName) {
    for (RemoteWebStartable webStartable : webStartables) {
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
  
  public List<UserProcess> getExpressProcesses() {
    return expressProcesses;
  }

  public boolean isCompactMode() {
    return compactMode;
  }

  public void switchEditMode() {
    editMode = !editMode;
    userProcesses.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    clearSelectedUserProcess();
  }

  public boolean isEditMode() {
    return editMode;
  }

  public void saveProcesses() {
    if (!selectedUserProcesses.isEmpty()) {
      userProcessService.deleteAll(selectedUserProcesses);
    }
    userProcesses.removeAll(selectedUserProcesses);
    setIndex(userProcesses);
    userProcessService.saveAll(userProcesses);
    editMode = false;
  }

  private void clearSelectedUserProcess() {
    selectedUserProcesses.clear();
  }

  public void selectDeletingProcess() {
    String processId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("processId");
    boolean isMarkedAsDeleting = isProcessMarkedAsDeleting(processId);
    if (!isMarkedAsDeleting) {
      Optional<UserProcess> selectedUserProcess =
          userProcesses.stream().filter(userProcess -> userProcess.getId().toString().equals(processId)).findFirst();
      if (selectedUserProcess.isPresent()) {
        selectedUserProcesses.add(selectedUserProcess.get());
      }
    } else {
      selectedUserProcesses.removeIf(userProcess -> userProcess.getId().toString().equals(processId));
    }
  }

  public boolean isProcessMarkedAsDeleting(String processId) {
    return selectedUserProcesses.stream().filter(userProcess -> userProcess.getId().toString().equals(processId))
        .findAny().isPresent();
  }

  public void cancelEditingProcess() {
    editMode = false;
    clearSelectedUserProcess();
  }

  public List<UserProcess> getSelectedUserProcesses() {
    return selectedUserProcesses;
  }

  public void setSelectedUserProcesses(List<UserProcess> selectedUserProcesses) {
    this.selectedUserProcesses = selectedUserProcesses;
  }

  public boolean isExpressWorkflow(UserProcess process) {
    return !StringUtils.isBlank(process.getWorkflowId());
  }

  public String getEditLinkOfExpressWorkflow(UserProcess process) {
    String editLink = Ivy.html().startref("Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp");
    return editLink + EXPRESS_WORKFLOW_ID_PARAM + process.getWorkflowId();
  }

  public void deleteExpressWorkflow() {
    String workflowId = editingProcess.getWorkflowId();
    ExpressServiceRegistry.getProcessService().delete(workflowId);
    ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(workflowId);
    ExpressServiceRegistry.getFormElementService().deleteByProcessId(workflowId);

    expressProcesses.remove(editingProcess);
  }

  public String getCreateExpessWorkflowLink() {
    return IvyExecutor.executeAsSystem(() -> {
      if (createExpressWorkflowProcessStart != null) {
        return RequestUriFactory.createProcessStartUri(
            ServerFactory.getServer().getApplicationConfigurationManager(), createExpressWorkflowProcessStart)
            .toString();
      }
      return StringUtils.EMPTY;
    });
  }
  
  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

  public void setCreateExpressWorkflowProcessStart(IProcessStart createExpressWorkflowProcessStart) {
    this.createExpressWorkflowProcessStart = createExpressWorkflowProcessStart;
  }

  public boolean canCreateExpessWorkflow() {
    return !compactMode && createExpressWorkflowProcessStart != null && PermissionUtils.hasPortalPermission(PortalPermission.EXPRESS_CREATE_WORKFLOW);
  }

  public boolean isExpressWorkflowLink(String link) {
    return !StringUtils.isBlank(link) && link.contains(EXPRESS_WORKFLOW_ID_PARAM);
  }

  public void resetEditingProcess() {
    if (editingProcess.isExternalLink()) {
      editingProcess.setUserName(userName);
    }
    editingProcess.setProcessName(StringUtils.EMPTY);
    editingProcess.setLink(StringUtils.EMPTY);
  }

  public void setDefaultUserProcesses(List<UserProcess> defaultUserProcesses) {
    this.defaultUserProcesses = defaultUserProcesses;
  }

  public List<UserProcess> getDefaultUserProcesses() {
    return defaultUserProcesses;
  }

  public void setUserProcesses(List<UserProcess> userProcesses) {
    this.userProcesses = userProcesses;
  }
  
  public void setExpressProcesses(List<UserProcess> expressProcesses) {
    this.expressProcesses = expressProcesses;
  }
  
  public boolean getIsUserFavoritesEnabled() {
    return isUserFavoritesEnabled;
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value.isEmpty()) {
      return null;
    }
    for (UserProcess item : userProcesses) {
      if (item.getId().toString().equals(value)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value == null) {
      return "";
    }
    UserProcess process = (UserProcess) value;
    return process.getId() == null ? "" : process.getId().toString();
  }

  public int getNumberOfProcesses() {
    if (CollectionUtils.isEmpty(webStartables)) {
      return 0;
    }
    return webStartables.size();
  }
}
