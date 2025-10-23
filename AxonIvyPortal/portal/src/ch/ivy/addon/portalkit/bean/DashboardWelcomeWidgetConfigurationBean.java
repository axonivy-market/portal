package ch.ivy.addon.portalkit.bean;

import static com.axonivy.portal.util.WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.axonivy.portal.components.document.SVGSecurityScanner;
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.bean.IMultiLanguage;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.WelcomeImageFit;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.enums.WelcomeTextSize;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetConfigurationBean extends DashboardWelcomeWidgetBean implements Serializable, IMultiLanguage  {

  private static final long serialVersionUID = 597266282990903281L;

  private static final String DEFAULT_WELCOME_CMS = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Welcome";

  private List<WelcomeTextPosition> textPositions;
  private List<WelcomeTextSize> textSizes;
  private ContentObject imageCMSObject;
  private ContentObject imageCMSObjectDarkMode;
  private int parsedClientTime;
  private List<WelcomeImageFit> imageFits;

  @Override
  public void init() {
    super.init();
    imageCMSObject = null;
    imageCMSObjectDarkMode = null;
    setTextPositions(Arrays.asList(WelcomeTextPosition.values()));
    setTextSizes(Arrays.asList(WelcomeTextSize.values()));
    setImageFits(Arrays.asList(WelcomeImageFit.values()));
    initWelcomeWidget();
  }

  private void initWelcomeWidget() {
    if (getWidget().getWelcomeTextSize() == null) {
      getWidget().setWelcomeTextSize(WelcomeTextSize.NORMAL_TEXT);
    }

    if (CollectionUtils.isEmpty(getWidget().getWelcomeTexts())) {
      getWidget().setWelcomeTexts(new ArrayList<>());
      for(String lang : LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages()) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(lang));
        displayName.setValue(Ivy.cms().coLocale(DEFAULT_WELCOME_CMS, displayName.getLocale()));
        getWidget().getWelcomeTexts().add(displayName);
      }
    }

    if (StringUtils.isNotBlank(widget.getImageLocation())) {
      imageCMSObject = getWelcomeWidgetImageContentObject(false);
    }
    
    if (StringUtils.isNotBlank(widget.getImageLocationDarkMode())) {
      imageCMSObjectDarkMode = getWelcomeWidgetImageContentObjectDarkMode(false);
    } else {
      imageCMSObjectDarkMode = null;
    }
  }

  public void handleFileUpload(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    if (file == null || file.getContent() == null || file.getContent().length == 0 || file.getFileName() == null) {
      return;
    }

    String originalLocation = getWidget().getImageLocation();
    String originalType = getWidget().getImageType();

    String extension = FilenameUtils.getExtension(file.getFileName());
    byte[] content = file.getContent();

    if (extension != null && extension.toLowerCase().startsWith("svg")) {
      String fileContent = new String(content, StandardCharsets.UTF_8);
      if (!SVGSecurityScanner.isSafe(fileContent)) {
        getWidget().setImageLocation(originalLocation);
        getWidget().setImageType(originalType);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
        FacesContext.getCurrentInstance().addMessage("config-welcome-widget-message", message);
        return;
      }
    }

    if (StringUtils.isNotBlank(originalLocation)) {
      ContentObject previousTemp = getWelcomeWidgetImageContentObject(true);
      if (previousTemp != null) {
        previousTemp.delete();
      }
    }

    String fileName = getWidget().getId().concat(DEFAULT_LOCALE_AND_DOT).concat(extension);
    getWidget().setImageLocation(fileName);
    getWidget().setImageType(extension);

    imageCMSObject = getWelcomeWidgetImageContentObject(true);
    if (imageCMSObject != null) {
      WelcomeWidgetUtils.readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(content);
    }
  }
  
  public void handleFileUploadDarkMode(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    if (file == null || file.getContent() == null || file.getContent().length == 0 || file.getFileName() == null) {
      return;
    }

    String originalLocation = getWidget().getImageLocationDarkMode();
    String originalType = getWidget().getImageTypeDarkMode();

    String extension = FilenameUtils.getExtension(file.getFileName());
    byte[] content = file.getContent();

    if (extension != null && extension.toLowerCase().startsWith("svg")) {
      String fileContent = new String(content, StandardCharsets.UTF_8);
      if (!SVGSecurityScanner.isSafe(fileContent)) {
        getWidget().setImageLocationDarkMode(originalLocation);
        getWidget().setImageTypeDarkMode(originalType);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
        FacesContext.getCurrentInstance().addMessage("config-welcome-widget-message", message);
        return;
      }
    }

    if (StringUtils.isNotBlank(originalLocation)) {
      ContentObject previousTemp = getWelcomeWidgetImageContentObjectDarkMode(true);
      if (previousTemp != null) {
        previousTemp.delete();
      }
    }

    String fileName = getWidget().getId().concat(WelcomeWidgetUtils.DARK_MODE).concat(DEFAULT_LOCALE_AND_DOT).concat(extension);
    getWidget().setImageLocationDarkMode(fileName);
    getWidget().setImageTypeDarkMode(extension);

    imageCMSObjectDarkMode = getWelcomeWidgetImageContentObjectDarkMode(true);

    if (imageCMSObjectDarkMode != null) {
      WelcomeWidgetUtils.readObjectValueOfDefaultLocale(imageCMSObjectDarkMode).write().bytes(content);
    }
  }
  
  public String getImageUriDarkMode() {
    return Objects.isNull(imageCMSObjectDarkMode) ? DEFAULT_IMAGE_DARK_CMS_URI : imageCMSObjectDarkMode.uri();
  }
  
  private ContentObject getWelcomeWidgetImageContentObjectDarkMode(boolean isTempImage) {
    String imageName = WelcomeWidgetUtils.getFileNameOfImage(widget.getImageLocationDarkMode());
    imageName = isTempImage ? "temp_".concat(imageName) : imageName;
    return WelcomeWidgetUtils.getImageContentObject(imageName, widget.getImageTypeDarkMode());
  }
  
  public ContentObject getImageCMSObjectDarkMode() {
    return imageCMSObjectDarkMode;
  }

  public void setImageCMSObjectDarkMode(ContentObject imageCMSObjectDarkMode) {
    this.imageCMSObjectDarkMode = imageCMSObjectDarkMode;
  }

  public void initClientTime() {
    parsedClientTime = WelcomeWidgetUtils.parseClientTime();
  }

  public String generateGreetingText(Locale locale) {
    String greetingTextCms = WelcomeWidgetUtils.generateGreetingTextByTime(parsedClientTime);
    String greetingTextLocalized = StringUtils.defaultString(Ivy.cms().coLocale(greetingTextCms, locale));
    return String.join(" ",
        greetingTextLocalized,
        Ivy.session().getSessionUser().getDisplayName(), "");
  }

  public List<WelcomeTextPosition> getTextPositions() {
    return textPositions;
  }

  public void setTextPositions(List<WelcomeTextPosition> textPositions) {
    this.textPositions = textPositions;
  }

  public String getImageUri() {
    return Objects.isNull(imageCMSObject) ? DEFAULT_IMAGE_CMS_URI : imageCMSObject.uri();
  }

  public List<WelcomeTextSize> getTextSizes() {
    return textSizes;
  }

  public void setTextSizes(List<WelcomeTextSize> textSizes) {
    this.textSizes = textSizes;
  }

  public Long getUploadFileLimit() {
    return UploadDocumentUtils.getImageUploadSizeLimit();
  }

  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getUploadFileLimit())));
  }

  public boolean isApplicationDefaultEmailLanguage(String language) {
    Locale defaultLocale = LanguageService.getInstance().getDefaultLanguage();
    return defaultLocale.toLanguageTag().equalsIgnoreCase(language);
  }

  private ContentObject getWelcomeWidgetImageContentObject(boolean isTempImage) {
    String imageName = WelcomeWidgetUtils.getFileNameOfImage(widget.getImageLocation());
    imageName = isTempImage ? "temp_".concat(imageName) : imageName;
    return WelcomeWidgetUtils.getImageContentObject(imageName, widget.getImageType());
  }

  public List<WelcomeImageFit> getImageFits() {
    return imageFits;
  }

  public void setImageFits(List<WelcomeImageFit> imageFits) {
    this.imageFits = imageFits;
  }
  
  public String getLanguageDisplayText(Locale x) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/DisplayedText", 
        Arrays.asList(x.getDisplayName(Ivy.session().getContentLocale())));
  }
  
  public void updateAltTextByLocale() {
    String currentAlt = LanguageUtils.getLocalizedName(widget.getAltTexts(), widget.getAltText());
    initAndSetValue(currentAlt, widget.getAltTexts());
  }
  
  public void updateCurrentLanguage() {
    widget.setAltText(DisplayNameConvertor.updateCurrentValue(widget.getAltText(), widget.getAltTexts()));
  }
}
