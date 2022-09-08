package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.enums.WelcomeTextSize;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.scripting.objects.File;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetConfigurationBean implements Serializable {

  private static final long serialVersionUID = 597266282990903281L;

  private static final String IMAGE_DIRECTORY = "DashboardWelcomeWidget";
  private static final String DEFAULT_TEXT_COLOR = "ffffff";
  private static final String BASE64_STRING_PATTERN = "data:%s;base64,%s";
  private static final Long UPLOAD_SIZE_LIMIT = 6291456L;
  private static final String DEFAULT_WELCOME_CMS = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Welcome";
  private static final String DEFAULT_IMAGE_CMS_URI = "/images/WelcomeWidget/DefaultImage";

  private UploadedFile originalImageFile;
  private List<WelcomeTextPosition> textPositions;
  private List<WelcomeTextSize> textSizes;
  private WelcomeDashboardWidget widget;
  private String encodedImg;
  private int parsedClientTime;

  public void init() {
    this.setOriginalImageFile(null);
    this.setEncodedImg(null);
    setTextPositions(Arrays.asList(WelcomeTextPosition.values()));
    setTextSizes(Arrays.asList(WelcomeTextSize.values()));
    initWelcomeWidget();
  }

  private void initWelcomeWidget() {
    setWidget(Attrs.currentContext().getAttribute("#{cc.attrs.widget}", WelcomeDashboardWidget.class));

    if (getWidget().getWelcomeTextColor() == null) {
      getWidget().setWelcomeTextColor(DEFAULT_TEXT_COLOR);
    }

    if (getWidget().getWelcomeTextPosition() == null) {
      getWidget().setWelcomeTextPosition(WelcomeTextPosition.BOTTOM_LEFT);
    }

    if (getWidget().getWelcomeTextSize() == null) {
      getWidget().setWelcomeTextSize(WelcomeTextSize.NORMAL_TEXT);
    }

    if (CollectionUtils.isEmpty(getWidget().getWelcomeTexts())) {
      getWidget().setWelcomeTexts(new ArrayList<>());
      for(String lang : LanguageService.newInstance().findUserLanguages().getIvyLanguage().getSupportedLanguages()) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(lang));
        displayName.setValue(Ivy.cms().coLocale(DEFAULT_WELCOME_CMS, displayName.getLocale()));
        getWidget().getWelcomeTexts().add(displayName);
      }
    }
    
    if (StringUtils.isNotBlank(widget.getImageLocation())) {
      try {
        widget.setUploadedImageFile(new File(widget.getImageLocation()));
        updateEncodedImage(FileUtils.readFileToByteArray(widget.getUploadedImageFile().getJavaFile()));
      } catch (IOException e) {
        Ivy.log().error(e);
      }
    }
  }

  public void handleFileUpload(FileUploadEvent event) throws IOException {
    UploadedFile file = event.getFile();
  
    // Save image to the temporary folder then use it as image for the widget while configuring.
    if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
      this.setOriginalImageFile(file);
      getWidget().setUploadedImageFile(new File(IMAGE_DIRECTORY.concat("/").concat(getWidget().getId()).concat("/").concat(file.getFileName())));
      if (!getWidget().getUploadedImageFile().exists()) {
        getWidget().getUploadedImageFile().createNewFile();
      }
      getWidget().getUploadedImageFile().writeBinary(new Binary(file.getContent()));
      getWidget().setImageLocation(getWidget().getUploadedImageFile().getPath());
      getWidget().setImageType(getOriginalImageFile().getContentType());
      updateEncodedImage(getOriginalImageFile().getContent());
    }
  }

  private void updateEncodedImage(byte[] content) {
    setEncodedImg(String.format(BASE64_STRING_PATTERN, getWidget().getImageType(), Base64.getEncoder().encodeToString(content)));
  }

  public void initClientTime() {
    parsedClientTime = 0;
    String clientTime = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clientTime");
    if (clientTime != null) {
      parsedClientTime = Integer.parseInt(clientTime);
    }
  }

  public String generateGreetingText(Locale locale) {
    String greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Afternoon";
    if (parsedClientTime < 12) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Morning";
    } else if (parsedClientTime > 18) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Evening";
    }
    return String.join(", ",
        Ivy.cms().coLocale(greetingTextCms, locale),
        Ivy.session().getSessionUser().getDisplayName(), "");
  }

  public List<WelcomeTextPosition> getTextPositions() {
    return textPositions;
  }

  public void setTextPositions(List<WelcomeTextPosition> textPositions) {
    this.textPositions = textPositions;
  }

  public UploadedFile getOriginalImageFile() {
    return originalImageFile;
  }

  public void setOriginalImageFile(UploadedFile originalImageFile) {
    this.originalImageFile = originalImageFile;
  }

  public WelcomeDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(WelcomeDashboardWidget widget) {
    this.widget = widget;
  }

  public String getEncodedImg() {
    return encodedImg;
  }

  public void setEncodedImg(String encodedImg) {
    this.encodedImg = encodedImg;
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

  public String getDefaultImageLink() {
    return DEFAULT_IMAGE_CMS_URI;
  }
}
