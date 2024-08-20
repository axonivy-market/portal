package ch.ivy.addon.portalkit.bean;

import static com.axonivy.portal.util.WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.WelcomeImageFit;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.enums.WelcomeTextSize;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetConfigurationBean extends DashboardWelcomeWidgetBean implements Serializable {

  private static final long serialVersionUID = 597266282990903281L;

  private static final Long UPLOAD_SIZE_LIMIT = 6291456L;
  private static final String DEFAULT_WELCOME_CMS = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Welcome";

  private List<WelcomeTextPosition> textPositions;
  private List<WelcomeTextSize> textSizes;
  private ContentObject imageCMSObject;
  private int parsedClientTime;
  private List<WelcomeImageFit> imageFits;

  @Override
  public void init() {
    super.init();
    imageCMSObject = null;
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
  }

  public void handleFileUpload(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
      if (StringUtils.isNotBlank(getWidget().getImageLocation())) {
        getWelcomeWidgetImageContentObject(true).delete();
      }
      // If image is not saved, create location
      String extension = FilenameUtils.getExtension(file.getFileName());
      String fileName = getWidget().getId().concat(DEFAULT_LOCALE_AND_DOT).concat(extension);
      getWidget().setImageLocation(fileName);
      getWidget().setImageType(extension);

      // save the temporary image
      imageCMSObject = getWelcomeWidgetImageContentObject(true);
      if (imageCMSObject != null) {
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(file.getContent());
      }
    }
  }

  public void initClientTime() {
    parsedClientTime = WelcomeWidgetUtils.parseClientTime();
  }

  public String generateGreetingText(Locale locale) {
    String greetingTextCms = WelcomeWidgetUtils.generateGreetingTextByTime(parsedClientTime);
    return String.join(" ",
        Ivy.cms().coLocale(greetingTextCms, locale),
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
    return UPLOAD_SIZE_LIMIT;
  }

  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(UPLOAD_SIZE_LIMIT)));
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
}
