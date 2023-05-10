package portalmigration.version112.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.util.LanguageUtils;
import portalmigration.version112.util.LanguageUtils.NameResult;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @Type(value = TaskDashboardWidget.class, name = "task"),
  @Type(value = CaseDashboardWidget.class, name = "case"),
  @Type(value = FullProcessDashboardWidget.class, name = "full-process"),
  @Type(value = ImageProcessDashboardWidget.class, name = "image-process"),
  @Type(value = CompactProcessDashboardWidget.class, name = "compact-process"),
  @Type(value = CombinedProcessDashboardWidget.class, name = "combined-process"),
  @Type(value = StatisticDashboardWidget.class, name = "statistic"),
  @Type(value = CustomDashboardWidget.class, name = "custom"),
  @Type(value = ProcessViewerDashboardWidget.class, name = "process-viewer"),
  @Type(value = WelcomeDashboardWidget.class, name = "welcome"),
  @Type(value = NewsDashboardWidget.class, name = "news")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class DashboardWidget implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  
  protected String id;
  @Deprecated(since = "10.0", forRemoval = true)
  @JsonProperty(access = Access.WRITE_ONLY)
  protected String name;
  protected List<DisplayName> names;
  private WidgetLayout layout;

  @JsonIgnore
  public abstract DashboardWidgetType getType();

  public DashboardWidget() {}

  public DashboardWidget(DashboardWidget widget) {
    id = widget.getId();
    name = widget.getName();
    names = widget.getNames();
    setLayout(widget.getLayout());
  }

  public DashboardWidget(String id, String name, WidgetLayout layout) {
    this.id = id;
    this.name = name;
    this.layout = layout;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return LanguageUtils.getLocalizedName(names, name);
  }

  public void setName(String name) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(names, name);
    this.names = nameResult.names();
    this.name = nameResult.name();
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public WidgetLayout getLayout() {
    return layout;
  }

  public void setLayout(WidgetLayout layout) {
    this.layout = layout;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DashboardWidget other = (DashboardWidget) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}