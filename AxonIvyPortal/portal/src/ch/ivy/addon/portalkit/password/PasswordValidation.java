package ch.ivy.addon.portalkit.password;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordValidation extends AbstractConfiguration implements Serializable {

	private static final long serialVersionUID = 934631443427512843L;

	private Boolean enablePasswordValidation;
	private List<PasswordPolicy> passwordPolicies;

	public PasswordValidation() {
		setIsPublic(true);
	}

	public Boolean getEnablePasswordValidation() {
		return enablePasswordValidation;
	}

	public void setEnablePasswordValidation(Boolean enablePasswordValidation) {
		this.enablePasswordValidation = enablePasswordValidation;
	}

	public List<PasswordPolicy> getPasswordPolicies() {
		return passwordPolicies;
	}

	public void setPasswordPolicies(List<PasswordPolicy> passwordPolicies) {
		this.passwordPolicies = passwordPolicies;
	}
}
