package ch.ivy.addon.portalkit.bean;

import static org.apache.commons.lang3.StringUtils.SPACE;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetBean implements Serializable {

  private static final long serialVersionUID = 5372831531773612518L;

  protected static final String DEFAULT_TEXT_COLOR = "ffffff";
  protected static final String DEFAULT_IMAGE_CMS_URI = "/images/WelcomeWidget/DefaultImage";
  protected static final String DEFAULT_IMAGE_DARK_CMS_URI = DEFAULT_IMAGE_CMS_URI + "Dark";

  protected WelcomeDashboardWidget widget;

  public void init() {
    widget = Attrs.currentContext().getAttribute("#{cc.attrs.widget}", WelcomeDashboardWidget.class);

    if (widget.getWelcomeTextColor() == null) {
      widget.setWelcomeTextColor(DEFAULT_TEXT_COLOR);
    }

    if (widget.getWelcomeTextPosition() == null) {
      widget.setWelcomeTextPosition(WelcomeTextPosition.BOTTOM_LEFT);
    }

    if (!CollectionUtils.isEmpty(widget.getWelcomeTexts())) {
      String userLanguage = UserUtils.getUserLanguage();
      widget.setWelcomeText(CollectionUtils.emptyIfNull(widget.getWelcomeTexts()).stream()
          .filter(name -> equalsLanguageLocale(name, userLanguage))
          .findFirst().orElse(new DisplayName()).getValue());
    }
    widget.setImageContentObject(renderImage());
  }

  public ContentObject renderImage() {
    if (Optional.ofNullable(widget).map(WelcomeDashboardWidget::getImageLocation).isEmpty()) {
      return null;
    }
    if (WelcomeWidgetUtils.isObsoleteImageData(widget.getImageLocation(), widget.getId())) {
      WelcomeWidgetUtils.migrateWelcomeWidget(widget.getId(), widget.getImageType(), widget.getImageLocation());
    }
    return WelcomeWidgetUtils.getImageContentObject(widget.getImageLocation(), widget.getImageType());
  }

  public void updateWelcomeText(WelcomeDashboardWidget welcomeWidget) {
    int parseClientTime = WelcomeWidgetUtils.parseClientTime();
    String greetingTextCms = WelcomeWidgetUtils.generateGreetingTextByTime(parseClientTime);
    var originWelcomeText = welcomeWidget.getWelcomeText();
    if (StringUtils.isNoneBlank(originWelcomeText) && (StringUtils.startsWith(originWelcomeText.trim(), ",") || StringUtils.startsWith(originWelcomeText.trim(), "."))) {
      var newWelcomeText = String.join(SPACE,
          Ivy.cms().coLocale(greetingTextCms, Ivy.session().getContentLocale()),
          Ivy.session().getSessionUser().getDisplayName());
      welcomeWidget.setWelcomeText(newWelcomeText.concat(welcomeWidget.getWelcomeText()));
      return;
    }

    welcomeWidget.setWelcomeText(String.join(SPACE,
          Ivy.cms().coLocale(greetingTextCms, Ivy.session().getContentLocale()),
          Ivy.session().getSessionUser().getDisplayName(),
          welcomeWidget.getWelcomeText()));
  }

  protected static boolean equalsLanguageLocale(DisplayName displayName, String language) {
    return StringUtils.equalsIgnoreCase(displayName.getLocale().toString(), language);
  }

  public WelcomeDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(WelcomeDashboardWidget widget) {
    this.widget = widget;
  }

  public String getDefaultImageLink() {
    return DEFAULT_IMAGE_CMS_URI;
  }

  public String getDefaultImageDarkLink() {
    return DEFAULT_IMAGE_DARK_CMS_URI;
  }
  
  public String sanitizeHTML(String text) {
    return StringUtils.isBlank(text) ? "" : HtmlParser.sanitizeHTML(text);
  }
}
