package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.WelcomeImageFit;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.enums.WelcomeTextSize;
import ch.ivyteam.ivy.cm.ContentObject;

public class WelcomeDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 2688408047581268704L;

  private String imageLocation;
  private String imageType;

  private WelcomeTextPosition welcomeTextPosition;
  private WelcomeTextSize welcomeTextSize;
  private String welcomeTextColor;
  private List<DisplayName> welcomeTexts;
  private boolean isGreeting;
  private String welcomeTextStyleClass;
  private String imageStyleClass;
  private WelcomeImageFit welcomeImageFit;
  private String imageInlineStyle;
  private String imageContent;

  @JsonIgnore
  private String welcomeText;
  @JsonIgnore
  private ContentObject imageContentObject;

  @JsonIgnore
  public static WelcomeDashboardWidget buildDefaultWidget(String id, String name) {
    WelcomeDashboardWidget result = new WelcomeDashboardWidget();
    result.setId(id);
    result.setName(name);
    result.setLayout(new WidgetLayout());
    result.getLayout().setWidth(12);
    result.getLayout().setHeight(3);
    result.getLayout().setAxisX(0);
    result.getLayout().setAxisY(0);
    result.setWelcomeTextPosition(WelcomeTextPosition.TOP_LEFT);
    result.setWelcomeTextSize(WelcomeTextSize.HEADING_2);
    return result;
  }

  public String getImageLocation() {
    return imageLocation;
  }

  public void setImageLocation(String imageLocation) {
    this.imageLocation = imageLocation;
  }

  public String getWelcomeText() {
    return welcomeText;
  }

  public void setWelcomeText(String welcomeText) {
    this.welcomeText = welcomeText;
  }

  public List<DisplayName> getWelcomeTexts() {
    return welcomeTexts;
  }

  public void setWelcomeTexts(List<DisplayName> welcomeTexts) {
    this.welcomeTexts = welcomeTexts;
  }

  @Override
  public void resetWidgetFilters() { }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.WELCOME;
  }

  public WelcomeTextPosition getWelcomeTextPosition() {
    return welcomeTextPosition;
  }

  public void setWelcomeTextPosition(WelcomeTextPosition welcomeTextPosition) {
    this.welcomeTextPosition = welcomeTextPosition;
  }

  public String getWelcomeTextColor() {
    return welcomeTextColor;
  }

  public void setWelcomeTextColor(String welcomeTextColor) {
    this.welcomeTextColor = welcomeTextColor;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public WelcomeTextSize getWelcomeTextSize() {
    return welcomeTextSize;
  }

  public void setWelcomeTextSize(WelcomeTextSize welcomeTextSize) {
    this.welcomeTextSize = welcomeTextSize;
  }

  public boolean isGreeting() {
    return isGreeting;
  }

  public void setGreeting(boolean isGreeting) {
    this.isGreeting = isGreeting;
  }

  public String getWelcomeTextStyleClass() {
    return welcomeTextStyleClass;
  }

  public void setWelcomeTextStyleClass(String welcomeTextStyleClass) {
    this.welcomeTextStyleClass = welcomeTextStyleClass;
  }

  public String getImageStyleClass() {
    return imageStyleClass;
  }

  public void setImageStyleClass(String imageStyleClass) {
    this.imageStyleClass = imageStyleClass;
  }

  public ContentObject getImageContentObject() {
    return imageContentObject;
  }

  public void setImageContentObject(ContentObject imageContentObject) {
    this.imageContentObject = imageContentObject;
  }

  public WelcomeImageFit getWelcomeImageFit() {
    return welcomeImageFit;
  }

  public void setWelcomeImageFit(WelcomeImageFit welcomeImageFit) {
    this.welcomeImageFit = welcomeImageFit;
  }

  public String getImageInlineStyle() {
    return imageInlineStyle;
  }

  public void setImageInlineStyle(String imageInlineStyle) {
    this.imageInlineStyle = imageInlineStyle;
  }

  public String getImageContent() {
    return imageContent;
  }

  public void setImageContent(String imageContent) {
    this.imageContent = imageContent;
  }

}
