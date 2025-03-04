package ch.ivy.addon.portalkit.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.service.GlobalSearchService;
import com.axonivy.portal.util.ExternalLinkUtils;

import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.IvyProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.util.Pair;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean extends AbstractProcessBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private static final String SPECIAL_CHARACTER_KEY = "SPECIAL_CHARACTER";

  private Process deletedProcess;
  private Process editedProcess;
  private ExternalLink editedExternalLink;
  private String originalExternalLinkImage;
  private String selectedIconProcess;
  private String viewMode;
  private List<ProcessMode> processViewModes;
  private List<SecurityMemberDTO> selectedSecurityMemberDTOsWhenCreatingExternalLink;
  private List<String> selectedPermissionsWhenCreatingExternalLink;
  private List<SecurityMemberDTO> selectedSecurityMemberDTOsWhenEditingExternalLink;
  private List<String> selectedPermissionsWhenEditingExternalLink;
  private List<String> selectedPermissionsForSavingEditedExternalLink;
  private Map<String, List<Process>> processesByAlphabet;
  
  private String warningText;
  private String translatedText;

  public void initConfiguration() {
    initProcessViewMode();
  }

  public void initProcesses() {
    super.init();
    processesByAlphabet = new HashMap<>();
    selectedPermissionsWhenCreatingExternalLink = new ArrayList<>();
    selectedPermissionsWhenEditingExternalLink = new ArrayList<>();
    groupProcessesByAlphabetIndex(getPortalProcesses());
  }

  private void initProcessViewMode() {
    String userProcessSetting = UserSettingService.getInstance().getDefaultProcessMode();
    if (StringUtils.isBlank(userProcessSetting)
        || UserSettingService.getInstance().isDefaultProcessModeOption(userProcessSetting)) {
      GlobalSetting defaultSetting = GlobalSettingService.getInstance()
          .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_PROCESS_MODE);
      viewMode = getProcessModeByLabel(defaultSetting.getDisplayValue());
    } else {
      viewMode = getProcessModeByLabel(userProcessSetting);
    }
  }

  private String getProcessModeByLabel(String processLabel) {
    return Stream.of(ProcessMode.values())
        .filter(e -> StringUtils.equalsIgnoreCase(processLabel, e.getLabel()) || StringUtils.equalsIgnoreCase(e.name(), processLabel))
        .findFirst()
        .orElse(ProcessMode.IMAGE).toString();
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
    List<IWebStartable> processes = ivyComponentLogicCaller.invokeComponentLogic(componentId, "#{logic.collectProcesses}", new Object[] {});
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(iWebStartable -> {
      IvyProcess ivyProcess = new IvyProcess(iWebStartable);
      defaultPortalProcesses.add(ivyProcess);
    });
    return defaultPortalProcesses;
  }

  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = EMPTY;
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/TextWithRoundBracket"), responsible.getDisplayName(), responsible.getName());
      }
      return responsible.isEnabled() ? responsibleName
          : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), responsibleName);
    }
    return responsibleName;
  }

  public List<SecurityMemberDTO> completePermissionsWhenCreatingExternalLink(String query) {
    return com.axonivy.portal.components.util.RoleUtils
        .findRoles(null, selectedPermissionsWhenCreatingExternalLink, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public List<SecurityMemberDTO> completePermissionsWhenEditingExternalLink(String query) {
    return com.axonivy.portal.components.util.RoleUtils
        .findRoles(null, selectedPermissionsWhenEditingExternalLink, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public void onSelectPermissionsWhenCreatingExternalLink(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedPermissionsWhenCreatingExternalLink.add(selectedItem.getName());
  }

  public void onUnSelectPermissionsWhenCreatingExternalLink(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedPermissionsWhenCreatingExternalLink.remove(selectedItem.getName());
  }

  public void onSelectPermissionsWhenEditingExternalLink(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedPermissionsWhenEditingExternalLink.add(selectedItem.getName());
  }

  public void onUnSelectPermissionsWhenEditingExternalLink(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedPermissionsWhenEditingExternalLink.remove(selectedItem.getName());
  }

  public void handleSavePermissionSelections() {
    this.selectedPermissionsForSavingEditedExternalLink = new ArrayList<>(this.selectedPermissionsWhenEditingExternalLink);
  }

  public void handleCancelPermissionSelections() {
    this.selectedPermissionsWhenEditingExternalLink = new ArrayList<>(this.selectedPermissionsForSavingEditedExternalLink);
    this.setSelectedSecurityMemberDTOsWhenEditingExternalLink(getSecurityMemberDTOsFromPermissions(this.selectedPermissionsForSavingEditedExternalLink));
  }

  public String getDisplayNameOfPermissionsWhenEditingExternalLink() {
    List<String> displayNames = getselectedSecurityMemberDTOsWhenEditingExternalLink().stream().map(dto -> dto.getDisplayName()).toList();
    return String.join(", ", displayNames);
  }

  public void deleteProcessWorkflow() {
    if (this.deletedProcess == null) {
      return;
    }

    switch (deletedProcess.getType()) {
      case EXTERNAL_LINK -> deleteExternalLink();
      default -> {}
    }
  }

  public void updateProcessData() {
    if (this.editedProcess == null) {
      return;
    }
    String oldProcessName = this.editedProcess.getName();
    switch (this.editedProcess.getType()) {
      case EXTERNAL_LINK -> {
        ExternalLink externalLink = updateExternalLink(editedProcess.getId());
        this.editedProcess = new ExternalLinkProcessItem(externalLink);
      }
      default -> {}
    }
    selectedIconProcess = null;
    updateStartProcessesList(oldProcessName);
    this.editedProcess = null;
  }

  private ExternalLink updateExternalLink(String processId) {
    ExternalLinkService externalLinkService = ExternalLinkService.getInstance();
    ExternalLink externalLink = externalLinkService.findById(processId);
    if (externalLink != null) {
      externalLink.setIcon(this.selectedIconProcess);
      externalLink.setName(this.editedExternalLink.getName());
      externalLink.setDescription(this.editedExternalLink.getDescription());
      if (!Objects.equals(this.editedExternalLink.getImageLocation(), this.originalExternalLinkImage)) {
        removeOriginalExternalLinkImage(externalLink.getImageLocation(), externalLink.getImageType());
        externalLink.setImageLocation(this.editedExternalLink.getImageLocation());
        externalLink.setImageType(this.editedExternalLink.getImageType());
      }
      if (CollectionUtils.isNotEmpty(this.selectedPermissionsForSavingEditedExternalLink)) {
        externalLink.setPermissions(this.selectedPermissionsForSavingEditedExternalLink);
      }
      externalLink.setNames(this.editedExternalLink.getNames());
      externalLink.setDescriptions(this.editedExternalLink.getDescriptions());
      updateEmptyNameAndDescription(externalLink);
      ExternalLinkBean externalLinkBean = ManagedBeans.get("externalLinkBean");
      String correctLink = externalLinkBean.correctLink(this.editedExternalLink.getLink());
      externalLink.setLink(correctLink);
      externalLinkService.save(externalLink);
    }
    return externalLink;
  }
  
  private void updateEmptyNameAndDescription(ExternalLink externalLink) {
    String userLanguguage = UserUtils.getUserLanguage();
    DisplayNameConvertor.updateEmptyValue(userLanguguage, externalLink.getNames());
    DisplayNameConvertor.updateEmptyValue(userLanguguage, externalLink.getDescriptions());
  }

  public void handleExternalLinkImageUpload(FileUploadEvent event) {
    if(this.editedExternalLink == null) {
      return;
    }
    removeTempExternalLinkImage();
    Pair<String, String> imageInfo = ExternalLinkUtils.handleImageUpload(event);
    this.editedExternalLink.setImageLocation(imageInfo.getLeft());
    this.editedExternalLink.setImageType(imageInfo.getRight());
  }

  public void removeTempExternalLinkImage() {
    if (this.editedExternalLink != null && StringUtils.isNoneBlank(this.editedExternalLink.getImageLocation())) {
      if (!Objects.equals(this.editedExternalLink.getImageLocation(), this.originalExternalLinkImage)) {
        ExternalLinkUtils.removeImage(this.editedExternalLink.getImageLocation(), this.editedExternalLink.getImageType());
      }
      this.editedExternalLink.setImageLocation(null);
      this.editedExternalLink.setImageType(null);
    }
  }

  public void removeOriginalExternalLinkImage(String imageUrl, String imageType) {
    if (StringUtils.isNoneBlank(imageUrl) && !isDefaultProcessImage(imageUrl)) {
      ExternalLinkUtils.removeImage(imageUrl, imageType);
    }
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
      getPortalProcesses().removeIf(process -> process.getId().equals(processId));
      getPortalProcesses().add(this.editedProcess);
    }
  }

  public void createNewExternalLink() {
    ExternalLinkBean externalLinkBean = (ExternalLinkBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "externalLinkBean");
    if (CollectionUtils.isNotEmpty(selectedPermissionsWhenCreatingExternalLink)) {
      externalLinkBean.getExternalLink().setIsPublic(true);
      externalLinkBean.getExternalLink().setPermissions(selectedPermissionsWhenCreatingExternalLink);
    } else {
      externalLinkBean.getExternalLink().setIsPublic(false);
    }
    externalLinkBean.saveNewExternalLink();
    getPortalProcesses().add(new ExternalLinkProcessItem(externalLinkBean.getExternalLink()));
    sortProcesses(getPortalProcesses());
    groupProcessesByAlphabetIndex(getPortalProcesses());
    resetPermission();
  }

  public void deleteExternalLink() {
    ExternalLinkService.getInstance().delete(deletedProcess.getId());
    removeOriginalExternalLinkImage(deletedProcess.getImageUrl(),
        ((ExternalLinkProcessItem) deletedProcess).getImageType());
    getPortalProcesses().removeIf(process -> process.getId().equals(deletedProcess.getId()));
    groupProcessesByAlphabetIndex(getPortalProcesses());
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
    this.editedExternalLink.setPermissions(editedProcess.getPermissions());
    if (!isDefaultProcessImage(editedProcess.getImageUrl())) {
      this.editedExternalLink.setImageLocation(editedProcess.getImageUrl());
      this.originalExternalLinkImage = editedProcess.getImageUrl();
      this.editedExternalLink.setImageType(((ExternalLinkProcessItem) editedProcess).getImageType());
    }
    if (CollectionUtils.isNotEmpty(editedProcess.getPermissions())) {
      List<String> permissions = editedProcess.getPermissions();
      this.editedExternalLink.setIsPublic(true);
      this.setSelectedPermissionsWhenEditingExternalLink(permissions);
      this.setSelectedSecurityMemberDTOsWhenEditingExternalLink(getSecurityMemberDTOsFromPermissions(permissions));
      this.selectedPermissionsForSavingEditedExternalLink = new ArrayList<>(permissions);
    } else {
      this.editedExternalLink.setIsPublic(false);
    }
    ExternalLink findById = ExternalLinkService.getInstance().findById(editedProcess.getId());
    if (findById != null) {
      this.editedExternalLink.setNames(findById.getNames());
      this.editedExternalLink.setDescriptions(findById.getDescriptions());
    }
  }

  private List<SecurityMemberDTO> getSecurityMemberDTOsFromPermissions(
      List<String> permissions) {
    if (CollectionUtils.isEmpty(permissions)) {
      return new ArrayList<>();
    }

    return permissions.stream().filter(Objects::nonNull).distinct()
        .map(permission -> findSecurityMemberDtoByName(permission))
        .collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#")
        ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  public Map<String, List<Process>> getProcessesByAlphabet() {
    return processesByAlphabet;
  }

  public void setUserProcessByAlphabet(Map<String, List<Process>> processesByAlphabet) {
    this.processesByAlphabet = processesByAlphabet;
  }

  public Map<String, String> getAllAlphabeticalCharacters() {
    Map<String, String> processGroups = new LinkedHashMap<>();
    if (processesByAlphabet == null) {
      return processGroups;
    }
    for (String processGroupName : CollectionUtils.emptyIfNull(processesByAlphabet.keySet())) {
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

  public List<SecurityMemberDTO> getSelectedSecurityMemberDTOsWhenCreatingExternalLink() {
    return selectedSecurityMemberDTOsWhenCreatingExternalLink;
  }

  public void setSelectedSecurityMemberDTOsWhenCreatingExternalLink(List<SecurityMemberDTO> selectedSecurityMemberDTOsWhenCreatingExternalLink) {
    this.selectedSecurityMemberDTOsWhenCreatingExternalLink = new ArrayList<>(selectedSecurityMemberDTOsWhenCreatingExternalLink);
  }

  public List<String> getSelectedPermissionsWhenCreatingExternalLink() {
    return selectedPermissionsWhenCreatingExternalLink;
  }

  public void setSelectedPermissionsWhenCreatingExternalLink(List<String> selectedPermissionsWhenCreatingExternalLink) {
    this.selectedPermissionsWhenCreatingExternalLink = new ArrayList<>(selectedPermissionsWhenCreatingExternalLink);
  }

  public List<SecurityMemberDTO> getselectedSecurityMemberDTOsWhenEditingExternalLink() {
    return selectedSecurityMemberDTOsWhenEditingExternalLink;
  }

  public void setSelectedSecurityMemberDTOsWhenEditingExternalLink(List<SecurityMemberDTO> selectedSecurityMemberDTOsWhenEditingExternalLink) {
    this.selectedSecurityMemberDTOsWhenEditingExternalLink = new ArrayList<>(selectedSecurityMemberDTOsWhenEditingExternalLink);
  }

  public List<String> getSelectedPermissionsWhenEditingExternalLink() {
    return selectedPermissionsWhenEditingExternalLink;
  }

  public void setSelectedPermissionsWhenEditingExternalLink(List<String> selectedPermissionsWhenEditingExternalLink) {
    this.selectedPermissionsWhenEditingExternalLink = new ArrayList<>(selectedPermissionsWhenEditingExternalLink);
  }
  
  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(editedExternalLink.getNames(), editedExternalLink.getName());
    initAndSetValue(currentName, editedExternalLink.getNames());
  }
  
  public void updateDescriptionByLocale() {
    String currentDescription = LanguageUtils.getLocalizedName(editedExternalLink.getDescriptions(), editedExternalLink.getDescription());
    initAndSetValue(currentDescription, editedExternalLink.getDescriptions());
  }
  
  private void initAndSetValue(String value, List<DisplayName> values) {
    DisplayNameConvertor.initMultipleLanguages(value, values);
    DisplayNameConvertor.setValue(value, values);
  }
  
  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }
  
  public boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  public boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }

  public String getWarningText() {
    return warningText;
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void setWarningText(String warningText) {
    this.warningText = warningText;
  }

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }
  
  public void translate(DisplayName title) {
    translateValues(title, editedExternalLink.getNames());
  }
  
  public void translateTextArea(DisplayName title) {
    translateValues(title, editedExternalLink.getDescriptions());
  }
  
  private void translateValues(DisplayName title, List<DisplayName> languages) {
    translatedText = Strings.EMPTY;
    warningText = Strings.EMPTY;

    String currentLanguage = UserUtils.getUserLanguage();
    if (!title.getLocale().getLanguage().equals(currentLanguage)) {
      Optional<DisplayName> optional = languages.stream()
          .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
      if (optional.isPresent()) {
        try {
          translatedText = DeepLTranslationService.getInstance().translate(optional.get().getValue(),
              optional.get().getLocale(), title.getLocale());
        } catch (Exception e) {
          warningText = Ivy.cms()
              .co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
          Ivy.log().error("DeepL Translation Service error: ", e.getMessage());
        }
      }
    }
  }
  
  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(translatedText)) {
      displayName.setValue(translatedText);
      translatedText = "";
    }
  }
  
  public boolean isShowGlobalSearchScope() {
    return GlobalSearchService.getInstance().isShowGlobalSearchByProcesses();
  }

  public void resetPermission() {
    if (selectedSecurityMemberDTOsWhenCreatingExternalLink != null) {
      selectedSecurityMemberDTOsWhenCreatingExternalLink.clear();
    }
  }
}
