package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.comparator.UserProcessIndexComparator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class CompactProcessWidgetBean implements Serializable, Converter {

private static final long serialVersionUID = -5889375917550618261L;
  
  private List<UserProcess> userProcesses;
  private List<UserProcess> defaultProcesses;
  private List<UserProcess> selectedUserProcesses;
  private List<UserProcess> processesToAdd;
  
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private UserProcess selectedProcess;
  private String userName;
  
  private boolean editMode;
  private boolean isUserFavoritesEnabled;
  private boolean isDisplayShowAllProcessesLink;
  private Locale currentLocale;
  
  @PostConstruct
  public void init() {
    currentLocale = getApplicationDefaultLanguage();
    userProcessService = new UserProcessService();
    selectedUserProcesses = new ArrayList<>();
    userName = UserUtils.getSessionUserName();

    String isUserFavoritesEnabledGlobalVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.ENABLE_USER_FAVORITES.toString());
    isUserFavoritesEnabled = StringUtils.isNotBlank(isUserFavoritesEnabledGlobalVariable) ? Boolean.parseBoolean(isUserFavoritesEnabledGlobalVariable) : true;
    userProcesses = findUserProcesses();
    defaultProcesses = findStartableDefaultProcesses();
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
  }

  private List<UserProcess> findStartableDefaultProcesses() {
    List<UserProcess> processes = userProcessService.getDefaultUserProcessesFromSubProcess();
    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    return processes;
  }

  private List<UserProcess> findUserProcesses() {
    if (!isUserFavoritesEnabled) {
      return new ArrayList<>();
    }
    
    List<UserProcess> processes = userProcessService.findByUserName(userName);
    processes.sort(UserProcessIndexComparator.comparatorNullsLast(UserProcess::getIndex));
    removeDeletedExpressWorkflowFromUserProcesses(processes);
    removeDeletedExternalLinkFromUserProcesses(processes);
    return processes;
  }

  private void setIndex(List<UserProcess> userProcesses) {
    int index = 0;
    for (UserProcess process : userProcesses) {
      process.setIndex(index);
      index++;
    }
  }

  public void addNewUserProcess() {
    this.editingProcess = new UserProcess();
    this.selectedProcess = new UserProcess();
    initDataForProcessAutoComplete();
  }
  
  public boolean canAddProcessLanguages() {
    return StringUtils.isNotBlank(this.editingProcess.getProcessName());
  }

  public List<DisplayName> generateProcessDisplayNames() {
    if (CollectionUtils.isNotEmpty(this.editingProcess.getNames())) {
      return this.editingProcess.getNames();
    }

    List<String> languages = getSupportedLanguage();
    List<DisplayName> displayNames = new ArrayList<>();
    for (String language : languages) {
      DisplayName displayName = new DisplayName();
      displayName.setLocale(new Locale(language));
      displayName.setValue(this.editingProcess.getProcessName());
      displayNames.add(displayName);
    }
    return displayNames;
  }

  private List<String> getSupportedLanguage() {
    return IvyExecutor.executeAsSystem(() -> {
      List<String> languages = new ArrayList<>();
      IvyLanguageResultDTO ivyLanguageResult = LanguageService.newInstance()
          .findUserLanguages(Ivy.session().getSessionUserName(), Arrays.asList(IApplication.current().getName()));
      if (CollectionUtils.isNotEmpty(ivyLanguageResult.getIvyLanguages())) {
        languages = ivyLanguageResult.getIvyLanguages().get(0).getSupportedLanguages();
      }
      return languages;
    });
  }

  public boolean isRequiredLanguage(Locale locale) {
    return this.currentLocale.equals(locale);
  }
  
  public void initDataForProcessAutoComplete() {
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
    editingProcess.setUserName(Ivy.session().getSessionUserName());
    editingProcess.setIndex(userProcesses.size());
    editingProcess = userProcessService.save(editingProcess);
    userProcesses.add(editingProcess);
  }

  public List<UserProcess> completeUserProcess(String query) {
    return processesToAdd.stream()
        .filter(processToAdd -> StringUtils.containsIgnoreCase(processToAdd.getProcessName(), query)
            && !isUserProcess(processToAdd) && !isExternalLinkUserProcess(processToAdd) 
            && !isDefaultUserProcess(processToAdd))
        .collect(Collectors.toList());
  }

  public void onSelectUserProcess() throws CloneNotSupportedException {
    this.editingProcess = this.selectedProcess.clone();
  }

  public void clearProcessDisplayNames() {
    this.editingProcess.setNames(new ArrayList<>());
  }

  public void sortUserProcessList(List<UserProcess> processes) {
    processes.sort((process1, process2) -> StringUtils.compareIgnoreCase(process1.getProcessName(), process2.getProcessName().toLowerCase()));
  }
  
  public void startProcess(UserProcess userProcess) throws IOException {
    Objects.requireNonNull(userProcess, "User process must not be null");
    String link = userProcess.getLink();
    if (userProcess.isExternalLink()) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
      return;
    }
    
    link += link.contains("?") ? "&" : "?";
    // Put the "embedInIFrame" param to the process link to open it in the DefaultFramePage process
    // Then this process will open task in IFrame or not based on its "embedInIFrame" String custom field
    FacesContext.getCurrentInstance().getExternalContext().redirect(link + "embedInFrame");
  }

  private boolean isUserProcess(UserProcess processToAdd) {
    return userProcesses.stream().anyMatch(userProcess -> !processToAdd.isExternalLink()
        && StringUtils.equalsIgnoreCase(userProcess.getLink(), processToAdd.getLink()));
  }
  
  private boolean isExternalLinkUserProcess(UserProcess processToAdd) {
    return userProcesses.stream().anyMatch(userProcess -> userProcess.isExternalLink() && processToAdd.isExternalLink()
        && StringUtils.equalsIgnoreCase(userProcess.getWorkflowId(), processToAdd.getWorkflowId()));
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
  
  private void removeDeletedExpressWorkflowFromUserProcesses(List<UserProcess> processes) {
    List<String> executableExpressProcessIds = ExpressServiceRegistry.getProcessService()
                                      .findReadyToExecuteProcessOrderByName()
                                      .stream().map(ExpressProcess::getId)
                                      .collect(Collectors.toList());
    
    List<UserProcess> deletedExpressProcesses = processes.stream()
        .filter(process ->  StringUtils.isNotBlank(process.getWorkflowId()) 
            && !executableExpressProcessIds.contains(process.getWorkflowId())
            && !process.isExternalLink())
        .collect(Collectors.toList());

    userProcessService.deleteAll(deletedExpressProcesses);
    processes.removeAll(deletedExpressProcesses);
    setIndex(processes);
  }
  
  private void removeDeletedExternalLinkFromUserProcesses(List<UserProcess> processes) {
    List<String> startableExternalLinkIds = ExternalLinkService.getInstance()
        .findStartableLink(Ivy.session().getSessionUserName())
        .stream()
        .map(link -> link.getId().toString())
        .collect(Collectors.toList());
    
    List<UserProcess> deletedExternalLinks = processes.stream()
        .filter(process ->  process.isExternalLink() && StringUtils.isNotBlank(process.getWorkflowId()) && !startableExternalLinkIds.contains(process.getWorkflowId().toString()))
        .collect(Collectors.toList());

    userProcessService.deleteAll(deletedExternalLinks);
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
    if (process.isExternalLink()) {
      target="_blank";
    }
    return target;
  }

  private Locale getApplicationDefaultLanguage() {
    return IvyExecutor.executeAsSystem(() -> {
      return IApplication.current().getDefaultEMailLanguage();
    });
  }
}
