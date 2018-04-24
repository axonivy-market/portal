package ch.ivy.addon.portalkit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;


/**
 * Bean for substitute node,
 * 
 */
public class SubstituteNode {

  private String name;
  private RemoteSubstitute remoteSubstitute;
  private List<RemoteApplicationUser> ivyUsers;
  private boolean isLeaf;
  private RemoteApplicationUser substituteUser;

  /**
   * Constructor
   *
   */
  public SubstituteNode() {
    this.remoteSubstitute = new RemoteSubstitute();
  }

  /**
   * Constructor
   *
   * @param name
   *          name of substitute.
   * @param remoteSubstitute
   *          remote substitute
   * @param ivyUsers
   *          list of {@link RemoteApplicationUser}
   * @param isLeaf
   *          is a leaf or not
   */
  public SubstituteNode(String name, RemoteSubstitute remoteSubstitute, List<RemoteApplicationUser> ivyUsers,
      boolean isLeaf) {
    this.name = name;
    this.remoteSubstitute = remoteSubstitute;
    this.isLeaf = isLeaf;
    this.ivyUsers = ivyUsers;
  }

  /**
   * Gets the name
   *
   * @return Returns the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name
   *
   * @param name
   *          The name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the remoteSubstitute
   *
   * @return Returns the remoteSubstitute
   */
  public RemoteSubstitute getRemoteSubstitute() {
    return remoteSubstitute;
  }

  /**
   * Sets the remoteSubstitute
   *
   * @param remoteSubstitute
   *          The remoteSubstitute to set
   */
  public void setRemoteSubstitute(RemoteSubstitute remoteSubstitute) {
    this.remoteSubstitute = remoteSubstitute;
  }

  /**
   * Gets the ivyUsers
   *
   * @return Returns the ivyUsers
   */
  public List<RemoteApplicationUser> getIvyUsers() {
    return ivyUsers;
  }

  /**
   * Sets the ivyUsers
   *
   * @param ivyUsers
   *          The ivyUsers to set
   */
  public void setIvyUsers(List<RemoteApplicationUser> ivyUsers) {
    this.ivyUsers = ivyUsers;
  }

  /**
   * Gets the isLeaf
   *
   * @return Returns the isLeaf
   */
  public boolean isLeaf() {
    return isLeaf;
  }

  /**
   * Sets the isLeaf
   *
   * @param isLeaf
   *          The isLeaf to set
   */
  public void setLeaf(boolean isLeaf) {
    this.isLeaf = isLeaf;
  }

  public RemoteApplicationUser getSubstituteUser() {
    if (substituteUser == null) {
      substituteUser = new RemoteApplicationUser();
      String name = remoteSubstitute.getMySubstitute();
      if (StringUtils.isNotEmpty(name)) {
        ivyUsers.stream().filter(user -> name.equals(user.getMemberName()))
            .forEach(ivyUser -> substituteUser = ivyUser);
      }
    }
    return substituteUser;
  }

  public void setSubstituteUser(RemoteApplicationUser substituteUser) {
    this.substituteUser = substituteUser;
  }

  public List<RemoteApplicationUser> autoCompleteUser(String query) {
    return ivyUsers.stream()
        .filter(user -> StringUtils.containsIgnoreCase(user.getDisplayName(), query) || StringUtils.containsIgnoreCase(user.getMemberName(), query))
        .collect(Collectors.toList());
  }
}
