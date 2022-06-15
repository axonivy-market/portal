package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;

@SuppressWarnings("deprecation")
public class SubstituteNode {

  private String name;
  private IvySubstitute substitute;
  private String applicationName;
  private boolean isLeaf;

  public SubstituteNode() {
    this.substitute = new IvySubstitute();
  }

  public SubstituteNode(String name, IvySubstitute substitute, boolean isLeaf, String applicationName) {
    this.name = name;
    this.substitute = substitute;
    this.isLeaf = isLeaf;
    this.applicationName = applicationName;
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

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean isLeaf) {
    this.isLeaf = isLeaf;
  }
  
  public String getApplicationName() {
    return applicationName;
  }
  
  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public UserDTO getSubstituteUser() {
    return substitute.getSubstituteUser();
  }

  public void setSubstituteUser(UserDTO substituteUser) {
    substitute.setSubstituteUser(substituteUser);
  }
}
