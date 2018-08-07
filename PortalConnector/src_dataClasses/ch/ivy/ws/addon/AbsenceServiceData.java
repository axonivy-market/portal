package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AbsenceServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AbsenceServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4394929530114738965L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.Absence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.Absence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.Absence> _absences)
  {
    absences = _absences;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> absencesToUpdate;

  /**
   * Gets the field absencesToUpdate.
   * @return the value of the field absencesToUpdate; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> getAbsencesToUpdate()
  {
    return absencesToUpdate;
  }

  /**
   * Sets the field absencesToUpdate.
   * @param _absencesToUpdate the new value of the field absencesToUpdate.
   */
  public void setAbsencesToUpdate(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> _absencesToUpdate)
  {
    absencesToUpdate = _absencesToUpdate;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applicationNames;

  /**
   * Gets the field applicationNames.
   * @return the value of the field applicationNames; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplicationNames()
  {
    return applicationNames;
  }

  /**
   * Sets the field applicationNames.
   * @param _applicationNames the new value of the field applicationNames.
   */
  public void setApplicationNames(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applicationNames)
  {
    applicationNames = _applicationNames;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient java.lang.String userName;

  /**
   * Gets the field userName.
   * @return the value of the field userName; may be null.
   */
  public java.lang.String getUserName()
  {
    return userName;
  }

  /**
   * Sets the field userName.
   * @param _userName the new value of the field userName.
   */
  public void setUserName(java.lang.String _userName)
  {
    userName = _userName;
  }

}
