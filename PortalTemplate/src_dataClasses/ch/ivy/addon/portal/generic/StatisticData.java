package ch.ivy.addon.portal.generic;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class StatisticData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class StatisticData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -933382643031087077L;

  private transient ch.ivy.ws.addon.PriorityStatistic priorityStatistic;

  /**
   * Gets the field priorityStatistic.
   * @return the value of the field priorityStatistic; may be null.
   */
  public ch.ivy.ws.addon.PriorityStatistic getPriorityStatistic()
  {
    return priorityStatistic;
  }

  /**
   * Sets the field priorityStatistic.
   * @param _priorityStatistic the new value of the field priorityStatistic.
   */
  public void setPriorityStatistic(ch.ivy.ws.addon.PriorityStatistic _priorityStatistic)
  {
    priorityStatistic = _priorityStatistic;
  }

  private transient ch.ivy.ws.addon.ExpiryStatistic expiryStatistic;

  /**
   * Gets the field expiryStatistic.
   * @return the value of the field expiryStatistic; may be null.
   */
  public ch.ivy.ws.addon.ExpiryStatistic getExpiryStatistic()
  {
    return expiryStatistic;
  }

  /**
   * Sets the field expiryStatistic.
   * @param _expiryStatistic the new value of the field expiryStatistic.
   */
  public void setExpiryStatistic(ch.ivy.ws.addon.ExpiryStatistic _expiryStatistic)
  {
    expiryStatistic = _expiryStatistic;
  }

}
