package ch.ivy.addon.portal.generic.PortalHome;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalHomeData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalHomeData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1843418338965864047L;

  private org.primefaces.model.LazyDataModel dataModel;

  /**
   * Gets the field dataModel.
   * @return the value of the field dataModel; may be null.
   */
  public org.primefaces.model.LazyDataModel getDataModel()
  {
    return dataModel;
  }

  /**
   * Sets the field dataModel.
   * @param _dataModel the new value of the field dataModel.
   */
  public void setDataModel(org.primefaces.model.LazyDataModel _dataModel)
  {
    dataModel = _dataModel;
  }

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

}
