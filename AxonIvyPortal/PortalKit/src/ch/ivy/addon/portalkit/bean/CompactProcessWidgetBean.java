package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.GuidePool;
import ch.ivy.addon.portalkit.bo.IvyProcess;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.comparator.UserProcessIndexComparator;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.DummyProcessService;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@Deprecated(since = "9.3")
@ManagedBean
@ViewScoped
public class CompactProcessWidgetBean implements Serializable, Converter {

private static final long serialVersionUID = -5889375917550618261L;
  
  private List<UserProcess> userProcesses;
  private List<UserProcess> defaultProcesses;
  private List<UserProcess> selectedUserProcesses;
  private List<UserProcess> processesToAdd;
  private Map<String, Process> ivyProcesses;
  private Map<String, Process> expressProcesses;
  private Map<String, Process> externalLinks;
  
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private UserProcess selectedProcess;
  
  private boolean editMode;
  private boolean isUserFavoritesEnabled;
  private boolean isDisplayShowAllProcessesLink;
  private boolean isGuide;
  private Locale currentLocale;
  
  @PostConstruct
  public void init() {
    currentLocale = getApplicationDefaultLanguage();
    // used in global search page
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
  }
  
  public void preRender() {
    isGuide = GuidePool.instance().guide(Ivy.session().getSessionUserName()).isGuideShown();
    userProcessService = UserProcessService.getInstance();
    selectedUserProcesses = new ArrayList<>();
    
    if (isGuide) {
      createDummyDataForGuide();
    }
  }
  
