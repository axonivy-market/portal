package portalmigration.version91.persistence.domain;

public class GlobalSetting extends BusinessEntity {
  private String key;
  private String value;
  
  public GlobalSetting(String key, String value) {
    this.key = key;
    this.value = value;
  }
  
  public GlobalSetting(String key) {
    this.key = key;
  }
  
  public GlobalSetting() {
  }
  
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
  
}
