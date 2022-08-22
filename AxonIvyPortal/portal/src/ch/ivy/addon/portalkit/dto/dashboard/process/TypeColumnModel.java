package ch.ivy.addon.portalkit.dto.dashboard.process;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TypeColumnModel extends ProcessColumnModel implements Serializable {

  private static final long serialVersionUID = -8859506518292689813L;
  @JsonIgnore
  private List<ProcessType> allProcessTypes;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/processwidget/processType");
    this.field = DashboardStandardProcessColumn.TYPE.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-process__type");
    this.format = DashboardColumnFormat.CUSTOM;
  }

  public String getUserFriendlyProcessType(ProcessType type) {
    if (type == null) {
      return EMPTY;
    }
    String displayType = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessType/" + type.toString());
    return StringUtils.isBlank(displayType) ? type.name() : displayType;
  }

  public List<ProcessType> getUserProcessTypes() {
    return userFilterList.stream().map(ProcessType::typeOf).collect(Collectors.toList());
  }

  public void setUserProcessTypes(List<ProcessType> processTypes) {
    this.userFilterList = processTypes.stream().map(ProcessType::getValue).collect(Collectors.toList());
  }

  public List<ProcessType> getAllProcessTypes() {
    return CollectionUtils.isEmpty(this.allProcessTypes) ? Arrays.asList(ProcessType.values()) : this.allProcessTypes;
  }

  public void setAllProcessTypes(List<ProcessType> allProcessTypes) {
    this.allProcessTypes = allProcessTypes;
  }
}
