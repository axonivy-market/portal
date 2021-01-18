package ch.ivy.addon.portalkit.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.SortDirection;

public class SortFieldUtil {

  public static final String SORT_FORMAT = "%s_%s";
  public static final String SORT_SEPARATOR = "_";

  /**<p>
   * Build a SortField with pattern: column name + "_ASCENDING" or column name + "_DESCENDING"
   * </p>
   * <p>
   * E.g your column is "PRIORITY" and ascending sort: PRIORITY_ASC
   * </p>
   * @param column
   * @param isSortDescending
   * @return SortField
   */
  public static String buildSortField(String column, boolean isSortDescending) {
    return String.format(SORT_FORMAT, column, getSortDirectionValue(isSortDescending));
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

  private static String getSortDirectionValue(boolean isSortDescending) {
    return isSortDescending ? SortDirection.DESCENDING.name().substring(0, 4)
        : SortDirection.ASCENDING.name().substring(0, 3);
  }

  /**
   * Extracts column name from SortField by sort direction
   * @param sortField
   * @return column name
   */
  public static String extractSortColumn(String sortField) {
    if (StringUtils.isBlank(sortField) || sortField.length() < 4) {
      return EMPTY;
    }
    int directionIndex = sortField.lastIndexOf(SORT_SEPARATOR);
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
    if (sortField.contains(SORT_SEPARATOR)) {
      int directionIndex = sortField.lastIndexOf(SORT_SEPARATOR);
      String sortDirection = StringUtils.substring(sortField, directionIndex + 1);
      return StringUtils.equalsIgnoreCase(sortDirection, SortDirection.ASCENDING.name())
          || StringUtils.startsWithIgnoreCase(SortDirection.ASCENDING.name(), sortDirection);
    }
    return StringUtils.equalsIgnoreCase(sortField, SortDirection.ASCENDING.name())
        || StringUtils.startsWithIgnoreCase(SortDirection.ASCENDING.name(), sortField);
  }

  public static boolean invalidSortField(String sortField, List<String> columns) {
    String compareSort = extractSortColumn(sortField);
    return columns.stream().map(column -> extractSortColumn(column))
        .filter(extractedColumn -> extractedColumn.equalsIgnoreCase(compareSort))
        .collect(Collectors.toList())
        .isEmpty();
  }
}
