package ch.ivy.gawfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import ch.ivy.gawfs.enums.FormElementType;

@ManagedBean(name = "Formelement")
@ViewScoped
public class Formelement implements Serializable {

  private static final String COLON = ":";

  private static final long serialVersionUID = -4960948147758580124L;

  private String id; // automatic Id
  private String label; // FormLabel
  private Boolean required; // Is the Field a required field?
  private Integer intSetting; // setting like number of rows or number of files
  private List<FormelementOption> options; // list of options for ManyCheckbox or OneMenu, but also allowed File-Formats
  private FormElementType type; // Form element type
  private List<String> optionsStr; // String List representation of options
  private int taskPosition; // Position of the task which this element belonged to
  private int indexInPanel; //Index of element in panel (Header, Footer, Left or Right)
  private Object value; // Value of the Formelement, later Userinput

  public Formelement() {
    options = new ArrayList<>();
    optionsStr = new ArrayList<>();
  }

  public Formelement(String id) {
    options = new ArrayList<>();
    optionsStr = new ArrayList<>();
    this.id = id;
  }

  public List<String> getOptionsStr() {
    this.optionsStr.clear();
    if (type == FormElementType.CHECKBOX && options != null
        && options.size() == 2
        && "portalExpressDataProvider()".equals(options.get(1).getValue())) {
      optionsStr = executeDataProvider(options.stream().map(FormelementOption::getValue).collect(Collectors.toList()));
    } else {
      for (FormelementOption formelementOption : options) {
        optionsStr.add(formelementOption.getValue());
      }
    }
    if (this.optionsStr.isEmpty()) {
      this.optionsStr.add(StringUtils.EMPTY);
    }
    return optionsStr;
  }

  private List<String> executeDataProvider(List<String> optionStrs) {
    return DataProvider.create(optionStrs).execute();
  }

  public void setOptionsStr(List<String> optionsStr) {
    this.options.clear();
    for (String optionString : optionsStr) {
      this.options.add(new FormelementOption(optionString));
    }
    this.optionsStr = optionsStr;
  }

  public String getOptionsAsString() {
    String x = StringUtils.EMPTY;
    for (String s : this.optionsStr) {
      if (this.optionsStr.size() > 1) {
        x += s + COLON;
      } else {
        x += s;
      }
    }
    if (x.length() > 1) {
      x = x.substring(0, x.length() - 1);
    }
    return x;

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public String getDisplayLabel() {
    String displayLabel = this.label.trim();
    if (displayLabel.substring(displayLabel.length() - 1).equals(COLON)) {
      return displayLabel;
    } else {
      displayLabel += COLON;
      return displayLabel;
    }
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public Integer getIntSetting() {
    return intSetting;
  }

  public void setIntSetting(Integer intSetting) {
    this.intSetting = intSetting;
  }

  public FormElementType getType() {
    return type;
  }

  public void setType(FormElementType type) {
    this.type = type;
  }

  public List<FormelementOption> getOptions() {
    return options;
  }

  public void setOptions(List<FormelementOption> options) {
    this.options = options;

    this.optionsStr.clear();
    for (FormelementOption formelementOption : options) {
      optionsStr.add(formelementOption.getValue());
    }
  }

  public void addOption() {
    this.options.add(new FormelementOption(StringUtils.EMPTY));
    this.optionsStr.clear();
    for (FormelementOption formelementOption : options) {
      optionsStr.add(formelementOption.getValue());
    }
  }

  public void addOption(FormelementOption option) {
    this.options.add(option);
    this.optionsStr.clear();
    for (FormelementOption formelementOption : options) {
      optionsStr.add(formelementOption.getValue());
    }
  }

  public void addOption(String option) {
    this.options.add(new FormelementOption(option));
    this.optionsStr.clear();
    for (FormelementOption formelementOption : options) {
      optionsStr.add(formelementOption.getValue());
    }
  }

  public void deleteOption(FormelementOption option) {
    this.options.remove(option);
  }

  public int getTaskPosition() {
    return taskPosition;
  }

  public void setTaskPosition(int taskPosition) {
    this.taskPosition = taskPosition;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public int getIndexInPanel() {
    return indexInPanel;
  }

  public void setIndexInPanel(int indexInPanel) {
    this.indexInPanel = indexInPanel;
  }
}
