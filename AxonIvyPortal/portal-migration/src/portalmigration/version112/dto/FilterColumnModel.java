package portalmigration.version112.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardColumnType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"header", "styleClass", "fieldStyleClass", "style", "fieldStyle", "visible", "sortable",
    "sorted", "sortDescending", "dateFilterFrom", "dateFilterTo", "userDateFilterFrom", "userDateFilterTo",
    "filterList", "filterListOptions", "userFilterListOptions",
    "format", "filterType", "type"})
public class FilterColumnModel extends AbstractColumn implements Serializable {

  private static final long serialVersionUID = -6624895834973797177L;

  public FilterColumnModel() {
    userFilterList = new ArrayList<>();
    userFilterListOptions = new ArrayList<>();
    filterListOptions = new ArrayList<>();
  }
  
  public FilterColumnModel(String field, DashboardColumnFormat format, DashboardColumnType type) {
    this.field = field;
    this.format = format;
    this.type = type;
  }

}
