package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.WelcomeTextPosition;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetBean implements Serializable {

  private static final String BASE64_STRING_PATTERN = "data:%s;base64,%s";
  private static final String DEFAULT_TEXT_COLOR = "000000";

  private WelcomeDashboardWidget widget;

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
  }

  public String renderImage(WelcomeDashboardWidget widget) {
    if (Optional.ofNullable(widget).map(WelcomeDashboardWidget::getUploadedImageFile).isEmpty()) {
      return "";
    }

      try {
        byte[] fileContent = FileUtils.readFileToByteArray(widget.getUploadedImageFile().getJavaFile());
        return String.format(BASE64_STRING_PATTERN, widget.getImageType(), Base64.getEncoder().encodeToString(fileContent));
      } catch (IOException e) {
        Ivy.log().error(e);
        return "";
      }
  }

  public void updateWelcomeText() {
    int parsedClientTime = 0;
    String clientTime = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clientTime");
    if (clientTime != null) {
      parsedClientTime = Integer.parseInt(clientTime);
    }

    String greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Afternoon";
    if (parsedClientTime < 12) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Morning";
    } else if (parsedClientTime > 18) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Evening";
    }
    
    widget.setWelcomeText(String.join(", ",
        Ivy.cms().coLocale(greetingTextCms, Ivy.session().getContentLocale()),
        Ivy.session().getSessionUser().getDisplayName(), widget.getWelcomeText()));
  }

  private static boolean equalsLanguageLocale(DisplayName displayName, String language) {
    return StringUtils.equalsIgnoreCase(displayName.getLocale().toString(), language);
  }

  public WelcomeDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(WelcomeDashboardWidget widget) {
    this.widget = widget;
  }
}
