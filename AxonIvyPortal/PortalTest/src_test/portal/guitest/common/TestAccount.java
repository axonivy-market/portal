package portal.guitest.common;

import java.util.Optional;

public enum TestAccount {

  DEMO_USER(nameOfDemoUser(), passwordOfDemoUser()),

  ADMIN_USER(nameOfAdminUser(), passwordOfAdminUser());

  private String username;
  private String password;

  private TestAccount(String username, String password) {
    this.username = username;
    this.password = password;
  }

  private static String nameOfDemoUser() {
    String userName = System.getProperty("demoUserName");
    return userName != null ? userName : "demo";
  }

  private static String passwordOfDemoUser() {
    String password = System.getProperty("demoUserPassword");
    return password != null ? password : "+d3m0++";
  }

  private static String nameOfAdminUser() {
    String userName = System.getProperty("adminUserName");
    return Optional.ofNullable(userName).orElse("portaladmin");
  }

  private static String passwordOfAdminUser() {
    String password = System.getProperty("adminUserPassword");
    return Optional.ofNullable(password).orElse("+d3m0++");
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
