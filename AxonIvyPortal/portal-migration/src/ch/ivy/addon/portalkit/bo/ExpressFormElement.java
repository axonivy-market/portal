package ch.ivy.addon.portalkit.bo;

import java.util.List;

public class ExpressFormElement {

  private String id;
  private String processID;
  private String elementID;
  private int taskPosition;
  private String name;
  private String label;
  private boolean required;
  private int intSetting;
  private String elementType;
  private List<String> optionStrs;
  private String elementPosition;
  private int indexInPanel;
  private int counter;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProcessID() {
    return processID;
  }

  public void setProcessID(String processID) {
    this.processID = processID;
  }

  public String getElementID() {
    return elementID;
  }

  public void setElementID(String elementID) {
    this.elementID = elementID;
  }

  public int getTaskPosition() {
    return taskPosition;
  }

  public void setTaskPosition(int taskPosition) {
    this.taskPosition = taskPosition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public int getIntSetting() {
    return intSetting;
  }

  public void setIntSetting(int intSetting) {
    this.intSetting = intSetting;
  }

  public String getElementType() {
    return elementType;
  }

  public void setElementType(String elementType) {
    this.elementType = elementType;
  }
  
  public String getElementPosition() {
    return elementPosition;
  }

  public void setElementPosition(String elementPosition) {
    this.elementPosition = elementPosition;
  }

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

  public List<String> getOptionStrs() {
    return optionStrs;
  }

  public void setOptionStrs(List<String> optionStrs) {
    this.optionStrs = optionStrs;
  }

  public int getIndexInPanel() {
    return indexInPanel;
  }

  public void setIndexInPanel(int indexInPanel) {
    this.indexInPanel = indexInPanel;
  }
}
