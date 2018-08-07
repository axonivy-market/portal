package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IvyAbsence", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IvyAbsence extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2239911300200100752L;

  private transient java.util.Date startDateInclusive;

  /**
   * Gets the field startDateInclusive.
   * @return the value of the field startDateInclusive; may be null.
   */
  public java.util.Date getStartDateInclusive()
  {
    return startDateInclusive;
  }

  /**
   * Sets the field startDateInclusive.
   * @param _startDateInclusive the new value of the field startDateInclusive.
   */
  public void setStartDateInclusive(java.util.Date _startDateInclusive)
  {
    startDateInclusive = _startDateInclusive;
  }

  private transient java.util.Date stopDateInclusive;

  /**
   * Gets the field stopDateInclusive.
   * @return the value of the field stopDateInclusive; may be null.
   */
  public java.util.Date getStopDateInclusive()
  {
    return stopDateInclusive;
  }

  /**
   * Sets the field stopDateInclusive.
   * @param _stopDateInclusive the new value of the field stopDateInclusive.
   */
  public void setStopDateInclusive(java.util.Date _stopDateInclusive)
  {
    stopDateInclusive = _stopDateInclusive;
  }

  private transient java.lang.String description;

  /**
   * Gets the field description.
   * @return the value of the field description; may be null.
   */
  public java.lang.String getDescription()
  {
    return description;
  }

  /**
   * Sets the field description.
   * @param _description the new value of the field description.
   */
  public void setDescription(java.lang.String _description)
  {
    description = _description;
  }

  private transient java.lang.String appName;

  /**
   * Gets the field appName.
   * @return the value of the field appName; may be null.
   */
  public java.lang.String getAppName()
  {
    return appName;
  }

  /**
   * Sets the field appName.
   * @param _appName the new value of the field appName.
   */
  public void setAppName(java.lang.String _appName)
  {
    appName = _appName;
  }

}
