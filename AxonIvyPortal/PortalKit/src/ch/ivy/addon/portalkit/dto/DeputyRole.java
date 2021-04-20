package ch.ivy.addon.portalkit.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SubstitutionType;

public class DeputyRole {
  private List<ISecurityMember> deputies;
  private DeputyRoleType deputyRoleType;
  private IRole substitutionRole;
  private String deputyRoleDisplayName;
  private SubstitutionType substitutionType;
  private String description;
  private IUser ownerUser;

  public DeputyRole() {
    this.deputies = new ArrayList<>();
  }

  public List<ISecurityMember> getDeputies() {
    return deputies;
  }

  public void setDeputies(List<ISecurityMember> deputies) {
    this.deputies = deputies;
  }

  public void addDeputy(ISecurityMember deputy) {
    this.deputies.add(deputy);
  }

  public void removeDeputy(ISecurityMember deputy) {
    this.deputies.remove(deputy);
  }

  public DeputyRoleType getDeputyRoleType() {
    return deputyRoleType;
  }

  public void setDeputyRoleType(DeputyRoleType deputyRoleType) {
    this.deputyRoleType = deputyRoleType;
  }

  public String getDeputyRoleDisplayName() {
    this.deputyRoleDisplayName = DeputyRoleType.TASK_FOR_ROLE.equals(this.deputyRoleType) ? this.deputyRoleType.getLabel().concat(substitutionRole.getDisplayName()) : this.deputyRoleType.getLabel();
    return deputyRoleDisplayName;
  }

  public SubstitutionType getSubstitutionType() {
    return substitutionType;
  }

  public void setSubstitutionType(SubstitutionType substitutionType) {
    this.substitutionType = substitutionType;
  }

  public IRole getSubstitutionRole() {
    return substitutionRole;
  }

  public void setSubstitutionRole(IRole substitutionRole) {
    this.substitutionRole = substitutionRole;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IUser getOwnerUser() {
    return ownerUser;
  }

  public void setOwnerUser(IUser ownerUser) {
    this.ownerUser = ownerUser;
  }
}
