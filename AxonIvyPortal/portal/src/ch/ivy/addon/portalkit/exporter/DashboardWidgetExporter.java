package ch.ivy.addon.portalkit.exporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Base class for export dashboard widget
 * Provide function to get stream content, default header ..
 *
 */
public abstract class DashboardWidgetExporter extends Exporter {

  private static final String CUSTOM_FIELD_SEPARATOR = "__";

  private String widgetName;
  private String fileNameCms;
  private Map<String, ColumnModel> mapHeaders;

  public DashboardWidgetExporter() {}
  
  public DashboardWidgetExporter(List<String> columnsVisibility, List<ColumnModel> columnModels, String widgetName, String fileNameCms) {
    this.setColumnsVisibility(columnsVisibility);
    this.setWidgetName(widgetName);
    this.setFileNameCms(fileNameCms);
    this.setMapHeaders(columnModels.stream().collect(Collectors.toMap(ColumnModel::getField, Function.identity())));
  }
  
  /**
   * Gets column value.
   * @param <T>
   * @param column 
   * @param item 
   * 
   * @return column value
   */
  public abstract <T> Object getColumnValue(String column, T item);

  /**
   * File name for export
   */
  @Override
  protected String generateFileName(Date creationDate, String extension, String suffix) {
    String fileNameSuffix = createFileNameSuffix(creationDate, suffix); 
    return Ivy.cms().co(fileNameCms,
        Arrays.asList(widgetName, fileNameSuffix, extension));
  }

  /**
   * Generate data for export
   */
  @Override
  protected <T> List<List<Object>> generateData(List<T> items) {
    List<List<Object>> rows = new ArrayList<>();
    for (T t : items) {
        List<Object> row = new ArrayList<>();
        for (String column : columnsVisibility) {
          row.add(getColumnValue(column, t));
        }
        rows.add(row);
    }
    return rows;

  }

  protected String[] getCustomColumnParts(String customColumn) {
    return customColumn.split(CUSTOM_FIELD_SEPARATOR);
  }

  protected String getWidgetName() {
    return widgetName;
  }

  protected void setWidgetName(String widgetName) {
    this.widgetName = widgetName;
  }

  protected String getFileNameCms() {
    return fileNameCms;
  }

  protected void setFileNameCms(String fileNameCms) {
    this.fileNameCms = fileNameCms;
  }

  public Map<String, ColumnModel> getMapHeaders() {
    return mapHeaders;
  }

  public void setMapHeaders(Map<String, ColumnModel> mapHeaders) {
    this.mapHeaders = mapHeaders;
  }

}
