package ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ApplicationSelectionMenuData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ApplicationSelectionMenuData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3844029840093470660L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> _applications)
  {
    applications = _applications;
  }

  private java.lang.Boolean isNotRequiredLogin;

  /**
   * Gets the field isNotRequiredLogin.
   * @return the value of the field isNotRequiredLogin; may be null.
   */
  public java.lang.Boolean getIsNotRequiredLogin()
  {
    return isNotRequiredLogin;
  }

  /**
   * Sets the field isNotRequiredLogin.
   * @param _isNotRequiredLogin the new value of the field isNotRequiredLogin.
   */
  public void setIsNotRequiredLogin(java.lang.Boolean _isNotRequiredLogin)
  {
    isNotRequiredLogin = _isNotRequiredLogin;
  }

  private java.util.List<org.primefaces.component.button.Button> menuItems;

  /**
   * Gets the field menuItems.
   * @return the value of the field menuItems; may be null.
   */
  public java.util.List<org.primefaces.component.button.Button> getMenuItems()
  {
    return menuItems;
  }

  /**
   * Sets the field menuItems.
   * @param _menuItems the new value of the field menuItems.
   */
  public void setMenuItems(java.util.List<org.primefaces.component.button.Button> _menuItems)
  {
    menuItems = _menuItems;
  }

  private java.lang.String applicationUrl;

  /**
   * Gets the field applicationUrl.
   * @return the value of the field applicationUrl; may be null.
   */
  public java.lang.String getApplicationUrl()
  {
    return applicationUrl;
  }

  /**
   * Sets the field applicationUrl.
   * @param _applicationUrl the new value of the field applicationUrl.
   */
  public void setApplicationUrl(java.lang.String _applicationUrl)
  {
    applicationUrl = _applicationUrl;
  }

}
