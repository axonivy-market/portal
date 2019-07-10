package portal.guitest.common;

import java.util.Optional;

public enum TestAccount {

	GUEST_USER(nameOfGuestUser(), passwordOfGuestUser(), "Portal Guest User"),

	DEMO_USER(nameOfDemoUser(), passwordOfDemoUser(), "Portal Demo User"),

	ADMIN_USER(nameOfAdminUser(), passwordOfAdminUser(), "Portal Admin User"),

	TEST_CHANGE_PASSWORD_USER("test_change_password_user", "123", "test_change_password_user"),

	TEST_RELATED_TASKS_USER("test_related_tasks_user", "+d3m0++", "test_related_tasks_user");

	private String username;
	private String password;
	private String fullName;

	private TestAccount(String username, String password, String fullName) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	private static String nameOfGuestUser() {
		String userName = System.getProperty("guestUserName");
		return userName != null ? userName : "guest";
	}

	private static String passwordOfGuestUser() {
		String password = System.getProperty("guestUserPassword");
		return password != null ? password : "guest";
	}

	private static String nameOfDemoUser() {
		String userName = System.getProperty("demoUserName");
		return userName != null ? userName : "demo";
	}

	private static String passwordOfDemoUser() {
		String password = System.getProperty("demoUserPassword");
		return password != null ? password : "demo";
	}

	private static String nameOfAdminUser() {
		String userName = System.getProperty("adminUserName");
		return Optional.ofNullable(userName).orElse("admin");
	}

	private static String passwordOfAdminUser() {
		String password = System.getProperty("adminUserPassword");
		return Optional.ofNullable(password).orElse("admin");
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
