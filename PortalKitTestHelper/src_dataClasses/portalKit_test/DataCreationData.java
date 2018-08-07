package portalKit_test;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class DataCreationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class DataCreationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -1440887841747199404L;

  /**
   * contain information of category, sub category of tasks. E.g. CAT1/SUBCAT1
   */
  private transient java.lang.String taskStructureInfo;

  /**
   * Gets the field taskStructureInfo.
   * @return the value of the field taskStructureInfo; may be null.
   */
  public java.lang.String getTaskStructureInfo()
  {
    return taskStructureInfo;
  }

  /**
   * Sets the field taskStructureInfo.
   * @param _taskStructureInfo the new value of the field taskStructureInfo.
   */
  public void setTaskStructureInfo(java.lang.String _taskStructureInfo)
  {
    taskStructureInfo = _taskStructureInfo;
  }

  private transient java.lang.Integer numOfCats;

  /**
   * Gets the field numOfCats.
   * @return the value of the field numOfCats; may be null.
   */
  public java.lang.Integer getNumOfCats()
  {
    return numOfCats;
  }

  /**
   * Sets the field numOfCats.
   * @param _numOfCats the new value of the field numOfCats.
   */
  public void setNumOfCats(java.lang.Integer _numOfCats)
  {
    numOfCats = _numOfCats;
  }

  private transient java.lang.Integer numOfSubCats;

  /**
   * Gets the field numOfSubCats.
   * @return the value of the field numOfSubCats; may be null.
   */
  public java.lang.Integer getNumOfSubCats()
  {
    return numOfSubCats;
  }

  /**
   * Sets the field numOfSubCats.
   * @param _numOfSubCats the new value of the field numOfSubCats.
   */
  public void setNumOfSubCats(java.lang.Integer _numOfSubCats)
  {
    numOfSubCats = _numOfSubCats;
  }

  private transient java.lang.Integer numOfCasesPerSubCat;

  /**
   * Gets the field numOfCasesPerSubCat.
   * @return the value of the field numOfCasesPerSubCat; may be null.
   */
  public java.lang.Integer getNumOfCasesPerSubCat()
  {
    return numOfCasesPerSubCat;
  }

  /**
   * Sets the field numOfCasesPerSubCat.
   * @param _numOfCasesPerSubCat the new value of the field numOfCasesPerSubCat.
   */
  public void setNumOfCasesPerSubCat(java.lang.Integer _numOfCasesPerSubCat)
  {
    numOfCasesPerSubCat = _numOfCasesPerSubCat;
  }

  private transient java.lang.String caseName;

  /**
   * Gets the field caseName.
   * @return the value of the field caseName; may be null.
   */
  public java.lang.String getCaseName()
  {
    return caseName;
  }

  /**
   * Sets the field caseName.
   * @param _caseName the new value of the field caseName.
   */
  public void setCaseName(java.lang.String _caseName)
  {
    caseName = _caseName;
  }

  private ch.ivy.addon.portalkit.test.util.DataCreationHandler handler;

  /**
   * Gets the field handler.
   * @return the value of the field handler; may be null.
   */
  public ch.ivy.addon.portalkit.test.util.DataCreationHandler getHandler()
  {
    return handler;
  }

  /**
   * Sets the field handler.
   * @param _handler the new value of the field handler.
   */
  public void setHandler(ch.ivy.addon.portalkit.test.util.DataCreationHandler _handler)
  {
    handler = _handler;
  }

}
