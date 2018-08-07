package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class NumberOfExpiryTasks", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class NumberOfExpiryTasks extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2181201241550596282L;

  private transient java.lang.Long numberOfExpiryTasks;

  /**
   * Gets the field numberOfExpiryTasks.
   * @return the value of the field numberOfExpiryTasks; may be null.
   */
  public java.lang.Long getNumberOfExpiryTasks()
  {
    return numberOfExpiryTasks;
  }

  /**
   * Sets the field numberOfExpiryTasks.
   * @param _numberOfExpiryTasks the new value of the field numberOfExpiryTasks.
   */
  public void setNumberOfExpiryTasks(java.lang.Long _numberOfExpiryTasks)
  {
    numberOfExpiryTasks = _numberOfExpiryTasks;
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
