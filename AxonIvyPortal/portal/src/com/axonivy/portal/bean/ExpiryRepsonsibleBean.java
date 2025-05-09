package com.axonivy.portal.bean;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.task.expiry.responsible.ExpiryResponsible;
import ch.ivyteam.ivy.workflow.task.expiry.responsible.ExpiryResponsibles;

@ManagedBean
@ViewScoped
public class ExpiryRepsonsibleBean {
  
  public int getResponsiblesSize(ExpiryResponsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? 0 : responsibles.all().size();
  }
  
  public List<ExpiryResponsible> getResponsibleLimit10(ExpiryResponsibles responsibles) {
    List<ExpiryResponsible> list = responsibles.all();
    return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.subList(0, Math.min(PortalConstants.LIMIT_NUMBER_RESPONSIBLE, list.size()));
  }
  
  public ISecurityMember getFirstResponsible(ExpiryResponsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? null : responsibles.all().getFirst().get();
  }
  
  public String getDisplayName(ISecurityMember member) {
    if (member == null) {
      return StringUtils.EMPTY;
    }
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }
  
  public String getFirstDisplayName(ExpiryResponsibles responsibles) {
    return getDisplayName(getFirstResponsible(responsibles));
  }
}
