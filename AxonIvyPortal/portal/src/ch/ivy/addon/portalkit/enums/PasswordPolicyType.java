package ch.ivy.addon.portalkit.enums;

import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;

public enum PasswordPolicyType {
  MIN_CHARACTERS("MinCharacters",
      "/ch.ivy.addon.portalkit.ui.jsf/components/passwordValidation/passwordPolicyType/minCharacter",
      "/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/minCharacterRequired"),
  MIN_LOWERCASE_CHARACTERS("MinLowercaseCharacters",
      "/ch.ivy.addon.portalkit.ui.jsf/components/passwordValidation/passwordPolicyType/minLowercaseCharacter",
      "/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/minLowercaseCharacterRequired"),
  MIN_UPPERCASE_CHARACTERS("MinUppercaseCharacters",
      "/ch.ivy.addon.portalkit.ui.jsf/components/passwordValidation/passwordPolicyType/minUppercaseCharacter",
      "/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/minUppercaseCharacterRequired"),
  MIN_NUMBERS("MinNumbers", "/ch.ivy.addon.portalkit.ui.jsf/components/passwordValidation/passwordPolicyType/minNumber",
      "/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/minNumberRequired"),
  MIN_SPEACIAL_CHARACTERS("MinSpecialCharacters",
      "/ch.ivy.addon.portalkit.ui.jsf/components/passwordValidation/passwordPolicyType/minSpecialCharacter",
      "/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/minSpecialCharacterRequired");

  private final String name;
  private final String titleCmsUri;
  private final String validationMessageCmsUri;

  PasswordPolicyType(String name, String titleCmsUri, String validationMessageCmsUri) {
    this.name = name;
    this.titleCmsUri = titleCmsUri;
    this.validationMessageCmsUri = validationMessageCmsUri;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return Ivy.cms().co(titleCmsUri);
  }

  public String getValidationMessage(List<Object> params) {
    return Ivy.cms().co(validationMessageCmsUri, params);
  }
}
