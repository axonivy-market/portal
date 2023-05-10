package portalmigration.version112.util;

import static portalmigration.version112.enums.DashboardStandardTaskColumn.ID;
import static portalmigration.version112.enums.DashboardStandardTaskColumn.START;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import portalmigration.version112.dto.ColumnModel;
import portalmigration.version112.dto.DashboardProcess;
import portalmigration.version112.dto.casecolumn.CaseColumnModel;
import portalmigration.version112.dto.taskcolumn.TaskColumnModel;
import portalmigration.version112.enums.DashboardStandardCaseColumn;

public class DashboardWidgetUtils {

  public static List<ColumnModel> buildCaseFilterableColumns(List<CaseColumnModel> caseColumns) {
    List<ColumnModel> filterableColumns = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(caseColumns)) {
      filterableColumns = caseColumns.stream().filter(Objects::nonNull)
          .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.ID.toString()))
          .collect(Collectors.toList());
    }
    return filterableColumns;
  }
  
  public static List<ColumnModel> buildTaskFilterableColumns(List<TaskColumnModel> columns) {
    if (CollectionUtils.isEmpty(columns)) {
      return new ArrayList<>();
    }
    return columns.stream().filter(Objects::nonNull)
        .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), START.toString())
                  && !StringUtils.equalsIgnoreCase(col.getField(), ID.toString()))
        .collect(Collectors.toList());
  }

  public static boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    if (CollectionUtils.isEmpty(categories) || Objects.isNull(process)) {
      return true;
    }
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    if (Objects.isNull(process.getCategory())) {
      return hasNoCategory;
    }
    return categories.indexOf(process.getCategory().getCmsUri()) > -1
        || (StringUtils.isBlank(process.getCategory().getPath()) && hasNoCategory);
  }
}
