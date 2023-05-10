package portalmigration.version112.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import portalmigration.constant.CustomFields;
import portalmigration.version112.enums.DefaultImage;
import portalmigration.version112.enums.ProcessType;

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
    var defaultImageSetting = DefaultImage.DEFAULT.name();
    if (!defaultImageSetting.equals(DefaultImage.DEFAULT.name())) {
      imageUri = DefaultImage.IMAGE_PATH + defaultImageSetting;
    }
    if (StringUtils.isNotBlank(getCustomFieldProcessImage(process))) {
      imageUri = getCustomFieldProcessImage(process);
    }
    return getContentImageUrl(imageUri);
  }
}
