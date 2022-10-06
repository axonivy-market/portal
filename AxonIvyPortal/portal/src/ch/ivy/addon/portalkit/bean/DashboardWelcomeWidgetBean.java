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
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

@ViewScoped
@ManagedBean
public class DashboardWelcomeWidgetBean implements Serializable {

  private static final long serialVersionUID = 5372831531773612518L;

  private static final String BASE64_STRING_PATTERN = "data:%s;base64,%s";
  private static final String DEFAULT_TEXT_COLOR = "ffffff";
  private static final String DEFAULT_IMAGE_CMS_URI = "/images/WelcomeWidget/DefaultImage";
  private static final String DEFAULT_IMAGE_DARK_CMS_URI = "/images/WelcomeWidget/DefaultImageDark";
  private static final String IMAGE_DIRECTORY = "DashboardWelcomeWidget";
  private static final String DEFAULT_LOCALE_AND_DOT = "_en.";

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

  public String renderImage() {
    if (Optional.ofNullable(widget).map(WelcomeDashboardWidget::getImageLocation).isEmpty()) {
      return "";
    }
    migrateWelcomeWidget();
    byte[] fileContent = getWidgetImage().read().bytes();
    return String.format(BASE64_STRING_PATTERN, widget.getImageType(), Base64.getEncoder().encodeToString(fileContent));

  }

  /**
   * Silent migrate old welcome widget
   * 
   */
  private void migrateWelcomeWidget() {
    if (widget.getImageLocation().startsWith(IMAGE_DIRECTORY.concat("/").concat(widget.getId()))) {
      try {
        //Create new file in CMS and update image location
        File oldImage = new File(widget.getImageLocation());
        byte[] oldFileContent = FileUtils.readFileToByteArray(oldImage.getJavaFile());
        widget.setImageLocation(widget.getId().concat(DEFAULT_LOCALE_AND_DOT).concat(getImageType()));
        getWidgetImage().write().bytes(oldFileContent);

        // Remove old image file
        oldImage.getParentFile().forceDelete();
      } catch (IOException e) {
        Ivy.log().error(e);
      }
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
    
    widget.setWelcomeText(String.join(" ",
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

  public String getDefaultImageLink() {
    return DEFAULT_IMAGE_CMS_URI;
  }

  public String getDefaultImageDarkLink() {
    return DEFAULT_IMAGE_DARK_CMS_URI;
  }

  private ContentObjectValue getWidgetImage() {
    var app = IApplication.current();
    var cms = ContentManagement.cms(app);
    return cms.root()
      .child().folder(IMAGE_DIRECTORY).child()
      .file(getWidget().getImageLocation().substring(0, getWidget().getImageLocation().indexOf(DEFAULT_LOCALE_AND_DOT)), getImageType())
      .value().get("en");
  }

  private String getImageType() {
    return getWidget().getImageType().substring(getWidget().getImageType().indexOf("/") + 1);
  }
}
