package ch.ivy.addon.portalkit.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.SortDirection;

public class SortFieldUtil {

  public static final String SORT_FORMAT = "%s_%s";

  /**<p>
   * Build a SortField with pattern: column name + "_ASC" or column name + "_DESC"
   * </p>
   * <p>
   * E.g your column is "PRIORITY" and ascending sort: PRIORITY_ASC
   * </p>
   * @param column
   * @param isSortDescending
   * @return SortField
   */
  public static String buildSortField(String column, boolean isSortDescending) {
    return String.format(SORT_FORMAT, column, getSortDirectionValue(isSortDescending).name());
  }

  /**<p>
   * Build a SortField with pattern: column name + "_ASC" or column name + "_DESC"
   * </p>
   * <p>
   * E.g your column is "PRIORITY" and ascending sort: PRIORITY_ASC
   * </p>
   * @param column
   * @param sortDirection
   * @return SortField
   */
  public static String buildSortField(String column, String sortDirection) {
    return String.format(SORT_FORMAT, column, sortDirection);
  }

  private static SortDirection getSortDirectionValue(boolean isSortDescending) {
    return isSortDescending ? SortDirection.DESC : SortDirection.ASC;
  }

  /**
   * Extracts column name from SortField by sort direction
   * @param sortField
   * @return column name
   */
  public static String extractSortColumn(String sortField) {
    if (StringUtils.isBlank(sortField)) {
      return EMPTY;
    }
    int directionIndex = sortField.lastIndexOf("_");
    return StringUtils.substring(sortField, 0, directionIndex);
  }

  /**<p>
   * This is checking input sort field is ascending or not
   * <br/>
   * By checks the field ends with ASC or start with ASC
   * </p>
   * <p>
   * E.g:
   * <ul>
   * <li> sortField = "_ASC" => true</li>
   * <li> sortField = "PRIORITY_ASC" => true</li>
   * </ul>
   * </p>
   * 
   * @param sortField
   * @return sort direction
   */
  public static boolean isAscendingSort(String sortField) {
    if (StringUtils.isBlank(sortField) || sortField.length() < 3) {
      return false;
    }
    String sortDirection = StringUtils.substring(sortField, sortField.length() - 3);
    return StringUtils.equalsIgnoreCase(sortDirection, SortDirection.ASC.name());
  }

}
