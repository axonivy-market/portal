package ch.ivy.addon.portalkit.filter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.enums.FilterType;

/**
 * Data for filter 
 *
 * @param <T> subclass of {@link AbstractFilter}
 */
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

  /**
   * @hidden
   */
  public AbstractFilterData() {}

  /**
   * Getter for id
   * @return Id of filter
   */
  public String getId() {
    return id;
  }

  /**
   * Setter for id
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Getter for filter list
   * @return filter list e.g task name filter, task prio filter.. 
   */
  public List<T> getFilters() {
    return filters;
  }

  /**
   * Setter for filter list
   * @param filters list subclass {@link AbstractFilter} 
   */
  public void setFilters(List<T> filters) {
    this.filters = filters;
  }

  /**
   * Getter for name
   * @return name
   */
  public String getFilterName() {
    return filterName;
  }

  /**
   * Setter for name
   * @param filterName
   */
  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  /**
   * Getter for user Id
   * @return Id user which filter belongs to
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Setter for user Id
   * @param userId
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Getter for type
   * @return ONLY_ME, ALL_USERS, ALL_ADMINS or DEFAULT
   */
  public FilterType getType() {
    return type;
  }

  /**
   * Setter for type
   * @param type
   */
  public void setType(FilterType type) {
    this.type = type;
  }

  /**
   * Getter for keyword
   * @return keyword to search
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * Setter for keyword
   * @param keyword
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  /**
   * Getter of group id. Use to distinguish between filters of different lists (e.g. task/case lists). Default value is the process model id
   * @return group id
   */
  public Long getFilterGroupId() {
    return filterGroupId;
  }

  /**
   * Setter of group id. Use to distinguish between filters of different lists (e.g. task/case lists). Default value is the process model id
   * @param filterGroupId
   */
  public void setFilterGroupId(Long filterGroupId) {
    this.filterGroupId = filterGroupId;
  }

}
