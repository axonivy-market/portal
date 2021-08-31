package portalmigration.version93.dto;

import java.io.Serializable;
import java.util.List;

public class FilterDataInfo implements Serializable {

  private static final long serialVersionUID = 3643899918776311770L;
  private Long userId;
  private Long filterGroupId;
  private String objectType;
  private List<String> rawValues;

  public FilterDataInfo() {}

  public FilterDataInfo(Long userId, Long filterGroupId, List<String> rawValues) {
    this.userId = userId;
    this.filterGroupId = filterGroupId;
    this.rawValues = rawValues;
  }

  public FilterDataInfo(Long userId, Long filterGroupId, String objectType, List<String> rawValues) {
    this.userId = userId;
    this.filterGroupId = filterGroupId;
    this.objectType = objectType;
    this.rawValues = rawValues;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getFilterGroupId() {
    return filterGroupId;
  }

  public void setFilterGroupId(Long filterGroupId) {
    this.filterGroupId = filterGroupId;
  }

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public List<String> getRawValues() {
    return rawValues;
  }

  public void setRawValues(List<String> rawValues) {
    this.rawValues = rawValues;
  }

}
