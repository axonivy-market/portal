package ch.ivy.addon.portalkit.bo;

public class FormElement {
	
  private String id;
  private String processID;
	private String elementID;
	private String name;
	private String label;
	private boolean required;
	private int intSetting;
	private String elementType;
	private String optionsStr;
	private String elementPosition;
	private int counter;
	/**
	 * @return the id
	 */
  public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
  public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the processID
	 */
  public String getProcessID() {
		return processID;
	}
	/**
	 * @param processID the processID to set
	 */
  public void setProcessID(String processID) {
		this.processID = processID;
	}
	/**
	 * @return the elementID
	 */
	public String getElementID() {
		return elementID;
	}
	/**
	 * @param elementID the elementID to set
	 */
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
	/**
	 * @return the intSetting
	 */
	public int getIntSetting() {
		return intSetting;
	}
	/**
	 * @param intSetting the intSetting to set
	 */
	public void setIntSetting(int intSetting) {
		this.intSetting = intSetting;
	}
	/**
	 * @return the elementType
	 */
	public String getElementType() {
		return elementType;
	}
	/**
	 * @param elementType the elementType to set
	 */
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	/**
	 * @return the optionsStr
	 */
	public String getOptionsStr() {
		return optionsStr;
	}
	/**
	 * @param optionsStr the optionsStr to set
	 */
	public void setOptionsStr(String optionsStr) {
		this.optionsStr = optionsStr;
	}
	/**
	 * @return the elementPosition
	 */
	public String getElementPosition() {
		return elementPosition;
	}
	/**
	 * @param elementPosition the elementPosition to set
	 */
	public void setElementPosition(String elementPosition) {
		this.elementPosition = elementPosition;
	}
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

}
