package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public interface Process {
  public static final String DEFAULT_PROCESS_ICON = "si si-hierarchy-6 si-rotate-270";
  public String getName();
  public String getStartLink();
  public String getDescription();
  public Object getProcess();
  public ProcessType getType();
  public String getTypeName();
  public String getId();
  public Category getCategory();
  public String getImageUrl();
  public String getApplication();
  public String getSortIndex();
  public String getPortalProcessInformation();

  default public List<String> getPermissions() {
    return new ArrayList<>();
  }

  default public String getIcon() {
    return DEFAULT_PROCESS_ICON;
  }

  default public String getCustomFieldProcessImage(IWebStartable process) {
    return process.customFields().value(CustomFields.PROCESS_IMAGE);
  }

  default public String getContentImageUrl(String imageUri) {
    if(!imageUri.contains("/cm")) {
      imageUri = Ivy.cms().cr(imageUri);
    }
    return imageUri;
  }

  /**
   * This method collects the image with the priority:
   *  - First priority is processImage in the customFields
   *  - Second priority is the value of the Portal Setting Portal.Processes.DefaultImage
   *  - Otherwise is the value of DefaultImage.PROCESSMODELING
   * @param process
   * @return the CMSUri of image object
   */
  default public String collectProcessImage(IWebStartable process) {
    var imageUri = DefaultImage.PROCESSMODELING.getPath();
    var defaultImageSetting = GlobalSettingService.getInstance()
        .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_PROCESS_IMAGE)
        .getDisplayValue().toUpperCase();
    if (!defaultImageSetting.equals(DefaultImage.DEFAULT.name())) {
      imageUri = DefaultImage.IMAGE_PATH + defaultImageSetting;
    }
    if (StringUtils.isNotBlank(getCustomFieldProcessImage(process))) {
      imageUri = getCustomFieldProcessImage(process);
    }
    return getContentImageUrl(imageUri);
  }
 
  /**
   * This method collect the index of process define by Custom Field name portalSortIndex
   * 
   * @param process
   * @return the value of index in process custom field
   */
  default public String getSortIndexInCustomField(IWebStartable process) {
    return process.customFields().value(CustomFields.SORT_INDEX);
  }

  /**
   * This method get the override template for process information defined by Custom Field name portalProcessInformation
   * 
   * @param process
   * @return the value of override template in process custom field
   */
  default public String getPortalProcessInformation(IWebStartable process) {
    return process.customFields().value(CustomFields.PROCESS_INFORMATION);
  }

}
