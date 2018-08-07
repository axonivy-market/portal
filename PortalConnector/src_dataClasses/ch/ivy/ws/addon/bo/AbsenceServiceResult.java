package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AbsenceServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AbsenceServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -452756294660224105L;

  private transient java.util.List<ch.ivy.ws.addon.types.Absence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.Absence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(java.util.List<ch.ivy.ws.addon.types.Absence> _absences)
  {
    absences = _absences;
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
