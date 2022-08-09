package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressFormElement implements Serializable {

  private static final long serialVersionUID = -1048666544578490353L;
  private String elementID;
  private String name;
  private String label;
  private boolean required;
  private int intSetting;
  private String elementType;
  private List<String> optionStrs;
  private String elementPosition;
  private int indexInPanel;

  public String getElementID() {
    return elementID;
  }

  public void setElementID(String elementID) {
    this.elementID = elementID;
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
