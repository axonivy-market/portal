package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

@ManagedBean
@ViewScoped
public class IconSelectionBean implements Serializable {
  
  private static final String STREAMLINE_ICON_PREFIX = "si-";
  private static final String ICON_WITH_FONT_FAMILY_PATTERN = " %s %s ";
  private static final String AWESOME_FAMILY = "fa";
  private static final String STREAMLINE_FAMILY = "si";
  private static final String DEFAULT_ICON = "fa-play";
  private String icon;
  private String iconPattern;

  private static final long serialVersionUID = 5293980958176538592L;
  
  public void init(String cssIcon) {
    if (StringUtils.isBlank(cssIcon)) {
      icon = DEFAULT_ICON;
      iconPattern = String.format(ICON_WITH_FONT_FAMILY_PATTERN, AWESOME_FAMILY, "fa-2x");
      iconPattern = String.format(ICON_WITH_FONT_FAMILY_PATTERN, iconPattern, DEFAULT_ICON);
      return;
    }

    this.icon = cssIcon;
    if (isStreamlineIcons(cssIcon)) {
      this.iconPattern = String.format(ICON_WITH_FONT_FAMILY_PATTERN, STREAMLINE_FAMILY, "si-xl");
    } else {
      this.iconPattern = String.format(ICON_WITH_FONT_FAMILY_PATTERN, AWESOME_FAMILY, "fa-2x");
    }
  }

  public boolean isStreamlineIcons(String cssIcon) {
    if (StringUtils.isBlank(cssIcon)) {
      return false;
    }
    return StringUtils.contains(cssIcon, STREAMLINE_ICON_PREFIX);
  }

  public String generateIconWithFontFamily(String cssIcon) {
    String iconWithFontFamily = String.format(ICON_WITH_FONT_FAMILY_PATTERN, AWESOME_FAMILY, DEFAULT_ICON);
    if (StringUtils.isBlank(cssIcon)) {
      return iconWithFontFamily;
    }

    iconWithFontFamily = String.format(ICON_WITH_FONT_FAMILY_PATTERN, AWESOME_FAMILY, cssIcon);
    if (isStreamlineIcons(cssIcon)) {
      iconWithFontFamily = String.format(ICON_WITH_FONT_FAMILY_PATTERN, STREAMLINE_FAMILY, cssIcon);
    }
    return iconWithFontFamily;
  }
  
  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIconPattern() {
    return iconPattern;
  }

  public void setIconPattern(String iconPattern) {
    this.iconPattern = iconPattern;
  }

}
