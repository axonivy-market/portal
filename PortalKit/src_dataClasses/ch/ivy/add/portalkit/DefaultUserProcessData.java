package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class DefaultUserProcessData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class DefaultUserProcessData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3820963752005148911L;

  private transient java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> defaultUserProcesses;

  /**
   * Gets the field defaultUserProcesses.
   * @return the value of the field defaultUserProcesses; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> getDefaultUserProcesses()
  {
    return defaultUserProcesses;
  }

  /**
   * Sets the field defaultUserProcesses.
   * @param _defaultUserProcesses the new value of the field defaultUserProcesses.
   */
  public void setDefaultUserProcesses(java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> _defaultUserProcesses)
  {
    defaultUserProcesses = _defaultUserProcesses;
  }

}
