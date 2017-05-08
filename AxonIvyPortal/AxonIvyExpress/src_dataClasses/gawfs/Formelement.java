package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Formelement", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
@javax.persistence.Entity
@javax.persistence.Table(name="Formelements")
public class Formelement extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2722938391294134259L;

  /**
   * Identifier
   */
  @javax.persistence.Id
  @javax.persistence.GeneratedValue
  private java.lang.Integer id;

  /**
   * Gets the field id.
   * @return the value of the field id; may be null.
   */
  public java.lang.Integer getId()
  {
    return id;
  }

  /**
   * Sets the field id.
   * @param _id the new value of the field id.
   */
  public void setId(java.lang.Integer _id)
  {
    id = _id;
  }

  private java.lang.Integer processID;

  /**
   * Gets the field processID.
   * @return the value of the field processID; may be null.
   */
  public java.lang.Integer getProcessID()
  {
    return processID;
  }

  /**
   * Sets the field processID.
   * @param _processID the new value of the field processID.
   */
  public void setProcessID(java.lang.Integer _processID)
  {
    processID = _processID;
  }

  private java.lang.String elementID;

  /**
   * Gets the field elementID.
   * @return the value of the field elementID; may be null.
   */
  public java.lang.String getElementID()
  {
    return elementID;
  }

  /**
   * Sets the field elementID.
   * @param _elementID the new value of the field elementID.
   */
  public void setElementID(java.lang.String _elementID)
  {
    elementID = _elementID;
  }

  private java.lang.String name;

  /**
   * Gets the field name.
   * @return the value of the field name; may be null.
   */
  public java.lang.String getName()
  {
    return name;
  }

  /**
   * Sets the field name.
   * @param _name the new value of the field name.
   */
  public void setName(java.lang.String _name)
  {
    name = _name;
  }

  private java.lang.String label;

  /**
   * Gets the field label.
   * @return the value of the field label; may be null.
   */
  public java.lang.String getLabel()
  {
    return label;
  }

  /**
   * Sets the field label.
   * @param _label the new value of the field label.
   */
  public void setLabel(java.lang.String _label)
  {
    label = _label;
  }

  private java.lang.Boolean required;

  /**
   * Gets the field required.
   * @return the value of the field required; may be null.
   */
  public java.lang.Boolean getRequired()
  {
    return required;
  }

  /**
   * Sets the field required.
   * @param _required the new value of the field required.
   */
  public void setRequired(java.lang.Boolean _required)
  {
    required = _required;
  }

  private java.lang.Integer intSetting;

  /**
   * Gets the field intSetting.
   * @return the value of the field intSetting; may be null.
   */
  public java.lang.Integer getIntSetting()
  {
    return intSetting;
  }

  /**
   * Sets the field intSetting.
   * @param _intSetting the new value of the field intSetting.
   */
  public void setIntSetting(java.lang.Integer _intSetting)
  {
    intSetting = _intSetting;
  }

  private java.lang.String elementType;

  /**
   * Gets the field elementType.
   * @return the value of the field elementType; may be null.
   */
  public java.lang.String getElementType()
  {
    return elementType;
  }

  /**
   * Sets the field elementType.
   * @param _elementType the new value of the field elementType.
   */
  public void setElementType(java.lang.String _elementType)
  {
    elementType = _elementType;
  }

  private java.lang.String optionsStr;

  /**
   * Gets the field optionsStr.
   * @return the value of the field optionsStr; may be null.
   */
  public java.lang.String getOptionsStr()
  {
    return optionsStr;
  }

  /**
   * Sets the field optionsStr.
   * @param _optionsStr the new value of the field optionsStr.
   */
  public void setOptionsStr(java.lang.String _optionsStr)
  {
    optionsStr = _optionsStr;
  }

  private java.lang.String elementPosition;

  /**
   * Gets the field elementPosition.
   * @return the value of the field elementPosition; may be null.
   */
  public java.lang.String getElementPosition()
  {
    return elementPosition;
  }

  /**
   * Sets the field elementPosition.
   * @param _elementPosition the new value of the field elementPosition.
   */
  public void setElementPosition(java.lang.String _elementPosition)
  {
    elementPosition = _elementPosition;
  }

  private java.lang.Integer counter;

  /**
   * Gets the field counter.
   * @return the value of the field counter; may be null.
   */
  public java.lang.Integer getCounter()
  {
    return counter;
  }

  /**
   * Sets the field counter.
   * @param _counter the new value of the field counter.
   */
  public void setCounter(java.lang.Integer _counter)
  {
    counter = _counter;
  }

}
