package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CustomPortalConfigOverrideData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CustomPortalConfigOverrideData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -5287964911476934962L;

  private transient ch.ivy.addon.portal.generic.PortalConfig portalConfig;

  /**
   * Gets the field portalConfig.
   * @return the value of the field portalConfig; may be null.
   */
  public ch.ivy.addon.portal.generic.PortalConfig getPortalConfig()
  {
    return portalConfig;
  }

  /**
   * Sets the field portalConfig.
   * @param _portalConfig the new value of the field portalConfig.
   */
  public void setPortalConfig(ch.ivy.addon.portal.generic.PortalConfig _portalConfig)
  {
    portalConfig = _portalConfig;
  }

}
