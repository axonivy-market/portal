package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Absence", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class Absence extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3605929764031327731L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyAbsence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyAbsence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(java.util.List<ch.ivy.ws.addon.types.IvyAbsence> _absences)
  {
    absences = _absences;
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

  private transient java.lang.String userFullName;

  /**
   * Gets the field userFullName.
   * @return the value of the field userFullName; may be null.
   */
  public java.lang.String getUserFullName()
  {
    return userFullName;
  }

  /**
   * Sets the field userFullName.
   * @param _userFullName the new value of the field userFullName.
   */
  public void setUserFullName(java.lang.String _userFullName)
  {
    userFullName = _userFullName;
  }

}
