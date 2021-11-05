package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"type", "userFilter", "userFilterList", "userFilterFrom", "userFilterTo", "filterListOptions",
    "dateFilterFrom", "dateFilterTo", "userDateFilterFrom", "userDateFilterTo", "userFilterListOptions"})
public class ColumnModel extends AbstractColumn implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @JsonIgnore
  @SuppressWarnings("unused")
  public void validate() throws ParseException {}
}
