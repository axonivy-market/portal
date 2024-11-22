package ch.ivy.addon.portalkit.dto.dashboard;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardFilterType;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractColumn implements Serializable {

  private static final long serialVersionUID = -8430449835327597359L;
  @JsonIgnore
  private static final String DEFAULT_WIDTH_UNIT = "px";
  @JsonIgnore
  public static final int TINY_WIDTH = 80;
  @JsonIgnore
  public static final int SMALL_WIDTH = 100;
  @JsonIgnore
  public static final int NORMAL_WIDTH = 120;
  @JsonIgnore
  public static final int EXTRA_WIDTH = 150;
  @JsonIgnore
  public static final int LARGE_WIDTH = 200;

  @Deprecated(since = "10.0", forRemoval = true)
  @JsonProperty(access = Access.WRITE_ONLY)
  protected String header;
  protected List<DisplayName> headers;
  protected String field;
  protected String styleClass;
  protected String fieldStyleClass;
  protected String style;
  protected String fieldStyle;
  protected Boolean visible;
  protected Boolean quickSearch;
  protected Boolean sortable;
  protected DashboardColumnFormat format;
  protected String pattern;
  protected String filter;
  protected List<String> filterList;
  protected String filterFrom;
  protected String filterTo;
  protected DashboardFilterType filterType;
  protected Boolean sorted;
  protected Boolean sortDescending;
  protected DashboardColumnType type;
  protected Boolean isCustomAction;
  protected String icon;
  protected String description;

  protected String userFilter;
  protected List<String> userFilterList;
  protected String userFilterFrom;
  protected String userFilterTo;
  protected List<String> filterListOptions;
  protected Date dateFilterFrom;
  protected Date dateFilterTo;
  protected Date userDateFilterFrom;
  protected Date userDateFilterTo;
  protected List<String> userFilterListOptions;
  protected String width;  
  
  @JsonIgnore
  protected String styleToDisplay;

  public void initDefaultValue() {
    if (isNull(this.visible)) {
      this.visible = true;
    }
    if (isNull(this.sortable)) {
      this.sortable = getDefaultSortable();
    }
    if (isNull(this.format)) {
      this.format = getDefaultFormat();
    }
    if (isNull(this.filterList)) {
      this.filterList = new ArrayList<>();
    }
    if (isNull(this.filterType)) {
      this.filterType = getDefaultFilterType();
    }
    if (isNull(this.type)) {
      this.type = getDefaultType();
    }
    if (isNull(this.userFilterList)) {
      this.userFilterList = new ArrayList<>();
    }
    if (isNull(this.style)) {
      this.style = getDefaultStyle();
    }

    this.styleToDisplay = initDefaultStyle();
  }

  @JsonIgnore
  public DashboardColumnType getDefaultType() {
    return DashboardColumnType.STANDARD;
  }

  @JsonIgnore
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.STRING;
  }

  @JsonIgnore
  public DashboardFilterType getDefaultFilterType() {
    return DashboardFilterType.LIKE;
  }

  @JsonIgnore
  public Boolean getDefaultSortable() {
    return true;
  }

  @JsonIgnore
  public String getDefaultHeaderCMS() {
    return "";
  }

  @JsonIgnore
  public String getDefaultStyle() {
    return "";
  }

  @JsonIgnore
  public String getDefaultStyleClass() {
    return "";
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
    return LanguageUtils.getLocalizedName(headers, header);
  }

  public void setHeader(String header) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(headers, header);
    this.headers = nameResult.names();
    this.header = nameResult.name();
  }

  public List<DisplayName> getHeaders() {
    return headers;
  }

  public void setHeaders(List<DisplayName> headers) {
    this.headers = headers;
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

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Boolean getSortable() {
    return sortable;
  }

  public void setSortable(Boolean sortable) {
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
    this.userFilterListOptions = filterList;
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
  
  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  @JsonIgnore
  public String getStyleToDisplay() {
    return styleToDisplay;
  }

  @JsonIgnore
  public void setStyleToDisplay(String styleToDisplay) {
    this.styleToDisplay = styleToDisplay;
  }


  @JsonIgnore
  public String getHeaderText() {
    return getHeader();
  }

  public List<String> getFilterListOptions() {
    if (filterListOptions == null) {
      Recordset recordset = TaskQuery.create().groupBy().customField().stringField(field).executor().recordset();
      filterListOptions = recordset.getColumn(field).stream().filter(Objects::nonNull).map(Object::toString)
          .sorted(StringUtils::compareIgnoreCase).collect(Collectors.toList());
    }
    return filterListOptions;
  }

  public void setFilterListOptions(List<String> filterListOptions) {
    this.filterListOptions = filterListOptions;
  }

  public List<String> getUserFilterListOptions() {
    if (userFilterListOptions == null) {
      userFilterListOptions = CollectionUtils.isEmpty(filterList) ? getFilterListOptions() : filterList;
    }
    return userFilterListOptions;
  }

  public void setUserFilterListOptions(List<String> userFilterListOptions) {
    this.userFilterListOptions = userFilterListOptions;
  }

  public Date getDateFilterFrom() {
    if (dateFilterFrom == null && filterFrom != null) {
      dateFilterFrom = Dates.parse(filterFrom);
    }
    return dateFilterFrom;
  }

  public void setDateFilterFrom(Date dateFilterFrom) {
    this.dateFilterFrom = dateFilterFrom;
    this.filterFrom = Dates.format(dateFilterFrom);
  }

  public Date getDateFilterTo() {
    if (dateFilterTo == null && filterTo != null) {
      dateFilterTo = Dates.parse(filterTo);
    }
    return dateFilterTo;
  }

  public void setDateFilterTo(Date dateFilterTo) {
    this.dateFilterTo = dateFilterTo;
    this.filterTo = Dates.format(dateFilterTo);
  }

  public Date getUserDateFilterFrom() {
    if (userDateFilterFrom == null && userFilterFrom != null) {
      userDateFilterFrom = Dates.parse(userFilterFrom);
    }
    return userDateFilterFrom;
  }

  public void setUserDateFilterFrom(Date userDateFilterFrom) {
    this.userDateFilterFrom = userDateFilterFrom;
    this.userFilterFrom = Dates.format(userDateFilterFrom);
  }

  public Date getUserDateFilterTo() {
    if (userDateFilterTo == null && userFilterTo != null) {
      userDateFilterTo = Dates.parse(userFilterTo);
    }
    return userDateFilterTo;
  }

  public void setUserDateFilterTo(Date userDateFilterTo) {
    this.userDateFilterTo = userDateFilterTo;
    this.userFilterTo = Dates.format(userDateFilterTo);
  }
  
  @JsonIgnore
  public void resetUserFilter() {
    setUserFilter(StringUtils.EMPTY);
    setUserFilterList(new ArrayList<>());
    setUserFilterFrom(StringUtils.EMPTY);
    setUserFilterTo(StringUtils.EMPTY);
    setUserDateFilterFrom(null);
    setUserDateFilterTo(null);
  }
  
  @JsonIgnore
  protected int getDefaultColumnWidth() {
    return SMALL_WIDTH;
  }

  @JsonIgnore
  protected int resolveColumnWidth() {
    return NumberUtils.toInt(width, getDefaultColumnWidth());
  }

  @JsonIgnore
  protected String initDefaultWidth() {
    return "width: " + resolveColumnWidth() + "px";
  }

  @JsonIgnore
  public String initDefaultStyle() {
    return String.join(";", initDefaultWidth(),
        StringUtils.defaultIfBlank(this.style, ""));
  }

  @JsonIgnore
  public Boolean getIsCustomAction() {
    return isCustomAction;
  }

  public void setIsCustomAction(Boolean isCustomAction) {
    this.isCustomAction = isCustomAction;
  }

  @JsonIgnore
  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  @JsonIgnore
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public Boolean getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(Boolean quickSearch) {
    this.quickSearch = quickSearch;
  }
}
