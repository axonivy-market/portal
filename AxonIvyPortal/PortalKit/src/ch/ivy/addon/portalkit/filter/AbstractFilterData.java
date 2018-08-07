package ch.ivy.addon.portalkit.filter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.enums.FilterType;

public abstract class AbstractFilterData<T extends AbstractFilter<?>> {
  private String id;
  private List<T> filters = new ArrayList<>();
  private String keyword;
  private String filterName;
  private Long userId;
  // Use to distinguish between filters of different lists (e.g. task/case lists). Default value is the process model
  // id.
  private Long filterGroupId;
  private FilterType type;

  public AbstractFilterData() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<T> getFilters() {
    return filters;
  }

  public void setFilters(List<T> filters) {
    this.filters = filters;
  }

  public String getFilterName() {
    return filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public FilterType getType() {
    return type;
  }

  public void setType(FilterType type) {
    this.type = type;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Long getFilterGroupId() {
    return filterGroupId;
  }

  public void setFilterGroupId(Long filterGroupId) {
    this.filterGroupId = filterGroupId;
  }

}
