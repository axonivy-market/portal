package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardFilterType;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  protected String header;
  protected String field;
  protected DashboardColumnType type = DashboardColumnType.STANDARD;
  protected String styleClass;
  protected String fieldStyleClass;
  protected String style;
  protected String fieldStyle;
  protected boolean visible = true;
  protected boolean sortable = true;
  protected DashboardColumnFormat format = DashboardColumnFormat.STRING;
  protected String pattern;
  protected String filter;
  protected List<String> filterList = new ArrayList<>();
  protected String filterFrom;
  protected String filterTo;
  protected DashboardFilterType filterType = DashboardFilterType.LIKE;
  protected boolean sorted;
  protected boolean sortDescending;
  
  @JsonIgnore
  protected String userFilter;
  @JsonIgnore
  protected List<String> userFilterList = new ArrayList<>();
  @JsonIgnore
  protected String userFilterFrom;
  @JsonIgnore
  protected String userFilterTo;
  @JsonIgnore
  protected List<String> filterListOptions;
  @JsonIgnore
  protected Date dateFilterFrom;
  @JsonIgnore
  protected Date dateFilterTo;
  @JsonIgnore
  protected Date userDateFilterFrom;
  @JsonIgnore
  protected Date userDateFilterTo;
  
  public void initDefaultValue() {};
  
  public Object display(ITask task) {
    ICustomFields customFields = task.customFields();
    if (isNumber()) {
      return customFields.numberField(field).getOrNull();
    } else if (isDate()) {
      return customFields.timestampField(field).getOrNull();
    } else if (isText()) {
      return customFields.textField(field).getOrNull();
    } else {
      return customFields.stringField(field).getOrNull();
    }
  }
  
  @JsonIgnore
  public boolean isNumber() {
    return format == DashboardColumnFormat.NUMBER;
  }
  
  @JsonIgnore
  public boolean isDate() {
    return format == DashboardColumnFormat.TIMESTAMP;
  }
  
  @JsonIgnore
  public boolean isText() {
    return format == DashboardColumnFormat.TEXT;
  }
  
  protected <T> T defaultIfEmpty(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }
  
  protected String cms(String path) {
    return Ivy.cms().co(path);
  }

  public String getHeader() {
    if (StringUtils.startsWithIgnoreCase(header, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(header, DashboardConfigurationPrefix.CMS));
    }
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public DashboardColumnType getType() {
    return type;
  }

  public void setType(DashboardColumnType type) {
    this.type = type;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
  }

  public String getFieldStyleClass() {
    return fieldStyleClass;
  }

  public void setFieldStyleClass(String fieldStyleClass) {
    this.fieldStyleClass = fieldStyleClass;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getFieldStyle() {
    return fieldStyle;
  }

  public void setFieldStyle(String fieldStyle) {
    this.fieldStyle = fieldStyle;
  }

  public boolean getVisible() {
    return visible;
  }
  
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public boolean getSortable() {
    return sortable;
  }

  public void setSortable(boolean sortable) {
    this.sortable = sortable;
  }

  public DashboardColumnFormat getFormat() {
    return format;
  }

  public void setFormat(DashboardColumnFormat format) {
    this.format = format;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }
  
  public List<String> getFilterList() {
    return filterList;
  }
  
  public void setFilterList(List<String> filterList) {
    this.filterList = filterList;
  }
  
  public String getFilterFrom() {
    return filterFrom;
  }
  
  public void setFilterFrom(String filterFrom) {
    this.filterFrom = filterFrom;
  }
  
  public String getFilterTo() {
    return filterTo;
  }
  
  public void setFilterTo(String filterTo) {
    this.filterTo = filterTo;
  }
  
  public DashboardFilterType getFilterType() {
    return filterType;
  }
  
  public void setFilterType(DashboardFilterType filterType) {
    this.filterType = filterType;
  }

  public String getUserFilter() {
    return userFilter;
  }

  public void setUserFilter(String userFilter) {
    this.userFilter = userFilter;
  }

  public List<String> getUserFilterList() {
    return userFilterList;
  }

  public void setUserFilterList(List<String> userFilterList) {
    this.userFilterList = userFilterList;
  }

  public String getUserFilterFrom() {
    return userFilterFrom;
  }

  public void setUserFilterFrom(String userFilterFrom) {
    this.userFilterFrom = userFilterFrom;
  }

  public String getUserFilterTo() {
    return userFilterTo;
  }

  public void setUserFilterTo(String userFilterTo) {
    this.userFilterTo = userFilterTo;
  }

  public List<String> getFilterListOptions() {
    if (filterListOptions == null) {
      Recordset recordset = TaskQuery.create().groupBy().customField().stringField(field).executor().recordset();
      filterListOptions = recordset.getColumn(field).stream().filter(Objects::nonNull).map(Object::toString).sorted(StringUtils::compareIgnoreCase).collect(Collectors.toList());
    }
    return filterListOptions;
  }
  
  public void setFilterListOptions(List<String> filterListOptions) {
    this.filterListOptions = filterListOptions;
  }

  public Date getDateFilterFrom() throws ParseException {
    if (dateFilterFrom == null && filterFrom != null) {
      dateFilterFrom = Dates.parse(filterFrom);
    }
    return dateFilterFrom;
  }

  public void setDateFilterFrom(Date dateFilterFrom) {
    this.dateFilterFrom = dateFilterFrom;
    this.filterFrom = Dates.format(dateFilterFrom);
  }

  public Date getDateFilterTo() throws ParseException {
    if (dateFilterTo == null && filterTo != null) {
      dateFilterTo = Dates.parse(filterTo);
    }
    return dateFilterTo;
  }

  public void setDateFilterTo(Date dateFilterTo) {
    this.dateFilterTo = dateFilterTo;
    this.filterTo = Dates.format(dateFilterTo);
  }

  public Date getUserDateFilterFrom() throws ParseException {
    if (userDateFilterFrom == null && userFilterFrom != null) {
      userDateFilterFrom = Dates.parse(userFilterFrom);
    }
    return userDateFilterFrom;
  }

  public void setUserDateFilterFrom(Date userDateFilterFrom) {
    this.userDateFilterFrom = userDateFilterFrom;
    this.userFilterFrom = Dates.format(userDateFilterFrom);
  }

  public Date getUserDateFilterTo() throws ParseException {
    if (userDateFilterTo == null && userFilterTo != null) {
      userDateFilterTo = Dates.parse(userFilterTo);
    }
    return userDateFilterTo;
  }

  public void setUserDateFilterTo(Date userDateFilterTo) {
    this.userDateFilterTo = userDateFilterTo;
    this.userFilterTo = Dates.format(userDateFilterTo);
  }
  
}
