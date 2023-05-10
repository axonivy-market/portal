package portalmigration.version112.dto.casecolumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portalmigration.dto.SecurityMemberDTO;
import portalmigration.ivydata.utils.ServiceUtilities;
import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardCaseColumn;

public class CreatorColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -7953754221117810034L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.CREATOR.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  public String getDefaultStyle() {
    return EXTRA_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__creator";
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getCreators() {
    return this.filterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setCreators(List<SecurityMemberDTO> creator) {
    if (creator != null) {
      this.filterList = creator.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.filterList = new ArrayList<>();
    }
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getUserFilterCreators() {
    return this.userFilterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterCreators(List<SecurityMemberDTO> creators) {
    if (creators != null) {
      this.userFilterList = creators.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.userFilterList = new ArrayList<>();
    }
  }
  
  private SecurityMemberDTO findSecurityMember(String memberName) {
    return ServiceUtilities.findSecurityMemberByName(memberName);
  }

}
