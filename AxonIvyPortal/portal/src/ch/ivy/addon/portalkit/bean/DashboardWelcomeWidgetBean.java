package ch.ivy.addon.portalkit.bean;

import static org.apache.commons.lang3.StringUtils.SPACE;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.addon.portal.generic.menu.MenuView;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardUtils;
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
  protected static final String REGEX_REPLACE_SPECIAL_CHARACTER = "[^\\p{Alpha}\\p{Digit}_]+";


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
    if (StringUtils.isNotBlank(widget.getId())) {
      String idWithoutSpecialChar = widget.getId().replaceAll(REGEX_REPLACE_SPECIAL_CHARACTER,"_");
      widget.setInternalId(idWithoutSpecialChar);
    }
  }

  public ContentObject renderImage() {
    if (Optional.ofNullable(widget).map(WelcomeDashboardWidget::getImageLocation).isEmpty()) {
      return null;
    }
    if (WelcomeWidgetUtils.isObsoleteImageData(widget.getImageLocation(), widget.getId())) {
      WelcomeWidgetUtils.migrateWelcomeWidget(widget.getId(), widget.getImageType(), widget.getImageLocation());
    }
    ContentObject imageContent = WelcomeWidgetUtils.getImageContentObject(widget.getImageLocation(), widget.getImageType());
    removeImageContentOfWidget(imageContent);
    
    return imageContent;
  }

  private void removeImageContentOfWidget(ContentObject imageContent) {
    if (StringUtils.isNotBlank(widget.getImageContent())) {
      WelcomeWidgetUtils.readObjectValueOfDefaultLocale(imageContent).write().bytes(Base64.getDecoder().decode(widget.getImageContent()));
      List<Dashboard> dashboards = DashboardUtils.collectDashboards();
      for (Dashboard dashboard :  dashboards) {
        dashboard.getWidgets().stream()
        .filter(item -> widget.getId().equals(item.getId()) && item.getType() == DashboardWidgetType.WELCOME)
        .findFirst()
        .ifPresent(item -> {
          ((WelcomeDashboardWidget) item).setImageContent(null);
        });
      }
      String dashboardJson = BusinessEntityConverter.entityToJsonValue(dashboards);
      Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardJson);
      MenuView menuView = (MenuView) ManagedBeans.get("menuView");
      menuView.updateDashboardCache(DashboardUtils.collectDashboards());
    }
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
