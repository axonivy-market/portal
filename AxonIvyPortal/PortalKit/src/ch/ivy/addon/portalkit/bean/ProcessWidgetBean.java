package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.SelectEvent;

import ch.ivy.addon.portalkit.bo.RemoteProcessStart;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.persistence.domain.UserSetting;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.service.UserSettingService;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private UserProcessService userProcessService;
  private UserSettingService userSettingService;
  private UserProcess editingProcess;
  private UserSetting userSetting;
  private List<UserProcess> userProcesses;
  private boolean compactMode;
  private boolean deleteMode;
  private boolean addDefaultAcmProcess;
  private String userName;
  private List<UserProcess> selectedUserProcesses;
  private List<IProcessStart> iProcessStarts;

  @PostConstruct
  public void init() {
    userSettingService = new UserSettingService();
    userProcessService = new UserProcessService();
    compactMode = true;
    deleteMode = false;
    selectedUserProcesses = new ArrayList<UserProcess>();
    userName = UserUtils.getSessionUserName();
    findProcessesOfUser();
  }

  public void preRenderProcessAutoComplete(List<IProcessStart> iProcessStarts) {
    if (this.iProcessStarts == null) {
      this.iProcessStarts = iProcessStarts;
    }
  }

  public void setCompactModeAsDefault() {
    compactMode = true;
  }

  public String getDisplayTextForSwitchModeButton() {
    return compactMode ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/processwidget/seeAllProcesses") : Ivy.cms().co(
        "/ch.ivy.addon.portalkit.ui.jsf/processwidget/backToOverview");
  }

  public void switchMode() {
    compactMode = !compactMode;
  }

  private String findACMLink() {
    IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
    List<IApplication> applications = appConfig.getApplications();
    for (IApplication app : applications) {
      ProcessStartCollector collector = new ProcessStartCollector(app);
      IProcessStart process =
          collector.findProcessStartByUserFriendlyRequestPath("BusinessProcesses/AdHocWF/start.ivp");
      if (process != null) {
        URI processUri = RequestUriFactory.createProcessStartUri(appConfig, process);
        return getServerUrl() + processUri.toString();
      }
    }
    return StringUtils.EMPTY;
  }

  private String getServerUrl() {
    UrlDetector urlDetector = new UrlDetector();
    String serverUrl = StringUtils.EMPTY;
    try {
      String baseUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      serverUrl = urlDetector.getHost(baseUrl);
    } catch (MalformedURLException e) {
      Ivy.log().error(e);
    }
    return serverUrl;
  }

  private UserProcess createACMProcess() {
    UserProcess acmProcess = new UserProcess();
    acmProcess.setLink(findACMLink());
    acmProcess.setProcessName("Ad hoc Processes");
    acmProcess.setIcon("fa-code-fork");
    acmProcess.setUserName(userName);
    return acmProcess;
  }

  private void findProcessesOfUser() {
    userProcesses = new ArrayList<UserProcess>();
    UserSetting userSetting = userSettingService.findByUserName(userName);
    addDefaultAcmProcess = false;
    if (userSetting == null) {
      addDefaultACMProcess();
    }

    userProcesses.addAll(userProcessService.findByUserName(userName));
  }

  private void addDefaultACMProcess() {
    addDefaultAcmProcess = true;
    editingProcess = createACMProcess();
    editingProcess = userProcessService.save(editingProcess);
    createUserSetting();
  }

  private void createUserSetting() {
    userSetting = new UserSetting();
    userSetting.setUserName(userName);
    userSetting.setDefaultLinkACMAdded(true);
    userSettingService.save(userSetting);
  }

  public void addNewUserProcess() {
    editingProcess = new UserProcess();
    editingProcess.setUserName(userName);
  }

  public void saveNewUserProcess() {
    correctProcessLink();
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
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue());
  }

  public List<String> completeUserProcess(String query) {
    List<String> filteredUserProcesses =
        iProcessStarts.stream().filter(processStart -> StringUtils.containsIgnoreCase(processStart.getName(), query))
            .map(processStart -> stripHtmlTags(processStart.getName())).collect(Collectors.toList());
    return filteredUserProcesses;
  }

  public void selectUserProcess(SelectEvent event) {
    String selectedProcessName = (String) event.getObject();
    for (IProcessStart iProcessStart : iProcessStarts) {
      if (stripHtmlTags(iProcessStart.getName()).equals(selectedProcessName)) {
        editingProcess.setLink(((RemoteProcessStart) iProcessStart).getStartLink());
        return;
      }
    }
  }

  public String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }

  public String getProcessDescription(String userProcessName) {
    for (IProcessStart iProcessStart : iProcessStarts) {
      if (iProcessStart.getName().equals(userProcessName)) {
        return iProcessStart.getDescription();
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
    userProcesses.removeAll(selectedUserProcesses);
    userProcessService.deleteAll(selectedUserProcesses);
    deleteMode = false;
  }

  private void clearSelectedUserProcess() {
    selectedUserProcesses.clear();
  }

  public void selectDeletingProcess(AjaxBehaviorEvent event) {
    SelectBooleanCheckbox booleanCheckbox = (SelectBooleanCheckbox) event.getComponent();
    UserProcess selectedUserProcess = (UserProcess) booleanCheckbox.getAttributes().get("selectedProcess");
    if (booleanCheckbox.isSelected()) {
      selectedUserProcesses.add(selectedUserProcess);
    } else {
      selectedUserProcesses.remove(selectedUserProcess);
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

  public boolean isAddDefaultAcmProcess() {
    return addDefaultAcmProcess;
  }

  public UserSetting getUserSetting() {
    return userSetting;
  }
}
