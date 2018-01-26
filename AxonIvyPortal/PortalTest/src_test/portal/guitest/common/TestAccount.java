package portal.guitest.common;

import java.util.Optional;

public enum TestAccount {

  DEMO_USER(nameOfDemoUser(), passwordOfDemoUser(), "Portal Demo User"),

  ADMIN_USER(nameOfAdminUser(), passwordOfAdminUser(), nameOfAdminUser()),

  TEST_CHANGE_PASSWORD_USER("test_change_password_user", "123", "test_change_password_user");
  
  private String username;
  private String password;
  private String fullName;

  private TestAccount(String username, String password, String fullName) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
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

  public String getFullName() {
    return fullName;
  }
}
