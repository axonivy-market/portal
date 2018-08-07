package ch.ivy.addon.portal.generic.admin.PortalDashBoard;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalDashBoardData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalDashBoardData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7050554291980311464L;

  private ch.ivy.addon.portal.generic.StatisticData statistic;

  /**
   * Gets the field statistic.
   * @return the value of the field statistic; may be null.
   */
  public ch.ivy.addon.portal.generic.StatisticData getStatistic()
  {
    return statistic;
  }

  /**
   * Sets the field statistic.
   * @param _statistic the new value of the field statistic.
   */
  public void setStatistic(ch.ivy.addon.portal.generic.StatisticData _statistic)
  {
    statistic = _statistic;
  }

  private java.lang.Long serverId;

  /**
   * Gets the field serverId.
   * @return the value of the field serverId; may be null.
   */
  public java.lang.Long getServerId()
  {
    return serverId;
  }

  /**
   * Sets the field serverId.
   * @param _serverId the new value of the field serverId.
   */
  public void setServerId(java.lang.Long _serverId)
  {
    serverId = _serverId;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> involvedApplications;

  /**
   * Gets the field involvedApplications.
   * @return the value of the field involvedApplications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getInvolvedApplications()
  {
    return involvedApplications;
  }

  /**
   * Sets the field involvedApplications.
   * @param _involvedApplications the new value of the field involvedApplications.
   */
  public void setInvolvedApplications(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _involvedApplications)
  {
    involvedApplications = _involvedApplications;
  }

}
