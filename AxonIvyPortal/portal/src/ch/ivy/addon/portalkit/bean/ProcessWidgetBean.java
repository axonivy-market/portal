package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.IvyProcess;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean extends AbstractProcessBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private static final String SPECIAL_CHARACTER_KEY = "SPECIAL_CHARACTER";
  private static final String DEFAULT_IMAGE_CMS_FOLDER = "/images/process/";

  private Process deletedProcess;
  private Process editedProcess;
  private ExternalLink editedExternalLink;
  private String selectedIconProcess;
  private String viewMode;
  private List<ProcessMode> processViewModes;

  private IProcessStart createExpressWorkflowProcessStart;
  private Map<String, List<Process>> processesByAlphabet;

  private String defaultImageType = DefaultImage.DEFAULT.name();

  public void initProcesses() {
    initProcessViewMode();
    initDefaultProcessImage();
    super.init();
    createExpressWorkflowProcessStart = collector.findExpressCreationProcess();
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  private void initProcessViewMode() {
    String userProcessSetting = UserSettingService.newInstance().getDefaultProcessMode();
    viewMode = getViewModeFromUserProcessSetting(userProcessSetting);
  }

  private String getViewModeFromUserProcessSetting(String userProcessSetting) {
    if (StringUtils.isBlank(userProcessSetting)
        || UserSettingService.newInstance().isDefaultProcessModeOption(userProcessSetting)) {
      GlobalSettingService globalSettingService = new GlobalSettingService();
      GlobalSetting defaultSetting =
          globalSettingService.findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_PROCESS_MODE);
      return getProcessModeByLabel(defaultSetting.getDisplayValue());
    }

    return getProcessModeByLabel(userProcessSetting);
  }

  private String getProcessModeByLabel(String processLabel) {
    return Arrays.stream(ProcessMode.values())
        .filter(e -> StringUtils.equalsIgnoreCase(processLabel, e.getLabel()) || StringUtils.equalsIgnoreCase(e.name(), processLabel))
        .findFirst()
        .orElse(ProcessMode.IMAGE).toString();
  }

  private void initDefaultProcessImage() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    GlobalSetting defaultSetting =
        globalSettingService.findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_PROCESS_IMAGE);
    defaultImageType = defaultSetting.getDisplayValue().toUpperCase();
  }

  private void groupProcessesByAlphabetIndex(List<Process> processes) {
    processesByAlphabet = new HashMap<>();
    // Follow Oracle document about regex for punctual character
    // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    String punctualRegex = "\\p{Punct}";

    for (Process process : processes) {
      String firstLetter = extractProcessFirstLetter(process.getName());
      if (StringUtils.isNotEmpty(firstLetter)) {
        if (firstLetter.matches(punctualRegex)) {
          addOrUpdateProcessesByKey(process, SPECIAL_CHARACTER_KEY);
        } else {
          addOrUpdateProcessesByKey(process, firstLetter);
        }
      }
    }

    List<Process> processesBySpecialCharacterGroup = processesByAlphabet.remove(SPECIAL_CHARACTER_KEY);
    Collator collator = Collator.getInstance(Locale.GERMAN);
    processesByAlphabet = processesByAlphabet.entrySet().stream().sorted(Map.Entry.comparingByKey(collator::compare))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    if (CollectionUtils.isNotEmpty(processesBySpecialCharacterGroup)) {
      processesByAlphabet.put(SPECIAL_CHARACTER_KEY, processesBySpecialCharacterGroup);
    }
  }

  private String extractProcessFirstLetter(String processName) {
    String firstLetter = "";
    String processNameUpperCase = StringUtils.trim(processName).toUpperCase();
    if (StringUtils.isNotEmpty(processNameUpperCase)) {
      firstLetter = processNameUpperCase.substring(0, 1);
    }
    return firstLetter;
  }

  private void addOrUpdateProcessesByKey(Process process, String key) {
    if (!processesByAlphabet.containsKey(key)) {
      List<Process> processes = new ArrayList<>();
      processes.add(process);
      processesByAlphabet.put(key, processes);
    } else {
      processesByAlphabet.get(key).add(process);
    }
  }

  @Override
  protected List<Process> findProcesses() {
    IvyComponentLogicCaller<List<IWebStartable>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    List<IWebStartable> processes =
        ivyComponentLogicCaller.invokeComponentLogic(componentId, "#{logic.collectProcesses}", new Object[] {});
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(iWebStartable -> {
      IvyProcess ivyProcess = new IvyProcess(iWebStartable);
      updateDefaultProcessImage(ivyProcess);
      defaultPortalProcesses.add(ivyProcess);
    });
    return defaultPortalProcesses;
  }

  private void updateDefaultProcessImage(IvyProcess ivyProcess) {
    String customFieldProcessImage = ivyProcess.getCustomFieldImageProcess();
    if (StringUtils.isNotBlank(customFieldProcessImage)) {
      String processImageSrc = getImageSrc(customFieldProcessImage);
      ivyProcess.setDefaultImageSrc(processImageSrc);
      ivyProcess.setDefaultImageCms(StringUtils.EMPTY);
    } else if (!this.defaultImageType.equals(DefaultImage.DEFAULT.name())) {
      ivyProcess.setDefaultImageCms(DEFAULT_IMAGE_CMS_FOLDER + this.defaultImageType);
      ivyProcess.setDefaultImageSrc(StringUtils.EMPTY);
    }
  }
  
  private String getImageSrc(String imageElement) {
    if(!imageElement.contains("/cm")) {
      imageElement = Ivy.cms().cr(imageElement);
    }
    int indexOfImageSrc = imageElement.indexOf("/cm");
    return imageElement.substring(indexOfImageSrc).replaceAll("\"/>", StringUtils.EMPTY);
  }

  public void editExpressWorkflow(ExpressProcess process) throws IOException {
    ProcessStartCollector collector = new ProcessStartCollector();
    String editLink = collector.findExpressWorkflowEditLink(process.getId());
    FacesContext.getCurrentInstance().getExternalContext().redirect(editLink);
  }

  public void deleteProcessWorkflow() {
    if (this.deletedProcess == null) {
      return;
    }

    switch (deletedProcess.getType()) {
      case EXPRESS_PROCESS:
        deleteExpressWorkflow();
        break;
      case EXTERNAL_LINK:
        deleteExternalLink();
        break;
      default:
        break;
    }
  }

  public void deleteExpressWorkflow() {
    String workflowId = this.deletedProcess.getId();
    ExpressProcessService.getInstance().delete(workflowId);
    portalProcesses.remove(
        portalProcesses.stream().filter(process -> process.getId().equals(deletedProcess.getId())).findFirst().get());
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public void updateProcessData() {
    if (this.editedProcess == null) {
      return;
    }
    String oldProcessName = this.editedProcess.getName();
    switch (this.editedProcess.getType()) {
      case EXPRESS_PROCESS:
        ExpressProcess expressProcess = updateExpressProcess(editedProcess.getId());
        this.editedProcess = new PortalExpressProcess(expressProcess);
        break;
      case EXTERNAL_LINK:
        ExternalLink externalLink = updateExternalLink(editedProcess.getId());
        this.editedProcess = new ExternalLinkProcessItem(externalLink);
        break;
      default:
        break;
    }
    selectedIconProcess = null;
    updateStartProcessesList(oldProcessName);
    this.editedProcess = null;
  }

  private ExpressProcess updateExpressProcess(String processId) {
    ExpressProcessService service = ExpressProcessService.getInstance();
    ExpressProcess expressProcess = service.findById(processId);
    if (expressProcess != null) {
      expressProcess.setIcon(this.selectedIconProcess);
      service.save(expressProcess);
    }
    PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(expressProcess);
    return expressProcess;
  }

  private ExternalLink updateExternalLink(String processId) {
    ExternalLinkService externalLinkService = ExternalLinkService.getInstance();
    ExternalLink externalLink = externalLinkService.findById(processId);
    if (externalLink != null) {
      externalLink.setIcon(this.selectedIconProcess);
      externalLink.setName(this.editedExternalLink.getName());
      externalLink.setDescription(this.editedExternalLink.getDescription());
      ExternalLinkBean externalLinkBean = ManagedBeans.get("externalLinkBean");
      String correctLink = externalLinkBean.correctLink(this.editedExternalLink.getLink());
      externalLink.setLink(correctLink);
      externalLinkService.save(externalLink);
    }
    return externalLink;
  }

  private void updateStartProcessesList(String oldProcessName) {
    String processId = this.editedProcess.getId();
    String oldProcessNameFirstLetter = extractProcessFirstLetter(oldProcessName);
    String firstLetter = extractProcessFirstLetter(this.editedProcess.getName());
    if (!StringUtils.equals(oldProcessNameFirstLetter, firstLetter)) {
      if (StringUtils.isNotEmpty(oldProcessNameFirstLetter)
          && this.processesByAlphabet.containsKey(oldProcessNameFirstLetter)) {
        List<Process> processes = this.processesByAlphabet.get(oldProcessNameFirstLetter);
        processes.removeIf(editProcess -> editProcess.getId().equals(processId));
        processes = sortProcesses(processes);
        processesByAlphabet.put(oldProcessNameFirstLetter, processes);
      }
    }

    if (StringUtils.isNotEmpty(firstLetter)) {
      List<Process> processes = this.processesByAlphabet.get(firstLetter);
      if (CollectionUtils.isNotEmpty(processes)) {
        processes.removeIf(editProcess -> editProcess.getId().equals(processId));
      } else {
        processes = new ArrayList<>();
      }
      processes.add(editedProcess);
      sortProcesses(processes);
      processesByAlphabet.put(firstLetter, processes);
    }
  }

  public void createNewExternalLink() {
    ExternalLinkBean externalLinkBean = (ExternalLinkBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "externalLinkBean");
    externalLinkBean.saveNewExternalLink();
    portalProcesses.add(new ExternalLinkProcessItem(externalLinkBean.getExternalLink()));
    portalProcesses = sortProcesses(portalProcesses);
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public void deleteExternalLink() {
    ExternalLinkService.getInstance().delete(this.deletedProcess.getId());
    portalProcesses.remove(portalProcesses.stream()
        .filter(process -> process.getId().equals(this.deletedProcess.getId())).findFirst().get());
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public String getCreateExpessWorkflowLink() {
    return IvyExecutor.executeAsSystem(() -> {
      return (createExpressWorkflowProcessStart != null) ? createExpressWorkflowProcessStart.getLink().getRelative()
          : StringUtils.EMPTY;
    });
  }

  public void startExpressWorkflowCreationLink() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getCreateExpessWorkflowLink());
    return;
  }

  public boolean canCreateExpessWorkflow() {
    return createExpressWorkflowProcessStart != null
        && PermissionUtils.hasPortalPermission(PortalPermission.EXPRESS_CREATE_WORKFLOW);
  }

  public Process getDeletedProcess() {
    return deletedProcess;
  }

  public void setDeletedProcess(Process deletedProcess) {
    this.deletedProcess = deletedProcess;
  }

  public Process getEditedProcess() {
    return editedProcess;
  }

  public void setEditedProcess(Process editedProcess) {
    this.editedProcess = editedProcess;
    setSelectedIconProcess(editedProcess.getIcon());
    if (editedProcess.getType().equals(ProcessType.EXTERNAL_LINK)) {
      updateSeletedEditExternalLink(editedProcess);
    }
  }

  private void updateSeletedEditExternalLink(Process editedProcess) {
    this.editedExternalLink = new ExternalLink();
    this.editedExternalLink.setId(editedProcess.getId());
    this.editedExternalLink.setName(editedProcess.getName());
    this.editedExternalLink.setLink(editedProcess.getStartLink());
    this.editedExternalLink.setDescription(editedProcess.getDescription());
  }

  public Map<String, List<Process>> getProcessesByAlphabet() {
    return processesByAlphabet;
  }

  public void setUserProcessByAlphabet(Map<String, List<Process>> processesByAlphabet) {
    this.processesByAlphabet = processesByAlphabet;
  }

  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

  public Map<String, String> getAllAlphabeticalCharacters() {
    Map<String, String> processGroups = new LinkedHashMap<>();
    for (String processGroupName : processesByAlphabet.keySet()) {
      if (!processGroupName.equals(SPECIAL_CHARACTER_KEY)) {
        processGroups.put(processGroupName, processGroupName);
      } else {
        processGroups.put(SPECIAL_CHARACTER_KEY, "#");
      }
    }

    return processGroups;
  }

  public String getViewMode() {
    return viewMode;
  }

  public void setViewMode(String viewMode) {
    this.viewMode = viewMode;
  }

  public String getSelectedIconProcess() {
    return selectedIconProcess;
  }

  public void setSelectedIconProcess(String selectedIconProcess) {
    this.selectedIconProcess = selectedIconProcess;
  }

  public ExternalLink getEditedExternalLink() {
    return editedExternalLink;
  }

  public void setEditedExternalLink(ExternalLink editedExternalLink) {
    this.editedExternalLink = editedExternalLink;
  }

  public boolean canChangeProcessIcon() {
    return PermissionUtils.isSessionUserHasAdminRole();
  }

  public List<ProcessMode> getProcessViewModes() {
    return CollectionUtils.isEmpty(processViewModes) ? Arrays.asList(ProcessMode.values()) : new ArrayList<>();
  }

  public void setProcessViewModes(List<ProcessMode> processViewModes) {
    this.processViewModes = processViewModes;
  }

  public boolean isCompactMode() {
    return StringUtils.equalsIgnoreCase(this.viewMode, ProcessMode.COMPACT.name());
  }
}
