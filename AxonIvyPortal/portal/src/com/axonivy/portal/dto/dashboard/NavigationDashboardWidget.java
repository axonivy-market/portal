package com.axonivy.portal.dto.dashboard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.VisualType;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import io.github.axonivy.json.schema.annotations.Examples;

public class NavigationDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = -565012312312361L;

  private String targetDashboardId;
  @JsonIgnore
  private String targetDashboardName;
  private String description;
  private String icon;
  @JsonIgnore
  private String buttonName;
  private List<DisplayName> buttonNames;
  private VisualType visualType;

  private String imageLocation;
  private String imageType;

  private String imageLocationDarkMode;
  private String imageTypeDarkMode;

  private String imageContent;
  private String imageContentDarkMode;

  @Override
  public void resetWidgetFilters() {}

  @Override
  public void cancelUserFilter() {}

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.NAVIGATION_DASHBOARD;
  }

  public NavigationDashboardWidget() {}

  public static NavigationDashboardWidget buildDefaultWidget(String widgetId, String buttonName) {
    var widget = new NavigationDashboardWidget();
    widget.setId(widgetId);
    widget.setButtonName(buttonName);
    widget.setVisualType(VisualType.ICON);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(3);
    widget.getLayout().setHeight(3);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    return widget;
  }

  public void setTargetDashboardId(String targetDashboardId) {
    this.targetDashboardId = targetDashboardId;
  }

  @JsonPropertyDescription("ID of the dashboard to navigate to when clicked.")
  public String getTargetDashboardId() {
    return this.targetDashboardId;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTargetDashboardName() {
    return this.targetDashboardName;
  }

  public void setTargetDashboardName(String targetDashboardName) {
    this.targetDashboardName = targetDashboardName;
  }

  public String getIcon() {
    return this.icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getButtonName() {
    return LanguageUtils.getLocalizedName(buttonNames);
  }

  public void setButtonName(String buttonName) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(buttonNames, buttonName);
    this.buttonNames = nameResult.names();
    this.buttonName = nameResult.name();
  }

  @JsonPropertyDescription("Multilingual button labels.")
  public List<DisplayName> getButtonNames() {
    return this.buttonNames;
  }

  public void setButtonNames(List<DisplayName> buttonNames) {
    this.buttonNames = buttonNames;
  }

  public VisualType getVisualType() {
    return visualType;
  }

  public void setVisualType(VisualType visualType) {
    this.visualType = visualType;
  }

  public String getImageLocation() {
    return imageLocation;
  }

  public void setImageLocation(String imageLocation) {
    this.imageLocation = imageLocation;
  }

  @Examples({"jpeg", "png"})
  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public String getImageLocationDarkMode() {
    return imageLocationDarkMode;
  }

  public void setImageLocationDarkMode(String imageLocationDarkMode) {
    this.imageLocationDarkMode = imageLocationDarkMode;
  }

  public String getImageTypeDarkMode() {
    return imageTypeDarkMode;
  }

  public void setImageTypeDarkMode(String imageTypeDarkMode) {
    this.imageTypeDarkMode = imageTypeDarkMode;
  }

  @JsonPropertyDescription("Base64-encoded light-mode image.")
  public String getImageContent() {
    return imageContent;
  }

  public void setImageContent(String imageContent) {
    this.imageContent = imageContent;
  }

  @JsonPropertyDescription("Base64-encoded dark-mode image.")
  public String getImageContentDarkMode() {
    return imageContentDarkMode;
  }

  public void setImageContentDarkMode(String imageContentDarkMode) {
    this.imageContentDarkMode = imageContentDarkMode;
  }
}
