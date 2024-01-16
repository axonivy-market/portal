package ch.ivy.addon.portalkit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.PasswordValidationStatus;
import ch.ivy.addon.portalkit.enums.PasswordPolicyType;
import ch.ivy.addon.portalkit.password.PasswordPolicy;
import ch.ivy.addon.portalkit.password.PasswordValidation;
import ch.ivyteam.ivy.environment.Ivy;

public class PasswordCheckingService {
  private static PasswordCheckingService instance;

  private PasswordCheckingService() {
  }

  public static PasswordCheckingService getInstance() {
    if (instance == null) {
      instance = new PasswordCheckingService();
    }
    return instance;
  }

  public PasswordValidationStatus isPasswordStrongEnough(String password) {

    PasswordValidation passwordValidation = PasswordValidationService.getInstance().loadCurrentConfiguration();
    String requiredMessage = buildPasswordRequirementMessages(passwordValidation.getPasswordPolicies());

    if (StringUtils.isBlank(password)) {
      return new PasswordValidationStatus(false, requiredMessage);
    }

    if (!passwordValidation.getEnablePasswordValidation()) {
      return new PasswordValidationStatus(true, requiredMessage);
    }

    Boolean result = checkPasswordPolicies(password, passwordValidation.getPasswordPolicies());
    return new PasswordValidationStatus(result, requiredMessage);
  }

  public String getPasswordRequirementMessages() {
    PasswordValidation passwordValidation = PasswordValidationService.getInstance().loadCurrentConfiguration();
    return buildPasswordRequirementMessages(passwordValidation.getPasswordPolicies());
  }

  private String buildPasswordRequirementMessages(List<PasswordPolicy> passwordPolicies) {
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/passwordMust"));
    messageBuilder.append(StringUtils.SPACE);

    for (int i = 0; i < passwordPolicies.size(); i++) {
      PasswordPolicy passwordPolicy = passwordPolicies.get(i);
      if (passwordPolicy.getActive()) {
        messageBuilder.append(passwordPolicy.getValidationMessage());

        if (i == passwordPolicies.size() - 1) {
          messageBuilder.append('.');
        } else {
          messageBuilder.append(", ");
        }
      }
    }
    return messageBuilder.toString();
  }

  private boolean checkPasswordPolicies(String password, List<PasswordPolicy> passwordPolicies) {

    int characterCount = password.length();
    int lowercaseCount = 0;
    int uppercaseCount = 0;
    int numberCount = 0;
    int specialCount = 0;

    for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);
      if (Character.isLowerCase(c)) {
        lowercaseCount++;
      } else if (Character.isUpperCase(c)) {
        uppercaseCount++;
      } else if (Character.isDigit(c)) {
        numberCount++;
      } else {
        specialCount++;
      }
    }

    for (PasswordPolicy passwordPolicy : passwordPolicies) {
      if (!checkPolicy(passwordPolicy, PasswordPolicyType.MIN_CHARACTERS, characterCount)
          || !checkPolicy(passwordPolicy, PasswordPolicyType.MIN_LOWERCASE_CHARACTERS, lowercaseCount)
          || !checkPolicy(passwordPolicy, PasswordPolicyType.MIN_UPPERCASE_CHARACTERS, uppercaseCount)
          || !checkPolicy(passwordPolicy, PasswordPolicyType.MIN_NUMBERS, numberCount)
          || !checkPolicy(passwordPolicy, PasswordPolicyType.MIN_SPEACIAL_CHARACTERS, specialCount)) {
        return false;
      }
    }
    return true;
  }

  private Boolean checkPolicy(PasswordPolicy policy, PasswordPolicyType type, int actualValue) {
    if (policy.getType().equals(type) && policy.getActive() && actualValue < policy.getValue()) {
      return false;
    }
    return true;
  }
}