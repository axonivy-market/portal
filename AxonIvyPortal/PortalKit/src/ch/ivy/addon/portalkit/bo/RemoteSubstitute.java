package ch.ivy.addon.portalkit.bo;

public class RemoteSubstitute {
  private String appName;
  private String forThisRole;
  private String roleDisplayName;
  private String mySubstitute;
  private String description;
  private Integer serverId;

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getForThisRole() {
    return forThisRole;
  }

  public void setForThisRole(String forThisRole) {
    this.forThisRole = forThisRole;
  }

  public String getMySubstitute() {
    return mySubstitute;
  }

  public void setMySubstitute(String mySubstitute) {
    this.mySubstitute = mySubstitute;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getServerId() {
    return serverId;
  }

  public void setServerId(Integer serverId) {
    this.serverId = serverId;
  }

  public String getRoleDisplayName() {
    return roleDisplayName;
  }

  public void setRoleDisplayName(String roleDisplayName) {
    this.roleDisplayName = roleDisplayName;
  }

}
