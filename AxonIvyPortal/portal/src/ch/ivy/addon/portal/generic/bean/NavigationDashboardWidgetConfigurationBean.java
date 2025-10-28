package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import com.axonivy.portal.components.util.ImageUploadResult;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivy.addon.portalkit.constant.DashboardConstants;

@ViewScoped
@ManagedBean
public class NavigationDashboardWidgetConfigurationBean extends NavigationDashboardWidgetBean implements Serializable, IMultiLanguage {

  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    super.init();
  }
  
  public void handleImageUpload(FileUploadEvent event) {
    if (getWidget() == null) {
      return;
    }
    
    // Remove previous image if exists
    removeImage(false);
    
    // Upload new image
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, DashboardConstants.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
    if (StringUtils.isNotBlank(imageInfo.imageLocation())) {
      getWidget().setImageLocation(imageInfo.imageLocation());
      getWidget().setImageType(imageInfo.imageType());
    }
  }
  
  public void handleImageDarkModeUpload(FileUploadEvent event) {
    if (getWidget() == null) {
      return;
    }
    
    // Remove previous image if exists
    removeImage(true);
    
    // Upload new image
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, DashboardConstants.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
    if (StringUtils.isNotBlank(imageInfo.imageLocation())) {
      getWidget().setImageLocationDarkMode(imageInfo.imageLocation());
      getWidget().setImageTypeDarkMode(imageInfo.imageType());
    }
  }
  
  /**
   * Temporarily removes image from UI only (for delete button).
   * The actual image deletion from server happens on save.
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
  
  public void onVisualTypeChange(NavigationDashboardWidget targetWidget) {
    // If switching away from IMAGE mode, remove the uploaded image
//    if (targetWidget != null && targetWidget.getVisualType() != VisualType.IMAGE) {
//      removePreviousImage();
//    }
  }
}
