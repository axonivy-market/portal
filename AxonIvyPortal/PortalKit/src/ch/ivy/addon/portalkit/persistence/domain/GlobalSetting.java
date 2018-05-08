package ch.ivy.addon.portalkit.persistence.domain;


public class GlobalSetting extends BusinessEntity { //NOSONAR
  private String key;
  private String value;
  private String note;
  
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "GlobalSetting [key=" + key + ", value=" + value + ", note=" + note + ", id=" + getId() + "]";
  }


}
