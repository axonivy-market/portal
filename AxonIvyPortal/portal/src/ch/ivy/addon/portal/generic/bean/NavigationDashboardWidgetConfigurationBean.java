package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import com.axonivy.portal.components.util.ImageUploadResult;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.enums.VisualType;
import ch.ivyteam.util.Pair;

@ViewScoped
@ManagedBean
public class NavigationDashboardWidgetConfigurationBean extends NavigationDashboardWidgetBean implements Serializable, IMultiLanguage {

  private static final long serialVersionUID = 1L;

  private NavigationDashboardWidget widget;
  
  // Track images to be deleted on save (deferred deletion) - stores imageLocation -> imageType
  private Map<String, String> imagesToDeleteOnSave = new HashMap<>();

  public void handleImageUpload(FileUploadEvent event) {
    if (this.widget == null) {
      return;
    }
    
    // Remove previous image if exists
    removePreviousImage();
    
    // Upload new image
    ImageUploadResult imageInfo = ImageUploadUtils.handleImageUpload(event, DashboardConstants.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
    if (StringUtils.isNotBlank(imageInfo.imageLocation())) {
      this.widget.setImageLocation(imageInfo.imageLocation());
      this.widget.setImageType(imageInfo.imageType());
    }
  }
  
  public void removePreviousImage() {
    if (this.widget != null && StringUtils.isNotBlank(this.widget.getImageLocation())) {
      ImageUploadUtils.removeImage(this.widget.getImageLocation(), this.widget.getImageType());
      this.widget.setImageLocation(null);
      this.widget.setImageType(null);
    }
  }
  
  public void removeImage(NavigationDashboardWidget targetWidget) {
    if (targetWidget != null && StringUtils.isNotBlank(targetWidget.getImageLocation())) {
      ImageUploadUtils.removeImage(targetWidget.getImageLocation(), targetWidget.getImageType());
      targetWidget.setImageLocation(null);
      targetWidget.setImageType(null);
    }
  }
  
  /**
   * Temporarily removes image from UI only (for delete button).
   * The actual image deletion from server happens on save.
   */
  public void removeTempImage(NavigationDashboardWidget targetWidget) {
    if (targetWidget != null && StringUtils.isNotBlank(targetWidget.getImageLocation())) {
      // Track this image for deletion on save (store both location and type)
      imagesToDeleteOnSave.put(targetWidget.getImageLocation(), targetWidget.getImageType());
      
      // Clear the image location from UI only
      targetWidget.setImageLocation(null);
      targetWidget.setImageType(null);
    }
  }
  
  /**
   * Actually deletes images from server that were marked for deletion.
   * Call this method when saving the widget configuration.
   */
  public void handleDeferredImageDeletions() {
    for (Map.Entry<String, String> entry : imagesToDeleteOnSave.entrySet()) {
      try {
        // Use existing image deletion logic with both location and type
        ImageUploadUtils.removeImage(entry.getKey(), entry.getValue());
      } catch (Exception e) {
        // Log error but don't fail the save operation
        System.err.println("Failed to delete image: " + entry.getKey() + " - " + e.getMessage());
      }
    }
    imagesToDeleteOnSave.clear();
  }
  
  /**
   * Cancels deferred deletions (e.g., when user cancels configuration dialog).
   * Images marked for deletion will remain on server.
   */
  public void cancelDeferredImageDeletions() {
    imagesToDeleteOnSave.clear();
  }
  
  public void onVisualTypeChange(NavigationDashboardWidget targetWidget) {
    // If switching away from IMAGE mode, remove the uploaded image
    if (targetWidget != null && targetWidget.getVisualType() != VisualType.IMAGE) {
      removeImage(targetWidget);
    }
  }

  public NavigationDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(NavigationDashboardWidget widget) {
    this.widget = widget;
  }
}
