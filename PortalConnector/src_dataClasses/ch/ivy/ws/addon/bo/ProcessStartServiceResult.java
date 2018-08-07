package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ProcessStartServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ProcessStartServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1737335309429198305L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyProcessStart> processStarts;

  /**
   * Gets the field processStarts.
   * @return the value of the field processStarts; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyProcessStart> getProcessStarts()
  {
    return processStarts;
  }

  /**
   * Sets the field processStarts.
   * @param _processStarts the new value of the field processStarts.
   */
  public void setProcessStarts(java.util.List<ch.ivy.ws.addon.types.IvyProcessStart> _processStarts)
  {
    processStarts = _processStarts;
  }

  private transient java.util.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

}
