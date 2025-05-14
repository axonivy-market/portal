package ch.ivy.addon.portalkit.bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.task.responsible.Responsible;
import ch.ivyteam.ivy.workflow.task.responsible.Responsibles;

@ManagedBean
@ViewScoped
public class ResponsibleBean {

  public int getResponsiblesSize(Responsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? 0 : responsibles.all().size();
  }
  
  public List<Responsible> getResponsibleLimit10(Responsibles responsibles) {
    List<Responsible> list = responsibles.all();
    return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.subList(0, Math.min(PortalConstants.LIMIT_NUMBER_RESPONSIBLE, list.size()));
  }
  
  public ISecurityMember getFirstResponsible(Responsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? null : responsibles.all().getFirst().get();
  }
  
  public boolean isSubstituteForTask(Responsibles responsibles) {
    if (CollectionUtils.isEmpty(responsibles.all())) {
        return false;
    }
    return responsibles.all().stream()
        .anyMatch(responsible -> responsible.get().available() &&
            Ivy.session().isMemberThroughActiveSubstitution(responsible.get()));
  }
  
  public String getDisplayName(ISecurityMember member) {
    if (member == null) {
      return StringUtils.EMPTY;
    }
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }
  
  public String getFirstDisplayName(Responsibles responsibles) {
    return getDisplayName(getFirstResponsible(responsibles));
  }
  
  public String getMoretext(Responsibles responsibles) {
    return Ivy.cms().co("/Labels/AndMore", Arrays.asList(responsibles.all().size() - PortalConstants.LIMIT_NUMBER_RESPONSIBLE));
  }
  
}
