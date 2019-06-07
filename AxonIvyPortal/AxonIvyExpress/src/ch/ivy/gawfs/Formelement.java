package ch.ivy.gawfs;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

@ManagedBean(name = "Formelement")
@ViewScoped
public class Formelement implements Serializable{

	private static final String COLON = ":";

	private static final long serialVersionUID = -4960948147758580124L;
	
	private String id;					//automatische Id
	private String name;				//Formelementname
	private String label;				//FormLabel
	private Boolean required;			//Is the Field a reqired field?
	private Integer intSetting;			//settting like number of rows or number of files
	private List<FormelementOption> options;		//list of options for ManyCheckbox or OneMenu, but also allowed File-Formats
	private String type;				//Formelement Typ	InputFieldText,	InputFieldDate, InputFieldNumber, InputTextArea, ManyCheckbox, OneRadio, FileUpload
	private List<String> optionsStr;	//String List representation of options
	private int indexInPanel; //Index of element in panel (Header, Footer, Left or Right)
	private Object value;        //Value of the Formelement, later Userinput
	
	public List<String> getOptionsStr() {
		this.optionsStr.clear();
		for (FormelementOption formelementOption : options) {
			optionsStr.add(formelementOption.getValue());			
		}			
		if(this.optionsStr.isEmpty()){
			this.optionsStr.add(StringUtils.EMPTY);
		}
		return optionsStr;
	}

	public void setOptionsStr(List<String> optionsStr) {
		this.options.clear();
		for (String optionString : optionsStr) {
			this.options.add(new FormelementOption(optionString));
		}	
		this.optionsStr = optionsStr;
	}
	
	public String getOptionsAsString(){
		String x = StringUtils.EMPTY;
		for(String s: this.optionsStr){
		  if(this.optionsStr.size() > 1) {
		    x += s + COLON;
		  }
		  else {
		    x += s;
		  }
		}
		if(x.length()>1){
			x = x.substring( 0, x.length() - 1 );
		}	
		return x;
		
	}

	public Formelement() {
		options = new ArrayList<FormelementOption>();
		optionsStr = new ArrayList<String>();
	}
	
	public Formelement(String id) {
		options = new ArrayList<FormelementOption>();
		optionsStr = new ArrayList<String>();
		this.id = id;
	}

	public Formelement(String id, String type, String name,
			String label, Boolean required, Integer intSetting,
			List<String> options) {
		super();
		this.id = id;
		this.name = name;
		setLabel(label);
		this.required = required;
		this.intSetting = intSetting;
		this.type = type;
		this.optionsStr = options;
		this.options = new ArrayList<FormelementOption>();
		
		if (!(options == null)) {
			for (String optionString : options) {
				this.options.add(new FormelementOption(optionString));
			}			
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		label = label.trim();
		if(label.substring(label.length() - 1).equals(COLON)){
			this.label = label;
		}else{
			label +=COLON;
			this.label = label;
		}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
	
	public void addOption(){
		this.options.add(new FormelementOption(StringUtils.EMPTY));
		this.optionsStr.clear();
		for (FormelementOption formelementOption : options) {
			optionsStr.add(formelementOption.getValue());			
		}
	}
	
	public void addOption(FormelementOption option){
		this.options.add(option);
		this.optionsStr.clear();
		for (FormelementOption formelementOption : options) {
			optionsStr.add(formelementOption.getValue());			
		}
	}
	
	public void addOption(String option){
		this.options.add(new FormelementOption(option));
		this.optionsStr.clear();
		for (FormelementOption formelementOption : options) {
			optionsStr.add(formelementOption.getValue());			
		}
	}
	
	public void deleteOption(@SuppressWarnings("unused") FormelementOption option){
		this.options.remove(this.options.size()-1);
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
