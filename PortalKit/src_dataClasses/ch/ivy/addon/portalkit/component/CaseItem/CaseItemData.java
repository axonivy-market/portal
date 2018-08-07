package ch.ivy.addon.portalkit.component.CaseItem;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 421714471018349090L;

  private ch.ivy.addon.portalkit.bo.RemoteCase remoteCase;

  /**
   * Gets the field remoteCase.
   * @return the value of the field remoteCase; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteCase getRemoteCase()
  {
    return remoteCase;
  }

  /**
   * Sets the field remoteCase.
   * @param _remoteCase the new value of the field remoteCase.
   */
  public void setRemoteCase(ch.ivy.addon.portalkit.bo.RemoteCase _remoteCase)
  {
    remoteCase = _remoteCase;
  }

  private ch.ivy.ws.addon.IvyCase ivyCase;

  /**
   * Gets the field ivyCase.
   * @return the value of the field ivyCase; may be null.
   */
  public ch.ivy.ws.addon.IvyCase getIvyCase()
  {
    return ivyCase;
  }

  /**
   * Sets the field ivyCase.
   * @param _ivyCase the new value of the field ivyCase.
   */
  public void setIvyCase(ch.ivy.ws.addon.IvyCase _ivyCase)
  {
    ivyCase = _ivyCase;
  }

}
