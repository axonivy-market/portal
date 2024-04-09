package ch.ivy.addon.portalkit.bo;

public class ItemByCategoryStatistic {
  private String displayName;
  private String displayPath;
  private long value;
  private String technicalName;
  
  public ItemByCategoryStatistic() {
  }

  public ItemByCategoryStatistic(String displayName, String displayPath, long value) {
    this.displayName = displayName;
    this.displayPath = displayPath;
    this.value = value;
  }
  
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  public String getDisplayPath() {
    return displayPath;
  }
  public void setDisplayPath(String displayPath) {
    this.displayPath = displayPath;
  }
  public long getValue() {
    return value;
  }
  public void setValue(long value) {
    this.value = value;
  }
  public String getTechnicalName() {
    return technicalName;
  }
  public void setTechnicalName(String technicalName) {
    this.technicalName = technicalName;
  }
}
