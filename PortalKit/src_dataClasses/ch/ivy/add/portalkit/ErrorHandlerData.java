package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ErrorHandlerData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ErrorHandlerData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 7893125701925880320L;

  private transient java.util.List<ch.ivy.ws.addon.WsException> wsExceptions;

  /**
   * Gets the field wsExceptions.
   * @return the value of the field wsExceptions; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WsException> getWsExceptions()
  {
    return wsExceptions;
  }

  /**
   * Sets the field wsExceptions.
   * @param _wsExceptions the new value of the field wsExceptions.
   */
  public void setWsExceptions(java.util.List<ch.ivy.ws.addon.WsException> _wsExceptions)
  {
    wsExceptions = _wsExceptions;
  }

}
