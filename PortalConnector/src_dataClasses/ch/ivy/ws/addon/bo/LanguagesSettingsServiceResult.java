package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class LanguagesSettingsServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class LanguagesSettingsServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2124726715245464990L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> settings;

  /**
   * Gets the field settings.
   * @return the value of the field settings; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> getSettings()
  {
    return settings;
  }

  /**
   * Sets the field settings.
   * @param _settings the new value of the field settings.
   */
  public void setSettings(java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> _settings)
  {
    settings = _settings;
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
