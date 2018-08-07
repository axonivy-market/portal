package ch.ivy.addon.portal.generic;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalConfig", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalConfig extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 5390376313026355728L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> customLinkList;

  /**
   * Gets the field customLinkList.
   * @return the value of the field customLinkList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> getCustomLinkList()
  {
    return customLinkList;
  }

  /**
   * Sets the field customLinkList.
   * @param _customLinkList the new value of the field customLinkList.
   */
  public void setCustomLinkList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> _customLinkList)
  {
    customLinkList = _customLinkList;
  }

  private transient java.lang.Boolean hideCreateNewMenuItem;

  /**
   * Gets the field hideCreateNewMenuItem.
   * @return the value of the field hideCreateNewMenuItem; may be null.
   */
  public java.lang.Boolean getHideCreateNewMenuItem()
  {
    return hideCreateNewMenuItem;
  }

  /**
   * Sets the field hideCreateNewMenuItem.
   * @param _hideCreateNewMenuItem the new value of the field hideCreateNewMenuItem.
   */
  public void setHideCreateNewMenuItem(java.lang.Boolean _hideCreateNewMenuItem)
  {
    hideCreateNewMenuItem = _hideCreateNewMenuItem;
  }

  private transient java.lang.Boolean hideShowAllCaseMenuItem;

  /**
   * Gets the field hideShowAllCaseMenuItem.
   * @return the value of the field hideShowAllCaseMenuItem; may be null.
   */
  public java.lang.Boolean getHideShowAllCaseMenuItem()
  {
    return hideShowAllCaseMenuItem;
  }

  /**
   * Sets the field hideShowAllCaseMenuItem.
   * @param _hideShowAllCaseMenuItem the new value of the field hideShowAllCaseMenuItem.
   */
  public void setHideShowAllCaseMenuItem(java.lang.Boolean _hideShowAllCaseMenuItem)
  {
    hideShowAllCaseMenuItem = _hideShowAllCaseMenuItem;
  }

  private transient java.lang.Boolean hideDashboardMenuItem;

  /**
   * Gets the field hideDashboardMenuItem.
   * @return the value of the field hideDashboardMenuItem; may be null.
   */
  public java.lang.Boolean getHideDashboardMenuItem()
  {
    return hideDashboardMenuItem;
  }

  /**
   * Sets the field hideDashboardMenuItem.
   * @param _hideDashboardMenuItem the new value of the field hideDashboardMenuItem.
   */
  public void setHideDashboardMenuItem(java.lang.Boolean _hideDashboardMenuItem)
  {
    hideDashboardMenuItem = _hideDashboardMenuItem;
  }

  private transient java.lang.Boolean hideShowTaskMenuItem;

  /**
   * Gets the field hideShowTaskMenuItem.
   * @return the value of the field hideShowTaskMenuItem; may be null.
   */
  public java.lang.Boolean getHideShowTaskMenuItem()
  {
    return hideShowTaskMenuItem;
  }

  /**
   * Sets the field hideShowTaskMenuItem.
   * @param _hideShowTaskMenuItem the new value of the field hideShowTaskMenuItem.
   */
  public void setHideShowTaskMenuItem(java.lang.Boolean _hideShowTaskMenuItem)
  {
    hideShowTaskMenuItem = _hideShowTaskMenuItem;
  }

  private transient java.lang.Boolean hideShowCaseMenuItem;

  /**
   * Gets the field hideShowCaseMenuItem.
   * @return the value of the field hideShowCaseMenuItem; may be null.
   */
  public java.lang.Boolean getHideShowCaseMenuItem()
  {
    return hideShowCaseMenuItem;
  }

  /**
   * Sets the field hideShowCaseMenuItem.
   * @param _hideShowCaseMenuItem the new value of the field hideShowCaseMenuItem.
   */
  public void setHideShowCaseMenuItem(java.lang.Boolean _hideShowCaseMenuItem)
  {
    hideShowCaseMenuItem = _hideShowCaseMenuItem;
  }

}
