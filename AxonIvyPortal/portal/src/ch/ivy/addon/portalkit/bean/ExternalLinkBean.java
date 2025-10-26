package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import com.axonivy.portal.components.util.ImageUploadResult;
import com.axonivy.portal.service.IvyTranslationService;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.UploadDocumentUtils;

import ch.ivy.addon.portal.generic.bean.IMultiLanguage;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExternalLinkBean implements Serializable, IMultiLanguage {

  private static final long serialVersionUID = 4772777911430826945L;
  private ExternalLink externalLink;
  private ExternalLinkService externaLinkService;
  private String warningText;
  private String translatedText;
  
  @PostConstruct
  public void init() {
    externaLinkService = ExternalLinkService.getInstance();
  }
  
  public void addNewExternalLink(String clientId) {
    externalLink = new ExternalLink();
    externalLink.setIcon(ExternalLinkProcessItem.DEFAULT_ICON);
    PrimeFaces.current().resetInputs(clientId + ":add-external-link-form");
  }

  public ExternalLink saveNewExternalLink() {
    IUser sessionUser = Ivy.session().getSessionUser();
    externalLink.setCreatorId(sessionUser.getId());
    externalLink.setSecurityMemberId(sessionUser.getSecurityMemberId());
    String processLink = correctLink(externalLink.getLink());
    externalLink.setLink(processLink);
    updateEmptyNameAndDescription(externalLink);
    
    externaLinkService.save(externalLink);
    return externalLink;
  }
  
  private void updateEmptyNameAndDescription(ExternalLink externalLink) {
    String userLanguguage = UserUtils.getUserLanguage();
    DisplayNameConvertor.updateEmptyValue(userLanguguage, externalLink.getNames());
    DisplayNameConvertor.updateEmptyValue(userLanguguage, externalLink.getDescriptions());
  }

  public boolean hasPublicLinkCreationPermission() {
    return PermissionUtils.checkPublicLinkCreationPermission();
  }
  
  public String correctLink(String link) {
    String processLink = link.trim();
    if (!isValidProcessLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
    }
    return processLink;
  }

  public void handleImageUpload(FileUploadEvent event) {
    removeImage();
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, DashboardConstants.EXTERNAL_LINK_IMAGE_DIRECTORY);
    if (imageInfo.isInvalid()) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
      FacesContext.getCurrentInstance().addMessage("external-link-dialog-message", message);
      return;
    }
    externalLink.setImageLocation(imageInfo.imageLocation());
    externalLink.setImageType(imageInfo.imageType());
  }
  
  public void removeImage() {
    if (StringUtils.isNoneBlank(externalLink.getImageLocation())) {
      ImageUploadUtils.removeImage(externalLink.getImageLocation(), externalLink.getImageType());
      externalLink.setImageLocation(null);
      externalLink.setImageType(null);
    }
  }

  private boolean isValidProcessLink(String processLink) {
    String linkInLowerCase = processLink.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }
  
  public void startExternalLink(ExternalLink selectedExternalLink) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(selectedExternalLink.getLink());
  }

  public ExternalLink getExternalLink() {
    return externalLink;
  }

  public void setExternalLink(ExternalLink externalLink) {
    this.externalLink = externalLink;
  }
  
  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(externalLink.getNames(), externalLink.getName());
    initAndSetValue(currentName, externalLink.getNames());
  }
  
  public void updateDescriptionByLocale() {
    String currentDescription = LanguageUtils.getLocalizedName(externalLink.getDescriptions(), externalLink.getDescription());
    initAndSetValue(currentDescription, externalLink.getDescriptions());
  }

  public String getWarningText() {
    return warningText;
  }

  public void setWarningText(String warningText) {
    this.warningText = warningText;
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }
  
  public void translate(DisplayName title) {
    translateValues(title, externalLink.getNames());
  }
  
  public void translateTextArea(DisplayName title) {
    translateValues(title, externalLink.getDescriptions());
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
          translatedText = IvyTranslationService.getInstance().translate(optional.get().getValue(),
              optional.get().getLocale(), title.getLocale());
        } catch (Exception e) {
          warningText = Ivy.cms()
              .co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
          Ivy.log().error("Ivy Translation Service error: ", e.getMessage());
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

  public Long getUploadFileLimit() {
    return UploadDocumentUtils.getImageUploadSizeLimit();
  }

  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getUploadFileLimit())));
  }
}
