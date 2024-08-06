package com.axonivy.portal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.enums.ThemeMode;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CookieHelper;
import ch.ivyteam.ivy.jsf.primefaces.theme.IvyFreyaTheme;

@ManagedBean
@ViewScoped
public class ThemeBean implements Serializable {

  private static final long serialVersionUID = 7350665702475663264L;

  // This attribute is handled by Ivy team. Portal will load theme mode by this attribute's value.
  // If it does not exist, Portal will load default theme mode by setting.
  private final String PRIMEFACES_THEME_MODE_COOKIE_ATTRIBUTE = "primefaces-theme-mode";
  private final String IVY_FREYA_THEME_BEAN = "ivyFreyaTheme";
  private final String SWITCH_THEME_MODE_SCRIPT =
      "Portal.switchToThemeMode('%s');Portal.changePortalVariableTheme('%s');";
  private IvyFreyaTheme ivyFreyaTheme;

  @PostConstruct
  public void init() {
    String themeModeFromCookie = getThemeModeFromCookie();
    String themeMode = StringUtils.isNotBlank(themeModeFromCookie) ? themeModeFromCookie : getDefaultThemeMode();
    changeToThemeMode(themeMode.toLowerCase());
  }

  private String getThemeModeFromCookie() {
    Cookie cookie = new CookieHelper().getCookie(PRIMEFACES_THEME_MODE_COOKIE_ATTRIBUTE);
    return cookie != null ? cookie.getValue() : StringUtils.EMPTY;
  }

  private String getDefaultThemeMode() {
    return StringUtils.defaultIfBlank(
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DEFAULT_THEME_MODE),
        ThemeMode.LIGHT.toString());
  }

  private void changeToThemeMode(String themeMode) {
    getIvyFreyaTheme().setMode(themeMode);
    PrimeFaces.current().executeScript(String.format(SWITCH_THEME_MODE_SCRIPT, themeMode, themeMode));
  }

  public boolean isEnableSwitchThemeButton() {
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_SWITCH_THEME_BUTTON);
  }

  public void toggleTheme() {
    getIvyFreyaTheme().toggleTheme();
  }

  public String getTheme() {
    return getIvyFreyaTheme().getTheme();
  }

  public String getThemeMode() {
    return getIvyFreyaTheme().getMode();
  }

  private IvyFreyaTheme getIvyFreyaTheme() {
    if (ivyFreyaTheme == null) {
      ivyFreyaTheme = ManagedBeans.get(IVY_FREYA_THEME_BEAN);
    }
    return ivyFreyaTheme;
  }
}
