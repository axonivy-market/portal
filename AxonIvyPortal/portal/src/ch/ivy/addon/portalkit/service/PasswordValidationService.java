package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.enums.PasswordPolicyType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.password.PasswordPolicy;
import ch.ivy.addon.portalkit.password.PasswordValidation;

public class PasswordValidationService extends JsonConfigurationService<PasswordValidation> {

	private static PasswordValidationService instance;

	private PasswordValidationService() {
	}

	public static PasswordValidationService getInstance() {
		if (instance == null) {
			instance = new PasswordValidationService();
		}
		return instance;
	}

	public PasswordValidation loadCurrentConfiguration() {
		List<PasswordValidation> passwordValidations = new ArrayList<>(getPublicConfig());
		return passwordValidations.isEmpty() ? initDefaultConfiguration() : updateCms(passwordValidations.get(0));
	}

	public PasswordValidation initDefaultConfiguration() {
		PasswordValidation passwordValidation = new PasswordValidation();
		List<PasswordPolicy> passwordPolicies = new ArrayList<PasswordPolicy>();
		passwordValidation.setEnablePasswordValidation(true);
		PasswordPolicy pw = new PasswordPolicy();
		pw.setType(PasswordPolicyType.MIN_CHARACTERS);
		pw.setActive(true);
		pw.setValue(4);
		pw.setTitle(PasswordPolicyType.MIN_CHARACTERS.getTitle());
		passwordPolicies.add(pw);

		pw = new PasswordPolicy();
		pw.setType(PasswordPolicyType.MIN_LOWERCASE_CHARACTERS);
		pw.setActive(true);
		pw.setValue(1);
		pw.setTitle(PasswordPolicyType.MIN_LOWERCASE_CHARACTERS.getTitle());
		passwordPolicies.add(pw);

		pw = new PasswordPolicy();
		pw.setType(PasswordPolicyType.MIN_UPPERCASE_CHARACTERS);
		pw.setActive(true);
		pw.setValue(1);
		pw.setTitle(PasswordPolicyType.MIN_UPPERCASE_CHARACTERS.getTitle());
		passwordPolicies.add(pw);

		pw = new PasswordPolicy();
		pw.setType(PasswordPolicyType.MIN_NUMBERS);
		pw.setActive(true);
		pw.setValue(1);
		pw.setTitle(PasswordPolicyType.MIN_NUMBERS.getTitle());
		passwordPolicies.add(pw);

		pw = new PasswordPolicy();
		pw.setType(PasswordPolicyType.MIN_SPEACIAL_CHARACTERS);
		pw.setActive(true);
		pw.setValue(1);
		pw.setTitle(PasswordPolicyType.MIN_SPEACIAL_CHARACTERS.getTitle());
		passwordPolicies.add(pw);
		passwordValidation.setPasswordPolicies(passwordPolicies);
		return passwordValidation;
	}
	
	public PasswordValidation updateCms(PasswordValidation pwValidation) {
		List<PasswordPolicy> passwordPolicies = pwValidation.getPasswordPolicies();
		for (PasswordPolicy passwordPolicy : passwordPolicies) {
			passwordPolicy.setTitle(passwordPolicy.getType().getTitle());
		}
		return pwValidation;
	}

	public PasswordValidation savePasswordValidation(PasswordValidation passwordValidation) {
		return save(passwordValidation);
	}

	@Override
	public Class<PasswordValidation> getType() {
		return PasswordValidation.class;
	}

	@Override
	public String getConfigKey() {
		return PortalVariable.PASSWORD_VALIDATION.key;
	}
}