  public void initProcesses() {
    String isUserFavoritesEnabledGlobalVariable =
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.ENABLE_USER_FAVORITES);
    isUserFavoritesEnabled = StringUtils.isNotBlank(isUserFavoritesEnabledGlobalVariable) ? Boolean.parseBoolean(isUserFavoritesEnabledGlobalVariable) : true;
    ivyProcesses = findProcesses();
    expressProcesses = findExpressProcesses();
    externalLinks = findExternalLink();
    userProcesses = findUserProcesses();
    defaultProcesses = findStartableDefaultProcesses();
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
  }
  
  private void createDummyDataForGuide() {
    isUserFavoritesEnabled = DummyProcessService.enableUserFavorites();
    isDisplayShowAllProcessesLink = DummyProcessService.displayShowAllProcessesLink();
    userProcesses = DummyProcessService.dummyFavorites();
    defaultProcesses = DummyProcessService.dummyApplicationProcesses();
  }

  private List<UserProcess> findStartableDefaultProcesses() {
    List<UserProcess> processes = userProcessService.getPublicConfig();

    updateLinkForDefaultProcesses(processes);

    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    return processes;
  }

  private List<UserProcess> findUserProcesses() {
    if (!isUserFavoritesEnabled) {
      return new ArrayList<>();
    }
    
    if (userProcessService == null) {
      userProcessService = UserProcessService.getInstance();
    }
    List<UserProcess> processes = userProcessService.getPrivateConfig();

    /*
     * 1. Update link because since 9.2, saved user favorite processes didn't store this value.
     * 2. Check if link is broken.
     */
    updateLinkForUserProcesses(processes);

    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    removeDeletedExpressWorkflowFromUserProcesses(processes);
    removeDeletedExternalLinkFromUserProcesses(processes);
    return processes;
  }

  private void updateLinkForUserProcesses(List<UserProcess> processes) {
    Process process;
    for (UserProcess userProcess : processes) {
      process = findProcess(userProcess);
      userProcess.setLink(process == null ? "" : process.getStartLink());
      userProcess.setBrokenLink(process == null);
    }
  }

  private void updateLinkForDefaultProcesses(List<UserProcess> processes) {
    Process process;
    for (UserProcess userProcess : processes) {
      process = findProcess(userProcess);
      if (process != null) {
        userProcess.setLink(process.getStartLink());
      } else {
        userProcess.setBrokenLink(StringUtils.isBlank(userProcess.getLink()));
      }
    }
  }

  private Process findProcess(UserProcess userProcess) {
    Process process = null;
    String processId = userProcess.getProcessId();
    if (StringUtils.isNotBlank(processId)) {
      switch (userProcess.getProcessType()) {
        case IVY_PROCESS:
          process = ivyProcesses.get(processId);
          break;
        case EXPRESS_PROCESS:
          process = expressProcesses.get(processId);
          break;
        case EXTERNAL_LINK:
          process = externalLinks.get(processId);
          break;
      }
    }
    return process;
  }

  private Map<String, Process> findProcesses() {
    IvyProcessResultDTO dto = ProcessService.newInstance().findProcesses();
    List<IWebStartable> processes = dto.getProcesses();
    Map<String, Process> defaultPortalProcesses = new HashedMap<>();
    processes.forEach(process -> defaultPortalProcesses.put(process.getId(), new IvyProcess(process)));
    return defaultPortalProcesses;
  }

  private Map<String, Process> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    ProcessStartCollector processStartCollector = new ProcessStartCollector();
    String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
    if (StringUtils.isNotBlank(expressStartLink)) {
      List<ExpressProcess> workflows = ExpressProcessService.getInstance().findReadyToExecuteProcessOrderByName();
      for (ExpressProcess wf : workflows) {
        if (PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(wf)) {
          processes.add(wf);
        }
      }
    }
    Map<String, Process> defaultPortalProcesses = new HashedMap<>();
    processes.forEach(process -> defaultPortalProcesses.put(process.getId(), new PortalExpressProcess(process)));
    return defaultPortalProcesses;
  }

  private Map<String, Process> findExternalLink() {
    List<ExternalLink> externalLinks = ExternalLinkService.getInstance().findAll();
    Map<String, Process> defaultPortalProcesses = new HashedMap<>();
    externalLinks.forEach(externalLink -> defaultPortalProcesses.put(externalLink.getId().toString(), new ExternalLinkProcessItem(externalLink)));
    return defaultPortalProcesses;
  }

  private void setIndex(List<UserProcess> userProcesses) {
    int index = 0;
    for (UserProcess process : userProcesses) {
      process.setIndex(index);
      index++;
    }
  }

  public void onSelectUserProcess() {
    this.editingProcess = new UserProcess(this.selectedProcess);
  }

  public void clearProcessDisplayNames() {
    this.editingProcess.setNames(new ArrayList<>());
  }
  
  public void addNewUserProcess() {
    this.selectedProcess = new UserProcess();
    this.editingProcess = new UserProcess();
    initDataForProcessAutoComplete();
  }

  public List<DisplayName> generateProcessDisplayNames() {
    if (CollectionUtils.isNotEmpty(this.editingProcess.getNames())) {
      return this.editingProcess.getNames();
    }

    List<String> languages = getSupportedLanguage();
    List<DisplayName> displayNames = new ArrayList<>();
    for (String language : languages) {
      DisplayName displayName = new DisplayName();
      displayName.setLocale(Locale.forLanguageTag(language));
      displayName.setValue(this.editingProcess.getProcessName());
      displayNames.add(displayName);
    }
    return displayNames;
  }

  private List<String> getSupportedLanguage() {
    List<String> languages = new ArrayList<>();
    IvyLanguageResultDTO ivyLanguageResult = LanguageService.newInstance().findUserLanguages();
    if (ivyLanguageResult.getIvyLanguage() != null) {
      languages = ivyLanguageResult.getIvyLanguage().getSupportedLanguages();
    }
    return languages;
  }

  public boolean isRequiredLanguage(Locale locale) {
    return this.currentLocale.equals(locale);
  }
  
  private void initDataForProcessAutoComplete() {
    if (CollectionUtils.isEmpty(this.processesToAdd)) {
      this.processesToAdd = collectProcesses();
      sortUserProcessList(processesToAdd);
    }
  }
  
  private List<UserProcess> collectProcesses() {
    String processWidgetComponentId = Attrs.currentContext().getBuildInAttribute("clientId");
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    List<UserProcess> processes = ivyComponentLogicCaller.invokeComponentLogic(processWidgetComponentId,
        "#{logic.collectProcesses}", new Object[] {});
    return processes;
  }


  public void saveNewUserProcess() {
    editingProcess.setIndex(userProcesses.size());

    // Since 9.2, we will store processId and processType instead of start link and use them to find it's latest link.
    /*
     * Because editingProcess will be updated after saving to database, so that we need to set link value for updated editingProcess
     */
    String link = editingProcess.getLink();
    editingProcess.setLink("");

    editingProcess = userProcessService.save(editingProcess);

    // Reset link value before adding to user processes cache
    editingProcess.setLink(link);

    userProcesses.add(editingProcess);
  }

  public List<UserProcess> completeUserProcess(String query) {
    return processesToAdd.stream()
        .filter(processToAdd -> StringUtils.containsIgnoreCase(processToAdd.getProcessName(), query)
            && !isUserProcess(processToAdd) && !isExternalLinkUserProcess(processToAdd) 
            && !isDefaultUserProcess(processToAdd))
        .collect(Collectors.toList());
  }

  public void sortUserProcessList(List<UserProcess> processes) {
    processes.sort((process1, process2) -> StringUtils.compareIgnoreCase(process1.getProcessName(), process2.getProcessName().toLowerCase()));
  }
  
  public void startProcess(UserProcess userProcess) throws IOException {
    Objects.requireNonNull(userProcess, "User process must not be null");
    String link = userProcess.getLink();
    if (isExternalLink(userProcess)) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
      return;
    }
    
    if (isExpressProcess(userProcess) && StringUtils.isNotBlank(userProcess.getProcessId())) {
      ProcessStartCollector processStartCollector = new ProcessStartCollector();
      String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
      if (StringUtils.isNotBlank(expressStartLink)) {
        FacesContext.getCurrentInstance().getExternalContext().redirect(expressStartLink + "?workflowID=" + userProcess.getProcessId());
        return;
      }
    }
    
    link += link.contains("?") ? "&" : "?";
    // Put the "embedInIFrame" param to the process link to open it in the DefaultFramePage process
    // Then this process will open task in IFrame or not based on its "embedInIFrame" String custom field
    FacesContext.getCurrentInstance().getExternalContext().redirect(link + "embedInFrame");
  }
  
  private boolean isUserProcess(UserProcess processToAdd) {
    return userProcesses.stream().anyMatch(userProcess -> !isExternalLink(processToAdd)
        && StringUtils.equalsIgnoreCase(userProcess.getLink(), processToAdd.getLink()));
  }
  
  private boolean isExternalLinkUserProcess(UserProcess processToAdd) {
    return userProcesses.stream().anyMatch(userProcess -> isExternalLink(userProcess) && isExternalLink(processToAdd)
        && StringUtils.equalsIgnoreCase(userProcess.getProcessId(), processToAdd.getProcessId()));
  }

  private boolean isDefaultUserProcess(UserProcess processToAdd) {
    return defaultProcesses.stream().anyMatch(userProcess -> StringUtils.equalsIgnoreCase(userProcess.getLink(), processToAdd.getLink()));
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

  public UserProcess getSelectedProcess() {
    return selectedProcess;
  }

  public void setSelectedProcess(UserProcess selectedProcess) {
    this.selectedProcess = selectedProcess;
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
    userProcesses.removeAll(selectedUserProcesses);
    setIndex(userProcesses);
    UserProcessService.getInstance().setPrivateConfig(userProcesses);
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
  
  private void removeDeletedExpressWorkflowFromUserProcesses(List<UserProcess> processes) {
    List<String> executableExpressProcessIds = expressProcesses.values().stream()
        .map(Process::getId).collect(Collectors.toList());

    List<UserProcess> deletedExpressProcesses = processes.stream()
        .filter(process -> isExpressProcess(process) && StringUtils.isNotBlank(process.getProcessId()) && !executableExpressProcessIds.contains(process.getProcessId()))
        .collect(Collectors.toList());

    userProcessService.deleteFromPrivateConfig(deletedExpressProcesses);
    processes.removeAll(deletedExpressProcesses);
    setIndex(processes);
  }

  private void removeDeletedExternalLinkFromUserProcesses(List<UserProcess> processes) {
    List<String> startableExternalLinkIds = externalLinks.values().stream()
        .map(Process::getId)
        .collect(Collectors.toList());
    
    List<UserProcess> deletedExternalLinks = processes.stream()
        .filter(process ->  isExternalLink(process) && StringUtils.isNotBlank(process.getProcessId()) && !startableExternalLinkIds.contains(process.getProcessId().toString()))
        .collect(Collectors.toList());

    userProcessService.deleteFromPrivateConfig(deletedExternalLinks);
    processes.removeAll(deletedExternalLinks);
    setIndex(processes);
  }

  public List<UserProcess> getDefaultUserProcesses() {
    return defaultProcesses;
  }

  public void setUserProcesses(List<UserProcess> userProcesses) {
    this.userProcesses = userProcesses;
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

  public boolean isDisplayShowAllProcessesLink() {
    return isDisplayShowAllProcessesLink;
  }

  public void setDisplayShowAllProcessesLink(boolean isDisplayShowAllProcessesLink) {
    this.isDisplayShowAllProcessesLink = isDisplayShowAllProcessesLink;
  }
  
  public String targetToStartProcess(UserProcess process) {
    String target="_self";
    if (isExternalLink(process)) {
      target="_blank";
    }
    return target;
  }

  private Locale getApplicationDefaultLanguage() {
    return IvyExecutor.executeAsSystem(() -> {
      return IApplication.current().getDefaultEMailLanguage();
    });
  }

  public boolean isExternalLink(UserProcess process) {
    return process != null && ProcessStartUtils.isExternalLink(process.getProcessType());
  }

  public boolean isExpressProcess(UserProcess process) {
    return process != null && ProcessStartUtils.isExpressProcess(process.getProcessType());
  }
  
}
