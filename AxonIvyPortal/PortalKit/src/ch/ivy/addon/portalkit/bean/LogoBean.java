package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class LogoBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String CORPORATE_LOGO_CMS = "/images/logo/CorporateLogo";
  private static final String SMALL_CORPORATE_LOGO_CMS = "/images/logo/SmallCorporateLogo";
  private static final String LOGIN_LOGO_CMS = "/images/logo/loginLogo";
  private static final String FAVICON_LOGO_CMS = "/images/logo/faviconLogo";

  public boolean hasCorporateLogoImage() {
    return checkDefaultContentExist(CORPORATE_LOGO_CMS);
  }

  public boolean hasSmallCorporateLogoImage() {
    return checkDefaultContentExist(SMALL_CORPORATE_LOGO_CMS);
  }

  public boolean hasLoginLogoImage() {
    return checkDefaultContentExist(LOGIN_LOGO_CMS);
  }

  public boolean hasFaviconLogoImage() {
    return checkDefaultContentExist(FAVICON_LOGO_CMS);
  }

  private boolean checkDefaultContentExist(String cmsUri) {
    return Ivy.cms().findContentObjectValue(cmsUri, null).getContentAsBinaryStream() != null;
  }
}
