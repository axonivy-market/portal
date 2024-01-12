package com.axonivy.portal.developerexamples.enums;

import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;

public enum PasswordPolicyType {
  MIN_CHARACTERS("MinCharacters",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinimumLength",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinCharacterReq"),
  MIN_LOWERCASE_CHARACTERS("MinLowercaseCharacters",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinLowercaseCharacter",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinLowercaseCharacter"),
  MIN_UPPERCASE_CHARACTERS("MinUppercaseCharacters",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinUppercaseCharacter",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinUppercaseCharacterRequired"), MIN_NUMBERS(
          "MinNumbers", "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinNumber",
          "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinNumberRequire"),
  MIN_SPEACIAL_CHARACTERS("MinSpecialCharacters",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinSpecialCharacter",
      "/Dialogs/com/axonivy/portal/developerexamples/testdata/LeaveRequestForm/MinSpecialCharacterRequired");

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
