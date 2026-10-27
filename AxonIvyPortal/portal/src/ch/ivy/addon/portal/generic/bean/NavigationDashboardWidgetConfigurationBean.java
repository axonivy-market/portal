package ch.ivy.addon.portal.generic.bean;

import java.util.Arrays;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import com.axonivy.portal.components.util.ImageUploadResult;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.UploadDocumentUtils;

import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@Named
public class NavigationDashboardWidgetConfigurationBean extends NavigationDashboardWidgetBean implements IMultiLanguage {

  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    super.init();
  }
  
  public void handleImageUpload(FileUploadEvent event) {
    if (getWidget() == null) {
      return;
    }
    
    removeImage(false);
    
    // Upload new image
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
    if (imageInfo.isInvalid()) {
      String messageKey = StringUtils.defaultIfBlank(imageInfo.message(),
          "/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript");
      String messageDetail = "/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize".equals(messageKey)
          ? getFileUploadInvalidSizeMessage()
          : Ivy.cms().co(messageKey);
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageDetail, null);
      FacesContext.getCurrentInstance().addMessage("config-custom-widget-message", message);
      return;
    }
    if (StringUtils.isNotBlank(imageInfo.imageLocation())) {
      getWidget().setImageLocation(imageInfo.imageLocation());
      getWidget().setImageType(imageInfo.imageType());
    }
  }
  
  public void handleImageDarkModeUpload(FileUploadEvent event) {
    if (getWidget() == null) {
      return;
    }
    
    removeImage(true);
    
    // Upload new image
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
    if (imageInfo.isInvalid()) {
      String messageKey = StringUtils.defaultIfBlank(imageInfo.message(),
          "/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript");
      String messageDetail = "/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize".equals(messageKey)
          ? getFileUploadInvalidSizeMessage()
          : Ivy.cms().co(messageKey);
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageDetail, null);
      FacesContext.getCurrentInstance().addMessage("config-custom-widget-message", message);
      return;
    }
    if (StringUtils.isNotBlank(imageInfo.imageLocation())) {
      getWidget().setImageLocationDarkMode(imageInfo.imageLocation());
      getWidget().setImageTypeDarkMode(imageInfo.imageType());
    }
  }
  
  /**
   * Remove previous image if exists
   * @param isDarkMode 
   */
  public void removeImage(boolean isDarkMode) {
    if (StringUtils.isNotBlank(getWidget().getImageLocation()) && !isDarkMode) {
      ImageUploadUtils.removeImage(getWidget().getImageLocation(), getWidget().getImageType());
      getWidget().setImageLocation(null);
      getWidget().setImageType(null);
    } else if (StringUtils.isNotBlank(getWidget().getImageLocationDarkMode()) && isDarkMode) {
      ImageUploadUtils.removeImage(getWidget().getImageLocationDarkMode(), getWidget().getImageTypeDarkMode());
      getWidget().setImageLocationDarkMode(null);
      getWidget().setImageTypeDarkMode(null);
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
