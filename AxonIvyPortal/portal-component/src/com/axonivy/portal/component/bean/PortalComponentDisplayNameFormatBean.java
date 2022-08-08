package com.axonivy.portal.component.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.axonivy.portal.component.util.SecurityMemberDisplayNameUtils;

import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ApplicationScoped
public class PortalComponentDisplayNameFormatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String generateBriefDisplayNameForSecurityMember(ISecurityMember member, String securityMemberName) {
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, securityMemberName);
  }
}
