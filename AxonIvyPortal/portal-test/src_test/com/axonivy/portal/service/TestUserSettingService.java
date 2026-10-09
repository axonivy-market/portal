package com.axonivy.portal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.IUser;

@IvyTest
public class TestUserSettingService {
  UserSettingService service = UserSettingService.getInstance();
  IUser user;

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.loginUser("demo");
    user = Ivy.session().getSessionUser();
  }

  @AfterEach
  void clear() {
    user.removeProperty(UserProperty.DEFAULT_PROCESS_MODE);
    user.removeProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS);
  }

  @Test
  void saveProcessMode_valueIsDefault_userPropertyIsEmpty() {
    String processMode = "DEFAULT";
    service.saveProcessModeSetting(processMode);
    String processModeInUserProfile = service.getDefaultProcessMode();
    assertEquals(StringUtils.EMPTY, processModeInUserProfile);
  }

  @Test
  void saveProcessMode_valueIsImage_userPropertyIsImage() {
    service.saveProcessModeSetting(ProcessMode.IMAGE.name());
    String processModeInUserProfile = service.getDefaultProcessMode();
    assertEquals(ProcessMode.IMAGE.name(), processModeInUserProfile);
  }

  @Test
  void getDefaultProcessMode_valueIsAnEnumValue_returnEnumValue() {
    user.setProperty(UserProperty.DEFAULT_PROCESS_MODE, ProcessMode.COMPACT.name());
    String userProperty = service.getDefaultProcessMode();
    assertEquals(ProcessMode.COMPACT.name(), userProperty);
  }

  @Test
  void getDefaultProcessMode_valueIsBlank_returnEmpty() {
    String userProperty = service.getDefaultProcessMode();
    assertTrue(userProperty.isEmpty());
  }

  @Test
  void isDefaultProcessModeOption_valueIsDefault_ReturnTrue() {
    assertTrue(service.isDefaultProcessModeOption("DEFAULT"));
  }

  @Test
  void isDefaultProcessModeOption_valueIsNull_ReturnFalse() {
    assertFalse(service.isDefaultProcessModeOption(null));
  }

  @Test
  void isKeyboardShortcutsEnabled_userPropertyIsBlank_returnTrue() {
    assertTrue(service.isKeyboardShortcutsEnabled());
  }

  @Test
  void isKeyboardShortcutsEnabled_userPropertyIsFalse_returnFalse() {
    user.setProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS, Boolean.FALSE.toString());
    assertFalse(service.isKeyboardShortcutsEnabled());
  }
}
