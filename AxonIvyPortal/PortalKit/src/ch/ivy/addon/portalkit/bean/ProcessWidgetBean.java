package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
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

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable, Converter {

  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  private static final long serialVersionUID = -5889375917550618261L;
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private List<UserProcess> userProcesses;
  private List<UserProcess> defaultUserProcesses;
  private boolean compactMode;
  private boolean editMode;
  private String userName;
  private List<UserProcess> selectedUserProcesses;
  private List<RemoteWebStartable> webStartables;
  private String processWidgetComnponentId;
  private IProcessStart createExpressWorkflowProcessStart;
  private Map<String, List<UserProcess>> userProcessByAlphabet;
  private static final String SPECIAL_CHARACTER_KEY = "SPECIAL_CHARACTER";

  @PostConstruct
  public void init() {
    processWidgetComnponentId = Attrs.currentContext().getBuildInAttribute("id");
    userProcessService = new UserProcessService();
    String compactModeAttribute = Attrs.currentContext().get("compactMode");
    compactMode = compactModeAttribute.isEmpty() ? true : Boolean.valueOf(compactModeAttribute);
    editMode = false;
    selectedUserProcesses = new ArrayList<UserProcess>();
    userName = UserUtils.getSessionUserName();
    if (compactMode) {
      defaultUserProcesses = findDefaultProcessUserCanStart();
    }
    userProcesses = findUserProcessBaseOnUIMode(compactMode);
    if(!compactMode) {
        userProcessByAlphabet = groupUserProcessByAlphabetIndex(userProcesses);
    }
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    try {
      createExpressWorkflowProcessStart = collector.findCreateExpressWorlflowProcess();
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }
  
  private Map<String, List<UserProcess>> groupUserProcessByAlphabetIndex(List<UserProcess> userProcesses) {
    Map<String, List<UserProcess>> userProcessGroupByAlphabet = new HashMap<>();
    //Follow Oracle document about regex for punctual character
    //https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    String punctualRegex = "\\p{Punct}";
    
    for(UserProcess userProcess : userProcesses) {
      String processNameUpperCase = StringUtils.trim(userProcess.getProcessName()).toUpperCase();
      if(StringUtils.isNotEmpty(processNameUpperCase)) {
        String firstLetter = processNameUpperCase.substring(0,1);
        if(firstLetter.matches(punctualRegex)) {
          addOrUpdateUserProcessGroupByKey(userProcessGroupByAlphabet, userProcess, SPECIAL_CHARACTER_KEY);
        }
        else {
          addOrUpdateUserProcessGroupByKey(userProcessGroupByAlphabet, userProcess, firstLetter);
        }
      }
    }
    List<UserProcess> userProcessOfSpecialCharacterGroup = userProcessGroupByAlphabet.remove(SPECIAL_CHARACTER_KEY);
    
    Collator collator = Collator.getInstance(Locale.GERMAN);
    userProcessGroupByAlphabet = userProcessGroupByAlphabet.entrySet().stream()
                                .sorted(Map.Entry.comparingByKey(collator::compare))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e2, LinkedHashMap::new));
    if(!CollectionUtils.isEmpty(userProcessOfSpecialCharacterGroup)) {
      userProcessGroupByAlphabet.put(SPECIAL_CHARACTER_KEY, userProcessOfSpecialCharacterGroup);
    }
    return userProcessGroupByAlphabet;
  }

  private void addOrUpdateUserProcessGroupByKey(Map<String, List<UserProcess>> userProcessGroupByAlphabet,
      UserProcess userProcess, String key) {
    if(!userProcessGroupByAlphabet.containsKey(key)) {
      List<UserProcess> userProcessByMapKey = new ArrayList<>();
      
      userProcessByMapKey.add(userProcess);
      userProcessGroupByAlphabet.put(key, userProcessByMapKey);
    }
    else {
      userProcessGroupByAlphabet.get(key).add(userProcess);
    }
  }
  private List<UserProcess> findDefaultProcessUserCanStart() {
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    List<UserProcess> processes =
        ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectDefaultProcesses}",
            new Object[] {});
    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    return processes;
  }

  private List<UserProcess> findAllProcesses() {
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    return ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectAllProcesses}",
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
        ExpressServiceRegistry.getProcessService().findAllOrderByName().stream()
            .filter(wf -> !isUserProcess(wf) && !isDefaultUserProcess(wf)).collect(Collectors.toList());
    for (ExpressProcess wf : workflows) {
      if (canStartWorkflow(wf) && StringUtils.containsIgnoreCase(wf.getProcessName(), query)) {
        workflow.add(new UserProcess(wf.getProcessName(), userName, generateWorkflowStartLink(wf)));
      }
    }
    return workflow;
  }

  private boolean canStartWorkflow(ExpressProcess workflow) {
    boolean isWorkflowAssignee = false;
    ISecurityMember permittedRole =
        Ivy.request().getApplication().getSecurityContext().findSecurityMember(workflow.getProcessPermission());
    if (!Objects.isNull(permittedRole)) {
      isWorkflowAssignee =
          permittedRole.isUser() ? Ivy.session().canActAsUser((IUser) permittedRole) : Ivy.session().hasRole(
              (IRole) permittedRole, false);
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

    userProcesses.remove(editingProcess);
  }

  public String getCreateExpessWorkflowLink() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        if (createExpressWorkflowProcessStart != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), createExpressWorkflowProcessStart)
              .toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }

  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

  public void setCreateExpressWorkflowProcessStart(IProcessStart createExpressWorkflowProcessStart) {
    this.createExpressWorkflowProcessStart = createExpressWorkflowProcessStart;
  }

  public boolean canCreateExpessWorkflow() {
    boolean result = !compactMode && createExpressWorkflowProcessStart != null;
    return result;
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
  
  public Map<String, List<UserProcess>> getUserProcessByAlphabet() {
    return userProcessByAlphabet;
  }

  public void setUserProcessByAlphabet(Map<String, List<UserProcess>> userProcessByAlphabet) {
    this.userProcessByAlphabet = userProcessByAlphabet;
  }

}
