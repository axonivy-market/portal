package ch.ivy.addon.portalkit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.util.IvyExecutor;

public class SubstituteNode {

  private String name;
  private IvySubstitute substitute;
  private List<UserDTO> users;
  private boolean isLeaf;

  public SubstituteNode() {
    this.substitute = new IvySubstitute();
  }

  public SubstituteNode(String name, IvySubstitute substitute, List<UserDTO> users,
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

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean isLeaf) {
    this.isLeaf = isLeaf;
  }

  public UserDTO getSubstituteUser() {
    return substitute.getSubstituteUser();
  }

  public void setSubstituteUser(UserDTO substituteUser) {
    substitute.setSubstituteUser(substituteUser);
  }

  public List<UserDTO> autoCompleteUser(String query) {
    return IvyExecutor.executeAsSystem(() -> {
      List<UserDTO> filteredUsers = users.stream()
          .filter(user -> StringUtils.containsIgnoreCase(user.getDisplayName(), query) || StringUtils.containsIgnoreCase(user.getMemberName(), query))
          .sorted((first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName()))
          .collect(Collectors.toList());
      filteredUsers.add(0, null);
      return filteredUsers;
    });
  }
}
