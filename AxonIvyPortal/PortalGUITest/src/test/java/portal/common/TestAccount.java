package portal.common;

public enum TestAccount {
  DEMO_USER("demo", "+d3m0++"), 
  ADMIN_USER("portaladmin", "+d3m0++");

  private String username;
  private String password;
  
  private TestAccount(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
