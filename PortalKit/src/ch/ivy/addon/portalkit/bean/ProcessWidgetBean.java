package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import ch.ivy.addon.portalkit.bo.RemoteProcessStart;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private UserProcessService userProcessService;
  private UserProcess editingProcess;
  private List<UserProcess> userProcesses;
  private boolean compactMode;
  private boolean deleteMode;
  private String userName;
  private List<UserProcess> selectedUserProcesses;
  private List<IProcessStart> iProcessStarts;
  private String processWidgetComnponentId;
  
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
  }
  
  private List<UserProcess> findFavoriteProcessUserCanStart(){
    
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    
    List<UserProcess> userProcesses =  ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectDefaultProcesses}",  new Object[]{});
    userProcesses.forEach(defaultProcess -> {
      defaultProcess.setUserName(userName);
      defaultProcess.setDefaultProcess(true);
    });
    
    userProcesses.addAll(userProcessService.findByUserName(userName));
    return userProcesses;
  }
  
  private List<UserProcess> findAllProcesses(){
    IvyComponentLogicCaller<List<UserProcess>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    return ivyComponentLogicCaller.invokeComponentLogic(processWidgetComnponentId, "#{logic.collectAllProcesses}",  new Object[]{});
  }

  public void preRenderProcessAutoComplete(List<IProcessStart> iProcessStarts) {
    if (this.iProcessStarts == null) {
      this.iProcessStarts = iProcessStarts;
    }
  }

  public String getDisplayTextForSwitchModeButton() {
    return compactMode ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/processwidget/seeAllProcesses") : Ivy.cms().co(
        "/ch.ivy.addon.portalkit.ui.jsf/processwidget/backToOverview");
  }

  public void switchMode() {
    compactMode = !compactMode;
    userProcesses =  findUserProcessBaseOnUIMode(compactMode);
  }

  private List<UserProcess> findUserProcessBaseOnUIMode(Boolean compactMode) {
    return compactMode ? findFavoriteProcessUserCanStart() : findAllProcesses();
  }

  public void addNewUserProcess(String clientId) {
    editingProcess = new UserProcess();
    editingProcess.setUserName(userName);
    RequestContext.getCurrentInstance().reset(clientId + ":add-new-process-dialog");
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
        iProcessStarts.stream().filter(processStart -> StringUtils.containsIgnoreCase(processStart.getName(), query) && !isUserProcess(processStart))
            .map(processStart -> stripHtmlTags(processStart.getName())).collect(Collectors.toList());
    return filteredUserProcesses;
  }
  
	private boolean isUserProcess(IProcessStart processStart) {
		return userProcesses.stream()
				.anyMatch(
						userProcess -> ((userProcess.getLink().toLowerCase()).contains(processStart.getFullRequestPath()
								.toLowerCase())));
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
}
