package ch.ivy.addon.portalkit.bo;

public class PasswordValidationStatus {
	private Boolean status;
	private String message;

	public PasswordValidationStatus(Boolean status, String message) {
		this.setStatus(status);
		this.setMessage(message);
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
