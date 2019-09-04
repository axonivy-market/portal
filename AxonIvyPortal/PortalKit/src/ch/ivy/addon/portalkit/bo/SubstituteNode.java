package ch.ivy.addon.portalkit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.security.IUser;

public class SubstituteNode {

  private String name;
  private IvySubstitute substitute;
  private List<IUser> users;
  private boolean isLeaf;

  public SubstituteNode() {
    this.substitute = new IvySubstitute();
  }

  public SubstituteNode(String name, IvySubstitute substitute, List<IUser> users,
      boolean isLeaf) {
    this.name = name;
    this.substitute = substitute;
    this.isLeaf = isLeaf;
    this.users = users;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IvySubstitute getSubstitute() {
    return substitute;
  }

  public void setSubstitute(IvySubstitute substitute) {
    this.substitute = substitute;
  }

  public List<IUser> getUsers() {
    return users;
  }

  public void setUsers(List<IUser> users) {
    this.users = users;
  }

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean isLeaf) {
    this.isLeaf = isLeaf;
  }

  public IUser getSubstituteUser() {
    return substitute.getSubstituteUser();
  }

  public void setSubstituteUser(IUser substituteUser) {
    substitute.setSubstituteUser(substituteUser);
  }

  public List<IUser> autoCompleteUser(String query) {
    return IvyExecutor.executeAsSystem(() -> {
      List<IUser> filteredUsers = users.stream()
          .filter(user -> StringUtils.containsIgnoreCase(user.getDisplayName(), query) || StringUtils.containsIgnoreCase(user.getMemberName(), query))
          .collect(Collectors.toList());
      filteredUsers.add(0, null);
      return filteredUsers;
    });
  }
}
